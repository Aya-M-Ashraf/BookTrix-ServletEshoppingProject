<%-- 
    Document   : categoriesList
    Created on : Mar 12, 2016, 7:16:37 PM
    Author     : lenovo
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.io.*,java.util.*,java.sql.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<script type="text/javascript" src="http://code.jquery.com/jquery-1.11.1.min.js"></script>  

<script>
    $(".categoriesList").click(function (event){
        var x= event.target.id;
            $.ajax({
                url: "ViewBooks",
                type: 'Post',
                async: false,
                data: {
                    "categoryName": x
                },
                success: function (data) {
                    $('#allbooks').load("ViewBooks.jsp");
                     
                }
            });
    });
</script>
    

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
            <c:forEach items="${sessionScope.allcategories}" var="myCategory" varStatus="loop">
                <li class="categoriesList">
                    <a id="${myCategory.name}" href="#allbooks"> ${myCategory.name} </a>
                </li>
            </c:forEach>
        
    </body>
</html>
