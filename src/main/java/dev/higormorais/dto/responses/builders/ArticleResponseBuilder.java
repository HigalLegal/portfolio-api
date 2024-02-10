package dev.higormorais.dto.responses.builders;

import dev.higormorais.dto.responses.ArticleResponse;

import java.util.ArrayList;
import java.util.List;

public class ArticleResponseBuilder {

    private Integer id;

    private String title;

    private String summary;

    private String urlArticle;

    private String date;

    private List<String> technologiesCovered = new ArrayList<>();

    // -------------------------------------------------------------------------------------------

    private ArticleResponseBuilder() {}

    public static ArticleResponseBuilder getInstance() {
        return new ArticleResponseBuilder();
    }

    // -------------------------------------------------------------------------------------------

    public ArticleResponseBuilder id(Integer id) {
        this.id = id;
        return this;
    }

    public ArticleResponseBuilder title(String title) {
        this.title = title;
        return this;
    }

    public ArticleResponseBuilder summary(String summary) {
        this.summary = summary;
        return this;
    }

    public ArticleResponseBuilder urlArticle(String urlArticle) {
        this.urlArticle = urlArticle;
        return this;
    }

    public ArticleResponseBuilder date(String date) {
        this.date = date;
        return this;
    }

    public ArticleResponseBuilder technologiesCovered(List<String> technologiesCovered) {
        this.technologiesCovered = technologiesCovered;
        return this;
    }

    public ArticleResponse build() {
        var articleResponse = new ArticleResponse();

        articleResponse.setId(id);
        articleResponse.setTitle(title);
        articleResponse.setSummary(summary);
        articleResponse.setUrlArticle(urlArticle);
        articleResponse.setDate(date);
        articleResponse.setTechnologiesCovered(technologiesCovered);

        return articleResponse;
    }

}
