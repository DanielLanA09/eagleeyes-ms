package com.eagleshing.ms.model.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.eagleshing.ms.model.HomeBlock;

public interface HomeBlockRepository extends JpaRepository<HomeBlock, Integer> {
    List<HomeBlock> findByActive(boolean active);
}
