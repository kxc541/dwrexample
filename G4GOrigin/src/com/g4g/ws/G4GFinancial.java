/**********************************************************
 * Copyright (c) 2008 - 2008, Askdigi, All rights reserved.
 * 
 **********************************************************/
package com.g4g.ws;

/**
 * Used to complement the LiquidPayment WebService.
 * 
 * @author Jigar Mistry
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

import com.g4g.utils.AuditUtil;
import com.g4g.utils.G4GConstants;
import com.g4g.utils.properties.G4GProperties;
import com.g4g.utils.properties.PropertiesConstants;
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
import com.impessa.worldgaming.client.LiquidPayment;
import com.impessa.worldgaming.client.LiquidPaymentService;
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
 * The Class G4GFinancial.
 */
public class G4GFinancial {

	// Database Related Information
	/** The DRIVER. */
	private final String DRIVER = G4GProperties.getProperty(PropertiesConstants.G4G_FINANCIALS_DB_DRIVER);

	/** The URL. */
	private final String URL = G4GProperties.getProperty(PropertiesConstants.G4G_FINANCIALS_DB_URL);

	/** The USERNAME. */
	private final String USERNAME = G4GProperties.getProperty(PropertiesConstants.G4G_FINANCIALS_DB_USERNAME);

	/** The PASSWORD. */
	private final String PASSWORD = G4GProperties.getProperty(PropertiesConstants.G4G_FINANCIALS_DB_PASSWORD);

	/** The WEBSERVICE. */
	private final String WEBSERVICE = "true";

	/** The connection. */
	private Connection connection = null;

	// Liquid Payment Web-Service Objects
	/** The liquid payment service. */
	private LiquidPaymentService liquidPaymentService;

	/** The liquid payment. */
	private LiquidPayment liquidPayment;

	/**
	 * Constructor which will initialize connection as well as initialize the
	 * web service as per the value set for the attribute
	 * 'liquidPaymentSystemAvailable' in Resource Bundle.
	 * 
	 *             the exception
	 */
	public static G4GFinancial g4gfinancial = null;
	

	/**
	 * @author ankur 
	 * @return G4GFinancial
	 * 
	 * 
	 */
	public static G4GFinancial getInstance() {
		if(g4gfinancial==null || g4gfinancial.liquidPayment == null || g4gfinancial.liquidPaymentService == null) {
			g4gfinancial = new G4GFinancial();
		}
		return g4gfinancial;
	}
	
	/**
	 * Instantiates a new g4 g financial.
	 */
	public G4GFinancial() {
		if (WEBSERVICE.equalsIgnoreCase(G4GConstants.FALSE)) {
			try {
				Class.forName(DRIVER);
				this.connection = DriverManager.getConnection(URL, USERNAME,PASSWORD);
			}
			catch (ClassNotFoundException exception) {
				AuditUtil.getInstance().writeLog(AuditUtil.FILE_TYPE_G4G, exception);
			}
			catch (SQLException exception) {
				AuditUtil.getInstance().writeLog(AuditUtil.FILE_TYPE_G4G, exception);
			}
		} else if (WEBSERVICE.equalsIgnoreCase(G4GConstants.TRUE)) {
			liquidPaymentService = new LiquidPaymentService();
			liquidPayment = liquidPaymentService.getLiquidPaymentPort();
		}
	}

