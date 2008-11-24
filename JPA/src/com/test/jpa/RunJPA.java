package com.test.jpa;

public class RunJPA {
	
	public static void main(String[] args) {
		 
		
		/* 2. Create a new Productline instance */ 
		Customer newProductline = new Customer( 
		        "Shoes for men.", 
		        "<strong>Men's Shoes</strong>"); 

		/* 3. Create a DAO instance to use */ 
		CustomerDAO dao = new CustomerDAO(); 

		/* 4. Store our new product line in the DB */ 
		EntityManagerHelper.beginTransaction();
		dao.save(newProductline);
		EntityManagerHelper.commit(); 

		/* 5. Now retrieve the new product line, using the ID we created */ 
		Customer loadedProductline = dao.findById(1); 

		/* 6. Print out the product line information */ 
		System.out.println("*NEW* Product Line [productLine=" 
		        + loadedProductline.getAddress() + ", textDescription=" 
		        + loadedProductline.getName() + ", image=" 
		        + loadedProductline.getId() + "]"); 

		/* 
		 * 7. Now let's change same value on the product line, and save the 
		 * change 
		 */ 
		loadedProductline.setAddress("Product line for men's shoes.");

		EntityManagerHelper.beginTransaction();
		dao.save(loadedProductline);
		EntityManagerHelper.commit();

		/* 
		 * 8. Now let's load the product line from the DB again, and make sure 
		 * it text description changed 
		 */ 
		Customer secondLoadedProductline = dao.findById(1); 

		System.out.println("*REVISED* Product Line [" 
		    + "productLine=" + secondLoadedProductline.getName() 
		    + ", textDescription=" + secondLoadedProductline.getAddress() 
		    + ", image=" + secondLoadedProductline.getId() + "]"); 

		/* 9. Now let's delete the product line from the DB */ 
		EntityManagerHelper.beginTransaction();
		dao.delete(secondLoadedProductline);
		EntityManagerHelper.commit();

		/* 
		 * 10. To confirm the deletion, try and load it again and make sure it 
		 * fails 
		 */ 
		Customer deletedProductline = dao.findById(1); 

		/* 
		 * We use a simple inlined IF clause to test for null and print 
		 * SUCCESSFUL/FAILED 
		 */ 
		System.out.println("Productline deletion: " 
		        + (deletedProductline == null ? "SUCCESSFUL" : "FAILED"));
	}

}
