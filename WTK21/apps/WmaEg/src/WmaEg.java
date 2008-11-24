

import javax.microedition.midlet.*;
import javax.microedition.lcdui.*;
import javax.microedition.io.*;
import java.util.*;
import java.io.*;

import javax.wireless.messaging.*;

public class WmaEg extends MIDlet implements CommandListener {

    private static Command sendCommand = new Command("Send", Command.SCREEN, 1);
    private Form setupScreen;
    private Form sendScreen;
	private boolean shutdown = false;
	String destSmsAddress = "+5550001"; //default for WMA Console Utility
	String destSmsPort = "50000"; //default listener for WMA Console Utility
	String listenSmsPort = "56789";
	String listenCbsPort = "24680";
	String messageToSend = "Hello!";

    private List list = new List("WMA Test", List.IMPLICIT);

	/**
	Used to create instances of the MIDlet
	*/
	public WmaEg()
	{
		list.setCommandListener(this);
        list.addCommand(sendCommand);
 	}

	public void startApp()
	{
		String[] connections = PushRegistry.listConnections(true);
		if (connections.length == 0) //manual start up, allow user to configure
		{
			setupScreen = new SetupScreen();
			Display.getDisplay(this).setCurrent(setupScreen);
		}
		else  //PushRegistry started the app, go with defaults
		{
			System.out.println("push connection: "+connections[0]);
			connections = PushRegistry.listConnections(false); // get all connections
			for(int ccnt=0; ccnt < connections.length; ccnt++)
			{
				initListener(connections[ccnt]);
			}
			sendScreen = new SendScreen();
			Display.getDisplay(this).setCurrent(list);
		}
	}

	public void pauseApp()
	{
	}

	public void destroyApp(boolean unc)
	{
		shutdown = true;
	}


	private void appendData(String data)
	{
		list.append(data, null);
	}

   public void commandAction(Command command, Displayable displayable)
   {
	   Display.getDisplay(this).setCurrent(sendScreen);
	}

   public void showList()
   {
		Display.getDisplay(this).setCurrent(list);
		registerUrls();
   }

   /**
   Need to do this on another thread in case the security prompts the user when changing settings.
   */
   private void registerUrls()
   {
	   Thread t = new Thread()
	   {
		   public void run()
		   {
				String sms = "sms://:"+listenSmsPort;
				String cbs = "cbs://:"+listenCbsPort;
				String[] connections = PushRegistry.listConnections(false); // get all connections
				for(int ccnt=0; ccnt < connections.length; ccnt++)
				{
					PushRegistry.unregisterConnection(connections[ccnt]);
				}
				try
				{
					PushRegistry.registerConnection(sms, "WmaEg", "*");
					PushRegistry.registerConnection(cbs, "WmaEg", "*");

				} catch (IOException x)
				{
					x.printStackTrace();
					return;
				} catch (ClassNotFoundException x)
				{
					x.printStackTrace();
					return;
				}
				initListener(sms);
				initListener(cbs);
				sendScreen = new SendScreen();
			}
	   };
	   t.start();
   }

	private void initListener(final String connectionString)
	{
		Thread t = new Thread()
		{
			public void run()
			{
				try
				{
					MessageConnection listener = (MessageConnection)
				       Connector.open(connectionString);
					try
					{
						while (!shutdown)
						{
							System.out.println("listener reading at "+connectionString);
							Message msg = listener.receive();
							if (msg instanceof TextMessage)
							{
								System.out.println("Message Received: "+((TextMessage)msg).getPayloadText());
								appendData(((TextMessage)msg).getPayloadText());
							}
							else
							{
								byte[] data = ((BinaryMessage)msg).getPayloadData();
								String s = decodeBinary(data);
								System.out.println("Binary Message Received: "+s );
								appendData(s);
							}
						}
					}
					finally
					{
						listener.close();
					}
				} catch (IOException x)
				{
					x.printStackTrace();
				}
			}
		};
		t.start();
	}

	/**
	For the sake of example, just assume this is meaningful data as a string
	*/
	private String decodeBinary(byte[] msg)
	{
		StringBuffer sb = new StringBuffer();
		for (int ccnt = 0; ccnt < msg.length; ccnt++) {
			sb.append((char)msg[ccnt]);
		}
		return sb.toString();
	}

	void doSend()
	{
		Display.getDisplay(this).setCurrent(list);
		new Thread(new Sender()).start();
	}

	class Sender implements Runnable
	{
		public void run()
		{
			try
			{
				String addr = "sms://"+destSmsAddress+":"+destSmsPort;
				MessageConnection sender = (MessageConnection)
				  Connector.open(addr);
				try
				{
					TextMessage msg = (TextMessage)sender.newMessage(MessageConnection.TEXT_MESSAGE);
					msg.setPayloadText(messageToSend);
					sender.send(msg);
					//msg.setAddress(addr);
					System.out.println("message sent to "+addr);
				}
				finally
				{
					sender.close();
				}
			} catch (IOException x)
			{
				x.printStackTrace();
			}
		}
	}

	class SetupScreen extends Form implements CommandListener
	{
    	private Command doneCmd = new Command("Done", Command.SCREEN, 1);

		private TextField toSmsAddress = new TextField("Send SMS Address", destSmsAddress, 10, TextField.PHONENUMBER);
		private TextField toSmsPort = new TextField("Send SMS Port", destSmsPort, 5, TextField.NUMERIC);
		private TextField fromSmsPort = new TextField("Listen SMS Port", listenSmsPort, 5, TextField.NUMERIC);
		private TextField fromCbsPort = new TextField("Listen CBS Port", listenCbsPort, 5, TextField.NUMERIC);

		public SetupScreen()
		{
			super("Setup");
			setCommandListener(this);
			addCommand(doneCmd);
			append(toSmsAddress);
			append(toSmsPort);
			append(fromSmsPort);
			append(fromCbsPort);
		}

		public void commandAction(Command command, Displayable displayable)
	   	{
		   destSmsAddress = toSmsAddress.getString();
		   destSmsPort = toSmsPort.getString();
		   listenSmsPort = fromSmsPort.getString();
		   listenCbsPort = fromCbsPort.getString();
		   showList();
	   	}
	}


	class SendScreen extends Form implements CommandListener
	{
    	private Command doneCmd = new Command("Send", Command.SCREEN, 1);
		private TextField toSmsAddress = new TextField("Send SMS Address", destSmsAddress, 10, TextField.PHONENUMBER);
		private TextField toSmsPort = new TextField("Send SMS Port", destSmsPort, 5, TextField.NUMERIC);
		private TextField msg = new TextField("Message", "", 256, TextField.ANY);

		public SendScreen()
		{
			super("Send");
			setCommandListener(this);
			addCommand(doneCmd);
			append(toSmsAddress);
			append(toSmsPort);
			toSmsAddress.setString(destSmsAddress);
			toSmsPort.setString(destSmsPort);
			msg.setString(messageToSend);
			append(msg);
		}

		public void commandAction(Command command, Displayable displayable)
	   	{
		   destSmsAddress = toSmsAddress.getString();
		   destSmsPort = toSmsPort.getString();
		   messageToSend = msg.getString();
		   doSend();
	   	}
	}
}