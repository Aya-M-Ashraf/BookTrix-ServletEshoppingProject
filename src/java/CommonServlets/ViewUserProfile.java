package CommonServlets;

import Beans.Book;
import Beans.User;
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
 * @author Ahmed Ashraf
 */
public class ViewUserProfile extends HttpServlet {

    ManipulateDB m = new ManipulateDB();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        //to view user Porfile
        String currEmail = request.getParameter("email");
        User user = m.selectUserByEmail(currEmail);
        HttpSession session = request.getSession(true);
        session.setAttribute("user", user);
        RequestDispatcher rd = request.getRequestDispatcher("ViewSingleUser.jsp");
        rd.forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Vector<User> allusers = new Vector<>();
        allusers = m.selectAllUsers();
        System.out.println("asdasdasdasd");
        
        HttpSession session = request.getSession(true);
        session.setAttribute("allusers", allusers);

    }

}
