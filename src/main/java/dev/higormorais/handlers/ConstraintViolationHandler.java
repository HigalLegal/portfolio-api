package dev.higormorais.handlers;

import dev.higormorais.handlers.entities.ErrorJSON;
import jakarta.validation.ConstraintViolationException;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
public class ConstraintViolationHandler implements ExceptionMapper<ConstraintViolationException> {

    @Override
    public Response toResponse(ConstraintViolationException e) {
        String messageError = e.getMessage().split(": ")[1];
        var error = new ErrorJSON("Campo inválido", 400, messageError);

        return Response
                .status(error.getStatusCode())
                .entity(error)
                .build();
    }
}
