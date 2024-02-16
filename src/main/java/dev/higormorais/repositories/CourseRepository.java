package dev.higormorais.repositories;

import java.util.List;

import dev.higormorais.entities.Course;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import io.quarkus.panache.common.Parameters;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import static dev.higormorais.utils.ExceptionUtil.throwExceptionNotFound;
import static dev.higormorais.utils.GeneratedJPQL.*;

@ApplicationScoped
public class CourseRepository implements PanacheRepositoryBase<Course, Integer> {

    @Inject
    private EntityManager entityManager;

    @Inject
    @ConfigProperty(name = "registro.inexistente")
    private String messageNotFound;

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

        String urlImageQuery = addQuery(course.getUrlImage(), "c");

        String jpql = "UPDATE Course c SET c.name = :name," + urlImageQuery + " c.urlCertificate = :urlCertificate, " +
                "c.importanceLevel = :importanceLevel WHERE c.id = :id";

        Parameters parameters = excludeImageIfNull(course.parametersValue(), course.getUrlImage());

        int rowsAffected = this.update(jpql, parameters);

        throwExceptionNotFound(rowsAffected, messageNotFound);
    }

}
