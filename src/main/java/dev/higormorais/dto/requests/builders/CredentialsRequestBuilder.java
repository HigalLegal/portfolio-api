package dev.higormorais.dto.requests.builders;

import dev.higormorais.dto.requests.CredentialsRequest;

public class CredentialsRequestBuilder {

    private String email;
    private String password;

    // ------------------------------------------------------------------------

    private CredentialsRequestBuilder() {}

    public static CredentialsRequestBuilder getInstance() {
        return new CredentialsRequestBuilder();
    }

    // ------------------------------------------------------------------------

    public CredentialsRequestBuilder email(String email) {
        this.email = email;
        return this;
    }

    public CredentialsRequestBuilder password(String password) {
        this.password = password;
        return this;
    }

    public CredentialsRequest build() {
        var credentialsRequest = new CredentialsRequest();

        credentialsRequest.setEmail(this.email);
        credentialsRequest.setPassword(this.password);

        return credentialsRequest;
    }

}
