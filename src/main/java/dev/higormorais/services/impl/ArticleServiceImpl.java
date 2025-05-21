package dev.higormorais.services.impl;

import static dev.higormorais.utils.RemovalById.deleteAbstract;

import dev.higormorais.dto.mappers.ArticleMapper;
import dev.higormorais.dto.requests.ArticleRequest;
import dev.higormorais.dto.responses.ArticleResponse;
import dev.higormorais.entities.Article;
import dev.higormorais.repositories.ArticleRepository;
import dev.higormorais.services.ArticleService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityNotFoundException;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class ArticleServiceImpl implements ArticleService {

    @Inject
    private ArticleRepository articleRepository;

    @Inject
    private ArticleMapper articleMapper;

    @Inject
    @ConfigProperty(name = "registro.inexistente")
    private String messageNotFound;

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
    public List<ArticleResponse> listByTitle(String title) {
        return articleRepository
                .findByTitle(title)
                .stream()
                .map(articleMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public ArticleResponse byId(Integer id) {
        Article article = articleRepository
                .findByIdOptional(id)
                .orElseThrow(this::throwsNotFoundException);
        return articleMapper.toResponse(article);
    }

    @Override
    public void create(ArticleRequest articleRequest) {
        articleRepository.persist(articleMapper.toEntitie(articleRequest));
    }

    @Override
    public void update(Integer id, ArticleRequest articleRequest) {
        Article article = articleMapper.toEntitie(articleRequest);
        article.setId(id);

        articleRepository.update(article);
    }

    @Override
    public void delete(Integer id) {
        deleteAbstract(id, articleRepository, messageNotFound);
    }

    private EntityNotFoundException throwsNotFoundException() {
        return new EntityNotFoundException("Artigo não encontrado.");
    }
}
