package com.eagleshing.ms.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.eagleshing.ms.model.Question;

public interface QuestionRepository extends JpaRepository<Question,Integer> {

}
