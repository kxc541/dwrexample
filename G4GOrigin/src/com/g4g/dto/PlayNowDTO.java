/**********************************************************
 * Copyright (c) 2008 - 2008, Askdigi, All rights reserved.
 * 
 **********************************************************/
package com.g4g.dto;

import java.util.Date;

/**
 * The Class PlayNowDTO is used for playNow widget.
 * 
 * @author Jigar Mistry
 */
public class PlayNowDTO {

	/** The game id. */
	private int gameId;

	/** The Match id. */
	private int matchId;

	/** The player id. */
	private int playerId;

	/** The schedule date. */
	private Date scheduleDate;

	/** The game name. */
	private String gameName;

	/** The game img src. */
	private String gameImgSrc;

	/** The entry fee. */
	private long entryFee;

	/** The player screen name. */
	private String playerScreenName;

	/**
	 * Gets the game id.
	 * 
	 * @return the gameId
	 */
	public int getGameId() {
		return gameId;
	}

	/**
	 * Sets the game id.
	 * 
	 * @param gameId
	 *            the gameId to set
	 */
	public void setGameId(int gameId) {
		this.gameId = gameId;
	}

	/**
	 * Gets the player id.
	 * 
	 * @return the playerId
	 */
	public int getPlayerId() {
		return playerId;
	}

	/**
	 * Sets the player id.
	 * 
	 * @param playerId
	 *            the playerId to set
	 */
	public void setPlayerId(int playerId) {
		this.playerId = playerId;
	}

	/**
	 * Gets the schedule date.
	 * 
	 * @return the scheduleDate
	 */
	public Date getScheduleDate() {
		return scheduleDate;
	}

	/**
	 * Sets the schedule date.
	 * 
	 * @param scheduleDate
	 *            the scheduleDate to set
	 */
	public void setScheduleDate(Date scheduleDate) {
		this.scheduleDate = scheduleDate;
	}

	/**
	 * Gets the game name.
	 * 
	 * @return the gameName
	 */
	public String getGameName() {
		return gameName;
	}

	/**
	 * Sets the game name.
	 * 
	 * @param gameName
	 *            the gameName to set
	 */
	public void setGameName(String gameName) {
		this.gameName = gameName;
	}

	/**
	 * Gets the game img src.
	 * 
	 * @return the gameImgSrc
	 */
	public String getGameImgSrc() {
		return gameImgSrc;
	}

	/**
	 * Sets the game img src.
	 * 
	 * @param gameImgSrc
	 *            the gameImgSrc to set
	 */
	public void setGameImgSrc(String gameImgSrc) {
		this.gameImgSrc = gameImgSrc;
	}

	/**
	 * Gets the entry fee.
	 * 
	 * @return the entryFee
	 */
	public long getEntryFee() {
		return entryFee;
	}

	/**
	 * Sets the entry fee.
	 * 
	 * @param entryFee
	 *            the entryFee to set
	 */
	public void setEntryFee(long entryFee) {
		this.entryFee = entryFee;
	}

	/**
	 * Gets the player screen name.
	 * 
	 * @return the playerScreenName
	 */
	public String getPlayerScreenName() {
		return playerScreenName;
	}

	/**
	 * Sets the player screen name.
	 * 
	 * @param playerScreenName
	 *            the playerScreenName to set
	 */
	public void setPlayerScreenName(String playerScreenName) {
		this.playerScreenName = playerScreenName;
	}

	/**
	 * @return the matchId
	 */
	public int getMatchId() {
		return this.matchId;
	}

	/**
	 * @param matchId
	 *            the matchId to set
	 */
	public void setMatchId(int matchId) {
		this.matchId = matchId;
	}

}
