package UserServlets;

import Beans.User;
import controllers.ControlServlet;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "BuyCart", urlPatterns = {"/BuyCart"})
public class BuyCart extends HttpServlet {

    ControlServlet controller;

    public void init() {
        controller = new ControlServlet();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userName = request.getParameter("userName");
        int bookId = Integer.parseInt(request.getParameter("bookId"));
        int value = Integer.parseInt(request.getParameter("value"));
        System.out.println(userName + " " + bookId + " " + value + "in post of BuyCart");
        controller.updateBookCountInCart(userName, bookId, value);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userName = request.getParameter("userName");
        System.out.println(userName);
        PrintWriter out = response.getWriter();

        if (controller.buyMyCart(userName)) {
            out.print("your books will be there soon :) ");
            ControlServlet c = new ControlServlet();
            User myUser = c.getUser(userName);
            HttpSession session = request.getSession(true);
            session.setAttribute("user", myUser);

        } else {   // customer can't afford the cart
            out.print("No enough Credit .. Sorry! ");
        }

    }

}
