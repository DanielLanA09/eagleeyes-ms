package com.eagleshing.ms.old.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.eagleshing.ms.old.model.ExplainDic;

public interface VacabularyRepository extends JpaRepository<ExplainDic, Long>{
	
	java.util.List<ExplainDic> findByTitle(String title);

}
