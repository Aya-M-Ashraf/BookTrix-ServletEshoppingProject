
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.io.*,java.util.*,java.sql.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
      
    </head>
    <c:if test="${userName==null}">
        <c:redirect url="jsps/Login.jsp"></c:redirect>
    </c:if>

    <c:if test="${role=='user'}">
        <c:redirect url="UserHome.jsp"></c:redirect>
    </c:if>
    <body>
       
    </body>
</html>
