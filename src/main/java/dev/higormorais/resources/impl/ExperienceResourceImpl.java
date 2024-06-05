package dev.higormorais.resources.impl;

import dev.higormorais.dto.requests.ExperienceRequest;
import dev.higormorais.resources.ExperienceResource;
import dev.higormorais.services.ExperienceService;
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

import static dev.higormorais.utils.IntegerNumberOperations.toPrimitive;

@Path("/experiences")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@ApplicationScoped
public class ExperienceResourceImpl implements ExperienceResource {

    @Inject
    ExperienceService experienceService;

    // -------------------------------------------------------------------------------------------------

    @Override
    @GET
    @PermitAll
    public Response listAll(@QueryParam("offset") @Nullable Integer offset,
                            @QueryParam("limit") @Nullable Integer limit) {
        return Response
                .ok(experienceService.listAll(toPrimitive(offset), toPrimitive(limit)))
                .build();
    }

    @Override
    @POST
    @Transactional
    @RolesAllowed({"ADMIN", "NON_ADMIN"})
    public Response create(@Valid ExperienceRequest experienceRequest) {
        experienceService.create(experienceRequest);

        return Response
                .status(201)
                .build();
    }

    @Override
    @PUT
    @Path("/{id}")
    @Transactional
    @RolesAllowed({"ADMIN", "NON_ADMIN"})
    public Response update(@PathParam("id") Integer id, @Valid ExperienceRequest experienceRequest) {
        experienceService.update(id, experienceRequest);

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
        experienceService.delete(id);

        return Response
                .status(204)
                .build();
    }
}
