package com.eagleshing.ms.old.repository;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;

import com.eagleshing.ms.old.model.OlderCover;


public interface OldCoverRepository extends JpaRepository<OlderCover, Long> {
	
	public List<OlderCover> findByStatus(byte status);
	
	Set<OlderCover> findByTitleAndStatus(String title,byte status);
}
