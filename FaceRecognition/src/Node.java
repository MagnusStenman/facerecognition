import java.util.Random;

public class Node {

    private int nodeValue;
    private double[] weights;
    private double[] activation;
    
    public Node() {
    	weights = new double[4];
        activation = new double[4];
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

    public void calculateActivation() {
    	for(int i = 0; i < weights.length; i++) {
    		activation[i] = activationFunction(nodeValue*weights[i]);
    	}
    }
    /*
    public double getActivationRecord() {
        double temp = 0;

        for (double i : weights) {
            temp += nodeValue * i;
        }
        return activationFunction(temp);
    }
    */

    private double activationFunction(double x) {
//        return 1/(1+Math.pow(Math.E, -x));
        if (x > 0) {
            return 1;
        } else {
            return 0;
        }
//        return Math.tanh(x);
    }

	public double[] getAct() {
		return activation;
	}

	@Override
	public String toString() {

		return ""+weights[0]+ " "+weights[1]+" "+weights[2]+" "+weights[3];
	}


	public void setNodeValue(int i) {
		nodeValue = i;
	}
}
