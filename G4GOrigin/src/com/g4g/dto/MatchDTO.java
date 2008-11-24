/**********************************************************
 * Copyright (c) 2008 - 2008, Askdigi, All rights reserved.
 * 
 **********************************************************/
package com.g4g.dto;

/**
 * The Class MatchDTO used for playNow widget.
 * 
 * @author Jigar Mistry
 */
public class MatchDTO {

	/** The game id. */
	private int gameId;

	/** The game image src. */
	private String gameImageSrc;

	/** The game name. */
	private String gameName;

	/** The console. */
	private String console;

	/** The schedule date time. */
	private String scheduleDateTime;

	/**
	 * Gets the game id.
	 * 
	 * @return the game id
	 */
	public int getGameId() {
		return gameId;
	}

	/**
	 * Sets the game id.
	 * 
	 * @param gameId
	 *            the new game id
	 */
	public void setGameId(int gameId) {
		this.gameId = gameId;
	}

	/**
	 * Gets the game image src.
	 * 
	 * @return the game image src
	 */
	public String getGameImageSrc() {
		return gameImageSrc;
	}

	/**
	 * Sets the game image src.
	 * 
	 * @param gameImageSrc
	 *            the new game image src
	 */
	public void setGameImageSrc(String gameImageSrc) {
		this.gameImageSrc = gameImageSrc;
	}

	/**
	 * Gets the game name.
	 * 
	 * @return the game name
	 */
	public String getGameName() {
		return gameName;
	}

	/**
	 * Sets the game name.
	 * 
	 * @param gameName
	 *            the new game name
	 */
	public void setGameName(String gameName) {
		this.gameName = gameName;
	}

	/**
	 * Gets the console.
	 * 
	 * @return the console
	 */
	public String getConsole() {
		return console;
	}

	/**
	 * Sets the console.
	 * 
	 * @param console
	 *            the new console
	 */
	public void setConsole(String console) {
		this.console = console;
	}

	/**
	 * @return the scheduleDateTime
	 */
	public String getScheduleDateTime() {
		return this.scheduleDateTime;
	}

	/**
	 * @param scheduleDateTime
	 *            the scheduleDateTime to set
	 */
	public void setScheduleDateTime(String scheduleDateTime) {
		this.scheduleDateTime = scheduleDateTime;
	}
}
