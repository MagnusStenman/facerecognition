import java.io.FileNotFoundException;

public class Faces {

	public Faces(FileReader fr) {

		
		
	}

	public static void main(String[] args) {
		FileReader fr = null;
		if (args.length > 2) {
            try {
                fr = new FileReader(args[0], args[1], args[2]);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            Faces face = new Faces(fr);
		} else {  
			System.err.println("Not enough arguments to run program.");
		}
	}
	
	

}
