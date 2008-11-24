/**
 *
 */
package com.ankur.chat;

import java.util.Collection;
import java.util.LinkedList;

import org.directwebremoting.WebContext;
import org.directwebremoting.WebContextFactory;
import org.directwebremoting.proxy.dwr.Util;
import org.directwebremoting.util.Logger;

/**
 * @author ankur
 */
public class JavaChat {

	/**
	 * Adds the message.
	 *
	 * @param text
	 *            the text
	 */
	public void addMessage(String text) {
		if (text != null && text.trim().length() > 0) {
			messages.addFirst(new Message(text));
			while (messages.size() > 10) {
				messages.removeLast();
			}
		}

		WebContext wctx = WebContextFactory.get();
		String currentPage = wctx.getCurrentPage();

		// Clear the input box in the browser that kicked off this page only
		Util utilThis = new Util(wctx.getScriptSession());
		utilThis.setValue("text", "");

		// For all the browsers on the current page:
		Collection<?> sessions = wctx.getScriptSessionsByPage(currentPage);
		Util utilAll = new Util(sessions);
		try {
			Thread.sleep(10000);
		}
		catch (InterruptedException exception) {
			exception.printStackTrace();
		}
		// Clear the list and add in the new set of messages
		//utilAll.removeAllOptions("chatlog");
		utilAll.addOptions("chatlog", messages, "text");
	}

	private LinkedList<Message> messages = new LinkedList<Message>();

	protected static final Logger log = Logger.getLogger(JavaChat.class);
}
