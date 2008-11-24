<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>

<%@page import="com.g4g.utils.G4GConstants"%>
<%@page import="com.g4g.dto.PlayerDTO"%>
<%@page import="com.g4g.delegator.PlayerServiceDelegator"%>
<%@page import="com.g4g.utils.DataUtil"%>
<%@page import="com.g4g.delegator.TwoPlayerMatchServiceDelegator"%>
<%@page import="com.g4g.dto.TwoplayertournamentDTO"%>
<%@page import="com.g4g.delegator.TwoPlayerTournamentServiceDelegator"%>
<%@page import="com.g4g.dto.SearchCriteria"%>
<%@page import="com.g4g.dto.TwoplayermatchDTO"%>
<%@page import="com.g4g.utils.TournamentsUtil"%>
<%@page import="java.util.List"%>
<%@page import="com.g4g.dto.Twoplayermatchcomments"%>
<%@page import="com.g4g.delegator.MatchCommentsServiceDelegator"%>
<%@page import="com.g4g.utils.DateUtil"%>
<%@page import="com.g4g.dto.UserDTO"%>
<%
	UserDTO userDTO = new UserDTO();
	if (DataUtil.getUserDTO(request) != null)
		userDTO = DataUtil.getUserDTO(request);

	PlayerDTO playerDTO = PlayerServiceDelegator.getPlayer(Integer
			.parseInt(DataUtil.decrypt(request
					.getParameter(G4GConstants.FROMID))));

	//MatchDTO matchDTO = TwoPlayerMatchServiceDelegator.getMatchInformation(messageDTO.getMatchid(), request);

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

	searchCriteria.removeAllAttribute();
	searchCriteria.setAttribute(G4GConstants.MATCHID_DB, request
			.getParameter(G4GConstants.MATCHID));
	List<Twoplayermatchcomments> comments = MatchCommentsServiceDelegator
			.getList(searchCriteria);

	String date = DataUtil.getDate(DateUtil.getDateOfTimeZone(DataUtil
			.getDate(twoplayermatchDTO.getScheduledstartdate()
					.toString(),
					G4GConstants.DATE_YYYY_MM_DD_HH_MM_SS_sss), userDTO
			.getOffset()), G4GConstants.DATE_DD_MM_YYYY);
	String time = DataUtil.getDate(DateUtil.getDateOfTimeZone(DataUtil
			.getDate(twoplayermatchDTO.getScheduledstartdate()
					.toString(),
					G4GConstants.DATE_YYYY_MM_DD_HH_MM_SS_sss), userDTO
			.getOffset()), G4GConstants.DATE_H_MM_A);
%>

<h1>
	Your Challenge has been Accepted
</h1>

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
		<a href="<%=G4GConstants.VIEW_CARD%><%=twoplayermatchDTO.getId()%><%= G4GConstants.THICKBOX %>"
			class="gray modal thickbox" onclick="hideNotification();">View Challenge Card</a>
	</li>
</ul>

<table class="generalInfo">
	<tbody>

		<tr>
			<td colspan="2" scope="row"><%=playerDTO.getScreenname()%>
				has accepted your Challenge
			</td>
		</tr>
		<tr>
			<th scope="row">
				Game:&nbsp;&nbsp;
			</th>
			<td>
				<strong>Middle Weight</strong>
			</td>
		</tr>
		<tr>
			<th scope="row">
				Entry Fee:&nbsp;&nbsp;
			</th>
			<td>
				$<%=twoplayertournamentDTO.getEntryfee()%></td>
		</tr>
		<tr>
			<th scope="row">
				Jackpot:&nbsp;&nbsp;
			</th>
			<td>
				$<%= twoplayertournamentDTO.getJackpot() %></td>
		</tr>

		<tr>
			<th scope="row">
				Date:&nbsp;&nbsp;
			</th>
			<td><%=date%></td>
		</tr>
		<tr>
			<th scope="row">
				Time:&nbsp;&nbsp;
			</th>
			<td><%=time%></td>
		</tr>
	</tbody>
</table>
<h2>
	Comments
</h2>
<p class="comments">
	<%
		if (comments.size() > 0) {
			for (int index = 0; index < comments.size(); index++) {
				out.println(comments.get(index).getComment());
			}
		}
	%>
</p>