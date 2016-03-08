

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title> user home</title>
    </head>
    <body>
      <%  


        session = request.getSession(false);
        if (session == null) {
            response.sendRedirect("Login.jsp");
        } else {
            String loggedIn = (String) session.getAttribute("loggedIn");
            if (!loggedIn.equals("true")) {
                response.sendRedirect("Login.jsp");
            }
        }

        Cookie cookie = null;
        Cookie[] cookies = null;
        String username=(String) session.getAttribute("username");
        String role="";
        cookies = request.getCookies();
        if (cookies != null) {
            for (int i = 0; i < cookies.length; i++) {
                cookie = cookies[i];
                if ((cookie.getName()).equals("role")) {
                      role=cookie.getValue();
                         break;
                } 
            }

      
  %>
        
       <center><h1> Welcome  <%=role +     username %> </h1></center>;
     <%  }%>
    </body>
</html>
