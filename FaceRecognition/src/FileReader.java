import javax.sound.midi.Soundbank;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.StringTokenizer;

public class FileReader {
    private ArrayList<Integer[][]> trainingData;
    private List<Integer[][]> testTrainingData;
    private ArrayList<Integer[][]> testData;
    private HashMap<String, Integer> facit;

    public FileReader(String trainingFile, String trainingFacit, String testFile) throws FileNotFoundException {
        trainingData = getFaceData(trainingFile);
        testTrainingData = splitTrainingData();

        System.out.println(trainingData.size());
        System.out.println(testTrainingData.size());
    }

    private ArrayList<Integer[][]> getFaceData(String trainingFile) throws FileNotFoundException {
        java.io.FileReader fr = new java.io.FileReader(trainingFile);
        BufferedReader br = new BufferedReader(fr);
        String currentLine = "";
        ArrayList<Integer[][]> data = new ArrayList<Integer[][]>();

        try {

            while ((currentLine = jumpToNextImage(br)) != null) {
                //skapa matris
                Integer[][] matrix = new Integer[20][20];
                for (int i=0;i<20;i++) {
                    currentLine = br.readLine();

                    StringTokenizer st = new StringTokenizer(currentLine);
                    int j = 0;
                    while (st.hasMoreTokens()) {
                        matrix[i][j] = Integer.parseInt(st.nextToken());
                        j++;
                    }

                    //ta en rad dela pp stoppa
                }
                data.add(matrix);
            }
            printData(data);

        } catch (IOException e) {

        }
        return data;
    }

    private List<Integer[][]> splitTrainingData() {
        int testSize = (int) (trainingData.size()*0.33);
        List<Integer[][]> testTrainingData = trainingData.subList(0,testSize);
        for (int i=0;i<testSize;i++) {
            trainingData.remove(i);
        }
        return testTrainingData;
    }

    private void printData(ArrayList<Integer[][]> data) {

        for (Integer[][] i : data) {
            printMatrix(i);
        }

    }

    private String jumpToNextImage(BufferedReader br) throws IOException {
        String tempLine;
        while ((tempLine = br.readLine()) != null && (tempLine.equals("") || tempLine.startsWith("#"))) {}

        return tempLine;
    }

    private void printMatrix(Integer[][] m) {
        System.out.println();
        for (int i=0;i<20;i++) {
            for (int j=0;j<20;j++) {
                System.out.print(m[i][j] + " ");
            }
            System.out.println();
        }
    }
}
