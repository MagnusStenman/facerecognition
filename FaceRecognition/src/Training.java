import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

public class Training {

    private ArrayList<FaceData> trainingData;
    private ArrayList<FaceData> testTrainingData;
    private HashMap<String,Integer> facit;
    private static final double LEARNING_RATE = 0.0002;

    public Training() {

    }

    public void setTrainingData(ArrayList<FaceData> trainingData) {
        this.trainingData = trainingData;
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

        for (FaceData f : trainingData) {
            double[][] aMatrix = activation(f);
            double error[][] = computeError(aMatrix, f);
        }

        return true;
    }

    private double[][] computeError(double[][] aMatrix, FaceData f) {
        double temp[][] = new double[aMatrix.length][aMatrix.length];
        int curFacit = facit.get(f.getImageID());
        for (int i=0;i<aMatrix.length;i++) {
            for (int j=0;j<aMatrix[i].length;j++) {
                temp[i][j] = curFacit - aMatrix[i][j];
            }
        }
        return temp;
    }

    private double[][] activation(FaceData f) {
        double[][] tempAct = new double[f.getFaceData().length][f.getFaceData().length];
        Node[][] tempNode = f.getFaceData();

        for (int i=0;i<tempNode.length;i++) {
            for (int j=0;j<tempNode[i].length;j++) {
                tempAct[i][j] = tempNode[i][j].getActivationRecord();
                System.out.println(tempAct[i][j]);
            }
        }
        return tempAct;
    }
}
