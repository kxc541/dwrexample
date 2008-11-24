
<%@page import="com.g4g.delegator.TwoPlayerMatchServiceDelegator"%>
<%@page import="com.g4g.dto.MatchDTO"%>
<%@page import="com.g4g.dto.TwoplayermatchDTO"%><%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>

<%@page import="java.util.List"%>
<%@page import="com.g4g.forms.PlayerGameScheduleForm"%>
<%@page import="com.g4g.utils.DataUtil"%>
<%@page import="com.g4g.utils.G4GConstants"%>
<%@page import="com.g4g.utils.DateUtil"%>
<%@page import="com.g4g.dto.UserDTO"%>

<bean:define id="siteId" value="wg" type="java.lang.String"/>
<logic:present name="siteId" >
	<bean:define id="siteId" name="siteId" type="java.lang.String"/>
</logic:present>

<%
UserDTO userDTO = new UserDTO();
	if(DataUtil.getUserDTO(request) != null){
		userDTO = DataUtil.getUserDTO(request);
	}
List playerRecentGamesList = (List) request.getAttribute(G4GConstants.PLAYERRECENTGAMESLIST);
int maxRecentGames = (playerRecentGamesList.size()>3)?3:playerRecentGamesList.size();

if(playerRecentGamesList.size() != 0) {
	for(int index=0;index<maxRecentGames;index++){
		PlayerGameScheduleForm playerGameScheduleForm = (PlayerGameScheduleForm) playerRecentGamesList.get(index);
		TwoplayermatchDTO matchDTO = TwoPlayerMatchServiceDelegator.getDTOFromId(playerGameScheduleForm.getMatchId());
		%>
		<div class="WidgetSubContent">
			<div class="Avatar GameAvatar">
				<div class="AvatarImage">
					<div class="AvatarOptions">
						<table>
						<tr>
							<td align="center"><img src="WebContent/<bean:write name="siteId"/>/css/images/icons/whitearrow.png" alt="Arrow"></td>
							<td><a href="displayGameLobbyPage.do">Game Lobby</a></td>
						</tr>
					<% if(request.getParameter(G4GConstants.PROFILEUSERID) == null) { %>
						<tr>
							<%String encryptedPlayerGameId = DataUtil.encrypt(String.valueOf(playerGameScheduleForm.getGameId())); %>
							<td align="center"><img src="WebContent/<bean:write name="siteId"/>/css/images/icons/challenge.png" alt="Challenge"></td>
							<td><a onclick="javascript:removeGameFromCurrentUser('user', '<%= encryptedPlayerGameId %>')" href="#">Remove Game!</a></td>
						</tr>
					<% } %>
						<tr class="AvatarClose">
							<td align="center"><img class="AvatarClose" src="WebContent/<bean:write name="siteId"/>/css/images/icons/close.png" alt="Close"></td>
							<td>Close</td>
						</tr>
						</table>
					</div>
					<img class="GameImage" src="WebContent/<bean:write name="siteId"/><%=playerGameScheduleForm.getGameDTO().getWithoutWGImgsrc() %>" alt="<%=playerGameScheduleForm.getGameName() %>">
				</div>
			</div>

		<% if(matchDTO.getWinnerid().getId() == playerGameScheduleForm.getPlayerId()) { %>
			<div class="copy won">
		<% } else { %>
			<div class="copy lost">
		<% } %>
		<!--
				<div class="buttons">
					<a href="<%=G4GConstants.VIEW_CARD%><%=playerGameScheduleForm.getMatchId()%><%=G4GConstants.THICKBOX%>" class="thickbox blue">view results</a>
				</div>
		 -->
				<div class="date">
					<%
					String scheduleDate = DataUtil.getDate(DateUtil.getDateOfTimeZone(playerGameScheduleForm.getScheduledStartDate(),userDTO.getOffset()), G4GConstants.DATE_DD_MM_YYYY_AT_HH_MM_A);
					String formatDate = DataUtil.getDate(scheduleDate).toString();
					%>
					<%=formatDate.substring(4,7) %> <%=formatDate.substring(8,10) %> @ <%=scheduleDate.substring(14,22) %>
				</div>
				<h3><%=playerGameScheduleForm.getGameName() %></h3>
				<h4>Jackpot $<%=playerGameScheduleForm.getPayoutAmount() %></h4>
				<p>vs <a href="displayProfile.do?<%=G4GConstants.PROFILEUSERID%>=<%=playerGameScheduleForm.getOppositePlayerId() %>"><%=playerGameScheduleForm.getOppositePlayerName() %></a></p>
			</div>
		</div>
		<div class="clear"></div>
	<%
	}

%>
	<a href="WebContent/Profile_RecentGames_All.jsp?KeepThis=true&TB_iframe=true&height=900&width=600&modal=false" class="thickbox">More Games</a>

		<!-- <a href="WebContent/Profile_RecentGames_All.jsp?KeepThis=true&TB_iframe=true&height=900&width=600&modal=false" class="thickbox">View All</a>
		 -->
<%
	session.setAttribute("playerRecentGamesList",playerRecentGamesList);
} else {
	out.print("<h3>No Recent Matches</h3>");
}
%>
