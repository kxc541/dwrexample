/**********************************************************
 * HibernateSessionFactory.java 
 *
 * Created : 16 apr. 08 by author
 * Last modified $ Date: $ by $ Author:  $
 * Revision: $ Revision:  $
 * Version : $ ID : 1$
 * Copyright (c) 2008 - 2008 Askdigi, All rights reserved.
 **********************************************************/
package com.g4g.utils;

import static com.g4g.utils.G4GConstants.BETID_SENDING;
import static com.g4g.utils.G4GConstants.CANCEL_CHALLENGE_VALUE;
import static com.g4g.utils.G4GConstants.COLON_WITH_SPACES;
import static com.g4g.utils.G4GConstants.CALLINGMETHOD;
import static com.g4g.utils.G4GConstants.CHALLENGING_CARDPAGE;
import static com.g4g.utils.G4GConstants.CURRENTMETHOD;
import static com.g4g.utils.G4GConstants.DASHES;
import static com.g4g.utils.G4GConstants.DIRECTED_CHALLENGE_FORM;
import static com.g4g.utils.G4GConstants.DISSOLVE;
import static com.g4g.utils.G4GConstants.ENTRY_FEE;
import static com.g4g.utils.G4GConstants.GAME_STAUS_CANCELLED;
import static com.g4g.utils.G4GConstants.GAME_STAUS_CANCELLED_USER;
import static com.g4g.utils.G4GConstants.REJECT;
import static com.g4g.utils.G4GConstants.REJECT_CHALLENGE_VALUE;
import static com.g4g.utils.G4GConstants.TEN;
import static com.g4g.utils.G4GConstants.TOURNAMENT_ID;
import static com.g4g.utils.G4GConstants.TWENTYFIVE_NUMBER;
import static com.g4g.utils.G4GConstants.TWO_NUMBER;
import static com.g4g.utils.G4GConstants.TWO_PLAYER_MATCH_ID;
import static com.g4g.utils.G4GConstants.USER_SESSION_ID;
import static com.g4g.utils.G4GConstants.ZERO;
import static com.g4g.utils.G4GConstants.ZERO_POINTONE;

import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Level;

import com.g4g.delegator.G4GFinancialDelegator;
import com.g4g.delegator.NotificationServiceDelegator;
import com.g4g.delegator.TwoPlayerMatchServiceDelegator;
import com.g4g.delegator.TwoPlayerTournamentGameOptionServiceDelegator;
import com.g4g.delegator.TwoPlayerTournamentServiceDelegator;
import com.g4g.dto.GameoptionsDTO;
import com.g4g.dto.TwoplayermatchDTO;
import com.g4g.dto.TwoplayertournamentDTO;
import com.g4g.dto.TwoplayertournamentgameoptionsDTO;
import com.g4g.forms.DirectedChallengeForm;
import com.g4g.notification.MatchConfirmNotification;
import com.impessa.worldgaming.client.InternalException_Exception;
import com.impessa.worldgaming.client.InvalidBetException_Exception;
import com.impessa.worldgaming.client.InvalidSessionException_Exception;
import com.impessa.worldgaming.client.MoneyException_Exception;
import com.impessa.worldgaming.client.PlayerTransactionType;
import com.impessa.worldgaming.client.UserNotFoundException_Exception;

/**
 * The Class ChallengeCardUpdateUtil contains Methods for challenge card betting
 * money, can edit the old challenge details, rejection of challengecard,
 * removes attribures from session.
 * 
 * @author Ankur
 */
public class ChallengeCardUpdateUtil {

	/**
	 * Used when user edits his old challenge card Updates the details he wants
	 * to change.
	 * 
	 * @param twoplayermatchDTO
	 *            Changes payoutamount, playertwoaccepteddate,
	 *            scheduledStartdate .
	 * @param directedChallengeForm
	 *            directedchallengeform fills the details from
	 *            twoplayermatchDTO.
	 * @param request
	 *            the request object to deduct amount from twoplayertournament.
	 * @throws Exception
	 */

