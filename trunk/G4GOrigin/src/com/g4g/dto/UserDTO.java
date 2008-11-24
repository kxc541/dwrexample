/**********************************************************
 * Copyright (c) 2008 - 2008, Askdigi, All rights reserved.
 * 
 **********************************************************/
package com.g4g.dto;

import java.util.HashSet;
import java.util.Set;

import com.impessa.worldgaming.client.User;

/**
 * The Class UserDTO used to store session related data.
 * 
 * @author Ankur
 */
public class UserDTO extends BaseDTO {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 7095103074035022686L;

	/** The user. */
	private User user;

	private boolean isStateIllegal = false;
	/** The player dto. */
	private PlayerDTO playerDTO;

	/** The playergame dto. */
	private PlayergameDTO playergameDTO;

	/** The playernetwork dto xbox. */
	private PlayernetworkDTO playernetworkDTOxbox = new PlayernetworkDTO();

	/** The playernetwork dto xbox360. */
	private PlayernetworkDTO playernetworkDTOxbox360 = new PlayernetworkDTO();

	/** The playernetwork dt o3ps2. */
	private PlayernetworkDTO playernetworkDTO3ps2 = new PlayernetworkDTO();

	/** The playernetwork dt o4ps3. */
	private PlayernetworkDTO playernetworkDTO4ps3 = new PlayernetworkDTO();

	/** The player game. */
	Set<PlayergameDTO> playerGame = new HashSet<PlayergameDTO>();

	/** The session id. */
	String sessionId;

	/** The balance. */
	Double balance;

	/** The message count. */
	int messageCount;

	/** The offset. */
	int offset;

	/**
	 * Gets the user.
	 * 
	 * @return the user
	 */
	public User getUser() {
		return user;
	}

	/**
	 * Sets the user.
	 * 
	 * @param user
	 *            the user to set
	 */
	public void setUser(User user) {
		this.user = user;
	}

	/**
	 * Gets the player dto.
	 * 
	 * @return the playerDTO
	 */
	public PlayerDTO getPlayerDTO() {
		return playerDTO;
	}

	/**
	 * Sets the player dto.
	 * 
	 * @param playerDTO
	 *            the playerDTO to set
	 */
	public void setPlayerDTO(PlayerDTO playerDTO) {
		this.playerDTO = playerDTO;
	}

	/**
	 * Gets the playergame dto.
	 * 
	 * @return the playergameDTO
	 */
	public PlayergameDTO getPlayergameDTO() {
		return playergameDTO;
	}

	/**
	 * Sets the playergame dto.
	 * 
	 * @param playergameDTO
	 *            the playergameDTO to set
	 */
	public void setPlayergameDTO(PlayergameDTO playergameDTO) {
		this.playergameDTO = playergameDTO;
	}

	/**
	 * Gets the playernetwork dt oxbox.
	 * 
	 * @return the playernetworkDTOxbox
	 */
	public PlayernetworkDTO getPlayernetworkDTOxbox() {
		return playernetworkDTOxbox;
	}

	/**
	 * Sets the playernetwork dt oxbox.
	 * 
	 * @param playernetworkDTOxbox
	 *            the playernetworkDTOxbox to set
	 */
	public void setPlayernetworkDTOxbox(PlayernetworkDTO playernetworkDTOxbox) {
		this.playernetworkDTOxbox = playernetworkDTOxbox;
	}

	/**
	 * Gets the playernetwork dt oxbox360.
	 * 
	 * @return the playernetworkDTOxbox360
	 */
	public PlayernetworkDTO getPlayernetworkDTOxbox360() {
		return playernetworkDTOxbox360;
	}

	/**
	 * Sets the playernetwork dt oxbox360.
	 * 
	 * @param playernetworkDTOxbox360
	 *            the playernetworkDTOxbox360 to set
	 */
	public void setPlayernetworkDTOxbox360(
			PlayernetworkDTO playernetworkDTOxbox360) {
		this.playernetworkDTOxbox360 = playernetworkDTOxbox360;
	}

	/**
	 * Gets the playernetwork dt o3ps2.
	 * 
	 * @return the playernetworkDTO3ps2
	 */
	public PlayernetworkDTO getPlayernetworkDTO3ps2() {
		return playernetworkDTO3ps2;
	}

	/**
	 * Sets the playernetwork dt o3ps2.
	 * 
	 * @param playernetworkDTO3ps2
	 *            the playernetworkDTO3ps2 to set
	 */
	public void setPlayernetworkDTO3ps2(PlayernetworkDTO playernetworkDTO3ps2) {
		this.playernetworkDTO3ps2 = playernetworkDTO3ps2;
	}

	/**
	 * Gets the playernetwork dt o4ps3.
	 * 
	 * @return the playernetworkDTO4ps3
	 */
	public PlayernetworkDTO getPlayernetworkDTO4ps3() {
		return playernetworkDTO4ps3;
	}

	/**
	 * Sets the playernetwork dt o4ps3.
	 * 
	 * @param playernetworkDTO4ps3
	 *            the playernetworkDTO4ps3 to set
	 */
	public void setPlayernetworkDTO4ps3(PlayernetworkDTO playernetworkDTO4ps3) {
		this.playernetworkDTO4ps3 = playernetworkDTO4ps3;
	}

	/**
	 * Gets the player game.
	 * 
	 * @return the playerGame
	 */
	public Set<PlayergameDTO> getPlayerGame() {
		return playerGame;
	}

	/**
	 * Sets the player game.
	 * 
	 * @param playerGame
	 *            the playerGame to set
	 */
	public void setPlayerGame(Set<PlayergameDTO> playerGame) {
		this.playerGame = playerGame;
	}

	/**
	 * Gets the session id.
	 * 
	 * @return the sessionId
	 */
	public String getSessionId() {
		return sessionId;
	}

	/**
	 * Sets the session id.
	 * 
	 * @param sessionId
	 *            the sessionId to set
	 */
	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

	/**
	 * Gets the balance.
	 * 
	 * @return the balance
	 */
	public Double getBalance() {
		return balance;
	}

	/**
	 * Sets the balance.
	 * 
	 * @param balance
	 *            the balance to set
	 */
	public void setBalance(Double balance) {
		this.balance = balance;
	}

	/**
	 * Gets the message count.
	 * 
	 * @return the messageCount
	 */
	public int getMessageCount() {
		return messageCount;
	}

	/**
	 * Sets the message count.
	 * 
	 * @param messageCount
	 *            the messageCount to set
	 */
	public void setMessageCount(int messageCount) {
		this.messageCount = messageCount;
	}

	/**
	 * Gets the offset.
	 * 
	 * @return the offset
	 */
	public int getOffset() {
		return offset;
	}

	/**
	 * Sets the offset.
	 * 
	 * @param offset
	 *            the offset to set
	 */
	public void setOffset(int offset) {
		this.offset = offset;
	}

	/**
	 * @return the isStateIllegal
	 */
	public boolean isStateIllegal() {
		return this.isStateIllegal;
	}

	/**
	 * @param isStateIllegal the isStateIllegal to set
	 */
	public void setStateIllegal(boolean isStateIllegal) {
		this.isStateIllegal = isStateIllegal;
	}


}
