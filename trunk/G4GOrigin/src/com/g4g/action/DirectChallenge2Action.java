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

import static com.g4g.utils.G4GConstants.ACCEPT;
import static com.g4g.utils.G4GConstants.ACCEPT_CHALLENGE_VALUE;
import static com.g4g.utils.G4GConstants.ADD_COMMENTS;
import static com.g4g.utils.G4GConstants.AMOUNT_FIELD;
import static com.g4g.utils.G4GConstants.BLANK;
import static com.g4g.utils.G4GConstants.CALLINGMETHOD;
import static com.g4g.utils.G4GConstants.CANCEL_CHALLENGE_VALUE;
import static com.g4g.utils.G4GConstants.CHALLENGE_STATUS_PENDING;
import static com.g4g.utils.G4GConstants.COMMENT;
import static com.g4g.utils.G4GConstants.CURRENTMETHOD;
import static com.g4g.utils.G4GConstants.DASHES;
import static com.g4g.utils.G4GConstants.DATE_EEE_MMM_DD_YYYY_AT_hh_MM_A;
import static com.g4g.utils.G4GConstants.DIRECTED_CHALLENGE_FORM;
import static com.g4g.utils.G4GConstants.DISSOLVE;
import static com.g4g.utils.G4GConstants.ERROR;
import static com.g4g.utils.G4GConstants.ERRORS_LESSBALANCE;
import static com.g4g.utils.G4GConstants.EXCEPTION;
import static com.g4g.utils.G4GConstants.FOR;
import static com.g4g.utils.G4GConstants.GAME_STAUS_CANCELLED;
import static com.g4g.utils.G4GConstants.GAME_STAUS_CANCELLED_USER;
import static com.g4g.utils.G4GConstants.GOT;
import static com.g4g.utils.G4GConstants.HOMEPAGE;
import static com.g4g.utils.G4GConstants.ISSUEOPENCHALLANGE;
import static com.g4g.utils.G4GConstants.MATCHID_DB;
import static com.g4g.utils.G4GConstants.OPERATION;
import static com.g4g.utils.G4GConstants.PENDING;
import static com.g4g.utils.G4GConstants.REJECT;
import static com.g4g.utils.G4GConstants.REJECT_CHALLENGE_VALUE;
import static com.g4g.utils.G4GConstants.SEND;
import static com.g4g.utils.G4GConstants.STRING_NULL;
import static com.g4g.utils.G4GConstants.SUBMIT_STARTS;
import static com.g4g.utils.G4GConstants.SUCCESS;
import static com.g4g.utils.G4GConstants.TEN;
import static com.g4g.utils.G4GConstants.TOURNAMENT_ID;
import static com.g4g.utils.G4GConstants.TWO_PLAYER_MATCH_ID;
import static com.g4g.utils.G4GConstants.USERDTO;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Level;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

import com.g4g.delegator.G4GFinancialDelegator;
import com.g4g.delegator.GameOptionServiceDelegator;
import com.g4g.delegator.GameServiceDelegator;
import com.g4g.delegator.MatchCommentsServiceDelegator;
import com.g4g.delegator.NetworkServiceDelegator;
import com.g4g.delegator.NotificationServiceDelegator;
import com.g4g.delegator.TwoPlayerMatchServiceDelegator;
import com.g4g.delegator.TwoPlayerTournamentGameOptionServiceDelegator;
import com.g4g.delegator.TwoPlayerTournamentServiceDelegator;
import com.g4g.dto.GameDTO;
import com.g4g.dto.GameoptionsDTO;
import com.g4g.dto.PlayerDTO;
import com.g4g.dto.TwoplayermatchDTO;
import com.g4g.dto.Twoplayermatchcomments;
import com.g4g.dto.TwoplayertournamentDTO;
import com.g4g.dto.TwoplayertournamentgameoptionsDTO;
import com.g4g.forms.BaseForm;
import com.g4g.forms.DirectChallenge2Form;
import com.g4g.forms.DirectedChallengeForm;
import com.g4g.notification.ChallengeNotification;
import com.g4g.notification.MatchConfirmNotification;
import com.g4g.notification.TournamentJoinNotification;
import com.g4g.security.ChallengeCardSecurity;
import com.g4g.utils.AuditUtil;
import com.g4g.utils.ChallengeCardUpdateUtil;
import com.g4g.utils.DataUtil;
import com.g4g.utils.DataValidator;
import com.g4g.utils.DateUtil;
import com.g4g.utils.G4GConstants;
import com.g4g.utils.properties.G4GProperties;
import com.g4g.utils.properties.PropertiesConstants;
import com.impessa.worldgaming.client.InternalException_Exception;
import com.impessa.worldgaming.client.InvalidBetException_Exception;
import com.impessa.worldgaming.client.InvalidSessionException_Exception;
import com.impessa.worldgaming.client.MoneyException_Exception;
import com.impessa.worldgaming.client.User;
import com.impessa.worldgaming.client.UserNotFoundException_Exception;

/**
 * MyEclipse Struts Creation date: 03-04-2008.
 *
 * @author ankur
 * @struts.action path="/displayDirectChallenge2"
 *                name="displayDirectChallenge2Form" scope="request"
 * @struts.action-forward name="success" path="/WebContent/directChallenge3.jsp"
 */
public class DirectChallenge2Action extends BaseAction {

	private static final String UNUSED = "unused";

