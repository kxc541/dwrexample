<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
			"http://www.w3.org/TR/html4/loose.dtd">
<%@page import="com.g4g.utils.G4GConstants"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Iterator"%>
<%@page import="com.g4g.dto.GameoptionsDTO"%>
<%@page import="com.g4g.dto.PlayerDTO"%>
<%@page import="com.g4g.utils.DataUtil"%>
<%@page import="com.g4g.delegator.PlayerServiceDelegator"%>
<%@page import="com.g4g.forms.DirectedChallengeForm"%>
<%@page import="com.impessa.worldgaming.client.User"%>
<%@page import="com.g4g.delegator.NationalCodeServiceDelegator"%>
<%@page import="com.g4g.dto.TwoplayertournamentDTO"%>
<%@page import="com.g4g.delegator.TwoPlayerTournamentServiceDelegator"%>
<%@page import="com.g4g.delegator.G4GFinancialDelegator"%>
<%@page import="com.g4g.utils.ChallengeCardUpdateUtil"%>
<%@page import="com.g4g.utils.DateUtil"%>
<%@page import="com.g4g.utils.GamesUtil"%>
<%@page import="com.g4g.delegator.SubNationalCodeServiceDelegator"%>

<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>


<bean:define id="siteId" value="wg" type="java.lang.String" />
<logic:present name="siteId">
	<bean:define id="siteId" name="siteId" type="java.lang.String" />
</logic:present>

<html>
<html:errors property="error"/>

<!-- Following error tag will display error message
"You don't have enough balance, Please refill your account."-->
<html:errors property="amountField"/>

		<%
		int ForChat=0;
		DirectedChallengeForm directedChallengeForm = (DirectedChallengeForm)session.getAttribute(G4GConstants.DIRECTED_CHALLENGE_FORM);
		PlayerDTO  challengePlayer = null;
		if(directedChallengeForm.getTwoplayermatchDTO()!=null && directedChallengeForm.getTwoplayermatchDTO().getPlayeroneid()!=null){
			challengePlayer = directedChallengeForm.getTwoplayermatchDTO().getPlayeroneid();
		}
		if(challengePlayer == null){
			 challengePlayer = directedChallengeForm.getPlayerDTO();
		 }
		if(challengePlayer == null){
		challengePlayer  = DataUtil.getUserDTO(request).getPlayerDTO();
		}
		PlayerDTO oppPlayer = new PlayerDTO();
		User oppUser = new User();

try{
if(directedChallengeForm.getTwoplayermatchDTO()!=null && directedChallengeForm.getTwoplayermatchDTO().getPlayertwoid()!=null)
oppPlayer  = directedChallengeForm.getTwoplayermatchDTO().getPlayertwoid();
else
oppPlayer  = directedChallengeForm.getOppPlayerDTO();
if(oppPlayer.getId() == challengePlayer.getId()){
	if(directedChallengeForm.getTwoplayermatchDTO().getPlayertwoid() !=null){
		oppPlayer = directedChallengeForm.getTwoplayermatchDTO().getPlayertwoid();
		try{
			oppUser = G4GFinancialDelegator.getUserInfo(oppPlayer.getEmailaddress());
		}catch (Exception e){
		}
	}else{
		oppPlayer = null;
	}
}else
if(oppUser!=null){
if(directedChallengeForm.getTwoplayermatchDTO().getPlayertwoid() !=null){
		oppPlayer = directedChallengeForm.getTwoplayermatchDTO().getPlayertwoid();
		try{
			oppUser = G4GFinancialDelegator.getUserInfo(oppPlayer.getEmailaddress());
		}catch (Exception e){
	}
}if(oppUser==null)
oppUser = directedChallengeForm.getOppUser();
}
}
catch(NullPointerException e){
}
%>
<bean:define id="game" name="directedChallengeForm" property="gameDTO"
						type="com.g4g.dto.GameDTO" scope="session" />
<bean:define id="challengerUser" name="directedChallengeForm" property="challengerUser"
						type="com.impessa.worldgaming.client.User" scope="session" />
