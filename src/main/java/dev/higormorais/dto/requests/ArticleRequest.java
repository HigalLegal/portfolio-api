package dev.higormorais.dto.requests;


import dev.higormorais.dto.requests.builders.ArticleRequestBuilder;
import dev.higormorais.dto.responses.TechnologyResponse;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ArticleRequest {

    private String title;

    private String summary;

    private String urlArticle;

    private LocalDate date;

    private List<Integer> technologiesCoveredId = new ArrayList<>();

    // ----------------------------------------------------------------------------------------------------

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getUrlArticle() {
        return urlArticle;
    }

    public void setUrlArticle(String urlArticle) {
        this.urlArticle = urlArticle;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public List<Integer> getTechnologiesCoveredId() {
        return technologiesCoveredId;
    }

    public void setTechnologiesCoveredId(List<Integer> technologiesCoveredId) {
        this.technologiesCoveredId = technologiesCoveredId;
    }

    public static ArticleRequestBuilder builder() {
        return ArticleRequestBuilder.getInstance();
    }
}
