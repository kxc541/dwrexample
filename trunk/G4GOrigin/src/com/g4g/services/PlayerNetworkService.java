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

import com.g4g.dto.PlayernetworkDTO;
import com.g4g.dto.SearchCriteria;
/**
 * Interface of service functions for for Playernetwork table.
 * 
 * @author ankur
 *
 */
public interface PlayerNetworkService {
	/**
	 * Adds playernetwork details in playernetwork table.
	 * 
	 * @param dto -
	 *            object of PlayernetworkDTO kind containing details for the playernetwork.
	 * @return PlayernetworkDTO - returns link PlayernetworkDTO kind object which is added.
	 * @throws HibernateException 
	 */
	public PlayernetworkDTO add(PlayernetworkDTO dto) throws HibernateException;
	/**
	 * Delete playernetwork's entry from playernetwork table.
	 * 
	 * @param dto -
	 *            object of PlayernetworkDTO kind containing details for the network.
	
	 * @throws HibernateException 
	 */	
	public void delete(PlayernetworkDTO dto) throws HibernateException;
	
	/**
	 * Returns list containing all playerNetworks from playerNetwork table.
	 * 
	 * @return list containing PlayernetworkDTO objects.
	 * @throws HibernateException 
	 */
	public List<PlayernetworkDTO> getList()throws HibernateException;
	/**
	 * Returns list containing playerNetworks from playernetwork table, but according to given
	 * criteria.
	 * @param searchCriteria 
	 * 
	 * object with attributes set as per the require search.
	 * @return list containing PlayernetworkDTO objects satisfies given criteria.
	 * @throws HibernateException 
	 */
	
	public List<PlayernetworkDTO> getList(SearchCriteria searchCriteria)throws HibernateException;
	/**
	 * Update Player's network information in playernetwork table.
	 * 
	 * @param dto - PlayernetworkDTO's object
	 * @return PlayernetworkDTO's updated object
	 * @throws HibernateException 
	 */
	public PlayernetworkDTO update(PlayernetworkDTO dto) throws HibernateException;
}