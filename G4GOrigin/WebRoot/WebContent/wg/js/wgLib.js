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

var syncPlayNow = true;
var syncBreakingNews = true;
var syncNotes = true;
var syncOnlineUsersForGame = true;
var syncOnlineDots = true;
var syncOpenTournamentsOfGame = true;
var syncMemStats = true;
var syncMailFolderCounts = true;

var onlineDotsTimer = null;
var onlineUsersForGameTimer = null;
var openTournamentsOfGameTimer = null;
var memStatsTimer = null;
var playItNowTimer = null;
var breakingNewsTimer = null;
var notesTimer = null;
var mailFolderCountsTimer = null;

var gamesCaption = "In order for other members to identify you and your gaming preferences, please provide the following information. you can always login and edit your settings once your registration is complete.";

function loadGameLobbyWidget() {
	if($('.GameLobbyTimer').length >= 1) {
		var xmlHttp = null;
		loadXMLDoc(xmlHttp, getAjaxPath()+"gameLobbyRefresh", ajaxUpdateGameLobby);
	}
}

function stopBreakingNewsTimer() {
	console.log("stop active player timer");
	if(breakingNewsTimer != null)
		breakingNewsTimer.stop();
}

function resetBreakingNewsTimer() {
	console.log("reset active player timer");
	if(breakingNewsTimer != null)
		breakingNewsTimer.reset(sixty);
}

/**
*	Active players timer
*/
function breakingNewsTimer1() {
	if($('.BreakingNewsTimer').length >= 1) {
		breakingNewsTimer = $.timer(sixty,
			function (timer) {
				if(syncBreakingNews) {
					syncBreakingNews = false;
					console.log("active player timer is running");
					var xmlHttp = null;
					loadXMLDoc(xmlHttp, getAjaxPath()+"breakingNewsRefresh", ajaxUpdateBreakingNews);
					syncBreakingNews = true;
					resetBreakingNewsTimer();
				}
			}
		); //end timer
	}
}

function test(form) {
	var validate = true;
	var feedback = $('#feedback').get(0).value;
	var errorMessage = '';

	if(jQuery.trim(feedback) == '') {
		errorMessage = 'Please, enter your feedback.';
		validate = false;
	}

	if(validate == true) {
		$.get(getAjaxPath()+"sendMyFeedback",
		{feedback:feedback},
		function(retData) {
			if(retData == "true") {
				notificationPrompt('Feedback has been sent successfully.');
			}
			else {
				notificationPrompt('Problem while sending your feedback.');
			}
		}
		); //end .get
	}
	else {
		notificationPrompt(errorMessage);
	}
}

function depositAmountToWG() {
	var index = $("#accountTypeList").get(0).selectedIndex;
	var amount = $("#depositAmount").get(0).value;
	var validate = true;
	var errorMessage = '';

	if(index < 1) {
		errorMessage += '<dl>Please select an account</dl>';
		validate = false;
	}

	if(amount == 0 || amount == '') {
		errorMessage += '<dl>Please provide valid amount</dl>';
		validate = false;
	}

	if(validate == true) {
		document.forms[0].action = document.forms[0].action + '?operation=make deposit';
		document.forms[0].submit();
	}
	else {
		notificationPrompt(errorMessage);
	}
}

function withdrawAmountFromWG() {
	var index = $("#withdrawMethodList").get(0).selectedIndex;
	var amount = $("#withdrawalAmount").get(0).value;

	var validate = true;
	var errorMessage = '';

	if(index < 1) {
		errorMessage += '<dl>Please select a withdrawal method</dl>';
		validate = false;
	}

	if(amount == 0 || amount == '') {
		errorMessage += '<dl>Please provide valid amount</dl>';
		validate = false;
	}

	if(validate == true) {
		document.forms[0].action = document.forms[0].action + '?operation=make withdrawal';
		document.forms[0].submit();
	}
	else {
		notificationPrompt(errorMessage);
	}
}

function loadXMLDoc(xmlhttp, url, handler)
{
	// code for Mozilla, etc.
	if (window.XMLHttpRequest)
	{
		xmlhttp=new XMLHttpRequest();
	}
	// code for IE
	else if (window.ActiveXObject)
	{
		xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
	}

	if (xmlhttp!=null)
	{
		xmlhttp.onreadystatechange=function() {handler(xmlhttp)};
		xmlhttp.open("GET",url,true);
		xmlhttp.send(null);
	}
	else
	{
		alert("Your browser does not support XMLHTTP.");
	}
}

function ajaxUpdatePicker(xmlhttp)
{
	ajaxUpdater("pickerUpdate", xmlhttp)
}

function ajaxUpdatePlayNow(xmlhttp)
{
	ajaxUpdater("playNowContent", xmlhttp)
}

function ajaxUpdateBreakingNews(xmlhttp)
{
	ajaxUpdater("breakingNewsContent", xmlhttp)
}

function ajaxUpdateGameLobby(xmlhttp)
{
	ajaxUpdater("gameLobbyContent", xmlhttp)
}

function ajaxUpdater(section, xmlhttp)
{
	// if xmlhttp shows "loaded"
	if (xmlhttp.readyState==4)
	{
		// if "OK"
		if (xmlhttp.status==200 || xmlhttp.status==0)
		{
			// console.log(document.getElementById(section).innerHTML);
			document.getElementById(section).innerHTML = xmlhttp.responseText;
		}
		else
		{
			// alert("Problem retrieving XML data");
		}
	}
}

//
//	global callbacks
//
$(document).ajaxError(function(req, xhr){
	// console.log("ajaxError: "+xhr.responseText);
});

$(document).ajaxSuccess(function(reg, xhr){
	// console.log("ajaxSuccess: "+leftTrim(xhr.responseText.substr(0,50)));
});

