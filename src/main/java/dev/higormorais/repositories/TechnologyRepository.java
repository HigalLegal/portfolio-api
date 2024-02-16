package dev.higormorais.repositories;


import java.util.List;

import dev.higormorais.entities.Technology;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import io.quarkus.panache.common.Parameters;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import static dev.higormorais.utils.ExceptionUtil.throwExceptionNotFound;
import static dev.higormorais.utils.GeneratedJPQL.addQuery;
import static dev.higormorais.utils.GeneratedJPQL.excludeImageIfNull;


@ApplicationScoped
public class TechnologyRepository implements PanacheRepositoryBase<Technology, Integer> {

    @Inject
    private EntityManager entityManager;

    @Inject
    @ConfigProperty(name = "registro.inexistente")
    private String messageNotFound;

    public List<Technology> findAll(int offset, int limit) {

        limit = offset > limit ? (int) this.count() : limit;

        return entityManager.createQuery( "SELECT t FROM Technology c ORDER BY t.importanceLevel DESC", Technology.class)
                .setFirstResult(Math.max(offset, 0))
                .setMaxResults(Math.max(limit, 0))
                .getResultList();
    }

    public void update(Technology technology) {

        String urlImageQuery = addQuery(technology.getUrlImage(), "p");

        String jpql = "UPDATE Course c SET c.name = :name," + urlImageQuery + " c.urlCertificate = :urlCertificate, " +
                "c.importanceLevel = :importanceLevel WHERE c.id = :id";

        Parameters parameters = excludeImageIfNull(technology.parametersValue(), technology.getUrlImage());

        int rowsAffected = this.update(jpql, parameters);

        throwExceptionNotFound(rowsAffected, messageNotFound);

    }

}
