package dev.higormorais.services.impl;

import static dev.higormorais.utils.ExternalRequest.imageUpload;
import static dev.higormorais.utils.RemovalById.deleteAbstract;

import dev.higormorais.client.ApiImgBB;
import dev.higormorais.dto.mappers.ProjectMapper;
import dev.higormorais.dto.requests.ProjectRequest;
import dev.higormorais.dto.responses.ArticleResponse;
import dev.higormorais.dto.responses.ProjectResponse;
import dev.higormorais.entities.Article;
import dev.higormorais.entities.Project;
import dev.higormorais.repositories.ProjectRepository;
import dev.higormorais.repositories.KeyImgBbRepository;
import dev.higormorais.services.ProjectService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityNotFoundException;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import java.io.File;
import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class ProjectServiceImpl implements ProjectService {

    @Inject
    private ProjectRepository projectRepository;

    @Inject
    private ProjectMapper projectMapper;

    @Inject
    @ConfigProperty(name = "registro.inexistente")
    private String messageNotFound;

    @Inject
    private KeyImgBbRepository keyImgBbRepository;

    @RestClient
    private ApiImgBB imageAPI;
    
    // ----------------------------------------------------------------------------------------
    
    @Override
    public List<ProjectResponse> listAll(int offset, int limit) {
        return projectRepository
                .findAll(offset, limit)
                .stream()
                .map(projectMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public ProjectResponse byId(Integer id) {
        Project project = projectRepository
                .findByIdOptional(id)
                .orElseThrow(this::throwsNotFoundException);
        return projectMapper.toResponse(project);
    }


    @Override
    public void create(ProjectRequest projectRequest, File image) {
        String urlImage = imageUpload(imageAPI, image, keyImgBbRepository.returnKey());

        Project project = projectMapper.toEntitie(projectRequest);
        project.setUrlImage(urlImage);

        projectRepository.persist(project);

    }

    @Override
    public void update(Integer id, ProjectRequest projectRequest, File image) {
        String urlImage = imageUpload(imageAPI, image, keyImgBbRepository.returnKey());

        Project project = projectMapper.toEntitie(projectRequest);
        project.setId(id);
        project.setUrlImage(urlImage);

        projectRepository.persist(project);
    }

    @Override
    public void delete(Integer id) {
        deleteAbstract(id, projectRepository, messageNotFound);
    }

    private EntityNotFoundException throwsNotFoundException() {
        return new EntityNotFoundException("Projeto não encontrado.");
    }
}
