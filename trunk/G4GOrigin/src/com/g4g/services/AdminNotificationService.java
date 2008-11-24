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

import com.g4g.dto.AdminnotificationDTO;
import com.g4g.dto.SearchCriteria;

/**
 * Service interface for AdminNotification table.
 * 
 * @author Pratik
 * 
 */
public interface AdminNotificationService {
	/**
	 * Adds AdminNotification details in AdminNotification table.
	 * 
	 * @param dto -
	 *            object of AdminNotificationDTO kind containing details for the
	 *            AdminNotification.
	 * @return AdminNotificationDTO - returns AdminNotificationDTO kind object
	 *         which is added.
	 * @throws HibernateException
	 */
	public AdminnotificationDTO add(AdminnotificationDTO dto)
			throws HibernateException;

	/**
	 * Delete AdminNotification's entry from AdminNotification table.
	 * 
	 * @param dto -
	 *            object of AdminNotificationDTO kind containing details for the
	 *            AdminNotification.
	 * @throws HibernateException
	 */
	public void delete(AdminnotificationDTO dto) throws HibernateException;

	/**
	 * Returns list containing all AdminNotification from AdminNotification
	 * table.
	 * 
	 * @return list containing AdminNotificationDTO objects.
	 * @throws HibernateException
	 */
	public List<AdminnotificationDTO> getList() throws HibernateException;

	/**
	 * Returns list containing AdminNotification from AdminNotification table,
	 * but according to given criteria.
	 * 
	 * @param searchCriteria
	 * 
	 * object with attributes set as per the require search.
	 * @return list containing AdminNotificationDTO objects satisfies given
	 *         criteria.
	 * @throws HibernateException
	 */
	public List<AdminnotificationDTO> getList(SearchCriteria searchCriteria)
			throws HibernateException;

	/**
	 * Update AdminNotification information in AdminNotification table.
	 * 
	 * @param dto -
	 *            AdminNotificationDTO's object
	 * @return AdminNotificationDTO's updated object
	 * @throws HibernateException
	 */
	public AdminnotificationDTO update(AdminnotificationDTO dto)
			throws HibernateException;
}
