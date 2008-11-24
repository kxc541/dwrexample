/**********************************************************
 * Copyright (c) 2008 - 2008, Askdigi, All rights reserved.
 * 
 **********************************************************/
package com.g4g.dto;

import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * The Class TournamentGameDTO used for search widget.
 * 
 * @author Punam
 */
public class TournamentGameDTO {

	/** The twoplayertournament dto. */
	private TwoplayertournamentDTO twoplayertournamentDTO;

	/** The game dto. */
	private GameDTO gameDTO;

	/** The no_of_players. */
	private int no_of_players;

	/** The scheduledstartdate. */
	private Date scheduledstartdate;

	/** The two player match list. */
	private List<TwoplayermatchDTO> twoPlayerMatchList;

	/** The game options list. */
	private Set<GameoptionsDTO> gameOptionsList;

	/**
	 * Gets the game options list.
	 * 
	 * @return the game options list
	 */
	public Set<GameoptionsDTO> getGameOptionsList() {
		return gameOptionsList;
	}

	/**
	 * Sets the game options list.
	 * 
	 * @param gameOptionsList
	 *            the new game options list
	 */
	public void setGameOptionsList(Set<GameoptionsDTO> gameOptionsList) {
		this.gameOptionsList = gameOptionsList;
	}

	/**
	 * Gets the no_of_players.
	 * 
	 * @return the no_of_players
	 */
	public int getNo_of_players() {
		return no_of_players;
	}

	/**
	 * Sets the no_of_players.
	 * 
	 * @param no_of_players
	 *            the new no_of_players
	 */
	public void setNo_of_players(int no_of_players) {
		this.no_of_players = no_of_players;
	}

	/**
	 * Gets the scheduledstartdate.
	 * 
	 * @return the scheduledstartdate
	 */
	public Date getScheduledstartdate() {
		return scheduledstartdate;
	}

	/**
	 * Sets the scheduledstartdate.
	 * 
	 * @param scheduledstartdate
	 *            the new scheduledstartdate
	 */
	public void setScheduledstartdate(Date scheduledstartdate) {
		this.scheduledstartdate = scheduledstartdate;
	}

	/**
	 * Gets the twoplayertournament dto.
	 * 
	 * @return the twoplayertournament dto
	 */
	public TwoplayertournamentDTO getTwoplayertournamentDTO() {
		return twoplayertournamentDTO;
	}

	/**
	 * Sets the twoplayertournament dto.
	 * 
	 * @param twoplayertournamentDTO
	 *            the new twoplayertournament dto
	 */
	public void setTwoplayertournamentDTO(
			TwoplayertournamentDTO twoplayertournamentDTO) {
		this.twoplayertournamentDTO = twoplayertournamentDTO;
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
	 * Gets the two player match list.
	 * 
	 * @return the two player match list
	 */
	public List<TwoplayermatchDTO> getTwoPlayerMatchList() {
		return twoPlayerMatchList;
	}

	/**
	 * Sets the two player match list.
	 * 
	 * @param twoPlayerMatchList
	 *            the new two player match list
	 */
	public void setTwoPlayerMatchList(List<TwoplayermatchDTO> twoPlayerMatchList) {
		this.twoPlayerMatchList = twoPlayerMatchList;
	}

}
