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
import com.g4g.dto.Twoplayermatchcomments;

/**
 * Service interface for twoplayermatchcomments table.
 * 
 * @author Ankur
 * 
 */
public interface MatchCommentService {
	/**
	 * Adds twoplayermatchcomments details in twoplayermatchcomments table.
	 * 
	 * @param dto -
	 *            object of Twoplayermatchcomments kind containing details for
	 *            the Twoplayermatchcomments.
	 * @return Twoplayermatchcomments - returns Twoplayermatchcomments kind
	 *         object which is added.
	 */
	public Twoplayermatchcomments add(Twoplayermatchcomments dto)
			throws HibernateException;

	/**
	 * Delete twoplayermatchcomments' entry from Twoplayermatchcomments table.
	 * 
	 * @param dto -
	 *            object of Twoplayermatchcomments kind containing details for
	 *            the Twoplayermatchcomments.
	 * @return void
	 */
	public void delete(Twoplayermatchcomments dto) throws HibernateException;

	/**
	 * Returns Twoplayermatchcomments containing given gameId.
	 * 
	 * @param id -
	 *            gameId.
	 * @return Twoplayermatchcomments containing given gameId.
	 */
	public Twoplayermatchcomments getGame(int id) throws HibernateException;

	/**
	 * Returns list containing all twoplayermatches comments from
	 * twoplayermatchcomments table.
	 * 
	 * @param
	 * @return list containing Twoplayermatchcomments objects.
	 */
	public List<Twoplayermatchcomments> getList() throws HibernateException;

	/**
	 * Returns list Twoplayermatches comments' comments from
	 * Twoplayermatchcomments table, but according to given criteria.
	 * 
	 * @param searchCriteria
	 *            object with attributes set as per the require search.
	 * @return list containing Twoplayermatchcomments objects satisfies given
	 *         criteria.
	 */
    public List<Twoplayermatchcomments> getList(SearchCriteria searchCriteria)
			throws HibernateException;

	/**
	 * Update Twoplayermatchcomments information in Twoplayermatchcomments
	 * table.
	 * 
	 * @param dto -
	 *            Twoplayermatchcomments's object
	 * @return Twoplayermatchcomments's updated object
	 */
	public Twoplayermatchcomments update(Twoplayermatchcomments dto)
			throws HibernateException;

	/**
	 * Deletes all twoPlayermatch comment for given dto.
	 * 
	 * @param dto -
	 *            dto to delete.
	 */
	public void deleteMatchAllComments(Twoplayermatchcomments dto)
			throws HibernateException;

}
