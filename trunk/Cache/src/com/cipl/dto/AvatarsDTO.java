package com.cipl.dto;
/**
 * The Class AvatarsDTO.
 * 
 * @author Ankur
 * @see BaseDTO
 * @see DtoList Avatars entity.
 * @hibernate.mapping AvatarsDTO.hbm.xml
 */

public class AvatarsDTO extends BaseDTO implements DtoList {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = -6103884852953028721L;
    
    /** The image. */
    private byte[] image = new byte[4096];

    /**
     * Gets the description.
     * @return the description
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
     */
    public String getKey() {
	return String.valueOf(id);
    }

    /**
     * Gets the value.
     * 
     * @return the value
     */
    public String getValue() {
	return null;
    }

    /**
     * Sets the image.
     * 
     * @param image the image
     */
    public void setImage(byte[] image) {
	this.image = image;
    }

}