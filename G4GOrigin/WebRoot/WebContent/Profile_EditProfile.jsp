
<%@page import="com.g4g.utils.G4GConstants"%>
<%@page import="com.g4g.action.EditProfileAction"%>
<%@page import="com.g4g.forms.EditProfileForm"%>
<%@page import="com.impessa.worldgaming.client.User"%>
<%@page import="com.g4g.utils.DataUtil"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>

<bean:define id="siteId" value="wg" type="java.lang.String"/>
 <logic:present name="siteId" >
  <bean:define id="siteId" name="siteId" type="java.lang.String"/>
</logic:present>

<script type="text/javascript">

function textReplace(text){
	var intIndexOfMatch = text.indexOf( '\n' );
	while (intIndexOfMatch != -1 ){
		 text = text.replace('\n' , '');
		 intIndexOfMatch = text.indexOf( '\n' );
	}
	return text;
}

</script>
<bean:define id="playerDTO" name="playerDTO" type="com.g4g.dto.PlayerDTO"></bean:define>



<html:form action="/editProfile" onsubmit="document.getElementById('availabilityTemp').value = textReplace(document.getElementById('availability').value);document.getElementById('yourMessageTemp').value = textReplace(document.getElementById('yourMessage').value);">

<%
EditProfileForm form = (EditProfileForm)request.getAttribute(G4GConstants.EDITPROFILEFORM);
EditProfileAction.populate(request,form);
User user = DataUtil.getUserDTO(request).getUser();

%>

