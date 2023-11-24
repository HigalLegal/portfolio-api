package dev.higormorais.entities;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import dev.higormorais.entities.builders.ProjectBuilder;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;


@Entity
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, name = "description_project")
    private String description;

    @Column(nullable = false, name = "url_repository")
    private String urlRepository;

    @Column(name = "url_image")
    private String urlImage;

    @Column(nullable = false, name = "importance_level")
    private int importanceLevel;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "project_technology",
            joinColumns = @JoinColumn(name = "project_id"),
            inverseJoinColumns = @JoinColumn(name = "technology_id"))
    private List<Technology> technologiesWorked = new ArrayList<>();

    // ----------------------------------------------------------------------------------------

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

    public List<Technology> getTechnologiesWorked() {
        return technologiesWorked;
    }

    public void setTechnologiesWorked(List<Technology> technologiesWorked) {
        this.technologiesWorked.addAll(technologiesWorked);
    }

    public List<Object> values() {
        return Arrays.asList(description, urlRepository, urlImage, importanceLevel, id);
    }

    public static List<String> attributes() {
        return Arrays.asList("description,urlRepository,urlImage,importanceLevel,id".split(","));
    }

    public static List<String> attributesQuery() {
        return Arrays.asList(("newDescription,newUrlRepository,"
                + "newUrlImage,newImportanceLevel,projectId").split(","));
    }

    public static ProjectBuilder build() {
        return ProjectBuilder.getInstance();
    }

}
