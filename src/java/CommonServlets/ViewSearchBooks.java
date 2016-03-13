/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CommonServlets;

import Beans.Book;
import DBconnectivity.ManipulateDB;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Vector;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Ahmed
 */
public class ViewSearchBooks extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String searchString = request.getParameter("searchString");
        
        ManipulateDB m = new ManipulateDB();
        Vector<Book> searchBooks = m.selectAllBooksWhereNameLike(searchString);
//        Vector<Book> searchBooks = m.selectAllBooks();

        System.out.println("sizes"+searchBooks.size());
        HttpSession session = request.getSession(true);
        session.setAttribute("searchBooks", searchBooks);
//        RequestDispatcher rd = request.getRequestDispatcher("ViewSearchBooks.jsp");
//        rd.include(request, response);
    }

}
