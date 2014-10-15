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
            weights[i] = -10 + (20) *r.nextDouble();
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

// act på summan mao

//    private int act(int mood) {
//        double sum = 0;
//        for (int i = 0; i < neurons.length; i++) {
//            Neuron neuron = neurons[i];
//            sum += neuron.getGreyScale() * neuron.getWeight(mood);
//        }
//        if (sum > 0.5) {
//            return 1;
//        } else {
//            return 0;
//        }
//    }

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
