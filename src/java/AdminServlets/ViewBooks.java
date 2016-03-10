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
        Book b1 = new Book();
        Book b2 = new Book();

        b1.setId(1);
        b2.setId(2);
        b1.setImg("book1.jpg");
        b2.setImg("book2.jpg");

        ArrayList<Book> allbooks = new ArrayList<>();
        allbooks.add(b1);
        allbooks.add(b2);

        HttpSession session = request.getSession(true);
        session.setAttribute("book", allbooks);
//        RequestDispatcher rd = request.getRequestDispatcher("ViewBooks.jsp");
//        rd.include(request, response);
    }

}
