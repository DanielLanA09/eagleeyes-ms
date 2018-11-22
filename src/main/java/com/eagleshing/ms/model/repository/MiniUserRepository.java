package com.eagleshing.ms.model.repository;

import com.eagleshing.ms.model.MiniUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface MiniUserRepository extends JpaRepository<MiniUser,Integer> {

}
