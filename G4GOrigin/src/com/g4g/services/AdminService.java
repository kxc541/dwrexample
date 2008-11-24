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

import com.g4g.dto.AdminDTO;
import com.g4g.dto.SearchCriteria;

/**
 * Service interface for admin table.
 * 
 * @author Pratik
 * 
 */

public interface AdminService {
	/**
	 * Adds Admin details in Admin table.
	 * 
	 * @param dto -
	 *            object of AdminDTO kind containing details for the Admin.
	 * @return AdminDTO - returns AdminDTO kind object which is added.
	 * @throws HibernateException
	 */
	public AdminDTO add(AdminDTO dto) throws HibernateException;

	/**
	 * Delete admin's entry from Admin table.
	 * 
	 * @param dto -
	 *            object of AdminDTO kind containing details for the Admin.
	 * @throws HibernateException
	 */
	public void delete(AdminDTO dto) throws HibernateException;

	/**
	 * Returns list containing all Admin from Admin table.
	 * 
	 * @return list containing AdminDTO objects.
	 * @throws HibernateException
	 */
	public List<AdminDTO> getList() throws HibernateException;

	/**
	 * Returns list containing Admin from Admin table, but according to given
	 * criteria.
	 * 
	 * object with attributes set as per the require search.
	 * 
	 * @param searchCriteria
	 * @return list containing AdminDTO objects satisfies given criteria.
	 * @throws HibernateException
	 */
	public List<AdminDTO> getList(SearchCriteria searchCriteria)
			throws HibernateException;

	/**
	 * Update Admin information in Admin table.
	 * 
	 * @param dto -
	 *            AdminDTO's object
	 * @return AdminDTO's updated object
	 * @throws HibernateException
	 */
	public AdminDTO update(AdminDTO dto) throws HibernateException;
}
