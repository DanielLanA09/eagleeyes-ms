package com.eagleshing.ms.old.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.eagleshing.ms.old.model.SaveErroRecord;

public interface ErroRepository extends JpaRepository<SaveErroRecord, Long> {

}
