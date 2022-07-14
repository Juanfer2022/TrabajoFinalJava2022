package com.informatorio.trabaoFinal.repository;

import com.informatorio.trabaoFinal.model.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IArticleRepository extends JpaRepository<Article, Long> {
}
