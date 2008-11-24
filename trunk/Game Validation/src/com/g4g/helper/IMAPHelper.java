package com.g4g.helper;

/**
 * This function helps to connect with IMAP server. Through that connection  
 * 
 * @author Jigar Mistry
 */

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Properties;

import javax.mail.Flags;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.NoSuchProviderException;
import javax.mail.Part;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.URLName;
import javax.mail.internet.InternetAddress;

public class IMAPHelper {
	private Store store = null;
	
	private Session session = null;
	
	private URLName urlName = null;
	
	public IMAPHelper(String IP, String userName, String password, String isAuth, String IMAPName, int IMAPPort, String folderName) {
		try {
			Properties proplist = new Properties();

			proplist.put("mail.host", IP);
			proplist.put("mail.user", userName);
			proplist.put("mail.password", password);
			proplist.put("mail.smtp.auth", isAuth);

			session = Session.getDefaultInstance(proplist, null);
			session.setDebug(false);
			
			this.urlName = new URLName(IMAPName, IP, IMAPPort, folderName, userName, password);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void connect() {
		try {
			store = this.session.getStore(urlName);
			store.connect();
		} catch(NoSuchProviderException noSuchProviderException) {
			noSuchProviderException.printStackTrace();
		} catch (MessagingException messagingException) {
			messagingException.printStackTrace();
		} 
	}

	public MailMessage[] getMessages() {
		try {
			Folder folder = store.getFolder(this.urlName);
			
			folder.open(Folder.READ_WRITE);
			
			if (folder.isOpen()) {
				int totalMessages = folder.getMessageCount();
				Message[] messages = folder.getMessages();
				MailMessage[] mailMessages = new MailMessage[totalMessages];
				
				for(int index = 0; index < totalMessages; index++) {
					mailMessages[index] = new MailMessage();
					mailMessages[index].setFrom(messageFrom(messages[index]));
					mailMessages[index].setSubject(messages[index].getSubject());
					mailMessages[index].setBody(readMessage(messages[index]));
					mailMessages[index].setRecievedDate(messages[index].getReceivedDate());
					//System.out.println(MailParser.processLineByLine(mailMessages[index].getBody()));
				}
				folder.close(true);
				
				return mailMessages;
			}
		} catch(NoSuchProviderException noSuchProviderException) {
			noSuchProviderException.printStackTrace();
		} catch (MessagingException messagingException) {
			messagingException.printStackTrace();
		} 
		
		return null;
	}
	
	private String messageFrom(Message message) {
		// Get the header information
		String from = null; 
		try {
			from = ((InternetAddress) message.getFrom()[0])
					.getPersonal();
			if (from == null)
				from = ((InternetAddress) message.getFrom()[0]).getAddress();
		} catch (MessagingException messagingException) {
			messagingException.printStackTrace();
		}
		
		return from;
	}
	
	private String readMessage(Message message) {
		StringBuffer result = new StringBuffer();
		
		try {
			// -- Get the message part (i.e. the message itself) --
			Part messagePart = message;
			Object content = messagePart.getContent();
			
			// -- or its first body part if it is a multipart message --
			if (content instanceof Multipart) {
				messagePart = ((Multipart) content).getBodyPart(0);
			}
			
			// -- Get the content type --
			String contentType = messagePart.getContentType();
			
			// -- If the content is plain text, we can print it --
			if (contentType.startsWith("TEXT/PLAIN") || contentType.startsWith("TEXT/HTML")) {
				
				InputStream is = messagePart.getInputStream();
				BufferedReader reader = new BufferedReader(new InputStreamReader(is));
				
				String thisLine = reader.readLine();
				while (thisLine != null) {
					result.append(thisLine).append("\n");
					thisLine = reader.readLine();
				}
			}
			
			// Delete the message from folder.
			//message.setFlag(Flags.Flag.DELETED, true);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		
		return result.toString();
	}
	
	public static void main(String[] args) {
		IMAPHelper mailHelper = new IMAPHelper("192.168.100.1", "g4g", "gaming", "true", "imap", 143, "inbox");
		mailHelper.connect();
		System.out.println("Total msg : " + mailHelper.getMessages().length);
	}
}
