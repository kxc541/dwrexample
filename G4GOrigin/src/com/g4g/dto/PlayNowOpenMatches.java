package com.g4g.dto;

import java.util.List;

public class PlayNowOpenMatches {
	PlayNowDTO playNowDTO;
	String scheduledDate;
	List<GameoptionsDTO> gameOptionsList;
	TwoplayermatchDTO twoplayermatchDTO;
	String encryptedId;

	public PlayNowDTO getPlayNowDTO() {
		return playNowDTO;
	}
	public void setPlayNowDTO(PlayNowDTO playNowDTO) {
		this.playNowDTO = playNowDTO;
	}
	public String getScheduledDate() {
		return scheduledDate;
	}
	public void setScheduledDate(String scheduledDate) {
		this.scheduledDate = scheduledDate;
	}
	public List<GameoptionsDTO> getGameOptionsList() {
		return gameOptionsList;
	}
	public void setGameOptionsList(List<GameoptionsDTO> gameOptionsList) {
		this.gameOptionsList = gameOptionsList;
	}
	public TwoplayermatchDTO getTwoplayermatchDTO() {
		return twoplayermatchDTO;
	}
	public void setTwoplayermatchDTO(TwoplayermatchDTO twoplayermatchDTO) {
		this.twoplayermatchDTO = twoplayermatchDTO;
	}
	public String getEncryptedId() {
		return encryptedId;
	}
	public void setEncryptedId(String encryptedId) {
		this.encryptedId = encryptedId;
	}
}
