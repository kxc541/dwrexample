package com.g4g.dto;

import java.util.List;

public class PlayNowOpenTournaments {
	TournamentGameDTO tournamentGameDTO;
	String scheduledDate;
	List<GameoptionsDTO> gameOptionsList;
	String encryptedId;

	public TournamentGameDTO getTournamentGameDTO() {
		return tournamentGameDTO;
	}
	public void setTournamentGameDTO(TournamentGameDTO tournamentGameDTO) {
		this.tournamentGameDTO = tournamentGameDTO;
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
	public String getEncryptedId() {
		return encryptedId;
	}
	public void setEncryptedId(String encryptedId) {
		this.encryptedId = encryptedId;
	}

}
