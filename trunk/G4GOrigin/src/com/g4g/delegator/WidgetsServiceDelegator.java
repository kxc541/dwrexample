/**********************************************************
 * WidgetsServiceDelegator.java :
 *
 * Created by Jigar Mistry
 * Last modified Date: 18 Apr .08 by Punam
 * Revision: 0.1
 * Version : 0.0.8
 * Copyright (c) 2008 - 2008 Askdigi, All rights reserved.
 **********************************************************/
package com.g4g.delegator;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Level;
import org.hibernate.HibernateException;

import com.g4g.dto.ActivePlayersDTO;
import com.g4g.dto.GameDTO;
import com.g4g.dto.GameTournamentChallengeDTO;
import com.g4g.dto.PlayNowDTO;
import com.g4g.dto.PlayerDTO;
import com.g4g.services.ServiceLocator;
import com.g4g.services.WidgetsService;
import com.g4g.utils.AuditUtil;
import com.g4g.utils.DataUtil;
import com.g4g.utils.G4GConstants;

/**
 * The Class WidgetsServiceDelegator delegates action calls to
 * WidgetsServiceImplementation class via WidgetService interface.
 * WidgetsServiceDelegator class is attached to WidgetsService interface for the
 * purpose of pre and post method interception.
 *
 * @author Jigar Mistry
 * @author Punam
 * @author Ankur
 */
public class WidgetsServiceDelegator {

	/** The service instance. */
	private static WidgetsService service = ServiceLocator.getInstance()
			.getWidgetsService();

	/**
	 * @param playerId
	 * @return playNowList
	 * @throws HibernateException
	 * @see WidgetsService#getPlayNowList(int)
	 *
	 */
	public static List<PlayNowDTO> getPlayNowList(int playerId)
			throws HibernateException {

		AuditUtil.getInstance().writeLog(AuditUtil.FILE_TYPE_G4G,
				new StringBuffer (WidgetsServiceDelegator.class.getName()).append(G4GConstants.COLON_WITH_SPACES).append(G4GConstants.DASHES)
						.append(G4GConstants.CURRENTMETHOD).append(DataUtil.getCurrentMethod())
						.append(G4GConstants.CALLINGMETHOD).append(DataUtil.getCallingMethod())
						.append(G4GConstants.DASHES).append(G4GConstants.STARTED).toString(),Level.INFO);

		List<PlayNowDTO> playNowList = service.getPlayNowList(playerId);

		AuditUtil.getInstance().writeLog(AuditUtil.FILE_TYPE_G4G,
				new StringBuffer (WidgetsServiceDelegator.class.getName()).append(G4GConstants.COLON_WITH_SPACES).append(G4GConstants.DASHES)
						.append(G4GConstants.CURRENTMETHOD).append(DataUtil.getCurrentMethod()).append(G4GConstants.CALLINGMETHOD)
						.append(DataUtil.getCallingMethod()).append(G4GConstants.DASHES)
						.append(G4GConstants.PLAYYNOWLIST).append(G4GConstants.EQUAL).append(playNowList.size())
						.append(G4GConstants.ENDED).toString(),Level.INFO);
		return playNowList;
	}

	/**
	 * @param searchString
	 * @param request
	 * @return playNowList
	 * @throws HibernateException
	 * @see WidgetsService#getPlayersList(String, HttpServletRequest)
	 */
	public static List<PlayerDTO> getPlayersList(String searchString,
			int pageStartsAt) throws HibernateException {
		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,
				new StringBuffer(WidgetsServiceDelegator.class.getName()).append(
						G4GConstants.COLON_WITH_SPACES).append(G4GConstants.CALLINGMETHOD)
						.append(DataUtil.getCallingMethod()).append(
								G4GConstants.CURRENTMETHOD).append(
								DataUtil.getCurrentMethod()).append(
								G4GConstants.DASHES).append(G4GConstants.STARTED).toString(),Level.INFO);

