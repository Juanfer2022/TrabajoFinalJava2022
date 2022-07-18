package com.informatorio.trabaoFinal.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.informatorio.trabaoFinal.exceptions.Exceptions;
import com.informatorio.trabaoFinal.model.Author;
import com.informatorio.trabaoFinal.model.AuthorDTO;
import com.informatorio.trabaoFinal.model.Source;
import com.informatorio.trabaoFinal.model.SourceDTO;
import com.informatorio.trabaoFinal.repository.IAuthorRepository;
import com.informatorio.trabaoFinal.repository.ISourceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthorService implements IAuthorService{

    @Autowired
    IAuthorRepository iAuthorRepository;

    @Autowired
    ObjectMapper mapper;

    // Crear un Author
    public void createAuthor(AuthorDTO authorDTO) {

        Author author = mapper.convertValue(authorDTO, Author.class);

        iAuthorRepository.save(author);
    }

    //Mostrar un actor
    public AuthorDTO mostrarUnActor(Long id){
        Optional<Author> author=iAuthorRepository.findById(id);
        if(author.isEmpty()){

            throw new Exceptions("Author no encontrada. id inexistente", HttpStatus.NOT_FOUND);
        }
        AuthorDTO authorDTO=null;
        authorDTO = mapper.convertValue(author, AuthorDTO.class);
        return authorDTO;
    }
}
