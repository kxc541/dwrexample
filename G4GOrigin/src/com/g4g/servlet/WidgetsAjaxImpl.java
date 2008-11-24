/**********************************************************
 * WidgetsAjaxImpl.java :
 *
 * Created by Jigar Mistry
 * Last modified Date: 18 Apr .08 by Punam
 * Revision: 0.1
 * Version : 0.0.8
 * Copyright (c) 2008 - 2008 Askdigi, All rights reserved.
 **********************************************************/
package com.g4g.servlet;

import static com.g4g.utils.G4GConstants.*;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Level;
import org.hibernate.HibernateException;

import com.g4g.delegator.BreakingNewsServiceDelegator;
import com.g4g.delegator.G4GFinancialDelegator;
import com.g4g.delegator.GameOptionServiceDelegator;
import com.g4g.delegator.GameServiceDelegator;
import com.g4g.delegator.SubNationalCodeServiceDelegator;
import com.g4g.delegator.TwoPlayerMatchServiceDelegator;
import com.g4g.delegator.TwoPlayerTournamentGameOptionServiceDelegator;
import com.g4g.delegator.TwoPlayerTournamentServiceDelegator;
import com.g4g.delegator.WidgetsServiceDelegator;
import com.g4g.dto.AvatarsDTO;
import com.g4g.dto.BreakingNewsDTO;
import com.g4g.dto.GameDTO;
import com.g4g.dto.GameTournamentChallengeDTO;
import com.g4g.dto.GameoptionsDTO;
import com.g4g.dto.PlayNowDTO;
import com.g4g.dto.PlayerDTO;
import com.g4g.dto.SearchCriteria;
import com.g4g.dto.SearchListCriteria;
import com.g4g.dto.TournamentGameDTO;
import com.g4g.dto.TwoplayermatchDTO;
import com.g4g.dto.TwoplayertournamentDTO;
import com.g4g.dto.TwoplayertournamentgameoptionsDTO;
import com.g4g.dto.UserDTO;
import com.g4g.plugin.G4GOriginSession;
import com.g4g.utils.AuditUtil;
import com.g4g.utils.BlogUtils;
import com.g4g.utils.DataUtil;
import com.g4g.utils.FriendsUtil;
import com.g4g.utils.G4GConstants;
import com.g4g.utils.SQLConstants;
import com.g4g.utils.TournamentsUtil;
import com.impessa.worldgaming.client.InternalException_Exception;
import com.impessa.worldgaming.client.User;
import com.impessa.worldgaming.client.UserNotFoundException_Exception;

// $ANALYSIS-IGNORE
public class WidgetsAjaxImpl {

	private static final String JAVADOCREFERENCE = "JavadocReference"; //$NON-NLS-1$

	/**
	 * Constructor.
	 */
	private WidgetsAjaxImpl() {
	}

	/**
	 * This class should not be instantiated and cloned
	 */
	@Override
	public WidgetsAjaxImpl clone() throws CloneNotSupportedException {
		throw new CloneNotSupportedException();
	}

