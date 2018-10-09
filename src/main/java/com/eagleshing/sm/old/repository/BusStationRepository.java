package com.eagleshing.sm.old.repository;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;

import com.eagleshing.sm.old.model.BusStation;

public interface BusStationRepository extends JpaRepository<BusStation, Integer> {
	public Set<BusStation> findByStationName(String name);
}
