package com.informatorio.trabaoFinal.service;

import com.informatorio.trabaoFinal.model.ArticleDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IArticleService {
    public void createArticle(ArticleDTO articleDTO);
    public void updateFinished(Long id);
    public void deleteArticle(Long id);
    public Page<ArticleDTO> getAllArticleLikePage(Pageable pageable,String title);
}
