public class Faces {

	public Faces(FileReader fr) {

		
		
	}

	public static void main(String[] args) {
		FileReader fr;
		if (args.length > 2) {
			fr = new FileReader(args[0], args[1], args[2]);
			Faces face = new Faces(fr);
		} else {  
			System.err.println("Not enough arguments to run program.");
		}
	}
	
	

}
