/**********************************************************
 * PlayerGameScheduleForm.java : 
 *
 * Created by Pratik
 * Last modified Date: 18 Apr .08 by Punam
 * Revision: 0.1
 * Version : 0.0.8
 * Copyright (c) 2008 - 2008 Askdigi, All rights reserved.
 **********************************************************/
package com.g4g.forms;

import java.util.Date;

import org.apache.log4j.Level;

import com.g4g.dto.BaseDTO;
import com.g4g.dto.GameDTO;
import com.g4g.utils.AuditUtil;
import com.g4g.utils.DataUtil;
import com.g4g.utils.G4GConstants;

/**
 * The Class PlayerGameScheduleForm contains properties for player game
 * schedule. It contains properties to diaplay in my schedule tab of profile
 * page.It contains attributes as matchid, tournamentid,..etc.
 * 
 * @author Pratik
 */
public class PlayerGameScheduleForm extends BaseForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/** The match id. */
	private int matchId;

	/** The tournament id. */
	private int tournamentId;

	/** The player id. */
	private int playerId;

	/** The opposite player id. */
	private int oppositePlayerId;

	/** The player name. */
	private String playerName;

	/** The opposite player name. */
	private String oppositePlayerName;

	/** The scheduled start date. */
	private Date scheduledStartDate;

	/** The game id. */
	private int gameId;

	/** The game name. */
	private String gameName;

	/** The game dto. */
	private GameDTO gameDTO;

	/** The payout amount. */
	private Double payoutAmount;

	/** The twoplayermatchid. */
	private int twoplayermatchid;

	/**
	 * Gets the twoplayermatchid.
	 * 
	 * @return the twoplayermatchid
	 */
	public int getTwoplayermatchid() {
		return twoplayermatchid;
	}

	/**
	 * Sets the twoplayermatchid.
	 * 
	 * @param twoplayermatchid
	 *            the new twoplayermatchid
	 */
	public void setTwoplayermatchid(int twoplayermatchid) {
		this.twoplayermatchid = twoplayermatchid;
	}

	/**
	 * Gets the game id.
	 * 
	 * @return the game id
	 */
	public int getGameId() {
		return gameId;
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
	 * Gets the match id.
	 * 
	 * @return the match id
	 */
	public int getMatchId() {
		return matchId;
	}

	/**
	 * Gets the opposite player id.
	 * 
	 * @return the opposite player id
	 */
	public int getOppositePlayerId() {
		return oppositePlayerId;
	}

	/**
	 * Gets the opposite player name.
	 * 
	 * @return the opposite player name
	 */
	public String getOppositePlayerName() {
		return oppositePlayerName;
	}

	/**
	 * Gets the payout amount.
	 * 
	 * @return the payout amount
	 */
	public Double getPayoutAmount() {
		return payoutAmount;
	}

	/**
	 * Gets the player id.
	 * 
	 * @return the player id
	 */
	public int getPlayerId() {
		return playerId;
	}

	/**
	 * Gets the player name.
	 * 
	 * @return the player name
	 */
	public String getPlayerName() {
		return playerName;
	}

	/**
	 * Gets the scheduled start date.
	 * 
	 * @return the scheduled start date
	 */
	public Date getScheduledStartDate() {
		return scheduledStartDate;
	}

	/**
	 * Gets the tournament id.
	 * 
	 * @return the tournament id
	 */
	public int getTournamentId() {
		return tournamentId;
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
	 * Sets the game name.
	 * 
	 * @param gameName
	 *            the new game name
	 */
	public void setGameName(String gameName) {
		this.gameName = gameName;
	}

	/**
	 * Sets the match id.
	 * 
	 * @param matchId
	 *            the new match id
	 */
	public void setMatchId(int matchId) {
		this.matchId = matchId;
	}

	/**
	 * Sets the opposite player id.
	 * 
	 * @param oppositePlayerId
	 *            the new opposite player id
	 */
	public void setOppositePlayerId(int oppositePlayerId) {
		this.oppositePlayerId = oppositePlayerId;
	}

	/**
	 * Sets the opposite player name.
	 * 
	 * @param oppositePlayerName
	 *            the new opposite player name
	 */
	public void setOppositePlayerName(String oppositePlayerName) {
		this.oppositePlayerName = oppositePlayerName;
	}

	/**
	 * Sets the payout amount.
	 * 
	 * @param payoutAmount
	 *            the new payout amount
	 */
	public void setPayoutAmount(Double payoutAmount) {
		this.payoutAmount = payoutAmount;
	}

	/**
	 * Sets the player id.
	 * 
	 * @param playerId
	 *            the new player id
	 */
	public void setPlayerId(int playerId) {
		this.playerId = playerId;
	}

	/**
	 * Sets the player name.
	 * 
	 * @param playerName
	 *            the new player name
	 */
	public void setPlayerName(String playerName) {
		this.playerName = playerName;
	}

	/**
	 * Sets the scheduled start date.
	 * 
	 * @param scheduledStartDate
	 *            the new scheduled start date
	 */
	public void setScheduledStartDate(Date scheduledStartDate) {
		this.scheduledStartDate = scheduledStartDate;
	}

	/**
	 * Sets the tournament id.
	 * 
	 * @param tournamentId
	 *            the new tournament id
	 */
	public void setTournamentId(int tournamentId) {
		this.tournamentId = tournamentId;
	}

	/**
	 * Gets the game dto.
	 * 
	 * @return the game dto
	 */
	public GameDTO getGameDTO() {
		return gameDTO;
	}

	/**
	 * Sets the game dto.
	 * 
	 * @param gameDTO
	 *            the new game dto
	 */
	public void setGameDTO(GameDTO gameDTO) {
		this.gameDTO = gameDTO;
	}

	/**
	 * @see com.g4g.forms.BaseForm#getDTO()
	 */
	@Override
	public BaseDTO getDTO() {
		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,
				new StringBuffer(this.getClass().getName()).append(
						G4GConstants.COLON_WITH_SPACES).append(G4GConstants.CALLINGMETHOD)
						.append(DataUtil.getCallingMethod()).append(
								G4GConstants.CURRENTMETHOD).append(
								DataUtil.getCurrentMethod()).append(
								G4GConstants.DASHES).append(
								G4GConstants.STARTED).toString(), Level.INFO);
		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,
				new StringBuffer(this.getClass().getName()).append(
						G4GConstants.COLON_WITH_SPACES).append(G4GConstants.CALLINGMETHOD)
						.append(DataUtil.getCallingMethod()).append(
								G4GConstants.CURRENTMETHOD).append(
								DataUtil.getCurrentMethod()).append(
								G4GConstants.DASHES).append(G4GConstants.ENDED)
						.toString(), Level.INFO);
		return null;
	}

	/**
	 * @see com.g4g.forms.BaseForm#populate(com.g4g.dto.BaseDTO)
	 */
	@Override
	public void populate(BaseDTO baseDto) {
		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,
				new StringBuffer(this.getClass().getName()).append(
						G4GConstants.COLON_WITH_SPACES).append(G4GConstants.CALLINGMETHOD)
						.append(DataUtil.getCallingMethod()).append(
								G4GConstants.CURRENTMETHOD).append(
								DataUtil.getCurrentMethod()).append(
								G4GConstants.DASHES).append(
								G4GConstants.STARTED).toString(), Level.INFO);
		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,
				new StringBuffer(this.getClass().getName()).append(
						G4GConstants.COLON_WITH_SPACES).append(G4GConstants.CALLINGMETHOD)
						.append(DataUtil.getCallingMethod()).append(
								G4GConstants.CURRENTMETHOD).append(
								DataUtil.getCurrentMethod()).append(
								G4GConstants.DASHES).append(G4GConstants.ENDED)
						.toString(), Level.INFO);
	}

}
