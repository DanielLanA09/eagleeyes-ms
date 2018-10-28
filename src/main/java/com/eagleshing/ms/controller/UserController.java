package com.eagleshing.ms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eagleshing.ms.exception.ResourceNotFoundException;
import com.eagleshing.ms.model.User;
import com.eagleshing.ms.payload.UserProfile;
import com.eagleshing.ms.model.repository.UserRepository;

@RestController
@RequestMapping("/api")
public class UserController {

	@Autowired
	private UserRepository userRepository;
	
	@GetMapping("/users/{username}")
	public UserProfile getUserProfile(@PathVariable(value="username") String username) {
		User user = userRepository.findByUsername(username).orElseThrow(() -> new ResourceNotFoundException("User", "username", username));
		UserProfile userProfile = new UserProfile(user.getId(), user.getUsername(), user.getName(), user.getEmail(),user.getCreatedAt(),user.getRoles());
		return userProfile;
	}	
	
}
