<div align="right">
	<br>
</div>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>

<bean:define id="siteId" value="wg" type="java.lang.String" />
<logic:present name="siteId">
	<bean:define id="siteId" name="siteId" type="java.lang.String" />
</logic:present>

<%@page import="com.g4g.dto.TwoplayertournamentDTO"%>
<%@page import="com.g4g.dto.GameDTO"%>
<%@page import="java.util.List"%>
<%@page import="com.g4g.dto.PlayerDTO"%>
<%@page import="com.g4g.utils.DataUtil"%>
<%@page import="com.g4g.dto.TwoplayermatchDTO"%>
<%@page import="com.g4g.dto.UserDTO"%>
<%@page import="com.g4g.utils.DateUtil"%>
<%@page import="com.g4g.dto.PlayergameDTO"%>
<%@page import="com.g4g.utils.ChallengeCardUpdateUtil"%>
<%@page import="com.g4g.delegator.TwoPlayerMatchServiceDelegator"%>
<%@page import="java.text.NumberFormat"%>
<%@page import="java.util.ArrayList"%>
<link rel="stylesheet" media="screen"
	href="WebContent/<bean:write name="siteId"/>/css/Tournaments.css">
<%@page import="com.g4g.dto.TournamentGameDTO"%>
<%@page import="com.g4g.utils.G4GConstants"%>
<%@page import="java.util.Set"%>
<%@page import="com.g4g.dto.GameoptionsDTO"%>
<%@page import="java.util.Iterator"%>



<bean:define id="viewMultiTierTournamentForm"
	name="viewMultiTierTournamentForm"
	type="com.g4g.forms.ViewMultiTierTournamentForm" />

<bean:define id="tournamentGameDTO" property="tournamentGameDTO"
	name="viewMultiTierTournamentForm" type="com.g4g.dto.TournamentGameDTO" />

<bean:define id="userDTO" name="userDTO" type="com.g4g.dto.UserDTO" />

<bean:define id="twoplayertournamentDTO"
	property="twoplayertournamentDTO" name="viewMultiTierTournamentForm"
	type="com.g4g.dto.TwoplayertournamentDTO" />

<bean:define id="twoPlayerMatchList" property="twoPlayerMatchList"
	name="viewMultiTierTournamentForm"
	type="java.util.List<TwoplayermatchDTO>" />

<bean:define id="playesList" property="playesList"
	name="viewMultiTierTournamentForm" type="java.util.List<PlayerDTO>" />

<bean:define id="gameDTO" property="gameDTO"
	name="viewMultiTierTournamentForm" type="com.g4g.dto.GameDTO" />



<%
	int totalPlayersInTournament = 0;
	int totalMatchesSize = 0;
	int gameListSize = 0;
	boolean isAvailable = false;
	if (request.getAttribute(G4GConstants.TOURNAMENTDETAILS) != null) {
		tournamentGameDTO = (TournamentGameDTO) request
				.getAttribute(G4GConstants.TOURNAMENTDETAILS);
		twoplayertournamentDTO = tournamentGameDTO
				.getTwoplayertournamentDTO();
		gameDTO = tournamentGameDTO.getGameDTO();
		twoPlayerMatchList = tournamentGameDTO.getTwoPlayerMatchList();
		playesList = (List<PlayerDTO>) request
				.getAttribute(G4GConstants.PLAYERS_IN_TOURNAMENT);
		totalPlayersInTournament = playesList.size();
		totalMatchesSize = twoPlayerMatchList.size();

		List<PlayergameDTO> gameList = (List<PlayergameDTO>) request
				.getAttribute(G4GConstants.GAMELIST);
		gameListSize = gameList.size();

		for (int index = 0; index < gameListSize; index++) {
			PlayergameDTO playergameDTO = gameList.get(index);
			if (playergameDTO.getGame().getId() == gameDTO.getId()) {
				isAvailable = true;
				break;
			} else {
				isAvailable = false;
			}
		}
	}
	NumberFormat numberFormat = NumberFormat.getNumberInstance();
	numberFormat.setMinimumFractionDigits(2);
%>

