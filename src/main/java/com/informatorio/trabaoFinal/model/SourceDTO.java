package com.informatorio.trabaoFinal.model;

import lombok.Data;

import java.time.LocalDate;

@Data
public class SourceDTO {

    private Long id;
    private String name;
    private String code;
    private String content;
    private LocalDate createAt = LocalDate.now();
}