<logic:notEmpty name="directedChallengeForm" property="twoplayermatchDTO">
<%challengerUser = G4GFinancialDelegator.getUserByUserId(directedChallengeForm.getTwoplayermatchDTO().getPlayeroneid().getEmailaddress()); %>
</logic:notEmpty>
<head>
	<meta content="text/html; charset=UTF-8" http-equiv="Content-Type">
	<title>EO Object Display String Page</title>
	<link rel="stylesheet" type="text/css" media="screen" href="WebContent/<bean:write name="siteId"/>/css/Global.css">
	<link rel="stylesheet" type="text/css" media="screen" href="WebContent/<bean:write name="siteId"/>/css/Widgets.css">
	<link rel="stylesheet" type="text/css" media="screen" href="WebContent/<bean:write name="siteId"/>/css/Challenge.css">
	<link rel="stylesheet" type="text/css" media="screen" href="WebContent/<bean:write name="siteId"/>/css/Tables.css">
	<link rel="stylesheet" type="text/css" media="screen" href="WebContent/<bean:write name="siteId"/>/css/Chat.css">
	<link rel="stylesheet" type="text/css" media="screen" href="WebContent/<bean:write name="siteId"/>/css/rating.css">
	<script type="text/javascript" src="WebContent/<bean:write name="siteId"/>/lib/firebug/firebug.js">Generic</script>
	<SCRIPT language=JavaScript src="WebContent/<bean:write name="siteId"/>/js/jquery.js"></SCRIPT>
	<SCRIPT language=JavaScript src="WebContent/<bean:write name="siteId"/>/js/OpenClose.js"></SCRIPT>
	<SCRIPT language=JavaScript src="WebContent/<bean:write name="siteId"/>/javascript/chat.js"></SCRIPT>
	<SCRIPT language=JavaScript src="WebContent/<bean:write name="siteId"/>/js/ActiveAvatar.js"></SCRIPT>
	<SCRIPT language=JavaScript src="WebContent/<bean:write name="siteId"/>/js/date.js"></SCRIPT>
	<SCRIPT language=JavaScript src="WebContent/<bean:write name="siteId"/>/js/jquery.dimensions.js"></SCRIPT>
	<SCRIPT language=JavaScript src="WebContent/<bean:write name="siteId"/>/js/jquery.idTabs.pack.js"></SCRIPT>
	<SCRIPT language=JavaScript src="WebContent/<bean:write name="siteId"/>/js/jquery.timer.js"></SCRIPT>
	<SCRIPT language=JavaScript src="WebContent/<bean:write name="siteId"/>/js/jquery.selectboxes.js"></SCRIPT>
	<SCRIPT language=JavaScript src="WebContent/<bean:write name="siteId"/>/js/wgLib.js"></SCRIPT>
	<SCRIPT language=JavaScript src="WebContent/<bean:write name="siteId"/>/js/wg.validate.js"></SCRIPT>
	<SCRIPT language=JavaScript src="WebContent/<bean:write name="siteId"/>/js/jquery.validate.js"></SCRIPT>
	<SCRIPT language=JavaScript src="WebContent/<bean:write name="siteId"/>/js/jquery.rating.js"></SCRIPT>

	<script type="text/javascript" src="<%= DataUtil.getBasePath(request) %>dwr/engine.js"></script>
	<script type="text/javascript" src="<%= DataUtil.getBasePath(request) %>dwr/interface/Chat.js"></script>
	<script type="text/javascript" src="<%= DataUtil.getBasePath(request) %>dwr/util.js"></script>


	<script type="text/javascript">
	function setAgree(f) {
		f.agree.checked = true;
	}

	function checkCheckBox(f) {
		if(event && event.keyCode == 13){
			return false;
 			}

	  if (f.agree.checked == false) {
	  	alert('Please check the box to continue.');
	    return false;
	  } else
	  	document.hasAcceptedTermsForm.submit();
	}
	</script>

	<script type="text/javascript">
	$(document).ready(function(){
		$('.Avatar').ActiveAvatar();
		$('.Widget').OpenClose();
		//console.log("about to stop parent timers");
		parent.stopTimers();
		//console.log("about to stop modal timers");
		stopTimers();
		$('.Message').toggle(); $('.Message.organize').show();
		$('.idTabs li a').click(function() {
		if($('div#Confirmation').css("display")!="none") {
			$('.Message').hide(); $('.Message.confirm').show();
		}
		if($('div#GoodLuck').css("display")!="none") {
			$('.Message').hide(); $('.Message.chat').show();
		}
		if($('div#MatchDetails').css("display")!="none") {
			$('.Message').hide(); $('.Message.organize').show();
		}
	});
	$('.buttons a.orange').click(function(){ $('.Message').hide(); $('.Message.reject').show(); }); });
	</script>
