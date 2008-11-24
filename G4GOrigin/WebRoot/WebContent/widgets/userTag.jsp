<%@page import="com.g4g.utils.G4GConstants"%>
<%@page import="com.g4g.utils.SessionUtil"%>
<%@page import="com.g4g.dto.SearchCriteria"%>
<%@page import="com.g4g.delegator.PlayerNetworkServiceDelegator"%>
<%@page import="com.g4g.dto.NetworkDTO"%>

<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>

<bean:define id="playerDTO" name="playerDTO" type="com.g4g.dto.PlayerDTO"></bean:define>
<bean:define id="userDTO" name="userDTO" type="com.g4g.dto.UserDTO"></bean:define>

<%
	String screenName = request.getParameter(G4GConstants.SCREENNAME_DB);
	String xbox360tag = G4GConstants.NONE;
	String psTag = G4GConstants.NONE;
	SearchCriteria searchCriteria = new SearchCriteria();

	if(userDTO.getPlayernetworkDTOxbox360().getPlayernetworktag()!=null){
		xbox360tag = userDTO.getPlayernetworkDTOxbox360().getPlayernetworktag();
	}
	else{
		searchCriteria.removeAllAttribute();
		searchCriteria.setAttribute(G4GConstants.PLAYER, playerDTO);
		searchCriteria.setAttribute(G4GConstants.NETWORK,new NetworkDTO(G4GConstants.NETWORK_XBOX360_LIST_KEY));
		if(PlayerNetworkServiceDelegator.getList(searchCriteria).size() > 0)
			xbox360tag = PlayerNetworkServiceDelegator.getList(searchCriteria).get(0).getPlayernetworktag();
	}

	if(userDTO.getPlayernetworkDTO3ps2().getPlayernetworktag()!=null ||userDTO.getPlayernetworkDTO4ps3().getPlayernetworktag()!=null){
		if(userDTO.getPlayernetworkDTO3ps2().getPlayernetworktag() != null || userDTO.getPlayernetworkDTO4ps3().getPlayernetworktag() != null) {
			if(userDTO.getPlayernetworkDTO3ps2().getPlayernetworktag() != null && userDTO.getPlayernetworkDTO3ps2().getPlayernetworktag() != G4GConstants.NONE) {
				psTag = userDTO.getPlayernetworkDTO3ps2().getPlayernetworktag();
			}
			if(userDTO.getPlayernetworkDTO4ps3().getPlayernetworktag() != null && userDTO.getPlayernetworkDTO4ps3().getPlayernetworktag() != G4GConstants.NONE) {
				psTag = userDTO.getPlayernetworkDTO4ps3().getPlayernetworktag();
			}
		}
	}else{
		searchCriteria.removeAllAttribute();
		searchCriteria.setAttribute(G4GConstants.PLAYER, playerDTO);
		searchCriteria.setAttribute(G4GConstants.NETWORK,new NetworkDTO(G4GConstants.NETWORK_PS2_ID_LIST_KEY));
		searchCriteria.setAttribute(G4GConstants.NETWORK,new NetworkDTO(G4GConstants.NETWORK_PS3_ID_LIST_KEY));

		if(PlayerNetworkServiceDelegator.getList(searchCriteria).size() > 0)
			psTag = PlayerNetworkServiceDelegator.getList(searchCriteria).get(0).getPlayernetworktag();
	}
%>

<div id="MyGamerTag" class="Widget">
	<div class="WidgetHeader">
		<p><%= screenName %>'s Tags</p>
	</div>
	<div class="WidgetContent">
	<% if(!xbox360tag.equals(G4GConstants.NONE)) { %>
		<img src="WebContent/<%=SessionUtil.getSiteId(request) %>/images/icons/xbox360_med.png" alt="XBox360"><br>
		<div>
			<iframe src="http://gamercard.xbox.com/<%= xbox360tag %>.card" scrolling="no" frameBorder="0" height="140" width="204">Xbox Gamer Card: <%= xbox360tag %></iframe>
		</div>
	<% } %>
	<% if(!psTag.equals(G4GConstants.NONE)) { %>
		<img src="WebContent/<%=SessionUtil.getSiteId(request) %>/images/icons/ps3_med.png" alt="PS3">
		<h4>PSNetworkID: <%= psTag %></h4>
	<% } %>
	</div>
</div> <!-- end MyGamerTag -->