/**********************************************************
* className.java : 
*
* Created by Punam
* Last modified Date: 21 Apr .08 by Punam
* Revision: 0.1
* Version : 0.0.8
* Copyright (c) 2008 - 2008 Askdigi, All rights reserved.
**********************************************************/
package com.g4g.action;

import static com.g4g.utils.G4GConstants.ADD_COMMENTS;
import static com.g4g.utils.G4GConstants.CALLINGMETHOD;
import static com.g4g.utils.G4GConstants.DASHES;
import static com.g4g.utils.G4GConstants.DISPLAY_STARTS;
import static com.g4g.utils.G4GConstants.HOMEPAGE;
import static com.g4g.utils.G4GConstants.MATCHID_DB;
import static com.g4g.utils.G4GConstants.USERDTO;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Level;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.g4g.delegator.GameOptionServiceDelegator;
import com.g4g.delegator.GameServiceDelegator;
import com.g4g.delegator.MatchCommentsServiceDelegator;
import com.g4g.delegator.NetworkServiceDelegator;
import com.g4g.delegator.TwoPlayerMatchServiceDelegator;
import com.g4g.delegator.TwoPlayerTournamentGameOptionServiceDelegator;
import com.g4g.dto.GameDTO;
import com.g4g.dto.GameoptionsDTO;
import com.g4g.dto.PlayerDTO;
import com.g4g.dto.TwoplayermatchDTO;
import com.g4g.dto.Twoplayermatchcomments;
import com.g4g.dto.TwoplayertournamentDTO;
import com.g4g.dto.TwoplayertournamentgameoptionsDTO;
import com.g4g.dto.UserDTO;
import com.g4g.forms.BaseForm;
import com.g4g.forms.DirectChallenge3Form;
import com.g4g.forms.DirectedChallengeForm;
import com.g4g.utils.AuditUtil;
import com.g4g.utils.DataUtil;
import com.g4g.utils.DataValidator;
import com.g4g.utils.G4GConstants;

/**
 * The Class DirectChallenge3Action.
 * 
 * @author ankur
 * @struts.action path="/directChallenge3" name="directChallenge3Form"
 * scope="request"
 * @struts.action-forward name="success" path="/WebContent/directChallenge3.jsp"
 */
public class DirectChallenge3Action extends BaseAction {
    
    /**
     * Display.
     * 
     * @param mapping the mapping
     * @param form the form
     * @param operation the operation
     * @param request the request
     * @param response the response
     * 
     * @return the action forward
     * 
     * @author Ankur
     * @see com.g4g.action.BaseAction#display(org.apache.struts.action.ActionMapping,
     * com.g4g.forms.BaseForm, java.lang.String,
     * javax.servlet.http.HttpServletRequest,
     * javax.servlet.http.HttpServletResponse)
     */

