/**********************************************************
 * GameDTO.java : 
 *
 * Created by Rakesh
 * Last modified Date: 21 Apr .08 by Pratik
 * Revision: 0.1
 * Version : 0.0.8
 * Copyright (c) 2008 - 2008 Askdigi, All rights reserved.
 **********************************************************/
package com.g4g.dto;

import java.util.HashSet;
import java.util.Set;

import com.g4g.utils.G4GConstants;

/**
 * GameDTO is the DTO class for Game table in database. It contains gamename,
 * imgscr etc. attributes. Game table contains all the information about game.
 * 
 * 
 * @author Rakesh
 */

public class GameDTO extends BaseDTO implements DtoList {

	// Fields

	private static final String SLASH_WG = "/wg"; //$NON-NLS-1$

	private static final String UNNECESSARYLOCALVARIABLE = "UnnecessaryLocalVariable"; //$NON-NLS-1$

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -2395238998689415446L;

	private NetworkDTO network;
	private GamecategoryDTO gamecategory;
	private String gamename;
	private String imgsrc;
	private String bannerimgsrc;
	private Integer minplaytimeinmin;
	private String tournamentbannerimgsrc;
	private String introduction;
	private String multilayersupport;
	private String headtoheadfaq;
	private Set<TwoplayertournamentDTO> twoplayertournaments = new HashSet<TwoplayertournamentDTO>(
			0);
	private Set<GameoptionsDTO> gameoptions = new HashSet<GameoptionsDTO>(0);

	// Property accessors

	/**
	 * @param id 
	 * 
	 */
	public GameDTO(int id) {
		super();
		this.id = id;
	}

	/**
	 * 
	 */
	public GameDTO() {
		super();
	}

	/**
	 * Gets the introduction.
	 * 
	 * @return the introduction
	 */
	public String getIntroduction() {
		return introduction;
	}

