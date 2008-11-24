<%@page import="com.g4g.dto.UserDTO"%>
<%@page import="com.g4g.dto.PlayerDTO"%>
<%@page import="com.g4g.dto.GameDTO"%>
<%@page import="com.g4g.utils.G4GConstants"%>
<%@page import="com.g4g.utils.DataUtil"%>
<%@page import="com.g4g.dto.OpenMatchDTO"%>

<%@page import="com.g4g.utils.properties.G4GProperties"%>
<%@page import="com.g4g.utils.properties.PropertiesConstants"%>
<%@page import="com.g4g.utils.GamesUtil"%>
<%@page import="java.text.NumberFormat"%>
<%@page import="com.g4g.utils.TournamentsUtil"%>
<%@page import="com.g4g.utils.ChallengeCardUpdateUtil"%>
<%@page import="com.g4g.forms.DirectedChallengeForm"%>
<%@page import="com.g4g.utils.SessionUtil"%>
<%@page import="java.util.List"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles"%>


<bean:define id="siteId" value="wg" type="java.lang.String" />
<logic:present name="siteId">
	<bean:define id="siteId" name="siteId" type="java.lang.String" />
</logic:present>

<%
UserDTO userDTO = DataUtil.getUserDTO(request);
int playerId = userDTO.getPlayerDTO().getId();
PlayerDTO playerDTO_this = userDTO.getPlayerDTO();
GameDTO gameDTO = (GameDTO) request
		.getAttribute(G4GConstants.GAMEDTO);
boolean isGameAvailable = GamesUtil.isGameAvailable(playerDTO_this
		.getId(), gameDTO.getId());
boolean isNetworkAvailable = GamesUtil.isNetWorkAvailable(
		playerDTO_this.getId(), gameDTO.getNetwork().getId());
NumberFormat numberFormat = NumberFormat.getNumberInstance();
numberFormat.setMinimumFractionDigits(2);
%>
<link rel="stylesheet" type="text/css" media="screen" href="WebContent/<bean:write name="siteId"/>/css/thickbox.css">
<link rel="stylesheet" type="text/css" media="screen" href="WebContent/<bean:write name="siteId"/>/css/Chat.css">

