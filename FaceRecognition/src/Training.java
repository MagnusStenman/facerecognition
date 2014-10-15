import javax.swing.*;
import java.util.*;

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
                for (int i=0;i<4;i++) {
                    double act = calculateActivation();
                    adjust(i+1, facit.get(f.getImageID()), act);
                }

//				adjustWeights(brain, facit.get(f.getImageID()));

                // double[][] aMatrix = activation(f);
				// double error[][] = computeError(aMatrix, f);
			}
            Collections.shuffle(trainingData);
		} while (scoreChecker());

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
//
// Error is always -1, 0 or 1
//

// w is always positive or 0 when error is positive

//                    System.out.println("Error:" +  error);
                    if (error != 0) {
                        double w;
                        w = LEARNING_RATE * error * node.getNodeValue();
//                        if (error < 0)
//                        else
//                            w = (-1) * (LEARNING_RATE * error * node.getNodeValue());

                        node.setWeights(i, node.getWeights()[i] + w);
//                        if (node.getNodeValue() != 0 && error > 0) {
//                            System.out.println("w:" +  w);
//                            System.out.println();
//                            try {
//                                Thread.sleep(5000);
//                            } catch (InterruptedException e) {
//                                e.printStackTrace();
//                            }
//                        }
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


//	private void calculateActivation() {
//		for (int i = 0; i < brain.length; i++) {
//			for (int j = 0; j < brain[i].length; j++) {
//				brain[i][j].calculateActivation();
//			}
//		}
//	}

    private double calculateActivation() {
        double aSum = 0;
        for (int i = 0; i < brain.length; i++) {
            for (int j = 0; j < brain[i].length; j++) {
                for (int k=0; k < 4 ; k++) {
                    aSum += brain[i][j].getNodeValue()*brain[i][j].getWeights()[k];
                }
            }
        }

//        double act;
//        if (aSum > 0.5) {
//            act = 1;
//        } else {
//            act = 0;
//        }
        double act = Math.tanh(aSum);
        return act;
    }

    private void adjust(int mood, int curFacit, double act) {
        int desiredOutput = 0;
        if(curFacit == (mood)) {
            desiredOutput = 1;
        }
        double error = desiredOutput - act;

        for (int i = 0; i < brain.length; i++) {
            for (int j = 0; j < brain[i].length; j++) {
                double aex = (LEARNING_RATE * error * brain[i][j].getNodeValue());
                for (int k=0; k < 4 ; k++) {
                    brain[i][j].setWeights(k, brain[i][j].getWeights()[k] + aex);
                }
            }
        }
    }


	private boolean scoreChecker() {
		int[] faces = new int[4];
        int[] result = new int[4];
        int hitCount = 0;

        for (FaceData fd : testTrainingData) {
            buildBrain(fd);
            double act = calculateActivation();
            int mood = 0;
            double error = Double.MAX_VALUE;

            for (int i=0;i<4;i++) {
                int desiredOutput = 0;
                if(facit.get(fd.getImageID()) == (i+1)) {
                    desiredOutput = 1;
                }

                System.out.println("desired output " + desiredOutput);
                if ((desiredOutput - act) < error) {
                    System.out.println("IN");

//                try {
//                    Thread.sleep(5000);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
                    error = desiredOutput - act;
                    mood = i+1;
                }
//                System.out.println("act " + act);
//                System.out.println("mood " + mood);
//                System.out.println("error " + error);

//                try {
//                    Thread.sleep(5000);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }

            }

            System.out.println(fd.getImageID() + " " + mood);
            result[mood-1]++;

            if (facit.get(fd.getImageID()) == mood) {
                System.out.println(fd.getImageID() + " : is correct");
                hitCount++;
            }
        }

        System.out.println("Mood 1: " + result[0]);
        System.out.println("Mood 2: " + result[1]);
        System.out.println("Mood 3: " + result[2]);
        System.out.println("Mood 4: " + result[3]);

        double percent = ((double)hitCount/(double)testTrainingData.size())*100;
        System.out.println(percent + "% correct");

//        System.out.println("size "+testTrainingData.size());
		for (int i=0;i<faces.length;i++) {


//			System.out.println("Face "+(i+1)+": "+((faces[i]/testTrainingData.size()*100)));
			if(percent > 75) {
				return false;
			}
		}
		return true;
	}

	public void runTest(ArrayList<FaceData> testTrainingData2) {
		// TODO Auto-generated method stub

	}
}
