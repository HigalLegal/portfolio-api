package dev.higormorais.dto.requests;

import dev.higormorais.dto.requests.builders.CredentialsRequestBuilder;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

public class CredentialsRequest {

    @Email(message = "Email incorreto")
    private String email;

    @NotNull(message = "Senha incorreta")
    private String password;

    // -------------------------------------------------------------------

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public static CredentialsRequestBuilder builder() {
        return CredentialsRequestBuilder.getInstance();
    }

}
