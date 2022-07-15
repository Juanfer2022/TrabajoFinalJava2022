package com.informatorio.trabaoFinal.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.informatorio.trabaoFinal.exceptions.Exceptions;
import com.informatorio.trabaoFinal.model.Source;
import com.informatorio.trabaoFinal.model.SourceDTO;
import com.informatorio.trabaoFinal.repository.ISourceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SourceService implements ISourceService{

    @Autowired
    ISourceRepository iSourceRepository;

    @Autowired
    ObjectMapper mapper;

    public void createSource(SourceDTO sourceDTO) {

        Source source = mapper.convertValue(sourceDTO, Source.class);
        iSourceRepository.save(source);
    }

    public void deleteSource(Long id){
        Optional<Source> source = iSourceRepository.findById(id);
        if(source.isEmpty()){
            throw new Exceptions("Source no encontrada", HttpStatus.NOT_FOUND);
        }
        iSourceRepository.deleteById(id);
    }
}