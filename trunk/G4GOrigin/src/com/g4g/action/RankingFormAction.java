/**********************************************************
 * RankingFormAction.java : 
 *
 * Created by Punam
 * Last modified Date: 21 Apr .08 by Punam
 * Revision: 0.1
 * Version : 0.0.8
 * Copyright (c) 2008 - 2008 Askdigi, All rights reserved.
 **********************************************************/
package com.g4g.action;

import static com.g4g.utils.G4GConstants.CALLINGMETHOD;
import static com.g4g.utils.G4GConstants.CURRENTMETHOD;
import static com.g4g.utils.G4GConstants.DASHES;
import static com.g4g.utils.G4GConstants.DISPLAY_STARTS;
import static com.g4g.utils.G4GConstants.HOMEPAGE;
import static com.g4g.utils.G4GConstants.NONE;
import static com.g4g.utils.G4GConstants.SUBMIT_STARTS;
import static com.g4g.utils.G4GConstants.USERDTO;
import static com.g4g.utils.G4GConstants.USER_NOT_RECOGNIZE;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Level;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.g4g.delegator.FeedbackServiceDelegator;
import com.g4g.delegator.GameServiceDelegator;
import com.g4g.delegator.PlayerServiceDelegator;
import com.g4g.delegator.TwoPlayerMatchServiceDelegator;
import com.g4g.dto.FeedbackreputationDTO;
import com.g4g.dto.GameDTO;
import com.g4g.dto.PlayerDTO;
import com.g4g.dto.SearchCriteria;
import com.g4g.dto.TwoplayermatchDTO;
import com.g4g.dto.UserDTO;
import com.g4g.forms.BaseForm;
import com.g4g.forms.RankingForm;
import com.g4g.utils.AuditUtil;
import com.g4g.utils.DataUtil;
import com.g4g.utils.G4GConstants;

/**
 * The RankingFormAction Class contains the page to display after match.So, the
 * players can given their feedback and comments and rank to opposite other. it
 * will contain match details and information about opposite player.The rank and
 * feedback provided by other player will be stored in database.<BR>
 * XDoclet definition:.
 * 
 * @struts.action path="/displayRanking" name="rankingForm" validate="false"
 *                scope="request"
 * @struts.action-forward name="success" path="/WebContent/rankingform.jsp"
 */
public class RankingFormAction extends BaseAction {

	/**
	 * The display method shows the information to display on ranking page.As
	 * the information about another player, match details.
	 * 
	 * @see com.g4g.action.BaseAction#display(org.apache.struts.action.ActionMapping,
	 *      com.g4g.forms.BaseForm, java.lang.String,
	 *      javax.servlet.http.HttpServletRequest,
	 *      javax.servlet.http.HttpServletResponse)
	 */
	@Override
	public ActionForward display(ActionMapping mapping, BaseForm form,String operation, HttpServletRequest request,HttpServletResponse response) {
		AuditUtil.getInstance().writeLog(AuditUtil.FILE_TYPE_G4G, new StringBuffer(this.getClass().getName()).append(G4GConstants.CALLINGMETHOD) .append( DataUtil.getCallingMethod()) .append( G4GConstants.DISPLAY_STARTS ).append( G4GConstants.DASHES ).append( request.getSession().getId()).toString(),Level.INFO);
		if (request.getSession().getAttribute(USERDTO) == null) {
			AuditUtil.getInstance().writeLog(AuditUtil.FILE_TYPE_G4G,new StringBuffer(this.getClass().getName()).append(CALLINGMETHOD ).append( DataUtil.getCallingMethod() ).append( DISPLAY_STARTS ).append( DASHES ).append(
						 USER_NOT_RECOGNIZE ).append(
							request.getSession().getId()).toString() , Level.INFO);
			return mapping.findForward(HOMEPAGE);
		}
		int otherPlayerId;
		UserDTO userDTO =DataUtil.getUserDTO(request);
		int matchId = 0;
		if (request.getParameter(G4GConstants.MATCHID) != null) {
			matchId = Integer.parseInt(request.getParameter(G4GConstants.MATCHID));
		}
		SearchCriteria searchMatchCriteria = new SearchCriteria();
		searchMatchCriteria.setAttribute(G4GConstants.ID, matchId);
		AuditUtil.getInstance().writeLog(AuditUtil.FILE_TYPE_G4G, new StringBuffer(this.getClass().getName()).append( CALLINGMETHOD ).append( DataUtil.getCallingMethod() ).append(  DASHES ).append(
						 G4GConstants.GETTING_PLAYERMATCH_RANKING ).append(
							request.getSession().getId()).toString());
		List<TwoplayermatchDTO> matchList = TwoPlayerMatchServiceDelegator.getList(searchMatchCriteria);
		TwoplayermatchDTO twoplayermatchDTO = matchList.get(matchList.size() - 1);
		request.setAttribute(G4GConstants.TWOPLAYERMATCHDTO, twoplayermatchDTO);

		request.setAttribute(G4GConstants.TWOPLAYERTOURNAMENTDTO,twoplayermatchDTO.getTwoplayertournament());

		AuditUtil.getInstance().writeLog(AuditUtil.FILE_TYPE_G4G, new StringBuffer(this.getClass().getName()).append( CALLINGMETHOD ).append( DataUtil.getCallingMethod() ).append(  DASHES ).append(
						 G4GConstants.GETTING_MATCHGAME ).append(
							request.getSession().getId()).toString());
		GameDTO gameDTO = GameServiceDelegator.getGame(twoplayermatchDTO.getTwoplayertournament().getGameDTO().getId());
		request.setAttribute(G4GConstants.GAMEDTO, gameDTO);
		otherPlayerId = (twoplayermatchDTO.getPlayeroneid().getId() == userDTO.getPlayerDTO().getId()) ? twoplayermatchDTO.getPlayertwoid().getId(): twoplayermatchDTO.getPlayeroneid().getId();
		AuditUtil.getInstance().writeLog(AuditUtil.FILE_TYPE_G4G, new StringBuffer(this.getClass().getName()).append( CALLINGMETHOD ).append( DataUtil.getCallingMethod() ).append(  DASHES ).append(
						 G4GConstants.GETTING_OPPONENT_INFO ).append(
							request.getSession().getId()).toString());
		PlayerDTO playerDTO = PlayerServiceDelegator.getPlayer(otherPlayerId);
		request.setAttribute(G4GConstants.PLAYERDTO, playerDTO);
		
		FeedbackreputationDTO feedbackreputationDTO = FeedbackServiceDelegator.getReputation(otherPlayerId, twoplayermatchDTO.getId());
		RankingForm rankingForm = (RankingForm) form;
		
		if(feedbackreputationDTO != null) {
			rankingForm.setFeedbackcomment(feedbackreputationDTO.getFeedbackcomment());
			rankingForm.setReputation(String.valueOf(feedbackreputationDTO.getReputation()));
			rankingForm.setFeedbackReputationId(feedbackreputationDTO.getId());
		}
		
		return mapping.findForward(G4GConstants.SUCCESS);
	}

