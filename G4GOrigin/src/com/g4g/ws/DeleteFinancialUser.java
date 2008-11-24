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

import com.g4g.utils.AuditUtil;

// TODO: Auto-generated Javadoc
/**
 * The Class Deletes Financial User.
 */
public class DeleteFinancialUser {
    // Database related attributes
    /** The connection. */
    private Connection connection = null;

    /** The prepared statement. */
    private PreparedStatement preparedStatement = null;

    /** The result set. */
    private ResultSet resultSet = null;

    /**
     * Instantiates a new delete financial user.
     * 
     * @param url the url
     * @param username the username
     * @param password the password
     */
    public DeleteFinancialUser(String url, String username, String password) {
	try {
	    this.connection = DriverManager.getConnection(url, username,
		    password);
	} catch (SQLException e) {
		AuditUtil.getInstance().writeLog(AuditUtil.FILE_TYPE_G4G_FINANCIAL, e);
	}
    }

    /**
     * Delete financial user.
     * 
     * @param userId the user id
     */
    public void deleteFinancialUser(String userId) {
	try {
	    preparedStatement = connection
		    .prepareStatement("DELETE FROM g4guserfinancial WHERE email like ?");
	    preparedStatement.setString(1, userId);

	    preparedStatement.executeUpdate();
	} catch (SQLException sqlException) {
		AuditUtil.getInstance().writeLog(AuditUtil.FILE_TYPE_G4G_FINANCIAL, sqlException);
	} finally {
	    try {
		if (resultSet != null) {
			resultSet.close();
		}
		if (preparedStatement != null) {
			preparedStatement.close();
		}
		if (connection != null) {
			connection.close();
		}
	    } catch (SQLException sqlException) {
	    	AuditUtil.getInstance().writeLog(AuditUtil.FILE_TYPE_G4G_FINANCIAL, sqlException);
	    }
	}
    }
}
