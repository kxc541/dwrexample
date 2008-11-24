/**********************************************************
 * Copyright (c) 2008 - 2008, Askdigi, All rights reserved.
 * 
 **********************************************************/
package com.g4g.ws;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.g4g.utils.AuditUtil;
import com.g4g.utils.G4GConstants;
import com.impessa.worldgaming.client.CreditCardPaymentAccount;
import com.impessa.worldgaming.client.PaymentAccount;

// TODO: Auto-generated Javadoc
/**
 * The Class UpdatePaymentAccount updates details of Payment Account. Updates
 * payment account details basing on sessionId. Payment Id for payment which is
 * going to be updated is in PaymentAccount input object
 * 
 * @author Jigar Mistry
 */
public class UpdatePaymentAccount {

	/** The connection establishes connection with specific database. */
	private Connection connection = null;

	/**
	 * The prepared statement An object that represents a precompiled SQL
	 * statement.
	 */
	private PreparedStatement preparedStatement = null;

	/**
	 * The statement The object used for executing a static SQL statement and
	 * returning the results it produces.
	 */
	private Statement statement = null;

	/** The result set A table of data representing a database result set */
	private ResultSet resultSet = null;

	/**
	 * The Constructor UpdatePaymentAccount establishes the connection for
	 * updating the payment account details.
	 * 
	 * @param url
	 *            a database url of the form jdbc:subprotocol:subname.
	 * @param username
	 *            the username of the user who wants to withdraw money.
	 * @param password
	 *            the password to authenticate the given Username.
	 */
	public UpdatePaymentAccount(String url, String username, String password) {
		try {
			this.connection = DriverManager.getConnection(url, username,
					password);
		} catch (SQLException e) {
			AuditUtil.getInstance().writeLog(AuditUtil.FILE_TYPE_G4G,
					this.getClass().getName() + G4GConstants.NONE);
			AuditUtil.getInstance().writeLog(AuditUtil.FILE_TYPE_G4G_FINANCIAL, e);
		}
	}

	/**
	 * Update payment account.
	 * 
	 * @param sessionId
	 *            the sessionid of the current session of the user. Using the session id 
	 *            username can be known.
	 * @param paymentAccount
	 *            the payment account.
	 */
	public void updatePaymentAccount(String sessionId,
			PaymentAccount paymentAccount) {
		String username = (String) ApplicationLevelPlugin.getHashMap().get(
				sessionId);
		CreditCardPaymentAccount creditCardPaymentAccount = (CreditCardPaymentAccount) paymentAccount;

		StringBuffer query = new StringBuffer();
		query
				.append(
						"SELECT userfinancialid FROM g4guserfinancial WHERE email like '")
				.append(username).append("'");

		try {
			statement = connection.createStatement();
			resultSet = statement.executeQuery(query.toString());

			int userId = 0;
			while (resultSet.next()) {
				userId = resultSet.getInt(1);
			}

			if (userId != 0) {
				preparedStatement = connection
						.prepareStatement("UPDATE paymentaccounts SET name = ?, creditcardtype = ?, cardnumber = ?, expirymonth = ?, expiryyear = ?, firstname = ?, lastname = ?, street = ?, city = ?, state = ?, zip = ?, country = ?, issuenumber = ?, startmonth = ?, startyear = ? WHERE id = ? And userfinancialid = ?");

				preparedStatement.setString(1, creditCardPaymentAccount
						.getName());
				preparedStatement.setString(2, creditCardPaymentAccount
						.getType().name());
				preparedStatement.setString(3, creditCardPaymentAccount
						.getNumber());
				preparedStatement.setInt(4, creditCardPaymentAccount
						.getExpiryMonth());
				preparedStatement.setInt(5, creditCardPaymentAccount
						.getExpiryYear());
				preparedStatement.setString(6, creditCardPaymentAccount
						.getOwnerName());
				preparedStatement.setString(7, creditCardPaymentAccount
						.getOwnerSurname());
				preparedStatement.setString(8, creditCardPaymentAccount
						.getOwnerStreet());
				preparedStatement.setString(9, creditCardPaymentAccount
						.getOwnerCity());
				preparedStatement.setString(10, creditCardPaymentAccount
						.getOwnerState());
				preparedStatement.setString(11, creditCardPaymentAccount
						.getOwnerZip());
				preparedStatement.setString(12, creditCardPaymentAccount
						.getOwnerCountry().name());
				preparedStatement.setString(13, creditCardPaymentAccount
						.getIssueNumber());
				preparedStatement.setInt(14, creditCardPaymentAccount
						.getStartMonth());
				preparedStatement.setInt(15, creditCardPaymentAccount
						.getStartYear());
				preparedStatement.setLong(16, creditCardPaymentAccount
						.getAccountId());
				preparedStatement.setString(17, username);

				preparedStatement.executeUpdate();
				
			}
		} catch (SQLException sqlException) {
			AuditUtil.getInstance().writeLog(AuditUtil.FILE_TYPE_G4G_FINANCIAL, sqlException);
		} finally {
			try {
				if (resultSet != null) {
					resultSet.close();
				}
			} catch (SQLException sqlException) {
				AuditUtil.getInstance().writeLog(AuditUtil.FILE_TYPE_G4G_FINANCIAL, sqlException);
			}

			try {
				if (preparedStatement != null) {
					preparedStatement.close();
				}
			} catch (SQLException sqlException) {
				AuditUtil.getInstance().writeLog(AuditUtil.FILE_TYPE_G4G_FINANCIAL, sqlException);
			}

			try {
				if (statement != null) {
					statement.close();
				}
			} catch (SQLException sqlException) {
				AuditUtil.getInstance().writeLog(AuditUtil.FILE_TYPE_G4G_FINANCIAL, sqlException);
			}

			try {
				if (connection != null) {
					connection.close();
				}
			} catch (SQLException sqlException) {
				AuditUtil.getInstance().writeLog(AuditUtil.FILE_TYPE_G4G_FINANCIAL, sqlException);
			}
		}
	}
}
