package com.eagleshing.sm.old.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.eagleshing.sm.old.model.ExplainDic;

public interface VacabularyRepository extends JpaRepository<ExplainDic, Long>{
	
	java.util.List<ExplainDic> findByTitle(String title);

}
