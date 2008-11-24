/**********************************************************
 * FriendsDTO.java : 
 *
 * Created by Jigar Mistry
 * Last modified Date: 18 Apr .08 by Pratik
 * Revision: 0.1
 * Version : 0.0.8
 * Copyright (c) 2008 - 2008 Askdigi, All rights reserved.
 **********************************************************/
package com.g4g.dto;

import java.util.Date;

import com.g4g.delegator.PlayerServiceDelegator;
import com.g4g.utils.G4GConstants;

// TODO: Auto-generated Javadoc
/**
 * FriendsDTO is the DTO class for Friends table in database. It contains
 * player1id, player2id etc. attributes. Friends table contains information
 * about the players who are friends of each other.
 * 
 * @author Jigar Mistry
 * @see BaseDTO
 * @see DtoList
 * @hibernate.mapping FriendsDTO.hbm.xml
 */

public class FriendsDTO extends BaseDTO implements DtoList {

	// Fields

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -6527467263155127894L;

	/** The player by playerid2. */
	private PlayerDTO playerByPlayerid2;

	/** The player by playerid1. */
	private PlayerDTO playerByPlayerid1;

	/** The player1accepted. */
	private Date player1accepted;

	/** The player2accepted. */
	private Date player2accepted;

	/**
	 * default constructor.
	 */
	public FriendsDTO() {
	}

	// Property accessors

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	/**
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object other) {
		if ((this.equals(other))) {
			return true;
		}
		if ((other == null)) {
			return false;
		}
		if (!(other instanceof FriendsDTO)) {
			return false;
		}
		FriendsDTO castOther = (FriendsDTO) other;

		return ((this.getPlayerid1().equals(castOther.getPlayerid1())) || ((this
				.getPlayerid1() != null)
				&& (castOther.getPlayerid1() != null) && this.getPlayerid1()
				.equals(castOther.getPlayerid1())))
				&& ((this.getPlayerid2().equals(castOther.getPlayerid2())) || ((this
						.getPlayerid2() != null)
						&& (castOther.getPlayerid2() != null) && this
						.getPlayerid2().equals(castOther.getPlayerid2())))
				&& ((this.getPlayer1accepted().equals(castOther
						.getPlayer1accepted())) || ((this.getPlayer1accepted() != null)
						&& (castOther.getPlayer1accepted() != null) && this
						.getPlayer1accepted().equals(
								castOther.getPlayer1accepted())))
				&& ((this.getPlayer2accepted().equals(castOther
						.getPlayer2accepted())) || ((this.getPlayer2accepted() != null)
						&& (castOther.getPlayer2accepted() != null) && this
						.getPlayer2accepted().equals(
								castOther.getPlayer2accepted())));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.g4g.dto.DtoList#getDescription()
	 */
	/**
	 * @see com.g4g.dto.DtoList#getDescription()
	 */
	public String getDescription() {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.g4g.dto.DtoList#getKey()
	 */
	/**
	 * @see com.g4g.dto.DtoList#getKey()
	 */
	public String getKey() {
		// TODO Auto-generated method stub
		return String.valueOf(playerByPlayerid2.getId());
	}

	/**
	 * Gets the player1accepted.
	 * 
	 * @return the player1accepted
	 */
	public Date getPlayer1accepted() {
		return this.player1accepted;
	}

	/**
	 * Gets the player2accepted.
	 * 
	 * @return the player2accepted
	 */
	public Date getPlayer2accepted() {
		return this.player2accepted;
	}

	/**
	 * Gets the playerid1.
	 * 
	 * @return the playerid1
	 */
	public Integer getPlayerid1() {
		return this.playerByPlayerid1.getId();
	}

	/**
	 * Gets the playerid2.
	 * 
	 * @return the playerid2
	 */
	public Integer getPlayerid2() {
		return this.playerByPlayerid2.getId();
	}

	/**
	 * @see com.g4g.dto.DtoList#getValue()
	 */
	public String getValue() {
		// TODO Auto-generated method stub
		return (PlayerServiceDelegator.getPlayer(playerByPlayerid2.getId()))
				.getScreenname();
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		int result = G4GConstants.SEVENTEEN;

		result = G4GConstants.THIRTYSEVEN
				* result
				+ (getPlayerid1() == null ? G4GConstants.ZERO : this
						.getPlayerid1().hashCode());
		result = G4GConstants.THIRTYSEVEN
				* result
				+ (getPlayerid2() == null ? G4GConstants.ZERO : this
						.getPlayerid2().hashCode());
		result = G4GConstants.THIRTYSEVEN
				* result
				+ (getPlayer1accepted() == null ? G4GConstants.ZERO : this
						.getPlayer1accepted().hashCode());
		result = G4GConstants.THIRTYSEVEN
				* result
				+ (getPlayer2accepted() == null ? G4GConstants.ZERO : this
						.getPlayer2accepted().hashCode());
		return result;
	}

	/**
	 * Sets the player1accepted.
	 * 
	 * @param player1accepted
	 *            the new player1accepted
	 */
	public void setPlayer1accepted(Date player1accepted) {
		this.player1accepted = player1accepted;
	}

	/**
	 * Sets the player2accepted.
	 * 
	 * @param player2accepted
	 *            the new player2accepted
	 */
	public void setPlayer2accepted(Date player2accepted) {
		this.player2accepted = player2accepted;
	}

	/**
	 * Gets the player by playerid2.
	 * 
	 * @return the player by playerid2
	 */
	public PlayerDTO getPlayerByPlayerid2() {
		return playerByPlayerid2;
	}

	/**
	 * Sets the player by playerid2.
	 * 
	 * @param playerByPlayerid2
	 *            the new player by playerid2
	 */
	public void setPlayerByPlayerid2(PlayerDTO playerByPlayerid2) {
		this.playerByPlayerid2 = playerByPlayerid2;
	}

	/**
	 * Gets the player by playerid1.
	 * 
	 * @return the player by playerid1
	 */
	public PlayerDTO getPlayerByPlayerid1() {
		return playerByPlayerid1;
	}

	/**
	 * Sets the player by playerid1.
	 * 
	 * @param playerByPlayerid1
	 *            the new player by playerid1
	 */
	public void setPlayerByPlayerid1(PlayerDTO playerByPlayerid1) {
		this.playerByPlayerid1 = playerByPlayerid1;
	}

}