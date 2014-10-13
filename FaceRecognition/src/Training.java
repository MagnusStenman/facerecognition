import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

public class Training {

	private ArrayList<FaceData> trainingData;
	private ArrayList<FaceData> testTrainingData;
	private HashMap<String, Integer> facit;
	private static final double LEARNING_RATE = 0.0002;

	private Node[][] brain;

	public Training() {

	}

	public void setTrainingData(ArrayList<FaceData> trainingData) {
		this.trainingData = trainingData;
		brain = new Node[20][20];
		for (int i = 0; i < 20; i++) {
			for (int j = 0; j < 20; j++) {
				brain[i][j] = new Node();
			}
		}
	}

	public void setTestTrainingData(ArrayList<FaceData> testTrainingData) {
		this.testTrainingData = testTrainingData;
	}

	public void setFacit(HashMap<String, Integer> facit) {
		this.facit = facit;
	}

	public boolean startTraining() {
		if (trainingData == null || testTrainingData == null || facit == null) {
			return false;
		}

		do {
			for (FaceData f : trainingData) {
				buildBrain(f);
				calculateActivation();
				adjustWeights(brain, facit.get(f.getImageID()));

				// double[][] aMatrix = activation(f);
				// double error[][] = computeError(aMatrix, f);
			}
		} while (scoreChecker());

		return true;
	}

	private void buildBrain(FaceData f) {
		for (int i = 0; i < f.getFaceData().length; i++) {
			for (int j = 0; j < f.getFaceData()[i].length; j++) {
				brain[i][j].setNodeValue(f.getFaceData()[i][j]);
			}
		}
	}


	private void calculateActivation() {
		for (int i = 0; i < brain.length; i++) {
			for (int j = 0; j < brain[i].length; j++) {
				brain[i][j].calculateActivation();
			}
		}
	}
	

	private boolean scoreChecker() {
		int[] faces = new int[4];

		for (FaceData fd : testTrainingData) {
			buildBrain(fd);
			calculateActivation();

			for (Node[] nodes : brain) {
				for (Node node : nodes) {
					for (int i = 0; i < node.getAct().length; i++) {
						if (node.getAct()[i] == 1) {
							//TODO
							if ((i + 1) == facit.get(fd.getImageID())) {
								//System.out.println(fd.getImageID() +" "+(i+1));
								faces[i]++;
							}
						}

					}
					
				}
			}
			
		}
		System.out.println("size "+testTrainingData.size());
		for (int i=0;i<faces.length;i++) {
			
			System.out.println("Face "+(i+1)+": "+((faces[i]/testTrainingData.size()*100)));
			if(faces[i] > 75) {
				return false;
			}
		}
		return true;
	}

	private void adjustWeights(Node[][] brain, Integer curFacit) {
		
		for (Node[] ds : brain) {
			for (Node node : ds) {
				for (int i = 0; i < node.getAct().length; i++) {
					int desiredOutput = 0;
					if(curFacit == (i+1)) {
						desiredOutput = 1;
					}
					double error = desiredOutput - node.getAct()[i];
					if (error != 0) {
						double w = LEARNING_RATE * error * node.getNodeValue();
						node.setWeights(i, node.getWeights()[i] + w);
					}
				}
			}
		}
	}




	public void runTest(ArrayList<FaceData> testTrainingData2) {
		// TODO Auto-generated method stub
		
	}
}
