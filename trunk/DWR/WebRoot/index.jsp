<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%--
The taglib directive below imports the JSTL library. If you uncomment it,
you must also add the JSTL library to the project. The Add Library... action
on Libraries node in Projects view can be used to add the JSTL 1.1 library.
--%>
<%--
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
--%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>City State Welcome Page</title>
    </head>
    <body>
        <center><h1>DWR - Populate Drop Down List based on another</h1></center>
    
        Welcome to this demo. This demo will show you how to populate one drop down list based on the value of another.
        <br>
        
        <br/>
        <a href="./cityState.do">Click Here to go to demo</a>

		This is my JSP page. <br>
	    <%=(String)request.getParameter("aa") %>
    
    </body>
</html>
