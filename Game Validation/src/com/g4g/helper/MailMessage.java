package com.g4g.helper;

import java.util.Date;

/**
 * All getter - setter for Message. 
 * 
 * @author Jigar Mistry
 */

public class MailMessage {
	private String from;
	
	private String subject;
	
	private String body;

	private Date recievedDate;
	
	public Date getRecievedDate() {
		return recievedDate;
	}

	public void setRecievedDate(Date recievedDate) {
		this.recievedDate = recievedDate;
	}

	public MailMessage() {
		
	}
	
	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}
}
