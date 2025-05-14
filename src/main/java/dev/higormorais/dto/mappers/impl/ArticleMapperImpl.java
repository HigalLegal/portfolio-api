package dev.higormorais.dto.mappers.impl;

import dev.higormorais.dto.mappers.ArticleMapper;
import dev.higormorais.dto.requests.ArticleRequest;
import dev.higormorais.dto.responses.ArticleResponse;
import dev.higormorais.entities.Article;
import dev.higormorais.utils.Converter;
import jakarta.enterprise.context.ApplicationScoped;

import java.time.LocalDate;
import java.util.stream.Collectors;

@ApplicationScoped
public class ArticleMapperImpl implements ArticleMapper {

    @Override
    public Article toEntitie(ArticleRequest request) {
        return Article
                .builder()
                .title(request.getTitle())
                .summary(request.getSummary())
                .urlArticle(request.getUrlArticle())
                .technologiesCovered(request.getTechnologiesCoveredId()
                        .stream()
                        .map(Converter::integerIdToTechnology)
                        .collect(Collectors.toList()))
                .build();
    }

    @Override
    public ArticleResponse toResponse(Article entitie) {
        return ArticleResponse
                .builder()
                .id(entitie.getId())
                .title(entitie.getTitle())
                .summary(entitie.getSummary())
                .urlArticle(entitie.getUrlArticle())
                .technologiesCovered(entitie
                        .getTechnologiesCovered()
                        .stream()
                        .map(Converter::technologyToString)
                        .collect(Collectors.toList()))
                .build();
    }

}
