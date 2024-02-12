package dev.higormorais.services;

import dev.higormorais.dto.requests.ExperienceRequest;
import dev.higormorais.dto.responses.ExperienceResponse;

import java.util.List;

public interface ExperienceService {

    List<ExperienceResponse> listAll(int offset, int limit);

    void create(ExperienceRequest experienceRequest);

    void update(Integer id, ExperienceRequest experienceRequest);

    void delete(Integer id);

}
