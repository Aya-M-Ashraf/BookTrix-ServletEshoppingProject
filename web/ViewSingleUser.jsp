<%-- 
    Document   : ViewSingleUser
    Created on : Mar 11, 2016, 10:03:33 PM
    Author     : Ahmed
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
        <!--        <style>
                    table {
                        width: 100%;
                    }
                    td {
                        border-bottom: 1px solid #ddd;
                        border-top: 1px solid #ddd;
                        height: 50px;
                        width: 200px;
                    }
                    tr:hover {background-color: #f5f5f5}
                </style>-->
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
    </head>
    <body align="center" onload="">

        <img src="Resources/users_pics/${sessionScope.user.profilePicUrl}" style="display: inline-block; width: 12%;height: 5%;">

        <div class="container">

            <section id="content">
                <form action="EditUser" method="POST" ENCTYPE="MULTIPART/FORM-DATA" >
                    <h1>User Profile</h1>
                    <div class="row">
                        <input type="hidden" value="${sessionScope.user.email}" name="email">
                        <input type="hidden" value="${sessionScope.user.userName}" name="userName">

                        <div>Email:<br><input type="text" placeholder="Email" id="email"  name="email" disabled value="${sessionScope.user.email}"/></div>
                        <div>Name:<br><input type="text" placeholder="user name" id="userName"  name="userName" disabled value="${sessionScope.user.userName}"/></div>
                        <div>Credit Limit<br><input type="number" placeholder="credit limit" required="" id="creditLimit"  name="creditLimit" value="${sessionScope.user.creditLimit}"/></div>
                        <div>Job:<br><input type="text" placeholder="job" required="" id="job"  name="job" value="${sessionScope.user.job}"/></div>
                        <div>Address<br><input type="text" placeholder="address" id="address"  name="address" value="${sessionScope.user.address}"/></div>
                        <div>Password:<br><input type="password" placeholder="Password" required="" id="password"  name="password" value="${sessionScope.user.password}"/></div>
                        <div>Confirm Password:<br><input type="password" placeholder="Retype Password" required="" id="password2" value="${sessionScope.user.password}" name="password2" onblur="checkPassword()"/></div>
                        <div align = "center"> <h5> Choose a Personal Photo</h5><input type="file" Name=fileName align="right" value="${sessionScope.user.profilePicUrl}"/></div>
                        <div><input type="submit" value="Save Changes"/></div> <span id="usernamemsg" style="color: red"></span>
                    </div>
                </form>
                <c:if test = "${done=='1'}">
                    <div id="doneMsg"><font color="green"><b>Done Successfully</b></font></div>
                    </c:if>
            </section>
        </div>
    </body>
</html>
