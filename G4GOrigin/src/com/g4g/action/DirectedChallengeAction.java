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
import static com.g4g.utils.G4GConstants.BLANK;
import static com.g4g.utils.G4GConstants.CALLINGMETHOD;
import static com.g4g.utils.G4GConstants.CURRENTMETHOD;
import static com.g4g.utils.G4GConstants.DASHES;
import static com.g4g.utils.G4GConstants.DB;
import static com.g4g.utils.G4GConstants.DISPLAY_STARTS;
import static com.g4g.utils.G4GConstants.ERROR;
import static com.g4g.utils.G4GConstants.HOMEPAGE;
import static com.g4g.utils.G4GConstants.SUBMIT_STARTS;
import static com.g4g.utils.G4GConstants.USERDTO;

import java.text.SimpleDateFormat;
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
import com.g4g.delegator.PlayerServiceDelegator;
import com.g4g.delegator.SubNationalCodeServiceDelegator;
import com.g4g.delegator.TwoPlayerMatchServiceDelegator;
import com.g4g.delegator.TwoPlayerTournamentServiceDelegator;
import com.g4g.dto.GameoptionsDTO;
import com.g4g.dto.TwoplayermatchDTO;
import com.g4g.dto.Twoplayermatchcomments;
import com.g4g.dto.TwoplayertournamentDTO;
import com.g4g.forms.BaseForm;
import com.g4g.forms.DirectedChallengeForm;
import com.g4g.utils.AuditUtil;
import com.g4g.utils.ChallengeCardUpdateUtil;
import com.g4g.utils.DataUtil;
import com.g4g.utils.DataValidator;
import com.g4g.utils.DateUtil;
import com.g4g.utils.G4GConstants;
import com.impessa.worldgaming.client.InternalException_Exception;
import com.impessa.worldgaming.client.InvalidBetException_Exception;
import com.impessa.worldgaming.client.InvalidSessionException_Exception;
import com.impessa.worldgaming.client.MoneyException_Exception;
import com.impessa.worldgaming.client.UserNotFoundException_Exception;

/**
 * MyEclipse Struts Creation date: 02-15-2008.
 *
 * @author ankur
 * @struts.action path="/displayDirectedChallenge"
 *                name="displayDirectedChallengeForm"
 *                attribute="directedChallengeForm" scope="request"
 * @struts.action-forward name="success"
 *                        path="/WebContent/directedChallenge.jsp"
 */
public class DirectedChallengeAction extends BaseAction {

	/**
	 * Logic to perform while displaying a challenge card.
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
	 * @see com.g4g.action.BaseAction#display(org.apache.struts.action.ActionMapping,
	 *      com.g4g.forms.BaseForm, java.lang.String,
	 *      javax.servlet.http.HttpServletRequest,
	 *      javax.servlet.http.HttpServletResponse)
	 */
	@Override
	public ActionForward display(ActionMapping mapping, BaseForm form,
			String operation, HttpServletRequest request,
			HttpServletResponse response) {
		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,
				new StringBuffer(this.getClass().getName()).append(
						G4GConstants.CALLINGMETHOD).append(
						DataUtil.getCallingMethod()).append(
						G4GConstants.DISPLAY_STARTS)
						.append(G4GConstants.DASHES).append(
								request.getSession().getId()).toString(),
				Level.INFO);

		if (request.getSession().getAttribute(USERDTO) == null) {
			AuditUtil.getInstance().writeLog(
					AuditUtil.FILE_TYPE_G4G,
					new StringBuffer(this.getClass().getName()).append(
							CALLINGMETHOD).append(DataUtil.getCallingMethod())
							.append(DISPLAY_STARTS).append(DASHES).append(
									G4GConstants.USER_NOT_RECOGNIZE).append(
									request.getSession().getId()).toString(),
					Level.INFO);
			return mapping.findForward(HOMEPAGE);
		}
		DirectedChallengeForm directedChallengeForm = (DirectedChallengeForm) form;
		String game;
		String twoplayermatchid = null;
		// this is opponent playerId
		int playerId;
		int tounamentId;

