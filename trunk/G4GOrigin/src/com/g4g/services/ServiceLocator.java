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

import static com.g4g.utils.G4GConstants.ADIMIN_SERVICE;
import static com.g4g.utils.G4GConstants.ADMIN_NOTIFICATION_SERVICE;
import static com.g4g.utils.G4GConstants.AVATAR_SERVICE;
import static com.g4g.utils.G4GConstants.BREAKING_NEWS_SERVICE;
import static com.g4g.utils.G4GConstants.FEEDBACK_SERVICE;
import static com.g4g.utils.G4GConstants.FRIENDS_SERVICE;
import static com.g4g.utils.G4GConstants.GAME_CATEGORY_SERVICE;
import static com.g4g.utils.G4GConstants.GAME_OPTION_SERVICE;
import static com.g4g.utils.G4GConstants.GAME_SERVICE;
import static com.g4g.utils.G4GConstants.HIBERNATE;
import static com.g4g.utils.G4GConstants.MATCH_COMMENTS_SERVICE;
import static com.g4g.utils.G4GConstants.MESSAGE_SERVICE;
import static com.g4g.utils.G4GConstants.NATIONAL_CODE_SERVICE;
import static com.g4g.utils.G4GConstants.NETWORK_SERVICE;
import static com.g4g.utils.G4GConstants.NOTIFICATION_QUEUE_SERVICE;
import static com.g4g.utils.G4GConstants.NOTIFICATION_SERVICE;
import static com.g4g.utils.G4GConstants.NOTIFICATION_TYPE_SERVICE;
import static com.g4g.utils.G4GConstants.PICTURE_SERVICE;
import static com.g4g.utils.G4GConstants.PLAYER_COMMENTS_SERVICE;
import static com.g4g.utils.G4GConstants.PLAYER_GAME_SERVICE;
import static com.g4g.utils.G4GConstants.PLAYER_IM_SERVICE;
import static com.g4g.utils.G4GConstants.PLAYER_NETWORK_SERVICE;
import static com.g4g.utils.G4GConstants.PLAYER_SERVICE;
import static com.g4g.utils.G4GConstants.SKIN_SERVICE;
import static com.g4g.utils.G4GConstants.SUB_NATIONAL_CODE_SERVICE;
import static com.g4g.utils.G4GConstants.TIMEZONE_SERVICE;
import static com.g4g.utils.G4GConstants.TWO_PLAYER_MATCH_SERVICE;
import static com.g4g.utils.G4GConstants.TWO_PLAYER_TOURNAMENT_GAME_OPTION_SERVICE;
import static com.g4g.utils.G4GConstants.TWO_PLAYER_TOURNAMENT_SERVICE;
import static com.g4g.utils.G4GConstants.WIDGETS_SERVICE;
import gnu.trove.THashMap;

import com.g4g.utils.G4GConstants;

/**
 * Locates services for different delegators.
 * 
 * @author ankur
 * 
 */
@SuppressWarnings( { G4GConstants.CLONE_DOES_NOT_CALL_SUPER_CLONE })
public class ServiceLocator implements Cloneable {

	private static ServiceLocator locator = null;

	// $ANALYSIS-IGNORE
	/**
	 */
	@Override
	public Object clone() throws CloneNotSupportedException {
		throw new CloneNotSupportedException();
	}

	/**
	 * Creates instance of service locator.
	 * 
	 * @return ServiceLocator
	 */
	public static ServiceLocator getInstance() {
		if (locator == null) {
			locator = new ServiceLocator();
		}
		return locator;
	}

	private final String DATABASE = HIBERNATE;

	// Static variables to keep Singlton object

	private THashMap<String, Object> ServiceCache = new THashMap<String, Object>();

	// Other class can not create ServiceLocator instance
	private ServiceLocator() {
	}

