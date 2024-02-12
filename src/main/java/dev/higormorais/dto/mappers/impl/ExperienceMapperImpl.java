package dev.higormorais.dto.mappers.impl;

import dev.higormorais.dto.mappers.ExperienceMapper;
import dev.higormorais.dto.requests.ExperienceRequest;
import dev.higormorais.dto.responses.ExperienceResponse;
import dev.higormorais.entities.Experience;
import dev.higormorais.utils.Converter;
import jakarta.enterprise.context.ApplicationScoped;

import java.time.LocalDate;
import java.util.stream.Collectors;

@ApplicationScoped
public class ExperienceMapperImpl implements ExperienceMapper {

    @Override
    public Experience toEntitie(ExperienceRequest request) {
        return Experience
                .builder()
                .companyName(request.getCompanyName())
                .description(request.getDescription())
                .beginning(request.getBeginning())
                .end(request.getEnd())
                .technologiesWorked(request
                        .getTechnologiesWorkedId()
                        .stream()
                        .map(Converter::integerIdToTechnology)
                        .collect(Collectors.toList()))
                .builder();
    }

    @Override
    public ExperienceResponse toResponse(Experience entitie) {
        return ExperienceResponse
                .builder()
                .id(entitie.getId())
                .companyName(entitie.getCompanyName())
                .description(entitie.getDescription())
                .beginning(localDateToString(entitie.getBeginning()))
                .end(localDateToString(entitie.getEnd()))
                .technologiesWorked(entitie
                        .getTechnologiesWorked()
                        .stream()
                        .map(Converter::technologyToString)
                        .collect(Collectors.toList()))
                .build();
    }


    private String localDateToString(LocalDate date) {

        if(date == null) {
            return null;
        }

        String month = Converter.localDateToMonthString(date);
        int year = date.getYear();

        return month + " de " + year;
    }

}
