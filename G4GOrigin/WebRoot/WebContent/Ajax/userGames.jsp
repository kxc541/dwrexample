<%@page import="com.g4g.utils.G4GConstants"%>
<%@page import="com.g4g.utils.DataUtil"%>
<%@page import="java.net.URLDecoder"%>

<%
	String gameId = request.getParameter(G4GConstants.ID);
	String imgSrc = URLDecoder.decode(request.getParameter(G4GConstants.IMGSRC), G4GConstants.UTF8);
%>
<div class="Avatar GameAvatar" id="user<%= gameId %>">
	<a onclick="javascript:removeGameFromCurrentUser('user', '<%= gameId %>')" style="cursor: pointer;">
		<img src="WebContent/wg/css/images/icons/MinusGame.png" class="AddGame" alt="Remove Game">
	</a>
	<div class="AvatarImage">
		<a href="displayGameDetails.do?gameId=<%= DataUtil.decrypt(gameId) %>">
			<img src="WebContent<%= imgSrc %>" alt="" class="GameImage">
		</a>
	</div>
</div>