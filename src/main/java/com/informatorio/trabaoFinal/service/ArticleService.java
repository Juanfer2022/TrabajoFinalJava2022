package com.informatorio.trabaoFinal.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.informatorio.trabaoFinal.exceptions.Exceptions;
import com.informatorio.trabaoFinal.model.Article;
import com.informatorio.trabaoFinal.model.ArticleDTO;
import com.informatorio.trabaoFinal.repository.IArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.*;

@Service
public class ArticleService implements IArticleService{

    @Autowired
    IArticleRepository iArticleRepository;

    @Autowired
    ObjectMapper mapper;


    // Crear un Article
    public void createArticle(ArticleDTO articleDTO) {

        Article article = mapper.convertValue(articleDTO, Article.class);
        article.setPublishedAt(LocalDate.now());
        article.setPublished(false);
        iArticleRepository.save(article);
    }
    //Poner article como publicado
    @Transactional
    public void updateFinished(Long id){

        Optional<Article> article = iArticleRepository.findById(id);
        if (article.isEmpty()) {

            throw new Exceptions("Article no encontrado. id inexistente", HttpStatus.NOT_FOUND);
        }
        iArticleRepository.markAsPublished(id);

    }

    //B0rrar un Article
    public void deleteArticle(Long id) {
        Optional<Article> article = iArticleRepository.findById(id);
        if (article.isEmpty()) {
            throw new Exceptions("Article inexistente.El proceso de ELIMINACIO ha sido cancelado", HttpStatus.NOT_FOUND);
        }
        iArticleRepository.deleteById(id);
    }

    //Obtener todos los article
    public Collection<ArticleDTO> getAllArticle(){
        List<Article> articles= iArticleRepository.findAll();
        Set<ArticleDTO> articleDTOS = new HashSet<>();
        for (Article article: articles){
            articleDTOS.add(mapper.convertValue(article, ArticleDTO.class));
        }
        return articleDTOS;
    }

    public Set<ArticleDTO> getArticleWithTitleLike(String title) {

        if ((title.length() > 2) || (title=="")) {
            Set<Article> articles = iArticleRepository.getArticleByTitleLike(title);
            Set<ArticleDTO> articleDTOS = new HashSet<>();
            for (Article article : articles) {
                articleDTOS.add(mapper.convertValue(article, ArticleDTO.class));
            }
            return articleDTOS;
        } else {
            throw new Exceptions("La busqueda debe tener al menos 3 caracteres ", HttpStatus.NOT_FOUND);
        }
    }
}
