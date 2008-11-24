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

import com.g4g.dto.PlayerimDTO;
import com.g4g.dto.SearchCriteria;
/**
 * 
 * @author ankur
 *
 */
public interface PlayerImService {
	/**
	 * Adds player instant messanger details in Playerim table.
	 * 
	 * @param dto -
	 *            object of PlayerimDTO kind containing details for the player.
	 * @return PlayerimDTO - returns PlayerimDTO kind object which is added.
	 * @throws HibernateException 
	 */
	public PlayerimDTO add(PlayerimDTO dto) throws HibernateException;
	/**
	 * Delete player instant messanger's entry from Playerim table.
	 * 
	 * @param dto -
	 *            object of PlayerimDTO kind containing details for the player instant messanger.
	 * @throws HibernateException 
	 */	
	public void delete(PlayerimDTO dto)throws HibernateException;
	/**
	 * 
	 * @param id
	 * @return PlayerimDTO
	 * @throws HibernateException 
	 */

	public PlayerimDTO getGame(int id)throws HibernateException;
	/**
	 * Returns list containing all player instant messangers details from playerim table.
	 * 
	 * @return list containing PlayerimDTO objects.
	 * @throws HibernateException 
	 */
	public List<PlayerimDTO> getList()throws HibernateException;
	/**
	 * Returns list containing player instant messangers details from playerim table, but according to given
	 * criteria.
	 * @param searchCriteria 
	 * object with attributes set as per the require search.
	 * @return list containing PlayerimDTO objects satisfies given criteria.
	 * @throws HibernateException 
	 */
	public List<PlayerimDTO> getList(SearchCriteria searchCriteria)throws HibernateException;
	/**
	 * Update player instant messanger's information in Player table.
	 * 
	 * @param dto - PlayerimDTO's object
	 * @return PlayerimDTO's updated object
	 * @throws HibernateException 
	 */
	public PlayerimDTO update(PlayerimDTO dto) throws HibernateException;
}
