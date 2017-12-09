/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.util.List;
import javax.ejb.EJB;
import javax.servlet.http.Cookie;
import javax.ws.rs.FormParam;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import model.Comment;
import model.Media;
import model.User;

/**
 * REST Web Service
 *
 * @author TimiL
 */
@Path("commentservice")
public class CommentService {

    
    @EJB
    private Commentcontrol commentc;
    
    /*
    @Context
    private UriInfo context;
*/
    
    
    /**
     * Creates a new instance of DBSerivce
     */
    public CommentService() {
    }

    /**
     * Retrieves representation of an instance of controller.DBSerivce
     * @param comment
     * @return an instance of java.lang.String
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Comment> getJson() {
        return commentc.getAll();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Comment post(
            @FormParam("comment") String comment,
            @FormParam("userid") int userid,
            @FormParam("mediaid") int mediaid){
        
        
        Comment c= new Comment();
        c.setComment(comment);
        
        return commentc.insert(c);
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
