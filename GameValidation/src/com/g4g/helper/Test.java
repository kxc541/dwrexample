package com.g4g.helper;

public class Test {
	public static void main(String[] args) {
		try {

			GameValidatorSchedule gv = new GameValidatorSchedule("TestJ",
					"TestT", 15L * 60L * 1000L);

		} catch (Exception e) {
			// TODO: handle exception
		}
		/*
		 * IMAPHelper imapHelper = new
		 * IMAPHelper(G4GProperties.getProperty(PropertiesConstants.G4G_IMAP_IP),
		 * G4GProperties.getProperty(PropertiesConstants.G4G_IMAP_USERNAME),
		 * G4GProperties.getProperty(PropertiesConstants.G4G_IMAP_PASSWORD),
		 * G4GProperties.getProperty(PropertiesConstants.G4G_IMAP_AUTH),
		 * G4GProperties.getProperty(PropertiesConstants.G4G_IMAP_NAME),
		 * Integer.parseInt(G4GProperties.getProperty(PropertiesConstants.G4G_IMAP_PORT)),
		 * G4GProperties.getProperty(PropertiesConstants.G4G_IMAP_FOLDER));
		 * imapHelper.connect(); MailMessage[] message =
		 * imapHelper.getMessages(); for(int index = 0; index < message.length;
		 * index++) { //System.out.println("From : " +
		 * message[index].getFrom()); System.out.println("Subject : " +
		 * message[index].getSubject()); //System.out.println("Body : " +
		 * message[index].getBody()); String subject =
		 * message[index].getSubject(); String[] playerNames = null;
		 * if(subject.startsWith("Madden NFL 08 - ")) { String tmpSubject =
		 * subject.substring("Madden NFL 08 - ".length(), subject.length());
		 * playerNames = tmpSubject.split(" vs. "); } for (int jindex = 0;
		 * jindex < playerNames.length; jindex++) { System.out.println(jindex + " : " +
		 * playerNames[jindex]); } }
		 */

		/*
		 * RSSParser rssParser = new RSSParser();
		 * rssParser.connect("http://www.bungie.net/stats/halo3rss.ashx?g=",
		 * "AXE"); List<RSSContent> rssContent = rssParser.getRSSContent(); int
		 * totalRSSContent = rssContent.size(); for (int index = 0; index <
		 * totalRSSContent; index++) { RSSContent rssContent2 = (RSSContent)
		 * rssContent.get(index); System.out.println(rssContent2.getDate()); }
		 */
	}
}
