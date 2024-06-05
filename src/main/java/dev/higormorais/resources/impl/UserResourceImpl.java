package dev.higormorais.resources.impl;

import dev.higormorais.dto.requests.CredentialsRequest;
import dev.higormorais.dto.requests.UserRequest;
import dev.higormorais.resources.UserResource;
import dev.higormorais.services.UserService;
import jakarta.annotation.security.PermitAll;
import jakarta.annotation.security.RolesAllowed;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/users")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@ApplicationScoped
public class UserResourceImpl implements UserResource {

    @Inject
    private UserService userService;

    // ---------------------------------------------------------

    @Override
    @GET
    @Path("/login")
    @PermitAll
    public Response login(@Valid CredentialsRequest credentialsRequest) {
        return Response.ok(userService.login(credentialsRequest)).build();
    }

    @Override
    @POST
    @Transactional
    @RolesAllowed({"ADMIN", "NON_ADMIN"})
    public Response create(@Valid UserRequest userRequest) {
        userService.create(userRequest);
        return Response.status(Response.Status.CREATED).build();
    }

    @Override
    @PUT
    @Transactional
    @Path("/{id}")
    @RolesAllowed({"ADMIN", "NON_ADMIN"})
    public Response update(Integer id, @Valid UserRequest userRequest) {
        userService.update(id, userRequest);
        return Response.ok().build();
    }

    @Override
    @DELETE
    @Transactional
    @Path("/{id}")
    @RolesAllowed({"ADMIN", "NON_ADMIN"})
    public Response delete(Integer id) {
        userService.delete(id);
        return Response.status(Response.Status.NO_CONTENT).build();
    }
}
