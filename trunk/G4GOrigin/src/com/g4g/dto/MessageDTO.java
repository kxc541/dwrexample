/**********************************************************
 * Copyright (c) 2008 - 2008, Askdigi, All rights reserved.
 * 
 **********************************************************/
package com.g4g.dto;

import java.util.Date;

/**
 * MessageDTO contains attribute for message entity.
 * 
 * @author Jigar Mistry
 */

public class MessageDTO extends BaseDTO {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -5336159110504840936L;

	private PlayerDTO playerByFromplayerid;
	private SkinDTO skin;
	private NotificationtypeDTO notificationtype;
	private PlayerDTO playerByToplayerid;
	private String subject;
	private String body;
	private Boolean isarchivedbysender;
	private Boolean isread;
	private Boolean isdeletedbysender;
	private Boolean isarchievedbyreciever;
	private Boolean isdeletedbyreciever;
	private Date createddate;
	private TwoplayermatchDTO matchid;
	private String randomid;

	// Property accessors

	/**
	 * Gets the randomid.
	 * 
	 * @return the randomid
	 */
	public String getRandomid() {
		return randomid;
	}

	/**
	 * Sets the randomid.
	 * 
	 * @param randomid
	 *            the new randomid
	 */
	public void setRandomid(String randomid) {
		this.randomid = randomid;
	}

	/**
	 * Gets the body.
	 * 
	 * @return the body
	 */
	public String getBody() {
		return this.body;
	}

	/**
	 * Gets the createddate.
	 * 
	 * @return the createddate
	 */
	public Date getCreateddate() {
		return createddate;
	}

	/**
	 * Gets the subject.
	 * 
	 * @return the subject
	 */
	public String getSubject() {
		return this.subject;
	}

	/**
	 * Checks if is isarchievedbyreciever.
	 * 
	 * @return true, if is isarchievedbyreciever
	 */
	public boolean isIsarchievedbyreciever() {
		return isarchievedbyreciever;
	}

	/**
	 * Checks if is isarchivedbysender.
	 * 
	 * @return true, if is isarchivedbysender
	 */
	public boolean isIsarchivedbysender() {
		return isarchivedbysender;
	}

	/**
	 * Checks if is isdeletedbyreciever.
	 * 
	 * @return true, if is isdeletedbyreciever
	 */
	public boolean isIsdeletedbyreciever() {
		return isdeletedbyreciever;
	}

	/**
	 * Checks if is isdeletedbysender.
	 * 
	 * @return true, if is isdeletedbysender
	 */
	public boolean isIsdeletedbysender() {
		return isdeletedbysender;
	}

	/**
	 * Checks if is isread.
	 * 
	 * @return true, if is isread
	 */
	public boolean isIsread() {
		return isread;
	}

	/**
	 * Sets the body.
	 * 
	 * @param body
	 *            the new body
	 */
	public void setBody(String body) {
		this.body = body;
	}

	/**
	 * Sets the createddate.
	 * 
	 * @param createddate
	 *            the new createddate
	 */
	public void setCreateddate(Date createddate) {
		this.createddate = createddate;
	}

	/**
	 * Sets the isarchievedbyreciever.
	 * 
	 * @param isarchievedbyreciever
	 *            the new isarchievedbyreciever
	 */
	public void setIsarchievedbyreciever(boolean isarchievedbyreciever) {
		this.isarchievedbyreciever = isarchievedbyreciever;
	}

	/**
	 * Sets the isarchivedbysender.
	 * 
	 * @param isarchivedbysender
	 *            the new isarchivedbysender
	 */
	public void setIsarchivedbysender(boolean isarchivedbysender) {
		this.isarchivedbysender = isarchivedbysender;
	}

	/**
	 * Sets the isdeletedbyreciever.
	 * 
	 * @param isdeletedbyreciever
	 *            the new isdeletedbyreciever
	 */
	public void setIsdeletedbyreciever(boolean isdeletedbyreciever) {
		this.isdeletedbyreciever = isdeletedbyreciever;
	}

