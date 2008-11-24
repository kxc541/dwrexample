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

import java.util.List;

import org.hibernate.HibernateException;

import com.g4g.dto.SearchCriteria;
import com.g4g.dto.TwoplayertournamentgameoptionsDTO;
/**
 * Class contains method signature related to twoplayertournamentgameoption.
 * @author ankur
 */
public interface TwoPlayerTournamentGameOptionService {
	/**
	 * Adds record to twoplayertournamentgameoptions table.
	 * @param dto
	 * @return TwoplayertournamentgameoptionsDTO
	 * @throws HibernateException 
	 */
	public TwoplayertournamentgameoptionsDTO add(TwoplayertournamentgameoptionsDTO dto) throws HibernateException;
	/**
	 * Delete records from twoplayertournamentgameoptions table.
	 * @param dto
	 * @throws HibernateException 
	 */
	public void delete(TwoplayertournamentgameoptionsDTO dto) throws HibernateException;
	/**
	 * Returns all the records from twoplayertournamentgameoptions table.
	 * @return List<TwoplayertournamentgameoptionsDTO>
	 * @throws HibernateException 
	 */
	public List<TwoplayertournamentgameoptionsDTO> getList()throws HibernateException;
	/**
	 * Returns records from twoplayertournamentgameoptions table based on searchCriteria.
	 * @param searchCriteria
	 * @return List<TwoplayertournamentgameoptionsDTO>
	 * @throws HibernateException 
	 */
	public List<TwoplayertournamentgameoptionsDTO> getList(SearchCriteria searchCriteria)throws HibernateException;
	/**
	 * Returns updated TwoplayertournamentgameoptionsDTO.
	 * @param dto
	 * @return TwoplayertournamentgameoptionsDTO
	 * @throws HibernateException 
	 */
	public TwoplayertournamentgameoptionsDTO update(TwoplayertournamentgameoptionsDTO dto) throws HibernateException;
	/**
	 * Returns TwoplayertournamentgameoptionsDTO based on id of given TwoplayertournamentgameoptionsDTO.
	 * @param dto
	 * @return TwoplayertournamentgameoptionsDTO
	 * @throws HibernateException 
	 */
	
	public TwoplayertournamentgameoptionsDTO get(TwoplayertournamentgameoptionsDTO dto)throws HibernateException;
	
	/**
	 * Deletes all record of given tournament.
	 * @param tournamentid
	 * @throws HibernateException 
	 */
	public void deleteAll(int tournamentid)throws HibernateException;
}