	/**
	 * Creates service for adminServiceDelegator.
	 * 
	 * @return AdminService
	 */
	public AdminService getAdminService() {
		AdminService service = (AdminService) ServiceCache.get(ADIMIN_SERVICE);
		if (service == null) {
			if (DATABASE.equals(HIBERNATE)) {
				service = new AdminServiceImpl();
			}
			ServiceCache.put(ADIMIN_SERVICE, service);
		}
		return service;
	}

	/**
	 * @return NotificationTypeService
	 */
	public NotificationTypeService getNotificationTypeService() {
		NotificationTypeService service = (NotificationTypeService) ServiceCache
				.get(NOTIFICATION_TYPE_SERVICE);
		if (service == null) {
			if (DATABASE.equals(HIBERNATE)) {
				service = new NotificationTypeServiceImpl();
			}
			ServiceCache.put(NOTIFICATION_TYPE_SERVICE, service);
		}
		return service;
	}

	/**
	 * Creates service for adminServiceDelegator.
	 * 
	 * @return SkinService
	 */
	public SkinService getSkinService() {
		SkinService service = (SkinService) ServiceCache.get(SKIN_SERVICE);
		if (service == null) {
			if (DATABASE.equals(HIBERNATE)) {
				service = new SkinServiceImpl();
			}
			ServiceCache.put(SKIN_SERVICE, service);
		}
		return service;
	}

	/**
	 * Creates BreakingNewsService instance for BreakingNewsServiceDelegator.
	 * 
	 * @return BreakingNewsServices
	 */
	public BreakingNewsServices getBreakingNewsServices() {
		BreakingNewsServices service = (BreakingNewsServices) ServiceCache
				.get(BREAKING_NEWS_SERVICE);
		if (service == null) {
			if (DATABASE.equals(HIBERNATE)) {
				service = new BreakingNewsServiceImpl();
			}
			ServiceCache.put(BREAKING_NEWS_SERVICE, service);
		}
		return service;
	}

	/**
	 * Creates MatchCommentService instance for MatchCommentServiceDelegator.
	 * 
	 * @return MatchCommentService
	 */
	public MatchCommentService getMatchCommentService() {
		MatchCommentService service = (MatchCommentService) ServiceCache
				.get(MATCH_COMMENTS_SERVICE);
		if (service == null) {
			if (DATABASE.equals(HIBERNATE)) {
				service = new MatchCommentServiceImpl();
			}
			ServiceCache.put(MATCH_COMMENTS_SERVICE, service);
		}
		return service;
	}

	/**
	 * Creates TimeZoneService instance for TimeZoneServiceDelegator.
	 * 
	 * @return TimeZoneService
	 */
	public TimeZoneService getTimeZoneService() {
		TimeZoneService service = (TimeZoneService) ServiceCache
				.get(TIMEZONE_SERVICE);
		if (service == null) {
			if (DATABASE.equals(HIBERNATE)) {
				service = new TimeZoneServiceImpl();
			}
			ServiceCache.put(TIMEZONE_SERVICE, service);
		}
		return service;
	}

	/**
	 * Creates AvatarsService instance for AvatarsServiceDelegator.
	 * 
	 * @return AvatarsService
	 */
	public AvatarsService getAvatarsServiceService() {
		AvatarsService Service = (AvatarsService) ServiceCache
				.get(AVATAR_SERVICE);
		if (Service == null) {
			if (DATABASE.equals(HIBERNATE)) {
				Service = new AvatarServiceImpl();
			}
			ServiceCache.put(AVATAR_SERVICE, Service);
		}
		return Service;
	}

	/**
	 * Creates FeedbackService instance for FeedbackServiceDelegator.
	 * 
	 * @return FeedbackService
	 */
	public FeedbackService getFeedbackService() {
		FeedbackService service = (FeedbackService) ServiceCache
				.get(FEEDBACK_SERVICE);
		if (service == null) {
			if (DATABASE.equals(HIBERNATE)) {
				service = new FeedbackServiceImpl();
			}
			ServiceCache.put(FEEDBACK_SERVICE, service);
		}
		return service;
	}

