<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" 
	"http://www.w3.org/TR/html4/loose.dtd">

<html>
	<head>

		<title>Responsible Gaming at WorldGaming</title>

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
					Responsible Gaming
				</h1>

				<p>
					For most of us, gaming is just a bit of fun that we can enjoy any
					time, but a small percentage of players might have let gaming
					become the main focus in their life. As the provider of our gaming
					and tournament services, World Gaming is committed to responsible
					gambling.
				</p>

				<b>People who may need to control their gaming and/or wagering</b>

				<p>
					Most people gamble within their means, but for some it can spiral
					out of control. When playing the games, keep in mind the following:
				</p>

				<ul>
					<li>
						Gaming should be seen as just for fun, not a way of earning money
					</li>
					<li>
						Avoid chasing losses
					</li>
					<li>
						Only play in tournaments with entry fees in which you can afford
						to lose
					</li>
					<li>
						Keep track of how much time and money you spend gambing
					</li>
				</ul>

				<p>
					Ask yourself the following questions:
				</p>

				<ul>
					<li>
						Do you lose time from work or education due to gaming?
					</li>
					<li>
						Has gaming ever made your home life unhappy?
					</li>
					<li>
						Do you gamble to pay debts or solve financial difficulties?
					</li>
					<li>
						After a loss, do you feel you need to play again to try and make
						up what you've lost?
					</li>
					<li>
						After a win, do you have a strong urge to try and win more?
					</li>
					<li>
						Do you ever gamble until all your money has gone?
					</li>
					<li>
						Have you ever lied, borrowed, sold or stolen anything to finance
						gambling?
					</li>
					<li>
						Do you prefer to gamble than spend time with your family or
						friends?
					</li>
					<li>
						Are you reluctant to use 'gambling money' for normal expenditures?
					</li>
					<li>
						Have you ever gambled to escape worry, trouble or boredom?
					</li>
					<li>
						Does gambling cause you to have difficulty in sleeping?
					</li>
					<li>
						Do you ever have an urge to celebrate any good fortune with a few
						hours of gambling?
					</li>
					<li>
						Do arguments, disappointments or frustrations give you the urge to
						gamble?
					</li>
					<li>
						Do you ever feel depressed or consider harming yourself as a
						result of your gambling?
					</li>
				</ul>

				<p>
					People with a compulsive gambling problem are likely to answer
					'yes' to some of these questions. If you need more help, get in
					touch with one of the following organisations:
				</p>

				<p>
					Gamble Aware Gamblers Anonymous
					<br>
					National Council on Problem Gambling
					<br>
					GamCare
					<br>
					Helping Hand/ IGC
				</p>

				<b>Underage players</b>

				<p>
					Nobody under the age of 18 is allowed to play our games. If you are
					worried that under-18s may use your computer to play our games, try
					any of the following steps:
				</p>

				<ul>
					<li>
						Use child protection software, such as Cyberpatrol, to block
						gaming sites from under-18s
					</li>
					<li>
						Don't leave the computer unattended when you are logged in
					</li>
					<li>
						Don't share your credit card or bank account details with
						under-18s
					</li>
					<li>
						Do not use the 'Save Password' option on login screens
					</li>
					<li>
						Create separate profiles for everyone who uses your computer, so
						that nobody else can access your information
					</li>
				</ul>

				<p>
					If you know someone under the age of 18 who is registered with us,
					please contact us immediately at
					<a href="mailto:responsiblegaming@worldgaming.com">responsiblegaming@worldgaming.com</a>.
				</p>

			</div>

			<jsp:include page="common/sidebar.jsp" />

			<jsp:include page="common/FooterNavNew.jsp" />

		</div>

	</body>
</html>