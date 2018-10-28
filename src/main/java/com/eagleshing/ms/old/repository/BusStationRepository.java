package com.eagleshing.ms.old.repository;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;

import com.eagleshing.ms.old.model.BusStation;

public interface BusStationRepository extends JpaRepository<BusStation, Integer> {
	public Set<BusStation> findByStationName(String name);
}
