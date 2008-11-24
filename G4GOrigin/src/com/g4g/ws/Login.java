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
import com.g4g.utils.DataUtil;
import com.impessa.worldgaming.client.AuthenticationException_Exception;
import com.impessa.worldgaming.client.InternalException_Exception;
import com.impessa.worldgaming.client.PlayerStatusException_Exception;

// TODO: Auto-generated Javadoc
/**
 * The Class Login for user to log-in.
 * @author Jigar Mistry
 */
public class Login {

	/** The connection. */
	private Connection connection = null;

	/** The prepared statement. */
	private PreparedStatement preparedStatement = null;

	/** The statement. */
	private Statement statement = null;

	/** The result set. */
	private ResultSet resultSet = null;

	/**
	 * The Constructor.
	 * 
	 * @param url the url
	 * @param username the username
	 * @param password the password
	 */
	public Login(String url, String username, String password) {
		try {
			this.connection = DriverManager.getConnection(url, username,
					password);
		} catch (SQLException e) {
			AuditUtil.getInstance().writeLog(AuditUtil.FILE_TYPE_G4G,
					this.getClass().getName() + "");
		}
	}

	/**
	 * Login.
	 * 
	 * @param username the username
	 * @param password the password
	 * @param siteId the site id
	 * @param ipAddress the ip address
	 * 
	 * @return the string
	 * 
	 * @throws AuthenticationException_Exception the authentication exception_ exception
	 * @throws InternalException_Exception the internal exception_ exception
	 * @throws PlayerStatusException_Exception the player status exception_ exception
	 */
	@SuppressWarnings("unchecked")
	public String login(String username, String password, String siteId,
			String ipAddress) throws AuthenticationException_Exception,
			InternalException_Exception, PlayerStatusException_Exception {
		StringBuffer query = new StringBuffer();
		query.append(
				"SELECT userfinancialid FROM g4guserfinancial WHERE email = '")
				.append(username).append("' And password like '").append(
						password).append("'");
		try {
			statement = connection.createStatement();
			resultSet = statement.executeQuery(query.toString());
            String sessionId = null;
			if (resultSet.next()) {
				sessionId = DataUtil.getRandomPassword(16);
				ApplicationLevelPlugin.getHashMap().put(sessionId, username);
			}
            return sessionId;
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
		return null;
	}
}