	/**
	 * Display.
	 *
	 * @param mapping
	 *            the mapping
	 * @param form
	 *            the form
	 * @param operation
	 *            the operation
	 * @param request
	 *            the request
	 * @param response
	 *            the response
	 *
	 * @return the action forward
	 *
	 * @author Ankur Used For Logics to perform while displaying
	 *         DirectChallenge2.jsp
	 * @see com.g4g.action.BaseAction#display(org.apache.struts.action.ActionMapping,
	 *      com.g4g.forms.BaseForm, java.lang.String,
	 *      javax.servlet.http.HttpServletRequest,
	 *      javax.servlet.http.HttpServletResponse)
	 */
	@Override
	public ActionForward display(ActionMapping mapping, BaseForm form,String operation, HttpServletRequest request,HttpServletResponse response) {
		AuditUtil.getInstance().writeLog(AuditUtil.FILE_TYPE_G4G,new StringBuffer(this.getClass().getName()).append(
						G4GConstants.CALLINGMETHOD).append(DataUtil.getCallingMethod()).append(G4GConstants.DISPLAY_STARTS).append(G4GConstants.DASHES).append(
							request.getSession().getId()).toString(),Level.INFO);

		if (request.getSession().getAttribute(USERDTO) == null) {
			return mapping.findForward(HOMEPAGE);
		}
		// get the twoplayermatchid
		// populate directedchallengeForm playerdto oppplayer gameDTO

		int twoplayermatchid = G4GConstants.ZERO;
		int tounamentId;

		if (request.getParameter(TOURNAMENT_ID) != null) {
			tounamentId = Integer.parseInt(request.getParameter(TOURNAMENT_ID));
			request.setAttribute(TOURNAMENT_ID, tounamentId);
			request.getSession().setAttribute(TOURNAMENT_ID, tounamentId);
			AuditUtil.getInstance().writeLog(AuditUtil.FILE_TYPE_G4G,new StringBuffer(this.getClass().getName()).append(
							DataUtil.getCurrentMethod()).append(G4GConstants.TOURNAMENT_ID).append(tounamentId).append(FOR).append(request.getSession().getId()).toString(), Level.INFO);
			AuditUtil.getInstance().writeLog(AuditUtil.FILE_TYPE_G4G,new StringBuffer(this.getClass().getName()).append(DataUtil.getCurrentMethod()).append(G4GConstants.CALLING_TWOPLAYER_MATCH).append(
							request.getSession().getId()).toString(),Level.INFO);
			TwoplayermatchDTO getrandommatch = TwoPlayerMatchServiceDelegator.getDtoForChallenge(tounamentId, request);
			if (getrandommatch != null&& getrandommatch.getId() > G4GConstants.ZERO) {
				twoplayermatchid = getrandommatch.getId();
			} else {
				AuditUtil.getInstance().writeLog(AuditUtil.FILE_TYPE_G4G,new StringBuffer(this.getClass().getName()).append(
								DataUtil.getCallingMethod()).append(G4GConstants.NOMATCH_AVAILABLE).append(request.getSession().getId()).toString(),Level.INFO);
				msg = new ActionMessage(G4GConstants.NOMATCH);
				error.clear();
				error.add(ERROR, msg);
				this.addErrors(request, error);
				return mapping.getInputForward();
			}
		}
		String idtemp;
		if (twoplayermatchid < G4GConstants.ONE_NUMBER) {
			idtemp = ChallengeCardUpdateUtil.checkIdTemp(request);
		} else {
			idtemp = String.valueOf(twoplayermatchid);
		}
		/* I get matchid */
		if (!STRING_NULL.equals(idtemp) && !idtemp.equals(null)) {
			DirectedChallengeForm directedChallengeForm = new DirectedChallengeForm();
			TwoplayermatchDTO twoplayermatchDTO = new TwoplayermatchDTO();
			TwoplayertournamentDTO twoplayertournamentDTO;
			PlayerDTO challengerDTO;
			User challengerUser;
			PlayerDTO oppDTO;
			@SuppressWarnings(UNUSED)
			GameDTO gameDTO;
			List<GameoptionsDTO> gameoptionDTO = new ArrayList<GameoptionsDTO>();
			twoplayermatchid = DataUtil.getInteger(idtemp);
			// set the comments list here
			searchCriteria.removeAllAttribute();
			searchCriteria.setAttribute(MATCHID_DB, idtemp);
			AuditUtil.getInstance().writeLog(
					AuditUtil.FILE_TYPE_G4G,
					new StringBuffer(G4GConstants.CALLING_COMMENTS).append(
							DataUtil.getCurrentMethod()).append(
							request.getSession().getId()).toString(),
					Level.INFO);
			directedChallengeForm.setCommentList(MatchCommentsServiceDelegator
					.getList(searchCriteria));
			request.setAttribute(TWO_PLAYER_MATCH_ID, idtemp);
			request.getSession().setAttribute(TWO_PLAYER_MATCH_ID, idtemp);
			twoplayermatchDTO.setId(twoplayermatchid);
			AuditUtil.getInstance().writeLog(
					AuditUtil.FILE_TYPE_G4G,
					new StringBuffer(G4GConstants.TWOPLAYER_MATCH_SERVICE_INFO)
							.append(twoplayermatchid).append(BLANK).append(
									DataUtil.getCurrentMethod()).append(
									request.getSession().getId()).toString(),
					Level.INFO);
			twoplayermatchDTO = TwoPlayerMatchServiceDelegator.get(twoplayermatchDTO);
			directedChallengeForm.setTwoplayermatchDTO(twoplayermatchDTO);
			directedChallengeForm.setSchduledDate(twoplayermatchDTO.getScheduledstartdate());

			// set pk for twoplayertournamentDTO from twoplayermatch
			// get twoplayertournament dto

			twoplayertournamentDTO = twoplayermatchDTO.getTwoplayertournament();
			// get the players use twoplayermatchdto and set to
			// directedchallengeform
			if (request.getParameter(TOURNAMENT_ID) != null) {
				challengerDTO = DataUtil.getUserDTO(request).getPlayerDTO();
				try {
					AuditUtil.getInstance().writeLog(
							AuditUtil.FILE_TYPE_G4G,
							new StringBuffer(G4GConstants.USER_GEN_INFO)
									.append(DataUtil.getCurrentMethod())
									.append(BLANK).append(
											request.getSession().getId())
									.append(DASHES).append(
											challengerDTO.getEmailaddress())
									.toString(), Level.INFO);
					challengerUser = G4GFinancialDelegator
							.getUserInfo(challengerDTO.getEmailaddress());
				} catch (InternalException_Exception e) {
					AuditUtil.getInstance().writeLog(
							AuditUtil.FILE_TYPE_HIBERNATE,
							new StringBuffer(this.getClass().getName()).append(
									G4GConstants.COLON_WITH_SPACES).append(
									DataUtil.getCurrentMethod()).append(
									G4GConstants.DASHES).append(e.getMessage())
									.append(
											DataUtil.getUserDTO(request)
													.getPlayerDTO()
													.getScreenname()).append(
											request.getSession().getId())
									.toString(), Level.ERROR);
					AuditUtil.getInstance().writeLog(
							AuditUtil.FILE_TYPE_G4G,
							new StringBuffer(this.getClass().getName()).append(
									G4GConstants.COLON_WITH_SPACES).append(
									DataUtil.getCurrentMethod()).append(
									G4GConstants.DASHES).append(e.getMessage())
									.append(
											DataUtil.getUserDTO(request)
													.getPlayerDTO()
													.getScreenname()).append(
											request.getSession().getId())
									.toString(), Level.ERROR);
					msg = new ActionMessage(e.getMessage());
					error.clear();
					error.add(ERROR, msg);
					this.addErrors(request, error);
					return mapping.getInputForward();
				} catch (UserNotFoundException_Exception e) {
					AuditUtil.getInstance().writeLog(
							AuditUtil.FILE_TYPE_G4G,
							new StringBuffer(this.getClass().getName()).append(
									BLANK).append(DataUtil.getCurrentMethod())
									.append(BLANK).append(
											G4GConstants.NOT_REG_IMPASSA)
									.append(challengerDTO.getEmailaddress())
									.append(BLANK).append(e.getMessage())
									.append(request.getSession().getId())
									.toString(), Level.ERROR);
					AuditUtil.getInstance().writeLog(
							AuditUtil.FILE_TYPE_G4G_FINANCIAL,
							new StringBuffer(this.getClass().getName()).append(
									BLANK).append(DataUtil.getCurrentMethod())
									.append(BLANK).append(
											G4GConstants.NOT_REG_IMPASSA)
									.append(challengerDTO.getEmailaddress())
									.append(BLANK).append(e.getMessage())
									.append(request.getSession().getId())
									.toString(), Level.ERROR);
					msg = new ActionMessage(e.getMessage());
					error.clear();
					error.add(ERROR, msg);
					this.addErrors(request, error);
					return mapping.getInputForward();
				}

			} else {
				challengerDTO = twoplayermatchDTO.getPlayeroneid();
				try {
					challengerUser = G4GFinancialDelegator
							.getUserInfo(challengerDTO.getEmailaddress());
				} catch (InternalException_Exception e) {
					AuditUtil.getInstance().writeLog(
							AuditUtil.FILE_TYPE_G4G_FINANCIAL,
							new StringBuffer(this.getClass().getName()).append(
									DataUtil.getCurrentMethod()).append(
									G4GConstants.EXCE_IMPASSA).append(
									e.getMessage()).append(
									request.getSession().getId()).toString(),
							Level.ERROR);
					AuditUtil.getInstance().writeLog(
							AuditUtil.FILE_TYPE_G4G,
							new StringBuffer(this.getClass().getName()).append(
									DataUtil.getCurrentMethod()).append(
									G4GConstants.EXCE_IMPASSA).append(
									e.getMessage()).append(
									request.getSession().getId()).toString(),
							Level.ERROR);
					msg = new ActionMessage(e.getMessage());
					error.clear();
					error.add(ERROR, msg);
					this.addErrors(request, error);
					return mapping.getInputForward();
				} catch (UserNotFoundException_Exception e) {
					AuditUtil.getInstance().writeLog(
							AuditUtil.FILE_TYPE_G4G,
							new StringBuffer(this.getClass().getName()).append(
									BLANK).append(DataUtil.getCurrentMethod())
									.append(BLANK).append(
											G4GConstants.NOT_REG_IMPASSA)
									.append(challengerDTO.getEmailaddress())
									.append(BLANK).append(e.getMessage())
									.append(request.getSession().getId())
									.toString(), Level.ERROR);
					AuditUtil.getInstance().writeLog(
							AuditUtil.FILE_TYPE_G4G_FINANCIAL,
							new StringBuffer(this.getClass().getName()).append(
									BLANK).append(DataUtil.getCurrentMethod())
									.append(BLANK).append(
											G4GConstants.NOT_REG_IMPASSA)
									.append(challengerDTO.getEmailaddress())
									.append(BLANK).append(e.getMessage())
									.append(request.getSession().getId())
									.toString(), Level.ERROR);
					msg = new ActionMessage(e.getMessage());
					error.clear();
					error.add(ERROR, msg);
					this.addErrors(request, error);
					return mapping.getInputForward();
				}
			}
			directedChallengeForm.setPlayerDTO(challengerDTO);
			directedChallengeForm.setChallengerUser(challengerUser);

			if (twoplayermatchDTO.getPlayertwoid() != null
					&& STRING_NULL.equals(twoplayermatchDTO.getPlayertwoid())) {
				oppDTO = twoplayermatchDTO.getPlayertwoid();
				directedChallengeForm.setOppPlayerDTO(oppDTO);
				try {
					AuditUtil.getInstance().writeLog(
							AuditUtil.FILE_TYPE_G4G,
							new StringBuffer(this.getClass().getName()).append(
									CALLINGMETHOD).append(
									DataUtil.getCallingMethod()).append(DASHES)
									.append(CURRENTMETHOD).append(
											DataUtil.getCurrentMethod())
									.append(G4GConstants.OPPONENT_IMPASSA_INFO)
									.append(oppDTO.getEmailaddress()).append(
											request.getSession().getId())
									.toString(), Level.INFO);
					User oppUser = G4GFinancialDelegator.getUserInfo(oppDTO
							.getEmailaddress());
					directedChallengeForm.setOppUser(oppUser);
				} catch (InternalException_Exception e) {
					AuditUtil.getInstance().writeLog(
							AuditUtil.FILE_TYPE_G4G_FINANCIAL,
							new StringBuffer(this.getClass().getName()).append(
									DataUtil.getCurrentMethod()).append(
									G4GConstants.EXCE_IMPASSA).append(
									e.getMessage()).append(
									request.getSession().getId()).toString(),
							Level.ERROR);
					AuditUtil.getInstance().writeLog(
							AuditUtil.FILE_TYPE_G4G,
							new StringBuffer(this.getClass().getName()).append(
									DataUtil.getCurrentMethod()).append(
									G4GConstants.EXCE_IMPASSA).append(
									e.getMessage()).append(
									request.getSession().getId()).toString(),
							Level.ERROR);
					msg = new ActionMessage(e.getMessage());
					error.clear();
					error.add(ERROR, msg);
					this.addErrors(request, error);
					return mapping.getInputForward();
				} catch (UserNotFoundException_Exception e) {
					AuditUtil.getInstance().writeLog(
							AuditUtil.FILE_TYPE_G4G,
							new StringBuffer(this.getClass().getName()).append(
									BLANK).append(DataUtil.getCurrentMethod())
									.append(BLANK).append(
											G4GConstants.NOT_REG_IMPASSA)
									.append(challengerDTO.getEmailaddress())
									.append(BLANK).append(e.getMessage())
									.append(request.getSession().getId())
									.toString(), Level.ERROR);
					AuditUtil.getInstance().writeLog(
							AuditUtil.FILE_TYPE_G4G_FINANCIAL,
							new StringBuffer(this.getClass().getName()).append(
									BLANK).append(DataUtil.getCurrentMethod())
									.append(BLANK).append(
											G4GConstants.NOT_REG_IMPASSA)
									.append(challengerDTO.getEmailaddress())
									.append(BLANK).append(e.getMessage())
									.append(request.getSession().getId())
									.toString(), Level.ERROR);
					msg = new ActionMessage(e.getMessage());
					error.clear();
					error.add(ERROR, msg);
					this.addErrors(request, error);
					return mapping.getInputForward();
				}

			} else {
				directedChallengeForm.setOppPlayerDTO(DataUtil.getUserDTO(
						request).getPlayerDTO());
				directedChallengeForm.setOppUser(DataUtil.getUserDTO(request)
						.getUser());
			}

			// get gameDTO use twoplayertournamentDTO and set to
			// directedchallengeform
			gameDTO = GameServiceDelegator.getGame(twoplayertournamentDTO
					.getGameDTO().getId());
			directedChallengeForm.setGameDTO(gameDTO);
			// get the list from twoplayertournamentgameoption
			searchCriteria.removeAllAttribute();
			searchCriteria.setAttribute(TOURNAMENT_ID, twoplayertournamentDTO);
			AuditUtil.getInstance().writeLog(
					AuditUtil.FILE_TYPE_G4G,
					new StringBuffer(this.getClass().getName()).append(
							G4GConstants.GET_GAME_OPPTION)
							.append(CURRENTMETHOD).append(
									DataUtil.getCurrentMethod()).append(
									request.getSession().getId()).toString(),
					Level.INFO);
			List<TwoplayertournamentgameoptionsDTO> list = TwoPlayerTournamentGameOptionServiceDelegator
					.getList(searchCriteria);
			for (TwoplayertournamentgameoptionsDTO aList : list) {
				TwoplayertournamentgameoptionsDTO tptgo = aList;
				// three pk optionId , gameId , valueId
				GameoptionsDTO gameoptionsDTO = new GameoptionsDTO();
				gameoptionsDTO.setGameid(gameDTO.getId());
				gameoptionsDTO.setId(tptgo.getOptionid());
				gameoptionsDTO.setValueid(tptgo.getValueid());
				gameoptionsDTO = GameOptionServiceDelegator
						.getGame(gameoptionsDTO);
				// set the gameoptionsDTO to gameOption list
				gameoptionDTO.add(gameoptionsDTO);
			}
			AuditUtil.getInstance().writeLog(
					AuditUtil.FILE_TYPE_G4G_FINANCIAL,
					new StringBuffer(this.getClass().getName()).append(
							DataUtil.getCurrentMethod()).append(
							G4GConstants.GET_SET_GAME_OPPTION).append(
							gameDTO.getGamename()).append(BLANK).append(
							request.getSession().getId()).toString(),
					Level.INFO);
			// populate directedchallengeForm and keep it in session
			// populate list in directedChallengeForm
			directedChallengeForm.setGameoptionDTO(gameoptionDTO);

			// set other attributes of directedChallengeForm
			try {
				AuditUtil.getInstance().writeLog(
						AuditUtil.FILE_TYPE_G4G_FINANCIAL,
						new StringBuffer(this.getClass().getName()).append(
								DataUtil.getCurrentMethod()).append(
								G4GConstants.GET_TOURNAMENT_SCHEDULED).append(
								gameDTO.getGamename()).append(BLANK).append(
								request.getSession().getId()).toString(),
						Level.INFO);
				directedChallengeForm.setTournamentDate(DataUtil.getDate(
						twoplayermatchDTO.getScheduledstartdate(),
						DATE_EEE_MMM_DD_YYYY_AT_hh_MM_A));
			} catch (NullPointerException e) {
				directedChallengeForm.setTournamentDate(BLANK);
			}

			AuditUtil.getInstance().writeLog(
					AuditUtil.FILE_TYPE_G4G,
					new StringBuffer(this.getClass().getName()).append(
							G4GConstants.GET_GAME_NETWORK)
							.append(CURRENTMETHOD).append(
									DataUtil.getCurrentMethod()).append(
									request.getSession().getId()).toString(),
					Level.INFO);
			directedChallengeForm.setGameConsole(NetworkServiceDelegator
					.getNetwork(gameDTO.getNetwork().getId()));
			directedChallengeForm.setAmountField(String
					.valueOf(twoplayertournamentDTO.getEntryfee()));
			directedChallengeForm.setTwoplayermatchid(idtemp);
			searchCriteria.removeAllAttribute();
			searchCriteria.setAttribute(MATCHID_DB, idtemp);
			directedChallengeForm.setCommentList(MatchCommentsServiceDelegator
					.getList(searchCriteria));
			request.getSession().setAttribute(DIRECTED_CHALLENGE_FORM,
							directedChallengeForm);
			if(directedChallengeForm.getTwoplayermatchDTO()!=null) {
				ChallengeCardSecurity.canUserViewThisCard(request, response, twoplayermatchDTO);
			}
		}

		// have a twoplayermatchId in the jsp
		// check wheather the session user is opponent player or challenger
		// show the option of edit to challenger and not to opponent

		// setting twoplayer matchId in request whatever its value is

		return mapping.findForward(SUCCESS);
	}

