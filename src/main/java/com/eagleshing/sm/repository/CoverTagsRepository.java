package com.eagleshing.sm.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.eagleshing.sm.model.CoverTags;

public interface CoverTagsRepository extends JpaRepository<CoverTags, Integer>{
	
	List<CoverTags> findByCoverId(int id);

}
