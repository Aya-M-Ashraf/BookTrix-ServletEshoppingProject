<%-- 
    Document   : AddCategory
    Created on : Mar 18, 2016, 12:38:13 PM
    Author     : lenovo
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
            var addCatgReq = null;

            if (window.XMLHttpRequest) {
                addCatgReq = new XMLHttpRequest();
            }
            else if (window.ActiveXObject) {
                addCatgReq = new ActiveXObject(Microsoft.XMLHTTP);
            }
            function add() {
                addCatgReq.onreadystatechange = handleAdding;
                var name = document.getElementById("categoryName").value;
                addCatgReq.open("GET", "AddProduct?categoryName=" + name, true);
                addCatgReq.send();
            }
            function handleAdding() {
                if (addCatgReq.readyState === 4 && addCatgReq.status === 200) {
                    $("#msg").text(addCatgReq.responseText);
                    $.ajax({
                        url: "GetCategories",
                        type: 'Post',
                        async: false,
                        data: {},
                        success: function (data, textStatus, jqXHR) {
                            $("#categoriesList").load("categoriesList.jsp");
                        }
                    });
                }
            }
        </script>
    </head>
    <c:if test="${userName==null}">
        <c:redirect url="Login.jsp"></c:redirect>
    </c:if>

    <c:if test="${user.role=='user'}">
        <c:redirect url="UserHome.jsp"></c:redirect>
    </c:if>
    <body>   
        <br><br><br>
        <h1>Add Category</h1><br>
        <div class="row">
            <br>
            <div>Category Name:<br><br>
                <input type="text" placeholder="category name" required="" id="categoryName"/>
            </div>    
            <br><br>
            <div id="btn">
                <input type="button" value="Add" onclick="add()"/>
            </div>
            <br><br> 
            <div id="msg" style="font-size: small"></div>
        </div>

    </body>
</html>
