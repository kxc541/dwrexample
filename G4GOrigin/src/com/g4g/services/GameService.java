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

import com.g4g.dto.GameDTO;
import com.g4g.dto.SearchCriteria;
import com.g4g.utils.G4GConstants;

/**
 * Service interface for game table.
 * 
 * @author Punam
 */
public interface GameService {
	/**
	 * Adds games details in game table.
	 * 
	 * @param dto -
	 *            object of GameDTO kind containing details for the game.
	 * @return GameDTO - returns GameDTO kind object which is added.
	 */
	public GameDTO add(GameDTO dto) throws HibernateException;

	/**
	 * Delete game entry from game table.
	 * 
	 * @param dto -
	 *            object of GameDTO kind containing details for the game.
	 * @return void
	 */
	public void delete(GameDTO dto) throws HibernateException;

	/**
	 * Get game details by given id.
	 * 
	 * @param id
	 * @return GameDTO
	 */
	public GameDTO getGame(int id) throws HibernateException;

	/**
	 * Returns list containing all games from game table.
	 * 
	 * @param
	 * @return list containing GameDTO objects.
	 */
	public List<GameDTO> getList() throws HibernateException;

	/**
	 * Returns list containing games from game table, but according to given
	 * criteria.
	 * 
	 * @param searchCriteria
	 *            object with attributes set as per the require search.
	 * @return list containing GameDTO objects satisfies given criteria.
	 */
	@SuppressWarnings( { G4GConstants.JAVADOCREFERENCE })
	public List<GameDTO> getList(SearchCriteria searchCriteria)
			throws HibernateException;

	/**
	 * Method gives List containing GameDTO object of given network.
	 * 
	 * @param array -
	 *            Array of network consoles.
	 * @param networkId -
	 *            networkId to compare with given consoles.
	 * @return List<GameDTO>
	 * @author Ankur
	 */
	public List<GameDTO> getList(String[] array, int networkId)
			throws HibernateException;

	/**
	 * Update games information in game table.
	 * 
	 * @param dto -
	 *            GameDTO's object
	 * @return GameDTO's updated object
	 */
	public GameDTO update(GameDTO dto) throws HibernateException;
}
