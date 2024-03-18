package dev.higormorais.resources;

import dev.higormorais.dto.requests.CourseRequest;
import jakarta.validation.Valid;
import jakarta.ws.rs.core.Response;

import java.io.File;

public interface CourseResource {

    Response listAll(Integer offset, Integer limit);

    Response listByName(String name);

    Response listByTechnology(String technology);

    Response create(@Valid CourseRequest courseRequest, File image);

    Response update(Integer id, @Valid CourseRequest courseRequest, File image);

    Response delete(Integer id);

}
