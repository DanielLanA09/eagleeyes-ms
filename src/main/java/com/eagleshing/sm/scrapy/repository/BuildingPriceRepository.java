package com.eagleshing.sm.scrapy.repository;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import com.eagleshing.sm.scrapy.model.BuildingPrice;

public interface BuildingPriceRepository extends JpaRepository<BuildingPrice, Integer> {
	List<BuildingPrice> findByBuildingId(int id,Sort sort);
}
