<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>

<%@page import="com.g4g.dto.PlayerDTO"%>
<%@page import="com.g4g.delegator.PlayerServiceDelegator"%>
<%@page import="com.g4g.utils.DataUtil"%>
<%@page import="com.g4g.utils.G4GConstants"%>
<%@page import="com.g4g.delegator.TwoPlayerMatchServiceDelegator"%>
<%@page import="com.g4g.dto.TwoplayermatchDTO"%>
<%@page import="com.g4g.dto.TwoplayertournamentDTO"%>
<%@page import="com.g4g.dto.SearchCriteria"%>
<%@page import="com.g4g.delegator.TwoPlayerTournamentServiceDelegator"%>
<%@page import="com.g4g.dto.UserDTO"%>
<%@page import="com.g4g.utils.SessionUtil"%>
<%
	UserDTO userDTO = new UserDTO();
	if (DataUtil.getUserDTO(request) != null)
		userDTO = DataUtil.getUserDTO(request);

	PlayerDTO playerDTO = PlayerServiceDelegator.getPlayer(Integer
			.parseInt(DataUtil.decrypt(request
					.getParameter(G4GConstants.FROMID))));

	SearchCriteria searchCriteria = new SearchCriteria();
	searchCriteria.setAttribute(G4GConstants.ID, Integer
			.parseInt(request.getParameter(G4GConstants.MATCHID)));
	TwoplayermatchDTO twoplayermatchDTO = TwoPlayerMatchServiceDelegator
			.getList(searchCriteria).get(0);

	searchCriteria.removeAllAttribute();
	searchCriteria.setAttribute(G4GConstants.ID, twoplayermatchDTO
			.getTwoplayertournament().getId());
	TwoplayertournamentDTO twoplayertournamentDTO = TwoPlayerTournamentServiceDelegator
			.getList(searchCriteria).get(0);
%>


<div align="right">
	<br>
</div>
<div class="hideDiv"
	id="body<%=request.getParameter(G4GConstants.RANDOMID)%>">
	<div class="MessageContent clearfix">
		<div class="CloseButton">
			<a onclick="javascript:closeMessage(this)"
				id="close<%=request.getParameter(G4GConstants.RANDOMID)%>"
				href="javascript:{}">Close</a>
		</div>

		<div class="messageInfo grid grid9">
			<dl class="">
				<dt>
					<a><img
							src="WebContent/<%=SessionUtil.getSiteId(request)%>/css/images/icons/challenge.png">Match
						Confirmation</a>
				</dt>
				<dd>
					Your Challenge to (<%=playerDTO.getScreenname()%>) has been
					Rejected
				</dd>
			</dl>
		</div>

		<jsp:include page="../../../common/avatar.jsp">
					<jsp:param name="playerid" value="<%=playerDTO.getId() %>"/>
					<jsp:param name="avatarid" value="<%= (playerDTO.getAvatars() != null) ? playerDTO.getAvatars().getId() : 0 %>"/>
					<jsp:param name="screenname" value="<%=playerDTO.getScreenname() %>"/>
					<jsp:param name="isOnline" value="<%=playerDTO.isIsonline() %>"/>
					<jsp:param name="pictureId" value="<%=playerDTO.getPictureId() %>"/>
					<jsp:param name="showMenu" value="false"/>
					<jsp:param name="isLink" value="false"/>
		</jsp:include>
		<ul class="buttons">
			<li>
				<a
					href="<%=G4GConstants.ISSUEOPENCHALLANGE_LINK + twoplayermatchDTO.getId()%><%= G4GConstants.THICKBOX%>"
					class="thickbox">Issue as open Challenge</a>
			</li>
			<li>
				<a href="<%=G4GConstants.VIEW_CARD + twoplayermatchDTO.getId()%><%= G4GConstants.THICKBOX%>" class="thickbox green">Discard</a>
			</li>
		</ul>
		<table class="generalInfo">
			<tbody>
				<tr>
					<td>
						<strong>Your challenge to (<%=playerDTO.getScreenname()%>)
							has been rejected You can choose to leave the match open for
							another user to accept or you can discard the challenge</strong>
					</td>
				</tr>
			</tbody>
		</table>
	</div>
</div>
