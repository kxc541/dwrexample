
<%@page import="com.g4g.utils.DateUtil"%>
<%@page import="com.g4g.delegator.TimeZoneServiceDelegator"%>
<%@page import="com.g4g.dto.TimeZoneDTO"%><%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>

<%@page import="com.g4g.utils.G4GConstants"%>
<%@page import="com.g4g.utils.DataUtil"%>
<%@page import="com.g4g.dto.PlayerDTO"%>
<%@page import="com.g4g.delegator.PlayerServiceDelegator"%>
<%@page import="com.g4g.delegator.TwoPlayerMatchServiceDelegator"%>
<%@page import="com.g4g.dto.MatchDTO"%>
<%@page import="com.g4g.utils.SessionUtil"%>
<%@page import="com.g4g.utils.GamesUtil"%>
<%@page import="com.g4g.delegator.GameServiceDelegator"%>
<%@page import="com.g4g.dto.GameDTO"%>

<bean:define id="siteId" value="wg" type="java.lang.String"/>
<logic:present name="siteId" >
<bean:define id="siteId" name="siteId" type="java.lang.String"/>
</logic:present>

<%
	PlayerDTO toPlayerDTO = PlayerServiceDelegator.getPlayer(Integer.parseInt(DataUtil.decrypt(request.getParameter(G4GConstants.TOID))));
	PlayerDTO fromPlayerDTO = PlayerServiceDelegator.getPlayer(Integer.parseInt(DataUtil.decrypt(request.getParameter(G4GConstants.FROMID))));
	MatchDTO matchDTO = TwoPlayerMatchServiceDelegator.getMatchInformation(Integer.parseInt(request.getParameter(G4GConstants.MATCHID)),request);

	String matchDate = DataUtil.getDate(DateUtil.getDateOfTimeZone(DataUtil.getDate(matchDTO.getScheduleDateTime(), G4GConstants.DATE_YYYY_MM_DD_HH_MM_SS_sss), toPlayerDTO.getTimezone().getOffset()), G4GConstants.DATE_DD_MM_YYYY);
	String matchTime = DataUtil.getDate(DateUtil.getDateOfTimeZone(DataUtil.getDate(matchDTO.getScheduleDateTime(), G4GConstants.DATE_YYYY_MM_DD_HH_MM_SS_sss), toPlayerDTO.getTimezone().getOffset()), G4GConstants.DATE_H_MM_A);
	GameDTO gameDTO = GameServiceDelegator.getGame(matchDTO.getGameId());
	boolean isGameAvailable = GamesUtil.isGameAvailable(fromPlayerDTO.getId(), matchDTO.getGameId());
	boolean isNetworkAvailable = GamesUtil.isNetWorkAvailable(fromPlayerDTO.getId(), gameDTO.getNetwork().getId());

%>

<div class="hideDiv" id="body<%= request.getParameter(G4GConstants.RANDOMID) %>">
	<div class="MessageContent clearfix">
		<div class="CloseButton">
			<a onclick="javascript:closeMessage(this)" id="close<%= request.getParameter(G4GConstants.RANDOMID) %>"
				href="javascript:{}">Close</a>
		</div>
		<div class="grid grid2">

			<jsp:include page="../../../common/avatar.jsp">
					<jsp:param name="playerid" value="<%=fromPlayerDTO.getId() %>"/>
					<jsp:param name="avatarid" value="<%= (fromPlayerDTO.getAvatars() != null) ? fromPlayerDTO.getAvatars().getId() : 0 %>"/>
					<jsp:param name="screenname" value="<%=fromPlayerDTO.getScreenname() %>"/>
					<jsp:param name="isOnline" value="<%=fromPlayerDTO.isIsonline() %>"/>
					<jsp:param name="pictureId" value="<%=fromPlayerDTO.getPictureId() %>"/>
					<jsp:param name="showMenu" value="false"/>
					<jsp:param name="isLink" value="false"/>
			</jsp:include>
		</div>
		<div class="messageInfo grid grid9">
			<dl class="">
				<dt>
					<a><img src="WebContent/<bean:write name="siteId"/>/css/images/icons/challenge.png"><%= fromPlayerDTO.getScreenname() %>
						has challenged you to a <%= matchDTO.getGameName() %> tournament!</a>
				</dt>
				<dd>
					<p>
						<%= fromPlayerDTO.getScreenname() %> has challenged You to a Game of <%= matchDTO.getGameName() %>! Please
						visit the challenge card to complete the process and register this
						challenge!
					</p>
					<p>
					</p>
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
								<td class="buttons" colspan="2">
									<a
										href="displayDirectChallenge2.do?<%= G4GConstants.TWO_PLAYER_MATCH_ID %>=<%= request.getParameter(G4GConstants.MATCHID) %>&amp;KeepThis=true&amp;TB_iframe=true&amp;height=1500&amp;width=650&amp;modal=true"
										class="modal thickbox red">Challenge Card</a>
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
