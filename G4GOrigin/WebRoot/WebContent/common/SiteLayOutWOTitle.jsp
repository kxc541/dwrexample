<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" 
"http://www.w3.org/TR/html4/loose.dtd">


<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles"%>

<html:html>
<head>
	<tiles:insert attribute="headTag"></tiles:insert>
</head>


<body>
	<div id="container">
		<tiles:insert attribute="header" />
		<tiles:insert attribute="globalnavigation" />
		<tiles:insert attribute="body" />
		<tiles:insert attribute="footernavigation" />
		<tiles:insert attribute="footer" />
	</div>
</body>
</html:html>
