package dev.higormorais.dto.responses;

public class TokenResponse {

    private String jwt;

    // ---------------------------------------------------------------------------

    public String getJwt() {
        return jwt;
    }

    public void setJwt(String jwt) {
        this.jwt = jwt;
    }
}
