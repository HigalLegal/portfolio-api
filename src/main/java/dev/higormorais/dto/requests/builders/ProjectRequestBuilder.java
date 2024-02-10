package dev.higormorais.dto.requests.builders;

import dev.higormorais.dto.requests.ProjectRequest;

import java.util.ArrayList;
import java.util.List;

public class ProjectRequestBuilder {

    private String description;

    private String urlRepository;
    
    private int importanceLevel;

    private List<Integer> technologiesWorkedId = new ArrayList<>();

    // -----------------------------------------------------------------------------------

    private ProjectRequestBuilder() {}

    public static ProjectRequestBuilder getInstance() {
        return new ProjectRequestBuilder();
    }


    // -----------------------------------------------------------------------------------

    public ProjectRequestBuilder description(String description) {
        this.description = description;
        return this;
    }

    public ProjectRequestBuilder urlRepository(String urlRepository) {
        this.urlRepository = urlRepository;
        return this;
    }

    public ProjectRequestBuilder importanceLevel(int importanceLevel) {
        this.importanceLevel = importanceLevel;
        return this;
    }

    public ProjectRequestBuilder technologiesWorkedId(List<Integer> technologiesWorkedId) {
        this.technologiesWorkedId = technologiesWorkedId;
        return this;
    }

    public ProjectRequest build() {

        var project = new ProjectRequest();

        project.setDescription(description);
        project.setUrlRepository(urlRepository);
        project.setImportanceLevel(importanceLevel);
        project.setTechnologiesWorkedId(technologiesWorkedId);

        return project;
    }


}
