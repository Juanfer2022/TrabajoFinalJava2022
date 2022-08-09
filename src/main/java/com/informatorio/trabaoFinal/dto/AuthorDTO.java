package com.informatorio.trabaoFinal.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class AuthorDTO {

    private Long id;
    private String firstname;
    private String lastname;
    private String fullname;
    private LocalDate createdAT;
    private Long related;

    public AuthorDTO(Long id, String firstname, String lastname, String fullname, LocalDate createdAT, Long related) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.fullname = fullname;
        this.createdAT = createdAT;
        this.related = related;
    }

    public AuthorDTO() {

    }

  //  public AuthorDTO(Long id, String firstname, String lastname, String fullname, LocalDate createdAT) {

    }


    //public AuthorDTO(Long id, String firstname, String lastname, String fullname, LocalDate createdAT) {



