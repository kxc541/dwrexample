/*
 * Copyright 2003 Symbian Ltd.
 * For License terms see http://www.symbian.com/developer/techlib/codelicense.html
 */

package com.symbian.devnet.chatmidlet.model;

import com.symbian.devnet.chatmidlet.controller.ChatMIDlet;
import javax.wireless.messaging.*;
import java.io.*;
import javax.microedition.io.*;

/** Opens and closes a connection for receiving SMS messages */
public class Receiver implements Runnable
{	
	private MessageConnection smsconn;
	private ChatMIDlet chat;
	private Thread receiverThread;
	
	public Receiver(ChatMIDlet chat)
	{
		this.chat = chat;
	}
	
	
	public void openReceiver(String smsPort)
	{
		String smsAddress = "sms://:" + smsPort;
		
		try 
		{
			smsconn = (MessageConnection) Connector.open(smsAddress);
			smsconn.setMessageListener(chat);
					
		}
		catch (IOException ioe) 	
		{
			ioe.printStackTrace();
		}														
	}
		
		
	public void receiveMessage(MessageConnection conn)
	{		
		new Thread(this).start();	
	}	
						
				
	public void run()
	{
		Message msg = null;
		String senderAddress = null;
		String receivedMessage = null;
					
		try 
		{					
			msg = smsconn.receive();
			if (msg != null) 
			{
				senderAddress = msg.getAddress();
				if (msg instanceof TextMessage) 
				{
					receivedMessage = ((TextMessage)msg).getPayloadText();
				}
				else{
					chat.msgTypeError("Error whilst receiving.Message type not recognised.");
				}										
			}
		} 
		catch (IOException e) 	
		{
			System.out.println("IOException" + e.getMessage());
			e.printStackTrace();
		}
				
		chat.processMsg(receivedMessage, senderAddress);
	}		


	public void closeReceiver()
	{
		
		if (smsconn != null) 
		{
			try
			{
				smsconn.close();
			}catch(IOException ioe)
			{
				ioe.printStackTrace();
			}
		}
	}
		
	
}
