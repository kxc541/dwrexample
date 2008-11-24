<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>

<bean:define id="siteId" value="wg" type="java.lang.String"/>
 <logic:present name="siteId" >
  <bean:define id="siteId" name="siteId" type="java.lang.String"/>
</logic:present>

<link rel="stylesheet" media="screen" href="WebContent/<bean:write name="siteId"/>/css/Global.css">

<link rel="stylesheet" media="screen" href="WebContent/<bean:write name="siteId"/>/css/Widgets.css">

<link rel="stylesheet" media="screen" href="WebContent/<bean:write name="siteId"/>/css/UserHome.css">

<link rel="stylesheet" media="screen" href="WebContent/<bean:write name="siteId"/>/css/Tables.css">

<link rel="stylesheet" media="screen" href="WebContent/<bean:write name="siteId"/>/css/thickbox.css">

		<div class="Format1 clearfix">
			<h2>Add Money</h2>
			<div class="floatLeft grid6">
			<h3><em>Current Balance:  </em>$960.00<em><strong></strong></em></h3>
			</div>

			<div class="floatLeft">
				<form method="post" id="form" name="saveChangesForm" class="buttons" action="/cgi-bin/WebObjects/g4g.woa/wo/69.0.HomePageWrapper.19.3.1.1.0.0.1.0.1.0.0.UserDisplay.0.1.1.2.0.21.5">
					<select class="floatLeft" name="0.HomePageWrapper.19.3.1.1.0.0.1.0.1.0.0.UserDisplay.0.1.1.2.0.21.5.1">
						<option value="WONoSelectionString">Select Deposit Account</option>
						<option value="0">CitiBank Visa</option>
						<option value="1">Discover</option>
					</select>&nbsp;
					<input class="floatLeft" size="6" type="text" name="0.HomePageWrapper.19.3.1.1.0.0.1.0.1.0.0.UserDisplay.0.1.1.2.0.21.5.3">&nbsp;
					<input class="button green inline floatLeft" style="opacity:1.0;cursor:default;" type="submit" value="add money" name="0.HomePageWrapper.19.3.1.1.0.0.1.0.1.0.0.UserDisplay.0.1.1.2.0.21.5.5">
				</form>
	
				<p class="break"><a href="/cgi-bin/WebObjects/g4g.woa/wa/UserActions/cashierPage"> <img src="WebContent/<bean:write name="siteId"/>/css/images/icons/user.png"> Change Account Settings</a></p>
			</div>
		</div>


	<div class="Format1">
	<h2>Account History</h2>

		<table class="StyleFull">
			<thead>
				<tr>
					<th>Transaction type</th>
					<th>Amount</th>
					<th>Date</th>
					<th>Comments</th>
					<th>Balance</th>
				</tr>
			</thead>
			<tbody>

						<tr class="credit">
							<th>Deposit</th>
							<td>$800</td>
							<td>01-14-08</td>
							<td>Funds Deposit</td>
							<td>$800</td>
						</tr>

						<tr class="debit">
							<th>Paid Entry Fee</th>
							<td>$100</td>
							<td>01-15-08</td>
							<td>Game Bet</td>
							<td>$700</td>
						</tr>

						<tr class="credit">
							<th>Cancel Credit</th>
							<td>$100</td>
							<td>01-15-08</td>
							<td>Credit - Game Cancelled</td>
							<td>$800</td>
						</tr>

						<tr class="debit">
							<th>Paid Entry Fee</th>
							<td>$50</td>
							<td>01-16-08</td>
							<td>Game Bet</td>
							<td>$750</td>
						</tr>

						<tr class="credit">
							<th>Win Credit</th>
							<td>$90</td>
							<td>01-16-08</td>
							<td>Credit - Game Won</td>
							<td>$840</td>
						</tr>

			</tbody>
		</table>
	</div>