	public static void updateTwoPLayerMatch(
			TwoplayermatchDTO twoplayermatchDTO,
			DirectedChallengeForm directedChallengeForm,
			HttpServletRequest request) throws Exception {

		twoplayermatchDTO = TwoPlayerMatchServiceDelegator.get(twoplayermatchDTO);
		TwoplayertournamentDTO twoplayertournamentDTO = twoplayermatchDTO.getTwoplayertournament();

		// recover balance from house and bet a/c for player one and player 2
		// and deduct player 1 money from his a/c.

		double amount = Double.parseDouble(directedChallengeForm.getAmountField());
		twoplayertournamentDTO.setEntryfee(amount);
		double houseFee = twoplayertournamentDTO.getEntryfee() * TWO_NUMBER* ZERO_POINTONE;
		twoplayertournamentDTO.setHousefeeperplayer(houseFee);
		twoplayertournamentDTO.setCreationdate(DataUtil.todayGMT());
		TwoPlayerTournamentServiceDelegator.update(twoplayertournamentDTO);
		twoplayermatchDTO.setPayoutamount(TWO_NUMBER* (twoplayertournamentDTO.getEntryfee()- twoplayertournamentDTO.getHousefeeperplayer()));
		twoplayermatchDTO.setScheduledstartdate(directedChallengeForm.getSchduledDate());
		twoplayermatchDTO.setPlayertwoaccepteddate(null);
		TwoPlayerMatchServiceDelegator.update(twoplayermatchDTO);
		TwoplayertournamentgameoptionsDTO tptgameoptionDTO = new TwoplayertournamentgameoptionsDTO();
		tptgameoptionDTO.setTournamentid(twoplayertournamentDTO);
		TwoPlayerTournamentGameOptionServiceDelegator
				.deleteAll(twoplayertournamentDTO.getId());
		List<GameoptionsDTO> list = directedChallengeForm.getGameoptionDTO();
		Iterator<GameoptionsDTO> it = list.iterator();
		try {
			for (int i = ZERO; i < directedChallengeForm.getGameSetting().length; i++) {
				if (it.hasNext()) {
					GameoptionsDTO gameoptionsDTO = it.next();
					tptgameoptionDTO.setOptionid(gameoptionsDTO.getId());
					tptgameoptionDTO.setValueid(gameoptionsDTO.getValueid());
					TwoPlayerTournamentGameOptionServiceDelegator
							.add(tptgameoptionDTO);
				}
			}
		} catch (NullPointerException noGameOptionsForThisGamException) {
			AuditUtil.getInstance().writeLog(
					AuditUtil.FILE_TYPE_G4G,
					CHALLENGING_CARDPAGE
							+ noGameOptionsForThisGamException.getMessage(),
					Level.ERROR);

		}

		// populate form fields
		directedChallengeForm.setPlayerDTO(twoplayermatchDTO.getPlayeroneid());

		ChallengeCardUpdateUtil.challengeAcceptTimeAmountDeduction(request,
				twoplayertournamentDTO);

	}

