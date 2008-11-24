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
import com.g4g.utils.DataUtil;

// TODO: Auto-generated Javadoc
/**
 * The Class Creates Bet.
 */
public class CreateBet {
    
    /** The connection. */
    private Connection connection;

    /** The prepared statement. */
    private PreparedStatement preparedStatement = null;

    /** The result set. */
    private ResultSet resultSet = null;

    /**
     * Instantiates a new creates the bet.
     * 
     * @param url the url
     * @param username the username
     * @param password the password
     */
    public CreateBet(String url, String username, String password) {
	try {
	    this.connection = DriverManager.getConnection(url, username,
		    password);
	} catch (SQLException e) {
		AuditUtil.getInstance().writeLog(AuditUtil.FILE_TYPE_G4G, e);
	}
    }

    /**
     * Creates the bet.
     * 
     * @return the string
     */
    public String createBet() {
	String betId = DataUtil.getRandomPassword(5);

	try {
	    preparedStatement = connection
		    .prepareStatement("INSERT INTO bet(id) VALUES(?)");
	    preparedStatement.setString(1, betId);
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

	return betId;
    }
}
