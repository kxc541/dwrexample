/**
 * @author rick 
 *
 * g4glib.js - JavaScript functionality for World Gaming 
 */
//
//	setup until more functionality is fleshed out
//
var wosid = null;
var tag = null;
var ajaxPath = null;
var daPath = null;

//
//	global callbacks
//
$(document).ajaxError(function(req, xhr){
	//console.log("ajaxError: "+xhr.responseText);
});

$(document).ajaxSuccess(function(reg, xhr){
	//console.log("ajaxSuccess: "+leftTrim(xhr.responseText.substr(0,50)));
});

$(document).ready(function(){
//
//	get application path
//
	var apa = window.location.pathname.split("/");
	daPath = apa[0]+"/"+apa[1]+"/"+apa[2]+"/"+apa[3]+"/wa/"
	ajaxPath = daPath + "Ajax/";
	//console.log("ajaxPath = "+ajaxPath+": daPath = "+daPath);

	//	setup tabbed widgets	
	$.tabs('myprofile-tabs');
	$.tabs('myaccount-tabs');
	$.tabs('mygames-tabs');
	$.tabs('mymessages-tabs');
	
//	setUpDefText();

	processModalLinks();

	memberStats();
	
//	periodicUpdate();

	startDragAndDrop();

	globalNavSelected();

//	conditional loads (Safari bug)

});
//
//	GlobalNav menu stuff
//
function globalNavSelected() {
	$("#globalnav").find("li").removeClass("on");
	
//	if ($("#userAccountWidget").find("a[@href=/cgi-bin/WebObjects/G4G.woa/wa/UserActions/loginPage]").get(0))
//		return;
	// we're on the home page if the message center widget is there
//	if ($("#message_center").get().length > 0)
//		$("#gNavHome").addClass("on");
		
	if (window.location.search.indexOf("PS") > -1)
		$("#gNavGames").addClass("on");
		
	if (window.location. search.indexOf("XB") > -1)
		$("#gNavGames").addClass("on");
		
	if (window.location.pathname.indexOf("messageCenter") > -1)
		$("#gNavMessage").addClass("on");
			
	if (window.location. search.indexOf("FAQ") > -1)
		$("#gNavFAQs").addClass("on");
			
	if (window.location. search.indexOf("About") > -1)
		$("#gNavAbout").addClass("on");
			
	if (window.location.pathname.indexOf("home") > -1)
		$("#gNavHome").addClass("on");
			
	//console.log("globalNavSlected: has run.");
}
//
//  widget functionality 
//
function modOpen(t) {
// This expands a widget and switchs the arrows
//  and it unhides the Preferences and other title bar options
//
        $(t).parent('div').parent().find('.modcontent').toggle(500); //slideDown
        $(t).parent('div').find('.modopen').toggle();
        $(t).parent('div').find('.modclose').toggle();
        $(t).parent('div').find('.option-prefs').toggle();
// this sets the "show" attribute and the "dirty" attribute
// saving the widget can be done here or elsewhere                
        $(t).parent().parent().attr({show:false,dirty:true});
// send the action back to the server (test)
		saveWidget($(t).parent().parent().attr('id'));
        return false;
}

function modClose(t) {
// This collapses a widget and switches the arrows
//  and it hides the Preferences and other title bar options
//
        $(t).parent('div').parent().find('.modcontent').toggle(500); //slideUp
        $(t).parent('div').find('.modopen').toggle();
        $(t).parent('div').find('.modclose').toggle();
        $(t).parent('div').find('.option-prefs').toggle();
// this sets the "show" attribute and the "dirty" attribute
// saving the widget can be done here or elsewhere        
        $(t).parent().parent().attr({show:true,dirty:true});
		// send the action back to the server (test)
		saveWidget($(t).parent().parent().attr('id'));
        return false;
}

function modPrefs(t) {
// This opens or closes the preference pane
//
		$(t).parent().parent().find(".modprefs").toggle(500);
		// originally the content pane was hidden when the preference 
		// pane was shown. (rcw)
//		$(t).parent().parent().find(".modcontent").toggle(500);
		return false;
}

