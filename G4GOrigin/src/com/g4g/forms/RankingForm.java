/**********************************************************
 * RankingForm.java : 
 *
 * Created by Punam
 * Last modified Date: 18 Apr .08 by Punam
 * Revision: 0.1
 * Version : 0.0.8
 * Copyright (c) 2008 - 2008 Askdigi, All rights reserved.
 **********************************************************/
package com.g4g.forms;

import static com.g4g.utils.G4GConstants.COLON_WITH_SPACES;
import static com.g4g.utils.G4GConstants.CALLINGMETHOD;
import static com.g4g.utils.G4GConstants.CURRENTMETHOD;
import static com.g4g.utils.G4GConstants.DASHES;
import static com.g4g.utils.G4GConstants.DISPLAY_RANKING_ERRORS;
import static com.g4g.utils.G4GConstants.ENDED;
import static com.g4g.utils.G4GConstants.MATCHID;
import static com.g4g.utils.G4GConstants.STARTED;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Level;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;

import com.g4g.dto.BaseDTO;
import com.g4g.utils.AuditUtil;
import com.g4g.utils.DataUtil;
import com.g4g.utils.G4GConstants;

/**
 * The Class RankingForm contains properties for feedback and reputation.
 * RankingFormAction contains the RankingForm. it is used in
 * feedbackandReputationPage. It contains attributes like player,matchid,
 * feedbackcomment.
 * 
 * @struts.form name="rankingForm"
 * @author Punam
 */
public class RankingForm extends BaseForm {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -4370896893895851773L;

	private int feedbackReputationId = G4GConstants.ZERO;
	
	/** The playerid. */

	private String playerid;

	/** The matchid. */
	private String matchid;

	/** The reputation. */
	private String reputation = G4GConstants.THREE;

	/** The feedbackcomment. */
	private String feedbackcomment;

	/** The feedbackdate. */
	private Date feedbackdate;

	/**
	 * @see com.g4g.forms.BaseForm#getDTO()
	 */
	@Override
	public BaseDTO getDTO() {
		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,
				new StringBuffer(this.getClass().getName()).append(
						COLON_WITH_SPACES).append(CALLINGMETHOD)
						.append(DataUtil.getCallingMethod()).append(
								CURRENTMETHOD).append(
								DataUtil.getCurrentMethod()).append(
								DASHES).append(
								STARTED).toString(), Level.INFO);
		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,
				new StringBuffer(this.getClass().getName()).append(
						COLON_WITH_SPACES).append(CALLINGMETHOD)
						.append(DataUtil.getCallingMethod()).append(
								CURRENTMETHOD).append(
								DataUtil.getCurrentMethod()).append(
								DASHES).append(ENDED)
						.toString(), Level.INFO);
		return null;
	}

	/**
	 * Gets the feedbackcomment.
	 * 
	 * @return the feedbackcomment
	 */
	public String getFeedbackcomment() {
		return feedbackcomment;
	}

	/**
	 * Gets the feedbackdate.
	 * 
	 * @return the feedbackdate
	 */
	public Date getFeedbackdate() {
		return feedbackdate;
	}

	/**
	 * Gets the matchid.
	 * 
	 * @return the matchid
	 */
	public String getMatchid() {
		return matchid;
	}

	/**
	 * Gets the playerid.
	 * 
	 * @return the playerid
	 */
	public String getPlayerid() {
		return playerid;
	}

	/**
	 * Gets the reputation.
	 * 
	 * @return the reputation
	 */
	public String getReputation() {
		return reputation;
	}

	/**
	 * @see com.g4g.forms.BaseForm#populate(com.g4g.dto.BaseDTO)
	 */
	@Override
	public void populate(BaseDTO baseDto) {
		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,
				new StringBuffer(this.getClass().getName()).append(
						COLON_WITH_SPACES).append(CALLINGMETHOD)
						.append(DataUtil.getCallingMethod()).append(
								CURRENTMETHOD).append(
								DataUtil.getCurrentMethod()).append(
								DASHES).append(
								STARTED).toString(), Level.INFO);
		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,
				new StringBuffer(this.getClass().getName()).append(
						COLON_WITH_SPACES).append(CALLINGMETHOD)
						.append(DataUtil.getCallingMethod()).append(
								CURRENTMETHOD).append(
								DataUtil.getCurrentMethod()).append(
								DASHES).append(ENDED)
						.toString(), Level.INFO);
	}

	/**
	 * Sets the feedbackcomment.
	 * 
	 * @param feedbackcomment
	 *            the new feedbackcomment
	 */
	public void setFeedbackcomment(String feedbackcomment) {
		this.feedbackcomment = feedbackcomment;
	}

	/**
	 * Sets the feedbackdate.
	 * 
	 * @param feedbackdate
	 *            the new feedbackdate
	 */
	public void setFeedbackdate(Date feedbackdate) {
		this.feedbackdate = feedbackdate;
	}

	/**
	 * Sets the matchid.
	 * 
	 * @param matchid
	 *            the new matchid
	 */
	public void setMatchid(String matchid) {
		this.matchid = matchid;
	}

	/**
	 * Sets the playerid.
	 * 
	 * @param playerid
	 *            the new playerid
	 */
	public void setPlayerid(String playerid) {
		this.playerid = playerid;
	}

	/**
	 * Sets the reputation.
	 * 
	 * @param reputation
	 *            the new reputation
	 */
	public void setReputation(String reputation) {
		this.reputation = reputation;
	}

	/**
	 * @see org.apache.struts.validator.ValidatorForm#validate(org.apache.struts.action.ActionMapping,
	 *      javax.servlet.http.HttpServletRequest)
	 */
	@Override
	public ActionErrors validate(ActionMapping mapping,
			HttpServletRequest request) {
		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,
				new StringBuffer(this.getClass().getName()).append(
						COLON_WITH_SPACES).append(CALLINGMETHOD)
						.append(DataUtil.getCallingMethod()).append(
								CURRENTMETHOD).append(
								DataUtil.getCurrentMethod()).append(
								DASHES).append(
								STARTED).toString(), Level.INFO);

		ActionErrors errors = super.validate(mapping, request);

		if (errors != null) {
			mapping.getInputForward().setPath(new StringBuffer(DISPLAY_RANKING_ERRORS).append(request.getParameter(MATCHID)).toString());
		}

		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,
				new StringBuffer(this.getClass().getName()).append(
						COLON_WITH_SPACES).append(CALLINGMETHOD)
						.append(DataUtil.getCallingMethod()).append(
								CURRENTMETHOD).append(
								DataUtil.getCurrentMethod()).append(
								DASHES).append(ENDED)
						.toString(), Level.INFO);
		return errors;
	}

	/**
	 * @return the feedbackReputationId
	 */
	public int getFeedbackReputationId() {
		return this.feedbackReputationId;
	}

	/**
	 * @param feedbackReputationId the feedbackReputationId to set
	 */
	public void setFeedbackReputationId(int feedbackReputationId) {
		this.feedbackReputationId = feedbackReputationId;
	}
}
