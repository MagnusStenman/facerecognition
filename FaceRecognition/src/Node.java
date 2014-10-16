import java.util.Random;

public class Node {

    private int nodeValue;
    private double[] weights;
    
    public Node() {
    	weights = new double[4];
        Random r = new Random();
        for (int i=0;i<weights.length;i++) {
            weights[i] = -10 + (20) *r.nextDouble();
        }
    }
   
    public void setWeights(int i, double value) {
        this.weights[i] = value;
    }

    public int getNodeValue() {
        return nodeValue;
    }

    public double[] getWeights() {
        return weights;
    }

	public void setNodeValue(int val) {
		nodeValue = val;
	}
}