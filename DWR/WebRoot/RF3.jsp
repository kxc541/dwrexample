<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<script type='text/javascript' src='dwr/interface/RF.js'></script>
<script type="text/javascript" src="dwr/engine.js"></script>
<script type="text/javascript" src="dwr/util.js"></script>
<title>Insert title here</title>
<script type="text/javascript">
function forward() {
  	RF.getInclude(function(data) {
    dwr.util.setValue("forward", data, { escapeHtml:false });
  });
}
</script>
</head>
<body>

	<H1>Get value from server</H1>

	<div id="forward"></div>
   <input value="Send" type="button" onclick="forward()"/>

</body>
</html>