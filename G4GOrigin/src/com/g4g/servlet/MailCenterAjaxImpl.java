/**********************************************************
 * MailCenterAjaxImpl.java :
 *
 * Created by Jigar Mistry
 * Last modified Date: 18 Apr .08 by Punam
 * Revision: 0.1
 * Version : 0.0.8
 * Copyright (c) 2008 - 2008 Askdigi, All rights reserved.
 **********************************************************/
package com.g4g.servlet;

import static com.g4g.utils.G4GConstants.ARCHIVED_COUNT;
import static com.g4g.utils.G4GConstants.BODY;
import static com.g4g.utils.G4GConstants.BRACATES;
import static com.g4g.utils.G4GConstants.CALLINGMETHOD;
import static com.g4g.utils.G4GConstants.COLON_WITH_SPACES;
import static com.g4g.utils.G4GConstants.CURRENTMETHOD;
import static com.g4g.utils.G4GConstants.DASHES;
import static com.g4g.utils.G4GConstants.DATE_EEE_MMM_DD_YYYY_AT_hh_MM_A;
import static com.g4g.utils.G4GConstants.ENDED;
import static com.g4g.utils.G4GConstants.EQUALS;
import static com.g4g.utils.G4GConstants.FALSE;
import static com.g4g.utils.G4GConstants.FRIENDS_REQUEST_COUNT;
import static com.g4g.utils.G4GConstants.ID;
import static com.g4g.utils.G4GConstants.IS_ARCHIEVED_BY_RECIEVER;
import static com.g4g.utils.G4GConstants.IS_ARCHIEVED_BY_SENDER;
import static com.g4g.utils.G4GConstants.IS_DELETED_BY_RECIEVER;
import static com.g4g.utils.G4GConstants.IS_DELETED_BY_SENDER;
import static com.g4g.utils.G4GConstants.IS_READ;
import static com.g4g.utils.G4GConstants.IS_SENT;
import static com.g4g.utils.G4GConstants.MESSAGE_ARCHIVED;
import static com.g4g.utils.G4GConstants.MESSAGE_DELETE;
import static com.g4g.utils.G4GConstants.MESSAGE_LIST_SIZE_CRITERIA;
import static com.g4g.utils.G4GConstants.MESSAGE_READ;
import static com.g4g.utils.G4GConstants.MESSAGE_STATUS;
import static com.g4g.utils.G4GConstants.MESSAGE_TYPE;
import static com.g4g.utils.G4GConstants.MSG_ACCEPT_CHALLENGE_ICN;
import static com.g4g.utils.G4GConstants.MSG_ARCHIVED;
import static com.g4g.utils.G4GConstants.MSG_CASH_CONFIRM;
import static com.g4g.utils.G4GConstants.MSG_CASH_CONFIRM_NAME;
import static com.g4g.utils.G4GConstants.MSG_CHALLENGE;
import static com.g4g.utils.G4GConstants.MSG_CHALLENGE_ICN;
import static com.g4g.utils.G4GConstants.MSG_CHALLENGE_NAME;
import static com.g4g.utils.G4GConstants.MSG_CHALLENGE_RESULT;
import static com.g4g.utils.G4GConstants.MSG_CHALLENGE_RESULT_NAME;
import static com.g4g.utils.G4GConstants.MSG_FRIEND_REQUEST;
import static com.g4g.utils.G4GConstants.MSG_FRIEND_REQUEST_NAME;
import static com.g4g.utils.G4GConstants.MSG_IGNORE;
import static com.g4g.utils.G4GConstants.MSG_MATCH_CONFIRM;
import static com.g4g.utils.G4GConstants.MSG_MATCH_CONFIRM_NAME;
import static com.g4g.utils.G4GConstants.MSG_MATCH_RESULTS;
import static com.g4g.utils.G4GConstants.MSG_MATCH_RESULTS_NAME;
import static com.g4g.utils.G4GConstants.MSG_MESSAGE;
import static com.g4g.utils.G4GConstants.MSG_MESSAGE_ICN;
import static com.g4g.utils.G4GConstants.MSG_MESSAGE_NAME;
import static com.g4g.utils.G4GConstants.MSG_TOURNAMENT_JOIN;
import static com.g4g.utils.G4GConstants.MSG_TOURNAMENT_JOIN_NAME;
import static com.g4g.utils.G4GConstants.MSG_UNREAD;
import static com.g4g.utils.G4GConstants.MSG_UPCOMING_MATCH;
import static com.g4g.utils.G4GConstants.MSG_UPCOMING_MATCH_NAME;
import static com.g4g.utils.G4GConstants.MSG_USER_ICN;
import static com.g4g.utils.G4GConstants.MSG_WORLD_GAMING;
import static com.g4g.utils.G4GConstants.MSG_WORLD_GAMING_NAME;
import static com.g4g.utils.G4GConstants.NEW_CHALLENGE_COUNT;
import static com.g4g.utils.G4GConstants.NEW_COUNT;
import static com.g4g.utils.G4GConstants.NONE;
import static com.g4g.utils.G4GConstants.NOTIFICATIONTYPE;
import static com.g4g.utils.G4GConstants.NOTIFICATION_TYPE_SERVICE;
import static com.g4g.utils.G4GConstants.ONE_NUMBER;
import static com.g4g.utils.G4GConstants.PLAYER2_ACCEPTED;
import static com.g4g.utils.G4GConstants.PLAYERBYFROMPLAYERID;
import static com.g4g.utils.G4GConstants.PLAYERBYPLAYERID1;
import static com.g4g.utils.G4GConstants.PLAYERBYPLAYERID2;
import static com.g4g.utils.G4GConstants.PLAYERBYTOPLAYERID;
import static com.g4g.utils.G4GConstants.PLAYER_LIST_SIZE_CRITERIA;
import static com.g4g.utils.G4GConstants.READ_INBOX_COUNT;
import static com.g4g.utils.G4GConstants.REJECT;
import static com.g4g.utils.G4GConstants.SCREENNAME_DB;
import static com.g4g.utils.G4GConstants.SENT_MESSAGE_COUNT;
import static com.g4g.utils.G4GConstants.SESSIONID;
import static com.g4g.utils.G4GConstants.STARTED;
import static com.g4g.utils.G4GConstants.SUBJECT;
import static com.g4g.utils.G4GConstants.TO_UPDATE_MESSAGE_INFO;
import static com.g4g.utils.G4GConstants.TRUE;
import static com.g4g.utils.G4GConstants.UTF8;
import static com.g4g.utils.G4GConstants.WORLD_GAMING;
import static com.g4g.utils.G4GConstants.WORLD_GAMING_COUNT;
import static com.g4g.utils.G4GConstants.ZERO;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Level;

import com.g4g.delegator.FriendsServiceDelegator;
import com.g4g.delegator.MessageServiceDelegator;
import com.g4g.delegator.NotificationServiceDelegator;
import com.g4g.delegator.PlayerServiceDelegator;
import com.g4g.dto.FriendsDTO;
import com.g4g.dto.MessageDTO;
import com.g4g.dto.NotificationtypeDTO;
import com.g4g.dto.PlayerDTO;
import com.g4g.dto.SearchCriteria;
import com.g4g.dto.SearchListCriteria;
import com.g4g.dto.UserDTO;
import com.g4g.notification.MessageNotification;
import com.g4g.utils.AuditUtil;
import com.g4g.utils.ChallengeCardUpdateUtil;
import com.g4g.utils.DataUtil;
import com.g4g.utils.DateUtil;
import com.g4g.utils.G4GConstants;
import com.impessa.worldgaming.client.InternalException_Exception;
import com.impessa.worldgaming.client.InvalidBetException_Exception;
import com.impessa.worldgaming.client.InvalidSessionException_Exception;
import com.impessa.worldgaming.client.MoneyException_Exception;
import com.impessa.worldgaming.client.UserNotFoundException_Exception;

