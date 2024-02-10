package dev.higormorais.dto.responses;

import dev.higormorais.dto.responses.builders.ProjectResponseBuilder;

import java.util.ArrayList;
import java.util.List;

public class ProjectResponse {

    private Integer id;

    private String description;

    private String urlRepository;

    private String urlImage;

    private int importanceLevel;

    private List<String> technologiesWorked = new ArrayList<>();

    // --------------------------------------------------------------------------------------------------

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

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

    public String getUrlImage() {
        return urlImage;
    }

    public void setUrlImage(String urlImage) {
        this.urlImage = urlImage;
    }

    public int getImportanceLevel() {
        return importanceLevel;
    }

    public void setImportanceLevel(int importanceLevel) {
        this.importanceLevel = importanceLevel;
    }

    public List<String> getTechnologiesWorked() {
        return technologiesWorked;
    }

    public void setTechnologiesWorked(List<String> technologiesWorked) {
        this.technologiesWorked = technologiesWorked;
    }

    public static ProjectResponseBuilder builder() {
        return ProjectResponseBuilder.getInstance();
    }

}
