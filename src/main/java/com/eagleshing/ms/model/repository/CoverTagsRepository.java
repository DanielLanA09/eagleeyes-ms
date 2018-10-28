package com.eagleshing.ms.model.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.eagleshing.ms.model.CoverTags;

public interface CoverTagsRepository extends JpaRepository<CoverTags, Integer>{
	
	List<CoverTags> findByCoverId(int id);

}
