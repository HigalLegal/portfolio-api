package dev.higormorais.repositories;


import java.util.List;
import java.util.Map;

import dev.higormorais.entities.Article;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import io.quarkus.panache.common.Parameters;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import static dev.higormorais.repositories.utils.RepositoryUtils.*;


@ApplicationScoped
public class ArticleRepository implements PanacheRepositoryBase<Article, Integer> {

    @Inject
    EntityManager entityManager;

    @Inject
    @ConfigProperty(name = "registro.inexistente")
    private String messageNotFound;

    public List<Article> findAll(int offset, int limit) {

        limit = offset > limit ? (int) this.count() : limit;

        return entityManager.createQuery( "SELECT a FROM Article a ORDER BY a.date DESC", Article.class)
                .setFirstResult(Math.max(offset, 0))
                .setMaxResults(Math.max(limit, 0))
                .getResultList();
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
            throw new EntityNotFoundException(messageNotFound);
        }
    }

}