		// get request parameter and attributes
		// Trying first for the case of tournament mode
		if (request.getParameter(G4GConstants.TOURNAMENT_ID) != null) {
			tounamentId = Integer.parseInt(request
					.getParameter(G4GConstants.TOURNAMENT_ID));
			request.setAttribute(G4GConstants.TOURNAMENT_ID, tounamentId);
			TwoplayermatchDTO matchdto = TwoPlayerMatchServiceDelegator
					.getDtoForChallenge(tounamentId, request);
			if (matchdto != null && matchdto.getId() > G4GConstants.ZERO) {
				twoplayermatchid = String.valueOf(matchdto.getId());
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
		// no tournament and only twoplayermatchid . it may be
		// acceptance or
		// view card case.
		if (DataValidator.isNull(twoplayermatchid)) {
			twoplayermatchid = request.getParameter(G4GConstants.TWO_PLAYER_MATCH_ID) != null ? request
					.getParameter(G4GConstants.TWO_PLAYER_MATCH_ID): (String) request.getAttribute(G4GConstants.TWO_PLAYER_MATCH_ID);
		}

		// still null, fine only open and direct challenges , bet i will
		// get
		// gameid and / or playerId
		if (twoplayermatchid == null
				|| G4GConstants.STRING_NULL.equals(twoplayermatchid)) {
			game = request.getParameter(G4GConstants.GAMEID) != null ? request
					.getParameter(G4GConstants.GAMEID) : (String) request
					.getAttribute(G4GConstants.GAMEID);
			/* get opponent from req. parameter */
			/* if not present or else will return 0 on all exception */
			playerId = DataUtil.getInteger(request
					.getParameter(G4GConstants.PLAYERID) != null ? request
					.getParameter(G4GConstants.PLAYERID) : (String) request
					.getAttribute(G4GConstants.PLAYERID));
			if (playerId != G4GConstants.ZERO) {
				// direct challenge
				AuditUtil.getInstance().writeLog(
						AuditUtil.FILE_TYPE_G4G,
						new StringBuffer(this.getClass().getName()).append(
								DataUtil.getCallingMethod()).append(
								G4GConstants.OPPONENT_IMPASSA_INFO).append(
								request.getSession().getId()).toString(),
						Level.INFO);
				directedChallengeForm.setOppPlayerDTO(PlayerServiceDelegator
						.getPlayer(playerId));
				try {
					try {
						directedChallengeForm.setOppUser(G4GFinancialDelegator
								.getUserInfo(directedChallengeForm
										.getOppPlayerDTO().getEmailaddress()));
					} catch (UserNotFoundException_Exception e) {
						AuditUtil.getInstance().writeLog(
								AuditUtil.FILE_TYPE_HIBERNATE,
								new StringBuffer(this.getClass().getName())
										.append(G4GConstants.COLON_WITH_SPACES).append(
												DataUtil.getCurrentMethod())
										.append(G4GConstants.DASHES).append(
												e.getMessage()).append(
												DataUtil.getUserDTO(request)
														.getPlayerDTO()
														.getScreenname())
										.append(request.getSession().getId())
										.toString(), Level.ERROR);
						AuditUtil.getInstance().writeLog(
								AuditUtil.FILE_TYPE_G4G,
								new StringBuffer(this.getClass().getName())
										.append(G4GConstants.COLON_WITH_SPACES).append(
												DataUtil.getCurrentMethod())
										.append(G4GConstants.DASHES).append(
												e.getMessage()).append(
												DataUtil.getUserDTO(request)
														.getPlayerDTO()
														.getScreenname())
										.append(request.getSession().getId())
										.toString(), Level.ERROR);
						msg = new ActionMessage(e.getMessage());
						error.clear();
						error.add(G4GConstants.ERROR, msg);
						this.addErrors(request, error);
						return mapping.getInputForward();

					}
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
					error.add(G4GConstants.ERROR, msg);
					this.addErrors(request, error);
					return mapping.getInputForward();
				}
			} else {
				// open challenge
				directedChallengeForm.setOppPlayerDTO(null);
			}
			/* In case twoplayermatchid is present */
		} else {
			directedChallengeForm.setTwoplayermatchid(twoplayermatchid);
			searchCriteria.removeAllAttribute();
			searchCriteria.setAttribute(G4GConstants.MATCHID_DB, twoplayermatchid);
			directedChallengeForm.setCommentList(MatchCommentsServiceDelegator.getList(searchCriteria));
			TwoplayermatchDTO twoplayermatchDTO = new TwoplayermatchDTO();
			twoplayermatchDTO.setId(Integer.parseInt(twoplayermatchid));
			twoplayermatchDTO = TwoPlayerMatchServiceDelegator
					.get(twoplayermatchDTO);
			directedChallengeForm.setTwoplayermatchDTO(twoplayermatchDTO);
			TwoplayertournamentDTO twoplayertournamentDTO = twoplayermatchDTO
					.getTwoplayertournament();
			game = String.valueOf(TwoPlayerTournamentServiceDelegator.get(twoplayertournamentDTO).getGameDTO().getId());
			if (twoplayermatchDTO.getPlayertwoid() != null) {
				directedChallengeForm.setOppPlayerDTO(twoplayermatchDTO.getPlayertwoid());
				try {
					directedChallengeForm.setOppUser(G4GFinancialDelegator.getUserInfo(directedChallengeForm.getOppPlayerDTO().getEmailaddress()));
				} catch (InternalException_Exception e) {
					AuditUtil.getInstance().writeLog(
							AuditUtil.FILE_TYPE_G4G_FINANCIAL,
							new StringBuffer(this.getClass().getName()).append(DataUtil.getCurrentMethod()).append(
									G4GConstants.EXCE_IMPASSA).append(e.getMessage()).append(request.getSession().getId()).toString(),
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
					error.add(G4GConstants.ERROR, msg);
					this.addErrors(request, error);
					return mapping.getInputForward();
				} catch (UserNotFoundException_Exception e) {
					AuditUtil.getInstance().writeLog(
							AuditUtil.FILE_TYPE_G4G_FINANCIAL,
							new StringBuffer(this.getClass().getName()).append(
									DataUtil.getCurrentMethod()).append(
									G4GConstants.NOT_REG_IMPASSA).append(
									e.getMessage()).append(
									request.getSession().getId()).toString(),
							Level.ERROR);
					AuditUtil.getInstance().writeLog(
							AuditUtil.FILE_TYPE_G4G,
							new StringBuffer(this.getClass().getName()).append(
									DataUtil.getCurrentMethod()).append(
									G4GConstants.NOT_REG_IMPASSA).append(
									e.getMessage()).append(
									request.getSession().getId()).toString(),
							Level.ERROR);
					msg = new ActionMessage(e.getMessage());
					error.clear();
					error.add(G4GConstants.ERROR, msg);
					this.addErrors(request, error);
					return mapping.getInputForward();
				}
			}
			searchCriteria.removeAllAttribute();
			// twoplayermatchid thats String in match comments
			// database
			searchCriteria.setAttribute(G4GConstants.MATCHID_DB, twoplayermatchid);
			AuditUtil.getInstance().writeLog(
					AuditUtil.FILE_TYPE_G4G,
					new StringBuffer(this.getClass().getName()).append(
							DataUtil.getCallingMethod()).append(
							G4GConstants.SETTING_COMMENTS).append(DB).append(
							request.getSession().getId()).toString(),
					Level.INFO);
			directedChallengeForm.setCommentList(MatchCommentsServiceDelegator
					.getList(searchCriteria));
		}

		// get the challenging player from session
		directedChallengeForm.setPlayerDTO(DataUtil.getUserDTO(request)
				.getPlayerDTO());
		try {
			directedChallengeForm.setChallengerUser(G4GFinancialDelegator
					.getUserInfo(directedChallengeForm.getPlayerDTO()
							.getEmailaddress()));
		} catch (InternalException_Exception e) {
			AuditUtil.getInstance().writeLog(
					AuditUtil.FILE_TYPE_G4G_FINANCIAL,
					new StringBuffer(this.getClass().getName()).append(
							DataUtil.getCurrentMethod()).append(
							G4GConstants.EXCE_IMPASSA).append(e.getMessage())
							.append(request.getSession().getId()).toString(),
					Level.ERROR);
			AuditUtil.getInstance().writeLog(
					AuditUtil.FILE_TYPE_G4G,
					new StringBuffer(this.getClass().getName()).append(
							DataUtil.getCurrentMethod()).append(
							G4GConstants.EXCE_IMPASSA).append(e.getMessage())
							.append(request.getSession().getId()).toString(),
					Level.ERROR);
			msg = new ActionMessage(e.getMessage());
			error.clear();
			error.add(G4GConstants.ERROR, msg);
			this.addErrors(request, error);
			return mapping.getInputForward();
		} catch (UserNotFoundException_Exception e) {
			AuditUtil.getInstance().writeLog(
					AuditUtil.FILE_TYPE_G4G_FINANCIAL,
					new StringBuffer(this.getClass().getName()).append(
							DataUtil.getCurrentMethod()).append(
							G4GConstants.NOT_REG_IMPASSA)
							.append(e.getMessage()).append(
									request.getSession().getId()).toString(),
					Level.ERROR);
			AuditUtil.getInstance().writeLog(
					AuditUtil.FILE_TYPE_G4G,
					new StringBuffer(this.getClass().getName()).append(
							DataUtil.getCurrentMethod()).append(
							G4GConstants.NOT_REG_IMPASSA)
							.append(e.getMessage()).append(
									request.getSession().getId()).toString(),
					Level.ERROR);
			msg = new ActionMessage(e.getMessage());
			error.clear();
			error.add(G4GConstants.ERROR, msg);
			this.addErrors(request, error);
			return mapping.getInputForward();
		}
		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,
				new StringBuffer(this.getClass().getName()).append(
						DataUtil.getCallingMethod()).append(
						G4GConstants.GET_GAME_INFO).append(
						request.getSession().getId()).toString(), Level.INFO);
		directedChallengeForm.setGameDTO(GameServiceDelegator.getGame(DataUtil
				.getInteger(game)));
		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,
				new StringBuffer(this.getClass().getName()).append(
						DataUtil.getCallingMethod()).append(
						G4GConstants.GOT_GAME).append(
						directedChallengeForm.getGameDTO().getGamename())
						.append(request.getSession().getId()).toString(),
				Level.INFO);
		// this is game options
		GameOptionServiceDelegator.setgameOptionListInRequest(request,
				directedChallengeForm.getGameDTO().getId());

		// setting other information
		try {
			directedChallengeForm.setGameConsole(NetworkServiceDelegator
					.getNetwork(directedChallengeForm.getGameDTO().getNetwork()
							.getId()));
		} catch (Exception networkUnavailableException) {
			directedChallengeForm.setGameConsole(G4GConstants.NOT_AVAILABLE);
			AuditUtil.getInstance().writeLog(
					AuditUtil.FILE_TYPE_G4G,
					new StringBuffer(this.getClass().getName()).append(
							DataUtil.getCallingMethod()).append(
							G4GConstants.NO_NETWORK_AVAILABLE).append(
							directedChallengeForm.getGameDTO().getGamename())
							.append(request.getSession().getId()).toString(),
					Level.INFO);
		}
		return mapping.findForward(G4GConstants.SUCCESS);
	}

