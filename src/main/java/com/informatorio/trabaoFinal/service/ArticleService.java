package com.informatorio.trabaoFinal.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.informatorio.trabaoFinal.exceptions.Exceptions;
import com.informatorio.trabaoFinal.model.Article;
import com.informatorio.trabaoFinal.dto.ArticleDTO;
import com.informatorio.trabaoFinal.repository.IArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

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

        //Trae un article por id
    public ArticleDTO bringArticleById(Long id) {
        Optional<Article> article = iArticleRepository.findById(id);
        if (article.isEmpty()) {

            throw new Exceptions("Article no encontrada. Id inexistente", HttpStatus.NOT_FOUND);
        }
        ArticleDTO articleDTO = null;
        articleDTO = mapper.convertValue(article, ArticleDTO.class);
        return articleDTO;
    }
    //Modificar un Article
    public Article updateArticle(ArticleDTO articleDTO) {
        Optional<Article> article = iArticleRepository.findById(articleDTO.getId());
        if (article.isEmpty()) {
            throw new Exceptions("El Author que quiere" +
                    " actualizar no existe. La Actualiacion se canceló", HttpStatus.NOT_FOUND);
        }
        Article article1 = mapper.convertValue(articleDTO, Article.class);

        return iArticleRepository.save(article1);
    }
    //Borrar un Article
    public void deleteArticle(Long id) {
        Optional<Article> article = iArticleRepository.findById(id);
        if (article.isEmpty()) {
            throw new Exceptions("Article inexistente.El proceso de ELIMINACIO ha sido cancelado", HttpStatus.NOT_FOUND);
        }
        iArticleRepository.deleteById(id);
    }

    // buscar article por un string mayor a 2 caracteres,
    // que haya sido publicado y por los campos title y description
    public Page<ArticleDTO>  getAllArticleLikePage(Pageable pageable, String title) {

            Page<Article> page = iArticleRepository.getArticleByTitleLikePage(pageable, title);
            int totalCont = (int) page.getTotalElements();
            List<ArticleDTO> articleDTOList = Collections.emptyList();

        if ((title.length() < 3) || (title == "" )){
            throw new Exceptions("La busqueda debe tener al menos 3 caracteres ", HttpStatus.NOT_FOUND);
        }
           else if(totalCont == 0) {
            throw new Exceptions("No hay registros que coincidan con la busqueda lanzada ", HttpStatus.NOT_FOUND);
        }else {
                List<ArticleDTO> articleDTOS =  page.getContent().stream().map(article -> mapper
                        .convertValue(article, ArticleDTO.class)).collect(Collectors.toList());
                articleDTOList = articleDTOS;

            return new PageImpl<>(articleDTOList);
            }
    }



}
