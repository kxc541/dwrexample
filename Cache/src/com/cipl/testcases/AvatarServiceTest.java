package com.cipl.testcases;

////////////////////////////////////////////////////////////////////////////////
// CountryDAOTest: $Source$
// TODO Class summary goes here
//
// Created : 26 oct. 2005 by jfsmart
// Last modified $Date$ by $Author$
// Revision: $Revision$
// Version : $ID$
// Copyright (c) 2005
////////////////////////////////////////////////////////////////////////////////



import java.util.List;

import junit.framework.TestCase;

import org.hibernate.Transaction;
import org.junit.Test;

import com.cipl.dto.AvatarsDTO;
import com.cipl.services.AvatarService;
import com.cipl.util.HibernateSessionFactory;

/**
 * @author ankur
 *
 */
public class AvatarServiceTest extends TestCase {

	/**
	 * 
	 */
	@Test
	public void testGetAvatarList() {
		AvatarService service = new AvatarService();
		for(int i = 1; i <= 5; i++) {
			Transaction tx = HibernateSessionFactory.getSession().beginTransaction();
			TestTimer timer = new TestTimer("testAvatarList");
			List<AvatarsDTO> countries = service.getAvatarList();
			tx.commit();
			timer.done();
			HibernateSessionFactory.closeSession();
			assertNotNull(countries);
		}
	}
	
	/**
	 * 
	 */
	@Test
	public void testGetAvatar() {
		AvatarService service = new AvatarService();
		for(int i = 1; i <= 5; i++) {
			Transaction tx = HibernateSessionFactory.getSession().beginTransaction();
			TestTimer timer = new TestTimer("testAvatarListbyid");
			AvatarsDTO countries = service.getAvatar(2);
			tx.commit();
			timer.done();
			HibernateSessionFactory.closeSession();
			assertNotNull(countries);
		}
	}
	
	
	
	
}
