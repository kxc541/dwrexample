/**********************************************************
 * className.java :
 *
 * Created by
 * Last modified Date: 6 Jun .08 by Punam
 * Revision: 0.1
 * Version : 0.3.4076
 * Copyright (c) 2008 - 2008 Askdigi, All rights reserved.
 **********************************************************/
package com.g4g.services;

import static com.g4g.utils.G4GConstants.COLON_WITH_SPACES;
import static com.g4g.utils.G4GConstants.CALLINGMETHOD;
import static com.g4g.utils.G4GConstants.CURRENTMETHOD;
import static com.g4g.utils.G4GConstants.DASHES;
import static com.g4g.utils.G4GConstants.ENDED;
import static com.g4g.utils.G4GConstants.IN_INSTANCE_OF_CASHCONFIRM_NOTIFICATION;
import static com.g4g.utils.G4GConstants.IN_INSTANCE_OF_CHALLENGERESULT_NOTIFICATION;
import static com.g4g.utils.G4GConstants.IN_INSTANCE_OF_CHALLENGE_NOTIFICATION;
import static com.g4g.utils.G4GConstants.IN_INSTANCE_OF_CLAN_NOTIFICATION;
import static com.g4g.utils.G4GConstants.IN_INSTANCE_OF_FRIENDREQUEST_NOTIFICATION;
import static com.g4g.utils.G4GConstants.IN_INSTANCE_OF_MATCHCONFIRM_NOTIFICATION;
import static com.g4g.utils.G4GConstants.IN_INSTANCE_OF_MATCHRESULT_NOTIFICATION;
import static com.g4g.utils.G4GConstants.IN_INSTANCE_OF_MESSAGE_NOTIFICATION;
import static com.g4g.utils.G4GConstants.IN_INSTANCE_OF_TOURNAMENTJOIN_NOTIFICATION;
import static com.g4g.utils.G4GConstants.IN_INSTANCE_OF_TOURNAMENTRESULT_NOTIFICATION;
import static com.g4g.utils.G4GConstants.IN_INSTANCE_OF_UPCOMINGMATCH_NOTIFICATION;
import static com.g4g.utils.G4GConstants.IN_INSTANCE_OF_WORLDGAMING_NOTIFICATION;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.ResourceBundle;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Level;
import org.hibernate.HibernateException;

import com.g4g.delegator.AdminNotificationServiceDelegator;
import com.g4g.delegator.MessageServiceDelegator;
import com.g4g.delegator.NotificationQueueServiceDelegator;
import com.g4g.delegator.PlayerServiceDelegator;
import com.g4g.dto.AdminnotificationDTO;
import com.g4g.dto.MessageDTO;
import com.g4g.dto.NotificationqueueDTO;
import com.g4g.dto.NotificationtypeDTO;
import com.g4g.dto.PlayerDTO;
import com.g4g.dto.SearchCriteria;
import com.g4g.dto.SkinDTO;
import com.g4g.dto.TwoplayermatchDTO;
import com.g4g.notification.BaseNotification;
import com.g4g.notification.CashConfirmNotification;
import com.g4g.notification.ChallengeNotification;
import com.g4g.notification.ChallengeResultNotification;
import com.g4g.notification.ClanNotification;
import com.g4g.notification.FriendRequestNotification;
import com.g4g.notification.MatchConfirmNotification;
import com.g4g.notification.MatchResultNotification;
import com.g4g.notification.MessageNotification;
import com.g4g.notification.TournamentJoinNotification;
import com.g4g.notification.TournamentResultNotification;
import com.g4g.notification.UpcomingMatchNotification;
import com.g4g.notification.WorldGamingNotification;
import com.g4g.utils.AuditUtil;
import com.g4g.utils.DataUtil;
import com.g4g.utils.G4GConstants;
import com.g4g.utils.MailHelper;
import com.g4g.utils.ResourceBundleUtil;

/**
 * NotificationServiceImpl - contains description for sendNotification method
 * which checks adminnotification table and accordingly send message, mail or
 * popup for that notification.
 *
 * @author pratik
 *
 */

public class NotificationServiceImpl implements NotificationService {

	/**
	 * @see com.g4g.services.NotificationService#sendNotification(com.g4g.notification.BaseNotification,
	 *      javax.servlet.http.HttpServletRequest)
	 */

	public void sendNotification(BaseNotification notification,
			HttpServletRequest request) throws Exception {
		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,
                new StringBuffer(this.getClass().getName()).append(
                        G4GConstants.COLON_WITH_SPACES).append(G4GConstants.CALLINGMETHOD)
                        .append(DataUtil.getCallingMethod()).append(
                        G4GConstants.CURRENTMETHOD).append(DataUtil.getCurrentMethod()).append(G4GConstants.DASHES).append(
								G4GConstants.STARTED + G4GConstants.DASHES)
						.append(G4GConstants.PARAMETERS).append(
								G4GConstants.BASENOTIFICATION
										+ G4GConstants.COLON_WITH_SPACES).append(
								G4GConstants.HTTPSERVLETREQUEST).toString(),
				Level.INFO);
		ResourceBundle resourceBundle = ResourceBundleUtil
				.getApplicationProperties();
		// Sends popup, e-mail, or message for type messageNotification.

