package CommonServlets;

import Beans.User;
import controllers.ControlServlet;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.*;

@WebServlet(name = "Login", urlPatterns = {"/Login"})
public class Login extends HttpServlet {
    
    ControlServlet controlservlet;

    public void init() {
        controlservlet = new ControlServlet();
    }
    
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("in get method");
        Cookie[] cookies = request.getCookies();
        System.out.println(cookies.length);
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("role")) {
                    System.out.println(cookie.getValue());
                    if (cookie.getValue().equals("admin")) {

                        for (Cookie userNameCookie : cookies) {
                            if (userNameCookie.getName().equals("userName")) {
                                System.out.println(userNameCookie.getValue());
                                ControlServlet c = new ControlServlet();
                                User myUser = c.getUser(userNameCookie.getValue());
                                HttpSession session = request.getSession(true);
                                session.setAttribute("user", myUser);

                            }
                        }
                    } else if (cookie.getValue().equals("user")) {
                        System.out.println("true");
                        for (Cookie userNameCookie : cookies) {
                            if (userNameCookie.getName().equals("userName")) {
                                System.out.println(userNameCookie.getValue());
                                ControlServlet c = new ControlServlet();
                                User myUser = c.getUser(userNameCookie.getValue());
                                HttpSession session = request.getSession(true);
                                session.setAttribute("user", myUser);
                            }
                        }
                        PrintWriter out = response.getWriter();
                        out.print("user");
                    }
                }
            }
        }
    }
    
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        String userName = request.getParameter("userName");
        String password = request.getParameter("password");
        String[] values = request.getParameterValues("remember");
        
        Cookie[] cookies = null;
        cookies = request.getCookies();
        String result = "not found";
        boolean userNameExists = controlservlet.doesUserNameExist(userName);
        if (userNameExists) {
            result = controlservlet.checkLogin(userName, password);
        }
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
            session.setAttribute("user", controlservlet.getUser(userName));
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
            session.setAttribute("error", "1");
            response.sendRedirect("Login.jsp");
        }
    }
    
}
