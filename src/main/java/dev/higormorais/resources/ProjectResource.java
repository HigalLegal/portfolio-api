package dev.higormorais.resources;

import dev.higormorais.dto.requests.ProjectRequest;
import jakarta.ws.rs.core.Response;

import java.io.File;

public interface ProjectResource {

    Response listAll(Integer offset, Integer Integer);

    Response create(ProjectRequest projectRequest, File image);

    Response update(Integer id, ProjectRequest projectRequest, File image);

    Response delete(Integer id);
    
}
