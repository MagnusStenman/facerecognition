import java.io.FileNotFoundException;

public class Faces {

	private FaceHandler fh;

	public Faces(String[] args) {
		try {
			fh = new FaceHandler(args[0], args[1], args[2]);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		
	}

	public static void main(String[] args) {
		if (args.length > 2) {
            	new Faces(args);
		} else {  
			System.err.println("Not enough arguments to run program.");
		}
	}
}
