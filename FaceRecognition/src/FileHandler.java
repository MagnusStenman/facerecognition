import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.StringTokenizer;

public class FileHandler {

	private static final int IMAGE_SIZE = 20;
	private ArrayList<FaceData> trainingData;
	private ArrayList<FaceData> testData;
	private HashMap<String, Integer> facit;

	public FileHandler(String trainingFile, String trainingFacit,
                       String testFile) throws FileNotFoundException {

		trainingData = readToFaceData(trainingFile);
		testData = readToFaceData(testFile);
		facit = readFacitFile(trainingFacit);
	}

	private ArrayList<FaceData> readToFaceData(String faceFile)
			throws FileNotFoundException {
		BufferedReader br = new BufferedReader(new FileReader(faceFile));

		String currentLine = "";
		ArrayList<FaceData> data = new ArrayList<FaceData>();

		try {
			while ((currentLine = jumpToNextImage(br)) != null) {
				String imageID = currentLine;
				int[][] matrix = new int[IMAGE_SIZE][IMAGE_SIZE];
				for (int i = 0; i < IMAGE_SIZE; i++) {
					currentLine = br.readLine();

					StringTokenizer st = new StringTokenizer(currentLine);
					int j = 0;
					while (st.hasMoreTokens()) {
						matrix[i][j] = Integer.parseInt(st.nextToken());
						j++;
					}
				}
				data.add(new FaceData(imageID, matrix));
			}
		} catch (IOException e) {
			System.err.println(e.getMessage());
		}
		return data;
	}

	private HashMap<String, Integer> readFacitFile(String facitFile)
			throws FileNotFoundException {
		BufferedReader br = new BufferedReader(new FileReader(facitFile));
		String currentLine;
		HashMap<String, Integer> tempMap = new HashMap<String, Integer>();

		try {
			while ((currentLine = jumpToNextImage(br)) != null) {
				StringTokenizer st = new StringTokenizer(currentLine);
				if (st.countTokens() == 2) {
					tempMap.put(st.nextToken(),
							Integer.parseInt(st.nextToken()));
				} else {
					System.err.println("Error in file format: <" + facitFile
							+ ">");
				}
			}
		} catch (IOException e) {
			System.err.println(e.getMessage());
		}
		return tempMap;
	}

	private String jumpToNextImage(BufferedReader br) throws IOException {
		String tempLine;
		while ((tempLine = br.readLine()) != null
				&& (tempLine.equals("") || tempLine.startsWith("#"))) {
		}
		return tempLine;
	}

	public ArrayList<FaceData> getTestData() {
		return testData;
	}

	public ArrayList<FaceData> getTrainingData() {
		return trainingData;
	}

	public HashMap<String, Integer> getFacit() {
		return facit;
	}
}