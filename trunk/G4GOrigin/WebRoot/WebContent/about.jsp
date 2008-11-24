<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" 
	"http://www.w3.org/TR/html4/loose.dtd">

<html>
	<head>
		<title>About World Gaming</title>
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
					About Us
				</h1>

				<p>
					World Gaming is the Premier source for online console gaming
					competition and tournaments for Cash and Prizes.
				</p>

				<p>
					The World Gaming community Network brings together gamers from over
					130 countries world wide to compete in head to head and multiplayer
					tournaments for REAL MONEY!
				</p>

				<p>
					Gamers can choose to compete against other gamers for Money and
					Prizes.
				</p>

				<p>
					Registering to play takes just a few minutes and you can deposit
					funds online through a variety of different methods.
				</p>

				<p>
					Choose to play against other people, not against the house, knowing
					that you and your opponent will start the game with the exact same
					opportunity to win. How you play the game will determine who wins.
				</p>

				<p>
					You can earn serious money while having serious fun. What better
					way to earn money? Whether you're playing for low or high stakes,
					we're confident you'll enjoy the sharp thrill of real money
					competition.
				</p>

				<p>
					You'll find hundreds of tournaments to choose from available across
					our vibrant portfolio of high-quality games. We've got exciting
					knockout tournaments (also called "bracket" tournaments in the
					United States). We've even got regular scheduled tournaments that
					start & end at set times. Real money players also get access to our
					regular and frequent FreeRoll Tournaments which cost nothing to
					enter the very first time you play and payout real money to the
					winners.
				</p>

				<p>
					Competitions of skill between people are legal in nearly all
					countries worldwide including the United States, Europe and
					Australia. Why take a chance with offshore gaming sites when you
					can play to win real money legitimately from other players of
					skill-based games.
				</p>

				<p>
					Prompt Payouts. We routinely transfer winnings to players resident
					in over 130 countries worldwide. We'll make sure your winnings get
					to your bank account as quickly as possible when you request them.
				</p>

				<p>
					Customer Services. We're proud to offer customer services support
					during scheduled times 7 days a week. Our community of players is
					important to us and we're on hand to answer your questions and
					queries. You can read what our players say about us and see for
					yourself or simply contact us now if you still have questions about
					playing for real money.
				</p>

				<p>
					You can play all our games for real money at WorldGaming.com. By
					depositing money into your account using safe, secure and easy
					methods you will be able to join tournaments in no time and pit
					your skills against other World Gaming users.
				</p>

				<p>
					This page will tell you all you need to know about depositing,
					withdrawing and playing single player and multiplayer games for
					money.
				</p>

				<p>
					WorldGaming.com is not a casino. This means that you are betting
					against other players on the Internet not against the house. We
					receive compensation by taking a rake (see rake for more
					information).
				</p>

				<h1>
					Financial Security
				</h1>

				<p>
					The World Gaming Platform uses industry strength payment systems
					built using the latest encryption techniques and safety measures to
					guarantee transaction security.
				<p>
					The World Gaming Corporation guarantees that every transaction you
					make at WorldGaming.com will be secure.
				<p>
					The World Gaming platform uses Secure Socket Layer (SSL) encryption
					connections for all passwords and financial information.
				<p>
					Anytime you enter sensitive information it will always be sent to
					the World Gaming servers using encryption.
				<p>
					For our encryption of data we use digital certificates issued by
					Verisign, the industry leader for online financial
				<p>
					The World Gaming Corporation is committed to offering games of
					skill for the largest stakes available online.
				<p>
					Whether you are an amateur or professional, we are sure you will be
					able to find the game of your choice for the stake of your choice.
				</p>

				<p>
					All games can be played with buy-ins ranging from $10.00-$10,000.
					The WorldGaming.com platform will be launching innovative and
					exciting new betting options which will open up new facets to
					existing skill based games.
				</p>

				<p>
					The WorldGaming.com platform leads the way in Progressive jackpot
					tournaments offering thousands of dollars in prize money for a
					small initial stake as well as huge multi player tournaments
					whereby relatively small entry fees result in huge prizes.
				</p>

				<h1>
					Legal Issues
				</h1>

				<p>
					All the games provided on The World Gaming Platform are Games of
					Skill.
				</p>

				<p>
					Games of Skill are defined as:
				</p>

				<p>
					"Games in which the element of Skill supersedes the element of
					chance", as opposed to traditional casino games where "the element
					of chance supersedes the element of skill".
				</p>

				<p>
					We do not offer any games of chance or casino games where luck
					determines the winner; rather it is the player's skill which
					decides the winner.
				</p>

				<p>
					Due to the fact that the element of skill is the determining factor
					- as opposed to chance - The World Gaming Platform is not
					considered a gambling arena, is therefore legal in most European
					territories and the majority of the United States.
				</p>

				<p>
					The World Gaming Corporation has no vested interest as to which
					player prevails (unlike in a Casino game or sport betting
					scenario).
				</p>

				<p>
					The World Gaming Corporation purely provides the forum in which the
					games are played and guarantees that the winners get paid.
				</p>

				<p>
					The World Gaming Corporation only serves as a facilitator of games
					and tournaments and does not make any money from the outcome of any
					tournament.
				</p>

			</div>

			<jsp:include page="common/sidebar.jsp" />

			<jsp:include page="common/FooterNavNew.jsp" />

		</div>

	</body>
</html>