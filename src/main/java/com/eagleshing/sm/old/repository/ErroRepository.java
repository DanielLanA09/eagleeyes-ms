package com.eagleshing.sm.old.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.eagleshing.sm.old.model.SaveErroRecord;

public interface ErroRepository extends JpaRepository<SaveErroRecord, Long> {

}
