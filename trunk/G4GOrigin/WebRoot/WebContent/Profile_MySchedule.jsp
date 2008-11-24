
<%@page import="com.g4g.dto.TwoplayermatchDTO"%>
<%@page import="com.g4g.delegator.TwoPlayerMatchServiceDelegator"%><%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>

<%@page import="java.util.List"%>
<%@page import="com.g4g.forms.PlayerGameScheduleForm"%>
<%@page import="com.g4g.utils.DataUtil"%>
<%@page import="com.g4g.utils.G4GConstants"%>
<%@page import="com.g4g.dto.UserDTO"%>
<%@page import="com.g4g.utils.DateUtil"%>

<bean:define id="siteId" value="wg" type="java.lang.String"/>
 <logic:present name="siteId" >
  <bean:define id="siteId" name="siteId" type="java.lang.String"/>
</logic:present>

<%
UserDTO userDTO = new UserDTO();
if(DataUtil.getUserDTO(request) != null){
	userDTO = DataUtil.getUserDTO(request);
}
List playerScheduleList = (List) request.getAttribute(G4GConstants.PLAYERSCHEDULELIST);
int maxMySchedule = (playerScheduleList.size()>3)?3:playerScheduleList.size();

for(int index=0;index<maxMySchedule;index++){
	PlayerGameScheduleForm playerGameScheduleForm = (PlayerGameScheduleForm) playerScheduleList.get(index);
	if(playerGameScheduleForm.getScheduledStartDate().before(DataUtil.todayGMT())) {
		playerScheduleList.remove(index);
		index -= 1;
		maxMySchedule -= 1;
	}
}

if(playerScheduleList.size() != 0) {
	for(int index=0;index<maxMySchedule;index++){
		PlayerGameScheduleForm playerGameScheduleForm = (PlayerGameScheduleForm) playerScheduleList.get(index);
		TwoplayermatchDTO twoplayermatchDTO = TwoPlayerMatchServiceDelegator.getDTOFromId(playerGameScheduleForm.getMatchId());

	%>

		<table cellspacing="0">
		<tr valign="top">
			<td class="calendar" align="center">
			<%
				String scheduleDate = DataUtil.getDate(DateUtil.getDateOfTimeZone(playerGameScheduleForm.getScheduledStartDate(),userDTO.getOffset()), G4GConstants.DATE_DD_MM_YYYY_AT_HH_MM_A);
				String formatDate = DataUtil.getDate(scheduleDate).toString();
			%>
				<div class="month"><%=formatDate.substring(4,7) %></div>
				<div class="day"><%=formatDate.substring(8,10) %></div>
				<div class="time"><%=scheduleDate.substring(14,19) %> <%=scheduleDate.substring(20,22) %></div>
			</td>
			<td class="game">
				<div class="Avatar">
					<div class="AvatarImage">
						<div class="AvatarOptions">
							<table>
								<tr>
									<td align="center"><img src="WebContent/<bean:write name="siteId"/>/css/images/icons/whitearrow.png"></td>
									<td><a href="displayGameLobbyPage.do">Game Lobby</a></td>
								</tr>
							<% if(request.getParameter(G4GConstants.PROFILEUSERID) == null) { %>
								<tr>
									<% String encryptedPlayerGameId = DataUtil.encrypt(String.valueOf(playerGameScheduleForm.getGameId())); %>
									<td align="center"><img src="WebContent/<bean:write name="siteId"/>/css/images/icons/challenge.png"></td>
									<td><a onclick="javascript:removeGameFromCurrentUser('lobby', '<%= encryptedPlayerGameId %>')" >Remove Game!</a></td>
								</tr>
							<% } %>
								<tr class="AvatarClose">
									<td align="center"><img class="AvatarClose" src="WebContent/<bean:write name="siteId"/>/css/images/icons/close.png"></td>
									<td>Close</td>
								</tr>
							</table>
						</div>
						<img src="WebContent/<bean:write name="siteId"/><%=playerGameScheduleForm.getGameDTO().getWithoutWGImgsrc() %>" alt="<%=playerGameScheduleForm.getGameName() %>">
					</div>
				</div>
			</td>
			<td class="schedule">
				<h3><%=playerGameScheduleForm.getGameName() %></h3>
				<p>Jackpot $<%= DataUtil.format(playerGameScheduleForm.getPayoutAmount()) %>
				<br>
							<%
				 if(playerGameScheduleForm.getScheduledStartDate().after(DataUtil.todayGMT())) {
				  if(playerGameScheduleForm.getOppositePlayerName()!=null){
				%>
				    vs <a href="displayProfile.do?<%=G4GConstants.PROFILEUSERID%>=<%=playerGameScheduleForm.getOppositePlayerId() %>"><%=playerGameScheduleForm.getOppositePlayerName() %></a>
				<%
				  }else{
				      out.print("waiting for opponent to accept");
				  }
				 } else {
				  out.print("Pending game results validation");
				 }
				%>
				</p>
				<div class="buttons">
					<a href="<%=G4GConstants.VIEW_CARD%><%=playerGameScheduleForm.getMatchId() %>" class="modal thickbox blue">details</a>
					<%if(DateUtil.getOneHourEarlyDate(playerGameScheduleForm.getScheduledStartDate()).after(DataUtil.todayGMT())) {
					%>
					<a href="#" class="gray"><%=G4GConstants.CANCEL_MATCH%></a>
					<%}else{
						if((playerGameScheduleForm.getOppositePlayerName()!=null) && (playerGameScheduleForm.getOppositePlayerName()!=null)&& (playerGameScheduleForm.getScheduledStartDate().after(DataUtil.todayGMT()))){
					%>
					 <!-- Shown between  match time and one hr( or as time set in properties )-->
			       		<%long  min = (playerGameScheduleForm.getScheduledStartDate().getTime() - DataUtil.todayGMT().getTime())/(60 * 1000);
			       	%>
			      		Playing in <%=min %> minutes
					<%}}%>
				</div>

			</td>
		</tr>
		</table>
		<%}%>
<%
session.setAttribute("playerScheduleList",playerScheduleList);

if(playerScheduleList.size() > 3)
	out.print("<a href=\"WebContent/Profile_MySchedule_All.jsp?KeepThis=true&TB_iframe=true&height=2000&width=600&modal=false\" class=\"thickbox\">View All</a>");
}
else {
	out.print("<h3>No Pending Matches</h3>");
}
%>