package com.informatorio.trabaoFinal.service;

import com.informatorio.trabaoFinal.model.ArticleDTO;
import org.springframework.data.repository.query.Param;

import java.util.Collection;

public interface IArticleService {
    public void createArticle(ArticleDTO articleDTO);
    public void updateFinished(Long id);
    public void deleteArticle(Long id);
    public Collection<ArticleDTO> getAllArticle();

}
