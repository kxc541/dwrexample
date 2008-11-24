/**********************************************************
 * DirectChallenge2Form.java : 
 *
 * Created by Ankur
 * Last modified Date: 18 Apr .08 by Punam
 * Revision: 0.1
 * Version : 0.0.8
 * Copyright (c) 2008 - 2008 Askdigi, All rights reserved.
 **********************************************************/
package com.g4g.forms;

import java.util.List;

import org.apache.log4j.Level;

import com.g4g.dto.BaseDTO;
import com.g4g.dto.GameDTO;
import com.g4g.dto.PlayerDTO;
import com.g4g.utils.AuditUtil;
import com.g4g.utils.DataUtil;
import com.g4g.utils.G4GConstants;
import com.impessa.worldgaming.client.User;

/**
 * The Class DirectedChallengeForm contains attribute for directedchallenge2
 * page.DirectedChallenge2Form is used in DirectedChallenge2Action class,
 * DirectedChallenge3Action class, DirectedChallengeAction class. It contains
 * attribute for challenge. It contains attributes as tournamentDate, playerDTO,
 * ..etc..
 * 
 * @author Ankur
 */
public class DirectChallenge2Form extends BaseForm {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -8773179591336459951L;

	/** The tournament date. */
	private String tournamentDate;

	/** The player dto. */
	private PlayerDTO playerDTO;

	/** The player user. */
	private User playerUser;

	/** The comments. */
	private String comments;

	/** The opp player dto. */
	private PlayerDTO oppPlayerDTO;

	/** The opp player user. */
	private User oppPlayerUser;

	/** The game dto. */
	private GameDTO gameDTO;

	/** The amount field. */
	private String amountField;

	/** The game console. */
	private String gameConsole;

	/** The game option selected. */
	private String[] gameOptionSelected;

	/** The game options. */
	private List<Object> gameOptions;

	/**
	 * Gets the amount field.
	 * 
	 * @return amountField
	 */
	public String getAmountField() {
		return amountField;
	}

	/**
	 * Gets the game console.
	 * 
	 * @return gameConsole
	 */
	public String getGameConsole() {
		return gameConsole;
	}

	/**
	 * Gets the game dto.
	 * 
	 * @return gameDTO
	 */
	public GameDTO getGameDTO() {
		return gameDTO;
	}

	/**
	 * Gets the game options.
	 * 
	 * @return gameOptions
	 */
	public List<Object> getGameOptions() {
		return gameOptions;
	}

	/**
	 * Gets the game option selected.
	 * 
	 * @return gameOptionSelected
	 */
	public String[] getGameOptionSelected() {
		return gameOptionSelected;
	}

	/**
	 * Gets the opp player dto.
	 * 
	 * @return oppPlayerDTO
	 */
	public PlayerDTO getOppPlayerDTO() {
		return oppPlayerDTO;
	}

	/**
	 * Gets the opp player user.
	 * 
	 * @return oppPlayerUser
	 */
	public User getOppPlayerUser() {
		return oppPlayerUser;
	}

	/**
	 * Gets the player dto.
	 * 
	 * @return playerDTO
	 */
	public PlayerDTO getPlayerDTO() {
		return playerDTO;
	}

	/**
	 * Gets the player user.
	 * 
	 * @return playerUser
	 */
	public User getPlayerUser() {
		return playerUser;
	}

	/**
	 * Gets the tournament date.
	 * 
	 * @return tournamentDate
	 */
	public String getTournamentDate() {
		return tournamentDate;
	}

	/**
	 * Sets the amount field.
	 * 
	 * @param amountField
	 *            the amount field
	 */
	public void setAmountField(String amountField) {
		this.amountField = amountField;
	}

	/**
	 * Sets the game console.
	 * 
	 * @param gameConsole
	 *            the game console
	 */
	public void setGameConsole(String gameConsole) {
		this.gameConsole = gameConsole;
	}

	/**
	 * Sets the game dto.
	 * 
	 * @param gameDTO
	 *            the game dto
	 */
	public void setGameDTO(GameDTO gameDTO) {
		this.gameDTO = gameDTO;
	}

	/**
	 * Sets the game options.
	 * 
	 * @param gameOptions
	 *            the game options
	 */
	public void setGameOptions(List<Object> gameOptions) {
		this.gameOptions = gameOptions;
	}

	/**
	 * Sets the game option selected.
	 * 
	 * @param gameOptionSelected
	 *            the game option selected
	 */
	public void setGameOptionSelected(String[] gameOptionSelected) {
		this.gameOptionSelected = gameOptionSelected;
	}

	/**
	 * Sets the opp player dto.
	 * 
	 * @param oppPlayerDTO
	 *            the opp player dto
	 */
	public void setOppPlayerDTO(PlayerDTO oppPlayerDTO) {
		this.oppPlayerDTO = oppPlayerDTO;
	}

	/**
	 * Sets the opp player user.
	 * 
	 * @param oppPlayerUser
	 *            the opp player user
	 */
	public void setOppPlayerUser(User oppPlayerUser) {
		this.oppPlayerUser = oppPlayerUser;
	}

	/**
	 * Sets the player dto.
	 * 
	 * @param playerDTO
	 *            the player dto
	 */
	public void setPlayerDTO(PlayerDTO playerDTO) {
		this.playerDTO = playerDTO;
	}

	/**
	 * Sets the player user.
	 * 
	 * @param playerUser
	 *            the player user
	 */
	public void setPlayerUser(User playerUser) {
		this.playerUser = playerUser;
	}

	/**
	 * Sets the tournament date.
	 * 
	 * @param tournamentDate
	 *            the tournament date
	 */
	public void setTournamentDate(String tournamentDate) {
		this.tournamentDate = tournamentDate;
	}

	/**
	 * Gets the dto.
	 * 
	 * @return the DTO
	 * 
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
	 * Populate.
	 * 
	 * @param baseDto
	 *            the base dto
	 * 
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
	 * Gets the comments.
	 * 
	 * @return comments
	 */
	public String getComments() {
		return comments;
	}

	/**
	 * Sets the comments.
	 * 
	 * @param comments
	 *            the comments
	 */
	public void setComments(String comments) {
		this.comments = comments;
	}

}