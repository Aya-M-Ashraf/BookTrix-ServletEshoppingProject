<%-- 
    Document   : categoriesList
    Created on : Mar 12, 2016, 7:16:37 PM
    Author     : lenovo
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.io.*,java.util.*,java.sql.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
            <c:forEach items="${sessionScope.allcategories}" var="myCategory" varStatus="loop">
                <li>
                    <a onclick="" href="#"> ${myCategory.name} </a>
                </li>
            </c:forEach>
        
    </body>
</html>
