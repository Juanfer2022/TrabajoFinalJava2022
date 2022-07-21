package com.informatorio.trabaoFinal.model;

import lombok.Data;

import java.time.LocalDate;
import java.util.Date;

@Data
public class AuthorDTO {

    private Long id;
    private String firstname;
    private String lastname;
    private String fullname;
    private String createdAT;
}
