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

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.hibernate.HibernateException;

import com.g4g.dto.GameoptionsDTO;
import com.g4g.dto.SearchCriteria;

/**
 * Service interface for gameoptions table.
 * 
 * @author ankur
 * 
 */
public interface GameOptionService {
	/**
	 * Adds games options details in gameoptions table.
	 * 
	 * @param dto -
	 *            object of GameoptionsDTO kind containing details for the
	 *            gameoptions.
	 * @return GameoptionsDTO - returns GameoptionsDTO kind object which is
	 *         added.
	 */
	public GameoptionsDTO add(GameoptionsDTO dto) throws HibernateException;

	/**
	 * Delete game's options entry from gameoptions table.
	 * 
	 * @param dto -
	 *            object of GameoptionsDTO kind containing details for the
	 *            gameoptions.
	 * @return void
	 */
	public void delete(GameoptionsDTO dto) throws HibernateException;

	/**
	 * Method gives GameoptionsDTO for the match of given GameoptionsDTO.
	 * 
	 * @param dto -
	 *            GameoptionsDTO
	 * @return GameoptionsDTO
	 */
	public GameoptionsDTO getGame(GameoptionsDTO dto) throws HibernateException;

	/**
	 * Returns list containing all game's options from gameoptions table.
	 * 
	 * @param
	 * @return list containing GameoptionsDTO objects.
	 */
	public List<GameoptionsDTO> getList() throws HibernateException;

	/**
	 * Returns list containing games' options from playercomments table, but
	 * according to given criteria.
	 * 
	 * @param searchCriteria
	 *            object with attributes set as per the require search.
	 * @return list containing GameoptionsDTO objects satisfies given criteria.
	 * @throws HibernateException 
	 */
	public List<GameoptionsDTO> getList(SearchCriteria searchCriteria)throws HibernateException;
	
	
	/**
	 * Update game's options information in gameoptions table.
	 * 
	 * @param dto -
	 *            GameoptionsDTO's object
	 * @return GameoptionsDTO's updated object
	 */
	public GameoptionsDTO update(GameoptionsDTO dto) throws HibernateException;

	/**
	 * It sets game option list's in request object with names
	 * G4GConstants.TOTAL_LIST, (i - 1)
	 * 
	 * @param request
	 * @param gameid
	 */
	public void setgameOptionListInRequest(HttpServletRequest request,int gameid) throws HibernateException;

	/**
	 * @param gameid
	 * @return HashMap<String, List<GameoptionsDTO>>
	 */
	public HashMap<String, List<GameoptionsDTO>> getGameOptionListOfGame(int gameid) throws HibernateException;

	public List<GameoptionsDTO> getGameOptions(int gameid , int optionsequenceid,SearchCriteria searchCriteria)throws HibernateException;
	
	
}
