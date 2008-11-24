/**********************************************************
 * ManagePlayerGameForm.java : 
 *
 * Created by Ankur
 * Last modified Date: 18 Apr .08 by Punam
 * Revision: 0.1
 * Version : 0.0.8
 * Copyright (c) 2008 - 2008 Askdigi, All rights reserved.
 **********************************************************/
package com.g4g.forms;

import org.apache.log4j.Level;

import com.g4g.dto.BaseDTO;
import com.g4g.utils.AuditUtil;
import com.g4g.utils.DataUtil;
import com.g4g.utils.G4GConstants;

/**
 * The Class ManagePlayerGameForm contains attributes for direct challenge.It is
 * used by playerservice,It is for profile page.Its attributes are gameId,
 * creationdate, gamename.
 * 
 * @author Ankur
 */
public class ManagePlayerGameForm extends BaseForm {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The game id. */
	private int gameId;

	/** The player id. */
	private int playerId;

	/** The creation date. */
	private String creationDate;

	/** The last update date. */
	private String lastUpdateDate;

	/** The game name. */
	private String gameName;

	/**
	 * Gets the creation date.
	 * 
	 * @return the creation date
	 */
	public String getCreationDate() {
		return creationDate;
	}

	/**
	 * @see com.g4g.forms.BaseForm#getDTO()
	 */
	@Override
	public BaseDTO getDTO() {
		// TODO Auto-generated method stub
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
	 * Gets the last update date.
	 * 
	 * @return the last update date
	 */
	public String getLastUpdateDate() {
		return lastUpdateDate;
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

	/**
	 * Sets the creation date.
	 * 
	 * @param creationDate
	 *            the new creation date
	 */
	public void setCreationDate(String creationDate) {
		this.creationDate = creationDate;
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
	 * Sets the last update date.
	 * 
	 * @param lastUpdateDate
	 *            the new last update date
	 */
	public void setLastUpdateDate(String lastUpdateDate) {
		this.lastUpdateDate = lastUpdateDate;
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

}
