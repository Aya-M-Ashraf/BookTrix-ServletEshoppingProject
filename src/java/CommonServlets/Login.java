/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CommonServlets;

import controllers.ControlServlet;
import java.io.IOException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.*;
import javax.servlet.*;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebServlet(name = "Login", urlPatterns = {"/Login"})
public class Login extends HttpServlet {

    ServletConfig con;
    int notfound = 0;
    Connection conn;
    Statement stmt;
    ResultSet rs;

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        RequestDispatcher rd1 = request.getRequestDispatcher("Login.jsp");
        RequestDispatcher rd2 = request.getRequestDispatcher("LoginWithError.jsp");

        String notf = request.getParameter("notfound");
        if (notf != null && notf.equals("1")) {

            rd2.include(request, response);
        } else {

            Cookie cookie = null;
            Cookie[] cookies = null;
            cookies = request.getCookies();
            if (cookies != null) {
                for (int i = 0; i < cookies.length; i++) {
                      cookie = cookies[i];
                    if ((cookie.getName()).equals("role")) {
                        if (cookie.getValue().equals("user")) {
                            response.sendRedirect("UserHome.jsp");
                        } else {
                            response.sendRedirect("AdminHome.jsp");

                        }

                        break;
                    } else {

                        continue;
                    }

                }

            }

            rd1.include(request, response);

        }
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            String userName = request.getParameter("userName");
            String password = request.getParameter("password");
            String[] values = request.getParameterValues("remember");

            Cookie cookie = null;
            Cookie[] cookies = null;
            cookies = request.getCookies();

            ControlServlet controlservlet = new ControlServlet();

            String result = controlservlet.checkLogin(userName, password);

            if (result.equals("admin")) {
                HttpSession session = request.getSession(true);
                session.setAttribute("loggedIn", new String("true"));
                session.setAttribute("username", userName);

                if (values != null) {

                    Cookie role = new Cookie("role", "admin");
                    role.setMaxAge(60 * 60 * 24);
                    response.addCookie(role);
                }
                response.sendRedirect("AdminHome.jsp");
            } else if (result.equals("user")) {
                HttpSession session = request.getSession(true);
                session.setAttribute("loggedIn", new String("true"));
                session.setAttribute("username", userName);

                if (values != null) {

                    Cookie role = new Cookie("role", "user");
                    role.setMaxAge(60 * 60 * 24);
                    response.addCookie(role);
                }
                response.sendRedirect("UserHome.jsp");
            } else {
                response.sendRedirect("Login?notfound=1");

            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
