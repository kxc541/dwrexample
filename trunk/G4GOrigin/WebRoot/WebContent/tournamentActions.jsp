<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>

<bean:define id="siteId" value="wg" type="java.lang.String"/>
	<logic:present name="siteId" >
		<bean:define id="siteId" name="siteId" type="java.lang.String"/>
</logic:present>


<%@page import="java.util.List"%>
<%@page import="com.g4g.dto.TournamentGameDTO"%>
<%@page import="com.g4g.dto.GameoptionsDTO"%>
<%@page import="com.g4g.dto.PastTournamentDTO"%>
<%@page import="com.g4g.utils.G4GConstants"%>
<%@page import="com.g4g.dto.GameDTO"%>
<%@page import="com.g4g.dto.NetworkDTO"%>
<%@page import="com.g4g.dto.PlayerDTO"%>
<%@page import="java.util.Set"%>
<%@page import="com.g4g.dto.TwoplayertournamentDTO"%>
<%@page import="java.util.Iterator"%>
<%@page import="com.g4g.utils.DataUtil"%>
<%@page import="com.g4g.dto.UserDTO"%>
<%@page import="com.g4g.utils.DateUtil"%>
<%@page import="java.math.BigDecimal"%>


<%String path = request.getContextPath();
			String basePath = request.getScheme() + "://"
					+ request.getServerName() + ":" + request.getServerPort()
					+ path + "/";%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html  lang="en">
    <head>
		<link rel="stylesheet" media="screen" href="<%=basePath%>WebContent/<bean:write name="siteId"/>/css/Tournaments.css">
		<link rel="stylesheet" media="screen" href="<%=basePath%>WebContent/<bean:write name="siteId"/>/css/Challenge.css">

		<title>World Gaming Tournaments</title>

    </head>

