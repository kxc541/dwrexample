<%@page import="com.g4g.utils.G4GConstants"%>
<%@page import="com.g4g.utils.BlogUtils"%>

<div class="module" id="news">
	<div id="MediaCenter" class="Widget">
		<div class="WidgetHeader"><p>World Gaming Media Center</p></div>
		<div class="WidgetContent">
			<div class="Format1 clearfix">
				<div class="TabNav clearfix">
					<ul class="idTabs">
						<li class="selected">
							<div class="BreakingNewsTimer" id="breakingNewsContent">
								<%
								if(request.getAttribute(G4GConstants.BREAKINGNEWS)!=null){
									out.println(request.getAttribute(G4GConstants.BREAKINGNEWS));
									}
								%>
							</div>

						</li>
					</ul>
				</div>
				<div class="TabContent">
					<div id="GamingNews">
						<p class="Format3 clearfix">
							<img src="WebContent/wg/css/images/icons/more.png">
							<a href="<%=BlogUtils.getBreakingNewsLink()%>">Read more
								Gaming News</a>
						</p>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>