<%@page import="java.util.List"%>
<%@page import="com.g4g.plugin.G4GOriginSession"%>

<%
	int registeredUsers = ((List)application.getAttribute(G4GOriginSession.LIST_REGISTERED_PLAYER)).size();
	int logedinUsers = ((List)application.getAttribute(G4GOriginSession.LIST_ONLINE_PLAYER)).size();
%>


<div id="header">

	<p id="headerMenu"><a href="displayRegister1.do">register</a> &nbsp; <a href="displayLogin.do">log in</a></p>

	<div id="MemberStatusModule">
	<table>
		<tr>
			<td>Registered WG Members</td>
			<td><%= registeredUsers %></td>
		</tr>
		<tr>
			<td>Members Currently Online</td>
			<td><%= logedinUsers %></td>
		</tr>
	</table>
	</div>

</div>