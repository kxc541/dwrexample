<%@page import="com.g4g.utils.G4GConstants"%>

<%
	String messageId = request.getParameter(G4GConstants.ID);
	String recipient = request.getParameter(G4GConstants.RECIPIENT_PLAYER);
	String playerId = request.getParameter(G4GConstants.PLAYERID);
	String imageSrc = request.getParameter(G4GConstants.IMGSRC);
	String messageType = request.getParameter(G4GConstants.MESSAGE_TYPE);
%>

<div class="MessageContainer" id="<%= messageId %>">
	<div id="c<%= messageId %>" class="MessagePreview clearfix">
		<div class="grid grid3">
		<% if (!G4GConstants.WORLD_GAMING.equals(recipient)) { %>
			<a href="displayProfile.do?profileUserId=<%= playerId %>"><%= recipient %></a>
		<% } else {
			out.print(recipient);
		   } 
		%>
		</div>
		<div class="grid grid3">
			<a href="javascript:{}" onclick="javascript:openMessage(this)" id="open<%= messageId %>"><img src="WebContent/wg/css/images/icons/<%= imageSrc %>" alt="<%= messageType %>">&nbsp;&nbsp;<%= messageType %></a>
		</div>
		<div class="grid">
			<%= request.getParameter(G4GConstants.DATE) %>
		</div>
		<div class="grid grid7">
			<%= request.getParameter(G4GConstants.MESSAGE) %>
		</div>
		<div class="gridRight">
		<% if (!G4GConstants.MSG_ARCHIVED.equals(request.getParameter(G4GConstants.MESSAGE_STATUS))) { %>
			<a onclick="javascript:changeMessageStatus(this,'Archived')"
				id="archive<%= messageId %>" title="Archive" href="javascript:{}">
				<img src="WebContent/wg/css/images/icons/archivemessages.png" />
			</a>
		<% } %>
			<a onclick="javascript:changeMessageStatus(this, 'Deleted')"
				id="delete<%= messageId %>" title="Delete" href="javascript:{}">
				<img src="WebContent/wg/css/images/icons/smallx.png" />
			</a>
		</div>
	</div>

	<%= request.getParameter(G4GConstants.BODY) %>
</div>