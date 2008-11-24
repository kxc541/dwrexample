<%@page import="com.g4g.utils.DataUtil"%>
<%@page import="java.util.Date"%>
<%@page import="com.g4g.utils.G4GConstants"%>
<%@page import="com.impessa.worldgaming.client.CreditCardPaymentAccount"%>
<%@page import="java.util.List"%>
<%@page import="com.impessa.worldgaming.client.WithdrawalMethod"%>
<%@page import="com.impessa.worldgaming.client.PaymentAccount"%>
<%@page import="java.util.Calendar"%>
<%@page import="java.util.GregorianCalendar"%>
<%@page import="com.g4g.utils.DateUtil"%>
<%@page import="com.g4g.dto.UserDTO"%>
<%@page import="com.g4g.utils.SessionUtil"%>
<%@page import="com.impessa.worldgaming.client.InternalException_Exception"%>
<%@page import="com.impessa.worldgaming.client.UserNotFoundException_Exception"%>
<%@page import="com.impessa.worldgaming.client.InvalidSessionException_Exception"%>

<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles"%>

<logic:notPresent name="userDTO" property="playerDTO">
	<logic:redirect forward="homePage"></logic:redirect>
</logic:notPresent>

<bean:define id="userDTO" name="userDTO" type="com.g4g.dto.UserDTO"></bean:define>

<logic:present name="Token" scope="session">
	<bean:define id="Token" name="Token"></bean:define>
	<% session.removeAttribute("Token"); %>
	<script>
			document.location.href='https://www.sandbox.paypal.com/us/cgi-bin/webscr?cmd=_express-checkout&token=<bean:write name="Token" />';
	</script>
</logic:present>

<title>Cashier Page for <bean:write name="userDTO" property="playerDTO.screenname" /></title>

<script type="text/javascript">
function displayCVV2(isTrue) {
	if(isTrue) {
		document.getElementById('accountType').style.display = 'none';
		document.getElementById('paymentType').value = 'PayPal';
	}
	else {
		document.getElementById('accountType').style.display = 'block';
		document.getElementById('paymentType').value = 'CreditCard';
	}
}
</script>

<bean:define id="siteId" value="wg" type="java.lang.String" />
<logic:present name="siteId">
	<bean:define id="siteId" name="siteId" type="java.lang.String" />
</logic:present>

<div id="pageTitle">
	<img src="WebContent/<bean:write name="siteId"/>/css/images/banners/banner-cashier.jpg" width="920" height="41" alt="Cashier">
