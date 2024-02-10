package dev.higormorais.entities.builders;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import dev.higormorais.entities.Course;
import dev.higormorais.entities.Technology;

public class CourseBuilder {


    private Integer id;

    private String name;

    private String urlImage;

    private String urlCertificate;

    private int importanceLevel;

    private List<Technology> technologies = new ArrayList<>();

    // ---------------------------------------------------------------------------

    private CourseBuilder() {}

    public static CourseBuilder getInstance() {
        return new CourseBuilder();
    }

    // ---------------------------------------------------------------------------

    public CourseBuilder id(Integer id) {
        this.id = id;
        return this;
    }

    public CourseBuilder name(String name) {
        this.name = name;
        return this;
    }

    public CourseBuilder urlImage(String urlImage) {
        this.urlImage = urlImage;
        return this;
    }

    public CourseBuilder urlCertificate(String urlCertificate) {
        this.urlCertificate = urlCertificate;
        return this;
    }

    public CourseBuilder importanceLevel(int importanceLevel) {
        this.importanceLevel = importanceLevel;
        return this;
    }

    public CourseBuilder technologies(List<Technology> technologies) {
        this.technologies.addAll(technologies);
        return this;
    }

    public Course build() {
        var course = new Course();

        course.setId(id);
        course.setName(name);
        course.setUrlImage(urlImage);
        course.setUrlCertificate(urlCertificate);
        course.setImportanceLevel(importanceLevel);
        course.setTechnologies(technologies);

        return course;
    }

}
