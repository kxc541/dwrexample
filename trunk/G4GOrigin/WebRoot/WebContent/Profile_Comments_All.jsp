<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<bean:define id="siteId" value="wg" type="java.lang.String"/>
 <logic:present name="siteId" >
  <bean:define id="siteId" name="siteId" type="java.lang.String"/>
</logic:present>
<%@page import="com.g4g.utils.DataUtil"%>
<%@page import="com.g4g.utils.G4GConstants"%>
<%@page import="com.g4g.delegator.PlayerServiceDelegator"%>
<%@page import="com.g4g.dto.PlayercommentsDTO"%>
<%@page import="java.util.List"%>
<%@page import="com.g4g.dto.PlayerDTO"%>

<%@page import="com.g4g.utils.FriendsUtil"%>
<%@page import="com.g4g.utils.DateUtil"%>
<%@page import="com.g4g.dto.UserDTO"%>
<% UserDTO userDTO = new UserDTO();
	if(DataUtil.getUserDTO(request) != null)
		userDTO = DataUtil.getUserDTO(request);
 %>
<script type="text/javascript"
	src="WebContent/<bean:write name="siteId"/>/lib/firebug/firebug.js"> </script>

<script type="text/javascript"
	src="WebContent/<bean:write name="siteId"/>/js/jquery.js"> </script>

<script type="text/javascript"
	src="WebContent/<bean:write name="siteId"/>/js/jquery.timer.js"> </script>

<!--
		<script type="text/javascript" src="WebContent/<bean:write name="siteId"/>/js/wgLib.js"> </script>
		 -->
<script type="text/javascript"
	src="WebContent/<bean:write name="siteId"/>/js/OpenClose.js"> </script>

<script type="text/javascript"
	src="WebContent/<bean:write name="siteId"/>/js/ActiveAvatar.js"> </script>

<script type="text/javascript"
	src="WebContent/<bean:write name="siteId"/>/js/jquery.idTabs.pack.js"> </script>

<script type="text/javascript"
	src="WebContent/<bean:write name="siteId"/>/js/thickbox.js"> </script>

<script type="text/javascript"
	src="WebContent/<bean:write name="siteId"/>/js/jquery.innerfade.js"> </script>

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

<div id="CommentBoardWidget" class="Widget">
	<div class="WidgetHeader">
		<h1>
			Comments
		</h1>
	</div>
	<!-- WidgetHeader -->

	<div class="WidgetContent">
		<%	PlayerDTO playerDTO = new PlayerDTO();
			if(DataUtil.getUserDTO(request) != null)
				playerDTO = DataUtil.getUserDTO(request).getPlayerDTO();
				List playerCommentsList =(List) session.getAttribute(G4GConstants.PLAYERCOMMENTSLIST);
				for(int index=0;index<playerCommentsList.size();index++){
					PlayercommentsDTO playerCommentsDTO = (PlayercommentsDTO)playerCommentsList.get(index);
					PlayerDTO commentProviderPlayerDTO = PlayerServiceDelegator.getPlayer(playerCommentsDTO.getPlayer().getId());
					boolean isFriend = FriendsUtil.isFriend(playerDTO.getId(), commentProviderPlayerDTO.getId());
		%>
		<div class="CommentContainer Format1">
			<div class="floatLeft grid2">
				<jsp:include page="common/avatar.jsp">
					<jsp:param name="playerid"
						value="<%=commentProviderPlayerDTO.getId() %>" />
					<jsp:param name="avatarid"
						value="<%= (commentProviderPlayerDTO.getAvatars() != null) ? commentProviderPlayerDTO.getAvatars().getId() : 0 %>" />
					<jsp:param name="screenname"
						value="<%=commentProviderPlayerDTO.getScreenname()%>" />
					<jsp:param name="isOnline"
						value="<%=commentProviderPlayerDTO.isIsonline() %>" />
						<jsp:param name="pictureId" value="<%=commentProviderPlayerDTO.getPictureId() %>"/>
					<jsp:param name="showMenu" value="true" />
					<jsp:param name="isLink" value="true" />
				</jsp:include>
			</div>

			<div class="CommentContent floatLeft grid9">
				<%
					String commentDate = DataUtil.getString(playerCommentsDTO.getCreationdate());
					String formatDate =  DataUtil.getDate(DateUtil.getDateOfTimeZone(DataUtil.getDate(commentDate), userDTO.getOffset()),G4GConstants.DATE_DD_MM_YYYY_AT_HH_MM_A);
				%>
				<h3>
					<%=formatDate.substring(0,3) %>,
					<%=formatDate.substring(4,7) %>
					<%=formatDate.substring(8,10) %>,
					<%=formatDate.substring(formatDate.length()-4) %>
					at
					<%=formatDate.substring(11,16) %>
				</h3>
				<p>
					<%=playerCommentsDTO.getComment() %>
				</p>
				<p class="CommentOptions">
					<a
						href="displayProfile.do?delete=delete&object=comment&id=<%=playerCommentsDTO.getId() %>">Delete</a>
				</p>
			</div>
		</div>
		<%} %>
		<!-- Comment -->
	</div>
	<!-- Widg -->
</div>