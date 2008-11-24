<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>

<%@page import="java.util.List"%>
<%@page import="com.g4g.dto.PlayerDTO"%>
<%@page import="com.g4g.utils.G4GConstants"%>
<bean:define id="siteId" value="wg" type="java.lang.String"/>
 <logic:present name="siteId" >
  <bean:define id="siteId" name="siteId" type="java.lang.String"/>
</logic:present>

 <div style="overflow: auto;">		
	<%
	List<PlayerDTO> playerFriendsList = (List<PlayerDTO>)request.getAttribute(G4GConstants.PLAYERFRIENDSLIST);
	if(playerFriendsList.size() > 0 ){
		for(int index=0;index<playerFriendsList.size();index++){
			PlayerDTO playerfriendDTO = (PlayerDTO)playerFriendsList.get(index);
		 	%>
			<jsp:include page="common/avatar.jsp">
				<jsp:param name="playerid" value="<%=playerfriendDTO.getId() %>"/>
				<jsp:param name="avatarid" value="<%= (playerfriendDTO.getAvatars() != null) ? playerfriendDTO.getAvatars().getId() : 0 %>"/>
				<jsp:param name="screenname" value="<%=playerfriendDTO.getScreenname() %>"/>
				<jsp:param name="isOnline" value="<%=playerfriendDTO.isIsonline()%>"/>
				<jsp:param name="pictureId" value="<%=playerfriendDTO.getPictureId() %>"/>
				<jsp:param name="showMenu" value="true"/>
				<jsp:param name="isLink" value="true"/>
			</jsp:include>
		<%
		}
	} else {
	%>
		<h3>No Friends To Display</h3>
	<%} %>		
</div>