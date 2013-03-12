$(document).ready(function(){
  // Add active to navbar li according to path
  var path = new RegExp('http:\/\/[^\/]*\/(.*)').exec(window.location);
  $('.navbar a[href$='+path[1]+']').parent().addClass('active');
});