	// idtemp is twoplayer matchId
	/**
	 * The method checkIdTemp checks twoplayermatchId presence in the
	 * twoplayermatch table.
	 * 
	 * @param request
	 *            the request object to get attributes from the session.
	 * 
	 * @return twoplayermatchid
	 */
	public static String checkIdTemp(HttpServletRequest request) {
		String idtemp = request.getParameter(TWO_PLAYER_MATCH_ID) != null ? request
				.getParameter(TWO_PLAYER_MATCH_ID)
				: (String) request.getAttribute(TWO_PLAYER_MATCH_ID);
		if (idtemp == null) {
			try {
				idtemp = String.valueOf(request.getSession().getAttribute(
						TWO_PLAYER_MATCH_ID));
			} catch (ClassCastException e) {
				AuditUtil.getInstance().writeLog(AuditUtil.FILE_TYPE_HIBERNATE,
						new StringBuffer (COLON_WITH_SPACES
								).append( CALLINGMETHOD
								).append( DataUtil.getCallingMethod()
								).append( CURRENTMETHOD
								).append( DataUtil.getCurrentMethod() ).append( DASHES
								).append( e.getMessage()).toString(), Level.ERROR);
				
				idtemp = (String) request.getSession().getAttribute(
						TWO_PLAYER_MATCH_ID);
			}
		}
		if (request.getParameter(TOURNAMENT_ID) != null) {
			int tounamentId = Integer.parseInt(request
					.getParameter(TOURNAMENT_ID));
			request.getSession().setAttribute(TOURNAMENT_ID, tounamentId);
			idtemp = String.valueOf(TwoPlayerMatchServiceDelegator
					.getDtoForChallenge(tounamentId, request).getId());
		}
		return idtemp;
	}

	/**
	 * The method clearSession removes attributes directedChallengeForm,10,
	 * TournamentId set to session.
	 * 
	 * @param request
	 *            the request to get the session.
	 */
	public static void clearSession(HttpServletRequest request) {
		request.getSession().removeAttribute(DIRECTED_CHALLENGE_FORM);
		request.getSession().removeAttribute(TEN);
		request.getSession().removeAttribute(TOURNAMENT_ID);
	}

