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

            function ajaxCallBack(responseTxt, statusTxt, xhr) {
                if (statusTxt === "success") {


                }
            }

            $(document).ready(function () {
                $(".addToCartImg").click(function (event) {
                    alert(event.target.id);
                    $.post("Cart",
                            {
                                "userName": '${user.userName}',
                                "bookId": event.target.id,
                                "Quantity": "1"
                            }
                    , ajaxCallBack);
                });
                $(".viewBookInfo").click(function (event) {
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

        <c:if test="${user.userName==null}">
            <c:redirect url="Login.jsp"></c:redirect>
        </c:if>

        <c:if test="${user.role=='admin'}">
            <c:redirect url="AdminHome.jsp"></c:redirect>
        </c:if>

        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title></title>
    </head>
    <body><br><br><br><br>
        <div class="bookshelf" id="allbooks" onmouseover ="hideCart()">
            <div class="shelf">
                <c:forEach items="${sessionScope.viewAllbooks}" var="myBook" varStatus="stat">
                    <!--${stat.index}-->
                    <fmt:parseNumber var="i" type="number" value="${stat.index}" ></fmt:parseNumber>
                    <!--${i}-->

                    <!--<h1><c:out value="${myBook.bookId}" /></h1>-->
                    <c:if test="${i%5 ==0}">
                        <div class="row-1">
                            <div class="loc" id="shelf1"> 
                            </c:if>
                            <c:url var="myUrl" value="/Resources/pics/${myBook.img}"  context="/BookTrix"/>
                            <div id = "${myBook.bookId}" draggable="true" ondragstart="drag(event)" cost =520 class="gallery" > 
                                <a href="#allbooks" class="viewBookInfo" id = "${myBook.bookId}"> 
                                    <div  class="sample thumb1" style="width:95px; height:117px;" id = "${myBook.bookId}">
                                        <img src="${myUrl}" style="width:95px; height:117px;" id = "${myBook.bookId}">
                                    </div>  
                                </a>  
                                <table align="center" style="width:95px; height:40px;">
                                    <tr>
                                        <td>
                                            <a >  
                                                <br>
                                                <c:if test="${ myBook.quantity <=0}">
                                                    <img src="Resources/images/OutOfStock.png" style="width:95px; height:40px; "align="Center"  id="${myBook.bookId}"/> 
                                                    <h5>Avaliable : ${ myBook.quantity}</h5>
                                                </c:if>
                                                <c:if test="${myBook.quantity >0}">
                                                    <img src="Resources/images/AddToCart.png" style="width:95px; height:40px; cursor: pointer;"align="Center" class="addToCartImg" id="${myBook.bookId}"/>
                                                    <h5>Avaliable : ${ myBook.quantity}</h5>
                                                </c:if>
                                            </a>
                                        </td>
                                    </tr>

                                </table>

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
