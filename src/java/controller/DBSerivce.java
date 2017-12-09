/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.util.List;
import javax.ejb.EJB;
import javax.ws.rs.FormParam;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.NewCookie;
import javax.ws.rs.core.Response;
import model.Comment;
import model.Media;
import model.User;

/**
 * REST Web Service
 *
 * @author TimiL
 */
@Path("service")
public class DBSerivce {

    
    @EJB
    private DBcontrol dbc;
    
    public DBSerivce() {
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<User> getJson() {
        return dbc.getAll();
    }
    
    @GET
    @Path("img")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Media> getAllImges() {
        return dbc.getAllMedia();
    }
    
    @GET
    @Path("comment")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Comment> getAllComments() {
        return dbc.getAllComments();
    }
    
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public User post(
            @FormParam("username") String name,
            @FormParam("email") String email,
            @FormParam("password") String password,
            @FormParam("loginpassword") String loginpassword,
            @FormParam("loginusername") String loginusername){
        
        User u= new User();
        try{
            
            if(!dbc.isUsernameUsed(name)){
                if(!dbc.isEmailUsed(email)){
                    u.setName(name);
                    u.setEmail(email);
                    u.setPassword(password);
                    return dbc.insert(u);
                }
            }
            
            dbc.checkUsername(loginusername, loginpassword);
        }
        catch (Exception e){
        }
        return null;
    }
}
    
    
    /**
     * PUT method for updating or creating an instance of DBSerivce
     * @param content representation for the resource
     */
    
    /*
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void putJson(String content) {
    }
    
    
    ADD ALSO @DELETE*/