	/**
	 * Takes out money from players account at time when he is accepting or
	 * issuing a challenge.
	 * 
	 * @param request
	 *            the request object to deduct money from player's accept.
	 * @param twoplayertournamentDTO
	 *            the twoplayertournamentdto to get the betid from.
	 * 
	 * @throws MoneyException_Exception
	 * @throws InvalidBetException_Exception
	 * @throws InvalidSessionException_Exception
	 * @throws InternalException_Exception
	 * @throws UserNotFoundException_Exception
	 * @author Ankur
	 */
	public static void challengeSendTimeAmountDeduction(
			HttpServletRequest request,
			TwoplayertournamentDTO twoplayertournamentDTO)
			throws InternalException_Exception,
			InvalidSessionException_Exception, InvalidBetException_Exception,
			MoneyException_Exception, UserNotFoundException_Exception {
		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,
				new StringBuffer(twoplayertournamentDTO.getBetid()).append(
						BETID_SENDING).append(request.getSession().getId())
						.toString(), Level.INFO);

		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,
				new StringBuffer(DataUtil.getSessionId(request)).append(
						USER_SESSION_ID).toString(), Level.INFO);

		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,
				DataUtil.getMoney(twoplayertournamentDTO.getEntryfee())
						+ ENTRY_FEE + request.getSession().getId(), Level.INFO);

		G4GFinancialDelegator.bet(DataUtil.getSessionId(request),
				twoplayertournamentDTO.getBetid(), DataUtil
						.getMoney(twoplayertournamentDTO.getEntryfee()));

		SessionUtil.updateUserbalance(request);

	}

	/**
	 * Deduce money when user accepts the challenge, Currently calls
	 * challengeSendTimeAmountDeduction method.
	 * 
	 * @param request
	 *            the request object to pass to the called method.
	 * @param twoplayertournamentDTO
	 *            the twoplayertournamentdto object passed to the
	 *            challengeSendTimeAmountDeduction.
	 * @throws MoneyException_Exception
	 * @throws InvalidBetException_Exception
	 * @throws InvalidSessionException_Exception
	 * @throws InternalException_Exception
	 * @throws UserNotFoundException_Exception
	 */
	public static void challengeAcceptTimeAmountDeduction(
			HttpServletRequest request,
			TwoplayertournamentDTO twoplayertournamentDTO)
			throws InternalException_Exception,
			InvalidSessionException_Exception, InvalidBetException_Exception,
			MoneyException_Exception, UserNotFoundException_Exception {
		ChallengeCardUpdateUtil.challengeSendTimeAmountDeduction(request,
				twoplayertournamentDTO);
	}

	/**
	 * The method balanceRecovery Gives balance back to user/users when
	 * challenge is dissolve or rejected.
	 * 
	 * @param request
	 *            the request object updates user balance.
	 * @param twoplayermatchDTO
	 *            the twoplayermatchdto the entryfee is refunded back to user.
	 * @param twoplayertournamentDTO
	 *            the twoplayertournament the entryfee is refunded back to user.
	 * 
	 * @throws UserNotFoundException_Exception
	 * @throws MoneyException_Exception
	 * @throws InvalidSessionException_Exception
	 * @throws InvalidBetException_Exception
	 * @throws InternalException_Exception
	 * @author Ankur
	 */
	public static void balanceRecovery(HttpServletRequest request,
			TwoplayermatchDTO twoplayermatchDTO,
			TwoplayertournamentDTO twoplayertournamentDTO)
			throws InternalException_Exception, InvalidBetException_Exception,
			InvalidSessionException_Exception, MoneyException_Exception,
			UserNotFoundException_Exception {
		String useronename = twoplayermatchDTO.getPlayeroneid()
				.getEmailaddress();
		String usertwoname = null;

		if (twoplayermatchDTO.getPlayertwoaccepteddate() != null) {
			usertwoname = twoplayermatchDTO.getPlayertwoid().getEmailaddress();
		}
		// moving money to playerone account
		if(twoplayertournamentDTO.getEntryfee()>0) {
		G4GFinancialDelegator.moveToPlayerAccount(twoplayertournamentDTO.getBetid(), useronename, DataUtil.getMoney(twoplayertournamentDTO.getEntryfee()),PlayerTransactionType.PC);

		if (usertwoname != null) {
			G4GFinancialDelegator.moveToPlayerAccount(twoplayertournamentDTO.getBetid(), usertwoname, DataUtil.getMoney(twoplayertournamentDTO.getEntryfee()),PlayerTransactionType.PC);
		}
		}

		SessionUtil.updateUserbalance(request);

	}

	/**
	 * The method getHouseFee calculates housefee. HouseFee is either 20% of the
	 * entryfee or 25$.
	 * 
	 * @param entryfee
	 *            the entryfee to calculate housefee.
	 * 
	 * @return housefee
	 */
	public static Double getHouseFee(double entryfee) {
		double housefee = entryfee * ZERO_POINTONE;
		if (housefee > TWENTYFIVE_NUMBER) {
			housefee = TWENTYFIVE_NUMBER;
		}
		return housefee;

	}

	/**
	 * The method rejectChallenge is used for rejecting a challenge. It sets
	 * cancellationdate to twoplayermatch table telling that the challenge is
	 * rejected. Recovers the balance of the challenge to player account.
	 * MatchConfirm notification is send to players for cancelling the match.
	 * 
	 * @param twoplayermatchid
	 *            the id of the match rejected.
	 * @param request
	 *            the request to get balance recovery.
	 * @param operation
	 *            G4GConstant.REJECT or DISSOLVE
	 * @throws
	 */
	public static void rejectChallenge(int twoplayermatchid,
			HttpServletRequest request, String operation) throws Exception {
		TwoplayermatchDTO twoplayermatchDTO = new TwoplayermatchDTO();
		TwoplayertournamentDTO twoplayertournamentDTO = new TwoplayertournamentDTO();

		twoplayermatchDTO.setId(twoplayermatchid);
		twoplayermatchDTO = TwoPlayerMatchServiceDelegator
				.get(twoplayermatchDTO);
		twoplayertournamentDTO.setId(twoplayermatchDTO.getTwoplayertournament()
				.getId());
		twoplayertournamentDTO = TwoPlayerTournamentServiceDelegator
				.get(twoplayertournamentDTO);
		if(twoplayermatchDTO.getTwoplayertournament().getEntryfee()>G4GConstants.ZERO)
		ChallengeCardUpdateUtil.balanceRecovery(request, twoplayermatchDTO,
				twoplayertournamentDTO);

		twoplayertournamentDTO.setCancellationdate(DataUtil.todayGMT());
		twoplayermatchDTO.setCompleteddate(DataUtil.todayGMT());
		twoplayermatchDTO.setGamecompleted(GAME_STAUS_CANCELLED);

		TwoPlayerTournamentServiceDelegator.update(twoplayertournamentDTO);
		TwoPlayerMatchServiceDelegator.update(twoplayermatchDTO);
		MatchConfirmNotification confirmNotification = null;

		if (REJECT.equals(operation)) {
			confirmNotification = new MatchConfirmNotification(
					twoplayermatchDTO.getPlayeroneid().getId(),
					twoplayermatchDTO.getPlayertwoid().getId(),
					twoplayermatchDTO.getId(), REJECT_CHALLENGE_VALUE,
					twoplayermatchDTO.getScheduledstartdate());
		}
		if (DISSOLVE.equals(operation)) {
			confirmNotification = new MatchConfirmNotification(
					twoplayermatchDTO.getPlayertwoid().getId(),
					twoplayermatchDTO.getPlayeroneid().getId(),
					twoplayermatchDTO.getId(), CANCEL_CHALLENGE_VALUE,
					twoplayermatchDTO.getScheduledstartdate());
		}
		NotificationServiceDelegator.sendNotification(confirmNotification,
				request);
	}

	/**
	 * This method is used when any of the two players selected the option
	 * of cancelling the match
	 * This method return back money to both players
	 * and change status of twoplayermatchdto gamecompleted to 3"Game_Status_Canceled_User"
	 * and sends notification to both player about match cancellation
	 * Creator again receive a notification for converting challenge to open challenge
	 * 
	 * @throws UserNotFoundException_Exception 
	 * @throws MoneyException_Exception 
	 * @throws InvalidSessionException_Exception 
	 * @throws InvalidBetException_Exception 
	 * @throws InternalException_Exception 
	 */
	public static void cancelMatch(HttpServletRequest request , TwoplayermatchDTO dto) throws InternalException_Exception, InvalidBetException_Exception, InvalidSessionException_Exception, MoneyException_Exception, UserNotFoundException_Exception {
		// Return balance
		if(dto.getTwoplayertournament().getEntryfee()>G4GConstants.ZERO)
		balanceRecovery(request, dto, dto.getTwoplayertournament());
		
		//Updates user's current balance in session
		SessionUtil.updateUserbalance(request);
		
		//Update matchdto
		dto.setGamecompleted(GAME_STAUS_CANCELLED_USER);
		TwoPlayerMatchServiceDelegator.update(dto);
		
		//issue notification
		MatchConfirmNotification playerOneConfirmNotification = null;
		MatchConfirmNotification playerTwoConfirmNotification = null;

		playerOneConfirmNotification = new MatchConfirmNotification(dto.getPlayeroneid().getId(),dto.getPlayertwoid().getId(),
						dto.getId(), CANCEL_CHALLENGE_VALUE,dto.getScheduledstartdate());
		playerTwoConfirmNotification= new MatchConfirmNotification(dto.getPlayertwoid().getId(),dto.getPlayeroneid().getId(),
						dto.getId(), CANCEL_CHALLENGE_VALUE,dto.getScheduledstartdate());
		
		try {
			NotificationServiceDelegator.sendNotification(playerOneConfirmNotification, request);
			NotificationServiceDelegator.sendNotification(playerTwoConfirmNotification, request);
			playerOneConfirmNotification = new MatchConfirmNotification(dto.getPlayeroneid().getId(),dto.getPlayertwoid().getId(),
							dto.getId(), REJECT_CHALLENGE_VALUE,dto.getScheduledstartdate());
			NotificationServiceDelegator.sendNotification(playerOneConfirmNotification, request);
		} 
		catch (Exception exception) {
			
		}
	}


}
