package dev.higormorais.dto.responses.builders;

import dev.higormorais.dto.responses.TechnologyResponse;

public class TechnologyResponseAllBuilder {

    private Integer id;

    private String name;

    private String urlImage;

    private int importanceLevel;

    // ---------------------------------------------

    private TechnologyResponseAllBuilder() {}

    public static TechnologyResponseAllBuilder getInstance() {
        return new TechnologyResponseAllBuilder();
    }

    // ---------------------------------------------

    public TechnologyResponseAllBuilder id(Integer id) {
        this.id = id;
        return this;
    }

    public TechnologyResponseAllBuilder name(String name) {
        this.name = name;
        return this;
    }

    public TechnologyResponseAllBuilder urlImage(String urlImage) {
        this.urlImage = urlImage;
        return this;
    }

    public TechnologyResponseAllBuilder importanceLevel(int importanceLevel) {
        this.importanceLevel = importanceLevel;
        return this;
    }

    public TechnologyResponse build() {
        var technology = new TechnologyResponse();

        technology.setId(id);
        technology.setName(name);
        technology.setUrlImage(urlImage);
        technology.setImportanceLevel(importanceLevel);

        return technology;
    }


}