	/**
	 * Moves funds from player account to bet account. Records GB transaction
	 * (See 'Transaction types')
	 * 
	 * @param sessionId
	 *            Session identifier, obtained from login web method. Shows
	 *            which user is going to bet.
	 * @param betId
	 *            Bet identifier obtained from createBet method.
	 * @param money
	 *            Funds to be transferred.
	 * 
	 * @throws MoneyException_Exception
	 *             the money exception_ exception
	 * @throws InvalidSessionException_Exception
	 *             the invalid session exception_ exception
	 * @throws InternalException_Exception
	 *             the internal exception_ exception
	 * @throws InvalidBetException_Exception
	 *             the invalid bet exception_ exception
	 * 
	 */
	public void bet(String sessionId, String betId, Money money)
			throws InvalidSessionException_Exception,
			InternalException_Exception, InvalidBetException_Exception,
			MoneyException_Exception {
		if (WEBSERVICE.equalsIgnoreCase(G4GConstants.FALSE)) {
			Bet bet = new Bet(URL, USERNAME, PASSWORD);
			bet.bet(sessionId, betId, money);
		} else if (WEBSERVICE.equalsIgnoreCase(G4GConstants.TRUE)) {
			liquidPayment.bet(sessionId, betId, money);
		}
	}

	/**
	 * Changes user password. User is identified basing on sessionId.
	 * 
	 * @param sessionId
	 * @param oldPassword
	 * @param newPassword
	 * @throws InternalException_Exception
	 * @throws InvalidPasswordException_Exception
	 * @throws InvalidSessionException_Exception
	 * @throws UserValidationException_Exception
	 * 
	 * 
	 */
	public void changePassword(String sessionId, String oldPassword,
			String newPassword) throws InternalException_Exception,
			InvalidPasswordException_Exception,
			InvalidSessionException_Exception,
			UserValidationException_Exception {
		if (WEBSERVICE.equalsIgnoreCase(G4GConstants.FALSE)) {
			ChangePassword changePassword = new ChangePassword(URL, USERNAME,
					PASSWORD);
			changePassword.changePassword(sessionId, oldPassword, newPassword);
		} else if (WEBSERVICE.equalsIgnoreCase(G4GConstants.TRUE)) {
			liquidPayment.changePassword(sessionId, oldPassword, newPassword);
		}
	}

	/**
	 * 
	 * @return String Bet identifier.
	 * 
	 * @throws InternalException_Exception
	 *             the internal exception_ exception
	 * 
	 */
	public String createBet() throws InternalException_Exception {
		if (WEBSERVICE.equalsIgnoreCase(G4GConstants.FALSE)) {
			CreateBet createBet = new CreateBet(URL, USERNAME, PASSWORD);
			return createBet.createBet();
		} else if (WEBSERVICE.equalsIgnoreCase(G4GConstants.TRUE)) {
			return liquidPayment.createBet();
		}
		return null;
	}

	/**
	 * Delete financial user.
	 * 
	 * @param userId
	 *            the user id
	 * 
	 * @return the string
	 */
	public String deleteFinancialUser(String userId) {
		if (WEBSERVICE.equalsIgnoreCase(G4GConstants.FALSE)) {
			DeleteFinancialUser deleteFinancialUser = new DeleteFinancialUser(
					URL, USERNAME, PASSWORD);
			deleteFinancialUser.deleteFinancialUser(userId);
		}
		return null;
	}

	/**
	 * 
	 * @param sessionId
	 *            Session identifier, obtained from login web method.
	 * @param accountId
	 *            Identifier of a payment account which is going to be deleted
	 * 
	 * @throws InvalidSessionException_Exception
	 *             the invalid session exception_ exception
	 * @throws InternalException_Exception
	 *             the internal exception_ exception
	 * @throws AccessException_Exception
	 *             the access exception_ exception
	 * 
	 */
	public void deletePaymentAccount(String sessionId, long accountId)
			throws AccessException_Exception, InternalException_Exception,
			InvalidSessionException_Exception {
		if (WEBSERVICE.equalsIgnoreCase(G4GConstants.FALSE)) {
			DeletePaymentAccount deletePaymentAccount = new DeletePaymentAccount(
					URL, USERNAME, PASSWORD);
			deletePaymentAccount.deletePaymentAccount(sessionId, accountId);
		} else if (WEBSERVICE.equalsIgnoreCase(G4GConstants.TRUE)) {
			liquidPayment.deletePaymentAccount(sessionId, accountId);
		}
	}