	/**
	 * The method playNowRefresh contains html div of playNow widget. this
	 * method is called by Jquery timer and its content are changed dynamically
	 * by ajax call. each time the widget is refreshed this method is called.
	 *
	 * @param playerId
	 *            the widget will contain contents for this playerId.
	 *
	 * @return the string having the html div for playnow widget.
	 * @author Jigar Mistry
	 */
	public static String playNowRefresh(int playerId, HttpServletRequest request) {
		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,
				new StringBuffer(WidgetsAjaxImpl.class.getName()).append(
						COLON_WITH_SPACES).append(CALLINGMETHOD).append(
						DataUtil.getCallingMethod()).append(CURRENTMETHOD)
						.append(DataUtil.getCurrentMethod()).append(DASHES)
						.append(STARTED).toString(), Level.INFO);
		StringBuffer htmlContent = new StringBuffer();
		try {
			UserDTO userDTO = DataUtil.getUserDTO(request);
			htmlContent.delete(ZERO, htmlContent.length());
			htmlContent.append(DataUtil.getURLContent(new StringBuffer(DataUtil
					.getLocalBasePath(request)).append(
					G4GConstants.DISPLAY_WIDGET_PLAY_NOW).append(
					G4GConstants.QUESTIONMARK).append(G4GConstants.PLAYERID)
					.append(G4GConstants.EQUALS).append(playerId).append(AMBERSAND).append(G4GConstants.USEROFFSET)
					.append(G4GConstants.EQUALS).append(userDTO.getOffset()).toString()));
		} catch  (HibernateException e) {
			AuditUtil.getInstance().writeLog(
					AuditUtil.FILE_TYPE_G4G,
					new StringBuffer(WidgetsAjaxImpl.class.getName()).append(
							COLON_WITH_SPACES).append(CALLINGMETHOD).append(
							DataUtil.getCallingMethod()).append(CURRENTMETHOD)
							.append(DataUtil.getCurrentMethod()).append(DASHES)
							.append(e.getMessage()).toString(), Level.INFO);
		}
		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,
				new StringBuffer(WidgetsAjaxImpl.class.getName()).append(
						COLON_WITH_SPACES).append(CALLINGMETHOD).append(
						DataUtil.getCallingMethod()).append(CURRENTMETHOD)
						.append(DataUtil.getCurrentMethod()).append(DASHES)
						.append(ENDED).toString(), Level.INFO);
		return htmlContent.toString();
	}

	/**
	 * The method gameDetailedDisplay contains html div of MyLobbies widget.
	 * this method is called by ajax where this div is required.Each time the
	 * page is refreshed this method is called.
	 *
	 * @param PLAYERID
	 *            the widget will contain contents for this playerId.
	 *
	 * @return the string having the html div for myLobbies widget.
	 *
	 * @author Punam
	 */
	public static String gameDetailedDisplay(HttpServletRequest request) {
		int playerId = DataUtil.getUserIdInteger(request);

		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,
				new StringBuffer(WidgetsAjaxImpl.class.getName()).append(
						COLON_WITH_SPACES).append(CALLINGMETHOD).append(
						DataUtil.getCallingMethod()).append(CURRENTMETHOD)
						.append(DataUtil.getCurrentMethod()).append(DASHES)
						.append(STARTED).toString(), Level.INFO);
		StringBuffer htmlContent = new StringBuffer();

		htmlContent.append(DataUtil.getURLContent(new StringBuffer(DataUtil
				.getLocalBasePath(request)).append(
				DISPLAY_WIDGET_GAME_LOBBY + playerId).toString()));

		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,
				new StringBuffer(WidgetsAjaxImpl.class.getName()).append(
						COLON_WITH_SPACES).append(CALLINGMETHOD).append(
						DataUtil.getCallingMethod()).append(CURRENTMETHOD)
						.append(DataUtil.getCurrentMethod()).append(DASHES)
						.append(ENDED).toString(), Level.INFO);
		return htmlContent.toString();
	}

	/**
	 * The method getBreakingNews contains html div of BreakingNews widget. this
	 * method is called by ajax where this div is required. Each time the page
	 * is refreshed this method is called. Breaking news contents are retrieved
	 * from BreakingNewsDelegator's list method.
	 *
	 * @param request
	 *            the widget will contain contents for this playerId.
	 *
	 * @return the string having the html div for BreakingNews widget.
	 * @author Ankur
	 */
	@SuppressWarnings( { JAVADOCREFERENCE })
	public static String getBreakingNews(HttpServletRequest request) {
		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,
				new StringBuffer(WidgetsAjaxImpl.class.getName()).append(
						COLON_WITH_SPACES).append(CALLINGMETHOD).append(
						DataUtil.getCallingMethod()).append(CURRENTMETHOD)
						.append(DataUtil.getCurrentMethod()).append(DASHES)
						.append(STARTED).append(DASHES).append(SESSIONID)
						.append(request.getSession().getId()).toString(),
				Level.INFO);

		List<BreakingNewsDTO> breakingNewsList = BreakingNewsServiceDelegator
				.getList();
		StringBuffer htmlContent = new StringBuffer();
		if (breakingNewsList != null && breakingNewsList.size() > ZERO) {
			Iterator<BreakingNewsDTO> it = breakingNewsList.iterator();
			/* making this string buffer blank */

			if (it.hasNext()) {
				for (int i = ZERO; it.hasNext() || i < THREE_NUMBER; i++) {
					BreakingNewsDTO breakingNewsDTO = it.next();
					htmlContent
							.append(
									ANCHOR_TAG
											+ BlogUtils
													.getBreakingNewsLinkForThread(breakingNewsDTO
															.getId())
											+ GAMELOBBY_CLOSING
											+ breakingNewsDTO.getTitle())
							.append(CLOSE_ANCTAG);
				}
			} else {
				htmlContent.append(NONE);
			}
		} else {
			htmlContent.append(NONE);
		}
		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,
				new StringBuffer(WidgetsAjaxImpl.class.getName()).append(
						COLON_WITH_SPACES).append(CALLINGMETHOD).append(
						DataUtil.getCallingMethod()).append(CURRENTMETHOD)
						.append(DataUtil.getCurrentMethod()).append(DASHES)
						.append(ENDED).append(DASHES).append(SESSIONID).append(
								request.getSession().getId()).toString(),
				Level.INFO);
		return htmlContent.toString();
	}

	/**
	 * The method foundPeopleHTMLContent returns HTML DIV containing players
	 * searched using the given search div. It searches people using
	 * WidgetsServiceDelegator's getPlayersList method, First time it displays 5
	 * records, Next and previous link makes ajax call to fill up the div,
	 * ViewAll also displays all the records by ajax call. It searches people by
	 * their screenname, loginname, emailaddress, consolename , gamename.<br>
	 *
	 * User can add people found as his friend if he is not.
	 *
	 * @param searchString
	 *            the search string - search people using this string.
	 * @param request
	 *            the request object to get the attributes from session.
	 *
	 * @return the string containing people div having search result.
	 * @author Punam
	 */

	public static String foundPeopleHTMLContent(String searchString,
			HttpServletRequest request) {
		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,
				new StringBuffer(WidgetsAjaxImpl.class.getName()).append(
						COLON_WITH_SPACES).append(CALLINGMETHOD).append(
						DataUtil.getCallingMethod()).append(CURRENTMETHOD)
						.append(DataUtil.getCurrentMethod()).append(DASHES)
						.append(STARTED).append(DASHES).append(SESSIONID)
						.append(request.getSession().getId()).toString(),
				Level.INFO);
		String pageStartsAt = request.getParameter(G4GConstants.PEOPLEPAGESTARTSAT)!= null?request.getParameter(G4GConstants.PEOPLEPAGESTARTSAT):G4GConstants.ZEROSTRING;
		String prevPage = request.getParameter(G4GConstants.PREVPEOPLEPAGE)!= null?request.getParameter(G4GConstants.PREVPEOPLEPAGE):G4GConstants.ZEROSTRING;
		String nextPage = request.getParameter(G4GConstants.NEXTPEOPLEPAGE)!= null?request.getParameter(G4GConstants.NEXTPEOPLEPAGE):G4GConstants.ZEROSTRING;
		String showAll = request.getParameter(G4GConstants.SHOWALLPEOPLE)!= null?request.getParameter(G4GConstants.SHOWALLPEOPLE):G4GConstants.NONE;
		String maxPage = request.getParameter(G4GConstants.MAXPEOPLEPAGES)!= null?request.getParameter(G4GConstants.MAXPEOPLEPAGES):G4GConstants.NONE;
		String allPlayers = request.getParameter(G4GConstants.ALLPLAYERS);
		StringBuffer htmlContent = new StringBuffer();

		htmlContent.append(DataUtil.getURLContent(new StringBuffer(DataUtil
				.getLocalBasePath(request)).append(
				G4GConstants.DISPLAYWIDGETSEARCHPEOPLE).append(QUESTIONMARK).append(
				PEOPLEPAGESTARTSAT).append(EQUALS).append(pageStartsAt).append(
				AMBERSAND).append(NEXTPEOPLEPAGE).append(EQUALS).append(nextPage)
				.append(AMBERSAND).append(PREVPEOPLEPAGE).append(EQUALS).append(
						prevPage).append(AMBERSAND).append(SEARCHSTRING)
				.append(EQUALS).append(searchString).append(AMBERSAND).append(
						SHOWALLPEOPLE).append(EQUALS).append(showAll).append(
						AMBERSAND).append(MAXPEOPLEPAGES).append(EQUALS).append(
						maxPage).append(AMBERSAND).append(ALLPLAYERS).append(
						EQUALS).append(allPlayers).toString()));

		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,
				new StringBuffer(WidgetsAjaxImpl.class.getName()).append(
						COLON_WITH_SPACES).append(CALLINGMETHOD).append(
						DataUtil.getCallingMethod()).append(CURRENTMETHOD)
						.append(DataUtil.getCurrentMethod()).append(DASHES)
						.append(ENDED).append(DASHES).append(SESSIONID).append(
								request.getSession().getId()).toString(),
				Level.INFO);
		return htmlContent.toString();

	}

	/**
	 * The method foundGameHTMLContent returns HTML DIV containing Games
	 * searched, using the given search div. It searches game using
	 * WidgetsServiceDelegator's getGamesList method, First time it displays 5
	 * records, Next and previous link makes ajax call to fill up the div,
	 * ViewAll also displays all the records by ajax call. It searches game by
	 * its consolename , gamename.<br>
	 *
	 * User can add game if he/she has that console. else can view the gamelobby
	 * but cannot add the game if he/she wants to add has to purchase that
	 * console.
	 *
	 * @param searchString
	 *            the search string - search game using this string.
	 * @param request
	 *            the request object to get the attributes from session.
	 *
	 * @return the string containing game div having search result.
	 * @author Punam
	 */

	public static String foundGameHTMLContent(String searchString,
			HttpServletRequest request) {
		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,
				new StringBuffer(WidgetsAjaxImpl.class.getName()).append(
						COLON_WITH_SPACES).append(CALLINGMETHOD).append(
						DataUtil.getCallingMethod()).append(CURRENTMETHOD)
						.append(DataUtil.getCurrentMethod()).append(DASHES)
						.append(STARTED).append(DASHES).append(SESSIONID)
						.append(request.getSession().getId()).toString(),
				Level.INFO);

		String pageStartsAt = request.getParameter(G4GConstants.GAMEPAGESTARTSAT)!= null?request.getParameter(G4GConstants.GAMEPAGESTARTSAT):G4GConstants.ZEROSTRING;
		String prevPage = request.getParameter(G4GConstants.PREVGAMEPAGE)!= null?request.getParameter(G4GConstants.PREVGAMEPAGE):G4GConstants.ZEROSTRING;
		String nextPage = request.getParameter(G4GConstants.NEXTGAMEPAGE)!= null?request.getParameter(G4GConstants.NEXTGAMEPAGE):G4GConstants.ZEROSTRING;
		String showAll = request.getParameter(G4GConstants.SHOWALLGAME)!= null?request.getParameter(G4GConstants.SHOWALLGAME):G4GConstants.NONE;
		String maxPage = request.getParameter(G4GConstants.MAXGAMEPAGES)!= null?request.getParameter(G4GConstants.MAXGAMEPAGES):G4GConstants.NONE;
		String allGames = request.getParameter(ALLGAMES)!= null?request.getParameter(G4GConstants.ALLGAMES):G4GConstants.ZEROSTRING;
		StringBuffer htmlContent = new StringBuffer();
		String playerId = DataUtil.getUserId(request);
		htmlContent.append(DataUtil.getURLContent(new StringBuffer(DataUtil
				.getLocalBasePath(request))
				.append(DISPLAYWIDGETSEARCHGAME).append(QUESTIONMARK)
				.append(GAMEPAGESTARTSAT).append(EQUALS).append(pageStartsAt)
				.append(AMBERSAND).append(NEXTGAMEPAGE).append(EQUALS).append(
						nextPage).append(AMBERSAND).append(PREVGAMEPAGE).append(
						EQUALS).append(prevPage).append(AMBERSAND).append(
						SEARCHSTRING).append(EQUALS).append(searchString)
				.append(AMBERSAND).append(SHOWALLGAME).append(EQUALS).append(
						showAll).append(AMBERSAND).append(MAXGAMEPAGES).append(
						EQUALS).append(maxPage).append(AMBERSAND).append(
						PLAYERID).append(EQUALS).append(playerId).append(
						AMBERSAND).append(ALLGAMES).append(EQUALS).append(
						allGames).toString()));
		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,
				new StringBuffer(WidgetsAjaxImpl.class.getName()).append(
						COLON_WITH_SPACES).append(CALLINGMETHOD).append(
						DataUtil.getCallingMethod()).append(CURRENTMETHOD)
						.append(DataUtil.getCurrentMethod()).append(DASHES)
						.append(ENDED).append(DASHES).append(SESSIONID).append(
								request.getSession().getId()).toString(),
				Level.INFO);
		return htmlContent.toString();
	}

	/**
	 * The method foundTournamentHTMLContent returns HTML DIV containing
	 * tournaments searched, using the given search div. It searches tournament
	 * using WidgetsServiceDelegator's getTournamentsList method, First time it
	 * displays 5 records, Next and previous link makes ajax call to fill up the
	 * div, ViewAll also displays all the records by ajax call. It searches
	 * tournament by its consolename , gamename. <br>
	 * User can add game if he/she has that console. else can view the gamelobby
	 * but cannot add the game if he/she wants to add has to purchase that
	 * console.
	 *
	 * @param searchString
	 *            the search string - search tournament using this string.
	 * @param request
	 *            the request object to get the attributes from session.
	 *
	 * @return the string containing tournament div having search result.
	 * @author Punam
	 */

	public static String foundTournamentHTMLContent(String searchString,
			HttpServletRequest request) {
		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,
				WidgetsAjaxImpl.class.getName() + COLON_WITH_SPACES
						+ CALLINGMETHOD + DataUtil.getCallingMethod()
						+ CURRENTMETHOD + DataUtil.getCurrentMethod() + DASHES
						+ STARTED + DASHES + SESSIONID
						+ request.getSession().getId(), Level.INFO);

		String pageStartsAt = request.getParameter(G4GConstants.TOURNAMENTPAGESTARTSAT)!= null?request.getParameter(G4GConstants.TOURNAMENTPAGESTARTSAT):G4GConstants.ZEROSTRING;
		String prevPage = request.getParameter(G4GConstants.PREVTOURNAMENTPAGE)!= null?request.getParameter(G4GConstants.PREVTOURNAMENTPAGE):G4GConstants.ZEROSTRING;
		String nextPage = request.getParameter(G4GConstants.NEXTTOURNAMENTPAGE)!= null?request.getParameter(G4GConstants.NEXTTOURNAMENTPAGE):G4GConstants.ZEROSTRING;
		String showAll = request.getParameter(G4GConstants.SHOWALLTOURNAMENT)!= null?request.getParameter(G4GConstants.SHOWALLTOURNAMENT):G4GConstants.NONE;
		String maxPage = request.getParameter(G4GConstants.MAXTOURNAMENTPAGES)!= null?request.getParameter(G4GConstants.MAXTOURNAMENTPAGES):G4GConstants.ZEROSTRING;
		String allTournaments = request.getParameter(ALLTOURNAMENTS)!= null?request.getParameter(ALLTOURNAMENTS):G4GConstants.ZEROSTRING;
		String offset = request.getParameter(USEROFFSET)!= null?request.getParameter(USEROFFSET):G4GConstants.ZEROSTRING;
		StringBuffer htmlContent = new StringBuffer();

		String playerId = DataUtil.getUserId(request);
		htmlContent.append(DataUtil.getURLContent(new StringBuffer(DataUtil
				.getLocalBasePath(request)).append(
				DISPLAYWIDGETSEARCHTOURNAMENT).append(QUESTIONMARK)
				.append(TOURNAMENTPAGESTARTSAT).append(EQUALS).append(pageStartsAt)
				.append(AMBERSAND).append(NEXTTOURNAMENTPAGE).append(EQUALS).append(
						nextPage).append(AMBERSAND).append(PREVTOURNAMENTPAGE).append(
						EQUALS).append(prevPage).append(AMBERSAND).append(
						SEARCHSTRING).append(EQUALS).append(searchString)
				.append(AMBERSAND).append(SHOWALLTOURNAMENT).append(EQUALS).append(
						showAll).append(AMBERSAND).append(MAXTOURNAMENTPAGES).append(
						EQUALS).append(maxPage).append(AMBERSAND).append(
						PLAYERID).append(EQUALS).append(playerId).append(
						AMBERSAND).append(ALLTOURNAMENTS).append(EQUALS)
				.append(allTournaments).append(
						AMBERSAND).append(USEROFFSET).append(EQUALS)
						.append(offset).toString()));
		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,
				new StringBuffer(WidgetsAjaxImpl.class.getName()).append(
						COLON_WITH_SPACES).append(CALLINGMETHOD).append(
						DataUtil.getCallingMethod()).append(CURRENTMETHOD)
						.append(DataUtil.getCurrentMethod()).append(DASHES)
						.append(ENDED).append(DASHES).append(SESSIONID).append(
								request.getSession().getId()).toString(),
				Level.INFO);

		return htmlContent.toString();
	}

	/**
	 * The method foundOpenChallengeHTMLContent returns HTML DIV containing
	 * OpenChallenges searched, using the given search div. It searches
	 * OpenChallenge using WidgetsServiceDelegator's getOpenChallengeList
	 * method, First time it displays 5 records, Next and previous link makes
	 * ajax call to fill up the div, ViewAll also displays all the records by
	 * ajax call. It searches OpenChallenges by its consolename , gamename. <br>
	 * User can add game if he/she has that console. else can view the gamelobby
	 * but cannot add the game if he/she wants to add has to purchase that
	 * console.
	 *
	 * @param searchString
	 *            the search string - search OpenChallenge using this string.
	 * @param request
	 *            the request object to get the attributes from session.
	 *
	 * @return the string containing OpenChallenge div having search result.
	 * @author Punam
	 */

	public static String foundOpenChallengeHTMLContent(String searchString,
			HttpServletRequest request) {
		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,
				new StringBuffer(WidgetsAjaxImpl.class.getName()).append(
						COLON_WITH_SPACES).append(CALLINGMETHOD).append(
						DataUtil.getCallingMethod()).append(CURRENTMETHOD)
						.append(DataUtil.getCurrentMethod()).append(DASHES)
						.append(STARTED).append(DASHES).append(SESSIONID)
						.append(request.getSession().getId()).toString(),
				Level.INFO);

		String pageStartsAt = request.getParameter(G4GConstants.OPENCHALLENGEPAGESTARTSAT)!= null?request.getParameter(G4GConstants.OPENCHALLENGEPAGESTARTSAT):G4GConstants.ZEROSTRING;
		String prevPage = request.getParameter(G4GConstants.PREVOPENCHALLENGEPAGE)!= null?request.getParameter(G4GConstants.PREVOPENCHALLENGEPAGE):G4GConstants.ZEROSTRING;
		String nextPage = request.getParameter(G4GConstants.NEXTOPENCHALLENGEPAGE)!= null?request.getParameter(G4GConstants.NEXTOPENCHALLENGEPAGE):G4GConstants.ZEROSTRING;
		String showAll = request.getParameter(G4GConstants.SHOWALLOPENCHALLENGE)!= null?request.getParameter(G4GConstants.SHOWALLOPENCHALLENGE):G4GConstants.NONE;
		String maxPage = request.getParameter(G4GConstants.MAXOPENCHALLENGEPAGES)!= null?request.getParameter(G4GConstants.MAXOPENCHALLENGEPAGES):G4GConstants.NONE;
		String allOpenChallenges = request.getParameter(ALLOPENCHALLENGES)!= null?request.getParameter(G4GConstants.ALLOPENCHALLENGES):G4GConstants.NONE;
		String offset = request.getParameter(USEROFFSET)!= null?request.getParameter(USEROFFSET):G4GConstants.ZEROSTRING;
		StringBuffer htmlContent = new StringBuffer();

		String playerId = DataUtil.getUserId(request);
		htmlContent.append(DataUtil.getURLContent(new StringBuffer(DataUtil
				.getLocalBasePath(request)).append(
				DISPLAYWIDGETSEARCHOPENCHALLENGE).append(QUESTIONMARK)
				.append(OPENCHALLENGEPAGESTARTSAT).append(EQUALS).append(pageStartsAt)
				.append(AMBERSAND).append(NEXTOPENCHALLENGEPAGE).append(EQUALS).append(
						nextPage).append(AMBERSAND).append(PREVOPENCHALLENGEPAGE).append(
						EQUALS).append(prevPage).append(AMBERSAND).append(
						SEARCHSTRING).append(EQUALS).append(searchString)
				.append(AMBERSAND).append(SHOWALLOPENCHALLENGE).append(EQUALS).append(
						showAll).append(AMBERSAND).append(MAXOPENCHALLENGEPAGES).append(
						EQUALS).append(maxPage).append(AMBERSAND).append(
						PLAYERID).append(EQUALS).append(playerId).append(
						AMBERSAND).append(
						ALLOPENCHALLENGES).append(EQUALS).append(
						allOpenChallenges).append(AMBERSAND).append(USEROFFSET)
				.append(EQUALS).append(offset).toString()));
		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,
				new StringBuffer(WidgetsAjaxImpl.class.getName()).append(
						COLON_WITH_SPACES).append(CALLINGMETHOD).append(
						DataUtil.getCallingMethod()).append(CURRENTMETHOD)
						.append(DataUtil.getCurrentMethod()).append(DASHES)
						.append(ENDED).append(DASHES).append(SESSIONID).append(
								request.getSession().getId()).toString(),
				Level.INFO);
		return htmlContent.toString();
	}

	public static String getOpenMatches(HttpServletRequest request) {
		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,
				new StringBuffer(WidgetsAjaxImpl.class.getName()).append(
						COLON_WITH_SPACES).append(CALLINGMETHOD).append(
						DataUtil.getCallingMethod()).append(CURRENTMETHOD)
						.append(DataUtil.getCurrentMethod()).append(DASHES)
						.append(STARTED).append(DASHES).append(SESSIONID)
						.append(request.getSession().getId()).toString(),
				Level.INFO);
		UserDTO userDTO = DataUtil.getUserDTO(request);
		int playerId = userDTO.getPlayerDTO().getId();
		boolean isStateIllegal = userDTO.isStateIllegal();
		int gameId = Integer.parseInt(request.getParameter(ID));
		int networkId = GameServiceDelegator.getGame(gameId).getNetwork()
				.getId();
		StringBuffer htmlContent = new StringBuffer();
		htmlContent.append(DataUtil.getURLContent(DataUtil
				.getLocalBasePath(request)
				+ OPEN_MATCHES_PATH
				+ QUESTIONMARK
				+ PLAYERID
				+ EQUALS
				+ playerId
				+ AMBERSAND
				+ GAMEID
				+ EQUALS
				+ gameId
				+ AMBERSAND
				+ NETWORKID_DB + EQUALS + networkId+ AMBERSAND
				+ G4GConstants.ISSTATEILLEGAL + EQUALS + userDTO.isStateIllegal()));
		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,
				new StringBuffer(WidgetsAjaxImpl.class.getName()).append(
						COLON_WITH_SPACES).append(CALLINGMETHOD).append(
						DataUtil.getCallingMethod()).append(CURRENTMETHOD)
						.append(DataUtil.getCurrentMethod()).append(DASHES)
						.append(ENDED).append(DASHES).append(SESSIONID).append(
								request.getSession().getId()).toString(),
				Level.INFO);
		return htmlContent.toString();
	}
}
