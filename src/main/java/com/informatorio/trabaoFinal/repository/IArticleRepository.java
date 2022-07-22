package com.informatorio.trabaoFinal.repository;

import com.informatorio.trabaoFinal.model.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface IArticleRepository extends JpaRepository<Article, Long> {

    @Modifying
    @Query(value = "UPDATE Article SET published=true WHERE ID=:id")
    public void markAsPublished(@Param("id") Long id);

    ///buscar article por una palabra
    @Query("from Article a where a.published =1 and (a.title like %:title% or a.description like %:title%)" )
    Set<Article> getArticleByTitleLike(@Param("title") String title);
}
