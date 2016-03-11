package CommonServlets;

import controllers.ControlServlet;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class SignUp extends HttpServlet {
    ControlServlet controlServlet;
    
    @Override
    public void init(){
        controlServlet = new ControlServlet();
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter out=response.getWriter();
        String type = request.getParameter("type");
        switch (type) {
            case "userName":
            {
                String userName= request.getParameter("userName");
                boolean userNameFound = controlServlet.doesUserNameExist(userName);
                if(userNameFound){
                    System.out.println("This User Name Alraedy Exists");
                    out.println("This User Name Alraedy Exists");
                } else{
                    System.out.println("valid");
                    out.println("valid");
                }  
            }
                break;

        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
