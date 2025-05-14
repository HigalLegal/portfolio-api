package dev.higormorais.dto.requests.builders;

import dev.higormorais.dto.requests.ArticleRequest;

import java.util.ArrayList;
import java.util.List;

public class ArticleRequestBuilder {

    private String title;

    private String summary;

    private String urlArticle;

    private List<Integer> technologiesCoveredId = new ArrayList<>();

    // -------------------------------------------------------------------------------

    private ArticleRequestBuilder() {}

    public static ArticleRequestBuilder getInstance() {
        return new ArticleRequestBuilder();
    }

    // -------------------------------------------------------------------------------

    public ArticleRequestBuilder title(String title) {
        this.title = title;
        return this;
    }

    public ArticleRequestBuilder summary(String summary) {
        this.summary = summary;
        return this;
    }

    public ArticleRequestBuilder urlArticle(String urlArticle) {
        this.urlArticle = urlArticle;
        return this;
    }

    public ArticleRequestBuilder technologiesCovered(List<Integer> technologiesCoveredId) {
        this.technologiesCoveredId = technologiesCoveredId;
        return this;
    }

    public ArticleRequest build() {
        var articleRequest = new ArticleRequest();

        articleRequest.setTitle(title);
        articleRequest.setSummary(summary);
        articleRequest.setUrlArticle(urlArticle);
        articleRequest.setTechnologiesCoveredId(technologiesCoveredId);

        return articleRequest;
    }

}
