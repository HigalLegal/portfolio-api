package dev.higormorais.dto.responses.builders;

import dev.higormorais.dto.responses.ExperienceResponse;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ExperienceResponseBuilder {

    private Integer id;

    private String companyName;

    private String description;

    private String beginning;

    private String end;

    private List<String> technologiesWorked = new ArrayList<>();
    
    // -----------------------------------------------------------------

    private ExperienceResponseBuilder() {}

    public static ExperienceResponseBuilder getInstance() {
        return new ExperienceResponseBuilder();
    }
    
    // -----------------------------------------------------------------

    public ExperienceResponseBuilder id(Integer id) {
        this.id = id;
        return this;
    }

    public ExperienceResponseBuilder companyName(String companyName) {
        this.companyName = companyName;
        return this;
    }

    public ExperienceResponseBuilder description(String description) {
        this.description = description;
        return this;
    }

    public ExperienceResponseBuilder beginning(String beginning) {
        this.beginning = beginning;
        return this;
    }

    public ExperienceResponseBuilder end(String end) {
        this.end = end;
        return this;
    }

    public ExperienceResponseBuilder technologiesWorked(List<String> technologiesWorked) {
        this.technologiesWorked = technologiesWorked;
        return this;
    }

    public ExperienceResponse build() {
        var experience = new ExperienceResponse();

        experience.setId(id);
        experience.setCompanyName(companyName);
        experience.setDescription(description);
        experience.setBeginning(this.beginning);
        experience.setEnd(end);
        experience.setTechnologiesWorked(technologiesWorked);

        return experience;
    }

}
