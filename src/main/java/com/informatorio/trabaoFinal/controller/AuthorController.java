package com.informatorio.trabaoFinal.controller;

import com.informatorio.trabaoFinal.model.Author;
import com.informatorio.trabaoFinal.model.AuthorDTO;
import com.informatorio.trabaoFinal.model.Source;
import com.informatorio.trabaoFinal.model.SourceDTO;
import com.informatorio.trabaoFinal.service.IAuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Set;

@RestController
@RequestMapping("/authors")
public class AuthorController {

    @Autowired
    IAuthorService iAuthorService;

    //Crear author
    @PostMapping
    public ResponseEntity<?> createAuthor(@RequestBody AuthorDTO authorDTO) {

        iAuthorService.createAuthor(authorDTO);
        return ResponseEntity.status(HttpStatus.OK).body("AUTHOR creado");
    }

    //Mostrar un author
    @GetMapping("/{id}")
    public AuthorDTO getAuthor(@PathVariable Long id){

        return iAuthorService.mostrarUnActor(id);
    }
    //Borrar author
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteAuthor(@PathVariable Long id) {
        iAuthorService.deleteAuthor(id);
        return ResponseEntity.status(HttpStatus.OK).body("Author removido");

}
    //Modificar author
    @PutMapping()
    public ResponseEntity<Author> modifyAuthor(@RequestBody AuthorDTO newAuthor) {
        Author author= iAuthorService.updateAuthor(newAuthor);
        return new ResponseEntity<>(author, HttpStatus.OK);
    }

    //Obtener todos los author
    @GetMapping("/allauthors")
    public Collection<AuthorDTO> allauthor(){
        return iAuthorService.getAllAuthor();
    }

    //Mostrar souces con paginacion
    @GetMapping("/allauthor/page/{page}")
    public Page<Author> allauthor(@PathVariable Integer page)
    {
        Pageable pageable = PageRequest.of(page, 5);
        return iAuthorService.getAllAuthor(pageable);
    }

    //Buscar por una palabra
    @GetMapping("/fullname")
    public Set<AuthorDTO> getAuthorByFullName(@RequestParam String fullname){
        return iAuthorService.getAuthorWithFullNameLike(fullname);

    }

}
