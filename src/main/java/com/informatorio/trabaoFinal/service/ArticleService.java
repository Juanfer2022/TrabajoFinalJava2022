package com.informatorio.trabaoFinal.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.informatorio.trabaoFinal.dto.ArticleDTO;
import com.informatorio.trabaoFinal.exceptions.NewsAppException;
import com.informatorio.trabaoFinal.exceptions.ResourceNotFoundException;
import com.informatorio.trabaoFinal.model.Article;
import com.informatorio.trabaoFinal.repository.IArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;


@Service
public class ArticleService implements IArticleService{

    @Autowired
    IArticleRepository iArticleRepository;
    @Autowired
    ObjectMapper mapper;


    // Crear un Article
    @Transactional
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
        if (!article.isPresent()) {
            throw new ResourceNotFoundException("Article no encontrado. id inexistente","id: ",id);
        }
        Boolean article1= article.get().getPublished();
        if (article1){
            throw new ResourceNotFoundException("El Article ya esta publicado","id: ",id);
        }

        iArticleRepository.markAsPublished(id);
    }
    //despublicar un article
    @Transactional
    public void updateNotFinished(Long id){
        Optional<Article> article = iArticleRepository.findById(id);
        if (!article.isPresent()) {
           throw new ResourceNotFoundException("Article inexistente","id: ",id);
        }
        Boolean article1= article.get().getPublished();
        if (!article1){
            throw new ResourceNotFoundException("El Article ya esta como 'NO PUBLICADO'","id: ",id);
        }

        iArticleRepository.markNotPublished(id);
    }

        //Trae un article por id
    public ArticleDTO bringArticleById(Long id) {
        Optional<Article> article = iArticleRepository.findById(id);
        if (article.isEmpty()) {

            throw new ResourceNotFoundException("Article no encontrada. Id inexistente. ","id: ",id);
        }
        ArticleDTO articleDTO = null;
        articleDTO = mapper.convertValue(article, ArticleDTO.class);
        return articleDTO;
    }
    //Modificar un Article

    public ArticleDTO updateArticle(ArticleDTO articleDTO, Long id) {
        Article article = iArticleRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("Author","id: ",id));
        article.setTitle(articleDTO.getTitle());
        article.setDescription(articleDTO.getDescription());
        article.setUrl(articleDTO.getUrl());
        article.setContent(articleDTO.getContent());
        Article articlesave = iArticleRepository.save(article);
    return mapper.convertValue(articlesave, ArticleDTO.class);
    }
    //Borrar un Article
    public void deleteArticle(Long id) {
        Optional<Article> article = iArticleRepository.findById(id);
        if (article.isEmpty()) {
            throw new ResourceNotFoundException("Article inexistente.El proceso de ELIMINACIO ha sido cancelado ",
                    "id: ",id);
        }
        iArticleRepository.deleteById(id);
    }


    // buscar article por un string mayor a 2 caracteres,
    // que haya sido publicado y por los campos title y description paginado
    @Transactional
   public Page<ArticleDTO> getAllArticleLikePage(Pageable pageable, String wordToSearch){

        List<Article> articlePage = iArticleRepository.
                getArticleByPublishedAndTitleOrDescriptionAndFullname( wordToSearch, pageable);
        if(articlePage.size()==0){
            throw new NewsAppException("Source", HttpStatus.NOT_FOUND,
                    "No hay articles publicados o no hay Coincidencia con la busqueda lanzada");
        }
        List<ArticleDTO> articleDTOList= articlePage.stream().map(article -> mapper.convertValue(
                article, ArticleDTO.class)).toList();

        return new PageImpl<>(articleDTOList);
   }



    // Traer todos los articles publicados
    public Set<ArticleDTO> showAllPublished(){
        Set<Article> articles = iArticleRepository.showArticlePublished();
        if(articles.size()==0){
            throw new NewsAppException("Error.  "
                    ,HttpStatus.NOT_FOUND," No hay articles publicados hasta el momento.");
        }

        Set<ArticleDTO> articleDTOS = new HashSet<>();
        for ( Article article : articles){
            articleDTOS.add(mapper.convertValue(article, ArticleDTO.class));
        }
        return articleDTOS;
    }


}
