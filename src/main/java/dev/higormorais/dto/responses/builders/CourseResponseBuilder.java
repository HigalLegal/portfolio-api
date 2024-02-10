package dev.higormorais.dto.responses.builders;

import dev.higormorais.dto.responses.CourseResponse;

import java.util.ArrayList;
import java.util.List;

public class CourseResponseBuilder {

    private Integer id;

    private String name;

    private String urlImage;

    private String urlCertificate;

    private int importanceLevel;

    private List<String> technologies = new ArrayList<>();

    // -----------------------------------------------------------------------------------

    private CourseResponseBuilder() {}

    public static CourseResponseBuilder getInstance() {
        return new CourseResponseBuilder();
    }

    // -----------------------------------------------------------------------------------

    public CourseResponseBuilder id(Integer id) {
        this.id = id;
        return this;
    }

    public CourseResponseBuilder name(String name) {
        this.name = name;
        return this;
    }

    public CourseResponseBuilder urlImage(String urlImage) {
        this.urlImage = urlImage;
        return this;
    }

    public CourseResponseBuilder urlCertificate(String urlCertificate) {
        this.urlCertificate = urlCertificate;
        return this;
    }

    public CourseResponseBuilder importanceLevel(int importanceLevel) {
        this.importanceLevel = importanceLevel;
        return this;
    }

    public CourseResponseBuilder technologies(List<String> technologies) {
        this.technologies = technologies;
        return this;
    }

    public CourseResponse build() {
        var course = new CourseResponse();

        course.setId(this.id);
        course.setName(this.name);
        course.setUrlImage(this.urlImage);
        course.setUrlCertificate(this.urlCertificate);
        course.setImportanceLevel(this.importanceLevel);
        course.setTechnologies(this.technologies);

        return course;
    }
    

}
