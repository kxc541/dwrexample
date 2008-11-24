/**********************************************************
 * HibernateSessionFactory.java 
 *
 * Created by author Jigar Mistry
 * Last modified $ Date: 17 .apr by Author: Punam
 * Revision: $ Revision:  $
 * Version : $ ID : 1$
 * Copyright (c) 2008 - 2008 Askdigi, All rights reserved.
 **********************************************************/
package com.g4g.utils;

import static com.g4g.utils.G4GConstants.LIST_SIZE;
import static com.g4g.utils.G4GConstants.NETWORK;
import static com.g4g.utils.G4GConstants.NETWORK_PS2_ID_LIST_KEY;
import static com.g4g.utils.G4GConstants.NETWORK_PS3_ID_LIST_KEY;
import static com.g4g.utils.G4GConstants.NETWORK_XBOX360_LIST_KEY;
import static com.g4g.utils.G4GConstants.NETWORK_XBOX_LIST_KEY;

import java.util.HashMap;
import java.util.List;

import com.g4g.delegator.GameServiceDelegator;
import com.g4g.dto.GameDTO;
import com.g4g.dto.NetworkDTO;
import com.g4g.dto.SearchCriteria;

//$ANALYSIS-IGNORE
/**
 * The Class ApplicationData for storing logged-in users information at
 * application level. It keeps track of all the network based games. Which games
 * are under which console is specified here.
 * 
 * @author Jigar Mistry
 */
public class ApplicationData{

	//$ANALYSIS-IGNORE
	/** The applicationdata is created. */
		private static ApplicationData applicationData = null;

	/** Contains the networkids and its related games in hashmap */
	private static HashMap<Integer, List<GameDTO>> networkBasedGames = new HashMap<Integer, List<GameDTO>>();

	/**
	 * Only one instance of the applicationdata is created.
	 * 
	 * @return applicationdata object
	 */
	public static ApplicationData getApplicationData() {
		if (applicationData == null) {
			applicationData = new ApplicationData();
		}
		return applicationData;
	}

	/**
	 * Instantiates a new application data. calls the loaddata method which in
	 * turn calls loadPlatformBasedGames method.
	 */
	private ApplicationData() {
		loadData();
	}

	/**
	 * This class should not be instantiated and cloned
	 */

	@SuppressWarnings({G4GConstants.CLONE_DOES_NOT_CALL_SUPER_CLONE})
    @Override
	public ApplicationData clone() throws CloneNotSupportedException {
		throw new CloneNotSupportedException();
	}

	/**
	 * The method getNetworkBasedGames Gets the network based games. based on
	 * networkId.
	 * 
	 * @param networkId
	 *            the network id for which the games are retrieved.
	 * 
	 * @return the networkbasedgames List is get.
	 */
	public List<GameDTO> getNetworkBasedGames(String networkId) throws NumberFormatException {
		return networkBasedGames.get(Integer.parseInt(networkId));
	}

	/**
	 * Load data calls the loadPlatformBasedGames method.
	 */
	private void loadData() {
		// Load the Platform specific games
		loadPlatformBasedGames();
	}

	/**
	 * The method loadPlatformBasedGames Loads platform based games. For Ex: For
	 * xbox360 it loads all the games those xbox360 have, same for rest
	 * consoles.
	 */
	private void loadPlatformBasedGames() {
		SearchCriteria searchCriteria = new SearchCriteria();

		// Load the Games list
		// Load XBOX Games
		searchCriteria.removeAllAttribute();
		NetworkDTO networkDTO = new NetworkDTO();
		networkDTO.setId(NETWORK_XBOX_LIST_KEY);
		searchCriteria.setAttribute(NETWORK, networkDTO);
		List<GameDTO> gamesList = GameServiceDelegator.getList(searchCriteria);
		if (gamesList != null) {
			AuditUtil.getInstance().writeLog(
					AuditUtil.FILE_TYPE_G4G,
					new StringBuffer(NETWORK_XBOX_LIST_KEY)
							.append(LIST_SIZE).append(
									gamesList.size()).toString());
			networkBasedGames
					.put(NETWORK_XBOX_LIST_KEY, gamesList);
		}

		// Load XBOX360 Games
		searchCriteria.removeAllAttribute();
		networkDTO.setId(NETWORK_XBOX360_LIST_KEY);
		searchCriteria.setAttribute(NETWORK, networkDTO);

		gamesList = GameServiceDelegator.getList(searchCriteria);
		if (gamesList != null) {
			AuditUtil.getInstance().writeLog(
					AuditUtil.FILE_TYPE_G4G,
					new StringBuffer (NETWORK_XBOX360_LIST_KEY ).append( LIST_SIZE
							).append( gamesList.size()).toString());
			networkBasedGames.put(NETWORK_XBOX360_LIST_KEY,
					gamesList);
		}
		// Load PS2 Games
		searchCriteria.removeAllAttribute();
		networkDTO.setId(NETWORK_PS2_ID_LIST_KEY);
		searchCriteria.setAttribute(NETWORK, networkDTO);

		gamesList = GameServiceDelegator.getList(searchCriteria);
		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,
				new StringBuffer (NETWORK_PS2_ID_LIST_KEY ).append( LIST_SIZE
						).append( gamesList.size()).toString());
		networkBasedGames.put(NETWORK_PS2_ID_LIST_KEY, gamesList);

		// Load XBOX Games
		searchCriteria.removeAllAttribute();
		networkDTO.setId(NETWORK_PS3_ID_LIST_KEY);
		searchCriteria.setAttribute(NETWORK, networkDTO);

		gamesList = GameServiceDelegator.getList(searchCriteria);
		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,
				new StringBuffer (NETWORK_PS3_ID_LIST_KEY ).append( LIST_SIZE
						).append( gamesList.size()).toString());
		networkBasedGames.put(NETWORK_PS3_ID_LIST_KEY, gamesList);
	}
}
