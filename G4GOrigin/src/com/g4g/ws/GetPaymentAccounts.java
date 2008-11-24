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
import java.util.ArrayList;
import java.util.List;

import com.g4g.utils.AuditUtil;
import com.impessa.worldgaming.client.CreditCardPaymentAccount;
import com.impessa.worldgaming.client.CreditCardType;
import com.impessa.worldgaming.client.PaymentAccount;

// TODO: Auto-generated Javadoc
/**
 * The Class GetPaymentAccounts payment accounts of user.
 */
public class GetPaymentAccounts {
    
    /** The connection. */
    private Connection connection;

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
    public GetPaymentAccounts(String url, String username, String password) {
	try {
	    this.connection = DriverManager.getConnection(url, username,
		    password);
	} catch (SQLException sqlException) {
		AuditUtil.getInstance().writeLog(AuditUtil.FILE_TYPE_G4G_FINANCIAL, sqlException);
	}
    }

    /**
     * Gets the payment accounts.
     * 
     * @param sessionId the session id
     * @param detailsFlag the details flag
     * 
     * @return the payment accounts
     */
    public List<PaymentAccount> getPaymentAccounts(String sessionId,
	    boolean detailsFlag) {
	List<PaymentAccount> paymentAccountList = new ArrayList<PaymentAccount>();

	try {
	    String username = (String) ApplicationLevelPlugin.getHashMap().get(
		    sessionId);

	    statement = connection.createStatement();
	    resultSet = statement
		    .executeQuery("SELECT id, name, creditcardtype, cardnumber, expirymonth, expiryyear, firstname, lastname, street, city, state, zip, country, issuenumber, startmonth, startyear FROM paymentaccounts WHERE userfinancialid='"
			    + username + "'");

	    while (resultSet.next()) {
		CreditCardPaymentAccount paymentAccounts = new CreditCardPaymentAccount();
		paymentAccounts.setAccountId(resultSet.getLong(1));
		paymentAccounts.setName(resultSet.getString(2));
		if (resultSet.getString(3) != null) {
			paymentAccounts.setType(CreditCardType.valueOf(resultSet
			    .getString(3)));
		}

		paymentAccounts.setNumber(resultSet.getString(4));
		paymentAccounts.setExpiryMonth(resultSet.getInt(5));
		paymentAccounts.setExpiryYear(resultSet.getInt(6));
		paymentAccounts.setOwnerName(resultSet.getString(7));
		paymentAccounts.setOwnerStreet(resultSet.getString(9));
		paymentAccounts.setOwnerCity(resultSet.getString(10));
		paymentAccounts.setOwnerState(resultSet.getString(11));
		paymentAccounts.setOwnerZip(resultSet.getString(12));
		// paymentAccounts.setOwnerCountry(CountryType.fromValue(resultSet.getString(13)));
		paymentAccounts.setIssueNumber(resultSet.getString(14));
		paymentAccounts.setStartMonth(resultSet.getInt(15));
		paymentAccounts.setStartYear(resultSet.getInt(16));

		paymentAccountList.add(paymentAccounts);
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

	return paymentAccountList;
    }
}
