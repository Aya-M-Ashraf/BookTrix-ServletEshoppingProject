<%-- 
    Document   : ViewSingleUser
    Created on : Mar 11, 2016, 10:03:33 PM
    Author     : Ahmed
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.io.*,java.util.*,java.sql.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<script type="text/javascript" src="http://code.jquery.com/jquery-1.11.1.min.js"></script>  



<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>List user Data   </h1>
        <c:out value="${sessionScope.user.userName}" />
    </body>
</html>
