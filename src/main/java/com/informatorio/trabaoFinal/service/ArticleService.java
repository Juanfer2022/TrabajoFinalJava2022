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
import java.util.Optional;

@Service
public class ArticleService implements IArticleService{

    @Autowired
    IArticleRepository iArticleRepository;

    @Autowired
    ObjectMapper mapper;


    // Crear un Article
    public void createArticle(ArticleDTO articleDTO) {

        Article article = mapper.convertValue(articleDTO, Article.class);
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
}
