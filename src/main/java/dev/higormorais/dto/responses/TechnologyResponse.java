package dev.higormorais.dto.responses;

import dev.higormorais.dto.responses.builders.TechnologyResponseAllBuilder;

public class TechnologyResponse {

    private Integer id;

    private String name;

    private String urlImage;

    private int importanceLevel;

    // ------------------------------------------------------------

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrlImage() {
        return urlImage;
    }

    public void setUrlImage(String urlImage) {
        this.urlImage = urlImage;
    }

    public int getImportanceLevel() {
        return importanceLevel;
    }

    public void setImportanceLevel(int importanceLevel) {
        this.importanceLevel = importanceLevel;
    }

    public static TechnologyResponseAllBuilder builder() {
        return TechnologyResponseAllBuilder.getInstance();
    }

}
