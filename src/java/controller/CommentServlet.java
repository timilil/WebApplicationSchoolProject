/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Comment;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author TimiL
 */
public class CommentServlet extends HttpServlet {
    
    @EJB
    private Commentcontrol commentc;
    
    @EJB
    private DBcontrol dbc;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        JSONObject json = new JSONObject();
        
        try {
            String id = request.getParameter("id");
            if (id!= null && !id.equals("")) {
                json.put("commentList", commentc.getCommentsByMediaId(id));
                //json.put("commentList", commentc.getCommentsByUser());
                response.setContentType("application/json");
  
                PrintWriter out = response.getWriter();
                out.print(json);
                out.flush();
                
            }
            else {
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            }
        } catch (JSONException ex) {
            Logger.getLogger(FileUpload.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

   
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //response.setContentType("application/json");
        response.setContentType("text/html");
        try (PrintWriter out = response.getWriter()) {
        String comId= request.getParameter("cid"); 
        model.Comment comment = new Comment();
        String commentInput=request.getParameter("comment");  
          
        Cookie ck[]=request.getCookies();    
        String name=ck[0].getValue();
        
        if((!name.equals("")|| name != null) && null != comId && !"".equals(comId)) {
            comment.setComment(commentInput);
            int userID = dbc.getUserId(name);
            comment.setUserId(userID);
            comment.setMediaId(Integer.valueOf(comId));
            dbc.insertComment(comment);
            response.sendRedirect("mainhtml.html");
        }
        else{
            response.sendRedirect("uploadhtml.html");
        }
        }
    }

    
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
