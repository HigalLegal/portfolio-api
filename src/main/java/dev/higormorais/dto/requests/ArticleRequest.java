package dev.higormorais.dto.requests;


import dev.higormorais.dto.requests.builders.ArticleRequestBuilder;
import jakarta.json.bind.annotation.JsonbDateFormat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ArticleRequest {

    @NotBlank(message = "{validation.message.notBlank}")
    @NotNull(message = "{validation.message.notNull}")
    private String title;

    @NotBlank(message = "{validation.message.notBlank}")
    @NotNull(message = "{validation.message.notNull}")
    private String summary;

    @NotBlank(message = "{validation.message.notBlank}")
    @NotNull(message = "{validation.message.notNull}")
    private String urlArticle;

    @NotNull(message = "{validation.message.notNull}")
    @Past(message = "{validation.message.past}")
    @JsonbDateFormat("dd/MM/yyyy")
    private LocalDate date;

    @NotNull(message = "{validation.message.notNull}")
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
