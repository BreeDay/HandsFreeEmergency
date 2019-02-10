var scrolled = false;

window.onscroll = function() {
  scrolled = true;
}

setInterval(function(){
  if (scrolled) {
    scrolled = false;

    if ( window.pageYOffset > 99 ) {
        //document.getElementsByClassName("navbar-brand")[0].style.display = "inline";
        document.getElementById("brand").classList.add("navbar-brand-show");
    } else {
        document.getElementById("brand").classList.remove("navbar-brand-show");
    }
  }
}, 150);