	/**
	 * when successfully deposited (See 'Transaction types')
	 * 
	 * @param sessionId
	 *            Session identifier, obtained from login web method.
	 * @param depositInput
	 *            Detailed information about deposit.
	 * 
	 * @return DepositOutput
	 * 
	 * @throws LimitExceededException_Exception
	 *             the limit exceeded exception_ exception
	 * @throws InvalidSessionException_Exception
	 *             the invalid session exception_ exception
	 * @throws InvalidPaymentException_Exception
	 *             the invalid payment exception_ exception
	 * @throws InternalException_Exception
	 *             the internal exception_ exception
	 * 
	 */
	public DepositOutput deposit(String sessionId, DepositInput depositInput)
			throws InternalException_Exception,
			InvalidPaymentException_Exception,
			InvalidSessionException_Exception, LimitExceededException_Exception {
		if (WEBSERVICE.equalsIgnoreCase(G4GConstants.FALSE)) {
			Deposit deposit = new Deposit(URL, USERNAME, PASSWORD);
			return deposit.deposit(sessionId, depositInput);
		} else if (WEBSERVICE.equalsIgnoreCase(G4GConstants.TRUE)) {
			return liquidPayment.deposit(sessionId, depositInput);
			/*
			 * CreditCardPaymentRequest payment = new
			 * CreditCardPaymentRequest();
			 * 
			 * payment.setIpAddress("83.31.179.1"); Money m = new Money();
			 * 
			 * m.setAmount(1000); m.setCurrency(CurrencyType.GBP);
			 * 
			 * payment.setOrderTotal(m); payment.setAccountId(1); // NOTE THAT
			 * ACCOUNT ID is VALID Credit // Card Account Id.
			 * 
			 * CreditCardPaymentResponse response = null; response =
			 * (CreditCardPaymentResponse) liquidPayment.deposit( sessionId,
			 * payment);
			 * 
			 * String tranId = response.getTransactionId();
			 */
		}
		return null;
	}

	/**
	 * 
	 * @param sessionId
	 *            Session identifier, obtained from login web method.
	 * 
	 * @return Balance User balance in two parts - winnings balance and bank
	 *         balance. Total balance is sum of both.
	 * 
	 * @throws InvalidSessionException_Exception
	 *             the invalid session exception_ exception
	 * @throws InternalException_Exception
	 *             the internal exception_ exception
	 * 
	 */
	public Balance getBalance(String sessionId)
			throws InternalException_Exception,
			InvalidSessionException_Exception {
		if (WEBSERVICE.equalsIgnoreCase(G4GConstants.FALSE)) {
			GetBalance getBalance = new GetBalance(URL, USERNAME, PASSWORD);
			return getBalance.getBalance(sessionId);
		} else if (WEBSERVICE.equalsIgnoreCase(G4GConstants.TRUE)) {
			return liquidPayment.getBalance(sessionId);
		}
		return null;
	}

	/**
	 * Used to get user balance.
	 * 
	 * @param username
	 *            the username
	 * 
	 * @return Balance
	 * 
	 * @throws InternalException_Exception
	 *             the internal exception_ exception
	 * @throws InvalidSessionException_Exception
	 *             the invalid session exception_ exception
	 * @throws UserNotFoundException_Exception
	 *             the user not found exception_ exception
	 */
	public Balance getBalanceByUsername(String username)
			throws InternalException_Exception,
			InvalidSessionException_Exception, UserNotFoundException_Exception {
		if (WEBSERVICE.equalsIgnoreCase(G4GConstants.FALSE)) {
			GetBalanceByUsername getBalanceByUsername = new GetBalanceByUsername(
					URL, USERNAME, PASSWORD);
			return getBalanceByUsername.getBalanceByUsername(username);
		} else if (WEBSERVICE.equalsIgnoreCase(G4GConstants.TRUE)) {
			return liquidPayment.getBalanceByUsername(username);
		}
		return null;
	}

