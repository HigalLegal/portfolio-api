package dev.higormorais.resources;

import dev.higormorais.dto.requests.ArticleRequest;
import jakarta.ws.rs.core.Response;

public interface ArticleResource {

    Response listAll(Integer offset, Integer limit);

    Response listByName(String nameArticle);

    Response create(ArticleRequest articleRequest);

    Response update(Integer id, ArticleRequest articleRequest);

    Response delete(Integer id);

}
