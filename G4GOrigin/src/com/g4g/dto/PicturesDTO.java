/**********************************************************
 * Copyright (c) 2008 - 2008, Askdigi, All rights reserved.
 * 
 **********************************************************/
package com.g4g.dto;



/**
 * The Class PicturesDTO contains attribute for picture entity.
 * 
 * @author Jigar Mistry
 */

public class PicturesDTO extends BaseDTO {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = -5251707699175719657L;

    /** The image. */
    private byte[] image;

    private PlayerDTO playerDTO;

    /**
	 * @return the playerDTO
	 */
	public PlayerDTO getPlayerDTO() {
		return this.playerDTO;
	}

	/**
	 * @param playerDTO the playerDTO to set
	 */
	public void setPlayerDTO(PlayerDTO playerDTO) {
		this.playerDTO = playerDTO;
	}



	/**
     * Gets the image.
     * 
     * @return the image
     */
    public byte[] getImage() {
	return image;
    }

  
   
    /**
     * Sets the image.
     * 
     * @param image the new image
     */
    public void setImage(byte[] image) {
	this.image = image;
    }

  
    
}
