package com.eagleshing.ms.model.repository;

import com.eagleshing.ms.model.HomeBlock;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HomeBlockRepository extends JpaRepository<HomeBlock, Integer> {
    List<HomeBlock> findByActive(boolean active);
}
