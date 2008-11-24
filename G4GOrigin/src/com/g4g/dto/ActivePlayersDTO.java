/**********************************************************
 * Copyright (c) 2008 - 2008, Askdigi, All rights reserved.
 * 
 **********************************************************/
package com.g4g.dto;

/**
 * The Class ActivePlayersDTO contains attribute for active players widget.
 * 
 * @author Punam
 */
public class ActivePlayersDTO {

	/** The playerid. */
	private int playerid;

	/** The screenname. */
	private String screenname;

	/** The max matchcount. */
	private int maxMatchcount;
	
	private String location;
	
	private int avatarid;

	private int pictureid;
	
	private boolean isOnline;

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	/**
	 * Gets the playerid.
	 * 
	 * @return the playerid
	 */
	public int getPlayerid() {
		return playerid;
	}

	/**
	 * Sets the playerid.
	 * 
	 * @param playerid
	 *            the new playerid
	 */
	public void setPlayerid(int playerid) {
		this.playerid = playerid;
	}

	/**
	 * Gets the screenname.
	 * 
	 * @return the screenname
	 */
	public String getScreenname() {
		return screenname;
	}

	/**
	 * Sets the screenname.
	 * 
	 * @param screenname
	 *            the new screenname
	 */
	public void setScreenname(String screenname) {
		this.screenname = screenname;
	}

	/**
	 * Gets the max matchcount.
	 * 
	 * @return the max matchcount
	 */
	public int getMaxMatchcount() {
		return maxMatchcount;
	}

	/**
	 * Sets the max matchcount.
	 * 
	 * @param maxMatchcount
	 *            the new max matchcount
	 */
	public void setMaxMatchcount(int maxMatchcount) {
		this.maxMatchcount = maxMatchcount;
	}

	public int getAvatarid() {
		return avatarid;
	}

	public void setAvatarid(int avatarid) {
		this.avatarid = avatarid;
	}

	public int getPictureid() {
		return pictureid;
	}

	public void setPictureid(int pictureid) {
		this.pictureid = pictureid;
	}

	public boolean isOnline() {
		return isOnline;
	}

	public void setOnline(boolean isOnline) {
		this.isOnline = isOnline;
	}

}
