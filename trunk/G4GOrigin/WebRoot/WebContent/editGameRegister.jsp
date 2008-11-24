<%@ page language="java" pageEncoding="ISO-8859-1"%>


<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<bean:define id="siteId" value="wg" type="java.lang.String" />
<logic:present name="siteId">
	<bean:define id="siteId" name="siteId" type="java.lang.String" />
</logic:present>
<html:html>
	<head>
		<meta content="text/html; charset=UTF-8" http-equiv="Content-Type">
		<link rel="stylesheet" media="screen"
			href="WebContent/<bean:write name="siteId"/>/css/Global.css">
		<link rel="stylesheet" media="screen"
			href="WebContent/<bean:write name="siteId"/>/css/Widgets.css">
		<link rel="stylesheet" media="screen"
			href="WebContent/<bean:write name="siteId"/>/css/Registration.css">
		<link rel="stylesheet" media="screen"
			href="WebContent/<bean:write name="siteId"/>/css/cfab.css">
		<title>Registering to Win with World Gaming</title>
		<!--RegistrationWrapper-->
		<script type="text/javascript"
			src="WebContent/<bean:write name="siteId"/>/lib/firebug/firebug.js">Generic</script>
		<script type="text/javascript" src="WebContent/<bean:write name="siteId"/>/js/jquery.js">Generic</script>
		<script type="text/javascript" src="WebContent/<bean:write name="siteId"/>/js/jquery.timer.js">Generic</script>
		<script type="text/javascript" src="WebContent/<bean:write name="siteId"/>/js/wgLib.js">Generic</script>
		<script type="text/javascript" src="WebContent/<bean:write name="siteId"/>/js/OpenClose.js">Generic</script>
		<script type="text/javascript" src="WebContent/<bean:write name="siteId"/>/js/ActiveAvatar.js">Generic</script>
		<script type="text/javascript"
			src="WebContent/<bean:write name="siteId"/>/js/jquery.idTabs.pack.js">Generic</script>
		<script type="text/javascript" src="WebContent/<bean:write name="siteId"/>/lib/g4glib.js">Generic</script>
		<script type="text/javascript"
			src="WebContent/<bean:write name="siteId"/>/js/jquery.validate.js">Generic</script>
		<script type="text/javascript" src="WebContent/<bean:write name="siteId"/>/js/jquery.form.js">Generic</script>
		<script type="text/javascript"
			src="WebContent/<bean:write name="siteId"/>/js/jquery.metadata.js">Generic</script>
		<script type="text/javascript" src="WebContent/<bean:write name="siteId"/>/js/date.js">Generic</script>
		<script type="text/javascript"
			src="WebContent/<bean:write name="siteId"/>/js/ajaxfileupload.js">Generic</script>
	</head>
	<body>
		<div id="container">
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
										<strong>
											<div id="registeredCount">
												53
											</div> </strong>
									</td>
								</tr>
								<tr>
									<td>
										Members Currently Online
									</td>
									<td>
										<strong>
											<div id="onlineCount">
												0
											</div> </strong>
									</td>
								</tr>
							</tbody>
						</table>
					</div>
				</div>
				<p class="LogInOut">
					<a href="register1.jsp">register</a>&nbsp;
					<a href="HomePage.jsp">log in</a>
				</p>
			</div>
			<div id="globalnav">
				<ul>
					<li id="gNavHome">
						<span><a href="HomePage.jsp">HOME</a> </span>
					</li>
					<li id="gNavAbout">
						<span><a href="About.jsp">About World Gaming</a> </span>
					</li>
					<li id="gNavMessage">
						<span><a href="PlayForMoney.jsp">Play for money</a> </span>
					</li>
					<li>
						<br>
					</li>
					<li id="gNavBlog">
						<span><a href="/wordpress/">Blog</a> </span>
					</li>
					<li id="gNavForum">
						<span><a href="/wgforum/">Forum</a> </span>
					</li>
					<li id="gNavFAQs">
						<span><a href="FAQ.jsp">Help/FAQs</a> </span>
					</li>
					<li id="gNavContact">
						<span><a href="Contact.jsp">Contact</a> </span>
					</li>
				</ul>
			</div>
			<!-- 

