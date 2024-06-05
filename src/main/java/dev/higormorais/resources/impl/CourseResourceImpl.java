package dev.higormorais.resources.impl;

import dev.higormorais.dto.requests.CourseRequest;
import dev.higormorais.resources.CourseResource;
import dev.higormorais.services.CourseService;
import jakarta.annotation.Nullable;
import jakarta.annotation.security.PermitAll;
import jakarta.annotation.security.RolesAllowed;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.jboss.resteasy.reactive.PartType;
import org.jboss.resteasy.reactive.RestForm;

import java.io.File;

import static dev.higormorais.utils.IntegerNumberOperations.toPrimitive;

@Path("/courses")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@ApplicationScoped
public class CourseResourceImpl implements CourseResource {

    @Inject
    private CourseService courseService;

    // ---------------------------------------------------------------------------

    @Override
    @GET
    @PermitAll
    public Response listAll(@QueryParam("offset") @Nullable Integer offset,
                            @QueryParam("limit") @Nullable Integer limit) {
        return Response
                .ok(courseService.listAll(toPrimitive(offset), toPrimitive(limit)))
                .build();
    }

    @Override
    @GET
    @Path("/by-name")
    @PermitAll
    public Response listByName(@QueryParam("name") String name) {
        return Response
                .ok(courseService.listByName(name))
                .build();
    }

    @Override
    @GET
    @Path("/by-technology")
    @PermitAll
    public Response listByTechnology(@QueryParam("technology") String technology) {
        return Response
                .ok(courseService.listByTechnology(technology))
                .build();
    }

    @Override
    @POST
    @Transactional
    @RolesAllowed({"ADMIN", "NON_ADMIN"})
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public Response create(@RestForm @PartType(MediaType.APPLICATION_JSON) @Valid CourseRequest courseRequest,
                           @RestForm("image") @PartType(MediaType.MULTIPART_FORM_DATA) File image) {

        courseService.create(courseRequest, image);

        return Response
                .status(201)
                .build();
    }

    @Override
    @PUT
    @Path("/{id}")
    @Transactional
    @RolesAllowed({"ADMIN", "NON_ADMIN"})
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public Response update(@PathParam("id") Integer id,
                           @RestForm @PartType(MediaType.APPLICATION_JSON) @Valid CourseRequest courseRequest,
                           @RestForm("image") @PartType(MediaType.MULTIPART_FORM_DATA) File image) {

        courseService.update(id, courseRequest, image);

        return Response
                .status(200)
                .build();
    }

    @Override
    @DELETE
    @Path("/{id}")
    @Transactional
    @RolesAllowed({"ADMIN", "NON_ADMIN"})
    public Response delete(@PathParam("id") Integer id) {

        courseService.delete(id);

        return Response
                .status(204)
                .build();
    }
}