function themeOn(t) {
// This turns on themes
//
		$(t).parent().parent().find(".featured-item").addClass("themeon");
		$(t).parent().find(".option-themeson").toggle().parent().find(".option-themesoff").toggle();
		
		getId("mfut").checked = true; // this line is only for the test preference pane code
		
// this sets the "dirty" attribute saving can be done here or elsewhere		
		$(t).parent().parent().attr({dirty:true});
		return false;
}

function themeOff(t) {
// This turns off themes
//
		$(t).parent().parent().find(".featured-item").removeClass("themeon");
		$(t).parent().find(".option-themesoff").toggle().parent().find(".option-themeson").toggle();
		
		getId("mfut").checked = false; // this line is only for the test preference pane code
		
// this sets the "dirty" attribute saving can be done here or elsewhere		
		$(t).parent().parent().attr({dirty:true});
		return false;
}

function setPositions() {
	var wa = $('.groupWrapper').get();	// get wrappers
	
	for(var w=0; w<wa.length; w++) {
		var ma = $(wa[w]).find('.module').get();
		var pos = 0;
		for(var m=0; m<ma.length; m++) {
			if ($(ma[m]).attr('pos') != pos.toString() ||$(ma[m]).attr('wrapper') !=  $(wa[w]).attr('id')) {
				
				//console.log("setPositions: " + $(ma[m]).attr('id')+": " + $(ma[m]).attr('wrapper')+":"+$(wa[w]).attr('id')+": " + $(ma[m]).attr('pos')+":"+pos);
							
				$(ma[m]).attr({
					dirty	:	true,
					wrapper	:	$(wa[w]).attr('id'),
					pos		:	pos
				});
			}
			pos++;
		}
	}
	
	saveAllWidgets();
}

function selectAvatar(thisObj) {
	//console.log("selectAvatar: "+thisObj);
	$(".AvatarContainer").find("img").removeClass("sel");
	$("#avatarPath").val($(thisObj).attr("src"));
	$(thisObj).addClass("sel");
	FormValid.checkAll();
	return false;
}

//
//	Ajax Calls
//
function periodicUpdate() {
	$.getJSON(ajaxPath+'periodicUpdate',
		function() {
			//console.log('periodocUpdate: ');
			
			setTimeout(periodicUpdate, 60000);
		}
	);
}

function memberStats() {
	if ($("#userstats").get().length ==0)
		return;	

	$.getScript(ajaxPath+'memberStats', 
		function(jsonData) {
			//console.log('updateStats: has run.');
		
			setTimeout(memberStats, 60000);
	});
}

function updateStats(reg, online) {
	$("#regusers").html(reg);
	$("#onusers").html(online);
}

function saveAllWidgets() {
	var wa = $('.module').get();
	var was = "";
	var wlist = "wlist=";
	var cswlist = "";
	for (var i=0; i<wa.length; i++)
		if (wa[i].id != "search" && $(wa[i]).attr('dirty') == "true") {
			wlist += wa[i].id + ",";
			cswlist += "#"+wa[i].id+",";
			was += serializeWidget(wa[i].id) + "&";
		}
		
	if (was != "")
		$.post(ajaxPath+'saveAllWidgets', was+"&"+wlist, 
			function(rd) {
//				$('.module').attr({dirty:false});
				$(cswlist).attr({dirty:false});
				//console.log("saveAllWidgets: "+rd+": "+cswlist);	
			});
}

function serializeWidget(wid) {	
	var w = $("#"+wid);
	var s = wid +"=";
	s += w.attr('wrapper') +",";
	s += w.attr('pos') + ",";
	s += w.attr('show') + ",";
	s += w.attr('tab');

	return s;
}

function saveDirtyWidgets() {
	var wa = $('.module').get();
	for (var i=0; i<wa.length; i++)
		if ($(wa[i]).attr('dirty') == "true")
			saveWidget($(wa[i]).attr('id'));
			
	//console.log("saveDirtyWidgets(): run.");
}

