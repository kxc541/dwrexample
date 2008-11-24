		$(document).ready(function(){
			$(".friend-card").hide();
			
			$(".avatar .open-usercard").click(function(){
				$(".friend-card").slideToggle(1000);
				return false;
			});


			$("#myfriends .avatar .options").hide();
			
			$(".avatar").hover(function(){
					$(this).find(".options").slideToggle("slow");
			},function(){
				$(this).find(".options").slideToggle("slow");
			});
			
			$("#myfriends .fadebg").animate({opacity: .50}, 0);
			
			$("#myfriends img.close").click(function(){
				$(this).parent().parent().slideUp("slow");
			})
			
			$("#myfriends .friend").click(function(){
				$(this).parent().parent().parent().find(".featured-item:first").slideToggle(1500);
				return false;
			});


		});

