package dev.higormorais.services;

import dev.higormorais.dto.responses.TechnologyResponse;

import java.io.File;
import java.util.List;

public interface TechnologyService {

    List<TechnologyResponse> listAll(int offset, int limit);

    void create(TechnologyResponse technologyResponse, File image);

    void update(Integer id, TechnologyResponse technologyResponse, File image);

    void delete(Integer id);
}
