package dev.higormorais.dto.requests.builders;

import dev.higormorais.dto.requests.CourseRequest;

import java.util.ArrayList;
import java.util.List;

public class CourseRequestBuilder {
    
    private String name;

    private String urlCertificate;

    private int importanceLevel;

    private List<Integer> technologiesIds = new ArrayList<>();

    // ---------------------------------------------------------------------------------------------

    private CourseRequestBuilder() {}

    public static CourseRequestBuilder getInstance() {
        return new CourseRequestBuilder();
    }

    // ---------------------------------------------------------------------------------------------

    public CourseRequestBuilder name(String name) {
        this.name = name;
        return this;
    }

    public CourseRequestBuilder urlCertificate(String urlCertificate) {
        this.urlCertificate = urlCertificate;
        return this;
    }

    public CourseRequestBuilder importanceLevel(int importanceLevel) {
        this.importanceLevel = importanceLevel;
        return this;
    }

    public CourseRequestBuilder technologiesId(List<Integer> technologiesIds) {
        this.technologiesIds = technologiesIds;
        return this;
    }

    public CourseRequest builder() {
        var courseRequest = new CourseRequest();

        courseRequest.setName(name);
        courseRequest.setUrlCertificate(urlCertificate);
        courseRequest.setImportanceLevel(importanceLevel);
        courseRequest.setTechnologiesIds(technologiesIds);

        return courseRequest;
    }


}
