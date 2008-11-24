<%@page import="com.g4g.utils.DataUtil"%>
<%@page import="com.g4g.utils.SessionUtil"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles"%>

<%@page import="com.g4g.dto.UserDTO"%>
<%@page import="com.g4g.dto.PlayerDTO"%>
<%@page import="com.g4g.utils.G4GConstants"%>

<%
	UserDTO userDTO = DataUtil.getUserDTO(request);
	PlayerDTO playerDTO  = userDTO.getPlayerDTO();
%>

<div id="header">

	<p id="headerMenu">
		<html:link action="/displayMessageCenter.do"><img src="WebContent/<%=SessionUtil.getSiteId(request) %>/images/icons/new_message.png"></html:link>&nbsp;<html:link action="/displayMessageCenter.do"><%=userDTO.getMessageCount()%> new messages</html:link>
		&nbsp;&nbsp;
		<html:link action="/logout.do"><img src="WebContent/<%=SessionUtil.getSiteId(request) %>/images/icons/lock.png"></html:link>&nbsp;<html:link action="/logout.do">logout</html:link>
	</p>

	<div id="headerUserInfo">
		<table cellpadding="0" cellspacing="0" border="0" width="100%">
			<tr>
			<%if(playerDTO.getAvatars() != null){ %>
				<td width="60"><img src="WebContent/displayImage.jsp?dto=<%=G4GConstants.AVATARDTO%>&id=<%=playerDTO.getAvatars().getId()%>" width="60" height="60"></td>
			<%}else{ %>
			<td width="60"><img src="WebContent/displayImage.jsp?dto=<%=G4GConstants.PICTUREDTO%>&id=<%=playerDTO.getPictureId()%>" width="60" height="60"></td>
			<%} %>	
				<td width="100">
					&nbsp;&nbsp;<b>
					<%
						if (playerDTO != null)out.print(playerDTO.getScreenname());
					%>
					</b>
				</td>
				<td align="right">
					Balance:
					<b>
					$<%
						if (userDTO != null) {
							if (userDTO.getBalance() != null)
								out.print(DataUtil.format(userDTO.getBalance()));
							else {
							out.print("$0.00");
							}
						}
					%>
					</b>
				</td>
				<td align="right"><input onClick="window.location.href='displayCashier.do';" type="button" class="green" value="make deposit"></td>
			</tr>
		</table>
	</div>

</div>