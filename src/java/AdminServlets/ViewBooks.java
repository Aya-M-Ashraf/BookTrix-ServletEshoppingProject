package AdminServlets;

import Beans.Book;
import DBconnectivity.ManipulateDB;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
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
public class ViewBooks extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       
        ManipulateDB m = new ManipulateDB();
        Vector<Book> allbooks = m.selectAllBooks();
        
        System.out.println("iam in view books");

        HttpSession session = request.getSession(true);
        session.setAttribute("book", allbooks);
//        RequestDispatcher rd = request.getRequestDispatcher("ViewBooks.jsp");
//        rd.include(request, response);
    }

}
