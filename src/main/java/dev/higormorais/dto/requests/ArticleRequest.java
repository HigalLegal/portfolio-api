package dev.higormorais.dto.requests;


import dev.higormorais.dto.requests.builders.ArticleRequestBuilder;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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
