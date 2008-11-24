
<%@page import="com.g4g.delegator.SubNationalCodeServiceDelegator"%>
<%@page import="com.g4g.utils.DataValidator"%>
<%@page import="com.g4g.utils.DataUtil"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
			"http://www.w3.org/TR/html4/loose.dtd">

<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>
<%@page import="com.g4g.utils.G4GConstants"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Iterator"%>
<%@page import="com.g4g.dto.GameoptionsDTO"%>
<%@page import="com.g4g.dto.PlayerDTO"%>
<%@page import="com.g4g.delegator.NationalCodeServiceDelegator"%>
<%@page import="com.impessa.worldgaming.client.User"%>

<bean:define id="siteId" value="wg" type="java.lang.String" />
<logic:present name="siteId">
	<bean:define id="siteId" name="siteId" type="java.lang.String" />
</logic:present>

<html>
<head>
	<meta content="text/html; charset=UTF-8" http-equiv="Content-Type">
	<link rel="stylesheet" type="text/css" media="screen" href="WebContent/<bean:write name="siteId"/>/css/Global.css">
	<link rel="stylesheet" type="text/css" media="screen" href="WebContent/<bean:write name="siteId"/>/css/Widgets.css">
	<link rel="stylesheet" type="text/css" media="screen" href="WebContent/<bean:write name="siteId"/>/css/Challenge.css">
	<link rel="stylesheet" type="text/css" media="screen" href="WebContent/<bean:write name="siteId"/>/css/Tables.css">
	<link rel="stylesheet" type="text/css" media="screen" href="WebContent/<bean:write name="siteId"/>/css/Chat.css">
	<link rel="stylesheet" type="text/css" media="screen" href="WebContent/<bean:write name="siteId"/>/css/rating.css">
	<link rel="stylesheet" type="text/css" media="screen" href="WebContent/<bean:write name="siteId"/>/css/avatar.css">
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
	<script type="text/javascript" src="WebContent/js/AJAXScript.js"></script>
</head>

