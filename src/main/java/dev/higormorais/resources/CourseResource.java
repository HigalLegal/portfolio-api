package dev.higormorais.resources;

import dev.higormorais.dto.requests.CourseRequest;
import jakarta.ws.rs.core.Response;

import java.io.File;

public interface CourseResource {

    Response listAll(Integer offset, Integer limit);

    Response listByName(String name);

    Response listByTechnology(String technology);

    Response create(CourseRequest courseRequest, File image);

    Response update(Integer id, CourseRequest courseRequest, File image);

    Response delete(Integer id);

}
