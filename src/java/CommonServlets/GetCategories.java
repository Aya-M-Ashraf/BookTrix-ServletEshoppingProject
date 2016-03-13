/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CommonServlets;

import Beans.Book;
import Beans.Category;
import DBconnectivity.ManipulateDB;
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
public class GetCategories extends HttpServlet {

     @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
          

        Vector<Category> allcategories = new Vector<>();
         ManipulateDB m = new ManipulateDB();
         allcategories=m.selectAllCategories();

         System.out.println("size of all categoriess ==>"+allcategories.size());
        HttpSession session = request.getSession(true);
        session.setAttribute("allcategories", allcategories);
//        RequestDispatcher rd = request.getRequestDispatcher("ViewBooks.jsp");
//        rd.include(request, response);
    }

}