<body>

	<div id="content">
		<html:form method="post" styleId="form" action="/directChallenge1">
		<bean:define id="directedChallengeForm" name="directedChallengeForm" scope="session" type="com.g4g.forms.DirectedChallengeForm" />
		<bean:define id="challengePlayer" name="directedChallengeForm" property="playerDTO" type="com.g4g.dto.PlayerDTO" scope="session" />
		<bean:define id="challengerUser" name="directedChallengeForm" property="challengerUser" type="com.impessa.worldgaming.client.User" scope="session" />
		<%
		PlayerDTO oppPlayer = new PlayerDTO();
		User oppUser = new User();
		try{
			oppPlayer  = directedChallengeForm.getOppPlayerDTO();
			oppUser = directedChallengeForm.getOppUser();
		}
			catch(NullPointerException e){
		}
		%>
		<bean:define id="game" name="directedChallengeForm" property="gameDTO" type="com.g4g.dto.GameDTO" scope="session" />

		<div id="DirectChallenge" class="">
			<div class="SubNav">
				<ul class="idTabs">
					<li class="selected">1. Match Details</li>
					<li>2. Confirmation</li>
					<li>3. Good Luck</li>
				</ul>
			</div>
			<div class="Message organize">
				<h1>You are Organizing a Tournament!</h1>
				<p>Select your options carefully, then click &quot;Confirm&quot;</p>
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
								<bean:write property="recordwins" name="challengePlayer"/>
								/
							 	<bean:write property="recordlosses" name="challengePlayer"/>
							 </td>
						</tr>
						<!-- tr>
							<th scope="row">WG Points</th>
							<td>yet to get</td>
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
				<%try{
				oppPlayer.getId();
				%>
					<div class="Opponent">
						<jsp:include page="common/avatar.jsp">
							<jsp:param name="playerid" value="<%=oppPlayer.getId() %>"/>
							<jsp:param name="avatarid" value="<%= (oppPlayer.getAvatars() != null) ? oppPlayer.getAvatars().getId() : 0 %>"/>
							<jsp:param name="screenname" value="<%=oppPlayer.getScreenname() %>"/>
							<jsp:param name="isOnline" value="<%=oppPlayer.isIsonline() %>"/>
							<jsp:param name="showMenu" value="false"/>
							<jsp:param name="pictureId" value="<%=oppPlayer.getPictureId()%>"/>
							<jsp:param name="isLink" value="false"/>
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
							<td>Yet to implement</td>
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
				<%}catch(NullPointerException e){%>
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
						<table class="tableGameDetails">
						<tr>
							<th scope="row">Entry Fee <b>$</b></th>
							<td>
							<%if((oppUser!=null && SubNationalCodeServiceDelegator.get(DataUtil.getInteger(oppUser.getState())).getIslegal().trim().equalsIgnoreCase(G4GConstants.FALSE) )
							 ){
							%>
							Your opponent can only play for $0'.
								<html:hidden property="amountField" value="0"/>
							<%
							}else if(DataUtil.getUserDTO(request).isStateIllegal()){
							%>
							Your region does not support Skilled Gaming.
								<html:hidden property="amountField" value="0"/>
							<%
							}else{
							%>
								<html:text styleId="amountField" property="amountField" onblur="javascript:calculateTournamentJackpot()"></html:text>
								<html:errors property="amountField"/>
							<%} %>
							</td>
						</tr>
						<tr>
							<th scope="row"><img src="WebContent/<bean:write name="siteId"/>/css/images/icons/jackpot-challengecard.png" alt="Jackpot"></th>
							<td>
								<div style="display: none;" id="numberOfSlots" title="2"></div>
								<div id="dynamicTotalAmount">
									$0.00
								</div>
								<script type="text/javascript">
								calculateTournamentJackpot();
								</script>
							</td>
						</tr>
						<tr>
							<th scope="row">Console</th>
							<td>
								<bean:write property="gameConsole" name="directedChallengeForm"/>
							</td>
						</tr>
						<tr>
							<th scope="row">Game</th>
							<td><%=game.getGamename()%></td>
						</tr>
					<%int i = (Integer)request.getAttribute("totalList");
					String tempi=null;
					String [] gameSetting = new String[i];
					for(int j=1;j<i;j++){
						List<GameoptionsDTO> list = (List<GameoptionsDTO>)request.getSession().getAttribute("List"+j);
						Iterator<GameoptionsDTO> it = list.iterator();
						GameoptionsDTO dto = (GameoptionsDTO)it.next();
						gameSetting[(j-1)]=dto.getOption();
						%>
						<tr>
							<th scope="row"><%=dto.getOption() %></th>
							<td>
								<input type="hidden" id="optionsequenceid<%=j %>" value="<%=dto.getOptionsequenceid() %>">
								<input type="hidden" id="gameid" value="<%=game.getId() %>">
								<%if(tempi==null){%>
								<select name="gameOptionSelected" id="gameOptionSelected<%=j %>" onchange="populate1Option(this.value , document.getElementById('gameid').value ,  document.getElementById('optionsequenceid<%= j %>').value)"  >
								<%}else{ %>
								<select name="gameOptionSelected" id="gameOptionSelected<%=j %>" onchange="populate1Option(this.value , document.getElementById('gameid').value ,  document.getElementById('optionsequenceid<%= j %>').value)" disabled="disabled" onclick="populate1Option(this.value , document.getElementById('gameid').value ,  document.getElementById('optionsequenceid<%= j %>').value)" >
								<%} %>
									<option value="">&lt;Choose a game option&gt;</option>
									<%
										int temp=list.size();
										Iterator<GameoptionsDTO> itr=list.iterator();
										for(int k=0;k<temp;k++){
										GameoptionsDTO dtolist = (GameoptionsDTO)itr.next();
									%>
									<option value="<%=String.valueOf(dtolist.getValueid())%>" ><%=dtolist.getValue()%></option>
									<%
										}
										directedChallengeForm.setGameSetting(gameSetting);
									%>
								</select>
								<%if(tempi==null){
								 tempi="disabled";
								 } %>
							</td>
						</tr>
						<%
						}
						%>
						<tr>
							<th scope="row">Date/Time</th>
							<td>
								<script type="text/javascript">
								$(document).ready(function() {
									/*console.log("about to stop parent timers");
									parent.stopTimers();
									console.log("about to stop modal timers");
									stopTimers();*/
									var today = new Date().addDays(-1);
									var curHour =  today.getHours();
									var optionDate = null;
									var optionMonth = null;
									var optionYear = null;
									var dayName = null;
									var monthName = null;
									$("#matchDate").addOption("", "");

									for(i=0; i<=2; i++) {
										today = today.addDays(1);
										sDate = today.getDate();
										sYear = today.getFullYear();
										dayName = today.getDayName(true);
										monthName = today.getMonthName(true);
										$("#matchDate").addOption(today.asString(),
											dayName + ", " + monthName + " " + sDate + ", " + sYear);
									}

									if($("#tournamentDate").get(0).value != "") {
										//pre-select the pre-existing date in the dropdowns

										var tournDateString = $("#tournamentDate").get(0).value.split(" at ");

										$("#matchDate").selectOptions(tournDateString[0]);
										var preHour = tournDateString[1].substring(0,2);

										preHour = preHour.replace(":", "");
										if(preHour < 10){
										preHour = "" + preHour.replace("0", "");
										tournDateString[1] = "0"+tournDateString[1];
										}

										var preMin = tournDateString[1].substring(3,5);
										var preAMPM = tournDateString[1].substring(6);

										if(preMin.match(":")){
										preMin = tournDateString[1].substring(4,6);
										preAMPM = tournDateString[1].substring(7);
										}

										console.log(preHour + " " +  preMin + " " + preAMPM);
										$("#matchHour").selectOptions(preHour);
										$("#matchMin").selectOptions(preMin);
										$("#matchAMPM").selectOptions(preAMPM);
									}
									else {
										$("#matchDate").get(0).selectedIndex = 0;
										setHiddenDate();
									}
								});
								</script>
								<select id="matchDate" size="1" onchange="setHiddenDate()"></select>
								<br><br>
								<select id="matchHour" size="1" onchange="setHiddenDate()" style="width: 37px">
									<option value"">
									<option value="1">1</option>
									<option value="2">2</option>
									<option value="3">3</option>
									<option value="4">4</option>
									<option value="5">5</option>
									<option value="6">6</option>
									<option value="7">7</option>
									<option value="8">8</option>
									<option value="9">9</option>
									<option value="10">10</option>
									<option value="11">11</option>
									<option value="12">12</option>
								</select>
								<select id="matchMin" size="1" onchange="setHiddenDate()" style="width: 37px">
									<option value=""></option>
									<option value="00">00</option>
									<option value="15">15</option>
									<option value="30">30</option>
									<option value="45">45</option>
								</select>
								<select id="matchAMPM" size="1" onchange="setHiddenDate()" style="width: 40px">
									<option value=""></option>
									<option value="AM">AM</option>
									<option value="PM">PM</option>
								</select>
								<br>
								<html:errors property="tournamentDate"/>
								<html:hidden property="tournamentDate" styleId="tournamentDate" />
							</td>
						</tr>
						</table>

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
							<html:textarea property="comments" styleId="comments" />
							<html:submit property="operation" styleClass="green" value="<%=G4GConstants.ADD_COMMENTS%>" />
						</div>
					</div><!-- tournament details -->
					<div class="Options buttons clearfix">
						<table>
						<tr>
							<td><html:link action="/directChallenge3.do" styleClass="red" onclick="javascript:parent.resetTimers();resetTimers();parent.tb_remove(false);">Cancel</html:link></td>
							<td><html:link href="#" styleClass="green" onclick="javascript:document.directedChallengeForm.submit();">Continue</html:link></td>
						</tr>
						</table>
					</div><!-- options -->
				</div><!-- details -->
			</div><!-- end ChallengeCard -->
		</div><!--end DirectChallenge -->
		</html:form>
	</div>
</body>
</html>