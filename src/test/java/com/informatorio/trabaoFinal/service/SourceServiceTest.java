package com.informatorio.trabaoFinal.service;


import com.informatorio.trabaoFinal.model.Source;
import com.informatorio.trabaoFinal.repository.ISourceRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.persistence.EntityNotFoundException;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class SourceServiceTest {
    @Mock
    private ISourceRepository iSourceRepository;
    @Mock
    private ISourceService iSourceService;
    @InjectMocks
    private SourceService sourceService;
    Source source = new Source();

    @Test
    void  updateSource(){

        when(iSourceRepository.findById(1L)).thenThrow(new EntityNotFoundException());

        Exception exception = assertThrows(EntityNotFoundException.class, () -> sourceService.
                updateSource("Las Termas", 1L));

        assertNotNull(exception);
    }


}