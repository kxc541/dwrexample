<%@page import="com.g4g.dto.PlayerDTO"%>
<%@page import="com.g4g.dto.UserDTO"%>
<%@page import="com.g4g.utils.G4GConstants"%>
<%@page import="com.g4g.utils.SessionUtil"%>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";

PlayerDTO playerDTO = ((UserDTO) session.getAttribute(G4GConstants.USERDTO)).getPlayerDTO();
%>

<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>

<bean:define id="siteId" value="wg" type="java.lang.String"/>
 <logic:present name="siteId" >
  <bean:define id="siteId" name="siteId" type="java.lang.String"/>
</logic:present>

<link rel="stylesheet" media="screen" href="WebContent/<%=SessionUtil.getSiteId(request) %>/css/Mail.css">

<script type="text/javascript">
	$(document).ready(function(){
		$(".MessageContainer.compose").hide();
	});
</script>
<!--

The following is the global page layout structure.
- A containing "ContentWrapper" with a page specific ID
- PageContent container with an optional Context specific ID
- Sidebar

-->

<div class="ContentWrapper clearfix" id="MailCenter">
	<div class="AreaWrapperTitle">
    	<img src="<%= basePath %>WebContent/<bean:write name="siteId"/>/css/images/banners/banners-mailcenter.jpg">
    </div><!-- AreaWrapperTitle -->
    <div class="ComposeButton buttons">
    	<a href="javascript:{}" class="green" onclick="showCompose()">Compose Message</a>
    </div>
    <div class="Sidebar">
    	<div class="MailCenterNav updateFolderCounts">
    		<h1><%= playerDTO.getScreenname() %></h1>
			<input id="g4gTag" type="hidden" value="<%=playerDTO.getScreenname() %>" name="MessageCenterWrapper.23.3.5">
			<ul>
				<li>
					<a href="javascript:{}" onclick="showMessages('Unread', 'ignore', 'false')">
						<span class="image inbox">New<span class="count" id="newCount">(<bean:write name="messageCenterForm" property="newMessages" />)</span></span>
					</a>
                    <ul>
						<li>
							<a href="javascript:{}" onclick="showMessages('Unread', 'Challenge', 'false')">
								<span class="image challenge">Incoming Challenges<span class="count" id="newChallengeCount"></span></span>
							</a>
						</li>
						<li>
							<a href="javascript:{}" onclick="showMessages('Unread', 'Friend Request', 'false')">
								<span class="image user">Friend Requests<span class="count" id="friendRequestsCount"></span></span>
							</a>
						</li>
					</ul>
				</li>
                <li>
					<a href="javascript:{}" onclick="showMessages('Read', 'ignore', 'false')">
						<span class="image inbox">Inbox<span class="count" id="readInboxCount"></span></span>
					</a>
				</li>
                <li>
					<a href="javascript:{}" onclick="showMessages('ignore', 'WorldGaming', 'false')">
						<span class="image status">World Gaming<span class="count" id="worldGamingCounts"></span></span>
					</a>
				</li>
                <li>
					<a href="javascript:{}" onclick="showMessages('Archived', 'ignore', 'false')">
						<span class="image archive">Archived Messages<span class="count" id="archivedCount"></span></span>
					</a>
				</li>
                <li>
					<a href="javascript:{}" onclick="showMessages('ignore', 'ignore', 'true')">
						<span class="image sent">Sent Messages<span class="count" id="sentMessageCount">(<bean:write name="messageCenterForm" property="sentMessages" />)</span></span>
					</a>
				</li>
			</ul>
		</div>
	</div>
	<div class="PageContent">
		<div class="MessageContainer compose clearfix">
    		<h1>Compose Message</h1>
    		<label for="friendsList">Choose Friend:</label>
            <bean:define id="friendsList" name="messageCenterForm" property="friends" type="java.util.List" scope="request" />
			<select id="friendsList" name="playerFriend">
				<option value="WONoSelectionString">You must select a recipient</option>
				<%
				int totalFriends = friendsList.size();
				for(int index = 0; index < totalFriends; index++) {
					PlayerDTO friend = (PlayerDTO) friendsList.get(index);
					out.println("<option value=\"" + friend.getScreenname() + "\">" + friend.getScreenname() + "</option>");
				}
				%>	
			</select>
            <div class="clear"></div>
           	
           	<label for="newMessageSubject">Subject:</label>
           	<input type="text" id="newMessageSubject">
            <div class="clear"></div>
            
            <label for="newMessageBody">Body:</label>
            <textarea name="messagereply" id="newMessageBody" rows="70" cols="7"></textarea>
            <div class="clear"></div>
            
            <div class="buttons">
            	<a class="red" href="javascript:{}" onclick="hideCompose()">Cancel</a>
                <button class="green" onclick="sendNewMessage()">Send</button>
            </div>
        </div>
		<div id="messagesContent">
			<script type="text/javascript">
			showMessages('Unread', 'ignore', 'false');
			</script>
		</div>
	</div>
</div>