	/**
	 * Submit.
	 *
	 * @param mapping
	 *            the mapping
	 * @param form
	 *            the form
	 * @param operation
	 *            the operation
	 * @param request
	 *            the request
	 * @param response
	 *            the response
	 *
	 * @return the action forward
	 * @throws Exception
	 *
	 * @struts.form-field name="submit" If operation is Send Setup challenge.
	 *                    Pass form and request to DirectedChallengeForm for
	 *                    populating entities for table Twoplayertournament,
	 *                    Twoplayermatch
	 */
	@Override
	public ActionForward submit(ActionMapping mapping, BaseForm form,
			String operation, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,
				new StringBuffer(this.getClass().getName()).append(
						CURRENTMETHOD).append(DataUtil.getCurrentMethod())
						.append(CALLINGMETHOD).append(
								DataUtil.getCallingMethod()).append(
								SUBMIT_STARTS).append(DASHES).append(
								request.getSession().getId()).toString());
		if (request.getSession().getAttribute(USERDTO) == null) {
			AuditUtil.getInstance().writeLog(AuditUtil.FILE_TYPE_G4G,new StringBuffer(this.getClass().getName()).append(CALLINGMETHOD).append(DataUtil.getCallingMethod())
							.append(SUBMIT_STARTS).append(DASHES).append(G4GConstants.USER_NOT_RECOGNIZE).append(request.getSession().getId()).toString(),Level.INFO);
			return mapping.findForward(HOMEPAGE);
		}
		DirectChallenge2Form directChallenge2Form = (DirectChallenge2Form) form;
		if (ISSUEOPENCHALLANGE.equals(directChallenge2Form.getOperation())) {
			AuditUtil.getInstance().writeLog(AuditUtil.FILE_TYPE_G4G,new StringBuffer(this.getClass().getName()).append(CURRENTMETHOD).append(DataUtil.getCurrentMethod())
							.append(CALLINGMETHOD).append(DataUtil.getCallingMethod()).append(DASHES).append(G4GConstants.CONVERT_CHALLENGE).append(request.getSession().getId()).toString(),Level.INFO);
			TwoplayermatchDTO twoplayermatchDTO = TwoPlayerMatchServiceDelegator.getDTOFromId(Integer.parseInt(request.getParameter(TWO_PLAYER_MATCH_ID)));

			// In case of user cancels the match the creator has option
			// to re issue the match as an open match
			// The below if takes care of transaction handling related to that.
			if(twoplayermatchDTO.getGamecompleted()==GAME_STAUS_CANCELLED_USER) {

				ChallengeCardUpdateUtil.challengeSendTimeAmountDeduction(request, twoplayermatchDTO.getTwoplayertournament());
			}

			twoplayermatchDTO.setPlayertwoid(null);
			twoplayermatchDTO.setPlayertwoaccepteddate(null);
			twoplayermatchDTO.setMatchstatus(PENDING);
			twoplayermatchDTO.setCompleteddate(null);
			twoplayermatchDTO.setGamecompleted(G4GConstants.ZERO);
			TwoPlayerMatchServiceDelegator.update(twoplayermatchDTO);
			return mapping.findForward(DISSOLVE);
		}

		DirectedChallengeForm directedChallengeForm = (DirectedChallengeForm) request.getSession().getAttribute(G4GConstants.DIRECTED_CHALLENGE_FORM);

		if(G4GConstants.CANCEL_MATCH.equals(operation)) {
			AuditUtil.getInstance().writeLog(AuditUtil.FILE_TYPE_G4G,new StringBuffer(this.getClass().getName()).append(CURRENTMETHOD).append(DataUtil.getCurrentMethod())
							.append(CALLINGMETHOD).append(DataUtil.getCallingMethod()).append(DASHES).append(G4GConstants.CANCEL_CHALLENGE).append(request.getSession().getId()).toString(),Level.INFO);
			if(DateUtil.getOneHourEarlyDate(directedChallengeForm.getSchduledDate()).after(DataUtil.todayGMT())){
			ChallengeCardUpdateUtil.cancelMatch(request, directedChallengeForm.getTwoplayermatchDTO());
			}else {
				AuditUtil.getInstance().writeLog(AuditUtil.FILE_TYPE_G4G,new StringBuffer(this.getClass().getName()).append(CURRENTMETHOD).append(DataUtil.getCurrentMethod())
								.append(CALLINGMETHOD).append(DataUtil.getCallingMethod()).append(DASHES).append(G4GConstants.UNABLE_TO_CANCEL_MATCH).append(request.getSession().getId()).toString(),Level.INFO);
				int time;
				try {
				time = Integer.parseInt(G4GProperties.getProperty(PropertiesConstants.G4G_CANCEL_MATCH_TIME));
				}catch (Exception e) {
					AuditUtil.getInstance().writeLog(AuditUtil.FILE_TYPE_G4G, e);
					time = G4GConstants.CANCEL_MATCH_TIME;
				}
				if(time<G4GConstants.TEN_NUMBER || time>G4GConstants.ONE_HUNDREAD_TWENTY){
					AuditUtil.getInstance().writeLog(AuditUtil.FILE_TYPE_G4G, G4GConstants.CANCEL_MATCH_TIME_RANGE);
					time=G4GConstants.CANCEL_MATCH_TIME;
				}
				msg = new ActionMessage(G4GConstants.CHALLENGE_NOT_CANCELLED , time* G4GConstants.SIXTY);
				error.clear();
				error.add(ERROR, msg);
				this.addErrors(request, error);
				return mapping.getInputForward();

			}
		}

