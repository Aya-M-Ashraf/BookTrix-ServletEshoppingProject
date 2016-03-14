/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CommonServlets;

import Beans.Book;
import Beans.Category;
import DBconnectivity.ManipulateDB;
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
public class DeleteBookFromCart extends HttpServlet {

    ControlServlet controller;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        System.out.println("-========>" + request.getParameter("userName"));
        System.out.println("-========>" + request.getParameter("bookId"));
        String userName = request.getParameter("userName");
        int bookId = Integer.parseInt(request.getParameter("bookId"));
        ManipulateDB m = new ManipulateDB();
        m.deleteBook(userName, bookId);
//        Vector<Book> allbooks = controller.getAllBooksInCart(userName);
//        HttpSession session = request.getSession(true);
//        session.setAttribute("book", allbooks);
    }

}