	/**
	 * Creates FriendsService instance for FriendsServiceDelegator.
	 * 
	 * @return FriendsService
	 */
	public FriendsService getFriendsService() {
		FriendsService service = (FriendsService) ServiceCache
				.get(FRIENDS_SERVICE);
		if (service == null) {
			if (DATABASE.equals(HIBERNATE)) {
				service = new FriendsServiceImpl();
			}
			ServiceCache.put(FRIENDS_SERVICE, service);
		}
		return service;
	}

	/**
	 * Creates GameCategoryService instance for GameCategoryServiceDelegator.
	 * 
	 * @return GameCategoryService
	 */
	public GameCategoryService getGameCategoryService() {
		GameCategoryService Service = (GameCategoryService) ServiceCache
				.get(GAME_CATEGORY_SERVICE);
		if (Service == null) {
			if (DATABASE.equals(HIBERNATE)) {
				Service = new GameCategoryServiceImpl();
			}
			ServiceCache.put(GAME_CATEGORY_SERVICE, Service);
		}
		return Service;
	}

	/**
	 * Creates GameOptionService instance for GameOptionServiceDelegator.
	 * 
	 * @return GameOptionService
	 */
	public GameOptionService getGameOptionService() {
		GameOptionService Service = (GameOptionService) ServiceCache
				.get(GAME_OPTION_SERVICE);
		if (Service == null) {
			if (DATABASE.equals(HIBERNATE)) {
				Service = new GameOptionServiceImpl();
			}
			ServiceCache.put(GAME_OPTION_SERVICE, Service);
		}
		return Service;
	}

	/**
	 * Creates GameService instance for GameServiceDelegator.
	 * 
	 * @return GameService
	 */
	public GameService getGameService() {
		GameService service = (GameService) ServiceCache.get(GAME_SERVICE);
		if (service == null) {
			if (DATABASE.equals(HIBERNATE)) {
				service = new GameServiceImpl();
			}
			ServiceCache.put(GAME_SERVICE, service);
		}
		return service;
	}

	/**
	 * Creates MessageService instance for MessageServiceDelegator.
	 * 
	 * @return MessageService
	 */
	public MessageService getMessageService() {
		MessageService service = (MessageService) ServiceCache
				.get(MESSAGE_SERVICE);
		if (service == null) {
			if (DATABASE.equals(HIBERNATE)) {
				service = new MessageServiceImpl();
			}
			ServiceCache.put(MESSAGE_SERVICE, service);
		}
		return service;
	}

	/**
	 * Creates NationalCodeService instance for NationalCodeServiceDelegator.
	 * 
	 * @return NationalCodeService
	 */
	public NationalCodeService getNationalCodeService() {
		NationalCodeService Service = (NationalCodeService) ServiceCache
				.get(NATIONAL_CODE_SERVICE);
		if (Service == null) {
			if (DATABASE.equals(HIBERNATE)) {
				Service = new NationalCodeServiceImpl();
			}
			ServiceCache.put(NATIONAL_CODE_SERVICE, Service);
		}
		return Service;
	}

	/**
	 * Creates NetworkService instance for NetworkServiceDelegator.
	 * 
	 * @return NetworkService
	 */
	public NetworkService getNetworkService() {
		NetworkService service = (NetworkService) ServiceCache
				.get(NETWORK_SERVICE);
		if (service == null) {
			if (DATABASE.equals(HIBERNATE)) {
				service = new NetworkServiceImpl();
			}
			ServiceCache.put(NETWORK_SERVICE, service);
		}
		return service;
	}

	/**
	 * Creates PicturesService instance for PicturesServiceDelegator.
	 * 
	 * @return PicturesService
	 */
	public PicturesService getPicturesService() {
		PicturesService Service = (PicturesService) ServiceCache
				.get(PICTURE_SERVICE);
		if (Service == null) {
			if (DATABASE.equals(HIBERNATE)) {
				Service = new PicturesServiceImpl();
			}
			ServiceCache.put(PICTURE_SERVICE, Service);
		}
		return Service;
	}

