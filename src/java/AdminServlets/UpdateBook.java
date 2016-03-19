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
public class UpdateBook extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Book newBook = new Book();
        int bookId = Integer.parseInt(request.getParameter("bookId"));
        int bookquantity = Integer.parseInt(request.getParameter("quantity"));
        int bookprice = Integer.parseInt(request.getParameter("price"));
        String desc = request.getParameter("desc");
        String bookauthor = request.getParameter("bookAuthor");

        newBook.setAuthor(bookauthor);
        newBook.setBookId(bookId);
        newBook.setDescription(desc);
        newBook.setPrice(bookprice);
        newBook.setQuantity(bookquantity);
        
        
        ControlServlet controlServlet = new ControlServlet();
        controlServlet.updateBookInfo(newBook);


    }

}
