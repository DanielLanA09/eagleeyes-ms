package com.eagleshing.ms.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.eagleshing.ms.model.Tag;

public interface TagRepository extends JpaRepository<Tag,Integer>{
	public Tag findByName(String name);
}
