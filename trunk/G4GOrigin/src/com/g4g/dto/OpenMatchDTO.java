/**********************************************************
 * Copyright (c) 2008 - 2008, Askdigi, All rights reserved.
 * 
 **********************************************************/
package com.g4g.dto;

/**
 * The Class OpenMatchDTO for openMatch tab in game details page.
 * 
 * @author Punam
 */
public class OpenMatchDTO {

	/** The payoutamount. */
	private Long payoutamount;

	/** The twoplayertournament dto. */
	private TwoplayertournamentDTO twoplayertournamentDTO = new TwoplayertournamentDTO();

	/** The matchid. */
	private int matchid;
	
	private PlayerDTO playerDTO;

	/**
	 * @return the playerDTO
	 */
	public PlayerDTO getPlayerDTO() {
		return this.playerDTO;
	}

	/**
	 * @param playerDTO the playerDTO to set
	 */
	public void setPlayerDTO(PlayerDTO playerDTO) {
		this.playerDTO = playerDTO;
	}

	/**
	 * Gets the matchid.
	 * 
	 * @return the matchid
	 */
	public int getMatchid() {
		return matchid;
	}

	/**
	 * Sets the matchid.
	 * 
	 * @param matchid
	 *            the new matchid
	 */
	public void setMatchid(int matchid) {
		this.matchid = matchid;
	}

	/**
	 * Gets the payoutamount.
	 * 
	 * @return the payoutamount
	 */
	public Long getPayoutamount() {
		return payoutamount;
	}

	/**
	 * Sets the payoutamount.
	 * 
	 * @param payoutamount
	 *            the new payoutamount
	 */
	public void setPayoutamount(Long payoutamount) {
		this.payoutamount = payoutamount;
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

}
