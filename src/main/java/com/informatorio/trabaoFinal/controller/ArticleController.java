package com.informatorio.trabaoFinal.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.informatorio.trabaoFinal.model.Article;
import com.informatorio.trabaoFinal.model.ArticleDTO;

import com.informatorio.trabaoFinal.model.Author;
import com.informatorio.trabaoFinal.model.AuthorDTO;
import com.informatorio.trabaoFinal.repository.IArticleRepository;
import com.informatorio.trabaoFinal.service.IArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@RestController
@RequestMapping("/articles")
public class ArticleController {

    @Autowired
    ObjectMapper mapper;

    @Autowired
    IArticleRepository iArticleRepository;

    @Autowired
    IArticleService iArticleService;

    //Crear author
    @PostMapping
    public ResponseEntity<?> createArticle(@RequestBody ArticleDTO articleDTO) {

        iArticleService.createArticle(articleDTO);
        return ResponseEntity.status(HttpStatus.OK).body("Article creado");
    }

    //Marcar article como publicado
    @PatchMapping("/updateFinishes/{id}")
    public ResponseEntity<?> markAsPublished(@PathVariable("id") Long id){
        iArticleService.updateFinished(id);
        return ResponseEntity.status(HttpStatus.OK).body("El Article ha sido marcado como Publicado");
    }

    //Borrar article
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteArticle(@PathVariable Long id) {
        iArticleService.deleteArticle(id);
        return ResponseEntity.status(HttpStatus.OK).body("Article removido");

    }



    @GetMapping("/allarticles/pages")
    public Page<ArticleDTO> allArticlesPages(@RequestParam Integer pages, @RequestParam String title)
    {
        Pageable pageable = PageRequest.of(pages, 3);
        return iArticleService.getAllArticleLikePage(pageable, title);
    }
    @GetMapping("/allarticles")
    public ResponseEntity<?> allArticlesPagess(@RequestParam Integer pages, @RequestParam String title)
    {
        Pageable pageable = PageRequest.of(pages, 3);
        Page<Article> pageResult= iArticleRepository.getArticleByTitleLikePage(pageable, title);

        Map<String, Object> pageableDTO = new HashMap<>();
        pageableDTO.put("content", pageResult.getContent().stream().map(article ->mapper
                .convertValue(article, ArticleDTO.class) ).toList());
        pageableDTO.put("page",pageResult.getNumber());
        pageableDTO.put("size", pageResult.getSize());
        pageableDTO.put("totalElements",pageResult.getTotalElements());
        pageableDTO.put("totalPage",pageResult.getTotalPages());



        return new ResponseEntity<>(pageableDTO, HttpStatus.OK);
    }

}
