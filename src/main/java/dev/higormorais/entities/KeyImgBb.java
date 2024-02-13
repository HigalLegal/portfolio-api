package dev.higormorais.entities;

import jakarta.persistence.Id;

public class KeyImgBb {

    @Id
    private String value;

    // -------------------------------------------------------------------------

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }

}
