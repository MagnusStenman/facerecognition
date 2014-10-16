import java.io.FileNotFoundException;

public class Faces {

	private FaceHandler fh;
    private Training training;

	public Faces(String[] args) {
		try {
			fh = new FaceHandler(args[0], args[1], args[2]);
            training = new Training();
            training.setTrainingData(fh.getTrainingData());
            training.setFacit(fh.getFacit());
            
            if(training.startTraining()) {
            	training.runGuesses(fh.getTestData());
            } else {
            	System.err.println("Failure when training");
            }
		} catch (FileNotFoundException e) {
			System.err.println(e.getMessage());
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