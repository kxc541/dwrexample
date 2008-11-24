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

/**
 * The class Message is used to implement chat messenger utilities. It maintains
 * the id and text for the chat between users. Converts the given messages to
 * appropriate message.
 * 
 * @author Brajesh
 */

public class Message {

	/**
	 * Constructor Message checks the length if length > 256 of the newText and converts the
	 * symbol to appropriate symbol.
	 * 
	 * @param newtext
	 *            the newtext is chat message passed.
	 */
	public Message(String newtext) {
		text = newtext;
		if (text.length() > G4GConstants.TWOHUNDREDFIFTYSIX_NUMBER) {
			text = text.substring(G4GConstants.ZERO, G4GConstants.TWOHUNDREDFIFTYSIX_NUMBER);
		}
		text = text.replace('<', '[').replace('&', '_');
	}

	/**
	 * The method getId gives Milliseconds.
	 * 
	 * @return the time in milliseconds.
	 */
	public long getId() {
		return id;
	}

	/**
	 * The method getText gives the chat message converted using appropriate symbols.
	 * 
	 * @return the text - the chat message
	 */
	public String getText() {
		return text;
	}

	/** The id contains millisecond */
	long id = System.currentTimeMillis();

	/** The text contains chat message */
	String text;
}
