package dev.higormorais.dto.responses;

import dev.higormorais.dto.responses.builders.ExperienceResponseBuilder;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ExperienceResponse {

    private Integer id;

    private String companyName;

    private String description;

    private String beginning;

    private String end;

    private List<String> technologiesWorked = new ArrayList<>();

    // ----------------------------------------------------------------------------

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getBeginning() {
        return beginning;
    }

    public void setBeginning(String beginning) {
        this.beginning = beginning;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    public List<String> getTechnologiesWorked() {
        return technologiesWorked;
    }

    public void setTechnologiesWorked(List<String> technologiesWorked) {
        this.technologiesWorked = technologiesWorked;
    }

    public static ExperienceResponseBuilder builder() {
        return ExperienceResponseBuilder.getInstance();
    }
}
