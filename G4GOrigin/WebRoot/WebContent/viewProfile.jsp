
<%@page import="com.g4g.delegator.NationalCodeServiceDelegator"%>
<%@page import="com.g4g.delegator.G4GFinancialDelegator"%><%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>

<%@page import="com.g4g.delegator.SubNationalCodeServiceDelegator"%>
<%@page import="com.g4g.utils.DateUtil"%>
<%@page import="com.g4g.delegator.PlayerNetworkServiceDelegator"%>
<%@page import="com.g4g.dto.PlayernetworkDTO"%>
<%@page import="com.g4g.dto.NetworkDTO"%>
<bean:define id="siteId" value="wg" type="java.lang.String"/>
 <logic:present name="siteId" >
  <bean:define id="siteId" name="siteId" type="java.lang.String"/>
</logic:present>


<%

ChallengeCardUpdateUtil.clearSession(request);
 %>

<%@page import="com.g4g.dto.PlayerDTO"%>
<%@page import="com.g4g.dto.UserDTO"%>
<%@page import="com.impessa.worldgaming.client.User"%>
<%@page import="com.g4g.delegator.PicturesServiceDelegator"%>
<%@page import="com.g4g.dto.SearchCriteria"%>
<%@page import="java.util.List"%>
<%@page import="com.g4g.dto.PicturesDTO"%>

<%@page import="com.g4g.utils.G4GConstants"%>
<%@page import="com.g4g.dto.PlayercommentsDTO"%>
<%@page import="com.g4g.delegator.PlayerServiceDelegator"%>
<%@page import="com.g4g.utils.DataUtil"%>

<%@page import="com.g4g.utils.SessionUtil"%>
<%@page import="com.g4g.utils.ChallengeCardUpdateUtil"%>
<%@page import="com.g4g.utils.FriendsUtil"%>


<bean:define id="playerDTO" name="playerDTO"></bean:define>

<%

	request.getSession().removeAttribute(G4GConstants.PROFILEUSERID);
	UserDTO userDTO = null;
	User user = null;
	if (session.getAttribute(G4GConstants.USERDTO) != null) {
		userDTO = DataUtil.getUserDTO(request);
		user = userDTO.getUser();
	}

	if(request.getParameter(G4GConstants.PROFILEUSERID)!=null ) {
		user = G4GFinancialDelegator.getUserInfo(((PlayerDTO) playerDTO).getEmailaddress());
		userDTO = (UserDTO) request.getAttribute(G4GConstants.USERDTO);
		userDTO.setPlayerDTO((PlayerDTO) playerDTO);
	}
%>


	<title><bean:write name="playerDTO" property="screenname" />'s Profile</title>

	<bean:define id="playerDTO" name="playerDTO"></bean:define>

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



<div class="pageTitle">
	<img src="WebContent/<bean:write name="siteId"/>/css/images/banners/banners-userprofile.jpg">
</div>

<div id="sidebar">
	<jsp:include page="widgets/search.jsp"></jsp:include>
	<jsp:include page="widgets/myFriends.jsp"></jsp:include>
	<jsp:include page="widgets/playNow.jsp"></jsp:include>
	<jsp:include page="widgets/userTag.jsp">
		<jsp:param name="screenname" value="<%= ((PlayerDTO) playerDTO).getScreenname() %>" />
	</jsp:include>
</div> <!-- end sidebar -->


<div id="content" class="profile">

<!-- <div id="content" class="home">
	<div id="MyDashboard" class="home Widget"> -->

