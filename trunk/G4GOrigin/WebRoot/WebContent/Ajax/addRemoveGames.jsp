<%@page import="com.g4g.utils.G4GConstants"%>
<%@page import="java.net.URLDecoder"%>
<%@page import="com.g4g.utils.SessionUtil"%>

<%
	String add = request.getParameter(G4GConstants.ADD) != null ? request
			.getParameter(G4GConstants.ADD)
			: G4GConstants.NONE;
	String encryptedId = request.getParameter(G4GConstants.ENCRYPTEDID) != null ? request
			.getParameter(G4GConstants.ENCRYPTEDID)
			: G4GConstants.BLANK;
	String imgSrc = request.getParameter(G4GConstants.IMGSRC) != null ? request
			.getParameter(G4GConstants.IMGSRC)
			: G4GConstants.BLANK;
	String decryptedImgSrc = URLDecoder.decode(imgSrc, G4GConstants.UTF8);
	String gameId = request.getParameter(G4GConstants.GAMEID) != null ? request
			.getParameter(G4GConstants.GAMEID)
			: G4GConstants.ZEROSTRING;
	int gameid = Integer.parseInt(gameId);

if(add.equals(G4GConstants.REMOVE)){

%>
<!-- Game Removed -->
<a
	onclick="javascript:addGameToCurrentUser('platform', '<%=encryptedId %>')"
	style="cursor: pointer;"><img
		src="WebContent/<%=SessionUtil.getSiteId(request) %>/css/images/icons/AddGame.png" class="AddGame">
</a>
<%}else if(add.equals(G4GConstants.ADD)){

 %>
<!-- Game Added -->
<a
	onclick="javascript:removeGameFromCurrentUser('platform', '<%=encryptedId %>')"
	style="cursor: pointer;"><img
		src="WebContent/<%=SessionUtil.getSiteId(request) %>/css/images/icons/MinusGame.png" class="AddGame">
</a>
<%} %>
<div class="AvatarImage">
	<a style="cursor: pointer;" href="displayGameDetails.do?gameId=<%=gameid %>"><img
			style="width: 60px;"
			src="WebContent<%=decryptedImgSrc %>">
	</a>
</div>