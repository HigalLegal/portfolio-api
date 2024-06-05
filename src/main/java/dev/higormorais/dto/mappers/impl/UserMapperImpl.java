package dev.higormorais.dto.mappers.impl;

import dev.higormorais.dto.mappers.UserMapper;
import dev.higormorais.dto.requests.UserRequest;
import dev.higormorais.dto.responses.UserResponse;
import dev.higormorais.entities.User;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class UserMapperImpl implements UserMapper {

    @Override
    public User toEntitie(UserRequest request) {
        return User
                .builder()
                .email(request.getEmail())
                .password(request.getPassword())
                .admin(request.isAdmin())
                .build();
    }

    @Override
    public UserResponse toResponse(User entitie) {
        return UserResponse
                .builder()
                .id(entitie.getId())
                .email(entitie.getEmail())
                .password(entitie.getPassword())
                .admin(entitie.isAdmin())
                .build();
    }
}
