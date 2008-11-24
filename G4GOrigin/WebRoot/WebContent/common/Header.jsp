<%@page import="java.util.List"%>
<%@page import="com.g4g.plugin.G4GOriginSession"%>

<%
	int registeredUsers = ((List)application.getAttribute(G4GOriginSession.LIST_REGISTERED_PLAYER)).size();
	int logedinUsers = ((List)application.getAttribute(G4GOriginSession.LIST_ONLINE_PLAYER)).size();
%>

			<div id="Header">
				<div style="color: white; font-size: small;" id="userstats">
					<div id="MemberStatusModule" class="MemberStatsTimer">
						<h1>
							`WG Member Statistics
						</h1>
						<table>
							<tbody>
								<tr>
									<td>
										Registered WG Members
									</td>
									<td>
										<div id="registeredCount"><strong><%= registeredUsers %></strong></div>
									</td>
								</tr>
								<tr>
									<td>
										Members Currently Online
									</td>
									<td>
										<div id="onlineCount"><strong><%= logedinUsers %></strong></div>
									</td>
								</tr>
							</tbody>
						</table>
					</div>
				</div>
				<p class="LogInOut">
					<a href="displayRegister1.do">register</a>&nbsp;
					<a href="displayLogin.do">log in</a>
				</p>
			</div>
			
			<!-- 

The following is the global page layout structure.
- A containing "ContentWrapper" with a page specific ID
- PageContent container with an optional Context specific ID
- Sidebar

-->
			
		
	