package dev.higormorais.entities.builders;

import dev.higormorais.entities.Experience;
import dev.higormorais.entities.Technology;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ExperienceBuilder {

    private Integer id;

    private String companyName;

    private String description;

    private LocalDate beginning;

    private LocalDate end;

    private List<Technology> technologiesWorked = new ArrayList<>();

    // ---------------------------------------------------------------

    private ExperienceBuilder() {}

    public static ExperienceBuilder getInstance() {
        return new ExperienceBuilder();
    }

    // ---------------------------------------------------------------

    public ExperienceBuilder id(Integer id) {
        this.id = id;
        return this;
    }

    public ExperienceBuilder companyName(String companyName) {
        this.companyName = companyName;
        return this;
    }

    public ExperienceBuilder description(String description) {
        this.description = description;
        return this;
    }

    public ExperienceBuilder beginning(LocalDate beginning) {
        this.beginning = beginning;
        return this;
    }

    public ExperienceBuilder end(LocalDate end) {
        this.end = end;
        return this;
    }

    public ExperienceBuilder technologiesWorked(Technology... technologiesWorked) {
        this.technologiesWorked = Arrays.asList(technologiesWorked);
        return this;
    }

    public Experience builder() {

        var experience = new Experience();

        experience.setId(id);
        experience.setCompanyName(companyName);
        experience.setDescription(description);
        experience.setBeginning(beginning);
        experience.setEnd(end);
        experience.setTechnologiesWorked(technologiesWorked);

        return experience;
    }

}

