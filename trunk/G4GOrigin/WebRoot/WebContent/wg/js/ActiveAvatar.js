jQuery.fn.ActiveAvatar = function(settings) {

		//find all options and toggle them off



		$(".faded img, .faded em").css('opacity', 0.4);
		
		//when you click on an avatar Image
		$(".AvatarImage img").click(function(){
						
			//check if any other options are open

				$(".AvatarOptions").each(function(){
			
				if($(this).css("display")!="none"){
						$(this).toggle();
					}

				}).end();

				//Now that everything is closed, Toggle the selected options open	
				$(this).parent().find(".AvatarOptions").toggle().end();
			
		}).end();
		
		$(".AvatarClose").click(function(){
			$(this).parent().parent().parent().toggle();
		}).end();

	
};