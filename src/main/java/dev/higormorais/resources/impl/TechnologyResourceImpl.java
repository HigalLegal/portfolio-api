package dev.higormorais.resources.impl;

import dev.higormorais.dto.requests.TechnologyRequest;
import dev.higormorais.resources.TechnologyResource;
import dev.higormorais.services.TechnologyService;
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

import static dev.higormorais.utils.ExceptionUtil.throwImageException;
import static dev.higormorais.utils.IntegerNumberOperations.toPrimitive;

@Path("/technologies")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.MULTIPART_FORM_DATA)
@ApplicationScoped
public class TechnologyResourceImpl implements TechnologyResource {

    @Inject
    private TechnologyService technologyService;

    // ----------------------------------------------------------------------------------------------

    @Override
    @GET
    @PermitAll
    public Response listAll(@QueryParam("offset") @Nullable Integer offset,
                            @QueryParam("limit") @Nullable Integer limit) {
        return Response
                .ok(technologyService.listAll(toPrimitive(offset), toPrimitive(limit)))
                .build();
    }

    @Override
    @GET
    @Path("/{id}")
    @RolesAllowed({"ADMIN", "NON_ADMIN"})
    public Response byId(@PathParam("id") Integer id) {
        return Response
                .ok(this.technologyService.byId(id))
                .build();
    }

    @Override
    @POST
    @Transactional
    @RolesAllowed({"ADMIN", "NON_ADMIN"})
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public Response create(@RestForm("technologyRequest") @PartType(MediaType.APPLICATION_JSON)
                               @Valid TechnologyRequest technologyRequest,
                           @RestForm("image") File image) {
        throwImageException(image, "A imagem é obrigatório");

        technologyService.create(technologyRequest, image);

        return Response
                .status(201)
                .build();
    }

    @Override
    @PUT
    @Path("/{id}")
    @Transactional
    @RolesAllowed({"ADMIN", "NON_ADMIN"})
    public Response update(@PathParam("id") Integer id,
                           @RestForm("technologyRequest") @PartType(MediaType.APPLICATION_JSON)
                                @Valid TechnologyRequest technologyRequest,
                           @RestForm("image") File image) {
        technologyService.update(id, technologyRequest, image);

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

        technologyService.delete(id);

        return Response
                .status(204)
                .build();
    }

}
