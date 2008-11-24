/**********************************************************
 * HibernateSessionFactory.java 
 *
 * Created : 16 apr. 08 by author
 * Last modified $ Date: $ by $ Author:  $
 * Revision: $ Revision:  $
 * Version : $ ID : 1$
 * Copyright (c) 2008 - 2008 Askdigi, All rights reserved.
 **********************************************************/
package com.g4g.utils;

import java.util.List;

import org.hibernate.Session;

import com.g4g.dto.FriendsDTO;

/**
 * The Class FriendsUtil contains utilities for friend table.It checks the given
 * two ids are friends of each other.
 * 
 * @author Punam
 */
public class FriendsUtil {

	private static final String UNCHECKED = "unchecked"; //$NON-NLS-1$

	/**
	 * The method isFriend checks whether the given playerid for the player is
	 * friend of given friendId and vice versa. If player is friend in any of
	 * two ways ,playerid and friendid has mapping for them recursively then it
	 * returns true else returns false.
	 * 
	 * @param playerid
	 *            The playerId who is currently logged in.
	 * @param friendsId
	 *            the friendsid for whom to check the mapping in friend table.
	 * 
	 * @return true, if is both ids have mapping in friends table else returns
	 *         false.
	 */
	@SuppressWarnings(UNCHECKED)
	public static boolean isFriend(int playerid, int friendsId) {
		boolean isFriend = false;
		Session session = HibernateUtil.getSession();
		List<FriendsDTO> friendsList = session.createSQLQuery(
				new StringBuffer(SQLConstants.SELECT).append(
						SQLConstants.ASTERISTIC).append(SQLConstants.FROM)
						.append(SQLConstants.FRIENDS_SQL)
						.append(SQLConstants.WHERE).append(
								G4GConstants.PLAYER_ID1)
						.append(SQLConstants.IN)
						.append(SQLConstants.OPEN_PAREN).append(playerid)
						.append(SQLConstants.COMMA).append(friendsId).append(
								SQLConstants.CLOSE_PAREN).append(
								SQLConstants.AND).append(
								G4GConstants.PLAYER_ID2)
						.append(SQLConstants.IN)
						.append(SQLConstants.OPEN_PAREN).append(playerid)
						.append(SQLConstants.COMMA).append(friendsId).append(
								SQLConstants.CLOSE_PAREN).append(
								SQLConstants.AND).append(
								G4GConstants.PLAYER1_ACCEPTED).append(
								SQLConstants.IS).append(SQLConstants.NOT)
						.append(SQLConstants.NULL).append(SQLConstants.AND)
						.append(G4GConstants.PLAYER2_ACCEPTED).append(
								SQLConstants.IS).append(SQLConstants.NOT)
						.append(SQLConstants.NULL).toString()).addEntity(
				FriendsDTO.class).list();
		HibernateUtil.closeSession();
		isFriend = friendsList.size() > G4GConstants.ZERO;
		return isFriend;
	}

}
