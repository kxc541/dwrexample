<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>

<bean:define id="searchStr" value="" type="java.lang.String" />
<bean:define id="nextPages" value="0" type="java.lang.String" />
<bean:define id="prevPages" value="0" type="java.lang.String" />
<bean:define id="maxPage" value="0" type="java.lang.String" />
<bean:define id="totalGame" value="0" type="java.lang.String" />
<bean:define id="showAlls" value="false" type="java.lang.String" />
<bean:define id="allGame" value="0" type="java.lang.String" />

<logic:present name="searchString">
	<bean:define id="searchStr" name="searchString" type="java.lang.String" />
</logic:present>

<logic:present name="showAllGame">
	<bean:define id="showAlls" name="showAllGame" type="java.lang.String" />
</logic:present>

<logic:present name="totalGames">
	<bean:define id="totalGame" name="totalGames" type="java.lang.String" />
</logic:present>

<logic:present name="nextGamePage">
	<bean:define id="nextPages" name="nextGamePage" type="java.lang.String" />
</logic:present>

<logic:present name="prevGamePage">
	<bean:define id="prevPages" name="prevGamePage" type="java.lang.String" />
</logic:present>

<logic:present name="maxGamePages">
	<bean:define id="maxPage" name="maxGamePages" type="java.lang.String" />
</logic:present>

<logic:present name="allGames">
	<bean:define id="allGame" name="allGames" type="java.lang.String" />
</logic:present>


<logic:present name="gameSearchList">
	<bean:define id="gameSearchList" name="gameSearchList" />
	<logic:iterate id="searchedGame" name="gameSearchList"
		type="com.g4g.dto.SearchedGame" length="<%=totalGame %>">
		<div class="Format1 clearfix">
			<div class="floatLeft width80 CenterAvatar">
				<jsp:include page="../common/gameAvatar.jsp">
					<jsp:param name="isUser" value="lobby" />
					<jsp:param name="imgSrc"
						value="<%=searchedGame.getGameDTO().getImgsrc().trim()%>" />
					<jsp:param name="gameid"
						value="<%=searchedGame.getGameDTO().getId()%>" />
					<jsp:param name="gamename"
						value="<%=searchedGame.getGameDTO().getGamename()%>" />
					<jsp:param name="isGameavailable"
						value="<%=searchedGame.isGameAvailable()%>" />
					<jsp:param name="isNetworkAvailable"
						value="<%=searchedGame.isNetworkAvailable()%>" />
					<jsp:param name="showMenu" value="true" />
				</jsp:include>
				<!-- avatar -->
			</div>

			<div class="floatLeft width140">
				<h2>
					<%=searchedGame.getGameDTO().getGamename()%>
				</h2>
			</div>


			<div class="floatRight grid3">

				<span class="buttons"> <a href="displayGameLobbyPage.do"
					class="green">Game Lobby</a> <logic:greaterThan value="0"
						name="searchedGame" property="size">
						<a href="displayTournamentAction.do" class="green">Tournaments</a>
					</logic:greaterThan> <logic:lessEqual value="0" name="searchedGame" property="size">
						<a style="cursor: pointer;" class="green">Tournaments</a>
					</logic:lessEqual> </span>
			</div>

		</div>
	</logic:iterate>
	<!-- format -->
	<logic:greaterThan value="5" name="allGame">
		<logic:equal value="false" name="showAlls">
			<div class="SearchOptions">
				<p class="floatRight">
					<a style="cursor: pointer;"
						onclick="javascript:retriveGamePage(<%=nextPages%>,<%=maxPage%>,'<%=searchStr%>','<%=allGame %>')">Next
					</a>
					<a style="cursor: pointer"
						onclick="javascript:retriveGamePage(<%=prevPages%>,<%=maxPage%>,'<%=searchStr%>','<%=allGame %>')">Previous
					</a>
				</p>
				<p>
					<a style="cursor: pointer"
						onclick="javascript:retriveAllGames('<%=searchStr%>', <%=allGame %>)"> View
						all Games</a>
				</p>
			</div>
		</logic:equal>
	</logic:greaterThan>
</logic:present>
