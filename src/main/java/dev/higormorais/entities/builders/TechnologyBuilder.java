package dev.higormorais.entities.builders;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import dev.higormorais.entities.Article;
import dev.higormorais.entities.Course;
import dev.higormorais.entities.Experience;
import dev.higormorais.entities.Project;
import dev.higormorais.entities.Technology;


public class TechnologyBuilder {

    private Integer id;

    private String name;

    private String urlImage;

    private int importanceLevel;

    private List<Course> courses = new ArrayList<>();

    private List<Article> articles = new ArrayList<>();

    private List<Project> projects = new ArrayList<>();

    private List<Experience> experiences = new ArrayList<>();

    // ----------------------------------------------------------------------------------

    private TechnologyBuilder() {}

    public static TechnologyBuilder getInstance() {
        return new TechnologyBuilder();
    }

    // ----------------------------------------------------------------------------------

    public TechnologyBuilder id(Integer id) {
        this.id = id;
        return this;
    }

    public TechnologyBuilder name(String name) {
        this.name = name;
        return this;
    }

    public TechnologyBuilder urlImage(String urlImage) {
        this.urlImage = urlImage;
        return this;
    }

    public TechnologyBuilder importanceLevel(int importanceLevel) {
        this.importanceLevel = importanceLevel;
        return this;
    }

    public TechnologyBuilder courses(Course... courses) {
        this.courses.addAll(Arrays.asList(courses));
        return this;
    }

    public TechnologyBuilder articles(Article... articles) {
        this.articles.addAll(Arrays.asList(articles));
        return this;
    }

    public TechnologyBuilder projects(Project... projects) {
        this.projects.addAll(Arrays.asList(projects));
        return this;
    }

    public TechnologyBuilder experiences(Experience... experiences) {
        this.experiences.addAll(Arrays.asList(experiences));
        return this;
    }

    public Technology builder() {
        var technology = new Technology();

        technology.setId(id);
        technology.setName(name);
        technology.setUrlImage(urlImage);
        technology.setImportanceLevel(importanceLevel);
        technology.setCourses(courses);
        technology.setArticles(articles);
        technology.setProjects(projects);
        technology.setExperiences(experiences);

        return technology;
    }



}
