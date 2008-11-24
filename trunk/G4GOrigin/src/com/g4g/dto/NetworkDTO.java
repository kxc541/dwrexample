/**********************************************************
 * Copyright (c) 2008 - 2008, Askdigi, All rights reserved.
 * 
 **********************************************************/
package com.g4g.dto;

import java.util.HashSet;
import java.util.Set;

/**
 * NetworkDTO contains attribute for network entity.
 * 
 * @author Pratik
 */

public class NetworkDTO extends BaseDTO {

	// Fields
	

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -4849782728805893098L;

	/** The networkname. */
	private String networkname;
	
	/** The game. */
	private Set<GameDTO> game = new HashSet<GameDTO>(0);

	/**
	 * @param id 
	 * 
	 */
	public NetworkDTO(int id) {
		super();
		this.id = id;
	}

	/**
	 * 
	 */
	public NetworkDTO() {
		super();
	}

	/**
	 * @return the game
	 */
	public Set<GameDTO> getGame() {
		return this.game;
	}

	/**
	 * @param game
	 *            the game to set
	 */
	public void setGame(Set<GameDTO> game) {
		this.game = game;
	}

	/**
	 * Gets the networkname.
	 * 
	 * @return the networkname
	 */
	public String getNetworkname() {
		return networkname;
	}

	/**
	 * Sets the networkname.
	 * 
	 * @param networkname
	 *            the networkname to set
	 */
	public void setNetworkname(String networkname) {
		this.networkname = networkname;
	}

	// Property accessors

}