	/**
	 * Creates PlayerCommnetsService instance for
	 * PlayerCommnetsServiceDelegator.
	 * 
	 * @return PlayerCommnetsService
	 */
	public PlayerCommnetsService getPlayerCommentsService() {
		PlayerCommnetsService service = (PlayerCommnetsService) ServiceCache
				.get(PLAYER_COMMENTS_SERVICE);
		if (service == null) {
			if (DATABASE.equals(HIBERNATE)) {
				service = new PlayerCommentsServiceImpl();
			}
			ServiceCache.put(PLAYER_COMMENTS_SERVICE, service);
		}
		return service;
	}

	/**
	 * Creates PlayerGameService instance for PlayerGameServiceDelegator.
	 * 
	 * @return PlayerGameService
	 */
	public PlayerGameService getPlayerGameService() {
		PlayerGameService service = (PlayerGameService) ServiceCache
				.get(PLAYER_GAME_SERVICE);
		if (service == null) {
			if (DATABASE.equals(HIBERNATE)) {
				service = new PlayerGameServiceImpl();
			}
			ServiceCache.put(PLAYER_GAME_SERVICE, service);
		}
		return service;
	}

	/**
	 * Creates PlayerImService instance for PlayerImServiceDelegator.
	 * 
	 * @return PlayerImService
	 */
	public PlayerImService getPlayerImService() {
		PlayerImService Service = (PlayerImService) ServiceCache
				.get(PLAYER_IM_SERVICE);
		if (Service == null) {
			if (DATABASE.equals(HIBERNATE)) {
				Service = new PlayerImServiceImpl();
			}
			ServiceCache.put(PLAYER_IM_SERVICE, Service);
		}
		return Service;
	}

	/**
	 * Creates PlayerNetworkService instance for PlayerNetworkServiceDelegator.
	 * 
	 * @return PlayerNetworkService
	 */
	public PlayerNetworkService getPlayerNetworkService() {
		PlayerNetworkService Service = (PlayerNetworkService) ServiceCache
				.get(PLAYER_NETWORK_SERVICE);
		if (Service == null) {
			if (DATABASE.equals(HIBERNATE)) {
				Service = new PlayerNetworkServiceImpl();
			}
			ServiceCache.put(PLAYER_NETWORK_SERVICE, Service);
		}
		return Service;
	}

	/**
	 * Creates PlayerService instance for PlayerServiceDelegator.
	 * 
	 * @return PlayerService
	 */
	public PlayerService getPlayerService() {
		PlayerService service = (PlayerService) ServiceCache
				.get(PLAYER_SERVICE);
		if (service == null) {
			if (DATABASE.equals(HIBERNATE)) {
				service = new PlayerServiceImpl();
			}
			ServiceCache.put(PLAYER_SERVICE, service);
		}
		return service;
	}

	/**
	 * Creates SubNationalCodeService instance for
	 * SubNationalCodeServiceDelegator.
	 * 
	 * @return SubNationalCodeService
	 */
	public SubNationalCodeService getSubNationalCodeService() {
		SubNationalCodeService Service = (SubNationalCodeService) ServiceCache
				.get(SUB_NATIONAL_CODE_SERVICE);
		if (Service == null) {
			if (DATABASE.equals(HIBERNATE)) {
				Service = new SubNationalCodeServiceImpl();
			}
			ServiceCache.put(SUB_NATIONAL_CODE_SERVICE, Service);
		}
		return Service;
	}

	/**
	 * Creates TwoPlayerMatchService instance for
	 * TwoPlayerMatchServiceDelegator.
	 * 
	 * @return TwoPlayerMatchService
	 */
	public TwoPlayerMatchService getTwoPlayerMatchService() {
		TwoPlayerMatchService Service = (TwoPlayerMatchService) ServiceCache
				.get(TWO_PLAYER_MATCH_SERVICE);
		if (Service == null) {
			if (DATABASE.equals(HIBERNATE)) {
				Service = new TwoPlayerMatchServiceImpl();
			}
			ServiceCache.put(TWO_PLAYER_MATCH_SERVICE, Service);
		}
		return Service;
	}

