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
 * The Class MoveToHouseAccount moves the funds to house account.
 * @author Jigar Mistry
 */
public class MoveToHouseAccount {

	/** The connection. */
	private Connection connection = null;

	/** The prepared statement. */
	private PreparedStatement preparedStatement = null;

	/** The statement. */
	private Statement statement = null;

	/** The result set. */
	private ResultSet resultSet = null;

	/**
	 * Instantiates a new move to house account.
	 * 
	 * @param url the url
	 * @param username the username
	 * @param password the password
	 */
	public MoveToHouseAccount(String url, String username, String password) {
		try {
			this.connection = DriverManager.getConnection(url, username,
					password);
		} catch (SQLException e) {
			AuditUtil.getInstance().writeLog(AuditUtil.FILE_TYPE_G4G,
					this.getClass().getName() + G4GConstants.NONE);
		}
	}

	/**
	 * Move to house account.
	 * 
	 * @param betId the bet id
	 * @param siteId the site id
	 * @param money the money
	 */
	public void moveToHouseAccount(String betId, String siteId, Money money) {
		try {
			statement = connection.createStatement();
			resultSet = statement
					.executeQuery("SELECT amount FROM bet WHERE id='" + betId
							+ "'");

			if (resultSet.next()) {
				preparedStatement = connection
						.prepareStatement("UPDATE bet SET amount=amount - ? WHERE id=?");

				preparedStatement.setLong(1, money.getAmount());
				preparedStatement.setString(2, betId);

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
