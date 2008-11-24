<%@page import="com.g4g.utils.DataUtil"%>
<%@page import="com.g4g.utils.G4GConstants"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>

<bean:define id="searchStr" value="" type="java.lang.String" />
<bean:define id="nextPages" value="0" type="java.lang.String" />
<bean:define id="prevPages" value="0" type="java.lang.String" />
<bean:define id="maxPage" value="0" type="java.lang.String" />
<bean:define id="totalOpenChallenge" value="0" type="java.lang.String" />
<bean:define id="showAlls" value="false" type="java.lang.String" />
<bean:define id="allOpenChallenge" value="" type="java.lang.String" />
<bean:define id="offset" value="0" type="java.lang.String" />

<logic:present name="userOffset">
	<bean:define id="offset" name="userOffset" type="java.lang.String" />
</logic:present>

<logic:present name="searchString">
	<bean:define id="searchStr" name="searchString" type="java.lang.String" />
</logic:present>

<logic:present name="showAllOpenChallenge">
	<bean:define id="showAlls" name="showAllOpenChallenge" type="java.lang.String" />
</logic:present>

<logic:present name="totalOpenChallenges">
	<bean:define id="totalOpenChallenge" name="totalOpenChallenges"
		type="java.lang.String" />
</logic:present>

<logic:present name="nextOpenChallengePage">
	<bean:define id="nextPages" name="nextOpenChallengePage" type="java.lang.String" />
</logic:present>

<logic:present name="prevOpenChallengePage">
	<bean:define id="prevPages" name="prevOpenChallengePage" type="java.lang.String" />
</logic:present>

<logic:present name="maxOpenChallengePages">
	<bean:define id="maxPage" name="maxOpenChallengePages" type="java.lang.String" />
</logic:present>
<logic:present name="allOpenChallenges">
	<bean:define id="allOpenChallenge" name="allOpenChallenges"
		type="java.lang.String" />
</logic:present>

<logic:present name="openChallengeSearchList">
	<bean:define id="openChallengeSearchList"
		name="openChallengeSearchList" />
	<logic:iterate id="searchedOpenChallenge" name="openChallengeSearchList"
		type="com.g4g.dto.SearchedTournament" length="<%=totalOpenChallenge%>">

		<div class="Format1 clearfix">
			<div class="floatLeft width80 CenterAvatar">
				<jsp:include page="../common/gameAvatar.jsp">
					<jsp:param name="isUser" value="lobby" />
					<jsp:param name="imgSrc"
						value="<%=searchedOpenChallenge
										.getGameTournamentChallengeDTO()
										.getImgsrc().trim()%>" />
					<jsp:param name="gameid"
						value="<%=searchedOpenChallenge
										.getGameTournamentChallengeDTO()
										.getGameid()%>" />
					<jsp:param name="gamename"
						value="<%=searchedOpenChallenge
										.getGameTournamentChallengeDTO()
										.getGamename()%>" />
					<jsp:param name="isGameavailable"
						value="<%=searchedOpenChallenge.isGameAvailable()%>" />
					<jsp:param name="isNetworkAvailable"
						value="<%=searchedOpenChallenge.isNetworkAvailable()%>" />
					<jsp:param name="showMenu" value="true" />
				</jsp:include>
				<!-- avatar -->
			</div>
			<div class="floatLeft width140">
				<h2>
					<%=searchedOpenChallenge
										.getGameTournamentChallengeDTO()
										.getGamename()%>
				</h2>
				<p>
					Jackpot $<%=DataUtil.format(searchedOpenChallenge
										.getGameTournamentChallengeDTO()
										.getPayoutamount())%>
				</p>
			</div>
			<div class="floatLeft grid6">
				<ul>
					<li>
						<%=searchedOpenChallenge.getNoOfPlayers()%>&nbsp;players
						<logic:iterate id="gameoptionsDTO" name="searchedOpenChallenge"
							property="gameOptionsList">
							<br>
							<bean:write name="gameoptionsDTO" property="option" /> -
					<bean:write name="gameoptionsDTO" property="value" />
						</logic:iterate>
					</li>
					<li>
						$<%=searchedOpenChallenge
										.getGameTournamentChallengeDTO()
										.getEntryfee()%>
					</li>
					<li>
						<%=searchedOpenChallenge
										.getGameTournamentChallengeDTO()
										.getScheduledstartdate()%>
					</li>
				</ul>
			</div>
			<div class="floatRight grid3">
				<span class="buttons">
				<% if(searchedOpenChallenge.isGameAvailable()){ %>
					<a
						href="displayDirectChallenge2.do?twoplayermatchid=<%=searchedOpenChallenge
										.getGameTournamentChallengeDTO()
										.getMatchid()%><%=G4GConstants.THICKBOX %>"
						class="blue modal thickbox"> <span class="challenge">view
							details</span> </a>

				<%}else { %>
					<a style="cursor: pointer;" class="blue"> <span class="challenge">view
						details</span> </a>
				<%} %>
				</span>
			</div>
		</div>
		<!-- format -->
	</logic:iterate>
	<logic:greaterThan value="5" name="allOpenChallenge">
		<logic:equal value="false" name="showAlls">
			<div class="SearchOptions">
				<p class="floatRight">
					<a style="cursor: pointer;"
						onclick="javascript:retriveChallengePage(<%=nextPages%>,<%=maxPage%>,'<%=searchStr%>','<%=allOpenChallenge%>',<%=offset %>)">Next
					</a>
					<a style="cursor: pointer"
						onclick="javascript:retriveChallengePage(<%=prevPages%>,<%=maxPage%>,'<%=searchStr%>','<%=allOpenChallenge%>',<%=offset %>)">Previous
					</a>
				</p>
				<p>
					<a style="cursor: pointer"
						onclick="javascript:retriveAllChallenges('<%=searchStr%>', <%=allOpenChallenge%>,<%=offset %>)">
						View all Open Challenges</a>
				</p>
			</div>
		</logic:equal>
	</logic:greaterThan>
</logic:present>

