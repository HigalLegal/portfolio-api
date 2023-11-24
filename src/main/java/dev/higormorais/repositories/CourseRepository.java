package dev.higormorais.repositories;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import dev.higormorais.entities.Course;
import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import io.quarkus.panache.common.Parameters;
import io.quarkus.panache.common.Sort;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityNotFoundException;

import static dev.higormorais.repositories.utils.RepositoryUtils.createQueryUpdate;
import static dev.higormorais.repositories.utils.RepositoryUtils.insertPagination;

@ApplicationScoped
public class CourseRepository implements PanacheRepositoryBase<Course, Integer> {

    public List<Course> findBy(String courseName, String technologyName, Integer page, Integer size) {

        String queryJPQL = this.createQuery(courseName, technologyName);

        Parameters parameters = Parameters
                .with("courseName", "%" + courseName + "%")
                .and("technologyName", technologyName);

        PanacheQuery<Course> courses = this
                .find(queryJPQL, Sort.by("importanceLevel"), parameters);

        return insertPagination(courses, page, size);

    }

    public void update(Course course) {

        Map<String, Parameters> query = createQueryUpdate(
                Course.class.getSimpleName(),
                Course.attributes(),
                Course.attributesQuery(),
                course.values(),
                false
        );

        String queryJPQL = query.keySet().stream().findFirst().get();
        Parameters queryParameters = query.values().stream().findFirst().get();

        int rowsAffected = this.update(queryJPQL, queryParameters);

        if(rowsAffected == 0) {
            throw new EntityNotFoundException("Registro de curso inexistente.");
        }


    }

    private String createQuery(String courseName, String technologyName) {

        StringBuilder queryJPQL = new StringBuilder("SELECT c FROM Course c WHERE 1=1");

        if(courseName != null) {
            queryJPQL.append(" AND c.name LIKE :courseName");
        } else if(technologyName != null) {
            queryJPQL.append(" AND EXISTS ( SELECT t FROM Technology t WHERE t.name = :technologyName"
                    + " AND t MEMBER OF c.technologies )");
        }

        return queryJPQL.toString();

    }

}
