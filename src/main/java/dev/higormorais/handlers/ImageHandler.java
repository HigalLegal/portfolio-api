package dev.higormorais.handlers;

import dev.higormorais.exceptions.ImageException;
import dev.higormorais.handlers.entities.ErrorJSON;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;

public class ImageHandler implements ExceptionMapper<ImageException> {

    @Override
    public Response toResponse(ImageException e) {
        var error = new ErrorJSON("Imagem não enviada", 400, e.getMessage());

        return Response
                .status(error.getStatusCode())
                .entity(error)
                .build();
    }
}
