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

import com.g4g.dto.NationalCodeDTO;
import com.g4g.dto.SearchCriteria;

/**
 * Service interface for nationalcode table.
 * 
 * @author ankur
 * 
 */

public interface NationalCodeService {
	/**
	 * Adds nationalcode details in nationalcode table.
	 * 
	 * @param dto -
	 *            object of NationalCodeDTO kind containing details for the
	 *            nationalCode.
	 * @return NationalCodeDTO - returns NationalCodeDTO kind object which is
	 *         added.
	 */
	public NationalCodeDTO add(NationalCodeDTO dto) throws HibernateException;

	/**
	 * Delete nationalcodes' entry from nationalcode table.
	 * 
	 * @param dto -
	 *            object of NationalCodeDTO kind containing details for the
	 *            nationalcodes.
	 * @return void
	 */
	public void delete(NationalCodeDTO dto) throws HibernateException;

	/**
	 * Returns list containing all nationalcodes' from nationalcode table.
	 * 
	 * @param
	 * @return list containing NationalCodeDTO objects.
	 */
	public List<NationalCodeDTO> getList() throws HibernateException;

	/**
	 * Returns list containing nationalcodes from nationalcode table, but
	 * according to given criteria.
	 * 
	 * @param searchCriteria
	 *            object with attributes set as per the require search.
	 * @return list containing NationalCodeDTO objects satisfies given criteria.
	 */
    public List<NationalCodeDTO> getList(SearchCriteria searchCriteria)
			throws HibernateException;

	/**
	 * Update nationalcodes information in nationalcode table.
	 * 
	 * @param dto -
	 *            NationalCodeDTO's object.
	 * @return NationalCodeDTO's updated object.
	 */
	public NationalCodeDTO update(NationalCodeDTO dto)
			throws HibernateException;

	/**
	 * Method get the nationname based on nationcode(code) given.
	 * 
	 * @param code -
	 *            pass nationcode to get nation name.
	 * @return String - returns nation name.
	 */
	public String getNationName(int code);

}
