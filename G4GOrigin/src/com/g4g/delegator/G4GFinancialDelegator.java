/**********************************************************
 * G4GFinancialDelegator.java : 
 *
 * Created by Jigar Mistry
 * Last modified Date: 18 Apr .08 by Punam
 * Revision: 0.1
 * Version : 0.0.8
 * Copyright (c) 2008 - 2008 Askdigi, All rights reserved.
 **********************************************************/
package com.g4g.delegator;

import java.sql.SQLException;
import java.util.List;

import org.apache.log4j.Level;

import com.g4g.utils.AuditUtil;
import com.g4g.utils.DataUtil;
import com.g4g.utils.G4GConstants;
import com.g4g.ws.G4GFinancial;
import com.impessa.worldgaming.client.AccessException_Exception;
import com.impessa.worldgaming.client.AuthenticationException_Exception;
import com.impessa.worldgaming.client.Balance;
import com.impessa.worldgaming.client.BetDetails;
import com.impessa.worldgaming.client.DepositInput;
import com.impessa.worldgaming.client.DepositOutput;
import com.impessa.worldgaming.client.InternalException_Exception;
import com.impessa.worldgaming.client.InvalidBetException_Exception;
import com.impessa.worldgaming.client.InvalidPasswordException_Exception;
import com.impessa.worldgaming.client.InvalidPaymentException_Exception;
import com.impessa.worldgaming.client.InvalidSessionException_Exception;
import com.impessa.worldgaming.client.LimitExceededException_Exception;
import com.impessa.worldgaming.client.Money;
import com.impessa.worldgaming.client.MoneyException_Exception;
import com.impessa.worldgaming.client.PaymentAccount;
import com.impessa.worldgaming.client.PlayerStatusException_Exception;
import com.impessa.worldgaming.client.PlayerTransactionType;
import com.impessa.worldgaming.client.SiteNotFoundException_Exception;
import com.impessa.worldgaming.client.Transaction;
import com.impessa.worldgaming.client.User;
import com.impessa.worldgaming.client.UserNotFoundException_Exception;
import com.impessa.worldgaming.client.UserValidationException_Exception;
import com.impessa.worldgaming.client.WithdrawalMethod;

/**
 * G4GFinancialDelegator class call methods provided by impessa.Its
 * implementation is defined in G4GFinancial class of WS package.
 * 
 * @author Jigar Mistry
 */

public class G4GFinancialDelegator {

	/** The g4gfinancial. */
	private static G4GFinancial g4gFinancial = new G4GFinancial();

	/**
	 * Creates object of G4GFinancial class it throws exception if webservice is
	 * not available.
	 */

	/**
	 * It test jdbc connection or our financial stub and if found closed or null
	 * establish a newer one.
	 * 
	 * @author Ankur
	 */
	public static void testAndReconnect() {

		try {
			if (g4gFinancial.testConnection()) {
				g4gFinancial = new G4GFinancial();
			}
		} catch (SQLException exception) {
			AuditUtil.getInstance().writeLog(
					AuditUtil.FILE_TYPE_G4G,
					G4GFinancialDelegator.class.getName() + G4GConstants.ERROR_WITH_COLON
							+ exception.getMessage());

		} catch (Exception exception) {
			AuditUtil.getInstance().writeLog(
					AuditUtil.FILE_TYPE_G4G,
					G4GFinancialDelegator.class.getName() + G4GConstants.ERROR_WITH_COLON
							+ exception.getMessage());
		}
	}