function saveWidget(wid) {
		$.post(ajaxPath+'saveWidget',
			{
				widget	:	wid,
				show	:	$("#"+wid).attr('show'),
				wrapper	:	$("#"+wid).attr("wrapper"),
				pos		:	$("#"+wid).attr("pos"),
				tab		:	$("#"+wid).attr("tab")
			}, function(rd) {
				$("#"+wid).attr({dirty:false});
				//console.log("saveWidget: "+wid+": "+rd);
			});
}


//
//	message center
//
function messageCenterTab(thisObj) {
	if ($(thisObj).is(".on"))
		return;

	$(".mcTab").removeClass("on");
	$(".messageList").hide();
	
	var targetTab = thisObj.id.replace(/Tab/,"");
	//console.log("messageCenterTab: "+thisObj.id);
	
	$("#"+targetTab).show();
	$(thisObj).addClass("on");
}

function initUserMessageWidget() {
	var aMessages = $(".MessageContainer").get();
	
	for (var i=0; i<aMessages.length; i++) {
		var msgLink = $(aMessages[i]).find('a').get(0);
		var url = daPath+"showObjectAjax?"+msgLink.href.substr(msgLink.href.indexOf("?")+1);
		msgLink.href = "#";
		
		$(msgLink).parent().parent().click(function() {messageOpenClose(this, url)});;
	}
}

function initMessageList() {
	var aMessages = $(".MessageContainer").get();
	
	for (var i=0; i<aMessages.length; i++) {
		var msgLink = $(aMessages[i]).find('a').get(0);
		//cflog(msgLink.href);
		var url = daPath+"showObjectAjax?"+msgLink.href.substr(msgLink.href.indexOf("?")+1);
		//cflog(url);
		msgLink.href = "#";
		
		$(msgLink).parent().parent().click(function() {messageOpenClose(this, url)});
	}
	
	$(".mcTab").click(function() {messageCenterTab(this);});
	
	$("#newTab").addClass("on");
	$("#new").show();
}

function messageOpenClose(thisObj, url) {
	var msg = $(thisObj).parent().parent().parent().get(0);
	
	//cflog("id is "+$(thisObj).attr('id'));
	var msgLink = $(msg).find('a').get(0);
	//cflog(msgLink.href);
	var url2 = daPath+"showObjectAjax?"+msgLink.href.substr(msgLink.href.indexOf("?")+1);
	var url2 = daPath+"showObjectAjax?id="+$(thisObj).attr('id')+"&entityName=InternalMessage";
	//cflog(url2);

	if ($(msg).find(".MessageNav").get(0) == null)
		$.get(url2, function(retData) {
			$(msg).find(".MyMessage").append(retData.substr(19, retData.length-5)).slideToggle();
			processModalLinks();
		});
	else
		$(msg).find(".MyMessage").slideToggle();
}

function messageClose(thisObj) {
	$(thisObj).parent().parent().slideToggle();
}

//
//	utility functions
//
function cflog(msg) {
	if (window.console.log)
		window.console.log(msg);
	else if (console.log)
		console.log(msg);
}
function leftTrim(str) {
	for (var i=0; i<str.length; i++) {
		if (str[i] != " " && str[i] != '\r' && str[i] != '\n' && str[i] != "\t")
			return str.substr(i);
	}
	return str;
}

function processModalLinks() {
	//console.log("processModalLinks:");
	var aCards = $(".modal").get();
	var normSize = "&height=1000&width=650";
	var ieSize = "&height=1000&width=650";
	var sizeStr;
	if (navigator.userAgent.indexOf("MSIE") == -1) {
		sizeStr = normSize;
		//console.log("processModalLinks: not-IE");
	} else {
		sizeStr = ieSize;
		//console.log("processModalLinks: IE");
	}
	//console.log("processModalLinks: "+sizeStr);
	for (var i=0;i<aCards.length;i++) {
	    if ($(aCards[i]).attr("href").indexOf("TB_iframe") == -1) {
		$(aCards[i]).addClass("thickbox");
		$(aCards[i]).attr("href",
			$(aCards[i]).attr("href")+"&KeepThis=true&TB_iframe=true"+sizeStr+"&modal=true"	
		);
		tb_init(aCards[i]);
	    }
	}
}

