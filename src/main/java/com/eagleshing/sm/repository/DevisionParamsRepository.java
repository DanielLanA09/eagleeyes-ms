package com.eagleshing.sm.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.eagleshing.sm.model.DevisionParams;

public interface DevisionParamsRepository extends JpaRepository<DevisionParams, Integer> {
	List<DevisionParams> findByDevisionId(int id);
}