	/**
	 * @param username
	 * @return String
	 * @throws UserNotFoundException_Exception
	 * @throws InternalException_Exception
	 * @see com.g4g.ws.G4GFinancial#resetPassword(String)
	 */
	public static String resetPassword(String username)
			throws InternalException_Exception, UserNotFoundException_Exception {

		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,
				new StringBuffer(G4GFinancialDelegator.class.getName()).append(
						G4GConstants.COLON_WITH_SPACES).append(G4GConstants.CALLINGMETHOD)
						.append(DataUtil.getCallingMethod()).append(
								G4GConstants.CURRENTMETHOD).append(
								DataUtil.getCurrentMethod()).append(
								G4GConstants.DASHES).append(
								G4GConstants.STARTED).toString(), Level.INFO);

		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,
				new StringBuffer(G4GFinancialDelegator.class.getName()).append(
						G4GConstants.COLON_WITH_SPACES).append(G4GConstants.CALLINGMETHOD)
						.append(DataUtil.getCallingMethod()).append(
								G4GConstants.CURRENTMETHOD).append(
								DataUtil.getCurrentMethod()).append(
								G4GConstants.DASHES).append(G4GConstants.ENDED)
						.toString(), Level.INFO);

		return g4gFinancial.resetPassword(username);
	}

	/**
	 * @param dto
	 * @throws InternalException_Exception
	 * @throws SiteNotFoundException_Exception
	 * @throws UserValidationException_Exception
	 * @see com.g4g.ws.G4GFinancial#registerUser(User)
	 */
	public static void registerUser(User dto)
			throws InternalException_Exception,
			SiteNotFoundException_Exception, UserValidationException_Exception {

		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,
				new StringBuffer(G4GFinancialDelegator.class.getName()).append(
						G4GConstants.COLON_WITH_SPACES).append(G4GConstants.CALLINGMETHOD)
						.append(DataUtil.getCallingMethod()).append(
								G4GConstants.CURRENTMETHOD).append(
								DataUtil.getCurrentMethod()).append(
								G4GConstants.DASHES).append(G4GConstants.REGISTERINGUSER)
								.append(G4GConstants.STARTED).toString(), Level.INFO);

		g4gFinancial.registerUser(dto);

	}

	/**
	 * @param sessionId
	 * @param paymentAccount
	 * @throws InternalException_Exception
	 * @throws InvalidPaymentException_Exception
	 * @throws InvalidSessionException_Exception
	 * @see com.g4g.ws.G4GFinancial#registerPaymentAccount(String,
	 *      PaymentAccount)
	 */
	public static void registerPaymentAccount(String sessionId,
			PaymentAccount paymentAccount) throws InternalException_Exception,
			InvalidPaymentException_Exception,
			InvalidSessionException_Exception {

		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,
				new StringBuffer(G4GFinancialDelegator.class.getName()).append(
						G4GConstants.COLON_WITH_SPACES).append(G4GConstants.CALLINGMETHOD)
						.append(DataUtil.getCallingMethod()).append(
								G4GConstants.CURRENTMETHOD).append(
								DataUtil.getCurrentMethod()).append(
								G4GConstants.DASHES).append(
								G4GConstants.STARTED).toString(), Level.INFO);

		g4gFinancial.registerPaymentAccount(sessionId, paymentAccount);

		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,
				new StringBuffer(G4GFinancialDelegator.class.getName()).append(
						G4GConstants.COLON_WITH_SPACES).append(G4GConstants.CALLINGMETHOD)
						.append(DataUtil.getCallingMethod()).append(
								G4GConstants.CURRENTMETHOD).append(
								DataUtil.getCurrentMethod()).append(
								G4GConstants.DASHES)
								.append(G4GConstants.PARAMETERS).append(G4GConstants.SESSIONSITEID)
								.append(G4GConstants.ENDED)
						.toString(), Level.INFO);
	}

	/**
	 * @param sessionId
	 * @param paymentAccount
	 * @throws AccessException_Exception
	 * @throws InternalException_Exception
	 * @throws InvalidPaymentException_Exception
	 * @throws InvalidSessionException_Exception
	 * @see com.g4g.ws.G4GFinancial#updatePaymentAccount(String, PaymentAccount)
	 */
	public static void updatePaymentAccount(String sessionId,
			PaymentAccount paymentAccount) throws AccessException_Exception,
			InternalException_Exception, InvalidPaymentException_Exception,
			InvalidSessionException_Exception {

		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,
				new StringBuffer(G4GFinancialDelegator.class.getName()).append(
						G4GConstants.COLON_WITH_SPACES).append(G4GConstants.CALLINGMETHOD)
						.append(DataUtil.getCallingMethod()).append(
								G4GConstants.CURRENTMETHOD).append(
								DataUtil.getCurrentMethod()).append(
								G4GConstants.DASHES).append(
								G4GConstants.STARTED).toString(), Level.INFO);

		g4gFinancial.updatePaymentAccount(sessionId, paymentAccount);

		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,
				new StringBuffer(G4GFinancialDelegator.class.getName()).append(
						G4GConstants.COLON_WITH_SPACES).append(G4GConstants.CALLINGMETHOD)
						.append(DataUtil.getCallingMethod()).append(
								G4GConstants.CURRENTMETHOD).append(
								DataUtil.getCurrentMethod()).append(
								G4GConstants.DASHES)
							.append(G4GConstants.UPDATE_PAYMENT_ACCOUNT)
								.append(G4GConstants.ENDED)
						.toString(), Level.INFO);
	}

	/**
	 * @param sessionId
	 * @param accountId
	 * @throws AccessException_Exception
	 * @throws InternalException_Exception
	 * @throws InvalidSessionException_Exception
	 * @see com.g4g.ws.G4GFinancial#deletePaymentAccount(String, long)
	 */
	public static void deletePaymentAccount(String sessionId, long accountId)
			throws AccessException_Exception, InternalException_Exception,
			InvalidSessionException_Exception {

		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,
				new StringBuffer(G4GFinancialDelegator.class.getName()).append(
						G4GConstants.COLON_WITH_SPACES).append(G4GConstants.CALLINGMETHOD)
						.append(DataUtil.getCallingMethod()).append(
								G4GConstants.CURRENTMETHOD).append(
								DataUtil.getCurrentMethod()).append(
								G4GConstants.DASHES).append(
								G4GConstants.STARTED).toString(), Level.INFO);

		g4gFinancial.deletePaymentAccount(sessionId, accountId);

		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,
				new StringBuffer(G4GFinancialDelegator.class.getName()).append(
						G4GConstants.COLON_WITH_SPACES).append(G4GConstants.CALLINGMETHOD)
						.append(DataUtil.getCallingMethod()).append(
								G4GConstants.CURRENTMETHOD).append(
								DataUtil.getCurrentMethod()).append(
								G4GConstants.DASHES).append(G4GConstants.ENDED)
						.toString(), Level.INFO);
	}

	/**
	 * @param sessionId
	 * @param oldPassword
	 * @param newPassword
	 * @throws InternalException_Exception
	 * @throws InvalidPasswordException_Exception
	 * @throws InvalidSessionException_Exception
	 * @throws UserValidationException_Exception
	 * @see com.g4g.ws.G4GFinancial#changePassword(String, String, String)
	 */
	public static void changePassword(String sessionId, String oldPassword,
			String newPassword) throws InternalException_Exception,
			InvalidPasswordException_Exception,
			InvalidSessionException_Exception,
			UserValidationException_Exception {

		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,
				new StringBuffer(G4GFinancialDelegator.class.getName()).append(
						G4GConstants.COLON_WITH_SPACES).append(G4GConstants.CALLINGMETHOD)
						.append(DataUtil.getCallingMethod()).append(
								G4GConstants.CURRENTMETHOD).append(
								DataUtil.getCurrentMethod()).append(
								G4GConstants.DASHES).append(
								G4GConstants.STARTED).toString(), Level.INFO);

		g4gFinancial.changePassword(sessionId, oldPassword, newPassword);

		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,
				new StringBuffer(G4GFinancialDelegator.class.getName()).append(
						G4GConstants.COLON_WITH_SPACES).append(G4GConstants.CALLINGMETHOD)
						.append(DataUtil.getCallingMethod()).append(
								G4GConstants.CURRENTMETHOD).append(
								DataUtil.getCurrentMethod()).append(
								G4GConstants.DASHES)
								.append(G4GConstants.PARAMETERS).append(G4GConstants.SESSIONID)
								.append(G4GConstants.NEW_PASSWORD)
								.append(G4GConstants.ENDED)
						.toString(), Level.INFO);
	}

	/**
	 * @return String
	 * @throws InternalException_Exception
	 * @see com.g4g.ws.G4GFinancial#createBet()
	 */
	public static String createBet() throws InternalException_Exception {

		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,
				new StringBuffer(G4GFinancialDelegator.class.getName()).append(
						G4GConstants.COLON_WITH_SPACES).append(G4GConstants.CALLINGMETHOD)
						.append(DataUtil.getCallingMethod()).append(
								G4GConstants.CURRENTMETHOD).append(
								DataUtil.getCurrentMethod()).append(
								G4GConstants.DASHES).append(
								G4GConstants.STARTED).toString(), Level.INFO);

		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,
				new StringBuffer(G4GFinancialDelegator.class.getName()).append(
						G4GConstants.COLON_WITH_SPACES).append(G4GConstants.CALLINGMETHOD)
						.append(DataUtil.getCallingMethod()).append(
								G4GConstants.CURRENTMETHOD).append(
								DataUtil.getCurrentMethod()).append(
								G4GConstants.DASHES).append(G4GConstants.ENDED)
						.toString(), Level.INFO);

		return g4gFinancial.createBet();
	}

	/**
	 * @param sessionId
	 * @param betId
	 * @param money
	 * @throws InternalException_Exception
	 * @throws InvalidSessionException_Exception
	 * @throws InvalidBetException_Exception
	 * @throws MoneyException_Exception
	 * @see com.g4g.ws.G4GFinancial#bet(String, String, Money)
	 */
	public static void bet(String sessionId, String betId, Money money)
			throws InternalException_Exception,
			InvalidSessionException_Exception, InvalidBetException_Exception,
			MoneyException_Exception {

		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,
				new StringBuffer(G4GFinancialDelegator.class.getName()).append(
						G4GConstants.COLON_WITH_SPACES).append(G4GConstants.CALLINGMETHOD)
						.append(DataUtil.getCallingMethod()).append(
								G4GConstants.CURRENTMETHOD).append(
								DataUtil.getCurrentMethod()).append(
								G4GConstants.DASHES).append(
								G4GConstants.STARTED).toString(), Level.INFO);

		g4gFinancial.bet(sessionId, betId, money);
		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,
				new StringBuffer(G4GFinancialDelegator.class.getName()).append(
						G4GConstants.COLON_WITH_SPACES).append(G4GConstants.CALLINGMETHOD)
						.append(DataUtil.getCallingMethod()).append(
								G4GConstants.CURRENTMETHOD).append(
								DataUtil.getCurrentMethod()).append(
								G4GConstants.DASHES)
								.append(G4GConstants.PARAMETERS).append(G4GConstants.SESSIONID)
								.append(G4GConstants.ENDED)
						.toString(), Level.INFO);

	}

	/**
	 * @param betId
	 * @param siteId
	 * @param money
	 * @throws InternalException_Exception
	 * @throws InvalidBetException_Exception
	 * @throws MoneyException_Exception
	 * @throws SiteNotFoundException_Exception
	 * @see com.g4g.ws.G4GFinancial#moveToHouseAccount(String, String, Money)
	 */
	public static void moveToHouseAccount(String betId, String siteId,
			Money money) throws InternalException_Exception,
			InvalidBetException_Exception, MoneyException_Exception,
			SiteNotFoundException_Exception {
		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,
				new StringBuffer(G4GFinancialDelegator.class.getName()).append(
						G4GConstants.COLON_WITH_SPACES).append(G4GConstants.CALLINGMETHOD)
						.append(DataUtil.getCallingMethod()).append(
								G4GConstants.CURRENTMETHOD).append(
								DataUtil.getCurrentMethod()).append(
								G4GConstants.DASHES).append(
								G4GConstants.STARTED).toString(), Level.INFO);

		g4gFinancial.moveToHouseAccount(betId, siteId, money);

		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,
				new StringBuffer(G4GFinancialDelegator.class.getName()).append(
						G4GConstants.COLON_WITH_SPACES).append(G4GConstants.CALLINGMETHOD)
						.append(DataUtil.getCallingMethod()).append(
								G4GConstants.CURRENTMETHOD).append(
								DataUtil.getCurrentMethod()).append(
								G4GConstants.DASHES)
								.append(G4GConstants.PARAMETERS)
								.append(G4GConstants.SITEID_SMALL)
								.append(G4GConstants.ENDED)
						.toString(), Level.INFO);
	}

	/**
	 * @param betId
	 * @param username
	 * @param money
	 * @param transactionType
	 * @throws InternalException_Exception
	 * @throws InvalidBetException_Exception
	 * @throws InvalidSessionException_Exception
	 * @throws MoneyException_Exception
	 * @throws UserNotFoundException_Exception
	 * @see com.g4g.ws.G4GFinancial#moveToPlayerAccount(String, String, Money,
	 *      PlayerTransactionType)
	 */
	public static void moveToPlayerAccount(String betId, String username,
			Money money, PlayerTransactionType transactionType)
			throws InternalException_Exception, InvalidBetException_Exception,
			InvalidSessionException_Exception, MoneyException_Exception,
			UserNotFoundException_Exception {
		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,
				new StringBuffer(G4GFinancialDelegator.class.getName()).append(
						G4GConstants.COLON_WITH_SPACES).append(G4GConstants.CALLINGMETHOD)
						.append(DataUtil.getCallingMethod()).append(
								G4GConstants.CURRENTMETHOD).append(
								DataUtil.getCurrentMethod()).append(
								G4GConstants.DASHES).append(
								G4GConstants.STARTED).toString(), Level.INFO);

		g4gFinancial.moveToPlayerAccount(betId, username, money,
				transactionType);

		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,
				new StringBuffer(G4GFinancialDelegator.class.getName()).append(
						G4GConstants.COLON_WITH_SPACES).append(G4GConstants.CALLINGMETHOD)
						.append(DataUtil.getCallingMethod()).append(
								G4GConstants.CURRENTMETHOD).append(
								DataUtil.getCurrentMethod()).append(
								G4GConstants.DASHES)
								.append(G4GConstants.PARAMETERS)
								.append(G4GConstants.ENDED)
						.toString(), Level.INFO);
	}

	/**
	 * @param betId
	 * @return BetDetails
	 * @throws InternalException_Exception
	 * @throws InvalidBetException_Exception
	 * @see com.g4g.ws.G4GFinancial#getBetDetails(String)
	 */
	public static BetDetails getBetDetails(String betId)
			throws InternalException_Exception, InvalidBetException_Exception {
		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,
				new StringBuffer(G4GFinancialDelegator.class.getName()).append(
						G4GConstants.COLON_WITH_SPACES).append(G4GConstants.CALLINGMETHOD)
						.append(DataUtil.getCallingMethod()).append(
								G4GConstants.CURRENTMETHOD).append(
								DataUtil.getCurrentMethod()).append(
								G4GConstants.DASHES).append(
								G4GConstants.STARTED).toString(), Level.INFO);

		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,
				new StringBuffer(G4GFinancialDelegator.class.getName()).append(
						G4GConstants.COLON_WITH_SPACES).append(G4GConstants.CALLINGMETHOD)
						.append(DataUtil.getCallingMethod()).append(
								G4GConstants.CURRENTMETHOD).append(
								DataUtil.getCurrentMethod()).append(
								G4GConstants.DASHES)
								.append(G4GConstants.PARAMETERS)
								.append(G4GConstants.ENDED)
						.toString(), Level.INFO);

		return g4gFinancial.getBetDetails(betId);
	}

	/**
	 * @param userId
	 * @see com.g4g.ws.G4GFinancial#deleteFinancialUser(String)
	 */
	public static void deleteFinancialUser(String userId) {
		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,
				new StringBuffer(G4GFinancialDelegator.class.getName()).append(
						G4GConstants.COLON_WITH_SPACES).append(G4GConstants.CALLINGMETHOD)
						.append(DataUtil.getCallingMethod()).append(
								G4GConstants.CURRENTMETHOD).append(
								DataUtil.getCurrentMethod()).append(
								G4GConstants.DASHES)
								.append(G4GConstants.PARAMETERS).append(G4GConstants.USER_ID)
								.append(G4GConstants.STARTED).toString(), Level.INFO);

		g4gFinancial.deleteFinancialUser(userId);

		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,
				new StringBuffer(G4GFinancialDelegator.class.getName()).append(
						G4GConstants.COLON_WITH_SPACES).append(G4GConstants.CALLINGMETHOD)
						.append(DataUtil.getCallingMethod()).append(
								G4GConstants.CURRENTMETHOD).append(
								DataUtil.getCurrentMethod()).append(
								G4GConstants.DASHES).append(G4GConstants.ENDED)
						.toString(), Level.INFO);
	}

	/**
	 * @param sessionId
	 * @param depositInput
	 * @return DepositOutput
	 * @throws InternalException_Exception
	 * @throws InvalidPaymentException_Exception
	 * @throws InvalidSessionException_Exception
	 * @throws LimitExceededException_Exception
	 * @see com.g4g.ws.G4GFinancial#deposit(String, DepositInput)
	 */
	public static DepositOutput deposit(String sessionId,
			DepositInput depositInput) throws InternalException_Exception,
			InvalidPaymentException_Exception,
			InvalidSessionException_Exception, LimitExceededException_Exception {
		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,
				new StringBuffer(G4GFinancialDelegator.class.getName()).append(
						G4GConstants.COLON_WITH_SPACES).append(G4GConstants.CALLINGMETHOD)
						.append(DataUtil.getCallingMethod()).append(
								G4GConstants.CURRENTMETHOD).append(
								DataUtil.getCurrentMethod()).append(
								G4GConstants.DASHES).append(
								G4GConstants.STARTED).toString(), Level.INFO);

		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,
				new StringBuffer(G4GFinancialDelegator.class.getName()).append(
						G4GConstants.COLON_WITH_SPACES).append(G4GConstants.CALLINGMETHOD)
						.append(DataUtil.getCallingMethod()).append(
								G4GConstants.CURRENTMETHOD).append(
								DataUtil.getCurrentMethod()).append(
								G4GConstants.DASHES)
								.append(G4GConstants.PARAMETERS).append(G4GConstants.SESSIONID)
								.append(G4GConstants.ENDED)
						.toString(), Level.INFO);

		return g4gFinancial.deposit(sessionId, depositInput);
	}

	/**
	 * @param sessionId
	 * @param amount
	 * @param withdrawalMethod
	 * @return withdrawal
	 * @throws InternalException_Exception
	 * @throws InvalidSessionException_Exception
	 * @throws LimitExceededException_Exception
	 * @throws MoneyException_Exception
	 * @see com.g4g.ws.G4GFinancial#withdrawal(String, Money, WithdrawalMethod)
	 */
	public static String withdrawal(String sessionId, Money amount,
			WithdrawalMethod withdrawalMethod)
			throws InternalException_Exception,
			InvalidSessionException_Exception,
			LimitExceededException_Exception, MoneyException_Exception {
		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,
				new StringBuffer(G4GFinancialDelegator.class.getName()).append(
						G4GConstants.COLON_WITH_SPACES).append(G4GConstants.CALLINGMETHOD)
						.append(DataUtil.getCallingMethod()).append(
								G4GConstants.CURRENTMETHOD).append(
								DataUtil.getCurrentMethod()).append(
								G4GConstants.DASHES).append(
								G4GConstants.STARTED).toString(), Level.INFO);

		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,
				new StringBuffer(G4GFinancialDelegator.class.getName()).append(
						G4GConstants.COLON_WITH_SPACES).append(G4GConstants.CALLINGMETHOD)
						.append(DataUtil.getCallingMethod()).append(
								G4GConstants.CURRENTMETHOD).append(
								DataUtil.getCurrentMethod()).append(
								G4GConstants.DASHES).append(G4GConstants.ENDED)
						.toString(), Level.INFO);

		return g4gFinancial.withdrawal(sessionId, amount, withdrawalMethod);
	}

	/**
	 * @param sessionId
	 * @return Balance
	 * @throws InternalException_Exception
	 * @throws InvalidSessionException_Exception
	 * @see com.g4g.ws.G4GFinancial#getBalance(String)
	 */
	public static Balance getBalance(String sessionId)
			throws InternalException_Exception,
			InvalidSessionException_Exception {

		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,
				new StringBuffer(G4GFinancialDelegator.class.getName()).append(
						G4GConstants.COLON_WITH_SPACES).append(G4GConstants.CALLINGMETHOD)
						.append(DataUtil.getCallingMethod()).append(
								G4GConstants.CURRENTMETHOD).append(
								DataUtil.getCurrentMethod()).append(
								G4GConstants.DASHES).append(
								G4GConstants.STARTED).toString(), Level.INFO);

		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,
				new StringBuffer(G4GFinancialDelegator.class.getName()).append(
						G4GConstants.COLON_WITH_SPACES).append(G4GConstants.CALLINGMETHOD)
						.append(DataUtil.getCallingMethod()).append(
								G4GConstants.CURRENTMETHOD).append(
								DataUtil.getCurrentMethod()).append(
								G4GConstants.DASHES).append(G4GConstants.ENDED)
						.toString(), Level.INFO);

		return g4gFinancial.getBalance(sessionId);
	}

	/**
	 * @param username
	 * @return Balance
	 * @throws InternalException_Exception
	 * @throws InvalidSessionException_Exception
	 * @throws UserNotFoundException_Exception
	 * @see com.g4g.ws.G4GFinancial#getBalanceByUsername(String)
	 */
	public static Balance getBalanceByUsername(String username)
			throws InternalException_Exception,
			InvalidSessionException_Exception, UserNotFoundException_Exception {
		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,
				new StringBuffer(G4GFinancialDelegator.class.getName()).append(
						G4GConstants.COLON_WITH_SPACES).append(G4GConstants.CALLINGMETHOD)
						.append(DataUtil.getCallingMethod()).append(
								G4GConstants.CURRENTMETHOD).append(
								DataUtil.getCurrentMethod()).append(
								G4GConstants.DASHES).append(
								G4GConstants.STARTED).toString(), Level.INFO);

		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,
				new StringBuffer(G4GFinancialDelegator.class.getName()).append(
						G4GConstants.COLON_WITH_SPACES).append(G4GConstants.CALLINGMETHOD)
						.append(DataUtil.getCallingMethod()).append(
								G4GConstants.CURRENTMETHOD).append(
								DataUtil.getCurrentMethod()).append(
								G4GConstants.DASHES).append(G4GConstants.ENDED)
						.toString(), Level.INFO);

		return g4gFinancial.getBalanceByUsername(username);
	}

	/**
	 * @param userId
	 * @return Balance
	 * @throws InternalException_Exception
	 * @throws InvalidSessionException_Exception
	 * @throws UserNotFoundException_Exception
	 * @see com.g4g.ws.G4GFinancial#getBalanceByUserId(String)
	 */
	public static Balance getBalanceByUserId(String userId)
			throws InternalException_Exception,
			InvalidSessionException_Exception, UserNotFoundException_Exception {
		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,
				new StringBuffer(G4GFinancialDelegator.class.getName()).append(
						G4GConstants.COLON_WITH_SPACES).append(G4GConstants.CALLINGMETHOD)
						.append(DataUtil.getCallingMethod()).append(
								G4GConstants.CURRENTMETHOD).append(
								DataUtil.getCurrentMethod()).append(
								G4GConstants.DASHES).append(
								G4GConstants.STARTED).toString(), Level.INFO);

		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,
				new StringBuffer(G4GFinancialDelegator.class.getName()).append(
						G4GConstants.COLON_WITH_SPACES).append(G4GConstants.CALLINGMETHOD)
						.append(DataUtil.getCallingMethod()).append(
								G4GConstants.CURRENTMETHOD).append(
								DataUtil.getCurrentMethod()).append(
								G4GConstants.DASHES).append(G4GConstants.ENDED)
						.toString(), Level.INFO);

		return g4gFinancial.getBalanceByUserId(userId);
	}

	/**
	 * @param sessionId
	 * @return List<WithdrawalMethod>
	 * @throws InternalException_Exception
	 * @throws InvalidSessionException_Exception
	 * @see com.g4g.ws.G4GFinancial#getWithdrawalMethods(String)
	 */
	public static List<WithdrawalMethod> getWithdrawalMethods(String sessionId)
			throws InternalException_Exception,
			InvalidSessionException_Exception {
		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,
				new StringBuffer(G4GFinancialDelegator.class.getName()).append(
						G4GConstants.COLON_WITH_SPACES).append(G4GConstants.CALLINGMETHOD)
						.append(DataUtil.getCallingMethod()).append(
								G4GConstants.CURRENTMETHOD).append(
								DataUtil.getCurrentMethod()).append(
								G4GConstants.DASHES).append(
								G4GConstants.STARTED).toString(), Level.INFO);

		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,
				new StringBuffer(G4GFinancialDelegator.class.getName()).append(
						G4GConstants.COLON_WITH_SPACES).append(G4GConstants.CALLINGMETHOD)
						.append(DataUtil.getCallingMethod()).append(
								G4GConstants.CURRENTMETHOD).append(
								DataUtil.getCurrentMethod()).append(
								G4GConstants.DASHES).append(
								G4GConstants.WITHDRAWALMETHOD).append(
								G4GConstants.EQUAL).append(G4GConstants.ENDED)
						.toString(), Level.INFO);

		return g4gFinancial.getWithdrawalMethods(sessionId);
	}

	/**
	 * @param sessionId
	 * @param detailsFlag
	 * @return List<PaymentAccount>
	 * @throws InternalException_Exception
	 * @throws InvalidSessionException_Exception
	 * @see com.g4g.ws.G4GFinancial#getPaymentAccounts(String, boolean)
	 */
	public static List<PaymentAccount> getPaymentAccounts(String sessionId,
			boolean detailsFlag) throws InternalException_Exception,
			InvalidSessionException_Exception {
		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,
				new StringBuffer(G4GFinancialDelegator.class.getName()).append(
						G4GConstants.COLON_WITH_SPACES).append(G4GConstants.CALLINGMETHOD)
						.append(DataUtil.getCallingMethod()).append(
								G4GConstants.CURRENTMETHOD).append(
								DataUtil.getCurrentMethod()).append(
								G4GConstants.DASHES).append(
								G4GConstants.STARTED).toString(), Level.INFO);

		return g4gFinancial.getPaymentAccounts(sessionId, detailsFlag);
	}

	/**
	 * @param sessionId
	 * @param transactionTypeList
	 * @param from
	 * @param to
	 * @return List<Transaction>
	 * @throws InternalException_Exception
	 * @throws InvalidSessionException_Exception
	 * @see com.g4g.ws.G4GFinancial#statement(String, List, long, long)
	 */
	@SuppressWarnings(G4GConstants.UNCHECKED)
	// TransactionType is not available in Impassa
	public static List<Transaction> statement(String sessionId,
			List transactionTypeList, long from, long to)
			throws InternalException_Exception,
			InvalidSessionException_Exception {
		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,
				new StringBuffer(G4GFinancialDelegator.class.getName()).append(
						G4GConstants.COLON_WITH_SPACES).append(G4GConstants.CALLINGMETHOD)
						.append(DataUtil.getCallingMethod()).append(
								G4GConstants.CURRENTMETHOD).append(
								DataUtil.getCurrentMethod()).append(
								G4GConstants.DASHES).append(
								G4GConstants.STARTED).toString(), Level.INFO);

		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,
				new StringBuffer(G4GFinancialDelegator.class.getName()).append(
						G4GConstants.COLON_WITH_SPACES).append(G4GConstants.CALLINGMETHOD)
						.append(DataUtil.getCallingMethod()).append(
								G4GConstants.CURRENTMETHOD).append(
								DataUtil.getCurrentMethod()).append(
								G4GConstants.DASHES).append(
								G4GConstants.TRANSACTION).append(
								G4GConstants.EQUAL).append(G4GConstants.ENDED)
						.toString(), Level.INFO);

		return g4gFinancial.statement(sessionId, transactionTypeList, from, to);
	}

	/**
	 * @param sessionId
	 * @return User
	 * @throws InternalException_Exception
	 * @throws InvalidSessionException_Exception
	 * @see com.g4g.ws.G4GFinancial#getRegistrationDetails(String)
	 */
	public static User getRegistrationDetails(String sessionId)
			throws InternalException_Exception,
			InvalidSessionException_Exception {

		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,
				new StringBuffer(G4GFinancialDelegator.class.getName()).append(
						G4GConstants.COLON_WITH_SPACES).append(G4GConstants.CALLINGMETHOD)
						.append(DataUtil.getCallingMethod()).append(
								G4GConstants.CURRENTMETHOD).append(
								DataUtil.getCurrentMethod()).append(
								G4GConstants.DASHES).append(
								G4GConstants.STARTED).toString(), Level.INFO);

		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,
				new StringBuffer(G4GFinancialDelegator.class.getName()).append(
						G4GConstants.COLON_WITH_SPACES).append(G4GConstants.CALLINGMETHOD)
						.append(DataUtil.getCallingMethod()).append(
								G4GConstants.CURRENTMETHOD).append(
								DataUtil.getCurrentMethod()).append(
								G4GConstants.DASHES).append(G4GConstants.ENDED)
						.toString(), Level.INFO);

		return g4gFinancial.getRegistrationDetails(sessionId);
	}

	/**
	 * @param userName
	 * @return User
	 * @throws InternalException_Exception
	 * @throws UserNotFoundException_Exception
	 * @see com.g4g.ws.G4GFinancial#getUserInfo(String)
	 */
	public static User getUserInfo(String userName)
			throws InternalException_Exception, UserNotFoundException_Exception {
		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,
				new StringBuffer(G4GFinancialDelegator.class.getName()).append(
						G4GConstants.COLON_WITH_SPACES).append(G4GConstants.CALLINGMETHOD)
						.append(DataUtil.getCallingMethod()).append(
								G4GConstants.CURRENTMETHOD).append(
								DataUtil.getCurrentMethod()).append(
								G4GConstants.DASHES).append(
								G4GConstants.STARTED).toString(), Level.INFO);

		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,
				new StringBuffer(G4GFinancialDelegator.class.getName()).append(
						G4GConstants.COLON_WITH_SPACES).append(G4GConstants.CALLINGMETHOD)
						.append(DataUtil.getCallingMethod()).append(
								G4GConstants.CURRENTMETHOD).append(
								DataUtil.getCurrentMethod()).append(
								G4GConstants.DASHES).append(G4GConstants.ENDED)
						.toString(), Level.INFO);

		return g4gFinancial.getUserInfo(userName);
	}

	/**
	 * @param userId
	 * @return User
	 * @throws InternalException_Exception
	 * @throws UserNotFoundException_Exception
	 * @see com.g4g.ws.G4GFinancial#getUserByUserId(String)
	 */
	public static User getUserByUserId(String userId)
			throws InternalException_Exception, UserNotFoundException_Exception {
		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,
				new StringBuffer(G4GFinancialDelegator.class.getName()).append(
						G4GConstants.COLON_WITH_SPACES).append(G4GConstants.CALLINGMETHOD)
						.append(DataUtil.getCallingMethod()).append(
								G4GConstants.CURRENTMETHOD).append(
								DataUtil.getCurrentMethod()).append(
								G4GConstants.DASHES).append(
								G4GConstants.STARTED).toString(), Level.INFO);

		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,
				new StringBuffer(G4GFinancialDelegator.class.getName()).append(
						G4GConstants.COLON_WITH_SPACES).append(G4GConstants.CALLINGMETHOD)
						.append(DataUtil.getCallingMethod()).append(
								G4GConstants.CURRENTMETHOD).append(
								DataUtil.getCurrentMethod()).append(
								G4GConstants.DASHES).append(G4GConstants.ENDED)
						.toString(), Level.INFO);

		return g4gFinancial.getUserByUserId(userId);
	}

	/**
	 * @param username
	 * @param password
	 * @param siteId
	 * @param ipAddress
	 * @return sessionId
	 * @throws AuthenticationException_Exception
	 * @throws PlayerStatusException_Exception
	 * @throws InternalException_Exception
	 * @see com.g4g.ws.G4GFinancial#login(String, String, String, String)
	 */
	public static String login(String username, String password, String siteId,
			String ipAddress) throws AuthenticationException_Exception,
			PlayerStatusException_Exception, InternalException_Exception {
		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,
				new StringBuffer(G4GFinancialDelegator.class.getName()).append(
						G4GConstants.COLON_WITH_SPACES).append(G4GConstants.CALLINGMETHOD)
						.append(DataUtil.getCallingMethod()).append(
								G4GConstants.CURRENTMETHOD).append(
								DataUtil.getCurrentMethod()).append(
								G4GConstants.DASHES).append(
								G4GConstants.STARTED).toString(), Level.INFO);

		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,
				new StringBuffer(G4GFinancialDelegator.class.getName()).append(
						G4GConstants.COLON_WITH_SPACES).append(G4GConstants.CALLINGMETHOD)
						.append(DataUtil.getCallingMethod()).append(
								G4GConstants.CURRENTMETHOD).append(
								DataUtil.getCurrentMethod()).append(
								G4GConstants.DASHES)
								.append(G4GConstants.PARAMETERS).append(G4GConstants.USER_NAME)
								.append(G4GConstants.PASSWORD).append(G4GConstants.SITEID_SMALL)
								.append(G4GConstants.ENDED)
						.toString(), Level.INFO);

		return g4gFinancial.login(username, password, siteId, ipAddress);
	}

	/**
	 * @param sessionId
	 * @param user
	 * @throws InternalException_Exception
	 * @throws InvalidSessionException_Exception
	 * @throws UserValidationException_Exception
	 * @see com.g4g.ws.G4GFinancial#setRegistrationDetails(String, User)
	 */
	public static void setUpdateUser(String sessionId, User user)
			throws InternalException_Exception,
			InvalidSessionException_Exception,
			UserValidationException_Exception {
		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,
				new StringBuffer(G4GFinancialDelegator.class.getName()).append(
						G4GConstants.COLON_WITH_SPACES).append(G4GConstants.CALLINGMETHOD)
						.append(DataUtil.getCallingMethod()).append(
								G4GConstants.CURRENTMETHOD).append(
								DataUtil.getCurrentMethod()).append(
								G4GConstants.DASHES).append(
								G4GConstants.STARTED).toString(), Level.INFO);

		g4gFinancial.setRegistrationDetails(sessionId, user);

		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,
				new StringBuffer(G4GFinancialDelegator.class.getName()).append(
						G4GConstants.COLON_WITH_SPACES).append(G4GConstants.CALLINGMETHOD)
						.append(DataUtil.getCallingMethod()).append(
								G4GConstants.CURRENTMETHOD).append(
								DataUtil.getCurrentMethod()).append(
								G4GConstants.DASHES).append(G4GConstants.ENDED)
						.toString(), Level.INFO);
	}
}