	/**
	 * Gets the balance by user id.
	 * 
	 * @param userId
	 *            the user id
	 * 
	 * @return the balance by user id
	 * 
	 * @throws InternalException_Exception
	 *             the internal exception_ exception
	 * @throws InvalidSessionException_Exception
	 *             the invalid session exception_ exception
	 * @throws UserNotFoundException_Exception
	 *             the user not found exception_ exception
	 */
	public Balance getBalanceByUserId(String userId)
			throws InternalException_Exception,
			InvalidSessionException_Exception, UserNotFoundException_Exception {
		if (WEBSERVICE.equalsIgnoreCase(G4GConstants.TRUE)) {
			return liquidPayment.getBalanceByUsername(userId);
		}
		return null;
	}

	/**
	 * 
	 * @param betId
	 *            Bet identifier obtained from createBet method.
	 * 
	 * @return BetDetails
	 * 
	 * @throws InvalidBetException_Exception
	 *             the invalid bet exception_ exception
	 * @throws InternalException_Exception
	 *             the internal exception_ exception
	 * 
	 */
	public BetDetails getBetDetails(String betId)
			throws InternalException_Exception, InvalidBetException_Exception {
		if (WEBSERVICE.equalsIgnoreCase(G4GConstants.FALSE)) {
			GetBetDetails getBetDetails = new GetBetDetails();
			return getBetDetails.getBetDetails(betId);
		} else if (WEBSERVICE.equalsIgnoreCase(G4GConstants.TRUE)) {
			return liquidPayment.getBetDetails(betId);
		}
		return null;
	}

	/**
	 * Used to obtain URL of CSR web interface.
	 * 
	 * @return String url of CSR web interface
	 */
	public String getCSRUrl() {
		if (WEBSERVICE.equalsIgnoreCase(G4GConstants.FALSE)) {
			GetCSRUrl getCSRUrl = new GetCSRUrl();
			return getCSRUrl.getCSRUrl();
		} else if (WEBSERVICE.equalsIgnoreCase(G4GConstants.TRUE)) {
			return liquidPayment.getCSRUrl();
		}
		return null;
	}

	/**
	 * DetailsFlag decides wheather all information, or just accountId and name
	 * is returned.
	 * 
	 * @param sessionId
	 *            Session identifier, obtained from login web method.
	 * @param detailsFlag
	 *            when set to 'true', means that detailed information about
	 *            payment account will be returned. (leaf objects in inheritance
	 *            tree for PaymentAccount) When false just id and name. (Just
	 *            PaymentAccount – root objects are on a list)
	 * 
	 * @return List Payment accounts list.
	 * 
	 * @throws InvalidSessionException_Exception
	 *             the invalid session exception_ exception
	 * @throws InternalException_Exception
	 *             the internal exception_ exception
	 * 
	 */
	@SuppressWarnings("unchecked")
	public List<PaymentAccount> getPaymentAccounts(String sessionId,
			boolean detailsFlag) throws InternalException_Exception,
			InvalidSessionException_Exception {
		if (WEBSERVICE.equalsIgnoreCase(G4GConstants.FALSE)) {
			GetPaymentAccounts getPaymentAccounts = new GetPaymentAccounts(URL,
					USERNAME, PASSWORD);
			return getPaymentAccounts
					.getPaymentAccounts(sessionId, detailsFlag);
		} else if (WEBSERVICE.equalsIgnoreCase(G4GConstants.TRUE)) {
			return liquidPayment.getPaymentAccounts(sessionId, detailsFlag);
		}
		return null;
	}

