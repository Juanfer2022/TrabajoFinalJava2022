package com.informatorio.trabaoFinal.dto;

import com.informatorio.trabaoFinal.model.Author;
import com.informatorio.trabaoFinal.model.Source;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class ArticleDTO {
    private Long id;

    private String title;

    private String description;
    private String content;

    private Boolean published;

    private String urlToImage;
    private String url;
    private LocalDate publishedAt;
    private Author author;
    private Source source;

   

    public ArticleDTO(Long id, String title, String description, Boolean published, String urlToImage,
                      String url, String content, Author author, Source source, LocalDate publishedAt) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.published = published;
        this.urlToImage = urlToImage;
        this.url = url;
        this.content = content;
        this.author = author;
        this.source = source;
        this.publishedAt = publishedAt;
    }

    public ArticleDTO() {

    }
}
