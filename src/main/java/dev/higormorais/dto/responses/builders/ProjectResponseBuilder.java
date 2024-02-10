package dev.higormorais.dto.responses.builders;

import dev.higormorais.dto.responses.ProjectResponse;

import java.util.ArrayList;
import java.util.List;

public class ProjectResponseBuilder {

    private Integer id;

    private String description;

    private String urlRepository;

    private String urlImage;

    private int importanceLevel;

    private List<String> technologiesWorked = new ArrayList<>();

    // ---------------------------------------------------------------------------------

    private ProjectResponseBuilder(){}

    public static ProjectResponseBuilder getInstance() {
        return new ProjectResponseBuilder();
    }

    // ---------------------------------------------------------------------------------

    public ProjectResponseBuilder id(Integer id) {
        this.id = id;
        return this;
    }

    public ProjectResponseBuilder description(String description) {
        this.description = description;
        return this;
    }

    public ProjectResponseBuilder urlRepository(String urlRepository) {
        this.urlRepository = urlRepository;
        return this;
    }

    public ProjectResponseBuilder urlImage(String urlImage) {
        this.urlImage = urlImage;
        return this;
    }

    public ProjectResponseBuilder importanceLevel(int importanceLevel) {
        this.importanceLevel = importanceLevel;
        return this;
    }

    public ProjectResponseBuilder technologiesWorked(List<String> technologiesWorked) {
        this.technologiesWorked = technologiesWorked;
        return this;
    }

    public ProjectResponse build() {
        var project = new ProjectResponse();
        
        project.setId(this.id);
        project.setDescription(this.description);
        project.setUrlRepository(this.urlRepository);
        project.setUrlImage(this.urlImage);
        project.setImportanceLevel(this.importanceLevel);
        project.setTechnologiesWorked(this.technologiesWorked);
        
        return project;
    }

}
