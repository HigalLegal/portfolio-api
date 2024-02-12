package dev.higormorais.dto.mappers;

import dev.higormorais.dto.requests.CourseRequest;
import dev.higormorais.dto.responses.CourseResponse;
import dev.higormorais.entities.Course;

public interface CourseMapper extends Mapper<Course, CourseRequest, CourseResponse> {
}
