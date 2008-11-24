<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<script type='text/javascript' src='dwr/interface/HelloWorld.js'></script>
<script type="text/javascript" src="dwr/engine.js"></script>
<script type="text/javascript" src="dwr/util.js"></script>
<title>Insert title here</title>
<script type="text/javascript">
function update() {
 	 HelloWorld.getName(function(data) {
    dwr.util.setValue("demoReply", data);
  });
}
</script>
</head>
<body>

<H1>Get value from server</H1>
<form action="" method="post">
	<input type="text" id="demoReply" />
   <input value="Send" type="button" onclick="update()"/>
  </form>
</body>
</html>