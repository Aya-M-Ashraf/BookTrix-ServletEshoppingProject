
<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.io.*,java.util.*,java.sql.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html lang="en">
    <head>
        <meta charset="UTF-8" />
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta http-equiv="content-type" content="text/html; charset=utf-8" />

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
            var signUpReq = null;

            if (window.XMLHttpRequest) {
                signUpReq = new XMLHttpRequest();
            }
            else if (window.ActiveXObject) {
                signUpReq = new ActiveXObject(Microsoft.XMLHTTP);
            }

            function checkUserName() {
                signUpReq.onreadystatechange = handleNameVerfication;
                var name = document.getElementById("userName").value;
                signUpReq.open("GET", "SignUp?type=userName&userName=" + name, true);
                signUpReq.send();
            }

            function handleNameVerfication() {
                if (signUpReq.readyState === 4 && signUpReq.status === 200) {
                    if (signUpReq.responseText === "valid") {

                    } else {

                    }
                }
            }

            function checkPassword() { //to check the pervious input field equality

            }

        </script>

    </head>

    <body  > 
        <jsp:include page="htmls/StartOfThePage.html"></jsp:include>

            <div class="container">

                <section id="content">
                    <form action="SignUp" method="POST" ENCTYPE="MULTIPART/FORM-DATA"  method="POST">
                        <h1>Sign UP</h1>
                        <div class="row">
                            <div><input type="text" placeholder="Email" required="" id="email"  name="email" onblur="checkEmail()"/></div>
                            <div><input type="text" placeholder="user name" required="" id="userName"  name="userName" onblur="checkUserName()"/></div>
                            <div><input type="number" placeholder="credit limit" required="" id="creditLimit"  name="creditLimit" /></div>
                            <div><input type="text" placeholder="job" required="" id="job"  name="job" /></div>
                            <div><input type="text" placeholder="address" id="address"  name="address"/></div>
                            <div><input type="password" placeholder="Password" required="" id="password"  name="password"/></div>
                            <div><input type="password" placeholder="Retype Password" required="" id="password2"  name="password2" onblur="checkPassword()"/></div>
                            <div align = "center"> <h5> Choose a Personal Photo</h5><input type="file" Name=fileName align="right"  required/></div>

                            <div><input type="submit" value="Register" /></div>
                        </div>
                    </form>
                <c:if test = "${error=='1'}">
                    <div><font color="red"><b>login failed</div>
                    </c:if>
            </section>
        </div>

        <jsp:include page="htmls/RestOfThePage.html"></jsp:include>   
    </body>
</html>