		if (ADD_COMMENTS.equals(operation)) {
			AuditUtil.getInstance().writeLog(
					AuditUtil.FILE_TYPE_G4G,
					new StringBuffer(this.getClass().getName()).append(BLANK)
							.append(ADD_COMMENTS).append(BLANK).append(
									CURRENTMETHOD).append(
									DataUtil.getCurrentMethod()).append(
									request.getSession().getId()).toString(),
					Level.INFO);
			directChallenge2Form.setOperation(null);
			if (!DataValidator.isNull(directChallenge2Form.getComments())) {
				TwoplayermatchDTO dto = new TwoplayermatchDTO();
				// logic for checking tournament id or twoplayermatchId
				List<Twoplayermatchcomments> com = new ArrayList<Twoplayermatchcomments>();
				if (directedChallengeForm.getTwoplayermatchid() != null
						&& !STRING_NULL.equals(directedChallengeForm
								.getTwoplayermatchid())) {
					request.setAttribute(TWO_PLAYER_MATCH_ID,
							directedChallengeForm.getTwoplayermatchid());
					dto.setId(DataUtil.getInteger(directedChallengeForm
							.getTwoplayermatchid()));
					Twoplayermatchcomments comm = DirectedChallengeAction
							.setComments(directChallenge2Form.getComments(),
									directedChallengeForm.getCommentList()
											.size(), dto, request);
					AuditUtil.getInstance().writeLog(
							AuditUtil.FILE_TYPE_G4G,
							new StringBuffer(this.getClass().getName()).append(
									CURRENTMETHOD).append(
									DataUtil.getCurrentMethod()).append(
									CALLINGMETHOD).append(
									DataUtil.getCallingMethod()).append(DASHES)
									.append(request.getSession().getId())
									.append(G4GConstants.ADD_COMMENT_DATABASE)
									.append(request.getSession().getId())
									.toString(), Level.INFO);
					AuditUtil
							.getInstance()
							.writeLog(
									AuditUtil.FILE_TYPE_G4G,
									new StringBuffer(this.getClass().getName())
											.append(CURRENTMETHOD)
											.append(DataUtil.getCurrentMethod())
											.append(CALLINGMETHOD)
											.append(DataUtil.getCallingMethod())
											.append(DASHES)
											.append(
													G4GConstants.MATCHCOMMENT_SERVICE_DELEGATOR)
											.append(
													request.getSession()
															.getId())
											.toString(), Level.INFO);
					MatchCommentsServiceDelegator.add(comm);
					searchCriteria.removeAllAttribute();
					searchCriteria.setAttribute(MATCHID_DB, directedChallengeForm
							.getTwoplayermatchid());
					AuditUtil.getInstance().writeLog(
							AuditUtil.FILE_TYPE_G4G,
							new StringBuffer(this.getClass().getName()).append(
									CURRENTMETHOD).append(
									DataUtil.getCurrentMethod()).append(
									CALLINGMETHOD).append(
									DataUtil.getCallingMethod()).append(DASHES)
									.append(G4GConstants.NEW_COMMENT_MATCH)
									.append(request.getSession().getId())
									.toString(), Level.INFO);
					com = MatchCommentsServiceDelegator.getList(searchCriteria);
					directedChallengeForm.setCommentList(com);
				} else {
					dto.setId(G4GConstants.ONE_NEGATIVE);
					if (directedChallengeForm.getCommentList() == null) {
						if (directedChallengeForm.getTwoplayermatchDTO() != null) {
							if (directedChallengeForm.getTwoplayermatchDTO().getId() != G4GConstants.ZERO) {
								dto.setId(directedChallengeForm.getTwoplayermatchDTO().getId());
							}
						}
						com.add(DirectedChallengeAction.setComments(
								directChallenge2Form.getComments(),
								G4GConstants.ZERO, dto, request));
						directedChallengeForm.setCommentList(com);
					} else {
						directedChallengeForm.getCommentList().add(
								DirectedChallengeAction.setComments(
										directChallenge2Form.getComments(),
										directedChallengeForm.getCommentList()
												.size(), dto, request));
					}
				}
			}
			directChallenge2Form.setComments(null);
			DirectedChallengeAction action = new DirectedChallengeAction();
			action.setDisplay(mapping, directedChallengeForm, operation,request, response);
			AuditUtil.getInstance().writeLog(
					AuditUtil.FILE_TYPE_G4G,
					new StringBuffer(this.getClass().getName()).append(
							CURRENTMETHOD).append(DataUtil.getCurrentMethod())
							.append(CALLINGMETHOD).append(
									DataUtil.getCallingMethod()).append(DASHES)
							.append(G4GConstants.RETURNING_BACKTOCHALLENGE)
							.append(request.getSession().getId()).toString(),
					Level.INFO);
			return mapping.findForward(COMMENT);
		}

