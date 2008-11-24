/**********************************************************
 * GameLobbyPageForm.java : 
 *
 * Created by Rakesh Ray
 * Last modified Date: 18 Apr .08 by Punam
 * Revision: 0.1
 * Version : 0.0.8
 * Copyright (c) 2008 - 2008 Askdigi, All rights reserved.
 **********************************************************/

package com.g4g.forms;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Level;

import com.g4g.dto.ActivePlayersDTO;
import com.g4g.dto.BaseDTO;
import com.g4g.dto.GameDTO;
import com.g4g.utils.AuditUtil;
import com.g4g.utils.DataUtil;
import com.g4g.utils.G4GConstants;

/**
 * GameLobbyPageForm contains attribute for gameLobbyAction. GameLobby page uses
 * the attributes to display the data. It is used to store game information
 * consolewise.It contains the attribues for consoless lists and users mygames
 * tab list.
 * 
 * @struts.form name="gameLobbyPageForm"
 * @author Rakesh Ray
 */
public class GameLobbyPageForm extends BaseForm {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -5812813091819167809L;

	/** The player game id. */
	private String[] playerGameId;

	/** The my game. */
	private List<Object> myGame = new ArrayList<Object>();

	/** The xbox game. */
	private List<GameDTO> xboxGame = new ArrayList<GameDTO>();

	/** The xbox360 game. */
	private List<GameDTO> xbox360Games = new ArrayList<GameDTO>();

	/** The ps2 game. */
	private List<GameDTO> ps2Games = new ArrayList<GameDTO>();

	/** The ps3 game. */
	private List<GameDTO> ps3Games = new ArrayList<GameDTO>();
	
	private List<ActivePlayersDTO> activePlayers = new ArrayList<ActivePlayersDTO>();

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
	 * Gets the my game.
	 * 
	 * @return the my game
	 */
	public List<Object> getMyGame() {
		return myGame;
	}

	/**
	 * Gets the player game id.
	 * 
	 * @return the player game id
	 */
	public String[] getPlayerGameId() {
		return playerGameId;
	}

	/**
	 * Gets the ps2 game.
	 * 
	 * @return the ps2 game
	 */
	public List<GameDTO> getPs2Game() {
		return ps2Games;
	}

	/**
	 * Gets the ps3 game.
	 * 
	 * @return the ps3 game
	 */
	public List<GameDTO> getPs3Game() {
		return ps3Games;
	}

	/**
	 * Gets the xbox360 game.
	 * 
	 * @return the xbox360 game
	 */
	public List<GameDTO> getXbox360Game() {
		return xbox360Games;
	}

	/**
	 * Gets the xbox game.
	 * 
	 * @return the xbox game
	 */
	public List<GameDTO> getXboxGame() {
		return xboxGame;
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
	 * Sets the my game.
	 * 
	 * @param myGame
	 *            the new my game
	 */
	public void setMyGame(List<Object> myGame) {
		this.myGame = myGame;
	}

	/**
	 * Sets the player game id.
	 * 
	 * @param playerGameId
	 *            the new player game id
	 */
	public void setPlayerGameId(String[] playerGameId) {
		this.playerGameId = playerGameId;
	}

	/**
	 * Sets the ps2 game.
	 * 
	 * @param ps2Game
	 *            the new ps2 game
	 */
	public void setPs2Game(List<GameDTO> ps2Game) {
		this.ps2Games = ps2Game;
	}

	/**
	 * Sets the ps3 game.
	 * 
	 * @param ps3Game
	 *            the new ps3 game
	 */
	public void setPs3Game(List<GameDTO> ps3Game) {
		this.ps3Games = ps3Game;
	}

	/**
	 * Sets the xbox360 game.
	 * 
	 * @param xbox360Game
	 *            the new xbox360 game
	 */
	public void setXbox360Game(List<GameDTO> xbox360Game) {
		this.xbox360Games = xbox360Game;
	}

	/**
	 * Sets the xbox game.
	 * 
	 * @param xboxGame
	 *            the new xbox game
	 */
	public void setXboxGame(List<GameDTO> xboxGame) {
		this.xboxGame = xboxGame;
	}

	public List<GameDTO> getXbox360Games() {
		return xbox360Games;
	}

	public void setXbox360Games(List<GameDTO> xbox360Games) {
		this.xbox360Games = xbox360Games;
	}

	public List<GameDTO> getPs2Games() {
		return ps2Games;
	}

	public void setPs2Games(List<GameDTO> ps2Games) {
		this.ps2Games = ps2Games;
	}

	public List<GameDTO> getPs3Games() {
		return ps3Games;
	}

	public void setPs3Games(List<GameDTO> ps3Games) {
		this.ps3Games = ps3Games;
	}

	public List<ActivePlayersDTO> getActivePlayers() {
		return activePlayers;
	}

	public void setActivePlayers(List<ActivePlayersDTO> activePlayers) {
		this.activePlayers = activePlayers;
	}

}