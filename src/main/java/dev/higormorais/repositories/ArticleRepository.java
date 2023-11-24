package dev.higormorais.repositories;


import java.util.List;
import java.util.Map;

import dev.higormorais.entities.Article;
import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import io.quarkus.panache.common.Parameters;
import io.quarkus.panache.common.Sort;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityNotFoundException;

import static dev.higormorais.repositories.utils.RepositoryUtils.*;


@ApplicationScoped
public class ArticleRepository implements PanacheRepositoryBase<Article, Integer> {

    public List<Article> findAll(Integer page, Integer size) {
//        PanacheQuery<Article> allArticles = this.findAll(Sort.by("date"));

        return this.listAll();

    }

    public List<Article> findByName(String nameArticle) {

        Parameters nameParameter = Parameters.with("nameArticle", "%" + nameArticle + "%");

        String queryJPQL = "SELECT a FROM Article WHERE a.name LIKE :nameArticle";

        return this.find(queryJPQL, nameParameter).list();
    }

    public void update(Article article) {

        Map<String, Parameters> query = createQueryUpdate(
                Article.class.getSimpleName(),
                Article.namesAttributes(),
                Article.namesQuery(),
                article.values(),
                true
        );

        String queryJPQL = query.keySet().stream().findFirst().get();
        Parameters queryParameters = query.values().stream().findFirst().get();

        int rowsAffected =  this.update(queryJPQL, queryParameters);

        if(rowsAffected == 0) {
            throw new EntityNotFoundException("Registro de artigo inexistente.");
        }
    }

}