<div class="ContentWrapper clearfix" id="Tournaments">
	<div class="Sidebar">
		SidebarContent
	</div>
	<div class="PageContent" id="TournamentSingle">
		<!-- GlobalNav -->
		<div class="Format1 clearfix padTB10">
			<img src="WebContent<%=gameDTO.getTournamentbannerimgsrc()%>"
				class="tournamentBanner" alt="">
			<div class="grid grid3 margRight20">
				<img style="width: 100px;" src="WebContent<%=gameDTO.getImgsrc()%>"
					width="100px" height="177px" alt="">
			</div>
			<div class="grid grid8">
				<p>
					Lorem ipsum dolor sit ameted ultnisi ante , mi sit amet viverra
					faucibus, urna libero viverra nibh, sed dictum nisi mi et diam.
					Nulla nunc eros, convallis sed, varius ac, commodo et, magna. Proin
					vel risus. Vestibulum eu urna. Maecenas lobortis, pede ac dictum
					pulvinar, nibh ante vestibulum tortor, eget fermentum urna ipsum ac
					neque. Nam urna nulla, mollis blandit, pretium id, tristique vitae,
					neque. Etiam id tellus. Sed pharetra enim non nisl.
				</p>
				<ul class="WidgetInfo">
					<li>
						<a><img
								src="WebContent/<bean:write name="siteId"/>/css/images/icons/more.png"
								alt=""> All <%=gameDTO.getGamename()%> Tournaments</a>
					</li>
					<li>
						<a href="displayGameDetails.do?gameId=<%=gameDTO.getId()%>"><img
								src="WebContent/<bean:write name="siteId"/>/css/images/icons/more.png"
								alt=""> More about <%=gameDTO.getGamename()%></a>
					</li>
				</ul>
			</div>
			<div class="" style="margin-left: 500px;">
				<table class="row cols rowHeader">
					<tr>
						<td>
							<h1><%=gameDTO.getGamename()%></h1>
						</td>
						<td class="buttons" style="text-align: right;">
							<img
								src="WebContent/<bean:write name="siteId"/>/css/images/icons/xbox360_med.png"
								alt="">
						</td>
					</tr>
				</table>
				<%
					Set<GameoptionsDTO> list = tournamentGameDTO.getGameOptionsList();
					Iterator<GameoptionsDTO> iterator = list.iterator();
					while (iterator.hasNext()) {
						GameoptionsDTO gameoptionsDTO = iterator.next();
						if (gameoptionsDTO != null) {
				%>
				<table class="row cols">
					<tr>
						<td>
							<table>
								<tr>
									<th><%=gameoptionsDTO.getOption()%></th>
									<td class="col2"><%=gameoptionsDTO.getValue()%></td>
								</tr>
							</table>
						</td>
					</tr>
				</table>
				<%
					}
					}
				%>
				<table class="row cols">
					<tr>
						<td>
							<table>
								<tr>
									<th>
										Start Date
									</th>
									<td class="col2">
										<%=DataUtil.getDate(DateUtil.getDateOfTimeZone(
							tournamentGameDTO.getScheduledstartdate(), userDTO
									.getOffset()),
							G4GConstants.DATE_EEE_MMM_DD_YYYY_AT_hh_MM_A)%>
									</td>
								</tr>
							</table>
						</td>
					</tr>
				</table>
				<table class="row cols">
					<tr>
						<td>
							<table>
								<tr>
									<th>
										Player Fee
									</th>
									<td class="col2">
										$<%=numberFormat.format(twoplayertournamentDTO
									.getEntryfee())%>
									</td>
								</tr>
							</table>
						</td>
					</tr>
				</table>
				<table class="row cols">
					<tr>
						<td>
							<table>
								<tr>
									<th>
										Player Winnings
									</th>
									<td class="col2">
										$<%=numberFormat.format(twoplayertournamentDTO.getJackpot())%>
									</td>
								</tr>
							</table>
						</td>
					</tr>
				</table>
				<div class="Options buttons">

				</div>
			</div>

			<div class="userlist clearfix">
				<h1>
					Who's in
				</h1>
				<p>
					<%=totalPlayersInTournament%>
					of
					<%=tournamentGameDTO.getNo_of_players()%>
					Users so far:
				</p>
				<div class="Format4 clearfix">
					<%
						for (int index = 0; index < totalPlayersInTournament; index++) {
							PlayerDTO playerDTO = playesList.get(index);
					%>
					<jsp:include page="common/avatar.jsp">
						<jsp:param name="playerid" value="<%=playerDTO.getId()%>" />
						<jsp:param name="avatarid"
							value="<%=(playerDTO.getAvatars() != null) ? playerDTO
						.getAvatars().getId() : 0%>" />
						<jsp:param name="screenname"
							value="<%=playerDTO.getScreenname()%>" />
						<jsp:param name="isOnline" value="<%=playerDTO.isIsonline()%>" />
						<jsp:param name="pictureId" value="<%=playerDTO.getPictureId()%>" />
						<jsp:param name="showMenu" value="true" />
						<jsp:param name="isLink" value="true" />
					</jsp:include>
					<%
						}
					%>
					<!-- avatar -->
				</div>
			</div>
		</div>

		<div class="Format1 clearfix padAll10">
			<h1>
				<%=twoplayertournamentDTO.getLevels()%>
				Tiers
			</h1>
			<h2>
				Tournament Brackets
			</h2>
			<div class="TabContent" id="brackets">
				<div class="clearfix" id="South East">
					<h1>
						<%=gameDTO.getGamename()%>
						<%=DataUtil.getDate(DateUtil.getDateOfTimeZone(
							tournamentGameDTO.getScheduledstartdate(), userDTO
									.getOffset()),
							G4GConstants.DATE_EEE_MMM_DD_YYYY_AT_hh_MM_A)%>
						$<%="TBD"%>
					</h1>
					<div class="BracketContainer clearfilx">
						<%
							for (int index = 0; index < twoplayertournamentDTO.getLevels(); index++) {
								int currentLevel = twoplayertournamentDTO.getLevels()
										- (twoplayertournamentDTO.getLevels() - (index + 1));
						%>
						<div class="col level<%=currentLevel%>">
							<h2>
								<%
									if (currentLevel == (twoplayertournamentDTO.getLevels() - 2)) {
											if (currentLevel == 1) {
								%>
								Level
								<%=currentLevel%>
								<%
									} else {
								%>
								Quarter Finals
								<%
									}
										} else if (currentLevel == (twoplayertournamentDTO.getLevels() - 1)) {
											if (currentLevel == 1) {
								%>
								Level
								<%=currentLevel%>
								<%
									} else {
								%>
								Semi Finals
								<%
									}
										} else if (currentLevel == (twoplayertournamentDTO.getLevels())) {
								%>
								Finals
								<%
									} else {
								%>
								Level
								<%=currentLevel%>
								<%
									}
								%>
							</h2>

							<%
								for (int matchIndex = 0; matchIndex < totalMatchesSize; matchIndex++) {
										TwoplayermatchDTO twoplayermatchDTO = twoPlayerMatchList
												.get(matchIndex);
										Integer playerOneId = null;
										if (twoplayermatchDTO.getPlayeroneid() != null)
											playerOneId = twoplayermatchDTO.getPlayeroneid()
													.getId();
										Integer playerTwoId = null;
										if (twoplayermatchDTO.getPlayertwoid() != null)
											playerTwoId = twoplayermatchDTO.getPlayertwoid()
													.getId();
										if (twoplayermatchDTO.getLevel() == currentLevel) {
							%>
							<ul>
								<li class="top">
									<a><%=DataUtil
												.getDate(
														DateUtil
																.getDateOfTimeZone(
																		twoplayermatchDTO
																				.getScheduledstartdate(),
																		userDTO
																				.getOffset()),
														G4GConstants.DATE_EEE_MMM_DD_YYYY_AT_hh_MM_A)%></a>
								</li>
								<%
									if (TwoPlayerMatchServiceDelegator.getDtoForChallenge(
														twoplayertournamentDTO.getId(), request) != null) {

													if (isAvailable) {
														if (currentLevel == 1) {
								%>
								<li class="bottom">
									<a
										href="displayDirectChallenge2.do?<%=G4GConstants.TOURNAMENT_ID%>=<%=twoplayertournamentDTO.getId()%><%=G4GConstants.THICKBOX %>"
										class="modal thickbox">Join Now!!!!</a>
								</li>
								<%
									//}
														}
													}
												} else {
								%>
								<li class="bottom">
									<a style="cursor: pointer;"> <%
 	if (twoplayermatchDTO.getPlayeroneid() != null) {
 %> <%=twoplayermatchDTO.getPlayeroneid()
												.getScreenname()%> <%
 	if (twoplayermatchDTO.getPlayertwoid() != null) {
 %> vs <%=twoplayermatchDTO
													.getPlayertwoid()
													.getScreenname()%> <%
 	}
 					}
 %> </a>
								</li>
								<%
									}
								%>
							</ul>
							<%
								}
									}
							%>
						</div>
						<%
							}
						%>
					</div>
				</div>
			</div>
		</div>
		<script type="text/javascript"> $(document).ready(function(){ $('.Widget').OpenClose(); $('.Avatar').ActiveAvatar(); });
		</script>
	</div>
</div>

<%
	ChallengeCardUpdateUtil.clearSession(request);
%>
