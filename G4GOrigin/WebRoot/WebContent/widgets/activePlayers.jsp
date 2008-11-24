<%@ taglib
	uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@page import="com.g4g.utils.G4GConstants"%>
<%@page import="com.g4g.dto.PlayerDTO"%>
<%@page import="com.g4g.delegator.PlayerServiceDelegator"%>
<%@page import="com.g4g.utils.DataUtil"%>
<%@page import="com.impessa.worldgaming.client.User"%>
<%@page import="com.g4g.delegator.G4GFinancialDelegator"%>
<%@page import="com.g4g.utils.SQLConstants"%>
<%@page import="com.g4g.delegator.SubNationalCodeServiceDelegator"%>
<%@page import="com.impessa.worldgaming.client.InternalException_Exception"%>
<%@page import="com.g4g.dto.AvatarsDTO"%>
<%@page import="com.g4g.delegator.AvatarsServiceDelegator"%>
<%@page import="java.util.List"%>
<%@page import="com.g4g.delegator.WidgetsServiceDelegator"%>
<%@page import="com.g4g.dto.ActivePlayersDTO"%>

<bean:define id="siteId" value="wg" type="java.lang.String"/>
<logic:present name="siteId" >
	<bean:define id="siteId" name="siteId" type="java.lang.String"/>
</logic:present>
<div id="ActivePlayersWidget" class="Widget">
	<div class="WidgetHeader"><p>Most Active Players</p></div>
	<bean:define id="activePlayers" name="gameLobbyPageForm" property="activePlayers" />
	<div class="WidgetContent">
	<logic:iterate id="activeplayers" name="activePlayers" length="5">
		<div class="player">
			<div class="image">
				<jsp:include page="../common/avatar.jsp" >
					<jsp:param name="playerid" value="<%= ((ActivePlayersDTO) activeplayers).getPlayerid() %>"/>
					<jsp:param name="avatarid" value="<%= ((ActivePlayersDTO) activeplayers).getAvatarid() %>"/>
					<jsp:param name="screenname" value="<%= ((ActivePlayersDTO) activeplayers).getScreenname() %>"/>
					<jsp:param name="isOnline" value="<%= ((ActivePlayersDTO) activeplayers).isOnline() %>"/>
					<jsp:param name="isLink" value="active"/>
					<jsp:param name="pictureId" value="<%= ((ActivePlayersDTO) activeplayers).getPictureid() %>"/>
					<jsp:param name="showMenu" value="false"/>
				</jsp:include>
			</div>
			<div class="copy">
				<h4><bean:write name="activeplayers" property="screenname" /></h4>
				<p>Location: <bean:write name="activeplayers" property="location" /></p>
				<a href="<%=DataUtil.getBasePath(request)%>displayProfile.do?profileUserId=<bean:write name="activeplayers" property="playerid" />" class="viewprofile"><img alt=""	src="WebContent/<bean:write name="siteId"/>/css/images/bg/buttons/viewprofile.jpg" alt="View Profile"></a>
			</div>
		</div>
		<div class="clear"></div>
	</logic:iterate>
	</div>
</div>