<!DOCTYPE html>

<script type="text/javascript" src="http://code.jquery.com/jquery-1.11.1.min.js"></script>  
<script>
    $(document).ready(function () {
        $.ajax({
            url: "GetCategories",
            type: 'Post',
            async: false,
            data: {},
            success: function (data, textStatus, jqXHR) {
                $("#categoriesList").load("categoriesList.jsp");
            }
        });


        $("#searchText").keyup(function () {
            $.ajax({
                url: "ViewSearchBooks",
                type: 'Post',
                async: false,
                data: {
                    "searchString": $('#searchText').val()
                },
                success: function (data) {
                    $('#searchResult').load("ViewSearchBooks.jsp");
                }
            });

        });
        $("#searchText").focusin(function () {
            $("#searchResult").fadeIn(1000);
        });
        $("#searchText").focusout(function () {
            $("#myslide").fadeIn(1000);
            $("#searchResult").fadeOut(1000);

        });
        $("#searchResult").mouseover(function () {
            $("#searchResult").show();
        });
        //view all books in my cart
        $("#myCart").click(function () {
            $("#home").attr("class", "");
            $("#about").attr("class", "");
            $("#myCart").attr("class", "active");
            $("#myProfile").attr("class", "");
            $("#logout").attr("class", "");


//            alert("click");
            $.ajax({
                url: "Cart",
                type: 'Get',
                async: false,
                data: {
                    "userName": '${user.userName}'
                }, success: function (data, textStatus, jqXHR) {
                    $("#allbooks").load("ViewCart.jsp");

                }
            }
            );
        });

        $("#myProfile").click(function () {
            $("#home").attr("class", "");
            $("#about").attr("class", "");
            $("#myCart").attr("class", "");
            $("#myProfile").attr("class", "active");
            $("#logout").attr("class", "");
            $("#allbooks").load("ViewUserProfile?email=${user.email}" );
        });

        $("#about").click(function () {
            $("#home").attr("class", "");
            $("#about").attr("class", "active");
            $("#myCart").attr("class", "");
            $("#myProfile").attr("class", "");
            $("#logout").attr("class", "");

        });
        $("#logout").click(function () {
            $("#home").attr("class", "");
            $("#about").attr("class", "");
            $("#myCart").attr("class", "");
            $("#myProfile").attr("class", "");
            $("#logout").attr("class", "active");
            $.ajax({
                url: "Logout",
                type: 'Get',
                async: false,
                data: {}
            });
        });
        $("#home").click(function () {
            $("#home").attr("class", "active");
            $("#about").attr("class", "");
            $("#myCart").attr("class", "");
            $("#myProfile").attr("class", "");
            $("#logout").attr("class", "");

            $.ajax({
                url: "ViewBooks",
                type: 'Post',
                async: false,
                data: {},
                success: function (data, textStatus, jqXHR) {
                    $("#allbooks").load("ViewBooksUser.jsp");
                }
            });
        });
    });


</script>
<html>
</head>
<body>
    <!-- start section tool box   settings to choose color-->
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
    <nav class=" navbar navbar-inverse  navbar-fixed-top" role="navigation" >
        <div class="container">

            <!-- Brand and toggle get grouped for better mobile display -->
            <div class="navbar-header">
                <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#ournavbar">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a class="navbar-brand hvr-pop" href="#">${user.userName}  :<span id="creditOfUser">${user.creditLimit} $</span></a>

            </div>
            <div class="collapse navbar-collapse" id="ournavbar">

                <ul class="nav navbar-nav navbar-right">
                    <li id="myProfile"><a href="#allbooks">My Profile</a></li>
                    <li id="myCart"><a href="#allbooks" >My Cart</a></li>
                    <li id="home"class="active"><a href="#allbooks">Home<span class="sr-only">(current)</span></a></li>
                    <li id="about"><a href="#aboutdiv">About</a></li>
                    <li>
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false">Categories<span class="caret"></span></a>
                        <ul class="dropdown-menu" role="menu" id="categoriesList">
                        </ul>
                    </li>
                    <li id="logout"><a href="Login.jsp">Logout</a></li>
                </ul>
                <form class="navbar-form navbar-left" role="search">
                    <div class="form-group">
                        <input type="text" class="form-control" placeholder="Search" id="searchText" >
                    </div>
                    <!--<button type="submit" class="btn btn-default">Submit</button>-->
                </form>

            </div><!-- /.navbar-collapse -->
        </div><!-- /.container-fluid -->
    </nav>


    <div id="searchResult" class="navbar-fixed-top">

    </div>
    <!---start slideshow-->
    <div id="myslide" class="carousel slide" data-ride="carousel">
        <!-- Indicators de el -->
        <ol class="carousel-indicators">
            <li data-target="#myslide" data-slide-to="0" class="active"></li>
            <li data-target="#myslide" data-slide-to="1"></li>
            <li data-target="#myslide" data-slide-to="2"></li>
            <li data-target="#myslide" data-slide-to="3"></li>
        </ol>
        <!-- Wrapper for slides -->  
        <div class="carousel-inner">
            <div class="item active" >
                <img src="Resources/pics/11.jpg" alt="PIC 1">
                <div class="carousel-caption">...</div>
            </div>

            <div class="item">
                <img src="Resources/pics/22.jpg" alt="PIC 2">
                <div class="carousel-caption"></div>
            </div>

            <div class="item">
                <img src="Resources/pics/33.jpg" alt="PIC 3">
                <div class="carousel-caption"> </div>
            </div>

            <div class="item">
                <img src="Resources/pics/22.jpg" alt="PIC 4" >
                <div class="carousel-caption">...</div>
            </div>
        </div>

        <!-- Controls -->
        <a class="left carousel-control" href="#myslide" role="button" data-slide="prev">
            <span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
            <span class="sr-only">Previous</span>
        </a>

        <a class="right carousel-control" href="#myslide " role="button" data-slide="next">
            <span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
            <span class="sr-only">Next</span>
        </a>
    </div>

</body>
</html>
