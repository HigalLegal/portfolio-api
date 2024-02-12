package dev.higormorais.dto.mappers;

import dev.higormorais.dto.requests.ExperienceRequest;
import dev.higormorais.dto.responses.ExperienceResponse;
import dev.higormorais.entities.Experience;

public interface ExperienceMapper extends Mapper<Experience, ExperienceRequest, ExperienceResponse> {
}
