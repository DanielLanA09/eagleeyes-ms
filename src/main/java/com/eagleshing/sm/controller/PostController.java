package com.eagleshing.sm.controller;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eagleshing.sm.model.Cover;
import com.eagleshing.sm.model.CoverTags;
import com.eagleshing.sm.model.Devision;
import com.eagleshing.sm.model.DevisionParams;
import com.eagleshing.sm.model.DevisionParamsSet;
import com.eagleshing.sm.model.DevisionSet;
import com.eagleshing.sm.model.Module;
import com.eagleshing.sm.model.Tag;
import com.eagleshing.sm.old.payload.NewCoverRequest;
import com.eagleshing.sm.old.payload.NewDevisionsRequest;
import com.eagleshing.sm.old.payload.NewModuleRequest;
import com.eagleshing.sm.old.payload.NewTagRequest;
import com.eagleshing.sm.payload.ApiResponse;
import com.eagleshing.sm.payload.SaveCoverRequest;
import com.eagleshing.sm.payload.SaveCoverResponse;
import com.eagleshing.sm.payload.SaveDevisionResponse;
import com.eagleshing.sm.payload.SaveParamsRequest;
import com.eagleshing.sm.model.repository.CoverRepository;
import com.eagleshing.sm.model.repository.CoverTagsRepository;
import com.eagleshing.sm.model.repository.DevisionParamsRepository;
import com.eagleshing.sm.model.repository.DevisionParamsSetRepository;
import com.eagleshing.sm.model.repository.DevisionRepository;
import com.eagleshing.sm.model.repository.DevisionSetRepository;
import com.eagleshing.sm.model.repository.ModuleRepository;
import com.eagleshing.sm.model.repository.TagRepository;

@RestController
@RequestMapping("api/post")
public class PostController {

	@Autowired
	private CoverRepository coverHelper;

	@Autowired
	private DevisionRepository devisionHelper;

	@Autowired
	private ModuleRepository moduleHelper;

	@Autowired
	private TagRepository tagHelper;

	@Autowired
	private CoverTagsRepository coverTagHelper;

	@Autowired
	private DevisionSetRepository devisionSetHelper;

	@Autowired
	private DevisionParamsSetRepository devisionParamsSetHelper;

	@Autowired
	private DevisionParamsRepository devisionParamsHelper;

