/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;

import javax.ws.rs.FormParam;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

import javax.ws.rs.core.MediaType;
import model.Media;


/**
 * REST Web Service
 *
 * @author TimiL
 */
@Path("mediaservice")
public class MediaSerivce {

    
    @EJB
    private Mediacontrol mediac;
    
    /*
    @Context
    private UriInfo context;
*/
    
    
    /**
     * Creates a new instance of DBSerivce
     */
    public MediaSerivce() {
    }

    /**
     * Retrieves representation of an instance of controller.DBSerivce
     * @return an instance of java.lang.String
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Media> getJson() {
        return mediac.getAll();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Media post(
            @FormParam("description") String description,
            @FormParam("userid") int userid)/*,
            @FormParam("postdate") String postdate,
            @FormParam("url") String url)*/{
        Media m = new Media();
        //String postdate = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
        m.setDescription(description);
        m.setUserID(userid);
       // m.setPostDate(postdate);
        m.setURL(m.getURL());
        return mediac.insert(m);
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