The following is the global page layout structure.
- A containing "ContentWrapper" with a page specific ID
- PageContent container with an optional Context specific ID
- Sidebar

-->
			<div class="ContentWrapper clearfix" id="Registration">
				<div class="AreaWrapperTitle">
					<img
						src="WebContent/<bean:write name="siteId"/>/css/images/banners/banners-registration.jpg">
				</div>
				<!-- AreaWrapperTitle -->
				<div class="Sidebar">
					<dl id="regProcess">
						<dt>
							4 Easy Steps!
						</dt>
						<dd class="on">
							<img src="WebContent/<bean:write name="siteId"/>/css/images/icons/Registration-check.jpg">
							Enter your Login Info
						</dd>
						<dd class="on">
							<img src="WebContent/<bean:write name="siteId"/>/css/images/icons/Registration-check.jpg">
							Pick your Games
						</dd>
						<dd class="opacity">
							<img src="WebContent/<bean:write name="siteId"/>/css/images/icons/Registration-check.jpg">
							Review
						</dd>
						<dd class="opacity">
							<img src="WebContent/<bean:write name="siteId"/>/css/images/icons/Registration-check.jpg">
							Get Started!
						</dd>
					</dl>
					<dl id="GetIn">
						<dt>
							Get in the Game!
						</dt>
						<dd>
							Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Aenean
							consectetuer. Etiam venenatis. Sed ultricies, pede sit amet
							aliquet lobortis, nisi ante sagittis sapien, in rhoncus lectus
							mauris quis massa. Integer porttitor, mi sit amet viverra
							faucibus, urna libero viverra nibh, sed dictum nisi mi et diam.
						</dd>
					</dl>
					<p>
						<strong>*Residents of the following states are not
							eligible to compete in any cash tournaments on the site</strong> Arizona,
						Arkansas, Connecticut, Delaware, Florida, Illinois, Iowa,
						Louisiana, Montana, South Carolina, South Dakota, Tennessee and
						Vermont.
					</p>
					<p>
						Please
						<a href="#">here</a> for more information
					</p>
				</div>
				<div class="PageContent">
					<script type="text/javascript" src="WebContent/<bean:write name="siteId"/>/lib/FormValid.js"> </script>
					<script type="text/javascript"
						src="WebContent/<bean:write name="siteId"/>/js/wg.validate.js"> </script>
					<script type="text/javascript" src="WebContent/<bean:write name="siteId"/>/js/date.js"> </script>
					<script type="text/javascript" charset="utf-8">
		$(document).ready(function() {
			if($("#xboxLiveTag").get(0) != null && $("#psNetTag").get(0) != null) {
				
				if($("#xboxLiveTag").get(0).value == "" && !$("#xboxCB").get(0).checked && !$("#xbox360CB").get(0).checked )
					$("#xboxLiveTag").hide();
				if($("#psNetTag").get(0).value == "" && !$("#ps2CB").get(0).checked && !$("#ps3CB").get(0).checked )
					$("#psNetTag").hide();

				
				if( !$("#xboxCB").get(0).checked && !$("#xbox360CB").get(0).checked 
					&& !$("#ps2CB").get(0).checked && !$("#ps3CB").get(0).checked) {
					$("#gameSelection").hide();
				}
				
				if(!$("#ps2CB").get(0).checked)	
					$("#PS2").hide();
				
				if(!$("#ps3CB").get(0).checked)	
					$("#PS3").hide();
				if(!$("#xboxCB").get(0).checked)	
					$("#XBox").hide();
				if(!$("#xbox360CB").get(0).checked)	
					$("#XBox360").hide();
			}
			if($("#birthDate").get(0) != null) {
				if($("#birthDate").get(0).value != "") {
					registrationDateDefault();
				}
			}
			var avatarSelected = false;
		});			
	</script>
					<div class="SubNav" style="display: none;">
						<ul class="idTabs">
							<li>
								<a href="#page1" class="selected"><span>Login Info</span> </a>
								<br>
								<br>
							</li>
							<li>
								<a href="#page2"><span>Member Preferences</span> </a>
								<br>
								<br>
							</li>
							<li>
								<a href="#page3"><span>Review</span> </a>
								<br>
								<br>
							</li>
							<li>
								<a href="#page4"><span>Confirmation</span> </a>
								<br>
								<br>
							</li>
						</ul>
					</div>
					<html:form method="post" styleId="mainForm" action="/register2">
						<div>
							<fieldset>
								<legend>
									Select your consoles
								</legend>
								<table id="ConsolePrefs">
									<caption>
										In order for other members to identify you and your gaming
										preferences, please provide the following information. you
										can always login and edit your settings once your registration
										is complete.
									</caption>
									<tbody>
										<tr>
											<th>
												<h2>
													Your Consoles
												</h2>
											</th>
											<td class="clearfix">
												<ul>
													<li>
														<span class="checkboxWrapper"> <html:checkbox
																property="xbox" styleClass="checkbox xbox"
																onclick="xboxCheck(this)" styleId="xboxCB"
																value="0.RegistrationWrapper.15.1.6.1.1.1.3.0.1.1" />
														</span>
														<img src="WebContent/<bean:write name="siteId"/>/images/icons/xbox_med.png">
													</li>
													
													<li>
														<span class="checkboxWrapper"><html:checkbox property="xbox360"
																styleClass="checkbox xbox" onclick="xboxCheck(this)"
																styleId="xbox360CB" 
																value="0.RegistrationWrapper.15.1.6.1.1.1.3.0.1.3" />
														</span>
														<img src="WebContent/<bean:write name="siteId"/>/images/icons/xbox360_med.png">
													</li>
													<li>
														<html:text property="xboxLiveTag" styleId="xboxLiveTag" onblur="isXBoxLiveTagAvailable()" />
														<label id="xboxAvailableMsg" for="xboxLiveTag"
															class="error"></label>
														<!--br<div id="xboxAvailableMsg" class="error"></div-->
													</li>
												</ul>
												<ul>
													<li>
														<span class="checkboxWrapper"><html:checkbox property="ps2"
																styleClass="checkbox playStation" onclick="psCheck(this)"
																styleId="ps2CB" 
																value="0.RegistrationWrapper.15.1.6.1.1.1.3.0.1.9" />
														</span>
														<img src="WebContent/<bean:write name="siteId"/>/images/icons/ps2_med.png">
													</li>
													<li>
														<span class="checkboxWrapper"><html:checkbox property="ps3"
																styleClass="checkbox playStation" onclick="psCheck(this)"
																styleId="ps3CB" 
																value="0.RegistrationWrapper.15.1.6.1.1.1.3.0.1.11" />
														</span>
														<img src="WebContent/<bean:write name="siteId"/>/images/icons/ps3_med.png">
													</li>
													<li>
														<html:text styleId="psNetTag" property="psNetTag" onblur="isPSTagAvailable()" />
														<label id="psAvailableMsg" for="psNetTag" class="error"></label>
														<!--br<div id="psAvailableMsg" class="error"></div-->
													</li>
												</ul>
											</td>
										</tr>
										<tr>
											<th>
												<br>
											</th>
											<td>

												<p class="note">
													In order for WorldGaming.com to track your WorldGaming results, you must enter your XBOX-LIVE GamerTag and/or
													Sony Network ID above. You will not be able to initiate or accept challenges on WorldGaming.com without this ID.
												</p>
											</td>
										</tr>
									</tbody>
								</table>
							</fieldset>
							<fieldset id="gameSelection">
							<legend>
								Select your games
							</legend>
							<table id="GamePrefs">
								<caption>
									In order for other members to identify you and your gaming preferences, please provide the following information. you
										can always login and edit your settings once your registration is complete.
									</caption>
									<!-- To be discussed -->
									<tbody>
										<tr id="PS2">
											<th>
												<h2>
													PS2
												</h2>
											</th>
											<td>
												<div class="clearfix">
													<div class="Format1 clearfix floatLeft grid6">
														<span><input name="Madden NFL 2007" value="0.RegistrationWrapper.15.1.6.1.1.1.3.0.1.17.0.1.3.0.1"
																type="checkbox"> Madden NFL 2007 </span>
													</div>
													<div class="Format1 clearfix floatLeft grid6">
														<span><input name="NBA Live 2007"
																value="0.RegistrationWrapper.15.1.6.1.1.1.3.0.1.17.0.1.3.1.1"
																type="checkbox"> NBA Live 2007 </span>
													</div>
													<div class="Format1 clearfix floatLeft grid6">
														<span><input name="NHL 2007" value="0.RegistrationWrapper.15.1.6.1.1.1.3.0.1.17.0.1.3.2.1"
																type="checkbox"> NHL 2007 </span>
													</div>
													<div class="Format1 clearfix floatLeft grid6">
														<span><input name="Nascar 2008" value="0.RegistrationWrapper.15.1.6.1.1.1.3.0.1.17.0.1.3.3.1"
																type="checkbox"> Nascar 2008 </span>
													</div>
													<div class="Format1 clearfix floatLeft grid6">
														<span><input name="NCAA 2008" value="0.RegistrationWrapper.15.1.6.1.1.1.3.0.1.17.0.1.3.4.1"
																type="checkbox"> NCAA 2008 </span>
													</div>
													<div class="Format1 clearfix floatLeft grid6">
														<span><input name="NCAA 2007" value="0.RegistrationWrapper.15.1.6.1.1.1.3.0.1.17.0.1.3.5.1"
																type="checkbox"> NCAA 2007 </span>
													</div>
													<div class="Format1 clearfix floatLeft grid6">
														<span><input name="Madden NFL 2008"
																value="0.RegistrationWrapper.15.1.6.1.1.1.3.0.1.17.0.1.3.6.1"
																type="checkbox"> Madden NFL 2008 </span>
													</div>
													<div class="Format1 clearfix floatLeft grid6">
														<span><input name="FIFA 2008"
																value="0.RegistrationWrapper.15.1.6.1.1.1.3.0.1.17.0.1.3.7.1"
																type="checkbox"> FIFA 2008 </span>
													</div>
													<div class="Format1 clearfix floatLeft grid6">
														<span><input name="NCAA March Madness 2007"
																value="0.RegistrationWrapper.15.1.6.1.1.1.3.0.1.17.0.1.3.8.1"
																type="checkbox"> NCAA March Madness 2007 </span>
													</div>
													<div class="Format1 clearfix floatLeft grid6">
														<span><input name="NBA Live 2008"
																value="0.RegistrationWrapper.15.1.6.1.1.1.3.0.1.17.0.1.3.9.1"
																type="checkbox"> NBA Live 2008 </span>
													</div>
													<div class="Format1 clearfix floatLeft grid6">
														<span><input name="NHL 2008"
																value="0.RegistrationWrapper.15.1.6.1.1.1.3.0.1.17.0.1.3.10.1"
																type="checkbox"> NHL 2008 </span>
													</div>
												</div>
											</td>
										</tr>
										<tr id="PS3">
											<th>
												<h2>
													PS3
												</h2>
											</th>
											<td>
												<div class="clearfix">
													<div class="Format1 clearfix floatLeft grid6">
														<span><input name="Madden NFL 2007"
																value="0.RegistrationWrapper.15.1.6.1.1.1.3.0.1.17.1.1.3.0.1"
																type="checkbox"> Madden NFL 2007 </span>
													</div>
													<div class="Format1 clearfix floatLeft grid6">
														<span><input name="NBA Live 2007"
																value="0.RegistrationWrapper.15.1.6.1.1.1.3.0.1.17.1.1.3.1.1"
																type="checkbox"> NBA Live 2007 </span>
													</div>
													<div class="Format1 clearfix floatLeft grid6">
														<span><input name="Fight Night Round 3"
																value="0.RegistrationWrapper.15.1.6.1.1.1.3.0.1.17.1.1.3.2.1"
																type="checkbox"> Fight Night Round 3 </span>
													</div>
													<div class="Format1 clearfix floatLeft grid6">
														<span><input name="Tiger Woods 07"
																value="0.RegistrationWrapper.15.1.6.1.1.1.3.0.1.17.1.1.3.3.1"
																type="checkbox"> Tiger Woods 07 </span>
													</div>
													<div class="Format1 clearfix floatLeft grid6">
														<span><input name="NBA Street Home Court"
																value="0.RegistrationWrapper.15.1.6.1.1.1.3.0.1.17.1.1.3.4.1"
																type="checkbox"> NBA Street Home Court </span>
													</div>
													<div class="Format1 clearfix floatLeft grid6">
														<span><input name="Nascar 2008"
																value="0.RegistrationWrapper.15.1.6.1.1.1.3.0.1.17.1.1.3.5.1"
																type="checkbox"> Nascar 2008 </span>
													</div>
													<div class="Format1 clearfix floatLeft grid6">
														<span><input name="NCAA 2008"
																value="0.RegistrationWrapper.15.1.6.1.1.1.3.0.1.17.1.1.3.6.1"
																type="checkbox"> NCAA 2008 </span>
													</div>
													<div class="Format1 clearfix floatLeft grid6">
														<span><input name="Madden NFL 2008"
																value="0.RegistrationWrapper.15.1.6.1.1.1.3.0.1.17.1.1.3.7.1"
																type="checkbox"> Madden NFL 2008 </span>
													</div>
													<div class="Format1 clearfix floatLeft grid6">
														<span><input name="FIFA 2008"
																value="0.RegistrationWrapper.15.1.6.1.1.1.3.0.1.17.1.1.3.8.1"
																type="checkbox"> FIFA 2008 </span>
													</div>
													<div class="Format1 clearfix floatLeft grid6">
														<span><input name="NBA Live 2008"
																value="0.RegistrationWrapper.15.1.6.1.1.1.3.0.1.17.1.1.3.9.1"
																type="checkbox"> NBA Live 2008 </span>
													</div>
													<div class="Format1 clearfix floatLeft grid6">
														<span><input name="NHL 2008"
																value="0.RegistrationWrapper.15.1.6.1.1.1.3.0.1.17.1.1.3.10.1"
																type="checkbox"> NHL 2008 </span>
													</div>
												</div>
											</td>
										</tr>
										<tr id="XBox">
											<th>
												<h2>
													XBox
												</h2>
											</th>
											<td>
												<div class="clearfix">
													<div class="Format1 clearfix floatLeft grid6">
														<span><input name="Halo 2"
																value="0.RegistrationWrapper.15.1.6.1.1.1.3.0.1.17.2.1.3.0.1"
																type="checkbox"> Halo 2 </span>
													</div>
													<div class="Format1 clearfix floatLeft grid6">
														<span><input name="Madden NFL 2007"
																value="0.RegistrationWrapper.15.1.6.1.1.1.3.0.1.17.2.1.3.1.1"
																type="checkbox"> Madden NFL 2007 </span>
													</div>
													<div class="Format1 clearfix floatLeft grid6">
														<span><input name="NBA Live 2007"
																value="0.RegistrationWrapper.15.1.6.1.1.1.3.0.1.17.2.1.3.2.1"
																type="checkbox"> NBA Live 2007 </span>
													</div>
													<div class="Format1 clearfix floatLeft grid6">
														<span><input name="NHL 2007"
																value="0.RegistrationWrapper.15.1.6.1.1.1.3.0.1.17.2.1.3.3.1"
																type="checkbox"> NHL 2007 </span>
													</div>
													<div class="Format1 clearfix floatLeft grid6">
														<span><input name="NCAA 2008"
																value="0.RegistrationWrapper.15.1.6.1.1.1.3.0.1.17.2.1.3.4.1"
																type="checkbox"> NCAA 2008 </span>
													</div>
													<div class="Format1 clearfix floatLeft grid6">
														<span><input name="NCAA 2007"
																value="0.RegistrationWrapper.15.1.6.1.1.1.3.0.1.17.2.1.3.5.1"
																type="checkbox"> NCAA 2007 </span>
													</div>
													<div class="Format1 clearfix floatLeft grid6">
														<span><input name="Madden NFL 2008"
																value="0.RegistrationWrapper.15.1.6.1.1.1.3.0.1.17.2.1.3.6.1"
																type="checkbox"> Madden NFL 2008 </span>
													</div>
												</div>
											</td>
										</tr>
										<tr id="XBox360">
											<th>
												<h2>
													XBox 360
												</h2>
											</th>
											<td>
												<div class="clearfix">
													<div class="Format1 clearfix floatLeft grid6">
														<span><input name="Madden NFL 2007"
																value="0.RegistrationWrapper.15.1.6.1.1.1.3.0.1.17.3.1.3.0.1"
																type="checkbox"> Madden NFL 2007 </span>
													</div>
													<div class="Format1 clearfix floatLeft grid6">
														<span><input name="NBA Live 2007"
																value="0.RegistrationWrapper.15.1.6.1.1.1.3.0.1.17.3.1.3.1.1"
																type="checkbox"> NBA Live 2007 </span>
													</div>
													<div class="Format1 clearfix floatLeft grid6">
														<span><input name="NHL 2007"
																value="0.RegistrationWrapper.15.1.6.1.1.1.3.0.1.17.3.1.3.2.1"
																type="checkbox"> NHL 2007 </span>
													</div>
													<div class="Format1 clearfix floatLeft grid6">
														<span><input name="NBA Street Home Court"
																value="0.RegistrationWrapper.15.1.6.1.1.1.3.0.1.17.3.1.3.3.1"
																type="checkbox"> NBA Street Home Court </span>
													</div>
													<div class="Format1 clearfix floatLeft grid6">
														<span><input name="FIFA 2007"
																value="0.RegistrationWrapper.15.1.6.1.1.1.3.0.1.17.3.1.3.4.1"
																type="checkbox"> FIFA 2007 </span>
													</div>
													<div class="Format1 clearfix floatLeft grid6">
														<span><input name="Nascar 2008"
																value="0.RegistrationWrapper.15.1.6.1.1.1.3.0.1.17.3.1.3.5.1"
																type="checkbox"> Nascar 2008 </span>
													</div>
													<div class="Format1 clearfix floatLeft grid6">
														<span><input name="NCAA 2008"
																value="0.RegistrationWrapper.15.1.6.1.1.1.3.0.1.17.3.1.3.6.1"
																type="checkbox"> NCAA 2008 </span>
													</div>
													<div class="Format1 clearfix floatLeft grid6">
														<span><input name="NCAA 2007"
																value="0.RegistrationWrapper.15.1.6.1.1.1.3.0.1.17.3.1.3.7.1"
																type="checkbox"> NCAA 2007 </span>
													</div>
													<div class="Format1 clearfix floatLeft grid6">
														<span><input name="Madden NFL 2008"
																value="0.RegistrationWrapper.15.1.6.1.1.1.3.0.1.17.3.1.3.8.1"
																type="checkbox"> Madden NFL 2008 </span>
													</div>
													<div class="Format1 clearfix floatLeft grid6">
														<span><input name="Halo 3"
																value="0.RegistrationWrapper.15.1.6.1.1.1.3.0.1.17.3.1.3.9.1"
																type="checkbox"> Halo 3 </span>
													</div>
													<div class="Format1 clearfix floatLeft grid6">
														<span><input name="NHL 2008"
																value="0.RegistrationWrapper.15.1.6.1.1.1.3.0.1.17.3.1.3.10.1"
																type="checkbox"> NHL 2008 </span>
													</div>
													<div class="Format1 clearfix floatLeft grid6">
														<span><input name="FIFA 2008"
																value="0.RegistrationWrapper.15.1.6.1.1.1.3.0.1.17.3.1.3.11.1"
																type="checkbox"> FIFA 2008 </span>
													</div>
													<div class="Format1 clearfix floatLeft grid6">
														<span><input name="NCAA March Madness 2007"
																value="0.RegistrationWrapper.15.1.6.1.1.1.3.0.1.17.3.1.3.12.1"
																type="checkbox"> NCAA March Madness 2007 </span>
													</div>
													<div class="Format1 clearfix floatLeft grid6">
														<span><input name="NBA Live 2008"
																value="0.RegistrationWrapper.15.1.6.1.1.1.3.0.1.17.3.1.3.13.1"
																type="checkbox"> NBA Live 2008 </span>
													</div>
												</div>
											</td>
										</tr>
									</tbody><!-- To be discussed -->
								</table>
								<!-- To be discussed -->
								<ul class="avatarList">
									<li>
										<input value="WebContent/<bean:write name="siteId"/>/images/avatar/AVTR_1.jpg" name="avatarButton"
											type="radio">
										<img src="WebContent/<bean:write name="siteId"/>/images/avatar/AVTR_1.jpg" height="65" width="65">
									</li>
									<li>
										<input value="WebContent/<bean:write name="siteId"/>/images/avatar/AVTR_2.jpg" name="avatarButton"
											type="radio">
										<img src="WebContent/<bean:write name="siteId"/>/images/avatar/AVTR_2.jpg" height="65" width="65">
									</li>
									<li>
										<input value="WebContent/<bean:write name="siteId"/>/images/avatar/AVTR_3.jpg" name="avatarButton"
											checked="checked" type="radio">
										<img src="WebContent/<bean:write name="siteId"/>/images/avatar/AVTR_3.jpg" height="65" width="65">
									</li>
									<li>
										<input value="WebContent/<bean:write name="siteId"/>/images/avatar/AVTR_4.jpg" name="avatarButton"
											type="radio">
										<img src="WebContent/<bean:write name="siteId"/>/images/avatar/AVTR_4.jpg" height="65" width="65">
									</li>
								</ul>
								<!-- To be discussed -->
							</fieldset>


						<fieldset>
								<legend>
									What's your Message?
								</legend>
								<table id="MessagePrefs">
									<caption>
										Use this messsage to dare others to challenge you. max 100
										characters
									</caption>
									<tbody>
										<tr>
											<th>
												<h2>
													Your Message
												</h2>
											</th>
											<td class="clearfix">
												<html:textarea property="yourMessage" styleId="profileHeadline" />
												<label for="profileHeadline" generated="false" class="error"></label>
											</td>
										</tr>
									</tbody>
								</table>
								<table id="MessagePrefs">
									<caption>
										Tell on yourself
									</caption>
									<tbody>
										<tr>
											<th>
												<h2>
													Your Story
												</h2>
											</th>
											<td class="clearfix">
												<html:textarea styleId="aboutMe" property="yourStory"  />
											</td>
										</tr>
									</tbody>
								</table>
							</fieldset>

