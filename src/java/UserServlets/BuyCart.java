package UserServlets;

import controllers.ControlServlet;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "BuyCart", urlPatterns = {"/BuyCart"})
public class BuyCart extends HttpServlet {
    
    ControlServlet controller;
    
    public void init(){
        controller = new ControlServlet();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       String userName = request.getParameter("userName");
       int bookId = Integer.parseInt(request.getParameter("bookId"));
       int value = Integer.parseInt(request.getParameter("value"));
        System.out.println(userName+" "+bookId+" "+value+"in post of BuyCart");
      controller.updateBookCountInCart(userName,bookId,value);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       String userName = request.getParameter("userName");
        System.out.println(userName);
        if (controller.buyMyCart(userName))
        {
            
        } else{   // customer can't afford the cart
            
        }
            
    }

    
    
}
