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
import com.impessa.worldgaming.client.InternalException_Exception;
import com.impessa.worldgaming.client.SiteNotFoundException_Exception;
import com.impessa.worldgaming.client.User;
import com.impessa.worldgaming.client.UserValidationException;
import com.impessa.worldgaming.client.UserValidationException_Exception;

// TODO: Auto-generated Javadoc
/**
 * The Class RegisterUser.
 * @author Jigar Mistry
 */
public class RegisterUser {

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
	public RegisterUser(String url, String username, String password) {
		try {
			this.connection = DriverManager.getConnection(url, username,
					password);
		} catch (SQLException sqlException) {
			AuditUtil.getInstance().writeLog(AuditUtil.FILE_TYPE_G4G,
					this.getClass().getName() + sqlException.getMessage());
			AuditUtil.getInstance().writeLog(AuditUtil.FILE_TYPE_G4G_FINANCIAL, sqlException);
		}
	}

	/**
	 * Register user.
	 * 
	 * @param user the user
	 * 
	 * @throws InternalException_Exception the internal exception_ exception
	 * @throws SiteNotFoundException_Exception the site not found exception_ exception
	 * @throws UserValidationException_Exception the user validation exception_ exception
	 */
	public void registerUser(User user) throws InternalException_Exception,
			SiteNotFoundException_Exception, UserValidationException_Exception {
		StringBuffer query = new StringBuffer();
		query
				.append(
						"SELECT userfinancialid FROM g4guserfinancial WHERE email like '")
				.append(user.getEmail()).append("'");

		try {
			statement = connection.createStatement();
			resultSet = statement.executeQuery(query.toString());

			if (resultSet.next()) {
				UserValidationException userValidationException = new UserValidationException();
				userValidationException
						.setMessage("User is already registered.");
				throw new UserValidationException_Exception(
						"User is already registered.", userValidationException);
			}

			statement = connection.createStatement();
			resultSet = statement
					.executeQuery("SELECT Max(userfinancialid) FROM g4guserfinancial");

			int id = 0;
			while (resultSet.next()) {
				id = resultSet.getInt(1);
			}

			preparedStatement = connection
					.prepareStatement("INSERT INTO g4guserfinancial(email, firstname, lastname, address, city, country, state, postalcode, password, phone, birthdate, gender, siteid, ipaddress, userfinancialid) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");

			preparedStatement.setString(1, user.getEmail());
			preparedStatement.setString(2, user.getFirstname());
			preparedStatement.setString(3, user.getLastname());
			preparedStatement.setString(4, user.getAddress());
			preparedStatement.setString(5, user.getCity());
			preparedStatement.setString(6, user.getCountry());
			preparedStatement.setString(7, user.getState());
			preparedStatement.setString(8, user.getPostalcode());
			preparedStatement.setString(9, user.getPassword());
			preparedStatement.setString(10, user.getPhone());
			preparedStatement.setLong(11, user.getBirthdate());
			preparedStatement.setString(12, user.getGender());
			preparedStatement.setString(13, user.getSiteId());
			preparedStatement.setString(14, user.getIpAddress());
			preparedStatement.setInt(15, id + 1);

			preparedStatement.executeUpdate();
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
