/**
 *
 */
package com.ankur.chat;

import org.directwebremoting.Security;

/**
 * The Class Message.
 *
 * @author ankur
 */
public class Message {

	/**
	 * Instantiates a new message.
	 *
	 * @param newtext the newtext
	 */
	public Message(String newtext)
	   {
	     text = newtext;
	     if (text.length() > 256)
	     {
	        text = text.substring(0, 256);
	     }
	     text = Security.replaceXmlCharacters(text);
	    }

	    /**
    	 * Gets the id.
    	 *
    	 * @return the id
    	 */
    	public long getId()
	    {
	        return id;
	    }

	    /**
    	 * Gets the text.
    	 *
    	 * @return the text
    	 */
    	public String getText()
	    {
	        return text;
	    }

	    /** The id. */
    	private long id = System.currentTimeMillis();

	    /** The text. */
    	private String text;

}
