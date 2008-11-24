<%@ page language="java" pageEncoding="ISO-8859-1"%>

<%@page import="com.g4g.dto.PlayerDTO"%>
<%@page import="com.g4g.utils.DataUtil"%>
<%@page import="com.g4g.dto.PlayernetworkDTO"%>
<%@page import="com.g4g.dto.UserDTO"%>
<%@page import="com.g4g.delegator.SubNationalCodeServiceDelegator"%>
<%@page import="com.g4g.delegator.NationalCodeServiceDelegator"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>

<bean:define id="siteId" value="wg" type="java.lang.String"/>
 <logic:present name="siteId" >
  <bean:define id="siteId" name="siteId" type="java.lang.String"/>
</logic:present>


<div id="pageTitle">
	<img src="WebContent/<bean:write name="siteId"/>/css/images/banners/banners-registration.jpg" width="920" height="41" alt="Registration">
</div>

<div id="sidebar" class="registration">
	<div id="sidebarStepsTitle">
		<h3>4 Easy Steps!</h3>
	</div>
	<div id="sidebarSteps">
		<p class="off">Enter Your Login Info</p>
		<p class="off">Pick Your Games</p>
		<p class="on">Review</p>
		<p class="off">Get Started!</p>
	</div>

	<div id="sidebarCallOut">
		<h3>Get in the Game!</h3>
		<p>Ten years ago a crack commando unit was sent to prison by a military court for a crime they didn't commit. These men promptly escaped from a maximum security stockade to the Los Angeles underground. Today, still wanted by the government, they survive as soldiers of fortune. If you have a problem and no one else can help, and if you can find them, maybe you can hire the A-team.</p>
	</div>

	<p class="note"><b>*Residents of the following states are not eligible to compete in any cash tournaments on the site:</b><br>
	Arizona, Arkansas, Connecticut, Delaware, Florida, Illinois, Iowa, Louisiana, Montana, South Carolina, South Dakota, Tennessee, and Vermont.</p>

	<p class="note">Please <a href="#">here</a> for more information</p>
</div>

<div id="content" class="registration">

<script type="text/javascript" src="WebContent/<bean:write name="siteId"/>/lib/FormValid.js"> </script>
<script type="text/javascript" src="WebContent/<bean:write name="siteId"/>/js/wg.validate.js"> </script>
<script type="text/javascript" src="WebContent/<bean:write name="siteId"/>/js/date.js"> </script>

<html:form method="post" styleId="mainForm" action="/register3">
<bean:define id="userDTO" scope="session" name="userDTO" type="UserDTO" ></bean:define>
<bean:define id="player" type="PlayerDTO" name="userDTO" property="playerDTO"></bean:define>
<bean:define id="user" type="com.impessa.worldgaming.client.User" name="userDTO" property="user"></bean:define>
<bean:define id="playernetworkDTOxbox" type="PlayernetworkDTO" name="userDTO" property="playernetworkDTOxbox"></bean:define>
<bean:define id="playernetworkDTOxbox360" type="PlayernetworkDTO" name="userDTO" property="playernetworkDTOxbox360"></bean:define>
<bean:define id="playernetworkDTOps2" type="PlayernetworkDTO" name="userDTO" property="playernetworkDTO3ps2"></bean:define>
<bean:define id="playernetworkDTOps3" type="PlayernetworkDTO" name="userDTO" property="playernetworkDTO4ps3"></bean:define>


<h1>Review and Submit</h1>

<p>Please review the following information; you can always change it in your preferences later on as well. Click "Save" when you're done and you can start playing.</p>
<br>

<h2>Account Name, Address &amp; Communication Preferences</h2>

<p>Make sure your screen name and email address are correct as these cannot be changed once the account is created. Your email address will be your login to the site.</p>
<br>

