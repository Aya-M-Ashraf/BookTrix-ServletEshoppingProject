package UserServlets;

import controllers.ControlServlet;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class BuyCart extends HttpServlet {
    
    ControlServlet controller;
    
    public void init(){
        controller = new ControlServlet();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       String userName = request.getParameter("userName");
        if (controller.buyMyCart(userName))
        {
            
        } else{   // customer can't afford the cart
            
        }
            
    }

    
    
}
