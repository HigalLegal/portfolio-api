package dev.higormorais.repositories;


import static dev.higormorais.repositories.utils.RepositoryUtils.createQueryUpdate;

import java.util.List;
import java.util.Map;

import dev.higormorais.entities.Course;
import dev.higormorais.entities.Experience;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import io.quarkus.panache.common.Page;
import io.quarkus.panache.common.Parameters;
import io.quarkus.panache.common.Sort;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;


@ApplicationScoped
public class ExperienceRepository implements PanacheRepositoryBase<Experience, Integer> {

    @Inject
    private EntityManager entityManager;

    public List<Experience> findAll(int offset, int limit) {

        limit = offset > limit ? (int) this.count() : limit;

        return entityManager.createQuery( "SELECT e FROM Experience e ORDER BY e.beginning DESC", Experience.class)
                .setFirstResult(Math.max(offset, 0))
                .setMaxResults(Math.max(limit, 0))
                .getResultList();
    }

    public void update(Experience experience) {
        Map<String, Parameters> query = createQueryUpdate(
                Experience.class.getSimpleName(),
                Experience.attributes(),
                Experience.attributesQuery(),
                experience.values(),
                true
        );

        String queryJPQL = query.keySet().stream().findFirst().get();
        Parameters queryParameters = query.values().stream().findFirst().get();

        int rowsAffected = this.update(queryJPQL, queryParameters);

        if(rowsAffected == 0) {
            throw new EntityNotFoundException("Registro de experiência inexistente.");
        }
    }

}
