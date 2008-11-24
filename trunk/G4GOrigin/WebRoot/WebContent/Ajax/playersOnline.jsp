<%@page import="com.g4g.utils.G4GConstants"%>
<%@page import="com.g4g.utils.DataUtil"%>
<%@page import="java.net.URLDecoder"%>
<%@page import="com.g4g.utils.SessionUtil"%>
<%
	String userId = request.getParameter(G4GConstants.USER_ID) != null ? request
			.getParameter(G4GConstants.USER_ID)
			: G4GConstants.ZEROSTRING;
	int userid = Integer.parseInt(userId);
	String playerId = request.getParameter(G4GConstants.PLAYERID) != null ? request
			.getParameter(G4GConstants.PLAYERID)
			: G4GConstants.NONE;
	int playerid = Integer.parseInt(playerId);
	String screenname = request
			.getParameter(G4GConstants.SCREENNAME_DB) != null ? request
			.getParameter(G4GConstants.SCREENNAME_DB)
			: G4GConstants.NONE;
	String avgAmt = request
			.getParameter(G4GConstants.AVERAGE_AMOUNT) != null ? request
			.getParameter(G4GConstants.AVERAGE_AMOUNT)
			: G4GConstants.ZEROSTRING;
	int averageAmount = Integer.parseInt(avgAmt);
	String reputation = request
			.getParameter(G4GConstants.REPUTATION) != null ? request
			.getParameter(G4GConstants.REPUTATION)
			: G4GConstants.ZEROSTRING;
	int reputationInt = Integer.parseInt(reputation);
	String gameId = request.getParameter(G4GConstants.GAMEID) != null ? request
			.getParameter(G4GConstants.GAMEID)
			: G4GConstants.ZEROSTRING;
	int gameid = Integer.parseInt(gameId);
%>

<div class="Format1	clearfix">
	<div class="grid grid6 PlayerName">
		<img width="12" height="12" class="statusdot OnlineDotsTimer"
			border="0" alt="ONLINE" id="ankur jain"
			src="WebContent/<%=SessionUtil.getSiteId(request) %>/images/icons/status-online.png">
		<a href="displayProfile.do?profileUserId=<%=playerid %>"><%=URLDecoder.decode(screenname, G4GConstants.UTF8) %></a>
	</div>
	<div class="grid grid2">
		<span class="Reputation rep<%=reputationInt %>"> </span>
	</div>
	<div class="grid grid1">
		<%=averageAmount %>
	</div>
	<div class="grid grid1" style="display: none;">
		werty
	</div>
	<!-- if(userId == playerId ) -->
	<%if(userid == playerid){ %>
	<div class="gridRight grid2 option notavailable">
		Unavailable
	</div>
	<!-- if(userId != playerId ) -->
	<%}else{ %>
	<div class="gridRight grid2 option challenge">
		<a
			href="displayDirectedChallenge1.do?playerId=<%=playerid %>&gameId=<%=gameid %><%=G4GConstants.THICKBOX %>"
			class="modal thickbox">Challenge! </a>
	</div>
	<%} %>
</div>