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

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.hibernate.HibernateException;

import com.g4g.dto.MatchDTO;
import com.g4g.dto.PlayerDTO;
import com.g4g.dto.SearchCriteria;
import com.g4g.dto.SearchListCriteria;
import com.g4g.dto.TwoplayermatchDTO;
import com.g4g.forms.DirectedChallengeForm;

/**
 * Class contains method signature related to twoplayermatch.
 *
 * @author ankur
 */
public interface TwoPlayerMatchService {

	/**
	 * Adds record to twoplayermatch table.
	 *
	 * @param dto
	 * @return TwoplayermatchDTO
	 * @throws HibernateException
	 */
	public TwoplayermatchDTO add(TwoplayermatchDTO dto) throws HibernateException;

	/**
	 * Delete records from twoplayermatch table.
	 *
	 * @param dto
	 * @throws HibernateException
	 */
	public void delete(TwoplayermatchDTO dto) throws HibernateException;

	/**
	 * Returns all the records from twoplayermatch table.
	 *
	 * @return List<TwoplayermatchDTO>
	 * @throws HibernateException
	 */
	public List<TwoplayermatchDTO> getList()throws HibernateException;

	/**
	 * Returns records from twoplayermatch table based on searchCriteria.
	 *
	 * @param searchCriteria
	 * @return List<TwoplayermatchDTO>
	 * @throws HibernateException
	 */
	public List<TwoplayermatchDTO> getList(SearchCriteria searchCriteria)throws HibernateException;

	/**
	 * Returns records from twoplayermatch table based on searchListCriteria.
	 *
	 * @param searchCriteria
	 * @return List<TwoplayermatchDTO>
	 * @throws HibernateException
	 */
	public List<TwoplayermatchDTO> getList(SearchListCriteria searchCriteria)throws HibernateException;

	/**
	 * Returns updated twoplayermatch.
	 *
	 * @param dto
	 * @return TwoplayermatchDTO
	 * @throws HibernateException
	 */
	public TwoplayermatchDTO update(TwoplayermatchDTO dto)  throws HibernateException;

	/**
	 * Returns twoplayermatch based on id of given twoplayermatch.
	 *
	 * @param dto
	 * @return TwoplayermatchDTO
	 * @throws HibernateException
	 */
	public TwoplayermatchDTO get(TwoplayermatchDTO dto)throws HibernateException;

	/**
	 * @param id
	 * @return TwoplayermatchDTO
	 * @throws HibernateException
	 */
	public TwoplayermatchDTO getDTOFromId(int id)throws HibernateException;
	/**
	 * Checks if player's schedule on that date
	 *
	 * @param id
	 * @param date
	 * @return List<TwoplayermatchDTO>
	 * @throws HibernateException
	 */
	public List<TwoplayermatchDTO> CheckPlayerAtScheduleDate(int id, Date date) throws HibernateException;

	/**
	 * Gives match's information of given matchid
	 *
	 * @param matchId
	 * @param request
	 * @return MatchDTO
	 * @throws HibernateException
	 */
	public MatchDTO getMatchInformation(int matchId, HttpServletRequest request)throws HibernateException;

	// explicitly for challenge
	/**
	 * Gets DTO for challenge
	 *
	 * @param tounamentId
	 * @param request
	 * @return TwoplayermatchDTO
	 * @throws HibernateException
	 */
	public TwoplayermatchDTO getDtoForChallenge(int tounamentId,
			HttpServletRequest request)throws HibernateException;

	/**
	 * checks availability to join the tournament
	 *
	 * @param tournamentid
	 * @param level
	 * @return checkLevelAvailibility
	 * @throws HibernateException
	 */
	public boolean checkLevelAvailibility(int tournamentid, int level)throws HibernateException;

	/**
	 * Gets player's totalChallengeMoney.
	 *
	 * @param playerId
	 * @return Double
	 * @throws HibernateException
	 */
	public Double getPlayersTotalChallengeMoney(int playerId)throws HibernateException;

	/**
	 * Check if user is schedule around the schedule time he had provided for 1
	 * hrs
	 *
	 * @author ankur
	 * @param form
	 * @param dto
	 * @return true/false
	 * @throws HibernateException
	 */
	public List<TwoplayermatchDTO> checkPlayerChallengeAvailability(
			DirectedChallengeForm form, PlayerDTO dto)throws HibernateException;

	/**
	 * Fetches all the up-coming matches from the database.
	 *
	 * @return TwoplayermatchDTO
	 * @throws HibernateException
	 */
	public List<TwoplayermatchDTO> getUpcomingMatches()throws HibernateException;

	public List<TwoplayermatchDTO> getConsoleMatches(PlayerDTO playerDTO ,int... networkid  )throws HibernateException;
}
