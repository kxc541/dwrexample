
<%@page import="com.g4g.utils.G4GConstants"%>
<%@page import="com.g4g.dto.UserDTO"%>
<%@page import="com.g4g.dto.PlayerDTO"%>
<%@page import="com.g4g.utils.DataUtil"%>

<%@page import="com.g4g.utils.ChallengeCardUpdateUtil"%>

<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	String userId = "";
	if (DataUtil.getUserDTO(request) != null) {
		UserDTO userDTO = null;
		userDTO = DataUtil.getUserDTO(request);
		PlayerDTO playerDTO = userDTO.getPlayerDTO();
		userId = String.valueOf(playerDTO.getId());
	}
%>

<script type="text/javascript">
	$(document).ready(function() {
		$(".Sidebar .Widget").OpenClose();
		$(".Avatar").ActiveAvatar();
		$(".AvatarOptions").hide();

	$(".BannerAds ul").innerfade( {
		animationtype: 'fade',
		speed: 1750,
		timeout: 6000,
		type: 'sequence',
		containerheight: 'auto'
	});
});
</script>

<bean:define id="siteId" value="wg" type="java.lang.String" />
<logic:present name="siteId">
	<bean:define id="siteId" name="siteId" type="java.lang.String" />
</logic:present>
<div id="pageTitle">
	<img src="<%=basePath%>WebContent/<bean:write name="siteId"/>/css/images/banners/banners-gamespicker.jpg" width="920" height="41" alt="Games Picker">
</div>

<div id="content" class="lobby">

	<table>
	<tr valign="top">
		<td>
			<p style="margin-left: 20px; margin-top: 40px;">
				Click on a game to visit its game room. If a game is not part of
				your &quot;My Games&quot; list, you can quickly add it to your My
				Games list by clicking on the + button.
			</p>
		</td>
		<td>
			<img
				src="<%=basePath%>WebContent/<bean:write name="siteId"/>/css/images/ads/gamelobby.png"
				width="246" height="224" alt="Game lobby"
				style="margin: 20px 10px 0px 30px;">
		</td>
	</tr>
	</table>

	<div id="GamePicker">

		<div class="TabNav">
			<ul>
				<li>
					<a href="javascript:updatePickerWithUser('<%=userId%>');"><img src="<%=basePath%>WebContent/<bean:write name="siteId"/>/css/images/icons/mygames-nav.png" width="56" height="30" alt="My Games"></a>
				</li>
				<li>
					<a href="javascript:updatePickerWithPlatform('<%=G4GConstants.NETWORK_XBOX360_LIST_KEY%>');"><img src="<%=basePath%>WebContent/<bean:write name="siteId"/>/css/images/icons/xbox360-nav.png" width="54" height="30" alt="XBox 360"></a>
				</li>
				<li>
					<a href="javascript:updatePickerWithPlatform('<%=G4GConstants.NETWORK_PS2_ID_LIST_KEY%>');"><img src="<%=basePath%>WebContent/<bean:write name="siteId"/>/css/images/icons/ps2-nav.png" width="28" height="30" alt="PS2"></a>
				</li>
				<li>
					<a href="javascript:updatePickerWithPlatform('<%=G4GConstants.NETWORK_PS3_ID_LIST_KEY%>');"><img src="<%=basePath%>WebContent/<bean:write name="siteId"/>/css/images/icons/ps3-nav.png" width="28" height="30" alt="PS3"></a>
				</li>
			</ul>
		</div>

		<div class="WidgetContent" id="pickerUpdate"></div>

	</div>
	<!-- end GamePicker -->

	<div id="gameDetails">
		<h2>
			We put all the Game Page Help stuff here
		</h2>
	</div>

</div>
<!-- end content -->

<div id="sidebar" class="lobby">
	<jsp:include page="widgets/search.jsp"></jsp:include>
	<jsp:include page="widgets/myLobbies.jsp"></jsp:include>
	<jsp:include page="widgets/activePlayers.jsp"></jsp:include>	
	<jsp:include page="widgets/adBanner.jsp?NARROW=true"></jsp:include>
</div>

<!-- clear directChallenge form: don't delete it -->
<!-- ok -->

<%
	ChallengeCardUpdateUtil.clearSession(request);
%>



<script language="JavaScript" type="text/javascript">

var showPicker = true;
var currentG4GTag = '<%=userId%>';

$(document).ready(function() {
	if(showPicker == false) {
		$("#GamePicker").removeClass("showDiv").addClass("hideDiv");
	} else {
		updatePickerWithUser(currentG4GTag);
	}
});

</script>