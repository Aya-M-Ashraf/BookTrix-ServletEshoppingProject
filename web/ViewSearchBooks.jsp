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

        <style>
            .mybook {
                -moz-box-shadow:inset 0px 1px 0px 0px #ffffff;
                -webkit-box-shadow:inset 0px 1px 0px 0px #ffffff;
                box-shadow:inset 0px 1px 0px 0px #ffffff;
                background:-webkit-gradient(linear, left top, left bottom, color-stop(0.05, #ededed), color-stop(1, #dfdfdf));
                background:-moz-linear-gradient(top, #ededed 5%, #dfdfdf 100%);
                background:-webkit-linear-gradient(top, #ededed 5%, #dfdfdf 100%);
                background:-o-linear-gradient(top, #ededed 5%, #dfdfdf 100%);
                background:-ms-linear-gradient(top, #ededed 5%, #dfdfdf 100%);
                background:linear-gradient(to bottom, #ededed 5%, #dfdfdf 100%);
                filter:progid:DXImageTransform.Microsoft.gradient(startColorstr='#ededed', endColorstr='#dfdfdf',GradientType=0);
                background-color:#ededed;
                -moz-border-radius:6px;
                -webkit-border-radius:6px;
                border-radius:6px;
                border:1px solid #dcdcdc;
                display:inline-block;
                cursor:pointer;
                color:#777777;
                font-family:Arial;
                font-size:15px;
                font-weight:bold;
                padding:6px 24px;
                text-decoration:none;
                text-shadow:0px 1px 0px #ffffff;
            }
            .mybook:hover {
                background:-webkit-gradient(linear, left top, left bottom, color-stop(0.05, #dfdfdf), color-stop(1, #ededed));
                background:-moz-linear-gradient(top, #dfdfdf 5%, #ededed 100%);
                background:-webkit-linear-gradient(top, #dfdfdf 5%, #ededed 100%);
                background:-o-linear-gradient(top, #dfdfdf 5%, #ededed 100%);
                background:-ms-linear-gradient(top, #dfdfdf 5%, #ededed 100%);
                background:linear-gradient(to bottom, #dfdfdf 5%, #ededed 100%);
                filter:progid:DXImageTransform.Microsoft.gradient(startColorstr='#dfdfdf', endColorstr='#ededed',GradientType=0);
                background-color:#dfdfdf;
            }
            .mybook:active {
                position:relative;
                top:1px;
            }
        </style>
        <script>
            $(document).ready(function () {

                $(".mybook").click(function (event) {
//                    alert(event.target.id);
                    $.ajax({
                        url: "ViewSingleBook",
                        type: 'Post',
                        async: false,
                        data: {
                            "bookid": event.target.id
                        },
                        success: function (data) {
                            $('#allbooks').load("BookInfo.jsp");
//                            alert("sucess");
                        }
                    });
                });
            });
        </script>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title></title>
    </head>
    <body>
        <div   id="searchResult" >

            <br><br>

            <table align="center" style="width: 60%; height: 50%">
                <c:forEach items="${sessionScope.searchBooks}" var="myBook" varStatus="stat">
                    <!--${stat.index}-->
                    <fmt:parseNumber var="i" type="number" value="${stat.index}" ></fmt:parseNumber>
                    <c:url var="myUrl" value="/Resources/pics/${myBook.img}"  context="/BookTrix"/>
                    <tr style="background-color : transparent;">
                        <td  class="mybook" style="width: 60%; " id="${myBook.bookId}" > 
                            <table id="${myBook.bookId}">
                                <tr  id="${myBook.bookId}">
                                    <td id="${myBook.bookId}">
                                        <img src="${myUrl}" style="width:80px; height:80px;"  id="${myBook.bookId}"> 
                                        <h4 id="${myBook.bookId}">Name  : ${myBook.bookName}</h4>
                                    </td>
                                    <td style="width:100px;" id="${myBook.bookId}">
                                    </td>
                                    <td  id="${myBook.bookId}" >
                                        <h4 id="${myBook.bookId}">Author  : ${myBook.author}</h4>
                                        <h4 id="${myBook.bookId}">Price  : ${myBook.price}</h4>
                                        <h4 id="${myBook.bookId}">Description  : ${myBook.description}</h4>
                                    </td>
                                </tr> 
                            </table>                      
                        </td>
                    </tr>
                </c:forEach>
            </table>
        </div>
    </body> 
</html>
