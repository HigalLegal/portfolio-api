package dev.higormorais.services;

import dev.higormorais.dto.requests.CourseRequest;
import dev.higormorais.dto.responses.CourseResponse;

import java.io.File;
import java.util.List;

public interface CourseService {

    List<CourseResponse> listAll(int offset, int limit);

    List<CourseResponse> listByName(String name);

    List<CourseResponse> listByTechnology(String technology);

    CourseResponse byId(Integer id);

    void create(CourseRequest courseRequest, File image);

    void update(Integer id, CourseRequest courseRequest, File image);

    void delete(Integer id);

}
