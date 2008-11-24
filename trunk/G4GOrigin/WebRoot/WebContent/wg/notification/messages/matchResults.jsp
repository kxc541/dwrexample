<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>

<%@page import="com.g4g.utils.G4GConstants"%>
<%@page import="com.g4g.utils.DataUtil"%>
<%@page import="com.g4g.delegator.NetworkServiceDelegator"%>
<%@page import="com.g4g.delegator.GameServiceDelegator"%>
<%@page import="com.g4g.delegator.TwoPlayerTournamentServiceDelegator"%>
<%@page import="com.g4g.delegator.TwoPlayerMatchServiceDelegator"%>
<%@page import="com.g4g.dto.SearchCriteria"%>
<%@page import="com.g4g.dto.NetworkDTO"%>
<%@page import="com.g4g.dto.GameDTO"%>
<%@page import="com.g4g.dto.TwoplayertournamentDTO"%>
<%@page import="com.g4g.dto.TwoplayermatchDTO"%>
<%@page import="com.g4g.dto.MessageDTO"%>
<%@page import="com.g4g.delegator.MessageServiceDelegator"%>
<%@page import="com.g4g.utils.SessionUtil"%>
<%@page import="com.g4g.delegator.PlayerServiceDelegator"%>
<%@page import="com.g4g.dto.PlayerDTO"%>
<%@page import="com.g4g.utils.GamesUtil"%>
<%
	MessageDTO messageDTO = MessageServiceDelegator.getMessage(request.getParameter(G4GConstants.ID));
	PlayerDTO playerDTO = messageDTO.getPlayerByFromplayerid();
	TwoplayermatchDTO twoplayermatchDTO = new TwoplayermatchDTO();
	TwoplayertournamentDTO twoplayertournamentDTO = new TwoplayertournamentDTO();
	GameDTO gameDTO = new GameDTO();
	NetworkDTO networkDTO = new NetworkDTO();
	String date = G4GConstants.BLANK;
	String time = G4GConstants.BLANK;

	if(messageDTO.getMatchid() != null){
		SearchCriteria searchCriteria = new SearchCriteria();
		searchCriteria.setAttribute(G4GConstants.ID,messageDTO.getMatchid());
		twoplayermatchDTO = TwoPlayerMatchServiceDelegator.getList(searchCriteria).get(0);

		searchCriteria.removeAllAttribute();
		searchCriteria.setAttribute(G4GConstants.ID, twoplayermatchDTO.getTwoplayertournament().getId());
		twoplayertournamentDTO = TwoPlayerTournamentServiceDelegator.getList(searchCriteria).get(0);

		gameDTO = twoplayertournamentDTO.getGameDTO();

		searchCriteria.removeAllAttribute();
		searchCriteria.setAttribute(G4GConstants.ID, gameDTO.getNetwork().getId());
		networkDTO = NetworkServiceDelegator.getList(searchCriteria).get(0);

		date = DataUtil.getDate(DataUtil.getDate(twoplayermatchDTO.getScheduledstartdate().toString(), G4GConstants.DATE_YYYY_MM_DD_HH_MM_SS_sss), G4GConstants.DATE_DD_MM_YYYY) ;
		time = DataUtil.getDate(DataUtil.getDate(twoplayermatchDTO.getScheduledstartdate().toString(), G4GConstants.DATE_YYYY_MM_DD_HH_MM_SS_sss), G4GConstants.DATE_H_MM_A);
	}
	boolean isGameAvailable = GamesUtil.isGameAvailable(playerDTO.getId(), gameDTO.getId());
	boolean isNetworkAvailable = GamesUtil.isNetWorkAvailable(playerDTO.getId(), gameDTO.getNetwork().getId());

%>

<div class="hideDiv"
	id="body<%=request.getParameter(G4GConstants.ID)%>">
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
			<!-- avatar -->
		</div>
		<div class="messageInfo grid grid9">
			<dl class="">
				<%if(twoplayermatchDTO.getCompleteddate() == null){ %>
				<dt>
					<a><img src="WebContent/<%=SessionUtil.getSiteId(request)%>/css/images/icons/challenge.png"> Stale
						match swept into Incomplete state.</a>
				</dt>
				<%} %>
				<dd>
					<%if(twoplayermatchDTO.getCompleteddate() == null){ %>
					<p>
						This match has been set to Incomplete because we were unable to
						find results for it for a day since its starttime, which is
						<%=date %> <%=time %> Etc/GMT
					</p>
					<%} %>
					<p></p>
					<div class="grid grid2">
						<jsp:include page="../common/gameAvatar.jsp">
							<jsp:param name="imgSrc" value="<%=gameDTO.getImgsrc().trim() %>"/>
							<jsp:param name="gameid" value="<%=gameDTO.getId() %>"/>
							<jsp:param name="gamename" value="<%=gameDTO.getGamename() %>"/>
							<jsp:param name="isGameavailable" value="<%=isGameAvailable %>"/>
							<jsp:param name="isNetworkAvailable" value="<%=isNetworkAvailable %>"/>
							<jsp:param name="showMenu" value="true"/>
							<jsp:param name="isUser" value=""/>
						</jsp:include>
					</div>
					<table class="WidgetInfo Style1">
						<thead>
							<tr>
								<th colspan="2">
									<%=gameDTO.getGamename() %>
								</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<td>
									Console
								</td>
								<td>
									<%=networkDTO.getNetworkname() %>
								</td>
							</tr>
							<tr>
								<td>
									Date
								</td>
								<td>
									<%=date %>
								</td>
							</tr>
							<tr>
								<td>
									Time
								</td>
								<td>
									<%=time %>
								</td>
							</tr>
							<tr>
								<td>
									Opponent
								</td>
								<td>
									<%=playerDTO.getScreenname() %>
								</td>
							</tr>
							<tr>
								<td class="buttons" colspan="2">
									<a
										href="<%=G4GConstants.DISPLAY_RANKING %><%=twoplayermatchDTO.getId() %>"
										class="modal thickbox green">Rate Opponent NOW!!!</a>
									<a
										href="<%=G4GConstants.VIEW_CARD %><%=twoplayermatchDTO.getId() %>"
										class="modal thickbox green"> View Results</a>
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


