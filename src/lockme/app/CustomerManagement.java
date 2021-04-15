/*
 * @title CustomerManagement.java
 * @author Yussel Rosario
 * @date 4/14/2020
 * @purpose: Class definition CustomerManagement. This class will responsible for creating new Customers
 * and keeping track of all customers. 
 * 
 */

package lockme.app;

import java.util.HashMap;

public class CustomerManagement {
	
	private static int custId;
	private HashMap<Integer, Customer> customers;
	
	public CustomerManagement() {
		custId = 0;
		customers = new HashMap<Integer, Customer>();
	}
	
	public void AddCustomer(String firstname, String lastname) {
		custId++;
		Customer newCustomer = new Customer(firstname, lastname, custId);
		customers.put(custId, newCustomer);
		
	}
	
	public Customer getCustomer(int custId) {
		return customers.get(custId);
	}
	

}
