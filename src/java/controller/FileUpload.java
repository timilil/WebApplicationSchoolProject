/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Media;

/**
 *
 * @author timili
 */
@WebServlet(name = "FileUpload", urlPatterns = {"/do"})
@MultipartConfig(location = "/var/www/html/test")
public class FileUpload extends HttpServlet {

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    
    /*File f = new File("/var/www/html/test");
    ArrayList<String> names = new ArrayList<String>(Arrays.asList(f.list()));*/
    ArrayList<String> jsonUrl = new ArrayList();
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json");
        try (PrintWriter out = response.getWriter()) {
            request.getPart("fileup").write(request.getPart("fileup").getSubmittedFileName());
            String mediaURL = ("//10.114.32.124/test/" + request.getPart("fileup").getSubmittedFileName());
            String json = ("{\"src\" : \"" + mediaURL +"\"}");
            out.print(json);
            
            jsonUrl.add(json);
            
            //out.println("<!doctype html><title>upload</title><meta charset= \"utf-8"\"><h1>upload</h1>");
            //out.print("File <imgsrc = \"//10.114.32.124/test/"+request.getPart("").getSubmittedFilename()+"\">normally uploaded...");
            model.Media media = new Media();
            media.setURL(mediaURL);
            
            /*for(int i = 0; i<names.size(); i++){
                out.print(names.get(i));
            }*/
        }
    }

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
