package com.informatorio.trabaoFinal.repository;

import com.informatorio.trabaoFinal.model.Author;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Set;

@Repository
public interface IAuthorRepository extends JpaRepository<Author, Long> {

    //Buscar por un string en fullname
    @Query("from Author a where a.fullname like %:fullname%")
    Set<Author> getAuthorByFullNameLike(@Param("fullname")String fullname);

    @Query("from Author a where a.createdAT > :fecha")
    Set<Author> getAuthorByCreatedAt(@Param("fecha") LocalDate fecha);

    @Query("from Author a where a.createdAT > :fecha")
    Page<Author> getAuthorByCreatedAtPage(@Param("fecha") Pageable pageable, LocalDate fecha);

   /* //Identifica al author en una relacion
    @Modifying
    @Query(value = "UPDATE Author SET  related=1 WHERE ID=:idAut")
    public void authorRelatedArticle(@Param("idAut") Long idAut);
    // Cambia la situacion anterior
    @Modifying
    @Query(value = "UPDATE Author SET  related=0 WHERE ID=:idAut")
    public void authorNotRelatedArticle(@Param("idAut") Long idAut);*/
}
