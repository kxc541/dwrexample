/**
 * 
 */
package com.g4g.dto;

/**
 * @author jigartr
 *
 */
public class PlayerStatus {
	private String screenName;
	
	private boolean isOnline;

	/**
	 * @return the screenName
	 */
	public String getScreenName() {
		return this.screenName;
	}

	/**
	 * @param screenName the screenName to set
	 */
	public void setScreenName(String screenName) {
		this.screenName = screenName;
	}

	/**
	 * @return the isOnline
	 */
	public boolean isOnline() {
		return this.isOnline;
	}

	/**
	 * @param isOnline the isOnline to set
	 */
	public void setOnline(boolean isOnline) {
		this.isOnline = isOnline;
	}
}
