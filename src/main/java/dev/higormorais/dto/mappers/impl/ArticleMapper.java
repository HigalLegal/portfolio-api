package dev.higormorais.dto.mappers.impl;

import dev.higormorais.dto.mappers.Mapper;
import dev.higormorais.dto.requests.ArticleRequest;
import dev.higormorais.dto.responses.ArticleResponse;
import dev.higormorais.entities.Article;
import dev.higormorais.utils.Converter;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Named;

import java.time.LocalDate;
import java.util.stream.Collectors;

@ApplicationScoped
@Named("article")
public class ArticleMapper implements Mapper<Article, ArticleRequest, ArticleResponse> {
    @Override
    public Article toEntitie(ArticleRequest request) {
        return Article
                .builder()
                .title(request.getTitle())
                .summary(request.getSummary())
                .urlArticle(request.getUrlArticle())
                .date(request.getDate())
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
                .date(dateToString(entitie.getDate()))
                .technologiesCovered(entitie
                        .getTechnologiesCovered()
                        .stream()
                        .map(Converter::technologyToString)
                        .collect(Collectors.toList()))
                .build();
    }

    private String dateToString(LocalDate date) {

        int day = date.getDayOfMonth();
        String month = Converter.localDateToMonthString(date);
        int year = date.getYear();

        return day + " de " + month + " de " + year;
    }


}
