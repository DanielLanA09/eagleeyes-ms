package com.eagleshing.sm.old.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.eagleshing.sm.old.model.UserAccessHistory;

public interface UserAccessHistoryRepository extends JpaRepository<UserAccessHistory, Integer>{
	
}
