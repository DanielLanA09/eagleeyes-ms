package com.eagleshing.sm.old.repository;


import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.eagleshing.sm.old.model.BuildingBase;

public interface BuildingBaseRepository extends JpaRepository<BuildingBase, Integer> {
	
	@Query(value="SELECT * FROM eagleeyes.building_base where project like %?1%",nativeQuery = true)
	public Page<BuildingBase> findByProjectLike(String project,Pageable pageable);

	@Query(value="SELECT * FROM eagleeyes.building_base where project like %?1%",nativeQuery = true)
	public List<BuildingBase> findByProjectLike(String project);

}
