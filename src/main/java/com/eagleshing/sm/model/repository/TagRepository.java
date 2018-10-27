package com.eagleshing.sm.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.eagleshing.sm.model.Tag;

public interface TagRepository extends JpaRepository<Tag,Integer>{
	public Tag findByName(String name);
}
