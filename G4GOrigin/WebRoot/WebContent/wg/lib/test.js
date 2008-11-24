/**
 * @author rick
 *
 *  test routines....
 */
//
//  test functions
//
$(document).ready(function(){
	
//	$("#search").removeClass("module");

//
//	initial setPositions() until this is persistant
//
	setInitialPositions();

	console.log("test.js document ready has run.");
});

function l(msg) {
	console.log(msg);
}

function jtest2() {
		$.get(ajaxPath+'JSONTest', 
		{
			wosid	:	wosid,
			tag		:	tag
		},
		function(rd) {
		console.log("jtest: "+rd);
		$(".UserListContainer").append(rd);

		aOrder = UserList.getOrder2();
		var top = parseInt($(aOrder[aOrder.length-1]).css("top")) +19;
		$("#user1000001").css("top", top+"px");

		UserList.setContainerHeight();
	});
}

function jtest() {
		$.getJSON(ajaxPath+'JSONTest', 
		{
			wosid	:	wosid,
			tag		:	tag
		});
}

function reload() {
	window.location.reload();
	clear();
}



var tt = 0;
var ttt = 0;
var delta = 0;

var o, a, s, e;

function repo_test() {
console.log("rt1");
	for(var i=1;i<UserList.aUsers.length;i++) {
console.log("rt2");
		var newTop = parseInt($(UserList.aUsers[i]).css("top")) - 19;
console.log("rt3");
		$(UserList.aUsers[i]).css("top", newTop+"px");	
	}
}

function movemany() {
	
}

function reposition() {
	o = UserList.getOrder();
	a = UserList.aUsers[o[0]];
	s = 1;
	e = UserList.aUsers.length;

	delta = parseInt($(UserList.aUsers[o[e-1]]).css("top")) - parseInt($(a).css("top"));
	
	$(a).css("background", "lightgray");

	for(var i=s; i<e;i++) {
		var newTop = parseInt($(UserList.aUsers[o[i]]).css("top")) - 19;
		$(UserList.aUsers[o[i]]).css("top", newTop + "px");
	}

	doRepos();
}

function doRepos() {
	if (delta <= 0) {
		$(a).css("background", "transparent");
		return;
	}
		
	$(a).css("top", (parseInt($(a).css("top"))+2) +"px");
	
	delta -= 2;
	
	setTimeout(doRepos, 50);
}


function ulpos() {
	var a = $(".UserDiv").get();
	for(var i=0; i<a.length;i++) {
		console.log("ulpos: "+i+": "+parseInt($(a[i]).css("top"))/19);
	}
}

function slideUsers() {
	if (delta <= 0) {
		$("#user2").css("background", "transparent");
		$("#user3").css("background", "transparent");
		return;
	}
	
	tt = parseInt(tt) + 2;
	ttt = parseInt(ttt) - 2;
	delta -=2;

	$("#user2").css("top", tt+"px");
	$("#user3").css("top", ttt+"px");

	setTimeout(slideUsers, 50);
	
	console.log("slideUsers: "+delta+": "+tt+": "+ttt);
}

function switchUsers() {
	tt = $("#user2").css("top");
	ttt = $("#user3").css("top");
	
	delta = parseInt(ttt) - parseInt(tt);

	$("#user2").css("background", "lightgray");
	$("#user3").css("background", "lightgray");
	
	slideUsers();
}

function initUserList() {
	var a = $(".UserDiv").get();
	$(".UserListContainer").css("height", (a.length*20)+"px");
	$(".UserDiv").css("position","absolute");
	for(var i=0; i<a.length;i++) {
		$(a[i]).css("top", (i*19)+"px");
		console.log("initUserList: "+a[i].id+": "+$(a[i]).css("top"));
	}

}

function afterMoveUserList() {
	var pw = $(".UserListContainer").css("width");
	$(".UserDiv").css("width", pw);	
}

function setInitialPositions() {
	var wa = $('.groupWrapper').get();	// get wrappers
	
	for(var w=0; w<wa.length; w++) {
		var ma = $(wa[w]).find('.module').get();
		var pos = 0;
		for(var m=0; m<ma.length; m++)
			$(ma[m]).attr({
				wrapper	:	$(wa[w]).attr('id'),
				pos		:	pos++
			});
	}
	console.log("setInitialPositions: has run...");
}

function saveError() {
			$.get(ajaxPath+'wa/Ajax/saveWidget',
			{
				tag		: 	tag,
				wosid	: 	wosid,
				widget	:	"fred",
				show	:	false,
				wrapper	:	"sort1",
				pos		:	0,
				tab		:	3
			}, function(rd) {
				$("#"+wid).attr({dirty:false});
				console.log("saveWidget: "+wid+": "+rd);
			});
}

function jsontest(stuff) {
	$.get(ajaxPath+"JSONTest",
		stuff, function(rd){
			console.log("jsontest: "+rd);
		}
	);
}

