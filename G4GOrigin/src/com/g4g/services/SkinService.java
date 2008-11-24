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

import org.hibernate.HibernateException;

import com.g4g.dto.SkinDTO;

/**
 * Class contains method signature related to timezone.
 * 
 * @author Ankur
 */
public interface SkinService {
	/**
	 * Returns timezone table of given game.
	 * @param skinId 
	 * @return SkinDTO
	 * @throws HibernateException 
	 */
	public SkinDTO getSkin(String skinId)throws HibernateException;

	/**
	 * Returns all the records from timezone table.
	 * 
	 * @return
	 */
}
