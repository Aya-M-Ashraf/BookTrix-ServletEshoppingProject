package CommonServlets;

import controllers.ControlServlet;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.*;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebServlet(name = "Login", urlPatterns = {"/Login"})
public class Login extends HttpServlet {

    ControlServlet controlservlet;

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("role")) {
                    if (cookie.getValue().equals("admin")) {
                        response.sendRedirect("AdminHome.jsp");
                    } else if (cookie.getValue().equals("user")) {
                        response.sendRedirect("UserHome.jsp");
                    }
                }
            }
        }
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String userName = request.getParameter("userName");
            String password = request.getParameter("password");
            String[] values = request.getParameterValues("remember");

            Cookie[] cookies = null;
            cookies = request.getCookies();
            controlservlet = new ControlServlet();
            String result = controlservlet.checkLogin(userName, password);

            HttpSession session = request.getSession(true);

            if (result.equals("admin")) {

                session.setAttribute("role", "admin");
                session.setAttribute("userName", userName);
                if (values != null) {
                    Cookie nameCookie = new Cookie("userName", userName);
                    nameCookie.setMaxAge(60 * 60 * 24);
                    Cookie roleCookie = new Cookie("role", "admin");
                    roleCookie.setMaxAge(60 * 60 * 24);

                    response.addCookie(nameCookie);
                    response.addCookie(roleCookie);
                }
                response.sendRedirect("AdminHome.jsp");
            } else if (result.equals("user")) {
                session.setAttribute("role", "user");
                session.setAttribute("userName", userName);
                if (values != null) {
                    Cookie nameCookie = new Cookie("userName", userName);
                    nameCookie.setMaxAge(60 * 60 * 24);
                    Cookie roleCookie = new Cookie("role", "user");
                    roleCookie.setMaxAge(60 * 60 * 24);

                    response.addCookie(nameCookie);
                    response.addCookie(roleCookie);
                }
                response.sendRedirect("UserHome.jsp");
            } else {
                session.setAttribute("error","1");
                response.sendRedirect("jsps/Login.jsp");
            }
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
