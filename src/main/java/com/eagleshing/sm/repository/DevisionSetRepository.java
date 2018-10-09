package com.eagleshing.sm.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.eagleshing.sm.model.DevisionSet;

public interface DevisionSetRepository extends JpaRepository<DevisionSet, Integer> {
	List<DevisionSet> findByName(String name);
}
