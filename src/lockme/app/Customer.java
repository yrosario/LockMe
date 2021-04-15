/*
 * @title Customer.java
 * @author Yussel Rosario
 * @date 4/13/2020
 * @purpose: Class definition Customer. This class will represent a Customer entity. 
 * 
 */
package lockme.app;

public class Customer {
	
	private String firstName;
	private String lastName;
	private int custId;
	
	public Customer(String firstName, String lastName, int custId) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.custId = custId;
	}
	
	
	public int getId() {
		return custId;
	}
	
	public String getName() {
		return firstName + " " + lastName;
	}
	
	

}
