package com.test.jpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JPAHello {


	public void save(Customer customer) {
		
		 EntityManagerFactory emf = Persistence.createEntityManagerFactory("SpringDemoPU");
		 EntityManager manager= emf.createEntityManager();
		 manager.getTransaction().begin();
		 manager.persist(customer);
		 manager.getTransaction().commit();
		 manager.close();
		 
	}
	
	public static void main(String[] args) {
		JPAHello hello = new JPAHello();
		Customer customer= new Customer();
		customer.setAddress("Ahmedabad");
		customer.setName("Nishan");
		hello.save(customer);
	}

}