		if (SEND.equalsIgnoreCase(operation)) {
			AuditUtil.getInstance().writeLog(
					AuditUtil.FILE_TYPE_G4G,
					new StringBuffer(this.getClass().getName()).append(
							G4GConstants.CREATINGNEWMATCH_INFO).append(
							CURRENTMETHOD).append(DataUtil.getCurrentMethod())
							.append(request.getSession().getId()).toString(),
					Level.INFO);
			TwoplayertournamentDTO twoplayertournamentDTO;
			TwoplayermatchDTO twoplayermatchDTO;
			try {
				twoplayertournamentDTO = directedChallengeForm.getTwoplayertournamentDTO(request,directedChallengeForm);
				if (request.getSession().getAttribute(TEN) == null) {
					double jackpot = Double.parseDouble(directedChallengeForm.getAmountField())*2;
					jackpot = jackpot - ChallengeCardUpdateUtil.getHouseFee(jackpot);
					twoplayertournamentDTO.setJackpot(jackpot);
					TwoPlayerTournamentServiceDelegator.add(twoplayertournamentDTO);
				}
				AuditUtil.getInstance().writeLog(AuditUtil.FILE_TYPE_G4G,new StringBuffer(this.getClass().getName()).append(
								CURRENTMETHOD).append(DataUtil.getCurrentMethod()).append(CALLINGMETHOD).append(DataUtil.getCallingMethod()).append(DASHES)
								.append(request.getSession().getId()).append(G4GConstants.SET_TWOPLAYER_MATCH).append(request.getSession().getId()).toString(), Level.INFO);
				twoplayermatchDTO = directedChallengeForm.getTwoplayermatchDTO(request, directedChallengeForm, twoplayertournamentDTO);
				twoplayermatchDTO.setMatchstatus(CHALLENGE_STATUS_PENDING);
				twoplayermatchDTO.setGamecompleted(G4GConstants.ZERO);
				String idtemp = ChallengeCardUpdateUtil.checkIdTemp(request);
				if (!idtemp.equals(null) && !STRING_NULL.equals(idtemp)) {
					twoplayermatchDTO.setId(Integer.parseInt(idtemp));
				}
			} catch (InternalException_Exception e) {
				AuditUtil.getInstance().writeLog(
						AuditUtil.FILE_TYPE_G4G,
						new StringBuffer(this.getClass().getName()).append(
								CURRENTMETHOD).append(
								DataUtil.getCurrentMethod()).append(
								CALLINGMETHOD).append(
								DataUtil.getCallingMethod()).append(DASHES)
								.append(G4GConstants.EXCE_IMPASSA).append(
										e.getMessage()).append(
										request.getSession().getId())
								.toString(), Level.INFO);

				msg = new ActionMessage(e.getMessage());
				error.clear();
				error.add(ERROR, msg);
				this.addErrors(request, error);
				return mapping.getInputForward();
			}
			// adding data to twoplayermatch
			if (request.getSession().getAttribute(TEN) == null) {
				AuditUtil.getInstance().writeLog(
						AuditUtil.FILE_TYPE_G4G,
						new StringBuffer(this.getClass().getName()).append(
								CURRENTMETHOD).append(
								DataUtil.getCurrentMethod()).append(
								CALLINGMETHOD).append(
								DataUtil.getCallingMethod()).append(DASHES)
								.append(G4GConstants.CREATE_ADD_TWOPLAYERINDB)
								.append(request.getSession().getId())
								.toString(), Level.INFO);
				twoplayermatchDTO.setPlayertwoaccepteddate(null);
				TwoPlayerMatchServiceDelegator.add(twoplayermatchDTO);
			}
			directedChallengeForm.setTwoplayermatchDTO(twoplayermatchDTO);
			if (directedChallengeForm.getCommentList() != null) {
				int i = G4GConstants.ZERO;
				for (Twoplayermatchcomments type : directedChallengeForm
						.getCommentList()) {
					if (i == G4GConstants.ZERO) {
						MatchCommentsServiceDelegator
								.deleteMatchAllComments(type);
						i++;
					}
					type.setMatchid(String.valueOf(twoplayermatchDTO.getId()));
					MatchCommentsServiceDelegator.add(type);
				}
			}
			TwoPlayerTournamentGameOptionServiceDelegator
					.deleteAll(twoplayertournamentDTO.getId());
			List<GameoptionsDTO> list = directedChallengeForm
					.getGameoptionDTO();
			Iterator<GameoptionsDTO> it = list.iterator();
			try {
				for (int i = G4GConstants.ZERO; it.hasNext(); i++) {
					if (it.hasNext()) {
						GameoptionsDTO gameoptionsDTO = it.next();
						TwoplayertournamentgameoptionsDTO tptgameoptionDTO = new TwoplayertournamentgameoptionsDTO();
						tptgameoptionDTO
								.setTournamentid(twoplayertournamentDTO);
						tptgameoptionDTO.setOptionid(gameoptionsDTO.getId());
						tptgameoptionDTO
								.setValueid(gameoptionsDTO.getValueid());
						if (request.getSession().getAttribute(TEN) == null) {
							TwoPlayerTournamentGameOptionServiceDelegator
									.add(tptgameoptionDTO);
						}
					}
				}
			} catch (NullPointerException noGameOptionsForThisGamException) {
			}
			if (twoplayermatchDTO == null) {
				twoplayermatchDTO = ((DirectedChallengeForm) request.getSession().getAttribute(DIRECTED_CHALLENGE_FORM)).getTwoplayermatchDTO();
			}

			try {
				AuditUtil.getInstance().writeLog(
						AuditUtil.FILE_TYPE_G4G,
						new StringBuffer(this.getClass().getName()).append(
								CURRENTMETHOD).append(
								DataUtil.getCurrentMethod()).append(
								CALLINGMETHOD).append(
								DataUtil.getCallingMethod()).append(DASHES)
								.append(G4GConstants.DEDUCTING_CHALLENGE)
								.append(request.getSession().getId())
								.toString(), Level.INFO);
				ChallengeCardUpdateUtil.challengeSendTimeAmountDeduction(
						request, twoplayertournamentDTO);
			} catch (InternalException_Exception exception) {
				AuditUtil.getInstance().writeLog(
						AuditUtil.FILE_TYPE_G4G,
						new StringBuffer(this.getClass().getName()).append(
								CURRENTMETHOD).append(
								DataUtil.getCurrentMethod()).append(
								CALLINGMETHOD).append(
								DataUtil.getCallingMethod()).append(DASHES)
								.append(request.getSession().getId()).append(
										G4GConstants.EXCE_IMPASSA).append(
										exception.getMessage()).append(
										request.getSession().getId())
								.toString(), Level.WARN);
				AuditUtil.getInstance().writeLog(
						AuditUtil.FILE_TYPE_G4G_FINANCIAL,
						new StringBuffer(this.getClass().getName()).append(
								CURRENTMETHOD).append(
								DataUtil.getCurrentMethod()).append(
								CALLINGMETHOD).append(
								DataUtil.getCallingMethod()).append(DASHES)
								.append(G4GConstants.EXCE_IMPASSA).append(
										exception.getMessage()).append(
										request.getSession().getId())
								.toString(), Level.ERROR);
				msg = new ActionMessage(exception.getMessage());
				error.clear();
				error.add(ERROR, msg);
				this.addErrors(request, error);
				return mapping.getInputForward();
			} catch (InvalidSessionException_Exception exception) {
				AuditUtil.getInstance().writeLog(
						AuditUtil.FILE_TYPE_G4G,
						new StringBuffer(this.getClass().getName()).append(
								CURRENTMETHOD).append(
								DataUtil.getCurrentMethod()).append(
								CALLINGMETHOD).append(
								DataUtil.getCallingMethod()).append(DASHES)
								.append(G4GConstants.SESSION_EXCE_IMPASSA)
								.append(exception.getMessage()).append(
										request.getSession().getId())
								.toString(), Level.WARN);
				AuditUtil.getInstance().writeLog(
						AuditUtil.FILE_TYPE_G4G_FINANCIAL,
						new StringBuffer(this.getClass().getName()).append(
								CURRENTMETHOD).append(
								DataUtil.getCurrentMethod()).append(
								CALLINGMETHOD).append(
								DataUtil.getCallingMethod()).append(DASHES)
								.append(G4GConstants.SESSION_EXCE_IMPASSA)
								.append(exception.getMessage()).append(
										request.getSession().getId())
								.toString(), Level.ERROR);
				msg = new ActionMessage(exception.getMessage());
				error.clear();
				error.add(ERROR, msg);
				this.addErrors(request, error);
				return mapping.getInputForward();
			} catch (InvalidBetException_Exception exception) {
				AuditUtil.getInstance().writeLog(
						AuditUtil.FILE_TYPE_G4G,
						new StringBuffer(this.getClass().getName()).append(
								CURRENTMETHOD).append(
								DataUtil.getCurrentMethod()).append(
								CALLINGMETHOD).append(
								DataUtil.getCallingMethod()).append(DASHES)
								.append(G4GConstants.BAT_EXCE_IMPASSA).append(
										exception.getMessage()).append(
										request.getSession().getId())
								.toString(), Level.WARN);
				AuditUtil.getInstance().writeLog(
						AuditUtil.FILE_TYPE_G4G_FINANCIAL,
						new StringBuffer(this.getClass().getName()).append(
								CURRENTMETHOD).append(
								DataUtil.getCurrentMethod()).append(
								CALLINGMETHOD).append(
								DataUtil.getCallingMethod()).append(DASHES)
								.append(G4GConstants.BAT_EXCE_IMPASSA).append(
										exception.getMessage()).append(
										request.getSession().getId())
								.toString(), Level.ERROR);
				msg = new ActionMessage(exception.getMessage());
				error.clear();
				error.add(ERROR, msg);
				this.addErrors(request, error);
				return mapping.getInputForward();
			} catch (MoneyException_Exception exception) {
				AuditUtil.getInstance().writeLog(
						AuditUtil.FILE_TYPE_G4G,
						new StringBuffer(this.getClass().getName()).append(
								CURRENTMETHOD).append(
								DataUtil.getCurrentMethod()).append(
								CALLINGMETHOD).append(
								DataUtil.getCallingMethod()).append(DASHES)
								.append(G4GConstants.MONEY_EXCE_IMPASSA)
								.append(exception.getMessage()).append(
										request.getSession().getId())
								.toString(), Level.WARN);
				AuditUtil.getInstance().writeLog(
						AuditUtil.FILE_TYPE_G4G_FINANCIAL,
						new StringBuffer(this.getClass().getName()).append(
								CURRENTMETHOD).append(
								DataUtil.getCurrentMethod()).append(
								CALLINGMETHOD).append(
								DataUtil.getCallingMethod()).append(DASHES)
								.append(G4GConstants.MONEY_EXCE_IMPASSA)
								.append(exception.getMessage()).append(
										request.getSession().getId())
								.toString(), Level.ERROR);
				msg = new ActionMessage(exception.getMessage());
				error.clear();
				error.add(ERROR, msg);
				this.addErrors(request, error);
				return mapping.getInputForward();
			} catch (UserNotFoundException_Exception exception) {
				AuditUtil.getInstance().writeLog(
						AuditUtil.FILE_TYPE_G4G,
						new StringBuffer(this.getClass().getName()).append(
								CURRENTMETHOD).append(
								DataUtil.getCurrentMethod()).append(
								CALLINGMETHOD).append(
								DataUtil.getCallingMethod()).append(DASHES)
								.append(G4GConstants.USER_NOT_FOUND_EXCE)
								.append(exception.getMessage()).append(
										request.getSession().getId())
								.toString(), Level.WARN);
				AuditUtil.getInstance().writeLog(
						AuditUtil.FILE_TYPE_G4G_FINANCIAL,
						new StringBuffer(this.getClass().getName()).append(
								CURRENTMETHOD).append(
								DataUtil.getCurrentMethod()).append(
								CALLINGMETHOD).append(
								DataUtil.getCallingMethod()).append(DASHES)
								.append(G4GConstants.USER_NOT_FOUND_EXCE)
								.append(exception.getMessage()).append(
										request.getSession().getId())
								.toString(), Level.ERROR);
				msg = new ActionMessage(exception.getMessage());
				error.clear();
				error.add(ERROR, msg);
				this.addErrors(request, error);
				return mapping.getInputForward();
			}
			// message creation
			try {
				if (twoplayermatchDTO.getPlayertwoid() != null) {
					ChallengeNotification challengeNotification = new ChallengeNotification(twoplayermatchDTO.getPlayertwoid().getId(),twoplayermatchDTO.getPlayeroneid().getId(),
							twoplayermatchDTO.getId(), twoplayermatchDTO.getScheduledstartdate());
					AuditUtil.getInstance().writeLog(AuditUtil.FILE_TYPE_G4G,new StringBuffer(this.getClass().getName()).append(
									G4GConstants.SENDING_CHALLENGE).append(CURRENTMETHOD).append(DataUtil.getCurrentMethod()).append(request.getSession().getId()).toString(),Level.INFO);
					NotificationServiceDelegator.sendNotification(challengeNotification, request);
				}
			} catch (NullPointerException e) {
				//
				AuditUtil.getInstance().writeLog(AuditUtil.FILE_TYPE_G4G,new StringBuffer(this.getClass().getName()).append(G4GConstants.SENDING_CHALLENGE).append(CURRENTMETHOD).append(DataUtil.getCurrentMethod()).append(BLANK)
								.append(GOT).append(EXCEPTION).append(request.getSession().getId()).toString(), Level.INFO);
			}
		}
		if (ACCEPT.equals(operation)) {
			TwoplayermatchDTO twoplayermatchDTO = directedChallengeForm
					.getTwoplayermatchDTO();
			TwoplayertournamentDTO twoplayertournamentDTO = twoplayermatchDTO
					.getTwoplayertournament();
			if (twoplayertournamentDTO.getEntryfee() > DataUtil.getUserDTO(
					request).getBalance()) {
				AuditUtil.getInstance().writeLog(
						AuditUtil.FILE_TYPE_G4G,
						new StringBuffer(this.getClass().getName()).append(
								CURRENTMETHOD).append(
								DataUtil.getCurrentMethod()).append(
								G4GConstants.NOT_BALANCE).append(
								G4GConstants.ENTRY_FEE).append(
								twoplayertournamentDTO.getEntryfee()).append(
								request.getSession().getId()).toString(),
						Level.INFO);
				msg = new ActionMessage(ERRORS_LESSBALANCE);
				error.clear();
				error.add(AMOUNT_FIELD, msg);
				this.saveErrors(request, error);
				return mapping.getInputForward();
			}

			if (request.getSession().getAttribute(TOURNAMENT_ID) != null) {
				if (twoplayermatchDTO.getPlayeroneid() == null) {
					twoplayermatchDTO.setPlayeroneid(DataUtil.getUserDTO(
							request).getPlayerDTO());
					twoplayermatchDTO.setPlayeroneaccepteddate(DataUtil
							.todayGMT());
					directedChallengeForm.setPlayerDTO(twoplayermatchDTO
							.getPlayeroneid());
				} else {
					twoplayermatchDTO.setPlayertwoid(DataUtil.getUserDTO(
							request).getPlayerDTO());
					twoplayermatchDTO.setPlayertwoaccepteddate(DataUtil
							.todayGMT());
					directedChallengeForm.setPlayerDTO(twoplayermatchDTO
							.getPlayeroneid());
					directedChallengeForm.setOppPlayerDTO(DataUtil.getUserDTO(
							request).getPlayerDTO());
				}

				AuditUtil.getInstance().writeLog(
						AuditUtil.FILE_TYPE_G4G,
						new StringBuffer(this.getClass().getName()).append(
								CURRENTMETHOD).append(
								DataUtil.getCurrentMethod()).append(
								G4GConstants.UPDATE_TOURNAMENT).append(
								request.getSession().getId()).toString(),
						Level.INFO);
				TwoPlayerMatchServiceDelegator.update(twoplayermatchDTO);
				try {
					AuditUtil.getInstance().writeLog(
							AuditUtil.FILE_TYPE_G4G,
							new StringBuffer(this.getClass().getName()).append(
									G4GConstants.DEDUCTIONG_AMOUNT).append(
									CURRENTMETHOD).append(
									DataUtil.getCurrentMethod()).append(
									request.getSession().getId()).toString(),
							Level.INFO);
					ChallengeCardUpdateUtil.challengeAcceptTimeAmountDeduction(
							request, twoplayertournamentDTO);
				} catch (InternalException_Exception exception) {
					AuditUtil.getInstance().writeLog(
							AuditUtil.FILE_TYPE_G4G,
							new StringBuffer(this.getClass().getName()).append(
									CURRENTMETHOD).append(
									DataUtil.getCurrentMethod()).append(
									CALLINGMETHOD).append(
									DataUtil.getCallingMethod()).append(DASHES)
									.append(G4GConstants.USER_NOT_FOUND_EXCE)
									.append(exception.getMessage()).append(
											request.getSession().getId())
									.toString(), Level.WARN);
					AuditUtil.getInstance().writeLog(
							AuditUtil.FILE_TYPE_G4G_FINANCIAL,
							new StringBuffer(this.getClass().getName()).append(
									CURRENTMETHOD).append(
									DataUtil.getCurrentMethod()).append(
									CALLINGMETHOD).append(
									DataUtil.getCallingMethod()).append(DASHES)
									.append(G4GConstants.USER_NOT_FOUND_EXCE)
									.append(exception.getMessage()).append(
											request.getSession().getId())
									.toString(), Level.ERROR);
					msg = new ActionMessage(exception.getMessage());
					error.clear();
					error.add(ERROR, msg);
					this.addErrors(request, error);
					return mapping.getInputForward();
				} catch (InvalidSessionException_Exception exception) {
					AuditUtil.getInstance().writeLog(
							AuditUtil.FILE_TYPE_G4G,
							new StringBuffer(this.getClass().getName()).append(
									CURRENTMETHOD).append(
									DataUtil.getCurrentMethod()).append(
									CALLINGMETHOD).append(
									DataUtil.getCallingMethod()).append(DASHES)
									.append(G4GConstants.SESSION_EXCE_IMPASSA)
									.append(exception.getMessage()).append(
											request.getSession().getId())
									.toString(), Level.WARN);
					msg = new ActionMessage(exception.getMessage());
					error.clear();
					error.add(ERROR, msg);
					this.addErrors(request, error);
					return mapping.getInputForward();
				} catch (InvalidBetException_Exception exception) {
					AuditUtil.getInstance().writeLog(
							AuditUtil.FILE_TYPE_G4G,
							new StringBuffer(this.getClass().getName()).append(
									CURRENTMETHOD).append(
									DataUtil.getCurrentMethod()).append(
									CALLINGMETHOD).append(
									DataUtil.getCallingMethod()).append(DASHES)
									.append(G4GConstants.BAT_EXCE_IMPASSA)
									.append(exception.getMessage()).append(
											request.getSession().getId())
									.toString(), Level.WARN);
					msg = new ActionMessage(exception.getMessage());
					error.clear();
					error.add(ERROR, msg);
					this.addErrors(request, error);
					return mapping.getInputForward();
				} catch (MoneyException_Exception exception) {
					AuditUtil.getInstance().writeLog(AuditUtil.FILE_TYPE_G4G,new StringBuffer(this.getClass().getName()).append(
							CURRENTMETHOD).append(DataUtil.getCurrentMethod()).append(CALLINGMETHOD).append(DataUtil.getCallingMethod()).append(DASHES).append(G4GConstants.MONEY_EXCE_IMPASSA).append(exception.getMessage()).append(
							request.getSession().getId()).toString(), Level.WARN);

					msg = new ActionMessage(exception.getMessage());
					error.clear();
					error.add(ERROR, msg);
					this.addErrors(request, error);
					return mapping.getInputForward();
				} catch (UserNotFoundException_Exception exception) {
					AuditUtil.getInstance().writeLog(
							AuditUtil.FILE_TYPE_G4G,
							new StringBuffer(this.getClass().getName()).append(
									CURRENTMETHOD).append(
									DataUtil.getCurrentMethod()).append(
									CALLINGMETHOD).append(
									DataUtil.getCallingMethod()).append(DASHES)
									.append(G4GConstants.USER_NOT_FOUND_EXCE)
									.append(exception.getMessage()).append(
											request.getSession().getId())
									.toString(), Level.WARN);

					msg = new ActionMessage(exception.getMessage());
					error.clear();
					error.add(ERROR, msg);
					this.addErrors(request, error);
					return mapping.getInputForward();
				}

				AuditUtil.getInstance().writeLog(AuditUtil.FILE_TYPE_G4G,new StringBuffer(this.getClass().getName()).append(
								G4GConstants.SENDING_JOIN_NOTIFICATION).append(CURRENTMETHOD).append(DataUtil.getCurrentMethod()).append(
								request.getSession().getId()).toString(),Level.INFO);

				TournamentJoinNotification tjn = new TournamentJoinNotification(DataUtil.getUserDTO(request).getPlayerDTO().getId(),
						G4GConstants.ZERO, twoplayermatchDTO.getTwoplayertournament().getId(),twoplayermatchDTO.getId(), twoplayermatchDTO.getScheduledstartdate());
				try {
					NotificationServiceDelegator.sendNotification(tjn, request);
				} catch (NullPointerException e) {

				}
				return mapping.findForward(SUCCESS);
			} else {
				twoplayermatchDTO.setPlayertwoid(DataUtil.getUserDTO(request).getPlayerDTO());
				twoplayermatchDTO.setPlayertwoaccepteddate(DataUtil.todayGMT());
				// check for siteId
				if (twoplayermatchDTO.getPlayeroneid().getSkin().getId() == twoplayermatchDTO.getPlayertwoid().getSkin().getId()) {
					if (twoplayertournamentDTO.getEntryfee() > DataUtil.getUserDTO(request).getBalance()) {
						msg = new ActionMessage(ERRORS_LESSBALANCE);
						error.clear();
						error.add(AMOUNT_FIELD, msg);
						DirectedChallengeAction action = new DirectedChallengeAction();
						action.setDisplay(mapping, directedChallengeForm,operation, request, response);
						return mapping.getInputForward();
					}
					AuditUtil.getInstance().writeLog(
							AuditUtil.FILE_TYPE_G4G,
							new StringBuffer(this.getClass().getName()).append(
									CURRENTMETHOD).append(
									DataUtil.getCurrentMethod()).append(
									CALLINGMETHOD).append(
									DataUtil.getCallingMethod()).append(DASHES)
									.append(G4GConstants.UPDATING_TOURNAMENT)
									.append(request.getSession().getId())
									.toString(), Level.INFO);

					TwoPlayerTournamentServiceDelegator
							.update(twoplayertournamentDTO);
				}
				AuditUtil.getInstance().writeLog(
						AuditUtil.FILE_TYPE_G4G,
						new StringBuffer(this.getClass().getName()).append(
								CURRENTMETHOD).append(
								DataUtil.getCurrentMethod()).append(
								CALLINGMETHOD).append(
								DataUtil.getCallingMethod()).append(DASHES)
								.append(G4GConstants.UPDATING_TWOPLAYER_MATCH)
								.append(request.getSession().getId())
								.toString(), Level.INFO);
				TwoPlayerMatchServiceDelegator.update(twoplayermatchDTO);
			}

			// handling money transaction for the user
			try {
				AuditUtil.getInstance().writeLog(
						AuditUtil.FILE_TYPE_G4G,
						new StringBuffer(this.getClass().getName()).append(
								G4GConstants.DEDUCTIONG_AMOUNT).append(
								CURRENTMETHOD).append(
								DataUtil.getCurrentMethod()).append(
								request.getSession().getId()).toString(),
						Level.INFO);

				ChallengeCardUpdateUtil.challengeAcceptTimeAmountDeduction(
						request, twoplayertournamentDTO);
			} catch (InternalException_Exception exception) {
				AuditUtil.getInstance().writeLog(
						AuditUtil.FILE_TYPE_G4G,
						new StringBuffer(this.getClass().getName()).append(
								CURRENTMETHOD).append(
								DataUtil.getCurrentMethod()).append(
								CALLINGMETHOD).append(
								DataUtil.getCallingMethod()).append(DASHES)
								.append(G4GConstants.EXCE_IMPASSA).append(
										exception.getMessage()).append(
										request.getSession().getId())
								.toString(), Level.WARN);
				AuditUtil.getInstance().writeLog(
						AuditUtil.FILE_TYPE_G4G_FINANCIAL,
						new StringBuffer(this.getClass().getName()).append(
								CURRENTMETHOD).append(
								DataUtil.getCurrentMethod()).append(
								CALLINGMETHOD).append(
								DataUtil.getCallingMethod()).append(DASHES)
								.append(G4GConstants.EXCE_IMPASSA).append(
										exception.getMessage()).append(
										request.getSession().getId())
								.toString(), Level.ERROR);
				msg = new ActionMessage(exception.getMessage());
				error.clear();
				error.add(ERROR, msg);
				this.addErrors(request, error);
				return mapping.getInputForward();
			} catch (InvalidSessionException_Exception exception) {
				AuditUtil.getInstance().writeLog(
						AuditUtil.FILE_TYPE_G4G,
						new StringBuffer(this.getClass().getName()).append(
								CURRENTMETHOD).append(
								DataUtil.getCurrentMethod()).append(
								CALLINGMETHOD).append(
								DataUtil.getCallingMethod()).append(DASHES)
								.append(G4GConstants.SESSION_EXCE_IMPASSA)
								.append(exception.getMessage()).append(
										request.getSession().getId())
								.toString(), Level.WARN);
				AuditUtil.getInstance().writeLog(
						AuditUtil.FILE_TYPE_G4G_FINANCIAL,
						new StringBuffer(this.getClass().getName()).append(
								CURRENTMETHOD).append(
								DataUtil.getCurrentMethod()).append(
								CALLINGMETHOD).append(
								DataUtil.getCallingMethod()).append(DASHES)
								.append(G4GConstants.SESSION_EXCE_IMPASSA)
								.append(exception.getMessage()).append(
										request.getSession().getId())
								.toString(), Level.ERROR);
				msg = new ActionMessage(exception.getMessage());
				error.clear();
				error.add(ERROR, msg);
				this.addErrors(request, error);
				return mapping.getInputForward();
			} catch (InvalidBetException_Exception exception) {
				AuditUtil.getInstance().writeLog(
						AuditUtil.FILE_TYPE_G4G,
						new StringBuffer(this.getClass().getName()).append(
								CURRENTMETHOD).append(
								DataUtil.getCurrentMethod()).append(
								CALLINGMETHOD).append(
								DataUtil.getCallingMethod()).append(DASHES)
								.append(G4GConstants.BAT_EXCE_IMPASSA).append(
										exception.getMessage()).append(
										request.getSession().getId())
								.toString(), Level.WARN);
				AuditUtil.getInstance().writeLog(
						AuditUtil.FILE_TYPE_G4G_FINANCIAL,
						new StringBuffer(this.getClass().getName()).append(
								CURRENTMETHOD).append(
								DataUtil.getCurrentMethod()).append(
								CALLINGMETHOD).append(
								DataUtil.getCallingMethod()).append(DASHES)
								.append(G4GConstants.BAT_EXCE_IMPASSA).append(
										exception.getMessage()).append(
										request.getSession().getId())
								.toString(), Level.ERROR);
				msg = new ActionMessage(exception.getMessage());
				error.clear();
				error.add(ERROR, msg);
				this.addErrors(request, error);
				return mapping.getInputForward();
			} catch (MoneyException_Exception exception) {
				AuditUtil.getInstance().writeLog(
						AuditUtil.FILE_TYPE_G4G,
						new StringBuffer(this.getClass().getName()).append(
								CURRENTMETHOD).append(
								DataUtil.getCurrentMethod()).append(
								CALLINGMETHOD).append(
								DataUtil.getCallingMethod()).append(DASHES)
								.append(G4GConstants.MONEY_EXCE_IMPASSA)
								.append(exception.getMessage()).append(
										request.getSession().getId())
								.toString(), Level.WARN);
				AuditUtil.getInstance().writeLog(
						AuditUtil.FILE_TYPE_G4G_FINANCIAL,
						new StringBuffer(this.getClass().getName()).append(
								CURRENTMETHOD).append(
								DataUtil.getCurrentMethod()).append(
								CALLINGMETHOD).append(
								DataUtil.getCallingMethod()).append(DASHES)
								.append(G4GConstants.MONEY_EXCE_IMPASSA)
								.append(exception.getMessage()).append(
										request.getSession().getId())
								.toString(), Level.ERROR);
				msg = new ActionMessage(exception.getMessage());
				error.clear();
				error.add(ERROR, msg);
				this.addErrors(request, error);
				return mapping.getInputForward();
			} catch (UserNotFoundException_Exception exception) {
				AuditUtil.getInstance().writeLog(
						AuditUtil.FILE_TYPE_G4G,
						new StringBuffer(this.getClass().getName()).append(
								CURRENTMETHOD).append(
								DataUtil.getCurrentMethod()).append(
								CALLINGMETHOD).append(
								DataUtil.getCallingMethod()).append(DASHES)
								.append(G4GConstants.USER_NOT_FOUND_EXCE)
								.append(exception.getMessage()).append(
										request.getSession().getId())
								.toString(), Level.WARN);
				AuditUtil.getInstance().writeLog(
						AuditUtil.FILE_TYPE_G4G_FINANCIAL,
						new StringBuffer(this.getClass().getName()).append(
								CURRENTMETHOD).append(
								DataUtil.getCurrentMethod()).append(
								CALLINGMETHOD).append(
								DataUtil.getCallingMethod()).append(DASHES)
								.append(G4GConstants.USER_NOT_FOUND_EXCE)
								.append(exception.getMessage()).append(
										request.getSession().getId())
								.toString(), Level.ERROR);
				msg = new ActionMessage(exception.getMessage());
				error.clear();
				error.add(ERROR, msg);
				this.addErrors(request, error);
				return mapping.getInputForward();
			}

			// send a mail to message center

			AuditUtil.getInstance().writeLog(
					AuditUtil.FILE_TYPE_G4G,
					new StringBuffer(this.getClass().getName()).append(
							G4GConstants.SENDING_CHALLENGE).append(
							CURRENTMETHOD).append(DataUtil.getCurrentMethod())
							.append(request.getSession().getId()).toString(),
					Level.INFO);
			MatchConfirmNotification confirmNotification = new MatchConfirmNotification(
					twoplayermatchDTO.getPlayeroneid().getId(),
					twoplayermatchDTO.getPlayertwoid().getId(),
					twoplayermatchDTO.getId(), ACCEPT_CHALLENGE_VALUE,
					twoplayermatchDTO.getScheduledstartdate());
			try {
				NotificationServiceDelegator.sendNotification(
						confirmNotification, request);
			} catch (NullPointerException e) {

			}
		}

