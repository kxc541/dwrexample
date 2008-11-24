<%@page import="com.g4g.utils.G4GConstants"%>
<%@page import="com.g4g.dto.TwoplayermatchDTO"%>
<%@page import="com.g4g.utils.DataUtil"%>
<%@page import="com.g4g.dto.PlayerDTO"%>
<%@page import="com.g4g.utils.DateUtil"%>
<%@page import="com.g4g.dto.UserDTO"%>
<%@page import="com.g4g.dto.GameDTO"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
	"http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>

<bean:define id="siteId" value="wg" type="java.lang.String" />
<logic:present name="siteId">
	<bean:define id="siteId" name="siteId" type="java.lang.String" />
</logic:present>
<html>
	<head>

		<title>Ranking Form</title>

		<link rel="stylesheet" media="screen"
			href="WebContent/<bean:write name="siteId"/>/css/styles.css">

		<link rel="stylesheet"
			href="WebContent/<bean:write name="siteId"/>/css/Global.css"
			type="text/css" media="screen" />
		<link rel="stylesheet"
			href="WebContent/<bean:write name="siteId"/>/css/Widgets.css"
			type="text/css" media="screen" />

		<link rel="stylesheet"
			href="WebContent/<bean:write name="siteId"/>/css/rank.css"
			type="text/css" media="screen" />

	</head>
	<body>
		<br>
		<%
			UserDTO userDTO = new UserDTO();
			if (DataUtil.getUserDTO(request) != null)
				userDTO = DataUtil.getUserDTO(request);
			PlayerDTO playerDTO = (PlayerDTO) request
					.getAttribute(G4GConstants.PLAYERDTO);
			GameDTO gameDTO = (GameDTO) request
					.getAttribute(G4GConstants.GAMEDTO);
			TwoplayermatchDTO twoplayermatchDTO = (TwoplayermatchDTO) request
					.getAttribute(G4GConstants.TWOPLAYERMATCHDTO);
			String date = DataUtil.getDate(DateUtil.getDateOfTimeZone(DataUtil
					.getDate(twoplayermatchDTO.getCompleteddate().toString(),
							G4GConstants.DATE_YYYY_MM_DD_HH_MM_SS_sss), userDTO
					.getOffset()), G4GConstants.DATE_DD_MM_YYYY);
			String time = DataUtil.getDate(DateUtil.getDateOfTimeZone(DataUtil
					.getDate(twoplayermatchDTO.getCompleteddate().toString(),
							G4GConstants.DATE_YYYY_MM_DD_HH_MM_SS_sss), userDTO
					.getOffset()), G4GConstants.DATE_H_MM_A);
		%>
		<html:form method="post" action="/submitFeedbackReputation.do"
			styleId="rankingForm">
			<div id="page1">
				<div id="ComingSoon1" class="ContentWrapper1 clearfix">
					<div class="PageContent1">
						<img
							src="WebContent/<bean:write name="siteId"/>/images/rankingform/global.jpg"
							width="77" height="100" class="floatLeft">
						<h1 style="color: #fff;">
							Rate Your Opponents
						</h1>
						<p style="color: #fff;">
							Click on the ratings globes for each opponent that best represent
							your experience of them. If you like, you can add further
							comments in the comments box. Remember that you are rating your
							opponents' reputation. More text that explains what you are
							actually rating. Is it skill or their abillity to play fairly?
						</p>
					</div>
					<div class="PageContent-middle">
						<div id="greybg">
							<table width="100%" border="0" cellspacing="0" cellpadding="0"
								class="grey-text10">
								<tr>
									<td width="32%">
										game
										<span class="WidgetHeader"><%=gameDTO.getGamename() %> </span>
										<br>
									</td>
									<td width="32%">
										date/time
										<span class="WidgetHeader"><%=date%>; <%=time%></span>
										<br>
									</td>
									<td width="36%">
										wager
										<span class="WidgetHeader"><%=twoplayermatchDTO.getPayoutamount() %> </span>
										<br>
									</td>
								</tr>
							</table>
						</div>
					</div>

					<div class="PageContent-middle">
						<%
							if (playerDTO.getId() == twoplayermatchDTO.getWinnerid()
										.getId()) {
						%>

						<div id="olive-greenbg">
							<table width="100%" border="0" cellspacing="0" cellpadding="0">
								<tr>
									<td width="50%" valign="top">
										<div class="rankincontent-left">
											<div class="WidgetHeader rankincontent-bottom">
												<%=playerDTO.getScreenname()%>
												<span class="white-text14-bold">WINNER</span>
											</div>
											<table width="100%" border="0" cellspacing="0"
												cellpadding="0">
												<tr>
													<td width="31%" valign="top">
														<%
															if (playerDTO.getAvatars() != null) {
														%>
														<img width="47" height="46"
															src="<%=DataUtil.getBasePath(request)%>WebContent/displayImage.jsp?dto=<%=G4GConstants.AVATARDTO%>&id=<%=playerDTO.getAvatars().getId()%>">
														<%
															} else {
														%>
														<img width="47" height="46"
															src="<%=DataUtil.getBasePath(request)%>WebContent/displayImage.jsp?dto=<%=G4GConstants.PICTUREDTO%>&id=<%=playerDTO.getPictureId()%>">
														<%
															}
														%>
														<br>
													</td>
													<td width="69%" valign="top">
														<br>
														<table width="100%" border="0" cellspacing="0"
															cellpadding="0" class="grey-text10">
															<tr>
																<td width="20%" align="center" valign="top">
																	<html:radio property="reputation" value="1">sucks</html:radio>
																	<br>
																	<br>
																</td>
																<td width="20%" align="center" valign="top">
																	<html:radio property="reputation" value="2">lame</html:radio>
																	<br>
																	<br>
																</td>
																<td width="20%" align="center" valign="top">
																	<html:radio property="reputation" value="3">ok</html:radio>
																	<br>
																	<br>
																</td>
																<td width="20%" align="center" valign="top"
																	class="white-text10">
																	<html:radio property="reputation" value="4">good</html:radio>
																	<br>
																	<br>
																</td>
																<td width="20%" align="center" valign="top">
																	<html:radio property="reputation" value="5">rocks</html:radio>
																	<br>
																	s
																	<br>
																</td>
															</tr>
														</table>
														<br>
													</td>
												</tr>
											</table>
										</div>
										<br>
									</td>
									<td width="50%" valign="top">
										<div class="rankincontent-right">
											<img
												src="WebContent/<bean:write name="siteId"/>/images/rankingform/olive-comments-icon.jpg"
												alt="" width="9" height="9">
											<span class="grey-text10">comments </span>
											<br>
											<div>
												<html:textarea property="feedbackcomment"
													styleClass="white-border-olive white-text10">Good player. No complaints. Let's do it again.</html:textarea>
												<html:errors property="feedbackcomment" />
											</div>
										</div>
										<br>
									</td>
								</tr>
							</table>
						</div>
						<%
							} else {
						%>

						<div id="greybg">
							<table width="100%" border="0" cellspacing="0" cellpadding="0">
								<tr>
									<td width="50%" valign="top">
										<div class="rankincontent-left">
											<div class="WidgetHeader rankincontent-bottom">
												<%=playerDTO.getScreenname()%>
											</div>

											<table width="100%" border="0" cellspacing="0"
												cellpadding="0">
												<tr>
													<td width="31%" valign="top">
														<%
															if (playerDTO.getAvatars() != null) {
														%>
														<img width="47" height="46"
															src="<%=DataUtil.getBasePath(request)%>WebContent/displayImage.jsp?dto=<%=G4GConstants.AVATARDTO%>&id=<%=playerDTO.getAvatars().getId()%>">
														<%
															} else {
														%>
														<img width="47" height="46"
															src="<%=DataUtil.getBasePath(request)%>WebContent/displayImage.jsp?dto=<%=G4GConstants.PICTUREDTO%>&id=<%=playerDTO.getPictureId()%>">
														<%
															}
														%>
														<br>
													</td>
													<td width="69%" valign="top">
														<br>
														<table width="100%" border="0" cellspacing="0"
															cellpadding="0" class="grey-text10">
															<tr>
																<html:errors property="reputation" />
																<td width="20%" align="center" valign="top">
																	<html:radio property="reputation" value="1">
																		sucks
																	</html:radio>
																	<br>
																	<br>
																</td>
																<td width="20%" align="center" valign="top"
																	class="white-text10">
																	<html:radio property="reputation" value="2">lame</html:radio>
																	<br>
																	<br>
																</td>
																<td width="20%" align="center" valign="top">
																	<html:radio property="reputation" value="3">ok</html:radio>
																	<br>
																	<br>
																</td>
																<td width="20%" align="center" valign="top">
																	<html:radio property="reputation" value="4">good</html:radio>
																	<br>
																	<br>
																</td>
																<td width="20%" align="center" valign="top">
																	<html:radio property="reputation" value="5">rocks</html:radio>
																	<br>
																	<br>
																</td>
															</tr>
														</table>
														<br>
													</td>
												</tr>
											</table>
										</div>
										<br>
									</td>
									<td width="50%" valign="top">
										<div class="rankincontent-right">
											<img
												src="WebContent/<bean:write name="siteId"/>/images/rankingform/grey-comments-icon.jpg"
												alt="" width="9" height="9">
											<span class="grey-text10">comments </span>
											<br>
											<div>
												<html:textarea property="feedbackcomment"
													styleClass="white-border-grey white-text10">Unplugged when he was about to be eliminated. Not sure I'd play him again.</html:textarea>
												<html:errors property="feedbackcomment" />
											</div>
										</div>
										<br>
									</td>
								</tr>
							</table>
						</div>
						<%
							}
						%>
					</div>
					<div class="PageContent2">
						<p>
							Please review your ratings before you submit them. Be sure that
							you've been fair and honest in your evaluation.
						</p>
					</div>
					<table width="100%" border="0" cellspacing="0" cellpadding="0">
						<tr>
							<TD>
								<html:submit property="opearation" value="submit">
								</html:submit>
								<br>
							</TD>
							<td align="right">
								<div class="rankincontent-left">
									<a href="#" onclick="parent.tb_remove(false);"><img
											src="WebContent/<bean:write name="siteId"/>/images/Buttons/cancel-btn.jpg"
											alt="" border="0"> </a>
								</div>
							</td>
							<td align="left">
								<div class="rankincontent-right">
									<a href="#"><img
											src="WebContent/<bean:write name="siteId"/>/images/Buttons/submit-rating-btn.jpg"
											alt="" width="127" height="22" border="0"> </a>
								</div>
							</td>
						</tr>
					</table>
					<br clear="all">
					<br>
				</div>
			</div>
			<html:hidden property="playerid" value="<%=String.valueOf(playerDTO.getId())%>"></html:hidden>
			<input type="hidden" name="matchid" value="<%=request.getParameter(G4GConstants.MATCHID)%>" />
			<html:hidden property="feedbackReputationId" />
		</html:form>
	</body>
</html>