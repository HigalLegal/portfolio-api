package dev.higormorais.repositories;


import java.util.List;

import dev.higormorais.entities.Article;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import io.quarkus.panache.common.Parameters;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import static dev.higormorais.utils.IntegerNumberOperations.idealLimitReturn;

@ApplicationScoped
public class ArticleRepository implements PanacheRepositoryBase<Article, Integer> {

    @Inject
    EntityManager entityManager;

    @Inject
    @ConfigProperty(name = "registro.inexistente")
    private String messageNotFound;

    public List<Article> findAll(int offset, int limit) {

        limit = idealLimitReturn(limit, offset, (int) this.count());

        return entityManager.createQuery( "SELECT a FROM Article a ORDER BY a.date DESC", Article.class)
                .setFirstResult(Math.max(offset, 0))
                .setMaxResults(Math.max(limit, 0))
                .getResultList();
    }

    public List<Article> findByTitle(String title) {

        Parameters nameParameter = Parameters.with("title", "%".concat(title).concat("%"));

        String queryJPQL = "SELECT a FROM Article a WHERE LOWER(a.title) LIKE LOWER(:title)";

        return this.find(queryJPQL, nameParameter).list();
    }

    public void update(Article article) {
        if(this.isPersistent(article)) {
            entityManager.merge(article);
        } else {
            throw new EntityNotFoundException(messageNotFound);
        }
    }

}