$(document).ready(function(){
	//
	//	get application path
	//
	// console.log("Running wgLib document ready function");
	var apa = window.location.pathname.split("/");
	daPath = apa[0]+"/"+apa[1]+"/"+apa[2]+"/"+apa[3]+"/wa/"
		ajaxPath = daPath + "ajax/";
	// console.log("ajaxPath = "+ajaxPath+": daPath = "+daPath);

	globalNavSelected();


	loadTimers();


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

	if (window.location.pathname.indexOf("MessageCenter") > -1)
		$("#gNavMessage").addClass("on");

	if (window.location. search.indexOf("FAQ") > -1)
		$("#gNavFAQs").addClass("on");

	if (window.location. search.indexOf("About") > -1)
		$("#gNavAbout").addClass("on");

	if (window.location.pathname.indexOf("home") > -1)
		$("#gNavHome").addClass("on");

	// console.log("globalNavSlected: has run.");
}


///
/// ajax calls
///

function getAjaxPath() {
	var apa = window.location.pathname.split("/");
	var daPath = apa[0]+"/"+apa[1]+"/"+apa[2]+"/"+apa[3]+"/wa/"
	var ajaxPath = daPath + "ajax/";

	ajaxPath = "ajax/";

	return ajaxPath

}

function isXBoxLiveTagAvailable() {
	// console.log("isXBoxLiveTagAvailable");
	var xboxTag = $("#xboxLiveTag").get(0).value;

	$.get(getAjaxPath()+"isXBoxLiveTagAvailable",
		{id:xboxTag},
		function(retData) {
			if(retData == "true") {
				$("#xboxAvailableMsg").html("");
			}
			else {
				$("#xboxAvailableMsg").html("XBox tag is not valid");
				$("#xboxLiveTag").get(0).value = "";
				//$("#xboxLiveTag").focus();
			}

		}
	); //end .get


}

function isPSTagAvailable() {
	// console.log("called isPSTagAvailable");
	var psTag = $("#psNetTag").get(0).value;

	$.get(getAjaxPath()+"isPSTagAvailable",
		{id:psTag},
		function(retData) {
			// console.log(":::retData:::" + retData);
			if(retData == "true") {
				$("#psAvailableMsg").html("");
			}
			else {
				$("#psAvailableMsg").html("PS tag is not valid");
				$("#psNetTag").get(0).value = "";
				//$("#psNetTag").focus();
			}
		}
	); //end .get


}

function xboxCheck(thisObj) {
	var aXboxChecks = $(".xbox").get();

	if (aXboxChecks[0].checked || aXboxChecks[1].checked) {
		$("#xboxLiveTag").show();
		$("#xboxLiveTag").focus();
	}
	else {
		$("#xboxLiveTag").hide();
		$("#xboxAvailableMsg").html("");
		//$("#xboxAvailableMsg").focus();
	}
	gamesTableDisplay();
	//show-hide xbox and xbox360 games
	if(aXboxChecks[0].checked) {
		$("#XBox").show();
	}
	else {
		$("#XBox").hide();
	}
	if(aXboxChecks[1].checked) {
		$("#XBox360").show();
	}
	else {
		$("#XBox360").hide();
	}
}

function psCheck(thisObj) {
	var aPlayStationChecks = $(".playStation").get();

	if (aPlayStationChecks[0].checked || aPlayStationChecks[1].checked) {
		$("#psNetTag").show();
		$("#psNetTag").focus();
	}
	else {
		$("#psNetTag").hide();
		$("#psAvailableMsg").html("");
		//$("body").focus();
	}
	gamesTableDisplay();
	//show-hide ps2 and ps3 games
	if(aPlayStationChecks[0].checked) {
		$("#PS2").show();
	}
	else {
		$("#PS2").hide();
	}
	if(aPlayStationChecks[1].checked) {
		$("#PS3").show();
	}
	else {
		$("#PS3").hide();
	}
}

function gamesTableDisplay() {
	var aPlayStationChecks = $(".playStation").get();
	var aXboxChecks = $(".xbox").get();

	if (aPlayStationChecks[0].checked || aPlayStationChecks[1].checked ||
	    aXboxChecks[0].checked || aXboxChecks[1].checked)
	{
		$("#gameSelection").show();
	}
	else
	{
		//$("#gameSelection").hide();
	}

}


// When clicking on a GameActiveAvatar in
// the GamePicker, this fills out the
// GameLobby
function loadGameLobby(selectedImage) {
	var gameId = selectedImage.id;
	$("#GamePicker").removeClass("showDiv").addClass("hideDiv");
	$("#gameDetails").removeClass("hideDiv").addClass("showDiv");

	// console.log("called loadGameLobby with id = " + gameId);

	$.get(getAjaxPath()+"gameDetailedDisplay",
		//{id:gameId,entityName:"game"}, commented out the entityName now that we're using the encodedGlobalID
		{id:gameId},
		function(retData) {
			// console.log(":::retData::: " + retData);
			$("#gameDetails").html(retData);
		}
	); //end .get
	$("#gameDetails").html("retData");

}

//When clicking on the
//"My Games" Picker menu, this updates the
//contents of the Game Picker div tag with
//games available for the user.
//Parameters: g4gTag is a unique identifier for the user.
function updatePickerWithUser(g4gTag) {
	//alert("The tag is: " + g4gTag);
	$("#GamePicker").removeClass("hideDiv").addClass("showDiv");
	$("#gameDetails").removeClass("showDiv").addClass("hideDiv");
	$("#gameRoomParagraph").removeClass("showDiv").addClass("hideDiv");

	var xmlHttp = null;
	loadXMLDoc(xmlHttp, getAjaxPath()+"userGames?tag="+g4gTag, ajaxUpdatePicker);
}


//When clicking on a gaming platform in
//the Picker, this updates the contents of
//the div tag with available games for the
//platform
//Parameters: platformId is a unique identifier
//            for the platform
function updatePickerWithPlatform(platformId) {
	// console.log("called updatePickerPlatform platformId: " + platformId);
	$("#GamePicker").removeClass("hideDiv").addClass("showDiv");
	$("#gameDetails").removeClass("showDiv").addClass("hideDiv");
	$("#gameRoomParagraph").removeClass("showDiv").addClass("hideDiv")

	var xmlHttp = null;
	loadXMLDoc(xmlHttp, getAjaxPath()+"platformGames?platformId="+platformId, ajaxUpdatePicker);
}


/*
* When in the Game Lobby user picks show games
* the picker is shown, and the lobby hidden
*/
function showGamePicker(g4gTag) {
	$("#GamePicker").removeClass("hideDiv").addClass("showDiv");
	$("#gameDetails").removeClass("showDiv").addClass("hideDiv");

	updatePickerWithUser(g4gTag);
	// console.log("called showGamePicker");
}

/*
* This is probably not called.   Why don't we
* comment it out and see if something breaks?
*/
function hideGamePicker() {
	$("#GamePicker").removeClass("showDiv").addClass("hideDiv");
	$("#gameDetails").removeClass("hideDiv").addClass("showDiv");
	//console("called showGamePicker");
}


//updates the div with a fully loaded and set up address component using the natCodeID parameter
function updateAddressComponent(natCodeID) {
	//alert("called address update");
	//var Index = document.getElementById("natCodeSelect").selectedIndex;
	var Index = $("#natCodeSelect").get(0).selectedIndex;

	//var natCodeID = document.getElementById("natCodeSelect").options[Index].value;
	var natCodeID = $("#natCodeSelect").get(0).options[Index].value;

	// console.log("called update address component with: " + natCodeID);
	$.get(getAjaxPath()+"addressComponentWithNationalCode", {id:natCodeID},
		  function(retData) {
			  //alert("Return at updateAddressComponent is: " + retData);
			  $("#addressComponent").html(retData);
		  }
	);
}


/*
* Sets the options in the state/provence
* select after a user selects a country
*/
function subNatCodesForNatCode(countrySelect) {
	var Index = $("#nationCodes").get(0).selectedIndex;
	var natCodeID = $("#nationCodes").get(0).options[Index].value;

	if(Index > 0) {
		$.get(getAjaxPath()+"subNatCodesForNatCode", {id:natCodeID},
			function(jsonData) {
				// console.log("jsonData is: " + jsonData);
				var retData = eval(jsonData);
				var options = '';
				//Note: for loop only goes to <length-1.
				//For some reason the last array element
				//is the country name itself.  The -1
				//cuts it off.
				for (var i = 0; i < retData.length-1; i++) {
					options += '<option value="' + retData[i].optionValue;
					if(i==0) {
						options += ' selected="selected"';
					}
					options += '">';
					options += retData[i].optionDisplay + '</option>';
					// console.log("The options is now: " + options);
				}
				$("#states").html(options);
				$("#states").get(0).selectedIndex=0;

			}
		);
	}
	else {
		$("#states").html('<option value="WONoSelectionString">Select State</option>');
	}
}

/*
* Sends the selected state to the backend.
*/
function setSubNatOnAddress(stateSelect) {
	var Index = $("#states").get(0).selectedIndex;
	var stateID = $("#states").get(0).options[Index].value;

	$.get(getAjaxPath()+"setSubNationalCodeForAddress", {id:stateID},
		function(retData) {
			//this call back doesn't do anything.
		}
	);

}


//this is a hack.  would be better to use jQuery validationfunction
function registrationFormValidateState(stateSelect) {
	var index = $("#subNat").get(0).selectedIndex;
	var subNatCodeID = $("#subNat").get(0).options[index].value;

	if(subNatCodeID == "WONoSelectionString") {
		$("#mustSelectState").html("A State/Province is required");
	}
	else {
		$("#mustSelectState").html("");
	}

}

//this is a hack.  would be better to use jQuery validation
function registrationFormValidateStreet1() {
	var v = $("#billingStreet").get(0).value;
	if(v == "" ) {
		$("#mustProvideStreet1").html("A street address is required");
	}
	else {
		$("#mustProvideStreet1").html("");
	}
}

//this is a hack.  would be better to use jQuery validation
function registrationFormValidateCity() {
	var v = $('#billingStreet').get(0).value;

	if(v == "" ) {
		$("#mustProvideCity").html("A city is required");
	}
	else {
		$("#mustProvideCity").html("");
	}
}

//this is a hack.  would be better to use jQuery validation
function registrationFormValidateZip() {
	if($("#billingZip").get(0).value == "" ) {
		$("#mustProvideZip").html("A postal code is required");
	}
	else {
		$("#mustProvideZip").html("");
	}
}



/**
* Gets the html for game matches
* @param formElement
* @param status
* @param view
* @return The rendered html string
*/
function gameMatchesWithStatusAndView(formElement, status, view) {
	var id = formElement.id;
	$.get(getAjaxPath()+"gameMatchesWithStatusAndView",
		{id:escape(id),status:status,view:view},
		function(retData) {
			// console.log(":::retData::: " + retData);
			//TODO: $("#CHANGEME").html(retData);

		}
	); //end .get
}


/**
* returns a string representing matches relating so a User with status
* @param id
* @param status
*/
function userMatchesWithStatusAndView(formElement) {
	var id = formElement.id;
	$.get(getAjaxPath()+"userMatchesWithStatusAndView",
		{TODO:"params"},
		function(retData) {
			// console.log(":::retData::: " + retData);
			//TODO: $("#CHANGEME").html(retData);

		}
	); //end .get
}

/**
* returns a string representing condensed views of recent match
* @param id
* @parma
*/
function recentMatchesForUserWithGame(formElement) {
	var id = formElement.id;
	$.get(getAjaxPath()+"recentMatchesForUsesrWithGame",
		{TODO:"params"},
		function(retData) {
			// console.log(":::retData::: " + retData);
			//TODO: $("#CHANGEME").html(retData);

		}
	); //end .get
}

/*
* returns a string representing Tournaments relating to the Game with status
* @param id
* @param status
*/
function gameTournamentsWithStatusAndViewDetail(formElement, status, view) {
	var id = formElement.id;
	$.get(getAjaxPath()+"gameTournamentsWithStatusAndViewDetail",
		{id:escape(id), status:status},
		function(retData) {
			// console.log(":::retData::: " + retData);
			//TODO: $("#CHANGEME").html(retData);

		}
	); //end .get
}

/**
* returns a string representing Tournaments relating so a User with status
* @param id
* @param status
*/
function userTournamentsWithStatusAndViewDetail(formElement) {
	var id = formElement.id;
	$.get(getAjaxPath()+"userTournamentsWithStatusAndViewDetail",
		{TODO:params},
		function(retData) {
			// console.log(":::retData::: " + retData);
			//TODO: $("#CHANGEME").html(retData);

		}
	); //end .get
}

/**
* returns a list of matches in Tournament waiting for an
* opponent. Needs to be called on a timer js method and manually
* @param gameID
*/
function openTournamentsOfGame(formElement) {
	var id = formElement.id;
	$.get(getAjaxPath()+"openTournamentsOfGame",
		{TODO:params},
		function(retData) {
			// console.log(":::retData::: " + retData);
			//TODO: $("#CHANGEME").html(retData);

		}
	); //end .get
}

/**
* returns the html of online Users for a particular game
* needs to be called on a timer.
*/
function onlineUsersForGame(formElement) {
	var id = formElement.id;
	$.get(getAjaxPath()+"onlineUsersForGame",
		{TODO:params},
		function(retData) {
			// console.log(":::retData::: " + retData);
			//TODO: $("#CHANGEME").html(retData);

		}
	); //end .get
}

/**
* adds a UserGame pref to the currentUser.
* @param idPre idPrefix is either "user" or "platform"
* @param gameId The current gameId.
*/
function addGameToCurrentUser(idPre, gameId) {

	$.get(getAjaxPath()+"addGameToCurrentUser",
		{idPre:idPre,id:gameId},
		function(retData) {
			// console.log(":::retData::: " + retData);
			if(idPre == "lobby") {
				$("#" + idPre + gameId).html("");
				window.location.reload();
			}else if(idPre == "user") {
				$("#" + idPre + gameId).html("");
			}else{
				$("#" + idPre + gameId).html(retData);
			}

		}
	); //end .get*/

	loadGameLobbyWidget();
	//show human message
	humanMsg.displayMsg("Game added");
	//$.prompt('Game added');
}

/**
* remove a UserGame pref to the currentUser
* @param idPre idPrefix is either "user" or "platform"
* @param gameId The current gameId
*/
function removeGameFromCurrentUser(idPre, gameId) {

	$.get(getAjaxPath()+"removeGameFromCurrentUser",
		{idPre:idPre,id:gameId},
		function(retData) {
			// console.log(":::retData::: " + retData);

			if(idPre == "lobby") {
				//removed game from my games, so delete it
				$("#" + idPre + gameId).html("");
				window.location.reload();
			} else if(idPre == "user") {
				//removed game from my games, so delete it
				$("#" + idPre + gameId).html("");
			}
			else {
				//removed game from a platform, so
				//replace the -avatar with +avatar
				$("#" + idPre + gameId).html(retData);
			}
		}
	); //end .get

	loadGameLobbyWidget();
	humanMsg.displayMsg("Game removed");
	//$.prompt('Game removed');
}

/**
* remove a UserGame pref to the currentUser
* @param idPre idPrefix is either "user" or "platform"
* @param gameId The current gameId
*/
function removeFriendFromCurrentUser(idPre, userId) {

	$.get(getAjaxPath()+"removeFriendFromCurrentUser",
		{idPre:idPre,id:userId},
		function(retData) {
			// console.log(":::retData::: " + retData);

			if(idPre == "user") {
				//removed game from my games, so delete it
				$("#" + idPre + userId).html("");
			}
			else {
				//removed game from a platform, so
				//replace the -avatar with +avatar
				$("#" + idPre + userId).html(retData);
			}
		}
	); //end .get

	humanMsg.displayMsg("Friend removed");
	//$.prompt('Game removed');
}

/**
* sends a friend request message to a user
*@param id the g4gTag of the user getting the request
*/
function sendFriendRequestToUserWithTag(id){
	$.get(getAjaxPath()+"sendFriendRequest",
		  {id:escape(id)},
		  function(retData) {
			  notificationPrompt(retData);
		  }
	);
}

function addUserWithTagAsFriend(id){
	$.get(getAjaxPath()+"addUserAsFriend",
		  {id:escape(id)},
		  function(retData) {
			  notificationPrompt(retData);
		  }
	);
}


/**
* Uploads an avatar image. We need a DA to handle the upload and
* give the data and status to the callback.  See Bill S. for
* details about the return format.
*/
function ajaxFileUpload()
{
	$("#loading")
	.ajaxStart(function(){
		$(this).show();
	})
	.ajaxComplete(function(){
		$(this).hide();
	});

	$.ajaxFileUpload
	(
		{
			//TODO: url should be of a DirectAction
			url:'doajaxfileupload.wo',
			secureuri:false,
			fileElementId:'fileToUpload',
			dataType: 'json',
			success: function (data, status)
			{
				if(typeof(data.error) != 'undefined')
				{
					if(data.error != '')
					{
						alert(data.error);
					}else
					{
						alert(data.msg);
					}
				}
			},
			error: function (data, status, e)
			{
				alert("Ajax handler not found");
			}
		}
	)
	return false;
}

/**
* Initialize all timers
*/

var sixty = 60000;
var fifteen = 15000;
var five = 5000;


function loadTimers() {

	onlineStatusDots();
	onlineUsersForGame();
	openTournamentsOfGame();
	memberStatsTimer();
	playNowTimer();
	breakingNewsTimer1();
	notificatonTimer();
	updateMailFolderCountsTimer();


} //end loadTimers

/**
* Stop all timers
*/
function stopTimers() {
	// console.log("stopping all timers");
	stopOnlineStatusDotsTimer();
	stopOnlineUsersForGameTimer();
	stopOpenTournamentsOfGameTimer();
	stopMemberStatsTimer();
	stopPlayNowTimer();
	stopBreakingNewsTimer();
	stopNotificationTimer();
	stopUpdateMailFolderCountsTimer();

}

/**
* Reset all timers
*/
function resetTimers() {
	// console.log("resetting all timers");
	resetOnlineStatusDotsTimer();
	resetOnlineUsersForGameTimer();
	resetOpenTournamentsOfGameTimer();
	resetMemberStatsTimer();
	resetPlayNowTimer();
	resetBreakingNewsTimer();
	resetNotificationTimer();
	resetUpdateMailFolderCountsTimer();

} //end loadTimers

function resetUpdateMailFolderCountsTimer(){
	console.log("mail counts timer is reset");
	if(mailFolderCountsTimer != null)
		mailFolderCountsTimer.reset(fifteen);
}

function stopUpdateMailFolderCountsTimer(){

	if(mailFolderCountsTimer != null) {
		mailFolderCountsTimer.stop();
		console.log("mail counts are stopped");
	}
}

function updateMailFolderCountsTimer(){

	if($('.updateFolderCounts').length >= 1) {
		if (mailFolderCountsTimer != null) {
			console.log("mail folder already exists");
			resetUpdateMailFolderCountsTimer();
		}
		else {
			mailFolderCountsTimer = $.timer(fifteen,
			function (timer) {
				if(syncMailFolderCounts) {
					syncMailFolderCounts = false;
					console.log("mail counts running...");
		   	    	var g4gTag = $('#g4gTag').get(0).value;

		       		$.get(getAjaxPath()+"updateMessageCountsForCurrentUser.do",
						{id:escape(g4gTag)},
						function(jsonData) {
							var retData = eval(jsonData);
							if(retData.indexOf("No session for request")>=0) {
								mailFolderCountsTimer.stop();
							}
							else {
								$("#newCount").html("(" + retData[0].newCount + ")");
								$("#newChallengeCount").html("(" + retData[0].newChallengeCount + ")");
								$("#friendRequestsCount").html("(" + retData[0].friendRequestsCount + ")");
								$("#readInboxCount").html("(" + retData[0].readInboxCount + ")");
								$("#worldGamingCounts").html("(" + retData[0].worldGamingCounts + ")");
								$("#archivedCount").html("(" + retData[0].archivedCount + ")");
								$("#sentMessageCount").html("(" + retData[0].sentMessageCount + ")");
							}
							tb_init('a.thickbox, area.thickbox, input.thickbox');

						}
					); //end .get
					syncMailFolderCounts = true;
					resetUpdateMailFolderCountsTimer();
				}
			}); //end timer
		}
	}
}

function stopOnlineStatusDotsTimer() {
	console.log("stop onlineDots");
	if(onlineDotsTimer != null)
		onlineDotsTimer.stop();
}

function resetOnlineStatusDotsTimer() {
	console.log("reset online dots");
	if(onlineDotsTimer != null)
		onlineDotsTimer.reset(sixty);
}

function onlineStatusDots() {
	if($('.OnlineDotsTimer').length >= 1) {
		var count = 0; //only for testing
		//if there are, start the timer
		onlineDotsTimer = $.timer(sixty,
		function (timer) {
			if(syncOnlineDots) {
				syncOnlineDots = false;
				console.log("online dots timer is running");
				//for each g4gTag, get a status update
				$('.OnlineDotsTimer').each( function() {
	    	    	var element = this;

	        		$.get(getAjaxPath()+"onlineStatus",
						{id:escape(element.id)},
						function(retData) {
							// console.log(":::retData::: " + retData);
							if(retData.indexOf("No session for request")>=0) {
								onlineDotsTimer.stop();
							}
							else {
								if(retData == "OFFLINE") {
									element.src = "WebContent/wg/images/icons/status-offline.png";
								}
								else if(retData == "AWAY") {
									element.src = "WebContent/wg/images/icons/status-away.png";
								}
								else if(retData == "IDLE") {
									element.src = "WebContent/wg/images/icons/status-idle.png";
								}
								else if(retData == "ONLINE") {
									element.src = "WebContent/wg/images/icons/status-online.png";
								}
							}
						}
					); //end .get
				}); //end each
				syncOnlineDots = true;
				resetOnlineStatusDotsTimer();
			}
		}); //end timer
	}
	//end online status dots
}

function stopOnlineUsersForGameTimer() {
	console.log("stop online users for game timer");
	if(onlineUsersForGameTimer != null)
		onlineUsersForGameTimer.stop();
}

function resetOnlineUsersForGameTimer() {
	console.log("reset online users for game timer");
	if(onlineUsersForGameTimer != null)
		onlineUsersForGameTimer.reset(sixty);
}

/**
*	Players online timer
*/
function onlineUsersForGame() {

	if($('.PlayersOnlineTimer').length >= 1) {
		onlineUsersForGameTimer = $.timer(sixty,
		function (timer) {
			if(syncOnlineUsersForGame) {
				syncOnlineUsersForGame = false;
				console.log("players online timer is running");
	   	    	var element = $('.GameId').get(0);
	   	    	console.log("players online gameId: " + element.id);

	       		$.get(getAjaxPath()+"onlineUsersForGame",
					{id:escape(element.id)},
					function(retData) {
						if(retData.indexOf("No session for request")>=0) {
							onlineUsersForGameTimer.stop();
						}
						else {
							$("#playersOnlineContent").html(retData);
						}
						tb_init('a.thickbox, area.thickbox, input.thickbox');

					}
				); //end .get
				syncOnlineUsersForGame = true;
				resetOnlineUsersForGameTimer();
			}
		}); //end timer
	}
}

function stopOpenTournamentsOfGameTimer() {
	console.log("stop open tournaments of game timer");
	if(openTournamentsOfGameTimer != null)
		openTournamentsOfGameTimer.stop();
}

function resetOpenTournamentsOfGameTimer() {
	console.log("reset open tournaments of game timer");
	if(openTournamentsOfGameTimer != null)
		openTournamentsOfGameTimer.reset(sixty);
}

/**
*	Open tournaments (Game lobby) timer
*/
function openTournamentsOfGame() {
	if($('.OpenTournamentsTimer').length >= 1) {
		openTournamentsOfGameTimer = $.timer(sixty,
		function (timer) {
			syncOpenTournamentsOfGame = false;
			console.log("open tournaments of game timer running");
			var element = $('.GameId').get(0);
			var playerElement = $('.PlayerId').get(0);

        	$.get(getAjaxPath()+"openTournamentsOfGame",
				{id:escape(element.id), pid:escape(playerElement.id)},
				function(retData) {
					if(retData.indexOf("No session for request")>=0) {
						openTournamentsOfGameTimer.stop();
					}
					else {
						$("#openTournamentsContent").html(retData);
					}
					tb_init('a.thickbox, area.thickbox, input.thickbox');
				}
			); //end .get
			syncOpenTournamentsOfGame = true;
			resetOpenTournamentsOfGameTimer();
		}); //end timer
	}
}

function stopMemberStatsTimer() {
	console.log("stop member stats timer");
	if(memStatsTimer != null)
		memStatsTimer.stop();
}

function resetMemberStatsTimer() {
	console.log("reset member stats timer");
	if(memStatsTimer != null)
		memStatsTimer.reset(sixty);
}


/**
*	Member stats timer
*/
function memberStatsTimer() {
	if($('.MemberStatsTimer').length >= 1) {
		memStatsTimer = $.timer(sixty,
		function (timer) {
			syncMemStats = false;
			console.log("member stats timer is running");
        	$.get(getAjaxPath()+"memberStats",
				{},
				function(jsonData) {
					var retData = eval(jsonData);
					if(retData.indexOf("No session for request")>=0) {
						memStatsTimer.stop();
					}
					else {
						$("#registeredCount").html(retData[0]);
						$("#onlineCount").html(retData[1]);
					}
				}
			); //end .get
			syncMemStats = true;
			resetMemberStatsTimer();
		}); //end timer
	}
}

function stopPlayNowTimer() {
	console.log("stop play it now timer");
	if(playItNowTimer != null)
		playItNowTimer.stop();
}

function resetPlayNowTimer() {
	console.log("reset play it now timer");
	if(playItNowTimer != null)
		playItNowTimer.reset(sixty);
}

/**
*	Play now timer
*/
function playNowTimer() {
	if($('.PlayNowTimer').length >= 1) {
		playItNowTimer = $.timer(sixty,
			function (timer) {
				if(syncPlayNow ) {
					syncPlayNow = false;
					console.log("play it now timer is running");
					var xmlHttp = null;
					loadXMLDoc(xmlHttp, getAjaxPath()+"playNowRefresh", ajaxUpdatePlayNow);
					syncPlayNow = true;
					resetPlayNowTimer();
				}
			}
		); //end timer
	}
}

function stopNotificationTimer() {
	console.log("stop notification timer");
	if(notesTimer != null)
		notesTimer.stop();
}

function resetNotificationTimer() {
	console.log("reset notification timer");
	if(notesTimer != null)
		notesTimer.reset(fifteen);
}

/**
*	Heads up notification timer
*/
function notificatonTimer() {
	if($('.NotificationTimer').length >= 1) {
		notesTimer = $.timer(fifteen,
		function(timer) {
			if(syncNotes) {
				syncNotes = false;
				console.log("notification timer is running");
				$.get(getAjaxPath()+"alertsForUser",
					{},
					function(retData) {
						if(retData.indexOf("No session for request")>=0) {
							notesTimer.stop();
						}
						else if(retData != "") {
				         		notificationPrompt(retData);
						}
						tb_init('a.thickbox, area.thickbox, input.thickbox');
					}
				); //end get
				syncNotes = true;
				resetNotificationTimer();
			}
		});
	}
}


/***** Message Center *****/

/**
* Show a set of messages in the message center
*
* messageStatus can be:
*       Read, Unread, Deleted, Archived, null
*
* messageType can be:
*       Message, Clan, Challenge, Acceptance, ‘Friend Request’,
*       WorldGaming, MarchConfirm, TournamentJoin, CashConfirm,
*		MatchResults, TournamentResults, UpcomingMatch, null
*
* isSent can be:
*       true, false, null
*
*/
function showMessages(messageStatus, messageType, isSent) {
//alert("In message Center.....!");
	//$("#messagesContent").html("no message to display");
	var longDate = new Date().getTime();
	$.get(getAjaxPath()+"showMessages.do",
		{messageStatus:messageStatus, messageType:messageType, isSent:isSent, ms:longDate},
		function(retData) {
			$("#messagesContent").hide;
			// console.log(":::messagesContent::: " + retData);
			var temp = retData;
			document.getElementById('messagesContent').innerHTML = temp;
			//$("#messagesContent").html(temp);
			$("#messagesContent").show;
			//alert("show the messages");

			if(isSent == 'true') {
				$('dd.messageReply').addClass("hideDiv");
			} else {
				$('dd.messageReply').addClass("showDiv");
			}

			tb_init('a.thickbox, area.thickbox, input.thickbox');
		}
	); //end .get
}

/**
* Send new messages
*/
function sendNewMessage() {
	var nmBody = $('#newMessageBody').get(0).value;
	var nmSubject = $('#newMessageSubject').get(0).value;
	var index = $("#friendsList").get(0).selectedIndex;
	var recipientId = $("#friendsList").get(0).options[index].value;
	var validate = true;
	var errorMessage = '';

	if(index < 1) {
		errorMessage += '<dl>Please select a friend</dl>';
		validate = false;
	}
	if(nmSubject == '') {
		errorMessage += '<dl>Message subject is required</dl>';
		validate = false;
	}
	if(nmBody == '') {
		errorMessage += '<dl>Message body is required</dl>';
		validate = false;
	}
	if(validate == true) {

		$.get(getAjaxPath()+"composeMessage.do",
			{id:escape(recipientId), messageSubject:nmSubject, messageBody:nmBody},
			function(retData) {
				// console.log(":::retData::: " + retData);
			}
		); //end .get
		$(".MessageContainer.compose").hide("fast");
		//humanMsg.displayMsg(nmSubject + ' sent to ' + recipientId);
		notificationPrompt(nmSubject + " sent to " + recipientId);
	}
	else {
		notificationPrompt(errorMessage);
	}
}

function showCompose() {
	$('#newMessageBody').get(0).value = '';
	$('#newMessageSubject').get(0).value = '';
	$("#friendsList").get(0).selectedIndex = 0;

	$(".MessageContainer.compose").show("fast");
}
function hideCompose() {
	$(".MessageContainer.compose").hide("fast");
}

/**
* Changes status of message
* messageStatus can be:
* archived, deleted, read, unread
*/
function changeMessageStatus(message, messageStatus) {
	var messageId;

	//ugly hack, cause I couldn't figure out a
	//better way to set the Id's
	if(message.id.substring(0,7).toLowerCase() == "archive")
		messageId = message.id.substring(7);
	else if(message.id.substring(0,4).toLowerCase() == "read")
		messageId = message.id.substring(4);
	else if(message.id.substring(0,10).toLowerCase() == "deleteuser")
		messageId = message.id.substring(10);
	else if(message.id.substring(0,6).toLowerCase() == "delete")
		messageId = message.id.substring(6);
	else if(message.id.substring(0,4).toLowerCase() == "read")
		messageId = message.id.substring(4);
	else if(message.id.substring(0,6).toLowerCase() == "unread")
		messageId = message.id.substring(6);
	else if(message.id.substring(0,4).toLowerCase() == "open")
		messageId = message.id.substring(4);
	else
		messageId = message.id;
	messageId = jQuery.trim(messageId);
	// console.log("changing message status to " + messageStatus + " for " + messageId);
	$.get(getAjaxPath()+"changeMessageStatus.do",
		{id:messageId, messageStatus:messageStatus},
		function(retData) {

			// console.log(":::retData::: " + retData);
			if(messageStatus == "Read") {
				// console.log("message " + messageId + " marked read");
			}
			else if(messageStatus == "Archived") {
				// console.log("message archived");
				$("#" + messageId).html("");
			}
			else if(messageStatus == "Deleted" || messageStatus == "DeletedUser") {
				$("#" + messageId).html("");
			}

			var g4gTag = $('#g4gTag').get(0).value;

	       	$.get(getAjaxPath()+"updateMessageCountsForCurrentUser.do",
				{id:escape(g4gTag)},
				function(jsonData) {
					var retData = eval(jsonData);
					if(retData.indexOf("No session for request")>=0) {
						mailFolderCountsTimer.stop();
					}
					else {
						$("#newCount").html("(" + retData[0].newCount + ")");
						$("#newChallengeCount").html("(" + retData[0].newChallengeCount + ")");
						$("#friendRequestsCount").html("(" + retData[0].friendRequestsCount + ")");
						$("#readInboxCount").html("(" + retData[0].readInboxCount + ")");
						$("#worldGamingCounts").html("(" + retData[0].worldGamingCounts + ")");
						$("#archivedCount").html("(" + retData[0].archivedCount + ")");
						$("#sentMessageCount").html("(" + retData[0].sentMessageCount + ")");
					}
					tb_init('a.thickbox, area.thickbox, input.thickbox');
				}
			); //end .get

			tb_init('a.thickbox, area.thickbox, input.thickbox');
		}
	); //end .get
}

/**
* show the Message Center message div
*/
function openMessage(message) {
	var id = message.id.substring(4);
	$("#body" + id).removeClass("hideDiv").addClass("showDiv");
	$("#c" + id).addClass("msgOpen");
	//call change status to mark it read
	changeMessageStatus(message, 'Read');
	tb_init('a.thickbox, area.thickbox, input.thickbox');
}

/**
* hide the Message Center message div
*/
function closeMessage(message) {
	var id = message.id.substring(5);
	$("#body" + id).removeClass("showDiv").addClass("hideDiv");
	$("#c" + id).removeClass("msgOpen");
	tb_init('a.thickbox, area.thickbox, input.thickbox');
}

/**
* Reply to message
*/
function replyToMessage(thisObject)
{
	var id = thisObject.id.replace("reply", "");
	var replyBody = $("#replyText" + id).get(0).value;

	$.get(getAjaxPath()+"replyToMessage.do",
		{id:id, replyText:replyBody},
		function(retData) {
			// console.log(":::retData::: " + retData);


			tb_init('a.thickbox, area.thickbox, input.thickbox');
		}
	); //end .get
	$("#replyText" + id).get(0).value = "";
	notificationPrompt("Reply sent");


}

/***** End Message Center *****/

function notificationPrompt(m) {
	$("#Notification").remove();
	var msgbox = '<div id="Notification">';
	msgbox += '<div class="NotificationBG"></div>';
	msgbox += '<div class="NotificationContent">';
	msgbox += '<div class="NotificationContent">'
	msgbox += '<em class="Close"><a href="javascript:{}" onclick="hideNotification()">X Close</a></em>'
	msgbox += '<dl id="notificationList">'
	msgbox += m;
	msgbox += '</dl>'
	msgbox += '</div>'
	msgbox += '</div>'

	docBody = $("body");
	docBody.prepend(msgbox);
}

function hideNotification() {
	$("#Notification").removeClass("showDiv").addClass("hideDiv");

	//mark internalMessages read
	$('.internalMessage').each( function() {
		var element = this;
		// console.log("marking message read, id: " + element.id);
		changeMessageStatus(element, "Read");
	});
}


function submitUserReputation(thisObject) {
	// console.log('called submitUserReputation');

	var id = thisObject.id.replace("Button", "");

	var stars = $('#'+id+'Value').get(0).value; //how many stars did they select?
	var commentText = $('#'+id+'Text').get(0).value;  //what is in the textbox?

	// console.log(id + " " + stars + " " + commentText);

	//uncomment this next section to turn on the Ajax submit
	$.get(getAjaxPath()+"updateMatchFeedback",
		{id:id,rating:stars,comment:commentText},
		function(retData) {
			// console.log(":::retData::: " + retData);
			//$("#" + id + "Text").html(retData);

		}
	); //end .get


}

function starRatingClick(thisObject) {
	// console.log( "selected a star: " + thisObject.href );

	var rating = thisObject.name;
	var id = jQuery(thisObject).parent().parent().get(0).id;
	var isDynamic = ($('#'+id).is('.dynamicRating'));

	if( isDynamic ) {
		//we have dynamic ratings on the page
		//set the hidden value for the id
		$('#' + id + 'Value').get(0).value=rating;
		//set the text box with the default
		if(rating == '1')
			$('#' + id + 'Text').html('You Suck');
		else if(rating == '1.5')
			$('#' + id + 'Text').html('You Suck');
		else if(rating == '2')
			$('#' + id + 'Text').html('Pretty lame');
		else if(rating == '2.5')
			$('#' + id + 'Text').html('Not so bad');
		else if(rating == '3')
			$('#' + id + 'Text').html('Nice play');
		else if(rating == '3.5')
			$('#' + id + 'Text').html('Good');
		else if(rating == '4')
			$('#' + id + 'Text').html('Good job');
		else if(rating == '4.5')
			$('#' + id + 'Text').html('Good job');
		else if(rating == '5')
			$('#' + id + 'Text').html('Great job');

	}
	else {
		//we only have static ratings on the page
	}
}


//date picker function for registration page
function setHiddenDate() {
	var matchDate = $('#matchDate').get(0).value;

	if(matchDate == "") {
		$("#matchHour").get(0).disabled = true;
		$("#matchMin").get(0).disabled = true;
		$("#matchAMPM").get(0).disabled = true;
		$("#matchHour").get(0).index = 0;
		$("#matchMin").get(0).index = 0;
		$("#matchAMPM").get(0).index = 0;
		$('#tournamentDate').get(0).value = "";
	}
	else {
		//set the date and time
		$("#matchHour").get(0).disabled = false;
		$("#matchMin").get(0).disabled = false;
		$("#matchAMPM").get(0).disabled = false;

		sHour = $("#matchHour").get(0).value;
		sMin = $("#matchMin").get(0).value;
		sAMPM = $("#matchAMPM").get(0).value;

		if(sHour == "") {
			sHour = "01";
			$("#matchHour").get(0).selectedIndex = 1;
		}
		if(sMin == "") {
			sMin = "00";
			$("#matchMin").get(0).selectedIndex = 1;
		}
		if(sAMPM == "") {
			sAMPM = "AM";
			$("#matchAMPM").get(0).selectedIndex = 1;
		}
		matchTime = matchDate + " at " + sHour + ":" + sMin + " " + sAMPM;
		$('#tournamentDate').get(0).value = matchTime;

	}
}

/*function setHiddenTime() {
	var sHour = null;
	var sMin = null;
	var sAMPM = null;
	var matchTime = null;

	if($("#matchHour").get(0).value == "") {
		$("#matchMin").get(0).selectedIndex = 0;
		$("#matchAMPM").get(0).selectedIndex = 0;
		$("#matchMin").get(0).disabled = true;
		$("#matchAMPM").get(0).disabled = true;
		$('#tournamentDate').get(0).value = $('#matchDate').get(0).value;
		// console.log("no match time appended");
	}
	else {
		$("#matchMin").get(0).disabled = false;
		$("#matchAMPM").get(0).disabled = false;

		sHour = $("#matchHour").get(0).value;
		sMin = $("#matchMin").get(0).value;
		sAMPM = $("#matchAMPM").get(0).value;

		if(sMin == "") {
			sMin = "00";
			$("#matchMin").get(0).selectedIndex = 1;
		}
		if(sAMPM == "") {
			sAMPM = "AM";
			$("#matchAMPM").get(0).selectedIndex = 1;
		}
		matchTime = sHour + ":" + sMin + " " + sAMPM;
		$('#tournamentDate').get(0).value = $('#matchDate').get(0).value + ' at ' + matchTime;
		// console.log("matchTime appended: " + matchTime);
	}
}*/

var Mon3 = ["Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"];
var Day3 = ["Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat"];
var D_Opt = new Array() // 29..31

function Lz(x) {
    return (x < 0 || x >= 10 ? "" : "0") + x;
}

function LengthOfMonth(Y, M) {
    with (new Date(Y, M, 1, 12)) {
        setDate(0);
        return getDate();
    }
}

function MonLen(Yr, Mo, Dy) {
    var Dol, K, DiM, NsI;
    DiM = LengthOfMonth(+ Yr.options[Yr.selectedIndex].text, Mo.selectedIndex);
    // console.log("DiM: " + DiM);

    Dol = Dy.options.length;
    // console.log("Dol: " + Dol);
    if ((NsI = Dy.selectedIndex) >= DiM) {
        NsI = DiM; //was -1
    }

    for (K = Dol-1; K > DiM; K--) {
        D_Opt[K] = Dy.options[K]; //was K-1
        // console.log("K: " + K + " Dy.options[K]:" + Dy.options[K]);
        Dy.options[K] = null; //was K-1
    }
    for (K = Dol; K <= DiM; K++) { //was k=dol+1
        Dy.options[K] = D_Opt[K]; //was K -1
    }
    Dy.selectedIndex = NsI;
}

function DropReadYMD(Yr, Mo, Dy) {
    var Y = + Yr.options[Yr.selectedIndex].text;
    var M = Mo.selectedIndex - 1;
    var D = Dy.selectedIndex;
    // console.log(Lz(M + 1) + "/" + Lz(D) + "/" + Y);
    return Lz(M + 1) + "/" + Lz(D) + "/" + Y;
}

function InitYMDselector(Yr, Mo, Dy, Anni, Base, SetObj) {
    var J;
    if (!Base) {
        Base = (new Date).getFullYear();
    }
    for (J = 0; J < Anni; J++) {
        Yr.options[J] = new Option(Base + J);
    }
    for (J = 0; J < 12; J++) {
        Mo.options[J] = new Option(Mon3[J]);
    }
    for (J = 0; J < 31; J++) {
        Dy.options[J] = new Option(J + 1);
    }
    if (SetObj) {
        with (SetObj) {
            Yr.selectedIndex = getFullYear() - Base;
            Mo.selectedIndex = getMonth();
            Dy.selectedIndex = getDate() - 1;
        }
    } else {
        Yr.selectedIndex = Mo.selectedIndex = Dy.selectedIndex = 0;
    }
    MonLen(Yr, Mo, Dy);
}


//end date picker for registration page.


function calculateTournamentJackpot() {
	var numberOfSlots = $("#numberOfSlots").get(0).title;
	var amountField = $("#amountField").get(0).value;
	amountField = amountField.replace('$', '');
	$("#dynamicTotalAmount").html(formatCurrency(numberOfSlots * (amountField - (0.1 *amountField))));
}

function formatCurrency(num) {
	num = num.toString().replace(/\$|\,/g,'');
	if(isNaN(num))
		num = "0";
	sign = (num == (num = Math.abs(num)));
	num = Math.floor(num*100+0.50000000001);
	cents = num%100;
	num = Math.floor(num/100).toString();
	if(cents<10)
		cents = "0" + cents;
	for (var i = 0; i < Math.floor((num.length-(1+i))/3); i++)
		num = num.substring(0,num.length-(4*i+3))+','+
	num.substring(num.length-(4*i+3));
	return (((sign)?'':'-') + '$' + num + '.' + cents);
}

function registrationDateDefault() {
	var birthDateString = $("#birthDate").get(0).value;
	var birthDate = Date.fromString(birthDateString);

	$("#MoSel").get(0).selectedIndex = birthDate.getMonth() + 1;
	$("#DySel").get(0).selectedIndex = birthDate.getDate();
	$("#YrSel").get(0).selectedIndex = 1990 - birthDate.getFullYear();
}

