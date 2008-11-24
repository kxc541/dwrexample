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

import org.hibernate.HibernateException;

import com.g4g.dto.GameoptionsDTO;
import com.g4g.dto.OpenMatchDTO;
import com.g4g.dto.PastTournamentDTO;
import com.g4g.dto.PlayerDTO;
import com.g4g.dto.SearchCriteria;
import com.g4g.dto.SearchListCriteria;
import com.g4g.dto.TournamentGameDTO;
import com.g4g.dto.TwoplayertournamentDTO;
/**
 * Class contains method signature related to twoplayertournament.
 * @author punam
 */
public interface TwoPlayerTournamentService {
	/**
	 * Adds record into twoplayertournament table.
	 * 
	 * @param dto
	 * @return {@link TwoplayertournamentDTO}
	 * @throws HibernateException 
	 */
	public TwoplayertournamentDTO add(TwoplayertournamentDTO dto) throws HibernateException;
	/**
	 * Delete record from twoplayertournament table.
	 * 
	 * @param dto
	 * @throws HibernateException 
	 */
	public void delete(TwoplayertournamentDTO dto) throws HibernateException;
	
	/**
	 * Returns all the records from twoplayertournament table.
	 * @return List<TwoplayertournamentDTO>
	 * @throws HibernateException 
	 */
	public List<TwoplayertournamentDTO> getList()throws HibernateException;

	/**
	 * Returns TwoplayertournamentDTO based on SearchCriteria.
	 * @param searchCriteria
	 * @return List<TwoplayertournamentDTO>List<TwoplayertournamentDTO>
	 * @throws HibernateException 
	 */
	public List<TwoplayertournamentDTO> getList(SearchCriteria searchCriteria)throws HibernateException;
	/**
	 * Returns updated TwoplayertournamentDTO.
	 * @param dto
	 * @return TwoplayertournamentDTO
	 * @throws HibernateException 
	 */
	public TwoplayertournamentDTO update(TwoplayertournamentDTO dto) throws HibernateException;
	
	/**
	 * Returns TwoplayertournamentDTO based on id of given TwoplayertournamentDTO. 
	 * @param dto
	 * @return TwoplayertournamentDTO
	 * @throws HibernateException 
	 */
	public TwoplayertournamentDTO get(TwoplayertournamentDTO dto)throws HibernateException;
	
	/**
	 * Returns OpenMatches List. 
	 * @param gameId
	 * @param playerId
	 * @return List<OpenMatchDTO>
	 * @throws HibernateException 
	 */
	
	public List<OpenMatchDTO> getOpenMatchesDetails(int gameId, int playerId)throws HibernateException;
	
	/**
	 * Gives tournamentsList to be played. 
	 * @return List<TournamentGameDTO> 
	 * @throws HibernateException 
	 */
	public List<TournamentGameDTO> getTournamentActions()throws HibernateException;
	
	/**
	 * Gives past tournaments List.
	 * @return list of PastTournamentDTO
	 * @throws HibernateException 
	 */
	
	public List<PastTournamentDTO> getPastTournaments()throws HibernateException;
	
	/**
	 * Function returns list of players who has joined tournament.
	 * 
	 * @param tournamentId
	 * @return List<PlayerDTO>
	 */
	
	public List<PlayerDTO> getPlayersOfTournament(int tournamentId);
	public List<GameoptionsDTO> getGameOptionsOfTournament(int tournamentId);
	public Date getScheduledstartDate(int tournamentId);
	/**
	 * Gives list based on searchListCriteria.
	 * @param searchListCriteria
	 * @return List<TwoplayertournamentDTO>
	 */
	public List<TwoplayertournamentDTO> getList(SearchListCriteria searchListCriteria);
	

}