<fieldset>
								<legend>
									What is your availability to play?
								</legend>
								<table id="AvailabilityPrefs">
									<caption>
										Use this to describe you're availability to play
									</caption>
									<tbody>
										<tr>
											<th>
												<h2>
													Availability
												</h2>
											</th>
											<td class="clearfix">
												<html:textarea property="availability" styleId="availability"  />
											</td>
										</tr>
									</tbody>
								</table>
							</fieldset>
							<fieldset>
								<legend>
									Average Amount to Play for
								</legend>
								<table id="PlayFor">
									<caption>
										Set the average amount you like to wager on a game. This gives
										everybody a way to know how good you think you are!
									</caption>
									<tbody>
										<tr>
											<th>
												<h2>
													Play For $:
												</h2>
											</th>
											<td>
												<html:text property="playfor$" size="8" maxlength="8" value="$25"
													 />
											</td>
										</tr>
									</tbody>
								</table>
							</fieldset>
							<fieldset>
								<legend>
									Contact Information and Contact Prefrences
								</legend>
								<table id="AvailabilityPrefs">
									<caption>
										You can choose to make the following information available
									</caption>
									<tbody>
										<tr>
											<th>
												<h2>
													Contact Info
												</h2>
											</th>
											<td class="clearfix">
												<ul>
													<li>
														<html:text property="skype" />
														Skype
													</li>
													<li>
														<html:text property="aim" />
														AIM
													</li>
													<li>
														<html:text property="msn" />
														MSN
													</li>
													<li>
														<html:text property="yahoo" />
														Yahoo
													</li>
													<li>
														<html:text property="phone" />
														Phone
													</li>
												</ul>
											</td>
										</tr>
										<tr>
											<th>
												<h2>
													Preferences
												</h2>
											</th>
											<td class="clearfix">
												<ul>
													<li>
														Let us know if we can contact you with any of the
														following
													</li>
													<li>
														<html:checkbox property="contactMeAbtWorldGamingEvents"
															value="true" />
														Contact me about Worldgaming events and other info
													</li>
													<li>
														<html:checkbox property="emailMySystemAlerts"
															value="true" />
														Email my system alerts
													</li>
													<li>
														<html:checkbox property="showMyWinLosspublically"
															value="true" />
														Show my win/loss publicly
													</li>
												</ul>
											</td>
										</tr>
										<tr>
											<th>
												<h2>
													Proceed
												</h2>
											</th>
											<td class="buttons">
												<html:submit styleClass="green" value="Review"
													property="operation" />
											</td>
										</tr>
									</tbody>
								</table>
							</fieldset>
						</div>
					</html:form>
					<script type="text/javascript" src="wg/lib/jquery.corner.js"> </script>
					<!-- PageContent -->
				</div>
			</div>
			<div id="Footer">
				<div id="footerNav">
					<ul>
						<li id="fNavHome">
							<span><a href="HomePage.jsp">HOME</a> </span>
						</li>
						<li id="fNavAbout">
							<span><a href="About.jsp">About World Gaming</a> </span>
						</li>
						<li id="fNavMoney">
							<span><a href="PlayForMoney.jsp">Play for money</a> </span>
						</li>
						<li>
							<br>
						</li>
						<li id="fNavBlog">
							<span><a href="/wordpress/">Blog</a> </span>
						</li>
						<li id="fNavForum">
							<span><a href="/wgforum/">Forum</a> </span>
						</li>
						<li id="fNavFAQs">
							<span><a href="FAQ.jsp">Help/FAQs</a> </span>
						</li>
						<li id="fNavContant">
							<span><a href="Contact.jsp">Contact</a> </span>
						</li>
					</ul>
				</div>
			</div>
			<div id="copyright">
				<center>
					<span style="white-space: nowrap;">©2007 G4G Gaming, LLC®.</span>
				</center>
			</div>
		</div>
	</body>
</html:html>
