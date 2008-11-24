/**********************************************************
* AdminDTO.java : 
*
* Created by Pratik
* Last modified Date: 18 Apr .08 by Pratik
* Revision: 0.1
* Version : 0.0.8
* Copyright (c) 2008 - 2008 Askdigi, All rights reserved.
**********************************************************/
package com.g4g.dto;

/**
 * AdminDTO is the DTO class for Admin table in database. It contains adminemail,
 * maxpicsize etc attributes. Admin table contains information provided by admin
 * of G4G to restrict user.
 * 
 * @author Pratik
 * @see BaseDTO
 * @hibernate.mapping AdminDTO.hbm.xml
 */
public class AdminDTO extends BaseDTO {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -7992032239471833282L;

	/** The adminemail. This attribute contains admin's email address. */
	private String adminemail;

	/** The maxpicsize. This attribute contains max no. of pics one user 
	 * can upload. */
	private int maxpicsize;

	/** The maxpics. This attribute contains max no. of pics one user 
	 * can upload. */
	private int maxpics;

	/**
	 * Gets the adminemail.
	 * 
	 * @return the adminemail
	 */
	public String getAdminemail() {
		return adminemail;
	}

	/**
	 * Gets the maxpics.
	 * 
	 * @return the maxpics
	 */
	public int getMaxpics() {
		return maxpics;
	}

	/**
	 * Gets the maxpicsize.
	 * 
	 * @return the maxpicsize
	 */
	public int getMaxpicsize() {
		return maxpicsize;
	}

	/**
	 * Sets the adminemail.
	 * 
	 * @param adminemail
	 *            the new adminemail
	 */
	public void setAdminemail(String adminemail) {
		this.adminemail = adminemail;
	}

	/**
	 * Sets the maxpics.
	 * 
	 * @param maxpics
	 *            the new maxpics
	 */
	public void setMaxpics(int maxpics) {
		this.maxpics = maxpics;
	}

	/**
	 * Sets the maxpicsize.
	 * 
	 * @param maxpicsize
	 *            the new maxpicsize
	 */
	public void setMaxpicsize(int maxpicsize) {
		this.maxpicsize = maxpicsize;
	}

}
