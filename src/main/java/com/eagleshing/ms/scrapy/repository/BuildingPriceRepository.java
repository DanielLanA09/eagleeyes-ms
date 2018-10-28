package com.eagleshing.ms.scrapy.repository;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import com.eagleshing.ms.scrapy.model.BuildingPrice;

public interface BuildingPriceRepository extends JpaRepository<BuildingPrice, Integer> {
	List<BuildingPrice> findByBuildingId(int id,Sort sort);
}
