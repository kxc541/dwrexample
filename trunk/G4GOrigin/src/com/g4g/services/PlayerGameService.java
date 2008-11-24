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

import com.g4g.dto.PlayergameDTO;
import com.g4g.dto.SearchCriteria;
/**
 * Interface of service functions for Playergame table.
 * @author Punam
 *
 */
public interface PlayerGameService {
	/**
	 * Adds playergame details in playergame table.
	 * 
	 * @param dto -
	 *            object of PlayergameDTO kind containing details for the playergame.
	 * @return PlayergameDTO - returns PlayergameDTO kind object which is added.
	 * @throws HibernateException 
	 */
	public PlayergameDTO add(PlayergameDTO dto) throws HibernateException;
	/**
	 * Delete playergame's entry from playergame table.
	 * 
	 * @param dto -
	 *            object of PlayergameDTO kind containing details for the playergame.
	 * @throws HibernateException 
	 */	
	public void delete(PlayergameDTO dto) throws HibernateException;
	/**
	 * Returns list containing all playergames from playergame table.
	 * @return list containing PlayergameDTO objects.
	 * @throws HibernateException 
	 */
	public List<PlayergameDTO> getList()throws HibernateException;
	/**
	 * Returns list containing playergames from playergame table, but according to given
	 * criteria.
	 * object with attributes set as per the require search.
	 * @param searchCriteria 
	 * @return list containing PlayergameDTO objects satisfies given criteria.
	 * @throws HibernateException 
	 */
	public List<PlayergameDTO> getList(SearchCriteria searchCriteria)throws HibernateException;
	/**
	 * Update Playergames' information in Playergame table.
	 * 
	 * @param dto - PlayergameDTO's object
	 * @return PlayergameDTO's updated object
	 * @throws HibernateException 
	 */

	public PlayergameDTO update(PlayergameDTO dto) throws HibernateException;
	
}
