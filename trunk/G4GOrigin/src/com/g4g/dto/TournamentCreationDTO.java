/**********************************************************
* TournamentCreationDTO.java : 
*
* Created by author Ankur
* Last modified $ Date: $ by $ Author:  $
* Revision: $ Revision:  $
* Version : $ ID : 1$
* Copyright (c) 2008 - 2008 Askdigi, All rights reserved.
**********************************************************/

package com.g4g.dto;

import java.util.List;

/**
 * It contains all dto's that have relationship while creating tournament.
 * @author ankur
 *
 */
public class TournamentCreationDTO extends BaseDTO{

	private static final long serialVersionUID = 1L;
	
	private List<TwoplayermatchDTO> twoplayermatchList;
	private TwoplayertournamentDTO twoplayertournamentDTO;
	private List<TwoplayertournamentgameoptionsDTO> twoplayertournamentgameoptionsList;
	
	
	
	/**
	 * @return the twoplayertournamentgameoptionsList
	 */
	public List<TwoplayertournamentgameoptionsDTO> getTwoplayertournamentgameoptionsList() {
		return this.twoplayertournamentgameoptionsList;
	}
	/**
	 * @param twoplayertournamentgameoptionsList the twoplayertournamentgameoptionsList to set
	 */
	public void setTwoplayertournamentgameoptionsList(
					List<TwoplayertournamentgameoptionsDTO> twoplayertournamentgameoptionsList) {
		this.twoplayertournamentgameoptionsList = twoplayertournamentgameoptionsList;
	}
	/**
	 
	/**
	 * @return the twoplayertournamentDTO
	 */
	public TwoplayertournamentDTO getTwoplayertournamentDTO() {
		return this.twoplayertournamentDTO;
	}
	/**
	 * @param twoplayertournamentDTO the twoplayertournamentDTO to set
	 */
	public void setTwoplayertournamentDTO(TwoplayertournamentDTO twoplayertournamentDTO) {
		this.twoplayertournamentDTO = twoplayertournamentDTO;
	}
	/**
	 * @return the twoplayermatchList
	 */
	public List<TwoplayermatchDTO> getTwoplayermatchList() {
		return this.twoplayermatchList;
	}
	/**
	 * @param twoplayermatchList the twoplayermatchList to set
	 */
	public void setTwoplayermatchList(List<TwoplayermatchDTO> twoplayermatchList) {
		this.twoplayermatchList = twoplayermatchList;
	}
	
	
	
	

}
