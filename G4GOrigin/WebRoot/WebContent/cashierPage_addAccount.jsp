<%@page import="com.g4g.utils.SessionUtil"%>

<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>

<script type="text/javascript">

function displayCardType() {
	if (document.getElementById('accountType').value == 'PayPal') {
		document.getElementById('cardType').style.display = 'none';
		document.getElementById('cardTypeName').value = '';
		document.getElementById('issueNumber').style.display = 'none';
		document.getElementById('issueDate').style.display = 'none';
		document.getElementById('cardInfo').style.display = 'none';
		document.getElementById('billingName').style.display = 'none';
		document.getElementById('billingAddress').style.display = 'none';
	}
	if (document.getElementById('accountType').value == 'Credit/Debit') {
		document.getElementById('cardType').style.display = '';
		document.getElementById('cardTypeName').value = '';
	}
}

function displayIssueInfo() {
	document.getElementById('cardInfo').style.display = '';
	document.getElementById('billingName').style.display = '';
	document.getElementById('billingAddress').style.display = '';
	if (document.getElementById('cardTypeName').value == 'SOLO' || document.getElementById('cardTypeName').value == 'SWITCH') {
		document.getElementById('issueNumber').style.display = '';
		document.getElementById('issueDate').style.display = '';
	} else {
		document.getElementById('issueNumber').style.display = 'none';
		document.getElementById('issueDate').style.display = 'none';
	}
}

</script>

<bean:define id="siteId" value="wg" type="java.lang.String"/>
<logic:present name="siteId" >
<bean:define id="siteId" name="siteId" type="java.lang.String"/>
</logic:present>

<bean:define id="accountId" name="paymentAccountForm" property="accountId"></bean:define>

<div id="pageTitle">
	<img src="WebContent/<bean:write name="siteId"/>/css/images/banners/banner-cashier.jpg" width="920" height="41" alt="Cashier">
</div>

