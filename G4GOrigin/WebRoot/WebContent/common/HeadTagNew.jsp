<%@page import="com.g4g.utils.DataUtil"%>
<%@page import="com.g4g.utils.SessionUtil"%>

<base href="<%=DataUtil.getBasePath(request)%>">

<link rel="stylesheet" type="text/css" href="<%=DataUtil.getBasePath(request)%>WebContent/<%=SessionUtil.getSiteId(request) %>/css/styles.css">
<link rel="stylesheet" type="text/css" href="<%=DataUtil.getBasePath(request)%>WebContent/<%=SessionUtil.getSiteId(request) %>/css/header.css">
<link rel="stylesheet" type="text/css" href="<%=DataUtil.getBasePath(request)%>WebContent/<%=SessionUtil.getSiteId(request) %>/css/menu.css">
<link rel="stylesheet" type="text/css" href="<%=DataUtil.getBasePath(request)%>WebContent/<%=SessionUtil.getSiteId(request) %>/css/avatar.css">
<link rel="stylesheet" type="text/css" href="<%=DataUtil.getBasePath(request)%>WebContent/<%=SessionUtil.getSiteId(request) %>/css/wid.css">
<link rel="stylesheet" type="text/css" href="<%=DataUtil.getBasePath(request)%>WebContent/<%=SessionUtil.getSiteId(request) %>/css/tour.css">
<link rel="stylesheet" type="text/css" href="<%=DataUtil.getBasePath(request)%>WebContent/<%=SessionUtil.getSiteId(request) %>/css/pro.css">
<link rel="stylesheet" type="text/css" href="<%=DataUtil.getBasePath(request)%>WebContent/<%=SessionUtil.getSiteId(request) %>/css/reg.css">
<link rel="stylesheet" type="text/css" href="<%=DataUtil.getBasePath(request)%>WebContent/<%=SessionUtil.getSiteId(request) %>/css/soon.css">
<link rel="stylesheet" type="text/css" href="<%=DataUtil.getBasePath(request)%>WebContent/<%=SessionUtil.getSiteId(request) %>/css/lobby.css">
<link rel="stylesheet" type="text/css" href="<%=DataUtil.getBasePath(request)%>WebContent/<%=SessionUtil.getSiteId(request) %>/css/thickbox.css">
<link rel="stylesheet" type="text/css" href="<%=DataUtil.getBasePath(request)%>WebContent/<%=SessionUtil.getSiteId(request) %>/css/humanmsg.css">
<!--[if IE 6]>
<META HTTP-EQUIV="imagetoolbar" CONTENT="no">
<script type="text/javascript">
	var ieSrcPath = "<%=DataUtil.getBasePath(request)%>WebContent/<%=SessionUtil.getSiteId(request) %>/images/";
</script>
<style type="text/css">
	 img, div, a {behavior: url(<%=DataUtil.getBasePath(request)%>WebContent/<%=SessionUtil.getSiteId(request) %>/css/iepngfix.htc)}
</style>
<link rel="stylesheet" type="text/css" href="<%=DataUtil.getBasePath(request)%>WebContent/<%=SessionUtil.getSiteId(request) %>/css/ie6.css">
<![endif]-->
<script type="text/javascript"
	src="<%=DataUtil.getBasePath(request)%>WebContent/<%=SessionUtil.getSiteId(request) %>/lib/firebug/firebug.js">Generic</script>
