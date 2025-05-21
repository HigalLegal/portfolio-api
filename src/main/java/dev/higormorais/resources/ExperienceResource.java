package dev.higormorais.resources;

import dev.higormorais.dto.requests.ExperienceRequest;
import jakarta.validation.Valid;
import jakarta.ws.rs.core.Response;

public interface ExperienceResource {

    Response listAll(Integer offset, Integer limit);

    Response byId(Integer id);

    Response create(@Valid ExperienceRequest experienceRequest);

    Response update(Integer id, @Valid ExperienceRequest experienceRequest);

    Response delete(Integer id);
    
}
