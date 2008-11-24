<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>

<%@page import="com.g4g.utils.G4GConstants"%>
<%@page import="com.g4g.utils.DataUtil"%>
<%@page import="com.g4g.dto.PlayerDTO"%>
<%@page import="com.g4g.delegator.PlayerServiceDelegator"%>
<%@page import="com.g4g.utils.SessionUtil"%>
<%@page import="java.net.URLDecoder"%>

<%
	PlayerDTO toPlayerDTO = PlayerServiceDelegator.getPlayer(Integer
			.parseInt(DataUtil.decrypt(request
					.getParameter(G4GConstants.TOID))));
	PlayerDTO fromPlayerDTO = PlayerServiceDelegator.getPlayer(Integer
			.parseInt(DataUtil.decrypt(request
					.getParameter(G4GConstants.FROMID))));

	String messageId = request.getParameter(G4GConstants.RANDOMID);
	String subject = URLDecoder.decode(request
			.getParameter(G4GConstants.SUBJECT), G4GConstants.UTF8);
	String body = URLDecoder.decode(request
			.getParameter(G4GConstants.BODY), G4GConstants.UTF8);
	//G4GConstants.ISLINK
%>

<div class="hideDiv" id="body<%=messageId%>">
	<div class="MessageContent clearfix">
		<div class="CloseButton">
			<a onclick="javascript:closeMessage(this)" id="close<%=messageId%>"
				href="javascript:{}">Close</a>
		</div>
		<div class="grid grid2">
			<jsp:include page="../../../common/avatar.jsp">
					<jsp:param name="playerid" value="<%=fromPlayerDTO.getId() %>"/>
					<jsp:param name="avatarid" value="<%= (fromPlayerDTO.getAvatars() != null) ? fromPlayerDTO.getAvatars().getId() : 0 %>"/>
					<jsp:param name="screenname" value="<%=fromPlayerDTO.getScreenname() %>"/>
					<jsp:param name="isOnline" value="<%=fromPlayerDTO.isIsonline() %>"/>
					<jsp:param name="pictureId" value="<%=fromPlayerDTO.getPictureId() %>"/>
					<jsp:param name="showMenu" value="false"/>
					<jsp:param name="isLink" value="false"/>
			</jsp:include>
		</div>
		<div class="messageInfo grid grid9">
			<dl class="">
				<dt>
					<%=subject%>
				</dt>
				<dd>
					<p>
						<%=body%>
					</p>
				</dd>
				<dd class="messageReply textareaFull">
					<h1>
						Reply
					</h1>
					<textarea id="replyText<%=messageId%>" name="replyText"></textarea>
					<div class="buttons">
						<button class="green" onclick="replyToMessage(this)"
							id="reply<%=messageId%>">
							Send
						</button>
					</div>
				</dd>
			</dl>
		</div>
		<ul class="floatRight messageOptions">
			<li>
				<a class="messageOptionsArea" href="#"> </a>
			</li>
		</ul>
	</div>
</div>
