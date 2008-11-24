
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles"%>

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
	<link rel="stylesheet" media="screen" href="WebContent/<bean:write name="siteId"/>/css/cfab.css">
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
	<script type="text/javascript" src="WebContent/<bean:write name="siteId"/>/js/ajaxfileupload.js">Generic</script>
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
					<span><a href="HomePage.jsp">HOME</a>
					</span>
				</li>
				<li id="gNavAbout">
					<span><a href="About.jsp">About World Gaming</a>
					</span>
				</li>
				<li id="gNavMessage">
					<span><a href="PlayForMoney.jsp">Playfor money</a>
					</span>
				</li>
				<li>
					<br>
				</li>
				<li id="gNavBlog">
					<span><a href="/wordpress/">Blog</a>
					</span>
				</li>
				<li id="gNavForum">
					<span><a href="/wgforum/">Forum</a>
					</span>
				</li>
				<li id="gNavFAQs">
					<span><a href="FAQ.jsp">Help/FAQs</a>
					</span>
				</li>
				<li id="gNavContact">
					<span><a href="Contact.jsp">Contact</a>
					</span>
				</li>
			</ul>
		</div>

		<div class="ContentWrapper clearfix" id="Registration">

			<div class="AreaWrapperTitle">
				<img src="WebContent/<bean:write name="siteId"/>/css/images/banners/banners-registration.jpg">
			</div>


			<div class="Sidebar">
				<dl id="regProcess">
					<dt>
						4 Easy Steps!
					</dt>
					<dd class="on">
						<img src="WebContent/<bean:write name="siteId"/>/css/images/icons/Registration-check.jpg">
						Enter your Login Info
					</dd>
					<dd class="opacity">
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
				<script type="text/javascript" src="WebContent/<bean:write name="siteId"/>/js/wg.validate.js"> </script>
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
							<a href="#page1" class="selected"><span>Login Info</span>
							</a>
							<br>
							<br>
							<br>
							<br>
							<br>
							<br>
						</li>
						<li>
							<a href="#page2"><span>Member Preferences</span>
							</a>
							<br>
							<br>
							<br>
							<br>
							<br>
							<br>
						</li>
						<li>
							<a href="#page3"><span>Review</span>
							</a>
							<br>
							<br>
							<br>
							<br>
							<br>
							<br>
						</li>
						<li>
							<a href="#page4"><span>Confirmation</span>
							</a>
							<br>
							<br>
							<br>
							<br>
							<br>
							<br>
						</li>
					</ul>
				</div>

















				<html:form method="post" action="register1">
					<div>
						<fieldset>
							<legend>
								WG Login Info
							</legend>


							<table id="MoveMoney">
								<caption>
									As part of the registration process we will walk you through
									the steps necessary for WorldGaming Membership. Please fill out
									all required fields (*).
								</caption>
								<tbody>
									<tr>
										<th>
											<h2>
												WorldGaming.com Screen Name
											</h2>
										</th>
										<td>
											<em>*</em>
											<html:hidden property="id" />
											<html:hidden property="userId" />

											<html:text property="screenName"
												onblur="FormValid.isNameAvailable()"></html:text>
											<label for="screenName" generated="false" class="error"></label>
											<html:errors property="screenName" />
											<br>
											<br>
										</td>
									</tr>
									<tr>
										<th>
											<h2>
												Enter Email
											</h2>
										</th>
										<td>
											<ul>
												<li>
													<em>*</em>
													<html:text property="emailOne"
														onblur="FormValid.isLoginAvailable();"></html:text>
													<label for="emailOne" generated="false" class="error"></label>
													<html:errors property="emailOne" />
												</li>
												<li>
													<em>*</em>
													<html:text property="emailTwo"></html:text>
													<html:errors property="emailTwo" />
													<label for="emailTwo" generated="false" class="error"></label>
												</li>
											</ul>
										</td>
									</tr>
									<tr>
										<th>
											<h2>
												Enter Password
											</h2>
										</th>
										<td>
											<ul>
												<li>
													<em>*</em>
													<html:password property="passwordOne"></html:password>
													<html:errors property="passwordOne" />
													<label for="passwordOne" generated="false" class="error"></label>
												</li>
												<li>
													<em>*</em>
													<html:password property="passwordTwo"></html:password>
													<html:errors property="passwordTwo" />
													<label for="passwordTwo" generated="false" class="error"></label>
												</li>
											</ul>
										</td>
									</tr>
									<tr>
										<th>
											<h2>
												Name
											</h2>
										</th>
										<td class="clearfix">
											<html:text property="firstName"></html:text>
											<html:errors property="firstName" />
											<label for="firstName" generated="false" class="error"></label>
											&nbsp;

											<html:text property="middleName"></html:text>
											&nbsp;*&nbsp;
											<html:text property="lastName"></html:text>
											<html:errors property="lastName" />
											<label for="lastName" generated="false" class="error"></label>
										</td>
									</tr>















									<tr>
										<th>
											<h2>
												D.O.B.
												<br>
												(mm - dd - yyyy)
											</h2>
										</th>
										<td class="clearfix">
											<em>* <html:errors property="birthDate" /> <html:text
													property="birthDate" style="display: none;"></html:text> </em>
											<select id="MoSel" name="MoSel"
												onchange="MonLen(YrSel, MoSel, DySel);birthDate.value=DropReadYMD(YrSel, MoSel, DySel);">
												<option value="WONoSelectionString"></option>
												<option value="1">
													Jan
												</option>
												<option value="2">
													Feb
												</option>
												<option value="3">
													Mar
												</option>
												<option value="4">
													Apr
												</option>
												<option value="5">
													May
												</option>
												<option value="6">
													Jun
												</option>
												<option value="7">
													Jul
												</option>
												<option value="8">
													Aug
												</option>
												<option value="9">
													Sep
												</option>
												<option value="10">
													Oct
												</option>
												<option value="11">
													Nov
												</option>
												<option value="12">
													Dec
												</option>
											</select>


											<select name="DySel" id="DySel"
												onchange="birthDate.value=DropReadYMD(YrSel, MoSel, DySel);">
												<option></option>
												<option>
													1
												</option>
												<option>
													2
												</option>
												<option>
													3
												</option>
												<option>
													4
												</option>
												<option>
													5
												</option>
												<option>
													6
												</option>
												<option>
													7
												</option>
												<option>
													8
												</option>
												<option>
													9
												</option>
												<option>
													10
												</option>
												<option>
													11
												</option>
												<option>
													12
												</option>
												<option>
													13
												</option>
												<option>
													14
												</option>
												<option>
													15
												</option>
												<option>
													16
												</option>
												<option>
													17
												</option>
												<option>
													18
												</option>
												<option>
													19
												</option>
												<option>
													20
												</option>
												<option>
													21
												</option>
												<option>
													22
												</option>
												<option>
													23
												</option>
												<option>
													24
												</option>
												<option>
													25
												</option>
												<option>
													26
												</option>
												<option>
													27
												</option>
												<option>
													28
												</option>
												<option>
													29
												</option>
												<option>
													30
												</option>
												<option>
													31
												</option>
											</select>
											<select name="YrSel" id="YrSel"
												onchange="MonLen(YrSel, MoSel, DySel);birthDate.value=DropReadYMD(YrSel, MoSel, DySel);">
												<option></option>
												<option>
													1989
												</option>
												<option>
													1988
												</option>
												<option>
													1987
												</option>
												<option>
													1986
												</option>
												<option>
													1985
												</option>
												<option>
													1984
												</option>
												<option>
													1983
												</option>
												<option>
													1982
												</option>
												<option>
													1981
												</option>
												<option>
													1980
												</option>
												<option>
													1979
												</option>
												<option>
													1978
												</option>
												<option>
													1977
												</option>
												<option>
													1976
												</option>
												<option>
													1975
												</option>
												<option>
													1974
												</option>
												<option>
													1973
												</option>
												<option>
													1972
												</option>
												<option>
													1971
												</option>
												<option>
													1970
												</option>
												<option>
													1969
												</option>
												<option>
													1968
												</option>
												<option>
													1967
												</option>
												<option>
													1966
												</option>
												<option>
													1965
												</option>
												<option>
													1964
												</option>
												<option>
													1963
												</option>
												<option>
													1962
												</option>
												<option>
													1961
												</option>
												<option>
													1960
												</option>
												<option>
													1959
												</option>
												<option>
													1958
												</option>
												<option>
													1957
												</option>
												<option>
													1956
												</option>
												<option>
													1955
												</option>
												<option>
													1954
												</option>
												<option>
													1953
												</option>
												<option>
													1952
												</option>
												<option>
													1951
												</option>
												<option>
													1950
												</option>
												<option>
													1949
												</option>
												<option>
													1948
												</option>
												<option>
													1947
												</option>
												<option>
													1946
												</option>
												<option>
													1945
												</option>
												<option>
													1944
												</option>
												<option>
													1943
												</option>
												<option>
													1942
												</option>
												<option>
													1941
												</option>
												<option>
													1940
												</option>
												<option>
													1939
												</option>
												<option>
													1938
												</option>
												<option>
													1937
												</option>
												<option>
													1936
												</option>
												<option>
													1935
												</option>
												<option>
													1934
												</option>
												<option>
													1933
												</option>
												<option>
													1932
												</option>
												<option>
													1931
												</option>
												<option>
													1930
												</option>
												<option>
													1929
												</option>
												<option>
													1928
												</option>
												<option>
													1927
												</option>
												<option>
													1926
												</option>
												<option>
													1925
												</option>
												<option>
													1924
												</option>
												<option>
													1923
												</option>
												<option>
													1922
												</option>
												<option>
													1921
												</option>
												<option>
													1920
												</option>
											</select>
											<label for="birthDate" generated="false" class="error"></label>
											<br>
											<label for="MoSel" generated="false" class="error"></label>
											&nbsp;
											<label for="DySel" generated="false" class="error"></label>
											&nbsp;
											<label for="YrSel" generated="false" class="error"></label>
										</td>
									</tr>
									<tr>
										<th>
											<h2>
												Where do you live?
											</h2>
										</th>
										<td>
											<ul>
												<li>




													<html:select property="nationCodes"
														onchange="javascript:subNatCodesForNatCode(this);">
														<option value="WONoSelectionString">
															Select your country
														</option>
														<option value="v5749s22f4swd">
															Australia
														</option>
														<option value="v5749s22f4swe">
															Austria
														</option>
														<option value="v5749s22f4swl">
															Belgium
														</option>
														<option value="v5749s22f4sx2">
															Canada
														</option>
														<option value="v5749s22f4sxl">
															Denmark
														</option>
														<option value="v5749s22f4sy0">
															Finland
														</option>
														<option value="v5749s22f4sy1">
															France
														</option>
														<option value="v5749s22f4sy8">
															Germany
														</option>
														<option value="v5749s22f4syj">
															Hong Kong
														</option>
														<option value="v5749s22f4syp">
															Ireland
														</option>
														<option value="v5749s22f4syr">
															Italy
														</option>
														<option value="v5749s22f4sys">
															Japan
														</option>
														<option value="v5749s22f4sz6">
															Luxembourg
														</option>
														<option value="v5749s22f4szr">
															Netherlands
														</option>
														<option value="v5749s22f4szt">
															New Zealand
														</option>
														<option value="v5749s22f4t00">
															Norway
														</option>
														<option value="v5749s22f4t09">
															Portugal
														</option>
														<option value="v5749s22f4t0w">
															Spain
														</option>
														<option value="v5749s22f4t12">
															Sweden
														</option>
														<option value="v5749s22f4t13">
															Switzerland
														</option>
														<option value="v5749s22f4t1l">
															United Kingdom
														</option>
														<option value="v5749s22f4t1m">
															United States
														</option>
													</html:select>
													<label for="nationCodes" generated="false" class="error"></label>
													<html:errors property="nationCodes" />
												</li>






												<li>
													<html:text property="street1"></html:text>
													Street 1
													<label for="street1" generated="false" class="error"></label>
													<html:errors property="street1" />
												</li>
												<li>

													<html:text property="street2"></html:text>
													Street 2
												</li>
												<li>

													<html:text property="cityField"></html:text>
													City
													<label for="cityField" generated="false" class="error"></label>
													<html:errors property="cityField" />
												</li>
												<li>
													<html:select property="states"
														onchange="javascript:setSubNatOnAddress(this);">
														<option value="WONoSelectionString">
															Select State
														</option>
													</html:select>
													<html:errors property="states" />
													State/Province
												</li>
												<li>

													<html:text property="billingZip"></html:text>
													Postal Code
													<label for="billingZip" generated="false" class="error"></label>
													<html:errors property="billingZip" />
												</li>
											</ul>
										</td>
										<td>
											<br>
										</td>
									</tr>
									<tr>
										<th>
											<h2>
												Gender
											</h2>
										</th>
										<td class="clearfix">
											<ul>
												<li>
													<html:radio property="gender" value="M">Male</html:radio>

													<label for="gender" generated="true" class="error"></label>
												</li>
												<li>
													<html:radio property="gender" value="F">Female</html:radio>
													<html:errors property="gender" />
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
											<html:submit property="operation" styleClass="green"
												value="Next"></html:submit>

										</td>
									</tr>
									<!--/tbody-->
								</tbody>
							</table>




						</fieldset>
					</div>
				</html:form>





				<script type="text/javascript"
					src="WebContent/<bean:write name="siteId"/>/lib/jquery.corner.js"> </script>
				<!-- PageContent -->
			</div>
		</div>

		<div id="Footer">
			<div id="footerNav">
				<ul>
					<li id="fNavHome">
						<span><a href="HomePage.jsp">HOME</a>
						</span>
					</li>
					<li id="fNavAbout">
						<span><a href="About.jsp">About World Gaming</a>
						</span>
					</li>
					<li id="fNavMoney">
						<span><a href="PlayForMoney.jsp">Play for money</a>
						</span>
					</li>
					<li>
						<br>
					</li>
					<li id="fNavBlog">
						<span><a href="/wordpress/">Blog</a>
						</span>
					</li>
					<li id="fNavForum">
						<span><a href="/wgforum/">Forum</a>
						</span>
					</li>
					<li id="fNavFAQs">
						<span><a href="FAQ.jsp">Help/FAQs</a>
						</span>
					</li>
					<li id="fNavContant">
						<span><a href="Contact.jsp">Contact</a>
						</span>
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