	/**
	 * 
	 * @param sessionId
	 *            Session identifier, obtained from login web method.
	 * 
	 * @return User User object
	 * 
	 * @throws InvalidSessionException_Exception
	 *             the invalid session exception_ exception
	 * @throws InternalException_Exception
	 *             the internal exception_ exception
	 * 
	 */
	public User getRegistrationDetails(String sessionId)
			throws InternalException_Exception,
			InvalidSessionException_Exception {
		if (WEBSERVICE.equalsIgnoreCase(G4GConstants.FALSE)) {
			GetRegistrationDetails getRegistrationDetails = new GetRegistrationDetails(
					URL, USERNAME, PASSWORD);
			return getRegistrationDetails.getRegistrationDetails(sessionId);
		} else if (WEBSERVICE.equalsIgnoreCase(G4GConstants.TRUE)) {
			return liquidPayment.getRegistrationDetails(sessionId);
		}
		return null;
	}

	/**
	 * when successfully deposited (See 'Transaction types')
	 * 
	 * @param sessionId
	 *            Session identifier, obtained from login web method.
	 * 
	 * @return List List of withdrawal methods available for player
	 * 
	 * @throws InvalidSessionException_Exception
	 *             the invalid session exception_ exception
	 * @throws InternalException_Exception
	 *             the internal exception_ exception
	 * 
	 */
	public List<WithdrawalMethod> getWithdrawalMethods(String sessionId)
			throws InternalException_Exception,
			InvalidSessionException_Exception {
		if (WEBSERVICE.equalsIgnoreCase(G4GConstants.FALSE)) {
			GetWithdrawalMethods getWithdrawalMethods = new GetWithdrawalMethods(
					URL, USERNAME, PASSWORD);
			return getWithdrawalMethods.getWithdrawalMethods(sessionId);
		} else if (WEBSERVICE.equalsIgnoreCase(G4GConstants.TRUE)) {
			return liquidPayment.getWithdrawalMethods(sessionId);
		}
		return null;
	}

	/**
	 * sessionId - used to secure game and finance services.
	 * 
	 * @param username
	 *            Unique username – users’ identifier – (user email)
	 * @param password
	 *            Users’ password
	 * @param siteId
	 *            Site's id
	 * @param ipAddress
	 *            User's IP address
	 * 
	 * @return String Session identifier which is going to be used for different
	 *         operations.
	 * 
	 * @throws AuthenticationException_Exception
	 *             the authentication exception_ exception
	 * @throws PlayerStatusException_Exception
	 *             the player status exception_ exception
	 * @throws InternalException_Exception
	 *             the internal exception_ exception
	 */
	public String login(String username, String password, String siteId,
			String ipAddress) throws AuthenticationException_Exception,
			PlayerStatusException_Exception, InternalException_Exception {
		
		if (WEBSERVICE.equalsIgnoreCase(G4GConstants.FALSE)) {
			Login login = new Login(URL, USERNAME, PASSWORD);
			return login.login(username, password, siteId, ipAddress);
		} else if (WEBSERVICE.equalsIgnoreCase(G4GConstants.TRUE)) {
			return liquidPayment.login(username, password, siteId, ipAddress);
		}
		return null;

	}

	/**
	 * 
	 * @param betId
	 *            Bet identifier obtained from createBet method.
	 * @param siteId
	 *            Site identifier, identifies the House.
	 * @param money
	 *            Amount of money to move
	 * 
	 * @throws SiteNotFoundException_Exception
	 *             the site not found exception_ exception
	 * @throws MoneyException_Exception
	 *             the money exception_ exception
	 * @throws InvalidBetException_Exception
	 *             the invalid bet exception_ exception
	 * @throws InternalException_Exception
	 *             the internal exception_ exception
	 * 
	 */
	public void moveToHouseAccount(String betId, String siteId, Money money)
			throws InternalException_Exception, InvalidBetException_Exception,
			MoneyException_Exception, SiteNotFoundException_Exception {
		if (WEBSERVICE.equalsIgnoreCase(G4GConstants.FALSE)) {
			MoveToHouseAccount moveToHouseAccount = new MoveToHouseAccount(URL,
					USERNAME, PASSWORD);
			moveToHouseAccount.moveToHouseAccount(betId, siteId, money);
		} else if (WEBSERVICE.equalsIgnoreCase(G4GConstants.TRUE)) {
			liquidPayment.moveToHouseAccount(betId, siteId, money);
		}
	}

