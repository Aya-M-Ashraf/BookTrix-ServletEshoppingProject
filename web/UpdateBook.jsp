<!DOCTYPE html>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.io.*,java.util.*,java.sql.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<script type="text/javascript" src="http://code.jquery.com/jquery-1.11.1.min.js"></script>  

<style>
    input{padding:5px; border:2px solid #ccc; -webkit-border-radius: 5px;border-radius: 5px;}
    input[type=number ] {padding:5px; border:2px solid #ccc; -webkit-border-radius: 5px;border-radius: 5px;}
    input[type=text]:focus {border-color:#333;}
    input[type=password] {padding:5px; border:2px solid #ccc; -webkit-border-radius: 5px;border-radius: 5px;}
    input[type=password]:focus {border-color:#333;}
    input[type=submit] {padding: 15px 32px; background:#ccc; border:0 none;cursor:pointer;-webkit-border-radius: 5px;border-radius: 5px; }
    /*            input[type=file] {padding: 10px 20px; background:#ccc; border:0 none;cursor:pointer;-webkit-border-radius: 5px;border-radius: 5px; }*/
    select{padding:5px; border:2px solid #ccc; -webkit-border-radius: 5px;border-radius: 5px;}
    table {border-collapse: collapse;width: 100%;}
    th, td {padding: 8px; background-color: #f2f2f2}
    tr:nth-child(even){background-color: #f2f2f2}
    th {background-color: #a73f2d; color: white;}
</style>
<script>
    $(".updateImg").click(function () {
        $.ajax({
            url: "UpdateBook",
            type: 'Post',
            async: false,
            data: {
                "bookId": ${book.bookId},
                "bookAuthor": $("[ name = 'bookAuthor']").val(),
                "quantity": $("[ name = 'quantity']").val(),
                "price": $("[ name = 'price']").val(),
                "desc": $("[ name = 'desc']").val()

            },
            success: function (data) {
                
                $('#img').attr('src', 'Resources/images/Done.png');
                $('#textValue').text("Done");
            }
        });



    });
</script>

   <c:if test="${userName==null}">
        <c:redirect url="Login.jsp"></c:redirect>
    </c:if>

    <c:if test="${user.role=='user'}">
        <c:redirect url="UserHome.jsp"></c:redirect>
    </c:if>
<html>
    <head>
        <title>TODO supply a title</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <script type="text/javascript" src="http://code.jquery.com/jquery-1.11.1.min.js"></script>  
    </head>
    <body>
        <img src="/BookTrix/Resources/pics/${sessionScope.book.img}" style="display: inline-block; width: 22%;height: 40%;">
        <table align="center" style="width: 50%;">
            <tr>
                <td align="center">
                    <br><br>
                    <h1>${sessionScope.book.bookName}'s Information</h1>
                    <br>
                    Book Name: <br><input type="text" name="bookName" value="${sessionScope.book.bookName}" style="width: 40%;" disabled >

                </td>
            </tr>
            <tr>
                <td align="center">

                    Book Author: <br><input type="text" name="bookAuthor" value="${sessionScope.book.author}" style="width: 40%; " >

                </td>  
            </tr>
            <tr>
                <td align="center">

                    Available Quantity: <br><input type="number" name="quantity" min="0" value="${sessionScope.book.quantity}" style="width: 40%; "  >

                </td>  
            </tr>
            <tr>
                <td align="center">

                    Price: <br><input type="number" name="price" min="0" value="${sessionScope.book.price}" style="width: 40%; "  >

                </td>  
            </tr>
            <tr>
                <td align="center">

                    Category: <br><input type="text" name="category" value="${sessionScope.book.category.name}" style="width: 40%; " disabled >


                </td>
            </tr>
            <tr>
                <td align="center">
                    Description: <br><textarea name="desc" style="width: 50%; height:200px " >${sessionScope.book.description}</textarea>   
                </td>             
            </tr>

            <tr>
                <td>
                    <br>
                    <a > 
                        <img src="Resources/images/Update.png" style="width:100px; height:100px; cursor: pointer;"align="Center" class="updateImg" id="img"/> 
                    </a>
                    <h3 align="Center" id="textValue">Update</h3>
                </td>

            </tr>
        </table>
        <br>

    </body>
</html>
