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
 * Service interface for pictures table.
 * @author Jigar Mistry
 */

import java.util.List;

import org.hibernate.HibernateException;

import com.g4g.dto.PicturesDTO;
import com.g4g.dto.SearchCriteria;

/**
 * @author jigar mistry
 *
 */
public interface PicturesService {
	/**
	 * Adds picture details in pictures table.
	 * 
	 * @param dto -
	 *            object of PicturesDTO kind containing details for the playercomments.
	 * @return PicturesDTO - returns PicturesDTO kind object which is added.
	 * @throws HibernateException 
	 */
	public PicturesDTO add(PicturesDTO dto) throws HibernateException;
	/**
	 * Delete picture's entry from pictures table.
	 * 
	 * @param dto -
	 *            object of PicturesDTO kind containing details for the picture.
	 * @throws HibernateException 
	 */	
	public void delete(PicturesDTO dto) throws HibernateException;
	/**
	 * Returns list containing all pictures from pictures table.
	 * @return list containing PicturesDTO objects.
	 * @throws HibernateException 
	 */
	public List<PicturesDTO> getList()throws HibernateException;
	/**
	 * Returns list containing pictures from pictures table, but according to given
	 * criteria.
	 * @param searchCriteria 
	 * object with attributes set as per the require search.
	 * @return list containing PicturesDTO objects satisfies given criteria.
	 * @throws HibernateException 
	 */
	public List<PicturesDTO> getList(SearchCriteria searchCriteria)throws HibernateException;
	/**
	 * Update pictures' information in pictures table.
	 * 
	 * @param dto - PicturesDTO's object
	 * @return PicturesDTO's updated object
	 * @throws HibernateException 
	 */
	public PicturesDTO update(PicturesDTO dto)throws HibernateException;
	
}
