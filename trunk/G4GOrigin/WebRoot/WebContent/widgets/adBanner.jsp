<%@page import="com.g4g.utils.SessionUtil"%>
<% boolean isNarrow = (request.getParameter("NARROW") != null); %>

<div id="SidebarAdSpace" class="Widget" align="center">
	<div class="BannerAds">
		<img src="WebContent/<%=SessionUtil.getSiteId(request) %>/css/images/ads/<%= (isNarrow) ? "promo-celebrity1-narrow.jpg" : "promo-celebrity2.jpg" %>">
	</div>
	<br>
	<div class="BannerAds">
		<img src="WebContent/<%=SessionUtil.getSiteId(request) %>/css/images/ads/<%= (isNarrow) ? "promo-tigerwoods08-narrow.jpg" : "promo-nbalive08.jpg" %>">
	</div>
</div> <!-- end SidebarAdSpace -->