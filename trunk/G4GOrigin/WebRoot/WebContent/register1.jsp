
<%@page import="com.g4g.dto.SubNationalCodeDTO"%>
<%@page import="java.util.Calendar"%>
<%@page import="com.g4g.utils.DataUtil"%>
<%@page import="java.util.GregorianCalendar"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>

<bean:define id="siteId" value="wg" type="java.lang.String"/>
 <logic:present name="siteId" >
  <bean:define id="siteId" name="siteId" type="java.lang.String"/>
</logic:present>
<script type="text/javascript" src="WebContent/<bean:write name="siteId"/>/js/AJAXScript.js">Generic</script>
<script type="text/javascript" src="WebContent/<bean:write name="siteId"/>/js/Calendar.js">Date</script>
<script type="text/javascript" src="WebContent/<bean:write name="siteId"/>/lib/FormValid.js"> </script>
<script type="text/javascript" src="WebContent/<bean:write name="siteId"/>/js/wg.validate.js"> </script>
<script type="text/javascript" src="WebContent/<bean:write name="siteId"/>/js/date.js"> </script>
<script type="text/javascript" src="WebContent/<bean:write name="siteId"/>/lib/jquery.corner.js"> </script>

<script type="text/javascript" charset="utf-8">
$(document).ready(function() {
if($("#birthDate").get(0) != null) {
if($("#birthDate").get(0).value != "") {
registrationDateDefault();
}
}
var avatarSelected = false;
});
</script>


<div id="pageTitle">
	<img src="WebContent/<bean:write name="siteId"/>/css/images/banners/banners-registration.jpg" width="920" height="41" alt="Registration">
</div>

<div id="sidebar" class="registration">
	<div id="sidebarStepsTitle">
		<h3>4 Easy Steps!</h3>
	</div>
	<div id="sidebarSteps">
		<p class="on">Enter Your Login Info</p>
		<p class="off">Pick Your Games</p>
		<p class="off">Review</p>
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

<h1>Enter Your Login Info</h1>
<html:errors property="error" />
<p>As part of the registration process we will walk you through the steps necessary for WorldGaming Membership. Please fill out all required fields (&bull;).</p>


<html:form method="get" action="/register1">

<bean:define id="register1Form" name="registerForm" scope="request" type="com.g4g.forms.Register1Form"  />

