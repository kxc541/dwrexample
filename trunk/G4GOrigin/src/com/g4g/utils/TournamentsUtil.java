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

/**
 * The class TournamentsUtil contains utilities required for calculating/getting
 * tournament related values. The tournaments should have levels > 1. 1.
 * Calculates number of players based on tiers. 2. Calculates jackpot amount for
 * tournament.
 *
 * @author Punam version 1.0
 * @author Ankur version 2.0
 */
public class TournamentsUtil {

	private static final String UNNECESSATY_LOCAL_VARIABLE = "UnnecessaryLocalVariable"; //$NON-NLS-1$

	/**
	 * The method getNoOfPlayers will calculate number of players as per levels
	 * of the tournament. Only Number of players calculated can join the
	 * tournament not more than it are allowed.
	 *
	 * @param tiers
	 *            the tiers contains the levels of tournaments
	 *
	 * @return the no of players for the given tiers
	 */

	public static int getNoOfPlayers(int tiers) {
		int noOfPlayers = G4GConstants.TWO_NUMBER;
		for (int index = G4GConstants.ONE_NUMBER; index < tiers; index++) {
			noOfPlayers = noOfPlayers * G4GConstants.TWO_NUMBER;
		}
		return noOfPlayers;
	}
}
