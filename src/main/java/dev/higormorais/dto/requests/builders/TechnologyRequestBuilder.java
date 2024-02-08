package dev.higormorais.dto.requests.builders;

import dev.higormorais.dto.requests.TechnologyRequest;

public class TechnologyRequestBuilder {

    private String name;

    private int importanceLevel;

    // ------------------------------------------------------

    private TechnologyRequestBuilder() {}

    public static TechnologyRequestBuilder getInstance() {
        return new TechnologyRequestBuilder();
    }

    // ------------------------------------------------------

    public TechnologyRequestBuilder name(String name) {
        this.name = name;
        return this;
    }

    public TechnologyRequestBuilder importanceLevel(int importanceLevel) {
        this.importanceLevel = importanceLevel;
        return this;
    }

    public TechnologyRequest builder(){

        var technology = new TechnologyRequest();

        technology.setName(name);
        technology.setImportanceLevel(importanceLevel);

        return technology;

    }

}
