package com.informatorio.trabaoFinal.repository;

import com.informatorio.trabaoFinal.model.Source;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ISourceRepository  extends JpaRepository<Source, Long> {
}
