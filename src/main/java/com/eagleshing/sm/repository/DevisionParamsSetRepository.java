package com.eagleshing.sm.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.eagleshing.sm.model.DevisionParamsSet;

public interface DevisionParamsSetRepository extends JpaRepository<DevisionParamsSet, Integer> {
	List<DevisionParamsSet> findByDevisionSetId(int id);
	List<DevisionParamsSet> findByName(String name);
}
