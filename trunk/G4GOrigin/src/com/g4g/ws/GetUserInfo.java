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
import com.impessa.worldgaming.client.InternalException_Exception;
import com.impessa.worldgaming.client.User;

// TODO: Auto-generated Javadoc
/**
 * The Class GetUserInfo gets the user info from financial web service.
 * 
 * @author ankur
 */
public class GetUserInfo {

	/** The connection. */
	private Connection connection = null;

	/** The prepared statement. */
	private PreparedStatement preparedStatement = null;

	/** The statement. */
	private Statement statement = null;

	/** The result set. */
	private ResultSet resultSet = null;

	/**
	 * Constructor which will initialize the local connection variable.
	 * 
	 * @param url the url
	 * @param username the username
	 * @param password the password
	 */
	public GetUserInfo(String url, String username, String password) {
		try {
			this.connection = DriverManager.getConnection(url, username,
					password);
		} catch (SQLException e) {
			AuditUtil.getInstance().writeLog(AuditUtil.FILE_TYPE_G4G_FINANCIAL, e);
		}
	}

	/**
	 * Gets the user info.
	 * 
	 * @param userName the user name
	 * 
	 * @return the user info
	 * 
	 * @throws InternalException_Exception the internal exception_ exception
	 */
	public User getUserInfo(String userName) throws InternalException_Exception {
		User user = new User();
		StringBuffer query = new StringBuffer();
		query.append("SELECT * FROM g4guserfinancial WHERE email like '")
				.append(userName).append("'");
		try {
			statement = connection.createStatement();
			resultSet = statement.executeQuery(query.toString());

			if (resultSet.next()) {
				user.setFirstname(resultSet.getString(G4GConstants.FIRSTNAME));
				user.setLastname(resultSet.getString(G4GConstants.LASTNAME));
				user.setAddress(resultSet.getString(G4GConstants.ADDRESS));
				user.setCity(resultSet.getString(G4GConstants.CITY));
				user.setCountry(resultSet.getString(G4GConstants.COUNTRY));
				user.setState(resultSet.getString(G4GConstants.STATE));
				user.setPostalcode(resultSet.getString(G4GConstants.POSTALCODE));
				user.setPhone(resultSet.getString(G4GConstants.PHONE));
				user.setBirthdate(Long.valueOf(resultSet.getString(G4GConstants.BIRTHDATE)));
				user.setGender(resultSet.getString(G4GConstants.GENDER));
				user.setSiteId(resultSet.getString(G4GConstants.SITEID_SMALL));
			}
            return user;
		} catch (Exception sqlException) {
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
