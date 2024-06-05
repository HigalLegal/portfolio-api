package dev.higormorais.configs.jwt.impl;

import dev.higormorais.configs.jwt.TokenGenerator;
import dev.higormorais.entities.User;
import io.smallrye.jwt.build.Jwt;
import jakarta.enterprise.context.ApplicationScoped;

import java.time.Duration;
import java.time.Instant;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@ApplicationScoped
public class TokenGeneratorImpl implements TokenGenerator {

    private long EXPIRATION_TIME_IN_SECONDS = 60 * 2;

    @Override
    public String createToken(User user) {
        Instant now = Instant.now();

        return Jwt
                .upn(user.getEmail())
                .groups(generateRoles(user.isAdmin()))
                .claim("iat", now.getEpochSecond())
                .claim("exp", generateExpiration(EXPIRATION_TIME_IN_SECONDS, now))
                .sign();
    }

    private Set<String> generateRoles(boolean admin) {
        String[] rolesArray = admin ? new String[]{"ADMIN", "NON_ADMIN"} : new String[]{"NON_ADMIN"};
        return new HashSet<>(Arrays.asList(rolesArray));
    }

    private long generateExpiration(long seconds, Instant begin) {
        return begin.plus(Duration.ofSeconds(seconds)).getEpochSecond();
    }
}

