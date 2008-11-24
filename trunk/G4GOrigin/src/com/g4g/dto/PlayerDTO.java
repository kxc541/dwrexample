/**********************************************************
 * Copyright (c) 2008 - 2008, Askdigi, All rights reserved.
 * 
 **********************************************************/
package com.g4g.dto;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.g4g.utils.DataUtil;

// TODO: Auto-generated Javadoc
/**
 * PlayerDTO contains attribute for player entity.
 * 
 * @author Pratik
 */

public class PlayerDTO extends BaseDTO {

	// Fields
	
	/**
	 * Instantiates a new player dto.
	 * 
	 * @param id the id
	 */
	public PlayerDTO(int id) {
		this.id = id;
	}
	
	/**
	 * 
	 */
	public PlayerDTO() {
		super();
	}
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/** The twoplayermatchs for playeroneid. */
	private Set<TwoplayermatchDTO> twoplayermatchsForPlayeroneid = new HashSet<TwoplayermatchDTO>(0);
	
	/** The twoplayermatchs for playertwoid. */
	private Set<TwoplayermatchDTO> twoplayermatchsForPlayertwoid = new HashSet<TwoplayermatchDTO>(0);
	
	/** The playerims. */
	private Set<PlayerimDTO> playerims = new HashSet<PlayerimDTO>(0);
	
	/** The pictureses. */
	private Set<PicturesDTO> pictureses = new HashSet<PicturesDTO>(0);
	
	/** The skin. */
	private SkinDTO skin;
	
	/** The avatars. */
	private AvatarsDTO avatars;
	
	/** The timezone. */
	private TimeZoneDTO timezone;
	
	/** The screenname. */
	private String screenname;
	
	/** The loginname. */
	private String loginname;
	
	/** The emailaddress. */
	private String emailaddress;
	
	/** The message. */
	private String message;
	
	/** The story. */
	private String story;
	
	/** The availability. */
	private String availability;
	
	/** The averageamount. */
	private Integer averageamount;
	
	/** The prefaliases. */
	private Boolean prefaliases;
	
	/** The prefsysmail. */
	private Boolean prefsysmail;
	
	/** The prefdisplayrecord. */
	private Boolean prefdisplayrecord;
	
	/** The prefdisplaycontacts. */
	private Boolean prefdisplaycontacts;
	
	/** The recordwins. */
	private Integer recordwins;
	
	/** The recordlosses. */
	private Integer recordlosses;
	
	/** The creationdate. */
	private Date creationdate = DataUtil.todayGMT();
	
	/** The lastupdatedate. */
	private Date lastupdatedate;
	
	/** The lastlogintime. */
	private Date lastlogintime;
	
	/** The phone. */
	private String phone;
	
	/** The isonline. */
	private Boolean isonline;
	
	/** The reputation. */
	private Integer reputation;
	
	/** The pref contact info. */
	private Boolean prefContactInfo;
	
	/** The picture id. */
	private Integer pictureId;
	

	

	/**
	 * @return the pictureId
	 */
	public Integer getPictureId() {
		return this.pictureId;
	}

