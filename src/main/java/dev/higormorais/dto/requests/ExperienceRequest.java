package dev.higormorais.dto.requests;

import dev.higormorais.dto.requests.builders.ExperienceRequestBuilder;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ExperienceRequest {

    private String companyName;

    private String description;

    private LocalDate beginning;

    private LocalDate end;

    private List<Integer> technologiesWorkedId = new ArrayList<>();

    // ---------------------------------------------------------------------------------------

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

    public LocalDate getBeginning() {
        return beginning;
    }

    public void setBeginning(LocalDate beginning) {
        this.beginning = beginning;
    }

    public LocalDate getEnd() {
        return end;
    }

    public void setEnd(LocalDate end) {
        this.end = end;
    }

    public List<Integer> getTechnologiesWorkedId() {
        return technologiesWorkedId;
    }

    public void setTechnologiesWorkedId(List<Integer> technologiesWorkedId) {
        this.technologiesWorkedId = technologiesWorkedId;
    }

    public ExperienceRequestBuilder build() {
        return ExperienceRequestBuilder.getInstance();
    }
}
