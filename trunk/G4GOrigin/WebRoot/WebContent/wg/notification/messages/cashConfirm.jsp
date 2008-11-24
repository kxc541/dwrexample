<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>

<%@page import="com.g4g.utils.SessionUtil"%>
<%@page import="com.g4g.utils.G4GConstants"%>
<%@page import="com.g4g.utils.DataUtil"%>

<bean:define id="siteId" value="wg" type="java.lang.String"/>
<logic:present name="siteId" >
<bean:define id="siteId" name="siteId" type="java.lang.String" />
</logic:present>

<%
	String messageId = request.getParameter(G4GConstants.RANDOMID);
	String transactionType = request.getParameter(G4GConstants.TRANSACTION_TYPE);
	String am= request.getParameter(G4GConstants.AMOUNT);
	Double amount = G4GConstants.ZERO_DOUBLE;
	if(am!=null){
		amount = Double.valueOf(am);
	}
%>
<div class="hideDiv"
	id="body<%= messageId %>">
	<div class="MessageContent clearfix">
		<div class="CloseButton">
			<a onclick="javascript:closeMessage(this)"
				id="close<%=messageId%>"
				href="javascript:{}">Close</a>
		</div>
		<div class="grid grid2">
			<img alt="" src="WebContent/<bean:write name="siteId"/>/css/images/icons/money.png" class="gameImage">
		</div>

		<div class="messageInfo grid grid10">
			<dl class="">
				<dt>
					CONFIRMATION - You have <%=transactionType+"ed" %> Money!
				</dt>
				<dd>
					<p>
						This message is to confirm that you have <%= transactionType.equals(G4GConstants.DEBIT) ? "withdrawn money from" : "added money into" %> 
						your account. This transaction is now visible in your Account History.
						Please review the following information:
					</p>

					<p>
					<table class="WidgetInfo Style1">
						<tbody>
							<tr>
								<td>
									<%=transactionType %>
								</td>
								<td>
									$<%=DataUtil.format(amount)  %>
								</td>
							</tr>
							<tr>
								<td class="buttons">
									<a href="displayCashier.do" class="green">Visit Cashier</a>
								</td>
							</tr>
						</tbody>
					</table>
				</dd>
			</dl>
		</div>
		<ul class="floatRight messageOptions">
			<li>
				<a href="#" class="messageOptionsArea"></a>
			</li>
		</ul>
	</div>
	<!-- content -->

</div>
<!-- message container -->
