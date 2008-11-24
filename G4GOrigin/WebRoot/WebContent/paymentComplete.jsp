<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>

<%@page import="com.g4g.utils.DataUtil"%>
<%@page import="com.g4g.utils.SessionUtil"%>
<%@page import="com.g4g.utils.G4GConstants"%>


<bean:define id="siteId" value="wg" type="java.lang.String"/>
 <logic:present name="siteId" >
  <bean:define id="siteId" name="siteId" type="java.lang.String"/>
</logic:present>

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
		<div class="messageInfo grid grid8">
			<table>
				<tr>
					<td>Thank you.</td>
				</tr>				
				<tr>
					<td align="left"><div class="buttons"><a href="#" onclick="self.parent.tb_remove()" class="button green inline"  style="opacity:1.0;cursor:default;">close</a></div></td>
				</tr>
			</table>
		</div>
	</div>
	<!-- MessageContainer -->
</div>
<%
	session.removeAttribute(G4GConstants.PAYMENT_STATUS); 
%>