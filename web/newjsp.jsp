<!--el user el 2deem-->
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.io.*,java.util.*,java.sql.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
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

        <style>
            body { font-family: sans-serif; font-size: 12pt; color: #444; line-height: 1.5em; } 
            h1 { font-size: 1.5em; } 
            #wrapper { max-width: 800px; margin: 0 auto; text-align: center; } 
        </style>



        <script>

            function allowDrop(ev) {
                ev.preventDefault();
            }
            var bookDivId;
            function drag(ev) {
                ev.dataTransfer.setData("text", ev.target.id);
                bookDivId = ev.target.id;
            }

            function drop(ev) {

                addToMyCart(bookDivId);
            }


            function addToMyCart(bookId) {
                $.post("Cart",
                        {
                            "userName": '${userName}',
                            "bookId": bookId,
                            "Quantity": "1"
                        }
                , ajaxCallBack);
            }
            ///// to be continued

            /////// to be continued 
            function ajaxCallBack(responseTxt, statusTxt, xhr) {
                if (statusTxt === "success") {
                    alert(responseTxt);
                }
            }
        </script>

        <script>

            function showAddBook() {
                
                $("#addbook").show();
            }
            function showMyProfile() {
                
                $("#myProfile").show();
                $("#myslide").hide();
            }
            function showMyCart() {//coming from DB 
                $("#allbooks").hide();

                $.ajax({
                    url: "Cart",
                    type: 'Get',
                    async: false,
                    data: {
                        "userName": '${userName}'
                    }, success: function (data, textStatus, jqXHR) {
                        $("#resViewCart").load("ViewCart.jsp");
                        //    $("#resViewCart").html("<h1>hiii</h1>");
                        //    alert("asdad");

                    }
                }
                );

            }
            function showCart() {
                $("#mycart").show(1000);
            }
            function hideCart() {
                $("#mycart").hide(1000);
            }
            function initBooks() {
                $.ajax({
                    url: "ViewBooks",
                    type: 'Post',
                    async: false,
                    data: {},
                    success: function (data, textStatus, jqXHR) {
                        $("#allbooks").load("ViewBooks.jsp");
                    }
                });
            }

        </script>



    </head>

    <c:if test="${userName==null}">
        <c:redirect url="Login.jsp"></c:redirect>
    </c:if>

    <c:if test="${role=='admin'}">
        <c:redirect url="AdminHome.jsp"></c:redirect>
    </c:if>

    <body onload="initBooks()">
<!--    <center><h3> welcome ${userName} </h3></center> -->

        <jsp:include page="htmls/StartOfUserPage.html"></jsp:include>

            <section  class="about text-center wow bounceIn"  data-wow-duration="0.5s" data-wow-offset="300" >
                <div class="container" style="margin-bottom: 95px;">
                    <div  id="allbooks" style="float: left;" onmouseover ="hideCart()"> 
                    <jsp:include page="ViewBooks.jsp"></jsp:include>
                    </div>

                </div>

                <div class="bookshelf" style="float: right;" onclick="showCart()" >
                    <img id="shopping-cart" onclick="showMyCart()" onmouseover="showCart()" src="Resources/pics/cart.png" ondragover="allowDrop(event)" ondrop="drop(event)" style="width:90px;height:90px;" /> <br>
                    <p class="navbar-brand hvr-pop">Recently added items</p><br>
                    <div    ondragover="allowDrop(event)" ondrop="drop(event)" style="padding-top:190px; width: 220px;height: 780px; background-color: blanchedalmond; float: right; border-radius: 25px; background-image: url('Resources/pics/cart.jpg');background-repeat: no-repeat;"></div>
                </div>
            </section>

            <div id="myProfile"  style="display: none;">
                <h1><span>Your Profile</span></h1>
            <jsp:include page="ViewSingleUser.jsp"></jsp:include>
            </div>
            <div id="resViewCart">
                <h1>

                </h1>
            </div>


        <jsp:include page="htmls/RestOfMainPage.html"></jsp:include> 

    </body>
</html>











