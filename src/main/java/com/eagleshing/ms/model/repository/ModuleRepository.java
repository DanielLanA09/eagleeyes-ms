package com.eagleshing.ms.model.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.eagleshing.ms.model.Module;

public interface ModuleRepository extends JpaRepository<Module, Integer>{
	List<Module> findByDevisionId(int id);
}