<table width="100%" id="NameAddress">
	<tr>
		<td width="75"><html:submit styleClass="gray" value="Edit" property="operation"></html:submit></td>
		<td>
		<table width="100%">
			<tr>
				<td><b>Screen Name</b></td>
				<td><bean:write name="player" property="screenname"/></td>
			</tr>
			<tr>
				<td><b>Full Name</b></td>
				<td><bean:write name="user" property="firstname"/>&nbsp;<bean:write name="user" property="lastname"/></td>
			</tr>
			<tr>
				<td><b>Address</b></td>
				<td>
					<bean:write name="user" property="address"/><br>
					<bean:write name="user" property="city"/>,&nbsp;<%out.print(SubNationalCodeServiceDelegator.getSubNationName(user.getState()));%>,&nbsp;<%out.print(NationalCodeServiceDelegator.getNationName(user.getCountry()));%>
					<br>
					<bean:write name="user" property="postalcode"/>
				</td>
			</tr>
			<tr>
				<td><b>Date of Birth</b></td>
				<td><%=DataUtil.getDate(user.getBirthdate() , "dd-MMM-yyyy") %></td>
			</tr>
			<tr>
				<td><b>Gender</b></td>
				<td><bean:write name="user" property="gender"/></td>
			</tr>
			<tr><td colspan="2">&nbsp;</td></tr>
			<tr valign="top">
				<td><b>Privacy Preferences</b></td>
				<td>
          <%
          if(player.getPrefdisplaycontacts()){
          	out.print("Contact Me regarding World Gaming offers and events");
          }
          %><br>
          <%
          if(player.getPrefsysmail()){
          	out.print("Send me email notifications");
          }
          %><br>
          <%
          if(player.getPrefdisplayrecord()){
          	out.print("Show my win loss record publicly");
          }
          %>
				</td>
			</tr>
		</table>
		</td>
	</tr>
</table>

<br>
<h2>Consoles, Gamer Tags &amp; Games</h2>
<br>
<table width="100%" id="ReviewPrefs">
	<tr>
		<td width="75"><html:submit styleClass="gray" value="EDIT" property="operation"></html:submit></td>
		<td>
			<table width="100%">
				<tr>
		        <% if(playernetworkDTOxbox.getCreationdate()!=null){ %>
 					<td><p><b>XBoxLive</b>&nbsp;&nbsp;<%=playernetworkDTOxbox.getPlayernetworktag() %></p></td>
				<%}%>
				<% if(playernetworkDTOxbox360.getCreationdate()!=null){ %>
					<td><p><b>XBox360Live</b>&nbsp;&nbsp;<%=playernetworkDTOxbox360.getPlayernetworktag() %></p></td>
				<% } %>
				</tr>
				<tr>
				<% if(playernetworkDTOps2.getCreationdate()!=null){ %>
					<td><p><b>PS2Net</b>&nbsp;&nbsp;<%=playernetworkDTOps2.getPlayernetworktag() %></p></td>
				<% } %>
				<% if(playernetworkDTOps3.getCreationdate()!=null){ %>
					<td><p><b>PS3Net</b>&nbsp;&nbsp;<%=playernetworkDTOps3.getPlayernetworktag() %></p></td>
				<% } %>
				</tr>
			</table>
		</td>
	</tr>
</table>

<!-- Captcha -->
<table>
	<tr>
		<td width="225">
			<img id="captcha" src="Captcha.jpg" align="middle" alt="Enter the characters appearing in this image" border="1"/></td>
		<td>
		<input type="text" name="captcha" value="" ><br>
			Please type the characters you see in the picture.
		</td>
	</tr>
	<tr>
		<td>&nbsp;</td>
		<td><html:errors property="captcha" /></td>
	</tr>
</table>

<br><br>

<table>
	<tr>
		<td width="75">&nbsp;</td>
		<td><html:submit styleClass="green" value="save" property="operation"></html:submit></td>
	</tr>
</table>

</html:form>

<script type="text/javascript" src="WebContent/<bean:write name="siteId"/>/lib/jquery.corner.js"></script>

</div>