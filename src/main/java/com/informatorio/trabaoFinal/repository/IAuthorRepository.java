package com.informatorio.trabaoFinal.repository;

import com.informatorio.trabaoFinal.model.Author;
import com.informatorio.trabaoFinal.model.Source;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface IAuthorRepository extends JpaRepository<Author, Long> {

    //Buscar por un string en fullname
    @Query("from Author a where a.fullname like %:fullname%")
    Set<Author> getAuthorByFullNameLike(@Param("fullname")String fullname);
}
