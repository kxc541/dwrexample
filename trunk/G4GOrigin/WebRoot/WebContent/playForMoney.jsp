
<%@page import="com.g4g.utils.DataUtil"%><!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" 
	"http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>

<title>Coming Soon</title>

<link rel="stylesheet" media="screen" href="wg/css/styles.css">
<link rel="stylesheet" media="screen" href="wg/css/header.css">
<link rel="stylesheet" media="screen" href="wg/css/menu.css">

<link rel="stylesheet" media="screen" href="wg/css/soon.css">

</head>
<body>

<div id="page">

<jsp:include page="common/HeaderNew.jsp" />
<%if(DataUtil.getUserDTO(request)==null){ 
%>
<jsp:include page="common/GlobalNavigation.jsp" />
<%}else{ 
%>
<jsp:include page="common/GlobalNavigationLogin.jsp" />
<%} %>

<div id="contentNoSidebar" class="soon">

<br><br><br><br>

	<table width="575" align="center">
		<tr valign="top">
			<td>
				<p>Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Aenean consectetuer. Etiam venenatis. Sed ultricies, pede sit amet aliquet lobortis, nisi ante sagittis sapien, in rhoncus lectus mauris quis massa. Integer porttitor, mi sit amet viverra faucibus, urna libero viverra nibh, sed dictum nisi mi et diam. Nulla nunc eros, convallis sed, varius ac, commodo et, magna. Proin vel risus. Vestibulum eu urna. Maecenas lobortis, pede ac dictum pulvinar, nibh ante vestibulum tortor, eget fermentum urna ipsum ac neque. Nam urna nulla, mollis blandit, pretium id, tristique vitae, neque. Etiam id tellus. Sed pharetra enim non nisl.</p>
			</td>
			<td><img src="wg/css/images/ads/TournamentsPage.png" width="178" height="289" alt="Coming Soon" style="padding-left: 75px;"></td>
		</tr>
	</table>

</div>

<jsp:include page="common/FooterNavNew.jsp" />

</div>

</body>
</html>