package dev.higormorais.dto.requests;


import com.fasterxml.jackson.annotation.JsonFormat;
import dev.higormorais.dto.requests.builders.ArticleRequestBuilder;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ArticleRequest {

    @NotBlank(message = "O campo do título não pode ser vazio ou em branco")
    @NotNull(message = "O campo do título não pode ser nulo")
    private String title;

    @NotBlank(message = "O campo do resumo não pode ser vazio ou em branco")
    @NotNull(message = "O campo do resumo não pode ser nulo")
    private String summary;

    @NotBlank(message = "O campo da URL do artigo não pode ser vazio ou em branco")
    @NotNull(message = "O campo da URL do artigo não pode ser nulo")
    private String urlArticle;

    @NotNull(message = "O campo da data não pode ser nulo")
    @Past(message = "A data deve ser no passado!")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private LocalDate date;

    @NotNull(message = "Você deve colocar pelo menos uma tecnologia")
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
