package com.eagleshing.sm.model.repository;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;

import com.eagleshing.sm.model.QuestionOption;

public interface QuestionOptionRepository extends JpaRepository<QuestionOption, Integer>{
	Set<QuestionOption> findByQuestionId(int qid);
}
