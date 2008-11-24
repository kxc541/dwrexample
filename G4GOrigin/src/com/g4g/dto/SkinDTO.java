/**********************************************************
 * Copyright (c) 2008 - 2008, Askdigi, All rights reserved.
 * 
 **********************************************************/
package com.g4g.dto;

// TODO: Auto-generated Javadoc
/**
 * SkinDTO contains attribute of skin entity.
 * 
 * @author Ankur
 */

public class SkinDTO extends BaseDTO {

	// Fields

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -5993643869551344324L;

	/** The skinid. */
	private String skinid;

	/**
	 * Instantiates a new skin dto.
	 */
	public SkinDTO() {
		super();
	}

	/**
	 * @param skinid
	 */
	public SkinDTO(String skinid) {
		super();
		this.skinid = skinid;
	}

	/**
	 * @return the skinid
	 */
	public String getSkinid() {
		return this.skinid;
	}

	/**
	 * @param skinid
	 *            the skinid to set
	 */
	public void setSkinid(String skinid) {
		this.skinid = skinid;
	}

}