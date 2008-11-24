/**********************************************************
 * FriendsAjaxImpl.java : 
 *
 * Created by Jigar Mistry
 * Last modified Date: 18 Apr .08 by Punam
 * Revision: 0.1
 * Version : 0.0.8
 * Copyright (c) 2008 - 2008 Askdigi, All rights reserved.
 **********************************************************/
package com.g4g.servlet;

import static com.g4g.utils.G4GConstants.COLON_WITH_SPACES;
import static com.g4g.utils.G4GConstants.CALLINGMETHOD;
import static com.g4g.utils.G4GConstants.CURRENTMETHOD;
import static com.g4g.utils.G4GConstants.DASHES;
import static com.g4g.utils.G4GConstants.ENDED;
import static com.g4g.utils.G4GConstants.FILE_NOT_IMAGE;
import static com.g4g.utils.G4GConstants.FRIEND_REQUEST_ALREADY_MADE;
import static com.g4g.utils.G4GConstants.FRIEND_REQUEST_MADE;
import static com.g4g.utils.G4GConstants.NONE;
import static com.g4g.utils.G4GConstants.PLAYERBYPLAYERID1;
import static com.g4g.utils.G4GConstants.PLAYERBYPLAYERID2;
import static com.g4g.utils.G4GConstants.SCREENNAME_DB;
import static com.g4g.utils.G4GConstants.SESSIONID;
import static com.g4g.utils.G4GConstants.STARTED;
import static com.g4g.utils.G4GConstants.USER_ADDED_AS_FRIEND;
import static com.g4g.utils.G4GConstants.ZERO;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Level;
import org.hibernate.exception.ConstraintViolationException;

import com.g4g.delegator.FriendsServiceDelegator;
import com.g4g.delegator.NotificationServiceDelegator;
import com.g4g.delegator.PlayerServiceDelegator;
import com.g4g.dto.FriendsDTO;
import com.g4g.dto.PlayerDTO;
import com.g4g.dto.SearchCriteria;
import com.g4g.dto.SearchListCriteria;
import com.g4g.notification.FriendRequestNotification;
import com.g4g.utils.AuditUtil;
import com.g4g.utils.DataUtil;
import com.g4g.utils.FriendsUtil;

//$ANALYSIS-IGNORE
/**
 * The Class FriendsAjaxImpl contains ajax implementation for friends. It adds
 * the friend request to specified Ids. When the friend request is accepted both
 * the users will be friend. When the user is removed it will be removed from
 * both the users. This class cannot be instantiated or cloned.
 * 
 * @author Jigar Mistry
 */
@SuppressWarnings({"CloneDoesntCallSuperClone"})
public class FriendsAjaxImpl {

	private static final String UNCHECKED = "unchecked"; //$NON-NLS-1$

	/**
	 * Method addFriendRequest sends add friends request to given playerId from
	 * the user who is sending. The request will be sent but untill the other
	 * player accepts it, Both will not be friends of each other. It will give
	 * the message that friend request is made.
	 * 
	 * @param playerIdFrom
	 *            The friend request is friend from.
	 * @param playerIdTo
	 *            The friend request is friend to.
	 * @param request
	 *            the request object to get the values stored in the session.
	 * 
	 * @return the string says the friend request is already is made.
	 */
	public static String addFriendRequest(int playerIdFrom,
			int playerIdTo, HttpServletRequest request) {
		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,
				new StringBuffer(FriendsAjaxImpl.class.getName()).append(
						COLON_WITH_SPACES).append(CALLINGMETHOD)
						.append(DataUtil.getCallingMethod()).append(
								CURRENTMETHOD).append(
								DataUtil.getCurrentMethod()).append(
								DASHES).append(
								STARTED).append(
								DASHES).append(
								SESSIONID).append(
								request.getSession().getId()).toString(),
				Level.INFO);

