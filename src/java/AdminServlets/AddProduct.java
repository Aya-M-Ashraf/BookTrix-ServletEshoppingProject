package AdminServlets;

import Beans.Book;
import Beans.Category;
import DBconnectivity.ManipulateDB;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

/**
 *
 * @author Ahmed
 */
public class AddProduct extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println("welcome ===>" + new File("pics/").getAbsolutePath());

        try {
            String bookName =null,bookAuthor =null,category =null,desc =null,img =null;
                    double quantity =0,price =0;
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
                    System.out.println(name);
                    System.out.println(value);
                    
                     if (name.equalsIgnoreCase("bookName")) {
                        bookName = value;
                    } else if (name.equalsIgnoreCase("bookAuthor")) {
                        bookAuthor = value;
                    } else if (name.equalsIgnoreCase("quantity")) {
                        quantity = Double.parseDouble(value);
                    } else if (name.equalsIgnoreCase("category")) {
                        category = value;

                    } else if (name.equalsIgnoreCase("desc")) {
                        desc = value;
                    } else if (name.equalsIgnoreCase("price")) {
                        price = Double.parseDouble(value);
                    } 

                } else {
                    if (!item.isFormField()) {

                        System.out.println(new File(AddProduct.class.getClassLoader().getResource("").getPath().replace("%20", " ").substring(0, AddProduct.class.getClassLoader().getResource("").getPath().replace("%20", " ").length() - 16) + "/Resources/pics/" + item.getName()));
                        item.write(new File(AddProduct.class.getClassLoader().getResource("").getPath().replace("%20", " ").substring(0, AddProduct.class.getClassLoader().getResource("").getPath().replace("%20", " ").length() - 16) + "/Resources/pics/" + item.getName()));
                        img =item.getName();
                    }
                }
            }
            
            ManipulateDB m = new ManipulateDB();
            Category categoryObj=m.selectCategoryFromName(category);
            
            
            Book b = new Book(bookName,(int)quantity,bookAuthor ,categoryObj,(int) price,img, desc);
            System.out.println(b);
            m.insertBook(b);

            response.sendRedirect("ViewBooks.jsp");

        } catch (Exception ex) {
            Logger.getLogger(AddProduct.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
