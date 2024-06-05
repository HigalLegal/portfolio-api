package dev.higormorais.configs.jwt;

import dev.higormorais.entities.User;

public interface TokenGenerator {

    String createToken(User user);

}
