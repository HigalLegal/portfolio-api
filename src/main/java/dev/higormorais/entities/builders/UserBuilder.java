package dev.higormorais.entities.builders;

import dev.higormorais.entities.User;

public class UserBuilder {

    private Integer id;
    private String email;
    private String password;
    private boolean admin;

    // ---------------------------------------------------------------------------------------------

    private UserBuilder() {}

    public static UserBuilder getInstance() {
        return new UserBuilder();
    }

    // ---------------------------------------------------------------------------------------------

    public UserBuilder id(Integer id) {
        this.id = id;
        return this;
    }

    public UserBuilder email(String email) {
        this.email = email;
        return this;
    }

    public UserBuilder password(String password) {
        this.password = password;
        return this;
    }

    public UserBuilder admin(boolean admin) {
        this.admin = admin;
        return this;
    }

    public User build() {
        var user = new User();

        user.setId(this.id);
        user.setEmail(this.email);
        user.setPassword(this.password);
        user.setAdmin(this.admin);

        return user;
    }
    
}
