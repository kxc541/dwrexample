/**********************************************************
 * Copyright (c) 2008 - 2008, Askdigi, All rights reserved.
 * 
 **********************************************************/
package com.g4g.dto;

import java.util.HashMap;
import java.util.Set;

/**
 * The Class SearchCriteria Utility for building criteria in hibernate.
 * 
 * @author Ankur
 */

public class SearchCriteria {

	/** The attributes. */
	private HashMap<String, Object> attributes = new HashMap<String, Object>();

	/** The order by. */
	private String orderBy = null;

	/** The asc. */
	private boolean asc;

	/**
	 * Gets the attribute.
	 * 
	 * @param key
	 *            the key
	 * 
	 * @return Object
	 */
	public Object getAttribute(String key) {
		return attributes.get(key);
	}

	/**
	 * Gets the attribute names.
	 * 
	 * @return Set<String>
	 */
	public Set<String> getAttributeNames() {
		return attributes.keySet();
	}

	/**
	 * Gets the order by.
	 * 
	 * @return the orderBy
	 */
	public String getOrderBy() {
		return orderBy;
	}

	/**
	 * Checks if is asc.
	 * 
	 * @return the asc
	 */
	public boolean isAsc() {
		return asc;
	}

	/**
	 * removes all atributes from search criteria.
	 */
	public void removeAllAttribute() {
		attributes.clear();
	}

	/**
	 * Removes the attribute.
	 * 
	 * @param key
	 *            the key
	 * 
	 * @return Object
	 */
	public Object removeAttribute(String key) {
		return attributes.remove(key);
	}

	/**
	 * Sets the asc.
	 * 
	 * @param asc
	 *            the asc to set
	 */
	public void setAsc(boolean asc) {
		this.asc = asc;
	}

	/**
	 * Sets the attribute.
	 * 
	 * @param key
	 *            the key
	 * @param value
	 *            the value
	 */
	public void setAttribute(String key, Object value) {
		attributes.put(key, value);
	}

	/**
	 * Sets the order by.
	 * 
	 * @param orderBy
	 *            the orderBy to set
	 */
	public void setOrderBy(String orderBy) {
		this.orderBy = orderBy;
	}

}