<div id="MyDashboard" class="profile Widget">

	<table>
	<tr valign="top">
		<td>
			<div class="ProfileImage">
				<table>
					<tr>
						<td colspan="2">

						<%if(((PlayerDTO)playerDTO).getAvatars() != null){ %>
							<img height="120" width="120" alt='<bean:write name="playerDTO" property="screenname" />' src="WebContent/displayImage.jsp?dto=<%=G4GConstants.AVATARDTO%>&id=<%= ((PlayerDTO) playerDTO).getAvatars().getId() %>" />
						<%}else{ %>
							<img width="120" height="120" src="WebContent/displayImage.jsp?dto=<%=G4GConstants.PICTUREDTO%>&id=<bean:write name="playerDTO" property="pictureId" />"" />
						<%} %>
						</td>
					</tr>

				<% if(((PlayerDTO)playerDTO).isIsonline()) { %>
					<tr>
						<td align="center"><img width="12" height="12" class="OnlineDotsTimer" border="0" alt="ONLINE" id="<%= ((PlayerDTO)playerDTO).getScreenname() %>" src="WebContent/<bean:write name="siteId"/>/images/icons/status-online.png"></td>
						<td> ONLINE</td>
					</tr>
				<% } else { %>
					<tr>
						<td align="center"><img width="12" height="12" class="OnlineDotsTimer" border="0" alt="OFFLINE" id="<%= ((PlayerDTO)playerDTO).getScreenname() %>" src="WebContent/<bean:write name="siteId"/>/images/icons/status-offline.png"></td>
						<td> OFFLINE</td>
					</tr>
				<% } %>
				<% if(((PlayerDTO)playerDTO).getId() != DataUtil.getUserIdInteger(request)) { %>
					<tr>
						<td align="center"><img src="WebContent/<bean:write name="siteId"/>/images/icons/add.png" width="7" height="7" alt=""></td>
						<td> <a onClick="javascript:sendFriendRequestToUserWithTag('<%= DataUtil.encrypt(String.valueOf(((PlayerDTO)playerDTO).getId())) %>')" style="cursor: pointer;">add to friends</a></td>
					</tr>
					<tr>
						<td align="center"><img src="WebContent/<bean:write name="siteId"/>/images/icons/mail.png" width="10" height="7" alt=""></td>
						<td> <a href="WebContent/sendMessage.jsp?friendId=<%= ((PlayerDTO)playerDTO).getScreenname() %>&TB_iframe=true&height=600&width=650&modal=true" class="modal thickbox">send message</a></td>
					</tr>
				<% } %>
				</table>
			</div> <!-- end ProfileImage -->
		</td>
		<td>
			<div class="ProfileInfo">
				<table>
					<tr>
						<th colspan="2">
							<h1><bean:write name="playerDTO" property="screenname" /></h1>
						</th>
					</tr>
					<tr>
						<td width="16%">W / L</td>
						<td width="32%">
							<b>
							<% if(((PlayerDTO) playerDTO).getPrefdisplayrecord()) { %>
								<logic:equal value="" name="playerDTO"
									property="recordwins">0</logic:equal>
								<logic:notEqual value="" name="playerDTO"
									property="recordwins">
									<bean:write name="playerDTO" property="recordwins" />
								</logic:notEqual>
								/
								<logic:equal value="" name="playerDTO"
									property="recordlosses">0</logic:equal>
								<logic:notEqual value="" name="playerDTO"
									property="recordlosses">
									<bean:write name="playerDTO" property="recordlosses" />
								</logic:notEqual>
							<% } else { %>
								Not Available
							<% } %>
							</b>
						</td>
						<td width="32%">Pending Challenges</td>
						<td>
							<b><%int pending = (Integer)request.getAttribute(G4GConstants.PENDING); %> <%=pending %></b>
						</td>
					</tr>
					<tr>
						<td class="Reputation">Reputation</td>
						<td><span class="Reputation rep<bean:write name="playerDTO" property="reputation" />"></span></td>
						<td>Verifying</td>
						<td>
							<%int verifying = (Integer)request.getAttribute(G4GConstants.VERIFYING); %>
							<b><%=verifying %></b>
						</td>
					</tr>
					<tr>
						<td>Location</td>
						<td colspan="3">
							<b>
								<%
								if (user != null) {
									out.print(user.getCity());
									if(user.getState()!=null) {
										out.print(", ");
										out.print(SubNationalCodeServiceDelegator.getSubNationName(user.getState()));
									}

									if(user.getCountry()!=null) {
										out.print(", ");
										out.print(NationalCodeServiceDelegator.getNationName(user.getCountry()));
									}
								}
								%>
							</b>
						</td>
					</tr>
					<tr><td>Quote</td><td colspan="3"><b><%= DataUtil.getString(((PlayerDTO) playerDTO).getMessage()) %></b></td></tr>
					<tr><td width="16%">About</td><td colspan="3" width="84%"><b><%= DataUtil.getString(((PlayerDTO) playerDTO).getStory()) %></b></td></tr>
					<tr><td>Availability</td><td colspan="3"><b><%= DataUtil.getString(((PlayerDTO) playerDTO).getAvailability()) %></b></td></tr>
				</table>
			</div> <!-- end ProfileInfo -->
		</td>
	</tr>
	</table>
</div> <!-- end MyDashboard -->

<div class="TabNav">
	<ul class="idTabs">
		<li><a href="#RecentOpponents">Recent Games</a></li>
		<li><a href="#MyGamesWidget">Games</a></li>
		<%if(((PlayerDTO) playerDTO).getPrefaliases()) {  %>
			<li><a href="#Aliases">Aliases</a></li>
		<% } %>
		<li><a href="#AvailabilityWidget">Availability</a></li>
		<li><a href="#ReputationWidget">Reputation</a></li>
	</ul>
</div> <!-- end TabNav -->

<div class="TabContent">

<div id="MyGamesWidget" class="Widget">
	<div class="WidgetContent">
		<jsp:include page="Profile_Games.jsp"></jsp:include>
	</div>
