package dev.higormorais.resources;

import dev.higormorais.dto.requests.TechnologyRequest;
import jakarta.ws.rs.core.Response;


import java.io.File;

public interface TechnologyResource {

    Response listAll(Integer offset, Integer limit);

    Response create(TechnologyRequest technologyRequest, File image);

    Response update(Integer id, TechnologyRequest technologyRequest, File image);

    Response delete(Integer id);
    
}
