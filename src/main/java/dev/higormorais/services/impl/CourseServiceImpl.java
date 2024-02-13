package dev.higormorais.services.impl;

import static dev.higormorais.utils.ExternalRequest.imageUpload;
import static dev.higormorais.utils.RemovalById.deleteAbstract;

import dev.higormorais.client.ApiImgBB;
import dev.higormorais.dto.mappers.CourseMapper;
import dev.higormorais.dto.requests.CourseRequest;
import dev.higormorais.dto.responses.CourseResponse;
import dev.higormorais.entities.Course;
import dev.higormorais.repositories.CourseRepository;
import dev.higormorais.repositories.KeyImgBbRepository;
import dev.higormorais.services.CourseService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityNotFoundException;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import java.io.File;
import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class CourseServiceImpl implements CourseService {

    @Inject
    private CourseRepository courseRepository;

    @Inject
    private CourseMapper courseMapper;

    @Inject
    @ConfigProperty(name = "registro.inexistente")
    private String messageNotFound;

    @Inject
    private KeyImgBbRepository keyImgBbRepository;

    @RestClient
    private ApiImgBB imageAPI;

    // ------------------------------------------------------------------------------------------

    @Override
    public List<CourseResponse> listAll(int offset, int limit) {
        return courseRepository
                .findAll(offset, limit)
                .stream()
                .map(courseMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<CourseResponse> listByName(String name) {
        return courseRepository
                .findByName(name)
                .stream()
                .map(courseMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<CourseResponse> listByTechnology(String technology) {
        return courseRepository
                .findByTechnologyName(technology)
                .stream()
                .map(courseMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public void create(CourseRequest courseRequest, File image) {
        String urlImage = imageUpload(imageAPI, image, keyImgBbRepository.returnKey());

        Course course = courseMapper.toEntitie(courseRequest);
        course.setUrlImage(urlImage);

        courseRepository.persist(course);

    }

    @Override
    public void update(Integer id, CourseRequest courseRequest, File image) {
        String urlImage = imageUpload(imageAPI, image, keyImgBbRepository.returnKey());

        Course course = courseMapper.toEntitie(courseRequest);
        course.setId(id);

        if(urlImage != null && !urlImage.isBlank()) {
            course.setUrlImage(urlImage);
        }

        courseRepository.update(course);

    }

    @Override
    public void delete(Integer id) {
        deleteAbstract(id, courseRepository, messageNotFound);
    }
}
