//
//	UserList.js	- UserListWidget Object
//
//	This object collects the methods and vars for the UserListWidget
//
var UserList = {
	//
	// constants
	//
	MOVE_AMOUNT:			19,
	MOVE_STEP:				4,
	ONLINE:					"/wg/images/green_dot.gif",
	OFFLINE:				"/wg/images/gray_dot.gif",
	AWAY:					"/wg/images/red_dot.gif",
	IDLE:					"/wg/images/yellow_dot.gif",
	RECEINT_USER_LIST:		"Recent User List",
	BUDDY_LIST:				"Buddy List",
	DEBUG:					true,
	LOG_LEVEL:				3,

	//
	// vars
	//
	aUsers:					null,
	aPosition:				null,	//	index to user array by position
	aUserPosition:			null,	// 	index to position by user array index
	aUserId:				null,	//	index to user array by userid
	aMove:					null,
	aMove2:					null,
	listContent:			null,
	aContentUsers:			null,
	aListContentPosition:	null,
	aListContentUserIds:	null,
	bBuddies:				false,
	moveAmount:				0,
	moveStep:				0,
	moveTimeout:			5,
	
	//
	//	methods
	//
	init:	function() {
		if (!UserList.exists())
			return;
		
		UserList.getArrays();
		
		UserList.setContainerHeight();
		
		UserList.setDivTops(UserList.aUsers);
		
		UserList.getArrays();

//		if ($("#UserListTitle").html() == UserList.RECENT_USER_LIST)
//			UserList.bBuddies = false;
//		else
//			UserList.bBuddies = true;

		if (UserList.aUsers.length == 0) {
			UserList.bBuddies = false;
			$("#UserListTitle").html(UserList.RECENT_USER_LIST);
		} else {
			UserList.bBuddies = true;
			$("#UserListTitle").html(UserList.BUDDY_LIST);
		}

		UserList.log(5, "UserList.init: bBuddies = "+UserList.bBuddies);
		
		UserList.afterMove();
		UserList.updateUserList();
		
		UserList.log(5, "UserList.init: initialized "+UserList.aUsers.length+" users in list.");
	},
	
	updateUserList:	function() {
		if (!UserList.exists())
			return;
		if (UserList.bBuddies)
			UserList.getBuddyListContent();
		else
			UserList.allUsersListContent();
			
		setTimeout(UserList.updateUserList, 60000);
	},
	
	getBuddyListContent:	function() {
		if (UserList.aUsers != null) {
			UserList.log(5, "getBuddyListContent: "+ajaxPath+'buddyListContent');
			$.get(ajaxPath+'buddyListContent',
			function(userListContent) {
				UserList.log(5, "UserList.getBuddyListContent: callback.");
				
				if (userListContent.indexOf("DOCTYPE") != -1 || userListContent.indexOf("No session for request") != -1) {
					UserList.log(5, "UserList.getBuddyListContent: error returned, not injecting response.");
					return;
				}
				
				UserList.listContent = document.createElement("DIV");
				$(UserList.listContent).addClass("UserListContainer");
				$(UserList.listContent).append(userListContent);
								
				UserList.getContentArrays();
				
				$(".UserListContainer").remove();

				$("#myfriends").find("div.modcontent").get()[0].appendChild(UserList.listContent);
				UserList.getArrays();
				UserList.setContainerHeight();
				UserList.afterMove();
			});
			
			UserList.log(5, "UserList.getBuddyListContent: ran");
		}
	},
	
	allUsersListContent:	function() {
		if (UserList.aUsers != null) {
			$.get(ajaxPath+'allUsersListContent',
			function(userListContent) {
				UserList.log(5, "UserList.allUsersListContent: callback.");
				
				if (userListContent.indexOf("DOCTYPE") != -1 || userListContent.indexOf("No session for request") != -1) {
					UserList.log(5, "UserList.allUsersListContent: error returned, not injecting response.");
					return;
				}
								
				UserList.listContent = document.createElement("DIV");
				$(UserList.listContent).addClass("UserListContainer");
				$(UserList.listContent).append(userListContent);
				
				UserList.getContentArrays();
				
				$(".UserListContainer").remove();
//				$("#myfriends").find("div.modcontent").append(UserList.listContent.outterHTML);

				$("#myfriends").find("div.modcontent").get()[0].appendChild(UserList.listContent);
				UserList.getArrays();
				UserList.setContainerHeight();
				UserList.afterMove();
			});
			
			UserList.log(5, "UserList.allUsersListContent: ran");
		}		
	},
	
	switchPositionsNew:	function(begin, end) {
		var aBlock = new Array(0);
		
		for (var i=parseInt(begin)+1; i<parseInt(end); i++)
			aBlock[aBlock.length] = UserList.aPosition[i];
			
		var moveDistance = (end - begin) * 19;

		return aBlock;
	},
	
	switchPositions:	function(begin, end) {
		var beginUser = UserList.aUsers[UserList.aPosition[begin]];
		var endUser = UserList.aUsers[UserList.aPosition[end]];
		var beginTop = $(beginUser).css("top");
		
		$(beginUser).css("top", $(endUser).css("top"));
		$(endUser).css("top", beginTop);
		
		
		UserList.log(5, "UserList.swichPositions: "+UserList.aUsers[aPosition[begin]].id+": "+UserList.aUsers[aPosition[end]].id);
	},
	
	setStatus:	function(userid, status) {
		$("#"+userid).find("IMG").get(0).alt = status;
		$("#"+userid).find("IMG").get(0).src = UserList[status];
		
		UserList.log(5, "UserList.setStatus: "+userid+":"+status+":"+UserList[status]);
	},
	
	add:		function(userid, html) {
		UserList.log(5, "UserList.add: userid = "+userid);
		
		$(".UserListContainer").append(html);
		
		UserList.log(5, "UserList.add: "+$(UserList.aUsers[UserList.aUserPosition.length-1]).css("top"));
		
		$("#"+userid).css(
			"top",
			(parseInt($(UserList.aUsers[UserList.aUserPosition.length-1]).css("top"))+19) +"px"
		);
		UserList.getArrays();
		UserList.setContainerHeight();
	},
	
	remove:		function(userid) {
		// do the effect and then remove on callback
		$("#"+userid).fadeOut("slow", function() {
			UserList.removeCallback(userid);
		});
	},
	
	removeCallback:	function(userid) {
		var remPos = UserList.getPosition(userid);
		UserList.aMove = new Array(0);
		UserList.moveAmount = UserList.MOVE_AMOUNT;
		UserList.moveStep = - UserList.MOVE_STEP;
		var newIndex = 0;
		
		$("#"+userid).remove();
		UserList.getArrays();
				
		for (var i=parseInt(remPos+1); i<UserList.aPosition.length; i++) {
			UserList.aMove[newIndex++] = UserList.aPosition[i];
		}
		
		UserList.removeMove();
						
		UserList.log(5, "UserList.remove: "+userid);
	},
	
	removeMove:		function() {
		if (UserList.moveAmount <= 0) {
			UserList.setContainerHeight();
			return;
		}

		for (var i=0; i<UserList.aMove.length; i++) {
			$(UserList.aUsers[UserList.aMove[i]]).css("top",
				parseInt(parseInt($(UserList.aUsers[UserList.aMove[i]]).css("top")) + UserList.moveStep) +"px"
			);
		}
	
		$(".UserListContainer").css("height", 
			parseInt($(".UserListContainer").css("height")) + UserList.moveStep    +"px"
		);
		
		UserList.moveAmount += UserList.moveStep;
		setTimeout(UserList.removeMove, UserList.moveTimeout);
	},
//
//
//
	getTagsForStatus:	function(status) {
		var aTags = new Array(0);
		for (var i=0; i<UserList.aUsers.length;i++) {
			if ($(UserList.aUsers[i]).find("IMG[@alt="+status+"]").length)
				aTags[aTags.length] = $(UserList.aUsers[i]).find("A").html();
		}
		return aTags;
	},
	
	getTagForUserId:	function(userid) {
		return $("#"+userid).find("A").html();
	},
	
	getTagForPosition:	function(position) {		
		return $(UserList.aUsers[UserList.getOrder(UserList.aUsers)[position]]).find("A").html();
	},
	
	getStatus:		function(userid) {
		UserList.log(5, "UserList.getStatus: "+userid+" = "+$("#"+userid).find("IMG").attr("alt"));
		return $("#"+userid).find("IMG").attr("alt");
	},
	
	getPosition:	function(userid) {
		var aOrder = UserList.getOrder(UserList.aUsers);
		for (var i=0;i<UserList.aUsers.length;i++)
			if (UserList.aUsers[i].id == userid)
				return aOrder[i];
	
		return -1;
	},
	
	getFirstOffline:	function() {
		var aStatus = UserList.getStatusAll();
		
		for (var i=0; i<aStatus.length;i++)
			if (aStatus[i] == "OFFLINE") {
				return i;
			}
	
		return -1;
	},
	
	setDivTops:	function (userArray) {
		$(userArray).css("position","absolute");
		for (var i=0; i<userArray.length;i++) {
			$(userArray[i]).css("top", (i*19)+"px");
			UserList.log(5, "UserList.setDivTops: "+userArray[i].id+": "+$(userArray[i]).css("top"));
		}
	},
	
	getOrder:	function(userArray) {
		var aRet = new Array(0);
		
		for (var i=0; i<userArray.length;i++) {
			aRet[parseInt(parseInt($(userArray[i]).css("top"))/19)] = i;
		}
		
		return aRet;
	},

	getOrder2:	function(userArray) {
		var aRet = new Array(0);
		
		for (var i=0; i<userArray.length;i++) {
			aRet[i] = parseInt(parseInt($(userArray[i]).css("top"))/19);
		}
		
		return aRet;
	},
	
	getStatusAll:	function() {
		var aRet = new Array(0);
		
		for (var i=0; i<UserList.aUsers.length;i++){
			aRet[parseInt(parseInt($(UserList.aUsers[i]).css("top"))/19)] = $(UserList.aUsers[i]).find("IMG").attr("alt");
		}
		
		return aRet;
	},
	
	getUserIds:		function(userArray) {
		var aUserIds = new Array(0);
		for (var i=0;i<userArray.length;i++) {
			aUserIds[$(userArray[i]).attr("id")] = i;
		}
			
		return aUserIds;
	},

	
	setContainerHeight:	function() {
		$(".UserListContainer").css("height", (UserList.aUsers.length*20)+"px");		
	},
//
//	main array methods
//
	getArrays:	function() {
		UserList.getUsersArray();
		UserList.getOrderArray();
		UserList.getOrder2Array();
		UserList.aUserIds = UserList.getUserIds(UserList.aUsers);
	},
	
	getUsersArray:	function() {
		return (UserList.aUsers = $(".UserDiv").get());
	},
	
	getOrderArray:	function() {
		return (UserList.aPosition = UserList.getOrder(UserList.aUsers));
	},
	
	getOrder2Array:	function() {
		return (UserList.aUserPosition = UserList.getOrder2(UserList.aUsers));
	},
	
//
//	content array methods
//	
	getContentArrays:	function() {
		UserList.getContentUsers();
		UserList.setDivTops(UserList.aContentUsers);
		UserList.getContentOrder();
		UserList.getContentUserIds();
	},
	
	getContentUsers:	function() {
		return UserList.aContentUsers = $(UserList.listContent).find(".UserDiv").get();
	},
	
	getContentOrder:	function() {
		return UserList.aListContentPosition = UserList.getOrder(UserList.aContentUsers);
	},
	
	getContentUserIds:	function() {
		return UserList.aListContentUserIds = UserList.getUserIds(UserList.aContentUsers);
	},
//
//
//
	log:		function(level, msg) {
		if (UserList.DEBUG || level <= UserList.LOG_LEVEL)
			console.log.log(msg);
	},

	exists:		function() {
		if(document.getElementById("myfriends") == null)
			return false;
		else
			return true;
	},
	
	afterMove:	function() {
		$(".UserDiv").css("width", $(".UserListContainer").css("width"));
	}
};

$(document).ready(UserList.init);