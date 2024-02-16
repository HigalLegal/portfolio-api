package dev.higormorais.resources.impl;

import static dev.higormorais.utils.Primitive.toPrimitive;

import dev.higormorais.dto.requests.ArticleRequest;
import dev.higormorais.resources.ArticleResource;
import dev.higormorais.services.ArticleService;
import jakarta.annotation.Nullable;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/articles")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@ApplicationScoped
public class ArticleResourceImpl implements ArticleResource {

    @Inject
    ArticleService articleService;

    // ---------------------------------------------------------------------------------

    @Override
    @GET
    public Response listAll(@QueryParam("offset") @Nullable Integer offset,
                            @QueryParam("limit") @Nullable Integer limit) {
        return Response
                .ok(articleService.listAll(toPrimitive(offset), toPrimitive(limit)))
                .build();
    }

    @Override
    @GET
    @Path("/by-name")
    public Response listByName(@QueryParam("name") String nameArticle) {
        return Response
                .ok(articleService.listByName(nameArticle))
                .build();
    }

    @Override
    @POST
    @Transactional
    public Response create(@Valid ArticleRequest articleRequest) {

        articleService.create(articleRequest);

        return Response
                .status(201)
                .build();
    }

    @Override
    @PUT
    @Path("/{id}")
    @Transactional
    public Response update(@PathParam("id") Integer id, @Valid ArticleRequest articleRequest) {
        articleService.update(id, articleRequest);

        return Response
                .status(200)
                .build();
    }

    @Override
    @DELETE
    @Path("/{id}")
    @Transactional
    public Response delete(@PathParam("id") Integer id) {

        articleService.delete(id);

        return Response
                .status(204)
                .build();
    }

}
