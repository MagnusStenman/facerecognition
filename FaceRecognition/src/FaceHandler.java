import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.StringTokenizer;

public class FaceHandler {
    private ArrayList<FaceData> trainingData;
    private ArrayList<FaceData> testTrainingData;
    private ArrayList<Integer[][]> testData;
    private HashMap<String, Integer> facit;

    public FaceHandler(String trainingFile, String trainingFacit, String testFile) throws FileNotFoundException {
        trainingData = readFaceData(trainingFile);
        testTrainingData = splitTrainingData();

        printData(testTrainingData);
        System.out.println(testTrainingData.size() + " test");
        System.out.println(trainingData.size() + " train");
    }

    private ArrayList<FaceData> readFaceData(String faceFile) throws FileNotFoundException {
        java.io.FileReader fr = new java.io.FileReader(faceFile);
        BufferedReader br = new BufferedReader(fr);
        String currentLine = "";
        ArrayList<FaceData> data = new ArrayList<FaceData>();

        try {

            while ((currentLine = jumpToNextImage(br)) != null) {
                String imageID = currentLine;
                //skapa matris
                Integer[][] matrix = new Integer[20][20];
                for (int i = 0; i < 20; i++) {
                    currentLine = br.readLine();

                    StringTokenizer st = new StringTokenizer(currentLine);
                    int j = 0;
                    while (st.hasMoreTokens()) {
                        matrix[i][j] = Integer.parseInt(st.nextToken());
                        j++;
                    }

                    //ta en rad dela pp stoppa
                }
                data.add(new FaceData(imageID, matrix));
            }

        } catch (IOException e) {

        }
        return data;
    }

    private ArrayList<FaceData> splitTrainingData() {
        int testSize = (int) (trainingData.size() * 0.30);
        ArrayList<FaceData> tempList = new ArrayList<FaceData>();

        for (int i = 0; i < testSize; i++) {
            System.out.println(trainingData.get(0).getImageID());
            tempList.add(trainingData.get(0));
            trainingData.remove(0);
        }

        return tempList;
    }

    private HashMap<String, Integer> readFacitFile(String facitFile) {
        return null;
    }

    private String jumpToNextImage(BufferedReader br) throws IOException {
        String tempLine;
        while ((tempLine = br.readLine()) != null && (tempLine.equals("") || tempLine.startsWith("#"))) {
        }

        return tempLine;
    }

    private void printData(ArrayList<FaceData> data) {
        for (FaceData f : data) {
            System.out.println(f.getImageID());
            printMatrix(f.getFaceData());
            System.out.println();
        }

    }

    private void printMatrix(Integer[][] m) {
        System.out.println();
        for (int i = 0; i < 20; i++) {
            for (int j = 0; j < 20; j++) {
                System.out.print(m[i][j] + " ");
            }
            System.out.println();
        }
    }
}