	/**
	 * Records PC or PW transaction. (See 'Transaction types')
	 * 
	 * @param betId
	 *            Bet identifier obtained from createBet method.
	 * @param username
	 *            Username (email) of a player, we are moving funds to.
	 * @param money
	 *            Funds to move
	 * @param transactionType
	 *            Type of transaction – allowed values: PC – bet cancelled PW –
	 *            player won
	 * 
	 * @throws MoneyException_Exception
	 *             the money exception_ exception
	 * @throws InvalidSessionException_Exception
	 *             the invalid session exception_ exception
	 * @throws InvalidBetException_Exception
	 *             the invalid bet exception_ exception
	 * @throws InternalException_Exception
	 *             the internal exception_ exception
	 * @throws UserNotFoundException_Exception
	 *             the user not found exception_ exception
	 * 
	 */
	public void moveToPlayerAccount(String betId, String username, Money money,
			PlayerTransactionType transactionType)
			throws InternalException_Exception, InvalidBetException_Exception,
			InvalidSessionException_Exception, MoneyException_Exception,
			UserNotFoundException_Exception {
		if (WEBSERVICE.equalsIgnoreCase(G4GConstants.FALSE)) {
			MoveToPlayerAccount moveToPlayerAccount = new MoveToPlayerAccount(
					URL, USERNAME, PASSWORD);
			moveToPlayerAccount.moveToPlayerAccount(betId, username, money,
					transactionType);
		} else if (WEBSERVICE.equalsIgnoreCase(G4GConstants.TRUE)) {
			/*
			 * public void moveToPlayerAccount(String username, String betId,
			 * Money money, PlayerTransactionType transactionType)
			 */
			liquidPayment.moveToPlayerAccount(username, betId, money,
					transactionType);
		}
	}

	/**
	 * 
	 * @param sessionId
	 *            Session identifier, obtained from login web method.
	 * @param paymentAccount
	 *            Payment account details
	 * 
	 * @throws InvalidSessionException_Exception
	 *             the invalid session exception_ exception
	 * @throws InvalidPaymentException_Exception
	 *             the invalid payment exception_ exception
	 * @throws InternalException_Exception
	 *             the internal exception_ exception
	 * 
	 */
	public void registerPaymentAccount(String sessionId,
			PaymentAccount paymentAccount) throws InternalException_Exception,
			InvalidPaymentException_Exception,
			InvalidSessionException_Exception {
		if (WEBSERVICE.equalsIgnoreCase(G4GConstants.FALSE)) {
			RegisterPaymentAccount registerPaymentAccount = new RegisterPaymentAccount(
					URL, USERNAME, PASSWORD);
			registerPaymentAccount.registerPaymentAccount(sessionId,
					paymentAccount);
		} else if (WEBSERVICE.equalsIgnoreCase(G4GConstants.TRUE)) {
			liquidPayment.registerPaymentAccount(sessionId, paymentAccount);
		}
	}

	/**
	 * username. SiteId is valid site id registered in LiquidPayment system (by
	 * CSR)
	 * 
	 * @param user
	 *            the user
	 * 
	 * @throws UserValidationException_Exception
	 *             the user validation exception_ exception
	 * @throws SiteNotFoundException_Exception
	 *             the site not found exception_ exception
	 * @throws InternalException_Exception
	 *             the internal exception_ exception
	 * 
	 */
	public void registerUser(User user) throws InternalException_Exception,
			SiteNotFoundException_Exception, UserValidationException_Exception {
		if (WEBSERVICE.equalsIgnoreCase(G4GConstants.FALSE)) {
			RegisterUser registerUser = new RegisterUser(URL, USERNAME,PASSWORD);
			registerUser.registerUser(user);
		} else if (WEBSERVICE.equalsIgnoreCase(G4GConstants.TRUE)) {
			liquidPayment.registerUser(user);
		}
	}

