package dev.higormorais.dto.responses;

import dev.higormorais.dto.responses.builders.ArticleResponseBuilder;

import java.util.ArrayList;
import java.util.List;

public class ArticleResponse {

    private Integer id;

    private String title;

    private String summary;

    private String urlArticle;

    private String date;

    private List<String> technologiesCovered = new ArrayList<>();

    // -------------------------------------------------------------------------------


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public List<String> getTechnologiesCovered() {
        return technologiesCovered;
    }

    public void setTechnologiesCovered(List<String> technologiesCovered) {
        this.technologiesCovered = technologiesCovered;
    }

    public static ArticleResponseBuilder builder() {
        return ArticleResponseBuilder.getInstance();
    }
}
