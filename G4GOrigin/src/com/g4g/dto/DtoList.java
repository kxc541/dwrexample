/**********************************************************
* className.java : 
*
* Created by Ankur
* Last modified Date: 18 Apr .08 by Pratik
* Revision: 0.1
* Version : 0.0.8
* Copyright (c) 2008 - 2008 Askdigi, All rights reserved.
**********************************************************/
package com.g4g.dto;

/**
 * The Interface DtoList having methods for key value and description.
 * 
 * @author Ankur
 */
public interface DtoList {

	/**
	 * Gets the description.
	 * 
	 * @return String
	 */
	public String getDescription();

	/**
	 * Gets the key.
	 * 
	 * @return String
	 */
	public String getKey();

	/**
	 * Gets the value.
	 * 
	 * @return String
	 */
	public String getValue();
}
