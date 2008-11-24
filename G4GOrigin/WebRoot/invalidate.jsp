<form name="redirect">
<center>
<font face="Arial"><b>You were automatically signed-out of WorldGaming due to a
navigation error.  You will be redirected to the home page shortly where you
will be able to log in again.  Please accept our apologies for this
inconvenience.<br><br>
<input type="text" size="3" name="redirect2">
seconds</b></font>
</center>
</form>
<script>
<!--
//change below target URL to your own
var targetURL="displayLogin.do"
//change the second to start counting down from
var countdownfrom=10
var currentsecond=document.redirect.redirect2.value=countdownfrom+1
function countredirect(){
if (currentsecond!=1){
currentsecond-=1
document.redirect.redirect2.value=currentsecond
}
else{
window.location=targetURL
return
}
setTimeout("countredirect()",1000)
}
countredirect()
//-->
</script>