<table width="100%">
	<tr>
		<td width="40%"><b>WorldGaming.com Screen Name</b></td>
		<td width="60%">
			<span id="availableMsg" style="float: right"></span>
			<html:hidden property="id" />
			<html:hidden property="userId" />
			<html:text property="screenName" styleId="screenName" onblur="FormValid.isNameAvailable()" maxlength="250"></html:text>
			 &bull;<br>
			<label for="screenName" class="error"></label>
			<html:errors property="screenName" />
		</td>
	</tr>
	<tr>
		<td><b>Enter Email</b></td>
		<td>
			<span id="emailAvailableMsg" style="float: right"></span>
			<html:text property="emailOne" maxlength="250" onblur="FormValid.isLoginAvailable();" styleId="emailOne"></html:text>
			 &bull;<br>
			<label for="emailOne" class="error"></label>
			<html:errors property="emailOne" />
		</td>
	</tr>
	<tr>
		<td><b>Confirm Email</b></td>
		<td>
			<html:text property="emailTwo" styleId="emailTwo" maxlength="250"></html:text>
			<html:errors property="emailTwo" />
			 &bull;<br>
			<label for="emailTwo" class="error"></label>
		</td>
	</tr>
	<tr>
		<td>
			<b>Enter Password</b> (at least 6 characters)
		</td>
		<td>
			<html:password property="passwordOne" styleId="passwordOne" maxlength="250"></html:password>
			 &bull;<br>
			<html:errors property="passwordOne" />
			<label for="passwordOne" class="error"></label>
		</td>
	</tr>
	<tr>
		<td>
			<b>Confirm Password</b>
		</td>
		<td>
			<html:password property="passwordTwo" styleId="passwordTwo" maxlength="250"></html:password>
			<html:errors property="passwordTwo" />
			 &bull;<br>
			<label for="passwordTwo" class="error"></label>
		</td>
	</tr>
	<tr>
		<td>
			<b>First Name</b>
		</td>
		<td>
			<html:text property="firstName" maxlength="20"></html:text>
			 &bull;<br>
			<html:errors property="firstName" />
			<label for="firstName" class="error"></label>
		</td>
	</tr>
	<tr>
		<td>
			<b>Last Name</b>
		</td>
		<td>
			<html:text property="lastName" maxlength="20"></html:text>
			 &bull;<br>
			<html:errors property="lastName" />
			<label for="lastName" class="error"></label>
		</td>
	</tr>
	<tr>
		<td>
			<b>Date of Birth</b>
		</td>
		<td>
			<html:errors property="birthDate" />
			<html:select property="month" styleId="month">
			<html:option value=""></html:option>
			<html:option value="01">Jan</html:option>
			<html:option value="02">Feb</html:option>
			<html:option value="03">Mar</html:option>
			<html:option value="04">Apr</html:option>
			<html:option value="05">May</html:option>
			<html:option value="06">Jun</html:option>
			<html:option value="07">Jul</html:option>
			<html:option value="08">Aug</html:option>
			<html:option value="09">Sep</html:option>
			<html:option value="10">Oct</html:option>
			<html:option value="11">Nov</html:option>
			<html:option value="12">Dec</html:option>
			</html:select>


			<html:select property="day" styleId="day">
			<html:option value=""></html:option>
			<html:option value="01"></html:option>
			<html:option value="02"></html:option>
			<html:option value="03"></html:option>
			<html:option value="04"></html:option>
			<html:option value="05"></html:option>
			<html:option value="06"></html:option>
			<html:option value="07"></html:option>
			<html:option value="08"></html:option>
			<html:option value="09"></html:option>
			<html:option value="10"></html:option>
			<html:option value="11"></html:option>
			<html:option value="12"></html:option>
			<html:option value="13"></html:option>
			<html:option value="14"></html:option>
			<html:option value="15"></html:option>
			<html:option value="16"></html:option>
			<html:option value="17"></html:option>
			<html:option value="18"></html:option>
			<html:option value="19"></html:option>
			<html:option value="20"></html:option>
			<html:option value="21"></html:option>
			<html:option value="22"></html:option>
			<html:option value="23"></html:option>
			<html:option value="24"></html:option>
			<html:option value="25"></html:option>
			<html:option value="26"></html:option>
			<html:option value="27"></html:option>
			<html:option value="28"></html:option>
			<html:option value="29"></html:option>
			<html:option value="30"></html:option>
			<html:option value="31"></html:option>
			</html:select>

			<html:select property="year" styleId="year">
				<html:option value=""></html:option>

		<%
			Calendar cal = new GregorianCalendar();
			int year = cal.get(Calendar.YEAR) - 18;
			int endYear = year - 70;
			for(int index = year; index > endYear; index--) {
				out.print("<option value=\"" + index +"\">" + index + "</option>");
			}
		%>

			</html:select>
			 &bull;<br>
			<html:errors property="year"/>

		</td>
	</tr>

	<tr>
		<td><b>Where do you live?</b></td>
		<td>
			<html:select property="nationCodes" onkeyup="retrieveSubNationCode(this.value,'states')" onclick="retrieveSubNationCode(this.value,'states')" onchange="retrieveSubNationCode(this.value,'states')" >
			<html:option value="" >Select Country</html:option>
			<html:options property="key" labelProperty="value" collection="contryList" /></html:select>
			 &bull;<br>
			<html:errors property="nationCodes" />
		</td>
	</tr>
	<tr>
		<td align="right">Street 1</td>
		<td>
			<html:text property="street1" maxlength="250"></html:text>
			<label for="street1" class="error"></label>
			 &bull;<br>
			<html:errors property="street1" />
		</td>
	</tr>
	<tr>
		<td align="right">Street 2</td>
		<td>
			<html:text property="street2" maxlength="250"></html:text>
			<html:errors property="street2" />
		</td>
	</tr>
	<tr>
		<td align="right">City</td>
		<td>
			<html:text property="cityField" maxlength="250"></html:text>
			<label for="cityField" class="error"></label>
			 &bull;<br>
			<html:errors property="cityField" />
		</td>
	</tr>
	<tr>
		<td align="right">State/Province</td>
		<td>
			<html:select property="states" styleId="states" onchange="javascript:setSubNatOnAddress(this);" value="<%=register1Form.getStates()%>">
				<logic:notEmpty name="statesList">
					<bean:define id="statesList" name="statesList" type="java.util.List<SubNationalCodeDTO>" />
					<html:options collection="statesList" property="id"  labelProperty="nationname" labelName="nationname" ></html:options>
				</logic:notEmpty >
				<logic:empty name="statesList">
					<option value="WONoSelectionString">Select State</option>
				</logic:empty>
			</html:select>
			 &bull;<br>
			<html:errors property="states" />
		</td>
	</tr>
	<tr>
		<td align="right">Postal Code</td>
		<td>
			<html:text property="billingZip" maxlength="250"></html:text>
			 &bull;<br>
			<label for="billingZip" class="error"></label>
			<html:errors property="billingZip" />
		</td>
	</tr>
	<tr>
		<td><b>Phone</b></td>
		<td>
			<html:text property="phone" maxlength="255" />
			 &bull;<br>
			<html:errors property="phone"/>
		</td>
	</tr>
	<tr>
		<td>
			<b>Gender</b>
		</td>
		<td>
			<html:radio property="gender" value="M">&nbsp;Male</html:radio>
			<label for="gender"class="error"></label>
			&nbsp;&nbsp;
			<html:radio property="gender" value="F">&nbsp;Female</html:radio>
			<html:errors property="gender" />
		</td>
	</tr>
	<tr>
		<td>&nbsp;</td>
		<td>
			<br><html:submit property="operation" styleClass="green" value="Next"></html:submit>
		</td>
	</tr>
</table>
</html:form>
</div>