	/**
	 * 
	 * @param username
	 *            Username (email address).
	 * 
	 * @return String
	 * @throws UserNotFoundException_Exception 
	 * @throws InternalException_Exception 
	 * 
	 */
	public String resetPassword(String username) throws InternalException_Exception, UserNotFoundException_Exception {
		if (WEBSERVICE.equalsIgnoreCase(G4GConstants.FALSE)) {
			ResetPassword resetPassword = new ResetPassword(URL, USERNAME,PASSWORD);
			return resetPassword.resetPassword(username);
		} else if (WEBSERVICE.equalsIgnoreCase(G4GConstants.TRUE)) {
			return liquidPayment.resetPassword(username);
		}
		return null;
	}

	/**
	 * and their values are ignored when updating player profile.
	 * 
	 * @param sessionId
	 *            Session identifier, obtained from login web method.
	 * @param user
	 *            User object with data which is going to be edited.
	 * 
	 * 
	 * @throws UserValidationException_Exception
	 *             the user validation exception_ exception
	 * @throws InvalidSessionException_Exception
	 *             the invalid session exception_ exception
	 * @throws InternalException_Exception
	 *             the internal exception_ exception
	 * 
	 */
	public void setRegistrationDetails(String sessionId, User user)
			throws InternalException_Exception,
			InvalidSessionException_Exception,
			UserValidationException_Exception {
		if (WEBSERVICE.equalsIgnoreCase(G4GConstants.FALSE)) {
			SetRegistrationDetails setRegistrationDetails = new SetRegistrationDetails(
					URL, USERNAME, PASSWORD);
			setRegistrationDetails.setRegistrationDetails(sessionId, user);
		} else if (WEBSERVICE.equalsIgnoreCase(G4GConstants.TRUE)) {
			liquidPayment.setRegistrationDetails(sessionId, user);
		}
	}

	/**
	 * timestamps. See 'Transaction types'
	 * 
	 * @param sessionId
	 *            Session identifier, obtained from login web method.
	 * @param from
	 *            Start time for transaction list.
	 * @param to
	 *            End time for transaction list.
	 * @param transactionTypeList
	 *            the transaction type list
	 * 
	 * @return List List of transactions.
	 * @throws InternalException_Exception 
	 * @throws InvalidSessionException_Exception 
	 * 
	 */
	@SuppressWarnings("unchecked")
	public List<Transaction> statement(String sessionId,
			List transactionTypeList, long from, long to)
			throws InternalException_Exception,
			InvalidSessionException_Exception {
		if (WEBSERVICE.equalsIgnoreCase(G4GConstants.FALSE)) {
			Statement statement = new Statement(URL, USERNAME, PASSWORD);
			return statement
					.statement(sessionId, transactionTypeList, from, to);
		} else if (WEBSERVICE.equalsIgnoreCase(G4GConstants.TRUE)) {
			return liquidPayment.statement(sessionId, transactionTypeList,
					from, to);
		}
		return null;
	}

