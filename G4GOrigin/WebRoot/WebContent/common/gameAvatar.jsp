<%@page import="com.g4g.utils.G4GConstants"%>
<%@page import="com.g4g.utils.DataUtil"%>
<%@page import="com.g4g.utils.SessionUtil"%>
<%
	String imgSrc = request.getParameter(G4GConstants.IMGSRC) != null?request.getParameter(G4GConstants.IMGSRC):G4GConstants.NONE;
	String gameName = request.getParameter(G4GConstants.IMGSRC) != null?request.getParameter(G4GConstants.GAMENAME):G4GConstants.NONE;
	int gameId = request.getParameter(G4GConstants.GAMEID_DB) != null?Integer.parseInt(request.getParameter(G4GConstants.GAMEID_DB)):0;
	boolean isGameAvailable = request.getParameter(G4GConstants.ISGAMEAVAILABLE) != null?Boolean.valueOf(request.getParameter(G4GConstants.ISGAMEAVAILABLE)):false;
	boolean isNetworkAvailable = request.getParameter(G4GConstants.ISNETWORKAVAILABLE) != null?Boolean.valueOf(request.getParameter(G4GConstants.ISNETWORKAVAILABLE)):false;
	boolean showMenu = request.getParameter(G4GConstants.SHOWMENU) != null?Boolean.valueOf(request.getParameter(G4GConstants.SHOWMENU)):false;
	String user = request.getParameter(G4GConstants.ISUSER);
%>

<div class="Avatar GameAvatar" id="game<%=DataUtil.encrypt(String.valueOf(gameId)) %>">
	<div class="AvatarImage">
		<img src="WebContent<%=imgSrc %>" alt="<%=gameName %>" />
		<%if(showMenu){ %>
		<div class="AvatarOptions">
			<h1>
				<a href="displayGameDetails.do?gameId=<%=gameId %>"><%=gameName %></a>
			</h1>

			<table>
				<tr>
					<td>
						<img src="WebContent/<%=SessionUtil.getSiteId(request) %>/css/images/icons/whitearrow.png">
					</td>
					<td>
						<a href="displayGameLobbyPage.do">Game Lobby</a>
					</td>
				</tr>
				<%
				if(isNetworkAvailable)  {
				if(isGameAvailable){ %>
				<tr>
					<td>
						<img src="WebContent/<%=SessionUtil.getSiteId(request) %>/css/images/icons/challenge.png">
					</td>
					<td>
						<a style="cursor: pointer;" onclick="javascript:removeGameFromCurrentUser('<%=user %>', '<%= DataUtil.encrypt(String.valueOf(gameId)) %>')">Remove Game!</a>
					</td>
					<!-- onclick="javascript:removeGameFromCurrentUser('game', 'v5749rcf0o38g')" -->
				</tr>
				<%} else {%>
				<tr>
					<td>
						<img src="WebContent/<%=SessionUtil.getSiteId(request) %>/css/images/icons/challenge.png">
					</td>
					<td>
						<a style="cursor: pointer;"
							onclick="javascript:addGameToCurrentUser('<%=user %>', '<%= DataUtil.encrypt(String.valueOf(gameId)) %>')">Add
							Game!</a>
					</td>
					<!-- onclick="javascript:removeGameFromCurrentUser('game', 'v5749rcf0o38g')" -->
				</tr>
				<%} }%>
				<tr class="AvatarClose">
					<td>
						<img src="WebContent/<%=SessionUtil.getSiteId(request) %>/css/images/icons/close.png">
					</td>
					<td>
						Close
					</td>
				</tr>

			</table>
		</div>
		<%} %>
		<!-- avatar options -->
		<em><a href="displayGameDetails.do?gameId=<%=gameId %>"><%=gameName%></a>
		</em>
	</div>
	<!-- avatar-image -->
</div>