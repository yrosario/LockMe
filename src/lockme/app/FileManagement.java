/*
 * @title FileManagement.java
 * @author Yussel Rosario
 * @date 4/14/2020
 * @purpose: Class definition for File. This class will create and hold information about a file created on the hard drive. 
 * 
 */
package lockme.app;

import java.util.HashMap;
import java.util.ArrayList;
import java.util.Collections;
import java.io.Serializable;
import java.io.File;
import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;
import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.IOException;


public class FileManagement implements Serializable{
	
	//HashMap holds list of files for Customers
	HashMap<Integer, ArrayList<FileObj>> files;
	private String  objSerPath = "filestore/";
	private String serName = "hash.ser";
	private static final long serialVersionUID = 1234123421431234L;
	
	//Initialize files HashMap
	public FileManagement(){
		
		files = new HashMap<Integer, ArrayList<FileObj>>();

		File fileObjSer = new File(objSerPath + "/"+serName);

		if(fileObjSer.exists())
		   deserialize();
	}
	
	//Creates a file to the users folder
	public int addFile(String filename, int custId){
		
		FileObj newFile = new FileObj(filename, custId);
		
		if(newFile.create() == 1) {
			if(files.containsKey(custId))
				files.get(custId).add(newFile);
			else {
				files.put(custId, new ArrayList<FileObj>());
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
    	
    	//if user has no files return 
    	if(!files.containsKey(custId))
    		return;
    	
    	//sort files in descending ordered
    	Collections.sort(files.get(custId));
    	
    	if(files.containsKey(custId)) {
    		for(FileObj obj : files.get(custId))
    			System.out.println(obj.getName());
    	}
    }
    
    //Deletes file from specified user
    public int delete(String filename, int custId){
    	
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
    		for(FileObj obj : files.get(custId)) {
    			if(filename.equals(obj.getName()))
    				return true;
    		}
    	
    	
    	return false;
    }
	
    //Serializes files HashMap object to the hard drive
	private void serialize(){
		
		try {
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
		
		}catch(IOException e) {
			e.printStackTrace();
		}
		
	}
	
	//Read the files HashMap object stored on the hard drive
	@SuppressWarnings("unchecked")
	private void deserialize(){
		
		try {
		   FileInputStream fileIn = new FileInputStream(objSerPath + "/" + serName);
		
		   ObjectInputStream in = new ObjectInputStream(fileIn);
		   files =  (HashMap<Integer, ArrayList<FileObj>>) in.readObject();
		   in.close();
		   in.close();
		   
		}catch(IOException e) {
			e.printStackTrace();
		}catch (ClassNotFoundException e) {
			e.printStackTrace();
		}catch(ClassCastException e) {
			e.printStackTrace();
		}
	}
	
	
}
