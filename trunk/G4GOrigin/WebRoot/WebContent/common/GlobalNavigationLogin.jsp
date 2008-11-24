<%@page import="com.g4g.utils.G4GConstants"%>
<%@page import="com.g4g.utils.BlogUtils"%>
<%@page import="com.g4g.utils.DataUtil"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>
<%
	String basePath = request.getScheme() + G4GConstants.COLON + G4GConstants.SLASH + G4GConstants.SLASH
				+ request.getServerName() + G4GConstants.COLON + "8080";
%>

<div id="globalnav">
	<ul>
		<li id="gNavHome"><a href="<%= DataUtil.getBasePath(request) %>displayProfile.do">My Home</a></li>
		<li id="gNavGames"><a href="<%= DataUtil.getBasePath(request) %>displayGameLobbyPage.do">Games</a></li>
		<li id="gNavTournaments"><a href="<%= DataUtil.getBasePath(request) %>displayTournamentAction.do">Tournaments</a></li>
		<li id="gNavCashier"><a href="<%= DataUtil.getBasePath(request) %>displayCashier.do">Cashier</a></li>
		<li id="gNavMessage"><a href="<%= DataUtil.getBasePath(request) %>displayMessageCenter.do">Mail Center</a></li>
		<li id="gNavBlog"><a href="<%=BlogUtils.getBreakingNewsLink()%>">Blog</a></li>
		<li id="gNavFAQs"><html:link forward="<%=G4GConstants.FAQ %>">Help/FAQs</html:link></li>
		<li id="gNavContact"><html:link forward="<%=G4GConstants.CONTACT %>">Contact</html:link></li>
	</ul>
</div>