	/*
		Get all covers by title.
	 */
	@GetMapping("/getall/{title}")
	public ResponseEntity<?> getAll(Pageable pageable, @PathVariable String title) {
		try {
			Page<Cover> coverlist = coverHelper.findByTitleLike(title, pageable);
			return ResponseEntity.ok(coverlist);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getCause().getMessage());
		}
	}

	/*
		Get all covers.
	 */
	@GetMapping("/getall")
	public ResponseEntity<?> getAll(Pageable pageable) {
		try {
			Page<Cover> coverlist = coverHelper.findAll(pageable);
			return ResponseEntity.ok(coverlist);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getCause().getMessage());
		}
	}

	/*
	 * Save new cover entity
	 */
	@PostMapping("/savecover/{prepare}")
	@Transactional
	public ResponseEntity<?> saveCover(@Valid @RequestBody SaveCoverRequest request, @PathVariable boolean prepare) {
		try {
			if (devisionSetHelper.findAll().size() == 0) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("请先设置栏目再返回添加文章！");
			}
			Cover savedCover = coverHelper.save(request.getCover());
			// save tags and cover_tags table
			Set<Tag> tags = request.getTags();
			for (Tag tag : tags) {
				Tag _tag = tagHelper.findByName(tag.getName());
				if (_tag != null) {
					coverTagHelper.save(new CoverTags(savedCover.getId(), _tag.getId()));
				} else {
					_tag = tagHelper.save(tag);
					coverTagHelper.save(new CoverTags(savedCover.getId(), _tag.getId()));
				}
			}
			if (prepare) {
				// Retrieve all devisions from DevsionSet and All parameters from
				// DevisionParamsSet
				// Fist, find all devision sets
				List<DevisionSet> devisionSets = devisionSetHelper.findAll();

				// Second, save them and add them to SaveDevisionResponse list
				List<SaveDevisionResponse> saveDevisionResponses = new ArrayList<>();
				for (DevisionSet devisionSet : devisionSets) {
					// save it
					Devision savedDevision = devisionHelper.save(new Devision(devisionSet, savedCover));
					// convert it to SaveDevisionResponse
					//fixme: use savedDevision and devisionset to construct SaveDevisionResponse.
					SaveDevisionResponse saveDevisionResponse = new SaveDevisionResponse(savedDevision);
					// Retrieve all parameters
					List<DevisionParamsSet> devisionParamsSets = devisionParamsSetHelper
							.findByDevisionSetId(devisionSet.getId());
					// Save all parameters and add them to SaveDevisionResponse's paramsList
					for (DevisionParamsSet paramSet : devisionParamsSets) {
						DevisionParams savedParam = devisionParamsHelper
								.save(new DevisionParams(paramSet, savedDevision));
						saveDevisionResponse.getParamsList().add(savedParam);
					}
					// finally add into SaveDevisionResponse list
					saveDevisionResponses.add(saveDevisionResponse);
				}
				SaveCoverResponse saveCoverResponse = new SaveCoverResponse(savedCover, saveDevisionResponses, tags);
				return ResponseEntity.ok(saveCoverResponse);
			}
			return ResponseEntity.ok(savedCover);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getCause().getMessage());
		}
	}

	@PostMapping("/publish")
	public ResponseEntity<?> publish(@Valid @RequestBody Cover request){
		try{
			if(coverHelper.existsById(request.getId())){
				coverHelper.findById(request.getId()).map(res->{
					res.setStatus((byte) 0);
					return coverHelper.save(res);
				});
				return ResponseEntity.ok("发布成功！");
			}else{
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("未找到这条数据，请重试！");
			}
		}catch (Exception e){
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getCause().getMessage());
		}
	}

	//todo: Delete this after imported old database.
	@PostMapping("/saveall")
	@Transactional
	public ResponseEntity<?> saveAll(@Valid @RequestBody NewCoverRequest request) {
		try {
			Cover newCover = new Cover();
			newCover.setTitle(request.getTitle());
			newCover.setImg(request.getImg());
			newCover.setLatitude(request.getLatitude());
			newCover.setLongitude(request.getLongitude());
			newCover.setStatus(request.getStatus());
			newCover.setDes(request.getDes());
			newCover.setViewC(request.getViewC());
			newCover.setTransmitC(request.getTransmitC());
			newCover.setAddress(request.getAddress());
			newCover.setProject(request.getProject());
			newCover.setProjectDistrict(request.getProjectDistrict());
			newCover.setDistrict(request.getDistrict());
			newCover.setPrice(request.getPrice());

			Cover savedCover = coverHelper.save(newCover);
			List<NewTagRequest> tags = request.getTags();
			for (NewTagRequest tag : tags) {
				Tag _tag = tagHelper.findByName(tag.getName());
				if (_tag != null) {
					coverTagHelper.save(new CoverTags(savedCover.getId(), _tag.getId()));
				} else {
					Tag newTag = new Tag();
					newTag.setName(tag.getName());
					newTag.setType(tag.getType());
					_tag = tagHelper.save(newTag);
					coverTagHelper.save(new CoverTags(savedCover.getId(), _tag.getId()));
				}
			}
			List<NewDevisionsRequest> devisions = request.getDevisions();
			for (NewDevisionsRequest devision : devisions) {
				Devision newDevision = new Devision();
				newDevision.setName(devision.getName());
				newDevision.setDes(devision.getDes());
				newDevision.setSort(devision.getSort());
				newDevision.setType(devision.getType());
				newDevision.setNorm(devision.getNorm());
				newDevision.setMark(devision.getMark());
				newDevision.setCoverId(savedCover.getId());

				Devision savedDevision = devisionHelper.save(newDevision);

				List<NewModuleRequest> modules = devision.getModules();
				for (NewModuleRequest module : modules) {
					Module newModule = new Module();
					newModule.setName(module.getName());
					newModule.setBranch(module.getBranch());
					newModule.setSort(module.getSort());
					newModule.setStatus(module.getStatus());
					newModule.setDes(module.getDes());
					newModule.setJsonContent(module.getJsonContent());
					newModule.setDevisionId(savedDevision.getId());

					moduleHelper.save(newModule);
				}

			}
			return ResponseEntity.ok("Success");

		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getCause().getMessage());
		}
	}

	/*
	 * Retrieve Cover and it contains devisions, parameters, modules,tags.
	 */
	@GetMapping("/getcover/{id}")
	@Transactional
	public ResponseEntity<?> getCover(@PathVariable int id) {
		try {
			if (coverHelper.existsById(id)) {
				Cover existCover = coverHelper.findById(id).get();
				List<Devision> existDevisions = devisionHelper.findByCoverId(existCover.getId());

				List<CoverTags> tagRelatives = coverTagHelper.findByCoverId(existCover.getId());
				Set<Tag> tags = new HashSet<>();
				for (CoverTags coverTags : tagRelatives) {
					tags.add(tagHelper.findById(coverTags.getTagId()).get());
				}
				SaveCoverResponse result = new SaveCoverResponse(existCover, tags);
				for (Devision devision : existDevisions) {
					List<DevisionParams> existParams = devisionParamsHelper.findByDevisionId(devision.getId());
					List<Module> existModules = moduleHelper.findByDevisionId(devision.getId());

					SaveDevisionResponse saveDevisionResponse = new SaveDevisionResponse(devision);
					saveDevisionResponse.setModuleList(existModules);
					saveDevisionResponse.setParamsList(existParams);

					result.getDevisions().add(saveDevisionResponse);
				}

				return ResponseEntity.ok(result);

			} else {
				return ResponseEntity.notFound().build();
			}
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getCause().getMessage());
		}
	}

	/*
	 * Delete params,modules,devisions three parts from server.
	 */
	@DeleteMapping("/deletecover/{id}")
	@Transactional
	public ResponseEntity<?> deleteCover(@PathVariable int id) {
		try {
			if (coverHelper.existsById(id)) {
				Cover existCover = coverHelper.findById(id).get();
				List<Devision> devisions = devisionHelper.findByCoverId(existCover.getId());
				List<CoverTags> coverTags = coverTagHelper.findByCoverId(existCover.getId());
				for (CoverTags _coverTags : coverTags) {
					coverTagHelper.delete(_coverTags);
				}
				for (Devision devision : devisions) {
					List<Module> modules = moduleHelper.findByDevisionId(devision.getId());
					// Delete Modules First
					for (Module existModule : modules) {
						moduleHelper.delete(existModule);
					}
					// Delete Params then
					List<DevisionParams> params = devisionParamsHelper.findByDevisionId(devision.getId());
					for (DevisionParams existParam : params) {
						devisionParamsHelper.delete(existParam);
					}
					// Delete Devision then
					devisionHelper.delete(devision);
				}
				// Finally delete this cover;
				coverHelper.deleteById(id);
				return ResponseEntity.ok("success!");
			} else {
				return ResponseEntity.notFound().build();
			}
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getCause().getMessage());
		}
	}

	@PostMapping("/savedevision/{id}")
	@Transactional
	public ResponseEntity<?> saveDevision(@Valid @RequestBody Devision devision, @PathVariable int id) {
		try {
			Optional<Cover> cover = coverHelper.findById(id);
			devision.setCoverId(cover.get().getId());
			Devision result = devisionHelper.save(devision);
			return ResponseEntity.ok(result);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getCause().getMessage());
		}
	}

	@GetMapping("/getdevision/{id}")
	public ResponseEntity<?> getDevision(@PathVariable int id) {
		try {
			if (devisionHelper.existsById(id)) {
				return ResponseEntity.ok(new ApiResponse(true, "Success", devisionHelper.findById(id)));
			} else {
				return ResponseEntity.notFound().build();
			}
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getCause().getMessage());
		}
	}

	@DeleteMapping("/deletedevision/{id}")
	public ResponseEntity<?> deleteDevision(@PathVariable int id) {
		try {
			if (devisionHelper.existsById(id)) {
				devisionHelper.deleteById(id);
				return ResponseEntity.ok("delete success!");
			} else {
				return ResponseEntity.notFound().build();
			}
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getCause().getMessage());
		}
	}

	@PostMapping("/savemodule/{id}")
	@Transactional
	public ResponseEntity<?> saveModule(@Valid @RequestBody Module module, @PathVariable int id) {
		try {
			Optional<Devision> devision = devisionHelper.findById(id);
			module.setDevisionId(devision.get().getId());
			Module result = moduleHelper.save(module);
			return ResponseEntity.ok(result);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getCause().getMessage());
		}
	}

	@GetMapping("/getmodule/{id}")
	public ResponseEntity<?> getModule(@PathVariable int id) {
		try {
			if (moduleHelper.existsById(id)) {
				return ResponseEntity.ok(moduleHelper.findById(id));
			} else {
				return ResponseEntity.notFound().build();
			}
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getCause().getMessage());
		}
	}

	@DeleteMapping("/deletemodule/{id}")
	public ResponseEntity<?> deleteModule(@PathVariable int id) {
		try {
			if (moduleHelper.existsById(id)) {
				moduleHelper.deleteById(id);
				return ResponseEntity.ok("delete success!");
			} else {
				return ResponseEntity.notFound().build();
			}
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getCause().getMessage());
		}
	}

	@PostMapping("/saveparams")
	@Transactional
	public ResponseEntity<?> saveParams(@Valid @RequestBody SaveParamsRequest request) {
		try {
			Devision existDevision = devisionHelper.findById(request.getDevisionId()).get();
			List<DevisionParams> results = new ArrayList<>();
			for (DevisionParams param : request.getParams()) {
				param.setDevisionId(existDevision.getId());
				results.add(devisionParamsHelper.save(param));
			}
			return ResponseEntity.ok(results);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getCause().getMessage());
		}
	}
}
