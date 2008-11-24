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
import com.g4g.dto.TimeZoneDTO;

/**
 * Class contains method signature related to timezone.
 * 
 * @author Ankur
 */
public interface TimeZoneService {
	/**
	 * Adds record to timezone table.
	 * 
	 * @param dto
	 * @return TimeZoneDTO
	 * @throws HibernateException 
	 */
	public TimeZoneDTO add(TimeZoneDTO dto) throws HibernateException;

	/**
	 * Delete records from timezone table.
	 * 
	 * @param dto
	 * @throws HibernateException 
	 */
	public void delete(TimeZoneDTO dto) throws HibernateException;

	/**
	 * Returns timezone table of given game.
	 * 
	 * @param id
	 * @return TimeZoneDTO
	 * @throws HibernateException 
	 */
	public TimeZoneDTO getTimeZone(int id)throws HibernateException;

	/**
	 * Returns all the records from timezone table.
	 * 
	 * @return List<TimeZoneDTO>
	 * @throws HibernateException 
	 */
	public List<TimeZoneDTO> getList()throws HibernateException;

	/**
	 * Returns records from timezone table based on searchCriteria.
	 * 
	 * @param searchCriteria
	 * @return List<TimeZoneDTO>
	 * @throws HibernateException 
	 */
	public List<TimeZoneDTO> getList(SearchCriteria searchCriteria)throws HibernateException;

	/**
	 * Returns updated TimeZoneDTO.
	 * 
	 * @param dto
	 * @return TimeZoneDTO
	 * @throws HibernateException 
	 */
	public TimeZoneDTO update(TimeZoneDTO dto) throws HibernateException;
}
