/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.User;

/**
 *
 * @author TimiL
 */
public class SigninServlet extends HttpServlet {

    @EJB
    private DBcontrol dbc;

    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");  
        
        model.User user = new User();
        try{
            PrintWriter out=response.getWriter();  
        
            String name=request.getParameter("username");  
            String password=request.getParameter("password");  
            String email=request.getParameter("email");

            if(!dbc.isUsernameUsed(name)){
                if(!dbc.isEmailUsed(email)){
                    user.setName(name);
                    user.setEmail(email);
                    user.setPassword(password);
                    dbc.insert(user);
                    response.sendRedirect("login.html");
                }
            }
            
            if(dbc.isUsernameUsed(name) || dbc.isEmailUsed(email)) {
                //out.print("Username or email is already taken!");  
                //request.getRequestDispatcher("signin.html").include(request, response);
                response.sendRedirect("signInError.html");
                //response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid username or password, try again.");
            }
        }
        catch (Exception e){
        }
    }

    
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
