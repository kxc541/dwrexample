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

/**
 * Service interface for message table.
 * 
 * @author Jigar Mistry
 */

import java.util.List;

import org.hibernate.HibernateException;

import com.g4g.dto.MessageDTO;
import com.g4g.dto.SearchListCriteria;
import com.g4g.utils.G4GConstants;

public interface MessageService {
	/**
	 * Adds messages details in message table.
	 * 
	 * @param dto -
	 *            object of MessageDTO kind containing details for the message.
	 * @return MessageDTO - returns MessageDTO kind object which is added.
	 */
	public MessageDTO add(MessageDTO dto) throws HibernateException;

	/**
	 * Delete messages' entry from message table.
	 * 
	 * @param dto -
	 *            object of MessageDTO kind containing details for the message.
	 * @return void
	 */
	public void delete(MessageDTO dto) throws HibernateException;

	/**
	 * Method gives count of all archived messages' count for given playerId.
	 * 
	 * @param playerId -
	 *            pass the playerId to get information of.
	 * @return archived messages' count.
	 */
	public int getArchivedCount(int playerId) throws HibernateException;

	/**
	 * Method gives List of all archived messages' for given playerId.
	 * 
	 * @param playerId -
	 *            pass the playerId to get information of.
	 * @return archived messages' list.
	 */
	public List<MessageDTO> getArchivedList(int playerId)
			throws HibernateException;

	/**
	 * Method gives count of messages' count according to given criteria.
	 * 
	 * @param searchListCriteria
	 *            object with attributes set as per the require search.
	 * @return messages' count according to criteria.
	 */
	public int getCount(SearchListCriteria searchListCriteria)
			throws HibernateException;

	/**
	 * Returns list containing all messages' from message table.
	 * 
	 * @param
	 * @return list containing MessageDTO objects.
	 */
	public List<MessageDTO> getList() throws HibernateException;

	/**
	 * Method gives count of all archived messages' count according to given
	 * criteria.
	 * 
	 * @param searchCriteria
	 *            object with attributes set as per the require search.
	 * @return messages' count according to criteria.
	 */
	@SuppressWarnings( { G4GConstants.JAVADOCREFERENCE })
	public List<MessageDTO> getList(SearchListCriteria searchCriteria)
			throws HibernateException;

	/**
	 * Method gives message of given messageId(id).
	 * 
	 * @param id -
	 *            messageId to get message of.
	 * @return MessageDTO of given id.
	 */
	public MessageDTO getMessage(String id) throws HibernateException;

	/**
	 * Update messages' information in message table.
	 * 
	 * @param dto -
	 *            MessageDTO's object
	 * @return MessageDTO's updated object
	 */
	public MessageDTO update(MessageDTO dto) throws HibernateException;
}
