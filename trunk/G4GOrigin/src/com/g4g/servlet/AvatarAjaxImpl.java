/**********************************************************
 * AvatarAjaxImpl.java :
 *
 * Created by Jigar Mistry
 * Last modified Date: 18 Apr .08 by Punam
 * Revision: 0.1
 * Version : 0.0.8
 * Copyright (c) 2008 - 2008 Askdigi, All rights reserved.
 **********************************************************/
package com.g4g.servlet;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.g4g.plugin.G4GOriginSession;
import com.g4g.utils.G4GConstants;

// $ANALYSIS-IGNORE
/**
 * The Class AvatarAjaxImpl has ajax implementation for avatar status. It gives
 * the avatar status for the given screenname whether the user is online or not.
 *
 * @author Jigar Mistry
 */
@SuppressWarnings( { "CloneDoesntCallSuperClone" })
public class AvatarAjaxImpl {

	/**
	 * Instantiates a new avatar ajax impl.
	 */
	private AvatarAjaxImpl() {
	}

	/**
	 * This class should not be instantiated and cloned.
	 *
	 * @return the object
	 *
	 * @throws CloneNotSupportedException
	 *             the clone not supported exception
	 */
	@Override
	public AvatarAjaxImpl clone() throws CloneNotSupportedException {
		throw new CloneNotSupportedException();
	}

	/**
	 * The getAvatarStatus method gets the status of the player. It will check
	 * that the player is online or not for the player screenname and returns
	 * the status of the player.
	 *
	 * @param screenName
	 *            the player screenname whose avatar status is retrieved.
	 * @param request
	 *
	 * @return the avatar status online/offline
	 */
	@SuppressWarnings(G4GConstants.UNCHECKED)
	public static String getAvatarStatus(String screenName, HttpServletRequest request) {
		if(Boolean.valueOf(((Map<String, Boolean>) request.getSession().getServletContext().getAttribute(G4GOriginSession.ONLINE_USERS)).get(screenName))) {
			return G4GConstants.ONLINE;
		} else {
			return G4GConstants.OFFLINE;
		}
	}
}
