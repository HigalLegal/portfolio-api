package dev.higormorais.services.impl;

import dev.higormorais.client.ApiImgBB;
import dev.higormorais.dto.mappers.TechnologyMapper;
import dev.higormorais.dto.requests.TechnologyRequest;
import dev.higormorais.dto.responses.TechnologyResponse;
import dev.higormorais.entities.Technology;
import dev.higormorais.repositories.KeyImgBbRepository;
import dev.higormorais.repositories.TechnologyRepository;
import dev.higormorais.services.TechnologyService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityNotFoundException;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import java.io.File;
import java.util.List;
import java.util.stream.Collectors;

import static dev.higormorais.utils.ExternalRequest.imageUpload;
import static dev.higormorais.utils.RemovalById.deleteAbstract;

@ApplicationScoped
public class TechnologyServiceImpl implements TechnologyService {

    @Inject
    private TechnologyRepository technologyRepository;

    @Inject
    private TechnologyMapper technologyMapper;

    @Inject
    @ConfigProperty(name = "registro.inexistente")
    private String messageNotFound;

    @Inject
    private KeyImgBbRepository keyImgBbRepository;

    @RestClient
    private ApiImgBB imageAPI;

    // ----------------------------------------------------------------------------------------
    
    @Override
    public List<TechnologyResponse> listAll(int offset, int limit) {
        return technologyRepository
                .findAll(offset, limit)
                .stream()
                .map(technologyMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public void create(TechnologyRequest technologyRequest, File image) {
        String urlImage = imageUpload(imageAPI, image, keyImgBbRepository.returnKey());

        Technology technology = technologyMapper.toEntitie(technologyRequest);
        technology.setUrlImage(urlImage);

        technologyRepository.persist(technology);
    }

    @Override
    public void update(Integer id, TechnologyRequest technologyRequest, File image) {
        String urlImage = imageUpload(imageAPI, image, keyImgBbRepository.returnKey());

        Technology technology = technologyMapper.toEntitie(technologyRequest);
        technology.setId(id);
        technology.setUrlImage(urlImage);

        technologyRepository.update(technology);
    }

    @Override
    public void delete(Integer id) {
        deleteAbstract(id, technologyRepository, messageNotFound);
    }
}
