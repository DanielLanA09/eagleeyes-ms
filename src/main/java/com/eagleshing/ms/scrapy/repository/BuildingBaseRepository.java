package com.eagleshing.ms.scrapy.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.eagleshing.ms.scrapy.model.BuildingBase;

public interface BuildingBaseRepository extends JpaRepository<BuildingBase, Integer>{
	@Query("select b from BuildingBase b where b.project like %?1%")
	List<BuildingBase> findByProjectLike(String project);
}
