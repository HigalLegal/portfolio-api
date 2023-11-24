package dev.higormorais.repository;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import dev.higormorais.entities.Article;
import dev.higormorais.entities.Project;
import dev.higormorais.repositories.ArticleRepository;
//import io.quarkus.test.InjectMock;
import dev.higormorais.repositories.ProjectRepository;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;


@QuarkusTest
public class ProjectRepositoryTest {

    @Inject
    private ProjectRepository projectRepository;

    @Test
    @DisplayName("Testar método de update")
    @Transactional
    public void testUpdate() {
        System.out.println("-----------------------------------------------");

        Project project = projectRepository.findById(2);
        project.setDescription("API REST de cadastro de pessoas");
        project.setUrlImage(null);

        //vai ter que dá um debug bem profundo pra ver o pq desse erro... ou não KKKKKKKKKKKKKK

        projectRepository.update(project);

        System.out.println("-----------------------------------------------");
    }


}
