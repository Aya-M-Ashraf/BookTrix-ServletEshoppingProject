<%-- 
    Document   : Login
    Created on : Mar 3, 2016, 6:30:14 PM
    Author     : Ahmed Ashraf
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.io.*,java.util.*,java.sql.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <jsp:useBean id="book" class="Beans.Book" /> 
        <h1><c:out value="${sessionScope.book.id}" /></h1>
        <h1>Hello World!</h1>
    </body> 
</html>
