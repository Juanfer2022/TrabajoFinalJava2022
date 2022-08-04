package com.informatorio.trabaoFinal.model;

import com.sun.istack.NotNull;
import lombok.Data;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
public class Article {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    private String title;
    @NotBlank
    private String description;
    @NotBlank
    private String urlToImage;
    @NotBlank
    private String url;
    private LocalDate publishedAt;
    private boolean published;
    @NotBlank
    @Lob
    private String content;


    @ManyToOne
    @JoinColumn(name="author_id", nullable = false)
    private Author author;

    @ManyToOne
    @JoinColumn(name= "source_id", nullable = false)
    private Source source;
}
