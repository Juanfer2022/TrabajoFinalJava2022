package com.informatorio.trabaoFinal.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.informatorio.trabaoFinal.exceptions.Exceptions;
import com.informatorio.trabaoFinal.model.Author;
import com.informatorio.trabaoFinal.model.AuthorDTO;
import com.informatorio.trabaoFinal.repository.IAuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthorService implements IAuthorService {

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
    public AuthorDTO mostrarUnActor(Long id) {
        Optional<Author> author = iAuthorRepository.findById(id);
        if (author.isEmpty()) {

            throw new Exceptions("Author no encontrada. id inexistente", HttpStatus.NOT_FOUND);
        }
        AuthorDTO authorDTO = null;
        authorDTO = mapper.convertValue(author, AuthorDTO.class);
        return authorDTO;
    }

    //B0rra un Author
    public void deleteAuthor(Long id) {
        Optional<Author> author = iAuthorRepository.findById(id);
        if (author.isEmpty()) {
            throw new Exceptions("Author no encontrado.El proceso de ELIMINACIO ha sido cancelado", HttpStatus.NOT_FOUND);
        }
        iAuthorRepository.deleteById(id);
    }

    //Modificar un Author
    public Author updateAuthor(AuthorDTO authorDTO) {
        //{
            //Optional<Author> author = iAuthorRepository.findById(authorDTO.getId());
            //if (author.isEmpty()) {
              //  throw new Exceptions("Author no encontrado. La Actualiacion falló", HttpStatus.NOT_FOUND);
            //}
            //authorDTO.setId(authorDTO.getId());
            Author author1 = mapper.convertValue(authorDTO, Author.class);

            return iAuthorRepository.save(author1);
        }
    }
