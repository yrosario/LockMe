/*
 * @title FileManagement.java
 * @author Yussel Rosario
 * @date 4/15/2020
 * @purpose: This class displays a command line menu that allows the customer to 
 * performed several file management operations. 
 * 
 */
package lockme.app;

import java.io.IOException;
import java.util.Scanner;

public class Menu {
	
	//Displays command line menu to customer to performed file management operations and
	//performed those operations.
	public void display(FileManagement fileMng, CustomerManagement custMng, int custId) throws IOException {
		
		Scanner scn = new Scanner(System.in);
		String filename = "";
		
		System.out.println("\nUser:-> " + custMng.getCustomer(custId) + " <-is logged in\n");
		
		String message = "\nEnter 1 of the characters below"
				+ "\n1. l - List all files \n2. a - Add file" 
				+ "\n3. d - Delete file \n4. m - Display menu \n5. q - quit";
		System.out.println(message);
		while(true) {
			
			System.out.print(":");
			char choice = scn.next().charAt(0);
			scn.reset();
			switch(choice) {
			
			    case 'l':
				     fileMng.getFiles(custId);
				     break;
				     
			    case 'a':
			    	System.out.print("\nEnter file name: ");
			    	filename = scn.next();
			    	
			    	if(fileMng.addFile(filename, custId) == 1)
			    		System.out.println(filename + " was created successfully");
			    	else
			    		System.out.println("File creation failed");
			    	break;
			    	
			    case 'd':
			    	System.out.print("\nEnter file name to delete: ");
			    	filename = scn.next();
			    	
			    	if(fileMng.delete(filename, custId) == 1)
			    		System.out.println(filename + " was deleted successfully");
			    	else
			    		System.out.println("File deletion failed");
			    	break;
			    	
			    case 'm':
			    	System.out.println(message);
			    	break;
			    	
			    case 'q':
			    	System.out.println("\nClosing menu...");
			    	scn.close();
			    	return;
			    	
			    default:
			    	System.out.println("Invalid Selection");
			}
			
		}
		
	}

	public static void main(String[] args) throws ClassNotFoundException, IOException {
		
		
		CustomerManagement custMng = new CustomerManagement();
		custMng.AddCustomer("John", "Smith");
		
		FileManagement fileMng = new FileManagement();
		
		new Menu().display(fileMng, custMng, 1);

	}

}
