package dev.higormorais.dto.requests;

import dev.higormorais.dto.requests.builders.UserRequestBuilder;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class UserRequest {

    @Email(message = "Email inválido.")
    private String email;

    @NotBlank(message = "Senha inválida.")
    @Size(min = 3, message = "A senha precisa ter no mínimo 3 caracteres!")
    private String password;

    private boolean admin;

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

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    public static UserRequestBuilder builder() {
        return UserRequestBuilder.getInstance();
    }


}
