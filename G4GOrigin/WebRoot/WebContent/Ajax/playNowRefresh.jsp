
<%@page import="com.g4g.utils.G4GConstants"%><%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>

<logic:present name="playNowList">
<bean:define id="playNowList" name="playNowList" />
	<logic:iterate id="openMatches" name="playNowList" type="com.g4g.dto.PlayNowOpenMatches">
		<div class="PlayNowGame">
			<div class="Avatar GameAvatar"
				id="game<%=openMatches.getEncryptedId()%>">
				<div class="AvatarImage">
					<img
						src="WebContent<%=openMatches.getPlayNowDTO().getGameImgSrc()%>"
						alt="<%=openMatches.getPlayNowDTO().getGameName()%>">
				</div>
			</div>
			<div class="copy">
				<h4><%=openMatches.getScheduledDate()%></h4>
				<h3>
					<a
						href="displayGameDetails.do?gameId=<%=openMatches.getPlayNowDTO().getGameId()%>"><%=openMatches.getPlayNowDTO().getGameName()%></a>
				</h3>
				<p>
					Entry Fee $<%=openMatches.getTwoplayermatchDTO().getTwoplayertournament().getEntryfee()%></p>
				<p>
					vs
					<a
						href="displayProfile.do?profileUserId=<%=openMatches.getPlayNowDTO().getPlayerId()%>"><%=openMatches.getPlayNowDTO().getPlayerScreenName()%></a>
				</p>
				<div class="buttons">
					<a
						href="WebContent/sendMessage.jsp?friendId=<%=openMatches.getPlayNowDTO().getPlayerScreenName()%>&TB_iframe=true&height=1000&width=650&modal=true"
						class="modal thickbox button green inline"><span
						class="message">Message</span> </a>
					<a
						href="displayDirectChallenge2.do?twoplayermatchid=<%=openMatches.getPlayNowDTO().getMatchId()%><%=G4GConstants.THICKBOX %>"
						class="modal thickbox blue"><span class="challenge">View
							Details</span> </a>
				</div>
			</div>
		</div>
		<div class="clear"></div>
	</logic:iterate>
</logic:present>
<logic:notPresent name="playNowList">
	<h3>
		No Open Matches
	</h3>
</logic:notPresent>
<!-- Open Tournaments  -->
<logic:present name="openTournamentList">
	<bean:define id="openTournamentList" name="openTournamentList" />
	<logic:iterate id="openTournaments" name="openTournamentList"
		type="com.g4g.dto.PlayNowOpenTournaments">

			<div class="PlayNowGame">
				<div class="Avatar GameAvatar" id="game<%=openTournaments.getEncryptedId()%>">
					<div class="AvatarImage">
						<img src="WebContent<%=openTournaments.getTournamentGameDTO().getGameDTO().getImgsrc()%>" alt="<%=openTournaments.getTournamentGameDTO().getGameDTO().getGamename()%>">
					</div>
				</div>
				<div class="copy">
					<h4><%=openTournaments.getScheduledDate()%></h4>
					<h3><a href="displayGameDetails.do?gameId=<%=openTournaments.getTournamentGameDTO().getGameDTO().getId()%>"><%=openTournaments.getTournamentGameDTO().getGameDTO().getGamename()%></a></h3>
					<p>Entry Fee $<%=openTournaments.getTournamentGameDTO().getTwoplayertournamentDTO().getEntryfee()%></p>
					<logic:iterate id="gameOptionsDTO" name="openTournaments" property="gameOptionsList" type="com.g4g.dto.GameoptionsDTO">
						<bean:write name="gameOptionsDTO" property="option"/>:<bean:write name="gameOptionsDTO" property="value"/>
					</logic:iterate>
					<div class="buttons">
						<a href="displayViewMutiTierTournament.do?tournamentId=<%=openTournaments.getTournamentGameDTO().getTwoplayertournamentDTO().getId()%>"><span class="challenge">View Details</span></a>
					</div>
				</div>
			</div>
			<div class="clear"></div>
			</logic:iterate>
		</logic:present>
	<logic:notPresent name="openTournamentList">
		<h3>No Open Tournaments</h3>
</logic:notPresent>