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
        <link rel="stylesheet" href="Resources/css/bootstrap.css"> 
        <link rel="stylesheet" href="Resources/css/font-awesome.min.css">   
        <link rel="stylesheet" href="Resources/css/style.css" >
        <link rel="stylesheet" href="Resources/css/media.css" >
        <link rel="stylesheet" href="Resources/css/defult-theme.css" >
        <link rel="stylesheet" href='Resources/css/hover.css'>
        <link rel="stylesheet" href='Resources/css/animate.css'>
        <link rel="stylesheet" href='http://fonts.googleapis.com/css?family=Droid+Sans:400,700'>
        <link type="text/css" rel="stylesheet" href="Resources/css/jquery.ui.css"/>
        <link type="text/css" rel="stylesheet" href="Resources/css/default.css"/>
        <link type="text/css" rel="stylesheet" href="Resources/css/tooltipster.css"/>
        <script type="text/javascript" src="http://code.jquery.com/jquery-1.11.1.min.js"></script>  
        <script type="text/javascript" src="Resources/js/jquery.tooltipster.min.js"></script>
        <script type="text/javascript" src="Resources/js/html5shiv.min.js"></script>
        <script type="text/javascript" src="Resources/js/respond.min.js"></script>
        <script type="text/javascript" src="Resources/js/jquery.poptrox.min.js"></script>
        <script type="text/javascript" src="Resources/lib/hash.js"></script>
        <script type="text/javascript" src="Resources/lib/turn.min.js"></script>
        <script type="text/javascript" src="Resources/lib/zoom.min.js"></script>
        <script type="text/javascript" src="Resources/lib/bookshelf.js"></script>

        <script>
            $(document).ready(function () {
                $.ajax({
                    url: "Cart",
                    type: 'Get',
                    async: false,
                    data: {
                        "userName" : '${userName}'
                    }
                });


                $('#1').tooltipster({
                    content: $('<span><img src="Resources/pics/book1.jpg"/> <strong>This text is in bold case !</strong><br> discription</span>')
                });
                $('#2').tooltipster({
                    content: $('<span><img src="Resources/pics/book2.jpg"/> <strong>This text is in bold case !</strong><br> discription</span>')
                });
                $('#3').tooltipster({
                    content: $('<span><img src="Resources/pics/book3.jpg"/> <strong>This text is in bold case !</strong><br> discription</span>')
                });
                $('#4').tooltipster({
                    content: $('<span><img src="Resources/pics/book4.jpg"/> <strong>This text is in bold case !</strong><br> discription</span>')
                });
                $('#5').tooltipster({
                    content: $('<span><img src="Resources/pics/book5.jpg"/> <strong>This text is in bold case !</strong><br> discription</span>')
                });
                $('#6').tooltipster({
                    content: $('<span><img src="Resources/pics/book6.jpg"/> <strong>This text is in bold case !</strong><br> discription</span>')
                });
                $('#7').tooltipster({
                    content: $('<span><img src="Resources/pics/book7.jpg"/> <strong>This text is in bold case !</strong><br> discription</span>')
                });
                $('#8').tooltipster({
                    content: $('<span><img src="Resources/pics/book8.jpg"/> <strong>This text is in bold case !</strong><br> discription</span>')
                });
                $('#9').tooltipster({
                    content: $('<span><img src="Resources/pics/book9.jpg"/> <strong>This text is in bold case !</strong><br> discription</span>')
                });

                $('#10').tooltipster({
                    content: $('<span><img src="Resources/pics/book10.jpg"/> <strong>This text is in bold case !</strong><br> discription</span>')
                });
                $('#11').tooltipster({
                    content: $('<span><img src="Resources/pics/book11.jpg"/> <strong>This text is in bold case !</strong><br> discription</span>')
                });
                $('#12').tooltipster({
                    content: $('<span><img src="Resources/pics/book12.jpg"/> <strong>This text is in bold case !</strong><br> discription</span>')
                });
                $('#13').tooltipster({
                    content: $('<span><img src="Resources/pics/book13.jpg"/> <strong>This text is in bold case !</strong><br> discription</span>')
                });
                $('#14').tooltipster({
                    content: $('<span><img src="Resources/pics/book14.jpg"/> <strong>This text is in bold case !</strong><br> discription</span>')
                });
                $('#15').tooltipster({
                    content: $('<span><img src="Resources/pics/book15.jpg"/> <strong>This text is in bold case !</strong><br> discription</span>')
                });

            });
        </script>


        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title></title>
    </head>
    <body>
        <center><h2>${userName}'s Cart</h2></center>
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
                                <a href="Resources\Work\samples\1-java\index.html" data-poptrox="iframe,950x515" id = "${myBook.bookId}"> 
                                    <div  class="sample thumb1" style="width:95px; height:117px;" id = "${myBook.bookId}">
                                        <img src="${myUrl}" style="width:95px; height:117px;" id = "${myBook.bookId}">
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
