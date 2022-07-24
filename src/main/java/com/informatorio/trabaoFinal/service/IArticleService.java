package com.informatorio.trabaoFinal.service;

import com.informatorio.trabaoFinal.model.ArticleDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


import java.util.Collection;
import java.util.Set;

public interface IArticleService {
    public void createArticle(ArticleDTO articleDTO);
    public void updateFinished(Long id);
    public void deleteArticle(Long id);
    public Collection<ArticleDTO> getAllArticle();
    public Set<ArticleDTO> getArticleWithTitleLike(String title);

    public Page<ArticleDTO> getAllArticlePage(Pageable pageable);

}
