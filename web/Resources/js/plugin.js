/*global $, jQuery, alert*/
$(document).ready(function () {
 $('.carousel').carousel({
   interval:5000
 });

//Show color option div when click on the gear
$(".gear-check").click(function () {

	$(".color-option").fadeToggle ();
});




//show the color


var colorLi =$(".color-option ul li");

colorLi
.eq(0).css("backgroundColor", "#E41B17").end()
.eq(1).css("backgroundColor"," #E426D5").end()
.eq(2).css("backgroundColor", "#009AFF").end()
.eq(3).css("backgroundColor", "#FFD400");


colorLi.click(function (){


   $("link[href*='theme']").attr("href",$(this).attr("data-value"));

});

//caching the scroll top element

var scrollButton = $("#scroll-top");

$(window).scroll(function () {

   if($(this).scrollTop ()>=700)
    {
       scrollButton.show ();

   }
   else
   {

   	scrollButton.hide();
   }



});

scrollButton.click(function () {
  $("html,body").animate({scrollTop: 0},600);
});
});

//loading screen

$(window).load(function () {



//loading element
$(".loading-overlay .spinner").fadeOut(2000, function () {

  $("body").css("overflow","auto");

$(this).parent ().fadeOut(2000, function () {

			//show the scroll
        $(this).remove();
      });

    });

});
