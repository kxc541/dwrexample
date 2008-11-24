/**********************************************************
 * Copyright (c) 2008 - 2008, Askdigi, All rights reserved.
 * 
 **********************************************************/
package com.g4g.dto;

import java.util.HashMap;
import java.util.Set;

/**
 * The Class SearchListCriteria Utility for building Listcriteria in hibernate.
 * 
 * @author Jigar Mistry
 */
public class SearchListCriteria {

	/** The Constant EQ. */
	public static final String EQ = "eq";

	/** The Constant GE. */
	public static final String GE = "ge";

	/** The Constant GT. */
	public static final String GT = "gt";

	/** The Constant ILIKE. */
	public static final String ILIKE = "ilike";

	/** The Constant ISNULL. */
	public static final String ISNULL = "isNull";

	/** The Constant ISNOTNULL. */
	public static final String ISNOTNULL = "isNotNull";

	/** The Constant LE. */
	public static final String LE = "le";

	/** The Constant LIKE. */
	public static final String LIKE = "like";

	/** The Constant LT. */
	public static final String LT = "lt";

	/** The Constant NE. */
	public static final String NE = "ne";

	/** The Constant IN. */
	public static final String IN = "in";

	/** The attributes. */
	private HashMap<String, Object[]> attributes = new HashMap<String, Object[]>();

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
	 * @return the attribute
	 */
	public Object[] getAttribute(String key) {
		return attributes.get(key);
	}

	/**
	 * Gets the attribute names.
	 * 
	 * @return the attribute names
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
	 * Removes the all attribute.
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
	 * @return the object
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
	public void setAttribute(String key, Object value[]) {
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
