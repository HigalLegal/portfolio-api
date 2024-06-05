package dev.higormorais.dto.requests.builders;

import dev.higormorais.dto.requests.UserRequest;

public class UserRequestBuilder {
    private String email;
    private String password;
    private boolean admin;

    // ---------------------------------------------------------------------------------------------

    private UserRequestBuilder() {}

    public static UserRequestBuilder getInstance() {
        return new UserRequestBuilder();
    }

    // ---------------------------------------------------------------------------------------------

    public UserRequestBuilder email(String email) {
        this.email = email;
        return this;
    }

    public UserRequestBuilder password(String password) {
        this.password = password;
        return this;
    }

    public UserRequestBuilder admin(boolean admin) {
        this.admin = admin;
        return this;
    }

    public UserRequest build() {
        var userRequest = new UserRequest();

        userRequest.setEmail(this.email);
        userRequest.setPassword(this.password);
        userRequest.setAdmin(this.admin);

        return userRequest;
    }

}
