package com.eagleshing.ms.model.repository;

import java.util.List;

import com.eagleshing.ms.model.Devision;
import org.springframework.data.jpa.repository.JpaRepository;

import com.eagleshing.ms.model.DevisionSet;

public interface DevisionSetRepository extends JpaRepository<DevisionSet, Integer> {

	DevisionSet findByName(String name);
}
