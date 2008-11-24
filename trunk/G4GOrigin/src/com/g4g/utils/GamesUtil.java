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

import java.util.List;

import com.g4g.delegator.PlayerGameServiceDelegator;
import com.g4g.delegator.PlayerNetworkServiceDelegator;
import com.g4g.dto.GameDTO;
import com.g4g.dto.NetworkDTO;
import com.g4g.dto.PlayerDTO;
import com.g4g.dto.PlayergameDTO;
import com.g4g.dto.PlayernetworkDTO;
import com.g4g.dto.SearchCriteria;

/**
 * The GamesUtil Class contains various utilities related to games. GameUtil
 * contains method for deciding if given network is available for given player.
 * It also decides whether the given game is available for given player.
 * 
 * @author Punam
 */
public class GamesUtil {

	/** The search criteria to determine the criteria for getting the record. */
	private static SearchCriteria searchCriteria = new SearchCriteria();

	/**
	 * The isGameAvailable method decieds the game for given gameId is available
	 * for the playerId given.Whether the player has selected this game in his
	 * profile if he/she has selected it will return true else it will return
	 * false.
	 * 
	 * @param playerId
	 *            the id of the player for whom the given game will be checked.
	 * @param gameId
	 *            the id of the game checks for this game.
	 * 
	 * @return true, if is game available else it will return false.
	 */
	public static boolean isGameAvailable(int playerId, int gameId) {
		boolean isAvailable = false;
		searchCriteria.removeAllAttribute();
		searchCriteria.setAttribute(G4GConstants.PLAYER, new PlayerDTO(playerId));
		searchCriteria.setAttribute(G4GConstants.GAME, new GameDTO(gameId));
		List<PlayergameDTO> gameList = PlayerGameServiceDelegator.getList(searchCriteria);
		isAvailable = gameList.size() > G4GConstants.ZERO;
		return isAvailable;
	}

	/**
	 * The isNetWorkAvailable method decieds the netWork for given netWorkId is
	 * available for the playerId given. Whether the player has selected this
	 * netWork in his profile if he/she has selected it will return true else it
	 * will return false.
	 * 
	 * @param playerId
	 *            the id of the player for whom the given netWork will be checked.
	 * @param networkId 
	 *            the id of the netWork checks for this netWork.
	 * 
	 * @return true, if netWork is available else it will return false.
	 */
	public static boolean isNetWorkAvailable(int playerId, int networkId) {
		boolean isAvailable = false;
		searchCriteria.removeAllAttribute();
		searchCriteria.setAttribute(G4GConstants.PLAYER, new PlayerDTO(playerId));
		searchCriteria.setAttribute(G4GConstants.NETWORK, new NetworkDTO(networkId));
		List<PlayernetworkDTO> playerNetworkList = PlayerNetworkServiceDelegator.getList(searchCriteria);
		isAvailable = playerNetworkList.size() > G4GConstants.ZERO;
		return isAvailable;
	}
}
