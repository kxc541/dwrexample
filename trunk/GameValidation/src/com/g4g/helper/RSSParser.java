package com.g4g.helper;

/**
 * This class parse the RSS feed on the basis of URL and gamer tag.
 * 
 * @author Jigar Mistry
 */

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import com.sun.cnpi.rss.elements.Channel;
import com.sun.cnpi.rss.elements.Description;
import com.sun.cnpi.rss.elements.Item;
import com.sun.cnpi.rss.elements.Rss;
import com.sun.cnpi.rss.parser.RssParser;
import com.sun.cnpi.rss.parser.RssParserException;
import com.sun.cnpi.rss.parser.RssParserFactory;

public class RSSParser {
	private Rss rss = null;
	
	private static SimpleDateFormat dateFormat = new SimpleDateFormat("'Game played at' EEE, dd MMM yyyy HH:mm:ss zzz");
	
	private static SimpleDateFormat teamDateFormat = new SimpleDateFormat("'Team game played at' EEE, dd MMM yyyy HH:mm:ss zzz");
	
	/**
	 * 
	 * @param URL
	 * @param user
	 */
	@SuppressWarnings("unchecked")
	public void connect(String URL, String user) {
		RssParser parser;
		try {
			parser = RssParserFactory.createDefault();
			this.rss = parser.parse(new URL(URL + user));
		} catch (RssParserException rssParserException) {
			rssParserException.printStackTrace();
		} catch (MalformedURLException malformedURLException) {
			malformedURLException.printStackTrace();
		} catch (IOException ioException) {
			ioException.printStackTrace();
		}
	}
	
	/**
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<RSSContent> getRSSContent() {
		List<RSSContent> rssContentList = new ArrayList<RSSContent>();
		
		Channel channel = rss.getChannel();
		Collection<Item> coll = channel.getItems();
			
		Iterator<Item> items = coll.iterator();
		while(items.hasNext()) {
			Item item = (Item) items.next();
			Description description = item.getDescription();
			RSSContent rssContent = rssContentChecker(description.getText());
			
			if(rssContent != null)
				rssContentList.add(rssContent);
		}			
		
		return rssContentList;
	}
	
	/**
	 * 
	 * @param rssContent
	 * @return {@link RSSContent}
	 */
	private RSSContent rssContentChecker(String rssContent) {
		RSSContent rssContentObj = null;
		String[] rssContents = rssContent.split("<br/>");

		try {
			if(rssContents[RSSLines.PLAYERLIST.eval()].substring(rssContents[RSSLines.PLAYERLIST.eval()].indexOf(": ") + 2, rssContents[RSSLines.PLAYERLIST.eval()].length()).equals("Head To Head")) {
				rssContentObj = new RSSContent();
				Date date = null;
				if(rssContents[RSSLines.DATE.eval()].startsWith("Team"))
					date = teamDateFormat.parse(rssContents[RSSLines.DATE.eval()]);
				else
					date = dateFormat.parse(rssContents[RSSLines.DATE.eval()]);
				
				String[] gamerTags = rssContents[RSSLines.GAMERTAG.eval()].substring(rssContents[RSSLines.GAMERTAG.eval()].indexOf(": "), rssContents[RSSLines.GAMERTAG.eval()].length()).split(",");
				String[][] player = new String[rssContents.length - 5][gamerTags.length + 1];
				
				int playerIndex = 0;
				for (int index = RSSLines.GAMERTAG.eval() + 1; index < rssContents.length; index++) {
					String[] playerPoints = rssContents[index].substring(rssContents[index].indexOf(": "), rssContents[index].length()).split(",");
					player[playerIndex][0] = rssContents[index].substring(0, rssContents[index].indexOf(": "));
					
					for(int tagIndex = 0; tagIndex < gamerTags.length; tagIndex++) {
						player[playerIndex][tagIndex + 1] = playerPoints[tagIndex];
					}
					
					playerIndex++;
				}
				
				rssContentObj.setDate(date);
				rssContentObj.setGamerTags(gamerTags);
				rssContentObj.setPlayers(player);
				
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		return rssContentObj;
	}
}
