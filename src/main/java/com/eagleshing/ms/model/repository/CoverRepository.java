package com.eagleshing.ms.model.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.eagleshing.ms.model.Cover;

public interface CoverRepository extends JpaRepository<Cover,Integer>{
	
	@Query("select c from Cover c where c.title like %?1%")
	Page<Cover> findByTitleLike(String title,Pageable pageable);
}
