import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class FaceHandler {
    private ArrayList<FaceData> trainingData;
    private ArrayList<FaceData> testTrainingData;
    private ArrayList<FaceData> testData; //TODO
    private HashMap<String, Integer> facit;

    public FaceHandler(String trainingFile, String trainingFacit, String testFile) throws FileNotFoundException {
        trainingData = readFaceData(trainingFile);
        testTrainingData = splitTrainingData();
        facit = readFacitFile(trainingFacit);
        printData(testTrainingData);
        System.out.println(testTrainingData.size() + " test");
        System.out.println(trainingData.size() + " train");
        printMap(facit);
    }

    private ArrayList<FaceData> readFaceData(String faceFile) throws FileNotFoundException {
        BufferedReader br = new BufferedReader(new FileReader(faceFile));
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
            tempList.add(trainingData.get(0));
            trainingData.remove(0);
        }
        return tempList;
    }

    private HashMap<String, Integer> readFacitFile(String facitFile) throws FileNotFoundException {
        BufferedReader br = new BufferedReader(new FileReader(facitFile));
        String currentLine;
        HashMap<String, Integer> tempMap = new HashMap<String, Integer>();

        try {
            while ((currentLine = jumpToNextImage(br)) != null) {
                StringTokenizer st = new StringTokenizer(currentLine);
                if (st.countTokens() == 2) {
                    tempMap.put(st.nextToken(), Integer.parseInt(st.nextToken()));
                } else {
                    System.err.println("Error in file format: <" + facitFile + ">");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return tempMap;
    }

    private String jumpToNextImage(BufferedReader br) throws IOException {
        String tempLine;
        while ((tempLine = br.readLine()) != null && (tempLine.equals("") || tempLine.startsWith("#"))) {
        }

        return tempLine;
    }

    public ArrayList<FaceData> getTestTrainingData() {
        return testTrainingData;
    }

    public HashMap<String, Integer> getFacit() {
        return facit;
    }

    private void printData(ArrayList<FaceData> data) {
        for (FaceData f : data) {
            System.out.println(f.getImageID());
            printMatrix(f.getFaceData());
            System.out.println();
        }
    }

    private void printMap(HashMap<String, Integer> map) {
        for (Map.Entry<String, Integer> e : map.entrySet()) {
            System.out.println(e.getKey() + ", " + e.getValue());
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
