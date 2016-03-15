package AdminServlets;
import javax.servlet.http.*;
import java.io.*;
import javax.servlet.ServletException;
public class AdminHome extends HttpServlet {
 public void doGet(HttpServletRequest request,
                    HttpServletResponse response)
            throws ServletException, IOException
  {
		
		
		HttpSession session = request.getSession(false);
		if (session == null)
		 response.sendRedirect("Login");
		else
		{
			String loggedIn= (String) session.getAttribute("loggedIn");
                        if(loggedIn==null)
                            response.sendRedirect("Login");
			if (!loggedIn.equals("true"))
			response.sendRedirect("Login");
		}
		
		
		
		
		PrintWriter out=response.getWriter();

		Cookie cookie = null;
	  Cookie[] cookies = null;
      cookies = request.getCookies();
	   if( cookies != null ){
         for (int i = 0; i < cookies.length; i++){
            cookie = cookies[i];
			if( (cookie.getName( )).equals("username")){
				out.println("welcome "+cookie.getValue( ));
				break;
			}
			else{
		
				continue;
			}
			
		 }
		   out.println("welcome ");
      }else{
         out.println("welcome ");
      }
		
	}
	
}