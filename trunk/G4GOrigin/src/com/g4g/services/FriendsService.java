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

import com.g4g.dto.FriendsDTO;
import com.g4g.dto.PlayerDTO;
import com.g4g.dto.SearchListCriteria;
/**
 * Service interface for friends table.
 * @author Jigar Mistry
 *
 */
public interface FriendsService {
	/**
	 * Adds friends details in friends table.
	 * 
	 * @param dto -
	 *            object of FriendsDTO kind containing details for the friends.
	 * @return FriendsDTO - returns FriendsDTO kind object which is added.
	 */
	public FriendsDTO add(FriendsDTO dto) throws HibernateException;
	/**
	 * Delete playercomment's entry from friends table.
	 * 
	 * @param dto -
	 *            object of FriendsDTO kind containing details for the friends.
	 * @return void
	 */
	public void delete(FriendsDTO dto) throws HibernateException;
	/**
	 * Returns list containing all friends' from friends table.
	 * 
	 * @param
	 * @return list containing FriendsDTO objects.
	 */
	public List<FriendsDTO> getList()throws HibernateException;
	/**
	 * Returns list containing friends' from friends table, but according to given
	 * criteria.
	 * 
	 * @param SearchListCriteria
	 *            object with attributes set as per the require search.
	 * @return list containing FriendsDTO objects satisfies given criteria.
	 */
	public List<FriendsDTO> getList(SearchListCriteria searchListCriteria)throws HibernateException;
	/**
	 * Update friends' information in friends table.
	 * 
	 * @param dto - FriendsDTO's object
	 * @return FriendsDTO's updated object
	 */
	public FriendsDTO update(FriendsDTO dto) throws HibernateException;
	/**
	 * Returns list of friends for given players.
	 * @param playerId
	 * @return List<PlayerDTO>
	 */
	public List<PlayerDTO> getFriends(int playerId)throws HibernateException;
}
