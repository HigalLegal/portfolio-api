package dev.higormorais.resources.impl;

import dev.higormorais.dto.requests.ProjectRequest;
import dev.higormorais.resources.ProjectResource;
import dev.higormorais.services.ProjectService;
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
import static dev.higormorais.utils.ExceptionUtil.throwImageException;

@Path("/projects")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@ApplicationScoped
public class ProjectResourceImpl implements ProjectResource {

    @Inject
    private ProjectService projectService;

    // ----------------------------------------------------------------------------------------------

    @Override
    @GET
    @PermitAll
    public Response listAll(@QueryParam("offset") @Nullable Integer offset,
                            @QueryParam("limit") @Nullable Integer limit) {
        return Response
                .ok(projectService.listAll(toPrimitive(offset), toPrimitive(limit)))
                .build();
    }

    @Override
    @GET
    @Path("/{id}")
    @RolesAllowed({"ADMIN", "NON_ADMIN"})
    public Response byId(@PathParam("id") Integer id) {
        return Response
                .ok(projectService.byId(id))
                .build();
    }


    @Override
    @POST
    @Transactional
    @RolesAllowed({"ADMIN", "NON_ADMIN"})
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public Response create(@RestForm @PartType(MediaType.APPLICATION_JSON) @Valid ProjectRequest projectRequest,
                           @RestForm("image") @PartType(MediaType.MULTIPART_FORM_DATA) File image) {
        throwImageException(image, "A imagem é obrigatório");

        projectService.create(projectRequest, image);

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
                           @RestForm @PartType(MediaType.APPLICATION_JSON) @Valid ProjectRequest projectRequest,
                           @RestForm("image") @PartType(MediaType.MULTIPART_FORM_DATA) File image) {
        projectService.update(id, projectRequest, image);

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

        projectService.delete(id);

        return Response
                .status(204)
                .build();
    }
}
