package com.eagleshing.ms.model.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.eagleshing.ms.model.DevisionParamsSet;

public interface DevisionParamsSetRepository extends JpaRepository<DevisionParamsSet, Integer> {
	List<DevisionParamsSet> findByDevisionSetId(int id);
	List<DevisionParamsSet> findByName(String name);
}
