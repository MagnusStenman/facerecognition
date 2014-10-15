import javax.swing.*;

import java.util.*;

public class Training {

	private ArrayList<FaceData> trainingData;
	private ArrayList<FaceData> testTrainingData;
	private HashMap<String, Integer> facit;
	private double LEARNING_RATE = 1;
	double percent = 0;
	private Node[][] brain;
    private long counter;

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
		int iterate = 0;
		do {
			iterate++;
			Collections.shuffle(trainingData);
//			Collections.shuffle(testTrainingData);
			for (FaceData f : trainingData) {
				buildBrain(f);
				for (int i = 0; i < 4; i++) {
					double act = calculateActivation(i + 1);
					adjust(i + 1, facit.get(f.getImageID()), act);
				}

				// adjustWeights(brain, facit.get(f.getImageID()));

				// double[][] aMatrix = activation(f);
				// double error[][] = computeError(aMatrix, f);
			}
			if (LEARNING_RATE + 0.01 > 0) {
				LEARNING_RATE -= 0.01;
			} else {
			}
		} while (scoreChecker());
		System.err.println("iterationts " + iterate);
		return true;
	}

	private void adjustWeights(Node[][] brain, Integer curFacit) {
		for (Node[] ds : brain) {
			for (Node node : ds) {
				for (int i = 0; i < node.getAct().length; i++) {
					int desiredOutput = 0;
					if (curFacit == (i + 1)) {
						desiredOutput = 1;
					}
					double error = desiredOutput - node.getAct()[i];
					//
					// Error is always -1, 0 or 1
					//

					// w is always positive or 0 when error is positive

					// System.out.println("Error:" + error);
					if (error != 0) {
						double w;
						w = LEARNING_RATE * error * node.getNodeValue();
						// if (error < 0)
						// else
						// w = (-1) * (LEARNING_RATE * error *
						// node.getNodeValue());

						node.setWeights(i, node.getWeights()[i] + w);
						// if (node.getNodeValue() != 0 && error > 0) {
						// System.out.println("w:" + w);
						// System.out.println();
						// try {
						// Thread.sleep(5000);
						// } catch (InterruptedException e) {
						// e.printStackTrace();
						// }
						// }
					}
				}
			}
		}
	}

	private void buildBrain(FaceData f) {
		for (int i = 0; i < f.getFaceData().length; i++) {
			for (int j = 0; j < f.getFaceData()[i].length; j++) {
				brain[i][j].setNodeValue(f.getFaceData()[i][j]);
			}
		}
	}

	// private void calculateActivation() {
	// for (int i = 0; i < brain.length; i++) {
	// for (int j = 0; j < brain[i].length; j++) {
	// brain[i][j].calculateActivation();
	// }
	// }
	// }

	private double calculateActivation(int mood) {
		double aSum = 0;
		for (int i = 0; i < brain.length; i++) {
			for (int j = 0; j < brain[i].length; j++) {
				aSum += brain[i][j].getNodeValue()
						* brain[i][j].getWeights()[mood - 1];
			}
		}

		double act;
		if (aSum > 0.5) {
			act = 1;
		} else {
			act = 0;
		}
		// double act = Math.tanh(aSum);
		return act;
	}

	private void adjust(int mood, int curFacit, double act) {
		int desiredOutput = 0;
		if (curFacit == (mood)) {
			desiredOutput = 1;
		}
		double error = desiredOutput - act;

		if (error != 0) {
			for (int i = 0; i < brain.length; i++) {
				for (int j = 0; j < brain[i].length; j++) {
					double aex = (LEARNING_RATE * error * brain[i][j]
							.getNodeValue());

					brain[i][j].setWeights(mood - 1,
							brain[i][j].getWeights()[mood - 1] + aex);

				}
			}
		}
	}

	private boolean scoreChecker() {
		int[] faces = new int[4];
		int[] result = new int[5];
		int hitCount = 0;
		int mood = 0;
		for (FaceData fd : testTrainingData) {
			buildBrain(fd);
			for (int i = 0; i < 4; i++) {

				double act = calculateActivation(i + 1);
				if (act == 1) {
					mood = i + 1;
				}
			}

			// System.out.println(fd.getImageID() + " " + mood);
			result[mood]++;

			if (facit.get(fd.getImageID()) == mood) {
				// System.out.println(fd.getImageID() + " : is correct");
				hitCount++;
			} else {
				// System.out.println(fd.getImageID() + ": is incorrect");
			}
		}

		// System.out.println("Mood 1: " + result[0]);
		// System.out.println("Mood 2: " + result[1]);
		// System.out.println("Mood 3: " + result[2]);
		// System.out.println("Mood 4: " + result[3]);

		if (((double) hitCount / (double) testTrainingData.size() * 100) > percent) {
			percent = ((double) hitCount / (double) testTrainingData.size() * 100);
			System.err.println(percent + "% correct");
		}

		// System.out.println("size "+testTrainingData.size());
		for (int i = 0; i < faces.length; i++) {

            if (percent >= 70) {
                counter++;
                if (counter == 3)
                    return false;
            } else {
                counter = 0;
            }
		}
		return true;
	}

	public void runTest(ArrayList<FaceData> data) {
		for (FaceData fd : data) {
			buildBrain(fd);
			boolean guess = true;
			
			for (int i = 0; i < 4; i++) {

				double act = calculateActivation(i + 1);
				if (act == 1) {
					guess = false;
					System.out.println(fd.getImageID() + " " + (i + 1));
					break;
				}
			}
			if(guess) {
				Random r = new Random();
				int g = r.nextInt(3)+1;
				System.out.println(fd.getImageID() + " " + g);
			}
			
		}
	}
}
