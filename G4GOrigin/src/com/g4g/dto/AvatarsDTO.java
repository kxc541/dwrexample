/**********************************************************
* className.java : 
*
* Created by Pratik
* Last modified Date: 18 Apr .08 by Pratik
* Revision: 0.1
* Version : 0.0.8
* Copyright (c) 2008 - 2008 Askdigi, All rights reserved.
**********************************************************/
package com.g4g.dto;

import java.util.HashSet;
import java.util.Set;

/**
 * AvatarDTO is the DTO class for Avatar table in database. It contains image
 * attribute. Avatar table is used to store default avatar images provided to
 * players.
 * 
 * @author Pratik
 * @see BaseDTO
 * @see DtoList Avatars entity.
 * @hibernate.mapping AvatarsDTO.hbm.xml
 */

public class AvatarsDTO extends BaseDTO implements DtoList {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -6103884852953028721L;

	/** The image. This attribute contains byte array of avatar image.*/
	private byte[] image = new byte[4096];
	
	/** The players. Refer's to playerDTO.*/
	private Set<PlayerDTO> players = new HashSet<PlayerDTO>(0);

	
	/**
	 * @return the players
	 */
	public Set<PlayerDTO> getPlayers() {
		return this.players;
	}

	/**
	 * @param players the players to set
	 */
	public void setPlayers(Set<PlayerDTO> players) {
		this.players = players;
	}

	/**
	 * Gets the description.
	 * 
	 * @return the description
	 * 
	 * @see com.g4g.dto.DtoList#getDescription()
	 */
	public String getDescription() {
		return null;
	}

	/**
	 * Gets the image.
	 * 
	 * @return byte[]
	 */
	public byte[] getImage() {
		return image;
	}

	/**
	 * Gets the key.
	 * 
	 * @return the key
	 * 
	 * @see com.g4g.dto.DtoList#getKey()
	 */
	public String getKey() {
		return String.valueOf(id);
	}

	/**
	 * Gets the value.
	 * 
	 * @return the value
	 * 
	 * @see com.g4g.dto.DtoList#getValue()
	 */
	public String getValue() {
		return null;
	}

	/**
	 * Sets the image.
	 * 
	 * @param image
	 *            the image
	 */
	public void setImage(byte[] image) {
		this.image = image;
	}

}