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
import com.impessa.worldgaming.client.CreditCardPaymentAccount;
import com.impessa.worldgaming.client.PaymentAccount;

// TODO: Auto-generated Javadoc
/**
 * The Class RegisterPaymentAccount registers new payment account.
 * @author Jigar Mistry
 */
public class RegisterPaymentAccount {

	/** The connection. */
	private Connection connection = null;

	/** The prepared statement. */
	private PreparedStatement preparedStatement = null;

	/** The statement. */
	private Statement statement = null;

	/** The result set. */
	private ResultSet resultSet = null;

	/**
	 * Instantiates a new register payment account.
	 * 
	 * @param url the url
	 * @param username the username
	 * @param password the password
	 */
	public RegisterPaymentAccount(String url, String username, String password) {
		try {
			this.connection = DriverManager.getConnection(url, username,
					password);
		} catch (SQLException sqlException) {
			AuditUtil.getInstance().writeLog(AuditUtil.FILE_TYPE_G4G,
					this.getClass().getName() + sqlException.getMessage());
		}
	}

	/**
	 * Register payment account.
	 * 
	 * @param sessionId the session id
	 * @param paymentAccount the payment account
	 */
	public void registerPaymentAccount(String sessionId,
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
						.prepareStatement("INSERT INTO paymentaccounts(id, name, userfinancialid, creditcardtype, cardnumber, expirymonth, expiryyear, firstname, lastname, street, city, state, zip, country, issuenumber, startmonth, startyear) VALUES ((select max(id) from paymentaccounts) + 1, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");

				preparedStatement.setString(1, creditCardPaymentAccount
						.getName());
				preparedStatement.setString(2, username);
				preparedStatement.setString(3, creditCardPaymentAccount
						.getType().name());
				preparedStatement.setString(4, creditCardPaymentAccount
						.getNumber());
				preparedStatement.setInt(5, creditCardPaymentAccount
						.getExpiryMonth());
				preparedStatement.setInt(6, creditCardPaymentAccount
						.getExpiryYear());
				preparedStatement.setString(7, creditCardPaymentAccount
						.getOwnerName());
				preparedStatement.setString(8, creditCardPaymentAccount
						.getOwnerSurname());
				preparedStatement.setString(9, creditCardPaymentAccount
						.getOwnerStreet());
				preparedStatement.setString(10, creditCardPaymentAccount
						.getOwnerCity());
				preparedStatement.setString(11, creditCardPaymentAccount
						.getOwnerState());
				preparedStatement.setString(12, creditCardPaymentAccount
						.getOwnerZip());
				preparedStatement.setString(13, creditCardPaymentAccount
						.getOwnerCountry().name());
				preparedStatement.setString(14, creditCardPaymentAccount
						.getIssueNumber());
				preparedStatement.setInt(15, creditCardPaymentAccount
						.getStartMonth());
				preparedStatement.setInt(16, creditCardPaymentAccount
						.getStartYear());

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