	/**
	 * @param pictureId
	 *            the pictureId to set
	 */
	public void setPictureId(Integer pictureId) {
		this.pictureId = pictureId;
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
	 *            the screenname to set
	 */
	public void setScreenname(String screenname) {
		this.screenname = screenname;
	}

	/**
	 * Gets the loginname.
	 * 
	 * @return the loginname
	 */
	public String getLoginname() {
		return loginname;
	}

	/**
	 * Sets the loginname.
	 * 
	 * @param loginname
	 *            the loginname to set
	 */
	public void setLoginname(String loginname) {
		this.loginname = loginname;
	}

	/**
	 * Gets the emailaddress.
	 * 
	 * @return the emailaddress
	 */
	public String getEmailaddress() {
		return emailaddress;
	}

	/**
	 * Sets the emailaddress.
	 * 
	 * @param emailaddress
	 *            the emailaddress to set
	 */
	public void setEmailaddress(String emailaddress) {
		this.emailaddress = emailaddress;
	}

	/**
	 * Gets the message.
	 * 
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * Sets the message.
	 * 
	 * @param message
	 *            the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}

	/**
	 * Gets the story.
	 * 
	 * @return the story
	 */
	public String getStory() {
		return story;
	}

	/**
	 * Sets the story.
	 * 
	 * @param story
	 *            the story to set
	 */
	public void setStory(String story) {
		this.story = story;
	}

	/**
	 * Gets the availability.
	 * 
	 * @return the availability
	 */
	public String getAvailability() {
		return availability;
	}

	/**
	 * Sets the availability.
	 * 
	 * @param availability
	 *            the availability to set
	 */
	public void setAvailability(String availability) {
		this.availability = availability;
	}

	/**
	 * Gets the averageamount.
	 * 
	 * @return the averageamount
	 */
	public Integer getAverageamount() {
		return averageamount;
	}

	/**
	 * Sets the averageamount.
	 * 
	 * @param averageamount
	 *            the averageamount to set
	 */
	public void setAverageamount(Integer averageamount) {
		this.averageamount = averageamount;
	}

	/**
	 * Gets the prefaliases.
	 * 
	 * @return the prefaliases
	 */
	public Boolean getPrefaliases() {
		return prefaliases;
	}

	/**
	 * Sets the prefaliases.
	 * 
	 * @param prefaliases
	 *            the prefaliases to set
	 */
	public void setPrefaliases(Boolean prefaliases) {
		this.prefaliases = prefaliases;
	}

	/**
	 * Gets the prefsysmail.
	 * 
	 * @return the prefsysmail
	 */
	public Boolean getPrefsysmail() {
		return prefsysmail;
	}

	/**
	 * Sets the prefsysmail.
	 * 
	 * @param prefsysmail
	 *            the prefsysmail to set
	 */
	public void setPrefsysmail(Boolean prefsysmail) {
		this.prefsysmail = prefsysmail;
	}

	/**
	 * Gets the prefdisplayrecord.
	 * 
	 * @return the prefdisplayrecord
	 */
	public Boolean getPrefdisplayrecord() {
		return prefdisplayrecord;
	}

	/**
	 * Sets the prefdisplayrecord.
	 * 
	 * @param prefdisplayrecord
	 *            the prefdisplayrecord to set
	 */
	public void setPrefdisplayrecord(Boolean prefdisplayrecord) {
		this.prefdisplayrecord = prefdisplayrecord;
	}

	/**
	 * Gets the prefdisplaycontacts.
	 * 
	 * @return the prefdisplaycontacts
	 */
	public Boolean getPrefdisplaycontacts() {
		return prefdisplaycontacts;
	}

	/**
	 * Sets the prefdisplaycontacts.
	 * 
	 * @param prefdisplaycontacts
	 *            the prefdisplaycontacts to set
	 */
	public void setPrefdisplaycontacts(Boolean prefdisplaycontacts) {
		this.prefdisplaycontacts = prefdisplaycontacts;
	}

	/**
	 * Gets the recordwins.
	 * 
	 * @return the recordwins
	 */
	public Integer getRecordwins() {
		return recordwins;
	}

	/**
	 * Sets the recordwins.
	 * 
	 * @param recordwins
	 *            the recordwins to set
	 */
	public void setRecordwins(Integer recordwins) {
		this.recordwins = recordwins;
	}

	/**
	 * Gets the recordlosses.
	 * 
	 * @return the recordlosses
	 */
	public Integer getRecordlosses() {
		return recordlosses;
	}

	/**
	 * Sets the recordlosses.
	 * 
	 * @param recordlosses
	 *            the recordlosses to set
	 */
	public void setRecordlosses(Integer recordlosses) {
		this.recordlosses = recordlosses;
	}

	/**
	 * Gets the creationdate.
	 * 
	 * @return the creationdate
	 */
	public Date getCreationdate() {
		return creationdate;
	}

	/**
	 * Sets the creationdate.
	 * 
	 * @param creationdate
	 *            the creationdate to set
	 */
	public void setCreationdate(Date creationdate) {
		this.creationdate = creationdate;
	}

	/**
	 * Gets the lastupdatedate.
	 * 
	 * @return the lastupdatedate
	 */
	public Date getLastupdatedate() {
		return lastupdatedate;
	}

	/**
	 * Sets the lastupdatedate.
	 * 
	 * @param lastupdatedate
	 *            the lastupdatedate to set
	 */
	public void setLastupdatedate(Date lastupdatedate) {
		this.lastupdatedate = lastupdatedate;
	}

	/**
	 * Gets the lastlogintime.
	 * 
	 * @return the lastlogintime
	 */
	public Date getLastlogintime() {
		return lastlogintime;
	}

	/**
	 * Sets the lastlogintime.
	 * 
	 * @param lastlogintime
	 *            the lastlogintime to set
	 */
	public void setLastlogintime(Date lastlogintime) {
		this.lastlogintime = lastlogintime;
	}

	/**
	 * Gets the phone.
	 * 
	 * @return the phone
	 */
	public String getPhone() {
		return phone;
	}

	/**
	 * Sets the phone.
	 * 
	 * @param phone
	 *            the phone to set
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}

	/**
	 * Checks if is isonline.
	 * 
	 * @return the isonline
	 */
	public boolean isIsonline() {
		return isonline;
	}

	/**
	 * Sets the isonline.
	 * 
	 * @param isonline
	 *            the isonline to set
	 */
	public void setIsonline(boolean isonline) {
		this.isonline = isonline;
	}

	/**
	 * Checks if is pref contact info.
	 * 
	 * @return the prefContactInfo
	 */
	public boolean isPrefContactInfo() {
		return prefContactInfo;
	}

	/**
	 * Sets the pref contact info.
	 * 
	 * @param prefContactInfo
	 *            the prefContactInfo to set
	 */
	public void setPrefContactInfo(boolean prefContactInfo) {
		this.prefContactInfo = prefContactInfo;
	}

	/**
	 * Gets the reputation.
	 * 
	 * @return the reputation
	 */
	public int getReputation() {
		return reputation;
	}

	/**
	 * Sets the reputation.
	 * 
	 * @param reputation
	 *            the reputation to set
	 */
	public void setReputation(int reputation) {
		this.reputation = reputation;
	}

	/**
	 * @return the skin
	 */
	public SkinDTO getSkin() {
		return this.skin;
	}

	/**
	 * @param skin
	 *            the skin to set
	 */
	public void setSkin(SkinDTO skin) {
		this.skin = skin;
	}

	/**
	 * @return the avatars
	 */
	public AvatarsDTO getAvatars() {
		return this.avatars;
	}

	/**
	 * @param avatars
	 *            the avatars to set
	 */
	public void setAvatars(AvatarsDTO avatars) {
		this.avatars = avatars;
	}

	/**
	 * @return the timezone
	 */
	public TimeZoneDTO getTimezone() {
		return this.timezone;
	}

	/**
	 * @param timezone
	 *            the timezone to set
	 */
	public void setTimezone(TimeZoneDTO timezone) {
		this.timezone = timezone;
	}

	/**
	 * @return the isonline
	 */
	public Boolean getIsonline() {
		return this.isonline;
	}

	/**
	 * @param isonline
	 *            the isonline to set
	 */
	public void setIsonline(Boolean isonline) {
		this.isonline = isonline;
	}

	/**
	 * @return the prefContactInfo
	 */
	public Boolean getPrefContactInfo() {
		return this.prefContactInfo;
	}

	/**
	 * @param prefContactInfo
	 *            the prefContactInfo to set
	 */
	public void setPrefContactInfo(Boolean prefContactInfo) {
		this.prefContactInfo = prefContactInfo;
	}

	/**
	 * @return the twoplayermatchsForPlayeroneid
	 */
	public Set<TwoplayermatchDTO> getTwoplayermatchsForPlayeroneid() {
		return this.twoplayermatchsForPlayeroneid;
	}

	/**
	 * @param twoplayermatchsForPlayeroneid
	 *            the twoplayermatchsForPlayeroneid to set
	 */
	public void setTwoplayermatchsForPlayeroneid(
			Set<TwoplayermatchDTO> twoplayermatchsForPlayeroneid) {
		this.twoplayermatchsForPlayeroneid = twoplayermatchsForPlayeroneid;
	}

	/**
	 * @return the twoplayermatchsForPlayertwoid
	 */
	public Set<TwoplayermatchDTO> getTwoplayermatchsForPlayertwoid() {
		return this.twoplayermatchsForPlayertwoid;
	}

	/**
	 * @param twoplayermatchsForPlayertwoid
	 *            the twoplayermatchsForPlayertwoid to set
	 */
	public void setTwoplayermatchsForPlayertwoid(
			Set<TwoplayermatchDTO> twoplayermatchsForPlayertwoid) {
		this.twoplayermatchsForPlayertwoid = twoplayermatchsForPlayertwoid;
	}

	/**
	 * @return the playerims
	 */
	public Set<PlayerimDTO> getPlayerims() {
		return this.playerims;
	}

	/**
	 * @param playerims
	 *            the playerims to set
	 */
	public void setPlayerims(Set<PlayerimDTO> playerims) {
		this.playerims = playerims;
	}

	/**
	 * @return the pictureses
	 */
	public Set<PicturesDTO> getPictureses() {
		return this.pictureses;
	}

	/**
	 * @param pictureses
	 *            the pictureses to set
	 */
	public void setPictureses(Set<PicturesDTO> pictureses) {
		this.pictureses = pictureses;
	}

	/**
	 * @param reputation
	 *            the reputation to set
	 */
	public void setReputation(Integer reputation) {
		this.reputation = reputation;
	}

	// Property accessors

}