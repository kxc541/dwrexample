<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>


<bean:define id="siteId" value="wg" type="java.lang.String"/>
 <logic:present name="siteId" >
  <bean:define id="siteId" name="siteId" type="java.lang.String"/>
</logic:present>

<%@page import="com.g4g.utils.G4GConstants"%>



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

<div id="Search" class="ContentWrapper clearfix">
	<div class="Sidebar">
		<jsp:include page="widgets/search.jsp"></jsp:include>
		<jsp:include page="widgets/playNow.jsp"></jsp:include>
		<jsp:include page="widgets/news.jsp" />
		<jsp:include page="widgets/feedback.jsp"></jsp:include>
		<!-- sidebar adspace -->
	</div>
	<div class="PageContent">
		<div class="AreaWrapperTitle">
			<img src="WebContent/<bean:write name="siteId"/>/css/images/banners/banners-searchresults.jpg" />
		</div>
		<div class="PageContent">
		<!-- People search DIV -->

		<div class="Widget" >
			<div class="WidgetHeader">
				<h1>People</h1>
			</div>
			<div class="WidgetContent" id="searchPeopleDiv">
				<%if(request.getAttribute(G4GConstants.PEOPLESEARCH_LIST) != null){ %>
					<jsp:include page="Ajax/searchPeople.jsp" />
				<%
				}else{out.println("No people found for this search");} %>

			</div>
		</div>

		<!-- Game search DIV -->

		<div class="Widget" >
			<div class="WidgetHeader">
				<h1>Game</h1>
			</div>
			<div class="WidgetContent" id="searchGameDIV">
					<%if(request.getAttribute(G4GConstants.GAMESEARCH_LIST) != null){ %>
					<jsp:include page="Ajax/searchGames.jsp" />
					<%}
				else{ out.println("No games found for this search"); } %>
				</div>
		</div>

		<!-- Tournament search DIV -->

		<div class="Widget" >
			<div class="WidgetHeader">
				<h1>Tournament</h1>
			</div>
			<div class="WidgetContent" id="searchTournamentDIV">
					<%if(request.getAttribute(G4GConstants.TOURNAMENTSEARCH_LIST) != null){ %>
					<jsp:include page="Ajax/searchTournaments.jsp" />
					<%}

				else{ out.println("No tournaments found for this search");} %>
				</div>
		</div>

		<!-- Open Challenge search DIV -->

		<div class="Widget" >
			<div class="WidgetHeader">
				<h1>Open Challenge</h1>
			</div>
			<div class="WidgetContent" id="searchChallengeDIV">
					<%if(request.getAttribute(G4GConstants.OPENCHALLENGESEARCH_LIST) != null){%>
					<jsp:include page="Ajax/searchOpenChallenge.jsp" />
					<%} else{ out.println("No open challenges found for this search"); } %>
				</div>
		</div>

		</div>
	</div>
	<%//out.println(request.getAttribute("resultDIV")); %>
</div>


