/**********************************************************
 * HibernateSessionFactory.java 
 *
 * Created : 16 apr. 08 by author
 * Last modified $ Date: $ by $ Author:  $
 * Revision: $ Revision:  $
 * Version : $ ID : 1$
 * Copyright (c) 2008 - 2008 Askdigi, All rights reserved.
 **********************************************************/
package com.g4g.utils;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

/**
 * The class Chat maintains the chat lobby operations as when the user enters
 * the lobby he/she is displayed in the lobby, as soon as he/she leaves the
 * lobby disappears from the chat lobby.
 * 
 * @author Brajesh, Jigar Mistry
 */
public class Chat {
	private static StringBuffer dateTime = new StringBuffer();
	
	private static final String REGEXP = "\\[]:";
	
	private static final String UNCHECKED = "unchecked"; //$NON-NLS-1$

	/**
	 * The method unSetUser removes the user from game lobby chat as soon as
	 * he/she removes the game lobby. Game will be known by the given gameId.
	 * 
	 * @param gameId
	 *            the game id for finding the gameLobby from which the user is
	 *            leaving.
	 * @param screenName
	 *            the screenname of the user who is in the lobby.
	 */
	@SuppressWarnings(UNCHECKED)
	public void unSetUser(String gameId, String screenName) {
		userMap.remove(screenName);
		((LinkedList<Message>) messageMap.get(gameId)).addFirst(new Message(G4GConstants.CHAT_FIRSTBRACKET
				+ screenName + G4GConstants.CHAT_LOBBYTEXT));
		if (userMap.size() == G4GConstants.ZERO && messageMap.get(gameId) != null) {
			((LinkedList<Message>) messageMap.get(gameId))
					.removeAll(((LinkedList<Message>) messageMap.get(gameId)));
		}
	}

	/**
	 * The method setUser displays user to GameLobby chat as soon as he/she
	 * enters the particular lobby . GameLobby is found by given gameId.
	 * 
	 * @param gameId
	 *            the game id for finding the gameLobby from which the user is
	 *            leaving.
	 * @param screenName
	 *            the screenname of the user who is in the lobby.
	 */
	@SuppressWarnings(UNCHECKED)
	public void setUser(String gameId, String screenName) {
		userMap.remove(screenName);
		if (userMap.size() == G4GConstants.ZERO && messageMap.get(gameId) != null) {
			((LinkedList<Message>) messageMap.get(gameId))
					.removeAll(((LinkedList<Message>) messageMap.get(gameId)));
		}
	}

	/**
	 * Adds the message.
	 * 
	 * @param text
	 *            the text
	 * @param gameId
	 *            the game id
	 * @param screenName
	 *            the screen name
	 * 
	 * @return the list< message>
	 */
	@SuppressWarnings(UNCHECKED)
	public List<Message> addMessage(String text, String gameId,
			String screenName) {
		
		if (userMap.get(screenName) == null) {
			userMap.put(screenName, ((LinkedList<Message>) messageMap
					.get(gameId)).size());
		}

		if (text != null && text.trim().length() > G4GConstants.ZERO) {
			dateTime.delete(0, dateTime.length());
			dateTime.append(G4GConstants.SQ_BRACKET).append(DataUtil.getDate(DataUtil.todayGMT(), G4GConstants.DATE_HH_mm)).append(G4GConstants.BRACKETRIGHT).append(G4GConstants.COLON);
			
			text = DataUtil.patternReplaceFirst(text, REGEXP, dateTime.toString());
			((LinkedList<Message>) messageMap.get(gameId))
					.addFirst(new Message(text));
		}

		return getMessages(gameId, screenName);
	}

	/**
	 * Gets the messages.
	 * 
	 * @param gameId
	 *            the game id
	 * @param screenName
	 *            the screen name
	 * 
	 * @return the messages
	 */
	@SuppressWarnings(UNCHECKED)
	public List<Message> getMessages(String gameId, String screenName) {

		if (messageMap.get(gameId) == null) {
			messageMap.put(gameId, new LinkedList<Message>());
		}

		int startLine = G4GConstants.ZERO;
		if (userMap.get(screenName) != null) {
			startLine = Integer.parseInt(userMap.get(screenName).toString());

		} else {
			((LinkedList<Message>) messageMap.get(gameId))
					.addFirst(new Message(G4GConstants.SQ_BRACKET + screenName
							+ G4GConstants.ENTERS_INTO_CHAT_LOBBY));
			startLine = ((LinkedList<Message>) messageMap.get(gameId)).size();
			userMap.put(screenName, startLine);

		}

		try {
			return ((LinkedList<Message>) messageMap.get(gameId)).subList(G4GConstants.ZERO,
					((LinkedList<Message>) messageMap.get(gameId)).size()
							- startLine);
		} catch (Exception e) {
			AuditUtil.getInstance().writeLog(AuditUtil.FILE_TYPE_G4G, e);
			return ((LinkedList<Message>) messageMap.get(gameId)).subList(G4GConstants.ZERO, G4GConstants.ZERO);

		}
	}

	/** The user map. */
	private static HashMap<String, Object> userMap = new HashMap<String, Object>();

	/** The message map. */
	private static HashMap<String, Object> messageMap = new HashMap<String, Object>();
}
