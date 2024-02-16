package dev.higormorais.handlers;

import dev.higormorais.exceptions.InvalidKeyApiException;
import dev.higormorais.handlers.entities.ErrorJSON;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;

public class InvalidKeyApiHandler implements ExceptionMapper<InvalidKeyApiException> {
    @Override
    public Response toResponse(InvalidKeyApiException e) {
        var error = new ErrorJSON("Recurso não encontrado", 500, e.getMessage());

        return Response
                .status(error.getStatusCode())
                .entity(error)
                .build();
    }
}
