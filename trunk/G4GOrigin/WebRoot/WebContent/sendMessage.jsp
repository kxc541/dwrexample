<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>

<bean:define id="siteId" value="wg" type="java.lang.String"/>
	<logic:present name="siteId" >
	<bean:define id="siteId" name="siteId" type="java.lang.String"/>
</logic:present>

<%@page import="com.g4g.dto.PlayerDTO"%>
<%@page import="com.g4g.delegator.PlayerServiceDelegator"%>
<%@page import="com.g4g.utils.G4GConstants"%>
<%@page import="com.g4g.dto.SearchCriteria"%>
<%@page import="com.g4g.utils.SessionUtil"%>
<%@page import="com.g4g.utils.DataUtil"%>
<%@page import="com.impessa.worldgaming.client.User"%>
<%@page import="com.g4g.delegator.G4GFinancialDelegator"%>
<link rel="stylesheet" href="<%= DataUtil.getBasePath(request) %>WebContent/<bean:write name="siteId"/>/css/Global.css" media="screen">
<script type="text/javascript" src="<%= DataUtil.getBasePath(request) %>WebContent/<bean:write name="siteId"/>/lib/firebug/firebug.js">Generic</script>
<script type="text/javascript" src="<%= DataUtil.getBasePath(request) %>WebContent/<bean:write name="siteId"/>/js/jquery.js"> </script>
<script type="text/javascript" src="<%= DataUtil.getBasePath(request) %>WebContent/<bean:write name="siteId"/>/javascript/interface.js"> </script> <!-- jquery drag and drop http://interface.eyecon.ro/ -->
<script type="text/javascript" src="<%= DataUtil.getBasePath(request) %>WebContent/<bean:write name="siteId"/>/lib/thickbox.js"> </script> <!-- thickbox cfab version -->
<script type="text/javascript" src="<%= DataUtil.getBasePath(request) %>WebContent/<bean:write name="siteId"/>/lib/g4glib.js"> </script> <!-- new js lib  -->

<style type="text/css">
.compose {
	margin:20px;
	padding:10px;
	border:1px solid #e8e5e0;
}

.compose h1 {
	font-size:12px;
	font-weight:bold;
	padding:10px;
	margin-bottom:18px;
}

.compose textarea {
	margin:5px 0;
	font-size:12px;
	z-index:300;
}
				
.compose .text {
	border:1px solid #999;
}
			
.compose .grid6 {
	margin-right:20px;
}

.compose .grid6 dt {
	line-height:18px;
	margin-bottom:5px;
}

img.gameImage {
	width:60px;
}

#ModalMessage {
	padding:10px;
	margin:0 auto;
	border:5px solid #666;
}

#ModalMessage .buttons a {
	color:#fff;
}

#ModalMessage textarea {
	height:140px;
	font-size:12px;
	padding:5px;
	borer:1px solid #666;
	z-index:500;
	font-family:verdana;
}

.MessageContainer {
	background:url(wg/css/images/bg/TournamentBG.jpg) top center no-repeat #d2d2d2;
	margin:5px 0;
	border:1px solid #e2e1dd;
}

.MessageContainer.Unread {}

.MessageContainer a {color:#000;}

.MessageContent .grid2 {
	width:100px;
}

.messageReply textarea {
	margin:10px 0;
}

.grid { float:left; }

.gridRight {float:right;}

.grid1 { width:60px; }
.grid2 { width:80px; }
.grid3 { width:100px; }
.grid4 { width:125px; }
.grid5 { width:140px; }
.grid6 { width:170px; }
.grid7 { width: 250px; }
.grid8 { width: 315px; }
.grid9 { width: 375px; }
.grid10 { width:455px; }
</style>
<%
	SearchCriteria searchCriteria = new SearchCriteria();
	searchCriteria.setAttribute(G4GConstants.SCREENNAME_DB, request.getParameter(G4GConstants.FRIEND_ID));
	PlayerDTO player = (PlayerDTO) PlayerServiceDelegator.getList(searchCriteria).get(0);
	User user = G4GFinancialDelegator.getUserInfo(player.getEmailaddress());
%>
<div id="content">
	<form method="post" action="../ajax/composeMessage.do" name="saveChangesForm">
	<div id="ModalMessage" class="MessageContainer compose clearfix">
		<h1>
			Compose Message - <img width="12" height="12" class="statusdot OnlineDotsTimer" border="0" alt="<%= (player.isIsonline()) ? "ONLINE" : "OFFLINE"%>" id="<%= player.getScreenname() %>" src="<%= (player.isIsonline()) ? ""+SessionUtil.getSiteId(request) +"/images/icons/status-online.png" : "wg/images/icons/status-offline.png" %>"> <%= player.getScreenname() %> (<%= user.getFirstname() + " " + user.getLastname() %>)
		</h1>
		<div class="grid grid3">
			<jsp:include page="common/avatar.jsp">
				<jsp:param name="playerid" value="<%=player.getId() %>"/>
				<jsp:param name="avatarid" value="<%= (player.getAvatars() != null) ? player.getAvatars().getId() : 0 %>"/>
				<jsp:param name="screenname" value="<%=player.getScreenname() %>"/>
				<jsp:param name="isOnline" value="<%=player.isIsonline() %>"/>
				<jsp:param name="pictureId" value="<%=player.getPictureId() %>"/>
				<jsp:param name="showMenu" value="false"/>
				<jsp:param name="isLink" value="true"/>
			</jsp:include><!-- avatar -->
		</div>
		<div class="messageInfo grid grid8">
			<div class="messageReply textareaFull">
				<input size="40" name="messageSubject" type="text" class="text">
				<textarea name="messageBody" cols="44" rows="40"></textarea>
				<input type="hidden" name="id" value="<%= request.getParameter("friendId") %>" />
				<div class="buttons">
					<a href="#" onclick="self.parent.tb_remove()" class="button red inline" style="cursor: pointer;">cancel</a>
					<a href="#" onclick="document.saveChangesForm.submit();self.parent.tb_remove(false);" class="button green inline" style="cursor: pointer;">send message</a>
				</div>
			</div>
		</div>
	</div><!-- MessageContainer -->
	</form>
</div>