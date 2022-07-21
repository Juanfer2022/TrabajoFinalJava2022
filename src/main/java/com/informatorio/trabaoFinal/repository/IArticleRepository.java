package com.informatorio.trabaoFinal.repository;

import com.informatorio.trabaoFinal.model.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface IArticleRepository extends JpaRepository<Article, Long> {

    @Modifying
    @Query(value = "UPDATE Article SET published=true WHERE ID=:id")
    public void markAsPublished(@Param("id") Long id);
}
