package com.informatorio.trabaoFinal.controller;

import com.informatorio.trabaoFinal.model.ArticleDTO;

import com.informatorio.trabaoFinal.model.AuthorDTO;
import com.informatorio.trabaoFinal.service.IArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/articles")
public class ArticleController {

    @Autowired
    IArticleService iArticleService;

    //Crear author
    @PostMapping
    public ResponseEntity<?> createAuthor(@RequestBody ArticleDTO articleDTO) {

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

    @GetMapping("/allarticles")
    public Collection<ArticleDTO> allarticle(){
        return iArticleService.getAllArticle();
    }

}
