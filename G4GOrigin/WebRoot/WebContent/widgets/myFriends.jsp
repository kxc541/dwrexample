<%@page import="java.util.List"%>
<%@page import="com.g4g.utils.G4GConstants"%>
<%@page import="com.g4g.dto.PlayerDTO"%>
<%
PlayerDTO userProfileDTO = (PlayerDTO) request.getAttribute(G4GConstants.PLAYERDTO);
List<PlayerDTO> playerFriendsList = (List<PlayerDTO>)request.getAttribute(G4GConstants.PLAYERFRIENDSLIST);
%>
<% if (playerFriendsList.size() > 0) { %>
<div id="MyFriendsWidget" class="Widget">
	<div class="WidgetHeader">
		<p><%= userProfileDTO.getScreenname() %>'s&nbsp;Friends</p>
	</div>
	<div class="WidgetContent">
	<% 
	for(int index=0;index<playerFriendsList.size();index++){
		PlayerDTO playerDTO = (PlayerDTO)playerFriendsList.get(index);	
	%>		
		<jsp:include page="../common/avatar.jsp">
			<jsp:param name="playerid" value="<%=playerDTO.getId() %>"/>
			<jsp:param name="avatarid" value="<%= (playerDTO.getAvatars() != null) ? playerDTO.getAvatars().getId() : null %>"/>
			<jsp:param name="screenname" value="<%=playerDTO.getScreenname() %>"/>
			<jsp:param name="isOnline" value="<%=playerDTO.isIsonline() %>"/>
			<jsp:param name="pictureId" value="<%=playerDTO.getPictureId() %>"/>
			<jsp:param name="showMenu" value="true"/>
			<jsp:param name="isLink" value="true"/>
		</jsp:include>
	<%
	}
	%>
	</div>
</div>
<% } %>