	/**
	 * The submit method will submit the comments, feedback ranking by opposite
	 * player to player's account.
	 * @throws Exception 
	 * 
	 * @see com.g4g.action.BaseAction#submit(org.apache.struts.action.ActionMapping,
	 *      com.g4g.forms.BaseForm, java.lang.String,
	 *      javax.servlet.http.HttpServletRequest,
	 *      javax.servlet.http.HttpServletResponse)
	 */
	@Override
	public ActionForward submit(ActionMapping mapping, BaseForm form,
			String operation, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		AuditUtil.getInstance().writeLog(AuditUtil.FILE_TYPE_G4G, new StringBuffer(this.getClass().getName() ).append( CURRENTMETHOD ).append( DataUtil.getCurrentMethod()).append( CALLINGMETHOD ).append( DataUtil.getCallingMethod() ).append( SUBMIT_STARTS ).append( DASHES ).append( request.getSession().getId()).toString());
		if (request.getSession().getAttribute(USERDTO) == null) {
			AuditUtil.getInstance().writeLog(AuditUtil.FILE_TYPE_G4G,new StringBuffer(this.getClass().getName()).append(CALLINGMETHOD ).append( DataUtil.getCallingMethod() ).append( SUBMIT_STARTS).append( DASHES ).append(
						 USER_NOT_RECOGNIZE ).append(request.getSession().getId()).toString() , Level.INFO);
			return mapping.findForward(HOMEPAGE);
		}
		
		AuditUtil.getInstance().writeLog(AuditUtil.FILE_TYPE_G4G, new StringBuffer(this.getClass().getName()).append( CALLINGMETHOD ).append( DataUtil.getCallingMethod() ).append( DASHES ).append(
						 G4GConstants.SAVING_OPPONENT_FEEDBACK ).append(
								request.getSession().getId()).toString());
			
		RankingForm rankingForm = (RankingForm) form;
		FeedbackreputationDTO feedbackreputationDTO = new FeedbackreputationDTO();
			// Reputation and isRated is remaining
			/* Inserting data into FeedbackreputationDTO */
		feedbackreputationDTO.setTwoplayermatch(TwoPlayerMatchServiceDelegator.getDTOFromId(Integer.parseInt(rankingForm.getMatchid())));
		feedbackreputationDTO.setPlayer(PlayerServiceDelegator.getPlayer(Integer.parseInt(rankingForm.getPlayerid())));
		feedbackreputationDTO.setFeedbackdate(DataUtil.todayGMT());
		feedbackreputationDTO.setIsrated(true);
		feedbackreputationDTO.setFeedbackcomment(rankingForm
					.getFeedbackcomment() != null ? rankingForm
					.getFeedbackcomment() : NONE);
		feedbackreputationDTO.setReputation(Integer.parseInt(rankingForm
					.getReputation()));
			
		if(rankingForm.getFeedbackReputationId() == G4GConstants.ZERO) {
			FeedbackServiceDelegator.add(feedbackreputationDTO);
		} else {
			feedbackreputationDTO.setId(rankingForm.getFeedbackReputationId());
			FeedbackServiceDelegator.update(feedbackreputationDTO);
		}
			
		PlayerDTO playerDTO = PlayerServiceDelegator.getPlayer(Integer.parseInt(rankingForm.getPlayerid()));
		playerDTO.setReputation(FeedbackServiceDelegator.getAverageReputation(Integer.parseInt(rankingForm.getPlayerid())));
		PlayerServiceDelegator.update(playerDTO);
		
		return mapping.findForward(G4GConstants.SUCCESS);
	}
}
