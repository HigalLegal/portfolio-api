package dev.higormorais.repositories;

import dev.higormorais.entities.User;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import io.quarkus.panache.common.Parameters;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityNotFoundException;

import java.util.Optional;

@ApplicationScoped
public class UserRepository implements PanacheRepositoryBase<User, Integer> {

    public Optional<User> findByEmail(String email) {
        final String JPQL = "SELECT u FROM User u WHERE u.email = :email";

        return this
                .find(JPQL, Parameters.with("email", email))
                .stream()
                .findAny();
    }

    public void update(User user) {
        int rowsAffected = this
                .update("UPDATE User u SET u.email = :email, u.password = :password, u.admin = :admin " +
                    "WHERE u.id = :id", generateParameters(user));

        if(rowsAffected <= 0) {
            throw new EntityNotFoundException("Usuário inexistente");
        }

    }

    private Parameters generateParameters(User user) {
        return Parameters
                .with("email", user.getEmail())
                .and("password", user.getPassword())
                .and("admin", user.isAdmin())
                .and("id", user.getId());
    }

}
