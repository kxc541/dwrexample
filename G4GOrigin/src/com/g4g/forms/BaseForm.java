/**********************************************************
 * BaseForm.java : 
 *
 * Created by Ankur
 * Last modified Date: 18 Apr .08 by Punam
 * Revision: 0.1
 * Version : 0.0.8
 * Copyright (c) 2008 - 2008 Askdigi, All rights reserved.
 **********************************************************/
package com.g4g.forms;

import org.apache.struts.validator.ValidatorForm;

import com.g4g.dto.BaseDTO;

// TODO: Auto-generated Javadoc
/**
 * The Class BaseForm inherited by all other forms. It contains the common
 * attributes and methods for all other forms those have this attributes and
 * method in common.BaseForm contains id, error, operation. and common methods
 * used.
 * 
 * @author Ankur
 */
public abstract class BaseForm extends ValidatorForm {

	static final long serialVersionUID = 1L;

	/** The id. */
	protected String id;

	/** The error. */
	private String error;

	/** The operation. */
	private String operation = null;

	/** The display. */
	protected boolean display = false;
	
	/** The path. */
	private String path;
	
	/** The base path. */
	private String basePath;

	/**
	 * Used to populate dto from Form bean.
	 * 
	 * @return baseDTO
	 */
	public abstract BaseDTO getDTO();

	/**
	 * Gets the id.
	 * 
	 * @return id
	 */
	public String getId() {
		return id;
	}

	/**
	 * Gets the operation.
	 * 
	 * @return operation
	 */
	public String getOperation() {
		return operation;
	}

	/**
	 * Checks if is display.
	 * 
	 * @return display
	 */
	public boolean isDisplay() {
		return display;
	}

	/**
	 * Gets the error.
	 * 
	 * @return error
	 */
	public String getError() {
		return error;
	}

	/**
	 * Sets the error.
	 * 
	 * @param error
	 *            the error
	 */
	public void setError(String error) {
		this.error = error;
	}

	/**
	 * Used to populate Formbean from dto.
	 * 
	 * @param baseDto
	 *            the base dto
	 */
	public abstract void populate(BaseDTO baseDto);

	/**
	 * Sets the display.
	 * 
	 * @param display
	 *            the display
	 */
	public void setDisplay(boolean display) {
		this.display = display;
	}

	/**
	 * Sets the id.
	 * 
	 * @param id
	 *            the id
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * Sets the operation.
	 * 
	 * @param operation
	 *            the operation
	 */
	public void setOperation(String operation) {
		this.operation = operation;
	}

	/**
	 * Gets the path.
	 * 
	 * @return the path
	 */
	public String getPath() {
		return path;
	}

	/**
	 * Sets the path.
	 * 
	 * @param path the new path
	 */
	public void setPath(String path) {
		this.path = path;
	}

	/**
	 * Gets the base path.
	 * 
	 * @return the base path
	 */
	public String getBasePath() {
		return basePath;
	}

	/**
	 * Sets the base path.
	 * 
	 * @param basePath the new base path
	 */
	public void setBasePath(String basePath) {
		this.basePath = basePath;
	}

}