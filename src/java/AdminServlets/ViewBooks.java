/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AdminServlets;

import Beans.Book;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
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
public class ViewBooks extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Book b1 = new Book(1, "name", "book1.jpg");
        Book b2 = new Book(1, "name", "book2.jpg");
        Book b3 = new Book(1, "name", "book3.jpg");
        Book b4 = new Book(1, "name", "book4.jpg");
        Book b5 = new Book(1, "name", "book7.jpg");
        Book b6 = new Book(1, "name", "book3.jpg");
        Book b7 = new Book(1, "name", "book1.jpg");
        Book b8 = new Book(1, "name", "book3.jpg");

        ArrayList<Book> allbooks = new ArrayList<>();
        allbooks.add(b1);
        allbooks.add(b2);
        allbooks.add(b3);
        allbooks.add(b4);
        allbooks.add(b5);
        allbooks.add(b6);
        allbooks.add(b7);
        allbooks.add(b8);
        allbooks.add(new Book(1, "sad", "book10.jpg"));
        allbooks.add(new Book(1, "sad", "book10.jpg"));
        allbooks.add(new Book(1, "sad", "book10.jpg"));
        allbooks.add(new Book(1, "sad", "book10.jpg"));
        allbooks.add(new Book(1, "sad", "book10.jpg"));
        allbooks.add(new Book(1, "sad", "book10.jpg"));
        allbooks.add(new Book(1, "sad", "book10.jpg"));
        allbooks.add(new Book(1, "sad", "book10.jpg"));

        HttpSession session = request.getSession(true);
        session.setAttribute("book", allbooks);
//        RequestDispatcher rd = request.getRequestDispatcher("ViewBooks.jsp");
//        rd.include(request, response);
    }

}
