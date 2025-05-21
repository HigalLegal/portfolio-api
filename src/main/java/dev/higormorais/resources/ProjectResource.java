package dev.higormorais.resources;

import dev.higormorais.dto.requests.ProjectRequest;
import jakarta.validation.Valid;
import jakarta.ws.rs.core.Response;

import java.io.File;

public interface ProjectResource {

    Response listAll(Integer offset, Integer Integer);

    Response byId(Integer id);

    Response create(@Valid ProjectRequest projectRequest, File image);

    Response update(Integer id, @Valid ProjectRequest projectRequest, File image);

    Response delete(Integer id);
    
}
