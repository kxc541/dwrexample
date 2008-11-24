/**********************************************************
 * Copyright (c) 2008 - 2008, Askdigi, All rights reserved.
 * 
 **********************************************************/
package com.g4g.dto;

import com.g4g.utils.G4GConstants;

/**
 * The Class TimeZoneDTO contains attribute of timezone entity.
 * 
 * @author Ankur
 */
public class TimeZoneDTO extends BaseDTO implements DtoList {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -7992032239471833282L;

	/** The timezone_location. */
	private String timezone_location;

	/** The gmt. */
	private String gmt;

	/** The offset. */
	private int offset;

	/**
	 * Gets the timezone_location.
	 * 
	 * @return the timezone_location
	 */
	public String getTimezone_location() {
		return timezone_location;
	}

	/**
	 * Sets the timezone_location.
	 * 
	 * @param timezone_location
	 *            the timezone_location to set
	 */
	public void setTimezone_location(String timezone_location) {
		this.timezone_location = timezone_location;
	}

	/**
	 * Gets the gmt.
	 * 
	 * @return the gmt
	 */
	public String getGmt() {
		return gmt;
	}

	/**
	 * Sets the gmt.
	 * 
	 * @param gmt
	 *            the gmt to set
	 */
	public void setGmt(String gmt) {
		this.gmt = gmt;
	}

	/**
	 * Gets the offset.
	 * 
	 * @return the offset
	 */
	public int getOffset() {
		return offset;
	}

	/**
	 * Sets the offset.
	 * 
	 * @param offset
	 *            the offset to set
	 */
	public void setOffset(int offset) {
		this.offset = offset;
	}

	/**
	 * Gets the description.
	 * 
	 * @return the description
	 * 
	 * @see com.g4g.dto.DtoList#getDescription()
	 */
	public String getDescription() {
		return new StringBuffer(gmt).append(G4GConstants.BLANK).append(timezone_location).toString();
	}

	/**
	 * Gets the key.
	 * 
	 * @return the key
	 * 
	 * @see com.g4g.dto.DtoList#getKey()
	 */
	public String getKey() {
		return String.valueOf(id);
	}

	/**
	 * Gets the value.
	 * 
	 * @return the value
	 * 
	 * @see com.g4g.dto.DtoList#getValue()
	 */
	public String getValue() {
		return timezone_location;
	}

}
