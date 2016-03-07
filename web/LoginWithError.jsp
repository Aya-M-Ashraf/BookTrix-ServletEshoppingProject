<%-- 
    Document   : Login
    Created on : Mar 3, 2016, 6:30:14 PM
    Author     : Ahmed Ashraf
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
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
        <script>
       
              
        </script>
    </head>

    <body  >  

        <!--Srart section tool box   settings to choose color-->
        <section class="option-box">
            <div class="color-option">
                <h4>color-option</h4>
                <ul class="list-unstyled">
                    <li data-value="Resources/css/defult-theme.css"></li>
                    <li data-value="Resources/css/perple-theme.css"></li>
                    <li data-value="Resources/css/blue-theme.css"></li>
                    <li data-value="Resources/css/yellow-theme.css"></li>
                </ul>
            </div>
            <i class="fa fa-gear fa-3x gear-check"></i>
        </section>
        <!--end section tool box-->


        <!--menu form-->
        <nav class=" navbar navbar-inverse  navbar-fixed-top" role="navigation">
            <div class="container">
                <!-- Brand and toggle get grouped for better mobile display -->
                <div class="navbar-header">
                    <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#ournavbar">
                        <span class="sr-only">Toggle navigation</span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                    </button>
                    <a class="navbar-brand hvr-pop" href="index.html">BookStore <span></span></a>
                </div>
                <!-- Collect the nav links, forms, and other content for toggling -->
                <div class="collapse navbar-collapse" id="ournavbar">

                    <ul class="nav navbar-nav navbar-right">
                        <li class="active"><a href="login.html">Login<span class="sr-only"></span></a></li>
                        <li ><a href="signUp.html">Sign UP</a></li>

                        <li ><a href="index.html">Home</a></li>
                        <li><a href="index.html">About</a></li>
                        <li>
                            <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false">Categories<span class="caret"></span></a>
                            <ul class="dropdown-menu" role="menu">
                                <li><a href="index.html">Science</a></li>
                                <li><a href="index.html">Computer Science</a></li>
                                <li><a href="index.html">Space</a></li>
                            </ul>
                        </li>
                        <li class="dropdown">
                            <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false">Cart <span class="caret"></span></a>
                            <ul class="dropdown-menu" role="menu">
                                <li><a href="index.html">Show</a></li>
                                <li><a href="index.html">Edit</a></li>
                            </ul>
                        </li>
                    </ul>
                    <form class="navbar-form navbar-left" role="search">
                        <div class="form-group">
                            <input type="text" class="form-control" placeholder="Search">
                        </div>
                        <button type="submit" class="btn btn-default" >Submit</button>
                    </form>

                </div><!-- /.navbar-collapse -->
            </div><!-- /.container-fluid -->
        </nav>
        <div class="container">
            <br>
            <br>
            <br>

            <section id="content">
               <form  method="POST">
			<h1>Login Form</h1>
			<div>
				<input type="text" placeholder="Username" required="" id="userName"  name="userName"/>
			</div>
			<div>
				<input type="password" placeholder="Password" required="" id="password"  name="password"/>
			</div>
             <div>
				<input type="submit" value="Log in" />
			    <div><input id="remember" name="remember" type="checkbox" value="rememberme"><span style="color:#FF1F1F">Remember me </span> </div>
				<a href="http://localhost:8084/ajaxtask/Registration">Register</a>
			 </div>


		</form><!-- form -->

                <div id="login_result"><font color="red"><b>login faild</div>

            </section>
        </div>
               <!--end Section Ultimate Footer-->

        <!--start The Loading Screen-->


        <section class="loading-overlay">

            <div class="spinner">
                <div class="double-bounce1"></div>
                <div class="double-bounce2"></div>
            </div>


        </section>


        <!--end The Loading Screen-->








        <!--start scroll to top-->


        <div id="scroll-top">
            <i class="fa fa-chevron-up fa-3x"></i>
        </div>

        <!--end scroll to top-->
        <!--                    <script src="js/jquery-1.11.1.js"></script>-->
        <script src="Resources/js/bootstrap.min.js"></script>
        <script src="Resources/js/plugin.js"></script>
        <script src="Resources/js/wow.min.js"></script>

        <script>
        new WOW().init();
        </script>
    </body>
</html>

