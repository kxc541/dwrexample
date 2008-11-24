/**********************************************************
 * Copyright (c) 2008 - 2008, Askdigi, All rights reserved.
 * 
 **********************************************************/
package com.g4g.ws;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;

import com.g4g.utils.AuditUtil;
import com.impessa.worldgaming.client.WithdrawalMethod;

/**
 * The Class GetWithdrawalMethods gives available withdrawal methods.
 * @author Jigar Mistry
 */
public class GetWithdrawalMethods {
    
    /** The connection. */
    @SuppressWarnings("unused")

  

    /**
     * The Constructor.
     * 
     * @param url the url
     * @param username the username
     * @param password the password
     */
    public GetWithdrawalMethods(String url, String username, String password) {
	try {
        Connection connection = DriverManager.getConnection(url, username, password);
	} catch (SQLException sqlException) {
	    AuditUtil.getInstance().writeLog(AuditUtil.FILE_TYPE_G4G,
		    this.getClass().getName() + sqlException.getMessage());
	}
    }

    /**
     * Gets the withdrawal methods.
     * 
     * @param sessionId the session id
     * 
     * @return the list of withdrawal methods
     */
    public List<WithdrawalMethod> getWithdrawalMethods(String sessionId) {
	List<WithdrawalMethod> withdrawalMethodList = new ArrayList<WithdrawalMethod>();

	WithdrawalMethod[] withdrawalMethod = WithdrawalMethod.values();

        withdrawalMethodList.addAll(Arrays.asList(withdrawalMethod));

	return withdrawalMethodList;
    }
}
