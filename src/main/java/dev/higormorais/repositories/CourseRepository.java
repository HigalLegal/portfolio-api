package dev.higormorais.repositories;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import dev.higormorais.entities.Article;
import dev.higormorais.entities.Course;
import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import io.quarkus.panache.common.Parameters;
import io.quarkus.panache.common.Sort;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;

import static dev.higormorais.repositories.utils.RepositoryUtils.createQueryUpdate;
import static dev.higormorais.repositories.utils.RepositoryUtils.insertPagination;

@ApplicationScoped
public class CourseRepository implements PanacheRepositoryBase<Course, Integer> {

    @Inject
    private EntityManager entityManager;

    public List<Course> findAll(int offset, int limit) {

        limit = offset > limit ? (int) this.count() : limit;

        return entityManager.createQuery( "SELECT c FROM Course c ORDER BY c.importanceLevel DESC", Course.class)
                .setFirstResult(Math.max(offset, 0))
                .setMaxResults(Math.max(limit, 0))
                .getResultList();
    }

    public List<Course> findByName(String name) {

        String jpql = "SELECT c FROM Course c WHERE lower(c.name) LIKE lower(concat('%', :partialName, '%'))";
        Parameters nameParameter = Parameters.with("partialName", name);

        return this.find(jpql, nameParameter).list();
    }


    public List<Course> findByTechnologyName(String technologyName) {

        String jpql = "SELECT c FROM Course c " +
                "JOIN c.technologies t " +
                "WHERE lower(t.name) LIKE lower(:technologyName)";
        Parameters nameParameter = Parameters.with("technologyName", "%" + technologyName + "%");

        return this.find(jpql, nameParameter).list();
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
