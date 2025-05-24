package dev.higormorais.dto.responses;

import dev.higormorais.dto.responses.builders.UserResponseBuilder;
import io.quarkus.runtime.annotations.RegisterForReflection;

@RegisterForReflection
public class UserResponse {

    private Integer id;

    private String email;

    private String password;

    private boolean admin;

    // -----------------------------------------------------------------------------------

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

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

    public static UserResponseBuilder builder() {
        return UserResponseBuilder.getInstance();
    }

}
