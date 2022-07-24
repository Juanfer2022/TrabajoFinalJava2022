package com.informatorio.trabaoFinal.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class ArticleDTO {
    private Long id;
    private String title;
    private String description;
    private String url;
    //private LocalDate publishedAt = LocalDate.now();
    private String content;
    //private boolean published= false;
    private Author author;
    private Source source;

    public ArticleDTO(Long id, String title, String description, String url, String content, Author author, Source source) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.url = url;
        this.content = content;
        this.author = author;
        this.source = source;
    }


    //public ArticleDTO(Long id, String title, String description, String url, String content, Author author, Source source) {
    //}
}
