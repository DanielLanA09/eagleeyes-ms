package com.eagleshing.sm.model.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.eagleshing.sm.model.TagSet;

public interface TagSetRepository extends JpaRepository<TagSet, Integer>{
	
	List<TagSet> findByType(String type);
}
