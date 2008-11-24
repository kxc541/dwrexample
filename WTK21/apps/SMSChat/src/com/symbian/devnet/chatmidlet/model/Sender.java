/*
 * Copyright 2003 Symbian Ltd.
 * For License terms see http://www.symbian.com/developer/techlib/codelicense.html
 */
 
package com.symbian.devnet.chatmidlet.model;

import com.symbian.devnet.chatmidlet.controller.ChatMIDlet;

import javax.microedition.io.*;
import javax.microedition.lcdui.*;
import javax.wireless.messaging.*;
import java.io.IOException;

/** Sends an SMS message */
public class Sender implements Runnable
{	
	private String smsReceiverPort;	
	private ChatMIDlet chat;
	private String message;	
	private String phoneNumber;

	
	public Sender(ChatMIDlet chat, String smsReceiverPort)
	{
		this.chat = chat;		
		this.smsReceiverPort = smsReceiverPort;		
	}
	
	
	public void run()
	{
		String address = "sms://" + phoneNumber + ":" + smsReceiverPort;
	   	MessageConnection smsconn = null;
		try 
		{
			//Open the message connection.
			smsconn = (MessageConnection)Connector.open(address);
			//Create the message.
			TextMessage txtmessage = (TextMessage)smsconn.newMessage(MessageConnection.TEXT_MESSAGE);
			txtmessage.setPayloadText(message);
			smsconn.send(txtmessage);
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		
		if (smsconn != null) {
			try 
			{
				smsconn.close();
			} 
			catch (IOException ioe) 
			{
				ioe.printStackTrace();
			}                
		}
	}


	public void connectAndSend(String message, String phoneNumber)
	{
		this.message = message;
		this.phoneNumber = phoneNumber;
		Thread t = new Thread(this);
		t.start();		
	}
	
}
