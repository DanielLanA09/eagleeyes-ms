package com.eagleshing.ms.old.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.eagleshing.ms.old.model.Tab;

public interface TabsRepository extends JpaRepository<Tab, Long> {
	
}