<div id="content" class="registration">

	<div class="SubNav">
		<ul class="idTabs">
		<html:errors/>
			<li><a href="displayCashier.do">Account Balance</a></li>
		</ul>
	</div>
	
	<html:form method="post" styleId="form" action="saveCashierAccount.do">
	<div id="Wallet_page1">
		
		<p><b>Add a New Account</b></p>
		<table id="MyAccounts">
			<tr>
				<td width="25%">
					<b>Account Nickname</b><br>
					<i>eg, 'My VISA'</i>
				</td>
				<td width="75%"><html:text property="accountName" maxlength="255"></html:text><br></td>
			</tr>
			<tr><td colspan="2">&nbsp;</td></tr>
			<tr>
				<td width="25%"><b>Account Type</b></td>
				<td width="75%">
				<% if(Integer.parseInt(accountId.toString()) != 0) { %>
					<html:select property="accountType" styleId="accountType" disabled="true">
						<html:option value="PayPal">PayPal</html:option>
						<html:option value="Credit/Debit">Credit/Debit Card</html:option>
					</html:select>
					
					<input type="hidden" name="accountType" value="<bean:write name="paymentAccountForm" property="accountType" />" />
				<% } else { %>
					<html:select property="accountType" styleId="accountType" onchange="displayCardType();">
						<html:option value="PayPal">PayPal</html:option>
						<html:option value="Credit/Debit">Credit/Debit Card</html:option>
					</html:select>
				<% } %>
				</td>
			</tr>
			<bean:define id="styleValue" value=""></bean:define>
			<logic:notEqual value="Credit/Debit" name="paymentAccountForm" property="accountType"><bean:define id="styleValue" value="display: none;"></bean:define></logic:notEqual>
			
			<tr id="cardType" style="<bean:write name="styleValue" />">
				<td><b>Card Type</b><br></td>
				<td>
					<html:select property="cardTypeName" styleId="cardTypeName" onchange="displayIssueInfo();">
						<html:option value=""></html:option>
						<html:option value="VISA">Visa</html:option>
						<html:option value="MASTERCARD">MasterCard</html:option>
						<html:option value="DISCOVER">Discover</html:option>
						<html:option value="AMEX">Amex</html:option>
						<html:option value="SWITCH">Switch</html:option>
						<html:option value="SOLO">Solo</html:option>
					</html:select>
				</td>
			</tr>
			
			<tr id="cardInfo" style="<bean:write name="styleValue" />" valign="top">
				<td>&nbsp;</td>
				<td>
					<table id="MyAccounts">
						<tr>
							<td>Card Number</td>
							<td><html:text property="cardNumber" styleClass="IntegerValue" size="16" maxlength="16"></html:text></td>
						</tr>
						<bean:define id="issueStyleValue" value="display: none;"></bean:define>
						
						<logic:equal value="SWITCH" name="paymentAccountForm" property="cardTypeName"><bean:define id="issueStyleValue" value="display: block;"></bean:define></logic:equal>
						<logic:equal value="SOLO" name="paymentAccountForm" property="cardTypeName"><bean:define id="issueStyleValue" value="display: block;"></bean:define></logic:equal>
						
						<tr id="issueNumber" style="<bean:write name="issueStyleValue" />">
							<td>Issue Number</td>
							<td><html:text property="issueNumber" styleClass="IntegerValue" size="2" maxlength="2"></html:text><br></td>
						</tr>
						<tr id="issueDate" style="<bean:write name="issueStyleValue" />">
							<td>Issue Date</td>
							<td> 
								<html:select property="startMonth">
									<html:option value="">mo.</html:option>
							        <html:option value="01">01</html:option>
							        <html:option value="02">02</html:option>
							        <html:option value="03">03</html:option>
							        <html:option value="04">04</html:option>
							        <html:option value="05">05</html:option>
							        <html:option value="06">06</html:option>
							        <html:option value="07">07</html:option>
							        <html:option value="08">08</html:option>
							        <html:option value="09">09</html:option>
							        <html:option value="10">10</html:option>
							        <html:option value="11">11</html:option>
							        <html:option value="12">12</html:option>
								</html:select>
								&nbsp;
								<html:select property="startYear">
									<html:option value="">yr.</html:option>
									<html:option value="2005">2005</html:option>
									<html:option value="2006">2006</html:option>
									<html:option value="2007">2007</html:option>
									<html:option value="2008">2008</html:option>
									<html:option value="2009">2009</html:option>
									<html:option value="2010">2010</html:option>
									<html:option value="2011">2011</html:option>
									<html:option value="2012">2012</html:option>
									<html:option value="2013">2013</html:option>
									<html:option value="2014">2014</html:option>
									<html:option value="2015">2015</html:option>									
								</html:select>
							</td>
						</tr>
						<tr>
							<td>Expiry Date</td>
							<td> 
								<html:select property="expiryDateMonth">
							        <html:option value="01">01</html:option>
							        <html:option value="02">02</html:option>
							        <html:option value="03">03</html:option>
							        <html:option value="04">04</html:option>
							        <html:option value="05">05</html:option>
							        <html:option value="06">06</html:option>
							        <html:option value="07">07</html:option>
							        <html:option value="08">08</html:option>
							        <html:option value="09">09</html:option>
							        <html:option value="10">10</html:option>
							        <html:option value="11">11</html:option>
							        <html:option value="12">12</html:option>
								</html:select>
							&nbsp;
								<html:select property="expiryDateYear">
									<html:option value="">yr.</html:option>
									<html:option value="2008">2008</html:option>
									<html:option value="2009">2009</html:option>
									<html:option value="2010">2010</html:option>
									<html:option value="2011">2011</html:option>
									<html:option value="2012">2012</html:option>
									<html:option value="2013">2013</html:option>
									<html:option value="2014">2014</html:option>
									<html:option value="2015">2015</html:option>
									<html:option value="2016">2016</html:option>
									<html:option value="2017">2017</html:option>
									<html:option value="2018">2018</html:option>
									<html:option value="2019">2019</html:option>
									<html:option value="2020">2020</html:option>
								</html:select>
							<br></td>
						</tr>
					</table>
				</td>
			</tr>
			<tr id="billingName" style="<bean:write name="styleValue" />">
				<td><b>Billing Name</b></td>
				<td><html:text property="first" styleClass="AlphaValue" maxlength="50" size="40"></html:text></td>
			</tr>
			<tr id="billingAddress" style="<bean:write name="styleValue" />" valign="top">
				<td><b>Billing Address</b></td>
				<td>
					<table id="MyAccounts">
						<tr id="firstName" style="display: none;">
							<td>First Name</td>
							<td><input type="text" name="first" maxlength="25"></td>
						</tr>
						<tr id="lastName" style="display: none;">
							<td>Last Name</td>
							<td><input type="text" name="last" maxlength="25"></td>
						</tr>
						<tr>
							<td colspan="4"><html:text property="street" maxlength="100" size="40"></html:text></td>
						</tr>
						<tr>
							<td colspan="2"><html:text property="city" maxlength="40"></html:text></td>
							<td colspan="2"><html:text property="state" maxlength="40" size="20"></html:text></td>
						</tr>
						<tr>
							<td colspan="2"><html:text property="postal" maxlength="25" size="20"></html:text></td>
							<td colspan="2">
								<html:select property="country">
									<html:option value="WONoSelectionString">Select Your Country</html:option>
									<html:option value="AU">Australia</html:option>
								    <html:option value="AT">Austria</html:option>
								    <html:option value="BE">Belgium</html:option>
								    <html:option value="CA">Canada</html:option>
								    <html:option value="DK">Denmark</html:option>
								    <html:option value="FI">Finland</html:option>
								
								    <html:option value="FR">France</html:option>
								    <html:option value="DE">Germany</html:option>
								    <html:option value="HK">Hong Kong</html:option>
								    <html:option value="IE">Ireland</html:option>
								    <html:option value="IT">Italy</html:option>
								    <html:option value="JP">Japan</html:option>
								
								    <html:option value="LU">Luxembourg</html:option>
								    <html:option value="NL">Netherlands</html:option>
								    <html:option value="NZ">New Zealand</html:option>
								    <html:option value="NO">Norway</html:option>
								    <html:option value="PT">Portugal</html:option>
								    <html:option value="ES">Spain</html:option>
								
								    <html:option value="SE">Sweden</html:option>
								    <html:option value="CH">Switzerland</html:option>
								    <html:option value="GB">United Kingdom</html:option>
								    <html:option value="US">United States</html:option>
								</html:select>
							</td>
						</tr>
					</table>
				</td>
			</tr>
			<tr><td colspan="2">&nbsp;</td></tr>
			<tr>
				<td>&nbsp;</td>
				<td>
					<logic:equal value="0" property="accountId" name="paymentAccountForm">
						<html:submit property="operation" styleClass="green" value="add account"></html:submit><br>
					</logic:equal>
					<logic:notEqual value="0" property="accountId" name="paymentAccountForm">
						<html:submit property="operation" styleClass="green" value="update account"></html:submit><br>
					</logic:notEqual>
				</td>
			</tr>
			</table>
			
		
		<input type="hidden" name="accountId" value="<%= accountId.toString() %>" />
	</div>
	</html:form>
</div>
<script>
	document.getElementById('accountType').value = 'PayPal';
	document.getElementById('cardTypeName').options[0].selected = 0;
</script>