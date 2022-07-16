package com.informatorio.trabaoFinal.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.informatorio.trabaoFinal.exceptions.Exceptions;
import com.informatorio.trabaoFinal.model.Source;
import com.informatorio.trabaoFinal.model.SourceDTO;
import com.informatorio.trabaoFinal.repository.ISourceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PutMapping;

import java.util.Optional;

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
}

    /*public Product modifyProduct(long id, Product newProduct) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException(id));
        newProduct.setId(product.getId());
        return productRepository.save(newProduct);
    }

    public void updateSource(SourceDTO sourceDTO){
        Source source1 = mapper.convertValue(sourceDTO, Source.class);
        iSourceRepository.save(source1);

}*/
