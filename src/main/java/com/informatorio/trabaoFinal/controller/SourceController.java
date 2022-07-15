package com.informatorio.trabaoFinal.controller;

import com.informatorio.trabaoFinal.model.SourceDTO;
import com.informatorio.trabaoFinal.service.ISourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sources")
public class SourceController {

    @Autowired
    ISourceService iSourceService;

    @PostMapping
    public ResponseEntity<?> createSource(@RequestBody SourceDTO sourceDTO) {

        iSourceService.createSource(sourceDTO);
        return ResponseEntity.status(HttpStatus.OK).body("SOURCE creado");


    }
}
