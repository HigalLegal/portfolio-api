package dev.higormorais.dto.requests;

import com.fasterxml.jackson.annotation.JsonFormat;
import dev.higormorais.dto.requests.builders.ExperienceRequestBuilder;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ExperienceRequest {

    @NotBlank(message = "O campo do nome da empresa não pode ser vazio ou em branco")
    @NotNull(message = "O campo do nome da empresa não pode ser nulo")
    private String companyName;

    @NotBlank(message = "O campo da descrição não pode ser vazio ou em branco")
    @NotNull(message = "O campo da descrição não pode ser nulo")
    private String description;

    @NotNull(message = "O campo do começo não pode ser nulo")
    @Past(message = "A data do começo deve está no passado!")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private LocalDate beginning;

    @Past(message = "A data de fim deve está no passado!")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private LocalDate end;

    @NotNull(message = "Você deve colocar pelo menos uma tecnologia")
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
