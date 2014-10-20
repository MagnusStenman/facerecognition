import java.io.FileNotFoundException;

public class Faces {

	public Faces(String[] args) {
		try {
			FileHandler fh = new FileHandler(args[0], args[1], args[2]);
            Training training = new Training();
            training.setTrainingData(fh.getTrainingData());
            training.setFacit(fh.getFacit());
            
            if(training.startTraining()) {
            	training.runGuesses(fh.getTestData());
            } else {
            	System.err.println("Failure when training");
            }
		} catch (FileNotFoundException e) {
			System.err.println("File not found");
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