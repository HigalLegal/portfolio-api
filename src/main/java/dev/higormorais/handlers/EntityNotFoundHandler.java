package dev.higormorais.handlers;

import dev.higormorais.handlers.entities.ErrorJSON;
import jakarta.persistence.EntityNotFoundException;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
public class EntityNotFoundHandler implements ExceptionMapper<EntityNotFoundException> {
    @Override
    public Response toResponse(EntityNotFoundException e) {

        var error = new ErrorJSON("Recurso não encontrado", 404, e.getMessage());

        return Response
                .status(error.getStatusCode())
                .entity(error)
                .build();
    }
}
