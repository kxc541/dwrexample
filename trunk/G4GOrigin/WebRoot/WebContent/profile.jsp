
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

	<title><bean:write name="playerDTO" property="screenname" />'s Home</title>
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

		<% if(session.getAttribute(G4GConstants.LOAD_PICTURE) != null) {
			session.removeAttribute(G4GConstants.LOAD_PICTURE);
		%>
			$("#MyPictures").toggle();
			$("#ScheduleWidget").toggle();
			$("#aMyPictures").addClass("selected");
			$("#aScheduleWidget").removeClass("selected");
		<% } else if(session.getAttribute(G4GConstants.LOAD_EDIT_PROFILE) != null) {
			session.removeAttribute(G4GConstants.LOAD_EDIT_PROFILE);
		%>
			$("#EditProfileWidget").toggle();
			$("#ScheduleWidget").toggle();
			$("#aEditProfileWidget").addClass("selected");
			$("#aScheduleWidget").removeClass("selected");
		<% } %>

		});

	</script>



<div class="pageTitle">
	<img src="WebContent/<bean:write name="siteId"/>/css/images/banners/banners-myhome.jpg">
</div>

<div id="sidebar">
	<jsp:include page="widgets/search.jsp"></jsp:include>
	<jsp:include page="widgets/playNow.jsp"></jsp:include>
	<jsp:include page="widgets/news.jsp" />
	<jsp:include page="widgets/adBanner.jsp"></jsp:include>
	<jsp:include page="widgets/feedback.jsp"></jsp:include>
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
						<th colspan="2" class="buttons">
							<a class="blue" style="float: right;" href="displayProfile.do?profileUserId=<%=userDTO.getPlayerDTO().getId()%>">View Profile</a>
						</th>
					</tr>
					<tr>
						<td width="16%">W / L</td>
						<td width="32%">
							<b>
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

					<tr>
						<td>Balance</td>
						<td colspan="3">
							<b>
							<%
								if (userDTO != null) {
									if (userDTO.getBalance() > 0) {
							%>
										<bean:write name="userDTO" property="balance" format="$##0.00" />
							<%
									}
								} else {
									out.print("$0.00");
								}
							%>
							</b>
						</td>
					</tr>
				</table>
			</div> <!-- end ProfileInfo -->
		</td>
	</tr>
	</table>
</div> <!-- end MyDashboard -->

<div class="TabNav">
	<ul class="idTabs">
		<li><a href="#ScheduleWidget" id="aScheduleWidget">My Schedule</a></li>
		<li><a href="#RecentOpponents">Recent Games</a></li>
		<li><a href="#MyWallet">Cashier</a></li>
		<li><a href="#MyFriendsWidget">Friends</a></li>
		<li><a href="#MyGamesWidget">Games</a></li>
		<li><a href="#Aliases">Aliases</a></li>
		<li><a href="#EditProfileWidget" id="aEditProfileWidget">Edit Profile</a></li>
	</ul>
</div> <!-- end TabNav -->

<div class="TabContent">

	<div id="ScheduleWidget" class="Widget">
		<div class="WidgetContent">
			<jsp:include page="Profile_MySchedule.jsp"></jsp:include>
		</div>
	</div> <!--  end ScheduleWidget -->

	<div id="MyFriendsWidget" class="Widget">
		<div class="WidgetContent">
			<jsp:include page="Profile_Friends.jsp"></jsp:include>
		</div>
	</div> <!-- end MyFriendsWidget -->

	<div id="MyGamesWidget" class="Widget">
		<div class="WidgetContent">
			<jsp:include page="Profile_Games.jsp"></jsp:include>
		</div>
	</div> <!--  end MyGamesWidget -->

	<div id="EditProfileWidget" class="Widget">
		<div class="WidgetContent">
			<jsp:include page="Profile_EditProfile.jsp"></jsp:include>
		</div>
	</div> <!-- end EditProfileWidget -->

	<div id="RecentOpponents" class="Widget">
		<div class="WidgetContent">
			<jsp:include page="Profile_RecentGames.jsp"></jsp:include>
		</div>
	</div> <!-- end RecentOpponents -->

	<div id="MyWallet" class="Widget">
		<div class="WidgetContent">
			<h1>
				Current Balance:
				<%
				if (userDTO != null) {
						if (userDTO.getBalance() != null) {
				%>
					<%= "$" + DataUtil.format(userDTO.getBalance()) %>
				<%
						}else{
						out.print("$0.00");
						}
				} else {
					out.print("$0.00");
				}
				%>
			</h1>
			<br>
			<input onclick="window.location.href='displayCashier.do';" type="button" class="green" value="withdraw money">&nbsp;&nbsp;
			<input onclick="window.location.href='displayCashier.do';" type="button" class="green" value="add money">
		</div>
	</div> <!-- end MyWallet -->

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

</div> <!--  end TabContent -->

<div id="CommentBoardWidget" class="Widget">
	<div class="WidgetHeader">
		<p>Comments</p>
	</div>
	<%
	List playerCommentsList =(List) request.getAttribute(G4GConstants.PLAYERCOMMENTSLIST);
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