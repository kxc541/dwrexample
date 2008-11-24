<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>

<%@page import="com.g4g.utils.G4GConstants"%>

<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" 
	"http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>

<title>About WorldGaming</title>

<jsp:include page="common/HeadTagNew.jsp" />

</head>
<body>

<div id="page">

		<logic:present name="userDTO" property="playerDTO">
				<jsp:include page="common/HeaderLoginNew.jsp" />
				<jsp:include page="common/GlobalNavigationLogin.jsp" />
			</logic:present>

			<logic:notPresent name="userDTO" property="playerDTO">
				<jsp:include page="common/HeaderNew.jsp" />
				<jsp:include page="common/GlobalNavigation.jsp" />
			</logic:notPresent>
<div id="content" class="info">

<h1>Help</h1>

<p><b>What is WorldGaming.com?</b></p>

<p>WorldGaming.com is a global platform for gamers to challenge each other for REAL MONEY in well known and hugely popular console video games. Bringing together gamers from over 100 countries to compete in head to head and multiplayer tournaments, WorldGaming.com is the premier source for online gaming competition, whether the player is new to the world of online gaming or a seasoned pro.</p>

<p>In addition to being the #1 source for "Real Money" online gaming tournaments, WorldGaming.com combines online gaming with social networking, cutting edge gaming media, gaming news, reviews, and much, much more.</p>

<p>What consoles and games are compatible with WorldGaming.com tournaments?</p>

<p>WorldGaming.com tournaments are compatible with top multiplayer games on Xbox, Xbox 360, PlayStation 2 and PlayStation 3.</p>

<p>WorldGaming also offers a wide variety of games including: Halo 2, Halo 3, Madden 08, NHL 08, NBA Live 08, FIFA 08, Fight Night Round 3 , MotorStorm, Resistance: Fall of Man and many more - with new titles added regularly.</p>

<p><b>How do I begin playing</b></p>

<p><b>Is WorldGaming.com legal?</b></p>

<p>Yes!</p>

<p>All tournaments and services offered through WorldGaming.com are considered "Games of Skill." Games of Skill are games in which the element of skill supersedes the element of chance, as opposed to traditional casino games where the element of chance supersedes the element of skill. WorldGaming.com is therefore not considered a gambling arena and as such is legal in most European territories and the majority of the United States.</p>

<p>Additionally, WorldGaming.com has no vested interest in which player prevails (unlike in a Casino game or sport betting scenario). WorldGaming.com provides the forum in which the games are arranged and guarantees that the winners get paid. WorldGaming.com serves as a facilitator of games and tournaments and does not make any money from the outcome of any tournament.</p>

<p><b>Does it cost money to register and use the site?</b></p>

<p>No, registration is 100% free and does not require any payment or deposit.. Gamers are invited to participate in the WorldGaming.com community, make friends or foes and build their gaming networks while enhancing their skills until they think they're ready to step up and compete for real money. In addition to having access to the sites social networking features, registered users have access to all of the websites content including original video content, gaming news, reviews, lifestyle blogs, and more.</p>

<p>To experience the thrill of playing for money you will need to deposit money in your account. This money is yours just like an online bank account. Select the "Cashier" option on the homepage menu at any time to review and manage your account.</p>

<p><b>What payment methods are available?</b></p>

<p>Users may deposit funds into their account via Credit card (Visa, MasterCard, Discover, and American Express), Debit card (Star, Interact), or through PayPal.</p>

<p><b>Is my Money Safe (and Will I Get Paid)?</b></p>

<p>Yes!</p>

<p>Your money is safely deposited in a bank account with global financial services institution Barclays Bank and the money is yours. You can withdraw it from your account any time you wish! Please see our Site Terms and Conditions for further details about deposits and withdrawals.</p>

<p>You can always be 100% certain that you will get paid your winnings by World Gaming. Each player's funds are held in escrow by World Gaming and are released to the winner upon completion of the game or tournament. Because the money has to be in the gamers accounts before competing in games for money, you can always be sure that you will get paid.</p>

<p>How much does it cost to enter a tournament and how much money can players win?</p>

<p>Worldgaming.com offers a variety of tournaments at different entry fee ranges to match its users needs. Tournaments entry fees range from FREE to a maximum of $1,000. In order to participate in the site's Free Entry tournaments, users must have money deposited to their account on the site; the minimum deposit is $5.50. Winnings also vary depending on what type of tournament the player competes in, as the company offers a wide variety of tournament pay out structures.</p>

<p><b>Is the WorldGaming.com site safe and secure?</b></p>

<p>YES!</p>

<p>WorldGaming.com has multiple systems in place to ensure the integrity of every game played, offering an unmatched guarantee of probity to our global community of players.</p>

<p>WorldGaming.com only uses strength payment systems built using the latest encryption techniques and safety measures to guarantee transaction security. WorldGaming.com, Inc. guarantees that every transaction made at WorldGaming.com is secure. WorldGaming.com uses Secure Socket Layer (SSL) encryption connections for all passwords and financial information. Any sensitive information sent to the WorldGaming.com servers is encrypted meeting the highest security standards for online financial transactions.</p>

</div>

<div id="sidebar" class="info">
	<ul>
		<li><html:link forward="<%=G4GConstants.TERMS %>">Terms &amp; Conditions</html:link></li>
		<li><html:link forward="<%=G4GConstants.PRIVACY %>">Privacy Policy</html:link></li>
		<li><html:link forward="<%=G4GConstants.ABOUT %>">About World Gaming</html:link></li>
		<li><html:link forward="<%=G4GConstants.MANAGEMENT %>">Management Team</html:link></li>
		<li><html:link forward="<%=G4GConstants.PRESS %>">Press</html:link></li>
		<li><html:link forward="<%=G4GConstants.ADVERTISE %>">Advertise</html:link></li>
		<li><html:link forward="<%=G4GConstants.RESPONSIBLEGAMING %>">Responsible Gaming</html:link></li>
		<li><html:link forward="<%=G4GConstants.CONTACT %>">Contact</html:link></li>
	</ul>
</div>


<jsp:include page="common/FooterNavNew.jsp" />

</div>

</body>
</html>