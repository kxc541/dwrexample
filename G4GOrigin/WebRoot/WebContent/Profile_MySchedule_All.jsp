<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>

<%@page import="java.util.List"%>
<%@page import="com.g4g.forms.PlayerGameScheduleForm"%>
<%@page import="com.g4g.utils.DataUtil"%>
<%@page import="com.g4g.utils.G4GConstants"%>

<bean:define id="siteId" value="wg" type="java.lang.String"/>
 <logic:present name="siteId" >
  <bean:define id="siteId" name="siteId" type="java.lang.String"/>
</logic:present>

<link rel="stylesheet" media="screen" href="<%=DataUtil.getBasePath(request)%>WebContent/<bean:write name="siteId"/>/css/Global.css">
<link rel="stylesheet" type="text/css" media="screen"href="<%=DataUtil.getBasePath(request)%>WebContent/<bean:write name="siteId"/>/css/Widgets.css"/>	
<link rel="stylesheet" media="screen" href="<%=DataUtil.getBasePath(request)%>WebContent/<bean:write name="siteId"/>/css/cfab.css">
<link rel="stylesheet" type="text/css" media="screen" href="<%=DataUtil.getBasePath(request)%>WebContent/<bean:write name="siteId"/>/css/Tables.css"/>
<link rel="stylesheet" type="text/css" media="screen" href="<%=DataUtil.getBasePath(request)%>WebContent/<bean:write name="siteId"/>/css/Chat.css">
<link rel="stylesheet" type="text/css" media="screen" href="<%=DataUtil.getBasePath(request)%>WebContent/<bean:write name="siteId"/>/css/rating.css">
<link rel="stylesheet" type="text/css" media="screen" href="<%=DataUtil.getBasePath(request)%>WebContent/<bean:write name="siteId"/>/css/Games.css">	
<link rel="stylesheet" type="text/css" media="screen" href="<%=DataUtil.getBasePath(request)%>WebContent/<bean:write name="siteId"/>/css/thickbox.css">	
<link rel="stylesheet" type="text/css" media="screen" href="<%=DataUtil.getBasePath(request)%>WebContent/<bean:write name="siteId"/>/css/humanmsg.css">
<link rel="stylesheet" type="text/css" media="screen" href="<%=DataUtil.getBasePath(request)%>WebContent/<bean:write name="siteId"/>/css/Search.css">






		<script type="text/javascript" src="<%=DataUtil.getBasePath(request)%>WebContent/<bean:write name="siteId"/>/lib/firebug/firebug.js"> </script>

		<script type="text/javascript" src="<%=DataUtil.getBasePath(request)%>WebContent/<bean:write name="siteId"/>/js/jquery.js"> </script>

		<script type="text/javascript" src="<%=DataUtil.getBasePath(request)%>WebContent/<bean:write name="siteId"/>/js/jquery.timer.js"> </script>

		<script type="text/javascript" src="<%=DataUtil.getBasePath(request)%>WebContent/<bean:write name="siteId"/>/js/wgLib.js"> </script>

		<script type="text/javascript" src="<%=DataUtil.getBasePath(request)%>WebContent/<bean:write name="siteId"/>/js/OpenClose.js"> </script>

		<script type="text/javascript" src="<%=DataUtil.getBasePath(request)%>WebContent/<bean:write name="siteId"/>/js/ActiveAvatar.js"> </script>

		<script type="text/javascript" src="<%=DataUtil.getBasePath(request)%>WebContent/<bean:write name="siteId"/>/js/jquery.idTabs.pack.js"> </script>

		<script type="text/javascript" src="<%=DataUtil.getBasePath(request)%>WebContent/<bean:write name="siteId"/>/js/thickbox.js"> </script>

		<script type="text/javascript" src="<%=DataUtil.getBasePath(request)%>WebContent/<bean:write name="siteId"/>/js/jquery.innerfade.js"> </script>

		<script type="text/javascript">
		$(document).ready(function(){
			$(".Sidebar .Widget").OpenClose();
			$(".Avatar").ActiveAvatar();
			$(".AvatarOptions").hide();

			$(".BannerAds ul").innerfade({
				animationtype: 'fade',
				speed: 1750,
				timeout: 6000,
				type: 'sequence',
				containerheight: 'auto'
			});
		});
		
