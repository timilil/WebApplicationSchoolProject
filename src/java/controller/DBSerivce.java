/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.util.List;
import javax.ejb.EJB;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.core.MediaType;
import model.User;
import static model.User_.name;

/**
 * REST Web Service
 *
 * @author TimiL
 */
@Path("service")
public class DBSerivce {

    
    @EJB
    private DBcontrol dbc;
    
    /*
    @Context
    private UriInfo context;
*/
    
    
    /**
     * Creates a new instance of DBSerivce
     */
    public DBSerivce() {
    }

    /**
     * Retrieves representation of an instance of controller.DBSerivce
     * @return an instance of java.lang.String
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<User> getJson() {
        return dbc.getAll();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public User post(@FormParam("val") String name){
        User u= new User();
        u.setName(name);
        return dbc.insert(u);
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
}