	/**
	 * payment which is going to be updated is in PaymentAccount input object.
	 * 
	 * @param sessionId
	 *            Session identifier, obtained from login web method.
	 * @param paymentAccount
	 *            the payment account
	 * 
	 * @throws InvalidSessionException_Exception
	 *             the invalid session exception_ exception
	 * @throws InvalidPaymentException_Exception
	 *             the invalid payment exception_ exception
	 * @throws InternalException_Exception
	 *             the internal exception_ exception
	 * @throws AccessException_Exception
	 *             the access exception_ exception
	 * 
	 */
	public void updatePaymentAccount(String sessionId,
			PaymentAccount paymentAccount) throws AccessException_Exception,
			InternalException_Exception, InvalidPaymentException_Exception,
			InvalidSessionException_Exception {
		if (WEBSERVICE.equalsIgnoreCase(G4GConstants.FALSE)) {
			UpdatePaymentAccount updatePaymentAccount = new UpdatePaymentAccount(
					URL, USERNAME, PASSWORD);
			updatePaymentAccount
					.updatePaymentAccount(sessionId, paymentAccount);
		} else if (WEBSERVICE.equalsIgnoreCase(G4GConstants.TRUE)) {
			liquidPayment.updatePaymentAccount(sessionId, paymentAccount);
		}
	}

	/**
	 * (RR) transaction (See 'Transaction types')
	 * 
	 * @param sessionId
	 *            Session identifier, obtained from login web method.
	 * @param amount
	 *            Funds to withdraw.
	 * @param withdrawalMethod
	 *            the withdrawal method
	 * 
	 * @return String transactionId – internal transaction identifier.
	 * 
	 * @throws MoneyException_Exception
	 *             the money exception_ exception
	 * @throws LimitExceededException_Exception
	 *             the limit exceeded exception_ exception
	 * @throws InvalidSessionException_Exception
	 *             the invalid session exception_ exception
	 * @throws InternalException_Exception
	 *             the internal exception_ exception
	 * 
	 */
	public String withdrawal(String sessionId, Money amount,
			WithdrawalMethod withdrawalMethod)
			throws InternalException_Exception,
			InvalidSessionException_Exception,
			LimitExceededException_Exception, MoneyException_Exception {
		if (WEBSERVICE.equalsIgnoreCase(G4GConstants.FALSE)) {
			Withdrawal withdrawal = new Withdrawal(URL, USERNAME, PASSWORD);
			withdrawal.withdrawal(sessionId, amount, withdrawalMethod);
		} else if (WEBSERVICE.equalsIgnoreCase(G4GConstants.TRUE)) {
			liquidPayment.withdrawal(sessionId, amount, withdrawalMethod);
		}
		return null;
	}

	/**
	 * Validates connection with database.
	 * 
	 * @return boolean
	 * 
	 * @throws SQLException
	 *             the SQL exception
	 * 
	 * @author ankur
	 */
	public boolean testConnection() throws SQLException {
        return connection.isClosed() || connection == null;
		}

	/**
	 * Gets the user info.
	 * 
	 * @param username
	 *            the username
	 * 
	 * @return the user info
	 * 
	 * @throws InternalException_Exception
	 *             the internal exception_ exception
	 * @throws UserNotFoundException_Exception
	 *             the user not found exception_ exception
	 */
	public User getUserInfo(String username)
			throws InternalException_Exception, UserNotFoundException_Exception {
		if (WEBSERVICE.equalsIgnoreCase(G4GConstants.FALSE)) {
			GetUserInfo getUserInfo = new GetUserInfo(URL, USERNAME, PASSWORD);
			return getUserInfo.getUserInfo(username);
		} else if (WEBSERVICE.equalsIgnoreCase(G4GConstants.TRUE)) {
			return liquidPayment.getUserByUsername(username);
		}
		return null;

	}

	/**
	 * Gets the user by user id.
	 * 
	 * @param userId
	 *            the user id
	 * 
	 * @return the user by user id
	 * 
	 * @throws InternalException_Exception
	 *             the internal exception_ exception
	 * @throws UserNotFoundException_Exception
	 *             the user not found exception_ exception
	 */
	public User getUserByUserId(String userId)
			throws InternalException_Exception, UserNotFoundException_Exception {
		if (WEBSERVICE.equalsIgnoreCase(G4GConstants.TRUE)) {
			return liquidPayment.getUserByUsername(userId);
		}
		return null;
	}

}