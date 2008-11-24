/**********************************************************
 * G4GOriginSession.java :
 *
 * Created by Brajesh
 * Last modified Date: 18 Apr .08 by Punam
 * Revision: 0.1
 * Version : 0.0.8
 * Copyright (c) 2008 - 2008 Askdigi, All rights reserved.
 **********************************************************/
package com.g4g.plugin;

import static com.g4g.utils.G4GConstants.COLON_WITH_SPACES;
import static com.g4g.utils.G4GConstants.CALLINGMETHOD;
import static com.g4g.utils.G4GConstants.CURRENTMETHOD;
import static com.g4g.utils.G4GConstants.DASHES;
import static com.g4g.utils.G4GConstants.ENDED;
import static com.g4g.utils.G4GConstants.IS_LEGAL;
import static com.g4g.utils.G4GConstants.IS_ONLINE;
import static com.g4g.utils.G4GConstants.PLAYER_ONLINE_STATUS_UPDATE_FAIL;
import static com.g4g.utils.G4GConstants.STARTED;
import static com.g4g.utils.G4GConstants.TRUE;
import gnu.trove.THashMap;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;

import org.apache.log4j.Level;
import org.apache.struts.action.ActionServlet;
import org.apache.struts.action.PlugIn;
import org.apache.struts.config.ModuleConfig;

import com.g4g.delegator.AvatarsServiceDelegator;
import com.g4g.delegator.NationalCodeServiceDelegator;
import com.g4g.delegator.PlayerServiceDelegator;
import com.g4g.delegator.TimeZoneServiceDelegator;
import com.g4g.dto.AvatarsDTO;
import com.g4g.dto.PlayerDTO;
import com.g4g.dto.SearchCriteria;
import com.g4g.utils.AuditUtil;
import com.g4g.utils.DataUtil;
import com.g4g.utils.G4GConstants;

/**
 * The Class G4GOriginSession maintains the session while the running state of
 * the application. It sets the attributes in session when the server is
 * started. And removes total sessions from session when the server gets off.
 *
 * @author Brajesh
 */
public class G4GOriginSession implements PlugIn {
	/**  */
	public static final String ONLINE_USERS = "onlineUsers";

	/** The map holds the value for total session. */
	public static HashMap<Object, Object> map = new HashMap<Object, Object>();

	/** The map holds the value for total session. */
	public static Map<String, Boolean> onlineUsers = new THashMap<String, Boolean>();

	/** The TOTALSESSION String variable. */
	public static final String TOTALSESSION = "map";

	/** The LISt_registered_ player contains the list for registered players. */
	public static final String LIST_REGISTERED_PLAYER = "registerplayer";

	/** The LISt_online_player contains the list for online players. */
	public static final String LIST_ONLINE_PLAYER = "onlineplayer";

	/** The LISt_country_list contains the list for countries. */
	public static final String LIST_COUNTRY_LIST = "contryList";

	/** The LISt_time_zone contains the list for timezones. */
	public static final String LIST_TIME_ZONE = "timezoneList";

	public static final String GAME_OPTION_RULE_ENGINE = "gameoptionruleengine";
	public static final String AVATAR_LIST ="avatarlist";
	public static final String DEFAULTAVATAR = "defaultavatar";


	/** Defines the servletContext */
	private ServletContext application = null;

	/**
	 * TOTALSESSION attribute set in servletContext is removed when the server
	 * gets off.
	 */
	public void destroy() {
		AuditUtil.getInstance().writeLog(AuditUtil.FILE_TYPE_G4G,new StringBuffer(this.getClass().getName()).append(COLON_WITH_SPACES)
				.append(CALLINGMETHOD).append(DataUtil.getCallingMethod()).append(CURRENTMETHOD).append(DataUtil.getCurrentMethod()).append(DASHES).append(STARTED).toString(), Level.INFO);

		application.removeAttribute(TOTALSESSION);
		AuditUtil.getInstance().writeLog(AuditUtil.FILE_TYPE_G4G,new StringBuffer(this.getClass().getName()).append(COLON_WITH_SPACES)
				.append(CALLINGMETHOD).append(DataUtil.getCallingMethod()).append(CURRENTMETHOD).append(DataUtil.getCurrentMethod()).append(DASHES)
				.append(ENDED).toString(), Level.INFO);
	}

