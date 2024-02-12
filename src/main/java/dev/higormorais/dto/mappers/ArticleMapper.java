package dev.higormorais.dto.mappers;

import dev.higormorais.dto.requests.ArticleRequest;
import dev.higormorais.dto.responses.ArticleResponse;
import dev.higormorais.entities.Article;

public interface ArticleMapper extends Mapper<Article, ArticleRequest, ArticleResponse> {
}
