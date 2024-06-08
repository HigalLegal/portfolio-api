package dev.higormorais.handlers;

import dev.higormorais.exceptions.InvalidPasswordException;
import dev.higormorais.handlers.entities.ErrorJSON;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
public class InvalidPasswordHandler implements ExceptionMapper<InvalidPasswordException> {

    @Override
    public Response toResponse(InvalidPasswordException e) {
        var error = new ErrorJSON("Senha incorreta", 403, e.getMessage());

        return Response
                .status(error.getStatusCode())
                .entity(error)
                .build();
    }

}
