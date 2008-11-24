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

import com.g4g.dto.FeedbackreputationDTO;
import com.g4g.dto.SearchListCriteria;
/**
 * Service interface for feedbackreputation table.
 * @author Punam
 *
 */
public interface FeedbackService {

	/**
	 * Adds Feedbackreputation details in Feedbackreputation table.
	 * 
	 * @param dto -
	 *            object of FeedbackreputationDTO kind containing details for the Feedbackreputation.
	 * @return FeedbackreputationDTO - returns FeedbackreputationDTO kind object which is added.
	 */
	public FeedbackreputationDTO add(FeedbackreputationDTO dto)throws HibernateException;
	/**
	 * Delete Feedbackreputation's entry from Feedbackreputation table.
	 * 
	 * @param dto -
	 *            object of FeedbackreputationDTO kind containing details for the Feedbackreputation.
	 * @return void
	 */
	public void delete(FeedbackreputationDTO dto)throws HibernateException;
	
	/**
	 * Returns list containing Feedbackreputation for given player information from Feedbackreputation table.
	 * 
	 * @param
	 * @return list containing FeedbackreputationDTO objects.
	 */
	public FeedbackreputationDTO getReputation(SearchListCriteria searchListCriteria)throws HibernateException;

	/**
	 * Returns list containing Feedbackreputation for given player information from Feedbackreputation table.
	 * 
	 * @param
	 * @return list containing FeedbackreputationDTO objects.
	 */
	public int getAverageReputation(int playerId)throws HibernateException;

	/**
	 * Returns list containing Feedbackreputation for given player information from Feedbackreputation table.
	 * 
	 * @param
	 * @return list containing FeedbackreputationDTO objects.
	 */
	
	public List<FeedbackreputationDTO> getReputationList(int playerId)throws HibernateException;
	/**
	 * Update Feedbackreputation information in Feedbackreputation table.
	 * 
	 * @param dto - FeedbackreputationDTO's object
	 * @return FeedbackreputationDTO's updated object
	 */
	public FeedbackreputationDTO update(FeedbackreputationDTO dto)throws HibernateException;
	}
