package dev.higormorais.repositories;


import static dev.higormorais.utils.Converter.technologiesToIds;
import static dev.higormorais.utils.ExceptionUtil.throwExceptionNotFound;
import static dev.higormorais.utils.GeneratedJPQL.mapToParameters;
import static dev.higormorais.utils.IntegerNumberOperations.idealLimitReturn;

import java.util.List;

import dev.higormorais.entities.Experience;
import dev.higormorais.entities.Project;
import dev.higormorais.entities.Technology;
import dev.higormorais.exceptions.DateException;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import io.quarkus.panache.common.Parameters;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;
import org.eclipse.microprofile.config.inject.ConfigProperty;


@ApplicationScoped
public class ExperienceRepository implements PanacheRepositoryBase<Experience, Integer> {

    @Inject
    private EntityManager entityManager;

    @Inject
    @ConfigProperty(name = "registro.inexistente")
    private String messageNotFound;

    @Inject
    private TechnologyRepository technologyRepository;

    public List<Experience> findAll(int offset, int limit) {

        limit = idealLimitReturn(limit, offset, (int) this.count());

        return entityManager.createQuery( "SELECT e FROM Experience e ORDER BY e.beginning DESC", Experience.class)
                .setFirstResult(Math.max(offset, 0))
                .setMaxResults(Math.max(limit, 0))
                .getResultList();
    }

    public void insert(Experience experience) {

        throwDateException(experience);

        persist(experience);
    }

    public void update(Experience experience) {

        List<Integer> idsTechnologies = technologiesToIds(experience.getTechnologiesWorked());

        List<Technology> technologies = technologyRepository.findByTechnologiesByIds(idsTechnologies);

        Experience managedExperience = this
                .findByIdOptional(experience.getId())
                .orElseThrow(() -> new EntityNotFoundException("Experiência não encontrada."));

        managedExperience.setCompanyName(experience.getCompanyName());
        managedExperience.setDescription(experience.getDescription());
        managedExperience.setBeginning(experience.getBeginning());
        managedExperience.setEnd(experience.getEnd());
        managedExperience.setTechnologiesWorked(technologies);

        this.entityManager.merge(managedExperience);
    }

    private void throwDateException(Experience experience) {
        if(!experience.endIsAfterBeginning()) {
            throw new DateException("A data de admissão não pode ser antes da saída!");
        }
    }

}
