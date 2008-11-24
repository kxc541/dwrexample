/**********************************************************
 * Copyright (c) 2008 - 2008, Askdigi, All rights reserved.
 * 
 **********************************************************/
package com.g4g.dto;

import java.util.Date;
import java.util.List;

/**
 * The Class PastTournamentDTO contains attribute for past tournaments of
 * tournament page.
 * 
 * @author Punam
 */
public class PastTournamentDTO {

	/** The network dto. */
	private NetworkDTO networkDTO;

	/** The game dto. */
	private GameDTO gameDTO;

	/** The twoplayertournament dto. */
	private TwoplayertournamentDTO twoplayertournamentDTO;

	/** The players list. */
	private List<PlayerDTO> playersList;

	/** The winner dto. */
	private PlayerDTO winnerDTO;

	/** The completiondate. */
	private Date completiondate;

	/**
	 * Gets the completiondate.
	 * 
	 * @return the completiondate
	 */
	public Date getCompletiondate() {
		return completiondate;
	}

	/**
	 * Sets the completiondate.
	 * 
	 * @param completiondate
	 *            the new completiondate
	 */
	public void setCompletiondate(Date completiondate) {
		this.completiondate = completiondate;
	}

	/**
	 * Gets the network dto.
	 * 
	 * @return the network dto
	 */
	public NetworkDTO getNetworkDTO() {
		return networkDTO;
	}

	/**
	 * Sets the network dto.
	 * 
	 * @param networkDTO
	 *            the new network dto
	 */
	public void setNetworkDTO(NetworkDTO networkDTO) {
		this.networkDTO = networkDTO;
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
	 * Gets the players list.
	 * 
	 * @return the players list
	 */
	public List<PlayerDTO> getPlayersList() {
		return playersList;
	}

	/**
	 * Sets the players list.
	 * 
	 * @param playersList
	 *            the new players list
	 */
	public void setPlayersList(List<PlayerDTO> playersList) {
		this.playersList = playersList;
	}

	/**
	 * Gets the winner dto.
	 * 
	 * @return the winner dto
	 */
	public PlayerDTO getWinnerDTO() {
		return winnerDTO;
	}

	/**
	 * Sets the winner dto.
	 * 
	 * @param winnerDTO
	 *            the new winner dto
	 */
	public void setWinnerDTO(PlayerDTO winnerDTO) {
		this.winnerDTO = winnerDTO;
	}

}
