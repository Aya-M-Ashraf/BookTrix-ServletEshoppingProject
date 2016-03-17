package AdminServlets;

import Beans.Book;
import DBconnectivity.ManipulateDB;
import controllers.ControlServlet;
import java.io.IOException;
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
public class ViewBooks extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       
        
       Vector<Book> allBooks = new Vector<>();
        ControlServlet controlServlet = new ControlServlet();
        String categoryName = request.getParameter("categoryName");
        System.out.println("viewBooks category name: "+ categoryName);
        if(categoryName!=null){
            allBooks=controlServlet.getBooksInCategory(categoryName);
            System.out.println("in not null condition"+allBooks.size());
        }else{
            allBooks = controlServlet.getAllBooks();
            System.out.println("in null condition");
        }
        HttpSession session = request.getSession(true);
        session.setAttribute("viewAllbooks", allbooks);
    }
}
