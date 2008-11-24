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
 * Service interface G4gUserFinancial details. 
 * @author Jigar Mistry
 */

import java.util.List;

import com.g4g.dto.SearchCriteria;
import com.impessa.worldgaming.client.User;

public interface G4GUserFinancialService {
	/**
	 * Adds playercomments details in User object.
	 * 
	 * @param dto -
	 *            object of User kind containing details for the G4gUserFinancial.
	 * @return User - returns User kind object which is added.
	 */
	public User add(User dto);
	/**
	 * Delete playercomment's entry from User object.
	 * 
	 * @param dto -
	 *            object of User kind containing details for the G4gUserFinancial.
	 */
	public void delete(User dto);
	/**
	 * Returns list containing all Users' from User object.
	 * 
	 * @param
	 * @return List<User> containing User objects.
	 */
	public List<User> getList();
	/**
	 * Returns list containing Users' from User object, but according to given
	 * criteria.
	 * 
	 * @param searchCriteria
	 *            object with attributes set as per the require search.
	 * @return list containing User objects satisfies given criteria.
	 */
	public List<User> getList(SearchCriteria searchCriteria);
	/**
	 * Update Users' information in User object.
	 * 
	 * @param dto - User's object
	 * @return User's updated object
	 */
	public User update(User dto);

}
