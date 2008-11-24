/**********************************************************
 * Copyright (c) 2008 - 2008, Askdigi, All rights reserved.
 * 
 **********************************************************/
package com.g4g.ws;

/**
 * 
 * 
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.g4g.utils.AuditUtil;

// TODO: Auto-generated Javadoc
/**
 * The Class ChangePassword.
 * @author Jigar Mistry
 */
public class ChangePassword {

	/** The connection. */
	private Connection connection = null;

	/** The prepared statement. */
	private PreparedStatement preparedStatement = null;

	/** The statement. */
	private Statement statement = null;

	/** The result set. */
	private ResultSet resultSet = null;

	/**
	 * Constructor to create the connection with given parameter.
	 * 
	 * @param url the url
	 * @param username the username
	 * @param password the password
	 */
	public ChangePassword(String url, String username, String password) {
		try {
			this.connection = DriverManager.getConnection(url, username,
					password);
		} catch (SQLException e) {
			AuditUtil.getInstance().writeLog(AuditUtil.FILE_TYPE_G4G_FINANCIAL, e);
		}
	}

	/**
	 * Change password.
	 * 
	 * @param sessionId the session id
	 * @param oldPassword the old password
	 * @param newPassword the new password
	 */
	public void changePassword(String sessionId, String oldPassword,
			String newPassword) {
		String username = (String) ApplicationLevelPlugin.getHashMap().get(
				sessionId);
		StringBuffer query = new StringBuffer();

		query
				.append(
						"SELECT userfinancialid FROM g4guserfinancial WHERE email like '")
				.append(username).append("' AND password like '").append(
						oldPassword).append("'");

		try {
			statement = connection.createStatement();
			resultSet = statement.executeQuery(query.toString());

			query = new StringBuffer();
			query.append("UPDATE g4guserfinancial SET password = '").append(
					newPassword).append("' WHERE email like '")
					.append(username).append("'");
			while (resultSet.next()) {
				preparedStatement = connection.prepareStatement(query
						.toString());
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
