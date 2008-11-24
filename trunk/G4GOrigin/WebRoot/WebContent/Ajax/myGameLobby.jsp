<%@page import="com.g4g.utils.SessionUtil"%><%@ taglib
	uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<bean:define id="siteId" value="wg" type="java.lang.String" />
<logic:present name="siteId">
	<bean:define id="siteId" name="siteId" type="java.lang.String" />
</logic:present>
<link rel="stylesheet"
	href="WebContent/<bean:write name="siteId"/>/css/Global.css"
	type="text/css" media="screen">
<link rel="stylesheet"
	href="WebContent/<bean:write name="siteId"/>/css/Widgets.css"
	type="text/css" media="screen">
<link rel="stylesheet"
	href="WebContent/<bean:write name="siteId"/>/css/Tables.css"
	type="text/css" media="screen">
<link rel="stylesheet"
	href="WebContent/<bean:write name="siteId"/>/css/Games.css"
	type="text/css" media="screen">

<%@page import="com.g4g.utils.G4GConstants"%>

<bean:define id="xbox360Games" name="gameLobbyPageForm" property="xbox360Games" />
<logic:notEmpty name="xbox360Games">
	<ul>
		<li>
			<img src="WebContent/<bean:write name="siteId"/>/css/images/icons/xbox360_med.png" alt="XBOX360">
		</li>
		<logic:iterate id="xbox360Games" name="xbox360Games" length="5">
			<li>
				<a href="displayGameDetails.do?gameId=<bean:write name="xbox360Games" property="id" />"><bean:write name="xbox360Games" property="gamename" /> <span>lobby</span></a>
			</li>
		</logic:iterate>
	</ul>
</logic:notEmpty>
	
<bean:define id="ps2Games" name="gameLobbyPageForm" property="ps2Game" />
<logic:notEmpty name="ps2Games">
<ul>
	<li class="head"><img src="WebContent/<bean:write name="siteId"/>/css/images/icons/ps2_med.png" alt="PS2"></li>
	<logic:iterate id="ps2Games" name="ps2Games" length="5">
		<li>
			<a href="displayGameDetails.do?gameId=<bean:write name="ps2Games" property="id" />"><bean:write name="ps2Games" property="gamename" /> <span>lobby</span></a>
		</li>
	</logic:iterate>
</ul>
</logic:notEmpty>

<bean:define id="ps3Games" name="gameLobbyPageForm" property="ps3Game" />
<logic:notEmpty name="ps3Games">
<ul>
	<li class="head"><img src="WebContent/<bean:write name="siteId"/>/css/images/icons/ps3_med.png" alt="PS3"></li>
	<logic:iterate id="ps3Games" name="ps3Games" length="5">
		<li>
			<a href="displayGameDetails.do?gameId=<bean:write name="ps3Games" property="id" />"><bean:write name="ps3Games" property="gamename" /> <span>lobby</span></a>
		</li>
	</logic:iterate>
</ul>
</logic:notEmpty>
<ul>
	<li class="last"><a href="displayGameLobbyPage.do">More Games</a></li>
</ul>