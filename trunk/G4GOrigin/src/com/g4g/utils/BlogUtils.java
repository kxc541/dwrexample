/**********************************************************
 * BlogUtils.java 
 *
 * Created by author Ankur
 * Last modified Date: 17 apr .08 by Author: Punam
 * Revision:  Revision:  
 * Version : $ ID : 1$
 * Copyright (c) 2008 - 2008 Askdigi, All rights reserved.
 **********************************************************/
package com.g4g.utils;

import com.g4g.utils.properties.G4GProperties;
import com.g4g.utils.properties.PropertiesConstants;

/**
 * The Class BlogUtils provides link for Blog/Forum, breaking news.
 * 
 * @author Ankur
 */
public class BlogUtils {

	/**
	 * The method getBreakingNewsLinkForThread gets the forum link stored in
	 * G4GProperties file, Gets the breaking news link.
	 * 
	 * @return the breaking news link
	 */
	public static String getBreakingNewsLink() {
		return G4GProperties.getProperty(PropertiesConstants.G4G_BLOG_LINK);
	}

	/**
	 * The method getBreakingNewsLinkForThread gets the forum link stored in
	 * G4GProperties file, Gets the breaking news link for thread.
	 * 
	 * @param threadId
	 *            the id for breakingNews thread.
	 * 
	 * @return the breaking news link for thread id given.
	 */
	public static String getBreakingNewsLinkForThread(int threadId) {
		return new StringBuffer (G4GProperties
				.getProperty(PropertiesConstants.G4G_BREAKING_NEWS_LINK)
				).append( String.valueOf(threadId)).toString();
	}

	/**
	 * The method getForumLink gets the forum link stored in G4GProperties file
	 * for redirecting to forum.
	 * 
	 * @return theforumlink string
	 */
	public static String getForumLink() {
		return G4GProperties.getProperty(PropertiesConstants.G4G_FORUM_LINK);
	}

}
