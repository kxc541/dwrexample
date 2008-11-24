/**
 * @author rick
 *
 *  CodeFab JS Routes
 */
 $(document).ready(function(){
    startDragAndDrop();
  });
 
 
function addWidget( url, parms, name, hb, pos, rollUp ) {
//console.log.log('addWidget called...'+url);
    $.get(url, parms, function(retData) {
        var w = document.createElement('div');
        w.id = name;
        w.setAttribute("id", name);
        w.className = "module";
        w.innerHTML = retData;
        $(w).attr({widget   :   true, 
                    horzBin :   "he"+hb, 
                    hbPos   :   pos,
                    rollUp  :   rollUp, 
                    dataSrc :   url, 
                    parms   :   parms,
                    dirty   :   false}
         );

         if ( rollUp == "true" || rollUp == true ) {
            $(w).find('span.modclose').hide();
            $(w).find('.modcontent').hide();         
            $(w).find('.option-prefs').hide();   
         } else {            
            $(w).find('span.modopen').hide();
         }
         // if there is a preferences pane, hide it
         $(w).find('.modprefs').hide();
         // add the onClick functions for various things.
         addOnClick(w);
         modSpecific(w);

         insertWidget(w);
         
         //console.log.log("AjaxCallback complete..."+url);
    });
}

function refreshWidget(wid) {
    var w = getId(wid);
    $.get($(w).attr('url'), {action:'refresh', parms:$(w).attr('parms')});
}

function saveWidget(wid) {
    var w = getId(wid);
    
//    $.post($(w).attr('url'), {action:'save', parms:$(w).attr('parms'), attrs:serializeWidgetAttributes(wid)});
    
    $("#"+wid).attr({dirty:false});
}

function removeWidget(wid) {
    if(!confirm("Are you sure? ("+wid+")"))
        return;
    var w = getId(wid);
    var h = getId($(w).attr('horzBin'));
//    $.post($(w).attr('url'), {action:'remove', parms:$(w).attr('parms')});
    h.removeChild(w);
    reorderBin(h);
}

function insertWidget(w) {
    //console.log.log("insertWidget() started..."+w.id);

    var h = getId(w.getAttribute("horzBin"));
    var p = parseInt(w.getAttribute("hbPos"));
    var m = $(h).find('.module').get();
//console.log.log("insertWidget() got ids and modules..."+h.id+"..."+p+"..."+m.length);

    if (m.length == 0 ) {
        h.appendChild(w);
    } else if ( p == 0 ) {    
        $(m[p]).before(w);
    } else {
        $(m[p-1]).after(w);
    }
        
        
//console.log.log("insertWidget() widget inserted into DOM..."+w.id);    
    $(h).SortableAddItem(w);
    //console.log.log("widget added as a sortable item..."+w.id);
    reorderBin(h);
    //console.log.log("reorderBin() completed..."+h.id+"..."+w.id);
    // this is the initial placement so it's not dirty
    $(w).attr({dirty:false});
    
    //console.log.log("insertWidget(): completed... "+w.id);
}

function serializeWidgetAttributes(wid) {
    var w = getId(wid);
    var s = "{";
    s += "horzBin:"+$(w).attr('horzBin')+",";
    s += "hbPos:"+$(w).attr('hbPos')+",";
    s += "rollUp:"+$(w).attr('rollUp')+"}";
    
    return s;
}

function saveAllWidgets() {
//  this function will process all widgets that need to be saved
//  and save them.
    var wa = $(".module").get();
    for(var i=0; i<wa.length; i++)
        if (wa[i].getAttribute("dirty") == "true")
            saveWidget(wa[i].id);
}

//
//  DOM and CSS functions - front end functionallity
//
function reorderAllBins() {
    reorderBin(getId('he0'));
    reorderBin(getId('he1'));
    reorderBin(getId('he2'));
}

