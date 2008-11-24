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

import com.g4g.dto.NetworkDTO;
import com.g4g.dto.SearchCriteria;

/**
 * Service interface for network table.
 * 
 * @author ankur
 * 
 */
public interface NetworkService {
	/**
	 * Adds networks details in network table.
	 * 
	 * @param dto -
	 *            object of NetworkDTO kind containing details for the network.
	 * @return NetworkDTO - returns NetworkDTO kind object which is added.
	 */
	public NetworkDTO add(NetworkDTO dto) throws HibernateException;

	/**
	 * Delete networks' entry from network table.
	 * 
	 * @param dto -
	 *            object of NetworkDTO kind containing details for the network.
	 * @return void
	 */
	public void delete(NetworkDTO dto) throws HibernateException;

	/**
	 * Returns NetworkDTO containing id of given NetworkDTO.
	 * 
	 * @param
	 * @return NetworkDTO object.
	 */
	public NetworkDTO get(NetworkDTO dto) throws HibernateException;

	/**
	 * Returns list containing all networks' comments from network table.
	 * 
	 * @param
	 * @return list containing NetworkDTO objects.
	 */
	public List<NetworkDTO> getList() throws HibernateException;

	/**
	 * Returns list containing networks' comments from network table, but
	 * according to given criteria.
	 * 
	 * @param searchCriteria
	 *            object with attributes set as per the require search.
	 * @return list containing NetworkDTO objects satisfies given criteria.
	 */
    public List<NetworkDTO> getList(SearchCriteria searchCriteria)
			throws HibernateException;

	/**
	 * Update networks' information in network table.
	 * 
	 * @param dto -
	 *            NetworkDTO's object
	 * @return NetworkDTO's updated object
	 */
	public NetworkDTO update(NetworkDTO dto) throws HibernateException;

}