// $ANALYSIS-IGNORE
/**
 * The Class MailCenterAjaxImpl contains the methods called by ajax to send
 * message, show message, replyToMessage, updateMail, gets Head for showing
 * messages, gets html content for mail center. When the mail center tab is
 * selected, these methods are called in every 30 seconds and update mail
 * centers values.
 *
 * @author Jigar Mistry
 */

public class MailCenterAjaxImpl {
	private static final String DELETED_USER = "DeletedUser";
	/**
	 * Instantiates a new mail center ajax impl.
	 */
	private MailCenterAjaxImpl() {
	}

	/**
	 * This class should not be instantiated and cloned
	 */
	@Override
	public MailCenterAjaxImpl clone() throws CloneNotSupportedException {
		throw new CloneNotSupportedException();
	}

	/**
	 * The method sendMessage sends the message from the logged-in user to other
	 * player.
	 *
	 * @param playerId
	 *            the id of the player from which the message is sent.
	 * @param request
	 *            the request to get the player info of recipient.
	 * @throws UnsupportedEncodingException
	 */
	public static void sendMessage(int playerId, HttpServletRequest request)
			throws UnsupportedEncodingException {
		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,
				new StringBuffer(MailCenterAjaxImpl.class.getName()).append(
						COLON_WITH_SPACES).append(CALLINGMETHOD).append(
						DataUtil.getCallingMethod()).append(CURRENTMETHOD)
						.append(DataUtil.getCurrentMethod()).append(DASHES)
						.append(STARTED).append(DASHES).append(SESSIONID)
						.append(request.getSession().getId()).toString(),
				Level.INFO);

		SearchCriteria searchCriteria = new SearchCriteria();
		String screenName = request.getParameter(ID);
		try {
			screenName = URLDecoder.decode(screenName, UTF8);
		} catch (UnsupportedEncodingException e) {
			AuditUtil.getInstance().writeLog(
					AuditUtil.FILE_TYPE_G4G,
					new StringBuffer(MailCenterAjaxImpl.class.getName())
							.append(COLON_WITH_SPACES).append(CALLINGMETHOD).append(
									DataUtil.getCallingMethod()).append(
									CURRENTMETHOD).append(
									DataUtil.getCurrentMethod()).append(DASHES)
							.append(SESSIONID).append(
									request.getSession().getId())
							.append(DASHES).append(e).toString(), Level.ERROR);
		}
		searchCriteria.setAttribute(SCREENNAME_DB, screenName);
		List<PlayerDTO> playerList = PlayerServiceDelegator
				.getList(searchCriteria);