	/**
	 * Called once when application is loaded. Initilizes the application object
	 * with servletContext. Sets all the user status to offline. Sets
	 * countryList, RegisteredUsersList, timeZoneList, totalsessions to
	 * servletContext.
	 * @param actionServlet
	 * @param module
	 * @throws ServletException
	 */
	public void init(ActionServlet actionServlet, ModuleConfig module)throws ServletException {
		AuditUtil.getInstance().writeLog(AuditUtil.FILE_TYPE_G4G,new StringBuffer(this.getClass().getName()).append(COLON_WITH_SPACES)
				.append(CALLINGMETHOD).append(DataUtil.getCallingMethod()).append(CURRENTMETHOD).append(DataUtil.getCurrentMethod()).append(DASHES)
				.append(STARTED).toString(), Level.INFO);
    	AuditUtil.getInstance().writeLog(AuditUtil.FILE_TYPE_G4G,new StringBuffer(this.getClass().getName()).append(COLON_WITH_SPACES)
				.append(CALLINGMETHOD).append(DataUtil.getCallingMethod()).append(CURRENTMETHOD).append(DataUtil.getCurrentMethod()).append(DASHES).append(ENDED).toString(), Level.INFO);
		setSystemProperties();
		application = actionServlet.getServletContext();
		resetUserOnlineStatus();
		loadData();
		System.gc();
		AuditUtil.getInstance().writeLog(AuditUtil.FILE_TYPE_G4G,new StringBuffer(this.getClass().getName()).append(COLON_WITH_SPACES)
				.append(CALLINGMETHOD).append(DataUtil.getCallingMethod()).append(CURRENTMETHOD).append(DataUtil.getCurrentMethod()).append(DASHES)
				.append(ENDED).toString(), Level.INFO);
	}

	/**
	 * The resetUserOnlineStatus method sets the status of all the users to
	 * offline when the application is loaded.
	 */
	private void resetUserOnlineStatus() {AuditUtil.getInstance().writeLog(AuditUtil.FILE_TYPE_G4G,new StringBuffer(this.getClass().getName()).append(COLON_WITH_SPACES)
				.append(CALLINGMETHOD).append(DataUtil.getCallingMethod()).append(CURRENTMETHOD).append(DataUtil.getCurrentMethod()).append(DASHES)
				.append(STARTED).toString(), Level.INFO);


		List<PlayerDTO> list = PlayerServiceDelegator.getList();
		Iterator<PlayerDTO> it = list.iterator();
		while (it.hasNext()) {
			PlayerDTO dto = it.next();
			dto.setIsonline(false);
			try {
				PlayerServiceDelegator.update(dto);
				onlineUsers.put(dto.getScreenname(), Boolean.valueOf(false));
			}
			catch (Exception exception) {
				AuditUtil.getInstance().writeLog(AuditUtil.FILE_TYPE_G4G,new StringBuffer(this.getClass().getName()).append(COLON_WITH_SPACES)
						.append(CALLINGMETHOD).append(DataUtil.getCallingMethod()).append(CURRENTMETHOD).append(DataUtil.getCurrentMethod()).append(DASHES)
						.append(PLAYER_ONLINE_STATUS_UPDATE_FAIL).toString(), Level.ERROR);
				AuditUtil.getInstance().writeLog(AuditUtil.FILE_TYPE_HIBERNATE,new StringBuffer(this.getClass().getName()).append(COLON_WITH_SPACES)
						.append(CALLINGMETHOD).append(DataUtil.getCallingMethod()).append(CURRENTMETHOD).append(
								DataUtil.getCurrentMethod()).append(DASHES).append(exception.getMessage()).toString(),Level.ERROR);
			}
		}

		application.setAttribute(ONLINE_USERS, onlineUsers);
		AuditUtil.getInstance().writeLog(
    		    AuditUtil.FILE_TYPE_G4G,
    		    new StringBuffer(this.getClass().getName()).append(COLON_WITH_SPACES)
				.append(CALLINGMETHOD).append(
						DataUtil.getCallingMethod()).append(
						CURRENTMETHOD).append(
						DataUtil.getCurrentMethod()).append(DASHES)
				.append(ENDED).toString(), Level.INFO);
	}

	/**
	 * resetOnlineUser method sets all the online users list to servletContext
	 * when the application is loaded.
	 */
	public void resetOnlineUser() {
		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,
				new StringBuffer(this.getClass().getName()).append(COLON_WITH_SPACES)
				.append(CALLINGMETHOD).append(
						DataUtil.getCallingMethod()).append(
						CURRENTMETHOD).append(
						DataUtil.getCurrentMethod()).append(DASHES)
				.append(STARTED).toString(),
				Level.INFO);

