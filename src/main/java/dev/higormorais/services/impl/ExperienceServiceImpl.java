package dev.higormorais.services.impl;

import dev.higormorais.dto.mappers.ExperienceMapper;
import dev.higormorais.dto.requests.ExperienceRequest;
import dev.higormorais.dto.responses.ExperienceResponse;
import dev.higormorais.entities.Experience;
import dev.higormorais.repositories.ExperienceRepository;
import dev.higormorais.services.ExperienceService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityNotFoundException;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import java.util.List;
import java.util.stream.Collectors;

import static dev.higormorais.utils.RemovalById.deleteAbstract;

@ApplicationScoped
public class ExperienceServiceImpl implements ExperienceService {

    @Inject
    private ExperienceRepository experienceRepository;

    @Inject
    private ExperienceMapper experienceMapper;

    @Inject
    @ConfigProperty(name = "registro.inexistente")
    private String messageNotFound;

    // -------------------------------------------------------------------------------------------

    @Override
    public List<ExperienceResponse> listAll(int offset, int limit) {
        return experienceRepository
                .findAll(offset, limit)
                .stream()
                .map(experienceMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public ExperienceResponse byId(Integer id) {
        Experience experience = experienceRepository
                .findByIdOptional(id)
                .orElseThrow(this::throwsNotFoundException);

        return experienceMapper.toResponse(experience);
    }

    @Override
    public void create(ExperienceRequest experienceRequest) {
        experienceRepository.persist(experienceMapper.toEntitie(experienceRequest));
    }

    @Override
    public void update(Integer id, ExperienceRequest experienceRequest) {

        Experience experience = experienceMapper.toEntitie(experienceRequest);
        experience.setId(id);

        experienceRepository.update(experience);
    }

    @Override
    public void delete(Integer id) {
        deleteAbstract(id, experienceRepository, messageNotFound);
    }

    private EntityNotFoundException throwsNotFoundException() {
        return new EntityNotFoundException("Experiência inexistente.");
    }
}
