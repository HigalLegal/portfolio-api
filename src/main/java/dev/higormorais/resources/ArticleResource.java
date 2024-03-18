package dev.higormorais.resources;

import dev.higormorais.dto.requests.ArticleRequest;
import jakarta.validation.Valid;
import jakarta.ws.rs.core.Response;

public interface ArticleResource {

    Response listAll(Integer offset, Integer limit);

    Response listByTitle(String nameArticle);

    Response create(@Valid ArticleRequest articleRequest);

    Response update(Integer id, @Valid ArticleRequest articleRequest);

    Response delete(Integer id);

}
