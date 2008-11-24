/**********************************************************
 * Copyright (c) 2008 - 2008, Askdigi, All rights reserved.
 * 
 **********************************************************/
package com.g4g.dto;

/**
 * SitecontentDTO contains attribute of sitecontent entity.
 * 
 * @author Ankur
 */

public class SitecontentDTO extends BaseDTO {

	// Fields

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -6598322308358721906L;

	/** The siteid. */
	private Integer siteid;

	/** The contentid. */
	private Integer contentid;

	/** The content. */
	private String content;

	// Property accessors

	/**
	 * Gets the content.
	 * 
	 * @return the content
	 */
	public String getContent() {
		return this.content;
	}

	/**
	 * Gets the contentid.
	 * 
	 * @return the contentid
	 */
	public Integer getContentid() {
		return contentid;
	}

	/**
	 * Gets the siteid.
	 * 
	 * @return the siteid
	 */
	public Integer getSiteid() {
		return this.siteid;
	}

	/**
	 * Sets the content.
	 * 
	 * @param content
	 *            the new content
	 */
	public void setContent(String content) {
		this.content = content;
	}

	/**
	 * Sets the contentid.
	 * 
	 * @param contentid
	 *            the new contentid
	 */
	public void setContentid(Integer contentid) {
		this.contentid = contentid;
	}

	/**
	 * Sets the siteid.
	 * 
	 * @param siteid
	 *            the new siteid
	 */
	public void setSiteid(Integer siteid) {
		this.siteid = siteid;
	}

}