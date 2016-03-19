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
                }
            }
        </script>
    </head>
    <body>
        <div class="container">

            <section id="content">
                <form>
                    <h1>Add Category</h1>
                    <div class="row">
                        <div>Category Name:<br><input type="text" placeholder="category name" required="" id="categoryName"/></div>
                        <div id="btn"><input type="button" value="Add" onclick="add()"/></div><br> <div id="msg" style="font-size: small"></div>
                    </div>
                </form>
            </section>
        </div>
    </body>
</html>
