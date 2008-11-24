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

import com.g4g.dto.PlayercommentsDTO;
import com.g4g.dto.SearchCriteria;

/**
 * Service interface for PlayerComments table.
 * @author Pratik
 *
 */
public interface PlayerCommnetsService {
	/**
	 * Adds playercomments details in playercomments table.
	 * 
	 * @param dto -
	 *            object of PlayercommentsDTO kind containing details for the playercomments.
	 * @return PlayercommentsDTO - returns PlayercommentsDTO kind object which is added.
	 * @throws HibernateException 
	 */
	public PlayercommentsDTO add(PlayercommentsDTO dto) throws HibernateException;
	/**
	 * Delete playercomment's entry from playercomments table.
	 * 
	 * @param dto -
	 *            object of PlayercommentsDTO kind containing details for the playercomments.
	 * @throws HibernateException 
	 */	
	public void delete(PlayercommentsDTO dto) throws HibernateException;
	/**
	 * Returns list containing all players' comments from playercomments table.
	 * @return list containing PlayercommentsDTO objects.
	 * @throws HibernateException 
	 */
	 public List<PlayercommentsDTO> getList()throws HibernateException;
	/**
	 * Returns list containing players' comments from playercomments table, but according to given
	 * criteria.
	 * object with attributes set as per the require search.
	 * @param searchCriteria 
	 * @return list containing PlayercommentsDTO objects satisfies given criteria.
	 * @throws HibernateException 
	 */
	 public List<PlayercommentsDTO> getList(SearchCriteria searchCriteria)throws HibernateException;
	/**
	 * Update Players' comments information in Playercomments table.
	 * 
	 * @param dto - PlayercommentsDTO's object
	 * @return PlayercommentsDTO's updated object
	 * @throws HibernateException 
	 */
	 public PlayercommentsDTO update(PlayercommentsDTO dto) throws HibernateException;
	
 }
