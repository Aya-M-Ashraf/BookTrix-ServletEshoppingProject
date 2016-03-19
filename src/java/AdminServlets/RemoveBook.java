/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AdminServlets;

import Beans.Book;
import controllers.ControlServlet;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Vector;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Ahmed
 */
public class RemoveBook extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Vector<Book> allBooks = new Vector<>();
        ControlServlet controlServlet = new ControlServlet();
        int bookId = Integer.parseInt(request.getParameter("bookId"));
        controlServlet.deleteBookById(bookId);

        allBooks = controlServlet.getAllBooks();
        HttpSession session = request.getSession(true);
        session.setAttribute("viewAllbooks", allBooks);

    }
}
