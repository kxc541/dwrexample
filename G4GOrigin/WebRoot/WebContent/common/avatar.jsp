<%@page import="com.g4g.utils.G4GConstants"%>
<%@page import="com.g4g.utils.DataUtil"%>
<%@page import="com.g4g.utils.SessionUtil"%>
<%@page import="com.g4g.dto.PlayerDTO"%>
<%@page import="com.g4g.utils.FriendsUtil"%>
<%@page import="java.net.URLDecoder"%>
<%@page import="com.g4g.dto.AvatarsDTO"%>
<%@page import="com.g4g.plugin.G4GOriginSession"%>

<%
	PlayerDTO playerDTO = new PlayerDTO();
	if (DataUtil.getUserDTO(request) != null)
		playerDTO = DataUtil.getUserDTO(request).getPlayerDTO();
	String defaultAvatarId = String
			.valueOf(((AvatarsDTO) application.getAttribute(G4GOriginSession.DEFAULTAVATAR)).getId());
	String pictureId = request.getParameter(G4GConstants.PICTUREID) != null ? request
			.getParameter(G4GConstants.PICTUREID)
			: defaultAvatarId;
	String playerid = request.getParameter(G4GConstants.PLAYERID_DB) != null ? request
			.getParameter(G4GConstants.PLAYERID_DB)
			: null;
	String avatarid = request.getParameter(G4GConstants.AVATARID_DB) != null ? request
			.getParameter(G4GConstants.AVATARID_DB)
			: null;
	int playerId = Integer.parseInt(playerid);
	int avatarId = 0;
	if (avatarid != null && !avatarid.equals(G4GConstants.STRING_NULL))
		avatarId = Integer.parseInt(avatarid);

	String encryptedPlayerId = DataUtil.encrypt(String
			.valueOf(playerId));
	String screenName = URLDecoder.decode(request
			.getParameter(G4GConstants.SCREENNAME_DB) != null ? request
			.getParameter(G4GConstants.SCREENNAME_DB) : null, G4GConstants.UTF8);
	boolean showMenu = request.getParameter(G4GConstants.SHOWMENU) != null ? Boolean
			.valueOf(request.getParameter(G4GConstants.SHOWMENU))
			: false;
	boolean isOnline = request.getParameter(G4GConstants.ISONLINE) != null ? Boolean
			.valueOf(request.getParameter(G4GConstants.ISONLINE))
			: false;
	if (showMenu)
		if (playerDTO.getId() == playerId)
			showMenu = false;

	boolean isLink = request.getParameter(G4GConstants.ISLINK) != null ? Boolean
			.valueOf(request.getParameter(G4GConstants.ISLINK))
			: false;

	if (playerId != 0) {
%>
<div class="PlayerAvatar">
	<div class="Avatar" id="user<%=encryptedPlayerId%>">
		<div class="AvatarImage">
	<%
	if (showMenu) {
		boolean isFriend = FriendsUtil.isFriend(playerDTO.getId(),
				playerId);
		boolean isAdded = !isFriend;
		boolean isRemoved = isFriend;
	%>
			<div class="AvatarOptions">
				<table>
				<tr>
					<td>
						<img src="<%=DataUtil.getBasePath(request)%>WebContent/<%=SessionUtil.getSiteId(request)%>/images/icons/mail.png" alt="Mail">
					</td>
					<td>
						<a href="<%=DataUtil.getBasePath(request)%>WebContent/sendMessage.jsp?friendId=<%=screenName%>&TB_iframe=true&height=1000&width=650&modal=true" class="modal thickbox">Message</a>
					</td>
				</tr>
				<tr>
					<td>
						<img src="<%=DataUtil.getBasePath(request)%>WebContent/<%=SessionUtil.getSiteId(request)%>/images/icons/whitearrow.png" alt="Arrow">
					</td>
					<td>
						<a href="<%=DataUtil.getBasePath(request)%>displayProfile.do?<%=G4GConstants.PROFILEUSERID%>=<%=playerId%>">Profile</a>
					</td>
				</tr>
			<%
			if (isAdded) {
			%>
				<tr>
					<td>
						<img src="<%=DataUtil.getBasePath(request)%>WebContent/<%=SessionUtil.getSiteId(request)%>/images/icons/challenge.png" alt="Challenge">
					</td>
					<td>
						<a href="#"	onclick="javascript:sendFriendRequestToUserWithTag('<%=encryptedPlayerId%>');return false;">Add</a>
					</td>
				</tr>
			<%
			}
			%>
			<%
			if (isRemoved) {
			%>
				<tr>
					<td>
						<img src="<%=DataUtil.getBasePath(request)%>WebContent/<%=SessionUtil.getSiteId(request)%>/images/icons/close.png" alt="Close">
					</td>
					<td>
						<a href="#" onclick="removeFriendFromCurrentUser('user', '<%=encryptedPlayerId%>');return false;">Remove</a>
					</td>
				</tr>
			<%
			}
			%>
				<tr class="AvatarClose">
					<td>
						<img src="<%=DataUtil.getBasePath(request)%>WebContent/<%=SessionUtil.getSiteId(request)%>/images/icons/close.png" alt="Close">
					</td>
					<td>Close</td>
				</tr>
				</table>
			</div>
			<!-- end AvatarOptions -->
		<%
		}
		%>

		<%
		if (avatarId > 0) {
		%>
			<img class="AvatarPic" src="<%=DataUtil.getBasePath(request)%>WebContent/displayImage.jsp?dto=<%=G4GConstants.AVATARDTO%>&id=<%=avatarId%>" alt="<%=screenName%>">
		<%
		} else {
			if (pictureId != null && pictureId.equals(G4GConstants.STRING_NULL)) {
		%>
			<img class="AvatarPic" src="<%=DataUtil.getBasePath(request)%>WebContent/displayImage.jsp?dto=<%=G4GConstants.AVATARDTO%>&id=<%=defaultAvatarId%>" alt="<%=screenName%>">
		<%
		} else {
		%>
			<img class="AvatarPic" src="<%=DataUtil.getBasePath(request)%>WebContent/displayImage.jsp?dto=<%=G4GConstants.PICTUREDTO%>&id=<%=pictureId%>" alt="<%=screenName%>">
		<%
		}
	}
	%>
		</div>
		<!-- end AvatarImage -->
	<%
	if (request.getParameter(G4GConstants.ISLINK).equals(G4GConstants.ACTIVE)) {
		} else {
	%>
		<div class="AvatarStatus">
		<%
		if (isOnline) {
		%>
			<img alt="ONLINE" id="<%=screenName%>" src="<%=DataUtil.getBasePath(request)%>WebContent/<%=SessionUtil.getSiteId(request)%>/images/icons/status-online.png">
		<%
		} else {
		%>
			<img alt="OFFLINE" id="<%=screenName%>" src="<%=DataUtil.getBasePath(request)%>WebContent/<%=SessionUtil.getSiteId(request)%>/images/icons/status-offline.png">
		<%
		}
		%>

		<%
		if (isLink) {
		%>
			<a href="<%=DataUtil.getBasePath(request)%>displayProfile.do?<%=G4GConstants.PROFILEUSERID%>=<%=playerId%>"><%=screenName%></a>
		<%
		} else {
		%>
			<%=screenName%>
		<%
		}
		%>
		</div>
	<% } %>
	</div>
	<!-- end Avatar -->
	<%
	}
	%>
</div>