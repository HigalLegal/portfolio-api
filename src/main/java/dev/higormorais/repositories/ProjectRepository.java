package dev.higormorais.repositories;


import java.util.Map;

import dev.higormorais.entities.Project;
import dev.higormorais.repositories.utils.RepositoryUtils;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import io.quarkus.panache.common.Parameters;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityNotFoundException;


@ApplicationScoped
public class ProjectRepository implements PanacheRepositoryBase<Project, Integer> {

    public void update(Project project) {
        Map<String, Parameters> query = RepositoryUtils.createQueryUpdate(
                Project.class.getSimpleName(),
                Project.attributes(),
                Project.attributesQuery(),
                project.values(),
                false
        );

        String queryJPQL = query.keySet().stream().findFirst().get();
        Parameters queryParameters = query.values().stream().findFirst().get();

        int rowsAffected = this.update(queryJPQL, queryParameters);

        if(rowsAffected == 0) {
            throw new EntityNotFoundException("Registro de projeto inexistente.");
        }
    }

}
