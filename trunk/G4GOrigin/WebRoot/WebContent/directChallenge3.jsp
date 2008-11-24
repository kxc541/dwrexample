<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<%@page import="com.g4g.utils.G4GConstants"%>
<%@page import="com.g4g.dto.GameoptionsDTO"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Iterator"%>
<%@page import="com.g4g.dto.PlayerDTO"%>
<%@page import="com.g4g.utils.DataUtil"%>
<%@page import="com.impessa.worldgaming.client.User"%>
<%@page import="com.g4g.delegator.NationalCodeServiceDelegator"%>
<%@page import="com.g4g.utils.DateUtil"%>
<%@page import="com.g4g.forms.DirectedChallengeForm"%>
<%@page import="com.g4g.utils.ChallengeCardUpdateUtil"%>
<%@page import="com.g4g.utils.DataValidator"%>
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
<logic:notPresent name="directedChallengeForm" scope="session" >
	<jsp:forward page="removeThickbox.jsp"></jsp:forward>
</logic:notPresent>
<bean:define id="directedChallengeForm" name="directedChallengeForm" scope="session" type="com.g4g.forms.DirectedChallengeForm" />
<bean:define id="challengePlayer" name="directedChallengeForm" property="playerDTO"type="com.g4g.dto.PlayerDTO" scope="session" />
<bean:define id="challengerUser" name="directedChallengeForm" property="challengerUser"type="com.impessa.worldgaming.client.User" scope="session" />
<%
PlayerDTO oppPlayer = new PlayerDTO();
User oppUser = new User();
try{
oppPlayer  = directedChallengeForm.getOppPlayerDTO();
oppUser = directedChallengeForm.getOppUser();
if(oppPlayer.getId()==challengePlayer.getId())oppPlayer = null;
}
catch(NullPointerException e){
}
%>
<bean:define id="game" name="directedChallengeForm" property="gameDTO" type="com.g4g.dto.GameDTO" scope="session" />
   	<script type="text/javascript" src="/G4GOrigin/dwr/engine.js"></script>
	<script type="text/javascript" src="/G4GOrigin/dwr/interface/Chat.js"></script>
	<script type="text/javascript" src="/G4GOrigin/dwr/util.js"></script>
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
<%if(oppPlayer!=null){ %>
<body onload="Chat.setUser('<%=(game.getId()) %>', '<%= DataUtil.getUserDTO(request).getPlayerDTO().getScreenname() %>'); setTimeout('checkMessages()', 1000);" >.i&nbsp;
	<%}else {%>
<body>
<%} %>
<div id="content">
	<div id="DirectChallenge">
		<div class="SubNav">
			<ul class="idTabs">
				<li>1. Match Details</li>
				<li>2. Confirmation</li>
				<li class="selected">3. Good Luck</li>
			</ul>
		</div>
		<div class="Message organize">
			<h1>Good Luck!!</h1>
			<p>If other players are online, you can chat in this room</p>
			<p>You are entered as a player in this competition; &quot;Close&quot; to exit</p>
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
						<td><span class="Reputation rep<%=challengePlayer.getReputation()%>"></span></td>
					</tr>
					<tr>
						<th scope="row">W/L</th>
						<td><bean:write property="recordwins" name="challengePlayer"/>
								 /
								 <bean:write property="recordlosses" name="challengePlayer"/></td>
					</tr>
					<!-- tr>
						<th scope="row">WG Points</th>
						<td>100</td>
					</tr -->
					<tr>
						<th scope="row">Location</th>
						<td>
						<bean:write property="city" name="challengerUser"/>
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
	<% String challenge1 = "displayDirectedChallenge1.do?"+"gameId="+game.getId()+"&"+G4GConstants.TWO_PLAYER_MATCH_ID+"="+DataUtil.getChallengeLink(directedChallengeForm);
	Boolean lobby = true;
	try{
		oppPlayer.getId();
	%>
				<div class="Opponent">
					<jsp:include page="common/avatar.jsp">
						<jsp:param name="playerid" value="<%=oppPlayer.getId() %>"/>
						<jsp:param name="avatarid" value="<%= (oppPlayer.getAvatars() != null) ? oppPlayer.getAvatars().getId() : 0 %>"/>
						<jsp:param name="screenname" value="<%=oppPlayer.getScreenname() %>"/>
						<jsp:param name="isOnline" value="<%=oppPlayer.isIsonline() %>"/>
						<jsp:param name="pictureId" value="<%=oppPlayer.getPictureId() %>"/>
						<jsp:param name="showMenu" value="false"/>
						<jsp:param name="isLink" value="false"/>
					</jsp:include>
					<table class="ccPlayerInfo">
					<tr>
						<th scope="row">Reputation</th>
						<td>
							<span class="Reputation rep<%=oppPlayer.getReputation()%>"></span>
						</td>
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
		<% challenge1 = "displayDirectedChallenge1.do?playerId="+oppPlayer.getId()+"&gameId="+game.getId()+"&"+G4GConstants.TWO_PLAYER_MATCH_ID+"="+DataUtil.getChallengeLink(directedChallengeForm);%>
	<%}catch(NullPointerException e){
	lobby = false;
	%>

				<div class="Opponent">
			    	<h4>Awaiting<br/>Challenger</h4>
	    		</div><!-- end Opponent -->
			<%
			} %>
				<div class="GameDetails">
					<div class="vs"><span>VS</span></div>
					<!-- div class="Avatar GameAvatar" id="gamev5749rcf0o38g">
						<div class="AvatarImage">
							<img src="WebContent<%=game.getImgsrc()%>" alt="<%=game.getGamename()%>">
						</div>
					</div-->
					<!-- This section should be turned into a separate component with edit in place features -->
				<table class="tableGameDetails">
				<tr>
					<th scope="row"><img src="WebContent/<bean:write name="siteId"/>/css/images/icons/jackpot-challengecard.png" alt="Jackpot"></th>
					<td>
					<%
					double jackpot = Double.parseDouble(directedChallengeForm.getAmountField())*2;
					jackpot = jackpot - jackpot*0.1;
					%>
						<strong>$<%=DataUtil.format(jackpot)%></strong>
					</td>
				</tr>
				<tr>
					<th scope="row">Console</th><td><%=directedChallengeForm.getGameConsole() %></td>
				</tr>
				<tr>
					<th scope="row">Game</th>
					<td><%=game.getGamename() %></td>
				</tr>
				<%
				List<GameoptionsDTO> list = directedChallengeForm.getGameoptionDTO();
				Iterator<GameoptionsDTO> it = list.iterator();
				 while(it.hasNext()){
					GameoptionsDTO dto =it.next();
					if(dto!=null && dto.getOption()!=null && dto.getValue()!=null){
					%>
					<tr>
						<th scope="row"><%=dto.getOption() %></th>
						<td><%=dto.getValue() %></td>
					</tr>
					<%
					} }%>
					<tr>
						<th scope="row">Date/Time</th>
						<%if(challengePlayer.getId() != DataUtil.getInteger(DataUtil.getUserId(request))){%>
						<td><%=DataUtil.getDate(DateUtil.getDateOfTimeZone(directedChallengeForm.getSchduledDate() , DataUtil.getUserDTO(request).getOffset()) , G4GConstants.DATE_EEE_MMM_DD_YYYY_AT_hh_MM_A)%></td>
						<%}else{ %>
						<td><%=directedChallengeForm.getTournamentDate()%></td>
						<%} %>
					</tr>
					</table>
					<html:form action="/directChallenge3" method="post" styleId="directChallenge3Form">
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
						<html:textarea property="comments" styleId="comments"  value=""/>
						<html:submit property="operation" styleClass="green" value="<%=G4GConstants.ADD_COMMENTS%>" />
					</div>
					</html:form>
				</div>
			</div><!-- tournament details -->
		<%if(lobby){ %>
			<div class="ChatWrap">
				<h3>Live Chat</h3>
				<p>Discuss the upcoming match details in real time</p>
				<jsp:include page="../chat.jsp" />
			</div>
			<!-- chat -->
		<%} %>
			<div class="Options buttons clearfix">
				<table>
				<tr>
					<td>
					<%if(directedChallengeForm.getOppPlayerDTO()==null || directedChallengeForm.getOppPlayerDTO().getId()!=DataUtil.getUserDTO(request).getPlayerDTO().getId()){ %>
						<a class="gray" href="<%=challenge1%>">edit details</a>
					<%} %>
					</td>
					<td>
						<a class="green" onclick="self.parent.tb_remove();self.parent.location.reload();" href="directChallenge3.do">close challenge card</a>
						<!-- javascript:parent.resetTimers();parent.tb_remove(false); -->
					</td>
				</tr>
				</table>
			</div><!-- options -->
		</div><!-- end ChallengeCard -->
	</div> <!--end DirectChallenge -->
</div>
	<!-- for chat -->

	<%if(oppPlayer!=null){ %>
	 <script type='text/javascript'>
      function sendMessage(f)
      {
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
          Chat.addMessage(gotMessages, text, '<%=(game.getId()) + "M" + directedChallengeForm.getTwoplayermatchid() %>' , '<%= DataUtil.getUserDTO(request).getPlayerDTO().getScreenname() %>');
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

		<%}
		%>
</body>
</html>