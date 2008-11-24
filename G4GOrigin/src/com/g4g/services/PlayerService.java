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

import java.util.List;

import org.hibernate.HibernateException;

import com.g4g.dto.PlayerDTO;
import com.g4g.dto.PlayerStatus;
import com.g4g.dto.SearchCriteria;
import com.g4g.forms.ManagePlayerGameForm;
import com.g4g.forms.PlayerGameScheduleForm;

/**
 * Interface of service functions for Player table.
 * 
 * @author pratik
 * 
 */
public interface PlayerService {
	/**
	 * Adds player details in player table.
	 * 
	 * @author pratik
	 * @param dto -
	 *            object of PlayerDTO kind containing details for the player.
	 * @return PlayerDTO - returns PlayerDTO kind object which is added.
	 * @throws HibernateException 
	 */
	public PlayerDTO add(PlayerDTO dto)throws HibernateException;

	/**
	 * Delete player's entry from player table.
	 * 
	 * @author pratik
	 * @param dto -
	 *            object of PlayerDTO kind containing details for the player.
	 * @return void
	 * @throws HibernateException 
	 */	
	public void delete(PlayerDTO dto) throws HibernateException;
	/**
	 * Returns list of players of the given who are playing given game.
	 * 
	 * @author punam
	 * @param gameId
	 * @return List containing PlayerDTO objects.
	 * @throws HibernateException 
	 */

	public List<PlayerDTO> getGameWisePlayersList(int gameId)throws HibernateException;
	/**
	 * Returns list containing all players from player table.
	 * 
	 * @author pratik
	 * @param
	 * @return list containing PlayerDTO objects.
	 * @throws HibernateException 
	 */
	public List<PlayerDTO> getList()throws HibernateException;
	/**
	 * Returns list containing players from player table, but according to given
	 * criteria.
	 * 
	 * @author pratik
	 * @param searchCriteria 
	 * @param searchCriteria object with attributes set as per the require search.
	 * @return list containing PlayerDTO objects satisfies given criteria.
	 * @throws HibernateException 
	 */
    public List<PlayerDTO> getList(SearchCriteria searchCriteria)throws HibernateException;
	/**
	 * Returns PlayerDTO object containing player's detail according to given
	 * playerId.
	 * 
	 * @author pratik
	 * @param playerId  player's id for which wants detail.
	 * @return PlayerDTO object.
	 * @throws HibernateException 
	 */
    public PlayerDTO getPlayer(int playerId)throws HibernateException;
	/**
	 * Function to get friend list of given player.
	 * 
	 * @author jigar
	 * @param playerId -
	 *            id of player for whom need friend list.
	 * @return list containing PlayerDTO objects.
	 * @throws HibernateException 
	 */
	public List<PlayerDTO> getPlayerFriends(int playerId)throws HibernateException;
	/**
	 * Function returns list of plalyer's games' detail
	 * 
	 * @author pratik
	 * @param playerId
	 * @return list containing ManagePlayerGameForm objects.
	 * @throws HibernateException 
	 */
	public List<ManagePlayerGameForm> getPlayerGame(int playerId)throws HibernateException;
	/**
	 * Function to get given players recent games - games which player has
	 * already played.
	 * 
	 * @author pratik
	 * @param playerId -
	 *            id of the player for which want recent games
	 * @return list containing PlayerGameScheduleForm - all required information
	 *         related played game
	 * @throws HibernateException 
	 */
	public List<PlayerGameScheduleForm> getPlayerRecentGames(int playerId)throws HibernateException;
	/**
	 * Function to get Player's schedule games - next games yet to play by user
	 * 
	 * @author pratik
	 * @param playerId -
	 *            id of the player for which want scheduled games
	 * @return list containing PlayerGameScheduleForm objects with all details
	 *         related matches
	 * @throws HibernateException 
	 */
	public List<PlayerGameScheduleForm> getPlayerSchedule(int playerId)throws HibernateException;
	
	/**
	 * Function to get Player's games in verifying state. - Game is in
	 * verifying state from its starting time to the end of the match.
	 * 
	 * @author pratik
	 * @param playerId - Id of the player for which we want verifying game's list
	 * @return List<PlayerGameScheduleForm>
	 * @throws HibernateException 
	 */
	public List<PlayerGameScheduleForm> getPlayerVerifying(int playerId)throws HibernateException;
	
	/**
	 * Update Player's information in Player table.
	 * 
	 * @author pratik
	 * @param dto - PlayerDTO's object
	 * @return PlayerDTO's updated object
	 * @throws HibernateException 
	 */
	public PlayerDTO update(PlayerDTO dto) throws HibernateException;

	/**
	 * Function to get either player is online or not.
	 * 
	 * @author punam
	 * @param screenname  screenName of player
	 * @return boolean - true if player is online otherwise false.
	 * @throws HibernateException 
	 */
    public List<PlayerStatus> usersOnline()throws HibernateException;

	/**
	 * Returns list containing players from player table, but according to given
	 * criteria.
	 * 
	 * @author pratik
	 * @param searchCriteria 
	 *            object with attributes set as per the require search.
	 * @param caseinsensitive set to disable/enable caseinsensitiveness.
	 * @return list containing PlayerDTO objects satisfies given criteria.
	 * @throws HibernateException 
	 */
	public List<PlayerDTO> getList(SearchCriteria searchCriteria,
			boolean caseinsensitive)throws HibernateException;

}