</head>
<%if( directedChallengeForm.getTwoplayermatchDTO()!=null && directedChallengeForm.getTwoplayermatchDTO().getId()>0 &&  directedChallengeForm.getTwoplayermatchDTO().getPlayertwoaccepteddate()!=null){ %>
<body onload="Chat.setUser('<%=(game.getId())%>', '<%= DataUtil.getUserDTO(request).getPlayerDTO().getScreenname() %>'); setTimeout('checkMessages()', 1000);">
<%}else{
%>
<body>
<%
}
%>
	<div id="content">
		<div id="DirectChallenge">
			<div class="SubNav">
				<ul class="idTabs">
					<li>1. Match Details</li>
					<li class="selected">2. Confirmation</li>
					<li>3. Good Luck</li>
				</ul>
			</div>
			<div class="Message organize">
				<h1>Confirm!</h1>
				<p>You must accept the terms of service before you can compete</p>
			</div>

			<div class="ChallengeCard clearfix">
				<div id="MatchDetails" class="MatchDetails clearfix">
					<div class="Organizer">
						<jsp:include page="common/avatar.jsp">
							<jsp:param name="playerid" value="<%=challengePlayer.getId() %>"/>
							<jsp:param name="avatarid" value="<%= (challengePlayer.getAvatars() != null) ? challengePlayer.getAvatars().getId() : 0 %>"/>
							<jsp:param name="screenname" value="<%=challengePlayer.getScreenname() %>"/>
							<jsp:param name="isOnline" value="<%=challengePlayer.isIsonline() %>"/>
							<jsp:param name="pictureId" value="<%=challengePlayer.getPictureId() %>"/>
							<jsp:param name="showMenu" value="false"/>
							<jsp:param name="isLink" value="false"/>
						</jsp:include>

						<table class="ccPlayerInfo">
						<tr>
							<th scope="row">Reputation</th>
							<td><%=challengePlayer.getReputation()%></td>
						</tr>
						<tr>
							<th scope="row">W/L</th>
							<td>
							<%=challengePlayer.getRecordwins()%>
							 /
							 <%=challengePlayer.getRecordlosses()%>
							</td>
						</tr>
						<!-- tr>
							<th scope="row">WG Points</th>
							<td>100</td>
						</tr -->
						<tr>
							<th scope="row">Location</th>
							<td>
								<bean:write property="city" name="challengerUser"/>,
								<%
									if(challengerUser.getState()!=null) {
    									out.print(", ");
    									out.print(SubNationalCodeServiceDelegator.getSubNationName(challengerUser.getState()));
									}

									if(challengerUser.getCountry()!=null) {
										out.print(", ");
										out.print(NationalCodeServiceDelegator.getNationName(challengerUser.getCountry()));
									}
								%>
							</td>
						</tr>
						</table>
					</div><!-- end Organizer -->

					<%
					String challenge1 = "displayDirectedChallenge1.do?"+"gameId="+game.getId()+"&"+G4GConstants.TWO_PLAYER_MATCH_ID+"="+DataUtil.getChallengeLink(directedChallengeForm);
					Boolean lobby = true;
					try{

					if(session.getAttribute(G4GConstants.TOURNAMENT_ID) != null){
					PlayerDTO dto = null;
					dto.getId();
					//throwing exception
					}

					oppPlayer.getId();
					%>
					<div class="Opponent">
						<jsp:include page="common/avatar.jsp">
							<jsp:param name="playerid" value="<%=oppPlayer.getId() %>"/>
							<jsp:param name="avatarid" value="<%= (oppPlayer.getAvatars() != null) ? oppPlayer.getAvatars().getId() : 0 %>"/>
							<jsp:param name="screenname" value="<%=oppPlayer.getScreenname() %>"/>
							<jsp:param name="isOnline" value="<%=oppPlayer.isIsonline() %>"/>
							<jsp:param name="showMenu" value="false"/>
							<jsp:param name="isLink" value="false"/>
							<jsp:param name="pictureId" value="<%=oppPlayer.getPictureId()%>"/>
						</jsp:include>

						<table class="ccPlayerInfo">
						<tr>
							<th scope="row">Reputation</th>
							<td><%=oppPlayer.getReputation()%></td>
						</tr>
						<tr>
							<th scope="row">W/L</th>
							<td><%=oppPlayer.getRecordwins() %> / <%=oppPlayer.getRecordlosses() %></td>
						</tr>
						<!-- tr>
							<th scope="row">WG Points</th>
							<td>100</td>
						</tr -->
						<tr>
							<th scope="row">Location</th>
							<td>
								<%
									out.print(oppUser.getCity());

									if(oppUser.getState()!=null) {
    									out.print(", ");
    									out.print(SubNationalCodeServiceDelegator.getSubNationName(oppUser.getState()));
									}

									if(oppUser.getCountry()!=null) {
										out.print(", ");
										out.print(NationalCodeServiceDelegator.getNationName(oppUser.getCountry()));
									}
								%>
							</td>
						</tr>
						</table>
					</div><!-- end Opponent -->
				<%
				challenge1 = "displayDirectedChallenge1.do?playerId="+oppPlayer.getId()+"&gameId="+game.getId()+"&"+G4GConstants.TWO_PLAYER_MATCH_ID+"="+DataUtil.getChallengeLink(directedChallengeForm);%>
				<%
				}
				catch(NullPointerException e){
				lobby = false;
				%>
					<div class="Opponent">
	    		    	<h4>Awaiting<br/>Challenger</h4>
					</div><!-- end Opponent -->
				<%
				}
				%>
					<div class="GameDetails">
						<div class="vs"><span>VS</span></div>
					<!-- <div class="Avatar GameAvatar" id="gamev5749rcf0o38g">
							<div class="AvatarImage">
								<img src="WebContent<%=game.getImgsrc()%>" alt="<%=game.getGamename()%>">
							</div>
						</div>	-->
						<!-- This section should be turned into a separate component with edit in place features -->
					<table class="tableGameDetails">
					<tr>
						<th scope="row"><img src="WebContent/<bean:write name="siteId"/>/css/images/icons/jackpot-challengecard.png" alt="Jackpot"></th>
						<%
						double jackpot = Double.parseDouble(directedChallengeForm.getAmountField())*2;
						jackpot = jackpot - ChallengeCardUpdateUtil.getHouseFee(jackpot);
						if(directedChallengeForm.getTwoplayermatchDTO()!=null)jackpot =directedChallengeForm.getTwoplayermatchDTO().getPayoutamount();
						%>
						<td>$<%=DataUtil.format(jackpot)%></td>
					</tr>
					<tr>
						<th scope="row">Console</th>
						<td><%=directedChallengeForm.getGameConsole() %></td>
					</tr>
					<tr>
						<th scope="row">Game</th>
						<td><%=game.getGamename() %></td>
					</tr>
				<%
				List<GameoptionsDTO> list = directedChallengeForm.getGameoptionDTO();

				Iterator<GameoptionsDTO> it  =null;
				it = list.iterator();

				while(it!=null && it.hasNext()){
					GameoptionsDTO dto =it.next();
					if(dto!=null){
					%>
					<tr>
						<th scope="row"><%=dto.getOption() %></th>
						<td><%=dto.getValue() %>
						</td>
					</tr>
					<%
					}
				}
				%>
					<tr>
						<th scope="row">Date/Time</th>
						<td><%=DataUtil.getDate(DateUtil.getDateOfTimeZone(directedChallengeForm.getSchduledDate() , DataUtil.getUserDTO(request).getOffset()) , G4GConstants.DATE_EEE_MMM_DD_YYYY_AT_hh_MM_A)%></td>
					</tr>
				<%
				if(directedChallengeForm.getTwoplayermatchDTO()!=null && directedChallengeForm.getTwoplayermatchDTO().getCompleteddate()!=null){ %>
					<tr>
						<th>Completed Date</th>
						<%
						TwoplayertournamentDTO twoplayertournamentDTO = directedChallengeForm.getTwoplayermatchDTO().getTwoplayertournament();
						%>
						<td><%=DataUtil.getDate(DateUtil.getDateOfTimeZone(directedChallengeForm.getTwoplayermatchDTO().getCompleteddate() , DataUtil.getUserDTO(request).getOffset()) , G4GConstants.DATE_EEE_MMM_DD_YYYY_AT_hh_MM_A)%></td>
					</tr>
					<tr>
						<th>Winner</th>
						<td>
							<%if(twoplayertournamentDTO.getCancellationdate()==null){ %>
							<%
							if(directedChallengeForm.getTwoplayermatchDTO().getWinnerid() == null){
							out.print("There is no winner of this game");
							}else{
								out.print((directedChallengeForm.getTwoplayermatchDTO().getWinnerid()).getScreenname());
							 }
							 } else{
							out.print("This Game was not played");
							} %>
						</td>
					</tr>
				<%} %>
					</table>
					<html:form action="directChallenge2.do" method="post" onsubmit="return checkCheckBox(this);">
					<div class="MatchComments">
						<h1>Comments</h1>
						<logic:present name="directedChallengeForm" scope="session" property="commentList">
							<div class="commentView" id="allComments">
								<bean:define id="commentList" name="directedChallengeForm" scope="session"
								property="commentList" />
								<logic:iterate id="com" name="commentList" type="com.g4g.dto.Twoplayermatchcomments">
									<bean:write name="com" property="comment"/>
									<br>
								</logic:iterate>
							</div>
						</logic:present>
						<html:textarea property="comments" styleId="comments" value="" />
						<html:submit property="operation" styleClass="green" value="<%=G4GConstants.ADD_COMMENTS%>" />
					</div>
					</html:form>
				</div><!-- tournament details -->
				<form method="post" name="hasAcceptedTermsForm" action="directChallenge2.do" onsubmit="return checkCheckBox(this)">
				<%
				String temp = null;
				try{
				temp = directedChallengeForm.getTwoplayermatchDTO().getCompleteddate().toString();
				}catch(NullPointerException e){
				}
				if(temp == null){
				%>
				<div class="TOS">
				<%if(  directedChallengeForm.getTwoplayermatchDTO() ==null ||  ( directedChallengeForm.getTwoplayermatchDTO().getPlayeroneaccepteddate()==null || ( directedChallengeForm.getTwoplayermatchDTO().getPlayertwoaccepteddate()==null &&  (   directedChallengeForm.getTwoplayermatchDTO().getPlayertwoid() == null || directedChallengeForm.getTwoplayermatchDTO().getPlayertwoid().getId() == DataUtil.getUserIdInteger(request))))){ %>
					<h1>You hereby accept the terms of service.</h1>
					<textarea readonly="readonly">Terms and Condtions</textarea>
				 	<input type="checkbox" id="agree" name="agree"> I agree!
				 <%}else {
				 ForChat =1;
				 %>
				<jsp:include page="../chat.jsp" />
				<!-- chat -->
				<%} %>
				</div>
				<%} %>
				<div class="Options buttons clearfix">
					<table>
					<tr>

 <!-- Assuming player1= the one who creates challenge
 and player2 = the one who is accepting that
 The if's check:
 1.match is yet to be created. or Scheduled date is after current date
 2.if there is match than, is Complete date is null ( or Game is not completed)
 3.The player who is viewing card is player1 and current match is not part of a tournament
 4.Game Scheduledstartdate should be after current date and Challenge is yet to be accepted
 -->
			<%
			if( directedChallengeForm.getTwoplayermatchDTO() == null||directedChallengeForm.getTwoplayermatchDTO().getScheduledstartdate().after(DataUtil.todayGMT())){
				if(directedChallengeForm.getTwoplayermatchDTO()!=null && directedChallengeForm.getTwoplayermatchDTO().getCompleteddate()==null){
					if(directedChallengeForm.getPlayerDTO().getId() == DataUtil.getUserDTO(request).getPlayerDTO().getId() && (session.getAttribute(G4GConstants.TOURNAMENT_ID) == null)) {
						if(directedChallengeForm.getTwoplayermatchDTO().getScheduledstartdate().after(DataUtil.todayGMT()) && directedChallengeForm.getTwoplayermatchDTO().getPlayertwoaccepteddate()==null){
						%>
						<!-- This button will be shown to player1 when no body had accepted his challenge, so that he can dissolve the challenge  and recover his money, if he do not wish to play the match (Here challenge can be open or direct)-->
						 <td>
							<html:submit onclick="checkCheckBox(hasAcceptedTermsForm)" property="operation" styleClass="red" value="<%=G4GConstants.DISSOLVE %>"/>
						 </td>
						<%
						}
					}else{%>
				<!--  Else of second if:
						test's for tournament mode
				-->
					<%
						if(session.getAttribute(G4GConstants.TOURNAMENT_ID) != null) {
						%>
						<td>
						<!-- Yes its tournament: Here we checks that whether the player is going to join the tournament. if true than we will show only accept and not reject	-->
							<html:submit styleClass="green" property="operation" value="<%=G4GConstants.ACCEPT %>"/>
						</td>
					<!-- in case not a tournament than it will go in else -->
						<%
						}else{
						%>
						<!-- Check's whether player2 is there and he had accepted the challenge -->
						<%
							if(directedChallengeForm.getTwoplayermatchDTO()!=null)
								if(directedChallengeForm.getTwoplayermatchDTO().getPlayertwoid()!=null){
									if((directedChallengeForm.getTwoplayermatchDTO().getPlayertwoaccepteddate() == null) && (directedChallengeForm.getTwoplayermatchDTO().getPlayertwoid().getId() == DataUtil.getUserDTO(request).getPlayerDTO().getId())){
				 		%>
		 					<td>
								<!--It is shown when player2 is going to accept direct challenge  in this case playertwoid is not null-->
								<html:submit styleClass="green" property="operation" value="<%=G4GConstants.ACCEPT %>"/>
							</td>
							<td>
							<%
							if(directedChallengeForm.getTwoplayermatchDTO().getPlayertwoid()!=null){ %>
							<!--It shows when player2 is going to accept the direct challenge  -->
								<html:submit styleClass="red" property="operation" value="<%=G4GConstants.REJECT %>" onclick="javascript:setAgree(document.hasAcceptedTermsForm);document.hasAcceptedTermsForm.submit();"/>
							<%} %>
							</td>
							<%
							}
						}else{
						%>
						<!-- this is the case for open challenge where playertwoid=null and player2 is going to accept the challenge-->
						<td>
						<%if(jackpot>0 ||
						(SubNationalCodeServiceDelegator.get(DataUtil.getInteger(challengerUser.getState())).getIslegal().trim().equalsIgnoreCase(G4GConstants.FALSE) )
						||
						(SubNationalCodeServiceDelegator.get(DataUtil.getInteger(oppUser.getState())).getIslegal().trim().equalsIgnoreCase(G4GConstants.FALSE))
						){ %>
							<html:submit styleClass="green" property="operation" value="<%=G4GConstants.ACCEPT %>"/>
						<%} %>
						</td>
						<%
						}
					}
				}
			}
			else{
				// first time creating a match
				if(directedChallengeForm.getTwoplayermatchDTO()==null){%>
						<td>
						<!-- Shown to player1 when he is creating a match  -->
							<html:link styleClass="gray" action="<%=challenge1%>">Edit</html:link>
						</td>
						<td>
							<html:submit onclick="checkCheckBox(hasAcceptedTermsForm)" property="operation" styleClass="green" value="<%=G4GConstants.SEND %>"/>
						</td>
					<%
					}
				}
			}
			%>
			<!-- Checks if its not a tournament
			and if the match is accepted by both the users
			 -->
			<%
			if( session.getAttribute(G4GConstants.TOURNAMENT_ID)==null &&
directedChallengeForm.getTwoplayermatchDTO() !=null &&
directedChallengeForm.getTwoplayermatchDTO().getPlayertwoid() !=null &&
directedChallengeForm.getTwoplayermatchDTO().getPlayertwoaccepteddate()!=null &&
directedChallengeForm.getTwoplayermatchDTO().getGamecompleted()==0 &&
directedChallengeForm.getTwoplayermatchDTO().getCompleteddate() == null	&&
DateUtil.getOneHourEarlyDate(directedChallengeForm.getSchduledDate()).after(DataUtil.todayGMT())) {
					session.setAttribute(G4GConstants.DIRECTED_CHALLENGE_FORM,directedChallengeForm);
					%>
					<td>
					<!-- Shown before one hour ( or as time set in properties ) of scheduled time if both player had accepted challenge  and to both player-->
						<html:submit onclick="checkCheckBox(hasAcceptedTermsForm)" property="operation" styleClass="red" value="<%=G4GConstants.CANCEL_MATCH%>" />
					</td>
					<%

					}else if(directedChallengeForm.getSchduledDate().after(DataUtil.todayGMT()) && directedChallengeForm.getTwoplayermatchDTO()!=null && directedChallengeForm.getTwoplayermatchDTO().getId()>0 && directedChallengeForm.getTwoplayermatchDTO().getPlayertwoaccepteddate()!=null){
					%>
					<td>
					<!-- Shown between  match time and one hr( or as time set in properties )-->
							<%long  min = (directedChallengeForm.getSchduledDate().getTime() - DataUtil.todayGMT().getTime())/(60 * 1000);
							%>
							Playing in <%=min %> minutes
					</td>
					<%
					}else if(directedChallengeForm.getSchduledDate().before(DataUtil.todayGMT()) && directedChallengeForm.getTwoplayermatchDTO().getGamecompleted()==0 ){
					%>
					<td>
					<!-- Shown after  match time and before game is validated-->
							Pending Results Validation
					</td>
					<%
					}
					%>
					<td>
					<!-- Shown always -->
						<a class="green" onclick="self.parent.tb_remove();self.parent.location.reload();" href="directChallenge3.do">close</a>
					</td>
				</tr>
				</table>
				</div><!-- options -->
				</form>
			</div><!-- end ChallengeCard -->
		</div> <!--end DirectChallenge -->
	</div>
