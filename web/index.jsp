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
            $(function () {

                var foo = $('.gallery');
                foo.poptrox({
                    usePopupCaption: true
                });

                var preview = $('.preview');
                preview.poptrox({
                    usePopupCaption: true
                });
            });
        </script>
        <script>
            var hashmap = {};
            var shelfHashmapVar = {};

            var html5rocks = {};
            html5rocks.webdb = {};

            html5rocks.webdb.db = null;

            html5rocks.webdb.open = function () {
                var dbSize = 5 * 1024 * 1024; // 5MB
                html5rocks.webdb.db = openDatabase("Todo", "1", "Todo manager", dbSize);
            };

            html5rocks.webdb.onError = function (tx, e) {
                //            alert("There has been an error: " + e.message);
            };

            html5rocks.webdb.onSuccess = function (tx, r) {

            };
            html5rocks.webdb.createTable = function () {
                var db = html5rocks.webdb.db;
                db.transaction(function (tx) {
                    tx.executeSql("CREATE TABLE IF NOT EXISTS " +
                            "cart(bookid INTEGER PRIMARY KEY,divText TEXT)", []);
                });
            };
            html5rocks.webdb.addTodo = function (divId) {
                divsHashmap();
                var db = html5rocks.webdb.db;
                db.transaction(function (tx) {
                    var text = hashmap[bookDivId];
                    tx.executeSql("INSERT INTO cart(bookid,divText) VALUES (?,?)",
                            [bookDivId, text],
                            html5rocks.webdb.onSuccess,
                            html5rocks.webdb.onError);
                });
            };

            function allowDrop(ev) {
                ev.preventDefault();
            }
            var bookDivId;
            function drag(ev) {
                ev.dataTransfer.setData("text", ev.target.id);
                bookDivId = ev.target.id;
            }

            function drop(ev) {
                ev.preventDefault();
                var data = ev.dataTransfer.getData("text");
                html5rocks.webdb.addTodo(bookDivId);
                html5rocks.webdb.getAllTodoItems(loadTodoItems);
            }

            function showAllCart() {
                $("#viewcart").show();
                $("#allbooks").hide();
                $("#techbooks").hide();
                $("#fashionbooks").hide();
                html5rocks.webdb.getAllTodoItems(loadAllTodoItems);
            }
            function loadAllTodoItems(tx, rs) {
                var rowOutput = "";
                var bookcost = 0;
                var totalCost = 0;
                var todoItems = document.getElementById("allcart");
                rowOutput = "<table> <tr>"
                for (var i = 0; i < rs.rows.length; i++) {
                    rowOutput += "<td>" + renderTodo(rs.rows.item(i)) + "</td>";
                    if (i % 5 === 0 && i !== 0) {
                        rowOutput += "<tr>";
                    }
                }
                for (var i = 0; i < rs.rows.length; i++) {
                    bookcost = document.getElementById(rs.rows.item(i).bookid).getAttribute('cost');
                    totalCost = totalCost + parseInt(bookcost);
                }
                var costIs = "  <h4 class='text-danger'>Total Cost = " + totalCost + "$</h4>";

                rowOutput += " </tr> </table> <br><br> " + costIs + "<input  type=submit value='Buy Now' >";
                todoItems.innerHTML = rowOutput;
            }

            html5rocks.webdb.getAllTodoItems = function (renderFunc) {
                var db = html5rocks.webdb.db;
                db.transaction(function (tx) {
                    tx.executeSql("SELECT * FROM cart", [], renderFunc,
                            html5rocks.webdb.onError);
                });
            };
            function loadTodoItems(tx, rs) {
                var rowOutput = "";
                var bookcost = 0;
                var totalCost = 0;

                var todoItems = document.getElementById("mycart");

                for (var i = 0; i < rs.rows.length; i++) {
                    bookcost = document.getElementById(rs.rows.item(i).bookid).getAttribute('cost');
                    totalCost = totalCost + parseInt(bookcost);
                }
                var costIs = "  <h4 class='text-danger'>Total Cost = " + totalCost + "$</h4>";
                if (totalCost != 0)
                    rowOutput += costIs;
                for (var i = rs.rows.length - 1; i > -1; i--) {
                    rowOutput += renderTodo(rs.rows.item(i));
                    if ((rs.rows.length - i + 1) > 3) {
                        break;
                    }
                }

                todoItems.innerHTML = rowOutput;
            }
            function renderTodo(row) {
                var bookcost = document.getElementById(row.bookid).getAttribute('cost');
                return row.divText +
                        "cost = " + bookcost + "<br> [<a href='javascript:void(0);' onclick=\'html5rocks.webdb.deleteTodo(" + row.bookid + ");\'>Delete</a>]<br>";
            }
            html5rocks.webdb.deleteTodo = function (id) {
                var db = html5rocks.webdb.db;
                db.transaction(function (tx) {
                    var x = document.getElementById(id).getAttribute('cost');
                    tx.executeSql("DELETE FROM cart WHERE bookid =?", [id],
                            html5rocks.webdb.onSuccess,
                            html5rocks.webdb.onError);
                });
                html5rocks.webdb.getAllTodoItems(loadTodoItems);
                html5rocks.webdb.getAllTodoItems(loadAllTodoItems);

            };

            function divsHashmap() {

                hashmap[1] = '<div id = "1" cost =520 class="gallery" > <div class="sample thumb1"></div> </a></div>';
                hashmap[2] = '<div id = "2" cost =350 class="gallery" > <div class="sample thumb2"></div> </a></div>';
                hashmap[3] = '<div id = "3" cost =500 class="gallery" > <div class="sample thumb3"></div> </a></div>';
                hashmap[4] = '<div id = "4" cost =100 class="gallery" > <div class="sample thumb4"></div> </a></div>';
                hashmap[5] = '<div id = "5" cost =400 class="gallery"> <div class="sample thumb5"></div> </a></div>';
                hashmap[6] = '<div id = "6" cost =460 class="gallery"> <div class="sample thumb6"></div> </a></div>';
                hashmap[7] = '<div id = "7" cost =900 class="gallery"> <div class="sample thumb7"></div> </a></div>';
                hashmap[8] = '<div id = "8" cost =480 class="gallery"> <div class="sample thumb8"></div> </a></div>';
                hashmap[9] = '<div id = "9" cost =280 class="gallery"> <div class="sample thumb9"></div> </a></div>';
                hashmap[10] = '<div id = "10" cost =520 class="gallery" > <div class="sample thumb10"></div> </a></div>';
                hashmap[11] = '<div id = "11" cost =350 class="gallery" > <div class="sample thumb11"></div> </a></div>';
                hashmap[12] = '<div id = "12" cost =500 class="gallery" > <div class="sample thumb12"></div> </a></div>';
                hashmap[13] = '<div id = "13" cost =100 class="gallery" > <div class="sample thumb13"></div> </a></div>';
                hashmap[14] = '<div id = "14" cost =400 class="gallery"> <div class="sample thumb14"></div> </a></div>';
                hashmap[15] = '<div id = "15" cost =460 class="gallery"> <div class="sample thumb15"></div> </a></div>';
                hashmap[16] = '<div id = "16" cost =900 class="gallery"> <div class="sample thumb16"></div> </a></div>';
                hashmap[17] = '<div id = "17" cost =480 class="gallery"> <div class="sample thumb17"></div> </a></div>';
                hashmap[18] = '<div id = "18" cost =280 class="gallery"> <div class="sample thumb18"></div> </a></div>';
                hashmap[19] = '<div id = "19" cost =480 class="gallery"> <div class="sample thumb19"></div> </a></div>';
                hashmap[20] = '<div id = "20" cost =280 class="gallery"> <div class="sample thumb20"></div> </a></div>';


            }

            function init() {
                html5rocks.webdb.open();
                html5rocks.webdb.createTable();
                html5rocks.webdb.getAllTodoItems(loadTodoItems);

            }

            $(document).ready(function () {
                 $.ajax({
                    url: "ViewBooks",
                    type: 'Post',
                    async: false,
                    data: {}
                });

                $('#shopping-cart').tooltipster({
                    content: $('<span id="tooltip">Click Here To Show Your Cart</span>')
                });

                $('#tooltip3').tooltipster({
                    content: $('<span><img src="Resources/audio/dracula.jpg"/><br> <strong>Dracula<br>Auther: Bram Stoker</strong></span>')
                });
                $('#tooltip4').tooltipster({
                    content: $('<span><img src="Resources/audio/broken.jpg"/><br> <strong>The Broken Circle<br>Auther: Cheryl Potter</strong></span>')
                });
                $('#tooltip5').tooltipster({
                    content: $('<span><img src="Resources/audio/living.jpg"/><br> <strong>Living On Air<br>Auther: Joe Cipriano</strong></span>')
                });
            });
        </script>

        <script>
            function hideallfun() {
                $("#allbooks").hide();
                $("#techbooks").hide();
                $("#fashionbooks").hide();
                $("#childrenbooks").hide();
                $("#audiobooks").hide();
                $("#viewcart").hide();
                $("#addbook").hide();

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
            function showAddBook() {
                hideallfun();
                alert("asdasd");
                $("#addbook").show();
            }
            function showCart() {
                $("#mycart").show(1000);
            }
            function hideCart() {
                $("#mycart").hide(1000);
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

    <body onload="init()">  

        <jsp:include page="htmls/StartOfMainPage.html"></jsp:include>

            <section  class="about text-center wow bounceIn"  data-wow-duration="0.5s" data-wow-offset="300" >
                <div class="container" style="margin-bottom: 95px;">

                    <div  id="allbooks"  onmouseover ="hideCart()">

                    <jsp:include page="ViewBooks.jsp"></jsp:include>
                    </div>

                    <div class="bookshelf" id="viewcart"  style="display: none" onmouseover ="hideCart()">
                        <h1><span>Your Cart</span></h1>
                        <P class="lead">These  are  all The Books  you Added to the Cart</P>
                        <div class="shelf" id="allcart" ></div>
                        <br><br><br><br><br><br><br><br><br><br>
                    </div>                  

                    <div class="bookshelf" style="float: right;" onclick="showCart()" >
                        <img id="shopping-cart" onclick="showAllCart()" onmouseover="showCart()" src="Resources/pics/cart.png" ondragover="allowDrop(event)" ondrop="drop(event)" style="width:90px;height:90px;" /> <br>
                        <p class="navbar-brand hvr-pop">Recently added items</p><br>
                        <div  id="mycart"  ondragover="allowDrop(event)" ondrop="drop(event)" style="padding-top:190px; display: none; width: 220px;height: 780px; background-color: blanchedalmond; float: right; border-radius: 25px; background-image: url('Resources/pics/cart.jpg');background-repeat: no-repeat;">
                        </div>
                    </div>

                </div>
            </section>
            <div id="addbook" style="display: none" onmouseover ="hideCart()">
            <jsp:include page="AddBookForm.jsp"></jsp:include>
            </div>

        <jsp:include page="htmls/RestOfMainPage.html"></jsp:include>    
    </body>
</html>
