<!DOCTYPE html>
<html lang="en">
    <head>
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


//             $(document).ready(function () {
//                alert("sadsd");
//                
//              $.ajax({
//                    url: "ViewBooks",
//                    type: 'Post',
//                    async: false,
//                    data: {},
//                    success: function (data, textStatus, jqXHR) {
//                        alert("donne");
//                    }
//                });
//            });

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

        <script>
            function hideallfun() {
                $("#allbooks").hide();
                $("#techbooks").hide();
                $("#fashionbooks").hide();
                $("#childrenbooks").hide();
                $("#audiobooks").hide();


            }
            function allbooksfun() {
                hideallfun();
                $("#allbooks").show();
            }
            function techbooksfun() {
                hideallfun();
                $("#techbooks").show();
            }
            function fashionbooksfun() {
                hideallfun();
                $("#fashionbooks").show();
            }
            function childrenbooksfun() {
                hideallfun();
                $("#childrenbooks").show();
            }
            function audiobooksfun() {
                hideallfun();
                $("#audiobooks").show();
            }


        </script>
        <script>
            function myMusic() {
                if (document.getElementById('playMusic').paused) {
                    document.getElementById('playMusic').play();
                    document.getElementById('music-button').innerHTML = "<img src=\"Resources/pics/images.jpg\"  height=\"35\" width=\"35\"/>";
                }
                else {
                    document.getElementById('playMusic').pause();
                    document.getElementById('music-button').innerHTML = "<img src=\"Resources/pics/Picture1.png\"  height=\"35\" width=\"35\"/>";
                }
            }
        </script>
    </head>

    <body onload="initBooks();" >  

        <jsp:include page="htmls/StartOfMainPage.html"></jsp:include>

            <section  class="about text-center wow bounceIn"  data-wow-duration="0.5s" data-wow-offset="300" >
                <div class="container" style="margin-bottom: 95px;">

                    <div  id="allbooks">

                    <jsp:include page="ViewBooks.jsp"></jsp:include>
                    </div>

                </div>
            </section>
        <jsp:include page="htmls/RestOfMainPage.html"></jsp:include>    
    </body>
</html>
