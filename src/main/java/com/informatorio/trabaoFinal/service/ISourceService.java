package com.informatorio.trabaoFinal.service;

import com.informatorio.trabaoFinal.model.Source;
import com.informatorio.trabaoFinal.model.SourceDTO;

public interface ISourceService {
    public void createSource(SourceDTO sourceDTO);
    public void deleteSource(Long id);

}
