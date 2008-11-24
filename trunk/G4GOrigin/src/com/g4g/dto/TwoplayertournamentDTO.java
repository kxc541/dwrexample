/**********************************************************
 * Copyright (c) 2008 - 2008, Askdigi, All rights reserved.
 * 
 **********************************************************/
package com.g4g.dto;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

// TODO: Auto-generated Javadoc
/**
 * Twoplayertournament contains attribute of twoplayertournament entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class TwoplayertournamentDTO extends BaseDTO {

	// Fields

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -6776972601480610665L;

	/** The game dto. */
	private GameDTO gameDTO;
	
	/** The skin dto. */
	private SkinDTO skinDTO;
	
	/** The betid. */
	private String betid;
	
	/** The levels. */
	private Integer levels;
	
	/** The entryfee. */
	private Double entryfee;
	
	/** The housefeeperplayer. */
	private Double housefeeperplayer;
	
	/** The creationdate. */
	private Date creationdate;
	
	/** The completiondate. */
	private Date completiondate;
	
	/** The cancellationdate. */
	private Date cancellationdate;
	
	/** The createdby. */
	private EmployeeDTO createdby;
	
	/** The jackpot. */
	private Double jackpot = 0.00D;
	
	/** The twoplayermatches. */
	private Set<TwoplayermatchDTO> twoplayermatches = new HashSet<TwoplayermatchDTO>(0);

	/**
	 * Instantiates a new twoplayertournament dto.
	 */
	public TwoplayertournamentDTO() {
		super();
	}

	/**
	 * Instantiates a new twoplayertournament dto.
	 * 
	 * @param id the id
	 */
	public TwoplayertournamentDTO(int id) {
		super();
		this.id = id;
	}

	/**
	 * Gets the betid.
	 * 
	 * @return the betid
	 */
	public String getBetid() {
		return this.betid;
	}

	/**
	 * Sets the betid.
	 * 
	 * @param betid the new betid
	 */
	public void setBetid(String betid) {
		this.betid = betid;
	}

	/**
	 * Gets the levels.
	 * 
	 * @return the levels
	 */
	public Integer getLevels() {
		return this.levels;
	}

	/**
	 * Sets the levels.
	 * 
	 * @param levels the new levels
	 */
	public void setLevels(Integer levels) {
		this.levels = levels;
	}

	/**
	 * Gets the entryfee.
	 * 
	 * @return the entryfee
	 */
	public Double getEntryfee() {
		return this.entryfee;
	}

	/**
	 * Sets the entryfee.
	 * 
	 * @param entryfee the new entryfee
	 */
	public void setEntryfee(Double entryfee) {
		this.entryfee = entryfee;
	}

	/**
	 * Gets the housefeeperplayer.
	 * 
	 * @return the housefeeperplayer
	 */
	public Double getHousefeeperplayer() {
		return this.housefeeperplayer;
	}

	/**
	 * Sets the housefeeperplayer.
	 * 
	 * @param housefeeperplayer the new housefeeperplayer
	 */
	public void setHousefeeperplayer(Double housefeeperplayer) {
		this.housefeeperplayer = housefeeperplayer;
	}

	/**
	 * Gets the creationdate.
	 * 
	 * @return the creationdate
	 */
	public Date getCreationdate() {
		return this.creationdate;
	}

	/**
	 * Sets the creationdate.
	 * 
	 * @param creationdate the new creationdate
	 */
	public void setCreationdate(Date creationdate) {
		this.creationdate = creationdate;
	}

	/**
	 * Gets the completiondate.
	 * 
	 * @return the completiondate
	 */
	public Date getCompletiondate() {
		return this.completiondate;
	}

	/**
	 * Sets the completiondate.
	 * 
	 * @param completiondate the new completiondate
	 */
	public void setCompletiondate(Date completiondate) {
		this.completiondate = completiondate;
	}

	/**
	 * Gets the cancellationdate.
	 * 
	 * @return the cancellationdate
	 */
	public Date getCancellationdate() {
		return this.cancellationdate;
	}

	/**
	 * Sets the cancellationdate.
	 * 
	 * @param cancellationdate the new cancellationdate
	 */
	public void setCancellationdate(Date cancellationdate) {
		this.cancellationdate = cancellationdate;
	}

	

	/**
	 * @return the createdby
	 */
	public EmployeeDTO getCreatedby() {
		return this.createdby;
	}

	/**
	 * @param createdby the createdby to set
	 */
	public void setCreatedby(EmployeeDTO createdby) {
		this.createdby = createdby;
	}

	/**
	 * @return the gameDTO
	 */
	public GameDTO getGameDTO() {
		return this.gameDTO;
	}

	/**
	 * @param gameDTO
	 *            the gameDTO to set
	 */
	public void setGameDTO(GameDTO gameDTO) {
		this.gameDTO = gameDTO;
	}

	/**
	 * @return the skinDTO
	 */
	public SkinDTO getSkinDTO() {
		return this.skinDTO;
	}

	/**
	 * @param skinDTO
	 *            the skinDTO to set
	 */
	public void setSkinDTO(SkinDTO skinDTO) {
		this.skinDTO = skinDTO;
	}

	/**
	 * @return the twoplayermatches
	 */
	public Set<TwoplayermatchDTO> getTwoplayermatches() {
		return this.twoplayermatches;
	}

	/**
	 * @param twoplayermatches
	 *            the twoplayermatches to set
	 */
	public void setTwoplayermatches(Set<TwoplayermatchDTO> twoplayermatches) {
		this.twoplayermatches = twoplayermatches;
	}

	/**
	 * Gets the jackpot.
	 * 
	 * @return the jackpot
	 */
	public Double getJackpot() {
		return jackpot;
	}

	/**
	 * Sets the jackpot.
	 * 
	 * @param jackpot the new jackpot
	 */
	public void setJackpot(Double jackpot) {
		this.jackpot = jackpot;
	}
}