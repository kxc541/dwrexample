/**********************************************************
 * Copyright (c) 2008 - 2008, Askdigi, All rights reserved.
 * 
 **********************************************************/
package com.g4g.dto;

import static com.g4g.utils.G4GConstants.*;

import com.g4g.utils.G4GConstants;

/**
 * NationalCodeDTO contains attribute for nationalcode entity.
 * 
 * @author Pratik
 * @author Punam
 */

public class NationalCodeDTO extends BaseDTO implements DtoList {

	

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1369164923212503393L;

	/** The islegal. */
	private String islegal;

	/** The nationcode. */
	private String nationcode;

	/** The nationname. */
	private String nationname;

	/**
	 * Gets the nationcode.
	 * 
	 * @return the nationcode
	 */
	public String getNationcode() {
		return nationcode;
	}

	/**
	 * Sets the nationcode.
	 * 
	 * @param nationcode
	 *            the nationcode to set
	 */
	public void setNationcode(String nationcode) {
		this.nationcode = nationcode;
	}

	/**
	 * Gets the nationname.
	 * 
	 * @return the nationname
	 */
	public String getNationname() {
		return nationname;
	}

	/**
	 * Sets the nationname.
	 * 
	 * @param nationname
	 *            the nationname to set
	 */
	public void setNationname(String nationname) {
		this.nationname = nationname;
	}

	/**
	 * Sets the islegal.
	 * 
	 * @param islegal
	 *            the islegal to set
	 */
	public void setIslegal(String islegal) {
		this.islegal = islegal;
	}

	/**
	 * Gets the description.
	 * 
	 * @return nationname + nationcode
	 * 
	 * @see com.g4g.dto.DtoList#getDescription()
	 */
	public String getDescription() {
		return nationname + nationcode;
	}

	/**
	 * Gets the value.
	 * 
	 * @return nationname + G4GConstants.BLANK + "("+nationcode + ")";
	 * 
	 * @see com.g4g.dto.DtoList#getValue()
	 */
	public String getValue() {
		return new StringBuffer(nationname).append(G4GConstants.BLANK).append(LEFTBRACKET).append(nationcode).append(RIGHTBRACKET).toString();
	}

	/**
	 * Gets the islegal.
	 * 
	 * @return islegal
	 */
	public String getIslegal() {
		return islegal;
	}

	/**
	 * Gets the key.
	 * 
	 * @return id
	 * 
	 * @see com.g4g.dto.DtoList#getKey()
	 */
	public String getKey() {
		return String.valueOf(id);
	}

}