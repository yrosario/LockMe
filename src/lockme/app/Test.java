package lockme.app;

public class Test {

	public static void main(String[] args) {
		FileObj file = new FileObj("joe.txt", 3);
		file.create();
		file.create();
		file.delete();

	}

}
