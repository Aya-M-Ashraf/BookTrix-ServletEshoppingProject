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
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

public class SignUp extends HttpServlet {

    ControlServlet controlServlet;

    @Override
    public void init() {
        controlServlet = new ControlServlet();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        String type = request.getParameter("type");
        System.out.println(type);
        switch (type) {
            case "mail": {
                String email = request.getParameter("email");
                boolean emailFound = controlServlet.doesEmailExist(email);
                System.out.println(emailFound);
                if (emailFound) {
                    out.println("This email alraedy exists");
                    System.out.println("email exists");
                } else {
                    out.println("valid");
                    System.out.println("email doesn't exists");
                }
            }
            break;
            case "userName": {
                String userName = request.getParameter("userName");
                boolean userNameFound = controlServlet.doesUserNameExist(userName);
                if (userNameFound) {
                    out.println("This user name alraedy exists");
                } else {
                    out.println("valid");
                }

            }
            break;

        }

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
                    if (!item.isFormField()) {

                        System.out.println(new File(AddProduct.class.getClassLoader().getResource("").getPath().replace("%20", " ").substring(0, AddProduct.class.getClassLoader().getResource("").getPath().replace("%20", " ").length() - 27) + "/web/Resources/users_pics/" + item.getName()));
                        item.write(new File(AddProduct.class.getClassLoader().getResource("").getPath().replace("%20", " ").substring(0, AddProduct.class.getClassLoader().getResource("").getPath().replace("%20", " ").length() - 27) + "/web/Resources/users_pics/" + item.getName()));
                        img = item.getName();
                    }
                }
            }

            User u = new User(email, userName, password, creditLimit, job, address, img, role);
            ManipulateDB m = new ManipulateDB();
            m.insertUser(u);

            response.sendRedirect("ViewAllUsers.jsp");

        } catch (Exception ex) {
            Logger.getLogger(AddProduct.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
