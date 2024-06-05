package dev.higormorais.dto.responses.builders;

import dev.higormorais.dto.responses.UserResponse;

public class UserResponseBuilder {

    private Integer id;
    private String email;
    private String password;
    private boolean admin;

    // ---------------------------------------------------------------------------------------------

    private UserResponseBuilder() {}

    public static UserResponseBuilder getInstance() {
        return new UserResponseBuilder();
    }

    // ---------------------------------------------------------------------------------------------

    public UserResponseBuilder id(Integer id) {
        this.id = id;
        return this;
    }

    public UserResponseBuilder email(String email) {
        this.email = email;
        return this;
    }

    public UserResponseBuilder password(String password) {
        this.password = password;
        return this;
    }

    public UserResponseBuilder admin(boolean admin) {
        this.admin = admin;
        return this;
    }

    public UserResponse build() {
        var userResponse = new UserResponse();

        userResponse.setId(this.id);
        userResponse.setEmail(this.email);
        userResponse.setPassword(this.password);
        userResponse.setAdmin(this.admin);

        return userResponse;
    }

}
