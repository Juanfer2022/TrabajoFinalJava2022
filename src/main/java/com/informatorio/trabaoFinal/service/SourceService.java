package com.informatorio.trabaoFinal.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.informatorio.trabaoFinal.exceptions.Exceptions;
import com.informatorio.trabaoFinal.model.Source;
import com.informatorio.trabaoFinal.model.SourceDTO;
import com.informatorio.trabaoFinal.repository.ISourceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class SourceService implements ISourceService {

    @Autowired
    ISourceRepository iSourceRepository;

    @Autowired
    ObjectMapper mapper;

    public void createSource(SourceDTO sourceDTO) {

        Source source = mapper.convertValue(sourceDTO, Source.class);
        iSourceRepository.save(source);
    }

    public void deleteSource(Long id) {
        Optional<Source> source = iSourceRepository.findById(id);
        if (source.isEmpty()) {
            throw new Exceptions("Source no encontrada", HttpStatus.NOT_FOUND);
        }
        iSourceRepository.deleteById(id);
    }

    public Source updateSource(Long id, SourceDTO sourceDTO) {
        {
            Optional<Source> source1 = iSourceRepository.findById(id);
            if (source1.isEmpty()) {
                throw new Exceptions("Source no encontrada. La Actualiacion falló", HttpStatus.NOT_FOUND);
            }
            sourceDTO.setId(sourceDTO.getId());
            Source source2 = mapper.convertValue(sourceDTO, Source.class);

            return iSourceRepository.save(source2);
        }
    }
        public SourceDTO mostrarSource(Long id){

            SourceDTO sourceDTO=null;
            Optional<Source> source=iSourceRepository.findById(id);
            if(source.isEmpty()){

                throw new Exceptions("Source no encontrada. id inexistente", HttpStatus.NOT_FOUND);
            }
            sourceDTO = mapper.convertValue(source, SourceDTO.class);
            return sourceDTO;
    }

    public Collection<SourceDTO> getAllSource(){
        List<Source> sourceList= iSourceRepository.findAll();
        Set<SourceDTO> sourceDTOSet = new HashSet<>();
        for (Source source: sourceList){
            sourceDTOSet.add(mapper.convertValue(source, SourceDTO.class));
        }
        return sourceDTOSet;
    }


    public Page<Source> getAllSource(Pageable pageable) {
        return iSourceRepository.findAll(pageable);
    }

    public Set<SourceDTO> getSourceWithNameLike(String name){
        Set<Source> sources = iSourceRepository.getSourceByNameLike(name);
        Set<SourceDTO> sourceDTOSet = new HashSet<>();
        for (Source source: sources){
            sourceDTOSet.add(mapper.convertValue(source, SourceDTO.class));
        }
        return sourceDTOSet;
    }

}

    /* public Set<StudentDTO> getStudentWithLastNameLike(String lastname){
        Set<Student> students = studentRepository.getStudentByLastNameLike(lastname);
        Set<StudentDTO> studentsDTO = new HashSet<>();
        for (Student student: students){
            studentsDTO.add(mapper.convertValue(student, StudentDTO.class));
        }
        return studentsDTO;
    }

    public Collection<StudentDTO> getAll(){
        List<Student> students = studentRepository.findAll();
        Set<StudentDTO> studentsDTO = new HashSet<>();
        for (Student student: students){
            studentsDTO.add(mapper.convertValue(student, StudentDTO.class));
        }
        return studentsDTO;
    }




    public Product modifyProduct(long id, Product newProduct) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException(id));
        newProduct.setId(product.getId());
        return productRepository.save(newProduct);
    }

    public void updateSource(SourceDTO sourceDTO){
        Source source1 = mapper.convertValue(sourceDTO, Source.class);
        iSourceRepository.save(source1);

}*/
