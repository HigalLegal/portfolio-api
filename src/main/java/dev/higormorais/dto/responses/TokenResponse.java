package dev.higormorais.dto.responses;

import io.quarkus.runtime.annotations.RegisterForReflection;

@RegisterForReflection
public class TokenResponse {

    private String jwt;

    // -----------------------------------------------------------------------------

    public TokenResponse() {}

    public TokenResponse(String jwt) {
        this.jwt = jwt;
    }

    // -----------------------------------------------------------------------------
    public String getJwt() {
        return jwt;
    }

    public void setJwt(String jwt) {
        this.jwt = jwt;
    }
}
