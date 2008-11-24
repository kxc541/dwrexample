/*
 * Copyright 2003 Symbian Ltd.
 * For License terms see http://www.symbian.com/developer/techlib/codelicense.html
 */
 

package com.symbian.devnet.chatmidlet.view;

import com.symbian.devnet.chatmidlet.controller.ChatMIDlet;
import javax.microedition.lcdui.*;

/** Displays the ongoing conversation */
public class ChatView extends TextBox {
	private Command send;
	private Command end;
	private ChatMIDlet midlet;

	public ChatView(ChatMIDlet midlet)
	{
		super("Conversation: ",null,4000,TextField.UNEDITABLE);
		this.midlet=midlet;		
		send = new Command("Send",Command.OK,1);
		end = new Command("Exit",Command.EXIT,2);		
		addCommand(send);
		addCommand(end);
		setCommandListener(midlet);
	}		
	
	
	public synchronized void addMsg(int type, String msg)
	{
		//method used when a msg has been received and is to be added to the 
		//current dialogue		
		String current = getString();
		
		if(current.length()==4000)
		{
			//making room for the new msg
			delete(current.length()-1000,1000);
		}
		
		//depending on whether the message was received or sent set the font
		switch(type)
		{
			case 1:
			{
				//adding the new msg to the chat dialogue
				insert("S: " + msg + "\n",current.length());
				break;
			}
			case 2: 
			{
				//adding the new msg to the chat dialogue
				insert("R: " + msg + "\n",current.length());
				break;
			}
			case 3:
			{
				insert(msg,current.length());
				break;
			}
			default: 
			{
				System.out.println("Not recognised.");
				break;
			}
		}
		
	}
}





