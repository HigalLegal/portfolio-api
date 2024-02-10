package dev.higormorais.entities;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import dev.higormorais.entities.builders.TechnologyBuilder;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;


@Entity
public class Technology {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, name = "name_technology")
    private String name;

    @Column(name = "url_image")
    private String urlImage;

    @Column(nullable = false, name = "importance_level")
    private int importanceLevel;

    @ManyToMany(mappedBy = "technologies")
    private List<Course> courses = new ArrayList<>();

    @ManyToMany(mappedBy = "technologiesCovered")
    private List<Article> articles = new ArrayList<>();

    @ManyToMany(mappedBy = "technologiesWorked")
    private List<Project> projects = new ArrayList<>();

    @ManyToMany(mappedBy = "technologiesWorked")
    private List<Experience> experiences = new ArrayList<>();

    // ----------------------------------------------------------------------------------------

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses.addAll(courses);
    }

    public List<Article> getArticles() {
        return articles;
    }

    public void setArticles(List<Article> articles) {
        this.articles.addAll(articles);
    }

    public List<Project> getProjects() {
        return projects;
    }

    public void setProjects(List<Project> projects) {
        this.projects.addAll(projects);
    }

    public List<Experience> getExperiences() {
        return experiences;
    }

    public void setExperiences(List<Experience> experiences) {
        this.experiences.addAll(experiences);
    }

    public List<Object> values() {
        return Arrays.asList(name, urlImage, importanceLevel, id);
    }

    public static List<String> attributes() {
        return Arrays.asList("name,urlImage,importanceLevel,id".split(","));
    }

    public static List<String> attributesQuery() {
        return Arrays.asList("newName,newUrlImage,newImportanceLevel,technologyId".split(","));
    }

    public static TechnologyBuilder builder() {
        return TechnologyBuilder.getInstance();
    }

}
