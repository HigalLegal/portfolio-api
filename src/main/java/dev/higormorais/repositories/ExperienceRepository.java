package dev.higormorais.repositories;


import static dev.higormorais.repositories.utils.RepositoryUtils.createQueryUpdate;

import java.util.Map;

import dev.higormorais.entities.Experience;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import io.quarkus.panache.common.Parameters;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityNotFoundException;


@ApplicationScoped
public class ExperienceRepository implements PanacheRepositoryBase<Experience, Integer> {

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