	/**
	 * Sets the introduction.
	 * 
	 * @param introduction
	 *            the new introduction
	 */
	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}

	/**
	 * Gets the multilayersupport.
	 * 
	 * @return the multilayersupport
	 */
	public String getMultilayersupport() {
		return multilayersupport;
	}

	/**
	 * Sets the multilayersupport.
	 * 
	 * @param multilayersupport
	 *            the new multilayersupport
	 */
	public void setMultilayersupport(String multilayersupport) {
		this.multilayersupport = multilayersupport;
	}

	/**
	 * Gets the headtoheadfaq.
	 * 
	 * @return the headtoheadfaq
	 */
	public String getHeadtoheadfaq() {
		return headtoheadfaq;
	}

	/**
	 * Sets the headtoheadfaq.
	 * 
	 * @param headtoheadfaq
	 *            the new headtoheadfaq
	 */
	public void setHeadtoheadfaq(String headtoheadfaq) {
		this.headtoheadfaq = headtoheadfaq;
	}

	/**
	 * Gets the bannerimgsrc.
	 * 
	 * @return the bannerimgsrc
	 */
	public String getBannerimgsrc() {
		return bannerimgsrc;
	}

	/**
	 * Gets the gamename.
	 * 
	 * @return the gamename
	 */
	public String getGamename() {
		return this.gamename;
	}

	/**
	 * Gets the imgsrc.
	 * 
	 * @return the imgsrc
	 */
	public String getImgsrc() {

		return imgsrc;
	}

	/**
	 * Gets the without wg imgsrc.
	 * 
	 * @return the without wg imgsrc
	 */
	@SuppressWarnings({UNNECESSARYLOCALVARIABLE})
    public String getWithoutWGImgsrc() {
		return this.imgsrc.replaceFirst(SLASH_WG, G4GConstants.NONE);
	}

	/**
	 * Gets the minplaytimeinmin.
	 * 
	 * @return the minplaytimeinmin
	 */
	public Integer getMinplaytimeinmin() {
		return minplaytimeinmin;
	}

	/**
	 * Sets the bannerimgsrc.
	 * 
	 * @param bannerimgsrc
	 *            the new bannerimgsrc
	 */
	public void setBannerimgsrc(String bannerimgsrc) {
		this.bannerimgsrc = bannerimgsrc;
	}

	/**
	 * Sets the gamename.
	 * 
	 * @param gamename
	 *            the new gamename
	 */
	public void setGamename(String gamename) {
		this.gamename = gamename;
	}

	/**
	 * Sets the imgsrc.
	 * 
	 * @param imgsrc
	 *            the new imgsrc
	 */
	public void setImgsrc(String imgsrc) {
		this.imgsrc = imgsrc;
	}

	/**
	 * Sets the minplaytimeinmin.
	 * 
	 * @param minplaytimeinmin
	 *            the new minplaytimeinmin
	 */
	public void setMinplaytimeinmin(Integer minplaytimeinmin) {
		this.minplaytimeinmin = minplaytimeinmin;
	}

	/**
	 * Gets the tournamentbannerimgsrc.
	 * 
	 * @return the tournamentbannerimgsrc
	 */
	public String getTournamentbannerimgsrc() {
		return tournamentbannerimgsrc;
	}

	/**
	 * Sets the tournamentbannerimgsrc.
	 * 
	 * @param tournamentbannerimgsrc
	 *            the new tournamentbannerimgsrc
	 */
	public void setTournamentbannerimgsrc(String tournamentbannerimgsrc) {
		this.tournamentbannerimgsrc = tournamentbannerimgsrc;
	}

	/**
	 * @return a String as a combination of (game name and network name)
	 * 
	 */
	public String getDescription() {
		StringBuffer networkname = new StringBuffer();
		networkname.append(gamename).append(G4GConstants.BLANK).append(
				G4GConstants.COLON).append(G4GConstants.BLANK);
		networkname.append(G4GConstants.BRACKETLEFT);
		if (network.getId() == G4GConstants.NETWORK_PS2_ID_LIST_KEY) {
			networkname.append(G4GConstants.PS2);
		} else if (network.getId() == G4GConstants.NETWORK_PS3_ID_LIST_KEY) {
			networkname.append(G4GConstants.PS3);
		} else if (network.getId() == G4GConstants.NETWORK_XBOX360_LIST_KEY) {
			networkname.append(G4GConstants.Xbox360);

		} else if (network.getId() == G4GConstants.NETWORK_XBOX_LIST_KEY) {
			networkname.append(G4GConstants.XBOX);
		}
		networkname.append(G4GConstants.BRACKETRIGHT);

		return networkname.toString();
	}

	/**
	 * @return gameid in String format
	 */
	public String getKey() {
		return String.valueOf(id);
	}

	/**
	 * @return game name
	 */
	public String getValue() {

		return gamename;
	}

	/**
	 * @return the network
	 */
	public NetworkDTO getNetwork() {
		return this.network;
	}

	/**
	 * @param network
	 *            the network to set
	 */
	public void setNetwork(NetworkDTO network) {
		this.network = network;
	}

	/**
	 * @return the gamecategory
	 */
	public GamecategoryDTO getGamecategory() {
		return this.gamecategory;
	}

	/**
	 * @param gamecategory
	 *            the gamecategory to set
	 */
	public void setGamecategory(GamecategoryDTO gamecategory) {
		this.gamecategory = gamecategory;
	}

	/**
	 * @return the twoplayertournaments
	 */
	public Set<TwoplayertournamentDTO> getTwoplayertournaments() {
		return this.twoplayertournaments;
	}

	/**
	 * @param twoplayertournaments
	 *            the twoplayertournaments to set
	 */
	public void setTwoplayertournaments(
			Set<TwoplayertournamentDTO> twoplayertournaments) {
		this.twoplayertournaments = twoplayertournaments;
	}

	/**
	 * @return the gameoptions
	 */
	public Set<GameoptionsDTO> getGameoptions() {
		return this.gameoptions;
	}

	/**
	 * @param gameoptions
	 *            the gameoptions to set
	 */
	public void setGameoptions(Set<GameoptionsDTO> gameoptions) {
		this.gameoptions = gameoptions;
	}

}