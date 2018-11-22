package com.eagleshing.ms.controller;

import com.eagleshing.ms.model.MiniUser;
import com.eagleshing.ms.model.UserAnswer;
import com.eagleshing.ms.model.repository.MiniUserRepository;
import com.eagleshing.ms.model.repository.UserAnswerRepository;
import com.eagleshing.ms.model.repository.UserQuestionRepository;
import com.eagleshing.ms.model.type.QuestionType;
import com.eagleshing.ms.payload.UserQuestionResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.eagleshing.ms.exception.ResourceNotFoundException;
import com.eagleshing.ms.model.User;
import com.eagleshing.ms.payload.UserProfile;
import com.eagleshing.ms.model.repository.UserRepository;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
public class UserController {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private UserQuestionRepository userQuestionRepository;

	@Autowired
	private UserAnswerRepository userAnswerRepository;

	@Autowired
	private MiniUserRepository miniUserRepository;

	@GetMapping("/users/{username}")
	public UserProfile getUserProfile(@PathVariable(value="username") String username) {
		User user = userRepository.findByUsername(username).orElseThrow(() -> new ResourceNotFoundException("User", "username", username));
		UserProfile userProfile = new UserProfile(user.getId(), user.getUsername(), user.getName(), user.getEmail(),user.getCreatedAt(),user.getRoles());
		return userProfile;
	}

	@GetMapping("/users/userquestions")
	public ResponseEntity<?> userQuestions(Pageable pageable){
		return ResponseEntity.ok(userQuestionRepository.findAll(pageable));
	}

	@GetMapping("/users/questionansers")
	public ResponseEntity<?> questionAnswers(int id){
		return ResponseEntity.ok(userAnswerRepository.findByQuestion(userQuestionRepository.findById(id).get()));
	}

	@GetMapping("/users/miniuser")
	public ResponseEntity<?> miniUser(int id){
		MiniUser user = miniUserRepository.findById(id).get();
		return  ResponseEntity.ok(user);
	}

	@PostMapping("/users/saveanswer/{id}")
	@Transactional
	public ResponseEntity<?> saveAnswer(@Valid @RequestBody UserAnswer answer, @PathVariable int id){
		userAnswerRepository.deleteByQuestion(userQuestionRepository.findById(id).get());
		userQuestionRepository.findById(id).map(q->{
			answer.setQuestion(q);
			q.setType(QuestionType.PASSED);
			userQuestionRepository.save(q);
			return userAnswerRepository.save(answer);
		});

		return ResponseEntity.ok("Success!");
	}

	@DeleteMapping("/users/deletequestion/{id}")
	public ResponseEntity<?> deleteQuestion(@PathVariable int id){
		userQuestionRepository.deleteById(id);
		return ResponseEntity.ok("删除完成！");
	}
}
