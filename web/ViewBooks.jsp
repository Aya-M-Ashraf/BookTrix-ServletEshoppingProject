<%-- 
    Document   : ViewBooks
    Created on : Mar 7, 2016, 9:48:30 PM
    Author     : Ahmed
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.io.*,java.util.*,java.sql.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<!DOCTYPE html>
<html>
    <head>
        <script type="text/javascript" src="http://code.jquery.com/jquery-1.11.1.min.js"></script>  

        <script>
            $(document).ready(function () {
                $.ajax({
                    url: "ViewBooks",
                    type: 'Post',
                    async: false,
                    data: {},
                    success: function (data, textStatus, jqXHR) {
//                        $("#allbooks").load(location.href + " #allbooks");
                    }
                });
            });
        </script>


        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title></title>
    </head>
    <body>
        <div class="bookshelf" id="allbooks" onmouseover ="hideCart()">
            <div class="shelf">
                <c:forEach items="${sessionScope.book}" var="myBook" varStatus="stat">
                    <!--${stat.index}-->
                    <fmt:parseNumber var="i" type="number" value="${stat.index}" ></fmt:parseNumber>
                    <!--${i}-->

                    <!--<h1><c:out value="${myBook.bookId}" /></h1>-->
                    <c:if test="${i%5 ==0}">
                        <div class="row-1">
                            <div class="loc" id="shelf1"> 
                            </c:if>
                            <c:url var="myUrl" value="/Resources/pics/${myBook.img}"  context="/BookTrix"/>
                            <div id = "${myBook.bookId}"  draggable="true" ondragstart="drag(event)" cost =520 class="gallery" > 
                                <a href="Resources\Work\samples\1-java\index.html" data-poptrox="iframe,950x515"> 
                                    <div  class="sample thumb1" style="width:95px; height:117px;">
                                        <img src="${myUrl}" style="width:95px; height:117px;">
                                    </div>  
                                </a>  

                            </div>
                            <c:if test="${i%5 ==4 and i!=0}">
                            </div>  
                        </div>
                        <br><br><br><br>
                    </c:if>
                </c:forEach>
            </div>
        </div>
    </body> 
</html>