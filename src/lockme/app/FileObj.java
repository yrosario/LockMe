/*
 * @title FileObj.java
 * @author Yussel Rosario
 * @date 4/14/2020
 * @purpose: Class definition for File. This class will create and hold information about a file created on the hard drive. 
 * 
 */
package lockme.app;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;

public class FileObj implements Serializable {
	
	private String name;
	private String path;
	private int custId;
	private File fh;
	private File dirFh;
	private static final long serialVersionUID = 12341234214312L;
	

	public FileObj(String filename, int custId){
		name = filename;
		this.custId = custId;
		path = custId + "/";
		
	}
	
	//Creates a directory on the hard drive base on the users id if directory doesn't already exist.
	private void createDirectory() {
		
		try {
			dirFh = new File(path);
			if(!dirFh.exists())
				if(dirFh.mkdir())
					System.out.println("created directory");
				else
					System.out.println(path + " unable to create directory");
			
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	//Creates a user specified file if it doesn't already exist
	public int create() {
		
		createDirectory();
		
		try {
			fh = new File(path + name);
			if(!fh.exists()) {
				if(fh.createNewFile()) {
					System.out.println(fh.getPath());
					return 1;}
				else {
					System.out.println("unable to create file");
				}
				return -1;
			}else {
				return -1;
			}
			
		}catch(IOException e) {
			e.printStackTrace();
			System.out.println(e);
			return -1;
		}
	}
	
	//deletes current file from the hard drive
	public int delete() {
		
		try {
			if(fh.exists())
				if(fh.delete())
					return 1;
				else
					return -1;
			
			return -1;
		}catch(Exception e) {
			e.printStackTrace();
			return -1;
		}
	}
	
	//returns name of file
	public String getName() {
		return name;
	}
	
	//returns owner of file
	public int getOwner() {
		return custId;
	}
}

