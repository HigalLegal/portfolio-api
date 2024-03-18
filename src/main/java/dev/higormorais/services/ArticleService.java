package dev.higormorais.services;

import dev.higormorais.dto.requests.ArticleRequest;
import dev.higormorais.dto.responses.ArticleResponse;

import java.util.List;

public interface ArticleService {

    List<ArticleResponse> listAll(int offset, int limit);

    List<ArticleResponse> listByTitle(String nameArticle);

    void create(ArticleRequest articleRequest);

    void update(Integer id, ArticleRequest articleRequest);

    void delete(Integer id);

}
