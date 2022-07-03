package dev.ifrs;

import java.util.List;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.ws.rs.BadRequestException;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.SecurityContext;

import org.eclipse.microprofile.jwt.Claim;
import org.eclipse.microprofile.jwt.Claims;
import org.eclipse.microprofile.jwt.JsonWebToken;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import dev.ifrs.backendclients.LoginBC;
import dev.ifrs.backendclients.UsuarioBC;
import dev.ifrs.model.Usuario;

@Path("/bff/usuario")
public class UsuarioBFF {
            
    @Inject
    JsonWebToken jwt;

    @Inject
    @Claim(standard = Claims.email)
    String eMail;
    
    @Inject
    @RestClient
    UsuarioBC usuariobc;

    @Inject
    @RestClient
    LoginBC loginBC;

    @POST
    @Path("/create")
    @RolesAllowed({"Admin"})
    @Produces(MediaType.APPLICATION_JSON)
    public Usuario createBFF(@FormParam("login") String login,
                                @FormParam("password") String password,
                                @FormParam("email") String email){
        return usuariobc.create(login, password, email);
    }

    @GET
    @Path("/list")
    @RolesAllowed({"Admin", "User"})
    @Produces(MediaType.APPLICATION_JSON)
    public List<Usuario> listBFF(){
      
        return usuariobc.list();
    }

    @GET
    @Path("/list/{id}")
    @RolesAllowed({"Admin", "User"})
    @Produces(MediaType.APPLICATION_JSON)
    public Usuario getUserBFF(@PathParam("id") Long id){
        return usuariobc.getUser(id);
    }

    @POST
    @Path("/login")
    @PermitAll
    @Produces(MediaType.TEXT_PLAIN)
    public String getUser2BFF(@FormParam("name") String name, 
                                @FormParam("password") String password){
        
        List <Usuario> users = usuariobc.list();
        for(Usuario user : users){
            System.out.println("Lista de usuarios: "+user.getLogin());
            if(user.getLogin().equals(name) && user.getPassword().equals(password)){
                if(user.isAdmin()){
                    System.out.println("usuario admin: "+user.getLogin());
                return loginBC.getADMIN(user.getLogin(), user.getEmail());
                } else {
                    System.out.println("usuario certo: "+user.getLogin());
                    return loginBC.getJWT(user.getLogin(), user.getEmail());
            }
            }
        }
        throw new BadRequestException("Usuario e Senha incorretos");
    }
    @DELETE
    @Path("/delete/{id}")
    @RolesAllowed({"Admin"})
    public void delete(@PathParam("id") Long id){
        System.out.println("BFF DELETE INICIO ");
        usuariobc.delete(id);
        System.out.println("BFF DELETE OK ");

    }

    @PUT
    @Path("/update/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @RolesAllowed({"Admin"})
    public Usuario update(@PathParam("id") Long id, 
                            @FormParam("login") String login,
                            @FormParam("password") String password,
                            @FormParam("email") String email){

        return usuariobc.update(id, login, password, email);
                            }
    
    @PUT
    @Path("/setAdmin/{id}")
    @RolesAllowed({"Admin"})                
    public Usuario setAdmin(@PathParam("id") Long id){
        return usuariobc.setAdmin(id);
    }
    
}
