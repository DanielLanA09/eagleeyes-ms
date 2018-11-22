package com.eagleshing.ms.model.repository;

import com.eagleshing.ms.model.UserAnswer;
import com.eagleshing.ms.model.UserQuestion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;

public interface UserAnswerRepository extends JpaRepository<UserAnswer,Integer> {
    List<UserAnswer> findByQuestion(UserQuestion question);

    void deleteByQuestion(UserQuestion question);
}
