package dev.higormorais.dto.requests;

import dev.higormorais.dto.requests.builders.CourseRequestBuilder;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.ArrayList;
import java.util.List;

public class CourseRequest {

    @NotBlank(message = "{validation.message.notBlank}")
    @NotNull(message = "{validation.message.notNull}")
    private String name;

    @NotBlank(message = "{validation.message.notBlank}")
    @NotNull(message = "{validation.message.notNull}")
    private String urlCertificate;

    @NotNull(message = "{validation.message.notNull}")
    private int importanceLevel;

    @NotNull(message = "{validation.message.notNull}")
    private List<Integer> technologiesIds = new ArrayList<>();

    // ------------------------------------------------------------------------------------

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