	/**
	 * Validate user request as per challenge rules.
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
	 * @return ActionForward
	 * @throws Exception
	 */
	@Override
	public ActionForward submit(ActionMapping mapping, BaseForm form,
			String operation, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,
				new StringBuffer(this.getClass().getName()).append(
						G4GConstants.SUBMIT_STARTS).append(
						request.getSession().getId()).toString(), Level.INFO);
		if (request.getSession().getAttribute(USERDTO) == null) {
			AuditUtil.getInstance().writeLog(
					AuditUtil.FILE_TYPE_G4G,
					new StringBuffer(this.getClass().getName()).append(
							CALLINGMETHOD).append(DataUtil.getCallingMethod())
							.append(SUBMIT_STARTS).append(DASHES).append(
									G4GConstants.NOT_RECOGNIZE_USER).append(
									request.getSession().getId()).toString());
			return mapping.findForward(HOMEPAGE);
		}

		DirectedChallengeForm directedChallengeForm = (DirectedChallengeForm) form;
		error.clear();
		if (ADD_COMMENTS.equals(operation)) {
			AuditUtil.getInstance().writeLog(
					AuditUtil.FILE_TYPE_G4G,
					new StringBuffer(this.getClass().getName()).append(BLANK)
							.append(ADD_COMMENTS).append(BLANK).append(
									CURRENTMETHOD).append(
									DataUtil.getCurrentMethod()).append(
									request.getSession().getId()).toString(),
					Level.INFO);
			directedChallengeForm.setOperation(null);
			if (!DataValidator.isNull(directedChallengeForm.getComments())) {
				TwoplayermatchDTO dto = new TwoplayermatchDTO();
				// logic for checking twoplayermatchId
				List<Twoplayermatchcomments> com = new ArrayList<Twoplayermatchcomments>();
				if (directedChallengeForm.getTwoplayermatchid() != null) {
					dto.setId(DataUtil.getInteger(directedChallengeForm
							.getTwoplayermatchid()));
					Twoplayermatchcomments comm = DirectedChallengeAction
							.setComments(directedChallengeForm.getComments(),
									G4GConstants.ZERO, dto, request);
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
													request.getSession()
															.getId())
											.append(
													G4GConstants.MATCHCOMMENT_SERVICE_DELEGATOR)
											.append(
													request.getSession()
															.getId())
											.toString(), Level.INFO);
					MatchCommentsServiceDelegator.add(comm);
					com.add(comm);
					directedChallengeForm.getCommentList().add(comm);
				} else {
					dto.setId(G4GConstants.ONE_NEGATIVE);
					if (directedChallengeForm.getCommentList() == null) {
						if (directedChallengeForm.getTwoplayermatchDTO() != null) {
							if (directedChallengeForm.getTwoplayermatchDTO()
									.getId() != G4GConstants.ZERO) {
								dto.setId(directedChallengeForm
										.getTwoplayermatchDTO().getId());
							}
						}
						com.add(DirectedChallengeAction.setComments(
								directedChallengeForm.getComments(),
								G4GConstants.ZERO, dto, request));
						directedChallengeForm.setCommentList(com);
					} else {
						directedChallengeForm.getCommentList().add(
								DirectedChallengeAction.setComments(
										directedChallengeForm.getComments(),
										directedChallengeForm.getCommentList()
												.size(), dto, request));
					}
				}
				directedChallengeForm.setComments(null);
			}
			setDisplay(mapping, directedChallengeForm, operation, request,
					response);
			return mapping.getInputForward();
		}

		String dollar = directedChallengeForm.getAmountField();
		dollar = dollar.replaceAll(G4GConstants.$, G4GConstants.NONE);
		dollar = dollar.replace(G4GConstants.$, G4GConstants.NONE);
		directedChallengeForm.setAmountField(dollar);
		try {
			if(!DataUtil.getUserDTO(request).isStateIllegal()
							&&
			(directedChallengeForm.getOppUser()==null
							|| SubNationalCodeServiceDelegator.get(DataUtil.getInteger(directedChallengeForm.getOppUser().getState())).getIslegal().trim().equalsIgnoreCase(G4GConstants.TRUE)))
			if (DataValidator.isDollarZero(dollar)) {
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
										.append(request.getSession().getId())
										.append(
												G4GConstants.NOT_ENTER_ZERO_AMOUNT_CHALLENGE)
										.append(request.getSession().getId())
										.toString(), Level.INFO);
				msg = new ActionMessage(G4GConstants.ERRORS_ZEROBALANCE);
				error.clear();
				error.add(G4GConstants.AMOUNT_FIELD, msg);
				setDisplay(mapping, directedChallengeForm, operation, request,
						response);
				return mapping.getInputForward();
			}
		} catch (Exception e) {
			AuditUtil.getInstance().writeLog(
					AuditUtil.FILE_TYPE_G4G,
					new StringBuffer(this.getClass().getName()).append(
							CURRENTMETHOD).append(DataUtil.getCurrentMethod())
							.append(CALLINGMETHOD).append(
									DataUtil.getCallingMethod()).append(DASHES)
							.append(G4GConstants.WRONG_NUMBER).append(
									request.getSession().getId()).toString(),
					Level.INFO);
			msg = new ActionMessage(G4GConstants.ERRORS_NUMBERFROMAT);
			error.clear();
			error.add(G4GConstants.AMOUNT_FIELD, msg);
			setDisplay(mapping, directedChallengeForm, operation, request,
					response);
			return mapping.getInputForward();
		}

		try {
			dollar = dollar.trim();
			if (Double.parseDouble(dollar) > DataUtil.getUserDTO(request).getBalance()) {
				AuditUtil.getInstance().writeLog(
						AuditUtil.FILE_TYPE_G4G,
						new StringBuffer(this.getClass().getName()).append(
								CURRENTMETHOD).append(
								DataUtil.getCurrentMethod()).append(
								CALLINGMETHOD).append(
								DataUtil.getCallingMethod()).append(DASHES)
								.append(request.getSession().getId()).append(
										G4GConstants.NOT_SUFFICIENT_BALANCE)
								.append(request.getSession().getId())
								.toString(), Level.INFO);
				msg = new ActionMessage(G4GConstants.ERRORS_LESSBALANCE);
				request.setAttribute(G4GConstants.ERRORS_LESSBALANCE,
						G4GConstants.ERRORS_LESSBALANCE);
				error.clear();
				error.add(G4GConstants.AMOUNT_FIELD, msg);
				setDisplay(mapping, directedChallengeForm, operation, request,
						response);
				return mapping.getInputForward();
			}
		} catch (Exception wrongFormat) {
			msg = new ActionMessage(G4GConstants.ERRORS_NUMBERFROMAT);
			error.clear();
			error.add(G4GConstants.AMOUNT_FIELD, msg);
			setDisplay(mapping, directedChallengeForm, operation, request,
					response);
			return mapping.getInputForward();
		}

		// check whether the player2 or 1 is already scheduled
		SimpleDateFormat format = new SimpleDateFormat(G4GConstants.DATE_DD_MM_YYYY_AT_HH_MM_A);
		try {
			directedChallengeForm.setSchduledDate(format.parse(directedChallengeForm.getTournamentDate()));
			String mssg = G4GConstants.ERRORS_PLAYERONEBUSY;
			directedChallengeForm.setSchduledDate(DateUtil.getDateOfTimeZone(directedChallengeForm.getSchduledDate(), -DataUtil.getUserDTO(request).getOffset()));
			if (directedChallengeForm.getSchduledDate().before(DataUtil.todayGMT())) {
				AuditUtil.getInstance().writeLog(AuditUtil.FILE_TYPE_G4G,new StringBuffer(this.getClass().getName()).append(
								CURRENTMETHOD).append(DataUtil.getCurrentMethod()).append(CALLINGMETHOD).append(DataUtil.getCallingMethod()).append(DASHES).append(request.getSession().getId()).append(
										G4GConstants.USER_GONE_AWAY).append(request.getSession().getId()).toString(), Level.INFO);
				msg = new ActionMessage(G4GConstants.ERROR_OLDDATE);
				error.clear();
				error.add(G4GConstants.TOURNAMENT_DATE, msg);
				setDisplay(mapping, directedChallengeForm, operation, request,response);
				return mapping.getInputForward();
			}
			List<TwoplayermatchDTO> templisterror = TwoPlayerMatchServiceDelegator.checkPlayerChallengeAvailability(directedChallengeForm,
							null);
			if (templisterror.size() != G4GConstants.ZERO) {
				msg = new ActionMessage(mssg);
				error.clear();
				error.add(G4GConstants.TOURNAMENT_DATE, msg);
				setDisplay(mapping, directedChallengeForm, operation, request,
						response);
				templisterror = null;
				return mapping.getInputForward();
			}

		} catch (Exception e) {
			msg = new ActionMessage(G4GConstants.ERRORS_CHALLENGE_DATE);
			error.clear();
			error.add(G4GConstants.TOURNAMENT_DATE, msg);
			if (directedChallengeForm.getOppPlayerDTO() != null) {
				this.addErrors(request, error);
			}
			setDisplay(mapping, directedChallengeForm, operation, request,
					response);
			return mapping.getInputForward();
		}

		try {

			if (directedChallengeForm.getGameOptionSelected() != null) {
				// ip is valuesequenceid of gameoption;
				AuditUtil.getInstance().writeLog(
						AuditUtil.FILE_TYPE_G4G,
						new StringBuffer(this.getClass().getName()).append(
								CURRENTMETHOD).append(
								DataUtil.getCurrentMethod()).append(
								CALLINGMETHOD).append(
								DataUtil.getCallingMethod()).append(DASHES)
								.append(request.getSession().getId()).append(
										G4GConstants.GET_GAME_OPPTION_USER)
								.append(request.getSession().getId())
								.toString(), Level.INFO);
				String[] ip = directedChallengeForm.getGameOptionSelected();
				int temp = ip.length;
				List<GameoptionsDTO> list;
				List<GameoptionsDTO> listtemp = new ArrayList<GameoptionsDTO>();
				for (int i = G4GConstants.ZERO; i < temp; i++) {

					searchCriteria.removeAllAttribute();
					searchCriteria.setAttribute("valueid",DataUtil.getInteger(ip[i]));
					searchCriteria.setAttribute(G4GConstants.OPTION,directedChallengeForm.getGameSetting()[i]);
					searchCriteria.setAttribute(G4GConstants.GAMEID_DB,
							directedChallengeForm.getGameDTO().getId());
					list = GameOptionServiceDelegator.getList(searchCriteria);
					if(list==null || list.size()<1) {
						searchCriteria.removeAttribute(G4GConstants.VALUE_SEQUENCE_ID);
						searchCriteria.setAttribute(G4GConstants.OPTIONSEQUENCEID,DataUtil.getInteger(ip[i]));
						list = GameOptionServiceDelegator.getList(searchCriteria);
					}
					searchCriteria.removeAllAttribute();
					if (list.size() != G4GConstants.ZERO) {
						Iterator<GameoptionsDTO> it = list.iterator();
						GameoptionsDTO dto = it.next();
						listtemp.add(dto);
					}
				}
				directedChallengeForm.setGameoptionDTO(listtemp);
			}
		} catch (Exception noGameOPtions) {
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
									.append(request.getSession().getId())
									.append(
											G4GConstants.PLAYER_NOTSELECTED_GAME_OPPTION)
									.append(request.getSession().getId())
									.toString(), Level.INFO);
		}
		String idtemp = ChallengeCardUpdateUtil.checkIdTemp(request);
		if (!G4GConstants.STRING_NULL.equals(idtemp) && !idtemp.equals(null)) {
			TwoplayermatchDTO twoplayermatchDTO = new TwoplayermatchDTO();
			twoplayermatchDTO.setId(Integer.parseInt(idtemp));
			try {
				AuditUtil.getInstance().writeLog(
						AuditUtil.FILE_TYPE_G4G,
						new StringBuffer(this.getClass().getName()).append(
								CURRENTMETHOD).append(
								DataUtil.getCurrentMethod()).append(
								CALLINGMETHOD).append(
								DataUtil.getCallingMethod()).append(DASHES)
								.append(request.getSession().getId()).append(
										G4GConstants.UPDATING_PLAYER_MATCH)
								.append(request.getSession().getId())
								.toString(), Level.INFO);
				ChallengeCardUpdateUtil.updateTwoPLayerMatch(twoplayermatchDTO,directedChallengeForm, request);

			} catch (InternalException_Exception e) {
				AuditUtil.getInstance().writeLog(
						AuditUtil.FILE_TYPE_G4G,
						new StringBuffer(this.getClass().getName()).append(
								CURRENTMETHOD).append(
								DataUtil.getCurrentMethod()).append(
								CALLINGMETHOD).append(
								DataUtil.getCallingMethod()).append(DASHES)
								.append(request.getSession().getId()).append(
										G4GConstants.EXCE_IMPASSA).append(
										e.getMessage()).append(
										request.getSession().getId())
								.toString(), Level.INFO);
				AuditUtil.getInstance().writeLog(
						AuditUtil.FILE_TYPE_G4G_FINANCIAL,
						new StringBuffer(this.getClass().getName()).append(
								CURRENTMETHOD).append(
								DataUtil.getCurrentMethod()).append(
								CALLINGMETHOD).append(
								DataUtil.getCallingMethod()).append(DASHES)
								.append(request.getSession().getId()).append(
										G4GConstants.EXCE_IMPASSA).append(
										e.getMessage()).append(
										request.getSession().getId())
								.toString(), Level.ERROR);
				msg = new ActionMessage(e.getMessage());
				error.clear();
				error.add(G4GConstants.ERROR, msg);
				this.addErrors(request, error);
				setDisplay(mapping, directedChallengeForm, operation, request,
						response);
				return mapping.getInputForward();

			} catch (InvalidSessionException_Exception e) {
				AuditUtil.getInstance().writeLog(
						AuditUtil.FILE_TYPE_G4G,
						new StringBuffer(this.getClass().getName()).append(
								CURRENTMETHOD).append(
								DataUtil.getCurrentMethod()).append(
								CALLINGMETHOD).append(
								DataUtil.getCallingMethod()).append(DASHES)
								.append(G4GConstants.SESSION_EXCE_IMPASSA)
								.append(e.getMessage()).append(
										request.getSession().getId())
								.toString(), Level.INFO);
				AuditUtil.getInstance().writeLog(
						AuditUtil.FILE_TYPE_G4G_FINANCIAL,
						new StringBuffer(this.getClass().getName()).append(
								CURRENTMETHOD).append(
								DataUtil.getCurrentMethod()).append(
								CALLINGMETHOD).append(
								DataUtil.getCallingMethod()).append(DASHES)
								.append(G4GConstants.SESSION_EXCE_IMPASSA)
								.append(e.getMessage()).append(
										request.getSession().getId())
								.toString(), Level.ERROR);
				msg = new ActionMessage(e.getMessage());
				error.clear();
				error.add(G4GConstants.ERROR, msg);
				this.addErrors(request, error);
				setDisplay(mapping, directedChallengeForm, operation, request,
						response);
				return mapping.getInputForward();
			} catch (InvalidBetException_Exception e) {
				AuditUtil.getInstance().writeLog(
						AuditUtil.FILE_TYPE_G4G,
						new StringBuffer(this.getClass().getName()).append(
								CURRENTMETHOD).append(
								DataUtil.getCurrentMethod()).append(
								CALLINGMETHOD).append(
								DataUtil.getCallingMethod()).append(DASHES)
								.append(request.getSession().getId()).append(
										G4GConstants.BAT_EXCE_IMPASSA).append(
										e.getMessage()).append(
										request.getSession().getId())
								.toString(), Level.INFO);
				AuditUtil.getInstance().writeLog(
						AuditUtil.FILE_TYPE_G4G_FINANCIAL,
						new StringBuffer(this.getClass().getName()).append(
								CURRENTMETHOD).append(
								DataUtil.getCurrentMethod()).append(
								CALLINGMETHOD).append(
								DataUtil.getCallingMethod()).append(DASHES)
								.append(request.getSession().getId()).append(
										G4GConstants.BAT_EXCE_IMPASSA).append(
										e.getMessage()).append(
										request.getSession().getId())
								.toString(), Level.ERROR);
				msg = new ActionMessage(e.getMessage());
				error.clear();
				error.add(G4GConstants.ERROR, msg);
				this.addErrors(request, error);
				setDisplay(mapping, directedChallengeForm, operation, request,
						response);
				return mapping.getInputForward();
			} catch (MoneyException_Exception e) {
				AuditUtil.getInstance().writeLog(
						AuditUtil.FILE_TYPE_G4G,
						new StringBuffer(this.getClass().getName()).append(
								CURRENTMETHOD).append(
								DataUtil.getCurrentMethod()).append(
								CALLINGMETHOD).append(
								DataUtil.getCallingMethod()).append(DASHES)
								.append(request.getSession().getId()).append(
										G4GConstants.MONEY_EXCE_IMPASSA)
								.append(e.getMessage()).append(
										request.getSession().getId())
								.toString(), Level.INFO);
				AuditUtil.getInstance().writeLog(
						AuditUtil.FILE_TYPE_G4G_FINANCIAL,
						new StringBuffer(this.getClass().getName()).append(
								CURRENTMETHOD).append(
								DataUtil.getCurrentMethod()).append(
								CALLINGMETHOD).append(
								DataUtil.getCallingMethod()).append(DASHES)
								.append(request.getSession().getId()).append(
										G4GConstants.MONEY_EXCE_IMPASSA)
								.append(e.getMessage()).append(
										request.getSession().getId())
								.toString(), Level.ERROR);
				msg = new ActionMessage(e.getMessage());
				error.clear();
				error.add(G4GConstants.ERROR, msg);
				this.addErrors(request, error);
				setDisplay(mapping, directedChallengeForm, operation, request,
						response);
				return mapping.getInputForward();
			} catch (UserNotFoundException_Exception e) {
				AuditUtil.getInstance().writeLog(
						AuditUtil.FILE_TYPE_G4G,
						new StringBuffer(this.getClass().getName()).append(
								CURRENTMETHOD).append(
								DataUtil.getCurrentMethod()).append(
								CALLINGMETHOD).append(
								DataUtil.getCallingMethod()).append(DASHES)
								.append(request.getSession().getId()).append(
										G4GConstants.NOT_REG_IMPASSA).append(
										e.getMessage()).append(
										request.getSession().getId())
								.toString(), Level.INFO);
				AuditUtil.getInstance().writeLog(
						AuditUtil.FILE_TYPE_G4G_FINANCIAL,
						new StringBuffer(this.getClass().getName()).append(
								CURRENTMETHOD).append(
								DataUtil.getCurrentMethod()).append(
								CALLINGMETHOD).append(
								DataUtil.getCallingMethod()).append(DASHES)
								.append(request.getSession().getId()).append(
										G4GConstants.NOT_REG_IMPASSA).append(
										e.getMessage()).append(
										request.getSession().getId())
								.toString(), Level.ERROR);
				msg = new ActionMessage(e.getMessage());
				error.clear();
				error.add(G4GConstants.ERROR, msg);
				this.addErrors(request, error);
				setDisplay(mapping, directedChallengeForm, operation, request,
						response);
				return mapping.getInputForward();
			}
		}

		request.getSession().removeAttribute(
				G4GConstants.DIRECTED_CHALLENGE_FORM);

		request.getSession().setAttribute(G4GConstants.DIRECTED_CHALLENGE_FORM,
				directedChallengeForm);

		return mapping.findForward(G4GConstants.SUCCESS);
	}

	/**
	 * Method to set comment dto.
	 *
	 * @param comment
	 *            the comment
	 * @param seq
	 *            the seq
	 * @param dto
	 *            the dto
	 * @param request
	 *            the request
	 *
	 * @return Twoplayermatchcomments
	 *
	 * @author ankur
	 */
	public static Twoplayermatchcomments setComments(String comment, int seq,
			TwoplayermatchDTO dto, HttpServletRequest request) {
		Twoplayermatchcomments comments = new Twoplayermatchcomments();
		comments.setComment(comment);
		comments.setCommentseq(seq);
		comments.setCreationdate(DataUtil.todayGMT());
		if (dto.getId() > G4GConstants.ONE_NEGATIVE) {
			comments.setMatchid(String.valueOf(dto.getId()));
		}
		comments.setPlayerid(DataUtil.getUserDTO(request).getPlayerDTO()
				.getId());
		return comments;
	}

	/**
	 * Method to set request object for directChallenge 1 page.
	 *
	 * @param mapping
	 *            the mapping
	 * @param directedChallengeForm
	 *            the directed challenge form
	 * @param operation
	 *            the operation
	 * @param request
	 *            the request
	 * @param response
	 *            the response
	 *
	 * @author ankur
	 */
	public void setDisplay(ActionMapping mapping,
			DirectedChallengeForm directedChallengeForm, String operation,
			HttpServletRequest request, HttpServletResponse response) {
		if (directedChallengeForm.getOppPlayerDTO() != null) {
			request.setAttribute(G4GConstants.PLAYERID, String
					.valueOf(directedChallengeForm.getOppPlayerDTO().getId()));
		}
		request.setAttribute(G4GConstants.GAMEID, String
				.valueOf(directedChallengeForm.getGameDTO().getId()));
		this.addErrors(request, error);
		display(mapping, directedChallengeForm, operation, request, response);
	}

}