<div id="EditProfileWidget" class="Widget">
	<div class="WidgetContent">
		<div class="WidgetSubContent">
			<table>
				<tr>
					<th>Change Avatar</th>
					<td>coming soon...</td>
				</tr>
			</table>
		</div>

		<div class="WidgetSubContent">
			<table>
				<tr>
					<th rowspan="3">Change Password</th>
					<td width="150">
						<html:password property="password" value="" styleId="password"/>
						<html:errors property="password"/>
					</td>
					<td>Old Password</td>
				</tr>
				<tr>
					<td>
						<html:password property="newPassword"value=""/>
						<html:errors property="newPassword"/>
					</td>
					<td>New Password</td>
				</tr>
				<tr>
					<td>
						<html:password property="confirmPassword" value=""/>
						<html:errors property="confirmPassword"/>
					</td>
					<td>Confirm Password</td>
				</tr>
			</table>
		</div>

		<div class="WidgetSubContent">
			<table>
				<tr>
					<th>Change Availability</th>
					<td>
						<textarea name="availability"><bean:write name="playerDTO" property="availability" /></textarea>
						<html:hidden property="availabilityTemp" styleId="availabilityTemp"/>
						<html:errors property="availability"/>
					</td>
				</tr>
			</table>
		</div>

		<div class="WidgetSubContent">
			<table>
				<tr>
					<th>Change Quote</th>
					<td>
						<textarea name="yourMessage"><bean:write name="playerDTO" property="message" /></textarea>
						<html:hidden property="yourMessageTemp" styleId="yourMessageTemp"/>
						<html:errors property="yourMessage"/>
					</td>
				</tr>
			</table>
		</div>

		<div class="WidgetSubContent">
			<table>
				<tr>
					<th rowspan="5">Change Contact Information</th>
					<td width="150">
						<html:text property="skype" value="<%=(String)request.getAttribute(G4GConstants.SKYPE_ALIAS) %>"></html:text>
						<html:errors property="skype"/>
					</td>
					<td>Skype</td>
				</tr>
				<tr>
					<td>
						<html:text property="aim" value="<%=(String)request.getAttribute(G4GConstants.AIM_ALIAS) %>"></html:text>
						<html:errors property="aim"/>
					</td>
					<td>AIM</td>
				</tr>
				<tr>
					<td>
						<html:text property="msn" value="<%=(String)request.getAttribute(G4GConstants.MSN_ALIAS) %>" ></html:text>
						<html:errors property="msn"/>
					</td>
					<td>MSN</td>
				</tr>
				<tr>
					<td>
						<html:text property="yahoo" value="<%=(String)request.getAttribute(G4GConstants.YAHOO_ALIAS) %>"></html:text>
						<html:errors property="yahoo"/>
					</td>
					<td>Yahoo</td>
				</tr>
				<tr>
					<td>
						<html:text property="phone" value="<%=user.getPhone()%>"></html:text>
						<html:errors property="phone"/>
					</td>
					<td>Phone</td>
				</tr>
			</table>
		</div>

		<div class="WidgetSubContent">
			<table>
				<tr>
					<th rowspan="4">Change Gamer Tags</th>
					<td colspan="3">In order for WorldGaming.com to track your WorldGaming results, you must enter your XBOX-LIVE GamerTag and/or Sony Network ID. You will not be able to initiate or accept challenges on WorldGaming.com without this ID.</td>
				</tr>
				<tr>
					<td width="20"><html:checkbox property="xbox360" value="<%=String.valueOf(G4GConstants.NETWORK_XBOX360_LIST_KEY)%>"></html:checkbox></td>
					<td width="80"><img src="WebContent/<bean:write name="siteId"/>/images/icons/xbox360_med.png" width="70" height="24" alt="XBox 360" /></td>
					<td><html:text property="xboxLiveTag" size="23"></html:text></td>
				</tr>
				<tr>
					<td><html:checkbox property="ps2" value="<%=String.valueOf(G4GConstants.NETWORK_PS2_ID_LIST_KEY)%>"></html:checkbox></td>
					<td><img src="WebContent/<bean:write name="siteId"/>/images/icons/ps2_med.png" width="64" height="25" alt="PS2" /></td>
					<td>
						<html:text property="psNetTag" size="23" ></html:text>
						<html:errors property="xbox360"/>
					</td>
				</tr>
				<tr>
					<td><html:checkbox property="ps3" value="<%=String.valueOf(G4GConstants.NETWORK_PS3_ID_LIST_KEY)%>"></html:checkbox></td>
					<td><img src="WebContent/<bean:write name="siteId"/>/images/icons/ps3_med.png" width="64" height="24" alt="PS3" /></td>
				</tr>
			</table>
		</div>

		<div class="WidgetSubContent">
			<table>
				<tr>
					<th rowspan="6">Privacy Preferences</th>
					<td colspan="2">Let us know if we can contact you by email or publish certain information on your public profile:</td>
				</tr>
				<tr>
					<td width="20"><html:checkbox property="prefdisplaycontacts" name="playerDTO"></html:checkbox></td>
					<td>email me WorldGaming events and other info</td>
				</tr>
				<tr>
					<td><html:checkbox property="prefsysmail" name="playerDTO"></html:checkbox></td>
					<td>email me system alerts</td>
				</tr>
				<tr>
					<td><html:checkbox  property="prefdisplayrecord" name="playerDTO"></html:checkbox></td>
					<td>show my win/loss record publicly</td>
				</tr>
				<tr>
					<td><html:checkbox  property="prefaliases" name="playerDTO"></html:checkbox></td>
					<td>show my aliases publicly</td>
				</tr>
				<!--
				<tr>
					<td><html:checkbox  property="prefContactInfo" name="playerDTO"></html:checkbox></td>
					<td>show my contacts publicly</td>
				</tr>
				-->
				<tr>
					<td colspan="3" style="text-align:center;">
						<br />
						<input type="reset" class="green" value="reset" />
						&nbsp;
						<html:submit property="operation" value="change" styleClass="orange" />
					</td>
				</tr>
			</table>
		</div>
	</div>
</div> <!-- end EditProfileWidget -->

</html:form>

<script type="text/javascript">
$(document).ready(function() {
    $(':input').attr('autocomplete', 'off');
document.getElementById('password').value ='';
});
</script>
<%if(request.getAttribute(G4GConstants.SUCCESS)!=null) {%>
	<script>
		notificationPrompt('Profile changed successfully.');
	</script>
<% request.removeAttribute(G4GConstants.SUCCESS);
}%>
