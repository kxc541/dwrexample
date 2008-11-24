/**********************************************************
 * GameLobbyAjaxImpl.java :
 *
 * Created by Jigar Mistry
 * Last modified Date: 18 Apr .08 by Punam
 * Revision: 0.1
 * Version : 0.0.8
 * Copyright (c) 2008 - 2008 Askdigi, All rights reserved.
 **********************************************************/
package com.g4g.servlet;

import static com.g4g.utils.G4GConstants.CALLINGMETHOD;
import static com.g4g.utils.G4GConstants.COLON_WITH_SPACES;
import static com.g4g.utils.G4GConstants.CURRENTMETHOD;
import static com.g4g.utils.G4GConstants.DASHES;
import static com.g4g.utils.G4GConstants.ENDED;
import static com.g4g.utils.G4GConstants.GAME;
import static com.g4g.utils.G4GConstants.GAME_LIST_SIZE_CRITERIA;
import static com.g4g.utils.G4GConstants.PLAYER;
import static com.g4g.utils.G4GConstants.PLAYER_GAME_LIST_SIZE_CRITERIA;
import static com.g4g.utils.G4GConstants.SESSIONID;
import static com.g4g.utils.G4GConstants.STARTED;
import static com.g4g.utils.G4GConstants.ZERO;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Level;
import org.hibernate.HibernateException;

import com.g4g.delegator.GameServiceDelegator;
import com.g4g.delegator.PlayerGameServiceDelegator;
import com.g4g.delegator.PlayerServiceDelegator;
import com.g4g.dto.GameDTO;
import com.g4g.dto.PlayerDTO;
import com.g4g.dto.PlayergameDTO;
import com.g4g.dto.SearchCriteria;
import com.g4g.utils.ApplicationData;
import com.g4g.utils.AuditUtil;
import com.g4g.utils.DataUtil;
import com.g4g.utils.G4GConstants;
import com.g4g.utils.GamesUtil;

// $ANALYSIS-IGNORE
/**
 * The GameLobbyAjaxImpl Class contains ajax implementation related to game
 * lobby. It retrieves the games specific to particular console. The games that
 * this user owns, It adds the game to user game, Removes the game from the user
 * game.
 *
 * @author Jigar Mistry
 */
@SuppressWarnings( { G4GConstants.CLONE_DOES_NOT_CALL_SUPER_CLONE })
public class GameLobbyAjaxImpl {

	private static final String UNCHECKED = "unchecked"; //$NON-NLS-1$

	/**
	 * The method getPlatformSpecificGamesHTMLFormat contains HTML content to
	 * fill out the game lobby differentiated by their consoles.And also
	 * contains the tab to display User's own games. Gets the platform specific
	 * games in html format. User can visit the gameDetails page by clicking on
	 * game name.
	 *
	 * User can add game if he/she has that console. else can view the gamelobby
	 * but cannot add the game if he/she does not have the console, if he/she
	 * wants to add has to purchase that console.
	 *
	 * @param platformID
	 *            the platformId of the console to get games of given consoleId.
	 * @param inPlayerId
	 *            the playerId to check whether he/she has this console.
	 * @param request
	 *            the request object to get the values from the session.
	 *
	 * @return the platform specific games html format according to given
	 *         platform.
	 */
	public static String getPlatformSpecificGamesHTMLFormat(String platformID,
			String inPlayerId, HttpServletRequest request) {
		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,
				new StringBuffer(GameLobbyAjaxImpl.class.getName()).append(
						COLON_WITH_SPACES).append(CALLINGMETHOD).append(
						DataUtil.getCallingMethod()).append(CURRENTMETHOD)
						.append(DataUtil.getCurrentMethod()).append(DASHES)
						.append(STARTED).append(DASHES).append(SESSIONID)
						.append(request.getSession().getId()).toString(),
				Level.INFO);

