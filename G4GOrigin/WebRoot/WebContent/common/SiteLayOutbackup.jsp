<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" 
"http://www.w3.org/TR/html4/loose.dtd">


<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles"%>

<html:html>
<head>
	<title><tiles:getAsString name="title"></tiles:getAsString>
	</title>
	<tiles:insert attribute="headTag"></tiles:insert>
</head>


<body>
	<div id="container">
		&nbsp;&nbsp;

		<%@include file="Header.jsp"%>
		<%@include file="GlobalNavigation.jsp"%>

		<%@include file="FooterNav.jsp"%>
		<%@include file="Footer.jsp"%>
	</div>
</body>
</html:html>
