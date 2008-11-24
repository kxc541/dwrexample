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

import com.g4g.dto.NotificationqueueDTO;
import com.g4g.dto.SearchListCriteria;

/**
 * Service interface for notificationqueue table.
 * 
 * @author Pratik
 * 
 */
public interface NotificationQueueService {
	/**
	 * Adds notificationqueue details in notificationqueue table.
	 * 
	 * @param dto -
	 *            object of NotificationqueueDTO kind containing details for the
	 *            notificationqueue.
	 * @return NotificationqueueDTO - returns NotificationqueueDTO kind object
	 *         which is added.
	 */
	public NotificationqueueDTO add(NotificationqueueDTO dto)
			throws HibernateException;

	/**
	 * Delete notificationqueue's entry from notificationqueue table.
	 * 
	 * @param dto -
	 *            object of NotificationqueueDTO kind containing details for the
	 *            notificationqueue.
	 * @return void
	 */
	public void delete(NotificationqueueDTO dto) throws HibernateException;

	/**
	 * Returns list containing all notificationqueues' from notificationqueues
	 * table.
	 * 
	 * @param
	 * @return list containing notificationqueueDTO objects.
	 */
	public List<NotificationqueueDTO> getList() throws HibernateException;

	/**
	 * Returns list containing notificationqueues' from notificationqueues
	 * table, but according to given criteria.
	 * 
	 * @param searchCriteria
	 *            object with attributes set as per the require search.
	 * @return list containing NotificationqueuesDTO objects satisfies given
	 *         criteria.
	 */
    public List<NotificationqueueDTO> getList(
			SearchListCriteria searchListCriteria) throws HibernateException;

	/**
	 * Update notificationqueues' information in notificationqueues table.
	 * 
	 * @param dto -
	 *            NotificationqueuesDTO's object
	 * @return NotificationqueuesDTO's updated object
	 */
	public NotificationqueueDTO update(NotificationqueueDTO dto)
			throws HibernateException;

}
