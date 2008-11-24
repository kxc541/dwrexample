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

import com.g4g.dto.AvatarsDTO;
import com.g4g.dto.SearchCriteria;

/**
 * Service interface for avatars table.
 * 
 * @author Pratik
 * 
 */
public interface AvatarsService {
	/**
	 * Adds Avatars details in Avatars table.
	 * 
	 * @param dto -
	 *            object of AvatarsDTO kind containing details for the Avatars.
	 * @return AvatarsDTO - returns AvatarsDTO kind object which is added.
	 * @throws HibernateException
	 */
	public AvatarsDTO add(AvatarsDTO dto) throws HibernateException;

	/**
	 * Delete avatar's entry from Avatars table.
	 * 
	 * @param dto -
	 *            object of AvatarsDTO kind containing details for the Avatars.
	 * @throws HibernateException
	 */
	public void delete(AvatarsDTO dto) throws HibernateException;

	/**
	 * @param id
	 * @return AvatarsDTO
	 * @throws HibernateException
	 */
	public AvatarsDTO getAvatars(int id) throws HibernateException;

	/**
	 * Returns list containing all Avatars from Avatars table.
	 * 
	 * @return list containing AvatarsDTO objects.
	 * @throws HibernateException
	 */
	public List<AvatarsDTO> getList() throws HibernateException;

	/**
	 * Returns list containing Avatars from Avatars table, but according to
	 * given criteria.
	 * 
	 * object with attributes set as per the require search.
	 * 
	 * @param searchCriteria
	 * @return list containing AvatarsDTO objects satisfies given criteria.
	 * @throws HibernateException
	 */
	public List<AvatarsDTO> getList(SearchCriteria searchCriteria)
			throws HibernateException;

	/**
	 * Update Avatars information in Avatars table.
	 * 
	 * @param dto -
	 *            AvatarsDTO's object
	 * @return AvatarsDTO's updated object
	 * @throws HibernateException
	 */
	public AvatarsDTO update(AvatarsDTO dto) throws HibernateException;
}
