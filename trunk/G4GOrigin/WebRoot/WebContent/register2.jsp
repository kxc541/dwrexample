<%@page import="com.g4g.plugin.G4GOriginSession"%>
<%@page import="com.g4g.utils.DataUtil"%>
<%@page import="com.g4g.utils.G4GConstants"%>

<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>

<bean:define id="siteId" value="wg" type="java.lang.String"/>
 <logic:present name="siteId" >
  <bean:define id="siteId" name="siteId" type="java.lang.String"/>
</logic:present>

<script language="JavaScript" type="text/javascript">

function checkConsoles() {
	if (document.getElementById('xbox360CB').checked) {
		document.getElementById('xbox360Games').className = 'show';
	}
	if (document.getElementById('ps2CB').checked) {
		document.getElementById('ps2Games').className = 'show';
	}
	if (document.getElementById('ps3CB').checked) {
		document.getElementById('ps3Games').className = 'show';
	}	
}

function toggleGames(console) {

	gamesList = document.getElementById(console);

	gamesList.className = ((gamesList.className == 'show' || !gamesList.className) ? 'hide' : 'show');

	if (document.getElementById('xbox360CB').checked) {
	} else {
		document.getElementById('xboxLiveTag').value = '';
	}

	if (document.getElementById('ps2CB').checked || document.getElementById('ps3CB').checked) {
	} else {
		document.getElementById('psNetTag').value = '';
	}
}

function textReplace(text){
	var intIndexOfMatch = text.indexOf( '\n' );
	while (intIndexOfMatch != -1 ){
		 text = text.replace('\n' , '');
		 intIndexOfMatch = text.indexOf( '\n' );
	}
	return text;
}


</script>

<script type="text/javascript"
	src="WebContent/<bean:write name="siteId"/>/lib/FormValid.js"> </script>
<script type="text/javascript"
	src="WebContent/<bean:write name="siteId"/>/js/wg.validate.js"> </script>
<script type="text/javascript"
	src="WebContent/<bean:write name="siteId"/>/js/date.js"> </script>

<div id="pageTitle">
	<img
		src="WebContent/<bean:write name="siteId"/>/css/images/banners/banners-registration.jpg"
		width="920" height="41" alt="Registration" onLoad="checkConsoles();">
</div>

<div id="sidebar" class="registration">
	<div id="sidebarStepsTitle">
		<h3>
			4 Easy Steps!
		</h3>
	</div>
	<div id="sidebarSteps">
		<p class="off">
			Enter Your Login Info
		</p>
		<p class="on">
			Pick Your Games
		</p>
		<p class="off">
			Review
		</p>
		<p class="off">
			Get Started!
		</p>
	</div>

	<div id="sidebarCallOut">
		<h3>
			Get in the Game!
		</h3>
		<p>
			Ten years ago a crack commando unit was sent to prison by a military
			court for a crime they didn't commit. These men promptly escaped from
			a maximum security stockade to the Los Angeles underground. Today,
			still wanted by the government, they survive as soldiers of fortune.
			If you have a problem and no one else can help, and if you can find
			them, maybe you can hire the A-team.
		</p>
	</div>

	<p class="note">
		<b>*Residents of the following states are not eligible to compete
			in any cash tournaments on the site:</b>
		<br>
		Arizona, Arkansas, Connecticut, Delaware, Florida, Illinois, Iowa,
		Louisiana, Montana, South Carolina, South Dakota, Tennessee, and
		Vermont.
	</p>

	<p class="note">
		Please
		<a href="#">here</a> for more information
	</p>
</div>

