<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
   
   <bean:define id="siteId" value="wg" type="java.lang.String"/>
 <logic:present name="siteId" >
  <bean:define id="siteId" name="siteId" type="java.lang.String"/>
</logic:present>

<html lang="en">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<title>Coming Soon</title>
	<meta name="generator" content="WebObjects 5.3.2">

	<link rel="stylesheet" href="WebContent/<bean:write name="siteId"/>/css/Global.css" type="text/css" media="screen" />
	<link rel="stylesheet" href="WebContent/<bean:write name="siteId"/>/css/Widgets.css" type="text/css" media="screen" />
	<link rel="stylesheet" href="WebContent/<bean:write name="siteId"/>/css/ComingSoon.css" type="text/css" media="screen" />
	<link rel="stylesheet" href="WebContent/<bean:write name="siteId"/>/css/Tables.css" type="text/css" media="screen" />
	<link rel="stylesheet" href="WebContent/<bean:write name="siteId"/>/css/thickbox.css" type="text/css" media="screen" />
	<script type="text/javascript" src="WebContent/<bean:write name="siteId"/>/js/wgLib.js"></script>
	<script type="text/javascript" src="WebContent/<bean:write name="siteId"/>/js/jquery-1.1.3.1.js"></script>
	<script type="text/javascript" src="WebContent/<bean:write name="siteId"/>/js/OpenClose.js"></script>
	<script type="text/javascript" src="WebContent/<bean:write name="siteId"/>/js/ActiveAvatar.js"></script>
	<script type="text/javascript" src="WebContent/<bean:write name="siteId"/>/js/jquery.idTabs.pack.js"></script>
	<script type="text/javascript" src="WebContent/<bean:write name="siteId"/>/js/thickbox.js"></script>

</head>
<body>
<bean:define id="siteId" value="wg" type="java.lang.String"/>
 <logic:present name="siteId" >
  <bean:define id="siteId" name="siteId" type="java.lang.String"/>
</logic:present>



<div id="container">

	<div id="Header">

</div>
<div id="globalnav">
	<ul>


			<li id="gNavHome"><span><a href="HomePage.jsp">HOME</a></span></li>
			<li id="gNavMessage"><span><a href="messageCenter.jsp">Mail Center</a></span></li>
			<li id="gNavCashier"><span><a href="cashierPage.jsp">Cashier Page</a></span></li>
			<li id="gNavGames"><span><a href="gameLobbyPage.jsp">Games</a></span></li>
			<li id="gNavTournaments"><span><a href="TournamentActions.jsp">Tournaments</a></span></li>
			<li id="gNavBlog"><span><a href="/wordpress/">Blog</a></span></li>
			<li id="gNavForum"><span><a href="/wgforum/">Forum</a></span></li>
			<li id="gNavFAQs"><span><a href="FAQ.jsp">Help/FAQs</a></span></li>
			<li id="gNavContact"><span><a href="Contact.jsp">Contact</a></span></li>
			<li id="gNavLeagues"><span><a href="ComingSoon.jsp">Leagues</a></span></li>
			<li id="gNavClan"><span><a href="ComingSoon.jsp">Clan Center</a></span></li>
			<li id="gNavMarketPlace"><span><a href="Marketplace.jsp">Marketplace</a></span></li>

	</ul>
</div>


<div id="ComingSoon" class="ContentWrapper clearfix">


	<div class="PageContent">
	<!--	<img src="WebContent/<bean:write name="siteId"/>/css/images/ads/TournamentsPage.png" class="floatRight">
		<h1>Coming Soon</h1>
		<p>Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Aenean consectetuer. Etiam venenatis. Sed ultricies, pede sit amet aliquet lobortis, nisi ante sagittis sapien, in rhoncus lectus mauris quis massa. Integer porttitor, mi sit amet viverra faucibus, urna libero viverra nibh, sed dictum nisi mi et diam. Nulla nunc eros, convallis sed, varius ac, commodo et, magna. Proin vel risus. Vestibulum eu urna. Maecenas lobortis, pede ac dictum pulvinar, nibh ante vestibulum tortor, eget fermentum urna ipsum ac neque. Nam urna nulla, mollis blandit, pretium id, tristique vitae, neque. Etiam id tellus. Sed pharetra enim non nisl.</p>
	-->
	<h2>Ajax Link</h2>
	<p>
	/cgi-bin/WebObjects/g4g.woa/wa/TournamentActions/newMTTournament?KeepThis=true&TB_iframe=true&height=800&width=650&modal=true
	</p>
	</div><!-- PageContent -->


</div><!-- ContentWrapper -->

</div><!-- container //-->

<div id="Footer">
<div id="footerNav">
	<ul>


			<li id="fNavHome"><span><a href="HomePage.jsp">HOME</a></span></li>
			<li id="fNavAbout"><span><a href="About.jsp">About World Gaming</a></span></li>
			<li id="fNavMoney"><span><a href="PlayForMoney.jsp">Play for money</a></span><li>
			<li id="fNavBlog"><span><a href="/wordpress/">Blog</a></span></li>
			<li id="fNavForum"><span><a href="/wgforum/">Forum</a></span></li>
			<li id="fNavFAQs"><span><a href="FAQ.jsp">Help/FAQs</a></span></li>
			<li id="fNavContant"><span><a href="Contact.jsp">Contact</a></span></li>

	</ul>
</div>
</div>
<div id="copyright">
	<center><span style="white-space: nowrap;">&copy;2007 G4G Gaming, LLC&reg;.</span></center>
</div>



</body>
</html>