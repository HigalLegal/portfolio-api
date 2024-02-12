package dev.higormorais.services;

import dev.higormorais.dto.requests.ProjectRequest;
import dev.higormorais.dto.responses.ProjectResponse;

import java.io.File;
import java.util.List;

public interface ProjectService {

    List<ProjectResponse> listAll(int offset, int limit);

    void create(ProjectRequest projectRequest, File image);

    void update(Integer id, ProjectRequest projectRequest, File image);

    void delete(Integer id);

}
