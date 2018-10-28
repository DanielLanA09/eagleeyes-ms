package com.eagleshing.ms.controller;

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

import com.eagleshing.ms.model.DevisionParamsSet;
import com.eagleshing.ms.model.DevisionSet;
import com.eagleshing.ms.model.Question;
import com.eagleshing.ms.model.QuestionOption;
import com.eagleshing.ms.model.TagSet;
import com.eagleshing.ms.payload.ApiResponse;
import com.eagleshing.ms.payload.SaveQuestionRequest;
import com.eagleshing.ms.payload.SaveQuestionResponse;
import com.eagleshing.ms.model.repository.DevisionParamsSetRepository;
import com.eagleshing.ms.model.repository.DevisionSetRepository;
import com.eagleshing.ms.model.repository.QuestionOptionRepository;
import com.eagleshing.ms.model.repository.QuestionRepository;
import com.eagleshing.ms.model.repository.TagSetRepository;

@RestController
@RequestMapping("api/postmanage/")
public class PostManageController {
	
	@Autowired
	private DevisionSetRepository devisionSetHelper;
	
	@Autowired
	private DevisionParamsSetRepository devisionParamsSetHelper;
	
	@Autowired
	private TagSetRepository tagsetHelper;
	
	@Autowired
	QuestionRepository questionHelper;
	
	@Autowired
	QuestionOptionRepository optionHelper;
	
//	@Autowired
//	private DevisionParamsRepository devisionParamsHelper;
	
