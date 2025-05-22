package dev.higormorais.repositories;

import java.util.List;

import dev.higormorais.entities.Course;
import dev.higormorais.entities.Technology;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import io.quarkus.panache.common.Parameters;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import static dev.higormorais.utils.Converter.technologiesToIds;
import static dev.higormorais.utils.IntegerNumberOperations.idealLimitReturn;

@ApplicationScoped
public class CourseRepository implements PanacheRepositoryBase<Course, Integer> {

    @Inject
    private EntityManager entityManager;

    @Inject
    @ConfigProperty(name = "registro.inexistente")
    private String messageNotFound;

    @Inject
    private TechnologyRepository technologyRepository;

    public List<Course> findAll(int offset, int limit) {

        limit = idealLimitReturn(limit, offset, (int) this.count());

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

        List<Integer> idsTechnologies = technologiesToIds(course.getTechnologies());

        List<Technology> technologies = technologyRepository.findByTechnologiesByIds(idsTechnologies);

        Course managedCourse = this
                .findByIdOptional(course.getId())
                .orElseThrow(() -> new EntityNotFoundException("Curso não encontrado."));

        managedCourse.setName((course.getName()));
        managedCourse.setUrlCertificate(course.getUrlCertificate());
        managedCourse.setImportanceLevel(course.getImportanceLevel());
        managedCourse.setTechnologies(technologies);

        if(course.getUrlImage() != null) {
            managedCourse.setUrlImage(course.getUrlImage());
        }

        this.entityManager.merge(managedCourse);
    }

}
