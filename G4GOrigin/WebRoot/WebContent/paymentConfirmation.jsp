<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>

<%@page import="com.impessa.worldgaming.client.PpecDetailsResponse"%>
<%@page import="com.g4g.utils.DataUtil"%>
<%@page import="com.g4g.utils.SessionUtil"%>
<%@page import="com.g4g.utils.G4GConstants"%>

<bean:define id="siteId" value="wg" type="java.lang.String"/>
 <logic:present name="siteId" >
  <bean:define id="siteId" name="siteId" type="java.lang.String"/>
</logic:present>

<%
	PpecDetailsResponse ppecDetailsResponse = new PpecDetailsResponse();
	
	if(session.getAttribute(G4GConstants.PAYPAL_DETAIL_RESPONSE) != null)
		ppecDetailsResponse = (PpecDetailsResponse) session.getAttribute(G4GConstants.PAYPAL_DETAIL_RESPONSE);

%>
<link rel="stylesheet" href="<%= DataUtil.getBasePath(request) %>WebContent/<%=SessionUtil.getSiteId(request) %>/css/Global.css" media="screen">

<!--ModalWrapper-->
<div id="content">
	<style>
.grid {
	float: left;
}

.gridRight {
	float: right;
}

.grid1 {
	width: 60px;
}

.grid2 {
	width: 80px;
}

.grid3 {
	width: 100px;
}

.grid4 {
	width: 125px;
}

.grid5 {
	width: 140px;
}

.grid6 {
	width: 170px;
}

.grid7 {
	width: 250px;
}

.grid8 {
	width: 315px;
}

.grid9 {
	width: 375px;
}

.grid10 {
	width: 455px;
}

.compose {
	margin:20px;
	padding:10px;
	border:1px solid #e8e5e0;
}

.compose h1 {
	font-size:12px;
	font-weight:bold;
	padding:10px;
	margin-bottom:18px;
}
									
.MessageContainer {
	background:url(wg/css/images/bg/TournamentBG.jpg) top center no-repeat #d2d2d2;
	margin:5px 0;
	border:1px solid #e2e1dd;
}

#ModalMessage {
	padding:10px;
	margin:0 auto;
	border:5px solid #666;
}
</style>
	<div id="ModalMessage" class="MessageContainer compose clearfix">
		<h1>
			Payment Confirmation
		</h1>
		<div class="messageInfo grid grid8">
			<table>
				<tr>
					<td width="50%" align="right">Name : </td>
					<td width="50%" align="left"><%= ppecDetailsResponse.getFirstName() %>&nbsp;<%= ppecDetailsResponse.getMiddleName() %>&nbsp;<%= ppecDetailsResponse.getLastName() %></td>
				</tr>
				<tr>
					<td align="right">Address : </td>
					<td align="left"><%= ppecDetailsResponse.getStreet1() %>,
                        <%= ppecDetailsResponse.getStreet2() %></br>
						<%= ppecDetailsResponse.getCityName() %></br>
						<%= ppecDetailsResponse.getState() %></br>
						<%= ppecDetailsResponse.getPayerCountry() %></br>
					</td>
				</tr>
				<tr>
					<td align="right">E-mail : </td>
					<td align="left"><%= ppecDetailsResponse.getEmail() %></td>
				</tr>
				<tr>
					<td align="right">Business : </td>
					<td align="left"><%= ppecDetailsResponse.getPayerBusiness() %></td>
				</tr>
				<tr>
					<td align="right"><div class="buttons"><a onclick="parent.location.href='../displayCashier.do?paymentConfirm=true&token=<%= session.getAttribute(G4GConstants.PAYMENT_TOKEN) %>&PayerID=<%= session.getAttribute(G4GConstants.PAYMENT_PAYERID) %>';self.parent.tb_remove(false);" class="button orange inline" style="opacity:1.0;cursor:default;" >Pay</a></div></td>
					<td align="left"><div class="buttons"><a href="#" onclick="self.parent.tb_remove()" class="button green inline"  style="opacity:1.0;cursor:default;">cancel</a></div></td>
				</tr>
			</table>
		</div>
		
	</div>
	<!-- MessageContainer -->
</div>
<% 
	session.removeAttribute(G4GConstants.PAYMENT_TOKEN);
	session.removeAttribute(G4GConstants.PAYMENT_PAYERID);
	session.removeAttribute(G4GConstants.PAYPAL_DETAIL_RESPONSE);
%>