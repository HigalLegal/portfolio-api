package dev.higormorais.dto.mappers;

import dev.higormorais.dto.requests.ProjectRequest;
import dev.higormorais.dto.responses.ProjectResponse;
import dev.higormorais.entities.Project;

public interface ProjectMapper extends Mapper<Project, ProjectRequest, ProjectResponse> {
}
