package dev.higormorais.dto.mappers.impl;

import dev.higormorais.dto.mappers.CourseMapper;
import dev.higormorais.dto.requests.CourseRequest;
import dev.higormorais.dto.responses.CourseResponse;
import dev.higormorais.entities.Course;
import dev.higormorais.utils.Converter;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.stream.Collectors;

@ApplicationScoped
public class CourseMapperImpl implements CourseMapper {

    @Override
    public Course toEntitie(CourseRequest request) {
        return Course
                .builder()
                .name(request.getName())
                .urlCertificate(request.getUrlCertificate())
                .importanceLevel(request.getImportanceLevel())
                .technologies(request
                        .getTechnologiesIds()
                        .stream()
                        .map(Converter::integerIdToTechnology)
                        .collect(Collectors.toList()))
                .build();
    }

    @Override
    public CourseResponse toResponse(Course entitie) {
        return CourseResponse
                .builder()
                .id(entitie.getId())
                .name(entitie.getName())
                .urlImage(entitie.getUrlImage())
                .urlCertificate(entitie.getUrlCertificate())
                .importanceLevel(entitie.getImportanceLevel())
                .technologies(entitie
                        .getTechnologies()
                        .stream()
                        .map(Converter::technologyToString)
                        .collect(Collectors.toList()))
                .build();
    }
}
