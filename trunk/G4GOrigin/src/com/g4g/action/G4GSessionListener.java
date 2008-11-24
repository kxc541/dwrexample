/**********************************************************
 * G4GSessionListener.java : 
 *
 * Created by Punam
 * Last modified Date: 23 Apr .08 by Punam
 * Revision: 0.1
 * Version : 0.0.8
 * Copyright (c) 2008 - 2008 Askdigi, All rights reserved.
 **********************************************************/
package com.g4g.action;

import static com.g4g.utils.G4GConstants.ACTIVE_SESSION_DECREMENTED;
import static com.g4g.utils.G4GConstants.ACTIVE_SESSION_INCREMENTED;
import static com.g4g.utils.G4GConstants.ADDING_USERDTO_T_SESSION;
import static com.g4g.utils.G4GConstants.BLANK;
import static com.g4g.utils.G4GConstants.DASHES;
import static com.g4g.utils.G4GConstants.G4GSESSIONLISTENER;
import static com.g4g.utils.G4GConstants.KEY_FOR_USER;
import static com.g4g.utils.G4GConstants.PLAYER_ONLINE_STATUS_UPDATE_FAILS;
import static com.g4g.utils.G4GConstants.REMOVING_PLAYER_FROM_SESSION;
import static com.g4g.utils.G4GConstants.USERNAME;
import static com.g4g.utils.G4GConstants.WITH_NOTHING_USERDTO;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.apache.log4j.Level;

import com.g4g.delegator.PlayerServiceDelegator;
import com.g4g.dto.UserDTO;
import com.g4g.plugin.G4GOriginSession;
import com.g4g.utils.AuditUtil;
import com.g4g.utils.DataUtil;
import com.g4g.utils.G4GConstants;

/**
 * The listener interface for receiving g4GSession events. The class that is
 * interested in processing a g4GSession event implements this interface, and
 * the object created with that class is registered with a component using the
 * component's <code>addG4GSessionListener<code> method. When
 * the g4GSession event occurs, that object's appropriate
 * method is invoked.
 * To implement the Session Management for following operations.
 * 
 * 1. To count the active session
 * @author Ankur
 */
public class G4GSessionListener implements HttpSessionListener {

	/** The active sessions. */
	private static int activeSessions = G4GConstants.ZERO;

	/**
	 * The addSession method is called when the user logs-in.One session is
	 * increased.The online flag in database is set to true.
	 * 
	 * @param inSession
	 *            Session to be added
	 * @param dto
	 *            the dto
	 */
	public static void addSession(HttpSession inSession, UserDTO dto) {
		if (inSession != null) {
			AuditUtil.getInstance().writeLog(
					AuditUtil.FILE_TYPE_SESSION,
					new StringBuffer(ADDING_USERDTO_T_SESSION).append(
							DataUtil.getCurrentMethod()).append(
							inSession.getId()).toString());
			dto.getPlayerDTO().setIsonline(true);
			try {
				PlayerServiceDelegator.update(dto.getPlayerDTO());
			} catch (Exception exception) {
				AuditUtil.getInstance().writeLog(
						AuditUtil.FILE_TYPE_G4G,
						new StringBuffer(G4GSESSIONLISTENER).append(
								PLAYER_ONLINE_STATUS_UPDATE_FAILS).append(
								DataUtil.getCurrentMethod()).append(
								inSession.getId()).toString(), Level.ERROR);
				AuditUtil.getInstance().writeLog(AuditUtil.FILE_TYPE_G4G,
						exception);
			}
			G4GOriginSession.map.put(inSession.getId(), dto);
			AuditUtil.getInstance().writeLog(AuditUtil.FILE_TYPE_SESSION,
					KEY_FOR_USER + inSession.getId());
			G4GOriginSession originSession = new G4GOriginSession();
			originSession.setApplication(inSession.getServletContext());
			originSession.resetOnlineUser();
		}
	}

	/**
	 * This method will increent the session each time the user logs in.
	 * 
	 * @see javax.servlet.http.HttpSessionListener#sessionCreated(javax.servlet.http.HttpSessionEvent)
	 */
	public void sessionCreated(HttpSessionEvent se) {
		activeSessions++;
		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_SESSION,
				new StringBuffer(ACTIVE_SESSION_INCREMENTED).append(
						activeSessions).append(DASHES).append(
						DataUtil.getCurrentMethod()).toString(), Level.INFO);
		G4GOriginSession.map.put(se.getSession().getId(), null);
		AuditUtil.getInstance().writeLog(AuditUtil.FILE_TYPE_SESSION,
				se.getSession().getId() + WITH_NOTHING_USERDTO);
	}

	/**
	 * The session Destroyed method is called when the user logs-out, closes the
	 * browser, or remains idle more than 30 minutes. User gets logs-out
	 * isOnline field in the database will be updated to false, User will be
	 * offline for other users. His session is decremeted from the session.
	 * Session Invalidation Event validates the session.
	 * 
	 * @see javax.servlet.http.HttpSessionListener#sessionDestroyed(javax.servlet.http.HttpSessionEvent)
	 */
	public void sessionDestroyed(HttpSessionEvent se) {
		if (activeSessions > G4GConstants.ZERO) {
			activeSessions--;
		}
		UserDTO dto = (UserDTO) G4GOriginSession.map.get(se.getSession()
				.getId());
		if (dto != null) {
			AuditUtil.getInstance().writeLog(
					AuditUtil.FILE_TYPE_SESSION,
					new StringBuffer(REMOVING_PLAYER_FROM_SESSION).append(
							USERNAME).append(BLANK).append(
							dto.getPlayerDTO().getEmailaddress()).toString(),
					Level.INFO);
			dto.getPlayerDTO().setIsonline(false);
			try {
				PlayerServiceDelegator.update(dto.getPlayerDTO());
			} catch (Exception exception) {
				AuditUtil.getInstance()
						.writeLog(
								AuditUtil.FILE_TYPE_HIBERNATE,
								new StringBuffer(this.getClass().getName())
										.append(G4GConstants.COLON_WITH_SPACES).append(
												DataUtil.getCurrentMethod())
										.append(G4GConstants.DASHES).append(
												exception.getMessage())
										.toString(), Level.ERROR);
				AuditUtil.getInstance()
						.writeLog(
								AuditUtil.FILE_TYPE_G4G,
								new StringBuffer(this.getClass().getName())
										.append(G4GConstants.COLON_WITH_SPACES).append(
												DataUtil.getCurrentMethod())
										.append(G4GConstants.DASHES).append(
												exception.getMessage())
										.toString(), Level.ERROR);
			}
		}
		G4GOriginSession originSession = new G4GOriginSession();
		originSession.setApplication(se.getSession().getServletContext());
		originSession.resetOnlineUser();
		AuditUtil.getInstance().writeLog(AuditUtil.FILE_TYPE_SESSION,
				ACTIVE_SESSION_DECREMENTED + activeSessions);
	}

	/**
	 * Gets the active sessions at the time.
	 * 
	 * @return the active sessions
	 */
	public static int getActiveSessions() {
		return activeSessions;
	}
}