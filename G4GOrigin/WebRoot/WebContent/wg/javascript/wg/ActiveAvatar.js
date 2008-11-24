jQuery.fn.ActiveAvatar = function(settings) {

		//find all options and toggle them off
		$( ".avatar-options" ).toggle();



		$(".faded img, .faded em").css('opacity',0.4);
		
		//when you click on an avatar Image
		$(".avatar-image img").click(function(){
						
			//check if any other options are open

				$(".avatar-options").each(function(){
			
					if($(this).css("display")!="none"){
						$(this).slideToggle();
					}

				}).end();

				//Toggle the options open	
				$(this).parent().find(".avatar-options").slideToggle('slow').end();
			
		}).end();
		
		$(".avatar-close").click(function(){
			$(this).parent().parent().parent().slideToggle();
		}).end();

	
};