package com.eagleshing.sm.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.eagleshing.sm.model.Question;

public interface QuestionRepository extends JpaRepository<Question,Integer> {

}
