<%@ page language="java" pageEncoding="ISO-8859-1"%>
<%@page import="java.util.List"%>
<%@page import="com.g4g.plugin.G4GOriginSession"%>



<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<bean:define id="siteId" value="wg" type="java.lang.String"/>
 <logic:present name="siteId" >
  <bean:define id="siteId" name="siteId" type="java.lang.String"/>
</logic:present>

<%							
	int registeredUsers = ((List)application.getAttribute(G4GOriginSession.LIST_REGISTERED_PLAYER)).size();
	int logedinUsers = ((List)application.getAttribute(G4GOriginSession.LIST_ONLINE_PLAYER)).size();  
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
	"http://www.w3.org/TR/html4/loose.dtd">

<html:html>
<head>

<title>Welcome to World Gaming</title>

<link rel="stylesheet" href="WebContent/<bean:write name="siteId"/>/css/Global.css" type="text/css" media="screen">
<link rel="stylesheet" href="WebContent/<bean:write name="siteId"/>/css/Widgets.css" type="text/css" media="screen">
<link rel="stylesheet" href="WebContent/<bean:write name="siteId"/>/css/PublicHome.css" type="text/css" media="screen">
<link rel="stylesheet" href="WebContent/<bean:write name="siteId"/>/css/Tables.css" type="text/css" media="screen">
<link rel="stylesheet" href="WebContent/<bean:write name="siteId"/>/css/header.css" type="text/css" media="screen">
<link rel="stylesheet" href="WebContent/<bean:write name="siteId"/>/css/thickbox.css" type="text/css" media="screen">

<script type="text/javascript" src="WebContent/<bean:write name="siteId"/>/js/jquery.js"></script>
<script type="text/javascript" src="WebContent/<bean:write name="siteId"/>/js/OpenClose.js"></script>
<script type="text/javascript" src="WebContent/<bean:write name="siteId"/>/js/ActiveAvatar.js"></script>
<script type="text/javascript" src="WebContent/<bean:write name="siteId"/>/js/jquery.idTabs.pack.js"></script>
<script type="text/javascript" src="WebContent/<bean:write name="siteId"/>/js/thickbox.js"></script>
<script type="text/javascript" src="WebContent/<bean:write name="siteId"/>/js/jquery.innerfade.js"></script>

<script type="text/javascript">
	$(document).ready(function(){
		$(".Sidebar .Widget").OpenClose();
		$(".Avatar").ActiveAvatar();
		$(".IntroImages").innerfade({
			speed: 'slow',
			timeout: 4000
		});
	});
</script>

<style>

.PreviewImage {
	padding: 0px 10px 10px 10px;
}

</style>

</head>
<body>


<div id="container">

	<jsp:include page="common/HeaderNew.jsp" />
		
	<jsp:include page="common/GlobalNavigation.jsp" />

	<div id="PublicHome" class="ContentWrapper clearfix">
		<div id="TopAdSpace" class="clearfix">
			<div class="IntroImages">
				<html:link page="/displayRegister1.do" ><img src="WebContent/<bean:write name="siteId"/>/css/images/PublicHome/promo-wg1.jpg" border="0"/></html:link>
			</div>
			<div class="Supported">
				<img src="WebContent/<bean:write name="siteId"/>/css/images/ads/supported_consoles.jpg">
				<p><%= registeredUsers %> members - <%= logedinUsers %> online</p>
				<div class="buttons">
					<html:link page="/displayRegister1.do" styleClass="red">register</html:link>
				</div>
			</div>
		</div>
		<div class="bgWrapper clearfix">