function stopModalSession(refreshPage) {
	//console.log("stopModalSession: "+refreshPage);
	$.get(ajaxPath+'stopModal');
		
	if (refreshPage)
		window.location.reload();	
}

function gameContainerMouseOver() {
	return;
	//console.log("gameContainerMouseOver:");
	var imgElement = this.firstChild.nextSibling;
	if (imgElement.src.indexOf("blank.png") == -1)
		$(this).addClass("sel");
}

function gameContainerMouseOut() {
	return;
	//console.log("gameContainerMouseOut:");
	$(this).removeClass("sel");
}
//
// default text 
//
function setUpDefText() {
	// this function sets up the default
	// text behavior for input fields
	var ia = $("input.cleardeftext").get();
	for(var i=0; i<ia.length; i++) {
		//console.log("setUpDefText: "+ia[i].name);
		
		if ($(ia[i]).attr('defText') == undefined)
			$(ia[i]).attr({defText:ia[i].value});
		else if (ia[i].value == "")
			ia[i].value = $(ia[i]).attr('defText');
			
		$(ia[i]).blur(setDefText);
		$(ia[i]).focus(clearDefText);
	}
}
function clearDefText() {
	//console.log("clearDefText: "+this.name);
	// this function clears the default text
	// onFocus handler
	if (this.value == $(this).attr('defText'))
		this.value = ""; 
}

function setDefText() {
	//console.log("setDefText: "+this.name);
	// this function sets the feault text
	// onBlur handler
	if (this.value == "")
		this.value = $(this).attr('defText');
}

function defTextOnSubmit() {
	var aText = $(".cleardeftext").get();
	for (var i=0;i<aText.length;i++)
		if (aText[i].value == $(aText[i]).attr('defText'))
			aText[i].value = "";
}

function defTextOnError() {
	var aText = $(".cleardeftext").get();
	for (var i=0;i<aText.length;i++)
		if (aText[i].value == "")
			aText[i].value = $(aText[i]).attr('defText');	
}
//
// plugins
//
$.tabs = function(containerId, start) {
//console.log("$.tabs: "+containerId);
    var ON_CLASS = 'tabs-selected';
    var id = '#' + containerId;
    var i = (typeof start == "number") ? start - 1 : 0;
    $(id + '>div:lt(' + i + ')').add(id + '>div:gt(' + i + ')').hide();
	
//    $(id + '>ul>li:nth-child(' + i + ')').addClass(ON_CLASS);
// fix for seemingly broken nth-child selector in jquery 1.1.3	
	$(id + '>ul>li:eq('+i+')').addClass(ON_CLASS);
	
    $(id + '>ul>li>a').click(function() {
        if (!$(this.parentNode).is('.' + ON_CLASS)) {
            var re = /([_\-\w]+$)/i;
            var target = $('#' + re.exec(this.href)[1]);
            if (target.size() > 0) {
                $(id + '>div:visible').hide();
                target.show();
                $(id + '>ul>li').removeClass(ON_CLASS);
                $(this.parentNode).addClass(ON_CLASS);
				
				$(target.parent().parent().parent()).attr({
					dirty	:	true,
					tab		: 	target.attr('id')
				});
				
				saveWidget(target.parent().parent().parent().attr('id'));
            } else {
                alert('There is no such container.');
            }
        }
        return false;
    });
};
//
//	setup routines
//
function startDragAndDrop() {	
	//drag drop
	/*$('div.groupWrapper').Sortable( //WBS commented out, js errors.
		{
			accept: 'module',
			helperclass: 'sortHelper',
			activeclass : 	'sortableactive',
			hoverclass : 	'sortablehover',
			handle: 'div.modtitle h1',
			tolerance: 'pointer',
			onChange : function(ser)
			{
			},
			onStart : function()
			{
				$.iAutoscroller.start(this, document.getElementsByTagName('body'));
			},
			onStop : function()
			{
				$.iAutoscroller.stop();
				setPositions();
				UserList.afterMove();
			}
		}
	);  */
	//console.log("startDragAndDrop: Drag and drop started.");
}
//
// //console.log msg for firebug
//
//console.log("g4glib.js loaded...");