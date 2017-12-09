/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.json.Json;
import org.json.JSONObject;
import javax.json.JsonArray;
import javax.json.stream.JsonParser;
import javax.ws.rs.core.Response;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Media;
import org.json.JSONException;

/**
 *
 * @author timili
 */
@WebServlet(name = "FileUpload", urlPatterns = {"/do"})
//@WebServlet(name = "FileUpload", urlPatterns = {"/do"})
@MultipartConfig(location = "/var/www/html/test")
public class FileUpload extends HttpServlet {
    
    @EJB
    private DBcontrol dbc;

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    
    //File f = new File("/var/www/html/test");
    //ArrayList<String> names = new ArrayList<String>(Arrays.asList(f.list()));
    //JsonArray value;
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json");
        try (PrintWriter out = response.getWriter()) {
            request.getPart("fileup").write(request.getPart("fileup").getSubmittedFileName());
            String mediaURL = ("//10.114.32.124/test/" + request.getPart("fileup").getSubmittedFileName());
            String json = ("{\"src\" : \"" + mediaURL +"\",");
            
            model.Media media = new Media();
            media.setURL(mediaURL);
            media.setDescription(request.getParameter("description"));
            
            Cookie ck[]=request.getCookies();    
            String name=ck[0].getValue();
            
            if(!name.equals("") || name!=null) {
                int userID = dbc.getUserId(name);
                media.setUserID(userID);
            
                dbc.insertImg(media);
                json += "\"id\" : \"" + media.getId() + "\"}";
                //out.print(json);
                response.setContentType("text/html");
                response.sendRedirect("mainhtml.html");
            }
            else{  
                response.setContentType("text/html");
                out.print("You must login first");  
                
                response.sendRedirect("uploadhtml.html");  
            }  
        }
        
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        JSONObject json = new JSONObject();
        
        try {
            json.put("mediaList", dbc.getAllMedia());
            response.setContentType("application/json");
  
            PrintWriter out = response.getWriter();

            out.print(json);
            out.flush();
        } catch (JSONException ex) {
            Logger.getLogger(FileUpload.class.getName()).log(Level.SEVERE, null, ex);
        }
    };
    
    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
