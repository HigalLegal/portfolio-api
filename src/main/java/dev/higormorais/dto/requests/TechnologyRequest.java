package dev.higormorais.dto.requests;

import dev.higormorais.dto.requests.builders.TechnologyRequestBuilder;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class TechnologyRequest {

    @NotBlank(message = "O campo do nome não pode ser vazio ou em branco")
    @NotNull(message = "O campo do nome não pode ser nulo")
    private String name;

    @NotNull(message = "O campo de importância não pode ser nulo")
    private int importanceLevel;

    // ----------------------------------------------------------------------------------

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getImportanceLevel() {
        return importanceLevel;
    }

    public void setImportanceLevel(int importanceLevel) {
        this.importanceLevel = importanceLevel;
    }

    public static TechnologyRequestBuilder build() {
        return TechnologyRequestBuilder.getInstance();
    }
}