function reorderBin(he) {
//  this function finds the places of the widgets and
//  sets the attributes correctly -- a sepearte state
//  svae needs to be done afterwards for any moved widgets.
    var m = $(he).find('.module').get();
    for(var i=0; i<m.length; i++)
        if( m[i].getAttribute("widget") == "true" ) {
            m[i].setAttribute("hbPos", i);
            m[i].setAttribute("horzBin", he.id);
            m[i].setAttribute("dirty", "true");
        }
}
// module specific stuff
function modSpecific(w) {
    if (w.id == "news") {
	// post hover
	$(w).find(".post").hover(function(){
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
    }
    
    if (w.id == "messagecenter") {
	// HOVER STATE FOR RED ARROW ICONS
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
	// HOVER STATE FOR THE MESSAGE CENTER
	$(w).find(".yui_content_options tr td").hover(function(){
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
    }
    
    if (w.id == "myfriends")
        myFriendsOptions(w);
    
    //console.log.log("modSpecific(): completed..."+w.id);
}

function myFriendsOptions(w) {
			$(w).find(".friend-card").hide();
			
			$(w).find(".avatar .open-usercard").click(function(){
				$(".friend-card").slideToggle(1000);
				return false;
			});


			$(w).find(".avatar .options").hide();

			$(".avatar").hoverIntent(function(){
					$(this).find(".options").slideToggle("slow");
			},function(){
                $(this).find(".options").slideToggle("slow");
			});
/*			
            $(w).find(".avator").hoverIntent({
                sensitivity: 1,
                interval: 750,
                over: function(){alert('hi');},
                timeout: 750,
                out:  function(){$(this).find(".options").slideToggle("slow");}
            });
*/		
			$(w).find(".fadebg").animate({opacity: .50}, 0);
			
			$(w).find("img.close").click(function(){
				$(this).parent().parent().slideUp("slow");
			})
			
			$(w).find(".friend").click(function(){
				$(this).parent().parent().parent().find(".featured-item:first").slideToggle(1500);
				return false;
			});
}
function togOut() {
    //console.log.log(this);
}
function addOnClick(w) {
// delete function
    //$(w).find('.delWidget').get().href = "javascript:removeWidget('"+w.id+"')";

// this function adds the open close functions
// to a widget - WARNING: only run once!
    $(w).find('span.modopen').click(function() {
        $(this).parent('div').parent().find('.modcontent').toggle(500); //slideDown
        $(this).parent('div').find('.modopen').toggle();
        $(this).parent('div').find('.modclose').toggle();
        $(this).parent('div').find('.option-prefs').toggle();
        
        $(this).parent().parent().attr({rollUp:false,dirty:true});
        return false;
    });
    $(w).find('span.modclose').click(function() {
        $(this).parent('div').parent().find('.modcontent').toggle(500); //slideUp
        $(this).parent('div').find('.modopen').toggle();
        $(this).parent('div').find('.modclose').toggle();
        $(this).parent('div').find('.option-prefs').toggle();
        
        $(this).parent().parent().attr({rollUp:true,dirty:true});
        return false;
    });
// preferences pane onClick
//open and close the prefrence pane
	$(w).find(".option-prefs").click(function(){
		$(this).parent().parent().find(".modprefs").toggle(500);
//		$(this).parent().parent().find(".modcontent").toggle(500);
		return false;
	});
//OMG theme switching
	$(w).find(".option-themeson").click(function(){
		$(this).parent().parent().find(".featured-item").addClass("themeon");
		$(this).parent().find(".option-themeson").toggle().parent().find(".option-themesoff").toggle();
		getId("mfut").checked = true;
		$(this).parent().parent().attr({dirty:true});
		//console.log.log('theme ON: '+$(this).parent().parent().attr('id'));
		return false;
	});
	$(w).find(".option-themesoff").click(function(){
		$(this).parent().parent().find(".featured-item").removeClass("themeon");
		$(this).parent().find(".option-themesoff").toggle().parent().find(".option-themeson").toggle();
		getId("mfut").checked = false;
		$(this).parent().parent().attr({dirty:true});
		//console.log.log('theme OFF: '+$(this).parent().parent().attr('id'));
		return false;
	});
	
	//set a class for faded opacity
	$(w).find('.faded').css({
	    filter: 'alpha(opacity=40)',
	    '-moz-opacity': '0.4',
	    opacity: '0.4'
	});

	//function for fading all arrowsicons
	$(w).find('.arrowicon').css({
	    filter: 'alpha(opacity=40)',
	    '-moz-opacity': '0.4',
	    opacity: '0.4'
	});	
	//function for fading the icons in the message area
	$(w).find('.messageicon').css({
	    filter: 'alpha(opacity=40)',
	    '-moz-opacity': '0.4',
	    opacity: '0.4'
	});		
	
	//HOVER STATE FOR RED ARROW ICONS
	$(w).find("tr").hover(function(){
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
	
	
	
//console.log.log("addOnClick() conpleted..."+w.id);    
}

function startDragAndDrop() {
			//drag drop
			$('div.groupWrapper').Sortable(
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
						reorderAllBins();
					}
				}
			);
			
			//console.log.log("Drag and Drop started.");
}

//
//  utility functions
//
function getId(oid) {
    return document.getElementById(oid);
}

//console.log.log("cfablib.js loaded.");