<div id="content" class="registration">

	<h1>
		Select Your Consoles
	</h1>

	<html:form method="post" styleId="mainForm" action="/register2"
		enctype="multipart/form-data">
		<html:errors property="firstLogin" />
		<p>
			In order for other members to identify you and your gaming
			preferences, please provide the following information. You can always
			login and edit your settings once your registration is complete.
		</p>

		<table cellpadding="0" cellspacing="0" border="0" width="100%"
			id="ConsolePrefs">

			<bean:define id="display" value="" type="java.lang.String" />
			<logic:present name="screenName">
				<bean:define id="display" name="screenName" type="java.lang.String" />
			</logic:present>

			<logic:notEqual value="" name="display">
				<%
					session.setAttribute(G4GConstants.ATTR_SCREEN_NAME, G4GConstants.ATTR_SCREEN_NAME_VALUE);
				%>
				<tr>
					<td style="padding: 10px;">
						<b>WorldGaming.com Screen Name&nbsp;</b>
					</td>
					<td>
						<html:text property="screenName"
							onblur="FormValid.isNameAvailable()" styleId="screenName" />
						&bull;
						<br>
						<html:errors property="screenName" />
						<span id="availableMsg" style="float: right"></span>
					</td>
				</tr>
			</logic:notEqual>
			<tr>
				<td style="padding: 10px;">
					<html:checkbox property="xbox360"
						onclick="toggleGames('xbox360Games');" styleId="xbox360CB"
						value="2" />
					&nbsp;&nbsp;
					<b>Xbox360</b>
				</td>
				<td align="right">
					<b>Xbox LIVE Gamertag</b>
					<html:text property="xboxLiveTag" styleId="xboxLiveTag"
						maxlength="255" />
					<br>
					<label id="xboxAvailableMsg" for="xboxLiveTag" class="error"></label>
					<html:errors property="errors.xboxLiveTag" />
					<html:errors property="xboxLiveTag" />
				</td>
			</tr>
			<tr>
				<td style="padding: 10px;">
					<html:checkbox property="ps2" onclick="toggleGames('ps2Games');"
						styleId="ps2CB" value="3" />
					&nbsp;&nbsp;
					<b>PS2</b>&nbsp;&nbsp;&nbsp;&nbsp;
					<html:checkbox property="ps3" onclick="toggleGames('ps3Games');"
						styleId="ps3CB" value="4" />
					&nbsp;&nbsp;
					<b>PS3</b>
				</td>
				<td align="right">
					<b>PlayStation Network ID</b>
					<html:text styleId="psNetTag" property="psNetTag" maxlength="255" />
					<br>
					<label id="psAvailableMsg" for="psNetTag" class="error"></label>
					<html:errors property="errors.ps2LiveTag" />
					<html:errors property="errors.psNetTag" />
				</td>
			</tr>
			<tr>
				<td align="left">
					<html:errors property="xbox" />
				</td>
				<td align="left">
					<html:errors property="errors.xboxLiveTag" />
				</td>
				<td align="right">
					<html:errors property="errors.ps2LiveTag" />
				</td>
			</tr>
		</table>

		<p align="right">
			<html:errors property="xbox" />
		<p>
		<p>
			You must enter your Xbox LIVE Gamertag and/or PlayStation Network ID
			so WorldGaming.com can track your WorldGaming results. You will not
			be able to initiate or accept challenges on WorldGaming.com without a
			Gamertag or Network ID.
		</p>

		<br>

		<div id="selectGames" class="show">

			<h2>
				Select Your Games
			</h2>

			<p>
				In order for other members to identify you and your gaming
				preferences, please provide the following information. You can
				always login and edit your settings once your registration is
				complete.
			</p>

		</div>

		<div id="xbox360Games" class="hide">
			<h2>
				XBox 360
			</h2>
			<table cellpadding="0" cellspacing="0" border="0" width="100%">
				<tr valign="top">
					<td>
						<logic:iterate id="xbox360" name="register2Form"
							property="xbox360List">
							<html:multibox property="selectedxbox360Id">
								<bean:write name="xbox360" property="id" />
							</html:multibox>
							<bean:write name="xbox360" property="gamename" />
							<br>
						</logic:iterate>
					</td>
				</tr>
			</table>
		</div>

		<div id="ps2Games" class="hide">
			<h2>
				PS2
			</h2>
			<table cellpadding="0" cellspacing="0" border="0" width="100%">
				<tr valign="top">
					<td>
						<logic:iterate id="ps2" name="register2Form" property="ps2List">
							<html:multibox property="selectedps2Id">
								<bean:write name="ps2" property="id" />
							</html:multibox>
							<bean:write name="ps2" property="gamename" />
							<br>
						</logic:iterate>

					</td>
				</tr>
			</table>
		</div>

		<div id="ps3Games" class="hide">
			<h2>
				PS3
			</h2>
			<table cellpadding="0" cellspacing="0" border="0" width="100%">
				<tr valign="top">
					<td>
						<logic:iterate id="ps3" name="register2Form" property="ps3List">
							<html:multibox property="selectedps3Id">
								<bean:write name="ps3" property="id" />
							</html:multibox>
							<bean:write name="ps3" property="gamename" />
							<br>
						</logic:iterate>
					</td>
				</tr>
			</table>
		</div>

		<br>

		<h2>
			Upload Your Avatar
		</h2>

		<p>
			<html:errors property="avatarImageId" />
		</p>

		<bean:define id="list" name="register2Form" scope="session" property="avatarList"></bean:define>


		<script type="text/javascript" src="wg/js/jquery-1.1.3.1.js"></script>

		<script type="text/javascript" src="wg/js/ajaxfileupload.js"></script>
		<script type="text/javascript">
 function ajaxFileUpload()
 {
  $("#loading")
  .ajaxStart(function(){
   $(this).show();
  })
  .ajaxComplete(function(){
   $(this).hide();
  });

  $.ajaxFileUpload
  (
   {
    url:'ajax/fileUpload.do',
    secureuri:false,
    fileElementId: 'fileToUpload',
    dataType: 'json',
    success: function (data, status)
    {
     if(typeof(data.error) != 'undefined')
     {
      if(data.error != '')
      {
     	tb_show('', '<%=DataUtil.getBasePath(request)%>WebContent/displayPicture.jsp?TB_iframe=true&height=500&width=650&modal=true', '');
      	}else {
      	}
     }
    },
    error: function (data, status, e){}
   }
  )
  return false;

 }
 </script>

		<html:file property="picture" styleId="fileToUpload"
			onchange="document.getElementById('picturetemp').value = this.value; "></html:file>
		<html:hidden property="picturetemp" styleId="picturetemp" />


