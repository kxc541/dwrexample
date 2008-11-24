/**********************************************************
* BaseDTO.java : 
*
* Created by Ankur
* Last modified Date: 18 Apr .08 by Pratik
* Revision: 0.1
* Version : 0.0.8
* Copyright (c) 2008 - 2008 Askdigi, All rights reserved.
**********************************************************/
package com.g4g.dto;

import java.io.Serializable;

/**
 * The Class BaseDTO inherited by all the DTOs which contain entity ralation.
 * The Class BaseDTO, is the base class all DTO classes. It contains attribute 
 * named id. So, every class has id as their basic attribute.
 * 
 * @author Ankur
 */
public class BaseDTO implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 3997253853882245429L;

	/** The id. */
	protected int id;

	/**
	 * Gets the id.
	 * 
	 * @return id
	 */
	public int getId() {
		return id;
	}

	/**
	 * Sets the id.
	 * 
	 * @param id
	 *            the id
	 */
	public void setId(int id) {
		this.id = id;
	}

	

}
