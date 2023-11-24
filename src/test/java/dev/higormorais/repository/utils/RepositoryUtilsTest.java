package dev.higormorais.repository.utils;

import org.junit.jupiter.api.Test;

import dev.higormorais.entities.Project;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.persistence.EntityNotFoundException;

import static dev.higormorais.repositories.utils.RepositoryUtils.createQueryUpdate;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;


@QuarkusTest
public class RepositoryUtilsTest {

    private static final String URL_IMAGE = "https://cdn.icon-icons.com/icons2/2415/PNG/512/"
            + "java_original_wordmark_logo_icon_146459.png";
    @Test
    public void testJPQL() {
        this.beging();

        String queryUpdate = "UPDATE Project p SET p.description = :newDescription, "
                + "p.urlRepository = :newUrlRepository, p.urlImage = :newUrlImage, "
                + "p.importanceLevel = :newImportanceLevel WHERE p.id = :projectId";

        String queryGenerated = createQueryUpdate(
                Project.class.getSimpleName(),
                Project.attributes(),
                Project.attributesQuery(),
                this.instanceProject().values(),
                false)

                .keySet()
                .stream()
                .findFirst()
                .get();

        System.out.println(queryUpdate);
        System.out.println(queryGenerated);

        assertTrue(queryUpdate.equals(queryGenerated));

        this.end();
    }

    @Test
    public void testJpqlNullImage() {
        this.beging();

        String queryUpdate = "UPDATE Project p SET p.description = :newDescription, "
                + "p.urlRepository = :newUrlRepository, p.importanceLevel = :newImportanceLevel "
                + "WHERE p.id = :projectId";

        String queryGenerated = createQueryUpdate(
                Project.class.getSimpleName(),
                Project.attributes(),
                Project.attributesQuery(),
                this.instanceProjectImageNull().values(),
                false)

                .keySet()
                .stream()
                .findFirst()
                .get();

        System.out.println(queryUpdate);
        System.out.println(queryGenerated);

        assertTrue(queryUpdate.equals(queryGenerated));

        this.end();
    }

    @Test
    public void testJpqlNullValue() {
        this.beging();

        String queryUpdate = "UPDATE Project p SET p.description = :newDescription, "
                + "p.urlRepository = :newUrlRepository, p.urlImage = :newUrlImage, "
                + "p.importanceLevel = :newImportanceLevel WHERE p.id = :projectId";

        String queryGenerated = createQueryUpdate(
                Project.class.getSimpleName(),
                Project.attributes(),
                Project.attributesQuery(),
                this.instanceProjectImageNull().values(),
                true)

                .keySet()
                .stream()
                .findFirst()
                .get();

        System.out.println(queryUpdate);
        System.out.println(queryGenerated);

        assertTrue(queryUpdate.equals(queryGenerated));

        this.end();
    }

    @Test
    public void testExceptionNullId() {
        assertThrows(EntityNotFoundException.class, () -> { this.throwException(); });
    }

    private void throwException() {
        createQueryUpdate(
                Project.class.getSimpleName(),
                Project.attributes(),
                Project.attributesQuery(),
                this.instanceProjectIdNull().values(),
                true);
    }
    private Project instanceProject() {
        var project = new Project();

        project.setId(40);
        project.setDescription("Site de um restaurante indo-italiano fictício.");
        project.setUrlRepository("https://github.com/HigalLegal/mama-shiva");
        project.setUrlImage("https://miro.medium.com/v2/resize:fit:1400/format:"
                + "webp/1*bMgQ8MhbnQexpqHgIgBJPA.png");
        project.setImportanceLevel(30);

        return project;
    }
    private Project instanceProjectImageNull() {
        var project = new Project();

        project.setId(40);
        project.setDescription("Site de um restaurante indo-italiano fictício.");
        project.setUrlRepository("https://github.com/HigalLegal/mama-shiva");
        project.setImportanceLevel(30);

        return project;
    }

    private Project instanceProjectIdNull() {
        var project = new Project();

        project.setDescription("Site de um restaurante indo-italiano fictício.");
        project.setUrlRepository("https://github.com/HigalLegal/mama-shiva");
        project.setImportanceLevel(30);

        return project;
    }

    private void beging() {
        System.out.println("---------------------------------------------------------------------");
        System.out.println();
    }

    private void end() {
        System.out.println();
        System.out.println("---------------------------------------------------------------------");
    }

}
