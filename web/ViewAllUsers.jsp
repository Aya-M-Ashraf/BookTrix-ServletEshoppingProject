<%-- 
    Document   : ViewAllUsers
    Created on : Mar 11, 2016, 6:17:33 PM
    Author     : Ahmed
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.io.*,java.util.*,java.sql.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<script type="text/javascript" src="http://code.jquery.com/jquery-1.11.1.min.js"></script>  

<style>
    .myuser {
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
    .myuser:hover {
        background:-webkit-gradient(linear, left top, left bottom, color-stop(0.05, #dfdfdf), color-stop(1, #ededed));
        background:-moz-linear-gradient(top, #dfdfdf 5%, #ededed 100%);
        background:-webkit-linear-gradient(top, #dfdfdf 5%, #ededed 100%);
        background:-o-linear-gradient(top, #dfdfdf 5%, #ededed 100%);
        background:-ms-linear-gradient(top, #dfdfdf 5%, #ededed 100%);
        background:linear-gradient(to bottom, #dfdfdf 5%, #ededed 100%);
        filter:progid:DXImageTransform.Microsoft.gradient(startColorstr='#dfdfdf', endColorstr='#ededed',GradientType=0);
        background-color:#dfdfdf;
    }
    .myuser:active {
        position:relative;
        top:1px;
    }
</style>

<script>
    $(document).ready(function () {
        $.post("ViewUserProfile");
        $(".myuser").click(function (event) {
            $("#allbooks").load("ViewUserProfile?email=" + event.target.id);
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
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <br><br><br><br>
        <table align="center">
            <c:forEach items="${sessionScope.allusers}" var="myUser" varStatus="stat">

                <fmt:parseNumber var="i" type="number" value="${stat.index}" ></fmt:parseNumber>            
                <c:url var="myUrl" value="/Resources/users_pics/${myUser.profilePicUrl}"  context="/BookTrix"/>
                <tr>
                    <td  class="myuser" style="width: 700px" id="${myUser.email}" > 
                        <table id="${myUser.email}">
                            <tr  id="${myUser.email}">
                                <td id="${myUser.email}">
                                    <img src="${myUrl}" style="width:80px; height:80px;"  id="${myUser.email}"> 
                                    <h3 id="${myUser.email}">Name  : ${myUser.userName}</h3>
                                </td>
                                <td style="width:100px;" id="${myUser.email}">
                                </td>
                                <td  id="${myUser.email}" >
                                    <h3 id="${myUser.email}">Email  : ${myUser.email}</h3>
                                    <h3 id="${myUser.email}">Credit Limit  : ${myUser.creditLimit}</h3>
                                    <h3 id="${myUser.email}">Address  : ${myUser.address}</h3>
                                </td>
                            </tr> 
                        </table>                      
                    </td>
                </tr>
            </c:forEach>
        </table>
    </body>
</html>