</div> <!--  end MyGamesWidget -->

<div id="RecentOpponents" class="Widget">
	<div class="WidgetContent">
		<jsp:include page="Profile_RecentGames.jsp"></jsp:include>
	</div>
</div> <!-- end RecentOpponents -->

<div id="AvailabilityWidget" class="Widget">
	<div class="WidgetContent">
		<b>My Availability</b>
		<p>&nbsp;</p>
		<p><%= DataUtil.getString(((PlayerDTO) playerDTO).getAvailability()) %></p>
	</div>
</div> <!-- end AvailabilityWidget -->

<div id="ReputationWidget" class="Widget">
	<div class="WidgetContent">
		<b>My Reputation</b>
		<p>&nbsp;</p>
		<p><span class="Reputation rep<%= ((PlayerDTO) playerDTO).getReputation() %>"></span></p>
	</div>
</div> <!-- end AvailabilityWidget -->

<%if(((PlayerDTO) playerDTO).getPrefaliases()) {  %>
<div id="Aliases" class="Widget">
	<div class="WidgetContent">
		<table>
		<tr>
			<th width="25%">Contact Info</th>
			<td width="25%"><img src="WebContent/<%=SessionUtil.getSiteId(request) %>/css/images/icons/logos/skype.png" alt="Skype logo" /></td>
			<td><%=request.getAttribute(G4GConstants.SKYPE_ALIAS) %></td>
		</tr>
		<tr>
			<td>&nbsp;</td>
			<td><img src="WebContent/<%=SessionUtil.getSiteId(request) %>/css/images/icons/logos/aim.png" alt="AIM logo" /></td>
			<td><%=request.getAttribute(G4GConstants.AIM_ALIAS) %></td>
		</tr>
		<tr>
			<td>&nbsp;</td>
			<td><img src="WebContent/<%=SessionUtil.getSiteId(request) %>/css/images/icons/logos/msn.png" alt="MSN logo" /></td>
			<td><%=request.getAttribute(G4GConstants.MSN_ALIAS) %></td>
		</tr>
		<tr>
			<td>&nbsp;</td>
			<td><img src="WebContent/<%=SessionUtil.getSiteId(request) %>/css/images/icons/logos/yahoo.png" alt="Yahoo! logo" /></td>
			<td><%=(String)request.getAttribute(G4GConstants.YAHOO_ALIAS) %></td>
		</tr>
		<tr>
			<td>&nbsp;</td>
			<td>Phone</td>
			<td><%=user.getPhone() %></td>
		</tr>
		<tr>
			<th colspan="3">My GamerTag</th>
		</tr>
	<%	String xbox360tag = G4GConstants.NONE;
		String psTag = G4GConstants.NONE;
		SearchCriteria searchCriteria = new SearchCriteria();

		if(userDTO.getPlayernetworkDTOxbox360().getPlayernetworktag()!=null){
		 	xbox360tag = userDTO.getPlayernetworkDTOxbox360().getPlayernetworktag();
		}
		else{
			searchCriteria.removeAllAttribute();
			searchCriteria.setAttribute(G4GConstants.PLAYER, playerDTO);
			searchCriteria.setAttribute(G4GConstants.NETWORK,new NetworkDTO(G4GConstants.NETWORK_XBOX360_LIST_KEY));
			if(PlayerNetworkServiceDelegator.getList(searchCriteria).size() > 0)
				xbox360tag = PlayerNetworkServiceDelegator.getList(searchCriteria).get(0).getPlayernetworktag();
		}
	%>
		<tr>
			<td>&nbsp;</td>
			<td><img src="WebContent/<%=SessionUtil.getSiteId(request) %>/css/images/icons/logos/xboxlive.gif" alt="Xbox Live" /></td>
			<td>Xbox Live<br/><b><%=xbox360tag %></b></td>
		</tr>
	<%
	if(userDTO.getPlayernetworkDTO3ps2().getPlayernetworktag()!=null ||userDTO.getPlayernetworkDTO4ps3().getPlayernetworktag()!=null){
		if(userDTO.getPlayernetworkDTO3ps2().getPlayernetworktag() != null || userDTO.getPlayernetworkDTO4ps3().getPlayernetworktag() != null) {
			if(userDTO.getPlayernetworkDTO3ps2().getPlayernetworktag() != null && userDTO.getPlayernetworkDTO3ps2().getPlayernetworktag() != G4GConstants.NONE) {
				psTag = userDTO.getPlayernetworkDTO3ps2().getPlayernetworktag();
			}
			if(userDTO.getPlayernetworkDTO4ps3().getPlayernetworktag() != null && userDTO.getPlayernetworkDTO4ps3().getPlayernetworktag() != G4GConstants.NONE) {
				psTag = userDTO.getPlayernetworkDTO4ps3().getPlayernetworktag();
			}
		}
	}else{
		searchCriteria.removeAllAttribute();
		searchCriteria.setAttribute(G4GConstants.PLAYER, playerDTO);
		searchCriteria.setAttribute(G4GConstants.NETWORK,new NetworkDTO(G4GConstants.NETWORK_PS2_ID_LIST_KEY));
		searchCriteria.setAttribute(G4GConstants.NETWORK,new NetworkDTO(G4GConstants.NETWORK_PS3_ID_LIST_KEY));
		if(PlayerNetworkServiceDelegator.getList(searchCriteria).size() > 0)
			psTag = PlayerNetworkServiceDelegator.getList(searchCriteria).get(0).getPlayernetworktag();
		}

	%>
		<tr>
			<td>&nbsp;</td>
			<td><img src="WebContent/<%=SessionUtil.getSiteId(request) %>/css/images/icons/logos/psnetwork.gif" alt="PS3" /></td>
			<td><br>PS3 ID<br><b><%=psTag %></b></td>
		</tr>
		</table>
	</div>
</div> <!-- end Aliases -->
<% } %>

