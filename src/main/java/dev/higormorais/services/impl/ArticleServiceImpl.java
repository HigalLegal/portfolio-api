package dev.higormorais.services.impl;

import dev.higormorais.dto.mappers.ArticleMapper;
import dev.higormorais.dto.requests.ArticleRequest;
import dev.higormorais.dto.responses.ArticleResponse;
import dev.higormorais.repositories.ArticleRepository;
import dev.higormorais.services.ArticleService;
import jakarta.inject.Inject;

import java.util.List;
import java.util.stream.Collectors;

public class ArticleServiceImpl implements ArticleService {

    @Inject
    private ArticleRepository articleRepository;

    @Inject
    private ArticleMapper articleMapper;

    // --------------------------------------------------------------------

    @Override
    public List<ArticleResponse> listAll(int offset, int limit) {
        return articleRepository
                .findAll(offset, limit)
                .stream()
                .map(articleMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<ArticleResponse> listByName(String nameArticle) {
        return null;
    }

    @Override
    public void create(ArticleRequest articleRequest) {

    }

    @Override
    public void update(Integer id, ArticleRequest articleRequest) {

    }

    @Override
    public void delete(Integer id) {

    }
}
