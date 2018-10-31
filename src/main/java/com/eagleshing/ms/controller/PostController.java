package com.eagleshing.ms.controller;

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

import com.eagleshing.ms.model.Cover;
import com.eagleshing.ms.model.CoverTags;
import com.eagleshing.ms.model.Devision;
import com.eagleshing.ms.model.DevisionParams;
import com.eagleshing.ms.model.DevisionParamsSet;
import com.eagleshing.ms.model.DevisionSet;
import com.eagleshing.ms.model.Module;
import com.eagleshing.ms.model.Tag;
import com.eagleshing.ms.model.repository.CoverRepository;
import com.eagleshing.ms.model.repository.CoverTagsRepository;
import com.eagleshing.ms.model.repository.DevisionParamsRepository;
import com.eagleshing.ms.model.repository.DevisionParamsSetRepository;
import com.eagleshing.ms.model.repository.DevisionRepository;
import com.eagleshing.ms.model.repository.DevisionSetRepository;
import com.eagleshing.ms.model.repository.ModuleRepository;
import com.eagleshing.ms.model.repository.TagRepository;
import com.eagleshing.ms.old.model.AdditionContent;
import com.eagleshing.ms.old.model.AdditionType;
import com.eagleshing.ms.old.model.OlderCover;
import com.eagleshing.ms.old.model.Tab;
import com.eagleshing.ms.old.payload.NewCoverRequest;
import com.eagleshing.ms.old.payload.NewDevisionsRequest;
import com.eagleshing.ms.old.payload.NewModuleRequest;
import com.eagleshing.ms.old.payload.NewParamsRequest;
import com.eagleshing.ms.old.payload.NewTagRequest;
import com.eagleshing.ms.old.repository.OldCoverRepository;
import com.eagleshing.ms.payload.ApiResponse;
import com.eagleshing.ms.payload.CoverRequest;
import com.eagleshing.ms.payload.CoverResponse;
import com.eagleshing.ms.payload.DevisionResponse;
import com.eagleshing.ms.payload.ModuleResponse;
import com.eagleshing.ms.payload.ParamResponse;
import com.eagleshing.ms.payload.ParamsRequest;
import com.eagleshing.ms.payload.TagResponse;

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
	public ResponseEntity<?> saveCover(@Valid @RequestBody CoverRequest request, @PathVariable boolean prepare) {
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
					List<CoverTags> ct = coverTagHelper.findByCoverIdAndTagId(savedCover.getId(), _tag.getId());
					if(ct == null) {
						coverTagHelper.save(new CoverTags(savedCover.getId(), _tag.getId()));
					}
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
				List<DevisionResponse> saveDevisionResponses = new ArrayList<>();
				for (DevisionSet devisionSet : devisionSets) {
					
					// save it
					Devision savedDevision = devisionHelper.save(new Devision(devisionSet, savedCover));
					savedDevision.setCoverId(savedCover.getId());
					// convert it to SaveDevisionResponse
					//fixme: use savedDevision and devisionset to construct SaveDevisionResponse.
					DevisionResponse saveDevisionResponse = new DevisionResponse(devisionSet,savedDevision);
					
					// Retrieve all parameters
					List<DevisionParamsSet> devisionParamsSets = devisionParamsSetHelper.findByDevisionSetId(devisionSet.getId());
					// Save all parameters and add them to SaveDevisionResponse's paramsList
					for (DevisionParamsSet paramSet : devisionParamsSets) {
						DevisionParams savedParam = devisionParamsHelper.save(new DevisionParams(paramSet, savedDevision));
						saveDevisionResponse.getParamsList().add(new ParamResponse(savedParam));
					}
					// finally add into SaveDevisionResponse list
					saveDevisionResponses.add(saveDevisionResponse);
				}
				CoverResponse saveCoverResponse = new CoverResponse(savedCover);
				saveCoverResponse.setDevisions(saveDevisionResponses);
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

	@Autowired
	private OldCoverRepository oldCoverHelper;
	
	@GetMapping("/getoldcover")
	public ResponseEntity<?> getAllOldCovers(){
		try {
			return ResponseEntity.ok(oldCoverHelper.findByStatus((byte)0));
		} catch (Exception e) {
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
				newDevision.setMark(devision.getMark());
				newDevision.setCoverId(savedCover.getId());
				newDevision.setDevSetId(devision.getDevSetId());
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
				
				List<NewParamsRequest> params = devision.getDevisionParams();
				for (NewParamsRequest param : params) {
					DevisionParams newParam = new DevisionParams();
					newParam.setType(param.getType());
					newParam.setName(param.getName());
					newParam.setData(param.getData());
					newParam.setDes(param.getDes());
					newParam.setMust(param.isMust());
					newParam.setParamSetId(param.getParamSetId());
					newParam.setSort(param.getSort());
					newParam.setDevisionId(savedDevision.getId());
					
					devisionParamsHelper.save(newParam);
				}

			}
			return ResponseEntity.ok("Success");

		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getCause().getMessage());
		}
	}
	
	@GetMapping("/fetchcoverforparams")
	@Transactional
	public ResponseEntity<?> fetchForParams(@Valid @RequestBody OlderCover request){
		try {
			
			List<Cover> covers = coverHelper.findAll();
			for (Cover cover : covers) {
				Set<OlderCover> oldCovers = oldCoverHelper.findByTitleAndStatus(cover.getTitle(),(byte)0);
				OlderCover oldCover = oldCovers.iterator().next();
				if(oldCover!=null) {
					List<Tab> tabs = oldCover.getTabs();
					for (Tab tab : tabs) {
						List<AdditionType> types = tab.getAdditions();
						Devision devision = devisionHelper.findByCoverIdAndName(cover.getId(), tab.getTitle());

						for (AdditionType aType : types) {

							List<AdditionContent> contents = aType.getAdditionInfo();
							
							for (AdditionContent c : contents) {
								DevisionParamsSet paramSet = devisionParamsSetHelper.findByName(c.getKeyName()).get(0);
								DevisionParams dParam = new DevisionParams();
								dParam.setType(aType.getTypeName());
								dParam.setName(c.getKeyName());
								dParam.setData(c.getKeyValue());
								dParam.setMust(c.getNeed());
								dParam.setDevisionId(devision.getId());
								dParam.setParamSetId(paramSet.getId());
								devisionParamsHelper.save(dParam);
							}
						}
					}
				}
			}
			return ResponseEntity.ok("success");
			
			
			
			
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getCause().getMessage());
		}
	}

	
	/*
	 * Retrieve Cover and it contains and tags.
	 */
	@GetMapping("/getcover/{id}")
	@Transactional
	public ResponseEntity<?> getCover(@PathVariable int id) {
		try {
			if (coverHelper.existsById(id)) {
				Cover existCover = coverHelper.findById(id).get();
				List<CoverTags> tagRelatives = coverTagHelper.findByCoverId(existCover.getId());
				Set<TagResponse> tags = new HashSet<>();
				for (CoverTags coverTags : tagRelatives) {
					tags.add(new TagResponse(tagHelper.findById(coverTags.getTagId()).get()));
				}
				CoverResponse coverResponse = new CoverResponse(existCover);
				coverResponse.setTags(tags);
				
				List<DevisionResponse> devisionResponses = new ArrayList<>();
				List<Devision> existDevisions = devisionHelper.findByCoverId(coverResponse.getId());
				for (Devision devision : existDevisions) {
					DevisionSet devSet = devisionSetHelper.findById(devision.getDevSetId()).get();
					DevisionResponse devisionResponse = new DevisionResponse(devSet,devision);
					
					List<DevisionParams> params = devisionParamsHelper.findByDevisionId(devision.getId());
					List<ParamResponse> paramResponses = new ArrayList<>();
					for (DevisionParams param : params) {
						paramResponses.add(new ParamResponse(param));
					}
					devisionResponse.setParamsList(paramResponses);
					
					List<Module> modules = moduleHelper.findByDevisionId(devision.getId());
					List<ModuleResponse> moduleResponses = new ArrayList<>();
					for (Module module : modules) {
						moduleResponses.add(new ModuleResponse(module));
					}
					devisionResponse.setModuleList(moduleResponses);
					
					devisionResponses.add(devisionResponse);
				}
				coverResponse.setDevisions(devisionResponses);
				
				return ResponseEntity.ok(coverResponse);
				
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
	
	@GetMapping("/getdevisionbyname/{coverid}/{devname}")
	@Transactional
	public ResponseEntity<?> getDevisionsByName(@PathVariable int coverid,@PathVariable String devname){
		try {
			DevisionResponse devRes;
			
			DevisionSet devSet = devisionSetHelper.findByName(devname);
			Devision devision = devisionHelper.findByDevSetId(devSet.getId());			
			if(devision==null) {
				devision = devisionHelper.findByCoverIdAndName(coverid, devname);
				devRes = new DevisionResponse(devSet,devision);
			}else {
				devRes = new DevisionResponse(devSet,devision);
			}
			
			List<DevisionParams> params = devisionParamsHelper.findByDevisionId(devision.getId());
			List<ParamResponse> paramResList = new ArrayList<>();
			for (DevisionParams param : params) {
				paramResList.add(new ParamResponse(param));
			}
			
			if(paramResList.size()==0) {
				List<DevisionParamsSet> devSets = devisionParamsSetHelper.findByDevisionSetId(devSet.getId());
				for (DevisionParamsSet devisionParamsSet : devSets) {
					paramResList.add(new ParamResponse(devisionParamsHelper.save(new DevisionParams(devisionParamsSet,devision))));
				}
			}
			
			List<Module> modules = moduleHelper.findByDevisionId(devision.getId());
			List<ModuleResponse> moduleResList = new ArrayList<>();
			for (Module module : modules) {
				moduleResList.add(new ModuleResponse(module));
			}
			
			devRes.setModuleList(moduleResList);
			devRes.setParamsList(paramResList);
			return ResponseEntity.ok(devRes);
		}catch(Exception e) {
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
	public ResponseEntity<?> saveParams(@Valid @RequestBody ParamsRequest request) {
		try {
			Devision existDevision = devisionHelper.findById(request.getDevisionId()).get();
			List<ParamResponse> results = new ArrayList<>();
			for (ParamResponse param : request.getParams()) {
				param.setDevisionId(existDevision.getId());
				DevisionParams savedParam = devisionParamsHelper.save(new DevisionParams(param));
				results.add(new ParamResponse(savedParam));
			}
			return ResponseEntity.ok(results);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getCause().getMessage());
		}
	}
}
