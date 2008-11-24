<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" 
	"http://www.w3.org/TR/html4/loose.dtd">

<html>
	<head>

		<title>Management Team</title>

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

				<h1>
					Management Team
				</h1>

				<p>
					<b>William Levy, President &amp; Co-Founder</b>
				</p>

				<p>
					As far as entrepenuers go, few make it happen like World Gaming's
					President Billy "Chips" Levy. With over $20 million raised, a
					lucrative commercial real estate group in Chicago, the uber-chic
					Supper Club in San Francisco, as well as an import/export company
					that he started at 19 and later sold for a handsome profit on his
					resume, Billy is seasoned at developing new companies into
					lucrative brand entities. A gamer at heart, Billy and Zack came up
					with the idea for World Gaming over a heated game of Madden
					Football, New Year's day, 2006, and never looked back.
				</p>

				<p>
					<b>Zack Zeldin, Vice-President &amp; Co-Founder</b>
				</p>

				<p>
					If gaming was religion, Zack "The Sleez" Zeldin would be a 36th
					Chamber, double black belt high priestess. Gaming since gaming had
					him, Zack heads the brand's Gamer Innovation Project, and oversees
					all aspects of the User Experience for World Gaming. In his free
					time, "Sleez" enjoys talking trash on any and every video game he's
					ever played, fondling his curiously dimunitive Chiwauwa and
					stalking fomer grade school classmates on Facebook.
				</p>

				<p>
					<b>Clive Cartlidge, Chief Technology Officer</b>
				</p>

				<p>
					Clive is World Gaming's resident expert in technical intergration
					parctices, online payment protocol, affiliate management systems,
					and lethal street fighting tactics. Prior to joining World Gaming,
					Clive was the Director of Web Services at CryptoLogic, one of the
					prominent gaming and payment solution providers in the Internet
					Gaming industry. In his free time, Clive raises his beautiful
					daughter, practices Mixed Martial Arts with reknowned Jiu Jitsu
					artist Steve Lin, and manages a healthy addiction to World of
					Warcraft, Resistance: Fall of Man, and Fifa Soccer.
				</p>

				<p>
					<b>Richard Santiago, Global Director of Marketing</b>
				</p>

				<p>
					Known as the Mr. "Cut the Check," Richard brings global marketing
					expertise and a perverse capacity for creativity to World Gaming.
					Having helped shape the creative and strategic direction for such
					brands as Altoids, Marlboro, Coor's Brewing, Krispy Kreme
					Doughnuts, Southwest Airlines, Pennzoil, Nickelodeon, and the PGA
					tour, as well as several new business wins and trends projects,
					Richard is your favorite marketer's favorite marketer. When Richard
					isn't dominating NBA 2K8 or padding his statistics in over-thirty
					basketball leagues, he enjoys dancing with his new daughter and
					bribing his dog to fetch a multi-colored tennis ball. He is also
					rumored to love long walks on the beach and comfortable back seats.
				</p>

			</div>

			<jsp:include page="common/sidebar.jsp" />

			<jsp:include page="common/FooterNavNew.jsp" />

		</div>

	</body>
</html>