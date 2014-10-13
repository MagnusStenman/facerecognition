import java.util.Random;

public class Node {

    private int nodeValue;
    private double[] weights;

    public Node(int nodeValue) {
        this.nodeValue = nodeValue;
        weights = new double[4];
        Random r = new Random();
        for (int i=0;i<weights.length;i++) {
            weights[i] = r.nextDouble();
            //TODO
        }
    }

    public void setWeights(int i, double value) {
        this.weights[i] = value;
        //TODO
    }

    public int getNodeValue() {
        return nodeValue;
    }

    public double[] getWeights() {
        return weights;
    }

    public double getActivationRecord() {
        double temp = 0;

        for (double i : weights) {
            temp += nodeValue * i;
        }
        return activationFunction(temp);
    }

    private double activationFunction(double x) {
//        return 1/(1+Math.pow(Math.E, -x));
        return Math.tanh(x);
    }
}
