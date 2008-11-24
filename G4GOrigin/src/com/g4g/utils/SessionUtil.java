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

import javax.servlet.http.HttpServletRequest;

import com.g4g.delegator.G4GFinancialDelegator;
import com.impessa.worldgaming.client.InternalException_Exception;
import com.impessa.worldgaming.client.InvalidSessionException_Exception;
import com.impessa.worldgaming.client.UserNotFoundException_Exception;

/**
 * The SessionUtil class maintains the data stored in session. Session is
 * duration between user logs-in a to user logs-out. Seprate session is
 * maintained for each user. SessionUtil provides the methods for getting the
 * siteId. And also updates the user balance.
 * 
 * @author Ankur
 */
public class SessionUtil {

	/**
	 * The getSiteId method gives the siteId of the user. Which specifies from
	 * which site the user came, his site will be stored in the session and user
	 * can get it using this method.
	 * 
	 * @param request
	 *            the request object of the current session to get the session
	 *            id from it.
	 * 
	 * @return default siteId if there is no siteid present in session
	 * 
	 * @author Ankur
	 */
	public static String getSiteId(HttpServletRequest request) {
		String siteId = (String) request.getSession().getAttribute(G4GConstants.SITE_ID_ATTRIBUTE);
		if (siteId == null) {
			siteId = G4GConstants.WORLD_GAMING_SITE_ID;
		}
		return siteId;
	}

	/**
	 * The method updateUserBalance Updates user balance in session's UserDTO
	 * object. If the user updates world gaming balance this method updates the
	 * balance in user's DTO. It can be updated in header.
	 * 
	 * @param request
	 *            the request object of current session is passed so bank
	 *            balance and winning balance of the user can be retrieved.
	 * 
	 * @author Ankur
	 * @throws UserNotFoundException_Exception
	 *             If the session expires
	 * @throws InvalidSessionException_Exception -
	 *             If the session closes
	 * @throws InternalException_Exception
	 */
	
	public static void updateUserbalance(HttpServletRequest request)
			throws InternalException_Exception,
			InvalidSessionException_Exception, UserNotFoundException_Exception {
		Double bankBalance;
		bankBalance = DataUtil.getImpassaMoney(G4GFinancialDelegator
				.getBalanceByUsername(
						DataUtil.getUserDTO(request).getUser().getEmail())
				.getBankBalance().getAmount());
		Double winningBalance = DataUtil.getImpassaMoney(G4GFinancialDelegator
				.getBalanceByUsername(
						DataUtil.getUserDTO(request).getUser().getEmail())
				.getWinningsBalance().getAmount());
		DataUtil.getUserDTO(request).setBalance(bankBalance + winningBalance);
	}
}
