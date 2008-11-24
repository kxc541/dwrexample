<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>

<%@page import="com.g4g.utils.DataUtil"%>
<%@page import="com.g4g.dto.PlayerDTO"%>
<%@page import="com.g4g.utils.G4GConstants"%>
<%@page import="com.g4g.utils.GamesUtil"%>
<%@page import="com.g4g.dto.OpenMatchDTO"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.g4g.dto.TwoplayertournamentDTO"%>
<%@page import="com.g4g.utils.TournamentsUtil"%>
<%@page import="com.g4g.delegator.TwoPlayerTournamentServiceDelegator"%>
<%@page import="com.g4g.delegator.PlayerServiceDelegator"%>
<%@page import="com.g4g.delegator.AvatarsServiceDelegator"%>

<bean:define id="siteId" value="wg" type="java.lang.String"/>
<logic:present name="siteId" >
	<bean:define id="siteId" name="siteId" type="java.lang.String"/>
</logic:present>


<%
	int playerId = Integer.parseInt(request.getParameter(G4GConstants.PLAYERID));
	PlayerDTO playerDTO_this = PlayerServiceDelegator.getPlayer(playerId);
	int gameId = Integer.parseInt(request.getParameter(G4GConstants.GAMEID));
	int networkId = Integer.parseInt(request.getParameter(G4GConstants.NETWORKID_DB));
	//GameDTO gameDTO = (GameDTO)request.getAttribute(G4GConstants.GAMEDTO);
	boolean isGameAvailable = GamesUtil.isGameAvailable(playerDTO_this.getId(), gameId);
	boolean isNetworkAvailable = GamesUtil.isNetWorkAvailable(playerDTO_this.getId(), networkId);
	boolean isStateIllegal = request.getParameter(G4GConstants.ISSTATEILLEGAL) != null ? Boolean.valueOf(request
					.getParameter(G4GConstants.ISSTATEILLEGAL)) : false;
	List<OpenMatchDTO> matches = new ArrayList<OpenMatchDTO>();
	matches = (List<OpenMatchDTO>) TwoPlayerTournamentServiceDelegator.getOpenMatchesDetails(gameId, playerId);
%>
<div class="OpenTournamentsTimer"></div>
<div id="OpenMatches" class="Widget">
<%
if (matches.size() == 0) {
	if (isNetworkAvailable) {
		if (isGameAvailable) {
	%>
			<div class="btn buttons clearfix">
				<a href="<%=DataUtil.getBasePath(request)%>displayDirectedChallenge1.do?gameId=<%=gameId%><%=G4GConstants.THICKBOX %>" class="thickbox blue">Create an Open Match</a>
			</div>
	<%
		}
	}
}
%>
	<div class="WidgetContent clearfix">
	<%
		for (int index = 0; index < matches.size(); index++) {
			OpenMatchDTO openMatchDTO = matches.get(index);
			PlayerDTO playerDTO = openMatchDTO.getPlayerDTO();
			int defaultAvatarId = AvatarsServiceDelegator.getList().get(0)
					.getId();
			int avatarId = 0;
			if (playerDTO.getAvatars() == null) {
				if (playerDTO.getPictureId() == null)
					avatarId = defaultAvatarId;
			} else {
				avatarId = playerDTO.getAvatars().getId();
			}
			TwoplayertournamentDTO twoplayertournamentDTO = openMatchDTO.getTwoplayertournamentDTO();
		if((isStateIllegal && twoplayertournamentDTO.getEntryfee() == 0) || (!isStateIllegal)){
	%>
		<div class="ChallengeCard clearfix">
			<div class="Organizer">
				<dl>
					<dt>
						<span>Entry Fee:</span> <h4>$<%=DataUtil.format(twoplayertournamentDTO.getEntryfee())%></h4>
					</dt>
					<dd class="buttons">
						<a href="displayDirectChallenge2.do?<%=G4GConstants.TWO_PLAYER_MATCH_ID%>=<%=openMatchDTO.getMatchid()%>" class="thickbox blue">details</a>
						<!-- ---------------------------------------- --
						find the acceptChallenge page for details, above
						 --- ---------------------------------------- -->
						<!-- /cgi-bin/WebObjects/g4g.woa/wa/showObjectModal?id=1000031&entityName=Tournament&KeepThis=true&TB_iframe=true&height=1500&width=650&modal=true -->
					</dd>
				</dl>
			</div>
			<div class="Opponent">
				<jsp:include page="common/avatar.jsp">
					<jsp:param name="playerid" value="<%=playerDTO_this.getId()%>" />
					<jsp:param name="avatarid" value="<%=avatarId%>" />
					<jsp:param name="screenname" value="<%=playerDTO.getScreenname()%>" />
					<jsp:param name="isOnline" value="<%=playerDTO.isIsonline()%>" />
					<jsp:param name="pictureId" value="<%=playerDTO.getPictureId()%>"/>
					<jsp:param name="showMenu" value="false" />
					<jsp:param name="isLink" value="true" />
				</jsp:include>
			</div>
			<div class="GameDetails">
				<%
				int noOfPlayers = TournamentsUtil.getNoOfPlayers(Integer.parseInt(twoplayertournamentDTO.getLevels().toString()));
				Double entryfeedbl = Double.valueOf(twoplayertournamentDTO.getEntryfee().toString());
				Double housefeedbl = Double.valueOf(twoplayertournamentDTO.getHousefeeperplayer().toString());
				%>
				<h3>$<%=DataUtil.format(openMatchDTO.getPayoutamount())%></h3>
			</div>
		</div>
		<!-- end ChallengeCard -->
	<%
		}} %>
	</div>
	<!-- widget content -->
</div>
