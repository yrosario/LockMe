package lockme.app;

import java.io.IOException;

public class Test {

	public static void main(String[] args) throws ClassNotFoundException, IOException {
		
		FileManagement flM = new FileManagement();
		
		//flM.addFile("test1", 10);
		//flM.addFile("sfdsfg", 10);
		//flM.addFile("test", 10);
		if(flM.delete("test1", 10) == 1)
			System.out.println("File was delete successfully");
		else
			System.out.println("File deletion failed");
		flM.getFiles(10);

	}

}