<script type="text/javascript" src="<%=DataUtil.getBasePath(request)%>WebContent/<%=SessionUtil.getSiteId(request) %>/js/jquery.js">Generic</script>
<script type="text/javascript" src="<%=DataUtil.getBasePath(request)%>WebContent/<%=SessionUtil.getSiteId(request) %>/js/jquery.timer.js">Generic</script>
<script type="text/javascript" src="<%=DataUtil.getBasePath(request)%>WebContent/<%=SessionUtil.getSiteId(request) %>/js/wgLib.js">Generic</script>
<script type="text/javascript" src="<%=DataUtil.getBasePath(request)%>WebContent/<%=SessionUtil.getSiteId(request) %>/js/OpenClose.js">Generic</script>
<script type="text/javascript" src="<%=DataUtil.getBasePath(request)%>WebContent/<%=SessionUtil.getSiteId(request) %>/js/ActiveAvatar.js">Generic</script>
<script type="text/javascript" src="<%=DataUtil.getBasePath(request)%>WebContent/<%=SessionUtil.getSiteId(request) %>/js/jquery.idTabs.pack.js">Generic</script>
<script type="text/javascript" src="<%=DataUtil.getBasePath(request)%>WebContent/<%=SessionUtil.getSiteId(request) %>/lib/g4glib.js">Generic</script>
<script type="text/javascript" src="<%=DataUtil.getBasePath(request)%>WebContent/<%=SessionUtil.getSiteId(request) %>/js/jquery.validate.js">Generic</script>
<script type="text/javascript" src="<%=DataUtil.getBasePath(request)%>WebContent/<%=SessionUtil.getSiteId(request) %>/js/jquery.form.js">Generic</script>
<script type="text/javascript" src="<%=DataUtil.getBasePath(request)%>WebContent/<%=SessionUtil.getSiteId(request) %>/js/jquery.metadata.js">Generic</script>
<script type="text/javascript" src="<%=DataUtil.getBasePath(request)%>WebContent/<%=SessionUtil.getSiteId(request) %>/js/date.js">Generic</script>
<script type="text/javascript" src="<%=DataUtil.getBasePath(request)%>WebContent/<%=SessionUtil.getSiteId(request) %>/js/ajaxfileupload.js">Generic</script>
<script type="text/javascript" src="<%=DataUtil.getBasePath(request)%>WebContent/<%=SessionUtil.getSiteId(request) %>/js/AJAXScript.js">Generic</script>
<SCRIPT language=JavaScript src="<%=DataUtil.getBasePath(request)%>WebContent/<%=SessionUtil.getSiteId(request) %>/javascript/chat.js"></SCRIPT>
<SCRIPT language=JavaScript src="<%=DataUtil.getBasePath(request)%>WebContent/<%=SessionUtil.getSiteId(request) %>/js/jquery.dimensions.js"></SCRIPT>
<SCRIPT language=JavaScript src="<%=DataUtil.getBasePath(request)%>WebContent/<%=SessionUtil.getSiteId(request) %>/js/jquery.idTabs.pack.js"></SCRIPT>
<SCRIPT language=JavaScript src="<%=DataUtil.getBasePath(request)%>WebContent/<%=SessionUtil.getSiteId(request) %>/js/jquery.selectboxes.js"></SCRIPT>
<SCRIPT language=JavaScript src="<%=DataUtil.getBasePath(request)%>WebContent/<%=SessionUtil.getSiteId(request) %>/js/wg.validate.js"></SCRIPT>
<SCRIPT language=JavaScript src="<%=DataUtil.getBasePath(request)%>WebContent/<%=SessionUtil.getSiteId(request) %>/js/jquery.rating.js"></SCRIPT>
<script type="text/javascript" src="<%= DataUtil.getBasePath(request) %>WebContent/<%=SessionUtil.getSiteId(request) %>/lib/firebug/firebug.js">Generic</script>

<script type="text/javascript" src="<%= DataUtil.getBasePath(request) %>WebContent/<%=SessionUtil.getSiteId(request) %>/js/jquery.innerfade.js">Generic</script>

<script type="text/javascript" src="<%= DataUtil.getBasePath(request)%>WebContent/<%=SessionUtil.getSiteId(request) %>/js/jquery.tablesorter.js">Generic</script>

<script type="text/javascript" src="<%= DataUtil.getBasePath(request)%>WebContent/<%=SessionUtil.getSiteId(request) %>/js/jquery.tablesorter.pager.js">Generic</script>

<script type="text/javascript" src="<%= DataUtil.getBasePath(request) %>WebContent/<%=SessionUtil.getSiteId(request) %>/js/thickbox.js">Generic</script>

<script type="text/javascript" src="<%= DataUtil.getBasePath(request) %>WebContent/<%=SessionUtil.getSiteId(request) %>/js/humanmsg.js">Generic</script>

<script type="text/javascript" src="<%= DataUtil.getBasePath(request) %>WebContent/<%=SessionUtil.getSiteId(request) %>/js/jquery.easing.js">Generic</script>
<script type="text/javascript" src="<%= DataUtil.getBasePath(request) %>WebContent/<%=SessionUtil.getSiteId(request) %>/js/jquery-1.1.3.1.js"></script>
<script type="text/javascript" src="<%= DataUtil.getBasePath(request) %>WebContent/js/validateKeys.js"></script>
