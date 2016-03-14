<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.io.*,java.util.*,java.sql.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <script type="text/javascript" src="http://code.jquery.com/jquery-1.11.1.min.js"></script>

        <link rel="stylesheet" href="Resources/css/style1.css">
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
        <script src="Resources/js/bootstrap.min.js"></script>
        <script src="Resources/js/plugin.js"></script>
        <script src="Resources/js/wow.min.js"></script>
        <script>new WOW().init();</script>
        <script>
            function checkPassword() {
                if ($("#password").val() !== $("#password2").val()) {
                    $("#usernamemsg").text("unmatched password");
                } else {
                    $("#usernamemsg").text("");
                }
            }

        </script>
        <style >
            table th,td{
                border-radius: 20px;
                  text-align: center;
            }
        </style>
    </head>
    <body align="center" onload="">


        <div class="container">

            

                <h1>${sessionScope.book.name}'s Information</h1>
                <center> <table border=3px  style=" border-color: #f3f3f3; font-family: cursive; height: 400px; width:600px;"  >
                    <tr><th align="center" valign="middle">Book Name</th><td>${sessionScope.book.name}</td></tr>
                    <tr><th>Book Author</th><td>${sessionScope.book.author} </td></tr>
                    <tr><th>Cover Image</th><td><img src=${sessionScope.book.img} style="display: inline-block; width: 22%;height: 40%;"></td></tr>
                    <tr><th>Available quantity</th><td>${sessionScope.book.quantity} 15</td></tr>
                    <tr><th>Category</th><td>${sessionScope.book.category.name} </td></tr>
                    <tr><th>Price</th><td>${sessionScope.book.price} </td></tr>
                    <tr><th>Description</th><td>${sessionScope.book.description} </td></tr>                  
                    <tr><th></th><td><input type="button" value="buy"/></td></tr> 
                    </table></center>
         
        </div>
    </body>
</html>
