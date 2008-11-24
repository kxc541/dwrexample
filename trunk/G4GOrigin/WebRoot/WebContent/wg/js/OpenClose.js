
jQuery.fn.OpenClose = function() {
	var cContainers = this;  

	$(".WidgetHeader").click(function(){
		$(this).parent().find(".WidgetContent").slideToggle('fast');
	})

};

