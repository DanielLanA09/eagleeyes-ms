package com.eagleshing.ms.model.repository;

import com.eagleshing.ms.model.ArticleLink;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ArticleLinkRepository extends JpaRepository<ArticleLink,Integer> {
    List<ArticleLink> findByBlockId(int id);
}
