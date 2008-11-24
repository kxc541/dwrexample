<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>

<%@page import="java.util.List"%>
<%@page import="com.g4g.forms.PlayerGameScheduleForm"%>
<%@page import="com.g4g.utils.DataUtil"%>

<bean:define id="siteId" value="wg" type="java.lang.String"/>
 <logic:present name="siteId" >
  <bean:define id="siteId" name="siteId" type="java.lang.String"/>
</logic:present>


<script type="text/javascript"
			src="WebContent/<bean:write name="siteId"/>/lib/firebug/firebug.js"> </script>

		<script type="text/javascript" src="WebContent/<bean:write name="siteId"/>/js/jquery.js"> </script>

		<script type="text/javascript" src="WebContent/<bean:write name="siteId"/>/js/jquery.timer.js"> </script>

		<!-- 
		<script type="text/javascript" src="WebContent/<bean:write name="siteId"/>/js/wgLib.js"> </script>
		 -->
		<script type="text/javascript" src="WebContent/<bean:write name="siteId"/>/js/OpenClose.js"> </script>

		<script type="text/javascript" src="WebContent/<bean:write name="siteId"/>/js/ActiveAvatar.js"> </script>

		<script type="text/javascript"
			src="WebContent/<bean:write name="siteId"/>/js/jquery.idTabs.pack.js"> </script>

		<script type="text/javascript" src="WebContent/<bean:write name="siteId"/>/js/thickbox.js"> </script>

		<script type="text/javascript"
			src="WebContent/<bean:write name="siteId"/>/js/jquery.innerfade.js"> </script>

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
			List playerRecentGamesList = (List) session.getAttribute("playerRecentGamesList");
			for(int index=0;index<playerRecentGamesList.size();index++){
				PlayerGameScheduleForm playerGameScheduleForm = (PlayerGameScheduleForm) playerRecentGamesList.get(index);
		 %>
		
		<div class="Format1">
			<div class="SchDate floatLeft grid2">
				<%
				String scheduleDate = DataUtil.getString(playerGameScheduleForm.getScheduledStartDate());
				String formatDate = DataUtil.getDate(scheduleDate).toString();
				%>
				<h2><%=formatDate.substring(4,7) %></h2><h1><%=formatDate.substring(8,10) %></h1><h2><%=formatDate.substring(11,16) %></h2>
			</div>
			<div class="floatLeft grid2">
				<div class="Avatar GameAvatar" id="gamev5749rcf0o38g">
					<div class="AvatarImage">
						<img src="<%= DataUtil.getBasePath(request) + "WebContent" + playerGameScheduleForm.getGameDTO().getImgsrc()%>">
						<div class="AvatarOptions">
							<h1><a href="gameDetails.jsp"><%=playerGameScheduleForm.getGameName() %></a></h1>
							<table>
								<tr>
									<td><img src="<bean:write name="siteId"/>/css/images/icons/whitearrow.png"></td> 
									<td><a href="displayGameLobbyPage.do">Game Lobby</a></td>
								</tr>
								<tr>
									<td><img src="<bean:write name="siteId"/>/css/images/icons/challenge.png"></td> 
									<td><a onclick="javascript:removeGameFromCurrentUser('game', 'v5749rcf0o38g')" href="#">Remove Game!</a></td>
								</tr>
								<tr class="AvatarClose">
									<td><img src="<bean:write name="siteId"/>/css/images/icons/close.png"></td> 
									<td>Close</td>
								</tr>
							</table>
						</div><!-- avatar options -->
					</div><!-- avatar-image -->
					<em><a href="displayGameDetails.do?gameId=<%=playerGameScheduleForm.getGameId() %>"><%=playerGameScheduleForm.getGameName() %></a></em>
				</div><!-- avatar -->
			</div>
			<dl class="GameInfo">
				<dt><%=playerGameScheduleForm.getGameName() %></dt>
				<dd>Jackpot $<%=playerGameScheduleForm.getPayoutAmount() %></dd>
				<dd>
					vs <a href="userPage.jsp"><%=playerGameScheduleForm.getOppositePlayerName() %></a>
				</dd>
				<dd class="buttons clearfix">
					<a href="viewCard.jsp" class="modal blue">view card</a>
					<!-- /cgi-bin/WebObjects/g4g.woa/wa/showObjectModal?id=1000011&entityName=Tournament&KeepThis=true&TB_iframe=true&height=1500&width=650&modal=true -->
				</dd>
			</dl>
		</div><!--// format1 //-->
		<%}%>
		
		<a href="#" onclick="self.parent.tb_remove();" >Back to Profile</a>
