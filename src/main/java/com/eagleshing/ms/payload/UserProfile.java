package com.eagleshing.ms.payload;

import java.time.Instant;
import java.util.Set;

import com.eagleshing.ms.model.Role;

public class UserProfile {
	private long id;
	private String username;
	private String name;
	private String email;
	private Instant joinedAt;
	private Set<Role> roles;

	public UserProfile(Long id, String username, String name, String email, Instant joinedAt, Set<Role> roles) {
		this.id = id;
		this.username = username;
		this.name = name;
		this.joinedAt = joinedAt;
		this.email = email;
		this.roles = roles;
	}

	public long getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Instant getJoinedAt() {
		return joinedAt;
	}

	public void setJoinedAt(Instant joinedAt) {
		this.joinedAt = joinedAt;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}
	
	

}