		if (notification instanceof MessageNotification) {
			MessageNotification messageNotification = (MessageNotification) notification;
			sendMessageNotification(messageNotification, request);
		}
		// Sends popup, e-mail, or message for type ClanNotification.
		else if (notification instanceof ClanNotification) {
			ClanNotification clanNotification = (ClanNotification) notification;
			sendClanNotification(clanNotification, resourceBundle, request);
		}// Sends popup, e-mail, or message for type ChallengeNotification.
		else if (notification instanceof ChallengeNotification) {
			ChallengeNotification challengeNotification = (ChallengeNotification) notification;
			sendChallengeNotification(challengeNotification, resourceBundle,
					request);
		}// Sends popup, e-mail, or message for type
		// ChallengeResultNotification.
		else if (notification instanceof ChallengeResultNotification) {
			ChallengeResultNotification challengeResultNotification = (ChallengeResultNotification) notification;
			sendChallengeResultNotification(challengeResultNotification,
					resourceBundle, request);
		}// Sends popup, e-mail, or message for type Friend Request
		// Notification.
		else if (notification instanceof FriendRequestNotification) {
			FriendRequestNotification friendRequestNotification = (FriendRequestNotification) notification;
			sendFriendRequestNotification(friendRequestNotification,
					resourceBundle, request);
		}// Sends popup, e-mail, or message for type World Gaming
		// Notification.
		else if (notification instanceof WorldGamingNotification) {
			WorldGamingNotification worldGamingNotification = (WorldGamingNotification) notification;
			sendWorldGamingNotification(worldGamingNotification,
					resourceBundle, request);
		}// Sends popup, e-mail, or message for type Match Confirm
		// Notification.
		else if (notification instanceof MatchConfirmNotification) {
			MatchConfirmNotification matchConfirmNotification = (MatchConfirmNotification) notification;
			sendMatchConfirmNotification(matchConfirmNotification,
					resourceBundle, request);
		}// Sends popup, e-mail, or message for type Tournament Join
		// Notification.
		else if (notification instanceof TournamentJoinNotification) {
			TournamentJoinNotification tournamentJoinNotification = (TournamentJoinNotification) notification;
			sendTournamentJoinNotification(tournamentJoinNotification,
					resourceBundle, request);
		}// Sends popup, e-mail, or message for type Cash Confirm
		// Notification.
		else if (notification instanceof CashConfirmNotification) {
			CashConfirmNotification cashConfirmNotification = (CashConfirmNotification) notification;
			sendCashConfirmNotification(cashConfirmNotification,
					resourceBundle, request);
		}// Sends popup, e-mail, or message for type Match Result
		// Notification.
		else if (notification instanceof MatchResultNotification) {
			MatchResultNotification matchResultNotification = (MatchResultNotification) notification;
			sendMatchResultNotification(matchResultNotification,
					resourceBundle, request);
		}// Sends popup, e-mail, or message for type Tournamet Result
		// Notification.
		else if (notification instanceof TournamentResultNotification) {
			TournamentResultNotification tournamentResultNotification = (TournamentResultNotification) notification;
			sendTournamentResultNotification(tournamentResultNotification,
					resourceBundle, request);

		}// Sends popup, e-mail, or message for type Upcoming Match
		// Notification.
		else if (notification instanceof UpcomingMatchNotification) {
			UpcomingMatchNotification upcomingMatchNotification = (UpcomingMatchNotification) notification;
			sendUpcomingMatchNotification(upcomingMatchNotification,
					resourceBundle, request);

		}
		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,
				new StringBuffer(this.getClass().getName()).append(COLON_WITH_SPACES)
						.append(CALLINGMETHOD).append(
								DataUtil.getCallingMethod()).append(
								CURRENTMETHOD).append(
								DataUtil.getCurrentMethod()).append(DASHES)
						.append(ENDED).toString(), Level.INFO);
	}

	/**
	 *
	 * @param messageBody -
	 *            message body of popup
	 * @param notificationType -
	 *            one of the type of notification from 12 types
	 * @param recipientplayerId
	 * @param sourceplayerId
	 */
	private void addPopup(String messageBody, int notificationType,
			int recipientplayerId, int sourceplayerId, Date expiryDate) {
		NotificationqueueDTO notificationqueueDTO = new NotificationqueueDTO();
		notificationqueueDTO.setBody(messageBody);
		notificationqueueDTO.setCreationdate(DataUtil.todayGMT());
		if (expiryDate != null) {
			notificationqueueDTO.setExpiredate(expiryDate);
		} else {
			notificationqueueDTO.setExpiredate(null);
		}
		notificationqueueDTO.setNotificationtype(new NotificationtypeDTO(
				notificationType));
		notificationqueueDTO.setRecipientplayer(PlayerServiceDelegator
				.getPlayer(recipientplayerId));
		notificationqueueDTO.setSourceplayer(PlayerServiceDelegator
				.getPlayer(sourceplayerId));
		notificationqueueDTO.setViewdate(null);
		NotificationQueueServiceDelegator.add(notificationqueueDTO);
	}

	/**
	 *
	 * @param messageBody -
	 *            message body of popup
	 * @param notificationType -
	 *            one of the type of notification from 12 types
	 * @param toPlayerId
	 * @param fromPlayerId
	 * @param matchId -
	 *            if there is no match, pass -1
	 * @param skinId
	 * @param subject
	 * @param randomId
	 */
	private void addMessage(String messageBody, int notificationType,
			int toPlayerId, int fromPlayerId, int matchId, String skinId,
			String subject, String randomId) {
		MessageDTO messageDTO = new MessageDTO();
		messageDTO.setBody(messageBody);
		messageDTO.setCreateddate(DataUtil.todayGMT());
		messageDTO.setPlayerByFromplayerid(fromPlayerId != 0 ? new PlayerDTO(
				fromPlayerId) : null);
		messageDTO.setIsarchievedbyreciever(false);
		messageDTO.setIsarchivedbysender(false);
		messageDTO.setIsdeletedbyreciever(false);
		messageDTO.setIsdeletedbysender(false);
		messageDTO.setIsread(false);
		if (matchId <= G4GConstants.ZERO) {
			messageDTO.setMatchid(null);
		} else {
			messageDTO.setMatchid(new TwoplayermatchDTO(matchId));
		}
		messageDTO
				.setNotificationtype(new NotificationtypeDTO(notificationType));
		messageDTO.setSkin(new SkinDTO(skinId));
		messageDTO.setSubject(subject);
		messageDTO.setPlayerByToplayerid(new PlayerDTO(toPlayerId));
		messageDTO.setRandomid(randomId);
		MessageServiceDelegator.add(messageDTO);
	}

	/**
	 *
	 * @param skinId
	 * @param notificationKindName =
	 *            message, popup, email
	 * @param notificationFileName =
	 *            file name for notification
	 * @param encryptedToId
	 * @param encryptedFromId
	 * @param randomId
	 * @param request
	 * @return
	 */
	private StringBuffer generateURL(String skinId,
			String notificationKindName, String notificationFileName,
			String encryptedToId, String encryptedFromId, String randomId,
			HttpServletRequest request) {

		StringBuffer siteURL = new StringBuffer();
		siteURL.append(DataUtil.getLocalBasePath(request));
		siteURL.append(G4GConstants.WEBCONTENT_NAME);
		siteURL.append(G4GConstants.SLASH);
		siteURL.append(skinId);
		siteURL.append(G4GConstants.SLASH);
		siteURL.append(G4GConstants.NOTIFICATION_NAME);
		siteURL.append(G4GConstants.SLASH);
		siteURL.append(notificationKindName);
		siteURL.append(G4GConstants.SLASH);
		siteURL.append(notificationFileName);
		siteURL.append(G4GConstants.QUESTIONMARK);
		siteURL.append(G4GConstants.TOID);
		siteURL.append(G4GConstants.EQUALS);
		siteURL.append(encryptedToId);
		siteURL.append(G4GConstants.AMBERSAND);
		siteURL.append(G4GConstants.FROMID);
		siteURL.append(G4GConstants.EQUALS);
		siteURL.append(encryptedFromId);
		if (randomId != null) {
			siteURL.append(G4GConstants.AMBERSAND);
			siteURL.append(G4GConstants.RANDOMID);
			siteURL.append(G4GConstants.EQUALS);
			siteURL.append(randomId);
		}

		return siteURL;
	}

	private void sendMessageNotification(
			MessageNotification messageNotification, HttpServletRequest request)
			throws HibernateException {
		String messageBody = G4GConstants.BLANK;
		AuditUtil
				.getInstance()
				.writeLog(
						AuditUtil.FILE_TYPE_G4G,
						new StringBuffer(this.getClass().getName()).append(
								G4GConstants.COLON_WITH_SPACES).append(
								G4GConstants.CALLINGMETHOD).append(
								DataUtil.getCallingMethod()).append(
								G4GConstants.CURRENTMETHOD).append(
								DataUtil.getCurrentMethod()).append(
								IN_INSTANCE_OF_MESSAGE_NOTIFICATION).toString(),
						Level.INFO);

		String subject = G4GConstants.BLANK;
		String body = G4GConstants.BLANK;
		try {
			subject = URLEncoder.encode(messageNotification.getSubject(),
					G4GConstants.UTF8);
			body = URLEncoder.encode(messageNotification.getBody(),
					G4GConstants.UTF8);

			AuditUtil.getInstance().writeLog(
					AuditUtil.FILE_TYPE_G4G,
                    new StringBuffer(this.getClass().getName()).append(
                            G4GConstants.COLON_WITH_SPACES).append(
                            G4GConstants.CALLINGMETHOD).append(
                            DataUtil.getCallingMethod()).append(
                            G4GConstants.CURRENTMETHOD).append(DataUtil.getCurrentMethod()).append(G4GConstants.DASHES)
							.append(G4GConstants.RECORD_ADDED).toString(),
					Level.INFO);
		} catch (Exception e) {
			AuditUtil.getInstance().writeLog(
					AuditUtil.FILE_TYPE_HIBERNATE,
					new StringBuffer(this.getClass().getName()).append(COLON_WITH_SPACES)
							.append(CALLINGMETHOD).append(
									DataUtil.getCallingMethod()).append(
									CURRENTMETHOD).append(
									DataUtil.getCurrentMethod()).append(DASHES)
							.append(e.getMessage()).toString(), Level.ERROR);
			AuditUtil.getInstance().writeLog(
					AuditUtil.FILE_TYPE_G4G,
					new StringBuffer(this.getClass().getName()).append(COLON_WITH_SPACES)
							.append(CALLINGMETHOD).append(
									DataUtil.getCallingMethod()).append(
									CURRENTMETHOD).append(
									DataUtil.getCurrentMethod()).append(DASHES)
							.append(e.getMessage()).toString(), Level.ERROR);
		}

		SearchCriteria searchCriteria = new SearchCriteria();
		searchCriteria.setAttribute(G4GConstants.NOTIFICATIONTYPE,
				new NotificationtypeDTO(G4GConstants.MSG_MESSAGE));
		List<AdminnotificationDTO> adminNotificationList = AdminNotificationServiceDelegator
				.getList(searchCriteria);
		AdminnotificationDTO adminnotificationDTO = adminNotificationList
				.get(0);

		// Code to find to and from players
		PlayerDTO toPlayerDTO = PlayerServiceDelegator
				.getPlayer(messageNotification.getTo());
		PlayerDTO fromPlayerDTO = PlayerServiceDelegator
				.getPlayer(messageNotification.getFrom());

		String encryptedToId = DataUtil.encrypt(String
				.valueOf(messageNotification.getTo()));
		String encryptedFromId = DataUtil.encrypt(String
				.valueOf(messageNotification.getFrom()));
		Random random = new Random();
		String randomId = DataUtil.todayGMT().getTime()
				+ String.valueOf(random.nextInt());
		// Sends mail if true
		if (adminnotificationDTO.isEmail()) {
			MailHelper mailHelper = new MailHelper();
			try {
				mailHelper
						.sendMail(toPlayerDTO.getEmailaddress(), fromPlayerDTO
								.getEmailaddress(), messageNotification
								.getSubject(), messageNotification.getSubject());
			} catch (IOException ioe) {
				AuditUtil.getInstance().writeLog(AuditUtil.FILE_TYPE_G4G, ioe);
			} catch (MessagingException me) {
				AuditUtil.getInstance().writeLog(AuditUtil.FILE_TYPE_G4G, me);
			}
		}// Sends message if true
		if (adminnotificationDTO.isMessage()) {

			StringBuffer siteURL = generateURL(toPlayerDTO.getSkin()
					.getSkinid(), G4GConstants.NOTIFICATION_MESSAGE_NAME,
					G4GConstants.NOTIFICATION_MESSAGE_FILE_NAME, encryptedToId,
					encryptedFromId, randomId, request);
			siteURL.append(G4GConstants.AMBERSAND);
			siteURL.append(G4GConstants.SUBJECT);
			siteURL.append(G4GConstants.EQUALS);
			siteURL.append(subject);
			siteURL.append(G4GConstants.AMBERSAND);
			siteURL.append(G4GConstants.BODY);
			siteURL.append(G4GConstants.EQUALS);
			siteURL.append(body);

			StringBuffer defaultURL = generateURL(G4GConstants.DEFAULT_SITE_ID,
					G4GConstants.NOTIFICATION_MESSAGE_NAME,
					G4GConstants.NOTIFICATION_MESSAGE_FILE_NAME, encryptedToId,
					encryptedFromId, randomId, request);
			defaultURL.append(G4GConstants.AMBERSAND);
			defaultURL.append(G4GConstants.SUBJECT);
			defaultURL.append(G4GConstants.EQUALS);
			defaultURL.append(subject);
			defaultURL.append(G4GConstants.AMBERSAND);
			defaultURL.append(G4GConstants.BODY);
			defaultURL.append(G4GConstants.EQUALS);
			defaultURL.append(body);

			if (DataUtil.isURLAvailable(siteURL.toString())) {
				messageBody = DataUtil.getURLContent(siteURL.toString());
			} else if (DataUtil.isURLAvailable(defaultURL.toString())) {
				messageBody = DataUtil.getURLContent(defaultURL.toString());
			} else {
				messageBody = messageNotification.getBody();
			}

			addMessage(messageBody, G4GConstants.MSG_MESSAGE,
					messageNotification.getTo(), messageNotification.getFrom(),
					G4GConstants.ONE_NEGATIVE, toPlayerDTO.getSkin()
							.getSkinid(), messageNotification.getSubject(),
					randomId);

		}// Sends popup if true
		if (adminnotificationDTO.isPopup()) {
			// Code to make entry in NotificationQueue table

			StringBuffer siteURL = generateURL(toPlayerDTO.getSkin()
					.getSkinid(), G4GConstants.NOTIFICATION_POPUP_NAME,
					G4GConstants.NOTIFICATION_MESSAGE_FILE_NAME, encryptedToId,
					encryptedFromId, randomId, request);
			StringBuffer defaultURL = generateURL(G4GConstants.DEFAULT_SITE_ID,
					G4GConstants.NOTIFICATION_POPUP_NAME,
					G4GConstants.NOTIFICATION_MESSAGE_FILE_NAME, encryptedToId,
					encryptedFromId, randomId, request);

			if (DataUtil.isURLAvailable(siteURL.toString())) {
				messageBody = DataUtil.getURLContent(siteURL.toString());
			} else if (DataUtil.isURLAvailable(defaultURL.toString())) {
				messageBody = DataUtil.getURLContent(defaultURL.toString());
			} else {
				messageBody = messageNotification.getBody();
			}

			addPopup(messageBody, G4GConstants.MSG_MESSAGE, messageNotification
					.getTo(), messageNotification.getFrom(), null);
		}
	}

	private void sendClanNotification(ClanNotification clanNotification,
			ResourceBundle resourceBundle, HttpServletRequest request) {
		String messageBody = G4GConstants.BLANK;
		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,
				new StringBuffer(this.getClass().getName()).append(
						G4GConstants.COLON_WITH_SPACES).append(G4GConstants.CALLINGMETHOD)
						.append(DataUtil.getCallingMethod()).append(
								G4GConstants.CURRENTMETHOD).append(
								DataUtil.getCurrentMethod()).append(
								IN_INSTANCE_OF_CLAN_NOTIFICATION).toString(),
				Level.INFO);

		SearchCriteria searchCriteria = new SearchCriteria();
		searchCriteria.setAttribute(G4GConstants.NOTIFICATIONTYPE,
				new NotificationtypeDTO(G4GConstants.MSG_CLAN));
		List<AdminnotificationDTO> adminNotificationList = AdminNotificationServiceDelegator
				.getList(searchCriteria);
		AdminnotificationDTO adminnotificationDTO = adminNotificationList
				.get(0);

		// Code to find to and from players
		PlayerDTO toPlayerDTO = PlayerServiceDelegator
				.getPlayer(clanNotification.getTo());
		PlayerDTO fromPlayerDTO = PlayerServiceDelegator
				.getPlayer(clanNotification.getFrom());

		String encryptedToId = DataUtil.encrypt(String.valueOf(clanNotification
				.getTo()));
		String encryptedFromId = DataUtil.encrypt(String
				.valueOf(clanNotification.getFrom()));
		Random random = new Random();
		String randomId = DataUtil.todayGMT().getTime()
				+ String.valueOf(random.nextInt());
		// Sends email if true.
		if (adminnotificationDTO.isEmail()) {
			MailHelper mailHelper = new MailHelper();

			StringBuffer siteURL = generateURL(toPlayerDTO.getSkin()
					.getSkinid(), G4GConstants.NOTIFICATION_EMAIL_NAME,
					G4GConstants.NOTIFICATION_CLAN_FILE_NAME, encryptedToId,
					encryptedFromId, null, request);
			StringBuffer defaultURL = generateURL(G4GConstants.DEFAULT_SITE_ID,
					G4GConstants.NOTIFICATION_EMAIL_NAME,
					G4GConstants.NOTIFICATION_CLAN_FILE_NAME, encryptedToId,
					encryptedFromId, null, request);

			if (DataUtil.isURLAvailable(siteURL.toString())) {
				messageBody = DataUtil.getURLContent(siteURL.toString());
			} else if (DataUtil.isURLAvailable(defaultURL.toString())) {
				messageBody = DataUtil.getURLContent(defaultURL.toString());
			} else {
				messageBody = resourceBundle
						.getString(G4GConstants.NOTIFICATION_CLAN_EMAIL)
						+ fromPlayerDTO.getScreenname();
			}

			try {
				mailHelper.sendMail(toPlayerDTO.getEmailaddress(),
						fromPlayerDTO.getEmailaddress(), clanNotification
								.getSubject(), messageBody);
			} catch (IOException ioe) {
				AuditUtil.getInstance().writeLog(AuditUtil.FILE_TYPE_G4G, ioe);
			} catch (MessagingException me) {
				AuditUtil.getInstance().writeLog(AuditUtil.FILE_TYPE_G4G, me);
			}
		}// Sends message if true
		if (adminnotificationDTO.isMessage()) {

			StringBuffer siteURL = generateURL(toPlayerDTO.getSkin()
					.getSkinid(), G4GConstants.NOTIFICATION_MESSAGE_NAME,
					G4GConstants.NOTIFICATION_CLAN_FILE_NAME, encryptedToId,
					encryptedFromId, randomId, request);
			StringBuffer defaultURL = generateURL(G4GConstants.DEFAULT_SITE_ID,
					G4GConstants.NOTIFICATION_MESSAGE_NAME,
					G4GConstants.NOTIFICATION_CLAN_FILE_NAME, encryptedToId,
					encryptedFromId, randomId, request);

			if (DataUtil.isURLAvailable(siteURL.toString())) {
				messageBody = DataUtil.getURLContent(siteURL.toString());
			} else if (DataUtil.isURLAvailable(defaultURL.toString())) {
				messageBody = DataUtil.getURLContent(defaultURL.toString());
			} else {
				messageBody = resourceBundle
						.getString(G4GConstants.NOTIFICATION_CLAN_MESSAGE)
						+ fromPlayerDTO.getScreenname();
			}

			addMessage(messageBody, G4GConstants.MSG_CLAN, clanNotification
					.getTo(), clanNotification.getFrom(),
					G4GConstants.ONE_NEGATIVE, toPlayerDTO.getSkin()
							.getSkinid(), clanNotification.getSubject(),
					randomId);

		}// Sends popup if true.
		if (adminnotificationDTO.isPopup()) {
			// Code to make entry in NotificationQueue table

			StringBuffer siteURL = generateURL(toPlayerDTO.getSkin()
					.getSkinid(), G4GConstants.NOTIFICATION_POPUP_NAME,
					G4GConstants.NOTIFICATION_CLAN_FILE_NAME, encryptedToId,
					encryptedFromId, null, request);
			StringBuffer defaultURL = generateURL(G4GConstants.DEFAULT_SITE_ID,
					G4GConstants.NOTIFICATION_POPUP_NAME,
					G4GConstants.NOTIFICATION_CLAN_FILE_NAME, encryptedToId,
					encryptedFromId, null, request);

			if (DataUtil.isURLAvailable(siteURL.toString())) {
				messageBody = DataUtil.getURLContent(siteURL.toString());
			} else if (DataUtil.isURLAvailable(defaultURL.toString())) {
				messageBody = DataUtil.getURLContent(defaultURL.toString());
			} else {
				messageBody = resourceBundle
						.getString(G4GConstants.NOTIFICATION_CLAN_POPUP)
						+ fromPlayerDTO.getScreenname();
			}

			addPopup(messageBody, G4GConstants.MSG_CLAN, clanNotification
					.getTo(), clanNotification.getFrom(), null);
		}
	}

	private void sendChallengeNotification(
			ChallengeNotification challengeNotification,
			ResourceBundle resourceBundle, HttpServletRequest request) {
		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,
				new StringBuffer(this.getClass().getName()).append(
						G4GConstants.COLON_WITH_SPACES).append(G4GConstants.CALLINGMETHOD)
						.append(DataUtil.getCallingMethod()).append(
								G4GConstants.CURRENTMETHOD).append(
								DataUtil.getCurrentMethod()).append(
								IN_INSTANCE_OF_CHALLENGE_NOTIFICATION)
						.toString(), Level.INFO);
		String messageBody = G4GConstants.BLANK;

		SearchCriteria searchCriteria = new SearchCriteria();
		searchCriteria.setAttribute(G4GConstants.NOTIFICATIONTYPE,
				new NotificationtypeDTO(G4GConstants.MSG_CHALLENGE));
		List<AdminnotificationDTO> adminNotificationList = AdminNotificationServiceDelegator
				.getList(searchCriteria);
		AdminnotificationDTO adminnotificationDTO = adminNotificationList
				.get(0);

		// Code to find to and from players
		PlayerDTO toPlayerDTO = PlayerServiceDelegator
				.getPlayer(challengeNotification.getTo());
		PlayerDTO fromPlayerDTO = PlayerServiceDelegator
				.getPlayer(challengeNotification.getFrom());

		String encryptedToId = DataUtil.encrypt(String
				.valueOf(challengeNotification.getTo()));
		String encryptedFromId = DataUtil.encrypt(String
				.valueOf(challengeNotification.getFrom()));
		Random random = new Random();
		String randomId = DataUtil.todayGMT().getTime()
				+ String.valueOf(random.nextInt());

		// Sends email if true.
		if (adminnotificationDTO.isEmail()) {
			MailHelper mailHelper = new MailHelper();

			StringBuffer siteURL = generateURL(toPlayerDTO.getSkin()
					.getSkinid(), G4GConstants.NOTIFICATION_EMAIL_NAME,
					G4GConstants.NOTIFICATION_CHALLENGE_FILE_NAME,
					encryptedToId, encryptedFromId, null, request);
			siteURL.append(G4GConstants.AMBERSAND);
			siteURL.append(G4GConstants.MATCHID);
			siteURL.append(G4GConstants.EQUALS);
			siteURL.append(challengeNotification.getMatchId());
			StringBuffer defaultURL = generateURL(G4GConstants.DEFAULT_SITE_ID,
					G4GConstants.NOTIFICATION_EMAIL_NAME,
					G4GConstants.NOTIFICATION_CHALLENGE_FILE_NAME,
					encryptedToId, encryptedFromId, null, request);
			defaultURL.append(G4GConstants.AMBERSAND);
			defaultURL.append(G4GConstants.MATCHID);
			defaultURL.append(G4GConstants.EQUALS);
			defaultURL.append(challengeNotification.getMatchId());
			if (DataUtil.isURLAvailable(siteURL.toString())) {
				messageBody = DataUtil.getURLContent(siteURL.toString());
			} else if (DataUtil.isURLAvailable(defaultURL.toString())) {
				messageBody = DataUtil.getURLContent(defaultURL.toString());
			} else {
				messageBody = resourceBundle
						.getString(G4GConstants.NOTIFICATION_CHALLENGE_EMAIL)
						+ fromPlayerDTO.getScreenname();
			}

			try {
				mailHelper.sendMail(toPlayerDTO.getEmailaddress(),
						fromPlayerDTO.getEmailaddress(), challengeNotification
								.getSubject(), messageBody);
			} catch (IOException ioe) {
				AuditUtil.getInstance().writeLog(AuditUtil.FILE_TYPE_G4G, ioe);
			} catch (MessagingException me) {
				AuditUtil.getInstance().writeLog(AuditUtil.FILE_TYPE_G4G, me);
			}
		}// Sends messageif true.
		if (adminnotificationDTO.isMessage()) {

			StringBuffer siteURL = generateURL(toPlayerDTO.getSkin()
					.getSkinid(), G4GConstants.NOTIFICATION_MESSAGE_NAME,
					G4GConstants.NOTIFICATION_CHALLENGE_FILE_NAME,
					encryptedToId, encryptedFromId, randomId, request);
			siteURL.append(G4GConstants.AMBERSAND);
			siteURL.append(G4GConstants.MATCHID);
			siteURL.append(G4GConstants.EQUALS);
			siteURL.append(challengeNotification.getMatchId());
			StringBuffer defaultURL = generateURL(G4GConstants.DEFAULT_SITE_ID,
					G4GConstants.NOTIFICATION_MESSAGE_NAME,
					G4GConstants.NOTIFICATION_CHALLENGE_FILE_NAME,
					encryptedToId, encryptedFromId, randomId, request);
			defaultURL.append(G4GConstants.AMBERSAND);
			defaultURL.append(G4GConstants.MATCHID);
			defaultURL.append(G4GConstants.EQUALS);
			defaultURL.append(challengeNotification.getMatchId());
			if (DataUtil.isURLAvailable(siteURL.toString())) {
				messageBody = DataUtil.getURLContent(siteURL.toString());
			} else if (DataUtil.isURLAvailable(defaultURL.toString())) {
				messageBody = DataUtil.getURLContent(defaultURL.toString());
			} else {
				messageBody = resourceBundle
						.getString(G4GConstants.NOTIFICATION_CHALLENGE_MESSAGE)
						+ fromPlayerDTO.getScreenname();
			}

			addMessage(messageBody, G4GConstants.MSG_CHALLENGE,
					challengeNotification.getTo(), challengeNotification
							.getFrom(), challengeNotification.getMatchId(),
					toPlayerDTO.getSkin().getSkinid(), challengeNotification
							.getSubject(), randomId);

		}// Sends popup if true.
		if (adminnotificationDTO.isPopup()) {
			// Code to make entry in NotificationQueue table

			StringBuffer siteURL = generateURL(toPlayerDTO.getSkin()
					.getSkinid(), G4GConstants.NOTIFICATION_POPUP_NAME,
					G4GConstants.NOTIFICATION_CHALLENGE_FILE_NAME,
					encryptedToId, encryptedFromId, randomId, request);
			siteURL.append(G4GConstants.AMBERSAND);
			siteURL.append(G4GConstants.MATCHID);
			siteURL.append(G4GConstants.EQUALS);
			siteURL.append(challengeNotification.getMatchId());
			StringBuffer defaultURL = generateURL(G4GConstants.DEFAULT_SITE_ID,
					G4GConstants.NOTIFICATION_POPUP_NAME,
					G4GConstants.NOTIFICATION_CHALLENGE_FILE_NAME,
					encryptedToId, encryptedFromId, randomId, request);
			defaultURL.append(G4GConstants.AMBERSAND);
			defaultURL.append(G4GConstants.MATCHID);
			defaultURL.append(G4GConstants.EQUALS);
			defaultURL.append(challengeNotification.getMatchId());

			if (DataUtil.isURLAvailable(siteURL.toString())) {
				messageBody = DataUtil.getURLContent(siteURL.toString());
			} else if (DataUtil.isURLAvailable(defaultURL.toString())) {
				messageBody = DataUtil.getURLContent(defaultURL.toString());
			} else {
				messageBody = resourceBundle
						.getString(G4GConstants.NOTIFICATION_CHALLENGE_POPUP)
						+ fromPlayerDTO.getScreenname();
			}

			addPopup(messageBody, G4GConstants.MSG_CHALLENGE,
					challengeNotification.getTo(), challengeNotification
							.getFrom(), challengeNotification.getScheduleDate());
		}
	}

	private void sendChallengeResultNotification(
			ChallengeResultNotification challengeResultNotification,
			ResourceBundle resourceBundle, HttpServletRequest request) {
		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,
				new StringBuffer(this.getClass().getName()).append(
						G4GConstants.COLON_WITH_SPACES).append(G4GConstants.CALLINGMETHOD)
						.append(DataUtil.getCallingMethod()).append(
								G4GConstants.CURRENTMETHOD).append(
								DataUtil.getCurrentMethod()).append(
								IN_INSTANCE_OF_CHALLENGERESULT_NOTIFICATION)
						.toString(), Level.INFO);
		String messageBody = G4GConstants.BLANK;

		SearchCriteria searchCriteria = new SearchCriteria();
		searchCriteria.setAttribute(G4GConstants.NOTIFICATIONTYPE,
				new NotificationtypeDTO(G4GConstants.MSG_CHALLENGE_RESULT));
		List<AdminnotificationDTO> adminNotificationList = AdminNotificationServiceDelegator
				.getList(searchCriteria);
		AdminnotificationDTO adminnotificationDTO = adminNotificationList
				.get(0);

		// Code to find to and from players
		PlayerDTO toPlayerDTO = new PlayerDTO(challengeResultNotification
				.getTo());
		PlayerDTO fromPlayerDTO = new PlayerDTO(challengeResultNotification
				.getFrom());
		String encryptedToId = DataUtil.encrypt(String
				.valueOf(challengeResultNotification.getTo()));
		String encryptedFromId = DataUtil.encrypt(String
				.valueOf(challengeResultNotification.getFrom()));
		Random random = new Random();
		String randomId = DataUtil.todayGMT().getTime()
				+ String.valueOf(random.nextInt());
		// Sends mail if true.
		if (adminnotificationDTO.isEmail()) {
			MailHelper mailHelper = new MailHelper();
			StringBuffer siteURL = generateURL(toPlayerDTO.getSkin()
					.getSkinid(), G4GConstants.NOTIFICATION_EMAIL_NAME,
					G4GConstants.NOTIFICATION_CHALLENGE_RESULT_FILE_NAME,
					encryptedToId, encryptedFromId, null, request);
			siteURL.append(G4GConstants.AMBERSAND);
			siteURL.append(G4GConstants.MATCHID);
			siteURL.append(G4GConstants.EQUALS);
			siteURL.append(challengeResultNotification.getMatchId());
			siteURL.append(G4GConstants.AMBERSAND);
			siteURL.append(G4GConstants.TOURNAMENTID);
			siteURL.append(G4GConstants.EQUALS);
			siteURL.append(challengeResultNotification.getTournamentId());
			StringBuffer defaultURL = generateURL(G4GConstants.DEFAULT_SITE_ID,
					G4GConstants.NOTIFICATION_EMAIL_NAME,
					G4GConstants.NOTIFICATION_CHALLENGE_RESULT_FILE_NAME,
					encryptedToId, encryptedFromId, null, request);
			defaultURL.append(G4GConstants.AMBERSAND);
			defaultURL.append(G4GConstants.MATCHID);
			defaultURL.append(G4GConstants.EQUALS);
			defaultURL.append(challengeResultNotification.getMatchId());
			defaultURL.append(G4GConstants.AMBERSAND);
			defaultURL.append(G4GConstants.TOURNAMENTID);
			defaultURL.append(G4GConstants.EQUALS);
			defaultURL.append(challengeResultNotification.getTournamentId());

			if (DataUtil.isURLAvailable(siteURL.toString())) {
				messageBody = DataUtil.getURLContent(siteURL.toString());
			} else if (DataUtil.isURLAvailable(defaultURL.toString())) {
				messageBody = DataUtil.getURLContent(defaultURL.toString());
			} else {
				messageBody = resourceBundle
						.getString(G4GConstants.NOTIFICATION_CHALLENGERESULT_EMAIL)
						+ fromPlayerDTO.getScreenname();
			}

			try {
				mailHelper.sendMail(toPlayerDTO.getEmailaddress(),
						fromPlayerDTO.getEmailaddress(),
						challengeResultNotification.getSubject(), messageBody);
			} catch (IOException ioe) {
				AuditUtil.getInstance().writeLog(AuditUtil.FILE_TYPE_G4G, ioe);
			} catch (MessagingException me) {
				AuditUtil.getInstance().writeLog(AuditUtil.FILE_TYPE_G4G, me);
			}
		}// Sends message if true.
		if (adminnotificationDTO.isMessage()) {

			StringBuffer siteURL = generateURL(toPlayerDTO.getSkin()
					.getSkinid(), G4GConstants.NOTIFICATION_MESSAGE_NAME,
					G4GConstants.NOTIFICATION_CHALLENGE_RESULT_FILE_NAME,
					encryptedToId, encryptedFromId, randomId, request);
			siteURL.append(G4GConstants.AMBERSAND);
			siteURL.append(G4GConstants.MATCHID);
			siteURL.append(G4GConstants.EQUALS);
			siteURL.append(challengeResultNotification.getMatchId());
			siteURL.append(G4GConstants.AMBERSAND);
			siteURL.append(G4GConstants.TOURNAMENTID);
			siteURL.append(G4GConstants.EQUALS);
			siteURL.append(challengeResultNotification.getTournamentId());
			StringBuffer defaultURL = generateURL(G4GConstants.DEFAULT_SITE_ID,
					G4GConstants.NOTIFICATION_MESSAGE_NAME,
					G4GConstants.NOTIFICATION_CHALLENGE_RESULT_FILE_NAME,
					encryptedToId, encryptedFromId, randomId, request);
			defaultURL.append(G4GConstants.AMBERSAND);
			defaultURL.append(G4GConstants.MATCHID);
			defaultURL.append(G4GConstants.EQUALS);
			defaultURL.append(challengeResultNotification.getMatchId());
			defaultURL.append(G4GConstants.AMBERSAND);
			defaultURL.append(G4GConstants.TOURNAMENTID);
			defaultURL.append(G4GConstants.EQUALS);
			defaultURL.append(challengeResultNotification.getTournamentId());

			if (DataUtil.isURLAvailable(siteURL.toString())) {
				messageBody = DataUtil.getURLContent(siteURL.toString());
			} else if (DataUtil.isURLAvailable(defaultURL.toString())) {
				messageBody = DataUtil.getURLContent(defaultURL.toString());
			} else {
				messageBody = resourceBundle
						.getString(G4GConstants.NOTIFICATION_CHALLENGERESULT_MESSAGE)
						+ fromPlayerDTO.getScreenname();
			}

			addMessage(messageBody, G4GConstants.MSG_CHALLENGE_RESULT,
					challengeResultNotification.getTo(),
					challengeResultNotification.getFrom(),
					challengeResultNotification.getMatchId(), toPlayerDTO
							.getSkin().getSkinid(), challengeResultNotification
							.getSubject(), randomId);
		}// sends popup if true.
		if (adminnotificationDTO.isPopup()) {
			// Code to make entry in NotificationQueue table

			StringBuffer siteURL = generateURL(toPlayerDTO.getSkin()
					.getSkinid(), G4GConstants.NOTIFICATION_POPUP_NAME,
					G4GConstants.NOTIFICATION_CHALLENGE_RESULT_FILE_NAME,
					encryptedToId, encryptedFromId, null, request);
			siteURL.append(G4GConstants.AMBERSAND);
			siteURL.append(G4GConstants.MATCHID);
			siteURL.append(G4GConstants.EQUALS);
			siteURL.append(challengeResultNotification.getMatchId());
			siteURL.append(G4GConstants.AMBERSAND);
			siteURL.append(G4GConstants.TOURNAMENTID);
			siteURL.append(G4GConstants.EQUALS);
			siteURL.append(challengeResultNotification.getTournamentId());
			StringBuffer defaultURL = generateURL(G4GConstants.DEFAULT_SITE_ID,
					G4GConstants.NOTIFICATION_POPUP_NAME,
					G4GConstants.NOTIFICATION_CHALLENGE_RESULT_FILE_NAME,
					encryptedToId, encryptedFromId, null, request);
			defaultURL.append(G4GConstants.AMBERSAND);
			defaultURL.append(G4GConstants.MATCHID);
			defaultURL.append(G4GConstants.EQUALS);
			defaultURL.append(challengeResultNotification.getMatchId());
			defaultURL.append(G4GConstants.AMBERSAND);
			defaultURL.append(G4GConstants.TOURNAMENTID);
			defaultURL.append(G4GConstants.EQUALS);
			defaultURL.append(challengeResultNotification.getTournamentId());

			if (DataUtil.isURLAvailable(siteURL.toString())) {
				messageBody = DataUtil.getURLContent(siteURL.toString());
			} else if (DataUtil.isURLAvailable(defaultURL.toString())) {
				messageBody = DataUtil.getURLContent(defaultURL.toString());
			} else {
				messageBody = resourceBundle
						.getString(G4GConstants.NOTIFICATION_CHALLENGERESULT_POPUP)
						+ fromPlayerDTO.getScreenname();
			}

			addPopup(messageBody, G4GConstants.MSG_CHALLENGE_RESULT,
					challengeResultNotification.getTo(),
					challengeResultNotification.getFrom(), null);

		}
	}

	private void sendFriendRequestNotification(
			FriendRequestNotification friendRequestNotification,
			ResourceBundle resourceBundle, HttpServletRequest request) {
		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,
				new StringBuffer(this.getClass().getName()).append(
						G4GConstants.COLON_WITH_SPACES).append(G4GConstants.CALLINGMETHOD)
						.append(DataUtil.getCallingMethod()).append(
								G4GConstants.CURRENTMETHOD).append(
								DataUtil.getCurrentMethod()).append(
								IN_INSTANCE_OF_FRIENDREQUEST_NOTIFICATION)
						.toString(), Level.INFO);
		String messageBody = G4GConstants.BLANK;

		SearchCriteria searchCriteria = new SearchCriteria();
		searchCriteria.setAttribute(G4GConstants.NOTIFICATIONTYPE,
				new NotificationtypeDTO(G4GConstants.MSG_FRIEND_REQUEST));
		List<AdminnotificationDTO> adminNotificationList = AdminNotificationServiceDelegator
				.getList(searchCriteria);
		AdminnotificationDTO adminnotificationDTO = adminNotificationList
				.get(0);

		// Code to find to and from players
		PlayerDTO toPlayerDTO = PlayerServiceDelegator
				.getPlayer(friendRequestNotification.getTo());
		PlayerDTO fromPlayerDTO = PlayerServiceDelegator
				.getPlayer(friendRequestNotification.getFrom());

		String encryptedToId = DataUtil.encrypt(String
				.valueOf(friendRequestNotification.getTo()));
		String encryptedFromId = DataUtil.encrypt(String
				.valueOf(friendRequestNotification.getFrom()));
		Random random = new Random();
		String randomId = DataUtil.todayGMT().getTime()
				+ String.valueOf(random.nextInt());

		// Sends email if true
		if (adminnotificationDTO.isEmail()) {
			MailHelper mailHelper = new MailHelper();

			StringBuffer siteURL = generateURL(toPlayerDTO.getSkin()
					.getSkinid(), G4GConstants.NOTIFICATION_EMAIL_NAME,
					G4GConstants.NOTIFICATION_FRIEND_REQUEST_FILE_NAME,
					encryptedToId, encryptedFromId, null, request);
			StringBuffer defaultURL = generateURL(G4GConstants.DEFAULT_SITE_ID,
					G4GConstants.NOTIFICATION_EMAIL_NAME,
					G4GConstants.NOTIFICATION_FRIEND_REQUEST_FILE_NAME,
					encryptedToId, encryptedFromId, null, request);

			if (DataUtil.isURLAvailable(siteURL.toString())) {
				messageBody = DataUtil.getURLContent(siteURL.toString());
			} else if (DataUtil.isURLAvailable(defaultURL.toString())) {
				messageBody = DataUtil.getURLContent(defaultURL.toString());
			} else {
				messageBody = resourceBundle
						.getString(G4GConstants.NOTIFICATION_FRIENDREQUEST_EMAIL)
						+ fromPlayerDTO.getScreenname();
			}

			try {
				mailHelper.sendMail(toPlayerDTO.getEmailaddress(),
						fromPlayerDTO.getEmailaddress(),
						friendRequestNotification.getSubject(), messageBody);
			} catch (IOException ioe) {
				AuditUtil.getInstance().writeLog(AuditUtil.FILE_TYPE_G4G, ioe);
			} catch (MessagingException me) {
				AuditUtil.getInstance().writeLog(AuditUtil.FILE_TYPE_G4G, me);
			}
		}// sends message if true.
		if (adminnotificationDTO.isMessage()) {

			StringBuffer siteURL = generateURL(toPlayerDTO.getSkin()
					.getSkinid(), G4GConstants.NOTIFICATION_MESSAGE_NAME,
					G4GConstants.NOTIFICATION_FRIEND_REQUEST_FILE_NAME,
					encryptedToId, encryptedFromId, randomId, request);
			StringBuffer defaultURL = generateURL(G4GConstants.DEFAULT_SITE_ID,
					G4GConstants.NOTIFICATION_MESSAGE_NAME,
					G4GConstants.NOTIFICATION_FRIEND_REQUEST_FILE_NAME,
					encryptedToId, encryptedFromId, randomId, request);

			if (DataUtil.isURLAvailable(siteURL.toString())) {
				messageBody = DataUtil.getURLContent(siteURL.toString());
			} else if (DataUtil.isURLAvailable(defaultURL.toString())) {
				messageBody = DataUtil.getURLContent(defaultURL.toString());
			} else {
				messageBody = resourceBundle
						.getString(G4GConstants.NOTIFICATION_FRIENDREQUEST_MESSAGE)
						+ fromPlayerDTO.getScreenname();
			}

			addMessage(messageBody, G4GConstants.MSG_FRIEND_REQUEST,
					friendRequestNotification.getTo(),
					friendRequestNotification.getFrom(),
					G4GConstants.ONE_NEGATIVE, toPlayerDTO.getSkin()
							.getSkinid(), friendRequestNotification
							.getSubject(), randomId);
		}// Sends popup if true.
		if (adminnotificationDTO.isPopup()) {
			// Code to make entry in NotificationQueue table

			StringBuffer siteURL = generateURL(toPlayerDTO.getSkin()
					.getSkinid(), G4GConstants.NOTIFICATION_POPUP_NAME,
					G4GConstants.NOTIFICATION_FRIEND_REQUEST_FILE_NAME,
					encryptedToId, encryptedFromId, null, request);
			StringBuffer defaultURL = generateURL(G4GConstants.DEFAULT_SITE_ID,
					G4GConstants.NOTIFICATION_POPUP_NAME,
					G4GConstants.NOTIFICATION_FRIEND_REQUEST_FILE_NAME,
					encryptedToId, encryptedFromId, null, request);

			if (DataUtil.isURLAvailable(siteURL.toString())) {
				messageBody = DataUtil.getURLContent(siteURL.toString());
			} else if (DataUtil.isURLAvailable(defaultURL.toString())) {
				messageBody = DataUtil.getURLContent(defaultURL.toString());
			} else {
				messageBody = resourceBundle
						.getString(G4GConstants.NOTIFICATION_FRIENDREQUEST_POPUP)
						+ fromPlayerDTO.getScreenname();
			}

			addPopup(messageBody, G4GConstants.MSG_FRIEND_REQUEST,
					friendRequestNotification.getTo(),
					friendRequestNotification.getFrom(), null);

		}
	}

	private void sendWorldGamingNotification(
			WorldGamingNotification worldGamingNotification,
			ResourceBundle resourceBundle, HttpServletRequest request) {
		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,
				new StringBuffer(this.getClass().getName()).append(
						G4GConstants.COLON_WITH_SPACES).append(G4GConstants.CALLINGMETHOD)
						.append(DataUtil.getCallingMethod()).append(
								G4GConstants.CURRENTMETHOD).append(
								DataUtil.getCurrentMethod()).append(
								IN_INSTANCE_OF_WORLDGAMING_NOTIFICATION)
						.toString(), Level.INFO);
		String messageBody = G4GConstants.BLANK;

		SearchCriteria searchCriteria = new SearchCriteria();
		searchCriteria.setAttribute(G4GConstants.NOTIFICATIONTYPE,
				new NotificationtypeDTO(G4GConstants.MSG_WORLD_GAMING));
		List<AdminnotificationDTO> adminNotificationList = AdminNotificationServiceDelegator
				.getList(searchCriteria);
		AdminnotificationDTO adminnotificationDTO = adminNotificationList
				.get(0);

		// Code to find to and from players
		PlayerDTO toPlayerDTO = PlayerServiceDelegator
				.getPlayer(worldGamingNotification.getTo());
		PlayerDTO fromPlayerDTO = PlayerServiceDelegator
				.getPlayer(worldGamingNotification.getFrom());

		String encryptedToId = DataUtil.encrypt(String
				.valueOf(worldGamingNotification.getTo()));
		String encryptedFromId = DataUtil.encrypt(String
				.valueOf(worldGamingNotification.getFrom()));
		Random random = new Random();
		String randomId = DataUtil.todayGMT().getTime()
				+ String.valueOf(random.nextInt());
		// Sends email if true.
		if (adminnotificationDTO.isEmail()) {
			MailHelper mailHelper = new MailHelper();

			StringBuffer siteURL = generateURL(toPlayerDTO.getSkin()
					.getSkinid(), G4GConstants.NOTIFICATION_EMAIL_NAME,
					G4GConstants.NOTIFICATION_WORLD_GAMING_FILE_NAME,
					encryptedToId, encryptedFromId, null, request);
			siteURL.append(G4GConstants.AMBERSAND);
			siteURL.append(G4GConstants.MATCHID);
			siteURL.append(G4GConstants.EQUALS);
			siteURL.append(worldGamingNotification.getMatchId());

			StringBuffer defaultURL = generateURL(G4GConstants.DEFAULT_SITE_ID,
					G4GConstants.NOTIFICATION_EMAIL_NAME,
					G4GConstants.NOTIFICATION_WORLD_GAMING_FILE_NAME,
					encryptedToId, encryptedFromId, null, request);
			defaultURL.append(G4GConstants.AMBERSAND);
			defaultURL.append(G4GConstants.MATCHID);
			defaultURL.append(G4GConstants.EQUALS);
			defaultURL.append(worldGamingNotification.getMatchId());

			if (DataUtil.isURLAvailable(siteURL.toString())) {
				messageBody = DataUtil.getURLContent(siteURL.toString());
			} else if (DataUtil.isURLAvailable(defaultURL.toString())) {
				messageBody = DataUtil.getURLContent(defaultURL.toString());
			} else {
				messageBody = resourceBundle
						.getString(G4GConstants.NOTIFICATION_WORLDGAMING_EMAIL)
						+ fromPlayerDTO.getScreenname();
			}

			try {
				mailHelper.sendMail(toPlayerDTO.getEmailaddress(),
						fromPlayerDTO.getEmailaddress(),
						worldGamingNotification.getSubject(), messageBody);
			} catch (IOException ioe) {
				AuditUtil.getInstance().writeLog(AuditUtil.FILE_TYPE_G4G, ioe);
			} catch (MessagingException me) {
				AuditUtil.getInstance().writeLog(AuditUtil.FILE_TYPE_G4G, me);
			}
		}// Sends message if true.
		if (adminnotificationDTO.isMessage()) {

			StringBuffer siteURL = generateURL(toPlayerDTO.getSkin()
					.getSkinid(), G4GConstants.NOTIFICATION_MESSAGE_NAME,
					G4GConstants.NOTIFICATION_WORLD_GAMING_FILE_NAME,
					encryptedToId, encryptedFromId, randomId, request);
			siteURL.append(G4GConstants.AMBERSAND);
			siteURL.append(G4GConstants.MATCHID);
			siteURL.append(G4GConstants.EQUALS);
			siteURL.append(worldGamingNotification.getMatchId());

			StringBuffer defaultURL = generateURL(G4GConstants.DEFAULT_SITE_ID,
					G4GConstants.NOTIFICATION_MESSAGE_NAME,
					G4GConstants.NOTIFICATION_WORLD_GAMING_FILE_NAME,
					encryptedToId, encryptedFromId, randomId, request);
			defaultURL.append(G4GConstants.AMBERSAND);
			defaultURL.append(G4GConstants.MATCHID);
			defaultURL.append(G4GConstants.EQUALS);
			defaultURL.append(worldGamingNotification.getMatchId());

			if (DataUtil.isURLAvailable(siteURL.toString())) {
				messageBody = DataUtil.getURLContent(siteURL.toString());
			} else if (DataUtil.isURLAvailable(defaultURL.toString())) {
				messageBody = DataUtil.getURLContent(defaultURL.toString());
			} else {
				messageBody = resourceBundle
						.getString(G4GConstants.NOTIFICATION_WORLDGAMING_MESSAGE)
						+ fromPlayerDTO.getScreenname();
			}

			addMessage(messageBody, G4GConstants.MSG_MATCH_CONFIRM,
					worldGamingNotification.getTo(), worldGamingNotification
							.getFrom(), worldGamingNotification.getMatchId(),
					toPlayerDTO.getSkin().getSkinid(), worldGamingNotification
							.getSubject(), randomId);
		}// Sends popup if true.
		if (adminnotificationDTO.isPopup()) {
			// Code to make entry in NotificationQueue table

			StringBuffer siteURL = generateURL(toPlayerDTO.getSkin()
					.getSkinid(), G4GConstants.NOTIFICATION_POPUP_NAME,
					G4GConstants.NOTIFICATION_WORLD_GAMING_FILE_NAME,
					encryptedToId, encryptedFromId, null, request);
			siteURL.append(G4GConstants.AMBERSAND);
			siteURL.append(G4GConstants.MATCHID);
			siteURL.append(G4GConstants.EQUALS);
			siteURL.append(worldGamingNotification.getMatchId());

			StringBuffer defaultURL = generateURL(G4GConstants.DEFAULT_SITE_ID,
					G4GConstants.NOTIFICATION_POPUP_NAME,
					G4GConstants.NOTIFICATION_WORLD_GAMING_FILE_NAME,
					encryptedToId, encryptedFromId, null, request);
			defaultURL.append(G4GConstants.AMBERSAND);
			defaultURL.append(G4GConstants.MATCHID);
			defaultURL.append(G4GConstants.EQUALS);
			defaultURL.append(worldGamingNotification.getMatchId());
			if (DataUtil.isURLAvailable(siteURL.toString())) {
				messageBody = DataUtil.getURLContent(siteURL.toString());
			} else if (DataUtil.isURLAvailable(defaultURL.toString())) {
				messageBody = DataUtil.getURLContent(defaultURL.toString());
			} else {
				messageBody = resourceBundle
						.getString(G4GConstants.NOTIFICATION_WORLDGAMING_POPUP)
						+ fromPlayerDTO.getScreenname();
			}

			addPopup(messageBody, G4GConstants.MSG_WORLD_GAMING,
					worldGamingNotification.getTo(), worldGamingNotification
							.getFrom(), null);
		}
	}

	private void sendMatchConfirmNotification(
			MatchConfirmNotification matchConfirmNotification,
			ResourceBundle resourceBundle, HttpServletRequest request) {
		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,
				new StringBuffer(this.getClass().getName()).append(
						G4GConstants.COLON_WITH_SPACES).append(G4GConstants.CALLINGMETHOD)
						.append(DataUtil.getCallingMethod()).append(
								G4GConstants.CURRENTMETHOD).append(
								DataUtil.getCurrentMethod()).append(
								IN_INSTANCE_OF_MATCHCONFIRM_NOTIFICATION)
						.toString(), Level.INFO);
		String messageBody = G4GConstants.BLANK;

		SearchCriteria searchCriteria = new SearchCriteria();
		searchCriteria.setAttribute(G4GConstants.NOTIFICATIONTYPE,
				new NotificationtypeDTO(G4GConstants.MSG_MATCH_CONFIRM));
		List<AdminnotificationDTO> adminNotificationList = AdminNotificationServiceDelegator
				.getList(searchCriteria);
		AdminnotificationDTO adminnotificationDTO = adminNotificationList
				.get(0);

		// Code to find to and from players
		PlayerDTO toPlayerDTO = PlayerServiceDelegator
				.getPlayer(matchConfirmNotification.getTo());
		PlayerDTO fromPlayerDTO = PlayerServiceDelegator
				.getPlayer(matchConfirmNotification.getFrom());

		String encryptedToId = DataUtil.encrypt(String
				.valueOf(matchConfirmNotification.getTo()));
		String encryptedFromId = DataUtil.encrypt(String
				.valueOf(matchConfirmNotification.getFrom()));
		Random random = new Random();
		String randomId = DataUtil.todayGMT().getTime()
				+ String.valueOf(random.nextInt());
		// Sends email if true.
		if (adminnotificationDTO.isEmail()) {

			StringBuffer siteURL = generateURL(toPlayerDTO.getSkin()
					.getSkinid(), G4GConstants.NOTIFICATION_EMAIL_NAME,
					G4GConstants.NOTIFICATION_MATCH_CONFIRM_FILE_NAME,
					encryptedToId, encryptedFromId, null, request);
			siteURL.append(G4GConstants.AMBERSAND);
			siteURL.append(G4GConstants.MATCHID);
			siteURL.append(G4GConstants.EQUALS);
			siteURL.append(matchConfirmNotification.getMatchId());

			StringBuffer defaultURL = generateURL(G4GConstants.DEFAULT_SITE_ID,
					G4GConstants.NOTIFICATION_EMAIL_NAME,
					G4GConstants.NOTIFICATION_MATCH_CONFIRM_FILE_NAME,
					encryptedToId, encryptedFromId, null, request);
			defaultURL.append(G4GConstants.AMBERSAND);
			defaultURL.append(G4GConstants.MATCHID);
			defaultURL.append(G4GConstants.EQUALS);
			defaultURL.append(matchConfirmNotification.getMatchId());

			if (DataUtil.isURLAvailable(siteURL.toString())) {
				messageBody = DataUtil.getURLContent(siteURL.toString());
			} else if (DataUtil.isURLAvailable(defaultURL.toString())) {
				messageBody = DataUtil.getURLContent(defaultURL.toString());
			} else {
				messageBody = resourceBundle
						.getString(G4GConstants.NOTIFICATION_MATCHCONFIRM_EMAIL)
						+ fromPlayerDTO.getScreenname();
			}

			/*
			 * try{ mailHelper.sendMail(toPlayerDTO.getEmailaddress(),
			 * fromPlayerDTO.getEmailaddress(),
			 * matchConfirmNotification.getSubject(), messageBody);
			 * }catch(IOException ioe){
			 * AuditUtil.getInstance().writeLog(AuditUtil.FILE_TYPE_G4G, ioe);
			 * }catch(MessagingException me){
			 * AuditUtil.getInstance().writeLog(AuditUtil.FILE_TYPE_G4G, me); }
			 */
		}// Sends message if true.
		if (adminnotificationDTO.isMessage()) {

			String filename = G4GConstants.NOTIFICATION_MATCH_CONFIRM_FILE_NAME;
			if (matchConfirmNotification.getConfirmationId() == G4GConstants.ACCEPT_CHALLENGE_VALUE) {
				filename = G4GConstants.NOTIFICATION_MATCH_CONFIRM_FILE_NAME;
			} else if (matchConfirmNotification.getConfirmationId() == G4GConstants.REJECT_CHALLENGE_VALUE) {
				filename = G4GConstants.NOTIFICATION_MATCH_REJECT_FILE_NAME;
			} else if (matchConfirmNotification.getConfirmationId() == G4GConstants.CANCEL_CHALLENGE_VALUE) {
				filename = G4GConstants.NOTIFICATION_MATCH_CANCEL_FILE_NAME;
			}

			StringBuffer siteURL = generateURL(toPlayerDTO.getSkin()
					.getSkinid(), G4GConstants.NOTIFICATION_MESSAGE_NAME,
					filename, encryptedToId, encryptedFromId, randomId, request);
			siteURL.append(G4GConstants.AMBERSAND);
			siteURL.append(G4GConstants.MATCHID);
			siteURL.append(G4GConstants.EQUALS);
			siteURL.append(matchConfirmNotification.getMatchId());

			StringBuffer defaultURL = generateURL(G4GConstants.DEFAULT_SITE_ID,
					G4GConstants.NOTIFICATION_MESSAGE_NAME, filename,
					encryptedToId, encryptedFromId, randomId, request);
			defaultURL.append(G4GConstants.AMBERSAND);
			defaultURL.append(G4GConstants.MATCHID);
			defaultURL.append(G4GConstants.EQUALS);
			defaultURL.append(matchConfirmNotification.getMatchId());

			if (DataUtil.isURLAvailable(siteURL.toString())) {
				messageBody = DataUtil.getURLContent(siteURL.toString());
			} else if (DataUtil.isURLAvailable(defaultURL.toString())) {
				messageBody = DataUtil.getURLContent(defaultURL.toString());
			} else {
				messageBody = resourceBundle
						.getString(G4GConstants.NOTIFICATION_MATCHCONFIRM_MESSAGE)
						+ fromPlayerDTO.getScreenname();
			}

			addMessage(messageBody, G4GConstants.MSG_MATCH_CONFIRM,
					matchConfirmNotification.getTo(), matchConfirmNotification
							.getFrom(), matchConfirmNotification.getMatchId(),
					toPlayerDTO.getSkin().getSkinid(), matchConfirmNotification
							.getSubject(), randomId);
		}// Sends popup if true.
		if (adminnotificationDTO.isPopup()) {
			// Code to make entry in NotificationQueue table

			String filename = G4GConstants.NOTIFICATION_MATCH_CONFIRM_FILE_NAME;
			if (matchConfirmNotification.getConfirmationId() == G4GConstants.ACCEPT_CHALLENGE_VALUE) {
				filename = G4GConstants.NOTIFICATION_ACCEPTED_FILE_NAME;
			} else if (matchConfirmNotification.getConfirmationId() == G4GConstants.REJECT_CHALLENGE_VALUE) {
				filename = G4GConstants.NOTIFICATION_REJECTED_FILE_NAME;
			} else if (matchConfirmNotification.getConfirmationId() == G4GConstants.CANCEL_CHALLENGE_VALUE) {
				filename = G4GConstants.NOTIFICATION_CANCELED_FILE_NAME;
			}

			StringBuffer siteURL = generateURL(toPlayerDTO.getSkin()
					.getSkinid(), G4GConstants.NOTIFICATION_POPUP_NAME,
					filename, encryptedToId, encryptedFromId, null, request);
			siteURL.append(G4GConstants.AMBERSAND);
			siteURL.append(G4GConstants.MATCHID);
			siteURL.append(G4GConstants.EQUALS);
			siteURL.append(matchConfirmNotification.getMatchId());

			StringBuffer defaultURL = generateURL(G4GConstants.DEFAULT_SITE_ID,
					G4GConstants.NOTIFICATION_POPUP_NAME, filename,
					encryptedToId, encryptedFromId, null, request);
			defaultURL.append(G4GConstants.AMBERSAND);
			defaultURL.append(G4GConstants.MATCHID);
			defaultURL.append(G4GConstants.EQUALS);
			defaultURL.append(matchConfirmNotification.getMatchId());
			if (DataUtil.isURLAvailable(siteURL.toString())) {
				messageBody = DataUtil.getURLContent(siteURL.toString());
			} else if (DataUtil.isURLAvailable(defaultURL.toString())) {
				messageBody = DataUtil.getURLContent(defaultURL.toString());
			} else {
				messageBody = resourceBundle
						.getString(G4GConstants.NOTIFICATION_MATCHCONFIRM_POPUP)
						+ fromPlayerDTO.getScreenname();
			}

			addPopup(messageBody, G4GConstants.MSG_MATCH_CONFIRM,
					matchConfirmNotification.getTo(), matchConfirmNotification
							.getFrom(), matchConfirmNotification
							.getScheduleDate());

		}
	}

	private void sendTournamentJoinNotification(
			TournamentJoinNotification tournamentJoinNotification,
			ResourceBundle resourceBundle, HttpServletRequest request) {
		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,
				new StringBuffer(this.getClass().getName()).append(
						G4GConstants.COLON_WITH_SPACES).append(G4GConstants.CALLINGMETHOD)
						.append(DataUtil.getCallingMethod()).append(
								G4GConstants.CURRENTMETHOD).append(
								DataUtil.getCurrentMethod()).append(
								IN_INSTANCE_OF_TOURNAMENTJOIN_NOTIFICATION)
						.toString(), Level.INFO);
		String messageBody = G4GConstants.BLANK;

		SearchCriteria searchCriteria = new SearchCriteria();
		searchCriteria.setAttribute(G4GConstants.NOTIFICATIONTYPE,
				new NotificationtypeDTO(G4GConstants.MSG_TOURNAMENT_JOIN));
		List<AdminnotificationDTO> adminNotificationList = AdminNotificationServiceDelegator
				.getList(searchCriteria);
		AdminnotificationDTO adminnotificationDTO = adminNotificationList
				.get(0);

		// Code to find to and from players
		PlayerDTO toPlayerDTO = PlayerServiceDelegator
				.getPlayer(tournamentJoinNotification.getTo());
		PlayerDTO fromPlayerDTO = null;

		String encryptedToId = DataUtil.encrypt(String
				.valueOf(tournamentJoinNotification.getTo()));
		String encryptedFromId = DataUtil.encrypt(String
				.valueOf(tournamentJoinNotification.getFrom()));
		Random random = new Random();
		String randomId = DataUtil.todayGMT().getTime()
				+ String.valueOf(random.nextInt());
		// Sends email if true.
		if (adminnotificationDTO.isEmail()) {
			MailHelper mailHelper = new MailHelper();
			StringBuffer siteURL = generateURL(toPlayerDTO.getSkin()
					.getSkinid(), G4GConstants.NOTIFICATION_EMAIL_NAME,
					G4GConstants.NOTIFICATION_TOURNAMENT_JOIN_FILE_NAME,
					encryptedToId, encryptedFromId, randomId, request);
			siteURL.append(G4GConstants.AMBERSAND);
			siteURL.append(G4GConstants.MATCHID);
			siteURL.append(G4GConstants.EQUALS);
			siteURL.append(tournamentJoinNotification.getMatchId());
			siteURL.append(G4GConstants.AMBERSAND);
			siteURL.append(G4GConstants.TOURNAMENTID);
			siteURL.append(G4GConstants.EQUALS);
			siteURL.append(tournamentJoinNotification.getTournamentId());

			StringBuffer defaultURL = generateURL(G4GConstants.DEFAULT_SITE_ID,
					G4GConstants.NOTIFICATION_EMAIL_NAME,
					G4GConstants.NOTIFICATION_TOURNAMENT_JOIN_FILE_NAME,
					encryptedToId, encryptedFromId, randomId, request);
			siteURL.append(G4GConstants.AMBERSAND);
			siteURL.append(G4GConstants.MATCHID);
			siteURL.append(G4GConstants.EQUALS);
			siteURL.append(tournamentJoinNotification.getMatchId());
			siteURL.append(G4GConstants.AMBERSAND);
			siteURL.append(G4GConstants.TOURNAMENTID);
			siteURL.append(G4GConstants.EQUALS);
			siteURL.append(tournamentJoinNotification.getTournamentId());
			if (DataUtil.isURLAvailable(siteURL.toString())) {
				messageBody = DataUtil.getURLContent(siteURL.toString());
			} else if (DataUtil.isURLAvailable(defaultURL.toString())) {
				messageBody = DataUtil.getURLContent(defaultURL.toString());
			} else {
				messageBody = resourceBundle
						.getString(G4GConstants.NOTIFICATION_TOURNAMENTJOIN_EMAIL)
						+ fromPlayerDTO.getScreenname();
			}

			try {
				mailHelper.sendMail(toPlayerDTO.getEmailaddress(),
						fromPlayerDTO.getEmailaddress(),
						tournamentJoinNotification.getSubject(), messageBody);
			} catch (IOException ioe) {
				AuditUtil.getInstance().writeLog(AuditUtil.FILE_TYPE_G4G, ioe);
			} catch (MessagingException me) {
				AuditUtil.getInstance().writeLog(AuditUtil.FILE_TYPE_G4G, me);
			}
		}// Sends message if true.
		if (adminnotificationDTO.isMessage()) {

			StringBuffer siteURL = generateURL(toPlayerDTO.getSkin()
					.getSkinid(), G4GConstants.NOTIFICATION_MESSAGE_NAME,
					G4GConstants.NOTIFICATION_TOURNAMENT_JOIN_FILE_NAME,
					encryptedToId, encryptedFromId, randomId, request);
			siteURL.append(G4GConstants.AMBERSAND);
			siteURL.append(G4GConstants.MATCHID);
			siteURL.append(G4GConstants.EQUALS);
			siteURL.append(tournamentJoinNotification.getMatchId());
			siteURL.append(G4GConstants.AMBERSAND);
			siteURL.append(G4GConstants.TOURNAMENTID);
			siteURL.append(G4GConstants.EQUALS);
			siteURL.append(tournamentJoinNotification.getTournamentId());

			StringBuffer defaultURL = generateURL(G4GConstants.DEFAULT_SITE_ID,
					G4GConstants.NOTIFICATION_MESSAGE_NAME,
					G4GConstants.NOTIFICATION_TOURNAMENT_JOIN_FILE_NAME,
					encryptedToId, encryptedFromId, randomId, request);
			siteURL.append(G4GConstants.AMBERSAND);
			siteURL.append(G4GConstants.MATCHID);
			siteURL.append(G4GConstants.EQUALS);
			siteURL.append(tournamentJoinNotification.getMatchId());
			siteURL.append(G4GConstants.AMBERSAND);
			siteURL.append(G4GConstants.TOURNAMENTID);
			siteURL.append(G4GConstants.EQUALS);
			siteURL.append(tournamentJoinNotification.getTournamentId());

			if (DataUtil.isURLAvailable(siteURL.toString())) {
				messageBody = DataUtil.getURLContent(siteURL.toString());
			} else if (DataUtil.isURLAvailable(defaultURL.toString())) {
				messageBody = DataUtil.getURLContent(defaultURL.toString());
			} else {
				messageBody = resourceBundle
						.getString(G4GConstants.NOTIFICATION_TOURNAMENTJOIN_MESSAGE)
						+ fromPlayerDTO.getScreenname();
			}

			addMessage(messageBody, G4GConstants.MSG_TOURNAMENT_JOIN,
					tournamentJoinNotification.getTo(),
					tournamentJoinNotification.getFrom(),
					tournamentJoinNotification.getMatchId(), toPlayerDTO
							.getSkin().getSkinid(), tournamentJoinNotification
							.getSubject(), randomId);

		}// Sends popup if true.
		if (adminnotificationDTO.isPopup()) {
			// Code to make entry in NotificationQueue table

			StringBuffer siteURL = generateURL(toPlayerDTO.getSkin()
					.getSkinid(), G4GConstants.NOTIFICATION_POPUP_NAME,
					G4GConstants.NOTIFICATION_TOURNAMENT_JOIN_FILE_NAME,
					encryptedToId, encryptedFromId, randomId, request);
			siteURL.append(G4GConstants.AMBERSAND);
			siteURL.append(G4GConstants.MATCHID);
			siteURL.append(G4GConstants.EQUALS);
			siteURL.append(tournamentJoinNotification.getMatchId());
			siteURL.append(G4GConstants.AMBERSAND);
			siteURL.append(G4GConstants.TOURNAMENTID);
			siteURL.append(G4GConstants.EQUALS);
			siteURL.append(tournamentJoinNotification.getTournamentId());

			StringBuffer defaultURL = generateURL(G4GConstants.DEFAULT_SITE_ID,
					G4GConstants.NOTIFICATION_POPUP_NAME,
					G4GConstants.NOTIFICATION_TOURNAMENT_JOIN_FILE_NAME,
					encryptedToId, encryptedFromId, randomId, request);
			siteURL.append(G4GConstants.AMBERSAND);
			siteURL.append(G4GConstants.MATCHID);
			siteURL.append(G4GConstants.EQUALS);
			siteURL.append(tournamentJoinNotification.getMatchId());
			siteURL.append(G4GConstants.AMBERSAND);
			siteURL.append(G4GConstants.TOURNAMENTID);
			siteURL.append(G4GConstants.EQUALS);
			siteURL.append(tournamentJoinNotification.getTournamentId());

			if (DataUtil.isURLAvailable(siteURL.toString())) {
				messageBody = DataUtil.getURLContent(siteURL.toString());
			} else if (DataUtil.isURLAvailable(defaultURL.toString())) {
				messageBody = DataUtil.getURLContent(defaultURL.toString());
			} else {
				messageBody = resourceBundle
						.getString(G4GConstants.NOTIFICATION_TOURNAMENTJOIN_POPUP)
						+ G4GConstants.WORLD_GAMING;
			}

			addPopup(messageBody, G4GConstants.MSG_TOURNAMENT_JOIN,
					tournamentJoinNotification.getTo(),
					tournamentJoinNotification.getFrom(),
					tournamentJoinNotification.getScheduleDate());
		}
	}

	private void sendCashConfirmNotification(
			CashConfirmNotification cashConfirmNotification,
			ResourceBundle resourceBundle, HttpServletRequest request) {
		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,
				new StringBuffer(this.getClass().getName()).append(
						G4GConstants.COLON_WITH_SPACES).append(G4GConstants.CALLINGMETHOD)
						.append(DataUtil.getCallingMethod()).append(
								G4GConstants.CURRENTMETHOD).append(
								DataUtil.getCurrentMethod()).append(
								IN_INSTANCE_OF_CASHCONFIRM_NOTIFICATION)
						.toString(), Level.INFO);
		String messageBody = G4GConstants.BLANK;

		SearchCriteria searchCriteria = new SearchCriteria();
		searchCriteria.setAttribute(G4GConstants.NOTIFICATIONTYPE,
				new NotificationtypeDTO(G4GConstants.MSG_CASH_CONFIRM));
		List<AdminnotificationDTO> adminNotificationList = AdminNotificationServiceDelegator
				.getList(searchCriteria);
		AdminnotificationDTO adminnotificationDTO = adminNotificationList
				.get(0);

		// Code to find to and from players
		PlayerDTO toPlayerDTO = PlayerServiceDelegator
				.getPlayer(cashConfirmNotification.getTo());
		PlayerDTO fromPlayerDTO = PlayerServiceDelegator
				.getPlayer(cashConfirmNotification.getFrom());

		String encryptedToId = DataUtil.encrypt(String
				.valueOf(cashConfirmNotification.getTo()));
		String encryptedFromId = DataUtil.encrypt(String
				.valueOf(cashConfirmNotification.getFrom()));
		Random random = new Random();
		String randomId = DataUtil.todayGMT().getTime()
				+ String.valueOf(random.nextInt());
		// Sends email if true.
		if (adminnotificationDTO.isEmail()) {
			MailHelper mailHelper = new MailHelper();

			StringBuffer siteURL = generateURL(toPlayerDTO.getSkin()
					.getSkinid(), G4GConstants.NOTIFICATION_EMAIL_NAME,
					G4GConstants.NOTIFICATION_CASH_CONFIRM_FILE_NAME,
					encryptedToId, encryptedFromId, null, request);
			siteURL.append(G4GConstants.AMBERSAND);
			siteURL.append(G4GConstants.TRANSACTION_TYPE);
			siteURL.append(G4GConstants.EQUALS);
			siteURL.append(cashConfirmNotification.getTransactionType());
			siteURL.append(G4GConstants.AMBERSAND + G4GConstants.AMOUNT);
			siteURL.append(G4GConstants.EQUALS);
			siteURL.append(cashConfirmNotification.getAmount());
			StringBuffer defaultURL = generateURL(G4GConstants.DEFAULT_SITE_ID,
					G4GConstants.NOTIFICATION_EMAIL_NAME,
					G4GConstants.NOTIFICATION_CASH_CONFIRM_FILE_NAME,
					encryptedToId, encryptedFromId, null, request);
			defaultURL.append(G4GConstants.AMBERSAND);
			defaultURL.append(G4GConstants.TRANSACTION_TYPE);
			defaultURL.append(G4GConstants.EQUALS);
			defaultURL.append(cashConfirmNotification.getTransactionType());
			defaultURL.append(G4GConstants.AMBERSAND + G4GConstants.AMOUNT);
			defaultURL.append(G4GConstants.EQUALS);
			defaultURL.append(cashConfirmNotification.getAmount());

			if (DataUtil.isURLAvailable(siteURL.toString())) {
				messageBody = DataUtil.getURLContent(siteURL.toString());
			} else if (DataUtil.isURLAvailable(defaultURL.toString())) {
				messageBody = DataUtil.getURLContent(defaultURL.toString());
			} else {
				messageBody = resourceBundle
						.getString(G4GConstants.NOTIFICATION_CASHCONFIRM_EMAIL)
						+ fromPlayerDTO.getScreenname();
			}

			try {
				mailHelper.sendMail(toPlayerDTO.getEmailaddress(),
						fromPlayerDTO.getEmailaddress(),
						cashConfirmNotification.getSubject(), messageBody);
			} catch (IOException ioe) {
				AuditUtil.getInstance().writeLog(AuditUtil.FILE_TYPE_G4G, ioe);
			} catch (MessagingException me) {
				AuditUtil.getInstance().writeLog(AuditUtil.FILE_TYPE_G4G, me);
			}
		}// Sends message if true.
		if (adminnotificationDTO.isMessage()) {

			StringBuffer siteURL = generateURL(toPlayerDTO.getSkin()
					.getSkinid(), G4GConstants.NOTIFICATION_MESSAGE_NAME,
					G4GConstants.NOTIFICATION_CASH_CONFIRM_FILE_NAME,
					encryptedToId, encryptedFromId, randomId, request);
			siteURL.append(G4GConstants.AMBERSAND);
			siteURL.append(G4GConstants.TRANSACTION_TYPE);
			siteURL.append(G4GConstants.EQUALS);
			siteURL.append(cashConfirmNotification.getTransactionType());
			siteURL.append(G4GConstants.AMBERSAND + G4GConstants.AMOUNT);
			siteURL.append(G4GConstants.EQUALS);
			siteURL.append(cashConfirmNotification.getAmount());

			StringBuffer defaultURL = generateURL(G4GConstants.DEFAULT_SITE_ID,
					G4GConstants.NOTIFICATION_MESSAGE_NAME,
					G4GConstants.NOTIFICATION_CASH_CONFIRM_FILE_NAME,
					encryptedToId, encryptedFromId, randomId, request);
			defaultURL.append(G4GConstants.AMBERSAND);
			defaultURL.append(G4GConstants.TRANSACTION_TYPE);
			defaultURL.append(G4GConstants.EQUALS);
			defaultURL.append(cashConfirmNotification.getTransactionType());
			defaultURL.append(G4GConstants.AMBERSAND + G4GConstants.AMOUNT);
			defaultURL.append(G4GConstants.EQUALS);
			defaultURL.append(cashConfirmNotification.getAmount());

			if (DataUtil.isURLAvailable(siteURL.toString())) {
				messageBody = DataUtil.getURLContent(siteURL.toString());
			} else if (DataUtil.isURLAvailable(defaultURL.toString())) {
				messageBody = DataUtil.getURLContent(defaultURL.toString());
			} else {
				messageBody = resourceBundle
						.getString(G4GConstants.NOTIFICATION_CASHCONFIRM_MESSAGE)
						+ fromPlayerDTO.getScreenname();
			}

			addMessage(messageBody, G4GConstants.MSG_CASH_CONFIRM,
					cashConfirmNotification.getTo(), cashConfirmNotification
							.getFrom(), G4GConstants.ONE_NEGATIVE, toPlayerDTO
							.getSkin().getSkinid(), cashConfirmNotification
							.getSubject(), randomId);
		}// Sends popup if true.
		if (adminnotificationDTO.isPopup()) {
			// Code to make entry in NotificationQueue table

			StringBuffer siteURL = generateURL(toPlayerDTO.getSkin()
					.getSkinid(), G4GConstants.NOTIFICATION_POPUP_NAME,
					G4GConstants.NOTIFICATION_CASH_CONFIRM_FILE_NAME,
					encryptedToId, encryptedFromId, null, request);
			siteURL.append(G4GConstants.AMBERSAND);
			siteURL.append(G4GConstants.TRANSACTION_TYPE);
			siteURL.append(G4GConstants.EQUALS);
			siteURL.append(cashConfirmNotification.getTransactionType());
			siteURL.append(G4GConstants.AMBERSAND + G4GConstants.AMOUNT);
			siteURL.append(G4GConstants.EQUALS);
			siteURL.append(cashConfirmNotification.getAmount());

			StringBuffer defaultURL = generateURL(G4GConstants.DEFAULT_SITE_ID,
					G4GConstants.NOTIFICATION_POPUP_NAME,
					G4GConstants.NOTIFICATION_CASH_CONFIRM_FILE_NAME,
					encryptedToId, encryptedFromId, null, request);
			defaultURL.append(G4GConstants.AMBERSAND);
			defaultURL.append(G4GConstants.TRANSACTION_TYPE);
			defaultURL.append(G4GConstants.EQUALS);
			defaultURL.append(cashConfirmNotification.getTransactionType());
			defaultURL.append(G4GConstants.AMBERSAND + G4GConstants.AMOUNT);
			defaultURL.append(G4GConstants.EQUALS);
			defaultURL.append(cashConfirmNotification.getAmount());

			if (DataUtil.isURLAvailable(siteURL.toString())) {
				messageBody = DataUtil.getURLContent(siteURL.toString());
			} else if (DataUtil.isURLAvailable(defaultURL.toString())) {
				messageBody = DataUtil.getURLContent(defaultURL.toString());
			} else {
				messageBody = resourceBundle
						.getString(G4GConstants.NOTIFICATION_CASHCONFIRM_POPUP)
						+ fromPlayerDTO.getScreenname();
			}

			addPopup(messageBody, G4GConstants.MSG_CASH_CONFIRM,
					cashConfirmNotification.getTo(), cashConfirmNotification
							.getFrom(), null);

		}
	}

	private void sendMatchResultNotification(
			MatchResultNotification matchResultNotification,
			ResourceBundle resourceBundle, HttpServletRequest request) {
		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,
				new StringBuffer(this.getClass().getName()).append(
						G4GConstants.COLON_WITH_SPACES).append(G4GConstants.CALLINGMETHOD)
						.append(DataUtil.getCallingMethod()).append(
								G4GConstants.CURRENTMETHOD).append(
								DataUtil.getCurrentMethod()).append(
								IN_INSTANCE_OF_MATCHRESULT_NOTIFICATION)
						.toString(), Level.INFO);
		String messageBody = G4GConstants.BLANK;

		SearchCriteria searchCriteria = new SearchCriteria();
		searchCriteria.setAttribute(G4GConstants.NOTIFICATIONTYPE,
				new NotificationtypeDTO(G4GConstants.MSG_MATCH_RESULTS));
		List<AdminnotificationDTO> adminNotificationList = AdminNotificationServiceDelegator
				.getList(searchCriteria);
		AdminnotificationDTO adminnotificationDTO = adminNotificationList
				.get(0);

		// Code to find to and from players
		PlayerDTO toPlayerDTO = PlayerServiceDelegator
				.getPlayer(matchResultNotification.getTo());
		PlayerDTO fromPlayerDTO = PlayerServiceDelegator
				.getPlayer(matchResultNotification.getFrom());

		String encryptedToId = DataUtil.encrypt(String
				.valueOf(matchResultNotification.getTo()));
		String encryptedFromId = DataUtil.encrypt(String
				.valueOf(matchResultNotification.getFrom()));
		Random random = new Random();
		String randomId = DataUtil.todayGMT().getTime()
				+ String.valueOf(random.nextInt());
		// Sends email if true.
		if (adminnotificationDTO.isEmail()) {
			MailHelper mailHelper = new MailHelper();
			StringBuffer siteURL = generateURL(toPlayerDTO.getSkin()
					.getSkinid(), G4GConstants.NOTIFICATION_EMAIL_NAME,
					G4GConstants.NOTIFICATION_MATCH_RESULT_FILE_NAME,
					encryptedToId, encryptedFromId, randomId, request);
			siteURL.append(G4GConstants.AMBERSAND);
			siteURL.append(G4GConstants.MATCHID);
			siteURL.append(G4GConstants.EQUALS);
			siteURL.append(matchResultNotification.getMatchId());
			siteURL.append(G4GConstants.AMBERSAND + G4GConstants.TOURNAMENTID);
			siteURL.append(G4GConstants.EQUALS);
			siteURL.append(matchResultNotification.getTournamentId());
			StringBuffer defaultURL = generateURL(G4GConstants.DEFAULT_SITE_ID,
					G4GConstants.NOTIFICATION_EMAIL_NAME,
					G4GConstants.NOTIFICATION_MATCH_RESULT_FILE_NAME,
					encryptedToId, encryptedFromId, randomId, request);
			defaultURL.append(G4GConstants.AMBERSAND);
			defaultURL.append(G4GConstants.MATCHID);
			defaultURL.append(G4GConstants.EQUALS);
			defaultURL.append(matchResultNotification.getMatchId());
			defaultURL.append(G4GConstants.AMBERSAND
					+ G4GConstants.TOURNAMENTID);
			defaultURL.append(G4GConstants.EQUALS);
			defaultURL.append(matchResultNotification.getTournamentId());

			if (DataUtil.isURLAvailable(siteURL.toString())) {
				messageBody = DataUtil.getURLContent(siteURL.toString());
			} else if (DataUtil.isURLAvailable(defaultURL.toString())) {
				messageBody = DataUtil.getURLContent(defaultURL.toString());
			} else {
				messageBody = resourceBundle
						.getString(G4GConstants.NOTIFICATION_MATCHRESULT_EMAIL)
						+ fromPlayerDTO.getScreenname();
			}

			try {
				mailHelper.sendMail(toPlayerDTO.getEmailaddress(),
						fromPlayerDTO.getEmailaddress(),
						matchResultNotification.getSubject(), messageBody);
			} catch (IOException ioe) {
				AuditUtil.getInstance().writeLog(AuditUtil.FILE_TYPE_G4G, ioe);
			} catch (MessagingException me) {
				AuditUtil.getInstance().writeLog(AuditUtil.FILE_TYPE_G4G, me);
			}
		}// Sends message if true.
		if (adminnotificationDTO.isMessage()) {

			StringBuffer siteURL = generateURL(toPlayerDTO.getSkin()
					.getSkinid(), G4GConstants.NOTIFICATION_MESSAGE_NAME,
					G4GConstants.NOTIFICATION_MATCH_RESULT_FILE_NAME,
					encryptedToId, encryptedFromId, randomId, request);
			siteURL.append(G4GConstants.AMBERSAND);
			siteURL.append(G4GConstants.MATCHID);
			siteURL.append(G4GConstants.EQUALS);
			siteURL.append(matchResultNotification.getMatchId());
			siteURL.append(G4GConstants.AMBERSAND + G4GConstants.TOURNAMENTID);
			siteURL.append(G4GConstants.EQUALS);
			siteURL.append(matchResultNotification.getTournamentId());
			StringBuffer defaultURL = generateURL(G4GConstants.DEFAULT_SITE_ID,
					G4GConstants.NOTIFICATION_MESSAGE_NAME,
					G4GConstants.NOTIFICATION_MATCH_RESULT_FILE_NAME,
					encryptedToId, encryptedFromId, randomId, request);
			defaultURL.append(G4GConstants.AMBERSAND);
			defaultURL.append(G4GConstants.MATCHID);
			defaultURL.append(G4GConstants.EQUALS);
			defaultURL.append(matchResultNotification.getMatchId());
			defaultURL.append(G4GConstants.AMBERSAND
					+ G4GConstants.TOURNAMENTID);
			defaultURL.append(G4GConstants.EQUALS);
			defaultURL.append(matchResultNotification.getTournamentId());
			if (DataUtil.isURLAvailable(siteURL.toString())) {
				messageBody = DataUtil.getURLContent(siteURL.toString());
			} else if (DataUtil.isURLAvailable(defaultURL.toString())) {
				messageBody = DataUtil.getURLContent(defaultURL.toString());
			} else {
				messageBody = resourceBundle
						.getString(G4GConstants.NOTIFICATION_MATCHRESULT_MESSAGE)
						+ fromPlayerDTO.getScreenname();
			}

			addMessage(messageBody, G4GConstants.MSG_MATCH_RESULTS,
					matchResultNotification.getTo(), matchResultNotification
							.getFrom(), matchResultNotification.getMatchId(),
					toPlayerDTO.getSkin().getSkinid(), matchResultNotification
							.getSubject(), randomId);
		}// Sends popup if true.
		if (adminnotificationDTO.isPopup()) {
			// Code to make entry in NotificationQueue table

			StringBuffer siteURL = generateURL(toPlayerDTO.getSkin()
					.getSkinid(), G4GConstants.NOTIFICATION_POPUP_NAME,
					G4GConstants.NOTIFICATION_MATCH_RESULT_FILE_NAME,
					encryptedToId, encryptedFromId, randomId, request);
			siteURL.append(G4GConstants.AMBERSAND);
			siteURL.append(G4GConstants.MATCHID);
			siteURL.append(G4GConstants.EQUALS);
			siteURL.append(matchResultNotification.getMatchId());
			siteURL.append(G4GConstants.AMBERSAND + G4GConstants.TOURNAMENTID);
			siteURL.append(G4GConstants.EQUALS);
			siteURL.append(matchResultNotification.getTournamentId());
			StringBuffer defaultURL = generateURL(G4GConstants.DEFAULT_SITE_ID,
					G4GConstants.NOTIFICATION_POPUP_NAME,
					G4GConstants.NOTIFICATION_MATCH_RESULT_FILE_NAME,
					encryptedToId, encryptedFromId, randomId, request);
			defaultURL.append(G4GConstants.AMBERSAND);
			defaultURL.append(G4GConstants.MATCHID);
			defaultURL.append(G4GConstants.EQUALS);
			defaultURL.append(matchResultNotification.getMatchId());
			defaultURL.append(G4GConstants.AMBERSAND
					+ G4GConstants.TOURNAMENTID);
			defaultURL.append(G4GConstants.EQUALS);
			defaultURL.append(matchResultNotification.getTournamentId());

			if (DataUtil.isURLAvailable(siteURL.toString())) {
				messageBody = DataUtil.getURLContent(siteURL.toString());
			} else if (DataUtil.isURLAvailable(defaultURL.toString())) {
				messageBody = DataUtil.getURLContent(defaultURL.toString());
			} else {
				messageBody = resourceBundle
						.getString(G4GConstants.NOTIFICATION_MATCHRESULT_POPUP)
						+ fromPlayerDTO.getScreenname();
			}

			addPopup(messageBody, G4GConstants.MSG_MATCH_RESULTS,
					matchResultNotification.getTo(), matchResultNotification
							.getFrom(), null);
		}
	}

	private void sendTournamentResultNotification(
			TournamentResultNotification tournamentResultNotification,
			ResourceBundle resourceBundle, HttpServletRequest request) {
		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,
				new StringBuffer(this.getClass().getName()).append(
						G4GConstants.COLON_WITH_SPACES).append(G4GConstants.CALLINGMETHOD)
						.append(DataUtil.getCallingMethod()).append(
								G4GConstants.CURRENTMETHOD).append(
								DataUtil.getCurrentMethod()).append(
								IN_INSTANCE_OF_TOURNAMENTRESULT_NOTIFICATION)
						.toString(), Level.INFO);
		String messageBody = G4GConstants.BLANK;

		SearchCriteria searchCriteria = new SearchCriteria();
		searchCriteria.setAttribute(G4GConstants.NOTIFICATIONTYPE,
				new NotificationtypeDTO(G4GConstants.MSG_TOURNAMENT_RESULTS));
		List<AdminnotificationDTO> adminNotificationList = AdminNotificationServiceDelegator
				.getList(searchCriteria);
		AdminnotificationDTO adminnotificationDTO = adminNotificationList
				.get(0);

		// Code to find to and from players
		PlayerDTO toPlayerDTO = PlayerServiceDelegator
				.getPlayer(tournamentResultNotification.getTo());
		PlayerDTO fromPlayerDTO = PlayerServiceDelegator
				.getPlayer(tournamentResultNotification.getFrom());

		String encryptedToId = DataUtil.encrypt(String
				.valueOf(tournamentResultNotification.getTo()));
		String encryptedFromId = DataUtil.encrypt(String
				.valueOf(tournamentResultNotification.getFrom()));
		Random random = new Random();
		String randomId = DataUtil.todayGMT().getTime()
				+ String.valueOf(random.nextInt());

		// Sends email if true
		if (adminnotificationDTO.isEmail()) {
			MailHelper mailHelper = new MailHelper();

			StringBuffer siteURL = generateURL(toPlayerDTO.getSkin()
					.getSkinid(), G4GConstants.NOTIFICATION_EMAIL_NAME,
					G4GConstants.NOTIFICATION_TOURNAMENT_RESULT_FILE_NAME,
					encryptedToId, encryptedFromId, randomId, request);
			siteURL.append(G4GConstants.AMBERSAND);
			siteURL.append(G4GConstants.MATCHID);
			siteURL.append(G4GConstants.EQUALS);
			siteURL.append(tournamentResultNotification.getMatchId());
			siteURL.append(G4GConstants.AMBERSAND + G4GConstants.TOURNAMENTID);
			siteURL.append(G4GConstants.EQUALS);
			siteURL.append(tournamentResultNotification.getTournamentId());
			StringBuffer defaultURL = generateURL(G4GConstants.DEFAULT_SITE_ID,
					G4GConstants.NOTIFICATION_EMAIL_NAME,
					G4GConstants.NOTIFICATION_TOURNAMENT_RESULT_FILE_NAME,
					encryptedToId, encryptedFromId, randomId, request);
			defaultURL.append(G4GConstants.AMBERSAND);
			defaultURL.append(G4GConstants.MATCHID);
			defaultURL.append(G4GConstants.EQUALS);
			defaultURL.append(tournamentResultNotification.getMatchId());
			defaultURL.append(G4GConstants.AMBERSAND
					+ G4GConstants.TOURNAMENTID);
			defaultURL.append(G4GConstants.EQUALS);
			defaultURL.append(tournamentResultNotification.getTournamentId());

			if (DataUtil.isURLAvailable(siteURL.toString())) {
				messageBody = DataUtil.getURLContent(siteURL.toString());
			} else if (DataUtil.isURLAvailable(defaultURL.toString())) {
				messageBody = DataUtil.getURLContent(defaultURL.toString());
			} else {
				messageBody = resourceBundle
						.getString(G4GConstants.NOTIFICATION_TOURNAMENTRESULT_EMAIL)
						+ fromPlayerDTO.getScreenname();
			}

			try {
				mailHelper.sendMail(toPlayerDTO.getEmailaddress(),
						fromPlayerDTO.getEmailaddress(),
						tournamentResultNotification.getSubject(), messageBody);
			} catch (IOException ioe) {
				AuditUtil.getInstance().writeLog(AuditUtil.FILE_TYPE_G4G, ioe);
			} catch (MessagingException me) {
				AuditUtil.getInstance().writeLog(AuditUtil.FILE_TYPE_G4G, me);
			}

		}// sends message if true.
		if (adminnotificationDTO.isMessage()) {

			StringBuffer siteURL = generateURL(toPlayerDTO.getSkin()
					.getSkinid(), G4GConstants.NOTIFICATION_MESSAGE_NAME,
					G4GConstants.NOTIFICATION_TOURNAMENT_RESULT_FILE_NAME,
					encryptedToId, encryptedFromId, randomId, request);
			siteURL.append(G4GConstants.AMBERSAND);
			siteURL.append(G4GConstants.MATCHID);
			siteURL.append(G4GConstants.EQUALS);
			siteURL.append(tournamentResultNotification.getMatchId());
			siteURL.append(G4GConstants.AMBERSAND + G4GConstants.TOURNAMENTID);
			siteURL.append(G4GConstants.EQUALS);
			siteURL.append(tournamentResultNotification.getTournamentId());
			StringBuffer defaultURL = generateURL(G4GConstants.DEFAULT_SITE_ID,
					G4GConstants.NOTIFICATION_MESSAGE_NAME,
					G4GConstants.NOTIFICATION_TOURNAMENT_RESULT_FILE_NAME,
					encryptedToId, encryptedFromId, randomId, request);
			defaultURL.append(G4GConstants.AMBERSAND);
			defaultURL.append(G4GConstants.MATCHID);
			defaultURL.append(G4GConstants.EQUALS);
			defaultURL.append(tournamentResultNotification.getMatchId());
			defaultURL.append(G4GConstants.AMBERSAND
					+ G4GConstants.TOURNAMENTID);
			defaultURL.append(G4GConstants.EQUALS);
			defaultURL.append(tournamentResultNotification.getTournamentId());

			if (DataUtil.isURLAvailable(siteURL.toString())) {
				messageBody = DataUtil.getURLContent(siteURL.toString());
			} else if (DataUtil.isURLAvailable(defaultURL.toString())) {
				messageBody = DataUtil.getURLContent(defaultURL.toString());
			} else {
				messageBody = resourceBundle
						.getString(G4GConstants.NOTIFICATION_TOURNAMENTRESULT_MESSAGE)
						+ fromPlayerDTO.getScreenname();
			}

			addMessage(messageBody, G4GConstants.MSG_TOURNAMENT_RESULTS,
					tournamentResultNotification.getTo(),
					tournamentResultNotification.getFrom(),
					tournamentResultNotification.getMatchId(), toPlayerDTO
							.getSkin().getSkinid(),
					tournamentResultNotification.getSubject(), randomId);
		}// sends popup if true.
		if (adminnotificationDTO.isPopup()) {
			// Code to make entry in NotificationQueue table

			StringBuffer siteURL = generateURL(toPlayerDTO.getSkin()
					.getSkinid(), G4GConstants.NOTIFICATION_POPUP_NAME,
					G4GConstants.NOTIFICATION_TOURNAMENT_RESULT_FILE_NAME,
					encryptedToId, encryptedFromId, randomId, request);
			siteURL.append(G4GConstants.AMBERSAND);
			siteURL.append(G4GConstants.MATCHID);
			siteURL.append(G4GConstants.EQUALS);
			siteURL.append(tournamentResultNotification.getMatchId());
			siteURL.append(G4GConstants.AMBERSAND + G4GConstants.TOURNAMENTID);
			siteURL.append(G4GConstants.EQUALS);
			siteURL.append(tournamentResultNotification.getTournamentId());
			StringBuffer defaultURL = generateURL(G4GConstants.DEFAULT_SITE_ID,
					G4GConstants.NOTIFICATION_POPUP_NAME,
					G4GConstants.NOTIFICATION_TOURNAMENT_RESULT_FILE_NAME,
					encryptedToId, encryptedFromId, randomId, request);
			defaultURL.append(G4GConstants.AMBERSAND);
			defaultURL.append(G4GConstants.MATCHID);
			defaultURL.append(G4GConstants.EQUALS);
			defaultURL.append(tournamentResultNotification.getMatchId());
			defaultURL.append(G4GConstants.AMBERSAND
					+ G4GConstants.TOURNAMENTID);
			defaultURL.append(G4GConstants.EQUALS);
			defaultURL.append(tournamentResultNotification.getTournamentId());

			if (DataUtil.isURLAvailable(siteURL.toString())) {
				messageBody = DataUtil.getURLContent(siteURL.toString());
			} else if (DataUtil.isURLAvailable(defaultURL.toString())) {
				messageBody = DataUtil.getURLContent(defaultURL.toString());
			} else {
				messageBody = resourceBundle
						.getString(G4GConstants.NOTIFICATION_TOURNAMENTRESULT_POPUP)
						+ fromPlayerDTO.getScreenname();
			}

			addPopup(messageBody, G4GConstants.MSG_TOURNAMENT_RESULTS,
					tournamentResultNotification.getTo(),
					tournamentResultNotification.getFrom(), null);

		}
	}

	private void sendUpcomingMatchNotification(
			UpcomingMatchNotification upcomingMatchNotification,
			ResourceBundle resourceBundle, HttpServletRequest request) {
		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,
				new StringBuffer(this.getClass().getName()).append(
						G4GConstants.COLON_WITH_SPACES).append(G4GConstants.CALLINGMETHOD)
						.append(DataUtil.getCallingMethod()).append(
								G4GConstants.CURRENTMETHOD).append(
								DataUtil.getCurrentMethod()).append(
								IN_INSTANCE_OF_UPCOMINGMATCH_NOTIFICATION)
						.toString(), Level.INFO);
		String messageBody = G4GConstants.BLANK;

		SearchCriteria searchCriteria = new SearchCriteria();
		searchCriteria.setAttribute(G4GConstants.NOTIFICATIONTYPE,
				new NotificationtypeDTO(G4GConstants.MSG_UPCOMING_MATCH));
		List<AdminnotificationDTO> adminNotificationList = AdminNotificationServiceDelegator
				.getList(searchCriteria);
		AdminnotificationDTO adminnotificationDTO = adminNotificationList
				.get(0);

		// Code to find to and from players
		PlayerDTO toPlayerDTO = PlayerServiceDelegator
				.getPlayer(upcomingMatchNotification.getTo());
		PlayerDTO fromPlayerDTO = PlayerServiceDelegator
				.getPlayer(upcomingMatchNotification.getFrom());

		String encryptedToId = DataUtil.encrypt(String
				.valueOf(upcomingMatchNotification.getTo()));
		String encryptedFromId = DataUtil.encrypt(String
				.valueOf(upcomingMatchNotification.getFrom()));
		Random random = new Random();
		String randomId = DataUtil.todayGMT().getTime()
				+ String.valueOf(random.nextInt());
		// Sends email if true.
		if (adminnotificationDTO.isEmail()) {
			MailHelper mailHelper = new MailHelper();

			StringBuffer siteURL = generateURL(toPlayerDTO.getSkin()
					.getSkinid(), G4GConstants.NOTIFICATION_EMAIL_NAME,
					G4GConstants.NOTIFICATION_UPCOMING_MATCH_FILE_NAME,
					encryptedToId, encryptedFromId, randomId, request);
			siteURL.append(G4GConstants.AMBERSAND);
			siteURL.append(G4GConstants.MATCHID);
			siteURL.append(G4GConstants.EQUALS);
			siteURL.append(upcomingMatchNotification.getMatchId());

			StringBuffer defaultURL = generateURL(G4GConstants.DEFAULT_SITE_ID,
					G4GConstants.NOTIFICATION_EMAIL_NAME,
					G4GConstants.NOTIFICATION_UPCOMING_MATCH_FILE_NAME,
					encryptedToId, encryptedFromId, randomId, request);
			defaultURL.append(G4GConstants.AMBERSAND);
			defaultURL.append(G4GConstants.MATCHID);
			defaultURL.append(G4GConstants.EQUALS);
			defaultURL.append(upcomingMatchNotification.getMatchId());

			if (DataUtil.isURLAvailable(siteURL.toString())) {
				messageBody = DataUtil.getURLContent(siteURL.toString());
			} else if (DataUtil.isURLAvailable(defaultURL.toString())) {
				messageBody = DataUtil.getURLContent(defaultURL.toString());
			} else {
				messageBody = resourceBundle
						.getString(G4GConstants.NOTIFICATION_UPCOMINGMATCH_EMAIL)
						+ fromPlayerDTO.getScreenname();
			}

			try {
				mailHelper.sendMail(toPlayerDTO.getEmailaddress(),
						fromPlayerDTO.getEmailaddress(),
						upcomingMatchNotification.getSubject(), messageBody);
			} catch (IOException ioe) {
				AuditUtil.getInstance().writeLog(AuditUtil.FILE_TYPE_G4G, ioe);
			} catch (MessagingException me) {
				AuditUtil.getInstance().writeLog(AuditUtil.FILE_TYPE_G4G, me);
			}
		}// Sends message if true.
		if (adminnotificationDTO.isMessage()) {

			StringBuffer siteURL = generateURL(toPlayerDTO.getSkin()
					.getSkinid(), G4GConstants.NOTIFICATION_MESSAGE_NAME,
					G4GConstants.NOTIFICATION_UPCOMING_MATCH_FILE_NAME,
					encryptedToId, encryptedFromId, randomId, request);
			siteURL.append(G4GConstants.AMBERSAND);
			siteURL.append(G4GConstants.MATCHID);
			siteURL.append(G4GConstants.EQUALS);
			siteURL.append(upcomingMatchNotification.getMatchId());

			StringBuffer defaultURL = generateURL(G4GConstants.DEFAULT_SITE_ID,
					G4GConstants.NOTIFICATION_MESSAGE_NAME,
					G4GConstants.NOTIFICATION_UPCOMING_MATCH_FILE_NAME,
					encryptedToId, encryptedFromId, randomId, request);
			defaultURL.append(G4GConstants.AMBERSAND);
			defaultURL.append(G4GConstants.MATCHID);
			defaultURL.append(G4GConstants.EQUALS);
			defaultURL.append(upcomingMatchNotification.getMatchId());

			if (DataUtil.isURLAvailable(siteURL.toString())) {
				messageBody = DataUtil.getURLContent(siteURL.toString());
			} else if (DataUtil.isURLAvailable(defaultURL.toString())) {
				messageBody = DataUtil.getURLContent(defaultURL.toString());
			} else {
				messageBody = resourceBundle
						.getString(G4GConstants.NOTIFICATION_UPCOMINGMATCH_MESSAGE)
						+ fromPlayerDTO.getScreenname();
			}

			addMessage(messageBody, G4GConstants.MSG_UPCOMING_MATCH,
					upcomingMatchNotification.getTo(),
					upcomingMatchNotification.getFrom(),
					upcomingMatchNotification.getMatchId(), toPlayerDTO
							.getSkin().getSkinid(), upcomingMatchNotification
							.getSubject(), randomId);
		}// Sends popup if true.
		if (adminnotificationDTO.isPopup()) {
			// Code to make entry in NotificationQueue table

			StringBuffer siteURL = generateURL(toPlayerDTO.getSkin()
					.getSkinid(), G4GConstants.NOTIFICATION_POPUP_NAME,
					G4GConstants.NOTIFICATION_UPCOMING_MATCH_FILE_NAME,
					encryptedToId, encryptedFromId, randomId, request);
			siteURL.append(G4GConstants.AMBERSAND);
			siteURL.append(G4GConstants.MATCHID);
			siteURL.append(G4GConstants.EQUALS);
			siteURL.append(upcomingMatchNotification.getMatchId());

			StringBuffer defaultURL = generateURL(G4GConstants.DEFAULT_SITE_ID,
					G4GConstants.NOTIFICATION_POPUP_NAME,
					G4GConstants.NOTIFICATION_UPCOMING_MATCH_FILE_NAME,
					encryptedToId, encryptedFromId, randomId, request);
			defaultURL.append(G4GConstants.AMBERSAND);
			defaultURL.append(G4GConstants.MATCHID);
			defaultURL.append(G4GConstants.EQUALS);
			defaultURL.append(upcomingMatchNotification.getMatchId());

			if (DataUtil.isURLAvailable(siteURL.toString())) {
				messageBody = DataUtil.getURLContent(siteURL.toString());
			} else if (DataUtil.isURLAvailable(defaultURL.toString())) {
				messageBody = DataUtil.getURLContent(defaultURL.toString());
			} else {
				messageBody = resourceBundle
						.getString(G4GConstants.NOTIFICATION_UPCOMINGMATCH_POPUP)
						+ fromPlayerDTO.getScreenname();
			}

			addPopup(messageBody, G4GConstants.MSG_UPCOMING_MATCH,
					upcomingMatchNotification.getTo(),
					upcomingMatchNotification.getFrom(), null);
		}
	}

}
