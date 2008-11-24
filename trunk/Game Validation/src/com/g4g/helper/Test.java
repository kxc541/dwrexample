package com.g4g.helper;

import java.util.List;
import java.util.regex.Pattern;

public class Test {
	public static void main(String[] args) {
		try {
			GameValidatorSchedule gv = new GameValidatorSchedule("TestJ", "TestT", 10L*1000L);
		} catch (Exception e) {
			// TODO: handle exception
		}
		/*IMAPHelper imapHelper = new IMAPHelper("192.168.100.1", "g4g", "gaming", "true", "imap", 143, "inbox");
		imapHelper.connect();
		MailMessage[] message = imapHelper.getMessages();
		
		for(int index = 0; index < message.length; index++) {
			//System.out.println("From : " + message[index].getFrom());
			System.out.println("Subject : " + message[index].getSubject());
			//System.out.println("Body : " + message[index].getBody());
			String subject = message[index].getSubject();
			String[] playerNames = null;
			if(subject.startsWith("Madden NFL 08 - ")) {
				String tmpSubject = subject.substring("Madden NFL 08 - ".length(), subject.length());
				playerNames = tmpSubject.split(" vs. ");
			}
			
			for (int jindex = 0; jindex < playerNames.length; jindex++) {
				System.out.println(jindex + " : " + playerNames[jindex]);
			}
		}*/
		
		/*RSSParser rssParser = new RSSParser();
		rssParser.connect("http://www.bungie.net/stats/halo3rss.ashx?g=", "AXE");
		List<RSSContent> rssContent =  rssParser.getRSSContent();
		int totalRSSContent = rssContent.size();
		
		for (int index = 0; index < totalRSSContent; index++) {
			RSSContent rssContent2 = (RSSContent) rssContent.get(index);
			System.out.println(rssContent2.getDate());
		}*/
	}
}
