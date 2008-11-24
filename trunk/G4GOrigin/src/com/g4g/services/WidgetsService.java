/**********************************************************
 * className.java :
 *
 * Created by
 * Last modified Date: 6 Jun .08 by Punam
 * Revision: 0.1
 * Version : 0.3.4076
 * Copyright (c) 2008 - 2008 Askdigi, All rights reserved.
 **********************************************************/
package com.g4g.services;

/**
 * Class contains methods signature for widgets.
 *
 * @author Jigar Mistry
 */

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.hibernate.HibernateException;

import com.g4g.dto.ActivePlayersDTO;
import com.g4g.dto.GameDTO;
import com.g4g.dto.GameTournamentChallengeDTO;
import com.g4g.dto.PlayNowDTO;
import com.g4g.dto.PlayerDTO;

/**
 * @author jigar mistry
 *
 */
public interface WidgetsService {
	/**
	 * The method getPlayNowList returns list of all the open matches. For which
	 * one player is decided and second player is yet to decide. Open matches
	 * will be displayed to all players. All the open Matches a
	 *
	 * @param playerId
	 * @return List<PlayNowDTO>
	 * @throws HibernateException
	 */
	public List<PlayNowDTO> getPlayNowList(int playerId)
			throws HibernateException;

	/**
	 * Function returns list of players.
	 *
	 * @param searchString
	 * @param request
	 * @return List<PlayerDTO>
	 * @throws HibernateException
	 */
	public List<PlayerDTO> getPlayersList(String searchString,
			int pageStartsAt) throws HibernateException;

	/**
	 * Function returns list of games.
	 *
	 * @param searchString
	 * @param request
	 * @return List<GameDTO>
	 * @throws HibernateException
	 */
	public List<GameDTO> getGamesList(String searchString,
			int pageStartsAt) throws HibernateException;

	/**
	 * Function returns list of tournaments.
	 *
	 * @param searchString
	 * @param request
	 * @return List<GameTournamentChallengeDTO>
	 * @throws HibernateException
	 */
	public List<GameTournamentChallengeDTO> getTournamentList(
			String searchString, int pageStartsAt)
			throws HibernateException;

	/**
	 * Function returns list of Open Challenges.
	 *
	 * @param searchString
	 * @param request
	 * @return List<GameTournamentChallengeDTO>
	 * @throws HibernateException
	 */
	public List<GameTournamentChallengeDTO> getOpenChallengeList(
			String searchString, int pageStartsAt)
			throws HibernateException;

	/**
	 * Function returns value for MyLobby widget.
	 *
	 * @param playerId
	 * @return List<GameDTO>
	 * @throws HibernateException
	 */
	public List<GameDTO> getMyLobby(int playerId, int networkid)
			throws HibernateException;

	/**
	 * Function returns value for ActivePlayers widget.
	 *
	 * @param maxRecords
	 * @param maxPlayedInDays
	 * @return List<ActivePlayersDTO>
	 * @throws HibernateException
	 */
	public List<ActivePlayersDTO> getActivePlayers(int maxRecords,
			int maxPlayedInDays) throws HibernateException;
}
