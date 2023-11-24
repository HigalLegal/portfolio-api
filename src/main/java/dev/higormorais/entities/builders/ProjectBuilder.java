package dev.higormorais.entities.builders;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import dev.higormorais.entities.Project;
import dev.higormorais.entities.Technology;

public class ProjectBuilder {

    private Integer id;

    private String description;

    private String urlRepository;

    private String urlImage;

    private int importanceLevel;

    private List<Technology> technologiesWorked = new ArrayList<>();

    // --------------------------------------------------------------------------------------

    private ProjectBuilder() {}

    public static ProjectBuilder getInstance(){
        return new ProjectBuilder();
    }

    // --------------------------------------------------------------------------------------

    public ProjectBuilder id(Integer id) {
        this.id = id;
        return this;
    }

    public ProjectBuilder description(String description) {
        this.description = description;
        return this;
    }

    public ProjectBuilder urlRepository(String urlRepository) {
        this.urlRepository = urlRepository;
        return this;
    }

    public ProjectBuilder urlImage(String urlImage) {
        this.urlImage = urlImage;
        return this;
    }

    public ProjectBuilder importanceLevel(int importanceLevel) {
        this.importanceLevel = importanceLevel;
        return this;
    }

    public ProjectBuilder technologiesWorked(Technology... technologiesWorked) {
        this.technologiesWorked.addAll(Arrays.asList(technologiesWorked));
        return this;
    }

    public Project builder() {
        var project = new Project();

        project.setId(id);
        project.setDescription(description);
        project.setUrlRepository(urlRepository);
        project.setUrlImage(urlImage);
        project.setImportanceLevel(importanceLevel);
        project.setTechnologiesWorked(technologiesWorked);

        return project;
    }

}