<body>&nbsp;
<% UserDTO userDTO = DataUtil.getUserDTO(request);
%>


	<div class="ContentWrapper clearfix" id="Tournaments"><div class="Sidebar">.SidebarContent</div><div class="PageContent" id="">
		<div class="intro">
			<p>Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Aenean consectetuer. Etiam venenatis.</p>

			<p><a><img src="<%=basePath%>WebContent/<bean:write name="siteId"/>/css/images/icons/more.png">  <a class="blue">Administer Tournaments</a></a></p>
		</div>


		<div class="TabNav">
			<ul class="idTabs">
				<li><a href="#MyGames" class="selected">All Tournaments</a></li>

			</ul>
		</div>

		<div class="TabContent">

		<div class="Format1">
		<%List<TournamentGameDTO> tournamentList = (List<TournamentGameDTO>)request.getAttribute(G4GConstants.TOURNAMENTGAMESLIST);
			if(tournamentList.size() <= 0){ %>
			<h1>No Tournaments Available</h1>
			<% }if(tournamentList.size() > 0 ){ %>
				<h1>Current Tournaments</h1>
			<%}	for(int index= 0;index<tournamentList.size();index++){
					TournamentGameDTO tournamentGameDTO = (TournamentGameDTO)tournamentList.get(index);
					GameDTO gameDTO = tournamentGameDTO.getGameDTO();
					TwoplayertournamentDTO twoplayertournamentDTO = tournamentGameDTO.getTwoplayertournamentDTO();
					BigDecimal entryfee = new BigDecimal(twoplayertournamentDTO.getEntryfee());
			%>
			<div class="Format2 clearfix">
				<div class="TournamentBanner">
					<img src="WebContent<%=gameDTO.getTournamentbannerimgsrc()%>">
					<dl class="GameInfo">
						<dt><%=gameDTO.getGamename() %></dt>
							<dd>Tiers: <%=twoplayertournamentDTO.getLevels() %></dd>
							<dd>Players: <%=tournamentGameDTO.getNo_of_players() %></dd>
							<dd>Entry Fee: $<%=DataUtil.format(entryfee.doubleValue())%></dd>
							<% Set<GameoptionsDTO> list = tournamentGameDTO.getGameOptionsList();
								Iterator<GameoptionsDTO> iterator = list.iterator();
								while(iterator.hasNext()){
									GameoptionsDTO gameoptionsDTO = iterator.next();
									if(gameoptionsDTO != null) {
							 %>
								<dd>
									<%=gameoptionsDTO.getOption() %>: <%=gameoptionsDTO.getValue() %>
								</dd>
							<%} } %>
						<dd class="jackpot"><img src="<%=basePath%>WebContent/<bean:write name="siteId"/>/css/images/icons/jackpot.png"> <strong>$<%=DataUtil.format(twoplayertournamentDTO.getJackpot().doubleValue()) %></strong></dd>
					</dl>

					<div class="TournamentDescription">
						<table>
							<tr>
								<th>Description</th>
								<td>
									Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Aenean consectetuer. Etiam venenatis. Sed ultricies, pede sit amet aliquet lobortis, nisi ante sagittis sapien, in rhoncus lectus mauris quis massa.
									<span style="display:none;">

										Type: Assault<br/>

										Playlist: <br/>

										Map: High Ground<br/>

									</span>
								</td>
								<td class="buttons"><a class="blue" href="displayViewMutiTierTournament.do?tournamentId=<%=twoplayertournamentDTO.getId() %>">Tournament Details</a></td>
							</tr>
						</table>
					</div>

				</div><!-- Tournament banner -->

		</div><!-- format2 -->
<%} %>


	</div><!-- Format 1 -->


		<div class="Format1 clearfix">

			<h1>Past Tournaments</h1>
				<div class="Format3 clearfix">

					<div class="PastTournaments clearfix" id="pastTournamentDIV">
							<table class="PastTournamentTable">
							<thead>
								<tr>
									<th>Console</th>
									<th>Game</th>
									<th>Tournament</th>
									<th>Date</th>
									<th>Players</th>
									<th>Winner</th>
									<th><br><br></th>
								</tr>
							</thead>
							<% List<PastTournamentDTO> pastTournamentList = (List<PastTournamentDTO>)request.getAttribute(G4GConstants.PASTTOURNAMENTLIST);
								int totalPastTournaments = pastTournamentList.size();

								int total = totalPastTournaments < 3?totalPastTournaments:3;
								for(int index =0; index < total; index++){
									PastTournamentDTO pastTournamentDTO = pastTournamentList.get(index);
									GameDTO gameDTO = pastTournamentDTO.getGameDTO();
									NetworkDTO networkDTO = pastTournamentDTO.getNetworkDTO();
									List<PlayerDTO> playersList = pastTournamentDTO.getPlayersList();
									PlayerDTO winnerDTO = pastTournamentDTO.getWinnerDTO();
									TwoplayertournamentDTO twoplayertournamentDTO = pastTournamentDTO.getTwoplayertournamentDTO();



							%>
								<tr>
									<td><%=networkDTO.getNetworkname() %></td>
									<td><%=gameDTO.getGamename() %></td>
									<td><%=gameDTO.getGamename() %></td>
									<td><%=DataUtil.getDate(DateUtil.getDateOfTimeZone(pastTournamentDTO.getCompletiondate(),userDTO.getOffset()),G4GConstants.DATE_EEE_MMM_DD_YYYY_AT_HH_MM_A) %></td>
									<td>
									<%
									Iterator<PlayerDTO> iterator = playersList.iterator();
									while(iterator.hasNext()){
										PlayerDTO playerDTO = iterator.next();
									 %>
									 <%=playerDTO.getScreenname() %><br>
									 <%} %>
									 </td>
									 <td> <%=winnerDTO.getScreenname() %></td>
								</tr>
								<%} %>
							<tbody>
							</tbody>
						</table>
						<%if(totalPastTournaments > 3 ){ %>
						<div class="more">
							<a style="cursor: pointer;" onclick="javascript:viewAllPastTournament();">View all</a>
						</div>
						<%} %>
					</div><!-- PastTournaments -->
				</div><!-- format 3 -->


			</div><!-- format 1 -->

		</div><!-- tabcontent --><script type="text/javascript">
		$(document).ready(function(){
			$('.Widget').OpenClose();
			$('.Avatar').ActiveAvatar();

		});
</script><!-- PageContent --></div></div>



</body>
</html>
<!-- ContentWrapper -->



