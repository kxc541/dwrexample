<%@page import="com.g4g.utils.G4GConstants"%>
<%@page import="com.g4g.utils.SessionUtil"%>
<%@page import="java.net.URLDecoder"%>
<%
	String add = request.getParameter(G4GConstants.ADD) != null ? request
			.getParameter(G4GConstants.ADD)
			: G4GConstants.BLANK;
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
%>

<div class="Avatar GameAvatar" id="platform<%= encryptedId %>">
<%
if(Boolean.valueOf(request.getParameter(G4GConstants.IS_LEGAL))) {
	if(add.equals(G4GConstants.FALSE)){
%>
		<a href="#" onclick="javascript:removeGameFromCurrentUser('platform', '<%=encryptedId %>');return false;"><img src="WebContent/<%=SessionUtil.getSiteId(request) %>/css/images/icons/MinusGame.png" class="AddGame" alt="Remove game"></a>
<%
	} else {
%>
		<a href="#" onclick="javascript:addGameToCurrentUser('platform', '<%=encryptedId %>');return false;"><img src="WebContent/<%=SessionUtil.getSiteId(request) %>/css/images/icons/AddGame.png" class="AddGame" alt="Add game"></a>
<%
	}
}
%>
	<div class="AvatarImage">
		<a href="displayGameDetails.do?gameId=<%=gameid%>">
			<img src="WebContent<%=decryptedImgSrc%>" alt="" class="GameImage">
		</a>
	</div>
</div>