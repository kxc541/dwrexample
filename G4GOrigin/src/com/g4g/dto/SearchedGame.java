package com.g4g.dto;

import java.util.List;

public class SearchedGame {

	GameDTO gameDTO;

	boolean isNetworkAvailable;

	boolean isGameAvailable;

	List<TwoplayertournamentDTO> tournamentList;

	int size;

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public List<TwoplayertournamentDTO> getTournamentList() {
		return tournamentList;
	}

	public void setTournamentList(List<TwoplayertournamentDTO> tournamentList) {
		this.tournamentList = tournamentList;
	}

	public GameDTO getGameDTO() {
		return gameDTO;
	}

	public void setGameDTO(GameDTO gameDTO) {
		this.gameDTO = gameDTO;
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

}