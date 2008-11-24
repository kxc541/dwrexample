<%@page import="com.g4g.utils.DataUtil"%>
<%@page import="com.g4g.utils.SessionUtil"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles"%>

<%@page import="com.g4g.dto.UserDTO"%>
<%@page import="com.g4g.dto.PlayerDTO"%>
<%@page import="com.g4g.utils.G4GConstants"%>
<%	UserDTO userDTO = new UserDTO();
	PlayerDTO playerDTO  = new PlayerDTO();
	if(DataUtil.getUserDTO(request) != null){
		userDTO = DataUtil.getUserDTO(request);
		playerDTO = userDTO.getPlayerDTO();
	}
%>

<div id="Header">
	<div id="userinfo">
		<div id="UserHeaderModule">
			<div class="ProfileImage">
			<%if(playerDTO.getAvatars() != null) { %>
				<img height="60"
						width="60"
						src="WebContent/displayImage.jsp?dto=<%=G4GConstants.AVATARDTO%>&id=<%=playerDTO.getAvatars().getId()%>" />
			<% } else { %>
				<img height="60"
						width="60"
						src="WebContent/displayImage.jsp?dto=<%=G4GConstants.PICTUREDTO%>&id=<%=playerDTO.getPictureId()%>" />
			<% } %>
			</div>
			<h1>
				<%
 					if (playerDTO != null)out.print(playerDTO.getScreenname());
 				%>
 			</h1>
			<table class="AccountValue">
				<tr>
					<td>
						Balance:
						<strong>
						<%
							if (userDTO != null) {
								if (userDTO.getBalance() != null) {
						%>
									<bean:write name="userDTO" property="balance" format="$##0.00" />
						<%
								} else {
									out.print("$0.00");
								}
							}
						%> 
						</strong>
					</td>
					<td>
						<div class="buttons">
							<a href="displayCashier.do" class="green">make deposit</a>
						</div>
					</td>
				</tr>
			</table>
		</div>
	</div>
	<p class="LogInOut">
	
		<html:link action="/displayMessageCenter.do">
			<img src="WebContent/<%=SessionUtil.getSiteId(request) %>/images/icons/new_message.png">&nbsp;<%=userDTO.getMessageCount()%>&nbsp;
				new messages</html:link>
		&nbsp;

		<html:link action="/logout.do">
			<img src="WebContent/<%=SessionUtil.getSiteId(request) %>/images/icons/lock.png">&nbsp;logout</html:link>
	</p>
</div>