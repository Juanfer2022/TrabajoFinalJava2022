package com.informatorio.trabaoFinal.service;

import com.informatorio.trabaoFinal.dto.ArticleDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Set;

public interface IArticleService {
    public void createArticle(ArticleDTO articleDTO,Long idAut, Long id);
    public void updateFinished(Long id);
    public void updateNotFinished(Long id);
    public void deleteArticle(Long ids,Long idAut, Long id);
    public ArticleDTO bringArticleById(Long id);
    public Page<ArticleDTO> getAllArticleLikePage(Pageable pageable, String wordToSearch);
   public ArticleDTO updateArticle(ArticleDTO articleDTO, Long id);
    public Set<ArticleDTO> showAllPublished();

}
