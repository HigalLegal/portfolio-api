package dev.higormorais.entities.builders;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import dev.higormorais.entities.Article;
import dev.higormorais.entities.Technology;

public class ArticleBuilder {

    private Integer id;

    private String title;

    private String summary;

    private String urlArticle;

    private LocalDate date;

    private List<Technology> technologiesCovered = new ArrayList<>();

    // -------------------------------------------------------------------------

    private ArticleBuilder() {}

    public static ArticleBuilder getInstance() {
        return new ArticleBuilder();
    }

    // -------------------------------------------------------------------------

    public ArticleBuilder id(Integer id) {
        this.id = id;
        return this;
    }

    public ArticleBuilder title(String title){
        this.title = title;
        return this;
    }

    public ArticleBuilder summary(String summary) {
        this.summary = summary;
        return this;
    }

    public ArticleBuilder urlArticle(String urlArticle) {
        this.urlArticle = urlArticle;
        return this;
    }

    public ArticleBuilder date(LocalDate date) {
        this.date = date;
        return this;
    }

    public ArticleBuilder technologiesCovered(Technology... technologies) {
        this.technologiesCovered.addAll(Arrays.asList(technologies));
        return this;
    }

    public Article build() {
        var article = new Article();

        article.setId(id);
        article.setTitle(title);
        article.setSummary(summary);
        article.setUrlArticle(urlArticle);
        article.setDate(date);
        article.setTechnologiesCovered(technologiesCovered);

        return article;
    }

}
