package dev.higormorais.repositories;


import static dev.higormorais.utils.ExceptionUtil.throwExceptionNotFound;
import static dev.higormorais.utils.GeneratedJPQL.mapToParameters;

import java.util.List;

import dev.higormorais.entities.Experience;
import dev.higormorais.exceptions.DateException;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import io.quarkus.panache.common.Parameters;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import org.eclipse.microprofile.config.inject.ConfigProperty;


@ApplicationScoped
public class ExperienceRepository implements PanacheRepositoryBase<Experience, Integer> {

    @Inject
    private EntityManager entityManager;

    @Inject
    @ConfigProperty(name = "registro.inexistente")
    private String messageNotFound;

    public List<Experience> findAll(int offset, int limit) {

        limit = offset > limit ? (int) this.count() : limit;

        return entityManager.createQuery( "SELECT e FROM Experience e ORDER BY e.beginning DESC", Experience.class)
                .setFirstResult(Math.max(offset, 0))
                .setMaxResults(Math.max(limit, 0))
                .getResultList();
    }

    public void insert(Experience experience) {

        if(!experience.endIsAfterBeginning()) {
            throw new DateException("A data de admissão não pode ser antes da saída!");
        }

        persist(experience);
    }

    public void update(Experience experience) {

        String jpql = "UPDATE Experience e SET e.companyName = :companyName, e.description = :description, " +
                "e.beginning = :beginning, e.end = :end, e.technologiesWorked = :technologiesWorked WHERE e.id = :id";

        Parameters parameters = mapToParameters(experience.parametersValue());

        int rowsAffected = this.update(jpql, parameters);

        throwExceptionNotFound(rowsAffected, messageNotFound);

    }

}
