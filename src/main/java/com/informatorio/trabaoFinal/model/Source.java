package com.informatorio.trabaoFinal.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;

@Entity
@Data
@Table( indexes = {@Index(name="code", columnList = "code", unique = true)})
public class Source {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "El nombre no puede ser nulo ni estar vacio!!!!!")
    private String name;
    private String code;
    private LocalDate createAt;
    //private Long related;




}