<table width="100%"><tr valign="top"><td bgcolor="#bfc1ba">
			<div class="PageContent">
				<div class="TabNav">
					<ul class="idTabs">
						<li><a href="#Public_Meet"><img src="WebContent/<bean:write name="siteId"/>/css/images/PublicHome/meet.png"></a></li>
						<li><a href="#Public_Challenge"><img src="WebContent/<bean:write name="siteId"/>/css/images/PublicHome/challenge.png"></a></li>
						<li><a href="#Public_Play"><img src="WebContent/<bean:write name="siteId"/>/css/images/PublicHome/play.png"></a></li>
						<li><a href="#Public_Win"><img src="WebContent/<bean:write name="siteId"/>/css/images/PublicHome/win.png"></a></li>
					</ul>
				</div>
				<div class="TabContent clearfix">
					<div id="Public_Meet" class="PublicPage">
						<img src="WebContent/<bean:write name="siteId"/>/css/images/PublicHome/screen-meet.jpg" class="PreviewImage">
	
						<h1>Meet People</h1>
	
						<p>Innovative Social Networking makes WorldGaming.com the place for gamers to create and join challenges from around the globe.</p>
						
						<p>First item on the agenda is to "meet" active WorldGaming members through a process that is easier than using the Up, Up, Down, Down, Left, Right, Left, Right, B+A, Select, Start, cheat for Contra.</p>
						
						<p>Through our wide range of platform users, we give you the opportunity to Search for gamers directly, or use their custom profile pages to help build your new gaming social network.</p>
						
						<p>So start adding members to your buddy list, form clans with your friends, and forge your relationships as you pave the way to becoming a legend in WorldGaming.</p>
	
						<div class="buttons"><html:link page="/displayRegister1.do" styleClass="orange">SIGN UP NOW</html:link></div>
					</div> <!-- end Public_Meet -->
					<div id="Public_Challenge" class="PublicPage">
						<img src="WebContent/<bean:write name="siteId"/>/css/images/PublicHome/screen-challenge.jpg" class="PreviewImage">

						<h1>Challenge Players</h1>

						<p>It's simple. It's easy. It's WorldGaming's challenge process. You can challenge users in a variety of tournaments that other users have created and are ready for you to quickly join. If you are unable to find a match that suits your gaming preference, simply create your own match with your very own details and wait for someone to accept the challenge!</p>

						<p>But challenges aren't just limited to Head to Head games, WorldGaming also offers massive multiplayer tournaments.</p>

						<p>Your opponent awaits, they're only an acceptance click away...so hit that button!</p>
						
						<div class="buttons"><html:link page="/displayRegister1.do" styleClass="orange">SIGN UP NOW</html:link></div>
					</div> <!-- end Public_Meet -->
					<div id="Public_Play" class="PublicPage">
						<img src="WebContent/<bean:write name="siteId"/>/css/images/PublicHome/screen-play.jpg" class="PreviewImage">

						<h1>Play Games</h1>

						<p>WorldGaming offers all the major consoles for game play on our site from XBOX, XBOX360, PS2 and PS3.</p>
						
						<p>We also offer a variety of the most popular titles in the world including Halo 2, Halo 3, Madden football, FIFA soccer, and much more. For a complete list of games offered please check back soon.</p>
						
						<p>Once your match has been agreed upon, you are all set to game for green. Immediately after your match is complete, your results will be automatically logged on the WorldGaming site and your records will be updated with your account balances reflecting your previous games results.</p>

						<div class="buttons"><html:link page="/displayRegister1.do" styleClass="orange">SIGN UP NOW</html:link></div>
					</div> <!-- end Public_Play -->
					<div id="Public_Win" class="PublicPage">
						<img src="WebContent/<bean:write name="siteId"/>/css/images/PublicHome/screen-win.jpg" class="PreviewImage">

						<h1>Win Cash</h1>

						<p>You play to win the game! Now your wins actually mean something here at WorldGaming.com...cash baby, straight cash. Whether you're playing for $20 or $200, we're sure you'll enjoy the thrill of victory along with watching your bank account grow.</p>
						
						<p>And remember, your winning potential is based solely on your own skill. And with large scale tournaments here at WorldGaming there are plenty of opportunities to prove you're the best and earn tons of cash!!</p>
						
						<p>The money is there for the taking, so sign up, deposit and pick up a controller because now pride is only half the prize.</p>

						<div class="buttons"><html:link page="/displayRegister1.do" styleClass="orange">SIGN UP NOW</html:link></div>
					</div> <!-- end Public_Win -->
				</div> <!-- end TabContent -->
			</div> <!-- end PageContent -->
</td><td bgcolor="#bfc1ba">
			<div class="Sidebar">
				<div class="MemberLogin">

					<html:form action="/login" method="post">
					<html:errors/>
					
					<table width="275">
						<tr><td>Email</td><td><html:text property="username"></html:text></td></tr>
						<tr><td>Password</td><td><html:password property="password" value=""></html:password></td></tr>
						<tr>
							<td>&nbsp;</td>
							<td>
								<table cellpadding="0" cellspacing="0" style="margin: 0px;">
									<tr>
										<td><div id="login" class="buttons"><html:submit styleClass="green" style="cursor: pointer;" property="operation" value="login"></html:submit></div></td>
										<td><a href="displayForgotPassword.do?KeepThis=true&TB_iframe=true&height=250&width=600&modal=true" class="modal thickbox">Forgot Your Password?</a></td>
									</tr>
								</table>
							</td>
						</tr>
					</table>
					
					<h1><html:errors property="username" prefix="<font color=white />" /></h1>

					<script language="JavaScript" src="WebContent/<bean:write name="siteId"/>/js/ClientTimeZone.js"></script>

					</html:form>
				</div> <!-- end MemberLogin -->
				<img src="WebContent/<bean:write name="siteId"/>/css/images/ads/tourney1.jpg" class="SideAd">
			</div> <!-- end Sidebar -->

</td></tr></table>

		</div> <!-- end bgWrapper -->
		
		<br>
		<span style="font-size: 10px; color: #999;">Worldgaming.com and its officers, employees, agents or assignees are not associated with, affiliated with, employed by, sponsored by, or connected in any other way with Electronic Arts, Microsoft Corporation, Xbox 360, Xbox Live, Sony Computer Entertainment America Inc., PlayStation 2, PlayStation 3, and/or any of their officers, employees, agents or assigns. The trademarks, logos, and service marks (collectively the "Trademarks") displayed on the Site, including the names of all games are registered and unregistered Trademarks of the owners, the Site and/or others.</span>
		<br><br>
		<div align="center"><img src="WebContent/<bean:write name="siteId"/>/css/images/ads/payments.gif"></div>
	
	</div> <!-- end PublicHome -->
</div> <!-- end container -->

<jsp:include page="common/FooterNav.jsp" />
<jsp:include page="common/Footer.jsp" />


<!-- This include file is needed -->
<jsp:include page="removeThickbox.jsp"></jsp:include>
<!-- This include file is needed -->
</body>
</html:html>