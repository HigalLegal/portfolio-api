package dev.higormorais.dto.requests.builders;

import dev.higormorais.dto.requests.ExperienceRequest;
import dev.higormorais.entities.Technology;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ExperienceRequestBuilder {

    private Integer id;

    private String companyName;

    private String description;

    private LocalDate beginning;

    private LocalDate end;

    private List<Integer> technologiesWorkedId = new ArrayList<>();

    // ---------------------------------------------------------------

    private ExperienceRequestBuilder() {}

    public static ExperienceRequestBuilder getInstance() {
        return new ExperienceRequestBuilder();
    }

    // ---------------------------------------------------------------

    public ExperienceRequestBuilder id(Integer id) {
        this.id = id;
        return this;
    }

    public ExperienceRequestBuilder companyName(String companyName) {
        this.companyName = companyName;
        return this;
    }

    public ExperienceRequestBuilder description(String description) {
        this.description = description;
        return this;
    }

    public ExperienceRequestBuilder beginning(LocalDate beginning) {
        this.beginning = beginning;
        return this;
    }

    public ExperienceRequestBuilder end(LocalDate end) {
        this.end = end;
        return this;
    }

    public ExperienceRequestBuilder technologiesWorkedId(Integer... technologiesWorked) {
        this.technologiesWorkedId = Arrays.asList(technologiesWorked);
        return this;
    }

    public ExperienceRequest builder() {

        var experience = new ExperienceRequest();

        experience.setCompanyName(companyName);
        experience.setDescription(description);
        experience.setBeginning(beginning);
        experience.setEnd(end);
        experience.setTechnologiesWorkedId(technologiesWorkedId);

        return experience;
    }


}
