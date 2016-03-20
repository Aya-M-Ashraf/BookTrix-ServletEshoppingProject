<!DOCTYPE html>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.io.*,java.util.*,java.sql.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<script type="text/javascript" src="http://code.jquery.com/jquery-1.11.1.min.js"></script>  

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
<html>
    <head>
        <title>TODO supply a title</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <script type="text/javascript" src="http://code.jquery.com/jquery-1.11.1.min.js"></script>  
    </head>
    <body>
        <form action="AddProduct" ENCTYPE="MULTIPART/FORM-DATA" id="addBookForm" method="POST">
            <table align="center" style="width: 50%;">
                <tr>
                    <td align="center">
                        <br><br>
                        <h1>Add Book</h1>
                        <br>
                        <input type="text" name="bookName" placeholder="Book Name"style="width: 40%;" required >
                        <br>
                    </td>
                </tr>
                <tr>
                    <td align="center">
                        <br>
                        <input type="text" name="bookAuthor" placeholder="Author Name"style="width: 40%; " required>
                        <br>
                    </td>  
                </tr>
                <tr>
                    <td align="center">
                        <br>
                        <input type="number" name="quantity" min="1" placeholder="Quantity"style="width: 40%; " required >
                        <br>
                    </td>  
                </tr>
                <tr>
                    <td align="center">
                        <br>
                        <input type="number" name="price" min="1" placeholder="Price"style="width: 40%; " required >
                        <br>
                    </td>  
                </tr>
                <tr>
                    <td align="center">
                        <br>
                        Category
                        <select name="category" id="categories" >
                            <c:forEach items="${sessionScope.allcategories}" var="myCategory" varStatus="stat">
                                <fmt:parseNumber var="i" type="number" value="${stat.index}" ></fmt:parseNumber> 
                                <option value="${myCategory.name}">${myCategory.name}</option>
                            </c:forEach>
                        </select>
                        <br>
                    </td>
                </tr>
                <tr>
                    <td align="center">
                        <br>
                        <textarea name="desc" form="addBookForm" placeholder="Write a description about the book ..." style="width: 50%; height:200px " required></textarea>   
                    </td>             
                </tr>

                <tr>     
                    <td align="center">
                        <br>
                        <h3>Choose a Cover Photo</h3> 
                        <h6><INPUT TYPE=FILE Name=fileName align="right" title="Choose a Cover Photo" required></h6>
                        <br>
                    </td>
                </tr>
                <tr>
                    <td align="center">
                        <br>
                        <input type="submit" value="Submit">
                        <br>
                        <br>    
                    </td>
                </tr>
            </table>
            <br>
        </form>
    </body>
</html>
