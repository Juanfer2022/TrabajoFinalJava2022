package com.informatorio.trabaoFinal.controller;

import com.informatorio.trabaoFinal.model.Source;
import com.informatorio.trabaoFinal.model.SourceDTO;
import com.informatorio.trabaoFinal.service.ISourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

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

    @DeleteMapping("/{id}")

    public ResponseEntity<?> deleteSource(@PathVariable Long id) {
        iSourceService.deleteSource(id);
        return ResponseEntity.status(HttpStatus.OK).body("Source removido");
    }

    @PutMapping("/{id}")
    public ResponseEntity<Source> modifyProduct(@PathVariable Long id,  @RequestBody SourceDTO newSource) {
        Source source = iSourceService.updateSource(id, newSource);
        return new ResponseEntity<>(source, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public SourceDTO getSource(@PathVariable Long id){
        return iSourceService.mostrarSource(id);
    }

    @GetMapping("/allsources")
    public Collection<SourceDTO> allsources(){
        return iSourceService.getAllSource();
    }

    @GetMapping("/allsources/page/{page}")
    public Page<Source> allsources(@PathVariable Integer page)
    {
        Pageable pageable = PageRequest.of(page, 5);
        return iSourceService.getAllSource(pageable);
    }

}