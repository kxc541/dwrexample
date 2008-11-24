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

import com.g4g.dto.GamecategoryDTO;
import com.g4g.dto.SearchCriteria;
/**
 * For future use.
 * Service interface for gamecategory table.
 * @author Punam
 */
public interface GameCategoryService {
	/**
	 * Adds gamecategory details in gamecategory table.
	 * 
	 * @param dto -
	 *            object of GamecategoryDTO kind containing details for the gamecategory.
	 * @return GamecategoryDTO - returns GamecategoryDTO kind object which is added.
	 */
	public GamecategoryDTO add(GamecategoryDTO dto) throws Exception;
	/**
	 * Delete gamecategory's entry from gamecategory table.
	 * 
	 * @param dto -
	 *            object of GamecategoryDTO kind containing details for the gamecategory.
	 * @return void
	 */
	public void delete(GamecategoryDTO dto) throws Exception;
	/**
	 * Method gives GamecategoryDTO for the match of given id.
	 * @param id
	 * @return GamecategoryDTO
	 */
	public GamecategoryDTO getGame(int id);
	/**
	 * Returns list containing all gamecategories' from gamecategory table.
	 * 
	 * @param
	 * @return list containing GamecategoryDTO objects.
	 */
	public List<GamecategoryDTO> getList();
	/**
	 * Returns list containing gamecategories' from gamecategory table, but according to given
	 * criteria.
	 * 
	 * @param searchCriteria
	 *            object with attributes set as per the require search.
	 * @return list containing GamecategoryDTO objects satisfies given criteria.
	 */
	public List<GamecategoryDTO> getList(SearchCriteria searchCriteria);
	/**
	 * Update gamecategories' information in gamecategory table.
	 * 
	 * @param dto - GamecategoryDTO's object
	 * @return GamecategoryDTO's updated object
	 */
	public GamecategoryDTO update(GamecategoryDTO dto) throws Exception;
}
