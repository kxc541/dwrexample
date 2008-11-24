/**
 * 
 */
package com.cipl.services;

import java.util.List;

import com.cipl.dto.AvatarsDTO;
import com.cipl.util.HibernateSessionFactory;

/**
 * @author ankur
 *
 */
public class AvatarService{
	
	/**
	 * @return List<AvatarsDTO>
	 */
	@SuppressWarnings("unchecked")
	public List<AvatarsDTO> getAvatarList(){
		return HibernateSessionFactory.currentSession().createQuery("from AvatarsDTO").setCacheable(true).list();
	}
	
	/**
	 * @param id
	 * @return AvatarsDTO
	 */
	public AvatarsDTO getAvatar(int id) {
		return (AvatarsDTO)HibernateSessionFactory.currentSession().createQuery("from AvatarsDTO as a where a.id=:id").setParameter("id", id).setCacheable(true).uniqueResult();
	}

	
}
