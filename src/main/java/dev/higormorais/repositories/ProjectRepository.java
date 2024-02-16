package dev.higormorais.repositories;


import java.util.List;

import dev.higormorais.entities.Project;
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
public class ProjectRepository implements PanacheRepositoryBase<Project, Integer> {

    @Inject
    private EntityManager entityManager;

    @Inject
    @ConfigProperty(name = "registro.inexistente")
    private String messageNotFound;

    public List<Project> findAll(int offset, int limit) {

        limit = offset > limit ? (int) this.count() : limit;

        return entityManager.createQuery( "SELECT p FROM Project p ORDER BY p.importanceLevel DESC", Project.class)
                .setFirstResult(Math.max(offset, 0))
                .setMaxResults(Math.max(limit, 0))
                .getResultList();
    }

    public void update(Project project) {

        String urlImageQuery = addQuery(project.getUrlImage(), "p");

        String jpql = "UPDATE Course c SET c.name = :name," + urlImageQuery + " c.urlCertificate = :urlCertificate, " +
                "c.importanceLevel = :importanceLevel WHERE c.id = :id";

        Parameters parameters = excludeImageIfNull(project.parametersValue(), project.getUrlImage());

        int rowsAffected = this.update(jpql, parameters);

        throwExceptionNotFound(rowsAffected, messageNotFound);

    }

}
