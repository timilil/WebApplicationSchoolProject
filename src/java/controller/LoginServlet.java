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

/**
 *
 * @author TimiL
 */
public class LoginServlet extends HttpServlet {

    @EJB
    private DBcontrol dbc;
   
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");  
        PrintWriter out=response.getWriter();  
        
        String name=request.getParameter("username");  
        String password=request.getParameter("password");  
          
        if(dbc.checkUsername(name, password)){  
            
            Cookie ck=new Cookie("name",name);  
            response.addCookie(ck);  
            
            response.sendRedirect("mainhtml.html");
            //out.print(dbc.getUserId(name));
        }
        
        else{  
            //out.print("Invalid username or password, try again!");  
            //request.getRequestDispatcher("login.html").include(request, response);  
            response.sendRedirect("login.html");
        }  
          
        //out.close();  
    }
    

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
