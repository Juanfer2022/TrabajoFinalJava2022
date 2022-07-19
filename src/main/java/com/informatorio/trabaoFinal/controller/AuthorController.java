package com.informatorio.trabaoFinal.controller;

import com.informatorio.trabaoFinal.model.AuthorDTO;
import com.informatorio.trabaoFinal.service.IAuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/authors")
public class AuthorController {

    @Autowired
    IAuthorService iAuthorService;

    @PostMapping
    public ResponseEntity<?> createAuthor(@RequestBody AuthorDTO authorDTO) {

        iAuthorService.createAuthor(authorDTO);
        return ResponseEntity.status(HttpStatus.OK).body("AUTHOR creado");
    }

    @GetMapping("/{id}")
    public AuthorDTO getAuthor(@PathVariable Long id){

        return iAuthorService.mostrarUnActor(id);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteAuthor(@PathVariable Long id) {
        iAuthorService.deleteAuthor(id);
        return ResponseEntity.status(HttpStatus.OK).body("Author removido");

}
    }
