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

// TODO: Auto-generated Javadoc
/**
 * The Class ResetPassword.
 * @author Jigar Mistry
 */
public class ResetPassword {
    
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
    public ResetPassword(String url, String username, String password) {
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
     * Reset password.
     * 
     * @param username the username
     * 
     * @return the string
     */
    public String resetPassword(String username) {
	String newPassword = DataUtil.getRandomPassword(6);

	try {
	    statement = connection.createStatement();
	    resultSet = statement
		    .executeQuery("SELECT userfinancialid FROM g4guserfinancial WHERE email like '"
			    + username + "'");

	    while (resultSet.next()) {
		int id = resultSet.getInt(1);

		preparedStatement = connection
			.prepareStatement("UPDATE g4guserfinancial SET password = ? WHERE userfinancialid = ?");

		preparedStatement.setString(1, newPassword);
		preparedStatement.setInt(2, id);

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

	return newPassword;
    }
}
