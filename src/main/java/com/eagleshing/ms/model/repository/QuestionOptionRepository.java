package com.eagleshing.ms.model.repository;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;

import com.eagleshing.ms.model.QuestionOption;

public interface QuestionOptionRepository extends JpaRepository<QuestionOption, Integer>{
	Set<QuestionOption> findByQuestionId(int qid);
}
