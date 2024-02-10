package dev.higormorais.dto.mappers.impl;

import dev.higormorais.dto.mappers.Mapper;
import dev.higormorais.dto.requests.ProjectRequest;
import dev.higormorais.dto.responses.ProjectResponse;
import dev.higormorais.entities.Project;
import dev.higormorais.utils.Converter;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Named;

import java.util.stream.Collectors;

@ApplicationScoped
@Named("project")
public class ProjectMapper implements Mapper<Project, ProjectRequest, ProjectResponse> {

    @Override
    public Project toEntitie(ProjectRequest request) {
        return Project
                .build()
                .description(request.getDescription())
                .urlRepository(request.getUrlRepository())
                .importanceLevel(request.getImportanceLevel())
                .technologiesWorked(request
                        .getTechnologiesWorkedId()
                        .stream()
                        .map(Converter::integerIdToTechnology)
                        .collect(Collectors.toList()))
                .builder();
    }

    @Override
    public ProjectResponse toResponse(Project entitie) {
        return ProjectResponse
                .builder()
                .id(entitie.getId())
                .description(entitie.getDescription())
                .urlRepository(entitie.getUrlRepository())
                .urlImage(entitie.getUrlImage())
                .importanceLevel(entitie.getImportanceLevel())
                .technologiesWorked(entitie
                        .getTechnologiesWorked()
                        .stream()
                        .map(Converter::technologyToString)
                        .collect(Collectors.toList()))
                .build();
    }


}
