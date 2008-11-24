var sendReq = getXmlHttpRequestObject();
var receiveReq = getXmlHttpRequestObject();
var lastMessage = 0;
var mTimer;
//Function for initializating the page.
function startChat() {
	//Set the focus to the Message Box.
	document.getElementById('txt_message').focus();
	//Start Recieving Messages.
	getChatText();
}		
//Gets the browser specific XmlHttpRequest Object
function getXmlHttpRequestObject() {
	if (window.XMLHttpRequest) {
		return new XMLHttpRequest();
	} else if(window.ActiveXObject) {
		return new ActiveXObject("Microsoft.XMLHTTP");
	} else {
		document.getElementById('p_status').innerHTML = 'Status: Cound not create XmlHttpRequest Object.  Consider upgrading your browser.';
	}
}

//Gets the current messages from the server
function getChatText() {
	if (receiveReq.readyState == 4 || receiveReq.readyState == 0) {
		receiveReq.open("GET", 'getChat.php?chat=1&last=' + lastMessage, true);
		receiveReq.onreadystatechange = handleReceiveChat; 
		receiveReq.send(null);
	}			
}
//Add a message to the chat server.
function sendChatText() {
	if(document.getElementById('txt_message').value == '') {
		alert("You have not entered a message");
		return;
	}
	if (sendReq.readyState == 4 || sendReq.readyState == 0) {
		sendReq.open("POST", 'getChat.php?chat=1&last=' + lastMessage, true);
		sendReq.setRequestHeader('Content-Type','application/x-www-form-urlencoded');
		sendReq.onreadystatechange = handleSendChat; 
		var param = 'message=' + document.getElementById('txt_message').value;
		param += '&name=Ryan Smith';
		param += '&chat=1';
		sendReq.send(param);
		document.getElementById('txt_message').value = '';
	}							
}
//When our message has been sent, update our page.
function handleSendChat() {
	//Clear out the existing timer so we don't have 
	//multiple timer instances running.
	clearInterval(mTimer);
	getChatText();
}
function handleReceiveChat() {
	if (receiveReq.readyState == 4) {
		//Get a reference to our chat container div for easy access
		var chat_div = document.getElementById('div_chat');
		//Get the AJAX response and run the JavaScript evaluation function
		//on it to turn it into a useable object.  Notice since we are passing
		//in the JSON value as a string we need to wrap it in parentheses
		var response = eval("(" + receiveReq.responseText + ")");
		for(i=0;i < response.messages.message.length; i++) {
			chat_div.innerHTML += '<p class="chat_time">' +  response.messages.message[i].time + '</p';
			chat_div.innerHTML += '<p class="chat_user">' + response.messages.message[i].user + '</p>';
			chat_div.innerHTML += '<p class="chat_message">' + response.messages.message[i].text + '</p>';
function 			chat_div(args) {
	
}
			chat_div.scrollTop = chat_div.scrollHeight;
			lastMessage = response.messages.message[i].id;
		}
		mTimer = setTimeout('getChatText();',2000); //Refresh our chat in 2 seconds
	}
}
//This functions handles when the user presses enter.  Instead of submitting the form, we
//send a new message to the server and return false.
function blockSubmit() {
	sendChatText();
	return false;
}
//This cleans out the database so we can start a new chat session.
function resetChat() {
	if (sendReq.readyState == 4 || sendReq.readyState == 0) {
		sendReq.open("POST", 'getChat.php?chat=1&last=' + lastMessage, true);
		sendReq.setRequestHeader('Content-Type','application/x-www-form-urlencoded');
		sendReq.onreadystatechange = handleResetChat; 
		var param = 'action=reset';
		sendReq.send(param);
		document.getElementById('txt_message').value = '';
	}							
}
//This function handles the response after the page has been refreshed.
function handleResetChat() {
	document.getElementById('div_chat').innerHTML = '';
	getChatText();
}