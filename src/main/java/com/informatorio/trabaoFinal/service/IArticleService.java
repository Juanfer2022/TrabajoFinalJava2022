package com.informatorio.trabaoFinal.service;

import com.informatorio.trabaoFinal.model.ArticleDTO;
import org.springframework.data.repository.query.Param;

public interface IArticleService {
    public void createArticle(ArticleDTO articleDTO);
    public void updateFinished(Long id);

}
