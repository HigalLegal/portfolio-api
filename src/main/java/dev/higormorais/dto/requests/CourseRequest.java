package dev.higormorais.dto.requests;

import dev.higormorais.dto.requests.builders.CourseRequestBuilder;

import java.util.ArrayList;
import java.util.List;

public class CourseRequest {

    private Integer id;

    private String name;

    private String imageBase64;

    private String urlCertificate;

    private int importanceLevel;

    private List<Integer> technologiesIds = new ArrayList<>();

    // ------------------------------------------------------------------------------------

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

    public String getImageBase64() {
        return imageBase64;
    }

    public void setImageBase64(String imageBase64) {
        this.imageBase64 = imageBase64;
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

    public List<Integer> getTechnologiesIds() {
        return technologiesIds;
    }

    public void setTechnologiesIds(List<Integer> technologiesIds) {
        this.technologiesIds = technologiesIds;
    }

    public static CourseRequestBuilder build() {
        return CourseRequestBuilder.getInstance();
    }
}
