package dev.higormorais.dto.mappers.impl;

import dev.higormorais.dto.mappers.TechnologyMapper;
import dev.higormorais.dto.requests.TechnologyRequest;
import dev.higormorais.dto.responses.TechnologyResponse;
import dev.higormorais.entities.Technology;
import jakarta.enterprise.context.ApplicationScoped;


@ApplicationScoped
public class TechnologyMapperImpl implements TechnologyMapper {

    @Override
    public Technology toEntitie(TechnologyRequest request) {
        return Technology
                .builder()
                .name(request.getName())
                .importanceLevel(request.getImportanceLevel())
                .build();
    }

    @Override
    public TechnologyResponse toResponse(Technology entitie) {
        return TechnologyResponse
                .builder()
                .id(entitie.getId())
                .name(entitie.getName())
                .urlImage(entitie.getUrlImage())
                .importanceLevel(entitie.getImportanceLevel())
                .build();
    }
}
