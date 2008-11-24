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

import com.g4g.dto.NotificationtypeDTO;
import com.g4g.dto.SearchCriteria;
import com.g4g.utils.G4GConstants;

/**
 * Service interface for admin table.
 * 
 * @author Pratik
 * 
 */

public interface NotificationTypeService {
	/**
	 * Adds Admin details in Admin table.
	 * 
	 * @param dto -
	 *            object of AdminDTO kind containing details for the Admin.
	 * @return AdminDTO - returns AdminDTO kind object which is added.
	 * @throws HibernateException
	 */
	public NotificationtypeDTO add(NotificationtypeDTO dto)
			throws HibernateException;

	/**
	 * Delete admin's entry from Admin table.
	 * 
	 * @param dto -
	 *            object of NotificationtypeDTO kind containing details for the
	 *            Admin.
	 * @return void
	 */
	public void delete(NotificationtypeDTO dto) throws HibernateException;

	/**
	 * Returns list containing all Admin from Admin table.
	 * 
	 * @param
	 * @return list containing NotificationtypeDTO objects.
	 */
	public List<NotificationtypeDTO> getList() throws HibernateException;

	/**
	 * Returns list containing Admin from Admin table, but according to given
	 * criteria.
	 * 
	 * @param searchCriteria
	 *            object with attributes set as per the require search.
	 * @return list containing NotificationtypeDTO objects satisfies given
	 *         criteria.
	 */
	@SuppressWarnings( { G4GConstants.JAVADOCREFERENCE })
	public List<NotificationtypeDTO> getList(SearchCriteria searchCriteria)
			throws HibernateException;

	/**
	 * Update Admin information in Admin table.
	 * 
	 * @param dto -
	 *            NotificationtypeDTO's object
	 * @return NotificationtypeDTO's updated object
	 * @throws HibernateException
	 */
	public NotificationtypeDTO update(NotificationtypeDTO dto)
			throws HibernateException;

	public NotificationtypeDTO getNotificationType(int id)
			throws HibernateException;

}
