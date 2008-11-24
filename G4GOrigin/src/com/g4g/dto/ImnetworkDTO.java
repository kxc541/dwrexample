/**********************************************************
 * Copyright (c) 2008 - 2008, Askdigi, All rights reserved.
 * 
 **********************************************************/
package com.g4g.dto;

// TODO: Auto-generated Javadoc
/**
 * ImnetworkDTO contains attribute for imnetwork entity.
 * 
 * @author Ankur
 */

public class ImnetworkDTO extends BaseDTO {

	// Fields

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1803521831735422067L;

	
	/** The imnetworkname. */
	private String imnetworkname;

	// Property accessors

	/**
	 * Gets the imnetworkname.
	 * 
	 * @return imnetworkname
	 */
	public String getImnetworkname() {
		return this.imnetworkname;
	}

	/**
	 * Sets the imnetworkname.
	 * 
	 * @param imnetworkname
	 *            the imnetworkname
	 */
	public void setImnetworkname(String imnetworkname) {
		this.imnetworkname = imnetworkname;
	}

	/**
	 * 
	 */
	public ImnetworkDTO() {
		super();
	}

	/**
	 * Instantiates a new imnetwork dto.
	 * 
	 * @param id the id
	 */
	public ImnetworkDTO(int id) {
		super();
		this.id = id;
	}
}