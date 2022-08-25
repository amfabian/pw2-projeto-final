package dev.ifrs.backendclients;

import java.util.List;

import javax.annotation.security.PermitAll;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import dev.ifrs.model.Usuario;

@RegisterRestClient(baseUri = "http://localhost:8084/usuario")
public interface UsuarioBC {

    @POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Path("/create")
    @PermitAll
    @Produces(MediaType.APPLICATION_JSON)
    public Usuario create (@FormParam("login") String login,
                            @FormParam("password") String password,
                            @FormParam("email") String email);

    @GET
    @Path("/list")
    @Produces(MediaType.APPLICATION_JSON)
    @PermitAll
    public List<Usuario> list ();

    @GET
    @Path("/list/{id}")
    @PermitAll
    @Produces(MediaType.APPLICATION_JSON)
    public Usuario getUser (@PathParam("id") Long id);

    @DELETE
    @Path("/delete/{id}")
    public void delete(@PathParam("id") Long id);

    @PUT
    @Path("/update/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Usuario update(@PathParam("id") Long id, @FormParam("login") String login,
                            @FormParam("password") String password,
                            @FormParam("email") String email);

    @PUT
    @Path("/setAdmin/{id}")                 
    public Usuario setAdmin(@PathParam("id") Long id);

}
