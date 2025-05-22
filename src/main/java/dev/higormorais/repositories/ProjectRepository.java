package dev.higormorais.repositories;


import java.util.List;

import dev.higormorais.entities.Project;
import dev.higormorais.entities.Technology;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import static dev.higormorais.utils.Converter.technologiesToIds;
import static dev.higormorais.utils.IntegerNumberOperations.idealLimitReturn;


@ApplicationScoped
public class ProjectRepository implements PanacheRepositoryBase<Project, Integer> {

    @Inject
    private EntityManager entityManager;

    @Inject
    @ConfigProperty(name = "registro.inexistente")
    private String messageNotFound;

    @Inject
    private TechnologyRepository technologyRepository;

    public List<Project> findAll(int offset, int limit) {

        limit = idealLimitReturn(limit, offset, (int) this.count());

        return entityManager.createQuery( "SELECT p FROM Project p ORDER BY p.importanceLevel DESC", Project.class)
                .setFirstResult(Math.max(offset, 0))
                .setMaxResults(Math.max(limit, 0))
                .getResultList();
    }

    public void update(Project project) {

        List<Integer> idsTechnologies = technologiesToIds(project.getTechnologiesWorked());

        List<Technology> technologies = technologyRepository.findByTechnologiesByIds(idsTechnologies);

        Project managedProject = this
                .findByIdOptional(project.getId())
                .orElseThrow(() -> new EntityNotFoundException("Curso não encontrado."));

        managedProject.setDescription(project.getDescription());
        managedProject.setUrlRepository(project.getUrlRepository());
        managedProject.setImportanceLevel(project.getImportanceLevel());

        if(project.getUrlImage() != null) {
            managedProject.setUrlImage(project.getUrlImage());
        }

        this.entityManager.merge(managedProject);

    }

}