    @Override
    public ActionForward display(ActionMapping mapping, BaseForm form,
	    String operation, HttpServletRequest request,
	    HttpServletResponse response) {

    	AuditUtil.getInstance().writeLog(AuditUtil.FILE_TYPE_G4G, new StringBuffer(this.getClass().getName()).append( G4GConstants.CALLINGMETHOD ).append( DataUtil.getCallingMethod() ).append( G4GConstants.DISPLAY_STARTS ).append( G4GConstants.DASHES).append(request.getSession().getId()).toString());
    	if (request.getSession().getAttribute(USERDTO) == null) {
			AuditUtil.getInstance().writeLog(AuditUtil.FILE_TYPE_G4G, new StringBuffer(this.getClass().getName()).append( CALLINGMETHOD ).append( DataUtil.getCallingMethod() ).append( DISPLAY_STARTS ).append( DASHES ).append(
						 G4GConstants.NOT_RECOGNIZE_USER ).append(
							request.getSession().getId()).toString());
			return mapping.findForward(HOMEPAGE);
		}

	int twoplayermatchid;
	
	if (!DataValidator.isNull(request.getParameter(G4GConstants.TWO_PLAYER_MATCH_ID))) {
	    DirectedChallengeForm directedChallengeForm = new DirectedChallengeForm();
	    TwoplayermatchDTO twoplayermatchDTO = new TwoplayermatchDTO();
	    TwoplayertournamentDTO twoplayertournamentDTO = new TwoplayertournamentDTO();
	    PlayerDTO challengerDTO = new PlayerDTO();
	    PlayerDTO oppDTO = new PlayerDTO();
	    GameDTO gameDTO  ;
	    List<GameoptionsDTO> gameoptionDTO = new ArrayList<GameoptionsDTO>();
	    twoplayermatchid = DataUtil.getInteger(request.getParameter(G4GConstants.TWO_PLAYER_MATCH_ID));
	    request.setAttribute(G4GConstants.TWO_PLAYER_MATCH_ID,twoplayermatchid);
	    twoplayermatchDTO.setId(twoplayermatchid);
	    twoplayermatchDTO = TwoPlayerMatchServiceDelegator.get(twoplayermatchDTO);
	    directedChallengeForm.setTwoplayermatchDTO(twoplayermatchDTO);
	    searchCriteria.removeAllAttribute();
	    searchCriteria.setAttribute(MATCHID_DB, String.valueOf(twoplayermatchid));
	    directedChallengeForm.setCommentList(MatchCommentsServiceDelegator.getList(searchCriteria));
	    // set pk for twoplayertournamentDTO from twoplayermatch
	    // get twoplayertournament dto
	    twoplayertournamentDTO =twoplayermatchDTO.getTwoplayertournament();
	    // get the players use twoplayermatchdto and set to
	    // directedchallengeform
	    challengerDTO = twoplayermatchDTO.getPlayeroneid();
	    directedChallengeForm.setPlayerDTO(challengerDTO);
	    if (twoplayermatchDTO.getPlayertwoid() != null) {
			oppDTO = twoplayermatchDTO.getPlayertwoid();
			directedChallengeForm.setOppPlayerDTO(oppDTO);
	    }
	    // get gameDTO use twoplayertournamentDTO and set to
	    // directedchallengeform
	    gameDTO = GameServiceDelegator.getGame(twoplayertournamentDTO.getGameDTO().getId());
	    directedChallengeForm.setGameDTO(gameDTO);
	    // get the list from twoplayertournamentgameoption
	    searchCriteria.removeAllAttribute();
	    searchCriteria.setAttribute(G4GConstants.TOURNAMENT_ID,twoplayertournamentDTO.getId());
	    List<TwoplayertournamentgameoptionsDTO> list = TwoPlayerTournamentGameOptionServiceDelegator.getList(searchCriteria);
        for (TwoplayertournamentgameoptionsDTO aList : list) {
            TwoplayertournamentgameoptionsDTO tptgo = aList;
            // three pk optionId , gameId , valueId
            GameoptionsDTO gameoptionsDTO = new GameoptionsDTO();
            gameoptionsDTO.setGameid(gameDTO.getId());
            gameoptionsDTO.setId(tptgo.getOptionid());
            gameoptionsDTO.setValueid(tptgo.getValueid());
            gameoptionsDTO = GameOptionServiceDelegator.getGame(gameoptionsDTO);
            // set the gameoptionsDTO to gameOption list
            gameoptionDTO.add(gameoptionsDTO);
        }

	    // populate directedchallengeForm and keep it in session
	    // populate list in directedChallengeForm
	    directedChallengeForm.setGameoptionDTO(gameoptionDTO);
	    // set other attributes of directedChallengeForm
	    directedChallengeForm.setTournamentDate(DataUtil.getDate(twoplayertournamentDTO.getCreationdate()));
	    directedChallengeForm.setGameConsole(NetworkServiceDelegator.getNetwork(gameDTO.getId()));
	    directedChallengeForm.setAmountField(String.valueOf(twoplayertournamentDTO.getEntryfee()));
	    request.getSession().setAttribute(G4GConstants.DIRECTED_CHALLENGE_FORM,directedChallengeForm);
	}
	
	
	return mapping.findForward(G4GConstants.SUCCESS);
    }

