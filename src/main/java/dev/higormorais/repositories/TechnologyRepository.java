package dev.higormorais.repositories;


import java.util.List;
import java.util.Map;

import dev.higormorais.entities.Technology;
import dev.higormorais.repositories.utils.RepositoryUtils;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import io.quarkus.panache.common.Parameters;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;


@ApplicationScoped
public class TechnologyRepository implements PanacheRepositoryBase<Technology, Integer> {

    @Inject
    private EntityManager entityManager;

    public List<Technology> findAll(int offset, int limit) {

        limit = offset > limit ? (int) this.count() : limit;

        return entityManager.createQuery( "SELECT t FROM Technology c ORDER BY t.importanceLevel DESC", Technology.class)
                .setFirstResult(Math.max(offset, 0))
                .setMaxResults(Math.max(limit, 0))
                .getResultList();
    }

    public void update(Technology technology) {
        Map<String, Parameters> query = RepositoryUtils.createQueryUpdate(
                Technology.class.getSimpleName(),
                Technology.attributes(),
                Technology.attributesQuery(),
                technology.values(),
                false
        );

        String queryJPQL = query.keySet().stream().findFirst().get();
        Parameters queryParameters = query.values().stream().findFirst().get();

        int rowsAffected = this.update(queryJPQL, queryParameters);

        if(rowsAffected == 0) {
            throw new EntityNotFoundException("Registro de tecnologia inexistente.");
        }
    }

}
