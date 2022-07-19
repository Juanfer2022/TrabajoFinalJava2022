package com.informatorio.trabaoFinal.service;

import com.informatorio.trabaoFinal.model.AuthorDTO;

public interface IAuthorService {

    public void createAuthor(AuthorDTO authorDTO);
    public AuthorDTO mostrarUnActor(Long id);
    public void deleteAuthor(Long id);
}
