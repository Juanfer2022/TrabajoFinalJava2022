package com.informatorio.trabaoFinal.model;

import lombok.Data;

import java.time.LocalDate;

@Data
public class AuthorDTO {

    private Long id;
    private String firstname;
    private String lastname;
    private String fullname;
    private LocalDate createdAT = LocalDate.now();
}
