/**********************************************************
 * GameoptionsDTO.java : 
 *
 * Created by Pratik
 * Last modified Date: 21 Apr .08 by Pratik
 * Revision: 0.1
 * Version : 0.0.8
 * Copyright (c) 2008 - 2008 Askdigi, All rights reserved.
 **********************************************************/
package com.g4g.dto;

/**
 * GameoptionDTO is the DTO class for GameOption table in database. It contains
 * option, value etc. attributes. GameOption table contains information about
 * different options for different games.
 * 
 * @author Pratik
 */

public class GameoptionsDTO extends BaseDTO {

	// Fields

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 4432295894599209139L;

	/** The valueid. */
	private Integer valueid;

	/** The gameid. */
	private Integer gameid;

	/** The option. */
	private String option;

	/** The value. */
	private String value;

	/** The optionsequenceid. */
	private Integer optionsequenceid;

	/** The valuesequenceid. */
	private Integer valuesequenceid;

	/**
	 * Gets the valueid.
	 * 
	 * @return the valueid
	 */
	public Integer getValueid() {
		return valueid;
	}

	/**
	 * Sets the valueid.
	 * 
	 * @param valueid
	 *            the valueid to set
	 */
	public void setValueid(Integer valueid) {
		this.valueid = valueid;
	}

	/**
	 * Gets the gameid.
	 * 
	 * @return the gameid
	 */
	public Integer getGameid() {
		return gameid;
	}

	/**
	 * Sets the gameid.
	 * 
	 * @param gameid
	 *            the gameid to set
	 */
	public void setGameid(Integer gameid) {
		this.gameid = gameid;
	}

	/**
	 * Gets the option.
	 * 
	 * @return the option
	 */
	public String getOption() {
		return option;
	}

	/**
	 * Sets the option.
	 * 
	 * @param option
	 *            the option to set
	 */
	public void setOption(String option) {
		this.option = option;
	}

	/**
	 * Gets the value.
	 * 
	 * @return the value
	 */
	public String getValue() {
		return value;
	}

	/**
	 * Sets the value.
	 * 
	 * @param value
	 *            the value to set
	 */
	public void setValue(String value) {
		this.value = value;
	}

	/**
	 * Gets the optionsequenceid.
	 * 
	 * @return the optionsequenceid
	 */
	public Integer getOptionsequenceid() {
		return optionsequenceid;
	}

	/**
	 * Sets the optionsequenceid.
	 * 
	 * @param optionsequenceid
	 *            the optionsequenceid to set
	 */
	public void setOptionsequenceid(Integer optionsequenceid) {
		this.optionsequenceid = optionsequenceid;
	}

	/**
	 * Gets the valuesequenceid.
	 * 
	 * @return the valuesequenceid
	 */
	public Integer getValuesequenceid() {
		return valuesequenceid;
	}

	/**
	 * Sets the valuesequenceid.
	 * 
	 * @param valuesequenceid
	 *            the valuesequenceid to set
	 */
	public void setValuesequenceid(Integer valuesequenceid) {
		this.valuesequenceid = valuesequenceid;
	}

	/**
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof GameoptionsDTO)) {
			return false;
		}
		super.equals(obj);
		GameoptionsDTO gameoptionsDTO = (GameoptionsDTO) obj;
		if (this.id == gameoptionsDTO.id
				&& this.gameid.equals(gameoptionsDTO.gameid)
				&& this.valueid.equals( gameoptionsDTO.valueid )) {
			return true;
		}
		return false;
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		return super.hashCode();
	}

	// Property accessors

}