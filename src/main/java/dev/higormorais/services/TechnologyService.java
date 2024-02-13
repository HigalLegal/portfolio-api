package dev.higormorais.services;

import dev.higormorais.dto.requests.TechnologyRequest;
import dev.higormorais.dto.responses.TechnologyResponse;
import dev.higormorais.entities.Technology;

import java.io.File;
import java.util.List;

public interface TechnologyService {

    List<TechnologyResponse> listAll(int offset, int limit);

    void create(TechnologyRequest technologyRequest, File image);

    void update(Integer id, TechnologyRequest technologyRequest, File image);

    void delete(Integer id);
}
