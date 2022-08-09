package com.informatorio.trabaoFinal.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class SourceDTO {

    private Long id;
    private String name;
    private String code;
    private LocalDate createAt;
    private Long related;


    public SourceDTO(Long id, String name, String code, LocalDate createAt, Long related) {
        this.id = id;
        this.name = name;
        this.code = code;
        this.createAt = createAt;
        this.related = related;
    }

    public SourceDTO() {
    }
}
