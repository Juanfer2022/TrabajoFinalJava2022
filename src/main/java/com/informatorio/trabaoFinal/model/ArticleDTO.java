package com.informatorio.trabaoFinal.model;

import lombok.Data;

import java.time.LocalDate;

@Data
public class ArticleDTO {
    private Long id;
    private String title;
    private String description;
    private String url;
    private LocalDate publishedAt = LocalDate.now();
    private String content;
    private Author author;
    private Source source;

}
