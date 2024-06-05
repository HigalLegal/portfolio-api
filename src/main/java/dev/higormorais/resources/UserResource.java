package dev.higormorais.resources;

import dev.higormorais.dto.requests.CredentialsRequest;
import dev.higormorais.dto.requests.UserRequest;
import jakarta.validation.Valid;
import jakarta.ws.rs.core.Response;

public interface UserResource {

    Response login(CredentialsRequest credentialsRequest);

    Response create(@Valid UserRequest userRequest);

    Response update(Integer id, @Valid UserRequest userRequest);

    Response delete(Integer id);

}