	@GetMapping("/getalldevisionset")
	public ResponseEntity<?> getAllDevisionSet(){
		try {
			return ResponseEntity.ok(devisionSetHelper.findAll());
		}catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse(false, e.getCause().getMessage(), null));
		}
	}
	
	@PostMapping("/savedevisionset")
	public ResponseEntity<?> saveDevisionSet(@Valid @RequestBody DevisionSet devisionSet) {
		try {
			DevisionSet result = devisionSetHelper.save(devisionSet);
			return ResponseEntity.ok(result);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse(false, e.getCause().getMessage(), null));
		}
	}
	
	@DeleteMapping("/deletedevisionset/{id}")
	public ResponseEntity<?> deleteDevisionSet(@PathVariable int id){
		try {
			if(devisionSetHelper.existsById(id)) {
				//Delete all DevisionParamsSet then delete DevisionSet
				List<DevisionParamsSet> paramsSets = devisionParamsSetHelper.findByDevisionSetId(devisionSetHelper.findById(id).get().getId());
				for (DevisionParamsSet devisionParamsSet : paramsSets) {
					devisionParamsSetHelper.delete(devisionParamsSet);
				}
				devisionSetHelper.deleteById(id);
				return ResponseEntity.ok("Delete Success!");
			}else {
				return ResponseEntity.notFound().build();
			}
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse(false, e.getCause().getMessage(), null));
		}
	}
	
	@GetMapping("/getdevision/{id}")
	public ResponseEntity<?> getDevisionById(@PathVariable int id){
		try {
			return ResponseEntity.ok(devisionSetHelper.findById(id));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse(false, e.getCause().getMessage(), null));
		}
	}
	
	@PostMapping("/savaparamset/{devisionsetid}")
	@Transactional
	public ResponseEntity<?> saveParamsSet(@Valid @RequestBody DevisionParamsSet paramsSet,@PathVariable int devisionsetid){
		try {
			Optional<DevisionSet> devisionSet = devisionSetHelper.findById(devisionsetid);
			paramsSet.setDevisionSetId(devisionSet.get().getId());			
			return ResponseEntity.ok(devisionParamsSetHelper.save(paramsSet));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse(false, e.getCause().getMessage(), null));
		}
	}
	
	@DeleteMapping("/deleteparamset/{id}")
	public ResponseEntity<?> deleteParamSet(@PathVariable int id){
		try {
			devisionParamsSetHelper.deleteById(id);
			return ResponseEntity.ok("Delete Success!");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse(false, e.getCause().getMessage(), null));
		}
	}
	
	@GetMapping("/getparamlist/{devid}")
	@Transactional
	public ResponseEntity<?> getParamsList(@PathVariable int devid){
		try {
			Optional<DevisionSet> devisionSet = devisionSetHelper.findById(devid);
			List<DevisionParamsSet> params = devisionParamsSetHelper.findByDevisionSetId(devisionSet.get().getId());
			return ResponseEntity.ok(params);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse(false, e.getCause().getMessage(), null));
		}
	}
	
	@GetMapping("/getparamlistbyname/{name}")
	public ResponseEntity<?> getParamsListByName(@PathVariable String name){
		try {
			DevisionSet devisionSet = devisionSetHelper.findByName(name);
			List<DevisionParamsSet> params = devisionParamsSetHelper.findByDevisionSetId(devisionSet.getId());
			return ResponseEntity.ok(params);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse(false, e.getCause().getMessage(), null));
		}
	}
	
	@PostMapping("/savetagset")
	public ResponseEntity<?> saveTagSet(@Valid @RequestBody TagSet tagSet){
		try {
			return ResponseEntity.ok(tagsetHelper.save(tagSet));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse(false, e.getCause().getMessage(), null));
		}
	}
	
	@DeleteMapping("/deletetagset/{id}")
	public ResponseEntity<?> deleteTagSet(@PathVariable int id){
		try {
			tagsetHelper.deleteById(id);
			return ResponseEntity.ok("Success!");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse(false, e.getCause().getMessage(), null));
		}
	}
	
	@GetMapping("/gettagsets")
	public ResponseEntity<?> gettagsets(Pageable pageable){
		try {
			Page<TagSet> results = tagsetHelper.findAll(pageable);
			return ResponseEntity.ok(results);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse(false, e.getCause().getMessage(), null));
		}
	}
	
	@GetMapping("/gettagbytype/{type}")
	public ResponseEntity<?> getTagByType(@PathVariable String type){
		try {
			List<TagSet> results = tagsetHelper.findByType(type);
			return ResponseEntity.ok(results);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse(false, e.getCause().getMessage(), null));
		}
	}
	
	@PostMapping("/addquestion")
	@Transactional
	public ResponseEntity<?> addQuestion(@RequestBody SaveQuestionRequest request){
		try {
			Question question = new Question();
			question.setId(request.getId());
			question.setTitle(request.getTitle());
			question.setMultiple(request.isMultiple());
			question.setMax(request.getMax());
			Question result = questionHelper.save(question);
			for (QuestionOption op : request.getOptions()) {
				op.setQuestionId(result.getId());
				optionHelper.save(op);
			}
			return ResponseEntity.ok(result);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse(false, e.getCause().getMessage(), null));
		}
	}
	
	@DeleteMapping("/deletequestion/{id}")
	@Transactional
	public ResponseEntity<?> deleteQuestion(@PathVariable int id){
		try {
			questionHelper.deleteById(id);
			Set<QuestionOption> options = optionHelper.findByQuestionId(id);
			for (QuestionOption op : options) {
				optionHelper.delete(op);
			}
			return ResponseEntity.ok("complete!");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse(false, e.getCause().getMessage(), null));
		}
	}
	
	@GetMapping("/getquestions")
	@Transactional
	public ResponseEntity<?> getQuestions(){
		try {
			Set<SaveQuestionResponse> results = new HashSet<>();
			List<Question> questions = questionHelper.findAll();
			for (Question question : questions) {
				Set<QuestionOption> options = optionHelper.findByQuestionId(question.getId());
				SaveQuestionResponse res = new SaveQuestionResponse();
				res.setId(question.getId());
				res.setTitle(question.getTitle());
				res.setMultiple(question.isMultiple());
				res.setMax(question.getMax());
				res.setOptions(options);
				results.add(res);
			}
			return ResponseEntity.ok(results);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse(false, e.getCause().getMessage(), null));
		}
	}
}