	/**
	 * Creates TwoPlayerTournamentService instance for
	 * TwoPlayerTournamentServiceDelegator.
	 * 
	 * @return TwoPlayerTournamentService
	 */
	public TwoPlayerTournamentService getTwoPlayerTournamentService() {
		TwoPlayerTournamentService Service = (TwoPlayerTournamentService) ServiceCache
				.get(TWO_PLAYER_TOURNAMENT_SERVICE);
		if (Service == null) {
			if (DATABASE.equals(HIBERNATE)) {
				Service = new TwoPlayerTournamentServiceImpl();
			}
			ServiceCache.put(TWO_PLAYER_TOURNAMENT_SERVICE, Service);
		}
		return Service;
	}

	/**
	 * Creates TwoPlayerTournamentGameOptionService instance for
	 * TwoPlayerTournamentGameOptionServiceDelegator.
	 * 
	 * @return TwoPlayerTournamentGameOptionService
	 */
	public TwoPlayerTournamentGameOptionService getTwoPlayerTournamentGameOptionService() {
		TwoPlayerTournamentGameOptionService Service = (TwoPlayerTournamentGameOptionService) ServiceCache
				.get(TWO_PLAYER_TOURNAMENT_GAME_OPTION_SERVICE);
		if (Service == null) {
			if (DATABASE.equals(HIBERNATE)) {
				Service = new TwoPlayerTournamentGameOptionServiceImpl();
			}
			ServiceCache
					.put(TWO_PLAYER_TOURNAMENT_GAME_OPTION_SERVICE, Service);
		}
		return Service;
	}

	/**
	 * Creates WidgetsService instance for WidgetsServiceDelegator.
	 * 
	 * @return WidgetsService
	 */
	public WidgetsService getWidgetsService() {
		WidgetsService service = (WidgetsService) ServiceCache
				.get(WIDGETS_SERVICE);
		if (service == null) {
			if (DATABASE.equals(HIBERNATE)) {
				service = new WidgetsServiceImpl();
			}
			ServiceCache.put(WIDGETS_SERVICE, service);
		}
		return service;
	}

	/**
	 * Creates AdminNotificationService instance for
	 * AdminNotificationServiceDelegator.
	 * 
	 * @return AdminNotificationService
	 */
	public AdminNotificationService getAdminNotificationService() {
		AdminNotificationService Service = (AdminNotificationService) ServiceCache
				.get(ADMIN_NOTIFICATION_SERVICE);
		if (Service == null) {
			if (DATABASE.equals(HIBERNATE)) {
				Service = new AdminNotificationServiceImpl();
			}
			ServiceCache.put(ADMIN_NOTIFICATION_SERVICE, Service);
		}
		return Service;
	}

	/**
	 * Creates NotificationService instance for NotificationServiceDelegator.
	 * 
	 * @return NotificationService
	 */
	public NotificationService getNotificationService() {
		NotificationService Service = (NotificationService) ServiceCache
				.get(NOTIFICATION_SERVICE);
		if (Service == null) {
			if (DATABASE.equals(HIBERNATE)) {
				Service = new NotificationServiceImpl();
			}
			ServiceCache.put(NOTIFICATION_SERVICE, Service);
		}
		return Service;
	}

	/**
	 * Creates NotificationQueueService instance for
	 * NotificationQueueServiceDelegator.
	 * 
	 * @return NotificationQueueService
	 */
	public NotificationQueueService getNotificationQueueService() {
		NotificationQueueService Service = (NotificationQueueService) ServiceCache
				.get(NOTIFICATION_QUEUE_SERVICE);
		if (Service == null) {
			if (DATABASE.equals(HIBERNATE)) {
				Service = new NotificationQueueServiceImpl();
			}
			ServiceCache.put(NOTIFICATION_QUEUE_SERVICE, Service);
		}
		return Service;
	}
}