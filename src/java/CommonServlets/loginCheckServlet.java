/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CommonServlets;

import controllers.ControlServlet;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author ahmed elnazer
 */
@WebServlet(name = "loginCheckServlet", urlPatterns = {"/loginCheckServlet"})
public class loginCheckServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ControlServlet controlservlet=new ControlServlet();
        String email=req.getParameter("email");
        String password=req.getParameter("password");
        String result=controlservlet.checkLogin( email,  password);
        if(result.equals("admin"))
            resp.sendRedirect("AdminHome.jsp");
        else if(result.equals("user"))
            resp.sendRedirect("UserHome.jsp");
        else
            resp.sendRedirect("");
        
    }


  
}
