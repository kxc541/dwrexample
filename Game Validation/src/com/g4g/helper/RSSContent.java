package com.g4g.helper;

/**
 * 
 * @author Jigar Mistry
 */

import java.util.Date;

public class RSSContent {
	private Date date;
	
	private String game;
	
	private String[] gamerTags;
	
	private String[][] players;

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getGame() {
		return game;
	}

	public void setGame(String game) {
		this.game = game;
	}

	public String[] getGamerTags() {
		return gamerTags;
	}

	public void setGamerTags(String[] gamerTags) {
		this.gamerTags = gamerTags;
	}

	public String[][] getPlayers() {
		return players;
	}

	public void setPlayers(String[][] players) {
		this.players = players;
	}
}
