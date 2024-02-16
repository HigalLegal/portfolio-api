package dev.higormorais.resources;

import dev.higormorais.dto.requests.ExperienceRequest;
import jakarta.ws.rs.core.Response;

public interface ExperienceResource {

    Response listAll(Integer offset, Integer limit);

    Response create(ExperienceRequest experienceRequest);

    Response update(Integer id, ExperienceRequest experienceRequest);

    Response delete(Integer id);
    
}
