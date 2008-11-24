package com.g4g.dto;

import java.util.Date;
import java.util.List;

public class SearchedTournament {
	GameTournamentChallengeDTO gameTournamentChallengeDTO;

	String scheduledStartDate;

	TwoplayertournamentDTO twoplayertournamentDTO;

	boolean isNetworkAvailable;

	boolean isGameAvailable;

	int noOfPlayers;

	List<GameoptionsDTO> gameOptionsList;

	public GameTournamentChallengeDTO getGameTournamentChallengeDTO() {
		return gameTournamentChallengeDTO;
	}

	public void setGameTournamentChallengeDTO(
			GameTournamentChallengeDTO gameTournamentChallengeDTO) {
		this.gameTournamentChallengeDTO = gameTournamentChallengeDTO;
	}

	public TwoplayertournamentDTO getTwoplayertournamentDTO() {
		return twoplayertournamentDTO;
	}

	public void setTwoplayertournamentDTO(
			TwoplayertournamentDTO twoplayertournamentDTO) {
		this.twoplayertournamentDTO = twoplayertournamentDTO;
	}

	public boolean isNetworkAvailable() {
		return isNetworkAvailable;
	}

	public void setNetworkAvailable(boolean isNetworkAvailable) {
		this.isNetworkAvailable = isNetworkAvailable;
	}

	public boolean isGameAvailable() {
		return isGameAvailable;
	}

	public void setGameAvailable(boolean isGameAvailable) {
		this.isGameAvailable = isGameAvailable;
	}

	public int getNoOfPlayers() {
		return noOfPlayers;
	}

	public void setNoOfPlayers(int noOfPlayers) {
		this.noOfPlayers = noOfPlayers;
	}

	public List<GameoptionsDTO> getGameOptionsList() {
		return gameOptionsList;
	}

	public void setGameOptionsList(List<GameoptionsDTO> gameOptionsList) {
		this.gameOptionsList = gameOptionsList;
	}

	public String getScheduledStartDate() {
		return scheduledStartDate;
	}

	public void setScheduledStartDate(String scheduledStartDate) {
		this.scheduledStartDate = scheduledStartDate;
	}
}
