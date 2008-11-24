<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>

<%@page import="java.util.List"%>
<%@page import="com.g4g.forms.ManagePlayerGameForm"%>
<%@page import="com.g4g.dto.GameDTO"%>
<%@page import="com.g4g.delegator.GameServiceDelegator"%>
<%@page import="com.g4g.utils.DataUtil"%>
<%@page import="com.g4g.utils.G4GConstants"%>

<bean:define id="siteId" value="wg" type="java.lang.String"/>
 <logic:present name="siteId" >
  <bean:define id="siteId" name="siteId" type="java.lang.String"/>
</logic:present>
<div id="Xbox360" style="overflow: auto;">
<%
List playerGameList = (List)request.getAttribute("playerGameList");

if(playerGameList.size() != 0) {

	for(int index=0;index<playerGameList.size();index++){
		ManagePlayerGameForm managePlayerGameForm = (ManagePlayerGameForm) playerGameList.get(index);
		GameDTO gameDTO = GameServiceDelegator.getGame(managePlayerGameForm.getGameId());
		String encryptedPlayerGameId = DataUtil.encrypt(String.valueOf(managePlayerGameForm.getGameId()));
		%>		
		<div class="Avatar GameAvatar" id="user<%= encryptedPlayerGameId %>">
			<div class="AvatarImage">
				<div class="AvatarOptions">
					<table>
					<tr><th colspan="2"><a href="displayGameDetails.do?gameId=<%=managePlayerGameForm.getGameId()%>"><%=managePlayerGameForm.getGameName() %></a></th></tr>
					<tr>
						<td align="center"><img src="WebContent/<bean:write name="siteId"/>/css/images/icons/whitearrow.png" alt="arrow"></td> 
						<td><a href="displayGameDetails.do?gameId=<%=managePlayerGameForm.getGameId()%>">Game Lobby</a></td>
					</tr>
				<% if(request.getParameter(G4GConstants.PROFILEUSERID) == null) { %>
					<tr>
						<td align="center"><img src="WebContent/<bean:write name="siteId"/>/css/images/icons/challenge.png" alt="challenge"></td>
						<td><a href="#" onclick="javascript:removeGameFromCurrentUser('user', '<%= encryptedPlayerGameId %>');return false;">Remove Game</a></td>
					</tr>
				<% } %>
					<tr class="AvatarClose">
						<td align="center"><img class="AvatarClose" src="WebContent/<bean:write name="siteId"/>/css/images/icons/close.png" alt="close"></td> 
						<td>Close</td>
					</tr>
					</table>
				</div>
				<img class="GameImage" src="WebContent<%= gameDTO.getImgsrc() %>" alt="<%=managePlayerGameForm.getGameName() %>">
			</div>
		</div> <!-- end Avatar -->
	<%
	} 
} else {
	out.print("<h3>No Games To Display</h3>");
}
%>
</div><!-- End Xbox360 -->