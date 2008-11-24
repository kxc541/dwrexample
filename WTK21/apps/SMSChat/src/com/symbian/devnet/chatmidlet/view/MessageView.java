/*
 * Copyright 2003 Symbian Ltd.
 * For License terms see http://www.symbian.com/developer/techlib/codelicense.html
 */

package com.symbian.devnet.chatmidlet.view;

import com.symbian.devnet.chatmidlet.controller.ChatMIDlet;
import javax.microedition.lcdui.*;

/* Provides text fields for entering the message and the recipients phone number */
public class MessageView extends Form {
	
	private TextField phoneField;
	private TextField msgField;
	private Command ok;
	private ChatMIDlet midlet;


	public MessageView(ChatMIDlet midlet)
	{
		super("Enter message");
		this.midlet = midlet;
		phoneField = new TextField("Enter phone number: ",null,256,TextField.PHONENUMBER);
		msgField = new TextField("Type message here",null,256,TextField.ANY);		
		insert(0,phoneField);
		insert(1,msgField);		
		ok = new Command("OK",Command.OK,1);
		addCommand(ok);
		setCommandListener(midlet);
	}
	
	
	public String getMessage()
	{
		return msgField.getString();
	}
	
	
	public String getPhoneNumber()
	{
		return phoneField.getString();
	}


	public void setPhoneNumber(String phoneNumber)
	{	
		phoneField.setString(phoneNumber);
	}
}