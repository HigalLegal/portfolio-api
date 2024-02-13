package dev.higormorais.dto.requests;

import dev.higormorais.dto.requests.builders.ExperienceRequestBuilder;
import jakarta.json.bind.annotation.JsonbDateFormat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ExperienceRequest {

    @NotBlank(message = "{validation.message.notBlank}")
    @NotNull(message = "{validation.message.notNull}")
    private String companyName;

    @NotBlank(message = "{validation.message.notBlank}")
    @NotNull(message = "{validation.message.notNull}")
    private String description;

    @NotNull(message = "{validation.message.notNull}")
    @Past(message = "{validation.message.past}")
    @JsonbDateFormat("dd/MM/yyyy")
    private LocalDate beginning;

    @Past(message = "{validation.message.past}")
    @JsonbDateFormat("dd/MM/yyyy")
    private LocalDate end;

    @NotNull(message = "{validation.message.notNull}")
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
