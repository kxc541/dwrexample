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
import com.impessa.worldgaming.client.Money;

// TODO: Auto-generated Javadoc
/**
 * The Class Bet to get bet details.
 */
public class Bet {

	/** The connection. */
	private Connection connection = null;

	/** The prepared statement. */
	private PreparedStatement preparedStatement = null;

	/** The statement. */
	private Statement statement = null;

	/** The result set. */
	private ResultSet resultSet = null;

	/**
	 * Instantiates a new bet.
	 * 
	 * @param url the url
	 * @param username the username
	 * @param password the password
	 */
	public Bet(String url, String username, String password) {
		try {
			this.connection = DriverManager.getConnection(url, username,
					password);
		} catch (SQLException e) {
			AuditUtil.getInstance().writeLog(AuditUtil.FILE_TYPE_G4G,
					this.getClass().getName() + G4GConstants.NONE);
		}
	}

	/**
	 * Bet.
	 * 
	 * @param sessionId the session id
	 * @param betId the bet id
	 * @param money the money
	 */
	public void bet(String sessionId, String betId, Money money) {
		String username = (String) ApplicationLevelPlugin.getHashMap().get(
				sessionId);
		Long bankBalance = 0L;

		try {
			statement = connection.createStatement();
			resultSet = statement
					.executeQuery("SELECT bankbalance FROM g4guserfinancial WHERE email='"
							+ username
							+ "' And bankbalance >= "
							+ money.getAmount() + "");

			if (resultSet.next()) {
				bankBalance = resultSet.getLong(1);

				preparedStatement = connection
						.prepareStatement("UPDATE g4guserfinancial SET bankbalance=? WHERE userfinancialid=?");

				preparedStatement.setLong(1, bankBalance - money.getAmount());
				preparedStatement.setString(2, username);

				preparedStatement = connection
						.prepareStatement("UPDATE bet SET amount=amount + ? WHERE id=?");

				preparedStatement.setLong(1, money.getAmount());
				preparedStatement.setString(2, betId);

				preparedStatement.executeUpdate();
			}
		} catch (SQLException sqlException) {
			AuditUtil.getInstance().writeLog(AuditUtil.FILE_TYPE_HIBERNATE, sqlException);
		} finally {
			try {
				if (resultSet != null) {
					resultSet.close();
				}
			} catch (SQLException sqlException) {
				AuditUtil.getInstance().writeLog(AuditUtil.FILE_TYPE_HIBERNATE, sqlException);
			}

			try {
				if (preparedStatement != null) {
					preparedStatement.close();
				}
			} catch (SQLException sqlException) {
				AuditUtil.getInstance().writeLog(AuditUtil.FILE_TYPE_HIBERNATE, sqlException);
			}

			try {
				if (statement != null) {
					statement.close();
				}
			} catch (SQLException sqlException) {
				AuditUtil.getInstance().writeLog(AuditUtil.FILE_TYPE_G4G, sqlException);
			}

			try {
				if (connection != null) {
					connection.close();
				}
			} catch (SQLException sqlException) {
				AuditUtil.getInstance().writeLog(AuditUtil.FILE_TYPE_G4G, sqlException);
			}
		}
	}
}
