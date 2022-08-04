package com.informatorio.trabaoFinal.service;

import com.informatorio.trabaoFinal.model.Article;
import com.informatorio.trabaoFinal.dto.ArticleDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Set;

public interface IArticleService {
    public void createArticle(ArticleDTO articleDTO);
    public void updateFinished(Long id);
    public void deleteArticle(Long id);
    public Page<ArticleDTO> getAllArticleLikePage(Pageable pageable,String title);
    public ArticleDTO bringArticleById(Long id);

   public ArticleDTO updateArticle(ArticleDTO articleDTO, Long id);
    public Set<ArticleDTO> showAllPublished();
}
