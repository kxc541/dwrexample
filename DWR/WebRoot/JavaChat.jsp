<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<script type='text/javascript' src='dwr/interface/JavaChat.js'> </script>
		<script type="text/javascript" src="dwr/engine.js"></script>
		<script type="text/javascript" src="dwr/util.js"></script>
		<script type="text/javascript">
	    	function sendMessage() {
	    		JavaChat.addMessage(dwr.util.getValue("text"));
	    	}
		</script>
		<title>Insert title here</title>

	</head>
	<body onload="dwr.engine.setActiveReverseAjax(true);">
		<h1>
			Java Chat
		</h1>

		<p>
			This is a very simple chat demo that uses reverse ajax to collect
			messages and server-side browser manipulation to update the pages
			with the results.
		</p>
		<p>
			just for knowing the functionality a sleep for 10 sec is put in the system
		</p>
		<p>
			Your Message:
			<input id="text" onkeypress="dwr.util.onReturn(event, sendMessage)" />
			<input type="button" value="Send" onclick="sendMessage()" />
		</p>
		<hr />

		<ul id="chatlog" style="list-style-type: none;">
		</ul>

	</body>


</html>

