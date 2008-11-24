/*
 * Copyright 2003 Symbian Ltd.
 * For License terms see http://www.symbian.com/developer/techlib/codelicense.html
 */

package com.symbian.devnet.chatmidlet.controller;

import com.symbian.devnet.chatmidlet.view.*;
import com.symbian.devnet.chatmidlet.model.*;

import javax.microedition.midlet.*;
import javax.microedition.lcdui.*;
import javax.wireless.messaging.*;
import java.io.*;


/** 
 * A simple chat application using SMS as the bearer.
 * 
 * This class is the main controller class of the application. Creates instances of Sender and Receiver
 * and the two Views. Responds to events from the Receiver and Views.
 */
public class ChatMIDlet extends MIDlet implements CommandListener, MessageListener
{	
	private Sender sender;
	private Receiver receiver;	
	
	//Widgets for the UI for entering and reading the msgs
	private ChatView chatBox;
	private MessageView messageView;
	private Display display;
	private MessageConnection smsconn;	
			
	private String smsPort;//The port on which we send SMS messages	
		
	private final static int SENT = 1;
	private final static int RECEIVED = 2;
	private final static int ERROR = 3;
	
	
	public ChatMIDlet()
	{
		display = Display.getDisplay(this);
			
		smsPort = getAppProperty("SMS-Port");
		receiver = new Receiver(this);
		sender = new Sender(this, smsPort);
		chatBox = new ChatView(this);
		messageView = new MessageView(this);
		receiver.openReceiver(smsPort);						
	}
	
	
	public void startApp()
	{				
		display.setCurrent(chatBox);
	}
	
	
	public void pauseApp()
	{
	}
	
	
	public void destroyApp(boolean unconditional)
	{
	}
	

	public void commandAction(Command command, Displayable displayable)
	{
		if(command.getLabel().equals("Send"))
		{
			display.setCurrent(messageView);
		}
		else if(command.getLabel().equals("Exit"))
		{			
			receiver.closeReceiver();
			destroyApp(true);
			notifyDestroyed();
		}
		else if(command.getLabel().equals("OK"))
		{
			String message = messageView.getMessage();
			String phoneNumber = messageView.getPhoneNumber();
			sender.connectAndSend(message, phoneNumber);
			chatBox.addMsg(ChatMIDlet.SENT, message);
			display.setCurrent(chatBox);
		}		
	}
		
	
	public Display getDisplay()
	{
		return display;
	}
		
	
	public void msgTypeError(String error)
	{
		chatBox.addMsg(ChatMIDlet.ERROR,error);
	}
	
	
	/** Mandated by MessageListener interface */	
	public void notifyIncomingMessage(MessageConnection conn) 
	{
		receiver.receiveMessage(conn);
	}
	
	
	public void processMsg(String message,String destinationAddress)
	{
		chatBox.addMsg(ChatMIDlet.RECEIVED,message);
		messageView.setPhoneNumber(destinationAddress);
	}
		
	
}
