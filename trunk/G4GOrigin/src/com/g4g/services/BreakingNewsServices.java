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

import com.g4g.dto.BreakingNewsDTO;

/**
 * Service interface for thread table(In blog/forum portion).
 * 
 * @author Ankur
 * 
 */
public interface BreakingNewsServices {
	/**
	 * Returns list containing all BreakingNews from thread table.
	 * 
	 * @return list containing BreakingNewsDTO objects.
	 * @throws HibernateException
	 */
	public List<BreakingNewsDTO> getList() throws HibernateException;

	/**
	 * Adds thread details in thread table.
	 * 
	 * @param dto -
	 *            object of BreakingNewsDTO kind containing details for the
	 *            thread.
	 * @return BreakingNewsDTO - returns BreakingNewsDTO kind object which is
	 *         added.
	 * @throws HibernateException
	 */
	public BreakingNewsDTO add(BreakingNewsDTO dto) throws HibernateException;
}
