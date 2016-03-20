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



        <style>
            input[type=text] {padding:5px; border:2px solid #ccc; -webkit-border-radius: 5px;border-radius: 5px;}
            input[type=text]:focus {border-color:#333;}
            input[type=password] {padding:5px; border:2px solid #ccc; -webkit-border-radius: 5px;border-radius: 5px;}
            input[type=password]:focus {border-color:#333;}
            input[type=submit],  input[type=button] {padding: 15px 32px; background:#ccc; border:0 none;cursor:pointer;-webkit-border-radius: 5px;border-radius: 5px; }
            table {border-collapse: collapse;width: 100%;}
            th, td {padding: 8px;}
            tr:nth-child(odd){background-color: #f2f2f2}
            th {background-color: #a73f2d; color: white;}
        </style>


        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title></title>
    </head>
    <body id="mainBody">

        <table align="center" style="width: 60%;" >
            <fmt:parseNumber var="totalCost" type="number" value="0" ></fmt:parseNumber>


            <c:forEach items="${sessionScope.cart.myBooks}" var="myBook" varStatus="stat">
                <fmt:parseNumber var="i" type="number" value="${stat.index}" ></fmt:parseNumber>
                <c:set var="totalCost" value="${totalCost =totalCost+(myBook.key.price * myBook.value)}"></c:set>


                <c:url var="myUrl" value="/Resources/pics/${myBook.key.img}"  context="/BookTrix"/>                       

                <c:if test="${i%5 ==0}">
                    <tr>
                    </c:if>
                    <td>
                        <table align="center" style="width: 60%;">
                            <tr>
                                <td style="background-color: transparent;">
                                    <img src="${myUrl}" style="width:95px; height:117px;" id = "${myBook.key.bookId}"> 
                                </td>
                            </tr>
                            <tr>
                                <td style="background-color: transparent;">
                                    <h5>Name : ${myBook.key.bookName}</h5>
                                </td> 
                            </tr>
                            <tr>
                                <td>
                                    <h5 style="color:#a73f2d; ">Price : ${myBook.key.price} $</h5>
                                    <h5 style="color:#a78f2a; ">Quantity :${myBook.key.quantity} </h5>
                                </td> 

                            </tr>

                        </table>
                    </td>


                    <c:if test="${i%5 ==4 and i!=0}">
                    </tr>
                </c:if>
            </c:forEach>
            <tr>
                <td colspan="5" align="center" style="background-color: white;">
                    <h1 id="changableTotal"> Total Cost : ${totalCost}</h1>
                </td>
            </tr>

        </table>
    </body> 
</html>
