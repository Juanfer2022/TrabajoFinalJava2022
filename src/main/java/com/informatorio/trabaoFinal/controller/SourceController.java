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
import java.util.Set;

@RestController
@RequestMapping("/sources")
public class SourceController {

    @Autowired
    ISourceService iSourceService;

   //Crear source
    @PostMapping
    public ResponseEntity<?> createSource(@RequestBody SourceDTO sourceDTO) {

        iSourceService.createSource(sourceDTO);
        return ResponseEntity.status(HttpStatus.OK).body("SOURCE creado");
    }

    //Borrar un source
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteSource(@PathVariable Long id) {
        iSourceService.deleteSource(id);
        return ResponseEntity.status(HttpStatus.OK).body("Source removido");
    }

    //Modificar un source
    @PutMapping()
    public ResponseEntity<Source> modifySource(@RequestBody SourceDTO newSource) {
        Source source = iSourceService.updateSource(newSource);
        return new ResponseEntity<>(source, HttpStatus.OK);
    }

    //Mostrar un source
    @GetMapping("/{id}")
    public SourceDTO getSource(@PathVariable Long id){
        return iSourceService.mostrarSource(id);
    }

    //Mostrar todos sources
    @GetMapping("/allsources")
    public Collection<SourceDTO> allsources(){
        return iSourceService.getAllSource();
    }

    //Mostrar souces con paginacion
    @GetMapping("/allsources/page")
    public Page<Source> allsources(@RequestParam Integer page, @RequestParam Integer tam)
    {
        Pageable pageable = PageRequest.of(page, tam);
        return iSourceService.getAllSource(pageable);
    }

    //Buscar por una palabra
    @GetMapping("/listname")
    public Set<SourceDTO> listname(@RequestParam String name){
        return iSourceService.getSourceWithNameLike(name);

    }


}