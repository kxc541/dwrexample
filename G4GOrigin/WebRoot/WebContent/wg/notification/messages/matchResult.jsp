
<%@page import="com.g4g.utils.DateUtil"%>
<%@page import="com.g4g.dto.TwoplayermatchDTO"%><%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>

<%@page import="com.g4g.utils.G4GConstants"%>
<%@page import="com.g4g.dto.MatchDTO"%>
<%@page import="com.g4g.dto.PlayerDTO"%>
<%@page import="com.g4g.delegator.TwoPlayerMatchServiceDelegator"%>
<%@page import="com.g4g.delegator.PlayerServiceDelegator"%>
<%@page import="com.g4g.utils.DataUtil"%>
<%@page import="com.g4g.utils.SessionUtil"%>
<%@page import="com.g4g.utils.GamesUtil"%>
<%@page import="com.g4g.dto.GameDTO"%>
<%@page import="com.g4g.delegator.GameServiceDelegator"%>

<%
	int matchId = 0;
	int fromPlayerId = 0;
	TwoplayermatchDTO matchDTO = new TwoplayermatchDTO();
	PlayerDTO playerDTO = new PlayerDTO();
	GameDTO gameDTO = new GameDTO();
	boolean isGameAvailable = false;
	boolean isNetworkAvailable = false;
	String matchDate = G4GConstants.NONE;
	String matchTime = G4GConstants.NONE;
	int winnerId = Integer.parseInt(request.getParameter(G4GConstants.WINNER_ID));

	if (request.getParameter(G4GConstants.MATCHID) != null) {
		matchId = Integer.parseInt(request
				.getParameter(G4GConstants.MATCHID));
		fromPlayerId = Integer.parseInt(request
				.getParameter(G4GConstants.FROMID));
		matchDTO = TwoPlayerMatchServiceDelegator.get(new TwoplayermatchDTO(matchId));

		playerDTO = PlayerServiceDelegator.getPlayer(fromPlayerId);
		gameDTO = GameServiceDelegator.getGame(matchDTO.getTwoplayertournament().getGameDTO().getId());

		matchDate = DataUtil.getDate(DateUtil.getDateOfTimeZone(matchDTO.getScheduledstartdate(), playerDTO.getTimezone().getOffset()), G4GConstants.DATE_DD_MM_YYYY);
		matchTime = DataUtil.getDate(DateUtil.getDateOfTimeZone(matchDTO.getScheduledstartdate(), playerDTO.getTimezone().getOffset()), G4GConstants.DATE_H_MM_A);

		isGameAvailable = GamesUtil.isGameAvailable(playerDTO.getId(),
				matchDTO.getTwoplayertournament().getGameDTO().getId());
		isNetworkAvailable = GamesUtil.isNetWorkAvailable(playerDTO
				.getId(), gameDTO.getNetwork().getId());
	}
%>

<div class="hideDiv" id="body<%=request.getParameter(G4GConstants.ID)%>">
	<div class="MessageContent clearfix">
		<div class="CloseButton">
			<a onclick="javascript:closeMessage(this)"
				id="close<%=request.getParameter(G4GConstants.ID)%>"
				href="javascript:{}">Close</a>
		</div>
		<div class="grid grid2">
			<jsp:include page="../../../common/avatar.jsp">
					<jsp:param name="playerid" value="<%=playerDTO.getId() %>"/>
					<jsp:param name="avatarid" value="<%= (playerDTO.getAvatars() != null) ? playerDTO.getAvatars().getId() : 0 %>"/>
					<jsp:param name="screenname" value="<%=playerDTO.getScreenname() %>"/>
					<jsp:param name="isOnline" value="<%=playerDTO.isIsonline() %>"/>
					<jsp:param name="pictureId" value="<%=playerDTO.getPictureId() %>"/>
					<jsp:param name="showMenu" value="false"/>
					<jsp:param name="isLink" value="false"/>
		</jsp:include>
		</div>
		<div class="messageInfo grid grid9">
			<dl class="">
				<dt>
					<a><img src="WebContent/<%=SessionUtil.getSiteId(request)%>/css/images/icons/challenge.png">
					<% 	if(winnerId == G4GConstants.ZERO) {
							out.print("Stale match swept into Incomplete state.");
						}
						else if(winnerId != fromPlayerId) {
							out.print("You loss this match, better luck next time.");
						}
						else {
							out.print("CONGRATULATION!!! You have won this match.");
						}
					%>
					</a>
				</dt>
				<dd>
					<p>
					<%
						if(winnerId != G4GConstants.ZERO) {
							out.print("Match has been successfully validated for its schedule time at ");
						}
						else {
							out.print("This match has been set to Incomplete because we were unable to find results for it for a day since its starttime, which is ");
						}
					%>
					<%= DataUtil.getDate(matchDTO.getScheduledstartdate(), G4GConstants.DATE_YYYY_DD_MM_HH_MM_SS) %> GMT
					</p>
					<p></p>
					<div class="grid grid2">
						<jsp:include page="../../../common/gameAvatar.jsp">
							<jsp:param name="imgSrc"
								value="<%=matchDTO.getTwoplayertournament().getGameDTO().getImgsrc().trim()%>" />
							<jsp:param name="gameid" value="<%=matchDTO.getTwoplayertournament().getGameDTO().getId() %>" />
							<jsp:param name="gamename" value="<%=matchDTO.getTwoplayertournament().getGameDTO().getGamename()%>" />
							<jsp:param name="isGameavailable" value="<%=isGameAvailable%>" />
							<jsp:param name="isNetworkAvailable"
								value="<%=isNetworkAvailable%>" />
							<jsp:param name="showMenu" value="false" />
							<jsp:param name="isUser" value=""/>
						</jsp:include>
					</div>
					<table class="WidgetInfo Style1">
						<thead>
							<tr>
								<th colspan="2">
									<%=matchDTO.getTwoplayertournament().getGameDTO().getGamename()%>
								</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<td>
									Console
								</td>
								<td>
									<%=matchDTO.getTwoplayertournament().getGameDTO().getNetwork().getNetworkname() %>
								</td>
							</tr>
							<tr>
								<td>
									Date
								</td>
								<td>
									<%= matchDate %>
								</td>
							</tr>
							<tr>
								<td>
									Time
								</td>
								<td>
									<%= matchTime %>
								</td>
							</tr>
							<tr>
								<td>
									Opponent
								</td>
								<td>
									<%=playerDTO.getScreenname()%>
								</td>
							</tr>
							<tr>
								<td class="buttons" colspan="2">
									<a href="displayRanking.do?matchId=<%= matchId %>&amp;KeepThis=true&amp;TB_iframe=true&amp;height=1500&amp;width=650&amp;modal=true" class="modal thickbox green">Rate
          								Opponent NOW!!!</a>
							         <a href="<%=G4GConstants.VIEW_CARD%><%= matchId %><%=G4GConstants.THICKBOX%>" class="modal thickbox green">
							          	View Results</a>
								</td>
							</tr>
						</tbody>
					</table>
				</dd>
			</dl>
		</div>
		<ul class="floatRight messageOptions">
			<li>
				<a class="messageOptionsArea" href="#"></a>
			</li>
		</ul>
	</div>
</div>


