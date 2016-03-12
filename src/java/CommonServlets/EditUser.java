/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CommonServlets;

import AdminServlets.AddProduct;
import Beans.User;
import DBconnectivity.ManipulateDB;
import controllers.ControlServlet;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

/**
 *
 * @author lenovo
 */
public class EditUser extends HttpServlet {

    ControlServlet controlServlet;

    @Override
    public void init() {
        controlServlet = new ControlServlet();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String email = null, userName = null, job = null, address = null, password = null, img = null, role = null;
        double creditLimit = 0;

        try {
            // Create a factory for disk-based file items
            DiskFileItemFactory factory = new DiskFileItemFactory();
// Create a new file upload handler
            ServletFileUpload upload = new ServletFileUpload(factory);

            // Parse the request
            List<FileItem> items = upload.parseRequest(request);
            Iterator<FileItem> iter = items.iterator();
            while (iter.hasNext()) {
                FileItem item = iter.next();
                if (item.isFormField()) {
//processFormField(item);
                    String name = item.getFieldName();
                    String value = item.getString();

                    if (name.equalsIgnoreCase("email")) {
                        email = value;
                    } else if (name.equalsIgnoreCase("userName")) {
                        userName = value;
                    } else if (name.equalsIgnoreCase("creditLimit")) {
                        creditLimit = Double.parseDouble(value);
                    } else if (name.equalsIgnoreCase("job")) {
                        job = value;

                    } else if (name.equalsIgnoreCase("address")) {
                        address = value;
                    } else if (name.equalsIgnoreCase("password")) {
                        password = value;
                    }
                } else {
                    if (!item.isFormField() && !item.getName().equals("")) {
                        System.out.println(new File(AddProduct.class.getClassLoader().getResource("").getPath().replace("%20", " ").substring(0, AddProduct.class.getClassLoader().getResource("").getPath().replace("%20", " ").length() - 16) + "/Resources/users_pics/" + item.getName()));
                        item.write(new File(AddProduct.class.getClassLoader().getResource("").getPath().replace("%20", " ").substring(0, AddProduct.class.getClassLoader().getResource("").getPath().replace("%20", " ").length() - 16) + "/Resources/users_pics/" + item.getName()));
                        img = item.getName();
                    }
                }
            }

            User u = new User(email, userName, password, creditLimit, job, address, img, role);
            controlServlet.editUserDate(u);
            HttpSession session = request.getSession(true);
            session.setAttribute("done", "1");
            response.sendRedirect("UserHome.jsp");

        } catch (Exception ex) {
            Logger.getLogger(AddProduct.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
