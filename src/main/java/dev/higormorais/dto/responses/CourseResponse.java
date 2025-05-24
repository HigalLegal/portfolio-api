package dev.higormorais.dto.responses;

import dev.higormorais.dto.responses.builders.CourseResponseBuilder;
import io.quarkus.runtime.annotations.RegisterForReflection;

import java.util.ArrayList;
import java.util.List;

@RegisterForReflection
public class CourseResponse {

    private Integer id;

    private String name;

    private String urlImage;

    private String urlCertificate;

    private int importanceLevel;

    private List<String> technologies = new ArrayList<>();

    // ------------------------------------------------------------------------------

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

    public String getUrlCertificate() {
        return urlCertificate;
    }

    public void setUrlCertificate(String urlCertificate) {
        this.urlCertificate = urlCertificate;
    }

    public int getImportanceLevel() {
        return importanceLevel;
    }

    public void setImportanceLevel(int importanceLevel) {
        this.importanceLevel = importanceLevel;
    }

    public List<String> getTechnologies() {
        return technologies;
    }

    public void setTechnologies(List<String> technologies) {
        this.technologies = technologies;
    }

    public static CourseResponseBuilder builder() {
        return CourseResponseBuilder.getInstance();
    }
}
