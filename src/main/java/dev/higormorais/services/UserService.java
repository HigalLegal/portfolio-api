package dev.higormorais.services;

import dev.higormorais.dto.requests.CredentialsRequest;
import dev.higormorais.dto.requests.UserRequest;
import dev.higormorais.dto.responses.TokenResponse;

public interface UserService {

    TokenResponse login(CredentialsRequest credentialsRequest);

    void create(UserRequest userRequest);

    void update(Integer id, UserRequest userRequest);

    void delete(Integer id);

}
