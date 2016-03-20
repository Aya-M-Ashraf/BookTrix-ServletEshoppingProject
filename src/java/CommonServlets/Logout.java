/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CommonServlets;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author lenovo
 */
public class Logout extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Cookie cookie = null;
        Cookie[] cookies = null;
        cookies = request.getCookies();
        if (cookies != null) {
            Cookie temp = null;
            for (int i = 0; i < cookies.length; i++) {
                if (cookies[i].getName().equals("userName")) {
                    temp = cookies[i];
                    temp.setMaxAge(0);
                    response.addCookie(temp);
                }
                  if (cookies[i].getName().equals("role")) {
                    temp = cookies[i];
                    temp.setMaxAge(0);
                    response.addCookie(temp);
                }
            }
        }

        HttpSession session = request.getSession(true);
        session.invalidate();
    }

}