function findAllWidgets() {
	var wCount = 0;
	var sMsg = "";
    var wa = $('.groupWrapper').find('.module').get();
    for(var i=0; i<wa.length; i++) {
		sMsg = "widget #"+ ++wCount+": "+wa[i].id;
		sMsg += ": show: "+wa[i].getAttribute('show');
		sMsg += ": dirty: "+wa[i].getAttribute('dirty');
		sMsg += ": wrapper: "+wa[i].getAttribute('wrapper');
		sMsg += ": pos: "+wa[i].getAttribute('pos');
		sMsg += ": curtab: "+wa[i].getAttribute('curtab');
		
        console.log(sMsg);
	}
			
	console.log("findAllWidgets found: "+wCount+" widgets.");
}

function dumpWidget(wid) {
	var w = getId(wid);
    var s = "";
    s += "pos = "+w.getAttribute('pos') + "\n";
    s += "wrapper = "+w.getAttribute("wrapper") + "\n";
    s += "show = "+w.getAttribute("show") + "\n";
	s += "tab = "+w.getAttribute("tab") + "\n";
    s += "dirty = "+w.getAttribute("dirty") + "\n";
    
    console.log(s);
}

var new_friend = false;

function addFriend() {
    if (new_friend) {
        var mc = $("#myfriends").find(".user-list").get();
        mc[0].removeChild(getId('hotdish'));
        new_friend = false;
    } else {         
        loadFriend('hotdish');
        new_friend = true;
    }
        
}

function removeFriend() {
    if (!new_friend)
        return;
        
    var mc = $("#myfriends").find(".modcontent").get();
    
    mc[0].removeChild(getId('hotdish'));
    new_friend = false;
    $("#myfriends").Bounce(100);
}

function widgetJSON() {
    $.getScript("lib/widgetjson.js", function(rd) {
        console.log("widgetJSON(): ran...");
        console.log("wjdata: "+rd);
    });
}

function myFriendsPrefChange() {
console.log('myFriendsPrefChange(): '+$('#mfup').val()+" : "+getId('mfut').checked);

    var u = $('#mfup').val();
    var mfu = $('#myfriends').find('.avatar').get();
    
    for(var i=0; i<mfu.length; i++)
        if (i<u)
            $(mfu[i]).show();
        else
            $(mfu[i]).hide();

    if (getId('mfut').checked) {
        $("#myfriends").find('.featured-item').addClass("themeon");
        $("#myfriends").find(".option-themeson").toggle().parent().find(".option-themesoff").toggle();
    } else {
        $("#myfriends").find('.featured-item').removeClass("themeon");
        $("#myfriends").find(".option-themeson").toggle().parent().find(".option-themesoff").toggle();
    }

            
    $('#myfriends').attr({dirty:'true'});
}

function fixSafari() {
    $("#myachievements").find("span.modclose").hide();
    $("#myachievements").find(".modcontent").hide();
    $("#myfriends").find(".modprefs").hide();
    $("#myfriends").find("span.modopen").hide();
    console.log("fixSafari() ran.");
}

function loadFriend(friend) {
    $.get("includes/users/"+friend+".html", function(rd) {
        var nf = document.createElement('div');
        nf.id = friend;
        nf.setAttribute("id", nf.id);
        nf.innerHTML = rd;
        
        myFriendsOptions(nf);
        
        var mc = $("#myfriends").find(".user-list").get();
        mc[0].appendChild(nf);    
    });
console.log("loadFriend called..."+friend);
}

function repopMyFriends() {
    $("#myfriends").find(".modcontent").empty();
    new_friend = false;
    $.get('halogod.html', function(rd) {
        var nf = document.createElement('div');
        $(nf).addClass("featured-item").addClass('clearfix');
        nf.id = "halogod";
        nf.setAttribute("id", nf.id);
        nf.innerHTML = rd;
        var mc = $("#myfriends").find(".modcontent").get();
        mc[0].appendChild(nf);
    });
    $.get('bibby2smooth.html', function(rd) {
        var nf = document.createElement('div');
        $(nf).addClass("featured-item").addClass('clearfix');
        nf.id = "bibby2smooth";
        nf.setAttribute("id", nf.id);
        nf.innerHTML = rd;
        var mc = $("#myfriends").find(".modcontent").get();
        mc[0].appendChild(nf);
    });
    
}

function getjs2eval() {
    var js = prompt('enter js: ');
    eval(js);
}

function listChildNodes(o) {
   var s = "";
   var c = o.firstChild;
   while(c != null) {
       s += c.id + ", ";
       c = c.nextSibling;
   }
   return s;
}

function listAttrs(o) {
    var s = "";
    for ( i in o )
        s += i + ", ";
    return s;
}

function getId(oid) {
	return document.getElementById(oid);
}

console.log("test.js loaded.");