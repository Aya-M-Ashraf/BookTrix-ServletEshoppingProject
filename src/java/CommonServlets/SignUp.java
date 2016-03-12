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
        System.out.println(type);
        switch (type) {
            case "mail":
            {
                String email = request.getParameter("email");
                boolean emailFound = controlServlet.doesEmailExist(email);
                System.out.println(emailFound);
                if(emailFound){
                    out.println("This email alraedy exists");
                    System.out.println("email exists");
                } else {
                    out.println("valid");
                    System.out.println("email doesn't exists");
                }
            }
                break;
            case "userName":
            {
                String userName= request.getParameter("userName");
                boolean userNameFound = controlServlet.doesUserNameExist(userName);
                if(userNameFound){
                    out.println("This user name alraedy exists");
                } else{
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
