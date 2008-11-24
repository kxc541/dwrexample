
jQuery.fn.OpenClose = function(settings) {

	var cContainers = this;     
    
	settings = jQuery.extend({
		iconContainer: ".OpenClose",
		content: ".modcontent",
		openIcon: "/wg/css/images/icons/OpenDark.png",
		closedIcon: "/wg/css/images/icons/ClosedDark.png", 
		openClass: "Open",
		closedClass: "Closed",
	}, settings);
	
	$( "." + settings.closedClass ).find( settings.content ).toggle();
	
	return cContainers.each(function(){
		
			//This current dom element.
	        var jDomElem = this;
	        var headerDomElem = jQuery(settings.iconContainer, jDomElem);
	        var contentDomElem = jQuery(settings.content, jDomElem);
			
			
			
			//Put the plus/minus icon in to the iconContainer.
			if(contentDomElem.css("display")!="none") {
				headerDomElem.prepend('<img src="' + settings.openIcon + '" alt="" />');
            }

			if(contentDomElem.css("display")=="none") {
		        headerDomElem.prepend('<img src="' + settings.closedIcon + '" alt="" />');
            }
	
			
			
			headerDomElem.parent().click(function() {
				
				//if the content IS VISIBLE
				if(contentDomElem.css("display")!="none") {
					headerDomElem.find("img").attr("src", settings.closedIcon ).end();
	            	$(this).parent().parent().addClass( settings.closedClass );
				}
	
				// if the content IS NOT VISIBLE
				if(contentDomElem.css("display")=="none") {
					headerDomElem.find("img").attr("src", settings.openIcon ); 
					$(this).parent().parent().removeClass( settings.closedClass );
	            }

                //Show/hide the content.
                contentDomElem.slideToggle();
				jDomElm.toggle();
			
			});
	
	});


};

