package com.eagleshing.ms.model.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.eagleshing.ms.model.TagSet;

public interface TagSetRepository extends JpaRepository<TagSet, Integer>{
	
	List<TagSet> findByType(String type);
}