		SearchCriteria searchCriteria = new SearchCriteria();
		searchCriteria.setAttribute(IS_ONLINE, true);
		List<PlayerDTO> list = PlayerServiceDelegator.getList(searchCriteria);
		application.setAttribute(LIST_ONLINE_PLAYER, list);
		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,
				new StringBuffer(this.getClass().getName()).append(COLON_WITH_SPACES)
				.append(CALLINGMETHOD).append(
						DataUtil.getCallingMethod()).append(
						CURRENTMETHOD).append(
						DataUtil.getCurrentMethod()).append(DASHES)
				.append(ENDED).toString(),
				Level.INFO);
	}

	/**
	 * The method loadData sets attribute values in the servletContext when the
	 * application is loaded. <br>
	 * It sets all the registered users to servletContext when the application
	 * is loaded. It sets timeZoneList to servletContext when the application is
	 * loaded. It sets countryList to servletContext when the application is
	 * loaded. It sets totalSessions value in servletContext when the
	 * application is loaded or server is started.
	 */
	public void loadData() {
		AuditUtil.getInstance().writeLog(AuditUtil.FILE_TYPE_G4G,new StringBuffer(this.getClass().getName()).append(COLON_WITH_SPACES).append(CALLINGMETHOD).append(DataUtil.getCallingMethod()).append(CURRENTMETHOD).append(
						DataUtil.getCurrentMethod()).append(DASHES).append(STARTED).toString(),Level.INFO);

		SearchCriteria searchCriteria = new SearchCriteria();
		// Load the # of Online players
		List<?> list = PlayerServiceDelegator.getList();
		application.setAttribute(LIST_REGISTERED_PLAYER, list);

		resetOnlineUser();

		searchCriteria.removeAllAttribute();
		searchCriteria.setAttribute(IS_LEGAL, TRUE);
		list = NationalCodeServiceDelegator.getList(searchCriteria);
		application.setAttribute(LIST_COUNTRY_LIST, list);

		list = TimeZoneServiceDelegator.getList();
		application.setAttribute(LIST_TIME_ZONE, list);

		application.setAttribute(TOTALSESSION, map);


		list = AvatarsServiceDelegator.getList();
		application.setAttribute(AVATAR_LIST, list);
		application.setAttribute(DEFAULTAVATAR, list.get(G4GConstants.ZERO));
		for (Iterator iterator = list.iterator(); iterator.hasNext();) {
			AvatarsDTO avatar = (AvatarsDTO) iterator.next();
			final String avatardto = G4GConstants.AVATAR+avatar.getId();
			application.setAttribute(avatardto, avatar);
		}

		application.setAttribute(GAME_OPTION_RULE_ENGINE, GameOptionsRuleEngine.getGameOptionsRuleEngine());

		AuditUtil.getInstance().writeLog(AuditUtil.FILE_TYPE_G4G,new StringBuffer(this.getClass().getName()).append(COLON_WITH_SPACES).append(CALLINGMETHOD).append(DataUtil.getCallingMethod()).append(
						CURRENTMETHOD).append(DataUtil.getCurrentMethod()).append(DASHES).append(ENDED).toString(), Level.INFO);


	}

	/**
	 * The method gives the servletContext object.
	 *
	 * @return the application
	 */
	public ServletContext getApplication() {
		AuditUtil.getInstance().writeLog(AuditUtil.FILE_TYPE_G4G,new StringBuffer(this.getClass().getName()).append(COLON_WITH_SPACES)
				.append(CALLINGMETHOD).append(
						DataUtil.getCallingMethod()).append(
						CURRENTMETHOD).append(
						DataUtil.getCurrentMethod()).append(DASHES)
				.append(STARTED).toString(), Level.INFO);
    	AuditUtil.getInstance().writeLog(AuditUtil.FILE_TYPE_G4G,new StringBuffer(this.getClass().getName()).append(COLON_WITH_SPACES)
				.append(CALLINGMETHOD).append(
						DataUtil.getCallingMethod()).append(
						CURRENTMETHOD).append(
						DataUtil.getCurrentMethod()).append(DASHES)
				.append(ENDED).toString(), Level.INFO);
		return application;
	}

	/**
	 * The method sets the servletContext object value.
	 *
	 * @param application
	 *            the new application
	 */
	public void setApplication(ServletContext application) {
		this.application = application;
	}

	/**
	 * It can be used to set up initial System Properties
	 */
	public void setSystemProperties() {


	}
}