<script type="text/javascript" src="<%=DataUtil.getBasePath(request)%>dwr/engine.js"></script>
<script type="text/javascript" src="<%=DataUtil.getBasePath(request)%>dwr/interface/Chat.js"></script>
<script type="text/javascript" src="<%=DataUtil.getBasePath(request)%>dwr/util.js"></script>

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
        	Chat.addMessage(gotMessages, text, '<%=gameDTO.getId()%>', '<%=playerDTO_this.getScreenname()%>');
        }
    }

    function checkMessages()
    {
        Chat.getMessages(gotMessages, '<%=gameDTO.getId()%>', '<%=playerDTO_this.getScreenname()%>');
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
</script>

<script type="text/javascript">
	$(document).ready(function(){
		$(".Sidebar .Widget").OpenClose();
		$(".Avatar").ActiveAvatar();
		$(".AvatarOptions").hide();

		$(".BannerAds ul").innerfade({
			animationtype: 'fade',
			speed: 1750,
			timeout: 6000,
			type: 'sequence',
			containerheight: 'auto'
		});

	});
</script>
<div id="TB_window" style="display: none"></div>
<div id="Games" class="ContentWrapper clearfix">
	<div class="Sidebar">
		<jsp:include page="widgets/search.jsp"></jsp:include>
		<jsp:include page="widgets/myLobbies.jsp"></jsp:include><!-- Widget -->
		<jsp:include page="widgets/activePlayers.jsp"></jsp:include>
		<jsp:include page="widgets/playNow.jsp"></jsp:include>
		<jsp:include page="widgets/adBanner.jsp?NARROW=true"></jsp:include>
		<!-- sidebar adspace -->
	</div>

	<div class="PageContent">
		<div id="gameDetails">
			<div class="GameId" id="<%=gameDTO.getId()%>"></div>
			<div class="PlayerId" id="<%=playerId%>"></div>
			<div class="PlayersOnlineTimer"></div>
			<div id="GamesLobby" class="PageContent">
				<img src="WebContent<%=gameDTO.getBannerimgsrc()%>" width="685" height="71" alt="" />

				<div class="colRight">
					<div class="TabNav clearfix">
						<ul class="idTabs">
							<li>
								<a href="#PlayersOnline">Players Online</a>
							</li>
							<li>
								<a href="#OpenMatches">Open Matches</a>
							</li>
							<li>
								<a href="#AllPlayers">All Players</a>
							</li>
						</ul>
					</div>

					<div class="TabContent">
						<div id="PlayersOnline" class="ListPlayers">
							<div class="Format2 clearfix">
								<div class="grid grid6">
									Player
								</div>
								<div class="grid grid2">
									Reputation
								</div>
								<div class="grid grid1">
									Play For
								</div>
								<div class="grid grid3" style="display: none;">
									Quote
								</div>
								<div class="gridRight grid2">
									Challenge
								</div>
							</div>

							<div id="playersOnlineContent">
								<bean:define id="players" name="gameDetailsForm" property="allPlayers" />
								<logic:iterate id="playerList" name="players">

									<logic:equal value="true" name="playerList"
										property="isonline">
										<%
											String src = "WebContent/"
																+ SessionUtil.getSiteId(request)
																+ "/images/icons/status-online.png";
										%>
										<div class="Format1 clearfix">
											<div class="grid grid6 PlayerName">
												<img width="12" height="12"
													class="statusdot OnlineDotsTimer" border="0"
													alt="ONLINE"
													id="<bean:write name="playerList" property="screenname"/>"
													src="<%=src%>">
												<a
													href="displayProfile.do?<%=G4GConstants.PROFILEUSERID%>=<%=((PlayerDTO) playerList).getId()%>"><bean:write
														name="playerList" property="screenname" /> </a>
											</div>
											<div class="grid grid2">
												<span
													class="Reputation rep<bean:write name="playerList" property="reputation"/>"></span>
											</div>
											<div class="grid grid1">
												<%=DataUtil
									.getPlayForAmount(((PlayerDTO) playerList)
											.getAverageamount())%>
											</div>
											<div class="grid grid1" style="display: none;">
												werty
											</div>
											<logic:equal value="<%=String.valueOf(playerId)%>"
												name="playerList" property="id">
												<div class="gridRight grid2 option notavailable">
													Unavailable
												</div>
											</logic:equal>
											<logic:notEqual value="<%=String.valueOf(playerId)%>"
												name="playerList" property="id">
												<%
													if (!isGameAvailable) {
												%>
												<div class="gridRight grid2 option notavailable">
													Unavailable
												</div>
												<%
													} else {													%>
												<div class="gridRight grid2 option challenge">
													<a
														href="<%=DataUtil.getBasePath(request)%>displayDirectedChallenge1.do?playerId=<bean:write name="playerList" property="id"/>&gameId=<%=gameDTO.getId()%><%=G4GConstants.THICKBOX %>"
														class="thickbox">Challenge!</a>
												</div>
												<%
													}
												%>
											</logic:notEqual>
										</div>
									</logic:equal>
								</logic:iterate>
							</div>
						</div>
						<!-- All players tab -->
						<div id="AllPlayers" class="ListPlayers">
							<div class="Format2 clearfix">
								<div class="grid grid6">
									Player
								</div>
								<div class="grid grid2">
									Reputation
								</div>
								<div class="grid grid1">
									Play For
								</div>
								<div class="grid grid3" style="display: none;">
									Quote
								</div>
								<div class="gridRight grid2">
									Challenge
								</div>
							</div>
							<logic:iterate id="playerList" name="gameDetailsForm"
								property="allPlayers">
								<%
									String src = "";
								%>
								<logic:notEqual value="true" name="playerList"
									property="isonline">
									<%
										src = "WebContent/" + SessionUtil.getSiteId(request)
															+ "/images/icons/status-offline.png";
									%>
								</logic:notEqual>
								<logic:equal value="true" name="playerList"
									property="isonline">
									<%
										src = "WebContent/" + SessionUtil.getSiteId(request)
															+ "/images/icons/status-online.png";
									%>
								</logic:equal>
								<div class="Format1 clearfix">
									<div class="grid grid6 PlayerName">
										<img width="12" height="12"
											class="statusdot OnlineDotsTimer" border="0" alt="ONLINE"
											id="<bean:write name="playerList" property="screenname"/>"
											src="<%=src%>">
										<!-- This anchor link pointed to the userPage component
										 but in order to keep the site linking to the right pages, i had to
										 make it point to viewOwnProfile.html -->
										<a
											href="displayProfile.do?<%=G4GConstants.PROFILEUSERID%>=<%=((PlayerDTO) playerList).getId()%>"><bean:write
												name="playerList" property="screenname" /> </a>
									</div>
									<div class="grid grid2">
										<span
											class="Reputation rep<bean:write name="playerList" property="reputation"/>"></span>
									</div>
									<div class="grid grid1">
										<%=DataUtil
						.getPlayForAmount(((PlayerDTO) playerList)
								.getAverageamount())%>
									</div>
									<div class="grid grid1" style="display: none;">
										werty
									</div>
									<logic:equal value="<%=String.valueOf(playerId)%>"
										name="playerList" property="id">
										<div class="gridRight grid2 option notavailable">
											Unavailable
										</div>
									</logic:equal>
									<logic:notEqual value="<%=String.valueOf(playerId)%>"
										name="playerList" property="id">
										<%
										if (isNetworkAvailable) {
											if (!isGameAvailable) {
										%>
										<div class="gridRight grid2 option notavailable">
											Unavailable
										</div>
										<%
											} else {
										%>
										<div class="gridRight grid2 option challenge">
											<a
												href="<%=DataUtil.getBasePath(request)%>displayDirectedChallenge1.do?playerId=<bean:write name="playerList" property="id"/>&gameId=<%=gameDTO.getId()%><%=G4GConstants.THICKBOX %>"
												class="thickbox">Challenge!</a>
										</div>
										<%
											}}
										%>
									</logic:notEqual>
								</div>
							</logic:iterate>
						</div>

						<!-- Open Matches tab -->
						<div id="OpenMatches">
							<div id="openTournamentsContent">
								<jsp:include page="openMatches.jsp">
									<jsp:param name="gameId" value="<%=gameDTO.getId() %>"/>
									<jsp:param name="networkid" value="<%=gameDTO.getNetwork().getId() %>"/>
									<jsp:param name="playerId" value="<%=playerDTO_this.getId() %>"/>
									<jsp:param name="isStateIllegal" value="<%=DataUtil.getUserDTO(request).isStateIllegal() %>"/>
								</jsp:include>
							</div>
						</div>
						<!-- Open matches -->
					</div>
					<!-- tabcontent -->
				</div>
				<div class="colLeft">
					<div class="widthFull clearfix CenterAvatar">
						<!--  -->
						<jsp:include page="common/gameAvatar.jsp">
							<jsp:param name="isUser" value="lobby" />
							<jsp:param name="imgSrc"
								value="<%=gameDTO.getImgsrc().trim()%>" />
							<jsp:param name="gameid" value="<%=gameDTO.getId()%>" />
							<jsp:param name="gamename" value="<%=gameDTO.getGamename()%>" />
							<jsp:param name="isGameavailable" value="<%=isGameAvailable%>" />
							<jsp:param name="isNetworkAvailable"
								value="<%=isNetworkAvailable%>" />
							<jsp:param name="showMenu" value="true" />
						</jsp:include>
					</div>
					<%
						if (isGameAvailable) {
					%>
					<div class="FastPlay">
						<a
							href="<%=DataUtil.getBasePath(request)%>displayDirectedChallenge1.do?gameId=<%=gameDTO.getId()%><%=G4GConstants.THICKBOX %>"
							class="thickbox"><img
								src="WebContent/<bean:write name="siteId"/>/images/Buttons/FastPlay.jpg"
								class="BlockButton" width="135" height="22"></a>
						<p>
							Create a Game in the Open Matches Lobby to get started right
							away!
						</p>
					</div>
					<!-- fast Play -->
					<%
						}
					%>
					<dl class="HelpTopics">
						<dt>
							Help Topics
						</dt>
						<dd>
							<a
								href="WebContent/<bean:write name="siteId"/><%=gameDTO.getIntroduction() != null ? gameDTO
		.getIntroduction() : "/doc/introduction.html"%>?KeepThis=true&TB_iframe=true&height=500&width=600"
								style="cursor: pointer;" class="thickbox"><span>Intro
									to <%=gameDTO.getGamename()%></span> </a>
						</dd>
						<dd>
							<a
								href="WebContent/<bean:write name="siteId"/><%=gameDTO.getMultilayersupport() != null ? gameDTO
		.getMultilayersupport() : "/doc/multilayersupport.html"%>?KeepThis=true&TB_iframe=true&height=500&width=600"
								style="cursor: pointer;" class="thickbox"><span>MultiPlayer
									Support</span> </a>
						</dd>
						<dd>
							<a
								href="WebContent/<bean:write name="siteId"/><%=gameDTO.getHeadtoheadfaq() != null ? gameDTO
		.getHeadtoheadfaq() : "/doc/headtoheadfaq.html"%>?KeepThis=true&TB_iframe=true&height=500&width=600"
								style="cursor: pointer;" class="thickbox"><span>Helpful
									Hints</span> </a>
						</dd>
					</dl>

					<div class="support">
						<a
							href="mailto:<%=G4GProperties
						.getProperty(PropertiesConstants.ADMIN_EMAIL_SUPPORT)%>"><img
								src="WebContent/<bean:write name="siteId"/>/images/Buttons/EmailSupportButton.png"
								class="BlockButton"> </a>
					</div>
					<!-- support -->
				</div>
			</div>
			<!-- PageContent -->

			<div id="Chat" class="clearfix" style="clear: both;">
				<div class="AreaWrapperTitle">
					<img
						src="WebContent/<bean:write name="siteId"/>/css/images/banners/banners-lobbychat.jpg">
				</div>
				<!-- AreaWrapperTitle -->
				<p class="grid grid5 margRight10">
					Taunt, trash talk, challenge, share recipes &amp; gardening
					tips.
				</p>

				<div class="grid">
					<jsp:include page="../chat.jsp">
						<jsp:param name="game" value="<%=gameDTO.getId()%>" />
					</jsp:include>
				</div>
			</div>
			<!-- chat -->
			<!-- chat -->

		</div>
	</div>
	<!-- PageContent -->
</div>
<%
ChallengeCardUpdateUtil.clearSession(request);
%>
<script type="text/javascript">
document.getElementsByTagName('body')[0].onload 	= Chat.setUser('<%=gameDTO.getId()%>', '<%=playerDTO_this.getScreenname()%>'); setTimeout('checkMessages()', 1000);
document.getElementsByTagName('body')[0].onunload 	= Chat.unSetUser('<%=gameDTO.getId()%>', '<%=playerDTO_this.getScreenname()%>');
</script>
<script type="text/javascript">
var currentG4GTag = 'lengrant';
</script>