		if (FriendsUtil.isFriend(playerIdFrom, playerIdTo)) {
			AuditUtil.getInstance()
					.writeLog(
							AuditUtil.FILE_TYPE_G4G,
							new StringBuffer(FriendsAjaxImpl.class.getName())
									.append(COLON_WITH_SPACES).append(
											CALLINGMETHOD).append(
											DataUtil.getCallingMethod())
									.append(CURRENTMETHOD).append(
											DataUtil.getCurrentMethod())
									.append(DASHES).append(
											FILE_NOT_IMAGE)
									.toString(), Level.INFO);
			return FRIEND_REQUEST_ALREADY_MADE;
		} else {
			FriendsDTO friendsDTO = new FriendsDTO();
			PlayerDTO playerDTO1 = new PlayerDTO();
			playerDTO1.setId(playerIdFrom);
			friendsDTO.setPlayerByPlayerid1(playerDTO1);

			PlayerDTO playerDTO2 = new PlayerDTO();
			playerDTO2.setId(playerIdTo);
			friendsDTO.setPlayerByPlayerid2(playerDTO2);
			friendsDTO.setPlayer1accepted(DataUtil.todayGMT());

			try {
				FriendsServiceDelegator.add(friendsDTO);

				FriendRequestNotification friendRequestNotification = new FriendRequestNotification(
						playerIdTo, playerIdFrom);
				NotificationServiceDelegator.sendNotification(
						friendRequestNotification, request);
			} catch (ConstraintViolationException jdbcException) {
				AuditUtil.getInstance().writeLog(
						AuditUtil.FILE_TYPE_HIBERNATE,
						new StringBuffer(FriendsAjaxImpl.class.getName())
								.append(COLON_WITH_SPACES).append(
										CALLINGMETHOD).append(
										DataUtil.getCallingMethod()).append(
										CURRENTMETHOD).append(
										DataUtil.getCurrentMethod()).append(
										DASHES).append(
										SESSIONID).append(
										request.getSession().getId()).append(
										DASHES).append(
										jdbcException).toString(), Level.ERROR);
				AuditUtil
						.getInstance()
						.writeLog(
								AuditUtil.FILE_TYPE_G4G,
								new StringBuffer(FriendsAjaxImpl.class
										.getName())
										.append(COLON_WITH_SPACES)
										.append(CALLINGMETHOD)
										.append(DataUtil.getCallingMethod())
										.append(CURRENTMETHOD)
										.append(DataUtil.getCurrentMethod())
										.append(DASHES)
										.append(
												FRIEND_REQUEST_ALREADY_MADE)
										.toString(), Level.INFO);

				return FRIEND_REQUEST_ALREADY_MADE;
			} catch (Exception e) {
				AuditUtil.getInstance().writeLog(
						AuditUtil.FILE_TYPE_G4G,
						new StringBuffer(FriendsAjaxImpl.class.getName())
								.append(COLON_WITH_SPACES).append(
										CALLINGMETHOD).append(
										DataUtil.getCallingMethod()).append(
										CURRENTMETHOD).append(
										DataUtil.getCurrentMethod()).append(
										DASHES).append(
										SESSIONID).append(
										request.getSession().getId()).append(
										DASHES).append(e)
								.toString(), Level.ERROR);
			}
			AuditUtil.getInstance().writeLog(
					AuditUtil.FILE_TYPE_G4G,
					new StringBuffer(FriendsAjaxImpl.class.getName()).append(
							COLON_WITH_SPACES).append(
							CALLINGMETHOD).append(
							DataUtil.getCallingMethod()).append(
							CURRENTMETHOD).append(
							DataUtil.getCurrentMethod()).append(
							DASHES).append(
							FRIEND_REQUEST_MADE).toString(),
					Level.INFO);
			AuditUtil.getInstance().writeLog(
					AuditUtil.FILE_TYPE_G4G,
					new StringBuffer(FriendsAjaxImpl.class.getName()).append(
							COLON_WITH_SPACES).append(
							CALLINGMETHOD).append(
							DataUtil.getCallingMethod()).append(
							CURRENTMETHOD).append(
							DataUtil.getCurrentMethod()).append(
							DASHES).append(ENDED)
							.append(DASHES).append(
									SESSIONID).append(
									request.getSession().getId()).toString(),
					Level.INFO);
			return FRIEND_REQUEST_MADE;

		}
	}

	/**
	 * The method addUserAsFriend is called when the user is accepted as friend.
	 * Adds the user as friend. Both will be friends of each other. It will
	 * update friends table for both the players are friends now.
	 * 
	 * @param toPlayerId
	 *            Id of player who sent request.
	 * @param fromPlayer
	 *            Id of player who accepted request.
	 * 
	 * @return the string that user is added as friend if friend table is
	 *         updated record found for given playerId else it gives blank.
	 */
	@SuppressWarnings(UNCHECKED)
	public static String addUserAsFriend(int toPlayerId, String fromPlayer) {
		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,
				new StringBuffer(FriendsAjaxImpl.class.getName()).append(
						COLON_WITH_SPACES).append(CALLINGMETHOD)
						.append(DataUtil.getCallingMethod()).append(
								CURRENTMETHOD).append(
								DataUtil.getCurrentMethod()).append(
								DASHES).append(
								STARTED).toString(), Level.INFO);

		SearchCriteria searchCriteria = new SearchCriteria();
		searchCriteria.setAttribute(SCREENNAME_DB, fromPlayer);
		List<PlayerDTO> playersList = PlayerServiceDelegator
				.getList(searchCriteria);

		if (playersList.size() > ZERO) {
			PlayerDTO playerDTO = playersList.get(ZERO);

			if (FriendsUtil.isFriend(playerDTO.getId(), toPlayerId)) {
				AuditUtil
						.getInstance()
						.writeLog(
								AuditUtil.FILE_TYPE_G4G,
								new StringBuffer(FriendsAjaxImpl.class
										.getName())
										.append(COLON_WITH_SPACES)
										.append(CALLINGMETHOD)
										.append(DataUtil.getCallingMethod())
										.append(CURRENTMETHOD)
										.append(DataUtil.getCurrentMethod())
										.append(DASHES)
										.append(
												FRIEND_REQUEST_ALREADY_MADE)
										.toString(), Level.INFO);
				return FRIEND_REQUEST_ALREADY_MADE;
			} else {
				SearchListCriteria searchListCriteria = new SearchListCriteria();
				PlayerDTO player1 = new PlayerDTO();
				player1.setId(playerDTO.getId());
				searchListCriteria.setAttribute(PLAYERBYPLAYERID1,
						new Object[] { player1, SearchListCriteria.EQ });

				PlayerDTO player2 = new PlayerDTO();
				player2.setId(toPlayerId);
				searchListCriteria.setAttribute(PLAYERBYPLAYERID2,
						new Object[] { player2, SearchListCriteria.EQ });

				List<FriendsDTO> friendsList = FriendsServiceDelegator
						.getList(searchListCriteria);
				if (friendsList.size() > ZERO) {
					FriendsDTO dto = friendsList.get(ZERO);
					dto.setPlayer2accepted(DataUtil.todayGMT());
					try {
						FriendsServiceDelegator.update(dto);
					} catch (Exception exception) {
						AuditUtil.getInstance().writeLog(
								AuditUtil.FILE_TYPE_HIBERNATE,
								new StringBuffer(FriendsAjaxImpl.class
										.getName()).append(COLON_WITH_SPACES)
										.append(CALLINGMETHOD)
										.append(DataUtil.getCallingMethod())
										.append(CURRENTMETHOD)
										.append(DataUtil.getCurrentMethod())
										.append(DASHES).append(
												exception).toString(),
								Level.ERROR);

					}
					AuditUtil.getInstance().writeLog(
							AuditUtil.FILE_TYPE_G4G,
							new StringBuffer(FriendsAjaxImpl.class.getName())
									.append(COLON_WITH_SPACES).append(
											CALLINGMETHOD).append(
											DataUtil.getCallingMethod())
									.append(CURRENTMETHOD).append(
											DataUtil.getCurrentMethod())
									.append(DASHES).append(
											USER_ADDED_AS_FRIEND)
									.toString(), Level.INFO);

					AuditUtil.getInstance().writeLog(
							AuditUtil.FILE_TYPE_G4G,
							new StringBuffer(FriendsAjaxImpl.class.getName())
									.append(COLON_WITH_SPACES).append(
											CALLINGMETHOD).append(
											DataUtil.getCallingMethod())
									.append(CURRENTMETHOD).append(
											DataUtil.getCurrentMethod())
									.append(DASHES).append(
											ENDED).toString(),
							Level.INFO);
					return USER_ADDED_AS_FRIEND;
				} else {
					AuditUtil.getInstance().writeLog(
							AuditUtil.FILE_TYPE_G4G,
							new StringBuffer(FriendsAjaxImpl.class.getName())
									.append(COLON_WITH_SPACES).append(
											CALLINGMETHOD).append(
											DataUtil.getCallingMethod())
									.append(CURRENTMETHOD).append(
											DataUtil.getCurrentMethod())
									.append(DASHES).append(
											ENDED).toString(),
							Level.INFO);
					return NONE;
				}
			}
		} else {
			AuditUtil.getInstance().writeLog(
					AuditUtil.FILE_TYPE_G4G,
					new StringBuffer(FriendsAjaxImpl.class.getName()).append(
							COLON_WITH_SPACES).append(
							CALLINGMETHOD).append(
							DataUtil.getCallingMethod()).append(
							CURRENTMETHOD).append(
							DataUtil.getCurrentMethod()).append(
							DASHES).append(ENDED)
							.toString(), Level.INFO);
			return NONE;
		}
	}

	/**
	 * The method removeFriendFromCurrentUser Removes the friend from current
	 * user. The entry from the friend table will be deleted. Both users will
	 * not be friend each other.
	 * 
	 * @param playerIdFrom
	 *            The id of player who removes the friend.
	 * @param playerIdTo
	 *            the id of player who is removed.
	 */
	@SuppressWarnings(UNCHECKED)
	public static void removeFriendFromCurrentUser(int playerIdFrom,
			int playerIdTo) {
		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,
				new StringBuffer(FriendsAjaxImpl.class.getName()).append(
						COLON_WITH_SPACES).append(CALLINGMETHOD)
						.append(DataUtil.getCallingMethod()).append(
								CURRENTMETHOD).append(
								DataUtil.getCurrentMethod()).append(
								DASHES).append(
								STARTED).toString(), Level.INFO);

		FriendsDTO dto = null;
		List<FriendsDTO> friendList = null;

		SearchListCriteria searchListCriteria = new SearchListCriteria();

		searchListCriteria.setAttribute(PLAYERBYPLAYERID1, new Object[] {
				new PlayerDTO(playerIdFrom), SearchListCriteria.EQ });
		searchListCriteria.setAttribute(PLAYERBYPLAYERID2, new Object[] {
				new PlayerDTO(playerIdTo), SearchListCriteria.EQ });

		friendList = FriendsServiceDelegator.getList(searchListCriteria);

		if (friendList.size() == ZERO) {
			searchListCriteria.removeAllAttribute();
			searchListCriteria.setAttribute(PLAYERBYPLAYERID2,
					new Object[] { new PlayerDTO(playerIdFrom), SearchListCriteria.EQ });
			searchListCriteria.setAttribute(PLAYERBYPLAYERID1,
					new Object[] { new PlayerDTO(playerIdTo), SearchListCriteria.EQ });
			friendList = FriendsServiceDelegator.getList(searchListCriteria);
		}

		if (friendList.size() != ZERO) {
			dto = friendList.get(ZERO);
			try {
				FriendsServiceDelegator.delete(dto);
			} catch (Exception exception) {

				AuditUtil.getInstance().writeLog(
						AuditUtil.FILE_TYPE_HIBERNATE,
						new StringBuffer(FriendsAjaxImpl.class.getName())
								.append(COLON_WITH_SPACES).append(
										CALLINGMETHOD).append(
										DataUtil.getCallingMethod()).append(
										CURRENTMETHOD).append(
										DataUtil.getCurrentMethod()).append(
										DASHES).append(exception)
								.toString(), Level.ERROR);
			}
		}
		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,
				new StringBuffer(FriendsAjaxImpl.class.getName()).append(
						COLON_WITH_SPACES).append(CALLINGMETHOD)
						.append(DataUtil.getCallingMethod()).append(
								CURRENTMETHOD).append(
								DataUtil.getCurrentMethod()).append(
								DASHES).append(
								STARTED).toString(), Level.INFO);
	}

	/**
	 * Instantiates a new friends ajax impl.
	 */
	private FriendsAjaxImpl() {
	}

	/**
	 * This class should not be instantiated and cloned.
	 * 
	 * @return the object
	 * 
	 * @throws CloneNotSupportedException
	 *             the clone not supported exception
	 */
	@Override
	public FriendsAjaxImpl clone() throws CloneNotSupportedException {
		throw new CloneNotSupportedException();
	}
}
