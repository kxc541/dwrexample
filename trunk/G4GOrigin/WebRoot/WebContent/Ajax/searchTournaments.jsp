<%@page import="com.g4g.utils.DataUtil"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>

<bean:define id="searchStr" value="" type="java.lang.String" />
<bean:define id="nextPages" value="0" type="java.lang.String" />
<bean:define id="prevPages" value="0" type="java.lang.String" />
<bean:define id="maxPage" value="0" type="java.lang.String" />
<bean:define id="totalTournament" value="0" type="java.lang.String" />
<bean:define id="showAlls" value="false" type="java.lang.String" />
<bean:define id="allTournament" value="" type="java.lang.String" />
<bean:define id="offset" value="0" type="java.lang.String" />

<logic:present name="searchString">
	<bean:define id="searchStr" name="searchString" type="java.lang.String" />
</logic:present>

<logic:present name="showAllTournament">
	<bean:define id="showAlls" name="showAllTournament" type="java.lang.String" />
</logic:present>

<logic:present name="totalTournaments">
	<bean:define id="totalTournament" name="totalTournaments"
		type="java.lang.String" />
</logic:present>

<logic:present name="nextTournamentPage">
	<bean:define id="nextPages" name="nextTournamentPage" type="java.lang.String" />
</logic:present>

<logic:present name="prevTournamentPage">
	<bean:define id="prevPages" name="prevTournamentPage" type="java.lang.String" />
</logic:present>

<logic:present name="maxTournamentPages">
	<bean:define id="maxPage" name="maxTournamentPages" type="java.lang.String" />
</logic:present>

<logic:present name="allTournaments">
	<bean:define id="allTournament" name="allTournaments" type="java.lang.String" />
</logic:present>

<logic:present name="userOffset">
	<bean:define id="offset" name="userOffset" type="java.lang.String" />
</logic:present>

<logic:present name="tournamentSearchList">
	<bean:define id="tournamentSearchList" name="tournamentSearchList" />
	<logic:iterate id="searchedTournament" name="tournamentSearchList"
		type="com.g4g.dto.SearchedTournament" length="<%=totalTournament %>">
<div class="Format1 clearfix">
	<div class="floatLeft width80 CenterAvatar">
		<jsp:include page="../common/gameAvatar.jsp">
			<jsp:param name="isUser" value="lobby" />
			<jsp:param name="imgSrc"
				value="<%=searchedTournament.getGameTournamentChallengeDTO().getImgsrc().trim()%>" />
			<jsp:param name="gameid" value="<%=searchedTournament.getGameTournamentChallengeDTO().getGameid()%>" />
			<jsp:param name="gamename" value="<%=searchedTournament.getGameTournamentChallengeDTO().getGamename()%>" />
			<jsp:param name="isGameavailable" value="<%=searchedTournament.isGameAvailable()%>" />
			<jsp:param name="isNetworkAvailable"
								value="<%=searchedTournament.isNetworkAvailable()%>" />
			<jsp:param name="showMenu" value="true" />
		</jsp:include>
		<!-- avatar -->
	</div>
	<div class="floatLeft width140">
		<h2>
			<%=searchedTournament.getGameTournamentChallengeDTO().getGamename()%>
		</h2>
		<p>
			Jackpot $<%=DataUtil.format(searchedTournament.getGameTournamentChallengeDTO().getPayoutamount())%>
		</p>
	</div>
	<div class="floatLeft grid6">
		<ul>
			<li>
				<%=searchedTournament.getNoOfPlayers()%>&nbsp;players
				<logic:iterate id="gameoptionsDTO" name="searchedTournament" property="gameOptionsList">
				<br>
					<bean:write name="gameoptionsDTO" property="option"/> -
					<bean:write name="gameoptionsDTO" property="value"/>
				</logic:iterate>
			</li>
			<li>
				$<%=searchedTournament.getGameTournamentChallengeDTO().getEntryfee() %>
			</li>
			<li>
				<%=searchedTournament.getGameTournamentChallengeDTO().getScheduledstartdate() %>
			</li>
		</ul>
	</div>
	<div class="floatRight grid3">
		<span class="buttons"> <a
			href="displayViewMutiTierTournament.do?tournamentId=<%=searchedTournament.getTwoplayertournamentDTO().getId() %>"
			class="blue"> <span class="challenge">view details</span>
		</a>
		</span>
	</div>
</div>
</logic:iterate>
<logic:greaterThan value="5" name="allTournament">
		<logic:equal value="false" name="showAlls">
			<div class="SearchOptions">
				<p class="floatRight">
					<a style="cursor: pointer;"
						onclick="javascript:retriveTournamentPage(<%=nextPages%>,<%=maxPage%>,'<%=searchStr%>','<%=allTournament %>',<%=offset %>)">Next
					</a>
					<a style="cursor: pointer"
						onclick="javascript:retriveTournamentPage(<%=prevPages%>,<%=maxPage%>,'<%=searchStr%>','<%=allTournament %>',<%=offset %>)">Previous
					</a>
				</p>
				<p>
					<a style="cursor: pointer"
						onclick="javascript:retriveAllTournament('<%=searchStr%>', <%=allTournament %>,<%=offset %>)"> View
						all Tournaments</a>
				</p>
			</div>
		</logic:equal>
	</logic:greaterThan>
</logic:present>
<!-- format -->