</div>

<%
if(ForChat==1){
%>
	 <script type='text/javascript'>
      function sendMessage(f){
       	var curDateTime = new Date()
       var curHour = curDateTime.getHours()
       var curMin = curDateTime.getMinutes()
       var curTime =
         ((curHour < 10) ? "0" : "") + curHour + ":"
          + ((curMin < 10) ? "0" : "") + curMin;
       var text = DWRUtil.getValue("text");
       text = jQuery.trim(text);

		    	if(text != '') {
       text = f + ' []: ' + text;
          DWRUtil.setValue("text", "");
          Chat.addMessage(gotMessages, text, '<%=(game.getId()) + "M" + directedChallengeForm.getTwoplayermatchid()%>' , '<%= DataUtil.getUserDTO(request).getPlayerDTO().getScreenname() %>');
         }
      	}
      function checkMessages()
      {
          Chat.getMessages(gotMessages, '<%=(game.getId()) + "M" + directedChallengeForm.getTwoplayermatchid()%>' , '<%= DataUtil.getUserDTO(request).getPlayerDTO().getScreenname() %>');
      }

      function gotMessages(messages)
      {
          var chatlog = "";
          for (var data in messages)
          {
              chatlog = "<div>" + messages[data].text + "</div>" + chatlog;
          }
          DWRUtil.setValue("div_chat", chatlog);
          setTimeout("checkMessages()", 1000);
      }
      function unsetUser(){
		Chat.unSetUser('<%=(game.getId()) + "M" + directedChallengeForm.getTwoplayermatchid()%>', '<%= DataUtil.getUserDTO(request).getPlayerDTO().getScreenname() %>');
		}
  </script>
<%
}
%>
</body>
</html>
