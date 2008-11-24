//
//	chat.js	- InternalChat support Object
//
//	This object collects the methods and vars for the UserListWidget
//
var InternalChat = {
    //
    //	attributes
    //
    lastMessage:	0,
    mTimer:		null,
	
	init: function() {
		//console.log("InternalChat.init starting.");
		if ($("#chat_owner_id").get().length > 0) {
			//Set the focus to the Message Box.
			//$("#txt_message").focus(); //WBS - giving the txt_message causes the chat to scroll.
			//Start Recieving Messages.
			InternalChat.mTimer = setTimeout(InternalChat.getChatText, 2000); //Refresh our chat in 2 seconds
		}
    },
    
    //Gets the current messages from the server
    getChatText: function() {
    	if ($("#chat_owner_id").get().length > 0) {
			// Clear out the existing timer so we don't have multiple timer instances running.
			clearInterval(InternalChat.mTimer);
			var chatOwnerID =  $("#chat_owner_id").get(0).value;
			$.post(ajaxPath+'InternalChatMessages', { id:  chatOwnerID }, InternalChat.handleReceiveChat);
		}
	},
    
	//Add a message to the chat server.
	sendChatText: function() {
		if ($("#txt_message").get().length == 0) {
			return;
		}
		
		var messageText = $("#txt_message").get(0).value;
		if(messageText == "") {
			InternalChat.getChatText();
		}

		// Clear out the existing timer so we don't have multiple timer instances running.
		clearInterval(InternalChat.mTimer);
		var chatOwnerID =  $("#chat_owner_id").get(0).value;

		$.post(ajaxPath+"InternalChatMessages",
			{ id: chatOwnerID, message: messageText},
			InternalChat.handleReceiveChat
		);

		// clear message field
		$("#txt_message").get(0).value = "";
		$("#txt_message").focus();
    },
    	
	handleReceiveChat: function(retData) {
		if ($("#div_chat").get().length == 0) 
			return;
	    
		//Get the AJAX response and run the JavaScript evaluation function
		//on it to turn it into a useable object.  Notice since we are passing
		//in the JSON value as a string we need to wrap it in parentheses
		var response = eval("(" + retData + ")");
		
		//Get a reference to our chat container div for easy access
		var chat_div = $("#div_chat").get(0);

		chat_div.innerHTML = '';
		for(i=0;i < response.messages.message.length; i++) {
			if (response.messages.message[i].type == "user") {
				$("#div_chat").append( 
		    		'<div class="click" id="id' + response.messages.message[i].id + '">' +
		    		'<p class="chat_user">' + response.messages.message[i].user + '</p>' +
		    		'<p class="chat_time">' +  response.messages.message[i].time + '</p>' +
		    		'<p class="chat_message">' + response.messages.message[i].text + '</p>' +
		    		'</div>'
		   		);
				InternalChat.lastMessage = response.messages.message[i].id;
			}
			if (response.messages.message[i].type == "status") {
				$("#div_chat").append( 
		    		'<div class="click" id="id' + response.messages.message[i].id + '">' +
		    		'<p class="chat_message">[[ ' + response.messages.message[i].time + ': ' + response.messages.message[i].text + ' ]]</p>' +
		    		'</div>'
		   		);
				InternalChat.lastMessage = response.messages.message[i].id;
			}
		}
		chat_div.scrollTop = chat_div.scrollHeight;
		InternalChat.mTimer = setTimeout(InternalChat.getChatText, 2000); //Refresh our chat in 2 seconds
    },

	//This functions handles when the user presses enter.  Instead of submitting the form, we
	//send a new message to the server and return false.
	blockSubmit: function() {
		InternalChat.sendChatText();
		return false;
	}
}; //end of InternalChat

$(document).ready(InternalChat.init);
//console.log("chat.js loaded.");