</div> <!--  end TabContent -->

<div id="CommentBoardWidget" class="Widget">
	<div class="WidgetHeader">
		<p>Comments</p>
	</div>
	<%
	List<PlayercommentsDTO> playerCommentsList =(List<PlayercommentsDTO>) request.getAttribute(G4GConstants.PLAYERCOMMENTSLIST);
	if (playerCommentsList.size() > 0) {
	%>
		<div class="WidgetContent">
		<%
		for(int index=0;index<playerCommentsList.size();index++){
			PlayercommentsDTO playerCommentsDTO = (PlayercommentsDTO)playerCommentsList.get(index);
			PlayerDTO commentProviderPlayerDTO = playerCommentsDTO.getPlayer();
			%>
			<div class="WidgetSubContent">
				<table cellspacing="0">
				<tr valign="top">
					<td width="70">
					<jsp:include page="common/avatar.jsp">
						<jsp:param name="playerid" value="<%=commentProviderPlayerDTO.getId() %>"/>
						<jsp:param name="avatarid" value="<%= (commentProviderPlayerDTO.getAvatars() != null) ? commentProviderPlayerDTO.getAvatars().getId() : 0 %>"/>
						<jsp:param name="screenname" value="<%=commentProviderPlayerDTO.getScreenname() %>"/>
						<jsp:param name="isOnline" value="<%=commentProviderPlayerDTO.isIsonline() %>"/>
						<jsp:param name="pictureId" value="<%=commentProviderPlayerDTO.getPictureId() %>"/>
						<jsp:param name="showMenu" value="true"/>
						<jsp:param name="isLink" value="true"/>
					</jsp:include>
					</td>
					<td>
						<div class="CommentContent">
							<p class="date"><% out.print(DataUtil.getDate(DateUtil.getDateOfTimeZone(playerCommentsDTO.getCreationdate(), userDTO.getOffset()), "EEE, MMM dd, yyyy 'at' hh:mm a")); %></p>
							<p><%=playerCommentsDTO.getComment() %></p>
							<%
							PlayerDTO loginPlayerDTO = userDTO.getPlayerDTO();
							if(playerCommentsDTO.getTargetplayer().getId() == loginPlayerDTO.getId()){  %>
							<p class="links">
								<a href="#">Reply</a><a href="displayProfile.do?delete=delete&object=comment&id=<%=playerCommentsDTO.getId() %>">Delete</a>
							<%} %>
						</div> <!-- end CommentContent -->
					</td>
				</tr>
				</table>
			</div>
		<%} %>
		</div> <!-- end WidgetContent -->
	<% } %>
	<%request.getSession().setAttribute(G4GConstants.PLAYERCOMMENTSLIST,playerCommentsList); %>
	<div class="PostComment">
		<h3>Post a Comment</h3>
		<html:form action="/profile">
			<html:textarea property="comment"></html:textarea>
			<input type="hidden" name="<%= G4GConstants.PROFILEUSERID %>" value="<%= ((PlayerDTO) playerDTO).getId() %>" />
			<html:submit property="operation" styleClass="green" value="post a comment"></html:submit>
			<html:errors property="comment"/>
		</html:form>
	</div> <!-- end PostComment -->
</div> <!-- end CommentBoardWidget -->

</div> <!--  end content -->

<% session.removeAttribute(G4GConstants.PROFILEUSERID);

ChallengeCardUpdateUtil.clearSession(request);
%>

<%
	if(request.getAttribute(G4GConstants.ERROR)!=null) {
%>
		<script>
			notificationPrompt('<%= request.getAttribute(G4GConstants.ERROR).toString() %>');
		</script>
<%
	}
%>