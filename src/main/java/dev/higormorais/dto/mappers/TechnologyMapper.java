package dev.higormorais.dto.mappers;

import dev.higormorais.dto.requests.TechnologyRequest;
import dev.higormorais.dto.responses.TechnologyResponse;
import dev.higormorais.entities.Technology;

public interface TechnologyMapper extends Mapper<Technology, TechnologyRequest, TechnologyResponse> {
}
