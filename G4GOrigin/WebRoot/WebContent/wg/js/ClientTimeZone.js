// This code lifted from 'http://www.mkolar.org/javascript/clock.html' and liberally modified

var msie = navigator.userAgent.indexOf("MSIE");
var now = new Date();
var clientTimeZoneOffset = now.getTimezoneOffset(); // = what browser thinks is TZ; but
        // MS IE version 3 gives wrong sign of TZ
	// MSIE 4.0b2 shifts TZ by -1 hour
	// Any other problems?

if (clientTimeZoneOffset) {
  if (msie > -1) {
    if (navigator.userAgent.substring(msie+5,msie+6) <= 3) {
      clientTimeZoneOffset *= -1;
    } else {
      if (navigator.userAgent.indexOf("4.0b2") > -1) {
        clientTimeZoneOffset += 60;
      }
    }
  }
  document.write('<input type="hidden" name="x-clientTimeZoneOffset" value="' + clientTimeZoneOffset * 60 * -1 + '" />');
}
