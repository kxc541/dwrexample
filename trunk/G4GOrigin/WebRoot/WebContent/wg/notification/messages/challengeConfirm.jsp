
<%@page import="com.g4g.utils.DateUtil"%><%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>

<%@page import="com.g4g.utils.G4GConstants"%>
<%@page import="com.g4g.utils.DataUtil"%>
<%@page import="com.g4g.dto.PlayerDTO"%>
<%@page import="com.g4g.delegator.PlayerServiceDelegator"%>
<%@page import="com.g4g.delegator.TwoPlayerMatchServiceDelegator"%>
<%@page import="com.g4g.dto.MatchDTO"%>
<%@page import="com.g4g.utils.SessionUtil"%>
<%@page import="com.g4g.utils.GamesUtil"%>
<%@page import="com.g4g.dto.GameDTO"%>
<%@page import="com.g4g.delegator.GameServiceDelegator"%>

<%
	PlayerDTO toPlayerDTO = PlayerServiceDelegator.getPlayer(Integer.parseInt(DataUtil.decrypt(request.getParameter(G4GConstants.TOID))));
	PlayerDTO fromPlayerDTO = PlayerServiceDelegator.getPlayer(Integer.parseInt(DataUtil.decrypt(request.getParameter(G4GConstants.FROMID))));
	MatchDTO matchDTO = TwoPlayerMatchServiceDelegator.getMatchInformation(Integer.parseInt(request.getParameter(G4GConstants.MATCHID)), request);
	String matchDate = DataUtil.getDate(DateUtil.getDateOfTimeZone(DataUtil.getDate(matchDTO.getScheduleDateTime(), G4GConstants.DATE_YYYY_MM_DD_HH_MM_SS_sss), fromPlayerDTO.getTimezone().getOffset()), G4GConstants.DATE_DD_MM_YYYY);
	String matchTime = DataUtil.getDate(DateUtil.getDateOfTimeZone(DataUtil.getDate(matchDTO.getScheduleDateTime(), G4GConstants.DATE_YYYY_MM_DD_HH_MM_SS_sss), fromPlayerDTO.getTimezone().getOffset()), G4GConstants.DATE_H_MM_A);
	GameDTO gameDTO = GameServiceDelegator.getGame(matchDTO.getGameId());
	boolean isGameAvailable = GamesUtil.isGameAvailable(fromPlayerDTO.getId(), matchDTO.getGameId());
	boolean isNetworkAvailable = GamesUtil.isNetWorkAvailable(fromPlayerDTO.getId(), gameDTO.getNetwork().getId());
%>

<div class="hideDiv"
	id="body<%= request.getParameter(G4GConstants.RANDOMID) %>">
	<div class="MessageContent clearfix">
		<div class="CloseButton">
			<a onclick="javascript:closeMessage(this)"
				id="close<%= request.getParameter(G4GConstants.RANDOMID) %>"
				href="javascript:{}">Close</a>
		</div>

		<div class="messageInfo grid grid9">
			<dl class="">
				<dt>
					<a><img src="WebContent/<%=SessionUtil.getSiteId(request) %>/css/images/icons/challenge.png">Match
						Confirmation</a>
				</dt>
				<dd>
					<p>
						You are confirmed to play <%= fromPlayerDTO.getScreenname() %> in a <%= matchDTO.getGameName() %> Match. You
						will receive an alert about 30 minutes prior to start time
					</p>
					<p></p>
					<div class="grid grid2">
						<jsp:include page="../../../common/gameAvatar.jsp">
							<jsp:param name="imgSrc" value="<%=matchDTO.getGameImageSrc().trim() %>"/>
							<jsp:param name="gameid" value="<%=matchDTO.getGameId() %>"/>
							<jsp:param name="gamename" value="<%=matchDTO.getGameName() %>"/>
							<jsp:param name="isGameavailable" value="<%=isGameAvailable %>"/>
							<jsp:param name="isNetworkAvailable" value="<%=isNetworkAvailable %>"/>
							<jsp:param name="showMenu" value="false"/>
							<jsp:param name="isUser" value=""/>
						</jsp:include>
					</div>
					<table class="WidgetInfo Style1">
						<thead>
							<tr>
								<th colspan="2">
									<%= matchDTO.getGameName() %>
								</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<td>
									Console
								</td>
								<td>
									<%= matchDTO.getConsole() %>
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
									<%= fromPlayerDTO.getScreenname() %>
								</td>
							</tr>
							<tr>
								<td>
									Confirmation #: 1000026
								</td>
								<td class="buttons" colspan="2">
									<a
										href="displayDirectChallenge2.do?<%= G4GConstants.TWO_PLAYER_MATCH_ID %>=<%= request.getParameter(G4GConstants.MATCHID) %>&amp;<%=G4GConstants.THICKBOX %>"
										class="modal thickbox green">Challenge Card</a>
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

