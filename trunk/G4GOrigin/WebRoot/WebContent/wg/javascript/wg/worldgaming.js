$(document).ready(function(){
	//hide all left column items except for mygames
	$("#sort1 div.modtitle").parent("#MyAccount").find(".modcontent").hide();
	//hide the search .modcontent
	$("#search .modcontent").hide();
	//hide all modpref panes
	$(".modprefs").hide();
	$("#featured-gamers .modcontent").hide();
	$("#featured-videos .modcontent").hide();

//	fix the show hide stuff

	$(".module").attr({show:true}); // set all true until hidden, ***TEMP***
	
	$("#MyAccount").attr({show:false});
	$("#featured-gamers").attr({show:false});
	$("#featured-videos").attr({show:false});
	
	
	//search for hidden and visible .modcontent then set the expand/collapse arrow accordingingly
	$(".modcontent:visible").parent("div").find("span.modopen").hide();
	$(".modcontent:hidden").parent("div").find("span.modclose").hide();

	// new mod open and close 
	$("span.modclose").click(function(){modClose(this)});
	$("span.modopen").click(function(){modOpen(this)});

	
/*	
	//ModClose function
	$("span.modclose").click(function(){
		$(this).parent("div").parent().find(".modcontent").slideUp(500);
		$(this).parent("div").find(".modopen").toggle();
		$(this).parent("div").find(".modclose").toggle();
		return false;
	});
	//ModOpen function
	$("span.modopen").click(function(){
		$(this).parent("div").parent().find(".modcontent").slideDown(500);
		$(this).parent("div").find(".modopen").toggle();
		$(this).parent("div").find(".modclose").toggle();
		return false;
	});
*/

	//delete function
	$(".delete").click(function(){
		$(this).parent().parent().hide(600);
		return false;
	});
	
	//open and close the prefrence pane
	$(".option-prefs").click(function(){modPrefs(this)});
/*		
	$(".option-prefs").click(function(){
		$(this).parent().parent().find(".modprefs").toggle(500);
		return false;
	});
*/	
	
	//OMG theme switching
	$(".option-themeson").click(function(){themeOn(this)});
	$(".option-themesoff").click(function(){themeOff(this)});
/*	
	$(".option-themeson").click(function(){
		$(this).parent().parent().find(".featured-item").addClass("themeon");
		$(this).parent().find(".option-themeson").toggle().parent().find(".option-themesoff").toggle();
		return false;
	});
	$(".option-themesoff").click(function(){
		$(this).parent().parent().find(".featured-item").removeClass("themeon");
		$(this).parent().find(".option-themesoff").toggle().parent().find(".option-themeson").toggle();
		return false;
	});
*/



	//post hover
	$("#news .post").hover(function(){
	     $(this).addClass("on").find(".faded").css({
		    filter: 'alpha(opacity=100)',
		    '-moz-opacity': '1.0',
		    opacity: '1.0'
		});
	   },function(){
	     $(this).removeClass("on").find(".faded").css({
		    filter: 'alpha(opacity=60)',
		    '-moz-opacity': '.60',
		    opacity: '.60'
		});
	   });
	
	
	//set a class for faded opacity
	$('.faded').css({
	    filter: 'alpha(opacity=40)',
	    '-moz-opacity': '0.4',
	    opacity: '0.4'
	});

	//function for fading all arrowsicons
	$('.arrowicon').css({
	    filter: 'alpha(opacity=40)',
	    '-moz-opacity': '0.4',
	    opacity: '0.4'
	});	
	//function for fading the icons in the message area
	$('.messageicon').css({
	    filter: 'alpha(opacity=40)',
	    '-moz-opacity': '0.4',
	    opacity: '0.4'
	});		
	
	//HOVER STATE FOR RED ARROW ICONS
	$("tr").hover(function(){
		$(this).find(".arrowicon").css({
		    filter: 'alpha(opacity=100)',
		    '-moz-opacity': '1.0',
		    opacity: '1.0'	
		});
	},function(){
		$(this).find(".arrowicon").css({
			filter: 'alpha(opacity=40)',
		    '-moz-opacity': '0.4',
		    opacity: '0.4'
		});
	});
	//HOVER STATE FOR THE MESSAGE CENTER
	$("#message_center .yui_content_options tr td").hover(function(){
		$(this).find(".messageicon").css({
		    filter: 'alpha(opacity=100)',
		    '-moz-opacity': '1.0',
		    opacity: '1.0'	
		});
	},function(){
		$(this).find(".messageicon").css({
			filter: 'alpha(opacity=40)',
		    '-moz-opacity': '0.4',
		    opacity: '0.4'
		});
	});

	
// drag and drop was here
	

 });