	/**
	 * Sets the isdeletedbysender.
	 * 
	 * @param isdeletedbysender
	 *            the new isdeletedbysender
	 */
	public void setIsdeletedbysender(boolean isdeletedbysender) {
		this.isdeletedbysender = isdeletedbysender;
	}

	/**
	 * Sets the isread.
	 * 
	 * @param isread
	 *            the new isread
	 */
	public void setIsread(boolean isread) {
		this.isread = isread;
	}

	/**
	 * Sets the subject.
	 * 
	 * @param subject
	 *            the new subject
	 */
	public void setSubject(String subject) {
		this.subject = subject;
	}

	/**
	 * @return the playerByFromplayerid
	 */
	public PlayerDTO getPlayerByFromplayerid() {
		return this.playerByFromplayerid;
	}

	/**
	 * @param playerByFromplayerid
	 *            the playerByFromplayerid to set
	 */
	public void setPlayerByFromplayerid(PlayerDTO playerByFromplayerid) {
		this.playerByFromplayerid = playerByFromplayerid;
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
	 * @return the notificationtype
	 */
	public NotificationtypeDTO getNotificationtype() {
		return this.notificationtype;
	}

	/**
	 * @param notificationtype
	 *            the notificationtype to set
	 */
	public void setNotificationtype(NotificationtypeDTO notificationtype) {
		this.notificationtype = notificationtype;
	}

	/**
	 * @return the playerByToplayerid
	 */
	public PlayerDTO getPlayerByToplayerid() {
		return this.playerByToplayerid;
	}

	/**
	 * @param playerByToplayerid
	 *            the playerByToplayerid to set
	 */
	public void setPlayerByToplayerid(PlayerDTO playerByToplayerid) {
		this.playerByToplayerid = playerByToplayerid;
	}

	/**
	 * @return the isarchivedbysender
	 */
	public Boolean getIsarchivedbysender() {
		return this.isarchivedbysender;
	}

	/**
	 * @param isarchivedbysender
	 *            the isarchivedbysender to set
	 */
	public void setIsarchivedbysender(Boolean isarchivedbysender) {
		this.isarchivedbysender = isarchivedbysender;
	}

	/**
	 * @return the isread
	 */
	public Boolean getIsread() {
		return this.isread;
	}

	/**
	 * @param isread
	 *            the isread to set
	 */
	public void setIsread(Boolean isread) {
		this.isread = isread;
	}

	/**
	 * @return the isdeletedbysender
	 */
	public Boolean getIsdeletedbysender() {
		return this.isdeletedbysender;
	}

	/**
	 * @param isdeletedbysender
	 *            the isdeletedbysender to set
	 */
	public void setIsdeletedbysender(Boolean isdeletedbysender) {
		this.isdeletedbysender = isdeletedbysender;
	}

	/**
	 * @return the isarchievedbyreciever
	 */
	public Boolean getIsarchievedbyreciever() {
		return this.isarchievedbyreciever;
	}

	/**
	 * @param isarchievedbyreciever
	 *            the isarchievedbyreciever to set
	 */
	public void setIsarchievedbyreciever(Boolean isarchievedbyreciever) {
		this.isarchievedbyreciever = isarchievedbyreciever;
	}

	/**
	 * @return the isdeletedbyreciever
	 */
	public Boolean getIsdeletedbyreciever() {
		return this.isdeletedbyreciever;
	}

	/**
	 * @param isdeletedbyreciever
	 *            the isdeletedbyreciever to set
	 */
	public void setIsdeletedbyreciever(Boolean isdeletedbyreciever) {
		this.isdeletedbyreciever = isdeletedbyreciever;
	}

	/**
	 * @return the matchid
	 */
	public TwoplayermatchDTO getMatchid() {
		return this.matchid;
	}

	/**
	 * @param matchid the matchid to set
	 */
	public void setMatchid(TwoplayermatchDTO matchid) {
		this.matchid = matchid;
	}

	
}