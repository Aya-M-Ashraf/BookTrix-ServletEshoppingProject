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
        <script>
            $(document).ready(function () {
                $.ajax({
                    url: "GetCategories",
                    type: 'Post',
                    async: false,
                    data: {}
                });
            });

        </script>
    </head>
    <body>
        <ul class="dropdown-menu" role="menu">

            <li><a href="#allbooks" onclick="allbooksfun()">All Books</a></li>
            <li><a href="#techbooks" onclick="techbooksfun()">Technology</a></li>
            <li><a href="#fashionbooks" onclick="fashionbooksfun()">Fashion</a></li>
            <li><a href="#childrenbooks" onclick="childrenbooksfun()">Children</a></li>
            <li><a href="#audiobooks" onclick="audiobooksfun()">Audio</a></li>
        </ul>
    </body>
</html>
