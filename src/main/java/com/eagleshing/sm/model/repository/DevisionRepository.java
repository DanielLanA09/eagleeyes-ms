package com.eagleshing.sm.model.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.eagleshing.sm.model.Devision;

public interface DevisionRepository extends JpaRepository<Devision, Integer>{
	
	List<Devision> findByCoverId(int id);
	
	Devision findByCoverIdAndName(int id,String name);
}
