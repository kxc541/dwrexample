<%@page import="com.g4g.utils.SessionUtil"%>

<div id="Feedback" class="Widget">
	<div class="WidgetHeader">
		<p>Feedback</p>
	</div>
	<div class="WidgetContent">
		<p><img src="WebContent/<%=SessionUtil.getSiteId(request) %>/css/images/icons/mail.png" style="border:1px solid #5c6662; margin-right:3px;"> Send us your feedback!</p>
		<form action="ajax/sendFeedback.do">
			<textarea name="feedback" id="feedback"></textarea><br><br>
			<input type="button" class="green" value="send feedback" onclick="test(this.form)">
		</form>
	</div>
</div> <!-- end Feedback -->