		if (operation == null) {
			operation = request.getParameter(OPERATION);
			String twoplayermatchId = request.getParameter(TWO_PLAYER_MATCH_ID);

			if (REJECT.equals(operation) && twoplayermatchId != null) {
				AuditUtil.getInstance().writeLog(
						AuditUtil.FILE_TYPE_G4G,
						new StringBuffer(this.getClass().getName()).append(
								G4GConstants.PERFORMING_OPERATION).append(
								CURRENTMETHOD).append(
								DataUtil.getCurrentMethod()).append(
								request.getSession().getId()).toString(),
						Level.INFO);
				directedChallengeForm = new DirectedChallengeForm();
				TwoplayermatchDTO dto = new TwoplayermatchDTO();
				dto.setId(Integer.parseInt(twoplayermatchId));
				directedChallengeForm
						.setTwoplayermatchDTO(TwoPlayerMatchServiceDelegator
								.get(dto));
				request.getSession().setAttribute(DIRECTED_CHALLENGE_FORM,
						directedChallengeForm);
			}
		}

		if (REJECT.equals(operation) || DISSOLVE.equals(operation)) {
			TwoplayermatchDTO twoplayermatchDTO;
			try {
				twoplayermatchDTO = directedChallengeForm
						.getTwoplayermatchDTO();
			} catch (NullPointerException e) {
				twoplayermatchDTO = TwoPlayerMatchServiceDelegator
						.getDTOFromId(Integer.parseInt(request
								.getParameter(G4GConstants.TWOPLAYERMATCHID)));
			}
			twoplayermatchDTO = TwoPlayerMatchServiceDelegator
					.get(twoplayermatchDTO);
			TwoplayertournamentDTO twoplayertournamentDTO = twoplayermatchDTO
					.getTwoplayertournament();
			try {
				if (DISSOLVE.equals(operation)) {
					ChallengeCardUpdateUtil.balanceRecovery(request,
							twoplayermatchDTO, twoplayertournamentDTO);
					twoplayertournamentDTO.setCancellationdate(DataUtil
							.todayGMT());
					twoplayermatchDTO.setCompleteddate(DataUtil.todayGMT());
					twoplayermatchDTO.setGamecompleted(GAME_STAUS_CANCELLED);
				}
			} catch (InternalException_Exception exception) {
				AuditUtil.getInstance().writeLog(
						AuditUtil.FILE_TYPE_G4G,
						new StringBuffer(this.getClass().getName()).append(
								CURRENTMETHOD).append(
								DataUtil.getCurrentMethod()).append(
								CALLINGMETHOD).append(
								DataUtil.getCallingMethod()).append(DASHES)
								.append(G4GConstants.EXCE_IMPASSA).append(
										exception.getMessage()).append(
										request.getSession().getId())
								.toString(), Level.WARN);
				AuditUtil.getInstance().writeLog(
						AuditUtil.FILE_TYPE_G4G_FINANCIAL,
						new StringBuffer(this.getClass().getName()).append(
								CURRENTMETHOD).append(
								DataUtil.getCurrentMethod()).append(
								CALLINGMETHOD).append(
								DataUtil.getCallingMethod()).append(DASHES)
								.append(G4GConstants.EXCE_IMPASSA).append(
										exception.getMessage()).append(
										request.getSession().getId())
								.toString(), Level.ERROR);
				msg = new ActionMessage(exception.getMessage());
				error.clear();
				error.add(ERROR, msg);
				this.addErrors(request, error);
				return mapping.getInputForward();
			} catch (InvalidBetException_Exception exception) {
				AuditUtil.getInstance().writeLog(
						AuditUtil.FILE_TYPE_G4G,
						new StringBuffer(this.getClass().getName()).append(
								CURRENTMETHOD).append(
								DataUtil.getCurrentMethod()).append(
								CALLINGMETHOD).append(
								DataUtil.getCallingMethod()).append(DASHES)
								.append(G4GConstants.BAT_EXCE_IMPASSA).append(
										exception.getMessage()).append(
										request.getSession().getId())
								.toString(), Level.WARN);
				AuditUtil.getInstance().writeLog(
						AuditUtil.FILE_TYPE_G4G_FINANCIAL,
						new StringBuffer(this.getClass().getName()).append(
								CURRENTMETHOD).append(
								DataUtil.getCurrentMethod()).append(
								CALLINGMETHOD).append(
								DataUtil.getCallingMethod()).append(DASHES)
								.append(G4GConstants.BAT_EXCE_IMPASSA).append(
										exception.getMessage()).append(
										request.getSession().getId())
								.toString(), Level.ERROR);
				msg = new ActionMessage(exception.getMessage());
				error.clear();
				error.add(ERROR, msg);
				this.addErrors(request, error);
				return mapping.getInputForward();
			} catch (InvalidSessionException_Exception exception) {
				AuditUtil.getInstance().writeLog(
						AuditUtil.FILE_TYPE_G4G,
						new StringBuffer(this.getClass().getName()).append(
								CURRENTMETHOD).append(
								DataUtil.getCurrentMethod()).append(
								CALLINGMETHOD).append(
								DataUtil.getCallingMethod()).append(DASHES)
								.append(G4GConstants.SESSION_EXCE_IMPASSA)
								.append(exception.getMessage()).append(
										request.getSession().getId())
								.toString(), Level.WARN);
				AuditUtil.getInstance().writeLog(
						AuditUtil.FILE_TYPE_G4G_FINANCIAL,
						new StringBuffer(this.getClass().getName()).append(
								CURRENTMETHOD).append(
								DataUtil.getCurrentMethod()).append(
								CALLINGMETHOD).append(
								DataUtil.getCallingMethod()).append(DASHES)
								.append(G4GConstants.SESSION_EXCE_IMPASSA)
								.append(exception.getMessage()).append(
										request.getSession().getId())
								.toString(), Level.ERROR);
				msg = new ActionMessage(exception.getMessage());
				error.clear();
				error.add(ERROR, msg);
				this.addErrors(request, error);
				return mapping.getInputForward();
			} catch (MoneyException_Exception exception) {
				AuditUtil.getInstance().writeLog(
						AuditUtil.FILE_TYPE_G4G,
						new StringBuffer(this.getClass().getName()).append(
								CURRENTMETHOD).append(
								DataUtil.getCurrentMethod()).append(
								CALLINGMETHOD).append(
								DataUtil.getCallingMethod()).append(DASHES)
								.append(G4GConstants.MONEY_EXCE_IMPASSA)
								.append(exception.getMessage()).append(
										request.getSession().getId())
								.toString(), Level.WARN);
				AuditUtil.getInstance().writeLog(
						AuditUtil.FILE_TYPE_G4G_FINANCIAL,
						new StringBuffer(this.getClass().getName()).append(
								CURRENTMETHOD).append(
								DataUtil.getCurrentMethod()).append(
								CALLINGMETHOD).append(
								DataUtil.getCallingMethod()).append(DASHES)
								.append(G4GConstants.MONEY_EXCE_IMPASSA)
								.append(exception.getMessage()).append(
										request.getSession().getId())
								.toString(), Level.ERROR);
				msg = new ActionMessage(exception.getMessage());
				error.clear();
				error.add(ERROR, msg);
				this.addErrors(request, error);
				return mapping.getInputForward();
			} catch (UserNotFoundException_Exception exception) {
				AuditUtil.getInstance().writeLog(
						AuditUtil.FILE_TYPE_G4G,
						new StringBuffer(this.getClass().getName()).append(
								CURRENTMETHOD).append(
								DataUtil.getCurrentMethod()).append(
								CALLINGMETHOD).append(
								DataUtil.getCallingMethod()).append(DASHES)
								.append(G4GConstants.USER_NOT_FOUND_EXCE)
								.append(exception.getMessage()).append(
										request.getSession().getId())
								.toString(), Level.WARN);
				AuditUtil.getInstance().writeLog(
						AuditUtil.FILE_TYPE_G4G_FINANCIAL,
						new StringBuffer(this.getClass().getName()).append(
								CURRENTMETHOD).append(
								DataUtil.getCurrentMethod()).append(
								CALLINGMETHOD).append(
								DataUtil.getCallingMethod()).append(DASHES)
								.append(G4GConstants.USER_NOT_FOUND_EXCE)
								.append(exception.getMessage()).append(
										request.getSession().getId())
								.toString(), Level.ERROR);
				msg = new ActionMessage(exception.getMessage());
				error.clear();
				error.add(ERROR, msg);
				this.addErrors(request, error);
				return mapping.getInputForward();
			}
			AuditUtil.getInstance().writeLog(
					AuditUtil.FILE_TYPE_G4G,
					new StringBuffer(this.getClass().getName()).append(
							G4GConstants.UPDATE_TWOPLAYERMATCHDTO).append(
							CURRENTMETHOD).append(DataUtil.getCurrentMethod())
							.append(request.getSession().getId()).toString(),
					Level.INFO);
			TwoPlayerTournamentServiceDelegator.update(twoplayertournamentDTO);
			TwoPlayerMatchServiceDelegator.update(twoplayermatchDTO);
			MatchConfirmNotification confirmNotification = null;

			if (REJECT.equals(operation)) {
				AuditUtil.getInstance().writeLog(
						AuditUtil.FILE_TYPE_G4G,
						new StringBuffer(this.getClass().getName()).append(
								G4GConstants.REJECT_TIME_NOTIFICATION).append(
								CURRENTMETHOD).append(
								DataUtil.getCurrentMethod()).append(
								request.getSession().getId()).toString(),
						Level.INFO);
				confirmNotification = new MatchConfirmNotification(
						twoplayermatchDTO.getPlayeroneid().getId(),
						twoplayermatchDTO.getPlayertwoid().getId(),
						twoplayermatchDTO.getId(), REJECT_CHALLENGE_VALUE,
						twoplayermatchDTO.getScheduledstartdate());
			}
			if (twoplayermatchDTO.getPlayertwoid() != null) {
				if (DISSOLVE.equals(operation)) {
					AuditUtil.getInstance().writeLog(AuditUtil.FILE_TYPE_G4G,new StringBuffer(this.getClass().getName()).append(G4GConstants.SEND_DISSOLVING_NOTIFICATION)
									.append(CURRENTMETHOD).append(DataUtil.getCurrentMethod()).append(request.getSession().getId()).toString(), Level.INFO);
					confirmNotification = new MatchConfirmNotification(
							twoplayermatchDTO.getPlayertwoid().getId(),
							twoplayermatchDTO.getPlayeroneid().getId(),
							twoplayermatchDTO.getId(), CANCEL_CHALLENGE_VALUE,
							twoplayermatchDTO.getScheduledstartdate());
				}
				AuditUtil.getInstance().writeLog(AuditUtil.FILE_TYPE_G4G,new StringBuffer(this.getClass().getName()).append(
								G4GConstants.SENDING_NOTIFICATION).append(CURRENTMETHOD).append(DataUtil.getCurrentMethod()).append(request.getSession().getId()).toString(),Level.INFO);
				try {
					NotificationServiceDelegator.sendNotification(confirmNotification, request);
				} catch (NullPointerException e) {

				}
			}
		}
		return mapping.findForward(SUCCESS);
	}
}
