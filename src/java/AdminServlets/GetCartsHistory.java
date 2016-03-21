package AdminServlets;

import Beans.Cart;
import controllers.ControlServlet;
import java.io.IOException;
import java.util.Vector;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class GetCartsHistory extends HttpServlet {

    ControlServlet controller;

    @Override
    public void init() {
        controller = new ControlServlet();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String cartId = request.getParameter("id");
        Cart cart = controller.selectCartById(Integer.parseInt(cartId));
        HttpSession session = request.getSession(true);
        session.setAttribute("cart", cart);
        RequestDispatcher rd = request.getRequestDispatcher("ViewPastCart.jsp");
        rd.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Vector<Cart> pastCarts = new Vector<>();
        String userName = request.getParameter("userName");
        pastCarts = controller.selectPastCarts(userName);
        HttpSession session = request.getSession(true);
        session.setAttribute("pastCarts", pastCarts);
    }

}