		if (playerList.size() != ZERO) {
			AuditUtil.getInstance().writeLog(
					AuditUtil.FILE_TYPE_G4G,
					new StringBuffer(MailCenterAjaxImpl.class.getName())
							.append(COLON_WITH_SPACES).append(CALLINGMETHOD).append(
									DataUtil.getCallingMethod()).append(
									CURRENTMETHOD).append(
									DataUtil.getCurrentMethod()).append(DASHES)
							.append(PLAYER_LIST_SIZE_CRITERIA).append(
									playerList.size()).toString(), Level.INFO);
			PlayerDTO playerDTO = playerList.get(ZERO);
			MessageNotification messageNotification = new MessageNotification(
					playerDTO.getId(), playerId, request.getParameter(SUBJECT),
					request.getParameter(BODY));
			try {
				NotificationServiceDelegator.sendNotification(
						messageNotification, request);
			} catch (Exception exception) {
				AuditUtil.getInstance().writeLog(
						AuditUtil.FILE_TYPE_G4G,
						new StringBuffer(MailCenterAjaxImpl.class.getName())
								.append(COLON_WITH_SPACES).append(CALLINGMETHOD).append(
										DataUtil.getCallingMethod()).append(
										CURRENTMETHOD).append(
										DataUtil.getCurrentMethod()).append(
										DASHES).append(SESSIONID).append(
										request.getSession().getId()).append(
										DASHES).append(exception).toString(),
						Level.ERROR);
			}
		}
		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,
				new StringBuffer(MailCenterAjaxImpl.class.getName()).append(
						COLON_WITH_SPACES).append(CALLINGMETHOD).append(
						DataUtil.getCallingMethod()).append(CURRENTMETHOD)
						.append(DataUtil.getCurrentMethod()).append(DASHES)
						.append(ENDED).append(DASHES).append(SESSIONID).append(
								request.getSession().getId()).toString(),
				Level.INFO);
	}

	/**
	 * Updates mail center values for new messages count, incoming challenges
	 * count, Friends request count, Inbox count, World gaming messages count,
	 * Archived messages count, Sent Messages count.
	 *
	 * @param playerId
	 *            the id of the player who is currently logged in.
	 *
	 * @return the string containing count of messages.
	 */
	public static String updateMailCenter(int playerId) {
		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,
				new StringBuffer(MailCenterAjaxImpl.class.getName()).append(
						COLON_WITH_SPACES).append(CALLINGMETHOD).append(
						DataUtil.getCallingMethod()).append(CURRENTMETHOD)
						.append(DataUtil.getCurrentMethod()).append(DASHES)
						.append(STARTED).toString(), Level.INFO);

		StringBuffer result = new StringBuffer();
		PlayerDTO player = new PlayerDTO();
		player.setId(playerId);

		SearchListCriteria searchListCriteria = new SearchListCriteria();

		searchListCriteria.removeAllAttribute();
		searchListCriteria.setAttribute(IS_READ, new Object[] { false,
				SearchListCriteria.EQ });
		searchListCriteria.setAttribute(IS_ARCHIEVED_BY_RECIEVER, new Object[] {
				false, SearchListCriteria.EQ });
		searchListCriteria.setAttribute(IS_DELETED_BY_RECIEVER, new Object[] {
				false, SearchListCriteria.EQ });
		searchListCriteria.setAttribute(PLAYERBYTOPLAYERID, new Object[] {
				player, SearchListCriteria.EQ });
		result.append(NEW_COUNT).append(
				MessageServiceDelegator.getCount(searchListCriteria));

		searchListCriteria.removeAllAttribute();
		NotificationtypeDTO notificationtypeDTO1 = new NotificationtypeDTO();
		notificationtypeDTO1.setId(MSG_CHALLENGE);
		searchListCriteria.setAttribute(IS_READ, new Object[] { false,
				SearchListCriteria.EQ });
		searchListCriteria.setAttribute(NOTIFICATIONTYPE, new Object[] {
				notificationtypeDTO1, SearchListCriteria.EQ });
		searchListCriteria.setAttribute(IS_ARCHIEVED_BY_RECIEVER, new Object[] {
				false, SearchListCriteria.EQ });
		searchListCriteria.setAttribute(IS_DELETED_BY_RECIEVER, new Object[] {
				false, SearchListCriteria.EQ });
		searchListCriteria.setAttribute(PLAYERBYTOPLAYERID, new Object[] {
				player, SearchListCriteria.EQ });
		result.append(NEW_CHALLENGE_COUNT).append(
				MessageServiceDelegator.getCount(searchListCriteria));

		searchListCriteria.removeAllAttribute();

		searchListCriteria.setAttribute(IS_READ, new Object[] { true,
				SearchListCriteria.EQ });
		searchListCriteria.setAttribute(PLAYERBYTOPLAYERID, new Object[] {
				player, SearchListCriteria.EQ });
		searchListCriteria.setAttribute(IS_ARCHIEVED_BY_RECIEVER, new Object[] {
				false, SearchListCriteria.EQ });
		searchListCriteria.setAttribute(IS_DELETED_BY_RECIEVER, new Object[] {
				false, SearchListCriteria.EQ });
		result.append(READ_INBOX_COUNT).append(

		MessageServiceDelegator.getCount(searchListCriteria));

		searchListCriteria.removeAllAttribute();
		NotificationtypeDTO notificationtypeDTO2 = new NotificationtypeDTO();
		notificationtypeDTO2.setId(MSG_FRIEND_REQUEST);
		searchListCriteria.setAttribute(NOTIFICATIONTYPE, new Object[] {
				notificationtypeDTO2, SearchListCriteria.EQ });
		searchListCriteria.setAttribute(IS_ARCHIEVED_BY_RECIEVER, new Object[] {
				false, SearchListCriteria.EQ });
		searchListCriteria.setAttribute(IS_DELETED_BY_RECIEVER, new Object[] {
				false, SearchListCriteria.EQ });
		searchListCriteria.setAttribute(IS_READ, new Object[] { false,
				SearchListCriteria.EQ });
		searchListCriteria.setAttribute(PLAYERBYTOPLAYERID, new Object[] {
				player, SearchListCriteria.EQ });
		result.append(FRIENDS_REQUEST_COUNT).append(
				MessageServiceDelegator.getCount(searchListCriteria));

		searchListCriteria.removeAllAttribute();
		NotificationtypeDTO notificationtypeDTO3 = new NotificationtypeDTO();
		notificationtypeDTO3.setId(MSG_WORLD_GAMING);
		searchListCriteria.setAttribute(IS_READ, new Object[] { false,
				SearchListCriteria.EQ });
		searchListCriteria.setAttribute(IS_DELETED_BY_RECIEVER, new Object[] {
				false, SearchListCriteria.EQ });
		searchListCriteria.setAttribute(IS_ARCHIEVED_BY_RECIEVER, new Object[] {
				false, SearchListCriteria.EQ });
		searchListCriteria.setAttribute(NOTIFICATIONTYPE, new Object[] {
				notificationtypeDTO3, SearchListCriteria.EQ });
		searchListCriteria.setAttribute(PLAYERBYTOPLAYERID, new Object[] {
				player, SearchListCriteria.EQ });
		result.append(WORLD_GAMING_COUNT).append(
				MessageServiceDelegator.getCount(searchListCriteria));

		searchListCriteria.removeAllAttribute();
		result.append(ARCHIVED_COUNT).append(
				MessageServiceDelegator.getArchivedCount(playerId));

		searchListCriteria.removeAllAttribute();
		/*
		 * NotificationtypeDTO notificationtypeDTO4 = new NotificationtypeDTO();
		 * notificationtypeDTO4.setId(MSG_MESSAGE);
		 */
		searchListCriteria.setAttribute(NOTIFICATIONTYPE, new Object[] {
				new NotificationtypeDTO(MSG_MESSAGE), SearchListCriteria.EQ });
		searchListCriteria.setAttribute(IS_ARCHIEVED_BY_SENDER, new Object[] {
				false, SearchListCriteria.EQ });
		searchListCriteria.setAttribute(IS_DELETED_BY_SENDER, new Object[] {
				false, SearchListCriteria.EQ });
		searchListCriteria.setAttribute(PLAYERBYFROMPLAYERID, new Object[] {
				new PlayerDTO(playerId), SearchListCriteria.EQ });
		result.append(SENT_MESSAGE_COUNT).append(
				MessageServiceDelegator.getCount(searchListCriteria)).append(
				BRACATES);
		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,
				new StringBuffer(MailCenterAjaxImpl.class.getName()).append(
						COLON_WITH_SPACES).append(CALLINGMETHOD).append(
						DataUtil.getCallingMethod()).append(CURRENTMETHOD)
						.append(DataUtil.getCurrentMethod()).append(DASHES)
						.append(TO_UPDATE_MESSAGE_INFO).toString(), Level.INFO);
		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,
				new StringBuffer(MailCenterAjaxImpl.class.getName()).append(
						COLON_WITH_SPACES).append(CALLINGMETHOD).append(
						DataUtil.getCallingMethod()).append(CURRENTMETHOD)
						.append(DataUtil.getCurrentMethod()).append(DASHES)
						.append(ENDED).toString(), Level.INFO);
		return result.toString();
	}

	/**
	 * The method shows message in the mail center, It compares the message
	 * types and displays the messages for new messages, incoming challenges ,
	 * Friends request , Inbox , World gaming messages , Archived messages ,
	 * Sent Messages of given message types.<br>
	 * Messages are of 12 types: <br>
	 * 1. Message<br>
	 * 2. Clan<br>
	 * 3. Challenge<br>
	 * 4. ChallengeResult<br>
	 * 5. Friend Request<br>
	 * 6. WorldGaming<br>
	 * 7. MatchConfirm<br>
	 * 8. TournamentJoin<br>
	 * 9. CashConfirm<br>
	 * 10. MatchResults<br>
	 * 11. TournamentResults<br>
	 * 12. UpcomingMatch<br>
	 *
	 * @param playerId
	 *            the id of currently logged-in player.
	 * @param messageStatus
	 *            messageStatus can be: Read, Unread, Deleted, Archived, null
	 * @param messageType
	 *            messageType can be: Message, Clan, Challenge, Acceptance,
	 *            Friend Request, WorldGaming, MarchConfirm, TournamentJoin,
	 *            CashConfirm, MatchResults, TournamentResults, UpcomingMatch,
	 *            null, It checks for all the message types and displays the
	 *            respective message to the mail center.
	 * @param isSent
	 *            the message is sent or not.
	 * @param request
	 *            the request object to get the values from sessions.
	 *
	 * @return the messages result string is given.
	 */
	public static String showMessage(int playerId, String messageStatus,
			String messageType, String isSent, HttpServletRequest request) {
		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,
				new StringBuffer(MailCenterAjaxImpl.class.getName()).append(
						COLON_WITH_SPACES).append(CALLINGMETHOD).append(
						DataUtil.getCallingMethod()).append(CURRENTMETHOD)
						.append(DataUtil.getCurrentMethod()).append(DASHES)
						.append(STARTED).append(DASHES).append(SESSIONID)
						.append(request.getSession().getId()).toString(),
				Level.INFO);
		PlayerDTO player = new PlayerDTO();
		player.setId(playerId);

		StringBuffer result = new StringBuffer();
		SearchListCriteria searchListCriteria = new SearchListCriteria();
		UserDTO userDTO = new UserDTO();
		if (DataUtil.getUserDTO(request) != null) {
			userDTO = DataUtil.getUserDTO(request);
		}
		NotificationtypeDTO notificationtypeDTO = new NotificationtypeDTO();

		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,
				new StringBuffer(MailCenterAjaxImpl.class.getName()).append(
						COLON_WITH_SPACES).append(CALLINGMETHOD).append(
						DataUtil.getCallingMethod()).append(CURRENTMETHOD)
						.append(DataUtil.getCurrentMethod()).append(DASHES)
						.append(MESSAGE_TYPE).append(EQUALS)
						.append(messageType).toString(), Level.INFO);
		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,
				new StringBuffer(MailCenterAjaxImpl.class.getName()).append(
						COLON_WITH_SPACES).append(CALLINGMETHOD).append(
						DataUtil.getCallingMethod()).append(CURRENTMETHOD)
						.append(DataUtil.getCurrentMethod()).append(DASHES)
						.append(MESSAGE_STATUS).append(EQUALS).append(
								messageStatus).toString(), Level.INFO);
		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,
				new StringBuffer(MailCenterAjaxImpl.class.getName()).append(
						COLON_WITH_SPACES).append(CALLINGMETHOD).append(
						DataUtil.getCallingMethod()).append(CURRENTMETHOD)
						.append(DataUtil.getCurrentMethod()).append(DASHES)
						.append(IS_SENT).append(EQUALS).append(isSent)
						.toString(), Level.INFO);
		if (MSG_IGNORE.equals(messageType)) {
			if (!MSG_IGNORE.equals(messageStatus)) {
				searchListCriteria.setAttribute(IS_READ, new Object[] {
						!MSG_UNREAD.equals(messageStatus),
						SearchListCriteria.EQ });
			}
			searchListCriteria.setAttribute(
					FALSE.equals(isSent) ? IS_DELETED_BY_RECIEVER
							: IS_DELETED_BY_SENDER, new Object[] { false,
							SearchListCriteria.EQ });
			searchListCriteria.setAttribute(
					FALSE.equals(isSent) ? PLAYERBYTOPLAYERID
							: PLAYERBYFROMPLAYERID, new Object[] { player,
							SearchListCriteria.EQ });
			// For sent messages.
			if (TRUE.equals(isSent)) {
				searchListCriteria.setAttribute(IS_ARCHIEVED_BY_SENDER,
						new Object[] { false, SearchListCriteria.EQ });
				notificationtypeDTO.setId(MSG_MESSAGE);
				searchListCriteria.setAttribute(NOTIFICATIONTYPE, new Object[] {
						notificationtypeDTO, SearchListCriteria.EQ });
			} else {
				searchListCriteria.setAttribute(IS_ARCHIEVED_BY_RECIEVER,
						new Object[] { false, SearchListCriteria.EQ });
			}
		} // For challenge type.
		else if (MSG_CHALLENGE_NAME.equals(messageType)) {
			if (!MSG_IGNORE.equals(messageStatus)) {
				searchListCriteria.setAttribute(IS_READ, new Object[] {
						!MSG_UNREAD.equals(messageStatus),
						SearchListCriteria.EQ });
			}
			searchListCriteria.setAttribute(IS_ARCHIEVED_BY_SENDER,
					new Object[] { false, SearchListCriteria.EQ });
			notificationtypeDTO.setId(MSG_CHALLENGE);
			searchListCriteria.setAttribute(NOTIFICATIONTYPE, new Object[] {
					notificationtypeDTO, SearchListCriteria.EQ });
			searchListCriteria.setAttribute(
					FALSE.equals(isSent) ? IS_DELETED_BY_RECIEVER
							: IS_DELETED_BY_SENDER, new Object[] { false,
							SearchListCriteria.EQ });
			searchListCriteria.setAttribute(
					FALSE.equals(isSent) ? PLAYERBYTOPLAYERID
							: PLAYERBYFROMPLAYERID, new Object[] { player,
							SearchListCriteria.EQ });
		} // For Friend Request type.
		else if (MSG_FRIEND_REQUEST_NAME.equals(messageType)) {
			searchListCriteria.setAttribute(
					FALSE.equals(isSent) ? IS_ARCHIEVED_BY_RECIEVER
							: IS_ARCHIEVED_BY_SENDER, new Object[] { false,
							SearchListCriteria.EQ });
			notificationtypeDTO.setId(MSG_FRIEND_REQUEST);
			searchListCriteria.setAttribute(NOTIFICATIONTYPE, new Object[] {
					notificationtypeDTO, SearchListCriteria.EQ });
			searchListCriteria.setAttribute(IS_READ, new Object[] {
					!MSG_UNREAD.equals(messageStatus), SearchListCriteria.EQ });
			searchListCriteria.setAttribute(
					FALSE.equals(isSent) ? IS_DELETED_BY_RECIEVER
							: IS_DELETED_BY_SENDER, new Object[] { false,
							SearchListCriteria.EQ });

			searchListCriteria.setAttribute(
					FALSE.equals( isSent ) ? PLAYERBYTOPLAYERID
							: PLAYERBYFROMPLAYERID, new Object[] { player,
							SearchListCriteria.EQ });
		} // For WorldGaming type.
		else if (MSG_WORLD_GAMING_NAME.equals( messageType )) {
			if (!MSG_IGNORE.equals( messageStatus )) {
				searchListCriteria.setAttribute(IS_READ, new Object[] {
						!MSG_UNREAD.equals( messageStatus ),
						SearchListCriteria.EQ });
			}
			searchListCriteria.setAttribute(
					FALSE.equals( isSent ) ? IS_ARCHIEVED_BY_RECIEVER
							: IS_ARCHIEVED_BY_SENDER, new Object[] { false,
							SearchListCriteria.EQ });
			notificationtypeDTO.setId(MSG_WORLD_GAMING);
			searchListCriteria.setAttribute(NOTIFICATIONTYPE, new Object[] {
					notificationtypeDTO, SearchListCriteria.EQ });
			searchListCriteria.setAttribute(
					FALSE.equals( isSent ) ? IS_DELETED_BY_RECIEVER
							: IS_DELETED_BY_SENDER, new Object[] { false,
							SearchListCriteria.EQ });
			searchListCriteria.setAttribute(
					FALSE.equals( isSent ) ? PLAYERBYTOPLAYERID
							: PLAYERBYFROMPLAYERID, new Object[] { player,
							SearchListCriteria.EQ });
		} // For ChallengeResult type.
		else if (MSG_CHALLENGE_RESULT_NAME.equals( messageType )) {
			if (!MSG_IGNORE.equals( messageStatus )) {
				searchListCriteria.setAttribute(IS_READ, new Object[] {
						!MSG_UNREAD.equals( messageStatus ),
						SearchListCriteria.EQ });
			}
			searchListCriteria.setAttribute(
					FALSE.equals( isSent ) ? IS_ARCHIEVED_BY_RECIEVER
							: IS_ARCHIEVED_BY_SENDER, new Object[] { false,
							SearchListCriteria.EQ });
			notificationtypeDTO.setId(MSG_CHALLENGE_RESULT);
			searchListCriteria.setAttribute(NOTIFICATIONTYPE, new Object[] {
					notificationtypeDTO, SearchListCriteria.EQ });
			searchListCriteria.setAttribute(
					FALSE.equals( isSent ) ? IS_DELETED_BY_RECIEVER
							: IS_DELETED_BY_SENDER, new Object[] { false,
							SearchListCriteria.EQ });
			searchListCriteria.setAttribute(
					FALSE.equals(isSent) ? PLAYERBYTOPLAYERID
							: PLAYERBYFROMPLAYERID, new Object[] { player,
							SearchListCriteria.EQ });
		} // For MatchConfirm type.
		else if (MSG_MATCH_CONFIRM_NAME.equals(messageType)) {
			if (!MSG_IGNORE.equals(messageStatus)) {
				searchListCriteria.setAttribute(IS_READ, new Object[] {
						!MSG_UNREAD.equals(messageStatus),
						SearchListCriteria.EQ });
			}
			searchListCriteria.setAttribute(
					FALSE.equals(isSent) ? IS_ARCHIEVED_BY_RECIEVER
							: IS_ARCHIEVED_BY_SENDER, new Object[] { false,
							SearchListCriteria.EQ });
			notificationtypeDTO.setId(MSG_MATCH_CONFIRM);
			searchListCriteria.setAttribute(NOTIFICATIONTYPE, new Object[] {
					notificationtypeDTO, SearchListCriteria.EQ });
			searchListCriteria.setAttribute(
					FALSE.equals(isSent) ? IS_DELETED_BY_RECIEVER
							: IS_DELETED_BY_SENDER, new Object[] { false,
							SearchListCriteria.EQ });
			searchListCriteria.setAttribute(
					FALSE.equals(isSent) ? PLAYERBYTOPLAYERID
							: PLAYERBYFROMPLAYERID, new Object[] { player,
							SearchListCriteria.EQ });
		} // For TournamentJoin type.
		else if (MSG_TOURNAMENT_JOIN_NAME.equals(messageType)) {
			if (!MSG_IGNORE.equals(messageStatus)) {
				searchListCriteria.setAttribute(IS_READ, new Object[] {
						!MSG_UNREAD.equals(messageStatus),
						SearchListCriteria.EQ });
			}
			searchListCriteria.setAttribute(
					FALSE.equals(isSent) ? IS_ARCHIEVED_BY_RECIEVER
							: IS_ARCHIEVED_BY_SENDER, new Object[] { false,
							SearchListCriteria.EQ });
			notificationtypeDTO.setId(MSG_TOURNAMENT_JOIN);
			searchListCriteria.setAttribute(NOTIFICATIONTYPE, new Object[] {
					notificationtypeDTO, SearchListCriteria.EQ });
			searchListCriteria.setAttribute(
					FALSE.equals(isSent) ? IS_DELETED_BY_RECIEVER
							: IS_DELETED_BY_SENDER, new Object[] { false,
							SearchListCriteria.EQ });
			searchListCriteria.setAttribute(
					FALSE.equals(isSent) ? PLAYERBYTOPLAYERID
							: PLAYERBYFROMPLAYERID, new Object[] { player,
							SearchListCriteria.EQ });
		} // For CashConfirm type
		else if (MSG_CASH_CONFIRM_NAME.equals(messageType)) {
			if (!MSG_IGNORE.equals(messageStatus)) {
				searchListCriteria.setAttribute(IS_READ, new Object[] {
						!MSG_UNREAD.equals(messageStatus),
						SearchListCriteria.EQ });
			}
			searchListCriteria.setAttribute(
					FALSE.equals(isSent) ? IS_ARCHIEVED_BY_RECIEVER
							: IS_ARCHIEVED_BY_SENDER, new Object[] { false,
							SearchListCriteria.EQ });
			notificationtypeDTO.setId(MSG_CASH_CONFIRM);
			searchListCriteria.setAttribute(NOTIFICATIONTYPE, new Object[] {
					notificationtypeDTO, SearchListCriteria.EQ });
			searchListCriteria.setAttribute(
					FALSE.equals(isSent) ? IS_DELETED_BY_RECIEVER
							: IS_DELETED_BY_SENDER, new Object[] { false,
							SearchListCriteria.EQ });
			searchListCriteria.setAttribute(
					FALSE.equals(isSent) ? PLAYERBYTOPLAYERID
							: PLAYERBYFROMPLAYERID, new Object[] { player,
							SearchListCriteria.EQ });
		} // For MatchResults type
		else if (MSG_MATCH_RESULTS_NAME.equals(messageType)) {
			if (!MSG_IGNORE.equals(messageStatus)) {
				searchListCriteria.setAttribute(IS_READ, new Object[] {
						!MSG_UNREAD.equals(messageStatus),
						SearchListCriteria.EQ });
			}
			searchListCriteria.setAttribute(
					FALSE.equals(isSent) ? IS_ARCHIEVED_BY_RECIEVER
							: IS_ARCHIEVED_BY_SENDER, new Object[] { false,
							SearchListCriteria.EQ });
			notificationtypeDTO.setId(MSG_MATCH_RESULTS);
			searchListCriteria.setAttribute(NOTIFICATIONTYPE, new Object[] {
					notificationtypeDTO, SearchListCriteria.EQ });
			searchListCriteria.setAttribute(
					FALSE.equals(isSent) ? IS_DELETED_BY_RECIEVER
							: IS_DELETED_BY_SENDER, new Object[] { false,
							SearchListCriteria.EQ });
			searchListCriteria.setAttribute(
					FALSE.equals(isSent) ? PLAYERBYTOPLAYERID
							: PLAYERBYFROMPLAYERID, new Object[] { player,
							SearchListCriteria.EQ });
		}// For TournamentJoin type
		else if (MSG_TOURNAMENT_JOIN_NAME.equals(messageType)) {
			if (!MSG_IGNORE.equals(messageStatus)) {
				searchListCriteria.setAttribute(IS_READ, new Object[] {
						!MSG_UNREAD.equals(messageStatus),
						SearchListCriteria.EQ });
			}
			searchListCriteria.setAttribute(
					FALSE.equals(isSent) ? IS_ARCHIEVED_BY_RECIEVER
							: IS_ARCHIEVED_BY_SENDER, new Object[] { false,
							SearchListCriteria.EQ });
			notificationtypeDTO.setId(MSG_TOURNAMENT_JOIN);
			searchListCriteria.setAttribute(NOTIFICATIONTYPE, new Object[] {
					notificationtypeDTO, SearchListCriteria.EQ });
			searchListCriteria.setAttribute(
					FALSE.equals(isSent) ? IS_DELETED_BY_RECIEVER
							: IS_DELETED_BY_SENDER, new Object[] { false,
							SearchListCriteria.EQ });
			searchListCriteria.setAttribute(
					FALSE.equals(isSent) ? PLAYERBYTOPLAYERID
							: PLAYERBYFROMPLAYERID, new Object[] { player,
							SearchListCriteria.EQ });
		} // For UpcomingMatch type
		else if (MSG_UPCOMING_MATCH_NAME.equals(messageType)) {
			if (!MSG_IGNORE.equals(messageStatus)) {
				searchListCriteria.setAttribute(IS_READ, new Object[] {
						!MSG_UNREAD.equals(messageStatus),
						SearchListCriteria.EQ });
			}
			searchListCriteria.setAttribute(
					FALSE.equals(isSent) ? IS_ARCHIEVED_BY_RECIEVER
							: IS_ARCHIEVED_BY_SENDER, new Object[] { false,
							SearchListCriteria.EQ });
			notificationtypeDTO.setId(MSG_UPCOMING_MATCH);
			searchListCriteria.setAttribute(NOTIFICATIONTYPE, new Object[] {
					notificationtypeDTO, SearchListCriteria.EQ });
			searchListCriteria.setAttribute(
					FALSE.equals(isSent) ? IS_DELETED_BY_RECIEVER
							: IS_DELETED_BY_SENDER, new Object[] { false,
							SearchListCriteria.EQ });
			searchListCriteria.setAttribute(
					FALSE.equals(isSent) ? PLAYERBYTOPLAYERID
							: PLAYERBYFROMPLAYERID, new Object[] { player,
							SearchListCriteria.EQ });
		}
		List<MessageDTO> messageList = null;
		// If messageStatus is archived.
		if (MSG_ARCHIVED.equals(messageStatus)) {
			messageList = MessageServiceDelegator.getArchivedList(playerId);
		} else {
			searchListCriteria.setOrderBy(G4GConstants.CREATED_DATE);
			messageList = MessageServiceDelegator.getList(searchListCriteria);
		}

		int totalMessages = messageList.size();
		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,
				new StringBuffer(MailCenterAjaxImpl.class.getName()).append(
						COLON_WITH_SPACES).append(CALLINGMETHOD).append(
						DataUtil.getCallingMethod()).append(CURRENTMETHOD)
						.append(DataUtil.getCurrentMethod()).append(DASHES)
						.append(MESSAGE_LIST_SIZE_CRITERIA).append(
								messageList.size()).toString(), Level.INFO);
		for (int index = ZERO; index < totalMessages; index++) {
			MessageDTO message = messageList.get(index);
			PlayerDTO playerDTO = message.getPlayerByFromplayerid();
			String datetime = DataUtil.getDate(DateUtil.getDateOfTimeZone(
					message.getCreateddate(), userDTO.getOffset()),
					DATE_EEE_MMM_DD_YYYY_AT_hh_MM_A);

			// For FRIEND_REQUEST type
			if (message.getNotificationtype().getId() == MSG_FRIEND_REQUEST) {
				AuditUtil.getInstance().writeLog(
						AuditUtil.FILE_TYPE_G4G,
						new StringBuffer(MailCenterAjaxImpl.class.getName())
								.append(COLON_WITH_SPACES).append(CALLINGMETHOD).append(
										DataUtil.getCallingMethod()).append(
										CURRENTMETHOD).append(
										DataUtil.getCurrentMethod()).append(
										DASHES).append(
										NOTIFICATION_TYPE_SERVICE).append(
										EQUALS).append(MSG_FRIEND_REQUEST_NAME)
								.toString(), Level.INFO);
				result
						.append(getHead(request, message.getRandomid(),
								playerDTO.getId(), playerDTO.getScreenname(),
								MSG_USER_ICN, MSG_FRIEND_REQUEST_NAME,
								datetime, NONE, messageStatus, message.getBody()));
			}// For CHALLENGE type
			else if (message.getNotificationtype().getId() == MSG_CHALLENGE) {
				// String encryptedMessageId =
				// DataUtil.encrypt(String.valueOf(message.getId()));
				AuditUtil.getInstance().writeLog(
						AuditUtil.FILE_TYPE_G4G,
						new StringBuffer(MailCenterAjaxImpl.class.getName())
								.append(COLON_WITH_SPACES).append(CALLINGMETHOD).append(
										DataUtil.getCallingMethod()).append(
										CURRENTMETHOD).append(
										DataUtil.getCurrentMethod()).append(
										DASHES).append(
										NOTIFICATION_TYPE_SERVICE).append(
										EQUALS).append(MSG_CHALLENGE_NAME)
								.toString(), Level.INFO);
				result.append(getHead(request, message.getRandomid(), playerDTO
						.getId(), playerDTO.getScreenname(), MSG_CHALLENGE_ICN,
						MSG_CHALLENGE_NAME, datetime, NONE, messageStatus, message.getBody()));
			}// For WORLD_GAMING type
			else if (message.getNotificationtype().getId() == MSG_WORLD_GAMING) {
				// String encryptedMessageId =
				// DataUtil.encrypt(String.valueOf(message.getId()));
				AuditUtil.getInstance().writeLog(
						AuditUtil.FILE_TYPE_G4G,
						new StringBuffer(MailCenterAjaxImpl.class.getName())
								.append(COLON_WITH_SPACES).append(CALLINGMETHOD).append(
										DataUtil.getCallingMethod()).append(
										CURRENTMETHOD).append(
										DataUtil.getCurrentMethod()).append(
										DASHES).append(
										NOTIFICATION_TYPE_SERVICE).append(
										EQUALS).append(MSG_WORLD_GAMING_NAME)
								.toString(), Level.INFO);
				result.append(getHead(request, message.getRandomid(), ZERO,
						MSG_WORLD_GAMING_NAME, MSG_CHALLENGE_ICN,
						MSG_WORLD_GAMING_NAME, datetime, NONE, messageStatus, message.getBody()));
			}// For MATCH_RESULTS type
			else if (message.getNotificationtype().getId() == MSG_MATCH_RESULTS) {
				// String encryptedMessageId =
				// DataUtil.encrypt(String.valueOf(message.getId()));
				AuditUtil.getInstance().writeLog(
						AuditUtil.FILE_TYPE_G4G,
						new StringBuffer(MailCenterAjaxImpl.class.getName())
								.append(COLON_WITH_SPACES).append(CALLINGMETHOD).append(
										DataUtil.getCallingMethod()).append(
										CURRENTMETHOD).append(
										DataUtil.getCurrentMethod()).append(
										DASHES).append(
										NOTIFICATION_TYPE_SERVICE).append(
										EQUALS).append(MSG_MATCH_RESULTS_NAME)
								.toString(), Level.INFO);
				result.append(getHead(request, message.getRandomid(),
						G4GConstants.ZERO, G4GConstants.WORLD_GAMING,
						MSG_CHALLENGE_ICN, MSG_MATCH_RESULTS_NAME, datetime,
						NONE, messageStatus, message.getBody()));
			}// For MATCH_CONFIRM type
			else if (message.getNotificationtype().getId() == MSG_MATCH_CONFIRM) {
				// String encryptedMessageId =
				// DataUtil.encrypt(String.valueOf(message.getId()));
				AuditUtil.getInstance().writeLog(
						AuditUtil.FILE_TYPE_G4G,
						new StringBuffer(MailCenterAjaxImpl.class.getName())
								.append(COLON_WITH_SPACES).append(CALLINGMETHOD).append(
										DataUtil.getCallingMethod()).append(
										CURRENTMETHOD).append(
										DataUtil.getCurrentMethod()).append(
										DASHES).append(
										NOTIFICATION_TYPE_SERVICE).append(
										EQUALS).append(MSG_MATCH_CONFIRM_NAME)
								.toString(), Level.INFO);
				result.append(getHead(request, message.getRandomid(), ZERO,
						WORLD_GAMING, MSG_ACCEPT_CHALLENGE_ICN,
						MSG_MATCH_CONFIRM_NAME, datetime, NONE, messageStatus, message.getBody()));
			}// For TOURNAMENT_JOIN type
			else if (message.getNotificationtype().getId() == MSG_TOURNAMENT_JOIN) {
				// String encryptedMessageId =
				// DataUtil.encrypt(String.valueOf(message.getId()));
				AuditUtil.getInstance().writeLog(
						AuditUtil.FILE_TYPE_G4G,
						new StringBuffer(MailCenterAjaxImpl.class.getName())
								.append(COLON_WITH_SPACES).append(CALLINGMETHOD).append(
										DataUtil.getCallingMethod()).append(
										CURRENTMETHOD).append(
										DataUtil.getCurrentMethod()).append(
										DASHES).append(
										NOTIFICATION_TYPE_SERVICE).append(
										EQUALS)
								.append(MSG_TOURNAMENT_JOIN_NAME).toString(),
						Level.INFO);
				result
						.append(getHead(request, message.getRandomid(), ZERO,
								WORLD_GAMING, MSG_CHALLENGE_ICN,
								MSG_TOURNAMENT_JOIN_NAME, datetime, NONE,
								messageStatus, message.getBody()));
			}// For MATCH_RESULTS type
			else if (message.getNotificationtype().getId() == MSG_MATCH_RESULTS) {
				// String encryptedMessageId =
				// DataUtil.encrypt(String.valueOf(message.getId()));
				AuditUtil.getInstance().writeLog(
						AuditUtil.FILE_TYPE_G4G,
						new StringBuffer(MailCenterAjaxImpl.class.getName())
								.append(COLON_WITH_SPACES).append(CALLINGMETHOD).append(
										DataUtil.getCallingMethod()).append(
										CURRENTMETHOD).append(
										DataUtil.getCurrentMethod()).append(
										DASHES).append(
										NOTIFICATION_TYPE_SERVICE).append(
										EQUALS).append(MSG_MATCH_RESULTS_NAME)
								.toString(), Level.INFO);
				result.append(getHead(request, message.getRandomid(), playerDTO
						.getId(), playerDTO.getScreenname(), MSG_CHALLENGE_ICN,
						MSG_MATCH_RESULTS_NAME, datetime, NONE, messageStatus, message.getBody()));
			}// For CASH_CONFIRM type
			else if (message.getNotificationtype().getId() == MSG_CASH_CONFIRM) {
				// String encryptedMessageId =
				// DataUtil.encrypt(String.valueOf(message.getId()));
				AuditUtil.getInstance().writeLog(
						AuditUtil.FILE_TYPE_G4G,
						new StringBuffer(MailCenterAjaxImpl.class.getName())
								.append(COLON_WITH_SPACES).append(CALLINGMETHOD).append(
										DataUtil.getCallingMethod()).append(
										CURRENTMETHOD).append(
										DataUtil.getCurrentMethod()).append(
										DASHES).append(
										NOTIFICATION_TYPE_SERVICE).append(
										EQUALS).append(MSG_CASH_CONFIRM_NAME)
								.toString(), Level.INFO);
				result.append(getHead(request, message.getRandomid(), ZERO,
						WORLD_GAMING, MSG_MESSAGE_ICN, MSG_CASH_CONFIRM_NAME,
						datetime, NONE, messageStatus, message.getBody()));
			}// For MESSAGE type
			else if (message.getNotificationtype().getId() == MSG_MESSAGE) {
				// String encryptedMessageId =
				// DataUtil.encrypt(String.valueOf(message.getId()));
				AuditUtil.getInstance().writeLog(
						AuditUtil.FILE_TYPE_G4G,
						new StringBuffer(MailCenterAjaxImpl.class.getName())
								.append(COLON_WITH_SPACES).append(CALLINGMETHOD).append(
										DataUtil.getCallingMethod()).append(
										CURRENTMETHOD).append(
										DataUtil.getCurrentMethod()).append(
										DASHES).append(
										NOTIFICATION_TYPE_SERVICE).append(
										EQUALS).append(MSG_MESSAGE_NAME)
								.toString(), Level.INFO);
				String screenName = playerDTO.getScreenname();
				int recipientId = playerDTO.getId();
				if (message.getPlayerByFromplayerid().getId() == userDTO
						.getPlayerDTO().getId()) {
					recipientId = message.getPlayerByToplayerid().getId();
					screenName = PlayerServiceDelegator.getPlayer(
							message.getPlayerByToplayerid().getId())
							.getScreenname();
				}

				result.append(getHead(request, message.getRandomid(), recipientId, screenName, MSG_MESSAGE_ICN,
						MSG_MESSAGE_NAME, datetime, NONE, messageStatus, message.getBody()));
			}
		}

		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,
				new StringBuffer(MailCenterAjaxImpl.class.getName()).append(
						COLON_WITH_SPACES).append(CALLINGMETHOD).append(
						DataUtil.getCallingMethod()).append(CURRENTMETHOD)
						.append(DataUtil.getCurrentMethod()).append(DASHES)
						.append(result.toString()).toString(), Level.INFO);

		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,
				new StringBuffer(MailCenterAjaxImpl.class.getName()).append(
						COLON_WITH_SPACES).append(CALLINGMETHOD).append(
						DataUtil.getCallingMethod()).append(CURRENTMETHOD)
						.append(DataUtil.getCurrentMethod()).append(DASHES)
						.append(STARTED).append(DASHES).append(SESSIONID)
						.append(request.getSession().getId()).toString(),
				Level.INFO);
		return result.toString();
	}

	/**
	 * The method replyToMessage fills out the messageDTO with replyText and
	 * sends the message as reply.
	 *
	 * @param messageId
	 *            the messageid is id of the message to which the reply is
	 *            given.
	 * @param replyText
	 *            the text body as reply.
	 * @param request
	 */
	public static void replyToMessage(String messageId, String replyText,
			HttpServletRequest request) {
		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,
				new StringBuffer(MailCenterAjaxImpl.class.getName()).append(
						COLON_WITH_SPACES).append(CALLINGMETHOD).append(
						DataUtil.getCallingMethod()).append(CURRENTMETHOD)
						.append(DataUtil.getCurrentMethod()).append(DASHES)
						.append(STARTED).append(DASHES).append(SESSIONID)
						.append(request.getSession().getId()).toString(),
				Level.INFO);

		MessageDTO dtoFrom = MessageServiceDelegator.getMessage(messageId);
		String subject = dtoFrom.getSubject();

		try {
			subject = URLDecoder.decode(subject, UTF8);
		} catch (UnsupportedEncodingException exception) {
			AuditUtil.getInstance().writeLog(
					AuditUtil.FILE_TYPE_G4G,
					new StringBuffer(MailCenterAjaxImpl.class.getName())
							.append(COLON_WITH_SPACES).append(CALLINGMETHOD).append(
									DataUtil.getCallingMethod()).append(
									CURRENTMETHOD).append(
									DataUtil.getCurrentMethod()).append(DASHES)
							.append(SESSIONID).append(
									request.getSession().getId())
							.append(DASHES).append(exception).toString(),
					Level.ERROR);
		}

		MessageNotification messageNotification = new MessageNotification(
				dtoFrom.getPlayerByFromplayerid().getId(), dtoFrom
						.getPlayerByToplayerid().getId(), subject, replyText);
		try {
			NotificationServiceDelegator.sendNotification(messageNotification,
					request);
		} catch (Exception exception) {
			AuditUtil.getInstance().writeLog(
					AuditUtil.FILE_TYPE_G4G,
					new StringBuffer(MailCenterAjaxImpl.class.getName())
							.append(COLON_WITH_SPACES).append(CALLINGMETHOD).append(
									DataUtil.getCallingMethod()).append(
									CURRENTMETHOD).append(
									DataUtil.getCurrentMethod()).append(DASHES)
							.append(SESSIONID).append(
									request.getSession().getId())
							.append(DASHES).append(exception).toString(),
					Level.ERROR);

		}
		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,
				new StringBuffer(MailCenterAjaxImpl.class.getName()).append(
						COLON_WITH_SPACES).append(CALLINGMETHOD).append(
						DataUtil.getCallingMethod()).append(CURRENTMETHOD)
						.append(DataUtil.getCurrentMethod()).append(DASHES)
						.append(STARTED).append(DASHES).append(SESSIONID)
						.append(request.getSession().getId()).toString(),
				Level.INFO);
	}

	/**
	 * Changes message status. MessageStatus can be: Read, Unread, Deleted,
	 * Archived, null
	 *
	 * @param playerId
	 *            the playerid of the logged-in player.
	 * @param messageId
	 *            the messageid id of message whose status changes.
	 * @param messageStatus
	 *            the messagestatus status of the message to change.
	 * @param request
	 *            the request object to get the values from the session.
	 */
	public static void changeMessageStatus(int playerId, String messageId,
			String messageStatus, HttpServletRequest request) {
		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,
				new StringBuffer(MailCenterAjaxImpl.class.getName()).append(
						COLON_WITH_SPACES).append(CALLINGMETHOD).append(
						DataUtil.getCallingMethod()).append(CURRENTMETHOD)
						.append(DataUtil.getCurrentMethod()).append(DASHES)
						.append(STARTED).append(DASHES).append(SESSIONID)
						.append(request.getSession().getId()).toString(),
				Level.INFO);
		messageId = messageId.trim();
		MessageDTO dtoFrom = MessageServiceDelegator.getMessage(messageId);
		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,
				new StringBuffer(MailCenterAjaxImpl.class.getName()).append(
						COLON_WITH_SPACES).append(CALLINGMETHOD).append(
						DataUtil.getCallingMethod()).append(CURRENTMETHOD)
						.append(DataUtil.getCurrentMethod()).append(DASHES)
						.append(MESSAGE_STATUS).append(EQUALS).append(
								messageStatus).toString(), Level.INFO);

		if (MESSAGE_ARCHIVED.equals(messageStatus)) {
			if (playerId == dtoFrom.getPlayerByFromplayerid().getId()) {
				dtoFrom.setIsarchivedbysender(true);
			} else {
				dtoFrom.setIsarchievedbyreciever(true);
			}
		} else if (MESSAGE_READ.equals(messageStatus)) {
			if (DataUtil.getUserDTO(request).getMessageCount() > ZERO) {
				DataUtil.getUserDTO(request).setMessageCount(
						DataUtil.getUserDTO(request).getMessageCount()
								- ONE_NUMBER);
			}
			dtoFrom.setIsread(true);
		} else if(DELETED_USER.equals(messageStatus)) {
			dtoFrom.setIsdeletedbyreciever(true);
		} else if (MESSAGE_DELETE.equals(messageStatus)) {
			if (dtoFrom.getPlayerByFromplayerid() != null
					&& playerId == dtoFrom.getPlayerByFromplayerid().getId()) {
				dtoFrom.setIsdeletedbysender(true);
			} else {
				if (dtoFrom.getNotificationtype().getId() == MSG_FRIEND_REQUEST) {
					FriendsDTO friendsDTO = new FriendsDTO();
					SearchListCriteria searchListCriteria = new SearchListCriteria();

					searchListCriteria.setAttribute(PLAYERBYPLAYERID1,
							new Object[] { dtoFrom.getPlayerByFromplayerid(),
									SearchListCriteria.EQ });
					searchListCriteria.setAttribute(PLAYERBYPLAYERID2,
							new Object[] { dtoFrom.getPlayerByToplayerid(),
									SearchListCriteria.EQ });
					searchListCriteria.setAttribute(PLAYER2_ACCEPTED,
							new Object[] { null,
									SearchListCriteria.ISNULL });
					List<FriendsDTO> friendsList = FriendsServiceDelegator
							.getList(searchListCriteria);

					if (friendsList.size() > ZERO) {
						friendsDTO = friendsList.get(ZERO);
						try {
							FriendsServiceDelegator.delete(friendsDTO);
						} catch (Exception exception) {
							AuditUtil
									.getInstance()
									.writeLog(
											AuditUtil.FILE_TYPE_G4G,
											new StringBuffer(
													MailCenterAjaxImpl.class
															.getName())
													.append(COLON_WITH_SPACES)
													.append(CALLINGMETHOD)
													.append(
															DataUtil
																	.getCallingMethod())
													.append(CURRENTMETHOD)
													.append(
															DataUtil
																	.getCurrentMethod())
													.append(DASHES)
													.append(SESSIONID)
													.append(
															request
																	.getSession()
																	.getId())
													.append(DASHES).append(
															exception)
													.toString(), Level.ERROR);
						}
					}
				}
				if (dtoFrom.getMatchid() != null
						&& dtoFrom.getMatchid().getId() != ZERO) {
					try {
						if (dtoFrom.getMatchid().getTwoplayertournament()
								.getLevels() <= ONE_NUMBER) {
							ChallengeCardUpdateUtil.rejectChallenge(dtoFrom
									.getMatchid().getId(), request, REJECT);
						}
					} catch (InternalException_Exception exception) {
						AuditUtil.getInstance().writeLog(
								AuditUtil.FILE_TYPE_G4G_FINANCIAL,
								new StringBuffer(MailCenterAjaxImpl.class
										.getName()).append(COLON_WITH_SPACES).append(
										CALLINGMETHOD).append(
										DataUtil.getCallingMethod()).append(
										CURRENTMETHOD).append(
										DataUtil.getCurrentMethod()).append(
										DASHES).append(SESSIONID).append(
										request.getSession().getId()).append(
										DASHES).append(exception).toString(),
								Level.ERROR);
					} catch (InvalidBetException_Exception exception) {
						AuditUtil.getInstance().writeLog(
								AuditUtil.FILE_TYPE_G4G_FINANCIAL,
								new StringBuffer(MailCenterAjaxImpl.class
										.getName()).append(COLON_WITH_SPACES).append(
										CALLINGMETHOD).append(
										DataUtil.getCallingMethod()).append(
										CURRENTMETHOD).append(
										DataUtil.getCurrentMethod()).append(
										DASHES).append(SESSIONID).append(
										request.getSession().getId()).append(
										DASHES).append(exception).toString(),
								Level.ERROR);
					} catch (InvalidSessionException_Exception exception) {
						AuditUtil.getInstance().writeLog(
								AuditUtil.FILE_TYPE_G4G_FINANCIAL,
								new StringBuffer(MailCenterAjaxImpl.class
										.getName()).append(COLON_WITH_SPACES).append(
										CALLINGMETHOD).append(
										DataUtil.getCallingMethod()).append(
										CURRENTMETHOD).append(
										DataUtil.getCurrentMethod()).append(
										DASHES).append(SESSIONID).append(
										request.getSession().getId()).append(
										DASHES).append(exception).toString(),
								Level.ERROR);
					} catch (MoneyException_Exception exception) {
						AuditUtil.getInstance().writeLog(
								AuditUtil.FILE_TYPE_G4G_FINANCIAL,
								new StringBuffer(MailCenterAjaxImpl.class
										.getName()).append(COLON_WITH_SPACES).append(
										CALLINGMETHOD).append(
										DataUtil.getCallingMethod()).append(
										CURRENTMETHOD).append(
										DataUtil.getCurrentMethod()).append(
										DASHES).append(SESSIONID).append(
										request.getSession().getId()).append(
										DASHES).append(exception).toString(),
								Level.ERROR);
					} catch (UserNotFoundException_Exception exception) {
						AuditUtil.getInstance().writeLog(
								AuditUtil.FILE_TYPE_G4G_FINANCIAL,
								new StringBuffer(MailCenterAjaxImpl.class
										.getName()).append(COLON_WITH_SPACES).append(
										CALLINGMETHOD).append(
										DataUtil.getCallingMethod()).append(
										CURRENTMETHOD).append(
										DataUtil.getCurrentMethod()).append(
										DASHES).append(SESSIONID).append(
										request.getSession().getId()).append(
										DASHES).append(exception).toString(),
								Level.ERROR);
					} catch (Exception exception) {
						AuditUtil.getInstance().writeLog(
								AuditUtil.FILE_TYPE_G4G,
								new StringBuffer(MailCenterAjaxImpl.class
										.getName()).append(COLON_WITH_SPACES).append(
										CALLINGMETHOD).append(
										DataUtil.getCallingMethod()).append(
										CURRENTMETHOD).append(
										DataUtil.getCurrentMethod()).append(
										DASHES).append(SESSIONID).append(
										request.getSession().getId()).append(
										DASHES).append(exception).toString(),
								Level.ERROR);
					}
				}
				dtoFrom.setIsdeletedbyreciever(true);
			}
		}

		try {
			MessageServiceDelegator.update(dtoFrom);
		} catch (Exception exception) {
			AuditUtil.getInstance().writeLog(
					AuditUtil.FILE_TYPE_HIBERNATE,
					new StringBuffer(MailCenterAjaxImpl.class.getName())
							.append(COLON_WITH_SPACES).append(CALLINGMETHOD).append(
									DataUtil.getCallingMethod()).append(
									CURRENTMETHOD).append(
									DataUtil.getCurrentMethod()).append(DASHES)
							.append(SESSIONID).append(
									request.getSession().getId())
							.append(DASHES).append(exception).toString(),
					Level.ERROR);
		}
		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,
				new StringBuffer(MailCenterAjaxImpl.class.getName()).append(
						COLON_WITH_SPACES).append(CALLINGMETHOD).append(
						DataUtil.getCallingMethod()).append(CURRENTMETHOD)
						.append(DataUtil.getCurrentMethod()).append(DASHES)
						.append(ENDED).append(DASHES).append(SESSIONID).append(
								request.getSession().getId()).toString(),
				Level.INFO);
	}

	/**
	 * Gets the header for the messages in new messages, incoming challenges,
	 * Friends request, Inbox, World gaming messages, Archived messages.
	 *
	 * @param request
	 *            the request object gets the session values from.
	 * @param messageId
	 *            the id of the message to get header for the message.
	 * @param playerId
	 *            the id of the player for whom to get message.
	 * @param reciepient
	 *            the recipient of the message.
	 * @param iconImage
	 *            the icon image for the message header for the type of the
	 *            message.
	 * @param messageTypeString
	 *            the messagetypestring defines type of the message.
	 * @param datetime
	 *            the datetime to display in the header.
	 * @param message
	 *            the subject of the message.
	 * @param messageStatus
	 *            MessageStatus can be: Read, Unread, Deleted, Archived, null
	 *
	 * @return the header of the message.
	 */
	private static String getHead(HttpServletRequest request, String messageId,
			int playerId, String reciepient, String iconImage,
			String messageTypeString, String datetime, String message,
			String messageStatus, String body) {

		try {
			String path = DataUtil.getLocalBasePath(request) + G4GConstants.WEBCONTENT_NAME + G4GConstants.SLASH + G4GConstants.AJAX + G4GConstants.SLASH + "messageHead.jsp?" + G4GConstants.ID + G4GConstants.EQUALS + messageId.trim() + G4GConstants.AMBERSAND + G4GConstants.PLAYERID + G4GConstants.EQUALS + playerId + G4GConstants.AMBERSAND + G4GConstants.RECIPIENT_PLAYER + G4GConstants.EQUALS + URLEncoder.encode(reciepient, G4GConstants.UTF8) + G4GConstants.AMBERSAND + G4GConstants.IMGSRC + G4GConstants.EQUALS + URLEncoder.encode(iconImage, G4GConstants.UTF8) + G4GConstants.AMBERSAND + G4GConstants.MESSAGE_TYPE + G4GConstants.EQUALS + URLEncoder.encode(messageTypeString, G4GConstants.UTF8) + G4GConstants.AMBERSAND + G4GConstants.DATE + G4GConstants.EQUALS + URLEncoder.encode(datetime, G4GConstants.UTF8) + G4GConstants.AMBERSAND + G4GConstants.MESSAGE + G4GConstants.EQUALS + URLEncoder.encode(message, G4GConstants.UTF8) + G4GConstants.AMBERSAND + G4GConstants.MESSAGE_STATUS + G4GConstants.EQUALS + URLEncoder.encode(messageStatus, G4GConstants.UTF8) + G4GConstants.AMBERSAND + G4GConstants.BODY + G4GConstants.EQUALS + URLEncoder.encode(body, G4GConstants.UTF8);
			String content = DataUtil.getURLContent(path);
			return content;
		} catch (UnsupportedEncodingException exception) {
			exception.printStackTrace();
		} catch (Exception exception) {
			exception.printStackTrace();
		}

		return G4GConstants.NONE;
	}
}