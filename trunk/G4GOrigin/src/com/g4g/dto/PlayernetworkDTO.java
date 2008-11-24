/**********************************************************
 * Copyright (c) 2008 - 2008, Askdigi, All rights reserved.
 * 
 **********************************************************/
package com.g4g.dto;

import java.util.Date;

// TODO: Auto-generated Javadoc
/**
 * Playernetwork contains attribute for playernetwork entity.
 * 
 * @author Ankur
 */

public class PlayernetworkDTO extends BaseDTO {

	// Fields

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 7974362566870764804L;

	/** The network. */
	private NetworkDTO network;
	
	/** The player. */
	private PlayerDTO player;
	
	/** The playernetworktag. */
	private String playernetworktag;
	
	/** The creationdate. */
	private Date creationdate;
	
	/** The lastupdatedate. */
	private Date lastupdatedate;
	
	/**
	 * Gets the network.
	 * 
	 * @return the network
	 */
	public NetworkDTO getNetwork() {
		return network;
	}
	
	/**
	 * Sets the network.
	 * 
	 * @param network the new network
	 */
	public void setNetwork(NetworkDTO network) {
		this.network = network;
	}
	
	/**
	 * Gets the player.
	 * 
	 * @return the player
	 */
	public PlayerDTO getPlayer() {
		return player;
	}
	
	/**
	 * Sets the player.
	 * 
	 * @param player the new player
	 */
	public void setPlayer(PlayerDTO player) {
		this.player = player;
	}
	
	/**
	 * Gets the playernetworktag.
	 * 
	 * @return the playernetworktag
	 */
	public String getPlayernetworktag() {
		return playernetworktag;
	}
	
	/**
	 * Sets the playernetworktag.
	 * 
	 * @param playernetworktag the new playernetworktag
	 */
	public void setPlayernetworktag(String playernetworktag) {
		this.playernetworktag = playernetworktag;
	}
	
	/**
	 * Gets the creationdate.
	 * 
	 * @return the creationdate
	 */
	public Date getCreationdate() {
		return creationdate;
	}
	
	/**
	 * Sets the creationdate.
	 * 
	 * @param creationdate the new creationdate
	 */
	public void setCreationdate(Date creationdate) {
		this.creationdate = creationdate;
	}
	
	/**
	 * Gets the lastupdatedate.
	 * 
	 * @return the lastupdatedate
	 */
	public Date getLastupdatedate() {
		return lastupdatedate;
	}
	
	/**
	 * Sets the lastupdatedate.
	 * 
	 * @param lastupdatedate the new lastupdatedate
	 */
	public void setLastupdatedate(Date lastupdatedate) {
		this.lastupdatedate = lastupdatedate;
	}

}