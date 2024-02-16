package dev.higormorais.handlers.entities;

import dev.higormorais.exceptions.DateException;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;

public class DateHandler implements ExceptionMapper<DateException> {
    
    @Override
    public Response toResponse(DateException e) {
        var error = new ErrorJSON("Erro de datas", 400, e.getMessage());

        return Response
                .status(error.getStatusCode())
                .entity(error)
                .build();
    }
}