&nbsp;&nbsp;

<html:errors property="picture" />
		<a href="#" id="imagepreview" onclick="return ajaxFileUpload();">Preview</a>

		<br>
		<br>


		<div style="overflow: auto;">
			<logic:iterate id="avatar" name="list" type="com.g4g.dto.AvatarsDTO">
				<div style="float: left;">
					<html:radio property="avatarImageId" value="<%=String.valueOf(avatar.getId())%>"
						onclick="javascript:document.getElementById('picture').value=null;">&nbsp;
						<img src="WebContent/displayImage.jsp?dto=AvatarsDTO&id=<%=avatar.getId()%>" height="65" width="65" style="padding: 0px 15px 0px 0px;">
					</html:radio>
				</div>
			</logic:iterate>
		</div>
		<br>

		<table width="100%">
			<tr valign="top">
				<td width="40%">
					<b>What's Your Message?</b>
					<br>
					Use this message (up to 100 characters) to dare others to challenge
					you.
				</td>
				<td width="60%">
					<html:textarea property="yourMessage" styleId="profileHeadline"
						onkeyup="document.getElementById('yourMessageTemp').value = textReplace(this.value);" />
					<html:hidden property="yourMessageTemp" styleId="yourMessageTemp" />
					<html:errors property="yourMessageTemp" />
				</td>
			</tr>
			<tr>
				<td>
					<b>What's Your Story?</b>
					<br>
					Tell on yourself.
				</td>
				<td>
					<html:textarea styleId="aboutMe" property="yourStory"
						onkeyup="document.getElementById('yourStoryTemp').value = textReplace(this.value);" />
					<html:hidden property="yourStoryTemp" styleId="yourStoryTemp" />
					<html:errors property="yourStoryTemp" />
				</td>
			</tr>
			<tr>
				<td>
					<b>When Do You Play?</b>
					<br>
					Describe when you're usually available to play.
				</td>
				<td>
					<html:textarea property="availability" styleId="availability"
						onkeyup="document.getElementById('availabilityTemp').value = textReplace(this.value);" />
					<html:hidden property="availabilityTemp" styleId="availabilityTemp" />
					<html:errors property="availabilityTemp" />
				</td>
			</tr>
			<tr>
				<td colspan="2">
					&nbsp;
				</td>
			</tr>
			<tr>
				<td>
					<b>What Do You Play For?</b>
					<br>
					Set the average amount you like to wager on a game. This gives
					everybody a way to know how good you think you are!
				</td>
				<td>
				<logic:present name="unskilled">
					<html:hidden property="playfor$" value="0"/>
					Your region does not support Skilled Gaming.
				</logic:present>
				<logic:notPresent name="unskilled">
					<html:select property="playfor$">
						<html:option value=""></html:option>
						<html:option value="5"> 05 - 10</html:option>
						<html:option value="15">10 - 25</html:option>
						<html:option value="40">25 - 50 </html:option>
						<html:option value="60">50 - 100 </html:option>
						<html:option value="120">100+ </html:option>
					</html:select>
				</logic:notPresent>
					&bull;
					<br>
					<html:errors property="playfor$" />
				</td>
			</tr>
			<tr>
				<td colspan="2">
					&nbsp;
				</td>
			</tr>
			<tr>
				<td>
					<b>Time Zone</b>
				</td>
				<td>

					<html:select property="prefTimeZone">
						<html:option value="">Select Your Time Zone</html:option>
						<html:options property="key" labelProperty="description"
							collection="<%=G4GOriginSession.LIST_TIME_ZONE%>" />
					</html:select>
					&nbsp;&bull;
					<br>
					<html:errors property="prefTimeZone" />
				</td>
			</tr>
			<tr>
				<td>
					<b>Contact Information</b>
					<br>
					You can choose to make the following information available:
				</td>
				<td>
					&nbsp;
				</td>
			</tr>
			<tr>
				<td align="right">
					Skype
				</td>
				<td>
					<html:text property="skype" maxlength="255" />
					<html:errors property="skype" />
				</td>
			</tr>
			<tr>
				<td align="right">
					AIM
				</td>
				<td>
					<html:text property="aim" maxlength="255" />
					<html:errors property="aim" />
				</td>
			</tr>
			<tr>
				<td align="right">
					MSN
				</td>
				<td>
					<html:text property="msn" maxlength="255" />
					<html:errors property="msn" />
				</td>
			</tr>
			<tr>
				<td align="right">
					Yahoo
				</td>
				<td>
					<html:text property="yahoo" maxlength="255" />
					<html:errors property="yahoo" />
				</td>
			</tr>
			<tr>
				<td>
					<b>Privacy Preferences</b>
					<br>
					Let us know if we can contact you by email or publish certain
					information on your public profile:
				</td>
				<td>
					&nbsp;
				</td>
			</tr>
			<tr>
				<td align="right">
					<br>
					email me WorldGaming events and other info
				</td>
				<td>
					<br>
					<html:checkbox property="contactMeAbtWorldGamingEvents"
						value="true" />
				</td>
			</tr>
			<tr>
				<td align="right">
					email me system alerts
				</td>
				<td>
					<html:checkbox property="emailMySystemAlerts" value="true" />
				</td>
			</tr>
			<tr>
				<td align="right">
					show my win/loss record publicly
				</td>
				<td>
					<html:checkbox property="showMyWinLosspublically" value="true" />
				</td>
			</tr>
			<tr>
				<td align="right">
					show my aliases publicly
				</td>
				<td>
					<html:checkbox property="aliases" value="true" />
				</td>
			</tr>
			<tr>
				<td>
					&nbsp;
				</td>
				<td>
					<br>
					<html:submit styleClass="green" value="Review" property="operation" />
				</td>
			</tr>
		</table>

	</html:form>

	<%
		session.removeAttribute("register2Form");
	%>

</div>

<script type="text/javascript">

		if(document.getElementById('picturetemp').value!=null){
			document.getElementById('fileToUpload').value = document.getElementById('picturetemp').value;
		}

	</script>
