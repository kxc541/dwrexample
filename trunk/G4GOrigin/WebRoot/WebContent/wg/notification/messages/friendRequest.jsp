<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>

<%@page import="com.g4g.utils.G4GConstants"%>
<%@page import="com.g4g.dto.PlayerDTO"%>
<%@page import="com.g4g.delegator.PlayerServiceDelegator"%>
<%@page import="com.g4g.utils.DataUtil"%>
<%@page import="com.g4g.utils.SessionUtil"%>

<%

	PlayerDTO toPlayerDTO = PlayerServiceDelegator.getPlayer(Integer.parseInt(DataUtil.decrypt(request.getParameter(G4GConstants.TOID))));
	PlayerDTO fromPlayerDTO = PlayerServiceDelegator.getPlayer(Integer.parseInt(DataUtil.decrypt(request.getParameter(G4GConstants.FROMID))));

%>

<div class="hideDiv" id="body<%= request.getParameter(G4GConstants.RANDOMID) %>">
	<div class="MessageContent clearfix">
		<div class="CloseButton">
			<a onclick="javascript:closeMessage(this)" id="close<%= request.getParameter(G4GConstants.RANDOMID) %>" href="javascript:{}">Close</a>
		</div>
		<div class="grid grid2">

		<jsp:include page="../../../common/avatar.jsp">
					<jsp:param name="playerid" value="<%=fromPlayerDTO.getId() %>"/>
					<jsp:param name="avatarid" value="<%= (fromPlayerDTO.getAvatars() != null) ? fromPlayerDTO.getAvatars().getId() : 0 %>"/>
					<jsp:param name="screenname" value="<%=fromPlayerDTO.getScreenname() %>"/>
					<jsp:param name="isOnline" value="<%=fromPlayerDTO.isIsonline() %>"/>
					<jsp:param name="pictureId" value="<%=fromPlayerDTO.getPictureId() %>"/>
					<jsp:param name="showMenu" value="false"/>
					<jsp:param name="isLink" value="true"/>
		</jsp:include>

		</div>
		<div class="messageInfo grid grid10">
			<dl class="">
				<dt><%= fromPlayerDTO.getScreenname() %> wants to be your friend!! You can Approve or Deny this request</dt>
				<dd class="buttons">
					<a class="green" id="deleteuser<%= request.getParameter(G4GConstants.RANDOMID) %>" onclick="javascript:addUserWithTagAsFriend('<%= fromPlayerDTO.getScreenname() %>');changeMessageStatus(this, 'DeletedUser');" style="cursor: pointer;">Accept</a>
            		<a class="orange" id="delete<%= request.getParameter(G4GConstants.RANDOMID) %>" onclick="javascript:changeMessageStatus(this, 'Deleted')" href="javascript:{}">Decline</a>
				</dd>
			</dl>
			</div>
			<ul class="floatRight messageOptions">
				<li><a class="messageOptionsArea" href="#"></a></li>
			</ul>
	</div>
</div>