		StringBuffer html = new StringBuffer();
		ApplicationData appData = ApplicationData.getApplicationData();
		HashMap<String, String> playerGames = new HashMap<String, String>();
		boolean isAvailable = GamesUtil.isNetWorkAvailable(Integer
				.parseInt(inPlayerId), Integer.parseInt(platformID));

		List<PlayergameDTO> playerGamesList = getPlayerSpecificGamesList(inPlayerId);
		if (playerGamesList != null) {
			int size = playerGamesList.size();
			AuditUtil.getInstance().writeLog(
					AuditUtil.FILE_TYPE_G4G,
					new StringBuffer(GameLobbyAjaxImpl.class.getName()).append(
							COLON_WITH_SPACES).append(CALLINGMETHOD).append(
							DataUtil.getCallingMethod()).append(CURRENTMETHOD)
							.append(DataUtil.getCurrentMethod()).append(DASHES)
							.append(PLAYER_GAME_LIST_SIZE_CRITERIA).append(
									playerGamesList.size()).toString(),
					Level.INFO);
			for (int i = ZERO; i < size; i++) {
				PlayergameDTO playergameDTO = playerGamesList.get(i);
				playerGames.put(
						String.valueOf(playergameDTO.getGame().getId()), String
								.valueOf(playergameDTO.getId()));
			}
		}
		if (appData != null) {
			List<GameDTO> gamesList = appData.getNetworkBasedGames(platformID);
			if (gamesList != null) {
				AuditUtil.getInstance().writeLog(
						AuditUtil.FILE_TYPE_G4G,
						new StringBuffer(GameLobbyAjaxImpl.class.getName())
								.append(COLON_WITH_SPACES)
								.append(CALLINGMETHOD).append(
										DataUtil.getCallingMethod()).append(
										CURRENTMETHOD).append(
										DataUtil.getCurrentMethod()).append(
										DASHES).append(GAME_LIST_SIZE_CRITERIA)
								.append(gamesList.size()).toString(),
						Level.INFO);
				int size = gamesList.size();
				for (int i = ZERO; i < size; i++) {
					GameDTO game = gamesList.get(i);
					if (game != null) {
						String encryptedGameId = DataUtil.encrypt(String
								.valueOf(game.getId()));
						String add = G4GConstants.BLANK;

						if (playerGames != null
									&& playerGames.containsKey(String
											.valueOf(game.getId()))) {
							add = G4GConstants.FALSE;
						} else {
							add = G4GConstants.TRUE;
						}
						String encyptedImgSrc = G4GConstants.NONE;
						try {
							encyptedImgSrc = URLEncoder.encode(game
									.getImgsrc(), G4GConstants.UTF8);
						} catch (UnsupportedEncodingException e) {
							AuditUtil
									.getInstance()
									.writeLog(
										AuditUtil.FILE_TYPE_G4G,
											new StringBuffer(
													GameLobbyAjaxImpl.class
															.getName())
													.append(
															COLON_WITH_SPACES)
													.append(CALLINGMETHOD)
													.append(
															DataUtil
																	.getCallingMethod())
													.append(CURRENTMETHOD)
													.append(
															DataUtil
																	.getCurrentMethod())
													.append(DASHES).append(
															e.getMessage())
													.toString(),
											Level.ERROR);
						}
						html.append(DataUtil.getURLContent(new StringBuffer(
												DataUtil
														.getLocalBasePath(request))
												.append(
														G4GConstants.WEBCONTENT_NAME)
												.append(G4GConstants.SLASH)
												.append(G4GConstants.AJAX)
												.append(G4GConstants.SLASH)
												.append(
														G4GConstants.PLATFORM_GAMES_JSP)
												.append(
														G4GConstants.QUESTIONMARK)
												.append(
														G4GConstants.ENCRYPTEDID)
												.append(G4GConstants.EQUALS)
												.append(encryptedGameId)
												.append(
														G4GConstants.AMBERSAND)
												.append(G4GConstants.ADD)
												.append(G4GConstants.EQUALS)
												.append(add)
												.append(
														G4GConstants.AMBERSAND)
												.append(G4GConstants.GAMEID)
												.append(G4GConstants.EQUALS)
												.append(game.getId())
												.append(
														G4GConstants.AMBERSAND)
												.append(G4GConstants.IMGSRC)
												.append(G4GConstants.EQUALS)
												.append(encyptedImgSrc)
												.append(
														G4GConstants.AMBERSAND)
												.append(
														G4GConstants.CONSOLE)
												.append(G4GConstants.EQUALS)
												.append(
														game.getNetwork()
																.getId())
												.append(
														G4GConstants.AMBERSAND)
												.append(
														G4GConstants.IS_LEGAL)
												.append(G4GConstants.EQUALS)
												.append(isAvailable)
												.toString()));

					}
				}
			}
		}
		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,
				new StringBuffer(GameLobbyAjaxImpl.class.getName()).append(
						COLON_WITH_SPACES).append(CALLINGMETHOD).append(
						DataUtil.getCallingMethod()).append(CURRENTMETHOD)
						.append(DataUtil.getCurrentMethod()).append(DASHES)
						.append(ENDED).append(DASHES).append(SESSIONID).append(
								request.getSession().getId()).toString(),
				Level.INFO);
		return html.toString();
	}

	/**
	 * The method getPlayerSpecificGamesHTMLFormat returns gameLobby HTML format
	 * string. It tells which games user has selected in his profile. User can
	 * remove the specific game if he/she doesn't want it. By clicking on
	 * gamename user can visit game details page.
	 *
	 * @param inPlayerId
	 *            the playerId to check whether he/she has this console.
	 * @param request
	 *            the request object to get the values from the session.
	 *
	 * @return the platform specific games html format according for given
	 *         playerid.
	 */
	@SuppressWarnings(UNCHECKED)
	public static String getPlayerSpecificGamesHTMLFormat(String inPlayerId,
			HttpServletRequest request) {
		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,
				new StringBuffer(GameLobbyAjaxImpl.class.getName()).append(
						COLON_WITH_SPACES).append(CALLINGMETHOD).append(
						DataUtil.getCallingMethod()).append(CURRENTMETHOD)
						.append(DataUtil.getCurrentMethod()).append(DASHES)
						.append(STARTED).append(DASHES).append(SESSIONID)
						.append(request.getSession().getId()).toString(),
				Level.INFO);
		StringBuffer html = new StringBuffer();
		if (inPlayerId != null) {
			List<PlayergameDTO> gamesList = getPlayerSpecificGamesList(inPlayerId);
			if (gamesList != null) {
				AuditUtil.getInstance().writeLog(
						AuditUtil.FILE_TYPE_G4G,
						new StringBuffer(GameLobbyAjaxImpl.class.getName())
								.append(COLON_WITH_SPACES)
								.append(CALLINGMETHOD).append(
										DataUtil.getCallingMethod()).append(
										CURRENTMETHOD).append(
										DataUtil.getCurrentMethod()).append(
										DASHES).append(
										PLAYER_GAME_LIST_SIZE_CRITERIA).append(
										gamesList.size()).toString(),
						Level.INFO);
				int size = gamesList.size();
				for (int i = ZERO; i < size; i++) {
					PlayergameDTO playergameDTO = gamesList.get(i);
					GameDTO game = GameServiceDelegator.getGame(playergameDTO
							.getGame().getId());
					String encryptedGameId = DataUtil.encrypt(String
							.valueOf(game.getId()));

					if (game != null) {
						try {
							String encodedSrc = URLEncoder.encode(game
									.getImgsrc(), G4GConstants.UTF8);
							AuditUtil
									.getInstance()
									.writeLog(
											AuditUtil.FILE_TYPE_G4G,
											GameLobbyAjaxImpl.class.getName()
													+ G4GConstants.URL
													+ new StringBuffer(
															DataUtil
																	.getBasePath(request))
															.append(
																	G4GConstants.WEBCONTENT_NAME)
															.append(
																	G4GConstants.SLASH)
															.append(
																	G4GConstants.AJAX)
															.append(
																	G4GConstants.SLASH)
															.append(
																	G4GConstants.USERGAMES_JSP)
															.append(
																	G4GConstants.ID)
															.append(
																	G4GConstants.EQUALS)
															.append(
																	encryptedGameId)
															.append(
																	G4GConstants.AMBERSAND)
															.append(
																	G4GConstants.IMGSRC)
															.append(
																	G4GConstants.EQUALS)
															.append(encodedSrc)
															.append(
																	G4GConstants.AMBERSAND)
															.append(
																	G4GConstants.CONSOLE)
															.append(
																	G4GConstants.EQUALS)
															.append(
																	game
																			.getNetwork()
																			.getId())
															.toString());
							html.append(DataUtil
									.getURLContent(new StringBuffer(DataUtil
											.getLocalBasePath(request)).append(
											G4GConstants.WEBCONTENT_NAME)
											.append(G4GConstants.SLASH).append(
													G4GConstants.AJAX).append(
													G4GConstants.SLASH).append(
													G4GConstants.USERGAMES_JSP)
											.append(G4GConstants.ID).append(
													G4GConstants.EQUALS)
											.append(encryptedGameId).append(
													G4GConstants.AMBERSAND)
											.append(G4GConstants.IMGSRC)
											.append(G4GConstants.EQUALS)
											.append(encodedSrc).append(
													G4GConstants.AMBERSAND)
											.append(G4GConstants.CONSOLE)
											.append(G4GConstants.EQUALS)
											.append(game.getNetwork().getId())
											.toString()));
						} catch (UnsupportedEncodingException e) {
							AuditUtil
									.getInstance()
									.writeLog(
											AuditUtil.FILE_TYPE_G4G,
											new StringBuffer(
													GameLobbyAjaxImpl.class
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
													.append(ENDED)
													.append(DASHES)
													.append(SESSIONID)
													.append(
															request
																	.getSession()
																	.getId())
													.toString(), Level.INFO);
						}
					}
				}
			}
		}
		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,
				new StringBuffer(GameLobbyAjaxImpl.class.getName()).append(
						COLON_WITH_SPACES).append(CALLINGMETHOD).append(
						DataUtil.getCallingMethod()).append(CURRENTMETHOD)
						.append(DataUtil.getCurrentMethod()).append(DASHES)
						.append(ENDED).append(DASHES).append(SESSIONID).append(
								request.getSession().getId()).toString(),
				Level.INFO);
		return html.toString();
	}

	/**
	 * The addGamesToPlayer method is used to add selected game to player's
	 * profile. The game added will be displayed in myGames tab in game lobby as
	 * well as where the users games are displayed. As soon as the game is added
	 * to player's profile it will contain the symbol to remove it at specific
	 * platform it belongs to.<br>
	 * When the game is added it will also added to my lobby of the user at all
	 * places where user's games are displayed.
	 *
	 * @param inPlayerId
	 *            The id of user who wants to add game.
	 * @param inGameId
	 *            The game id which is to be added.
	 * @param request
	 *            the request object to get the values from session as
	 *            siteid,..etc.
	 *
	 * @return the string containing the html content of the game is added.
	 */
	public static String addGamesToPlayer(String inPlayerId, String inGameId,
			HttpServletRequest request) {
		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,
				new StringBuffer(GameLobbyAjaxImpl.class.getName()).append(
						COLON_WITH_SPACES).append(CALLINGMETHOD).append(
						DataUtil.getCallingMethod()).append(CURRENTMETHOD)
						.append(DataUtil.getCurrentMethod()).append(DASHES)
						.append(STARTED).append(DASHES).append(SESSIONID)
						.append(request.getSession().getId()).toString(),
				Level.INFO);

		String encryptedGameId = inGameId;
		inGameId = DataUtil.decrypt(inGameId);

		StringBuffer html = new StringBuffer();
		if (inPlayerId != null && inGameId != null) {
			PlayergameDTO playerGameDTO = new PlayergameDTO();
			playerGameDTO.setGame(GameServiceDelegator.getGame(Integer
					.parseInt(inGameId)));
			playerGameDTO.setPlayer(PlayerServiceDelegator.getPlayer(Integer
					.parseInt(inPlayerId)));
			playerGameDTO.setCreationdate(DataUtil.todayGMT());
			playerGameDTO.setLastupdatedate(DataUtil.todayGMT());
			try {
				PlayerGameServiceDelegator.add(playerGameDTO);
				GameDTO game = GameServiceDelegator.getGame(playerGameDTO.getGame()
						.getId());
				String encryptedImgSrc = G4GConstants.NONE;
				encryptedImgSrc = URLEncoder.encode(game.getImgsrc(), G4GConstants.UTF8);
				html.append(DataUtil.getURLContent(new StringBuffer(DataUtil
						.getLocalBasePath(request)).append(
						G4GConstants.WEBCONTENT_NAME)
						.append(G4GConstants.SLASH).append(G4GConstants.AJAX)
						.append(G4GConstants.SLASH).append(
								G4GConstants.ADD_REMOVE_GAMES_JSP).append(
								G4GConstants.QUESTIONMARK).append(
								G4GConstants.ENCRYPTEDID).append(
								G4GConstants.EQUALS).append(encryptedGameId)
						.append(G4GConstants.AMBERSAND).append(
								G4GConstants.IMGSRC)
						.append(G4GConstants.EQUALS).append(encryptedImgSrc)
						.append(G4GConstants.AMBERSAND).append(
								G4GConstants.GAMEID)
						.append(G4GConstants.EQUALS).append(game.getId())
						.append(G4GConstants.AMBERSAND)
						.append(G4GConstants.ADD).append(G4GConstants.EQUALS)
						.append(G4GConstants.ADD).toString()));
			} catch (HibernateException exception) {
				AuditUtil.getInstance().writeLog(
						AuditUtil.FILE_TYPE_G4G,
						new StringBuffer(GameLobbyAjaxImpl.class.getName())
								.append(COLON_WITH_SPACES).append(CALLINGMETHOD).append(
										DataUtil.getCallingMethod()).append(
										CURRENTMETHOD).append(
										DataUtil.getCurrentMethod()).append(
										DASHES).append(SESSIONID).append(
										request.getSession().getId()).append(
										DASHES).append(exception).toString(),
						Level.ERROR);
			} catch (UnsupportedEncodingException e) {
				AuditUtil.getInstance().writeLog(AuditUtil.FILE_TYPE_G4G,
				new StringBuffer(GameLobbyAjaxImpl.class.getName())
						.append(COLON_WITH_SPACES).append(CALLINGMETHOD).append(
								DataUtil.getCallingMethod()).append(
								CURRENTMETHOD).append(
								DataUtil.getCurrentMethod()).append(
								DASHES).append(SESSIONID).append(
								request.getSession().getId()).append(
								DASHES).append(e.getMessage()).toString(),
				Level.ERROR);
			}
		}
		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,
				new StringBuffer(GameLobbyAjaxImpl.class.getName()).append(
						COLON_WITH_SPACES).append(CALLINGMETHOD).append(
						DataUtil.getCallingMethod()).append(CURRENTMETHOD)
						.append(DataUtil.getCurrentMethod()).append(DASHES)
						.append(ENDED).append(DASHES).append(SESSIONID).append(
								request.getSession().getId()).toString(),
				Level.INFO);
		return html.toString();
	}

	/**
	 * The removeGameFromCurrentUser method does the opposite of
	 * addGametoCurrentUser, It removes the game from player's profile. Once the
	 * game is removed it is displayed with add symbol under particular platform
	 * it belongs to. User can add it if he still owns the console for that
	 * game.<br>
	 * The removed game will also be removed from the my lobby of user and all
	 * the places where the games for this user is displayed.
	 *
	 * @param playerId
	 *            the id of the user the game will be removed from.
	 * @param idPre
	 *            the preferenceId decides it is userpage, gamepage
	 * @param strGameId
	 *            the id of game which is removed.
	 * @param request
	 *            the request object to get values from session.
	 *
	 * @return the string
	 */
	public static String removeGameFromCurrentUser(int playerId, String idPre,
			String strGameId, HttpServletRequest request) {
		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,
				new StringBuffer(GameLobbyAjaxImpl.class.getName()).append(
						COLON_WITH_SPACES).append(CALLINGMETHOD).append(
						DataUtil.getCallingMethod()).append(CURRENTMETHOD)
						.append(DataUtil.getCurrentMethod()).append(DASHES)
						.append(STARTED).append(DASHES).append(SESSIONID)
						.append(request.getSession().getId()).toString(),
				Level.INFO);
		int gameId = Integer.parseInt(DataUtil.decrypt(strGameId));

		StringBuffer html = new StringBuffer();
		SearchCriteria searchCriteria = new SearchCriteria();
		searchCriteria.setAttribute(PLAYER, new PlayerDTO(playerId));
		searchCriteria.setAttribute(GAME, new GameDTO(gameId));
		List<PlayergameDTO> playerGameList = PlayerGameServiceDelegator
				.getList(searchCriteria);

		if (playerGameList.size() != ZERO) {
			AuditUtil.getInstance().writeLog(
					AuditUtil.FILE_TYPE_G4G,
					new StringBuffer(GameLobbyAjaxImpl.class.getName()).append(
							COLON_WITH_SPACES).append(CALLINGMETHOD).append(
							DataUtil.getCallingMethod()).append(CURRENTMETHOD)
							.append(DataUtil.getCurrentMethod()).append(DASHES)
							.append(PLAYER_GAME_LIST_SIZE_CRITERIA).append(
									playerGameList.size()).toString(),
					Level.INFO);
			PlayergameDTO dto = playerGameList.get(ZERO);
			try {
				PlayerGameServiceDelegator.delete(dto);
				GameDTO game = GameServiceDelegator.getGame(gameId);
				String encryptedImgSrc = G4GConstants.NONE;
				encryptedImgSrc = URLEncoder.encode(game.getImgsrc(), G4GConstants.UTF8);

				html.append(DataUtil.getURLContent(new StringBuffer(DataUtil
						.getLocalBasePath(request)).append(
						G4GConstants.WEBCONTENT_NAME).append(G4GConstants.SLASH)
						.append(G4GConstants.AJAX).append(G4GConstants.SLASH)
						.append(G4GConstants.ADD_REMOVE_GAMES_JSP).append(
								G4GConstants.QUESTIONMARK).append(
										G4GConstants.ENCRYPTEDID).append(
										G4GConstants.EQUALS).append(
								strGameId).append(G4GConstants.AMBERSAND)
						.append(G4GConstants.IMGSRC).append(G4GConstants.EQUALS)
						.append(encryptedImgSrc).append(G4GConstants.AMBERSAND)
						.append(G4GConstants.GAMEID).append(G4GConstants.EQUALS)
						.append(game.getId()).append(G4GConstants.AMBERSAND)
						.append(G4GConstants.ADD).append(G4GConstants.EQUALS)
						.append(G4GConstants.REMOVE).toString()));
			} catch (HibernateException exception) {
				AuditUtil.getInstance().writeLog(
						AuditUtil.FILE_TYPE_G4G,
						new StringBuffer(GameLobbyAjaxImpl.class.getName())
								.append(COLON_WITH_SPACES)
								.append(CALLINGMETHOD).append(
										DataUtil.getCallingMethod()).append(
										CURRENTMETHOD).append(
										DataUtil.getCurrentMethod()).append(
										DASHES).append(SESSIONID).append(
										request.getSession().getId()).append(
										DASHES).append(exception).toString(),
						Level.ERROR);
			} catch (UnsupportedEncodingException e) {
				AuditUtil.getInstance().writeLog(AuditUtil.FILE_TYPE_G4G,
				new StringBuffer(GameLobbyAjaxImpl.class.getName())
						.append(COLON_WITH_SPACES).append(CALLINGMETHOD).append(
								DataUtil.getCallingMethod()).append(
								CURRENTMETHOD).append(
								DataUtil.getCurrentMethod()).append(
								DASHES).append(SESSIONID).append(
								request.getSession().getId()).append(
								DASHES).append(e.getMessage()).toString(),
				Level.ERROR);
			}
		}
		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,
				new StringBuffer(GameLobbyAjaxImpl.class.getName()).append(
						COLON_WITH_SPACES).append(CALLINGMETHOD).append(
						DataUtil.getCallingMethod()).append(CURRENTMETHOD)
						.append(DataUtil.getCurrentMethod()).append(DASHES)
						.append(ENDED).append(DASHES).append(SESSIONID).append(
								request.getSession().getId()).toString(),
				Level.INFO);

		return html.toString();
	}

	/**
	 * The getOnlineUserForGame method is called by ajax after every 30 seconds.
	 * Ajax call will each time check for the users currently online and will
	 * update the game details page with currently online users. It updates
	 * user's state with with online/offline users' state.
	 *
	 * @param userId
	 *            the userId who is currently logged-in.
	 * @param gameId
	 *            The id of the game thta gameLobby visited.
	 * @param request
	 *            the request object to get the values from the session.
	 *
	 * @return the users of the game lobby with current users' state
	 */
	public static String getOnlineUserForGame(int userId, int gameId,
			HttpServletRequest request) {
		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,
				new StringBuffer(GameLobbyAjaxImpl.class.getName()).append(
						COLON_WITH_SPACES).append(CALLINGMETHOD).append(
						DataUtil.getCallingMethod()).append(CURRENTMETHOD)
						.append(DataUtil.getCurrentMethod()).append(DASHES)
						.append(STARTED).append(DASHES).append(SESSIONID)
						.append(request.getSession().getId()).toString(),
				Level.INFO);

		StringBuffer html = new StringBuffer();
		List<PlayerDTO> gameWisePlayerList = PlayerServiceDelegator
				.getGameWisePlayersList(gameId);

		int gameWisePlayerSize = gameWisePlayerList.size();

		for (int index = ZERO; index < gameWisePlayerSize; index++) {
			PlayerDTO playerDTO = gameWisePlayerList.get(index);
			if (playerDTO.isIsonline()) {
				String encryptedScreenname = G4GConstants.NONE;
				try {
					encryptedScreenname = URLEncoder.encode(playerDTO.getScreenname(), G4GConstants.UTF8);
				} catch (UnsupportedEncodingException e) {
					AuditUtil.getInstance().writeLog(AuditUtil.FILE_TYPE_G4G,
							new StringBuffer(GameLobbyAjaxImpl.class.getName())
									.append(COLON_WITH_SPACES).append(CALLINGMETHOD).append(
											DataUtil.getCallingMethod()).append(
											CURRENTMETHOD).append(
											DataUtil.getCurrentMethod()).append(
											DASHES).append(SESSIONID).append(
											request.getSession().getId()).append(
											DASHES).append(e.getMessage()).toString(),
							Level.ERROR);
				}

				html.append(DataUtil.getURLContent(new StringBuffer(
								DataUtil.getLocalBasePath(request)).append(
								G4GConstants.WEBCONTENT_NAME).append(
								G4GConstants.SLASH).append(G4GConstants.AJAX)
								.append(G4GConstants.SLASH).append(
										G4GConstants.PLAYERS_ONLINE_JSP)
								.append(G4GConstants.QUESTIONMARK).append(
										G4GConstants.AVERAGE_AMOUNT).append(
										G4GConstants.EQUALS).append(
										playerDTO.getAverageamount()).append(
										G4GConstants.AMBERSAND).append(
										G4GConstants.PLAYERID).append(
										G4GConstants.EQUALS).append(
										playerDTO.getId()).append(
										G4GConstants.AMBERSAND).append(
										G4GConstants.SCREENNAME_DB).append(
										G4GConstants.EQUALS).append(
										encryptedScreenname).append(
										G4GConstants.AMBERSAND).append(
										G4GConstants.REPUTATION).append(
										G4GConstants.EQUALS).append(
										playerDTO.getReputation()).append(
										G4GConstants.AMBERSAND).append(
										G4GConstants.GAMEID).append(
										G4GConstants.EQUALS).append(gameId).append(G4GConstants.AMBERSAND)
								.append(G4GConstants.USER_ID).append(G4GConstants.EQUALS).append(userId)
								.toString()));
			}
		}
		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,
				new StringBuffer(GameLobbyAjaxImpl.class.getName()).append(
						COLON_WITH_SPACES).append(CALLINGMETHOD).append(
						DataUtil.getCallingMethod()).append(CURRENTMETHOD)
						.append(DataUtil.getCurrentMethod()).append(DASHES)
						.append(STARTED).append(DASHES).append(SESSIONID)
						.append(request.getSession().getId()).toString(),
				Level.INFO);
		return html.toString();
	}

	/**
	 * The method getPlayerSpecificGamesList returns games list that this player
	 * has. It tells which games user has selected in his profile. User can
	 * remove the specific game if he/she doesn't want it. By clicking on
	 * gamename user can visit game details page.
	 *
	 * @param inPlayerId
	 *            the player id for which games list is get.
	 *
	 * @return the games list having playergameDTO.
	 */
	public static List<PlayergameDTO> getPlayerSpecificGamesList(
			String inPlayerId) {
		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,
				new StringBuffer(GameLobbyAjaxImpl.class.getName()).append(
						COLON_WITH_SPACES).append(CALLINGMETHOD).append(
						DataUtil.getCallingMethod()).append(CURRENTMETHOD)
						.append(DataUtil.getCurrentMethod()).append(DASHES)
						.append(STARTED).toString(), Level.INFO);
		if (inPlayerId != null) {
			SearchCriteria searchCriteria = new SearchCriteria();
			searchCriteria.setAttribute(PLAYER, new PlayerDTO(Integer
					.parseInt(inPlayerId)));
			List<PlayergameDTO> gamesList = PlayerGameServiceDelegator
					.getList(searchCriteria);

			AuditUtil.getInstance().writeLog(
					AuditUtil.FILE_TYPE_G4G,
					new StringBuffer(GameLobbyAjaxImpl.class.getName()).append(
							COLON_WITH_SPACES).append(CALLINGMETHOD).append(
							DataUtil.getCallingMethod()).append(CURRENTMETHOD)
							.append(DataUtil.getCurrentMethod()).append(DASHES)
							.append(PLAYER_GAME_LIST_SIZE_CRITERIA).append(
									gamesList.size()).toString(), Level.INFO);
			AuditUtil.getInstance().writeLog(
					AuditUtil.FILE_TYPE_G4G,
					new StringBuffer(GameLobbyAjaxImpl.class.getName()).append(
							COLON_WITH_SPACES).append(CALLINGMETHOD).append(
							DataUtil.getCallingMethod()).append(CURRENTMETHOD)
							.append(DataUtil.getCurrentMethod()).append(DASHES)
							.append(ENDED).toString(), Level.INFO);
			return gamesList;
		}
		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,
				new StringBuffer(GameLobbyAjaxImpl.class.getName()).append(
						COLON_WITH_SPACES).append(CALLINGMETHOD).append(
						DataUtil.getCallingMethod()).append(CURRENTMETHOD)
						.append(DataUtil.getCurrentMethod()).append(DASHES)
						.append(ENDED).toString(), Level.INFO);
		return null;
	}

	/**
	 * Instantiates a new game lobby ajax impl.
	 */
	private GameLobbyAjaxImpl() {
	}

	/**
	 * This class should not be instantiated and cloned
	 */
	@Override
	public GameLobbyAjaxImpl clone() throws CloneNotSupportedException {
		throw new CloneNotSupportedException();
	}

}
