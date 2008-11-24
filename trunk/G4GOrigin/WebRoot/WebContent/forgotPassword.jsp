
<%@page import="com.g4g.utils.DataUtil"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles"%>

<bean:define id="siteId" value="wg" type="java.lang.String" />
<logic:present name="siteId">
	<bean:define id="siteId" name="siteId" type="java.lang.String" />
</logic:present>

<link rel="stylesheet"
	href="<%=DataUtil.getBasePath(request)%>WebContent/<bean:write name="siteId"/>/css/Global.css"
	media="screen">

<!--ModalWrapper-->

<div class="PageContent">


<style>

#container {
	width:100%;
	margin:0 auto;
	
}
				#ModalMessage {
					padding: 10px;
					margin: 0 auto;
					border: 5px solid #666;
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
						
				}

					.MessageContainer {
						background: url("<%=DataUtil.getBasePath(request)%>WebContent/<bean:write name="siteId"/>/css/images/bg/TournamentBG.jpg");
						margin: 5px 0;
						border: 1px solid #e2e1dd;
					}
				</style>

	<div id="ModalMessage" class="MessageContainer compose clearfix">
		<html:form action="/forgotPassword">
			<table width="100%" border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td width="45%" >
					<h1>	Enter Your UserName(Email Address) :</h1>
					</td>
					<td width="65%" align="left">
						<html:text property="username"/>
					</td>
				</tr>
				<tr>
					<td >
						<h1>Please enter text as in image :</h1>
					</td>
					<td align="left">
						<input type="text" name="captcha" value="" />
					</td>
				</tr>
				<tr>
					<td  colspan="2">
						<div style="margin: 0px 0px 0px 242px">
							<img src="Captcha.jpg"
								alt="Enter the
    		characters appearing in this image"
								border="1" />
						</div>
					</td>
				</tr>
				<tr>
					<td height="30"></td>
					<td align="left"> 
					<h1>You will soon get an Email for this.</h1>
					</td>
				</tr>
				<tr>
					<td height="30"></td>
					<td align="left">
					<div id="container">
					<div class="buttons">
							<html:submit property="operation"
								value="submit" styleClass="green"/>
							<input type="button" value="&nbsp;&nbsp;Close&nbsp;&nbsp;"
								onclick="self.parent.tb_remove();" class="orange" />
						</div>
					</div>
					<h1>
						<html:errors />
					</h1>
					</td>
				</tr>
			</table>
		</html:form>
	</div>
	<!-- MessageContainer -->

</div>