</script>


		
		<%
			List playerScheduleList = (List) session.getAttribute("playerScheduleList");
			for(int index=0;index<playerScheduleList.size();index++){
				PlayerGameScheduleForm playerGameScheduleForm = (PlayerGameScheduleForm) playerScheduleList.get(index);
		 %>
		 <div id="ScheduleWidget" class="Widget">
								<div class="WidgetContent">
							
		<div class="Format1">
			<div class="SchDate floatLeft grid2">
				<%
				String scheduleDate = DataUtil.getString(playerGameScheduleForm.getScheduledStartDate());
				String formatDate = DataUtil.getDate(scheduleDate).toString();
				%>
				<h2><%=formatDate.substring(4,7) %></h2><h1><%=formatDate.substring(8,10) %></h1><h2><%=formatDate.substring(11,16) %></h2>
			</div>
			<div class="floatLeft grid2">
				<div class="Avatar GameAvatar" id="<%=playerGameScheduleForm.getGameId() %>">
					<div class="AvatarImage">
						<img src="<bean:write name="siteId"/><%=playerGameScheduleForm.getGameDTO().getWithoutWGImgsrc() %>">
						<div class="AvatarOptions">
							<h1><a href="../displayGameDetails.do?gameId=<%=playerGameScheduleForm.getGameId() %>&playerId=<%=playerGameScheduleForm.getPlayerId() %>"><%=playerGameScheduleForm.getGameName() %></a></h1>
							
							<table>
								<tr>
									<td><img src="<bean:write name="siteId"/>/css/images/icons/whitearrow.png"></td> 
									<td><a href="displayGameLobbyPage.do">Game Lobby</a></td>
								</tr>
							<% if(request.getParameter(G4GConstants.PROFILEUSERID) == null) { %>
								<tr>
									<%String encryptedPlayerGameId = DataUtil.encrypt(String.valueOf(playerGameScheduleForm.getGameId())); %>
									<td><img src="<bean:write name="siteId"/>/css/images/icons/challenge.png"></td> 
									<td><a onclick="javascript:removeGameFromCurrentUser('user', '<%= encryptedPlayerGameId %>')" href="#">Remove Game!</a></td>
								</tr>
							<% } %>
								<tr class="AvatarClose">
									<td><img src="<bean:write name="siteId"/>/css/images/icons/close.png"></td> 
									<td>Close</td>
								</tr>
							</table>
							
							
						</div><!-- avatar options -->
					</div><!-- avatar-image -->
					<em><a href="../displayGameDetails.do?gameId=<%=playerGameScheduleForm.getGameId() %>&playerId=<%=playerGameScheduleForm.getPlayerId() %>"><%=playerGameScheduleForm.getGameName() %></a></em>
				</div><!-- avatar -->
			</div>
			<dl class="GameInfo">
				<dt><%=playerGameScheduleForm.getGameName() %></dt>
				<dd>Jackpot $<%=playerGameScheduleForm.getPayoutAmount() %></dd>
				<dd>
					vs <a href="../displayProfile.do?<%=G4GConstants.PROFILEUSERID%>=<%=playerGameScheduleForm.getOppositePlayerId() %>"><%=playerGameScheduleForm.getOppositePlayerName() %></a>
				</dd>
				<dd class="buttons clearfix">
					<a href="../displayDirectChallenge2.do?<%=G4GConstants.TWO_PLAYER_MATCH_ID%>=<%=playerGameScheduleForm.getMatchId() %>" class="modal blue">view card</a>
					<!-- /cgi-bin/WebObjects/g4g.woa/wa/showObjectModal?id=1000011&entityName=Tournament&KeepThis=true&TB_iframe=true&height=1500&width=650&modal=true -->
				</dd>
			</dl>
		</div>
		</div>
							</div><!--// format1 //-->
		<%}%>
		
		