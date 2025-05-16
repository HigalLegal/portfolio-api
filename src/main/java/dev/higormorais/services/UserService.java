package dev.higormorais.services;

import dev.higormorais.dto.requests.CredentialsRequest;
import dev.higormorais.dto.requests.UserRequest;
import dev.higormorais.dto.responses.TokenResponse;
import dev.higormorais.dto.responses.UserResponse;

import java.util.List;

public interface UserService {

    TokenResponse login(CredentialsRequest credentialsRequest);

    Long total();

    void create(UserRequest userRequest);

    void update(Integer id, UserRequest userRequest);

    void delete(Integer id);

}
