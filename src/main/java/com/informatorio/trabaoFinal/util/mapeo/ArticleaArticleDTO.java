package com.informatorio.trabaoFinal.util.mapeo;

import com.informatorio.trabaoFinal.model.Article;
import com.informatorio.trabaoFinal.model.ArticleDTO;
import org.springframework.stereotype.Component;

@Component
public class ArticleaArticleDTO implements IArticleaArticleDTO<ArticleDTO, Article> {
    @Override
    public Article map(ArticleDTO in) {

        Article article = new Article();
        article.setId(in.getId());
                article.setTitle(in.getTitle());
                article.setDescription(in.getDescription());
                article.setUrl(in.getUrl());
                article.setContent(in.getContent());
                article.setAuthor(in.getAuthor());
                article.setSource(in.getSource());
                return article;

    }

 /*   @Override
    public Task map(TaskDTO in) {
        Task task1 = new Task();
        task1.(in.getTitle());
        task1.setDescription(in.getDescription());
        task1.setEta(in.getEta());
        task1.setCreatedDate(LocalDateTime.now());
        task1.setFinished(false);
        task1.setTaskStatus(TaskStatus.ON_TIME);
        return task1;
    }*/
}
