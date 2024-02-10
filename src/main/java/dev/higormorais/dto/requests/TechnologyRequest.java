package dev.higormorais.dto.requests;

import dev.higormorais.dto.requests.builders.TechnologyRequestBuilder;

public class TechnologyRequest {

    private String name;

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
