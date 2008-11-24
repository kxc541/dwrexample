package com.g4g.dto;

import com.impessa.worldgaming.client.User;

public class SearchedPeople {
	PlayerDTO playerDTO;

	User user;

	boolean isFriend;

	public PlayerDTO getPlayerDTO() {
		return playerDTO;
	}

	public void setPlayerDTO(PlayerDTO playerDTO) {
		this.playerDTO = playerDTO;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public boolean isFriend() {
		return isFriend;
	}

	public void setFriend(boolean isFriend) {
		this.isFriend = isFriend;
	}
}
