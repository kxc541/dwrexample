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
import com.g4g.dto.SubNationalCodeDTO;
/**
 * 
 * Class contains method signature related to subnationalcode.
 * 
 * @author ankur
 *
 */
public interface SubNationalCodeService {
	/**
	 * Adds record to subnationalcode table.
	 * @param dto
	 * @return SubNationalCodeDTO
	 * @throws HibernateException 
	 */
	public SubNationalCodeDTO add(SubNationalCodeDTO dto) throws HibernateException;
	/**
	 * Delete records from subnationalcode table.
	 * @param dto
	 * @throws HibernateException 
	 */
	public void delete(SubNationalCodeDTO dto) throws HibernateException;
	/**
	 * Returns all the records from subnationalcode table.
	 * @return List<SubNationalCodeDTO>
	 * @throws HibernateException 
	 */
	public List<SubNationalCodeDTO> getList()throws HibernateException;
	/**
	 * Returns records from subnationalcode table based on searchCriteria.
	 * @param searchCriteria
	 * @return List<SubNationalCodeDTO>
	 * @throws HibernateException 
	 */
	public List<SubNationalCodeDTO> getList(SearchCriteria searchCriteria)throws HibernateException;
	/**
	 * Gets the name of the nation from its code.
	 * @param code
	 * @return String
	 * @throws HibernateException 
	 */
	public String getSubNationName(int code)throws HibernateException;
	/**
	 * Returns updated subnationalcode.
	 * @param dto
	 * @return SubNationalCodeDTO
	 * @throws HibernateException 
	 */
	public SubNationalCodeDTO update(SubNationalCodeDTO dto) throws HibernateException;

	public SubNationalCodeDTO get(int id) throws HibernateException;

}
