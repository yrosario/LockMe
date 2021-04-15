/*
 * @title FileManagement.java
 * @author Yussel Rosario
 * @date 4/14/2020
 * @purpose: Class definition for File. This class will create and hold information about a file created on the hard drive. 
 * 
 */
package lockme.app;

import java.util.HashMap;
import java.util.ArrayDeque;
import java.io.Serializable;
import java.io.File;
import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;
import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.IOException;

public class FileManagement implements Serializable{
	
	//HashMap holds list of files for Customers
	HashMap<Integer, ArrayDeque<FileObj>> files;
	private String  objSerPath = "ser";
	private String serName = "hash.ser";
	private static final long serialVersionUID = 1234123421431234L;
	
	//Initialize files HashMap
	public FileManagement() throws ClassNotFoundException, IOException {
		files = new HashMap<Integer, ArrayDeque<FileObj>>();
		
		File fileObjSer = new File(objSerPath + "/"+serName);
	
		if(fileObjSer.exists())
			desialize();
	}
	
	//Creates a file to the users folder
	public int addFile(String filename, int custId) throws IOException {
		FileObj newFile = new FileObj(filename, custId);
		
		if(newFile.create() == 1) {
			if(files.containsKey(custId))
				files.get(custId).add(newFile);
			else {
				files.put(custId, new ArrayDeque<FileObj>());
				files.get(custId).add(newFile);
			}
			
			serialize();
			return 1;
		}
		else
			return -1;
	}
	
	//Prints list of files from specified customer
    public void getFiles(int custId) {
    	
    	if(files.containsKey(custId)) {
    		for(FileObj obj : files.get(custId))
    			System.out.println(obj.getName());
    	}
    }
    
    //Deletes file from specified user
    public int delete(String filename, int custId) throws IOException {
    	
    	FileObj temp = null;
    	if(findFile(filename, custId)) {
    		for(FileObj obj : files.get(custId))
    			if(filename.equals(obj.getName())) {
    				temp = obj;
    				break;
    			}
    		
    		if(temp.delete() == 1) {
    			files.get(custId).remove(temp);
    			serialize();
    			return 1;
    		}
    	}
    	return -1;
    }
    
    //Finds a file and returns true if file is found
    public boolean findFile(String filename, int custId) {
    	if(files.containsKey(custId))
    		for(FileObj obj : files.get(custId))
    			if(filename.equals(obj.getName()))
    				return true;
    	
    	return false;
    }
	
	private void serialize() throws IOException {
		File dir = new File(objSerPath);
		
		//create ser directory if it doesn't exist
		if(!dir.exists())
			if(dir.mkdir())
				System.out.println("directory create successfully");
			else
				System.out.println("unable to create directory");
		
		//creates ser file and writes ser file
		FileOutputStream fileOut = new FileOutputStream(objSerPath + "/" + serName);
		ObjectOutputStream out = new ObjectOutputStream(fileOut);
		out.writeObject(files);
		out.close();
		fileOut.close();
		
	}
	
	private void desialize() throws IOException, ClassNotFoundException{
		
		
		FileInputStream fileIn = new FileInputStream(objSerPath + "/" + serName);
		
		ObjectInputStream in = new ObjectInputStream(fileIn);
		files =  (HashMap<Integer, ArrayDeque<FileObj>>) in.readObject();
		in.close();
		in.close();
	}
	
	
	
	

}
