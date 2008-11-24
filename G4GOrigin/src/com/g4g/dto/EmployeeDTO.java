/**********************************************************
* EmployeeDTO.java : 
*
* Created by Punam
* Last modified Date: 18 Apr .08 by Pratik
* Revision: 0.1
* Version : 0.0.8
* Copyright (c) 2008 - 2008 Askdigi, All rights reserved.
**********************************************************/
package com.g4g.dto;

/**
 * EmployeeDTO is the DTO class for Employee table in database. It contains loginname,
 * password etc. attributes. Employee table contains information related with 
 * admin panel of site.  
 *  
 * @author Punam
 */

public class EmployeeDTO extends BaseDTO {

	// Fields

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 6124917104296151175L;

	/** The roleid. */
	private Integer roleid;

	/** The loginname. */
	private String loginname;

	/** The passwordhash. */
	private String passwordhash;

	/** The passwordhashsalt. */
	private String passwordhashsalt;

	/**
	 * Gets the loginname.
	 * 
	 * @return the loginname
	 */
	public String getLoginname() {
		return this.loginname;
	}

	/**
	 * Gets the passwordhash.
	 * 
	 * @return the passwordhash
	 */
	public String getPasswordhash() {
		return this.passwordhash;
	}

	/**
	 * Gets the passwordhashsalt.
	 * 
	 * @return the passwordhashsalt
	 */
	public String getPasswordhashsalt() {
		return this.passwordhashsalt;
	}

	/**
	 * Gets the roleid.
	 * 
	 * @return the roleid
	 */
	public Integer getRoleid() {
		return this.roleid;
	}

	/**
	 * Sets the loginname.
	 * 
	 * @param loginname
	 *            the new loginname
	 */
	public void setLoginname(String loginname) {
		this.loginname = loginname;
	}

	/**
	 * Sets the passwordhash.
	 * 
	 * @param passwordhash
	 *            the new passwordhash
	 */
	public void setPasswordhash(String passwordhash) {
		this.passwordhash = passwordhash;
	}

	/**
	 * Sets the passwordhashsalt.
	 * 
	 * @param passwordhashsalt
	 *            the new passwordhashsalt
	 */
	public void setPasswordhashsalt(String passwordhashsalt) {
		this.passwordhashsalt = passwordhashsalt;
	}

	/**
	 * Sets the roleid.
	 * 
	 * @param roleid
	 *            the new roleid
	 */
	public void setRoleid(Integer roleid) {
		this.roleid = roleid;
	}

}