</div>
<%if(!DataUtil.getUserDTO(request).isStateIllegal()){ %>
<div id="content" class="registration">

	<div class="SubNav">
		<ul class="idTabs">
			<li><a href="#Wallet_page1">Account Balance</a></li>
			<li><a href="#Wallet_page2">Account Info</a></li>
		</ul>
	</div>

	<form method="post" id="form" name="addEditCashierForm" action="cashierAddEditAccount.do">

	<div id="Wallet_page1">
	<p>Review your WorldGaming.com account balance, move your money, and view a history of your transactions.</p>

		<table id="AccountBalanceSection">
			<tr>
				<td width="25%"><b>WorldGaming<br>Account Balance</b></td>
				<td width="75%" id="AccountBalance">
					<bean:define id="balance" name="balance" />
					<bean:write name="balance" format="$##0.00" />
				</td>
			</tr>
		</table>

		<br>

		<table id="MoveMoneySection">
			<tr>
				<td width="25%"><b>Move My Money</b></td>
				<td width="75%">&nbsp;</td>
			</tr>
			<tr>
				<td align="right"><b>Deposit</b></td>
				<td>
					<table>
						<tr>
							<td><em>*</em><html:text property="depositAmount" styleClass="IntegerValue" styleId="depositAmount" size="6" name="cashierForm" /></td>
							<td>
								<select name="accountType" id="accountTypeList">
									<option value="WONoSelectionString" onclick="displayCVV2(true);">
										Select Deposit Account
									</option>
									<logic:iterate id="depositAccountList"
										name="depositAccountList" indexId="index">
										<option value="<bean:write name="depositAccountList" property="accountId" />" onclick="<%= (depositAccountList instanceof CreditCardPaymentAccount) ? "displayCVV2(false);" : "displayCVV2(true);" %>">
											<bean:write name="depositAccountList" property="name" />
										</option>
									</logic:iterate>
								</select>
							</td>
							<td id="accountType" style="display: none">
								<em>*</em>
								<html:text property="cvv2" size="4" maxlength="4" styleClass="IntegerValue" name="cashierForm"></html:text>
							</td>
							<td><html:button styleClass="button green inline" value="make deposit" property="operation" onclick="depositAmountToWG()"></html:button></td>
						</tr>
					</table>
				</td>
			</tr>
			<tr>
				<td align="right"><b>Withdraw</b></td>
				<td>
					<table>
						<tr>
							<td><em>*</em><html:text property="withdrawalAmount" styleId="withdrawalAmount" styleClass="IntegerValue" size="6" name="cashierForm" /></td>
							<td>
								<html:select property="withdrawMethod" styleId="withdrawMethodList" name="cashierForm">
									<option value="WONoSelectionString">
										Select Withdrawal Method
									</option>
									<%
										List<WithdrawalMethod> withdrawalMethods = (List<WithdrawalMethod>) request.getAttribute("withdrawalAccountList");
										int totalMethods = withdrawalMethods.size();

										for(int index = 0; index < totalMethods; index++) {
											WithdrawalMethod withdrawalMethod = (WithdrawalMethod) withdrawalMethods.get(index);
											out.print("<option value=\"" + withdrawalMethod.toString() + "\">" + withdrawalMethod.toString() + "</option>");
										}
									%>
								</html:select>
							</td>
							<td><html:button styleClass="button green inline" value="make withdrawal" property="operation" onclick="withdrawAmountFromWG()"></html:button></td>
						</tr>
					</table>
				</td>
			</tr>
			<tr>
				<td colspan="2"><br>
					<table width="100%">
						<tr>
							<td width="25%"><b>Add An Account</b></td>
							<td width="75%"><html:submit property="operation" styleClass="green" value="add"></html:submit></td>
						</tr>
					</table>
					<input type="hidden" name="paymentId" id="paymentId" value="" />
				</td>
			</tr>
		</table>

		<br>

		<table id="TransactionHistorySection">
			<tr valign="top">
				<td width="25%"><b>WorldGaming.com<br>Account History</b></td>
				<td width="75%">

					<table id="TransactionHistory">
						<tr>
							<th>date</th>
							<th width="15">&nbsp;</th>
							<th>transaction</th>
							<th>amount</th>
							<th>note</th>
							<th>balance</th>
						</tr>

				<logic:iterate id="transactionList" name="transactionList"
					indexId="index">
					<bean:define id="transactionDate" name="transactionList"
						property="calendar" />
					<bean:define id="tmpAmount" name="transactionList"
						property="amount" />
					<bean:define id="tmpBalance" name="transactionList"
						property="balance" />
					<%
						tmpAmount = DataUtil.getImpassaMoney(Long.valueOf(tmpAmount.toString()));
						tmpBalance = DataUtil.getImpassaMoney(Long.valueOf(tmpBalance.toString()));

						String className = G4GConstants.BLANK;
						Calendar calendar = Calendar.getInstance();
						calendar.setTimeInMillis(Long.valueOf(transactionDate.toString()));
						Date calendarDate = calendar.getTime();

									if (index % 2 == 0) {
										className = G4GConstants.CREDIT;
									} else {
										className = G4GConstants.DEBIT;
									}
					%>
						<tr class="<%=className%>">
							<td>
								<%=DataUtil
												.getDate(DateUtil.getDateOfTimeZone(
														calendarDate, userDTO.getOffset()),
														G4GConstants.DATE_EEE_MMM_DD_YYYY_AT_hh_MM_A)%>
							</td>
							<td><img src="WebContent/<bean:write name="siteId"/>/images/history_<%=className%>.gif" width="5" height="8" alt=""></td>
							<td>
								<bean:write name="transactionList" property="type" />
							</td>
							<td>$<%= DataUtil.format(Double.valueOf(tmpAmount.toString())) %></td>
							<td>
								<bean:write name="transactionList" property="comment" />
							</td>
							<td>$<%= DataUtil.format(Double.valueOf(tmpBalance.toString())) %></td>
						</tr>
					</logic:iterate>
					</table>
				</td>
			</tr>
		</table>

	</div>

	<div id="Wallet_page2">
		<p>Add, edit, or delete your accounts.</p>

		<table id="MyAccountsSection">
			<tr>
				<td width="25%"><b>Existing Accounts</b></td>
				<td width="75">&nbsp;</td>
			</tr>
			<tr><td colspan="2">&nbsp;</td></tr>
	<%
		List<PaymentAccount> arrayPaymentList = (List) request.getAttribute("depositAccountList");
		int totalAccounts = arrayPaymentList.size();

		for(int index = 0; index < totalAccounts; index++) {
			PaymentAccount PaymentList = (PaymentAccount) arrayPaymentList.get(index);
			String strExpiryYear = (PaymentList instanceof CreditCardPaymentAccount) ? String.valueOf(((CreditCardPaymentAccount) PaymentList).getExpiryYear()) : ""; %>

			<tr><td colspan="2">&nbsp;</td></tr>
			<tr valign="top">
				<td width="25%">
					<input type="submit" name="operation" value="edit" class="green" onclick="document.getElementById('paymentId').value='<%= PaymentList.getAccountId()  %>'">
					<input type="submit" name="operation" value="delete" class="gray" onclick="document.getElementById('paymentId').value='<%= PaymentList.getAccountId()  %>'">
				</td>
				<td width="75%">
					<table id="MyAccount">
						<tr>
							<td width="50%">
								Type: <b><%= PaymentList.getName() %></b><br>
								<br>
							<% if(PaymentList instanceof CreditCardPaymentAccount) { %>
								Number: ****-****-****-<%= ((CreditCardPaymentAccount) PaymentList).getNumber().substring(((CreditCardPaymentAccount) PaymentList).getNumber().length() - 4, ((CreditCardPaymentAccount) PaymentList).getNumber().length()) %><br>
								Expiration Date: <%= ((CreditCardPaymentAccount) PaymentList).getExpiryMonth() %>/<%= strExpiryYear.substring(strExpiryYear.length() - 2, strExpiryYear.length()) %><br>
								Cardholder Name: <%= ((CreditCardPaymentAccount) PaymentList).getOwnerName() %>
							<% } %>
							</td>
							<td width="50%">
							<% if(PaymentList instanceof CreditCardPaymentAccount) { %>
								<b>Billing Address</b><br>
								<br>
								<%= ((CreditCardPaymentAccount) PaymentList).getOwnerName() %><br>
								<%= ((CreditCardPaymentAccount) PaymentList).getOwnerStreet() %><br>
								<%= ((CreditCardPaymentAccount) PaymentList).getOwnerCity() %>, <%= ((CreditCardPaymentAccount) PaymentList).getOwnerState() %> <%= ((CreditCardPaymentAccount) PaymentList).getOwnerZip() %>
							<% } %>
							</td>
						</tr>
					</table>
				</td>
			</tr>
		<% } %>
		</table>
		<br>
		<table width="100%">
			<tr>
				<td width="25%"><b>Add New Account</b></td>
				<td width="75%"><html:submit property="operation" styleClass="green" value="add"></html:submit></td>
			</tr>
		</table>
		<input type="hidden" name="paymentId" id="paymentId" value="" />
		<input type="hidden" name="paymentType" id="paymentType" value="" />
	</div>

	</form>

</div>
<%}else{ %>
<div id="content" class="registration" align="left">
Cashier disabled - your region does
not support Skilled Gaming.
</div>
<%} %>
<div id="sidebar" class="registration"></div>
<%
	if(session.getAttribute(G4GConstants.NOTIFICATIONMESSAGE) != null && !session.getAttribute(G4GConstants.NOTIFICATIONMESSAGE).toString().equals(G4GConstants.NONE)){
		out.print("<script>notificationPrompt('" + session.getAttribute(G4GConstants.NOTIFICATIONMESSAGE).toString() + "');</script>");
		session.removeAttribute(G4GConstants.NOTIFICATIONMESSAGE);
	}
	if(session.getAttribute(G4GConstants.PAYMENT_TOKEN) != null) {
		out.print("<script>tb_show('', '" + DataUtil.getBasePath(request) + "WebContent/paymentConfirmation.jsp?TB_iframe=true&height=500&width=650&modal=true', '');</script>");
	} else if(session.getAttribute(G4GConstants.PAYMENT_STATUS) != null) {
		out.print("<script>tb_show('', '" + DataUtil.getBasePath(request) + "WebContent/paymentComplete.jsp?TB_iframe=true&height=500&width=650&modal=true', '');</script>");
	}
%>
