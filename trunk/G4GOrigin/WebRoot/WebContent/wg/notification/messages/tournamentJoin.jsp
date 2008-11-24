<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>

<%@page import="com.g4g.utils.G4GConstants"%>
<%@page import="com.g4g.utils.DataUtil"%>
<%@page import="com.g4g.delegator.TwoPlayerTournamentServiceDelegator"%>
<%@page import="com.g4g.delegator.TwoPlayerMatchServiceDelegator"%>
<%@page import="com.g4g.dto.SearchCriteria"%>
<%@page import="com.g4g.dto.NetworkDTO"%>
<%@page import="com.g4g.dto.GameDTO"%>
<%@page import="com.g4g.dto.TwoplayertournamentDTO"%>
<%@page import="com.g4g.dto.TwoplayermatchDTO"%>
<%@page import="com.g4g.utils.TournamentsUtil"%>
<%@page import="com.g4g.utils.DateUtil"%>
<%@page import="com.g4g.dto.UserDTO"%>
<%
	UserDTO userDTO = new UserDTO();
	if (DataUtil.getUserDTO(request) != null)
		userDTO = DataUtil.getUserDTO(request);

	String messageId = request.getParameter(G4GConstants.RANDOMID);
	TwoplayermatchDTO twoplayermatchDTO = new TwoplayermatchDTO();
	TwoplayertournamentDTO twoplayertournamentDTO = new TwoplayertournamentDTO();
	GameDTO gameDTO = new GameDTO();
	NetworkDTO networkDTO = new NetworkDTO();
	String date = G4GConstants.BLANK;
	String time = G4GConstants.BLANK;
	if(request.getParameter(G4GConstants.MATCHID) != null){
		int matchId = Integer.parseInt(request.getParameter(G4GConstants.MATCHID));
		SearchCriteria searchCriteria = new SearchCriteria();
		searchCriteria.setAttribute(G4GConstants.ID, matchId);
		twoplayermatchDTO = TwoPlayerMatchServiceDelegator.getList(searchCriteria).get(0);

		searchCriteria.removeAllAttribute();
		searchCriteria.setAttribute(G4GConstants.ID, twoplayermatchDTO.getTwoplayertournament().getId());
		twoplayertournamentDTO = TwoPlayerTournamentServiceDelegator.getList(searchCriteria).get(0);

		gameDTO = twoplayertournamentDTO.getGameDTO();
		networkDTO = gameDTO.getNetwork();

		date = DataUtil.getDate(DateUtil.getDateOfTimeZone(DataUtil.getDate(twoplayermatchDTO.getScheduledstartdate().toString(), G4GConstants.DATE_YYYY_MM_DD_HH_MM_SS_sss), userDTO.getOffset()), G4GConstants.DATE_DD_MM_YYYY) ;
		time = DataUtil.getDate(DateUtil.getDateOfTimeZone(DataUtil.getDate(twoplayermatchDTO.getScheduledstartdate().toString(), G4GConstants.DATE_YYYY_MM_DD_HH_MM_SS_sss), userDTO.getOffset()), G4GConstants.DATE_H_MM_A);
	}
%>
<div class="hideDiv" id="body<%= messageId %>">
	<div class="MessageContent clearfix">
		<div class="CloseButton">
			<a onclick="javascript:closeMessage(this)" id="close<%= messageId %>" href="javascript:{}">Close</a> </div>
            	<div class="grid grid2">
                	<img class="gameImage" src="<%= DataUtil.getBasePath(request) %>WebContent/<%=gameDTO.getImgsrc() %>">
               	</div>
                <div class="messageInfo grid grid10">
                	<dl class="">
                		<dt><%=gameDTO.getGamename() %> Tournament - Confirmation</dt>
                        	<dd>
                            	<p> You have joined a <%=twoplayertournamentDTO.getLevels() %>-tier <%=gameDTO.getGamename() %>tournament. </p>
                                <p> </p>
                                <table class="WidgetInfo Style1">
                                    <thead>
                                        <tr>
                                            <th colspan="2">  </th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <tr>
                                            <td> Console </td>
                                            <td> <%=networkDTO.getNetworkname() %> </td>
                                        </tr>
                                        <tr>
                                            <td> Date </td>
                                            <td> <%=date %> </td>
                                        </tr>
                                        <tr>
                                            <td> Time </td>
                                            <td> <%=time %> </td>
                                        </tr>
                                        <tr>
                                            <td> Number of Players: </td>
                                            <td> <%=TournamentsUtil.getNoOfPlayers(twoplayertournamentDTO.getLevels()) %> </td>
                                        </tr>
                                        <tr>
                                            <td>Confirmation #</td><td class="buttons" colspan="2">
                                                <a class="green" style="cursor: pointer;">1000012</a>
                                            </td>
                                        </tr>
                                    </tbody>
                                </table>
                            </dd>
                    </dl>
                </div>
                    <ul class="floatRight messageOptions">
                        <li>
                            <a class="messageOptionsArea" style="cursor: pointer;"> </a>
                        </li>
                    </ul>
    </div>
</div>


