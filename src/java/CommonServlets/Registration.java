/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.*;
import java.sql.*;
/**
 *
 * @author ahmed elnazer
 */
@WebServlet(name = "Registration", urlPatterns = {"/Registration"})
public class Registration extends HttpServlet {

  ServletConfig con;
		int notfound=0;
		Connection conn;
		 PreparedStatement pst ;
		 Statement stmt;
		ResultSet rs;
	public void init (ServletConfig config) throws ServletException{
		con=config;
		System.out.println("inside applet");
	}
	public void doGet(HttpServletRequest request,HttpServletResponse response)
        throws ServletException, IOException
    {
	
		RequestDispatcher rd1 =request.getRequestDispatcher("registeration.html");
		RequestDispatcher rd2 =request.getRequestDispatcher("registeration2.html");
		
		String notf= request.getParameter("notfound");
		  if(notf!=null&&notf.equals("1")){
			
				rd2.include(request,response);
		  }else{
		  
				rd1.include(request,response);
		  }
		

		
	}
	public void doPost(HttpServletRequest request,HttpServletResponse response)
        throws ServletException, IOException
    {
				
			String userName = request.getParameter("userName");
			String password = request.getParameter("password");
			String firstname = request.getParameter("firstname");
			String lastname = request.getParameter("lastname");
		   String JDBC_DRIVER="com.mysql.jdbc.Driver";  
            String DB_URL="jdbc:mysql://localhost/test";
		   String USER = "root";
		   String PASS = "root";

		  try{
        
                Class.forName("com.mysql.jdbc.Driver");
				conn = DriverManager.getConnection(DB_URL, USER, PASS);
				stmt = conn.createStatement();
				String sql;
				sql = "SELECT * from user";
				 rs = stmt.executeQuery(sql);

              while(rs.next()){
			  
		    if(userName.equals(rs.getString("username")))
			{
			      response.sendRedirect("Registration?notfound=1");
				
				 
			}
		 }
		 
		        pst = conn.prepareStatement("insert into user(firstname,lastname,username,password,status) values(?,?,?,?,'unavailable')");
			    pst.setString(1,firstname); 
			    pst.setString(2,lastname); 
			    pst.setString(3,userName);  
                pst.setString(4,password);        
          
              int i = pst.executeUpdate();  
              if(i!=0){  
              
                    response.sendRedirect("Login");
              } 
			
		       
		
		  }catch(SQLException se){
        
                se.printStackTrace();
          }catch(Exception e){
            
           e.printStackTrace();
        }finally{
         
         try{
            if(pst!=null)
               pst.close();
         }catch(SQLException se2){
         }
		  try{
            if(stmt!=null)
               stmt.close();
         }catch(SQLException se2){
         }
         try{
            if(conn!=null)
            conn.close();
         }catch(SQLException se){
            se.printStackTrace();
         }
      }
	}
}
