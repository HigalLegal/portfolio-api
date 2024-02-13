package dev.higormorais.dto.requests;

import dev.higormorais.dto.requests.builders.ProjectRequestBuilder;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.ArrayList;
import java.util.List;

public class ProjectRequest {

    @NotBlank(message = "{validation.message.notBlank}")
    @NotNull(message = "{validation.message.notNull}")
    private String description;

    @NotBlank(message = "{validation.message.notBlank}")
    @NotNull(message = "{validation.message.notNull}")
    private String urlRepository;

    @NotNull(message = "{validation.message.notNull}")
    private int importanceLevel;

    @NotNull(message = "{validation.message.notNull}")
    private List<Integer> technologiesWorkedId = new ArrayList<>();

    // -------------------------------------------------------------------------

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrlRepository() {
        return urlRepository;
    }

    public void setUrlRepository(String urlRepository) {
        this.urlRepository = urlRepository;
    }

    public int getImportanceLevel() {
        return importanceLevel;
    }

    public void setImportanceLevel(int importanceLevel) {
        this.importanceLevel = importanceLevel;
    }

    public List<Integer> getTechnologiesWorkedId() {
        return technologiesWorkedId;
    }

    public void setTechnologiesWorkedId(List<Integer> technologiesWorkedId) {
        this.technologiesWorkedId = technologiesWorkedId;
    }

    public static ProjectRequestBuilder builder() {
        return ProjectRequestBuilder.getInstance();
    }

}