    /**
     * Submit.
     * 
     * @param mapping the mapping
     * @param form the form
     * @param operation the operation
     * @param request the request
     * @param response the response
     * 
     * @return the action forward
     * @throws Exception 
     * 
     * @struts.form-field name="submit"
     */
    @Override
    public ActionForward submit(ActionMapping mapping, BaseForm form,
	    String operation, HttpServletRequest request,
	    HttpServletResponse response) throws Exception {

    	AuditUtil.getInstance().writeLog(AuditUtil.FILE_TYPE_G4G, new StringBuffer(this.getClass().getName() ).append(DataUtil.getCurrentMethod() ).append( G4GConstants.SUBMIT_STARTS ).append( request.getSession().getId()).toString(),Level.INFO);
    	if (request.getSession().getAttribute(USERDTO) == null) {
			AuditUtil.getInstance().writeLog(AuditUtil.FILE_TYPE_G4G, new StringBuffer(this.getClass().getName()).append( CALLINGMETHOD ).append( DataUtil.getCallingMethod() ).append( DISPLAY_STARTS ).append( DASHES ).append(
						 G4GConstants.NOT_RECOGNIZE_USER ).append(
							request.getSession().getId()).toString());
			return mapping.findForward(HOMEPAGE);
		}
	DirectChallenge3Form directChallenge3Form = (DirectChallenge3Form) form;

	if (ADD_COMMENTS.equals(operation)) {
	    TwoplayermatchDTO dto = new TwoplayermatchDTO();
	    // logic for checking tournament id or twoplayermatchId
	    List<Twoplayermatchcomments> com = new ArrayList<Twoplayermatchcomments>();
	    DirectedChallengeForm directedChallengeForm = (DirectedChallengeForm) request.getSession().getAttribute(G4GConstants.DIRECTED_CHALLENGE_FORM);
	    if (!DataValidator.isNull(directChallenge3Form.getComments())) {
		directChallenge3Form.setOperation(null);
		// logic for checking tournament id or twoplayermatchId
		if (directedChallengeForm.getTwoplayermatchid() != null) {
		    dto.setId(DataUtil.getInteger(directedChallengeForm.getTwoplayermatchid()));
		    Twoplayermatchcomments comm = DirectedChallengeAction.setComments(directedChallengeForm.getComments(),G4GConstants.ZERO, dto, request);
		    AuditUtil.getInstance().writeLog(AuditUtil.FILE_TYPE_G4G, new StringBuffer(this.getClass().getName()).append( CALLINGMETHOD ).append( DataUtil.getCallingMethod() ).append( DASHES ).append(
							 G4GConstants.ADDING_COMMENTS ).append(
								request.getSession().getId()).toString() , Level.INFO);
		    MatchCommentsServiceDelegator.add(comm);
		    com.add(comm);
		    AuditUtil.getInstance().writeLog(AuditUtil.FILE_TYPE_G4G, new StringBuffer(this.getClass().getName()).append( CALLINGMETHOD ).append( DataUtil.getCallingMethod() ).append( DASHES ).append(
							 G4GConstants.SETTING_COMMENTS ).append(
								request.getSession().getId()).toString() , Level.INFO);
		    directedChallengeForm.getCommentList().add(comm);
		} else {
		    dto.setId(G4GConstants.ONE_NEGATIVE);
		    if (directedChallengeForm.getCommentList() == null) {
			if (directedChallengeForm.getTwoplayermatchDTO() != null) {
				if (directedChallengeForm.getTwoplayermatchDTO().getId() != G4GConstants.ZERO) {
					dto.setId(directedChallengeForm.getTwoplayermatchDTO().getId());
				}
			}
			com.add(DirectedChallengeAction.setComments(directChallenge3Form.getComments(), G4GConstants.ZERO, dto,request));
			directedChallengeForm.setCommentList(com);
		    } else {
			directedChallengeForm.getCommentList().add(DirectedChallengeAction.setComments(directChallenge3Form.getComments(),directedChallengeForm.getCommentList().size(), dto, request));
		    }
		    directChallenge3Form.setComments(null);
		}
	    }
	    DirectedChallengeAction action = new DirectedChallengeAction();
	    action.setDisplay(mapping, directedChallengeForm, operation,request, response);
	    return mapping.getInputForward();
	}

	AuditUtil.getInstance().writeLog(AuditUtil.FILE_TYPE_G4G,this.getClass().getName() + G4GConstants.SUBMIT_STARTS);

	UserDTO dto = DataUtil.getUserDTO(request);

	Enumeration sm = request.getSession().getAttributeNames();

	while (sm.hasMoreElements()) {
	    request.getSession().removeAttribute(sm.nextElement().toString());
	}

	request.getSession().setAttribute(G4GConstants.USERDTO, dto);
	return null;
    }
}