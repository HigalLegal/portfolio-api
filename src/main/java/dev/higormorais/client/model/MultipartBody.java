package dev.higormorais.client.model;

import jakarta.ws.rs.core.MediaType;
import org.jboss.resteasy.reactive.PartType;
import org.jboss.resteasy.reactive.RestForm;

public class MultipartBody {

    @RestForm
    @PartType(MediaType.TEXT_PLAIN)
    private String image;

    @RestForm
    @PartType(MediaType.TEXT_PLAIN)
    private String key;

    @RestForm
    @PartType(MediaType.TEXT_PLAIN)
    private String name;

    // ------------------------------------------------------------------------------------

    public MultipartBody() {}

    public MultipartBody(String image, String key, String name) {
        this.image = image;
        this.key = key;
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
