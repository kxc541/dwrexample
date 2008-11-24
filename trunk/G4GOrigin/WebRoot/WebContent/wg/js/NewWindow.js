$(document).ready(function() {
    $("a[@rel='external']").addClass("external")
       .click(function() { window.open($(this).href); return false; });
});