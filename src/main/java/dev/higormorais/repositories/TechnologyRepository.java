package dev.higormorais.repositories;


import java.util.Map;

import dev.higormorais.entities.Project;
import dev.higormorais.entities.Technology;
import dev.higormorais.repositories.utils.RepositoryUtils;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import io.quarkus.panache.common.Parameters;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityNotFoundException;


@ApplicationScoped
public class TechnologyRepository implements PanacheRepositoryBase<Technology, Integer> {

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