		List<PlayerDTO> playersList = service.getPlayersList(searchString,
				pageStartsAt);
		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,
				new StringBuffer(WidgetsServiceDelegator.class.getName()).append(
						G4GConstants.COLON_WITH_SPACES).append(G4GConstants.CALLINGMETHOD)
						.append(DataUtil.getCallingMethod()).append(
								G4GConstants.CURRENTMETHOD).append(
								DataUtil.getCurrentMethod()).append(
								G4GConstants.DASHES).append(G4GConstants.DASHES)
								.append(G4GConstants.PLAYERSLIST).append(G4GConstants.EQUAL).append(playersList.size())
								.append(
										G4GConstants.ENDED).toString(),Level.INFO);



		return playersList;
	}

	/**
	 * @param searchString
	 * @param request
	 * @return gamesList
	 * @throws HibernateException
	 * @see WidgetsService#getGamesList(String, HttpServletRequest)
	 */
	public static List<GameDTO> getGamesList(String searchString,
			int pageStartsAt) throws HibernateException {
		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,
				new StringBuffer(WidgetsServiceDelegator.class.getName()).append(
						G4GConstants.COLON_WITH_SPACES).append(G4GConstants.CALLINGMETHOD)
						.append(DataUtil.getCallingMethod()).append(
								G4GConstants.CURRENTMETHOD).append(
								DataUtil.getCurrentMethod()).append(
								G4GConstants.DASHES).append(
								G4GConstants.STARTED).toString(),Level.INFO);
		List<GameDTO> gamesList = service.getGamesList(searchString, pageStartsAt);

		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,
				new StringBuffer(WidgetsServiceDelegator.class.getName()).append(
						G4GConstants.COLON_WITH_SPACES).append(G4GConstants.CALLINGMETHOD)
						.append(DataUtil.getCallingMethod()).append(
								G4GConstants.CURRENTMETHOD).append(
								DataUtil.getCurrentMethod()).append(
								G4GConstants.DASHES)
								.append(G4GConstants.DASHES)
								.append(G4GConstants.GAMESLIST).toString(),Level.INFO);
		return gamesList;
	}

	/**
	 * @param searchString
	 * @param request
	 * @return tournamentList
	 * @throws HibernateException
	 * @see WidgetsService#getTournamentList(String, HttpServletRequest)
	 */
	public static List<GameTournamentChallengeDTO> getTournamentList(
			String searchString, int pageStartsAt)
			throws HibernateException {
		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,
				new StringBuffer(WidgetsServiceDelegator.class.getName()).append(
						G4GConstants.COLON_WITH_SPACES).append(G4GConstants.CALLINGMETHOD)
						.append(DataUtil.getCallingMethod()).append(
								G4GConstants.CURRENTMETHOD).append(
								DataUtil.getCurrentMethod()).append(
								G4GConstants.DASHES).append(
								G4GConstants.STARTED).toString(),Level.INFO);
		List<GameTournamentChallengeDTO> tournamentList = service
				.getTournamentList(searchString, pageStartsAt);

		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,
				new StringBuffer(WidgetsServiceDelegator.class.getName()).append(
						G4GConstants.COLON_WITH_SPACES).append(G4GConstants.CALLINGMETHOD)
						.append(DataUtil.getCallingMethod()).append(
								G4GConstants.CURRENTMETHOD).append(
								DataUtil.getCurrentMethod()).append(
								G4GConstants.DASHES).append(
								G4GConstants.DASHES)
							.append(G4GConstants.TOURNAMENTLIST).append(G4GConstants.EQUAL).append(tournamentList.size())
								.toString(),Level.INFO);

		return tournamentList;
	}

	/**
	 * @param searchString
	 * @param request
	 * @return playNowList
	 * @throws HibernateException
	 * @see WidgetsService#getGamesList(String, HttpServletRequest)
	 */
	public static List<GameTournamentChallengeDTO> getOpenChallengeList(
			String searchString, int pageStartsAt)
			throws HibernateException {
		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,
				new StringBuffer(WidgetsServiceDelegator.class.getName()).append(
						G4GConstants.COLON_WITH_SPACES).append(G4GConstants.CALLINGMETHOD)
						.append(DataUtil.getCallingMethod()).append(
								G4GConstants.CURRENTMETHOD).append(
								DataUtil.getCurrentMethod()).append(
								G4GConstants.DASHES).append(
								G4GConstants.STARTED).toString(),Level.INFO);

		List<GameTournamentChallengeDTO> OpenChallengeList = service
				.getOpenChallengeList(searchString, pageStartsAt);
		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,
				new StringBuffer(WidgetsServiceDelegator.class.getName()).append(
						G4GConstants.COLON_WITH_SPACES).append(G4GConstants.CALLINGMETHOD)
						.append(DataUtil.getCallingMethod()).append(
								G4GConstants.CURRENTMETHOD).append(
								DataUtil.getCurrentMethod()).append(
								G4GConstants.DASHES).append(
								G4GConstants.DASHES)
								.append(G4GConstants.OPENCHALLENGELIST_CAPS).append(G4GConstants.EQUAL).append(OpenChallengeList.size())
								.toString(),Level.INFO);
		return OpenChallengeList;

	}

	/**
	 * @param playerId
	 * @return OpenChallengeList
	 * @throws HibernateException
	 * @see WidgetsService#getGamesList(String, HttpServletRequest)
	 */
	public static List<GameDTO> getMyLobby(int playerId, int networkid)
			throws HibernateException {
		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,
				new StringBuffer(WidgetsServiceDelegator.class.getName()).append(G4GConstants.DASHES)
				.append(G4GConstants.CURRENTMETHOD).append(DataUtil.getCurrentMethod())
				.append(G4GConstants.CALLINGMETHOD).append(DataUtil.getCallingMethod())
				.append(G4GConstants.DASHES).append(G4GConstants.STARTED).toString(), Level.INFO);

		List<GameDTO> myLobby = service.getMyLobby(playerId, networkid);

		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,
				new StringBuffer(WidgetsServiceDelegator.class.getName()).append(
						G4GConstants.COLON_WITH_SPACES).append(G4GConstants.CALLINGMETHOD)
						.append(DataUtil.getCallingMethod()).append(
								G4GConstants.CURRENTMETHOD).append(
								DataUtil.getCurrentMethod()).append(
								G4GConstants.DASHES).append(G4GConstants.MYLOBBY).append(G4GConstants.EQUAL).append(myLobby.size())
								.append(G4GConstants.ENDED).toString(),Level.INFO);

		return myLobby;
	}

	/**
	 * @param maxRecords
	 * @param maxPlayedInDays
	 * @return activePlayers
	 * @throws HibernateException
	 * @see WidgetsService#getActivePlayers(int,int)
	 */
	public static List<ActivePlayersDTO> getActivePlayers(int maxRecords,
			int maxPlayedInDays) throws HibernateException {
		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,
				new StringBuffer(WidgetsServiceDelegator.class.getName()).append(G4GConstants.DASHES)
				.append(G4GConstants.CURRENTMETHOD).append(DataUtil.getCurrentMethod())
				.append(G4GConstants.CALLINGMETHOD).append(DataUtil.getCallingMethod())
				.append(G4GConstants.DASHES).append(G4GConstants.STARTED).toString(), Level.INFO);


		List<ActivePlayersDTO> activePlayers = service.getActivePlayers(
				maxRecords, maxPlayedInDays);

		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,
				new StringBuffer(WidgetsServiceDelegator.class.getName()).append(G4GConstants.DASHES)
				.append(G4GConstants.CURRENTMETHOD).append(DataUtil.getCurrentMethod())
				.append(G4GConstants.CALLINGMETHOD).append(DataUtil.getCallingMethod())
				.append(G4GConstants.DASHES).append(G4GConstants.ENDED).toString(), Level.INFO);
		return activePlayers;
	}
}